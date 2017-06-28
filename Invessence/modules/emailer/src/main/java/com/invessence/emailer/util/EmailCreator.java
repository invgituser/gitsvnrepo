package com.invessence.emailer.util;

import java.io.*;
import java.util.StringTokenizer;

import com.invessence.emailer.dao.MsgDAO;
import com.invessence.emailer.data.MsgData;
import com.invessence.service.util.*;
import org.springframework.context.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/20/16
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class EmailCreator implements MessageSourceAware, Serializable
{

   private MessageSource messageSource;
   private MsgDAO msgDAO;

   public MsgDAO getMsgDAO()
   {
      return msgDAO;
   }

   public void setMsgDAO(MsgDAO msgDAO)
   {
      this.msgDAO = msgDAO;
   }

   public MessageSource getMessageSource()
   {
      return messageSource;
   }

   public void setMessageSource(MessageSource messageSource)
{
   this.messageSource = messageSource;
}

   public String buildMessage(String msgType, String htmlSrc, String txtSrc, Object [] obj) {

      if (msgType == null || msgType.equalsIgnoreCase("HTML"))
         return getHTMLMessagetext(htmlSrc, obj);
      else
         return getMessagetext(txtSrc, obj);
   }

   public String lookupMessage(String text, Object [] obj) {
      String msgText = null;
      try {
         if (obj == null) {
            obj = new Object[]{};
         }
         if (text != null) {
            if (messageSource != null)
               msgText =  messageSource.getMessage(text, obj, null);
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return msgText;
   }

   public String buildInternalMessage(String text_version, Object [] obj) {
      return getMessagetext(text_version, obj);
   }

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
         System.out.println("Text: " + inputText + " not found in the message processing file.");
      }
      return inputText;
   }

   private String getHTMLMessagetext(String inputText, Object [] obj) {
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

   public void sendToSupport(String typeofalert, String subject, String message) {
      String receiver = null;
      try {

         // In it is warning or error, then send to support desk as well as write to email_alert table.

         if (typeofalert != null) {
            if (typeofalert.toUpperCase().contains("WARN")) {
                  receiver =  messageSource.getMessage("supportdesk.L1", null, null);
               }
               else if (typeofalert.toUpperCase().contains("ERR")) {
                  receiver =  messageSource.getMessage("supportdesk.L2", null, null);
               }
               else receiver = messageSource.getMessage("supportdesk.L1", null, null);
            }

               MsgData msgData = new MsgData();
               msgData.setMimeType("TEXT");
               msgData.setSource("Internal");
               msgData.setSubject(subject);
               msgData.setReceiver(receiver);
               // msgData.setCc();
               msgData.setSender("noreply@invessence.com");
               msgData.setMsg(message);
               writeMessage("Internal", msgData);
               // msgDAO.saveMsg(msgData);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void sendToSupport(String typeofalert, String subject, String message, String receiver) {
//      String receiver = null;
      try {

         // In it is warning or error, then send to support desk as well as write to email_alert table.

//         if (typeofalert != null) {
//            if (typeofalert.toUpperCase().contains("WARN")) {
//               receiver =  messageSource.getMessage("supportdesk.L1", null, null);
//            }
//            else if (typeofalert.toUpperCase().contains("ERR")) {
//               receiver =  messageSource.getMessage("supportdesk.L2", null, null);
//            }
//            else receiver = messageSource.getMessage("supportdesk.L1", null, null);
//         }

         MsgData msgData = new MsgData();
         msgData.setMimeType("TEXT");
         msgData.setSource("Internal");
         msgData.setSubject(subject);
         msgData.setReceiver(receiver);
         // msgData.setCc();
         msgData.setSender("noreply@invessence.com");
         msgData.setMsg(message);
         writeMessage("Internal", msgData);
         // msgDAO.saveMsg(msgData);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }



   public void writeMessage(String typeofalert, MsgData data) {
      String receiver = null;
      try {
         if (data == null) {
            return;
         }

         // In it is warning or error, then send to support desk as well as write to email_alert table.

         if (typeofalert != null) {
            if (typeofalert.toUpperCase().contains("WARN") ||
               typeofalert.toUpperCase().contains("ERR")) {
               data.setSource ("Internal");
               Object[] obj = new Object[]{};
               if (data.getReceiver() == null) {
                  if (typeofalert.toUpperCase().contains("WARN")) {
                     receiver =  messageSource.getMessage("supportdesk.L1", obj, null);
                  }
                  else if (typeofalert.toUpperCase().contains("ERR")) {
                     receiver =  messageSource.getMessage("supportdesk.L2", obj, null);
                  }
                  data.setReceiver(receiver);
               }
            }
         }
         String mimetype = data.getMimeType();
         if (mimetype == null || mimetype.isEmpty()) {
            data.setMimeType("HTML");
         }
         msgDAO.saveMsg(data);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   // Added by Abhang

   public void createEmail(String typeofalert, MsgData data, Object dataObject){

      data.getMsg();
      data.getAttachmentFile();
      // Map Object
   }
   public void createEmail(String typeOfAlert, String subject, String message){

      String receiver = null;
      try {

         // In it is warning, error and notification, then send to support desk as well as write to email_alert table.

         if (typeOfAlert != null) {
            if (typeOfAlert.toUpperCase().contains("WARN")) {
               receiver =  ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "L1_SUPPORT_EMAIL");//messageSource.getMessage("supportdesk.L1", null, null);
            }
            else if (typeOfAlert.toUpperCase().contains("ERR")) {
               receiver =  ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "L2_SUPPORT_EMAIL");//messageSource.getMessage("supportdesk.L2", null, null);
            }
            else receiver = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "L1_SUPPORT_EMAIL");//messageSource.getMessage("supportdesk.L1", null, null);
         }
         MsgData msgData = new MsgData();
         msgData.setMimeType("TEXT");
         msgData.setSource("Internal");
         msgData.setSubject(subject);
         msgData.setReceiver(receiver);
         // msgData.setCc();
         msgData.setSender(ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "SENDER_EMAIL"));
         msgData.setMsg(message);
         msgDAO.saveMsg(msgData);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }
}
