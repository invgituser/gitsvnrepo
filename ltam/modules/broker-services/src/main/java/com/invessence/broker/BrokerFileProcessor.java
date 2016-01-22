package com.invessence.broker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.broker.bean.*;
import com.invessence.broker.dao.*;
import com.invessence.broker.util.*;
import com.jcraft.jsch.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.*;
import org.springframework.stereotype.Component;

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
   protected MessageSource resource;

   protected String getMessage(String code, Object[] object, Locale locale) {
      return resource.getMessage(code, object, locale);
   }
   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

   public void process() {
      StringBuilder mailAlertMsg=null;
      try {
         mailAlertMsg=new StringBuilder();
         //System.out.println("Parameters"+Parameters.sqlInsertNewaccounts);
         Map<String, DBParameters> dbParamMap = commonDao.getDBParametres();
         logger.info("BROKER_BDATE :" + dbParamMap.get("BROKER_BDATE").getValue());
         if(dbParamMap==null || dbParamMap.size()==0 || ! dbParamMap.containsKey("BROKER_BDATE")){
            mailAlertMsg.append("Required DB parameters not available");
            System.out.println("Required DB parameters not available");
         }else {
            List<BrokerHostDetails> hostLst = commonDao.getBrokerHostDetails("");
            if (hostLst == null && hostLst.size() == 0)
            {
               mailAlertMsg.append("Required Host details not available");
               System.out.println("Required Host details not available");
            }else{
               Iterator<BrokerHostDetails> hostDetailsItr = hostLst.iterator();
               while (hostDetailsItr.hasNext())
               {
               BrokerHostDetails hostDetails = (BrokerHostDetails) hostDetailsItr.next();
               logger.info(hostDetails.toString());
               List<DownloadFileDetails> downloadFilesLst = commonDao.getDownloadFileDetails("where active = 'Y' and vendor='" + hostDetails.getVendor() + "'");
                  if(downloadFilesLst == null && downloadFilesLst.size() == 0)
                  {
                     mailAlertMsg.append("Download files are not available for "+hostDetails.getVendor());
                     System.out.println("Download files are not available for " +hostDetails.getVendor());
                  }else               {
                  JSch jsch = new JSch();
                  Session session = null;
                  session = jsch.getSession(hostDetails.getUsername(), hostDetails.getHost(), 22);
                  session.setPassword(hostDetails.getPassword());
                  session.setConfig("StrictHostKeyChecking", "no");
                  session.connect();

                  ChannelSftp channel = null;
                  channel = (ChannelSftp) session.openChannel("sftp");
                  channel.connect();
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
                        }
                     }
                     if(mayDownloadFile) {
                        try
                        {
                           InputStream in = channel.get(downloadFileDetails.getFileName() + "_" + dbParamMap.get("BROKER_BDATE").getValue() + ".csv");
                           // set local file
                           File localDir = new File(getMessage("baseDirectory", null, null) + "/" + downloadFileDetails.getDownloadDir() + "/");
                           System.out.println("theDir :" + localDir);
                           // if the directory does not exist, create it
                           if (!localDir.exists())
                           {
                              try
                              {
                                 System.out.println("Creating local directory :" + localDir);
                                 localDir.mkdirs();
                              }
                              catch (Exception e)
                              {
                                 exceptionHandler(e, mailAlertMsg, "While local directory creation");
                              }
                           }
                           String localFileName = localDir + "/" + downloadFileDetails.getFileName() + "_" + dbParamMap.get("BROKER_BDATE").getValue() + "."+downloadFileDetails.getFormat();

                           try
                           {
                              FileOutputStream tergetFile = new FileOutputStream(localFileName);

                              // read containts of remote file to local
                              int c;
                              while ((c = in.read()) != -1)
                              {
                                 tergetFile.write(c);
                              }

                              in.close();
                              tergetFile.close();
                              tergetFile.flush();
                              if(downloadFileDetails.getFormat().equalsIgnoreCase("csv"))
                              {
                                 try
                                 {
                                    processCsvFile(localFileName, downloadFileDetails);
                                 }
                                 catch (Exception e)
                                 {
                                    exceptionHandler(e, mailAlertMsg, "While "+downloadFileDetails.getFileName()+" csv file processing");
                                 }
                              }
                           }
                           catch (Exception e)
                           {
                              exceptionHandler(e, mailAlertMsg, "While "+downloadFileDetails.getFileName()+" file coping into local directory");
                           }

                           System.out.println("Done");
                        }catch(SftpException e){
                           if(downloadFileDetails.getRequired().equalsIgnoreCase("Y")) {
                              mailAlertMsg.append(downloadFileDetails.getFileName()+" file is required but its not available on server");
                           }
                        } catch (Exception e)
                        {
                           exceptionHandler(e, mailAlertMsg, "While "+downloadFileDetails.getFileName()+"file coping from server");
                        }
                     }
                  }
                  channel.disconnect();
                  session.disconnect();
               }

            }
            }

         }
      } catch (Exception e) {
         exceptionHandler(e, mailAlertMsg, "While processing files");

      }finally
      {
         if( mailAlertMsg.length()>0){
            System.out.println("MailAlertMsg :"+ mailAlertMsg);
         }else{
            System.out.println("MailAlertMsg is empty");
         }
      }
   }

//   private void SftpFiles(List<DownloadFileDetails> fileLst){
//      try{
//
//      }catch (Exception e){
//         e.printStackTrace();
//      }
//   }

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
               inLst.add(lineArr);
            }
         }
         System.out.println("MailAlertMsg is empty"+inLst.size());
         if(fileDetails.getContainsheader().equalsIgnoreCase("Y") && inLst !=null && inLst.size()>0){
            System.out.println("WE R INSIDE MailAlertMsg is empty"+inLst.size());
            inLst.remove(0);
         }
         System.out.println("MailAlertMsg is empty"+inLst.size());
         commonDao.trancateTable(fileDetails.getTmp_TableName());
         commonDao.insertBatch(inLst,getMessage("sqlInsert"+fileDetails.getTmp_TableName(),null,null), fileDetails.getPostInstruction());
      }finally {
         if (br != null) {try { br.close(); } catch (IOException e) {  e.printStackTrace();}}
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
      try{

         System.out.println("Exception Class :" + ex.getClass());
         ex.printStackTrace();
         logger.error(ex.getMessage());
         logger.error(CommonUtil.stackTraceToString(ex.getStackTrace()));

         if(ex instanceof FileNotFoundException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage()+"\n");
            System.out.println(process+" : " + ex.getMessage());
         }else if(ex instanceof MySQLIntegrityConstraintViolationException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage()+"\n");
            System.out.println(process+" : " + ex.getMessage());
         }else if(ex instanceof BadSqlGrammarException)
         {
            mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
            System.out.println(process + " : " + ex.getMessage());
         }else if(ex instanceof CannotGetJdbcConnectionException)
         {
            mailAlertMsg.append(process+" : " + ex.getMessage()+"\n");
            System.out.println(process+" : " + ex.getMessage());
         }else
         {
            mailAlertMsg.append(process+" : " +  ex.getMessage()+"\n");
            System.out.println(process+" : " + ex.getMessage());
         }
      }catch(Exception e){
         mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
         System.out.println(process + " : " + ex.getMessage());
         ex.printStackTrace();
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
