package com.invessence.price.processor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.invessence.service.util.ServiceParameters;
import com.invessence.web.constant.Const;
import com.invessence.price.processor.Service.PriceService;
import com.invessence.price.processor.bean.*;
import com.invessence.price.util.*;
import com.invessence.rbsa.RBSA2;
import com.invessence.rbsa.dao.data.RBSAData;
import com.invessence.emailer.util.EmailCreator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invessence.price.processor.dao.DBParametersDao;
import com.invessence.price.processor.dao.PriceDataDao;
import com.invessence.price.processor.dao.SecMasterDao;

@Component
public class PriceProcessor
{
   private static final Logger logger = Logger.getLogger(PriceProcessor.class);
//   @Autowired
   EmailCreator emailCreator;
   @Autowired
   DBParametersDao dbParametersDao;
   @Autowired
   SecMasterDao secMasterDao;
   @Autowired
   PriceDataDao priceDataDao;

//   @Autowired
//   private
//   MessageDao messageDao;

//   @Value(value = "${securities.provider}")
//   String price_provider;
   @Autowired
   PriceService priceService;

//   @Autowired
//   DataSource dataSource;
//   private JdbcTemplate jdbcTemplate;


   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat switchFormat = new SimpleDateFormat("yyyyMMdd");
   //EmailCreator emailCreator = new EmailCreator();
   //
   public void process() throws SQLException
   {
      System.out.println("PriceProcessor.process");
      String companyName=ServiceParameters.COMPANY_NAME;
      System.out.println("companyName = " + companyName);
      StringBuilder mailAlertMsg = null;

      try
      {
         mailAlertMsg = new StringBuilder();
         // code to get values for businessdate,last businessdate of month,price date from invessence_switch table
         Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
         if (dbParamMap == null && dbParamMap.size() == 0)
         {
            mailAlertMsg.append("DB parameters are not available");
            System.out.println("DB parameters are not available");
         }
         else
         {
            System.out.println("LAST_BDATE_OF_MONTH :" + dbParamMap.get("LAST_BDATE_OF_MONTH").getValue());
            // code to get the list of tickers from  sec_master table whose status=A

            List<SecMaster> lst = secMasterDao.findByWhere("status = 'A'");
            if (lst != null && lst.size() > 0)
            {
               String priceDate = sdf.format(switchFormat.parse(dbParamMap.get("PRICE_DATE").getValue().toString()));
               System.out.println("Price Date :" + priceDate);

               // code to check for dailyProcess or monthlyProcess
               if (CommonUtil.dateCompare(dbParamMap.get("PRICE_DATE").getValue().toString(), dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == false)
               {

                  List<APIDetails> apidetails = secMasterDao.getSwitch(companyName, "DAILY_PRICING");
                  if (apidetails == null || apidetails.size() == 0)
                  {
                     System.out.println("apidetails not available");
                     mailAlertMsg.append("apidetails not available");
                  }
                  else
                  {
                     System.out.println("************Number of serviceProvider available************" + apidetails.size());
                     dailyProcess(apidetails, priceDate, lst, mailAlertMsg);
                  }
               }

               // code to check for dailyProcess or monthlyProcess
               else if (CommonUtil.dateCompare(dbParamMap.get("PRICE_DATE").getValue().toString(), dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == true)
               {
                  List<APIDetails> apidetails = secMasterDao.getSwitch(companyName, "MONTHLY_PRICING");
                  if (apidetails == null || apidetails.size() == 0)
                  {
                     System.out.println("apidetails not available");
                     mailAlertMsg.append("apidetails not available");
                  }
                  else
                  {
                     System.out.println("************Number of serviceProvider available************" + apidetails.size());
                     monthlyProcess(apidetails, priceDate, lst, mailAlertMsg);
                  }
               }
            }
            else
            {
               mailAlertMsg.append("Ticker list not available for process");
               System.out.println("Ticker list not available for process");
            }
         }
      }
      catch (Exception e)
      {
         System.out.println("PriceProcessor.process() WE R HERE..");

      }

      // Here we are sending mail to support team, if we have any exception
      finally
      {
         if (mailAlertMsg.length() > 0)
         {
            System.out.println("MailAlertMsg IS :" + mailAlertMsg);
            emailCreator.sendToSupport("ERR", "EXCEPTION:PRICING MODULE", mailAlertMsg.toString());
            // EmailProcessor.main();
           /* EmailMsg md = new EmailMsg();
            md.setMsg(mailAlertMsg);
            try
            {
					messageDao.insert(md);
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}*/
         }
         else
         {
            System.out.println("MailAlertMsg is empty");
         }
      }

   }
    /*
    * In this method we are performing daily process
    */

   public void dailyProcess(List<APIDetails> apidetails, String priceDate, List<SecMaster> tickerList, StringBuilder mailAlertMsg)
   {
      Iterator<SecMaster> sec = tickerList.iterator();
      SecMaster secMaster = new SecMaster();
      System.out.println("PriceProcessor.process() executing Daily Process");
      List<PriceData> pdList = null;

      try
      {
         //deleting all rows from tmp_rbsa_daily table
         priceDataDao.delete();

         //selecting api based on priority
         pdList = priceService.getPrice(apidetails, PriceProcessConst.DAILY, priceDate, tickerList);

         if (pdList == null || pdList.size() == 0 || pdList.equals(""))
         {
            mailAlertMsg.append("Daily price data not available for upload");
         }


         else
         {
            for (int i = 0; i < pdList.size(); i++)
            {
               if (!pdList.get(i).getBusinessDate().equals(priceDate))
               {
                  if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                  {
                     mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
                  }

                  mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for busunessdate :" + priceDate + "\n");
               }
            }
            try
            {
               // List is inserted in tmp_rbsa_daily table
               priceDataDao.insertBatch(pdList,"");


               if (mailAlertMsg.length() == 0)
               {
                  try
                  {

                     // code to call daily_price_processor procedure(in this we are calculating daily return and inserting values in rbsa_daily table)
                     priceDataDao.callProcedure(PriceProcessConst.DAILY, priceDate, "");
                     try
                     {
                        //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table and invdb.inv_date_table)
                        priceDataDao.callEodProcedure(PriceProcessConst.DAILY, priceDate);
                     }
                     catch (Exception e)

                     {
                        mailAlertMsg.append("Daily price eod process issue " + e.getMessage());
                     }
                  }
                  catch (Exception e)
                  {
                     mailAlertMsg.append("Daily price data operation issue " + e.getMessage());
                  }
               }
            }
            catch (Exception e)
            {
               mailAlertMsg.append("Daily price data upload issue" + e.getMessage());
            }
         }

      }
      catch (Exception e)
      {
         mailAlertMsg.append("Daily price api exception" + e.getMessage());
      }

   }

   //In this method we are performing monthly process

   public void monthlyProcess(List<APIDetails> apidetails, String priceDate, List<SecMaster> tickerList, StringBuilder mailAlertMsg)
   {
      System.out.println("PriceProcessor.process() executing Monthly Process");

      List<PriceData> pdList = null;
      Iterator<SecMaster> sec = tickerList.iterator();

      boolean isPriceAvaiForBusiDate = false;
      while (sec.hasNext())
      {
         SecMaster secMaster = (SecMaster) sec.next();
         System.out.println(secMaster.toString());
         try
         {
            //deleting all rows from tmp_rbsa_daily table
            priceDataDao.delete();
            pdList = priceService.getPrice(apidetails, PriceProcessConst.MONTHLY, priceDate, secMaster.getTicker());
            //callingYahoo.getHistoricalPriceData(priceDate, secMaster.getTicker(), mailAlertMsg);
            if (pdList == null || pdList.size() == 0)
            {
               mailAlertMsg.append("Historical price data not available for ticker " + secMaster.getTicker() + "\n");
            }
            else
            {
               forloop:
               for (int i = 0; i < pdList.size(); i++)
               {
                  if (isPriceAvaiForBusiDate == false)
                  {
                     if (pdList.get(i).getBusinessDate().equals(priceDate))
                     {
                        isPriceAvaiForBusiDate = true;
                        break forloop;
                     }
                  }

                  if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                  {
                     System.out.println("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
                     // mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
                  }
               }
               if (isPriceAvaiForBusiDate == false)
               {
                  mailAlertMsg.append("Price not available for ticker:" + secMaster.getTicker() + " for businessdate :" + priceDate + "\n");

               }

               try
               {
                  // List is inserted in tmp_rbsa_daily table
                  priceDataDao.insertBatch(pdList,"");
                  try
                  {
                     // code to call monthly_price_processor procedure(in this we are calculating daily return,monthly return and inserting values in rbsa_daily table)
                     priceDataDao.callProcedure(PriceProcessConst.MONTHLY, priceDate, secMaster.getTicker());
                     if (secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
                     {
                        try
                        {
                           //
                           rbsaCall(secMaster.getTicker());
                        }
                        catch (Exception e)
                        {
                           mailAlertMsg.append("RBSA process call issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                        }
                     }

                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                     mailAlertMsg.append("Historical price data operation issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                  }
               }
               catch (Exception e)
               {
                  mailAlertMsg.append("Historical price data upload issue " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
               }
            }
         }
         catch (Exception e)
         {
            System.out.println("Monthly price api Exception for:" + secMaster.getTicker() + "\n" + e.getMessage());
            mailAlertMsg.append("Monthly price api Exception for:" + secMaster.getTicker() + "\n" + e.getMessage());
         }
      }

      if (mailAlertMsg.length() > 0)
      {
         System.out.println("MailAlertMsg IS :" + mailAlertMsg);
      }
      else
      {
         System.out.println("MailAlertMsg is empty");
         try
         {
            //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table,
            // invdb.inv_monthly_date_table and invdb.inv_date_table)
            priceDataDao.callEodProcedure(PriceProcessConst.MONTHLY, priceDate);
         }
         catch (Exception e)
         {
            mailAlertMsg.append("Historical price eod process issue for ticker " + e.getMessage());
         }
      }


   }

   // In this method we are performing onDemandProcess
   public void onDemandProcess(String ticker)
   {
      System.out.println("PriceProcessor.process() executing OnDemand Process");
      String companyName=ServiceParameters.COMPANY_NAME;
      System.out.println("companyName = " + companyName);
      StringBuilder mailAlertMsg = null;
      List<APIDetails> apidetails = null;
      try
      {

         apidetails = secMasterDao.getSwitch(companyName, "ONDEMAND_PRICING");
         if (apidetails == null || apidetails.size() == 0)
         {
            System.out.println("apidetails not available for ONDEMAND PROCESS ");
            mailAlertMsg.append("apidetails not available");
         }
         else
         {

            System.out.println("************Number of serviceProvider available************" + apidetails.size());

            List<PriceData> pdList = null;
            try
            {
               //selects the ticker for ondemand process
               SecMaster secMaster = secMasterDao.findByTicker(ticker);
               if (secMaster == null)
               {
                  //Need to call API for ticker information for stored into DB
                  System.out.println("Ticker " + ticker + " not available in our database");
               }
               else
               {

                  try
                  {
                     // code to get values for businessdate,last businessdate of month,price date from invessence_switch table
                     Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
                     if (dbParamMap == null && dbParamMap.size() == 0)
                     {
                        mailAlertMsg.append("DB parameters are not available");
                        System.out.println("DB parameters are not available");
                     }
                     else
                     {
                        String priceDate = sdf.format(switchFormat.parse(dbParamMap.get("PRICE_DATE").getValue().toString()));
                        try
                        {
                           //deleting all rows from tmp_rbsa_daily
                           priceDataDao.delete();
                           pdList = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker());
                           if (pdList == null || pdList.size() == 0 || pdList.equals(""))
                           {
                              mailAlertMsg.append("OnDemand price data not available for ticker " + secMaster.getTicker() + "\n");
                           }
                           else
                           {
                              for (int i = 0; i < pdList.size(); i++)
                              {
                                 if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                                 {
                                    mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
                                 }
                              }
                              try
                              {
                                 // List is inserted in tmp_rbsa_daily table
                                 priceDataDao.insertBatch(pdList,"");
                                 try
                                 {
                                    //code to call monthly_price_processor procedure(in this we are calculating daily return,monthly return and inserting values in rbsa_daily table)
                                    priceDataDao.callProcedure(PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker());
                                    if (secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
                                    {
                                       try
                                       {
                                          rbsaCall(secMaster.getTicker());
                                       }
                                       catch (Exception e)
                                       {
                                          mailAlertMsg.append("RBSA process call issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                                       }
                                    }
                                    try
                                    {
                                       //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table,
                                       // invdb.inv_monthly_date_table and invdb.inv_date_table)
                                       priceDataDao.callEodProcedure(PriceProcessConst.ONDEMAND, priceDate);
                                    }
                                    catch (Exception e)
                                    {
                                       mailAlertMsg.append("OnDemad price eod process issue  " + e.getMessage());
                                    }

                                 }
                                 catch (Exception e)
                                 {
                                    e.printStackTrace();
                                    mailAlertMsg.append("OnDemand price data operation issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                                 }
                              }
                              catch (Exception e)
                              {
                                 mailAlertMsg.append("OnDemand price data upload issue " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                              }
                           }
                        }
                        catch (Exception e)
                        {
                           System.out.println("OnDemand api Exception for ticker" + secMaster.getTicker() + "\n" + e.getMessage());
                           mailAlertMsg.append("OnDemand api Exception for ticker" + secMaster.getTicker() + "\n");
                        }
                     }
                  }
                  catch (Exception e)
                  {
                     System.out.println("Ticker Exception:" + secMaster.getTicker());
                     mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker() + "\n");
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println(" Exception in getting ticker from secmaster table:" + ticker);
               mailAlertMsg.append("Exception in getting ticker from secmaster table:" + ticker + "\n");
            }
         }
      }
      catch (SQLException e)
      {
         mailAlertMsg.append("Exception in getting operation name in OnDemand Process" + e.getMessage());
         e.printStackTrace();

      }

   }

   public void initialProcess(String ticker)
   {
      System.out.println("PriceProcessor.process() executing Initial Process");
      String companyName=ServiceParameters.COMPANY_NAME;
      System.out.println("companyName = " + companyName);
      StringBuilder mailAlertMsg = null;
      List<APIDetails> apidetails = null;
      try
      {

         apidetails = secMasterDao.getSwitch(companyName, PriceProcessConst.INITIAL_PROCESS);
         if (apidetails == null || apidetails.size() == 0)
         {

           mailAlertMsg.append("apidetails not available");
         }
         else
         {

            System.out.println("************Number of serviceProvider available************" + apidetails.size());

            List<PriceData> pdList = null;
            try
            {
               //selects the ticker for ondemand process
               SecMaster secMaster = secMasterDao.findByTicker(ticker);
               if (secMaster == null)
               {
                  //Need to call API for ticker information for stored into DB
                  System.out.println("Ticker " + ticker + " not available in our database");
               }
               else
               {

                  try
                  {
                     // code to get values for businessdate,last businessdate of month,price date from invessence_switch table
                     Map<String, DBParameters> dbParamMap = dbParametersDao.getDBParametres();
                     if (dbParamMap == null && dbParamMap.size() == 0)
                     {
                        mailAlertMsg.append("DB parameters are not available");
                        System.out.println("DB parameters are not available");
                     }
                     else
                     {
                        String priceDate = sdf.format(switchFormat.parse(dbParamMap.get("PRICE_DATE").getValue().toString()));
                        try
                        {
                           //deleting all rows from tmp_rbsa_dailyBUSINESS
                           priceDataDao.delete();
                           pdList = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker());
                           if (pdList == null || pdList.size() == 0 || pdList.equals(""))
                           {
                              mailAlertMsg.append("SPY price data not available  ");
                           }
                           else
                           {
                              for (int i = 0; i < pdList.size(); i++)
                              {
                                 if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                                 {
                                    mailAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker() + "\n");
                                 }
                              }
                              try
                              {
                                 // List is inserted in tmp_rbsa_daily table
                                 priceDataDao.insertBatch(pdList,"");
                                 try
                                 {
                                    //code to call monthly_price_processor procedure(in this we are calculating daily return,monthly return and inserting values in rbsa_daily table)
                                    priceDataDao.callProcedure(PriceProcessConst.INITIAL_PROCESS, priceDate, secMaster.getTicker());
                                    /*try
                                    {
                                       //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table,
                                       // invdb.inv_monthly_date_table and invdb.inv_date_table)
                                      // priceDataDao.callEodProcedure(PriceProcessConst.ONDEMAND, priceDate);
                                    }
                                    catch (Exception e)
                                    {
                                       mailAlertMsg.append("OnDemad price eod process issue  " + e.getMessage());
                                    }*/

                                 }
                                 catch (Exception e)
                                 {
                                    e.printStackTrace();
                                    mailAlertMsg.append("Initial process price data operation issue for ticker " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                                 }
                              }
                              catch (Exception e)
                              {
                                 mailAlertMsg.append("OnDemand price data upload issue " + secMaster.getTicker() + "\n" + e.getMessage() + "\n");
                              }
                           }
                        }
                        catch (Exception e)
                        {
                           System.out.println("Initial process api Exception for ticker" + secMaster.getTicker() + "\n" + e.getMessage());
                           mailAlertMsg.append("Initial process api Exception for ticker" + secMaster.getTicker() + "\n");
                        }
                     }
                  }
                  catch (Exception e)
                  {
                     System.out.println("Ticker Exception:" + secMaster.getTicker());
                     mailAlertMsg.append("Ticker Exception:" + secMaster.getTicker() + "\n");
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println(" Exception in getting ticker from secmaster table:" + ticker);
               mailAlertMsg.append("Exception in getting ticker from secmaster table:" + ticker + "\n");
            }
         }
      }
      catch (SQLException e)
      {
         mailAlertMsg.append("Exception in getting operation name in Initial Process" + e.getMessage());
         e.printStackTrace();

      }

   }

   public void rbsaCall(String ticker) throws Exception
   {

      RBSAData rbsaData;
      RBSA2 rp = new RBSA2();
      rbsaData = rp.optimizeSecurity(ticker);
      Double val = 0.0;
      Double totalAlloc = 0.0;
      if (rbsaData != null)
      {
         for (String key : rbsaData.getSolution().keySet())
         {
            val = (Math.round(rbsaData.getSolution().get(key) * 10000.0) / 100.0);
            totalAlloc = totalAlloc + val;
            System.out.println("Index (" + key + "): " + rbsaData.getSolution().get(key) + "(" + val + "%)");
         }
         System.out.println("Total Allocated: " + totalAlloc);
         System.out.println("Tracking Error: " + (rbsaData.getTrackingError() * 100.00) + "%");
      }
   }


//	public void onDemand(String ticker) throws Exception {
//
//		SecMaster secMaster=new SecMaster();
//		if(ticker.equals(secMaster.getTicker())){
//			System.out.println("TICKER ALREADY PRESENT"+ secMaster.getTicker());
//			StringBuilder mailAlertMsg= null;
//			mailAlertMsg.append("TICKER ALREADY PRESENT"+ secMaster.getTicker());
//
//		}
//		else{
//			System.out.println("***********"+price_provider+"********");
//			if(price_provider.equalsIgnoreCase("yahoo")){
//
//				try {
//
//					List<PriceData> pdl = new ArrayList<PriceData>();
//					Stock stk = YahooFinance.get(ticker);
//					System.out.println("*********************Daily Data************************");
//					System.out.println("Ticker :" + stk.getQuote().getSymbol());
//					System.out.println("LastTradeDate : " + sdf.format(stk.getQuote().getLastTradeTime().getTime()));
//					System.out.println("Open :" + stk.getQuote().getOpen());
//					System.out.println("LastTradePriceOnly :" +
//												 stk.getQuote().getPrice());
//					System.out.println("Volume :" + stk.getQuote().getVolume());
//					System.out.println("DayHigh :" + stk.getQuote().getDayHigh());
//					System.out.println("DayLow :" + stk.getQuote().getDayLow());
//					System.out.println("PreviousClose :" + stk.getQuote().getPreviousClose());
//					PriceData pd = new PriceData(stk.getQuote().getSymbol(),
//														  sdf.format(stk.getQuote().getLastTradeTime().getTime()),
//														  Double.valueOf("" + stk.getQuote().getOpen()), Double.valueOf("" + stk.getQuote().getPrice()),
//														  Double.valueOf("" + stk.getQuote().getDayHigh()), Double.valueOf("" + stk.getQuote().getDayLow()),
//														  Long.valueOf(stk.getQuote().getVolume()), new Date(),
//														  Double.valueOf("" + stk.getQuote().getPreviousClose()), new Long(2), new Date());
//
//					//pdl.add(pd);
//					Date d = new Date();
//					Calendar from = new GregorianCalendar(2015, 9, 5);// Calendar.getInstance();
//					Calendar to = new GregorianCalendar(2016, 1, 29);// Calendar.getInstance();//2007-05-30
//					from.add(Calendar.YEAR, -20); // from 5 years ago
//					List<HistoricalQuote> hstLst = stk.getHistory(from, to, Interval.DAILY);
//
//					Iterator<HistoricalQuote> itr = hstLst.iterator();
//					System.out.println("*********************Historical Data************************");
//
//					while (itr.hasNext()) {
//
//						HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
//						System.out.println("Ticker :" + historicalQuote.getSymbol());
//						System.out.println("LastTradeDate : "+sdf.format(historicalQuote.getDate().getTime()));
//						System.out.println("Open :" + historicalQuote.getOpen());
//						System.out.println("LastTradePriceOnly :" +historicalQuote.getClose());
//						System.out.println("Volume :" + historicalQuote.getVolume());
//						System.out.println("DayHigh :" + historicalQuote.getHigh());
//						System.out.println("DayLow :" + historicalQuote.getLow());
//						System.out.println("PreviousClose :" +
//													 historicalQuote.getAdjClose());
//
//						PriceData hpd = new PriceData(historicalQuote.getSymbol(),
//																sdf.format(historicalQuote.getDate().getTime()),
//																Double.valueOf("" + historicalQuote.getOpen()), Double.valueOf("" + historicalQuote.getClose()),
//																Double.valueOf("" + historicalQuote.getHigh()), Double.valueOf("" + historicalQuote.getLow()),
//																Long.valueOf(historicalQuote.getVolume()), new Date(),
//																Double.valueOf("" + historicalQuote.getAdjClose()), new Long(2), new Date());
//						if(!Double.valueOf(""+historicalQuote.getClose()).equals(0)){
//							pdl.add(hpd);
//						}
//
//						priceDataDao.insertBatch(pdl);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			else if(price_provider.equalsIgnoreCase("xignite")){
//
//
//			}else{
//
//			}
//		}
//
//	}

   /*public void exceptionHandler(Exception ex, StringBuilder mailAlertMsg, String process) {
      try {
         System.out.println("EXCEPTION CLASS:" + ex.getClass());
         ex.printStackTrace();
         if (ex instanceof MySQLIntegrityConstraintViolationException) {
            //mailAlertMsg.append(process + "MySQLViolationException: " + ex.getMessage() + "\n");
            System.out.println(process + " MySQLViolationException: " + ex.getMessage());
         } else if (ex instanceof BadSqlGrammarException) {
            //mailAlertMsg.append(process + "SqlGrammarException : " + ex.getMessage() + "\n");
            System.out.println(process + "SqlGrammarException : " + ex.getMessage());
         } else if (ex instanceof CannotGetJdbcConnectionException) {
            //mailAlertMsg.append(process + "JDBC ConnectionException : " + ex.getMessage() + "\n");
            System.out.println(process + "ConnectionException : " + ex.getMessage());
         } else if (ex instanceof DuplicateKeyException) {
            //mailAlertMsg.append(process + "DUPLICATE KEY : " + ex.getMessage() + "\n");
            System.out.println(process + "DUPLICATE KEY : " + ex.getMessage());
         }
         else if (ex instanceof DataIntegrityViolationException) {
            //mailAlertMsg.append(process + "DATA TRUNCATION : " + ex.getMessage() + "\n");
            System.out.println(process + "DATA TRUNCATION : " + ex.getMessage());
         }
         else if (ex instanceof NullPointerException) {
            //mailAlertMsg.append(process +  "NULL POINTER EXCEPTION:" + ex.getMessage() + "\n");
            System.out.println(process + "NULL POINTER EXCEPTION:" + ex.getMessage());
         }
         else if (ex instanceof SQLException) {
            //mailAlertMsg.append(process +  "sql exception:"  + ex.getMessage() + "\n");
            System.out.println(process +    "sql exception:"  + ex.getMessage());
         }
         else if (ex instanceof InvalidDataAccessApiUsageException) {
            //mailAlertMsg.append(process +  "Required input parameter  is missing:"  + ex.getMessage() + "\n");
            System.out.println(process +    "Required input parameter  is missing:"  + ex.getMessage());
         }
         else {
            //mailAlertMsg.append(process + "NO Exception:" + ex.getMessage() + "\n");
            System.out.println(process + " : " + ex.getMessage());
         }



      } catch (Exception e) {
         //mailAlertMsg.append(process + " QAZ: " + ex.getMessage() + "\n");
         System.out.println(process + " QAZ: " + ex.getMessage());

      }
   }
*/
   public PriceDataDao getPriceDataDao()
   {
      return priceDataDao;
   }

   public void setPriceDataDao(PriceDataDao priceDataDao)
   {
      this.priceDataDao = priceDataDao;
   }

   public SecMasterDao getSecMasterDao()
   {
      return secMasterDao;
   }

   public void setSecMasterDao(SecMasterDao secMasterDao)
   {
      this.secMasterDao = secMasterDao;
   }

   public DBParametersDao getDbParametersDao()
   {
      return dbParametersDao;
   }

   public void setDbParametersDao(DBParametersDao dbParametersDao)
   {
      this.dbParametersDao = dbParametersDao;
   }

//   public MessageDao getMessageDao()
//   {
//      return messageDao;
//   }
//
//   public void setMessageDao(MessageDao messageDao)
//   {
//      this.messageDao = messageDao;
//   }

}
