package com.invessence.fileProcessor.service;

import java.io.*;
import java.util.*;

import com.invessence.emailer.util.EmailCreator;
import com.invessence.fileProcessor.bean.*;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.service.bean.*;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 2/22/2017.
 */
@Component
public class FileProcessor
{
   private static final Logger logger = Logger.getLogger(FileProcessor.class);

   @Autowired
   FileDownloader fileDownloader;

   @Autowired
   FileUploader fileUploader;

   @Autowired
   FileProcessorDao fileProcessorDao;

   @Autowired
   EmailCreator emailCreator;

   StringBuilder mailAlertMsg = null;

   public WSCallResult process(ServiceRequest serviceRequest){
      logger.info("FileProcessor.process");
      logger.info("serviceRequest = [" + serviceRequest + "]");
      WSCallResult wsCallResult=null;
      FileDetails fileDetails=null;
      try
      {
         Map<String, DBParameters> dbParamMap = fileProcessorDao.getDBParametres();
         if (dbParamMap == null || dbParamMap.size() == 0 || !dbParamMap.containsKey("BUSINESS_DATE"))
         {
            mailAlertMsg.append("Required DB parameters not available");
            logger.info("Required DB parameters not available");
         }
         else
         {
            mailAlertMsg = new StringBuilder();
            List<FileDetails> fileList = (List<FileDetails>) ServiceDetails.getAdditionalDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
            if (fileList == null || fileList.size() == 0)
            {
               mailAlertMsg.append("Files are not available for FileProcessor");
               logger.warn("Files are not available for FileProcessor");
            }
            else
            {
               Map<String, Map<String, FileRules>> fileRulesMap = (Map<String, Map<String, FileRules>>) ServiceDetails.getCommonDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), Constant.COMMON_DETAILS.FILE_RULES.toString());
               logger.info(fileList);
               LinkedHashMap<String, FileRules> fileRules = null;
               Iterator<FileDetails> itr = fileList.iterator();

               List<FileDetails> fileProcessStatusList=new ArrayList<FileDetails>();
               Boolean preInstructionExecuted=false;
               Boolean preDBProcessExecuted=false;

               int fileExecutionCounter=1;
               System.out.println("File Processor execution Started");
               while (itr.hasNext())
               {
                  fileDetails = (FileDetails) itr.next();

                  boolean fileProcessResult=false;
                  if (fileDetails.getProcessId().equalsIgnoreCase(serviceRequest.getProcessId()))
                  {
                     System.out.println("fileExecutionCounter = " + fileExecutionCounter+" : "+fileDetails.getFileName());
                     if(!preInstructionExecuted){executeInstruction(fileDetails,"PARENTPRE", mailAlertMsg);
                        preInstructionExecuted=true;}
                     executeInstruction(fileDetails,"PRE", mailAlertMsg);
                     if (fileDetails.getProcess().equalsIgnoreCase("DOWNLOAD"))
                     {
                        fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                        fileProcessResult=fileDownloader.download(serviceRequest, fileDetails, fileRules, mailAlertMsg, dbParamMap);
                        if(fileProcessResult==true)
                        {
                           auditEntry(serviceRequest, fileDetails, "S", "Success");
                        }else{
                           auditEntry(serviceRequest, fileDetails, "F", "Fail");
                        }
                     }
                     else if (fileDetails.getProcess().equalsIgnoreCase("UPLOAD"))
                     {
                        fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                        fileProcessResult=fileUploader.upload(serviceRequest, fileDetails, fileRules,  mailAlertMsg, dbParamMap);
                        if(fileProcessResult==true)
                        {
                           auditEntry(serviceRequest, fileDetails, "S", "Success");
                           executeDBProcess(fileDetails,"POST", mailAlertMsg);
                        }else{
                           auditEntry(serviceRequest, fileDetails, "F", "Fail");
                        }
                     }
                     executeInstruction(fileDetails,"POST", mailAlertMsg);

                     fileProcessStatusList.add(new FileDetails(fileDetails.getFileName(),fileDetails.getProcessId(),fileProcessResult, fileDetails.getFileProcessType(),
                                                               getFilePath(serviceRequest,fileDetails,fileDetails.getFileProcessType(),dbParamMap.get("BUSINESS_DATE").getValue().toString())));
                     fileExecutionCounter++;
                  }
                  else
                  {
//                     logger.warn(fileDetails.getFileName() + " gets avoid from process execution, because it's not belong to processId :" + serviceRequest.getProcessId());
                  }
               }
               if(fileDetails!=null){executeInstruction(fileDetails,"PARENTPOST", mailAlertMsg);}

               wsCallResult = new WSCallResult(new WSCallStatus(0,"SUCCESS"),fileProcessStatusList);
            }
         }
      }catch (Exception e){
         logger.error(e.getMessage());
//         e.printStackTrace();
      }finally
      {
         logger.info("MailAlertMsg :" + mailAlertMsg);
         if (mailAlertMsg.length() > 0)
         {
            try
            {
               logger.info("Sending email to support team");
               emailCreator.sendToSupport("ERR", "Broker File Upload Process", mailAlertMsg.toString(), "abhang.patil@gmail.com");
            }
            catch (Exception e)
            {
               logger.error("While email processing \n" + e.getMessage());
               logger.error(e.getStackTrace());
            }
         }
         else
         {
            if(fileDetails!=null){executeDBProcess(fileDetails,"PARENTPOST", mailAlertMsg);}
         }
      }
      return wsCallResult;
   }


   public static String getFilePath(ServiceRequest serviceRequest, FileDetails fileDetails,
                                    String pathLocation, String date){
      logger.info("FileProcessor.getFilePath");
   StringBuilder filePath= new StringBuilder();
      try
      {
         if(pathLocation==null || pathLocation.equals("")){
            return null;
         }else
         {
            if (fileDetails.getProcess().equalsIgnoreCase("UPLOAD"))
            {
               if (pathLocation.equalsIgnoreCase("SFTP"))
               {
                  filePath.append(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_SFTP_SRC_DIRECTORY"));
                  filePath.append(fileDetails.getSourcePath() + "/");
               }
               else if (pathLocation.equalsIgnoreCase("LOCAL"))
               {
                  filePath.append(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "UPLOAD_LOCAL_SRC_DIRECTORY"));
                  filePath.append("/" + fileDetails.getDownloadDir() + "/");
               }
               filePath.append(getFileName(fileDetails, date));
            }
            else if (fileDetails.getProcess().equalsIgnoreCase("DOWNLOAD"))
            {
               if (pathLocation.equalsIgnoreCase("SFTP") && fileDetails.getFileProcessType().equalsIgnoreCase("SFTP"))
               {
                  filePath.append(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_SFTP_SRC_DIRECTORY"));
                  filePath.append(fileDetails.getUploadDir() + "/");
               }
               else if (pathLocation.equalsIgnoreCase("LOCAL") && fileDetails.getFileProcessType().equalsIgnoreCase("SFTP"))
               {
                  filePath.append(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_LOCAL_SRC_DIRECTORY"));
                  filePath.append(fileDetails.getUploadDir() + "/");
               }
               else if (fileDetails.getFileProcessType().equalsIgnoreCase("DOWNLOAD"))
               {
                  filePath.append(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_LOCAL_SRC_DIRECTORY"));
                  filePath.append("/" + fileDetails.getUploadDir() + "/");
               }
               filePath.append(getFileName(fileDetails, date));
            }
         }
      }catch(Exception e){
         e.printStackTrace();
         logger.error(e.getMessage());
      }
      System.out.println("filePath = " + filePath.toString());
      return filePath.toString();
   }
   public static String getFileName(FileDetails fileDetails, String date){
      StringBuilder filePath= new StringBuilder();
      if(fileDetails.getFileNameAppender().equalsIgnoreCase("PREFIX")){
         filePath.append(date+"_").append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
      }else if(fileDetails.getFileNameAppender().equalsIgnoreCase("POSTFIX")){
         filePath.append(fileDetails.getFileName()).append("_"+date).append("."+fileDetails.getFileExtension());
      }
      return filePath.toString();
   }

   private void auditEntry(ServiceRequest serviceRequest, FileDetails fileDetails, String status, String remarks)throws Exception{

      try      {
         fileProcessorDao.dbCallAudit(new FileProcessAudit(null, serviceRequest.getProduct(),
                                                           serviceRequest.getMode(), fileDetails.getProcess(),
                                                           fileDetails.getProcessId(), fileDetails.getFileName(), status, remarks, null, null));
      }catch(Exception e){
         logger.error(e.getMessage());
//         e.printStackTrace();
      }

   }

   private void executeDBProcess(FileDetails fileDetails, String event, StringBuilder mailAlertMsg){
try{
   if(event.equalsIgnoreCase("PARENTPRE"))
   {
      if (fileDetails.getParentPreDBProcess() == null || fileDetails.getParentPreDBProcess().trim() == "")
      {
         logger.info("No Parent Pre Processor DB event for execution.\n");
      }
      else
      {
         logger.info("Calling Parent Pre Processor DB event:" + fileDetails.getParentPreDBProcess() + "\n");
         executeCommand(fileDetails.getParentPreDBProcess());
      }
   }else if(event.equalsIgnoreCase("PARENTPOST"))
   {
      if (fileDetails.getParentPostDBProcess() == null || fileDetails.getParentPostDBProcess().trim() == "")
      {
         logger.info("No Parent Post Processor DB event for execution.\n");
      }
      else
      {
         logger.info("Calling Parent Post Processor DB event:" + fileDetails.getParentPostDBProcess() + "\n");
         executeCommand(fileDetails.getParentPostDBProcess());
      }
   }else if(event.equalsIgnoreCase("PRE"))
      {
         if (fileDetails.getPreDBProcess() == null || fileDetails.getPreDBProcess().trim() == "")
         {
            logger.info("No Pre Processor DB event for execution.\n");
         }
         else
         {
            logger.info("Calling Pre Processor DB event:" + fileDetails.getPreDBProcess() + "\n");
            fileProcessorDao.callProcedure(fileDetails.getPreDBProcess());
         }
      }else if(event.equalsIgnoreCase("POST"))
      {
         if (fileDetails.getPostDBProcess() == null || fileDetails.getPostDBProcess().trim() == "")
         {
            logger.info("No Post Processor DB event for execution.\n");
         }
         else
         {
            logger.info("Calling Post Processor DB event:" + fileDetails.getPostDBProcess() + "\n");
            fileProcessorDao.callProcedure(fileDetails.getPostDBProcess());
         }
      }}catch(Exception e){
   mailAlertMsg.append("FileProcessor.executeDBProcess" +e.getMessage()+"\n");
   logger.error(e.getMessage());
   //e.printStackTrace();
}
   }

   private void executeInstruction(FileDetails fileDetails, String event, StringBuilder mailAlertMsg){
try{
   if(event.equalsIgnoreCase("PARENTPRE"))
   {
      if (fileDetails.getParentPreInstruction() == null || fileDetails.getParentPreInstruction().trim() == "")
      {
         logger.info("No Parent Pre Processor Script event for execution.\n");
      }
      else
      {
         logger.info("Calling Parent Pre Processor Script event:" + fileDetails.getParentPreInstruction() + "\n");
         executeCommand(fileDetails.getParentPreInstruction());
      }
   }else if(event.equalsIgnoreCase("PARENTPOST"))
   {
      if (fileDetails.getParentPostInstruction() == null || fileDetails.getParentPostInstruction().trim() == "")
      {
         logger.info("No Parent Post Processor Script event for execution.\n");
      }
      else
      {
         logger.info("Calling Parent Post Processor Script event:" + fileDetails.getParentPostInstruction() + "\n");
         executeCommand(fileDetails.getParentPostInstruction());
      }
   }else if(event.equalsIgnoreCase("PRE"))
      {
         if (fileDetails.getPreInstruction() == null || fileDetails.getPreInstruction().trim() == "")
         {
            logger.info("No Pre Processor Script event for execution.\n");
         }
         else
         {
            logger.info("Calling Pre Processor Script event:" + fileDetails.getPreInstruction() + "\n");
            executeCommand(fileDetails.getPreInstruction());
         }
      }else if(event.equalsIgnoreCase("POST"))
      {
         if (fileDetails.getPostInstruction() == null || fileDetails.getPostInstruction().trim() == "")
         {
            logger.info("No Post Processor Script event for execution.\n");
         }
         else
         {
            logger.info("Calling Post Processor Script event:" + fileDetails.getPostInstruction() + "\n");
            executeCommand(fileDetails.getPostInstruction());
         }
      }
//   throw new Exception();
}catch(Exception e){
   mailAlertMsg.append("FileProcessor.executeInstruction" +e.getMessage()+"\n");
   logger.error(e.getMessage());
//   e.printStackTrace();
}
   }

   private String executeCommand(String command) {
      StringBuffer output = new StringBuffer();
   try{

      Process p;

         p = Runtime.getRuntime().exec(command);
         p.waitFor();
         BufferedReader reader =
            new BufferedReader(new InputStreamReader(p.getInputStream()));

         String line = "";
         while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
         }
      ;
      }catch(Exception e){
   logger.error(e.getMessage());
//   e.printStackTrace();
}
      return output.toString();
   }
}
