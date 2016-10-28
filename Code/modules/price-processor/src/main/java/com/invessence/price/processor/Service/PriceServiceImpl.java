package com.invessence.price.processor.Service;

import java.io.IOException;
import java.util.*;

import com.invessence.price.processor.bean.*;
import com.invessence.price.xignite.CallingServiceXignite;
import com.invessence.price.yahoo.CallingServiceYahoo;
import org.springframework.stereotype.*;

/**
 * Created by bhaveshy on 3/16/2016.
 */

@Service
//@ComponentScan("package com.invessence.price.xignite.util")
public class PriceServiceImpl implements PriceService
{
   /*@Autowired
   XigniteUtil xigniteUtil;*/
   CallingService callingService;


   @Override
   //In this  code we are selecting api based on priority to run daily process
   public List<PriceData> getPrice(List<APIDetails> apidetails, String operation, String priceDate, List<SecMaster> tickerList) throws IOException
   {

      List<PriceData> priceData = null;
      Iterator itr = apidetails.iterator();
      // here we are iterating list of api
      while (itr.hasNext())
      {

         APIDetails apiDetails = (APIDetails) itr.next();
         System.out.println(apiDetails.getServiceProvider());
         if (apiDetails.getServiceProvider().equalsIgnoreCase("XIGNITE"))
         {
            //Calling Xignite Layer
            callingService = new CallingServiceXignite();
         }
         else if (apiDetails.getServiceProvider().equalsIgnoreCase("YAHOO"))
         {
            //Calling Yahoo Layer
            callingService = new CallingServiceYahoo();
         }
         try
         {
            //if api does not throw any exception we end the loop
            priceData = callingService.getDailyPriceData(priceDate, tickerList);
            break;
         }
         catch (Exception e)
         {

            e.printStackTrace();
            //mailAlertMsg.append(e.getMessage());
         }
      }
      return priceData;

   }

   //In this  code we are selecting api based on priority to run monthly process
   @Override
   public List<PriceData> getPrice(List<APIDetails> apidetails, String operation, String priceDate, String ticker) throws Exception
   {
      List<PriceData> priceData = null;
      Iterator itr = apidetails.iterator();
      while (itr.hasNext())
      {

         APIDetails apiDetails = (APIDetails) itr.next();
         System.out.println(apiDetails.getServiceProvider());
         if (apiDetails.getServiceProvider().equalsIgnoreCase("XIGNITE"))
         {
            //Calling Xignite Layer
            callingService = new CallingServiceXignite();
         }
         else if (apiDetails.getServiceProvider().equalsIgnoreCase("YAHOO"))
         {
            //Calling Yahoo Layer
            callingService = new CallingServiceYahoo();
         }
         try
         {
            //if api does not throw any exception we end the loop
            priceData = callingService.getHistoricalPriceData(priceDate, ticker);
            break;
         }
         catch (Exception e)
         {
            e.printStackTrace();
            // mailAlertMsg.append(e.getMessage());
         }
      }

      return priceData;
   }
}
