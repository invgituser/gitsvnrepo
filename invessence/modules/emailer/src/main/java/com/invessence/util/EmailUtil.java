package com.invessence.util;

import javax.mail.internet.MimeMessage;

import com.invessence.constant.Const;
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
      helper.setFrom(msgData.getSender(), Const.COMPANY_NAME);
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
         emailsCc = cc.split(" ");
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
      message.setContent(msgData.getMsg(), "text/html");
            

           /*
            if (msgData.getAttachmentFile() != null) {
                helper.addAttachment(msgData.getAttachmentFile(),
                        new File(msgData.getAttachmentFile()));
            }
            mailSender.send(message);
            */
      try
      {
         int msgID = msgData.getMsgID();
         String src = msgData.getSource();
         String groupMail = "";
         for (int i=0;i<emails.length;i++) {
            if (i == 0)
               groupMail = emails[i];
            else
               groupMail = groupMail + ", " + emails[i];
         }

         System.out.println(src + ": Sending email " + msgID + " to:" + groupMail);
         mailSender.send(message);

      }
      catch (Exception e)
      {
         e.printStackTrace();
         LOG.error("Error sending email: " + msgData.getMsgID() + ":" + e);
      }
   }


   public static void main(String[] args)
   {
      try
      {
         if (args.length == 2)
         {
            if (args[0].equals("-test"))
            {
               String[] emailAddresses = args[1].split(",");
               EmailJob.setTestMode(true, emailAddresses);
            }
         }
         ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/email.util.xml");
         Scheduler schedulerFactory = (Scheduler) applicationContext.getBean("schedulerFactory");
         schedulerFactory.start();
      }
      catch (Error e)
      {
         e.printStackTrace();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}


