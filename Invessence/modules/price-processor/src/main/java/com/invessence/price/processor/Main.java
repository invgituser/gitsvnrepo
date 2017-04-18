package com.invessence.price.processor;

import java.net.URL;
import java.util.*;
import javax.xml.bind.*;

import com.invessence.price.FIS.bean.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/*
   This class is use to call method process() of PriceProcessor.class
 */

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", namespace= "http://91.212.43.32/XML/", propOrder = {"INFOTECXML"})
//@XmlRootElement(name="QUOTE")
//@XmlRootElement( namespace = "http://91.212.43.32/XML/", name = "INFOTECXML")
//

public class Main
{

   public static void main(String[] args)
   {
      try
      {
//         String company = "BUILDINGBENJAMINS", mode = "UAT", pricingrqd = "Y";

         String company = null, mode = null, pricingrqd = null;
         System.out.print("passing argument length " + args.length);
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

         System.out.print("passing argument company " + company + " mode " + mode+" pricingrqd "+pricingrqd);
         if (company != null && mode != null)
         {
            ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
            if (pricingrqd.equalsIgnoreCase("Y") || pricingrqd.equalsIgnoreCase("YES"))
            {
               ExchangeRateProcessing exchngProcess = context.getBean(ExchangeRateProcessing.class);
               boolean bFlag= exchngProcess.process(company, mode);
               if(true)
               {
                  System.out.print("Attempting for Pricing as exchange rates are collected");
                  PriceProcessing priceProcess = context.getBean(PriceProcessing.class);
                  priceProcess.process(company, mode);
               }else{
                  System.out.print("No Attempt for Pricing as exchange rates are not collected");
               }
               context.close();
            }
            else
            {
               PriceProcessing priceProcess = context.getBean(PriceProcessing.class);
               priceProcess.process(company, mode);
               context.close();
            }
         }
         else
         {
            System.out.print(" Company & mode are not specified, since process not started for price processing");
         }
      }
      catch (Exception e)
      {
         System.out.print("main error " + e);
         e.printStackTrace();
      }

//      try
//      {
//         java.util.Date utilDate = new java.util.Date();
//         java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//         System.out.println("utilDate:" + utilDate);
//         System.out.println("sqlDate:" + sqlDate);
//         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("priceBeanConf.xml");
//         ExchangeRateProcessing pp = context.getBean(ExchangeRateProcessing.class);
//         pp.process();
//      } catch (Exception e)
//      {
//         e.printStackTrace();
//      }

//      boolean bflag = true;
//      int counter = 0;
//      URL url = null;
//      String strURL = "http://91.212.43.32/XML/TimeSeries.xml?Symbol=$$SYMBOL$$&TimeScale=1440&MaxPoints=1600&Fields=last,bid,ask,high,low,date,open,close&Direction=$$DIRECTION$$&username=vyas&usergroup=INVESSENCE&password=welcome%20";
//      String symbol = "IPXJ.L";
//      String nxtFltrDt = "";
//      try
//      {
//         List<HistoricalDataRates> exchangeData = new ArrayList<HistoricalDataRates>();
//         while (bflag)
//         {
//            String strURL2;
//            JAXBContext jaxbContext = JAXBContext.newInstance(HistoricalData.class);
//            Unmarshaller u = jaxbContext.createUnmarshaller();
//            if (counter == 0)
//            {
//               strURL2 = strURL.replace("$$SYMBOL$$", symbol);
//               strURL2 = strURL2.replace("$$DIRECTION$$", "Forward");
//            }
//            else
//            {
//
//               strURL2 = strURL.replace("$$SYMBOL$$", symbol);
//               strURL2 = strURL2.replace("$$DIRECTION$$", "Forward") + "&To=" + nxtFltrDt.replaceAll("-", "");
//
//            }
//            counter++;
//
//            System.out.println("strURL2 " + strURL2);
//            url = new URL(strURL2);
//
////            Object o1 = u.unmarshal(url);
//            HistoricalData o = (HistoricalData) u.unmarshal(url);
//            int size = o.getHistoricalExchangeRates().getTimeSeriesPoint().size() - 1;
//            nxtFltrDt = o.getHistoricalExchangeRates().getTimeSeriesPoint().get(size).getDate();
//            System.out.println("he " + o.toString());
////            List<HistoricalDataRates> exchangeData = o.getHistoricalExchangeRates().getTimeSeriesPoint();
//            exchangeData.addAll(o.getHistoricalExchangeRates().getTimeSeriesPoint());
//            if (Integer.parseInt(o.getHistoricalExchangeRates().getNbpoints()) < 1600 || o.getHistoricalExchangeRates().getStatus().equalsIgnoreCase("NoData"))
//            {
//               bflag = false;
//               break;
//            }
//            exchangeData.remove(exchangeData.size() - 1);
//            System.out.println("bflag " + bflag);
//         }
//         System.out.println("Reverse Iteration Start ");
//         for (int i = exchangeData.size() - 1; i >= 0; i--)
//         {
//            System.out.println("" + exchangeData.get(i).getDate());
//         }
//         System.out.println("Reverse Iteration End ");
//         System.out.println("------------------------#######################################--------------------------------- ");
//         System.out.println("Normal Iteration Start ");
//         for (int i = 0; i < exchangeData.size(); i++)
//         {
//            System.out.println("" + exchangeData.get(i).getDate());
//         }
//         System.out.println("Normal Iteration End ");
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//
//      try
//      {
//         JAXBContext jaxbContext = JAXBContext.newInstance(DailyData.class);
//         Unmarshaller u = jaxbContext.createUnmarshaller();
//         URL url = new URL("http://91.212.43.32/XML/Quote.asp?SYMBOL=SGDUSD=&Fields=name,last,bid,ask,high,low,date,open,close,time&username=vyas&usergroup=INVESSENCE&password=welcome" );
//         Object o1 = u.unmarshal(url);
//         DailyData o = (DailyData) u.unmarshal(url);
//         System.out.println("de " + o.toString());
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }


   }


}
