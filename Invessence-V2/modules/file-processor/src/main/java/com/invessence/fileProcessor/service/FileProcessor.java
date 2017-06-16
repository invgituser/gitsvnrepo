package com.invessence.fileProcessor.service;

import java.util.*;

import com.invessence.fileProcessor.bean.*;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.service.bean.ServiceRequest;
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

   StringBuilder mailAlertMsg = null;

   public void process(ServiceRequest serviceRequest){
      logger.info("FileProcessor.process");
      logger.info("serviceRequest = [" + serviceRequest + "]");
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
               logger.warn("Files are not available for FileProcessor");
            }
            else
            {
               Map<String, Map<String, FileRules>> fileRulesMap = (Map<String, Map<String, FileRules>>) ServiceDetails.getCommonDetails(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), Constant.COMMON_DETAILS.FILE_RULES.toString());
               logger.info(fileList);
               LinkedHashMap<String, FileRules> fileRules = null;
               Iterator<FileDetails> itr = fileList.iterator();
               while (itr.hasNext())
               {
                  FileDetails fileDetails = (FileDetails) itr.next();
                  if (fileDetails.getProcessId().equalsIgnoreCase(serviceRequest.getProcessId()))
                  {
                     if (fileDetails.getProcess().equalsIgnoreCase("DOWNLOAD"))
                     {
                        fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                        fileDownloader.download(serviceRequest, fileDetails, fileRules);
                        fileProcessorDao.dbCallAudit(new FileProcessAudit(null, serviceRequest.getProduct(),
                                                                          serviceRequest.getMode(), fileDetails.getProcess(),
                                                                          fileDetails.getProcessId(), fileDetails.getFileName(), "S", "Success", null, null));

                     }
                     else if (fileDetails.getProcess().equalsIgnoreCase("UPLOAD"))
                     {
                        fileRules = (LinkedHashMap<String, FileRules>) fileRulesMap.get(fileDetails.getFileId());
                        fileUploader.upload(serviceRequest, fileDetails, fileRules,  mailAlertMsg, dbParamMap);
                     }
                  }
                  else
                  {
                     logger.warn(fileDetails.getFileName() + " gets avoid from process execution, because it's not belong to processId :" + serviceRequest.getProcessId());
                  }
               }
            }
         }
      }catch (Exception e){
         logger.error(e.getMessage());
         e.printStackTrace();
      }
   }
}
