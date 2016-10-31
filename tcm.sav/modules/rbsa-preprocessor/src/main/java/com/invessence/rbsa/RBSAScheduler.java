package com.invessence.rbsa;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RBSAScheduler
{
   public static void main(String[] args)
   {
      Logger logger = Logger.getLogger(RBSAScheduler.class.getName());
      logger.info("Starting Data files processor");
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
      try
      {

         Thread.sleep(50000);
         applicationContext.close();
      }
      catch (Exception e)
      {
         logger.error("Error running rbsa files processor", e);
      }
      logger.info("RBSA files processor ... done");
   }
}
