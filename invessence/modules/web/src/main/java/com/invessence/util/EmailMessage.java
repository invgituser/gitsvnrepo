package com.invessence.util;

import java.util.Map;

import javax.faces.bean.*;

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
@ApplicationScoped
public class EmailMessage implements MessageSourceAware
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
      return inputText;
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

         msgDAO.saveMsg(data);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }


}
