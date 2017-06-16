package com.invessence.price.processor;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by bhaveshy on 4/5/2016.
 */
public class InitialProcess
{
   public static void main(String[] args)
   {
      try
      {
          String tickerName= "SPY";
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
         PriceProcessor pp = context.getBean(PriceProcessor.class);
         pp.initialProcess(tickerName);

         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
