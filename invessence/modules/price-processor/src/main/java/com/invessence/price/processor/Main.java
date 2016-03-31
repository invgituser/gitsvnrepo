package com.invessence.price.processor;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
   This class is use to call method process() of PriceProcessor.class
 */
public class Main
{

   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
         PriceProcessor pp = context.getBean(PriceProcessor.class);
         pp.process();

         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }


}
