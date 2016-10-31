package com.invessence.web.bean;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.constant.Const;
import com.invessence.web.util.WebMessage;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/20/13
 * Time: 4:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBean {

   private WebMessage messageText;
   private static TestBean instance = null;

   private String message;

   /*
   public static synchronized TestBean TestBean() {
      if (instance == null) {
         instance = new TestBean("init");
      }
      return instance;
   }
   */

/*
   @PostConstruct
   public void init()  {
   }
*/

   public WebMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }

   public void sendEmail()
   {
      MsgData data = new MsgData();
      try
      {
         String htmlText="<html>\n" +
            "<head>\n" +
            "\t<title>Invessence</title>\n" +
            "\t<meta content=\"Invessence Welcome Message\" name=\"GENERATOR\" />\n" +
            "\t<meta content=\"text/html;charset=ISO-8859-1\" http-equiv=\"Content-Type\" />\n" +
            "</head>\n" +
            "<body bgcolor=\"#ffffff\">\n" +
            "<p>Dear Chris Test,</p>\n" +
            "\n" +
            "<p>Your userid (<span style=\"font-size: 22px\"><a href=\"mailto:christest@invessence.com\">christest@invessence.com</a></span>) is now registered with Invessence.com!</p>\n" +
            "\n" +
            "<p>Please click on the below link to set your password:</p>\n" +
            "\n" +
            "<p><span style=\"font-size: 22px\"><a href=\"http://uat.invessence.com:8080/setPassword.xhtml?u=christest@invessence.com&amp;r=316073\">http://uat.invessence.com:8080/setPassword.xhtml?u=christest@invessence.com&amp;r=316073</a></span></p>\n" +
            "\n" +
            "<p>If clicking on the link does not work, try copying and pasting it into your browser.</p>\n" +
            "\n" +
            "<p>If you did not register for this service, please email us directly at <a href=\"mailto:info@invessence.com\">info@invessence.com</a></p>\n" +
            "\n" +
            "<p>Thank you,</p>\n" +
            "\n" +
            "<p>Admin Team<br />\n" +
            "(201) 977-1954</p>\n" +
            "\n" +
            "<p><img border=\"0\" height=\"80\" src=\"invessence_logo.png\" style=\"height: 34px; width: 161px\" width=\"369\" /></p>\n" +
            "\n" +
            "<p>&nbsp;</p>\n" +
            "</body>\n" +
            "</html>\n";
         data.setSource("User");  // This is set to User to it insert into appropriate table.
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setReceiver("prashant@invessence.com");
         data.setSubject(Const.COMPANY_NAME + " - Successfully registered");
         if (messageText != null) {
            data.setMsg(htmlText);
            messageText.writeMessage("User", data);
         }
         else {
            System.out.println("Email failed Object (messageText) not defined" );
         }
      }
      catch (Exception ex)
      {
         System.out.println("Email failed:" + ex.getMessage());
      }
   }


}
