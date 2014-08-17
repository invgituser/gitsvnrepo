package com.invessence.broker;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BrokerFilesProcessor
{
   public static void main(String[] args)
   {
      Logger logger = Logger.getLogger(BrokerFilesProcessor.class.getName());
      logger.info("Starting Broker files processor");
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
      try
      {

         Thread.sleep(50000);
         applicationContext.close();
      }
      catch (Exception e)
      {
         logger.error("Error running broker files processor", e);
      }
      logger.info("Broker files processor ... done");
   }
}
