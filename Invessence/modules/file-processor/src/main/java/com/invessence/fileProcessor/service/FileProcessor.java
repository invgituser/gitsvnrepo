package com.invessence.fileProcessor.service;

import java.io.*;
import java.util.*;

import com.invessence.emailer.util.EmailCreator;
import com.invessence.fileProcessor.bean.*;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.fileProcessor.util.FileProcessorUtil;
import com.invessence.service.bean.*;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.util.*;
import com.jcraft.jsch.*;
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
   EmailCreator emailCreator;

   @Autowired
   FileProcessorUtil fileProcessorUtil;

   @Autowired
   FileProcessorDao fileProcessorDao;

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
            wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
         }
         else
         {
            mailAlertMsg = new StringBuilder();
            LinkedHashMap<String, LinkedList<FileDetails>> fileMap = (LinkedHashMap<String, LinkedList<FileDetails>>) ServiceDetails.getAdditionalDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());

            fileMap.get(serviceRequest.getProcessId());
            if(fileMap.containsKey(serviceRequest.getProcessId())){

               List<FileDetails> fileList=(List<FileDetails>)fileMap.get(serviceRequest.getProcessId());
   //            List<FileDetails> fileList = (List<FileDetails>) ServiceDetails.getAdditionalDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
               if (fileList == null || fileList.size() == 0)
               {
                  mailAlertMsg.append("Files are not available for ProceesId : "+serviceRequest.getProcessId()+"\n");
                  logger.warn("Files are not available for ProceesId : "+serviceRequest.getProcessId());
                  wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
               }
               else
               {
                  Map<String, Map<String, FileRules>> fileRulesMap = (Map<String, Map<String, FileRules>>) ServiceDetails.getCommonDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), Constant.COMMON_DETAILS.FILE_RULES.toString());
                  logger.info(fileList);
                  LinkedHashMap<String, FileRules> fileRules = null;
                  Iterator<FileDetails> itr = fileList.iterator();

                  List<FileDetails> fileProcessStatusList=new ArrayList<FileDetails>();
                  Boolean processExecutionStatus=true;
                  Boolean preInstructionExecuted=false;
//                  Boolean preDBProcessExecuted=false;

                  int fileExecutionCounter=1;
                  System.out.println("File Processor execution Started");
                  while (itr.hasNext())
                  {
                     fileDetails = (FileDetails) itr.next();
                     StringBuilder isEmailRaised=new StringBuilder();
                     boolean fileProcessResult=false;
//                     if (fileDetails.getProcessId().equalsIgnoreCase(serviceRequest.getProcessId()))
//                     {
                        logger.info("fileExecutionCounter = " + fileExecutionCounter+" : "+fileDetails.getFileName());
                        if(!preInstructionExecuted){
                           fileProcessorUtil.executeInstruction(fileDetails,"PARENTPRE", mailAlertMsg, serviceRequest);
                           preInstructionExecuted=true;}
                           fileProcessorUtil.executeInstruction(fileDetails,"PRE", isEmailRaised, serviceRequest);
                     System.out.println(isEmailRaised.length()+"isEmailRaised.length()");
                     if(isEmailRaised.length()==0)
                     {
                        if (fileDetails.getProcess().equalsIgnoreCase("DOWNLOAD"))
                        {
                           fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                           fileProcessResult = fileDownloader.download(serviceRequest, fileDetails, fileRules, mailAlertMsg, dbParamMap);
                           if (fileProcessResult == true)
                           {
                              fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "S", "Success");
                              fileProcessorUtil.executeDBProcess(fileDetails, "POST", mailAlertMsg);
                           }
                           else
                           {
                              fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", "Fail");
                              processExecutionStatus=false;
                           }
                        }
                        else if (fileDetails.getProcess().equalsIgnoreCase("UPLOAD"))
                        {
                           fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                           fileProcessResult = fileUploader.upload(serviceRequest, fileDetails, fileRules, mailAlertMsg, dbParamMap);
                           if (fileProcessResult == true)
                           {
                              fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "S", "Success");
                              fileProcessorUtil.executeDBProcess(fileDetails, "POST", mailAlertMsg);
                           }
                           else
                           {
                              fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", "Fail");
                              processExecutionStatus=false;
                           }
                        }
                        fileProcessorUtil.executeInstruction(fileDetails,"POST", mailAlertMsg, serviceRequest);
                     }else{
                        mailAlertMsg.append(isEmailRaised);
                        processExecutionStatus=false;
                     }

                     fileProcessStatusList.add(new FileDetails(fileDetails.getVendor(),fileDetails.getFileName(),fileDetails.getProcessId(),fileProcessResult, fileDetails.getFileProcessType(),
                                                               fileProcessorUtil.getFilePath(serviceRequest,fileDetails,fileDetails.getFileProcessType(),dbParamMap.get("BUSINESS_DATE").getValue().toString())));
                     fileExecutionCounter++;

                  }
                  if(processExecutionStatus==true){
                     fileProcessorUtil.executeInstruction(fileDetails,"PARENTPOST", mailAlertMsg, serviceRequest);
                     wsCallResult = new WSCallResult(new WSCallStatus(0,"SUCCESS"),fileProcessStatusList);
                  }else{
                     wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),fileProcessStatusList);
                  }
               }
            }else{
               mailAlertMsg.append("Files Details are not available for ProcessId : "+serviceRequest.getProcessId()+"\n");
               logger.warn("Files Details are not available for ProcessId : "+serviceRequest.getProcessId());
               wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
            }
         }
      }catch (Exception e){
         logger.error(e.getMessage());
         wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
//         e.printStackTrace();
      }finally
      {
         logger.info("MailAlertMsg :" + mailAlertMsg);
         if (mailAlertMsg.length() > 0)
         {
            try
            {
               logger.info("Sending email to support team");
               String emailId=ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_ISSUE_EMAIL");
               emailCreator.sendToSupport("ERR", "File Processor "+serviceRequest.getProduct()+ " ProcessID : "+serviceRequest.getProcessId()+ " execution Status", mailAlertMsg.toString(), emailId);
            }
            catch (Exception e)
            {
               logger.error("While email processing \n" + e.getMessage());
               logger.error(e.getStackTrace());
            }
         }
         else
         {
            if(fileDetails!=null){fileProcessorUtil.executeDBProcess(fileDetails,"PARENTPOST", mailAlertMsg);}
         }
      }
      return wsCallResult;
   }

}
