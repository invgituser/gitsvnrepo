package com.invessence.util;

import java.util.List;

import com.invessence.dao.*;
import com.invessence.data.MsgData;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;


public class EmailJob
{
   private static boolean testMode = false;
   private static final Logger LOG = Logger.getLogger(EmailJob.class);
   private static String[] recipients;

   private MsgDAO msgDAO = null;
   private EmailUtil emailUtil = null;

   public static void setTestMode(boolean testMode, String[] recipients)
   {
      EmailJob.testMode = testMode;
      EmailJob.recipients = recipients;
   }

   public void setMsgDAO(MsgDAO msgDAO)
   {
      this.msgDAO = msgDAO;
   }

   public void setEmailUtil(EmailUtil emailUtil)
   {
      this.emailUtil = emailUtil;
   }

   public void run()
   {
      LOG.info("Entered EmailJob:run()...");

      try
      {

         if (testMode)
         {
            sendTestEmail();
         }
         else
         {
            sendRegistrationEmail();
         }

      }
      catch (Exception e)
      {
         LOG.error("EmailJob:run():" + e);
      }

      LOG.info("Ended EmailJob:run()...");
   }

   private void sendTestEmail()
   {
      for (String recipient : recipients)
      {
         try
         {
            MsgData data = new MsgData();
            data.setMsgID(1);
            data.setSender("no-reply@invessence.com");
            data.setReceiver(recipient);
            data.setCc(null);
            data.setBcc(null);
            data.setSubject("Symbil Emailer Test Email");
            data.setMsg("Test Email Body");
            data.setStatus(0);
            data.setCategory(0);
            data.setPriority(0);
            data.setEnteredDate("");
            data.setUpdatedDate("");
            data.setSentDate("");
            data.setMimeType("Text");
            data.setAttachmentFile("");
            sendEmail(data);
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }

      }
   }

   private void sendRegistrationEmail()
   {
      List<MsgData> msgDataList = msgDAO.getMsgList(0);

      // System.out.println("size = " + msgDataList.size());

      for (MsgData msgData : msgDataList)
      {
         msgDAO.updMsgStatus(msgData.getMsgID(), 2, null, msgData.getSource());
      }

      for (MsgData msgData : msgDataList)
      {
         try
         {
            //msgData.setReceiver("sshrest@optonline.net");
            sendEmail(msgData);
            msgDAO.updMsgStatusSuccess(msgData.getMsgID(), msgData.getSource());
         }
         catch (Exception e)
         {
            e.printStackTrace();
            System.out.println("Exception:" + e);
            msgDAO.updMsgStatus(msgData.getMsgID(), -1, e.getMessage(), msgData.getSource());

         }
      }
   }

   private void sendEmail(MsgData msgData) throws Exception
   {
      emailUtil.sendEmail(msgData);
   }
    

/*
    public MsgData getTestMsg() {

        MsgData data = new MsgData();
        data.setSender("Shashi");
        data.setReceiver("sshrest@optonline.net");
        data.setSubject("TEST");
        data.setMsg("Test only");
        
        return data;
    }
    
    */


   public void saveMsg(MsgData msgData)
   {

      LOG.info("Entered EmailJob:saveMsg()...");

      try
      {
         MsgSP msgSP = new MsgSP(msgDAO.getDataSource());
         msgSP.execute(msgData);

      }
      catch (Exception e)
      {
         LOG.error("EmailJob:run():" + e);
      }

      LOG.info("Ended saveMsg:run()...");
   }

/*
   public static void main(String[] args)
   {

      // By loading the bean factory, we will start the Quartz Scheduler
      // The Quartz Scheduler will continue to run until explicitly shutdown.
      //ApplicationContext ctx = new ClassPathXmlApplicationContext(args[0]);

      try
      {
         BeanFactory factory = new XmlBeanFactory(new FileSystemResource(args[0]));
         //SchedulerFactoryBean schedulerFactoryBean =
         //(SchedulerFactoryBean) factory.getBean("schedulerFactoryBean");
         Scheduler scheduler =
            (Scheduler) factory.getBean("schedulerFactoryBean");
         scheduler.start();

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }


   }
*/
}
