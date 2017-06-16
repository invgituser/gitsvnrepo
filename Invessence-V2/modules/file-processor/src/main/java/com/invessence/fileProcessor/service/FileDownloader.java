package com.invessence.fileProcessor.service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.fileProcessor.dao.FileProcessorDao;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 4/25/2017.
 */
@Component
public class FileDownloader
{
   private static final Logger logger = Logger.getLogger(FileDownloader.class);
   @Autowired
   FileProcessorDao fileProcessorDao;

   SQLData convert = new SQLData();

   public void download(ServiceRequest serviceRequest, FileDetails fileDetails, LinkedHashMap<String,FileRules> fileRules){
      try
      {
         System.out.println("FileDownloader.download");

         System.out.println("serviceRequest = [" + serviceRequest + "], fileDetails = [" + fileDetails + "], fileRules = [" + fileRules + "]");
         ArrayList<LinkedHashMap<String, Object>> rows=(ArrayList<LinkedHashMap<String, Object>>)fileProcessorDao.dbCall(serviceRequest.getProduct(), Constant.SERVICES.FILE_PROCESS.toString(),
                                 Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString(), fileDetails.getPreDBProcess());
         if(rows==null || rows.size()==0){
            System.out.println("Data not available in "+fileDetails.getPreDBProcess()+" for File Processor ");
         }else{
            Iterator<LinkedHashMap<String, Object>> itr = rows.iterator();
            List<String> fileData=new ArrayList<String>();
            if(fileDetails.getContainsHeader().equalsIgnoreCase("Y")){
               StringBuilder fileHeader=new StringBuilder();
               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
               while (entries.hasNext())
               {
                  Map.Entry<String, FileRules> entry6 = entries.next();
                  FileRules fileRules1=(FileRules) entry6.getValue();
                  fileHeader.append(entry6.getKey()).append(fileRules1.getDelimiter());
//                  if(fileRules1.getIsDelimited().equalsIgnoreCase("N")){
//                     return appendSpace(entry6.getKey().toString(), fileRules1.getLength());
//                  }else if(fileRules1.getIsDelimited().equalsIgnoreCase("Y")){
//                     return val+fileRules1.getDelimiter();
//                  }
               }
               fileData.add(fileHeader.toString());
            }
            while (itr.hasNext())
            {
               StringBuilder fileRow=new StringBuilder();
               LinkedHashMap<String, Object> map = itr.next();
               Iterator<Map.Entry<String, FileRules>> entries = fileRules.entrySet().iterator();
               while (entries.hasNext())
               {
                  Map.Entry<String,FileRules> entry6 = entries.next();
                  FileRules fileRules1=(FileRules) entry6.getValue();
//                  System.out.println(fileRules1.getSeqNo()+" : "+fileRules1.getDataField()+" : "+fileRules1.getDbColumn()+" : "+convert.getIntData(map.get(fileRules1.getDbColumn())));
                  fileRow.append(getValue(""+map.get(fileRules1.getDbColumn()), fileRules1));
               }
               if(fileRow.length()>0){
                  fileData.add(fileRow.toString());
               }
//               System.out.println("-------------------------------------------------");
            }

            if(fileData.size()>0){
               generateFile(fileData, fileDetails);
            }else{
               logger.warn("Data not available for "+fileDetails.getFileName()+" file.");
            }
         }
      }catch(Exception e){
         e.printStackTrace();

      }
   }

   public String getValue(String val, FileRules fileRules){
      if(fileRules.getFormat().equalsIgnoreCase("TEXT")){
         if(fileRules.getIsDelimited().equalsIgnoreCase("N")){
            return appendSpace(val, fileRules.getLength(), fileRules.getJustified());
         }else if(fileRules.getIsDelimited().equalsIgnoreCase("Y")){
            return val+fileRules.getDelimiter();
         }
      }else if(fileRules.getFormat().equalsIgnoreCase("NUMERIC")){
         if(fileRules.getIsDelimited().equalsIgnoreCase("N")){
            return appendSpace(val, fileRules.getLength(), fileRules.getJustified());
         }else if(fileRules.getIsDelimited().equalsIgnoreCase("Y")){
            return val+fileRules.getDelimiter();
         }
      }else{
         System.out.println("Condition not mapped for format "+ fileRules.getFormat());
      }
      return val;
   }

   private String appendSpace(String val, Integer len, String position){
      StringBuilder sb=new StringBuilder();
      if(position==null  || position.equalsIgnoreCase("LEFT"))
      {
         sb.append(val);
         for (int i = 0; i < len - val.length(); i++)
         {
            sb.append(" ");
         }
      }else  if(position.equalsIgnoreCase("RIGHT"))
      {
         for (int i = 0; i < len - val.length(); i++)
         {
            sb.append(" ");
         }
         sb.append(val);
      }
      return sb.toString();
   }
   private void generateFile(List<String> fileData, FileDetails fileDetails){
      System.out.println("FileDownloader.generateFile");

      try{
         Iterator<String> fileDataItr=fileData.iterator();

         File file = new File(fileDetails.getUploadDir()+"/"+createFileName(fileDetails));
         if (!file.getParentFile().exists())
         {
            try
            {
               logger.info("Creating upload directory :" + file);
               file.getParentFile().mkdirs();
            }
            catch (Exception e)
            {
               logger.error("Creating upload directory :" + file + " \n" + e.getMessage());
            }
         }
         FileWriter fr = null;
         BufferedWriter br = null;
         //String dataWithNewLine=data+System.getProperty("line.separator");
         try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            while(fileDataItr.hasNext()){
               String row=fileDataItr.next();
               br.write(row);
               br.newLine();
            }
         } catch (IOException e) {
            e.printStackTrace();
         }finally{
            try {
               br.close();
               fr.close();
            } catch (IOException e) {
               e.printStackTrace();
            }
         }

      }catch(Exception e){
         e.printStackTrace();
      }

   }

   private String createFileName(FileDetails fileDetails){
      System.out.println("FileDownloader.createFileName");
      StringBuilder fileName=new StringBuilder();

      try{
         if(fileDetails.getFileNameAppender()==null || fileDetails.getFileNameAppender().equalsIgnoreCase(""))
         {
            return fileName.append(fileDetails.getFileName()).append(".").append(fileDetails.getFileExtension()).toString();
         }else
         {
            if (fileDetails.getFileNameAppender().equalsIgnoreCase("PREFIX"))
            {
               String formattedDate = getFormatedDate(fileDetails.getAppenderFormat());
               if (formattedDate != null)
               {
                  return fileName.append(formattedDate).append(fileDetails.getFileName()).append(".").append(fileDetails.getFileExtension()).toString();
               }
            }
            if (fileDetails.getFileNameAppender().equalsIgnoreCase("POSTFIX"))
            {
               String formattedDate = getFormatedDate(fileDetails.getAppenderFormat());
               if (formattedDate != null)
               {
                  return fileName.append(fileDetails.getFileName()).append(formattedDate).append(".").append(fileDetails.getFileExtension()).toString();
               }
            }
         }
      }catch(Exception e){
         e.printStackTrace();
      }
      return fileName.append(fileDetails.getFileName()).append(".").append(fileDetails.getFileExtension()).toString();
   }


   private String getFormatedDate(String dateFormat){

      try{
         if(dateFormat==null){
            logger.warn("DateFormat is not available!");
         }else
         {
            SimpleDateFormat mdyFormat = new SimpleDateFormat(dateFormat);
            Date myDate = new Date();
            return mdyFormat.format(myDate);
         }
      }catch(Exception e){
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return null;
   }

}
