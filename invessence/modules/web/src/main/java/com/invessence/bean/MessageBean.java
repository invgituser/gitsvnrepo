package com.invessence.bean;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.util.*;


@SessionScoped
public class MessageBean implements Serializable {
   private static final long serialVersionUID = -999L;
   private String type = null;
   private String reason = null;
   private String action = null;
   private String headerText = null;
   private String footerText = null;
   private String supportInfo = null;

   private EmailMessage messageText;
   private String message = null;
   private String act = null;

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      String msgText = null;
      try {
         if (message != null) {
            if (! message.contains(" "))
               setReason(messageText.getMessagetext(message, new Object[]{}));
            else
               setReason(message);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
         setReason(message);
      }
   }

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public String getAct()
   {
      return act;
   }

   public void setAct(String act)
   {
      String msgText = null;
      try {
         if (act != null) {
            if (! act.contains(" "))
               setAction(messageText.getMessagetext(message, new Object[]{}));
            else
               setAct(act);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
         setAction(act);
      }
   }

   public String getReason()
   {
      return reason;
   }

   public void setReason(String reason)
   {
      this.reason = reason;
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
   }

   public String getSupportInfo()
   {
      if (supportInfo != null )
         return supportInfo;
      else
         return messageText.getMessagetext("support.info", new Object[]{});
   }

   public void setSupportInfo(String supportInfo)
   {
      this.supportInfo = supportInfo;
   }

   public String getHeaderText()
   {
      String text = "";
      // If the message is general, then just print the message (No header or footer).
      if (getType() == null) {
         return getReason();
      }
      // If the message is type of Error, then do this.
      if (getType().startsWith("E")) {
         text = messageText.getMessagetext("error.header", new Object[]{getReason(), getAction()});
      }
      else // If the message is type of Warning, then do this.
      if (getType().startsWith("W")) {
         text = messageText.getMessagetext("warning.header", new Object[]{getReason(), getAction()});
      }
      else
         text = getReason();

      return text;
   }

   public void setHeaderText(String headerText)
   {
      this.headerText = headerText;
   }

   public String getFooterText()
   {
      return footerText;
   }

   public void setFooterText(String footerText)
   {
      this.footerText = footerText;
   }
}