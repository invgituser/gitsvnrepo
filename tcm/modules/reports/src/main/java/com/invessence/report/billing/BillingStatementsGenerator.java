package com.invessence.report.billing;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BillingStatementsGenerator
{
   public static void main(String[] args)
   {
      Logger logger = Logger.getLogger(BillingStatementsGenerator.class.getName());
      logger.info("Starting Billing Statements generator");
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
      try
      {
         Thread.sleep(50000);
         applicationContext.close();
      }
      catch (Exception e)
      {
         logger.error("Error running Billing Statements generator", e);
      }
      logger.info("Billing Statements generator ... done");
   }
}
