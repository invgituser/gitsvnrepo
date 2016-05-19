package com.invessence.report.billing.util;

import java.io.*;
import java.util.StringTokenizer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

@Component("config")
public class EmailMessage implements MessageSourceAware
{
   @Autowired
   private MessageSource messageSource;

   @Override
   public void setMessageSource(MessageSource messageSource) {
      this.messageSource = messageSource;
   }

   public MessageSource getMessageSource() {
      return messageSource;
   }

   private Logger logger = Logger.getLogger(EmailMessage.class.getName());


   public String getMessagetext(String inputText, Object [] obj) {
      String msgText = null;
      try {
         if (obj == null) {
            obj = new Object[]{};
         }
         if (inputText != null) {
               msgText =  messageSource.getMessage(inputText, obj, null);
            if (msgText != null)
               return msgText;
            return inputText;
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      // return inputText
      return inputText;
   }

   public String getHTMLMessagetext(String inputText, Object [] obj) {
      String msgText = null;
      try {
         if (obj == null) {
            obj = new Object[]{};
         }
         if (inputText != null) {
            msgText =  getEmailMessage(messageSource.getMessage(inputText, obj, null));
            if (msgText != null)
               return msgText;
            return inputText;
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return inputText;
   }

   public String getEmailMessage(String messageInformation){

   String message = "";
   String filePath = "";
   try
   {
      if(messageInformation != null)
      {
         StringTokenizer st = new StringTokenizer(messageInformation, "~");
         while (st.hasMoreElements())
         {
            filePath = (String) st.nextElement();
            message = getEmailTemplate(filePath);
            break;
         }
         st = new StringTokenizer(messageInformation, "~");
         int count = 0;
         int replaceValue = 0;
         while (st.hasMoreElements())
         {
            if(count ==0){
               st.nextElement();
            }
            if(count !=0){
               String strReplace = "'" + replaceValue + "'";
               String strReplaceWith = (String) st.nextElement();
               message = message.replaceAll(strReplace, strReplaceWith);
               replaceValue++;
              }
            count++;
         }
      }
   } catch (Exception e) {
      e.printStackTrace();
   }
   return message;
}

   public String getEmailTemplate(String filePath){

      String message = "";
      FileReader fr = null;
      BufferedReader br = null;
      try
      {
         fr=new FileReader(filePath);
         br= new BufferedReader(fr);
         StringBuilder content=new StringBuilder(1024);
         String line = "";
         while((line=br.readLine())!=null)
         {
            content.append(line);
         }
         message = content.toString();

      } catch (IOException e) {
         e.printStackTrace();
      } finally {
         try {
            if (br != null)br.close();
         } catch (IOException ex) {
            ex.printStackTrace();
         }
      }
      return message;
   }
}
