package com.invessence.price.processor;

import com.invessence.emailer.util.EmailCreator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by sagar on 4/20/2017.
 */

@Component
public class Processing
{

   private static final Logger logger = Logger.getLogger(Main.class);
   @Autowired
   EmailCreator emailCreator;
   @Autowired
   ExchangeRateProcessing exchngProcess;
   @Autowired
   PriceProcessing priceProcess;


   public void startProcessing(String[] args)
   {
      StringBuilder mailAlertMsg = null;
      try
      {
         mailAlertMsg = new StringBuilder();
         String company = null, mode = null, pricingrqd = null;
         logger.info(" Processing.startProcessing() passing argument length " + args.length);
         for (int i = 0; i < args.length; i++)
         {
            if (i == 0)
            {
               company = "" + args[i].trim();
            }
            if (i == 1)
            {
               mode = "" + args[i].trim();
            }
            if (i == 2)
            {
               pricingrqd = "" + args[i].trim();
            }
         }

         logger.info(" Processing.startProcessing() passing argument company " + company + " mode " + mode + " pricingrqd " + pricingrqd);
         if (company != null && mode != null)
         {

            if (pricingrqd.equalsIgnoreCase("Y") || pricingrqd.equalsIgnoreCase("EXCHANGE_YES"))
            {
               //ExchangeRateProcessing exchngProcess = context.getBean(ExchangeRateProcessing.class);
               boolean bFlag = exchngProcess.process(company, mode, mailAlertMsg);
               logger.info(" Processing.startProcessing() Exchange rate processing bFlag " + bFlag);
               if (bFlag)
               {
//                  PriceProcessing priceProcess = context.getBean(PriceProcessing.class);
                  priceProcess.process(company, mode, mailAlertMsg);
               }
               else
               {
                  logger.info(" Processing.startProcessing() No Attempt for Pricing as exchange rates are not collected ");
                  mailAlertMsg.append(" Processing.startProcessing() No Attempt for Pricing as exchange rates are not collected \n");
               }
//               context.close();
            }
            else
            {
//               PriceProcessing priceProcess = context.getBean(PriceProcessing.class);
               priceProcess.process(company, mode, mailAlertMsg);
//               context.close();
            }
         }
         else
         {
            logger.info(" Processing.startProcessing() Company & mode are not specified, since process not started for Exhange Rate &/ Price collection processing");
            mailAlertMsg.append("Processing.startProcessing() Company & mode are not specified, since process not started for Exhange Rate &/ Price collection processing \n");
         }
      }
      catch (Exception e)
      {
         logger.info(" Processing.startProcessing() Error " + e);
         e.printStackTrace();
      }
      finally
      {
         if (mailAlertMsg.length() > 0)
         {
            logger.info("MailAlertMsg IS :" + mailAlertMsg);
            emailCreator.sendToSupport("ERR", "EXCEPTION:PRICING MODULE", mailAlertMsg.toString());
         }
         else
         {
            logger.info("MailAlertMsg is empty");
         }
      }
   }
}
