package com.invessence.price.processor.Service;

import java.io.IOException;
import java.util.*;

import com.invessence.price.FIS.CallingServiceFISData;
import com.invessence.price.csidata.CallingServiceCSIData;
import com.invessence.price.processor.bean.*;
import com.invessence.price.util.PriceProcessConst;
import com.invessence.price.xignite.CallingServiceXignite;
import com.invessence.price.yahoo.CallingServiceYahoo;
import com.invessence.service.bean.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

/**
 * Created by bhaveshy on 3/16/2016.
 */

@Service
//@ComponentScan("package com.invessence.price.xignite.util")
public class PriceServiceImpl implements PriceService
{
   @Autowired
   CallingServiceFISData callingServiceFISData;

   /*@Autowired
   XigniteUtil xigniteUtil;*/
   CallingService callingService;


   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PriceServiceImpl.class);


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


   //In this  code we are selecting api based on priority to run monthly process
   @Override
   public HashMap<String, Object> getPrice(List<APIDetails> apidetails, String operation, String priceDate, String ticker, String tickerSource,ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> priceData = null;
      Iterator itr = apidetails.iterator();
      String srcArr[] = tickerSource.split(",");

     logger.info("getPrice priceDate "+priceDate+"ticker "+ticker+"tickerSource "+ tickerSource);
      apiloop:
      while (itr.hasNext())
      {

         for (int j = 0; j < srcArr.length; j++)
         {
            APIDetails apiDetails = (APIDetails) itr.next();
            System.out.println(apiDetails.getServiceProvider());
            if (apiDetails.getServiceProvider().equalsIgnoreCase("XIGNITE") && srcArr[j].equalsIgnoreCase("XIGNITE"))
            {
               //Calling Xignite Layer
               callingService = new CallingServiceXignite();
            }
            else if (apiDetails.getServiceProvider().equalsIgnoreCase("YAHOO") && srcArr[j].equalsIgnoreCase("YAHOO"))
            {
               //Calling Yahoo Layer
               callingService = new CallingServiceYahoo();
            }
            else if (apiDetails.getServiceProvider().equalsIgnoreCase("CSIDATA") && srcArr[j].equalsIgnoreCase("CSIDATA"))
            {
               //Calling CSIDATA Layer Pending for CSI Data Definition
               callingService = new CallingServiceCSIData();
            }
            else if (apiDetails.getServiceProvider().equalsIgnoreCase("FIS") && srcArr[j].equalsIgnoreCase("FIS"))
            {
               //Calling CSIDATA Layer Pending for CSI Data Definition
               callingService = callingServiceFISData;
            }

            try
            {
               if (callingService!=null && (operation.equalsIgnoreCase(PriceProcessConst.MONTHLY) ||operation.equalsIgnoreCase(PriceProcessConst.ONDEMAND) ))
               {
                  logger.info("getPrice monthly opertaion price source  "+srcArr[j]);
                  // priceData = callingService.getHistoricalPriceData(priceDate, ticker);
                  objPriceData = callingService.getHistoryPriceData(priceDate, ticker, serviceRequest);
               }
               else if (callingService!=null &&  (operation.equalsIgnoreCase(PriceProcessConst.DAILY)))
               {
                  logger.info("getPrice daily opertaion price source  "+srcArr[j]);
//               priceData = callingService.getDailyPriceData(priceDate, tickerList);
                  objPriceData = callingService.getDailyPriceData(priceDate, ticker, serviceRequest);
               }
               callingService=null;
               if (objPriceData!=null &&  objPriceData.get("status").toString().equalsIgnoreCase("success"))
               {
                  logger.info("getPrice data found using source "+srcArr[j]);
                  break apiloop;
               }
            }
            catch (Exception e)
            {
               logger.error("getPrice Exception "+e);
               e.printStackTrace();
               // mailAlertMsg.append(e.getMessage());
            }

         }
      }

      return objPriceData;
   }
}
