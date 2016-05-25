package com.invessence.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 5/17/2016.
 */
public class Test
{
   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("serviceConfig.xml");
         TestImpl serviceLayer = (TestImpl) context.getBean("testImpl");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
