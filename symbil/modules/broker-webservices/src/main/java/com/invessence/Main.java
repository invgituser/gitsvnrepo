package com.invessence;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 3/11/2016.
 */
public class Main
{
   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
         CallingProgramme callingProgramme = (CallingProgramme) context.getBean("callingProgramme");
         callingProgramme.call();
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
