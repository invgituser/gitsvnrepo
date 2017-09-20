package com.invessence.fileProcessor.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.fileProcessor.bean.DBParameters;
import com.invessence.fileProcessor.util.GPGUtil;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.util.*;
import com.invessence.util.EncryDecryAES;
import com.jcraft.jsch.*;
import com.jcraft.jsch.Logger;
import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 4/25/2017.
 */
@Component
public class FileUploader
{

   @Autowired
   GPGUtil gpgUtil;

   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileUploader.class);

   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   SimpleDateFormat sdfFileParsing = new SimpleDateFormat("yyyyMMdd");

   File localDirectory = null;

   public void upload(ServiceRequest serviceRequest, FileDetails fileDetails, LinkedHashMap<String,FileRules> fileRules, StringBuilder mailAlertMsg, Map<String, DBParameters> dbParamMap){

      try{

         JSch jsch = new JSch();
         Session session = null;
         session = jsch.getSession(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "SFTP_USERNAME"),
                                   ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "SFTP_HOST"), 22);
         session.setPassword(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "SFTP_PASSWORD"));
         session.setConfig("StrictHostKeyChecking", "no");
         session.connect();

         logger.info("Established the connection with host server");

         ChannelSftp channel = null;
         channel = (ChannelSftp) session.openChannel("sftp");
         channel.connect();

         String a1 = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "SFTP_SRC_DIRECTORY")+"/"+fileDetails.getSourcePath();
         channel.cd(a1);
         String filePath=a1+"/"+fileDetails.getFileName()+"_20170509."+fileDetails.getFileExtension();
         System.out.println("filePath = " + filePath);


         List<String> fileNameLst = new ArrayList<String>();
         Vector v = channel.ls(fileDetails.getFileName() + "*" + fileDetails.getFileExtension());

         logger.info("Fetching list of " + fileDetails.getFileName() + "*" + fileDetails.getFileExtension() + " files from server" + v.size());
         ChannelSftp.LsEntry entry = null;
         for (int i = 0; i < v.size(); i++)
         {
            entry = (ChannelSftp.LsEntry) v.get(i);
            fileNameLst.add(entry.getFilename());
         }
         logger.info("Fetching list of " + fileDetails.getFileName() + " files from server" + v.size());
         if (fileNameLst == null || fileNameLst.size() == 0)
         {
            if (fileDetails.getRequired().equalsIgnoreCase("Y"))
            {
              // mailAlertMsg.append(fileDetails.getFileName() + " files are not available on server for download, for Advisor " + fileDetails.getVendor() + "\n");
            }
            logger.info(fileDetails.getFileName() + " files are not available on server for download, for Advisor " + fileDetails.getVendor() + "\n");
         }
         else
         {
            Collections.sort(fileNameLst);
            List<String> filesToLoad = getFilesToLoad(fileNameLst, sdfFileParsing.parse("" + dbParamMap.get("BUSINESS_DATE").getValue()), fileDetails.getFileName());
            System.out.print("File Size ["+filesToLoad.size()+"]");
            if (filesToLoad == null || filesToLoad.size() == 0)
            {
               if (fileDetails.getRequired().equalsIgnoreCase("Y"))
               {
                  mailAlertMsg.append(fileDetails.getFileName() + " files are not available on server to load\n");
               }
               logger.info(fileDetails.getFileName() + " files are not available on server to load\n");
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
                     // setting local file --
                     localDirectory = new File(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "LOCAL_SRC_DIRECTORY")+fileDetails.getDownloadDir() + "/");
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
                           logger.error("Creating local directory :" + localDirectory + " \n" + e.getMessage());
                           e.printStackTrace();
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
                        if (fileDetails.getEncryptionMethod() == null || fileDetails.getEncryptionMethod().equals(""))
                        {
//                           if (fileDetails.getLoadFormat().equalsIgnoreCase("csv"))
//                           {
                              try
                              {
                                 processFile(mailAlertMsg, localFileName, fileDetails, ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOWNLOAD_SERVICES.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY"),fileRules );//hostDetails.getEncrDecrKey()
                              }
                              catch (Exception e)
                              {
                                 logger.error("While " + fileToDownload + " csv file processing\n" + e.getMessage());
                                 //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                 mailAlertMsg.append("Issue " + fileToDownload + " csv file processing\n");
                                 e.printStackTrace();
                              }
//                           }
                        }
                        else
                        {
                           //(InputStream in, OutputStream out, InputStream secKeyIn, InputStream pubKeyIn, char[] pass)
                           String decryptedFileName = localDirectory + "/" + fileToDownload.replace(fileDetails.getFileExtension(), fileDetails.getLoadFormat());
                           try
                           {
                              if (fileDetails.getEncryptionMethod().equalsIgnoreCase("pgp"))
                              {
                                 gpgUtil.decryptFile(new FileInputStream(localFileName), new FileOutputStream(decryptedFileName));
                              }
//                              if (fileDetails.getLoadFormat().equalsIgnoreCase("csv"))
//                              {
                                 try
                                 {
                                    processFile(mailAlertMsg, decryptedFileName, fileDetails, ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOWNLOAD_SERVICES.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY"), fileRules);//hostDetails.getEncrDecrKey()
                                    deleteDecryptedFile(decryptedFileName);
                                 }
                                 catch (Exception e)
                                 {
                                    logger.error("While " + fileToDownload + " csv file processing \n" + e.getMessage());
                                    //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                    mailAlertMsg.append("Issue " + fileToDownload + " csv file processing \n");
                                    e.printStackTrace();
                                 }
//                              }
                           }
                           catch (Exception e)
                           {
                              e.printStackTrace();
                           }
                           finally
                           {
                              //deleteDecryptedFile(decryptedFileName);
                           }

                        }
                     }
                     catch (Exception e)
                     {
                        logger.error("While " + fileToDownload + " file reading into local directory\n" + e.getMessage());
                        mailAlertMsg.append("While " + fileToDownload + " file reading into local directory\n" + e.getMessage());
                        //exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping into local directory");
                        e.printStackTrace();
                     }

                  }
                  catch (Exception e)
                  {
                     logger.error("While " + fileToDownload + " file coping from server\n" + e.getMessage());
                     mailAlertMsg.append("While " + fileToDownload + " file coping from server\n" + e.getMessage());
                     //exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping from server");
                     e.printStackTrace();
                  }
               }

            }
         }

//         if (fileNameLst == null || fileNameLst.size() == 0)
//         {
//            logger.info(fileDetails.getFileName() + " files are not available on server to delete.");
//         }
//         else
//         {
//            try
//            {
//               Calendar calendar = Calendar.getInstance();
//               calendar.setTime(sdfFileParsing.parse("" + dbParamMap.get("BUSINESS_DATE").getValue()));
//               calendar.add(Calendar.DATE, -30);
//               Date lastDate = calendar.getTime();
//               List<String> filesToDelete = getFilesToDelete(fileNameLst, lastDate, fileDetails.getFileName());
//               if (filesToDelete == null || filesToDelete.size() == 0)
//               {
//
//                  logger.info(fileDetails.getFileName() + " files are not available on server to delete.");
//               }
//               else
//               {
//                  Iterator<String> itr = filesToDelete.iterator();
//                  while (itr.hasNext())
//                  {
//                     String fileToDelete = (String) itr.next();
//                     logger.info("Deleting file :" + fileToDelete);
//                     channel.rm(fileToDelete);
//                  }
//               }
//            }
//            catch (Exception e)
//            {
//               e.printStackTrace();
//            }
//         }
//      }

         channel.disconnect();
         session.disconnect();

      }catch(Exception e){
         e.printStackTrace();
      }
   }

   private List<String> getFilesToLoad(List<String> fileNameLst, Date businessDate, String fileName)
   {
      List<String> fileLstToLoad = null;
      logger.info("Checking " + fileName + " files to load into DB for business date :" + businessDate);
      try
      {
         fileLstToLoad = new ArrayList<String>();
         Iterator<String> itr = fileNameLst.iterator();
         while (itr.hasNext())
         {
            String fileToLoadDB = (String) itr.next();

            String strDate = fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_") + 1, fileToLoadDB.lastIndexOf("."));
            try
            {
               Date date = sdfFileParsing.parse(fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_") + 1, fileToLoadDB.lastIndexOf(".")));
               if (date.equals(businessDate) )
               {
                  fileLstToLoad.add(fileToLoadDB);
                  logger.info("File to load into DB :" + fileToLoadDB);
               }
            }
            catch (Exception e)
            {
               logger.error("Date parsing issue \n" + e.getMessage());
               logger.error(e.getStackTrace());
            }
         }

      }
      catch (Exception e)
      {
         logger.error("Checking " + fileName + " files to load into DB for business date :" + businessDate + "\n" + e.getMessage());
         logger.error(e.getStackTrace());
      }
      return fileLstToLoad;
   }

   private void processFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key, LinkedHashMap<String,FileRules> fileRules){
      BufferedReader br = null;
      String line = "";
      try{
         if(fileDetails.getLoadFormat().equalsIgnoreCase("CSV")){
            processCsvFile(mailAlertMsg,csvFile, fileDetails,key);
         }else if(fileDetails.getLoadFormat().equalsIgnoreCase("FIXED")){
            processFixedFile(mailAlertMsg, csvFile, fileDetails, key, fileRules);
         }else{
            System.out.println("File load format "+ fileDetails.getLoadFormat()+" not supported");
         }
      }catch (Exception e ){
         logger.error("FileUploader.processFile" +e.getMessage());
         e.printStackTrace();
      }
   }

   private void processFixedFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key, LinkedHashMap<String,FileRules> fileRules)
   {
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";

      try
      {
         br = new BufferedReader(new FileReader(csvFile));
         List<String[]> inLst = new LinkedList<String[]>();
         int counter = 0;
         while ((line = br.readLine()) != null)
         {
            if (!line.equals(""))
            {
               System.out.println("line = " + line);

               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
               while (entries.hasNext())
               {
                  Map.Entry<String, FileRules> entry6 = entries.next();
                  FileRules fileRules1 = (FileRules) entry6.getValue();
                  System.out.println("--"+line.substring(fileRules1.getStartPos()-1, fileRules1.getEndPos()));
                  //System.out.println("fileRules1 = " + fileRules1);
               }


//               String[] lineArr = line.split(cvsSplitBy, -1);
//               if (lineArr.length > fileDetails.getKeyData())
//               {
//                  if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
//                  {
//                     if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
//                     {
//                        logger.info("Avoiding first row to add in db because it's header");
//                     }
//                     else
//                     {
//                        inLst.add(lineArr);
//                     }
//                     counter++;
//                  }
//               }
            }
         }
      }catch (Exception e){
         logger.error("FileUploader.processFixedFile" +e.getMessage());
         e.printStackTrace();
      }
   }
//   private String readValue(String line, int startPos, int endPos){
//
//
//   }

   private void processCsvFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key)
   {
      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";

      try
      {
         try
         {
//            commonDao.truncateTable(fileDetails.getTmpTableName());
            logger.info("Process Csv start");
            logger.info("fileDetails" + fileDetails.toString());

            StringBuilder sb = null;
            try
            {
               br = new BufferedReader(new FileReader(csvFile));
               List<String[]> inLst = new LinkedList<String[]>();
               int counter = 0;
               while ((line = br.readLine()) != null)
               {
                  if (!line.equals(""))
                  {
                     String[] lineArr = line.split(cvsSplitBy, -1);
                     if (lineArr.length > fileDetails.getKeyData())
                     {
                        if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
                        {
                           if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
                           {
                              logger.info("Avoiding first row to add in db because it's header");
                           }
                           else
                           {
                              inLst.add(lineArr);
                           }
                           counter++;
                        }
                     }
                  }
               }
               if (inLst.size() > 0)
               {
                  StringBuilder insertQuery = null;
                  System.out.print("fileDetails.getCanbedups()" + fileDetails.getCanBeDups());
                  if (fileDetails.getCanBeDups().equalsIgnoreCase("Y"))
                  {
                     insertQuery = new StringBuilder("insert ignore into " + fileDetails.getTmpTableName() + " values (");
                  }
                  else
                  {
                     insertQuery = new StringBuilder("insert into " + fileDetails.getTmpTableName() + " values (");
                  }
                  int inColLen = inLst.get(0).length;
                  for (int i = 1; i <= inColLen; i++)
                  {
                     insertQuery.append("?" + (i != inColLen ? "," : ")"));
                  }
                  if (fileDetails.getEncColumns() == null || fileDetails.getEncColumns().trim().equals(""))
                  {
                     logger.info("Encryption columns are not set");
                  }
                  else if (fileDetails.getEncColumns() != null && !fileDetails.getEncColumns().trim().equals(""))
                  {
                     String[] encColumns = fileDetails.getEncColumns().split(",");
                     for (int i = 0; i < inLst.size(); i++)
                     {
                        String[] arr = (String[]) inLst.get(i);
                        for (int j = 0; j < encColumns.length; j++)
                        {
                           try
                           {
                              int val = Integer.parseInt(encColumns[j].trim()) - 1;
                              logger.info(encColumns[j] + ":" + arr.length + ":" + val);
                              if (val <= arr.length)
                              {
                                 arr[val] = EncryDecryAES.encrypt(arr[val], key);//MsgDigester.getMessageDigest(arr[val]);
                              }
                              else
                              {
                                 logger.info("Encryption columns value :" + encColumns[j] + "is not valid \n");
                              }
                           }
                           catch (Exception e)
                           {
                              //mailAlertMsg.append("Encryption columns value :"+encColumns[j]+"is not valid \n"+e.getMessage());
                              logger.error("Encryption columns value :" + encColumns[j] + "is not valid" + e.getMessage());
                           }
                        }
                        inLst.set(i, arr);
                     }
                  }
                  logger.info("insertQuery :" + insertQuery.toString());
                  boolean bFlag = false;
                  try
                  {
//                     bFlag = commonDao.insertBatch(inLst, insertQuery.toString(), fileDetails.getPostInstruction());
                  }
                  catch (Exception e)
                  {
                     bFlag = false;
                     mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
                     logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
                  }
                  logger.error("Batch insertion Flag" + bFlag);
                  if (bFlag)
                  {
                     if (fileDetails.getPostInstruction() == null || fileDetails.getPostInstruction().equals(""))
                     {
                        logger.info("Procedure name is not valid");
                     }
                     else
                     {
                        try
                        {
                           logger.info("Calling post process procedure :" + fileDetails.getPostInstruction());
//                           commonDao.callProcedure(fileDetails.getPostInstruction());
                        }
                        catch (Exception e)
                        {
                           mailAlertMsg.append(" While post process procedure  " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
                           logger.error(" While post process procedure  " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
                        }
                     }
                  }

               }
               else
               {
                  logger.info(fileDetails.getFileName() + " file is empty");
                  if (fileDetails.getCanBeEmpty().equalsIgnoreCase("N"))
                  {
                     mailAlertMsg.append(csvFile + " file is empty \n");
                  }
                  //            logger.info(fileDetails.getFileName()+" file is empty");
                  //            if(fileDetails.getContainsheader().equalsIgnoreCase("N")) {
                  //               throw new FileEmptyException(fileDetails.getFileName() + " file is empty");
                  //            }
               }
            }
            catch (FileNotFoundException e)
            {
               mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
               logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
            }
            catch (IOException e)
            {
               mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
               logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
            }
            logger.info("Process Csv End");
         }
         catch (Exception e)
         {
            mailAlertMsg.append("While delete data from table " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
            logger.error("While delete data from table " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
         }

//         if(fileDetails.getContainsheader().equalsIgnoreCase("Y") && inLst !=null && inLst.size() > 0)
//         {
//            logger.info("Removing header row of "+fileDetails.getFileName()+" file");
//            inLst.remove(0);
//         }

      }
      finally
      {
         if (br != null)
         {
            try
            {
               br.close();
            }
            catch (IOException e)
            {  /*e.printStackTrace();*/}
         }
      }
   }
   private void deleteDecryptedFile(String fileName)
   {
      try
      {
         File fileTodelete = new File(fileName);
         if (fileTodelete.exists())
         {
            logger.info("Deleting decrypted file :" + fileName + ", isFileDeleted:" + fileTodelete.delete());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
