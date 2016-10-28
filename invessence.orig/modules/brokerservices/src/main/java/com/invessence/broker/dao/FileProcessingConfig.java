package com.invessence.broker.dao;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.*;

import com.invessence.broker.filters.*;

public class FileProcessingConfig {
    Logger logger = Logger.getLogger(FileProcessingConfig.class.getName());
    private DateFilter dateFilter;
    private Map<String, DataFileInfo> fileProcessMap = new HashMap<String, DataFileInfo>();

    public void setDataSource(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.query("select * from download_files where active = 'Y'", new Object[] {},
                new RowMapper<DataFileInfo>() {
                    @Override
                    public DataFileInfo mapRow(ResultSet resultSet, int i) throws SQLException {
                        String vendor = resultSet.getString("vendor");
                        String fileName = resultSet.getString("filename");
                        String tmp_tableName = resultSet.getString("tmp_tableName");
                        String available = resultSet.getString("available");
                        String sourcePath = resultSet.getString("sourcepath");
                        String downloadDir = resultSet.getString("downloaddir");
                        String format = resultSet.getString("format");
                        String required = resultSet.getString("required");
                        String canBeEmpty = resultSet.getString("canbeempty");
                        String postProcess = resultSet.getString("postProcess");
                        String postInstruction = resultSet.getString("postInstruction");

                        DataFileInfo dataFileInfo = new DataFileInfo(vendor, fileName, tmp_tableName, format, required,
                                canBeEmpty, available, sourcePath, postProcess, postInstruction, downloadDir);
                        fileProcessMap.put(fileName, dataFileInfo);
                        return dataFileInfo;
                    }
                });
    }

    public void setDateFilter(DateFilter dateFilter) {
        this.dateFilter = dateFilter;
    }

    public List<FTPFile> filterFiles(List<FTPFile> remoteFiles) {
        List<FTPFile> filteredList = new LinkedList<FTPFile>();

        for (FTPFile remoteFile : remoteFiles) {
            String fileName = remoteFile.getName();
            String splitName = fileName.split("\\.")[1];
            DataFileInfo dataFileInfo = fileProcessMap.get(splitName);
            if (dataFileInfo != null) {
                boolean timeStampAcceptable = isTimeStampAcceptable(fileName, dataFileInfo);
                if (timeStampAcceptable) {
                    filteredList.add(remoteFile);
                    logger.info("Accepting File: " + fileName);
                }
            } else {
                logger.info("No data base configuration for file " + remoteFile.getName());
            }
        }
        return filteredList;
    }

    private boolean isTimeStampAcceptable(String downloadedFileName, DataFileInfo dataFileInfo) {
        if (dataFileInfo.getAvailable().equals("DAILY")) {
            return isAfterBrokerBusinessDate(downloadedFileName);
        } else {
            return dateFilter.acceptFirstDayOfMonth(downloadedFileName);
        }
    }

    private boolean isAfterBrokerBusinessDate(String downloadedFileName) {
        return dateFilter.accept(downloadedFileName);
    }

    public Map<String, DataFileInfo> getFileProcessMap() {
        return fileProcessMap;
    }

    public List<FTPFile> filterDirectories(List<FTPFile> remoteDirs) {
        List<FTPFile> filteredList = new LinkedList<FTPFile>();

        for (FTPFile remoteDir : remoteDirs) {
            String dirName = remoteDir.getName();
            if (dirName.length() == 8 && isAfterBrokerBusinessDate(dirName)) {
                filteredList.add(remoteDir);
            }
        }
        return filteredList;
    }
}
