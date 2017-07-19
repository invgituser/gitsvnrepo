package com.invessence.fileProcessor.util;

/**
 * Created by abhangp on 7/3/2017.
 */

import java.io.*;
import java.text.SimpleDateFormat;
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

   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   SimpleDateFormat sdfFileParsing = new SimpleDateFormat("yyyyMMdd");

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
      String dbProcess=null;
      try{
         if(event.equalsIgnoreCase("PARENTPRE"))
         {
            dbProcess=fileDetails.getParentPreDBProcess();
         }else if(event.equalsIgnoreCase("PARENTPOST"))
         {
            dbProcess=fileDetails.getParentPostDBProcess();
         }else if(event.equalsIgnoreCase("PRE"))
         {
            dbProcess=fileDetails.getPreDBProcess();
         }else if(event.equalsIgnoreCase("POST"))
         {
            dbProcess=fileDetails.getPostDBProcess();
         }
         if (dbProcess == null || dbProcess.trim().equals(""))
         {
            logger.info("No "+event+" DB Process Script for execution.\n");
         }
         else
         {
            logger.info("Calling "+event+" DB Process Script:" + dbProcess + "\n");
            fileProcessorDao.callProcedure(dbProcess);
         }
      }catch(Exception e){
         mailAlertMsg.append("FileProcessor.executeDBProcess" +e.getMessage()+"\n");
         logger.error(e.getMessage());
         //e.printStackTrace();
      }
   }

   public void executeInstruction(FileDetails fileDetails, String event, StringBuilder mailAlertMsg, ServiceRequest serviceRequest){
      String instruction=null;
      try{
         if(event.equalsIgnoreCase("PARENTPRE"))
         {
            instruction=fileDetails.getParentPreInstruction();
         }else if(event.equalsIgnoreCase("PARENTPOST"))
         {
            instruction=fileDetails.getParentPostInstruction();
         }else if(event.equalsIgnoreCase("PRE"))
         {
            instruction=fileDetails.getPreInstruction();
         }else if(event.equalsIgnoreCase("POST"))
         {
            instruction=fileDetails.getPostInstruction();
         }
         if (instruction == null || instruction.trim().equals(""))
         {
            logger.info("No "+event+" Instruction Script for execution.\n");
         }
         else
         {
            logger.info("Calling "+event+" Instruction Script :" + instruction + "\n");
            executeCommand(instruction);
//          executeCommandOnServer(fileDetails.getParentPreInstruction(), serviceRequest);
         }
//   throw new Exception();
      }catch(Exception e){
         mailAlertMsg.append("FileProcessor.executeInstruction" +e.getMessage()+"\n");
         logger.error(e.getMessage());
//   e.printStackTrace();
      }
   }

   public String executeCommand(String command) throws Exception {
      StringBuffer output = new StringBuffer();
//      try{
         Process p;

         p = Runtime.getRuntime().exec(command);
         p.waitFor();
         BufferedReader reader =
            new BufferedReader(new InputStreamReader(p.getInputStream()));

         String line = "";
         while ((line = reader.readLine())!= null) {
            output.append(line + "\n");
         }
//      }catch(Exception e){
//         logger.error(e.getMessage());
//         //   e.printStackTrace();
//      }
      return output.toString();
   }

   public String executeCommandOnServer(String command, ServiceRequest serviceRequest) {
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

   public void auditEntry(ServiceRequest serviceRequest, FileDetails fileDetails, String status, String remarks, String processIdentifier)throws Exception{

      try      {
         fileProcessorDao.dbCallAudit(new FileProcessAudit(null, serviceRequest.getProduct(),
                                                           serviceRequest.getMode(), fileDetails.getProcess(),
                                                           fileDetails.getProcessId(), processIdentifier==null?fileDetails.getFileName():processIdentifier, status, remarks, null, null));
      }catch(Exception e){
         logger.error(e.getMessage());
      }

   }

   public List<String> getListOfFiles(String directoryName, final String fileName){
      List<String> filesToDelete =null;
      File directory = new File(directoryName);
      FilenameFilter mp3Filter = new FilenameFilter() {
         public boolean accept(File file, String name) {
            if (name.contains(fileName)) {
               // filters files whose extension is .mp3
               return true;
            } else {
               return false;
            }
         }
      };

      //get all the files from a directory

      File[] fList = directory.listFiles(mp3Filter);
      filesToDelete =new ArrayList<String>();
      for (File file : fList){
         if (file.isFile()){
            System.out.println(file.getName());
            filesToDelete.add(file.getName());
         }
      }
      return filesToDelete;
   }

   public void deleteFilesFromServer(FileDetails fileDetails, List<String> fileNameLst, ChannelSftp channel, String businessDate, StringBuilder mailAlertMsg){
      try{
         if(fileDetails.getDelFlagServerFile().equalsIgnoreCase("Y")){
            if (fileNameLst == null || fileNameLst.size() == 0)
            {
               logger.info(fileDetails.getFileName() + " files are not available on server to delete.");
            }
            else
            {
               if(fileDetails.getDelDayServerFile()>0)
               {
                  Calendar calendar = Calendar.getInstance();
                  calendar.setTime(sdfFileParsing.parse(businessDate));
                  calendar.add(Calendar.DATE, -fileDetails.getDelDayServerFile());
                  Date lastDate = calendar.getTime();
                  List<String> filesToDelete = getFilesToDelete(fileNameLst, lastDate, fileDetails.getFileName(),"Server");
                  if (filesToDelete == null || filesToDelete.size() == 0)
                  {
                     logger.info(fileDetails.getFileName() + " files are not available on server to delete.");
                  }
                  else
                  {
                     Iterator<String> itr = filesToDelete.iterator();
                     while (itr.hasNext())
                     {
                        String fileToDelete = (String) itr.next();
                        logger.info("Deleting file :" + fileToDelete);
                        channel.rm(fileToDelete);
                     }
                  }
               }else{
                  logger.info(fileDetails.getFileName() + "Number of days :"+fileDetails.getDelDayServerFile()+" are not proper to delete from server.");
               }
            }
         }
      }
      catch (Exception e)
      {
         mailAlertMsg.append("Checking " + fileDetails.getFileName() + " files to delete from Server for business date :" + businessDate + "\n" + e.getMessage());
         logger.error("Checking " + fileDetails.getFileName() + " files to delete from Server for business date :" + businessDate + "\n" + e.getMessage());
         logger.error(e.getStackTrace());
      }
   }

   public void deleteFilesFromLocal(FileDetails fileDetails, List<String> fileNameLst, String businessDate, String localDirectory, StringBuilder mailAlertMsg)
   {
      File deleteFileName=null;
      try{
         if(fileDetails.getDelFlagLocalFile().equalsIgnoreCase("Y")){
            if (fileNameLst == null || fileNameLst.size() == 0)
            {
               logger.info(fileDetails.getFileName() + " files are not available on Local System to delete.");
            }
            else
            {
               if(fileDetails.getDelDayLocalFile()>0)
               {
                  Calendar calendar = Calendar.getInstance();
                  calendar.setTime(sdfFileParsing.parse(businessDate));
                  calendar.add(Calendar.DATE, -fileDetails.getDelDayLocalFile());
                  Date lastDate = calendar.getTime();
                  List<String> filesToDelete = getFilesToDelete(fileNameLst, lastDate, fileDetails.getFileName(),"Local");
                  if (filesToDelete == null || filesToDelete.size() == 0)
                  {
                     logger.info(fileDetails.getFileName() + " files are not available on Local System to delete.");
                  }
                  else
                  {
                     Iterator<String> itr = filesToDelete.iterator();
                     while (itr.hasNext())
                     {
                        String fileToDelete = (String) itr.next();
                        logger.info("Deleting file :" +localDirectory+"/"+ fileToDelete);
                        deleteFileName=new File(localDirectory+"/"+fileToDelete);
                        deleteFileName.delete();
                     }
                  }
               }else{
                  logger.info(fileDetails.getFileName() + "Number of days :"+fileDetails.getDelDayServerFile()+" are not proper to delete from Local System.");
               }
            }
         }
      }
      catch (Exception e)
      {
         mailAlertMsg.append("Checking " + fileDetails.getFileName() + " files to delete from Local System for business date :" + businessDate + "\n" + e.getMessage());
         logger.error("Checking " + fileDetails.getFileName() + " files to delete from Local System for business date :" + businessDate + "\n" + e.getMessage());
         logger.error(e.getStackTrace());
      }
   }

   public List<String> getFilesToLoad(List<String> fileNameLst, String businessDate, FileDetails fileDetails)
   {
      List<String> fileLstToLoad = null;
      logger.info("Checking " + fileDetails.getFileName()+ " files to load into DB for business date :" + businessDate);
      try
      {
         Date inputDate=sdfFileParsing.parse(businessDate);
         fileLstToLoad = new ArrayList<String>();
         Iterator<String> itr = fileNameLst.iterator();
         while (itr.hasNext())
         {
            String fileToLoadDB = (String) itr.next();
            String strDateForCompare=fileToLoadDB.replaceAll(fileDetails.getFileName(),"").replaceAll("_","");
            try
            {

//               Date date = sdfFileParsing.parse(fileToDelete.substring(fileToDelete.lastIndexOf("_") + 1, fileToDelete.lastIndexOf(".")));
               Date date = sdfFileParsing.parse(strDateForCompare.substring(0, strDateForCompare.lastIndexOf(".")));//

//               if (date.before(lastDate) || date.equals(lastDate))

//
////            String strDate = fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_") + 1, fileToLoadDB.lastIndexOf("."));
//            try
//            {
//
//               Date date = sdfFileParsing.parse(fileToLoadDB.substring(fileToLoadDB.lastIndexOf("_") + 1, fileToLoadDB.lastIndexOf(".")));

               if (date.equals(inputDate) ) {
//                if(date.equals(businessDate) || date.after(businessDate)){
                  StringBuilder fileName= new StringBuilder();
                  if(fileDetails.getFileNameAppender().equalsIgnoreCase("PREFIX")){
                     fileName.append(businessDate+"_").append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
                  }else if(fileDetails.getFileNameAppender().equalsIgnoreCase("POSTFIX")){
                     fileName.append(fileDetails.getFileName()).append("_"+businessDate).append("."+fileDetails.getFileExtension());
                  }else{
                     fileName.append(fileDetails.getFileName()).append("."+fileDetails.getFileExtension());
                  }
                  if(fileToLoadDB.equalsIgnoreCase(fileName.toString()))
                  {
                     fileLstToLoad.add(fileToLoadDB);
                     logger.info("File to load into DB :" + fileToLoadDB);
                  }
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
         logger.error("Checking " + fileDetails.getFileName() + " files to load into DB for business date :" + businessDate + "\n" + e.getMessage());
         logger.error(e.getStackTrace());
      }
      return fileLstToLoad;
   }

   private List<String> getFilesToDelete(List<String> fileNameLst, Date lastDate, String fileName, String from)
   {
      List<String> filesToDelete = null;
      logger.info("Checking " + fileName + " files to delete from "+from+" system before business date :" + lastDate);
      try
      {

         filesToDelete = new ArrayList<String>();
         Iterator<String> itr = fileNameLst.iterator();
         while (itr.hasNext())
         {
            String fileToDelete = (String) itr.next();

            //String strDate = fileToDelete.substring(fileToDelete.lastIndexOf("_") + 1, fileToDelete.lastIndexOf("."));
            String strDateForCompare=fileToDelete.replaceAll(fileName,"").replaceAll("_","");
            try
            {

//               Date date = sdfFileParsing.parse(fileToDelete.substring(fileToDelete.lastIndexOf("_") + 1, fileToDelete.lastIndexOf(".")));
               Date date = sdfFileParsing.parse(strDateForCompare.substring(0, strDateForCompare.lastIndexOf(".")));//

               if (date.before(lastDate) || date.equals(lastDate))
               {
                  filesToDelete.add(fileToDelete);
                  logger.info("File to delete :" + fileToDelete);
               }
            }
            catch (Exception e)
            {
               logger.error("Date parsing issue");
               logger.error(e.getStackTrace());
            }
         }

      }
      catch (Exception e)
      {
         logger.error("While checking " + fileName + " files to delete from "+from+" system before business date :" + lastDate + "\n" + e.getMessage());
         logger.error(e.getStackTrace());
      }
      return filesToDelete;
   }

}
