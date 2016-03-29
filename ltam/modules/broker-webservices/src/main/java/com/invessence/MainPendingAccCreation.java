package com.invessence;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 3/11/2016.
 */
public class MainPendingAccCreation
{
   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         CallingProgramme callingProgramme = (CallingProgramme) context.getBean("callingProgramme");
         callingProgramme.createPendingWebUser();
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
