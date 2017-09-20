package com.invessence.price.processor;


import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main
{
   public static void main(String[] args)
   {
      try
      {
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
         Processing processing = context.getBean(Processing.class);
         processing.startProcessing(args);
      }
      catch (Exception e)
      {
         System.out.print(" Main Error " + e);
         e.printStackTrace();
      }
   }
}
