package com.invessence.util;

import java.io.*;
import java.util.StringTokenizer;
import javax.mail.Multipart;
import javax.mail.internet.*;
import javax.activation.*;

import com.invessence.data.MsgData;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.*;

public class EmailUtil
{

   private static final Logger LOG = Logger.getLogger(EmailUtil.class);
   private JavaMailSenderImpl mailSender = null;

   public void setMailSender(JavaMailSenderImpl mailSender)
   {
      this.mailSender = mailSender;
   }


   public void sendEmail(MsgData msgData) throws Exception
   {

      //try {

      MimeMessage message = mailSender.createMimeMessage();

      MimeMessageHelper helper = new MimeMessageHelper(message, true);
      helper.setFrom(msgData.getSender(), "Symbil");
      helper.setSubject(msgData.getSubject());


      String[] emails = msgData.getReceiver().split(" ");
      if ((emails != null) && (emails.length > 0))
      {
         helper.setTo(emails);
      }

      String cc = msgData.getCc();
      String[] emailsCc = null;

      if (cc != null)
      {
         if (! cc.isEmpty() && ! cc.toUpperCase().contains("NULL")) {
            emailsCc = cc.split(" ");
         }

      }

      if ((emailsCc != null) && (emailsCc.length > 0))
      {
         helper.setCc(emailsCc);
      }

      //String[] emailsBcc = msgData.getBcc().split(" ");
      //if ( (emailsBcc != null) && (emailsBcc.length > 0))
      //helper.setBcc(emailsBcc);

      //helper.addBcc(Const.MAIL_SUPPORT);


      // helper.setText(msgData.getMsg());
      //message.setContent(msgData.getMsg(), "text/html");
            

           /*
            if (msgData.getAttachmentFile() != null) {
                helper.addAttachment(msgData.getAttachmentFile(),
                        new File(msgData.getAttachmentFile()));
            }
            mailSender.send(message);
            */
      // COVER WRAP
      MimeBodyPart wrap = new MimeBodyPart();

      // ALTERNATIVE TEXT/HTML CONTENT
      MimeMultipart cover = new MimeMultipart("alternative");
      MimeBodyPart html = new MimeBodyPart();
      MimeBodyPart text = new MimeBodyPart();

      if(msgData.getMimeType() == null || msgData.getMimeType().trim().equalsIgnoreCase("html"))
      {
         cover.addBodyPart(html);
      }else
      {
         cover.addBodyPart(text);
      }

      wrap.setContent(cover);

      MimeMultipart content = new MimeMultipart("related");
      message.setContent(content);
      content.addBodyPart(wrap);


      if(msgData.getAttachmentFile() != null && msgData.getAttachmentFile() != "")
      {
         int count = 0;
         StringTokenizer st = new StringTokenizer(msgData.getAttachmentFile(), ",");
         String[] attachmentsFiles = new String[st.countTokens()];
         while (st.hasMoreElements())
         {
            attachmentsFiles[count] = st.nextToken();
            count++;
         }

         StringBuilder sb = new StringBuilder();

         for (String attachmentFileName : attachmentsFiles)
         {

            MimeBodyPart attachment = new MimeBodyPart();

            DataSource fds = new FileDataSource(attachmentFileName);
            attachment.setDataHandler(new DataHandler(fds));
            attachment.setFileName(fds.getName());

            content.addBodyPart(attachment);
         }
      }
      if(msgData.getMimeType() == null || msgData.getMimeType().equalsIgnoreCase("html"))
      {
         html.setContent(msgData.getMsg(), "text/html");
      }else
      {
         text.setText(msgData.getMsg());
      }


      try
      {
         int msgID = msgData.getMsgID();
         String src = msgData.getSource();
         String groupMail = "";
         String fromMail = msgData.getSender();
         for (int i=0;i<emails.length;i++) {
            if (i == 0)
               groupMail = emails[i];
            else
               groupMail = groupMail + ", " + emails[i];
         }

         System.out.println(src + ": Sending email ID(" + msgID + ") to: " + groupMail + ", from: " + fromMail);
         mailSender.send(message);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         System.out.println("MsgID: (" + msgData.getMsgID() +"), Err Msg: " + e.getMessage());
         LOG.error("Error sending email: " + msgData.getMsgID() + ":" + e);
      }
   }
}


