package com.invessence.price.processor;

import java.util.*;

import com.invessence.price.processor.dao.SecMasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 3/4/2016.
 */
@Component
public class MainOnDemand
{
   @Autowired
   SecMasterDao secMasterDao;

   public static void main(String[] args)
   {
      try
      {
         // create a scanner so we can read the command-line input
         Scanner scanner = new Scanner(System.in);

         //  prompt for the user's name
         System.out.print("Enter Ticker name to process onDemand: ");
         // get their input as a String
         String tickerName = scanner.next();
         if (tickerName == null || tickerName.equals(""))
         {
            System.out.print("Please Enter Ticker name for onDemand process");
         }
         else
         {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
            PriceProcessor pp = context.getBean(PriceProcessor.class);

            //TO call ondemand process

            pp.onDemandProcess(tickerName);
            context.close();
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }


   }
}
