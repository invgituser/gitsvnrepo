package com.invessence.fileProcessor.util;

/**
 * Created by abhangp on 7/3/2017.
 */

import java.io.*;
import java.util.*;

import com.invessence.fileProcessor.bean.FileProcessAudit;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.bean.fileProcessor.FileDetails;
import com.invessence.service.util.*;
import com.jcraft.jsch.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileProcessorUtil
{
   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FileProcessorUtil.class);

   @Autowired
   FileProcessorDao fileProcessorDao;

   public String getFilePath(ServiceRequest serviceRequest, FileDetails fileDetails,
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
   public String getFileName(FileDetails fileDetails, String date){
      StringBuilder filePath= new StringBuilder();
      if(fileDetails.getFileNameAppender().equalsIgnoreCase("PREFIX")){
         filePath.append(date+"_").append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
      }else if(fileDetails.getFileNameAppender().equalsIgnoreCase("POSTFIX")){
         filePath.append(fileDetails.getFileName()).append("_"+date).append("."+fileDetails.getFileExtension());
      }
      return filePath.toString();
   }

   public void executeDBProcess(FileDetails fileDetails, String event, StringBuilder mailAlertMsg){
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

   public static void executeInstruction(FileDetails fileDetails, String event, StringBuilder mailAlertMsg, ServiceRequest serviceRequest){
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
//         executeCommand(fileDetails.getParentPreInstruction());
               executeCommandOnServer(fileDetails.getParentPreInstruction(), serviceRequest);
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

   public static String executeCommand(String command) {
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
      }catch(Exception e){
         logger.error(e.getMessage());
         //   e.printStackTrace();
      }
      return output.toString();
   }

   public static String executeCommandOnServer(String command, ServiceRequest serviceRequest) {
      StringBuffer output = new StringBuffer();
      List<String> result = new ArrayList<String>();
      try{

         String command1=command;//"/home/support/test.sh";

         java.util.Properties config = new java.util.Properties();
         config.put("StrictHostKeyChecking", "no");
         JSch jsch = new JSch();
         Session session=null;
         session = jsch.getSession(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_SFTP_USERNAME"),
                                   ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_SFTP_HOST"), 22);
         session.setPassword(ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(), serviceRequest.getMode(), "DOWNLOAD_SFTP_PASSWORD"));
         session.setConfig("StrictHostKeyChecking", "no");
         session.connect();
         System.out.println("Connected");

         //create the excution channel over the session
         ChannelExec channel = (ChannelExec)session.openChannel("exec");

         // Gets an InputStream for this channel. All data arriving in as messages from the remote side can be read from this stream.
         InputStream in = channel.getInputStream();

         // Set the command that you want to execute
         // In our case its the remote shell script
         channel.setCommand("sh "+command1);

         // Execute the command
         channel.connect();

         // Read the output from the input stream we set above
         BufferedReader reader = new BufferedReader(new InputStreamReader(in));
         String line;

         //Read each line from the buffered reader and add it to result list
         // You can also simple print the result here
         while ((line = reader.readLine()) != null)
         {
            result.add(line);
            System.out.println("line = " + line);
         }

         //retrieve the exit status of the remote command corresponding to this channel
         int exitStatus = channel.getExitStatus();

         //Safely disconnect channel and disconnect session. If not done then it may cause resource leak

         InputStream in1= null;
         try
         {
            in1 = channel.getInputStream();

            channel.connect();
            byte[] tmp = new byte[1024];
            while (true)
            {
               while (in1.available() > 0)
               {
                  int i = in1.read(tmp, 0, 1024);
                  if (i < 0) break;
                  System.out.print(new String(tmp, 0, i));
               }
               if (channel.isClosed())
               {
                  System.out.println("exit-status: " + channel.getExitStatus());
                  break;
               }
            }
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
         channel.disconnect();
         session.disconnect();

         if(exitStatus < 0){
            System.out.println("Done, but exit status not set!");
         }
         else if(exitStatus > 0){
            System.out.println("Done, but with error!");
         }
         else{
            System.out.println("Done!");
         }
//         Process p;
//
//         p = Runtime.getRuntime().exec(command);
//         p.waitFor();
//         BufferedReader reader =
//            new BufferedReader(new InputStreamReader(p.getInputStream()));
//
//         String line = "";
//         while ((line = reader.readLine())!= null) {
//            output.append(line + "\n");
//         }
      }catch(Exception e){
         logger.error(e.getMessage());
         //   e.printStackTrace();
      }
      return output.toString();
   }


   public void auditEntry(ServiceRequest serviceRequest, FileDetails fileDetails, String status, String remarks)throws Exception{

      try      {
         fileProcessorDao.dbCallAudit(new FileProcessAudit(null, serviceRequest.getProduct(),
                                                           serviceRequest.getMode(), fileDetails.getProcess(),
                                                           fileDetails.getProcessId(), fileDetails.getFileName(), status, remarks, null, null));
      }catch(Exception e){
         logger.error(e.getMessage());
      }

   }
}
