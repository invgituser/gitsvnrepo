package com.invessence.virtualtrade;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VirtualTradesProcessor
{
   public static void main(String[] args)
   {
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
      try
      {

         Thread.sleep(50000);
         applicationContext.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
   }
}
