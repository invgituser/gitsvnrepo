package com.invessence.broker.processors;

import java.io.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.*;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.invessence.broker.dao.FileProcessingConfig;
import com.invessence.broker.filters.DataFileInfo;

public class FileProcessor {
    private String localDir;
    private Map<String, DataFileInfo> processingConfig;
    private DriverManagerDataSource dataSource;
    private String brokerDateToUpdate;
    private Logger logger = Logger.getLogger(FileProcessor.class.getName());
    private String reportLocalDir;

    public FileProcessor(String localDir, FileProcessingConfig processingConfig, DriverManagerDataSource dataSource,
            String reportLocalDir) {
        this.localDir = localDir;
        this.processingConfig = processingConfig.getFileProcessMap();
        this.reportLocalDir = reportLocalDir;
        this.dataSource = dataSource;
    }

    public static boolean downloadSingleFile(FTPSClient ftpsClient, String remoteFilePath, String savePath)
            throws IOException {
        File downloadFile = new File(savePath);

        File parentDir = downloadFile.getParentFile();
        if (!parentDir.exists()) {
            parentDir.mkdir();
        }

        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
        try {
           ftpsClient.setFileType(FTP.BINARY_FILE_TYPE);
            return ftpsClient.retrieveFile(remoteFilePath, outputStream);
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

    public void process(FTPFile remoteFile, FTPSClient ftpsClient) throws IOException {
        if (remoteFile.isFile()) {
            String[] splitName = remoteFile.getName().split("\\.");
            if (splitName.length > 2) {
                if (processingConfig.containsKey(splitName[1])) {
                    String fileName = processingConfig.get(splitName[1]).getFilename();
                    if (splitName[1].equalsIgnoreCase(fileName)) {
                        logger.info("Getting File:" + remoteFile.getName());
                        DataFileInfo dataFileInfo = processingConfig.get(fileName);

                        logger.info("Backup File to:" + dataFileInfo.getDownloadDir());
                        dataFileInfo.setDownloaded(true);
                        File localFile = copyRemoteFileToLocalDir(remoteFile, dataFileInfo.getDownloadDir(), ftpsClient);
                        if (!dataFileInfo.getPostProcess().equalsIgnoreCase("WEB")) {
                            uploadFileContentsToDB(localFile, dataFileInfo);
                        } else {
                            if (remoteFile.getSize() > 0)
                                dataFileInfo.setNumOfRecords(1);
                        }

                        // Update the date in the DB .. for daily files it BROKER_BDATE .. what about monthly?
                        brokerDateToUpdate = FileProcessorUtil.extractDateFromName(remoteFile.getName());
                    }
                }
            }
        }
    }

    private void uploadFileContentsToDB(File localFile, DataFileInfo dataFileInfo) throws IOException {
        byte[] bytes = IOUtils.toByteArray(new FileInputStream(localFile.getAbsolutePath()));
        List<String> lines = convertToLines(bytes);
        Connection connection = null;
        Statement statement = null;
        Boolean dataLoaded = false;
        String sql;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            connection.setAutoCommit(true);
            if (lines.size() > 0) {
                // First delete all data from tmp table.
                sql = "truncate table " + dataFileInfo.getTmp_tableName();
                statement.executeUpdate(sql);
                // connection.commit();
                dataLoaded = true;
                logger.info("Upload:" + dataFileInfo.getTmp_tableName());
                String tableName = dataFileInfo.getTmp_tableName();
                dataFileInfo.setNumOfRecords(lines.size());
                for (String line : lines) {
                    String[] fields = line.split(",");
                    String sqlFields = Joiner.on(",").join(fields);

                    // Insert into table...
                    sql = "insert into " + tableName + " values (" + sqlFields + ")";
                    statement.executeUpdate(sql);
                    // connection.commit();
                }
            }
        } catch (SQLException e) {
            logger.error("Error uploading contents of " + localFile.getAbsolutePath() + " to DB", e);
        } finally {
            DbUtils.closeQuietly(statement);
            DbUtils.closeQuietly(connection);
        }

        if (dataLoaded) {
            logger.info("SP: " + dataFileInfo.getPostInstruction());
            invokeStoredProcToUploadDataFromTmpTable(dataFileInfo.getPostInstruction());
        }
    }

    private List<String> convertToLines(byte[] bytes) {
        BufferedReader bufferedReader = null;
        List<String> lines = new LinkedList<String>();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                lines.add(temp);
            }
        } catch (IOException e) {
            logger.error("Error reading contents of remote file", e);
        } finally {
            IOUtils.closeQuietly(bufferedReader);
        }
        return lines;
    }

    private File copyRemoteFileToLocalDir(FTPFile remoteFile, String downloadDir, FTPSClient ftpsClient) {

        File downloadLocation = new File(localDir, downloadDir);

        if (!downloadLocation.exists()) {
            downloadLocation.mkdir();
        }

        File localFile = new File(downloadLocation, remoteFile.getName());
        try {
            OutputStream output = new FileOutputStream(localFile);
           ftpsClient.retrieveFile(remoteFile.getName(), output);
            output.close();
        } catch (IOException e) {
            logger.error("Error reading contents of remote file " + remoteFile.getName(), e);
        }
        return localFile;
    }

    public void postProcess() {
       updateBrokerDate();
       doeodprocess();
    }

    private void updateBrokerDate() {
        if (brokerDateToUpdate != null) {
            try {
                MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource().addValue("p_name",
                        "BROKER_BDATE").addValue("p_value", brokerDateToUpdate);
                new SimpleJdbcCall(dataSource).withProcedureName("sp_invessence_switch_eod_process").execute(
                        sqlParameterSource);
                logger.info("Updated broker date to " + brokerDateToUpdate);
            } catch (Exception e) {
                logger.error("Unable to update IB EOD Broker Date", e);
            }
        }
    }

   private void doeodprocess() {
         try {
            new SimpleJdbcCall(dataSource).withProcedureName("sp_eod_process").execute();
            logger.info("Do final eod process ");
         } catch (Exception e) {
            logger.error("Unable to complete the EOD process", e);
         }
   }
    private void invokeStoredProcToUploadDataFromTmpTable(String storedProcName) {
        try {
            new SimpleJdbcCall(new JdbcTemplate(dataSource)).withProcedureName(storedProcName).execute(
                    Maps.newHashMap());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void retrieveReports(List<FTPFile> dirToRetrieve, FTPSClient ftpsClient) throws IOException {
        File localReportDir = new File(reportLocalDir);
        if (!localReportDir.exists()) {
            localReportDir.mkdir();
        }
        {
            for (FTPFile remoteDir : dirToRetrieve) {
                File localDir = new File(reportLocalDir, remoteDir.getName());
                if (localDir.exists()) {
                    continue;
                }
                downloadDirectory(ftpsClient, remoteDir.getName(), "", reportLocalDir);

            }
        }
    }

    public void downloadDirectory(FTPSClient ftpsClient, String parentDir, String currentDir, String saveDir)
            throws IOException {
        String dirToList = parentDir;
        if (!currentDir.equals("")) {
            dirToList += "/" + currentDir;
        }

        FTPFile[] subFiles = ftpsClient.listFiles(dirToList);

        if (subFiles != null && subFiles.length > 0) {
            for (FTPFile aFile : subFiles) {
                String currentFileName = aFile.getName();
                if (currentFileName.equals(".") || currentFileName.equals("..")) {
                    // skip parent directory and the directory itself
                    continue;
                }
                String filePath = parentDir + "/" + currentDir + "/" + currentFileName;
                if (currentDir.equals("")) {
                    filePath = parentDir + "/" + currentFileName;
                }

                String newDirPath = saveDir + parentDir + File.separator + currentDir + File.separator
                        + currentFileName;
                if (currentDir.equals("")) {
                    newDirPath = saveDir + parentDir + File.separator + currentFileName;
                }

                if (aFile.isDirectory()) {
                    // create the directory in saveDir
                    File newDir = new File(newDirPath);
                    boolean created = newDir.mkdirs();
                    if (created) {
                        System.out.println("CREATED the directory: " + newDirPath);
                    } else {
                        System.out.println("COULD NOT create the directory: " + newDirPath);
                    }

                    // download the sub directory
                    downloadDirectory(ftpsClient, dirToList, currentFileName, saveDir);
                } else {
                    // download the file
                    boolean success = downloadSingleFile(ftpsClient, filePath, newDirPath);
                    if (success) {
                        System.out.println("DOWNLOADED the file: " + filePath);
                    } else {
                        System.out.println("COULD NOT download the file: " + filePath);
                    }
                }
            }
        }
    }
}
