package com.invessence.broker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.broker.bean.*;
import com.invessence.broker.dao.*;
import com.invessence.broker.util.*;
import com.invessence.emailer.util.EmailCreator;
import com.invessence.service.util.*;
import com.invessence.util.*;
import com.jcraft.jsch.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
   EmailCreator emailCreator;

   @Autowired
   GPGUtil gpgUtil;

//   @Autowired
//   protected MessageSource resource;
//
//   protected String getMessage(String code, Object[] object, Locale locale) {
//      return resource.getMessage(code, object, locale);
//   }

   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   SimpleDateFormat sdfFileParsing = new SimpleDateFormat("yyyyMMdd");

   private String baseDirectory;
   private String eodProcedure;

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
//            List<BrokerHostDetails> hostLst = commonDao.getBrokerHostDetails("");
//            if (hostLst == null && hostLst.size() == 0)
            if (! ServiceValidator.validateBrokerService(ServiceParameters.BROKER_WEBSERVICE_API))
            {
               mailAlertMsg.append("Required Host details are not available");
               logger.info("Required Host details are not available");
            }else{
//               Iterator<BrokerHostDetails> hostDetailsItr = hostLst.iterator();
//               while (hostDetailsItr.hasNext())
//               {
//                  BrokerHostDetails hostDetails = (BrokerHostDetails) hostDetailsItr.next();
//               String encryDecryKey="aRXDugfr4WQpVrxu";
//               logger.info("Host Details :"+hostDetails.toString());
               List<DownloadFileDetails> downloadFilesLst = commonDao.getDownloadFileDetails("where active = 'Y' and vendor='" + ServiceParameters.BROKER_SERVICE_API + "'");
               if(downloadFilesLst == null && downloadFilesLst.size() == 0)
               {
                  mailAlertMsg.append("Download files details are not available for broker :"+ServiceParameters.BROKER_SERVICE_API);
                  logger.info("Download files details are not available for broker :" + ServiceParameters.BROKER_SERVICE_API);
               }else{
                  try
                  {
                     //
                     JSch jsch = new JSch();
                     Session session = null;
                     session = jsch.getSession(ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_USERNAME, ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_HOST, 22);
                     session.setPassword(ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_PASSWORD);
                     session.setConfig("StrictHostKeyChecking", "no");
                     session.connect();

                     logger.info("Established the connection with host server");

                     ChannelSftp channel = null;
                     channel = (ChannelSftp) session.openChannel("sftp");
                     channel.connect();
                     try{
                        channel.cd(ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_SRC_DIRECTORY);
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
                              Vector v = channel.ls(downloadFileDetails.getFileName() +"*"+downloadFileDetails.getFileExtension());
                              logger.info("Fetching list of " + downloadFileDetails.getFileName() +"*"+downloadFileDetails.getFileExtension() + " files from server"+v.size());
                              ChannelSftp.LsEntry entry = null;
                              for (int i = 0; i < v.size(); i++)
                              {
                                 entry = (ChannelSftp.LsEntry) v.get(i);
                                 fileNameLst.add(entry.getFilename());
                              }
                              logger.info("Fetching list of " + downloadFileDetails.getFileName() + " files from server"+v.size());
                              if (fileNameLst == null || fileNameLst.size() == 0)
                              {
                                 mailAlertMsg.append(downloadFileDetails.getFileName() + " files are not available on server for download.\n");
                                 logger.info(downloadFileDetails.getFileName() + " files are not available on server for download.");
                              }
                              else
                              {
                                 Collections.sort(fileNameLst);
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
                                                logger.error("Creating local directory :" + localDirectory +" \n"+e.getMessage());
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
                                             if (downloadFileDetails.getEncryptionMethod() == null || downloadFileDetails.getEncryptionMethod().equals(""))
                                             {
                                                if (downloadFileDetails.getLoadFormat().equalsIgnoreCase("csv"))
                                                {
                                                   try
                                                   {
                                                      processCsvFile(mailAlertMsg, localFileName, downloadFileDetails, ServiceParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY);//hostDetails.getEncrDecrKey()
                                                   }
                                                   catch (Exception e)
                                                   {
                                                      logger.error("While " + fileToDownload + " csv file processing \n" + e.getMessage());
                                                      //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                                      mailAlertMsg.append("Issue " + fileToDownload + " csv file processing \n");
                                                   }
                                                }
                                             } else

                                             {

                                                //(InputStream in, OutputStream out, InputStream secKeyIn, InputStream pubKeyIn, char[] pass)
                                                String decryptedFileName = localDirectory + "/" + fileToDownload.replace(downloadFileDetails.getFileExtension(),downloadFileDetails.getLoadFormat());
                                                try
                                                {
                                                   gpgUtil.decryptFile(new FileInputStream(localFileName), new FileOutputStream(decryptedFileName));
                                                   if (downloadFileDetails.getLoadFormat().equalsIgnoreCase("csv"))
                                                   {
                                                      try
                                                      {
                                                         processCsvFile(mailAlertMsg, decryptedFileName, downloadFileDetails, ServiceParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY);//hostDetails.getEncrDecrKey()
                                                         deleteDecryptedFile(decryptedFileName);
                                                      }
                                                      catch (Exception e)
                                                      {
                                                         logger.error("While " + fileToDownload + " csv file processing \n" + e.getMessage());
                                                         //exceptionHandler(e, mailAlertMsg, "Issue " + fileToDownload + " csv file processing");
                                                         mailAlertMsg.append("Issue " + fileToDownload + " csv file processing \n");
                                                      }
                                                   }
                                                }catch(Exception e){
                                                   e.printStackTrace();
                                                }finally
                                                {
                                                   deleteDecryptedFile(decryptedFileName);
                                                }

                                             }
                                          }catch (Exception e)
                                          {
                                             logger.error("While " + fileToDownload + " file reading into local directory \n"+e.getMessage());
                                             mailAlertMsg.append("While " + fileToDownload + " file reading into local directory \n"+e.getMessage());
                                             //exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping into local directory");
                                          }

                                       }
                                       catch (Exception e)
                                       {
                                          logger.error("While " + fileToDownload + " file coping from server \n"+e.getMessage());
                                          mailAlertMsg.append("While " + fileToDownload + " file coping from server \n"+e.getMessage());
                                          //exceptionHandler(e, mailAlertMsg, "While " + fileToDownload + " file coping from server");
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
                        logger.error("Source directory not available on Server \n"+e.getMessage());
                        logger.error(e.getStackTrace());
                     }

                  }catch (Exception e){
                     logger.error("While connecting to host server \n"+e.getMessage());
                     logger.error(e.getStackTrace());
                  }
               }
               //}
            }
         }
      } catch (Exception e) {
         logger.error("While processing files \n"+e.getMessage());
         logger.error(CommonUtil.stackTraceToString(e.getStackTrace()));
      }finally
      {
         logger.info("MailAlertMsg :"+ mailAlertMsg);
         if( mailAlertMsg.length() > 0 || mailAlertMsg.length() <= 0)
         {
            try
            {
               logger.info("Sending email to support team");
               emailCreator.sendToSupport("ERR", "Broker File Upload Process", mailAlertMsg.toString());
            }catch (Exception e)
            {
               logger.error("While email processing \n"+e.getMessage());
               logger.error(e.getStackTrace());
            }
         }else
         {
            try
            {
               logger.info("Calling EOD process \n");
               commonDao.callEODProcess(eodProcedure);
            }
            catch (Exception e)
            {
               logger.error(e.getMessage());
            }
         }
      }

   }
   private void deleteDecryptedFile(String fileName){
      try{
         File fileTodelete=new File(fileName);
         if(fileTodelete.exists()){
            logger.info("Deleting decrypted file :"+fileName+", isFileDeleted:"+fileTodelete.delete());
         }
      }catch(Exception e){
         e.printStackTrace();
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
               logger.error("Date parsing issue \n"+e.getMessage());
               logger.error(e.getStackTrace());
            }
         }

      }catch (Exception e){
         logger.error("Checking "+fileName+" files to load into DB for business date :"+businessDate+"\n"+e.getMessage());
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
         logger.error("Checking "+fileName+" files to delete from server before business date :"+lastDate+"\n"+e.getMessage());
         logger.error(e.getStackTrace());
      }
      return filesToDelete;
   }

   private void processCsvFile(StringBuilder mailAlertMsg, String csvFile,DownloadFileDetails fileDetails, String key){

      BufferedReader br = null;
      String line = "";
      String cvsSplitBy = ",(?=([^\"]|\"[^\"]*\")*$)";// "(\",(?=([^\\\"]*\\\"[^\\\"]*\\\")*[^\\\"]*$)\")";//\",";

      try
      {
         try
         {
            commonDao.truncateTable(fileDetails.getTmp_TableName());

            StringBuilder sb = null;
            try
            {
               br = new BufferedReader(new FileReader(csvFile));
               List<String[]> inLst = new LinkedList<String[]>();
               int counter=0;
               while ((line = br.readLine()) != null)
               {
                  if (!line.equals(""))
                  {
                     String[] lineArr = line.split(cvsSplitBy);
                     if(lineArr.length>fileDetails.getKeyData())
                     {
                        if (!lineArr[fileDetails.getKeyData()].trim().equals("") || lineArr[fileDetails.getKeyData()].trim() != null)
                        {
                           if(counter==0 && fileDetails.getContainsheader().equalsIgnoreCase("Y"))
                           {
                              logger.info("Avoiding first row to add in db because it's header");
                           }else{
                              inLst.add(lineArr);
                           }
                           counter++;
                        }
                     }
                  }
               }
               if(inLst.size()>0)
               {
                  StringBuilder insertQuery=new StringBuilder("insert into "+fileDetails.getTmp_TableName()+" values (");
                  int inColLen=inLst.get(0).length;
                  for(int i=1; i<=inColLen; i++){
                     insertQuery.append("?"+(i!=inColLen?",":")"));
                  }
                  if(fileDetails.getEncColumns()==null || fileDetails.getEncColumns().trim().equals("")){
                     logger.info("Encryption columns are not set");
                  }else if(fileDetails.getEncColumns()!=null && !fileDetails.getEncColumns().trim().equals(""))
                  {
                     String []encColumns=fileDetails.getEncColumns().split(",");
                     for (int i = 0; i < inLst.size(); i++)
                     {
                        String[] arr = (String[]) inLst.get(i);
                        for(int j=0; j<encColumns.length; j++){
                           try
                           {
                              int val = Integer.parseInt(encColumns[j].trim()) - 1;
                              logger.info(encColumns[j]+":"+arr.length+":"+val);
                              if(val<=arr.length)
                              {
                                 arr[val] = EncryDecryAES.encrypt(arr[val], key) ;//MsgDigester.getMessageDigest(arr[val]);
                              }else{
                                 logger.info("Encryption columns value :"+encColumns[j]+"is not valid \n");
                              }
                           }catch(Exception e){
                              //mailAlertMsg.append("Encryption columns value :"+encColumns[j]+"is not valid \n"+e.getMessage());
                              logger.error("Encryption columns value :"+encColumns[j]+"is not valid"+e.getMessage());
                           }
                        }
                        inLst.set(i, arr);
                     }
                  }
                  logger.info("insertQuery :" + insertQuery);
                  try
                  {
                     commonDao.insertBatch(inLst, insertQuery.toString(), fileDetails.getPostInstruction());
                  }catch (Exception e)
                  {
                     mailAlertMsg.append("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
                     logger.error("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
                  }

               }else
               {
                  logger.info(fileDetails.getFileName()+" file is empty");
                  if(fileDetails.getCanBeEmpty().equalsIgnoreCase("N"))
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
               mailAlertMsg.append("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
               logger.error("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
            }
            catch (IOException e)
            {
               mailAlertMsg.append("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
               logger.error("While batch insertion "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
            }
         }catch (Exception e)
         {
            mailAlertMsg.append("While delete data from table "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
            logger.error("While delete data from table "+fileDetails.getTmp_TableName()+"\n"+e.getMessage());
         }

//         if(fileDetails.getContainsheader().equalsIgnoreCase("Y") && inLst !=null && inLst.size() > 0)
//         {
//            logger.info("Removing header row of "+fileDetails.getFileName()+" file");
//            inLst.remove(0);
//         }

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


   //   public void exceptionHandler(Exception ex, StringBuilder mailAlertMsg, String process){
//      Map<String, Object> errorDetails=new HashMap<String, Object>();
//      try
//      {
//
//         logger.error("Exception Class :" + ex.getClass());
//         //ex.printStackTrace();
//         logger.error(ex.getMessage());
//         logger.error(CommonUtil.stackTraceToString(ex.getStackTrace()));
//
//         if(ex instanceof FileEmptyException)
//         {
//            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
//            logger.error(process+" : " + ex.getMessage());
//         }else  if(ex instanceof FileNotFoundException)
//         {
//            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
//            logger.error(process+" : " + ex.getMessage());
//         }else if(ex instanceof MySQLIntegrityConstraintViolationException)
//         {
//            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
//            logger.error(process+" : " + ex.getMessage());
//         }else if(ex instanceof BadSqlGrammarException)
//         {
//            mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
//            logger.error(process + " : " + ex.getMessage());
//         }else if(ex instanceof CannotGetJdbcConnectionException)
//         {
//            mailAlertMsg.append(process+" : " + ex.getMessage() + "\n");
//            logger.error(process+" : " + ex.getMessage());
//         }else
//         {
//            mailAlertMsg.append(process+" : " +  ex.getMessage() + "\n");
//            logger.error(process+" : " + ex.getMessage());
//         }
//      }catch(Exception e){
//         mailAlertMsg.append(process + " : " + ex.getMessage() + "\n");
//         logger.error(process + " : " + ex.getMessage());
//         //ex.printStackTrace();
//      }
//   }
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
