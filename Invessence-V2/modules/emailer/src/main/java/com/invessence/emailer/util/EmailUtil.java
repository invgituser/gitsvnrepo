package com.invessence.emailer.util;


import java.util.StringTokenizer;
import javax.mail.internet.*;
import javax.activation.*;

import com.invessence.service.util.*;
import com.invessence.emailer.data.MsgData;
import org.apache.log4j.Logger;
import org.springframework.context.*;
import org.springframework.mail.javamail.*;
import java.util.*;
import java.util.regex.*;

public class EmailUtil
{

   private static final Logger LOG = Logger.getLogger(EmailUtil.class);
   private JavaMailSenderImpl mailSender = null;
   private MessageSource messageSource;

   String emailHost ="";
   String emailPort ="";
   String emailUsername ="";
   String emailPassword ="";
   String emailCCEXTERNALRECEIVER ="";
   String emailCCINVESSENCERECEIVER ="";
   String emailUserTitle = "";

   public EmailUtil()
   {
      // LOG.info("Contructor: EmailUtil.EmailUtil");
      //  Fetch parameters from ServiceParameters module (i.e. Database) Start & set to mailSender//

      mailSender = new JavaMailSenderImpl();
      emailHost = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "HOST");
      emailPort = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "PORT");
      emailUsername = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "USERNAME");
      emailUserTitle = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "USERTITLE");
      emailPassword = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "PASSWORD");
      emailCCEXTERNALRECEIVER = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "CC_EXTERNAL_RECEIVER");
      emailCCINVESSENCERECEIVER = ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(), ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()), "CC_INVESSENCE_RECEIVER");

      LOG.info("emailHost = "+emailHost+" || emailPort = "+emailPort+" || emailUsername = "+emailUsername+" || emailPassword = XXXX");

      mailSender.setHost(emailHost);
      mailSender.setPort(Integer.parseInt(emailPort));
      mailSender.setUsername(emailUsername);
      mailSender.setPassword(emailPassword);

      Properties mailProps = new Properties();
      mailProps.put("mail.smtp.starttls.enable", "true");
      mailProps.put("mail.smtp.auth", "true");

      mailSender.setJavaMailProperties(mailProps);
      //  Fetch parameters from ServiceParameters module (i.e. Database) End & set to mailSender//
   }

   public MessageSource getMessageSource()
   {
      return messageSource;
   }

   public void setMessageSource(MessageSource messageSource)
   {
      this.messageSource = messageSource;
   }

   public void setMailSender(JavaMailSenderImpl mailSender)
   {
      this.mailSender = mailSender;
   }

   public void sendEmail(MsgData msgData) throws Exception
   {
      try
      {
         MimeMessage message = mailSender.createMimeMessage();
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         helper.setFrom(msgData.getSender(), emailUserTitle);
         helper.setSubject(msgData.getSubject());

         String[] emails = msgData.getReceiver().split(" ");
         if ((emails != null) && (emails.length > 0))
         {
            helper.setTo(emails);
         }

         if(emailCCINVESSENCERECEIVER != null && ! emailCCINVESSENCERECEIVER.isEmpty())
         {
            //Adding emailCC_INVESSENCE_RECEIVER
            helper.setCc(emailCCINVESSENCERECEIVER);
         }else
         {
            LOG.info("CC policy not set for company.");
         }

         String cc = msgData.getCc();
         String[] emailsCc = null;

         if (cc != null)
         {
            if (!cc.isEmpty() && !cc.toUpperCase().contains("NULL"))
            {
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

         if (msgData.getMimeType() == null || msgData.getMimeType().trim().equalsIgnoreCase("html"))
         {
            cover.addBodyPart(html);
         }
         else
         {
            cover.addBodyPart(text);
         }

         wrap.setContent(cover);

         MimeMultipart content = new MimeMultipart("related");
         message.setContent(content);
         content.addBodyPart(wrap);


         if (msgData.getAttachmentFile() != null && msgData.getAttachmentFile() != "")
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
         if (msgData.getMimeType() == null || msgData.getMimeType().equalsIgnoreCase("html"))
         {
            html.setContent(msgData.getMsg(), "text/html");
         }
         else
         {
            text.setText(msgData.getMsg());
         }


         try
         {
            int msgID = msgData.getMsgID();
            String src = msgData.getSource();
            String groupMail = "";
            String fromMail = msgData.getSender();
            for (int i = 0; i < emails.length; i++)
            {
               if (i == 0)
                  groupMail = emails[i];
               else
                  groupMail = groupMail + ", " + emails[i];
            }

            LOG.info(src + ": Sending email ID(" + msgID + ") to: " + groupMail + ", from: " + fromMail);

            mailSender.send(message);

         }
         catch (Exception e)
         {
            e.printStackTrace();
            LOG.info("MsgID: (" + msgData.getMsgID() + "), Err Msg: " + e.getMessage());
            LOG.error("Error sending email: " + msgData.getMsgID() + ":" + e);
         }

      }
      catch(Exception e)
      {
         e.printStackTrace();

      }
   }

   public List<String> getEditParams(String msgBody, String delimiter){
      List<String> lstObj=null;
      try{
         lstObj=new ArrayList<String>();

         Matcher m = Pattern.compile(
            Pattern.quote(delimiter)+ "(.*?)"+ Pattern.quote(delimiter)
         ).matcher(msgBody);
         while(m.find()){
            String match = m.group(1);
            System.out.println(">"+match+"<");
            //here you insert 'match' into the list
            lstObj.add(match);
         }
      }catch (Exception e){
         e.printStackTrace();
      }
      return lstObj;
   }

   public void replaceEditParams(String msgBody, String delimiter, List<String> lstEditParams, Object dataObject){

      try{
         Iterator<String> itr=lstEditParams.iterator();
         while (itr.hasNext()) {
            String string = (String) itr.next();
            System.out.println("Param trim:"+string.trim());
            System.out.println("Param :"+delimiter+string+delimiter);
         }
      }catch (Exception e){
         e.printStackTrace();
      }
   }
}


