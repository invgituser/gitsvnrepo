package com.invessence.price.yahoo;

import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.price.processor.Service.CallingService;
import com.invessence.price.processor.bean.*;
import com.invessence.price.yahoo.histquotes.*;
import com.invessence.service.bean.ServiceRequest;

/**
 * Created by bhaveshy on 3/15/2016.
 */
public class CallingServiceYahoo implements CallingService
{
   private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallingServiceYahoo.class);
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


   //*********************Historical Process*****************
   // To fetch historical prices from Yahoo API
   public List<PriceData> getHistoricalPriceData(String businessDate, String ticker) throws Exception
   {
      List<PriceData> pdList = null;

      try
      {

         logger.info("CSIDATA getHistoricalPriceData Start " );
         pdList = new ArrayList<PriceData>();

         Date d = sdf.parse(businessDate);
//				Calendar from = new GregorianCalendar(2015, 9, 5);// Calendar.getInstance();
//				Calendar to = new GregorianCalendar(2016, 1, 29);// Calendar.getInstance();//2007-05-30
         Calendar from = Calendar.getInstance();
         from.setTime(d);
         Calendar to = Calendar.getInstance();//2007-05-30
         to.setTime(d);
         from.add(Calendar.YEAR, -20); // from 5 years ago
         Stock stk = YahooFinance.get(ticker, from, to, Interval.DAILY);

        /* System.out.println(priceDate);
         System.out.println(stk.getHistory().size() + "Open :" + stk.getQuote().getOpen());
         System.out.println(sdf.format(stk.getQuote().getLastTradeTime().getTime()));
         System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
         System.out.println("Volume   :" + stk.getQuote().getVolume());
         System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
         System.out.println("DayLow   :" + stk.getQuote().getDayLow());
         System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());
*/
         List<HistoricalQuote> hstLst = stk.getHistory();
         if (hstLst == null || hstLst.size() == 0 || hstLst.equals(""))
         {
            System.out.println("List is empty:");
            // mailAlertMsg.append("List is empty:" + "\n");
         }
         else
         {
            Iterator<HistoricalQuote> itr = hstLst.iterator();
            System.out.println("*********************Historical Data************************");

            boolean isPriceAvaiForBusiDate = false;
            while (itr.hasNext())
            {

               HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
               PriceData hpd = new PriceData(historicalQuote.getSymbol(),
                                             sdf.format(historicalQuote.getDate().getTime()),
                                             Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
                                             Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
                                             Long.valueOf(historicalQuote.getVolume()), new Date(),
                                             Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());

               if (isPriceAvaiForBusiDate == false)
               {
                  if (sdf.format(historicalQuote.getDate().getTime()).equals(businessDate))
                  {
                     isPriceAvaiForBusiDate = true;
                  }
               }

               if (!Double.valueOf("" + historicalQuote.getClose()).equals(0))
               {
                  pdList.add(hpd);
               }

            }

            if (isPriceAvaiForBusiDate == false)
            {
               System.out.println("Price not available for ticker:" + ticker + " for businessdate :" + businessDate + "\n");
               //mailAlertMsg.append("Price not available for ticker:" + ticker + " for businessdate :" + priceDate + "\n");

            }
         }
         logger.info("CSIDATA getHistoricalPriceData End " );
      }
      catch (Exception e)
      {
         logger.error("CSIDATA getHistoricalPriceData Exception "+e );
         e.printStackTrace();
      }
      return pdList;
   }

   // **********DAILY PROCESS***********************
   // To fetch daily prices from yahoo API
   public List<PriceData> getDailyPriceData(String businessDate, List<SecMaster> tickerList) throws Exception
   {
      List<PriceData> pdList = null;
      PriceData pd = null;

      Iterator<SecMaster> sec = tickerList.iterator();
      /*try
      {*/
      System.out.println("businessDate :" + businessDate);
      Date d = sdf.parse(businessDate);
      Calendar from = Calendar.getInstance();
      from.setTime(d);
      System.out.println("from:" + from.getTime() + "    date:" + d);
      int i = 0;
      pdList = new ArrayList<PriceData>();
      while (sec.hasNext())
      {
         SecMaster secMaster = (SecMaster) sec.next();
         System.out.println(secMaster.toString());
         try
         {

            Stock stk = YahooFinance.get(secMaster.getTicker(), from, from, Interval.DAILY);
           /* System.out.println(sdf.format(stk.getQuote().getLastTradeTime().getTime()) + "Open   :" + stk.getQuote().getOpen());
            System.out.println("LastTradePriceOnly   :" + stk.getQuote().getPrice());
            System.out.println("Volume   :" + stk.getQuote().getVolume());
            System.out.println("DayHigh   :" + stk.getQuote().getDayHigh());
            System.out.println("DayLow   :" + stk.getQuote().getDayLow());
            System.out.println("PreviousClose   :" + stk.getQuote().getPreviousClose());*/

            List<HistoricalQuote> hstLst = stk.getHistory();

            if (hstLst == null || hstLst.size() == 0 || hstLst.equals(""))
            {
               System.out.println("List is empty:");
               // mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for busunessdate :" + priceDate + "\n");
            }
            else
            {
               HistoricalQuote historicalQuote = (HistoricalQuote) hstLst.get(0);


               System.out.println(sdf.format(stk.getQuote().getLastTradeTime().getTime()));


               // if (sdf.format(historicalQuote.getDate().getTime()).equals(priceDate))
               //  {
               // if (Double.valueOf("" + historicalQuote.getClose()).equals(0))
               //  {
               //   mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
               //  }
               //  else
               //{
               PriceData hpd = new PriceData(historicalQuote.getSymbol(),
                                             sdf.format(historicalQuote.getDate().getTime()),
                                             Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
                                             Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
                                             Long.valueOf(historicalQuote.getVolume()), new Date(),
                                             Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());
               // System.out.println("**************PRICE******************" + hpd);
               pdList.add(hpd);

               // }
               //}
               // else
               // {
               //  mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for busunessdate :" + priceDate + "\n");

               // }
            }
         }
         catch (Exception e)
         {
            // mailAlertMsg.append("Price api exception for ticker:" + secMaster.getTicker() + "\n" + e.getMessage());
            e.printStackTrace();
         }


      }


      /*}
      *//*catch (Exception e)
      {
         mailAlertMsg.append("Price api exception for ticker: \n" + e.getMessage());
      }*/

      System.out.println("***************size of pdlist" + pdList.size());
      return pdList;
   }



   @Override
   // **********DAILY PROCESS***********************
   // To fetch daily prices from yahoo API
   public HashMap<String, Object> getDailyPriceData(String businessDate, String ticker,ServiceRequest serviceRequest) throws Exception
   {
      HashMap<String, Object> objPriceData = null;
      List<PriceData> pdList = null;
      PriceData pd = null;

      System.out.println("businessDate :" + businessDate);
      Date d = sdf.parse(businessDate);
      Calendar from = Calendar.getInstance();
      from.setTime(d);
      System.out.println("from:" + from.getTime() + "    date:" + d);
      int i = 0;

      try
      {
         logger.info("CSIDATA getDailyPriceData Start businessDate :" + businessDate +" ticker:"+ticker );
         objPriceData = new HashMap<String, Object>();
         pdList = new ArrayList<PriceData>();

         Stock stk = YahooFinance.get(ticker, from, from, Interval.DAILY);

         List<HistoricalQuote> hstLst = stk.getHistory();

         if (hstLst == null || hstLst.size() == 0 || hstLst.equals(""))
         {
            System.out.println("List is empty:");
            objPriceData.put("status", "failure");
            logger.info("CSIDATA getDailyPriceData Priced data not found");
         }
         else
         {
            HistoricalQuote historicalQuote = (HistoricalQuote) hstLst.get(0);


            System.out.println(sdf.format(stk.getQuote().getLastTradeTime().getTime()));

            PriceData hpd = new PriceData(historicalQuote.getSymbol(),
                                          sdf.format(historicalQuote.getDate().getTime()),
                                          Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
                                          Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
                                          Long.valueOf(historicalQuote.getVolume()), null,
                                          Double.valueOf("" + historicalQuote.getClose()), new Long(2), new Date());
            pdList.add(hpd);


            objPriceData.put("status", "success");
            objPriceData.put("priceData", pdList);
            logger.info("CSIDATA getDailyPriceData Priced data found data size " + pdList.size() );
         }
         logger.info("CSIDATA getDailyPriceData End " );
      }
      catch (Exception e)
      {
         objPriceData.put("status", "failure");

         logger.error("CSIDATA getDailyPriceData Exception " + e );
         e.printStackTrace();
      }


      System.out.println("***************size of pdlist" + pdList.size());
      return objPriceData;
   }


   @Override
   public HashMap<String, Object>  getHistoryPriceData(String businessDate, String ticker,ServiceRequest serviceRequest) throws Exception
   {
      List<PriceData> pdList = null;
      HashMap<String, Object> objPriceData = null;
      int counter=0;
      PriceData hpd=null;
      String prevClosePrice=null;
      Date prevBusinessdate=null;

      try
      {
         logger.info("CSIDATA getHistoryPriceData Start priceDate :" + businessDate +" ticker:"+ticker );
         objPriceData = new HashMap<String, Object>();
         pdList = new ArrayList<PriceData>();

         Date d = sdf.parse(businessDate);
         Calendar from = Calendar.getInstance();
         from.setTime(d);
         Calendar to = Calendar.getInstance();//2007-05-30
         to.setTime(d);
         from.add(Calendar.YEAR, -20); // from 5 years ago
         Stock stk = YahooFinance.get(ticker, from, to, Interval.DAILY);

         List<HistoricalQuote> hstLst = stk.getHistory();
         if (hstLst == null || hstLst.size() == 0 || hstLst.equals(""))
         {
            System.out.println("List is empty:");
            objPriceData.put("status","failure");
            logger.info("CSIDATA getHistoryPriceData price data not found" );
         }
         else
         {
            Iterator<HistoricalQuote> itr = hstLst.iterator();
            System.out.println("*********************Historical Data************************");

            boolean isPriceAvaiForBusiDate = false;
            while (itr.hasNext())
            {
               HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();

               if(counter==0)
               {
                   hpd = new PriceData(historicalQuote.getSymbol(),
                                                sdf.format(historicalQuote.getDate().getTime()),
                                                Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
                                                Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
                                                Long.valueOf(historicalQuote.getVolume()), null,
                                                Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());
               }else{
                   hpd = new PriceData(historicalQuote.getSymbol(),
                                                sdf.format(historicalQuote.getDate().getTime()),
                                                Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
                                                Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
                                                Long.valueOf(historicalQuote.getVolume()), prevBusinessdate,
                                                Double.valueOf("" + historicalQuote.getClose()), new Long(2), new Date());
               }

               if (isPriceAvaiForBusiDate == false)
               {
                  if (sdf.format(historicalQuote.getDate().getTime()).equals(businessDate))
                  {
                     isPriceAvaiForBusiDate = true;
                  }
               }

               if (!Double.valueOf("" + historicalQuote.getClose()).equals(0))
               {
                  pdList.add(hpd);
               }
               prevBusinessdate = null;
               prevClosePrice=null;
               prevBusinessdate = historicalQuote.getDate().getTime();
               prevClosePrice=""+historicalQuote.getClose();
               counter++;
               hpd=null;

            }

            objPriceData.put("status","success");
            objPriceData.put("priceData",pdList);

            if (isPriceAvaiForBusiDate == false)
            {
               System.out.println("Price not available for ticker:" + ticker + " for businessdate :" + businessDate + "\n");

            }
            logger.info("CSIDATA getHistoryPriceData price data found data size "+pdList.size() );
         }
      }
      catch (Exception e)
      {
         logger.error("CSIDATA getHistoryPriceData Exception "+e );
         e.printStackTrace();
      }
      return objPriceData;
   }
}
