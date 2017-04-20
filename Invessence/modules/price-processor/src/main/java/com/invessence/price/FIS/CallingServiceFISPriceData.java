package com.invessence.price.FIS;

import java.net.URL;
import java.util.*;
import javax.xml.bind.*;

import com.invessence.price.FIS.bean.*;
import com.invessence.price.processor.Service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * Created by sagar on 4/10/2017.
 */
@Service
public class CallingServiceFISPriceData implements CallingFISPriceService
{

   private static final Logger logger = Logger.getLogger(CallingServiceFISPriceData.class);

   public static void main(String[] args)
   {
      try
      {

//         CallingServiceFISExhange ob = new CallingServiceFISExhange();
//         ob.getHistoricalExchangeRate("http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close&Direction=Backward&username=vyas&usergroup=INVESSENCE&password=welcome%20", "IPXJ.L");
//         ob.getDailyExchangeRate("http://91.212.43.32/XML/Quote.asp?SYMBOL=$$SYMBOL$$&Fields=name,last,bid,ask,high,low,date,open,close,time&username=vyas&usergroup=INVESSENCE&password=welcome", "IPXJ.L");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   @Override
   public HistoricalData getDailyPrice(String URL, String symbol,String businessDate) throws Exception
   {
      HistoricalData objDailyData = null;
      URL url = null;
      JAXBContext jaxbContext = null;
      Unmarshaller unmarshaller = null;
      try
      {
         logger.info("getDailyExchangeRate Start URL:" + URL + " symbol:" + symbol);
         jaxbContext = JAXBContext.newInstance(HistoricalData.class);
         unmarshaller = jaxbContext.createUnmarshaller();
         URL = URL.replace("$$SYMBOL$$", symbol)+ "&From=" + businessDate.replaceAll("-", "");
         URL = URL.replace("$$DIRECTION$$", "Forward");
         logger.info("getDailyExchangeRate Exchange data URL:" + URL);
         url = new URL(URL);
         objDailyData = (HistoricalData) unmarshaller.unmarshal(url);
         logger.info("getDailyExchangeRate Exchange data " + objDailyData.toString());
      }
      catch (Exception e)
      {
         objDailyData = null;
         logger.error("getDailyExchangeRate Error:" + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            url = null;
            jaxbContext = null;
            unmarshaller = null;
         }
         catch (Exception e)
         {
         }
      }
      return objDailyData;
   }

   @Override
   public List<HistoricalDataRates> getHistoricalPrice(String URL, String symbol,String businessDate) throws Exception
   {
      boolean bflag = true;
      int counter = 0;
      URL url = null;
      String nxtFltrDt = "";
      JAXBContext jaxbContext = null;
      Unmarshaller unmarshaller = null;
      Object obj = null;
      HistoricalData objHistoricalData = null;
      List<HistoricalDataRates> historyData = new ArrayList<HistoricalDataRates>();
//      List<HistoricalDataRates> retHistoryData = new ArrayList<HistoricalDataRates>();
      try
      {
         logger.info("getHistoricalExchangeRate Start");
         jaxbContext = JAXBContext.newInstance(HistoricalData.class);
         unmarshaller = jaxbContext.createUnmarshaller();
         while (bflag)
         {
            String strURL2;
            if (counter == 0)
            {
               strURL2 = URL.replace("$$SYMBOL$$", symbol);
               strURL2 = strURL2.replace("$$DIRECTION$$", "Backward")+ "&To=" + businessDate.replaceAll("-", "");
            }
            else
            {
               strURL2 = URL.replace("$$SYMBOL$$", symbol);
               strURL2 = strURL2.replace("$$DIRECTION$$", "Backward") + "&To=" + nxtFltrDt.replaceAll("-", "");

            }
            logger.info("getHistoricalExchangeRate FIS URL " + strURL2);
            counter++;
            url = new URL(strURL2);
            objHistoricalData = (HistoricalData) unmarshaller.unmarshal(url);
            if(objHistoricalData.getHistoricalExchangeRates().getTimeSeriesPoint()!=null && objHistoricalData.getHistoricalExchangeRates().getTimeSeriesPoint().size()>0)
            {
               int size = objHistoricalData.getHistoricalExchangeRates().getTimeSeriesPoint().size() - 1;
               nxtFltrDt = objHistoricalData.getHistoricalExchangeRates().getTimeSeriesPoint().get(size).getDate();
               logger.info("getHistoricalExchangeRate return data" + objHistoricalData.toString());
               historyData.addAll(objHistoricalData.getHistoricalExchangeRates().getTimeSeriesPoint());
               if (Integer.parseInt(objHistoricalData.getHistoricalExchangeRates().getNbpoints()) < 1600
                  || objHistoricalData.getHistoricalExchangeRates().getStatus().equalsIgnoreCase("NoData"))
               {
                  bflag = false;
                  break;
               }
               historyData.remove(historyData.size() - 1);
            }
         }
//         for (int j = historyData.size() - 1; j >= 0; j--)
//         {
//            retHistoryData.add(historyData.get(j));
//         }
         logger.info("getHistoricalExchangeRate End");
      }
      catch (Exception e)
      {
//         historyData = null;
//         retHistoryData=null;
         logger.info("getHistoricalExchangeRate Error:" + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            url = null;
            nxtFltrDt = "";
            jaxbContext = null;
            unmarshaller = null;
            obj = null;
            objHistoricalData = null;
//            historyData = null;
         }
         catch (Exception e)
         {
         }
      }
      return historyData;
   }
}
