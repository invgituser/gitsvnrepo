package com.invessence.fileProcessor.service;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.fileProcessor.bean.DBParameters;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.fileProcessor.util.*;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.util.*;
import com.invessence.util.EncryDecryAES;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 4/25/2017.
 */
@Component
public class FileUploader
{
   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileUploader.class);

   @Autowired
   FileProcessorDao fileProcessorDao;

   @Autowired
   FileProcessorUtil fileProcessorUtil;

   public boolean upload(ServiceRequest serviceRequest, FileDetails fileDetails, LinkedHashMap<String,FileRules> fileRules, StringBuilder mailAlertMsg, Map<String, DBParameters> dbParamMap){
      ChannelSftp channel = null;
      Session session = null;
      boolean returnValue=false;
      try{

         String businessDate=dbParamMap.get("BUSINESS_DATE").getValue().toString();

         JSch jsch = new JSch();

         session = jsch.getSession(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_SFTP_USERNAME"),
                                   ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_SFTP_HOST"), 22);
         session.setPassword(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_SFTP_PASSWORD"));
         session.setConfig("StrictHostKeyChecking", "no");
         session.connect();

         logger.info("Established the connection with host server");


         channel = (ChannelSftp) session.openChannel("sftp");
         channel.connect();

//         String a1 = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_SFTP_SRC_DIRECTORY")+"/"+fileDetails.getSourcePath();

         Path p= Paths.get(fileProcessorUtil.getFilePath(serviceRequest,fileDetails,"SFTP",businessDate));

         String fileName = p.getFileName().toString();
         String directory = p.getParent().toString().replaceAll("\\\\","/");
         System.out.println("directory = " + directory+ " fileName = " + fileName);

         channel.cd(directory);
//         String filePath=a1+"/"+fileDetails.getFileName()+"_"+sdfFileParsing.parse("" + dbParamMap.get("BUSINESS_DATE").getValue())+"."+fileDetails.getFileExtension();
//         System.out.println("filePath = " + filePath);


         List<String> fileNameLst = new ArrayList<String>();
         StringBuilder searchString=new StringBuilder();
         if(fileDetails.getFileNameAppender().equalsIgnoreCase("PREFIX")){
            searchString.append("*_").append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
         }else if(fileDetails.getFileNameAppender().equalsIgnoreCase("POSTFIX")){
            searchString.append(fileDetails.getFileName()).append("_*").append("."+fileDetails.getFileExtension());
         }else{
            searchString.append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
         }
         System.out.println("SearchString = " + searchString +" to fetch files from Server.");
         Vector v = channel.ls(searchString.toString());

         logger.info("Fetching list of " + searchString.toString() + " files from server" + v.size());
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
               mailAlertMsg.append(fileDetails.getFileName() + " files are not available on server for download, for Advisor " + fileDetails.getVendor() + "\n");
            }
            logger.info(fileDetails.getFileName() + " files are not available on server for download, for Advisor " + fileDetails.getVendor() + "\n");
         }
         else
         {
            Collections.sort(fileNameLst);
            List<String> filesToLoad = fileProcessorUtil.getFilesToLoad(fileNameLst, businessDate, fileDetails);
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
                     //localDirectory = new File(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_LOCAL_SRC_DIRECTORY")+"/"+fileDetails.getDownloadDir() + "/");
                     Path p1= Paths.get(fileProcessorUtil.getFilePath(serviceRequest, fileDetails, "LOCAL", businessDate));

                     String fileName1 = p1.getFileName().toString();
                     String directory1 = p1.getParent().toString().replaceAll("\\\\","/");
                     System.out.println("directory = " + directory1+ " fileName = " + fileName1);

                     logger.info("Local directory path to stored the files :" + directory1);
                     // if the directory does not exist, create it
//                     if (!directory1.exists())
//                     {
//                        try
//                        {
//                           logger.info("Creating local directory :" + directory1);
//                           directory1.mkdirs();
//                        }
//                        catch (Exception e)
//                        {
//                           logger.error("Creating local directory :" + directory1 + " \n" + e.getMessage());
//                           e.printStackTrace();
//                        }
//                     }
                     File localUploadFile = new File(fileProcessorUtil.getFilePath(serviceRequest,fileDetails,"LOCAL",businessDate));
                     if (!localUploadFile.getParentFile().exists())
                     {
                        try
                        {
                           logger.info("Creating local upload directory :" + localUploadFile);
                           localUploadFile.getParentFile().mkdirs();
                        }
                        catch (Exception e)
                        {
                           logger.error("Creating local upload directory :" + localUploadFile + " \n" + e.getMessage());
                        }
                     }

                     String localFileName = directory1 + "/" + fileToDownload;

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
                           try
                           {
                              processFile(mailAlertMsg, localFileName, fileDetails, ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOWNLOAD_SERVICES.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY"),fileRules , serviceRequest);//hostDetails.getEncrDecrKey()
                              returnValue=true;
                           }
                           catch (Exception e)
                           {
                              logger.error("While " + fileToDownload + " csv file processing\n" + e.getMessage());
                              //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                              mailAlertMsg.append("Issue " + fileToDownload + " csv file processing\n");
                              e.printStackTrace();
                           }
                        }
                        else
                        {
                           String decryptedFileName = localUploadFile + "/" + fileToDownload.replace(fileDetails.getFileExtension(), fileDetails.getDecrFileExtension());
                           try
                           {
                              if (fileDetails.getEncryptionMethod().equalsIgnoreCase("pgp"))
                              {
                                 String gpgPrivateKey,gpgPublicKey,gpgPassword;
                                    gpgPrivateKey =ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "GPG_PRIVATE_KEY");
                                    gpgPublicKey =ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "GPG_PUBLIC_KEY");
                                    gpgPassword=ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "GPG_PASSWORD");


                                 GPGUtil gpgUtil= new GPGUtil(gpgPrivateKey,gpgPublicKey,gpgPassword);

                                 gpgUtil.decryptFile(new FileInputStream(localFileName), new FileOutputStream(decryptedFileName));
                              }
                              try
                              {
                                 processFile(mailAlertMsg, decryptedFileName, fileDetails, ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.DOWNLOAD_SERVICES.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY"), fileRules, serviceRequest);//hostDetails.getEncrDecrKey()
                                 if(fileDetails.getDelFlagDecrFile().equalsIgnoreCase("Y"))
                                 {
                                    deleteDecryptedFile(decryptedFileName);
                                 }
                                 returnValue=true;
                              }
                              catch (Exception e)
                              {
                                 logger.error("While " + fileToDownload + " csv file processing \n" + e.getMessage());
                                 //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                 mailAlertMsg.append("Issue " + fileToDownload + " csv file processing \n");
                                 e.printStackTrace();
                              }
                           }
                           catch (Exception e)
                           {
                              e.printStackTrace();
                           }
                        }
                        fileProcessorUtil.deleteFilesFromServer(fileDetails,fileNameLst,channel,businessDate, mailAlertMsg);
                        fileProcessorUtil.deleteFilesFromLocal(fileDetails,fileProcessorUtil.getListOfFiles(localUploadFile.getParent(),fileDetails.getFileName()),businessDate, localUploadFile.getParent(), mailAlertMsg);
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
         channel.disconnect();
         session.disconnect();

      }catch(Exception e){
         e.printStackTrace();
      }finally {

         channel.disconnect();
         System.out.println("Channel disconnected.");
         session.disconnect();
         System.out.println("Host Session disconnected.");
      }
      return returnValue;
   }



   private void processFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails,
                            String key, LinkedHashMap<String,FileRules> fileRules, ServiceRequest serviceRequest)
   {
      BufferedReader br = null;
      String line = "";
      String splitByDelimiter ="\\"+fileDetails.getDelimiter()+"(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";
      try
      {
         br = new BufferedReader(new FileReader(csvFile));
         List<List<String>> inLst = new ArrayList<List<String>>();
         int counter = 0;
         boolean isErrorPrint=false;
         while ((line = br.readLine()) != null)
         {
            if (!line.equals(""))
            {
               logger.debug("line = " + line);
               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
               entries = fileRules.entrySet().iterator();
               List<String> lineLst = new ArrayList<String>();
               String[] lineArr=null;
               if(fileDetails.getLoadFormat().equalsIgnoreCase("DELIMITED")){
                  lineArr = line.split(splitByDelimiter, -1);
               }
               int arrCounter=0;

               while (entries.hasNext())
               {
                  try{
                  Map.Entry<String, FileRules> entry6 = entries.next();
                  FileRules fileRules1 = (FileRules) entry6.getValue();
                  if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
                  {
                     logger.warn("Avoiding first row to add in db because it's header");
                  }
                  else
                  {
                     if(fileRules1.getDbColumn()==null || fileRules1.getDbColumn().equalsIgnoreCase("")){
                        if(isErrorPrint==false)
                        {
                           logger.warn("DB Column not set for file " + fileDetails.getFileName() + " and filed " + fileRules1.getDataField());
                           isErrorPrint=true;
                        }
                     }else
                     {
                        String[] dbColumns = fileRules1.getDbColumn().split(",");
                        for (int i = 0; i < dbColumns.length; i++)
                        {
                           String value=null;
                           if(fileDetails.getLoadFormat().equalsIgnoreCase("DELIMITED")){
                              value=lineArr[arrCounter].trim();
                           }else if(fileDetails.getLoadFormat().equalsIgnoreCase("FIXED")){
                              value = line.substring(fileRules1.getStartPos() - 1, fileRules1.getEndPos()).trim();
                           }
                           if(fileRules1.getIsRequired().equalsIgnoreCase("Y")){
                              if(value==null || value.equalsIgnoreCase("")){
                                 logger.info("Value not available for required DB Column " + fileRules1.getDbColumn());
                              }else
                              {
                                 if(fileRules1.getNeedToEncrypt().equalsIgnoreCase("Y"))
                                 {
                                    lineLst.add(EncryDecryAES.decrypt(value,ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY")));
                                 }else{
                                    lineLst.add(value);
                                 }
                              }
                           }else
                           {
                              if(fileRules1.getNeedToEncrypt().equalsIgnoreCase("Y"))
                              {
                                 lineLst.add(EncryDecryAES.decrypt(value,ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "ENCRY_DECRY_KEY")));
                              }else{
                                 lineLst.add(value);
                              }
                           }
                        }
                     }
                  }
                  arrCounter ++;
               }catch (Exception e){
               mailAlertMsg.append("While reading file Data in FileUploader.processFile" +e.getMessage()+"\n");
               logger.error("While reading file Data in FileUploader.processFile" +e.getMessage());
               e.printStackTrace();
            }
               }

               if(lineLst.size()>0){
                  inLst.add(lineLst);
               }
            }else{
               System.out.println("Line is empty");
            }
         }
         if(inLst.size()>0)
         {
            StringBuilder insertQuery = null;
            StringBuilder queryValuePart = null;
            if (fileDetails.getCanBeDups().equalsIgnoreCase("Y"))
            {
               insertQuery = new StringBuilder("insert ignore into " + fileDetails.getTmpTableName() + "(");
               queryValuePart= new StringBuilder(" values (");
            }
            else
            {
               insertQuery = new StringBuilder("insert into " + fileDetails.getTmpTableName() + " (");
               queryValuePart= new StringBuilder(" values (");
            }
            Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
            while (entries.hasNext())
            {
               Map.Entry<String, FileRules> entry6 = entries.next();
               FileRules fileRules1 = (FileRules) entry6.getValue();
               if (fileRules1.getDbColumn() == null || fileRules1.getDbColumn().equalsIgnoreCase(""))
               {
                  logger.warn("DB Column not set for file " + fileDetails.getFileName() + " and filed " + fileRules1.getDataField());
               }
               else
               {
                  String[] dbColumns = fileRules1.getDbColumn().split(",");
                  for (int i = 0; i < dbColumns.length; i++)
                  {
                     insertQuery.append(dbColumns[i] + ",");
                     queryValuePart.append("?,");
                  }
               }
            }
            if (insertQuery.lastIndexOf(",") <= 0)
            {
               logger.warn("Semicolon not available in insertQuery = " + insertQuery);
            }
            else
            {
               insertQuery.replace(insertQuery.lastIndexOf(","), insertQuery.lastIndexOf(",") + 1, ")");
               queryValuePart.replace(queryValuePart.lastIndexOf(","), queryValuePart.lastIndexOf(",") + 1, ")");

               insertQuery.append(queryValuePart);
               logger.info("insertQuery = " + insertQuery);
            }
            fileProcessorDao.insertBatch(inLst,insertQuery.toString(),"","");
         }else{
            logger.info("Data not available for processing.");
            if(fileDetails.getCanBeEmpty().equals("N")){
               mailAlertMsg.append(fileDetails.getFileName() + " file is empty, for Advisor " + fileDetails.getVendor() + "\n");
            }
         }
      }catch (Exception e){
         mailAlertMsg.append("FileUploader.processFile" +e.getMessage()+"\n");
         logger.error("FileUploader.processFile" +e.getMessage());
         e.printStackTrace();
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


//   private void processFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key, LinkedHashMap<String,FileRules> fileRules){
//      BufferedReader br = null;
//      String line = "";
//      try{
//         if(fileDetails.getLoadFormat().equalsIgnoreCase("DELIMITED")){
//            processDelimitedFile(mailAlertMsg, csvFile, fileDetails, key, fileRules);
//         }else if(fileDetails.getLoadFormat().equalsIgnoreCase("FIXED")){
//            processFixedFile(mailAlertMsg, csvFile, fileDetails, key, fileRules);
//         }else{
//            System.out.println("File load format "+ fileDetails.getLoadFormat()+" not supported");
//         }
//      }catch (Exception e ){
//         logger.error("FileUploader.processFile" +e.getMessage());
//         e.printStackTrace();
//      }
//   }
//   private boolean processFixedFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key, LinkedHashMap<String,FileRules> fileRules)
//   {
//      boolean processFlag=false;
//      BufferedReader br = null;
//      String line = "";
//      try
//      {
//         br = new BufferedReader(new FileReader(csvFile));
//         List<List<String>> inLst = new ArrayList<List<String>>();
//         int counter = 0;
//         while ((line = br.readLine()) != null)
//         {
//            if (!line.equals(""))
//            {
//               logger.debug("line = " + line);
//               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
//               entries = fileRules.entrySet().iterator();
//               List<String> lineLst = new ArrayList<String>();
//               while (entries.hasNext())
//               {
//                  Map.Entry<String, FileRules> entry6 = entries.next();
//                  FileRules fileRules1 = (FileRules) entry6.getValue();
//                  if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
//                  {
//                     logger.warn("Avoiding first row to add in db because it's header");
//                  }
//                  else
//                  {
//                     if(fileRules1.getDbColumn()==null || fileRules1.getDbColumn().equalsIgnoreCase("")){
//                        logger.warn("DB Column not set for file "+ fileDetails.getFileName()+" and filed "+fileRules1.getDataField());
//                     }else
//                     {
//                        if(fileRules1.getDbColumn().contains(","))
//                        {
//                           String[] dbColumns = fileRules1.getDbColumn().split(",");
//                           for (int i = 0; i < dbColumns.length; i++)
//                           {
//                              logger.debug("Field value " + line.substring(fileRules1.getStartPos() - 1, fileRules1.getEndPos()) + " from start position" + fileRules1.getStartPos() + " to end position " + fileRules1.getEndPos());
//                              lineLst.add(line.substring(fileRules1.getStartPos() - 1, fileRules1.getEndPos()));
//                           }
//                        }else{
//                           logger.debug("Field value " + line.substring(fileRules1.getStartPos() - 1, fileRules1.getEndPos()) + " from start position" + fileRules1.getStartPos() + " to end position " + fileRules1.getEndPos());
//                           lineLst.add(line.substring(fileRules1.getStartPos() - 1, fileRules1.getEndPos()));
//                        }
//                     }
//                  }
//               }
//               if(lineLst.size()>0){
//                  inLst.add(lineLst);
//               }
//
//
////               String[] lineArr = line.split(cvsSplitBy, -1);
////               if (lineArr.length > fileDetails.getKeyData())
////               {
////                  if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
////                  {
////                     if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
////                     {
////                        logger.info("Avoiding first row to add in db because it's header");
////                     }
////                     else
////                     {
////                        inLst.add(lineArr);
////                     }
////                     counter++;
////                  }
////               }
//            }else{
//               System.out.println("Line is empty");
//            }
//         }
//         if(inLst.size()>0)
//         {
//            StringBuilder insertQuery = null;
//            StringBuilder queryValuePart = null;
//            if (fileDetails.getCanBeDups().equalsIgnoreCase("Y"))
//            {
//               insertQuery = new StringBuilder("insert ignore into " + fileDetails.getTmpTableName() + "(");
//               queryValuePart= new StringBuilder(" values (");
//            }
//            else
//            {
//               insertQuery = new StringBuilder("insert into " + fileDetails.getTmpTableName() + " (");
//               queryValuePart= new StringBuilder(" values (");
//            }
//
//            Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
//            while (entries.hasNext())
//            {
//               Map.Entry<String, FileRules> entry6 = entries.next();
//               FileRules fileRules1 = (FileRules) entry6.getValue();
//               if (fileRules1.getDbColumn() == null || fileRules1.getDbColumn().equalsIgnoreCase(""))
//               {
//                  logger.warn("DB Column not set for file " + fileDetails.getFileName() + " and filed " + fileRules1.getDataField());
//               }
//               else
//               {
//                  if(fileRules1.getDbColumn().contains(","))
//                  {
//                     String[] dbColumns = fileRules1.getDbColumn().split(",");
//                     for (int i = 0; i < dbColumns.length; i++)
//                     {
//                        insertQuery.append(dbColumns[i] + ",");
//                        queryValuePart.append("?,");
//                     }
//                  }else{
//                     insertQuery.append(fileRules1.getDbColumn() + ",");
//                     queryValuePart.append("?,");
//                  }
//               }
//            }
//            if (insertQuery.lastIndexOf(",") <= 0)
//            {
//               logger.warn("Semicolon not available in insertQuery = " + insertQuery);
//            }
//            else
//            {
//               insertQuery.replace(insertQuery.lastIndexOf(","), insertQuery.lastIndexOf(",") + 1, ")");
//               queryValuePart.replace(queryValuePart.lastIndexOf(","), queryValuePart.lastIndexOf(",") + 1, ")");
//
//               insertQuery.append(queryValuePart);
//               logger.info("insertQuery = " + insertQuery);
//            }
//            processFlag = fileProcessorDao.insertBatch(inLst,insertQuery.toString(),"","");
//         }else{
//            logger.info("Data not available for processing.");
//            if(fileDetails.getCanBeEmpty().equals("N")){
//               mailAlertMsg.append(fileDetails.getFileName() + " file is empty, for Advisor " + fileDetails.getVendor() + "\n");
//            }
//         }
//
//      }catch (Exception e){
//         mailAlertMsg.append("FileUploader.processFixedFile" +e.getMessage()+"\n");
//         logger.error("FileUploader.processFixedFile" +e.getMessage());
//         e.printStackTrace();
//      }
//      return processFlag;
//   }
////   private String readValue(String line, int startPos, int endPos){
////
////
////   }
//
//   private void processDelimitedFile(StringBuilder mailAlertMsg, String csvFile, FileDetails fileDetails, String key, LinkedHashMap<String,FileRules> fileRules)
//   {
//      boolean processFlag=false;
//      logger.debug("FileUploader.processDelimitedFile");
//      logger.debug("mailAlertMsg = [" + mailAlertMsg + "], csvFile = [" + csvFile + "], fileDetails = [" + fileDetails + "], key = [" + key + "]");
//      BufferedReader br = null;
//      String line = "";
//      String splitByDelimiter ="\\"+fileDetails.getDelimiter()+"(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";
//
//      try
//      {
//         try
//         {
////            commonDao.truncateTable(fileDetails.getTmpTableName());
//          StringBuilder sb = null;
//            try
//            {
//               br = new BufferedReader(new FileReader(csvFile));
//               List<List<String>> inLst = new LinkedList<List<String>>();
//               int counter = 0;
//               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
//
//               while ((line = br.readLine()) != null)
//               {
//                  if (!line.equals(""))
//                  {
//                     String[] lineArr = line.split(splitByDelimiter, -1);
//                     if (lineArr.length > fileDetails.getKeyData())
//                     {
//                        if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
//                        {
//                           if (counter == 0 && fileDetails.getContainsHeader().equalsIgnoreCase("Y"))
//                           {
//                              logger.info("Avoiding first row to add in db because it's header");
//                           }
//                           else
//                           {
////                              inLst.add(lineArr);
////                              new ArrayList<String>(Arrays.asList(lineArr));
//
//
//                              List<String> lineLst = new ArrayList<String>();
//                              int arrCounter=0;
//                              entries = fileRules.entrySet().iterator();
//                              while (entries.hasNext())
//                              {
//                                 Map.Entry<String, FileRules> entry6 = entries.next();
//                                 FileRules fileRules1 = (FileRules) entry6.getValue();
//
//                                 if(fileRules1.getDbColumn()==null || fileRules1.getDbColumn().equalsIgnoreCase("")){
//                                    logger.warn("DB Column not set for file "+ fileDetails.getFileName()+" and filed "+fileRules1.getDataField());
//                                 }else
//                                 {
//                                    if(fileRules1.getDbColumn().contains(","))
//                                    {
//                                       String[] dbColumns = fileRules1.getDbColumn().split(",");
//                                       for (int i = 0; i < dbColumns.length; i++)
//                                       {
//                                          System.out.print(lineArr[arrCounter] + " : ");
//                                          lineLst.add(lineArr[arrCounter]);
//                                       }
//                                    }else{
//                                       System.out.print(lineArr[arrCounter] + " : ");
//                                       lineLst.add(lineArr[arrCounter]);
//                                    }
//                                 }
//
//                                 arrCounter++;
//                              }
//                              System.out.println();
//
//
//                              if(lineLst.size()>0){
//                                 inLst.add(lineLst);
//                              }
//
//                           }
//                           counter++;
//                        }
//                     }
//                  }
//               }
//               if(inLst.size()>0)
//               {
//                  StringBuilder insertQuery = null;
//                  StringBuilder queryValuePart = null;
//                  if (fileDetails.getCanBeDups().equalsIgnoreCase("Y"))
//                  {
//                     insertQuery = new StringBuilder("insert ignore into " + fileDetails.getTmpTableName() + "(");
//                     queryValuePart = new StringBuilder(" values (");
//                  }
//                  else
//                  {
//                     insertQuery = new StringBuilder("insert into " + fileDetails.getTmpTableName() + " (");
//                     queryValuePart = new StringBuilder(" values (");
//                  }
//
//                  entries = fileRules.entrySet().iterator();
//                  while (entries.hasNext())
//                  {
//                     Map.Entry<String, FileRules> entry6 = entries.next();
//                     FileRules fileRules1 = (FileRules) entry6.getValue();
//                     if (fileRules1.getDbColumn() == null || fileRules1.getDbColumn().equalsIgnoreCase(""))
//                     {
//                        logger.warn("DB Column not set for file " + fileDetails.getFileName() + " and filed " + fileRules1.getDataField());
//                     }
//                     else
//                     {
//                        if(fileRules1.getDbColumn().contains(","))
//                        {
//                           String[] dbColumns = fileRules1.getDbColumn().split(",");
//                           for (int i = 0; i < dbColumns.length; i++)
//                           {
//                              insertQuery.append(dbColumns[i] + ",");
//                              queryValuePart.append("?,");
//                           }
//                        }else{
//                           insertQuery.append(fileRules1.getDbColumn() + ",");
//                           queryValuePart.append("?,");
//                        }
//
//                     }
//                  }
//                  if (insertQuery.lastIndexOf(",") <= 0)
//                  {
//                     logger.warn("Semicolon not available in insertQuery = " + insertQuery);
//                  }
//                  else
//                  {
//                     insertQuery.replace(insertQuery.lastIndexOf(","), insertQuery.lastIndexOf(",") + 1, ")");
//                     queryValuePart.replace(queryValuePart.lastIndexOf(","), queryValuePart.lastIndexOf(",") + 1, ")");
//
//                     insertQuery.append(queryValuePart);
//                     logger.info("insertQuery = " + insertQuery);
//                  }
//                  processFlag = fileProcessorDao.insertBatch(inLst, insertQuery.toString(), "", "");
//               }else{
//
//               }
////               if (inLst.size() > 0)
////               {
////                  StringBuilder insertQuery = null;
////                  System.out.print("fileDetails.getCanbedups()" + fileDetails.getCanBeDups());
////                  if (fileDetails.getCanBeDups().equalsIgnoreCase("Y"))
////                  {
////                     insertQuery = new StringBuilder("insert ignore into " + fileDetails.getTmpTableName() + " values (");
////                  }
////                  else
////                  {
////                     insertQuery = new StringBuilder("insert into " + fileDetails.getTmpTableName() + " values (");
////                  }
////                  int inColLen = inLst.get(0).length;
////                  for (int i = 1; i <= inColLen; i++)
////                  {
////                     insertQuery.append("?" + (i != inColLen ? "," : ")"));
////                  }
////                  if (fileDetails.getEncColumns() == null || fileDetails.getEncColumns().trim().equals(""))
////                  {
////                     logger.info("Encryption columns are not set");
////                  }
////                  else if (fileDetails.getEncColumns() != null && !fileDetails.getEncColumns().trim().equals(""))
////                  {
////                     String[] encColumns = fileDetails.getEncColumns().split(",");
////                     for (int i = 0; i < inLst.size(); i++)
////                     {
////                        String[] arr = (String[]) inLst.get(i);
////                        for (int j = 0; j < encColumns.length; j++)
////                        {
////                           try
////                           {
////                              int val = Integer.parseInt(encColumns[j].trim()) - 1;
////                              logger.info(encColumns[j] + ":" + arr.length + ":" + val);
////                              if (val <= arr.length)
////                              {
////                                 arr[val] = EncryDecryAES.encrypt(arr[val], key);//MsgDigester.getMessageDigest(arr[val]);
////                              }
////                              else
////                              {
////                                 logger.info("Encryption columns value :" + encColumns[j] + "is not valid \n");
////                              }
////                           }
////                           catch (Exception e)
////                           {
////                              //mailAlertMsg.append("Encryption columns value :"+encColumns[j]+"is not valid \n"+e.getMessage());
////                              logger.error("Encryption columns value :" + encColumns[j] + "is not valid" + e.getMessage());
////                           }
////                        }
////                        inLst.set(i, arr);
////                     }
////                  }
////                  logger.info("insertQuery :" + insertQuery.toString());
////                  boolean bFlag = false;
////                  try
////                  {
////                     bFlag = fileProcessorDao.insertBatch(inLst, insertQuery.toString(), fileDetails.getPostInstruction());
////                  }
////                  catch (Exception e)
////                  {
////                     bFlag = false;
////                     mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
////                     logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
////                  }
//////                  logger.error("Batch insertion Flag" + bFlag);
//////                  if (bFlag)
//////                  {
//////                     if (fileDetails.getPostInstruction() == null || fileDetails.getPostInstruction().equals(""))
//////                     {
//////                        logger.info("Procedure name is not valid");
//////                     }
//////                     else
//////                     {
//////                        try
//////                        {
//////                           logger.info("Calling post process procedure :" + fileDetails.getPostInstruction());
////////                           commonDao.callProcedure(fileDetails.getPostInstruction());
//////                        }
//////                        catch (Exception e)
//////                        {
//////                           mailAlertMsg.append(" While post process procedure  " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//////                           logger.error(" While post process procedure  " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//////                        }
//////                     }
//////                  }
////
////               }
////               else
////               {
////                  logger.info(fileDetails.getFileName() + " file is empty");
////                  if (fileDetails.getCanBeEmpty().equalsIgnoreCase("N"))
////                  {
////                     mailAlertMsg.append(csvFile + " file is empty \n");
////                  }
////                  //            logger.info(fileDetails.getFileName()+" file is empty");
////                  //            if(fileDetails.getContainsheader().equalsIgnoreCase("N")) {
////                  //               throw new FileEmptyException(fileDetails.getFileName() + " file is empty");
////                  //            }
////               }
//            }
//            catch (FileNotFoundException e)
//            {
//               mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//               logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//            }
//            catch (IOException e)
//            {
//               mailAlertMsg.append("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//               logger.error("While batch insertion " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//            }
//            logger.info("Process Csv End");
//         }
//         catch (Exception e)
//         {
//            mailAlertMsg.append("While delete data from table " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//            logger.error("While delete data from table " + fileDetails.getTmpTableName() + "\n" + e.getMessage());
//         }
//
////         if(fileDetails.getContainsheader().equalsIgnoreCase("Y") && inLst !=null && inLst.size() > 0)
////         {
////            logger.info("Removing header row of "+fileDetails.getFileName()+" file");
////            inLst.remove(0);
////         }
//
//      }
//      finally
//      {
//         if (br != null)
//         {
//            try
//            {
//               br.close();
//            }
//            catch (IOException e)
//            {  /*e.printStackTrace();*/}
//         }
//      }
//   }
}
