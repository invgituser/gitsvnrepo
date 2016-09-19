package com.invessence.web.bean.common;

import java.io.Serializable;
import javax.faces.bean.*;

import com.invessence.web.util.*;


@ManagedBean(name = "messageBean")
@SessionScoped
public class MessageBean implements Serializable {
   private static final long serialVersionUID = -999L;
   private String type = null;
   private String bodyText = null;
   private String supportInfo = null;
   private String title = "";


   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;
   private String message = null;

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = null;
      if (type != null)
         this.type = type.substring(0,1).toUpperCase();
    }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      String msgText = null;
      try {
         msgText = messageText.buildInternalMessage(message, new Object[]{});
         if (msgText == null || msgText.length() == 0)
            msgText = getMessage();
         this.message =  msgText;
      }
      catch (Exception ex) {
         ex.printStackTrace();
         this.message = message;
      }
   }

   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   public String getSupportInfo()
   {
      if (supportInfo != null )
         return supportInfo;
      else
         return messageText.buildInternalMessage("support.info", new Object[]{webutil.getUiprofile().getSupportphone(), webutil.getUiprofile().getSupportemail()});
   }

   public void setSupportInfo(String supportInfo)
   {
      this.supportInfo = supportInfo;
   }


   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      String msgText = null;
      try {
         msgText = messageText.buildInternalMessage(title, new Object[]{});
         if (msgText == null || msgText.length() == 0)
            this.title = title;
         this.title =  msgText;
      }
      catch (Exception ex) {
         ex.printStackTrace();
         this.title = title;
      }
   }

   public String getBodyText()
   {
      if (bodyText == null)
         return getMessage();
      else
         return bodyText;
   }

   public void setBodyText(String bodyText)
   {
      this.bodyText = bodyText;
   }

   public String getTitleColor() {
      String style="font-size: 30px; font-family: Helvetica; font-weight: bold;  padding-bottom: 0px; font-style: normal; text-align: left; padding-top: 0px; padding-left: 0px; margin: 0px; display: block; letter-spacing: normal; line-height: 125%; padding-right: 0px;";
      String color="color: #009ABB !important;";
      if (getType() != null) {
         if (getType().startsWith("E"))
            color= "color: #CC0000 !important;";
         else
         if (getType().startsWith("W"))
            color= "color: #FF9900; !important;";
         else
            color= "color: #009ABB !important;";
      }
      return style + color;
   }

}