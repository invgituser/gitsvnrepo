package com.invessence.price.FIS;

import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.price.FIS.bean.*;
import com.invessence.price.processor.Service.*;
import com.invessence.price.processor.bean.*;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sagar on 4/10/2017.
 */
@Service
public class CallingServiceFISData implements CallingService
{
   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallingServiceFISData.class);

   @Autowired
   CallingFISPriceService objCallingFISPriceService;
   private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

   @Override
   public List<PriceData> getDailyPriceData(String priceDate, List<SecMaster> tickerList) throws Exception
   {
      return null;
   }

   @Override
   public List<PriceData> getHistoricalPriceData(String priceDate, String ticker) throws Exception
   {
      return null;
   }

   @Override
   public HashMap<String, Object> getDailyPriceData(String priceDate, String ticker, ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> pdList = null;
      PriceData hpd = null;
      try
      {
         logger.info("CallingServiceFISData getDailyPriceData Start ");
         pdList = new ArrayList<PriceData>();
         objPriceData = new HashMap<String, Object>();
            /*
            * exchangeData.getDate()-Business Date
            * exchangeData.getOpen()-Open Price
            * exchangeData.getHigh()-High Price
            * exchangeData.getLow()-Low Price
            * exchangeData.getClose()-Close Price
            * exchangeData.getVolume()-Volume
            * */
         String strURL =ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.FIS.toString(), "DAILY.URL");
         HistoricalData objDailyData = objCallingFISPriceService.getDailyPrice(strURL, ticker,priceDate);
         HistoricalDataRates dallyRates = objDailyData.getHistoricalExchangeRates().getTimeSeriesPoint().get(0);

         if (priceDate.equalsIgnoreCase(dallyRates.getDate()))
         {
            hpd = new PriceData(ticker,
                                dallyRates.getDate(),
                                Double.valueOf(dallyRates.getOpen().trim()), Double.valueOf(dallyRates.getClose().trim()),
                                Double.valueOf(dallyRates.getHigh().trim()), Double.valueOf(dallyRates.getLow().trim()),
                                Long.valueOf(dallyRates.getVolume().trim()), null,
                                Double.valueOf(0), new Long(2), new Date());
            pdList.add(hpd);
            hpd = null;
         }
         if (pdList.size() > 0)
         {
            logger.info("CallingServiceFISData getDailyPriceData price data found datasize " + pdList.size());
            objPriceData.put("status", "success");
            objPriceData.put("priceData", pdList);
         }
         else
         {
            logger.info("CallingServiceFISData getDailyPriceData price data not found ");
            objPriceData.put("status", "failure");
         }
         logger.info("CallingServiceFISData getDailyPriceData End ");
      }
      catch (Exception e)
      {
         objPriceData.put("status", "failure");
         logger.error("CallingServiceFISData getDailyPriceData Exception " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            priceDate = null;
            ticker = null;
            pdList = null;
         }
         catch (Exception e)
         {
         }
      }
      return objPriceData;
   }

   @Override
   public HashMap<String, Object> getHistoryPriceData(String priceDate, String ticker, ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> pdList = null;
      String line = "";
      String prevBusinessdate = null, prevClosePrice = null;
      boolean bflag = false;
      PriceData hpd = null;
      int counter = 0;

      try
      {
         logger.info("CallingServiceFISData getHistoryPriceData Start ");
         String strURL = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.FIS.toString(), "HISTORY.URL");
         pdList = new ArrayList<PriceData>();
         objPriceData = new HashMap<String, Object>();
         List<HistoricalDataRates> objHistoricalData = objCallingFISPriceService.getHistoricalPrice(strURL, ticker,priceDate);

         for (int i = 0; i < objHistoricalData.size(); i++)
         {
            if (counter == 0)
            {
               hpd = new PriceData(ticker,
                                   objHistoricalData.get(i).getDate(),
                                   Double.valueOf(objHistoricalData.get(i).getOpen().trim()), Double.valueOf(objHistoricalData.get(i).getClose().trim()),
                                   Double.valueOf(objHistoricalData.get(i).getHigh().trim()), Double.valueOf(objHistoricalData.get(i).getLow().trim()),
                                   Long.valueOf(objHistoricalData.get(i).getVolume().trim()), null,
                                   Double.valueOf(objHistoricalData.get(i).getClose().trim()), new Long(2), new Date());
            }
            else
            {
               hpd = new PriceData(ticker,
                                   objHistoricalData.get(i).getDate(),
                                   Double.valueOf(objHistoricalData.get(i).getOpen().trim()), Double.valueOf(objHistoricalData.get(i).getClose().trim()),
                                   Double.valueOf(objHistoricalData.get(i).getHigh().trim()), Double.valueOf(objHistoricalData.get(i).getLow().trim()),
                                   Long.valueOf(objHistoricalData.get(i).getVolume().trim()), sdf.parse(prevBusinessdate),
                                   Double.valueOf(objHistoricalData.get(i).getClose().trim()), new Long(2), new Date());
            }
            pdList.add(hpd);
            hpd = null;
            prevBusinessdate = null;
            prevClosePrice = null;
            prevBusinessdate = objHistoricalData.get(i).getDate();
            prevClosePrice = objHistoricalData.get(i).getClose().trim();
            counter++;
         }
//         System.out.println("***************size of pdlist" + pdList.size());
         if (pdList.size() > 0)
         {
            objPriceData.put("status", "success");
            objPriceData.put("priceData", pdList);
            logger.info("CallingServiceFISData getHistoryPriceData price data found size " + pdList.size());
         }
         else
         {
            objPriceData.put("status", "failure");
            logger.info("CallingServiceFISData getHistoryPriceData price data not found  ");
         }
         logger.info("CallingServiceFISData getHistoryPriceData End ");
      }
      catch (Exception e)
      {
         logger.error("CallingServiceFISData getHistoryPriceData Exception " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            priceDate = null;
            ticker = null;
            line = null;
            prevBusinessdate = null;
            pdList = null;
         }
         catch (Exception e)
         {
         }
      }
      return objPriceData;
   }
}
