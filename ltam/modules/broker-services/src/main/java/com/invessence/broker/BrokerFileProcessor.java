package com.invessence.broker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.broker.bean.*;
import com.invessence.broker.dao.*;
import com.invessence.broker.util.*;
import com.invessence.util.EmailCreator;
import com.jcraft.jsch.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.*;
import org.springframework.stereotype.Component;
import sun.security.krb5.internal.ccache.CredentialsCache;

/**
 * Created by abhangp on 1/17/2016.
 */
@Component
public class BrokerFileProcessor
{
   private static final Logger logger = Logger.getLogger(BrokerFileProcessor.class);

   @Autowired
   CommonDao commonDao;

   @Autowired
      EmailCreator emailCreator;

//   @Autowired
//   protected MessageSource resource;
//
//   protected String getMessage(String code, Object[] object, Locale locale) {
//      return resource.getMessage(code, object, locale);
//   }
   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   SimpleDateFormat sdfFileParsing = new SimpleDateFormat("yyyyMMdd");

   String baseDirectory;
   String eodProcedure;

   public BrokerFileProcessor(String _baseDirectory, String _eodProcedure){
      this.baseDirectory=_baseDirectory;
      this.eodProcedure=_eodProcedure;
   }

   public void process()
   {
      logger.info("BaseDirectory :" + baseDirectory);
      logger.info("EodProcedure :" + eodProcedure);
      StringBuilder mailAlertMsg=null;
      File localDirectory=null;
      try {
         mailAlertMsg=new StringBuilder();
         //logger.info("Parameters"+Parameters.sqlInsertNewaccounts);
         Map<String, DBParameters> dbParamMap = commonDao.getDBParametres();
         logger.info("BUSINESS_DATE :" + dbParamMap.get("BUSINESS_DATE").getValue());
         if(dbParamMap==null || dbParamMap.size()==0 || ! dbParamMap.containsKey("BUSINESS_DATE")){
            mailAlertMsg.append("Required DB parameters not available");
            logger.info("Required DB parameters not available");
         }else {
            List<BrokerHostDetails> hostLst = commonDao.getBrokerHostDetails("");
            if (hostLst == null && hostLst.size() == 0)
            {
               mailAlertMsg.append("Required Host details are not available");
               logger.info("Required Host details are not available");
            }else{
               Iterator<BrokerHostDetails> hostDetailsItr = hostLst.iterator();
               while (hostDetailsItr.hasNext())
               {
               BrokerHostDetails hostDetails = (BrokerHostDetails) hostDetailsItr.next();
               logger.info(hostDetails.toString());
               List<DownloadFileDetails> downloadFilesLst = commonDao.getDownloadFileDetails("where active = 'Y' and vendor='" + hostDetails.getVendor() + "'");
                  if(downloadFilesLst == null && downloadFilesLst.size() == 0)
                  {
                     mailAlertMsg.append("Download files are not available for broker :"+hostDetails.getVendor());
                     logger.info("Download files are not available for broker :" + hostDetails.getVendor());
                  }else{
                     try
                     {
                        JSch jsch = new JSch();
                        Session session = null;
                        session = jsch.getSession(hostDetails.getUsername(), hostDetails.getHost(), 22);
                        session.setPassword(hostDetails.getPassword());
                        session.setConfig("StrictHostKeyChecking", "no");
                        session.connect();

                        logger.info("Established the connection with host server");

                        ChannelSftp channel = null;
                        channel = (ChannelSftp) session.openChannel("sftp");
                        channel.connect();
                        try{
                           channel.cd(hostDetails.getSourcedir());
                           Iterator<DownloadFileDetails> downloadFilesItr = downloadFilesLst.iterator();
                           while (downloadFilesItr.hasNext()) {

                              DownloadFileDetails downloadFileDetails = (DownloadFileDetails) downloadFilesItr.next();
                              logger.info(downloadFileDetails.toString());
                              boolean mayDownloadFile=false;
                              if(Constants.FILE_PROCESS_DAILY.equalsIgnoreCase(downloadFileDetails.getAvailable())){
                                 mayDownloadFile=true;
                              }else  if(Constants.FILE_PROCESS_MONTHLY.equalsIgnoreCase(downloadFileDetails.getAvailable())){
                                 if (CommonUtil.todaysDateCompare(dbParamMap.get("1ST_BDATE_THIS_MONTH").getValue().toString())==true) {
                                    mayDownloadFile=true;
                                 }else{
                                    mailAlertMsg.append("To process MONTHLY files today is not First Business day");
                                    logger.info("To process MONTHLY files today is not First Business day");
                                 }
                              }
                              if(mayDownloadFile)
                              {
                                 List<String> fileNameLst = new ArrayList<String>();
                                 Vector v = channel.ls(downloadFileDetails.getFileName() + "*");
                                 ChannelSftp.LsEntry entry = null;
                                 for (int i = 0; i < v.size(); i++)
                                 {
                                    entry = (ChannelSftp.LsEntry) v.get(i);
                                    fileNameLst.add(entry.getFilename());
                                 }
                                 logger.info("Fetching list of " + downloadFileDetails.getFileName() + " files from server");
                                 if (fileNameLst == null || fileNameLst.size() == 0)
                                 {
                                    mailAlertMsg.append(downloadFileDetails.getFileName() + " files are not available on server for download.\n");
                                    logger.info(downloadFileDetails.getFileName() + " files are not available on server for download.");
                                 }
                                 else
                                 {
                                    List<String> filesToLoad = getFilesToLoad(fileNameLst, sdfFileParsing.parse("" + dbParamMap.get("BUSINESS_DATE").getValue()), downloadFileDetails.getFileName());
                                    if (filesToLoad == null || filesToLoad.size() == 0)
                                    {
                                       mailAlertMsg.append(downloadFileDetails.getFileName() + " files are not available on server to load.\n");
                                       logger.info(downloadFileDetails.getFileName() + " files are not available on server to load.");
                                    }
                                    else
                                    {
                                       Iterator<String> itr = filesToLoad.iterator();
                                       while (itr.hasNext())
                                       {
                                          String fileToDownload = (String) itr.next();
                                          try
                                          {
                                             logger.info("Downloading :" + fileToDownload + " file.");
                                             InputStream in = channel.get(fileToDownload);
                                             // setting local file
                                             localDirectory = new File(baseDirectory + "/" + downloadFileDetails.getDownloadDir() + "/");
                                             logger.info("Local directory path to stored the files :" + localDirectory);
                                             // if the directory does not exist, create it
                                             if (!localDirectory.exists())
                                             {
                                                try
                                                {
                                                   logger.info("Creating local directory :" + localDirectory);
                                                   localDirectory.mkdirs();
                                                }
                                                catch (Exception e)
                                                {
                                                   logger.error("Creating local directory :" + localDirectory);
                                                }
                                             }
                                             String localFileName = localDirectory + "/" + fileToDownload;

                                             try
                                             {
                                                FileOutputStream tergetFile = new FileOutputStream(localFileName);
                                                logger.info("Reading contents of remote file to local");
                                                int c;
                                                while ((c = in.read()) != -1)
                                                {
                                                   tergetFile.write(c);
                                                }

                                                in.close();
                                                tergetFile.close();
                                                tergetFile.flush();
                                                if (downloadFileDetails.getFormat().equalsIgnoreCase("csv"))
                                                {
                                                   try
                                                   {
                                                      processCsvFile(localFileName, downloadFileDetails);
                                                   }
                                                   catch (Exception e)
                                                   {
                                                      logger.error("While " + fileToDownload + " csv file processing");
                                                      exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                                   }
                                                }
                                             }
                                             catch (Exception e)
                                             {
                                                logger.error("While " + fileToDownload + " file reading into local directory");
                                                exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping into local directory");
                                             }

                                          }
                                          catch (Exception e)
                                          {
                                             logger.error("While " + fileToDownload + " file coping from server");
                                             exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping from server");
                                          }
                                       }

                                    }
                                 }


                                 if (fileNameLst == null || fileNameLst.size() == 0){
                                    logger.info(downloadFileDetails.getFileName() + " files are not available on server to delete.");
                                 }
                                 else
                                 {
                                    try
                                    {
                                       Calendar calendar = Calendar.getInstance();
                                       calendar.setTime(sdfFileParsing.parse("" + dbParamMap.get("BUSINESS_DATE").getValue()));
                                       calendar.add(Calendar.DATE, -30);
                                       Date lastDate = calendar.getTime();
                                       List<String> filesToDelete = getFilesToDelete(fileNameLst, lastDate, downloadFileDetails.getFileName());
                                       if (filesToDelete == null || filesToDelete.size() == 0){

                                          logger.info(downloadFileDetails.getFileName() + " files are not available on server to delete.");
                                       }else
                                       {
                                          Iterator<String> itr = filesToDelete.iterator();
                                          while (itr.hasNext())
                                          {
                                             String fileToDelete = (String) itr.next();
                                             logger.info("Deleting file :" + fileToDelete);
                                             channel.rm(fileToDelete);
                                          }
                                       }
                                    }
                                    catch (Exception e)
                                    {
                                       e.printStackTrace();
                                    }
                                 }
                              }
                           }
                           channel.disconnect();
                           session.disconnect();

                        }catch (Exception e){
                           logger.error("Source directory not available on Server");
                           logger.error(e.getStackTrace());
                        }

                     }catch (Exception e){
                        logger.error("While connecting to host server");
                        logger.error(e.getStackTrace());
                     }
                  }
               }
            }
         }
      } catch (Exception e) {
         logger.error("While processing files");
         logger.error(e.getStackTrace());
      }
      if( mailAlertMsg.length() > 0)
      {
         logger.info("MailAlertMsg :"+ mailAlertMsg);
         try
         {
            logger.info("Sending email to support team");
            //emailCreator.sendToSupport("", "Broker File Upload Process", mailAlertMsg.toString());
         }catch (Exception e)
         {
            logger.error("While email processing");
            logger.error(e.getStackTrace());
         }
      }else
      {
         try
         {
            logger.info("Calling EOD process");
            commonDao.callEODProcess(eodProcedure);
         }
         catch (Exception e)
         {
            exceptionHandler(e, mailAlertMsg, "While calling EOD process");
            logger.error("Calling EOD process");
            logger.error(e.getStackTrace());
         }
      }
   }

   private List<String> getFilesToLoad(List<String> fileNameLst, Date businessDate, String fileName){
      List<String> fileLstToLoad = null;
      logger.info("Checking "+fileName+" files to load into DB for business date :"+businessDate);
      try{
         fileLstToLoad=new ArrayList<>();
         Iterator<String> itr=fileNameLst.iterator();
         while(itr.hasNext()){
            String fileToLoadDB=(String)itr.next();

            String strDate=fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_")+1,fileToLoadDB.lastIndexOf("."));
            try
            {
               Date date=sdfFileParsing.parse(fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_") + 1, fileToLoadDB.lastIndexOf(".")));
               if(date.equals(businessDate) || date.after(businessDate)){
                  fileLstToLoad.add(fileToLoadDB);
                  logger.info("File to load into DB :"+fileToLoadDB);
               }
            }catch (Exception e)
            {
               logger.error("Date parsing issue");
               logger.error(e.getStackTrace());
            }
         }

      }catch (Exception e){
         logger.error("Checking "+fileName+" files to load into DB for business date :"+businessDate);
         logger.error(e.getStackTrace());
      }
      return fileLstToLoad;
   }


   private List<String> getFilesToDelete(List<String> fileNameLst, Date lastDate, String fileName){
      List<String> filesToDelete = null;
      logger.info("Checking "+fileName+" files to delete from server before business date :"+lastDate);
      try{

         filesToDelete=new ArrayList<>();
         Iterator<String> itr=fileNameLst.iterator();
         while(itr.hasNext()){
            String fileToDelete=(String)itr.next();

            String strDate=fileToDelete.substring(fileToDelete.lastIndexOf("_")+1,fileToDelete.lastIndexOf("."));
            try
            {
               Date date=sdfFileParsing.parse(fileToDelete.substring(fileToDelete.lastIndexOf("_") + 1, fileToDelete.lastIndexOf(".")));

               if(date.before(lastDate)){
                  filesToDelete.add(fileToDelete);
                  logger.info("File to delete :"+fileToDelete);
               }
            }catch (Exception e)
            {
               logger.error("Date parsing issue");
               logger.error(e.getStackTrace());
            }
         }

      }catch (Exception e){
         logger.error("Checking "+fileName+" files to delete from server before business date :"+lastDate);
         logger.error(e.getStackTrace());
      }
      return filesToDelete;
   }

   private void processCsvFile(String csvFile,DownloadFileDetails fileDetails)throws FileNotFoundException, IOException, Exception{

      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";

      try
      {
         StringBuilder sb = null;
         br = new BufferedReader(new FileReader(csvFile));
         List<String[]> inLst = new LinkedList<String[]>();
         while ((line = br.readLine()) != null)
         {
            if (!line.equals(""))
            {
               String[] lineArr = line.split(cvsSplitBy);
               if(lineArr.length>fileDetails.getKeyData())
               {
                  if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
                  {
                     inLst.add(lineArr);
                  }
               }
            }
         }
         if(fileDetails.getContainsheader().equalsIgnoreCase("Y") && inLst !=null && inLst.size() > 0)
         {
            logger.info("Removing header row of "+fileDetails.getFileName()+" file");
            inLst.remove(0);
         }
         if(inLst.size()>0)
         {
            StringBuilder insertQuery=new StringBuilder("insert into "+fileDetails.getTmp_TableName()+" values (");
            int inColLen=inLst.get(0).length;
            for(int i=1; i<=inColLen; i++){
               insertQuery.append("?"+(i!=inColLen?",":")"));
            }
            logger.info("insertQuery :" + insertQuery);
            commonDao.trancateTable(fileDetails.getTmp_TableName());
            commonDao.insertBatch(inLst, insertQuery.toString(), fileDetails.getPostInstruction());
         }else
         {
            logger.info(fileDetails.getFileName()+" file is empty");
            if(fileDetails.getContainsheader().equalsIgnoreCase("N")) {
               throw new FileEmptyException(fileDetails.getFileName() + " file is empty");
            }
         }
      }finally {
         if (br != null) {try { br.close(); } catch (IOException e) {  /*e.printStackTrace();*/}}
      }
   }

//   private void processCsvFile(){
//      try{
//
//      }catch (Exception e){
//         e.printStackTrace();
//      }
//   }


   public void exceptionHandler(Exception ex, StringBuilder mailAlertMsg, String process){
      Map<String, Object> errorDetails=new HashMap<String, Object>();
      try
      {

         logger.error("Exception Class :" + ex.getClass());
         //ex.printStackTrace();
         logger.error(ex.getMessage());
         logger.error(CommonUtil.stackTraceToString(ex.getStackTrace()));

         if(ex instanceof FileEmptyException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
            logger.error(process+" : " + ex.getMessage());
         }else  if(ex instanceof FileNotFoundException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
            logger.error(process+" : " + ex.getMessage());
         }else if(ex instanceof MySQLIntegrityConstraintViolationException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
            logger.error(process+" : " + ex.getMessage());
         }else if(ex instanceof BadSqlGrammarException)
         {
            mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
            logger.error(process + " : " + ex.getMessage());
         }else if(ex instanceof CannotGetJdbcConnectionException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
            logger.error(process+" : " + ex.getMessage());
         }else
         {
            mailAlertMsg.append(process+" : " +  ex.getMessage() + "\n");
            logger.error(process+" : " + ex.getMessage());
         }
      }catch(Exception e){
         mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
         logger.error(process + " : " + ex.getMessage());
         //ex.printStackTrace();
      }
   }
   public static String unescape(String data)
   {
      StringBuilder buffer = new StringBuilder(data.length());
      for (int i = 0; i < data.length(); i++) {
         if (data.charAt(i) > '?') {
            buffer.append("\\u").append(Integer.toHexString(data.charAt(i)));
         } else if (data.charAt(i) == '\n') {
            buffer.append("\\n");
         } else if (data.charAt(i) == '\t') {
            buffer.append("\\t");
         } else if (data.charAt(i) == '\r') {
            buffer.append("\\r");
         } else if (data.charAt(i) == '\b') {
            buffer.append("\\b");
         } else if (data.charAt(i) == '\f') {
            buffer.append("\\f");
         } else if (data.charAt(i) == '\'') {
            buffer.append("\\'");
         } else if (data.charAt(i) == '"') {
            buffer.append("\\\"");
         } else if (data.charAt(i) == '\\') {
            buffer.append("\\\\");
         } else {
            buffer.append(data.charAt(i));
         }
      }
      return buffer.toString();
   }
}
