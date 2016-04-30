package com.invessence.util;

import java.io.*;
import java.util.*;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.constant.Const;
import com.invessence.dao.MsgDAO;
import com.invessence.data.MsgData;
import org.springframework.context.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/23/14
 * Time: 12:13 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "emailMessage")
@SessionScoped
public class EmailMessage implements MessageSourceAware, Serializable
{
   private static final long serialVersionUID = -1001L;
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

   public String buildMessage(String msgType, String html_version, String text_version, Object [] obj) {

     if (msgType == null || msgType.equalsIgnoreCase("HTML"))
        return getHTMLMessagetext(html_version, obj);
     else
        return getMessagetext(text_version, obj);
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
         msgText = text;
         System.out.println("Error: could not parse > " + text);
         // ex.printStackTrace();
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
         // System.out.println("Text: " + inputText + " not found in the message processing file.");
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

   public void alertSupport(String module, String subject,
                            String message_line, String stacktrace, String userid)
   {
      MsgData data = new MsgData();
      String msg;
      try
      {
         if (messageSource == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (addGoals failure)");
         }
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + "[ " + subject + " ]");

         String error_text = buildInternalMessage(message_line, null);

         if (stacktrace != null)
            msg = "Module:" + module + "\nUser: " + userid + "\n\n" + error_text + "\n\n\n" + stacktrace;
         else
            msg = "Module:" + module + "\nUser: " + userid + "\n\n" + error_text + "\n\n\n";
         data.setMsg(msg);
         writeMessage("Error", data);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

}
