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

   public WSCallResult process(ServiceRequest serviceRequest){
      logger.info("FileProcessor.process");
      logger.info("serviceRequest = [" + serviceRequest + "]");
      WSCallResult wsCallResult=null;
      FileDetails fileDetails=null;
      StringBuilder mailAlertMsg=new StringBuilder();
      StringBuilder isProcessMailRaised=null;
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
            LinkedHashMap<String, LinkedList<FileDetails>> fileMap = (LinkedHashMap<String, LinkedList<FileDetails>>) ServiceDetails.getAdditionalDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
            if(fileMap==null || fileMap.get(serviceRequest.getProcessId())==null || !fileMap.containsKey(serviceRequest.getProcessId())){

                  mailAlertMsg.append("Files Details are not available for ProcessId : "+serviceRequest.getProcessId()+"\n");
                  logger.warn("Files Details are not available for ProcessId : "+serviceRequest.getProcessId());
                  wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
               }else{

               List<FileDetails> fileList=(List<FileDetails>)fileMap.get(serviceRequest.getProcessId());
   //            List<FileDetails> fileList = (List<FileDetails>) ServiceDetails.getAdditionalDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
               if (fileList == null || fileList.size() == 0)
               {
                  mailAlertMsg.append("Files are not available for ProceesId : "+serviceRequest.getProcessId()+"\n");
                  logger.info("Files are not available for ProceesId : "+serviceRequest.getProcessId());
                  wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
               }
               else
               {
                  Map<String, Map<String, FileRules>> fileRulesMap = (Map<String, Map<String, FileRules>>) ServiceDetails.getCommonDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), Constant.COMMON_DETAILS.FILE_RULES.toString());
                  LinkedHashMap<String, FileRules> fileRules = null;
                  Iterator<FileDetails> itr = fileList.iterator();
                  List<FileDetails> fileProcessStatusList=new ArrayList<FileDetails>();
                  Boolean preInstructionExecuted=false;

                  int fileExecutionCounter=1;
                  System.out.println("File Processor execution Started");
                  while (itr.hasNext())
                  {
                     fileDetails = (FileDetails) itr.next();
                     isProcessMailRaised=new StringBuilder();
                     boolean fileProcessResult=false;

                     logger.info("fileExecutionCounter = " + fileExecutionCounter+" : "+fileDetails.getFileName());

                     if(!preInstructionExecuted){
                        fileProcessorUtil.executeInstruction(fileDetails,"PARENTPRE", isProcessMailRaised, serviceRequest);
                        if(isProcessMailRaised.length()>0){ // Checking Parent Pre Instruction Process Result
                           mailAlertMsg.append(isProcessMailRaised);
                           fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), "PARENT PRE INSTRUCTION");
                           break;
                        }
                        fileProcessorUtil.executeDBProcess(fileDetails, "PARENTPRE", isProcessMailRaised);
                        if(isProcessMailRaised.length()>0){ // Checking Parent Pre DB Process Result
                           mailAlertMsg.append(isProcessMailRaised);
                           fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), "PARENT PRE DB PROCESS");
                           break;
                        }
                        preInstructionExecuted=true;
                     }

                     isProcessMailRaised = new StringBuilder();
                     fileProcessorUtil.executeInstruction(fileDetails, "PRE", isProcessMailRaised, serviceRequest);
                     if (isProcessMailRaised.length() == 0){ // Checking Pre Instruction Process Result

                        fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                        isProcessMailRaised = new StringBuilder();
                        if (fileDetails.getProcess().equalsIgnoreCase("DOWNLOAD"))
                        {
                           fileProcessResult = fileDownloader.download(serviceRequest, fileDetails, fileRules, isProcessMailRaised, dbParamMap);
                        }
                        else if (fileDetails.getProcess().equalsIgnoreCase("UPLOAD"))
                        {
                           fileProcessResult = fileUploader.upload(serviceRequest, fileDetails, fileRules, isProcessMailRaised, dbParamMap);
                        }

                        if (isProcessMailRaised.length() == 0) // Checking Process Result
                        {
                           isProcessMailRaised = new StringBuilder();
                           fileProcessorUtil.executeDBProcess(fileDetails, "POST", isProcessMailRaised);
                           if (isProcessMailRaised.length() == 0) // Checking Post DB Process Result
                           {
                              isProcessMailRaised = new StringBuilder();
                              fileProcessorUtil.executeInstruction(fileDetails, "POST", isProcessMailRaised, serviceRequest);
                              if (isProcessMailRaised.length() == 0) // Checking Post Instruction Process Result
                              {
                                 fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "S", "Success", null);
                              }
                              else
                              {
                                 mailAlertMsg.append(isProcessMailRaised);
                                 fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), null);
                              }
                           }
                           else
                           {
                              mailAlertMsg.append(isProcessMailRaised);
                              fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), null);
                           }
                        }
                        else
                        {
                           mailAlertMsg.append(isProcessMailRaised);
                           fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), null);
                        }
                     }else
                     {
                        mailAlertMsg.append(isProcessMailRaised);
                        fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), null);
                     }
                     fileProcessStatusList.add(new FileDetails(fileDetails.getVendor(), fileDetails.getFileName(), fileDetails.getProcessId(),
                                                               isProcessMailRaised.length() == 0 ? true : false, fileDetails.getFileProcessType(),
                                                               fileProcessorUtil.getFilePath(serviceRequest, fileDetails, fileDetails.getFileProcessType(),
                                                                                             dbParamMap.get("BUSINESS_DATE").getValue().toString())));
                     fileExecutionCounter++;
                  }

                  if(mailAlertMsg.length()==0){ // Checking Process Execution Status
                     isProcessMailRaised=new StringBuilder();
                     fileProcessorUtil.executeDBProcess(fileDetails, "PARENTPOST", isProcessMailRaised);
                     if (isProcessMailRaised.length() == 0)// Checking Parent Post DB Process Result
                     {
                        isProcessMailRaised=new StringBuilder();
                        fileProcessorUtil.executeInstruction(fileDetails, "PARENTPOST", isProcessMailRaised, serviceRequest);
                        if (isProcessMailRaised.length() == 0) // Checking Parent Post Instruction Process Result
                        {
                           wsCallResult = new WSCallResult(new WSCallStatus(0,"SUCCESS"),fileProcessStatusList);
                        }
                        else{
                           mailAlertMsg.append(isProcessMailRaised);
                           fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), "PARENT POST INSTRUCTION");
                           wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),fileProcessStatusList);
                        }
                     }
                     else{
                        mailAlertMsg.append(isProcessMailRaised);
                        fileProcessorUtil.auditEntry(serviceRequest, fileDetails, "F", isProcessMailRaised.toString(), "PARENT POST DB PROCESS");
                        wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),fileProcessStatusList);
                     }

                  }else{
                     wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),fileProcessStatusList);
                  }
               }
            }
         }
      }catch (Exception e){
         logger.error(e.getMessage());
         wsCallResult = new WSCallResult(new WSCallStatus(1,"FAIL"),null);
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
      }
      return wsCallResult;
   }

}
