package com.invessence;

import java.util.List;

import com.invessence.emailer.dao.InviteDAO;
import com.invessence.emailer.data.*;
import com.invessence.emailer.util.EmailCreator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 10/21/2016.
 */
public class EmailTester
{
   public static void main(String[] args)
   {
      try
      {
         ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/emailerConfig.xml");
//         InviteDAO inviteDAO = (InviteDAO) applicationContext.getBean("inviteDAO");
         EmailCreator emailCreator = (EmailCreator) applicationContext.getBean("emailCreator");
//         List<InvitedGuestData> list = inviteDAO.getEmailList();
//         for (InvitedGuestData data : list)
//         {
//            System.out.println("Send Invitation to " + data.getName() + ": " + data.getEmail());
//            String emailTxt;
//            if (data.getGuesttype() == null || data.getGuesttype().equalsIgnoreCase("I")) {
//               emailTxt = emailCreator.buildMessage("HTML", "html.guest.welcome", "txt.guest.welcome", new Object[]{data.getName(), data.getWeburl(), data.getEmail()});
//            }
//            else {
//               emailTxt = emailCreator.buildMessage("HTML", "html.advisor.welcome", "txt.advisor.welcome", new Object[]{data.getName(), data.getWeburl(), data.getEmail()});
//            }

            //System.out.println("Output Text: \n" + emailTxt);
//         String emailTxt=emailTxt = emailCreator.buildMessage("HTML", "html.advisor.welcome", "txt.advisor.welcome", new Object[]{"Abhang", "url", "abhang.patil@gmail.com"});
//
            MsgData msgData = new MsgData();
            msgData.setSource("User");
            msgData.setMimeType("HTML");
            msgData.setSubject("Test Mail");
            msgData.setReceiver("abhang.patil@gmail.com");
            msgData.setSender("no-reply@invessence.com");
            msgData.setMsg("Test Mail");

//            emailCreator.sendToExternal(msgData);
//
//         }

//         //no-reply@advisorgo.com
//         emailCreator.sendToSupport("ERR", "UAT~~File Upload Process", "Test Message for Support team");
//         emailCreator.sendToSupport("ERR", "PROD~~Broker File Upload Process", "Test Message for Support team");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
