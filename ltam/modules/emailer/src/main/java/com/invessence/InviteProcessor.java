package com.invessence;

import java.util.List;

import com.invessence.dao.*;
import com.invessence.data.*;
import com.invessence.util.*;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/5/14
 * Time: 12:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class InviteProcessor
{
   public static void main(String[] args)
   {
      try
      {
         ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/invitation.util.xml");
         InviteDAO inviteDAO = (InviteDAO) applicationContext.getBean("inviteDAO");
         EmailCreator emailCreator = (EmailCreator) applicationContext.getBean("emailCreator");
         List<InvitedGuestData> list = inviteDAO.getEmailList();
         for (InvitedGuestData data : list)
         {
            System.out.println("Send Invitation to " + data.getName() + ": " + data.getEmail());
            String emailTxt;
            if (data.getGuesttype() == null || data.getGuesttype().equalsIgnoreCase("I")) {
               emailTxt = emailCreator.buildMessage("HTML", "html.guest.welcome", "txt.guest.welcome", new Object[]{data.getName(), data.getWeburl(), data.getEmail()});
            }
            else {
               emailTxt = emailCreator.buildMessage("HTML", "html.advisor.welcome", "txt.advisor.welcome", new Object[]{data.getName(), data.getWeburl(), data.getEmail()});
            }

            //System.out.println("Output Text: \n" + emailTxt);

            MsgData msgData = new MsgData();
            msgData.setSource("User");
            msgData.setMimeType("HTML");
            msgData.setSubject("Welcome - Symbil.com");
            msgData.setReceiver(data.getEmail());
            msgData.setSender("noreply@symbil.com");
            msgData.setMsg(emailTxt);
            emailCreator.writeMessage("User", msgData);
            inviteDAO.updMsgStatus("R",data.getEmail());

         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
