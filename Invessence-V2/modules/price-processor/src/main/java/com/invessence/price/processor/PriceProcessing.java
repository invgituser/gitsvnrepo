package com.invessence.price.processor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.service.bean.ServiceRequest;
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

/**
 * Created by sagar on 2/20/2017.
 */
public class PriceProcessing
{
   private static final Logger logger = Logger.getLogger(PriceProcessing.class);
   @Autowired
   DBParametersDao dbParametersDao;
   @Autowired
   SecMasterDao secMasterDao;
   @Autowired
   PriceDataDao priceDataDao;
   @Autowired
   PriceService priceService;

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat switchFormat = new SimpleDateFormat("yyyyMMdd");

   // This method fetch Security details along with source information, depends on type it gets daily or historical data
   public void process(String company, String mode, StringBuilder mailFailureAlertMsg, StringBuilder mailWaringAlertMsg,String ondemandrqd) throws SQLException
   {

      ServiceRequest serviceRequest = new ServiceRequest(company, mode);
      logger.info("PriceProcessing.process Start company:" + company + " mode:" + mode);
      String companyName = company;
      logger.info("PriceProcessing.process Company Name " + companyName);
      List<APIDetails> apidetails = null;
      Map<String, DBParameters> dbParamMap = null;
      List<SecMaster> secLst = null;
      String businessDate = null,prev_businessDate=null;
      try
      {
         // code to get values for businessdate,last businessdate of month,price date from invessence_switch table
         dbParamMap = dbParametersDao.getDBParametres();
         if (dbParamMap == null && dbParamMap.size() == 0)
         {
            mailFailureAlertMsg.append("DB parameters are not available \n");
            logger.info("DB parameters are not available");
         }
         else
         {
            logger.info("PriceProcessing.process LAST_BDATE_OF_MONTH :" + dbParamMap.get("LAST_BDATE_OF_MONTH").getValue());
            // code to get the list of tickers from  sec_master table whose status=A

            secLst = secMasterDao.getTicker();
            if (secLst != null && secLst.size() > 0)
            {

               businessDate = sdf.format(switchFormat.parse(dbParamMap.get("BUSINESS_DATE").getValue().toString()));
               logger.info("PriceProcessing.process Business Date :" + businessDate);
               prev_businessDate=dbParametersDao.getPrevBusinessDate(businessDate).get("PREV_BUSINESSDATE").getValue().toString();
               logger.info("PriceProcessing.process Previous Business Date :" + prev_businessDate);
               logger.info("PriceProcessing.process Business Date :" + dbParamMap.get("BUSINESS_DATE").getValue().toString() + " Last BuiDate: " + dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString());
               // code to check for dailyProcess or monthlyProcess
               if (CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(), dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == false)
               {
                  logger.info("PriceProcessing.process DAILY_PRICING");
                  apidetails = secMasterDao.getSwitch(companyName, "DAILY_PRICING");
                  dailyProcess(apidetails, businessDate, secLst, mailFailureAlertMsg, serviceRequest, mailWaringAlertMsg,prev_businessDate,ondemandrqd);
               }
               // code to check for dailyProcess or monthlyProcess
               else if (CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(), dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString()) == true)
               {
                  logger.info("PriceProcessing.process MONTHLY_PRICING ");
                  apidetails = secMasterDao.getSwitch(companyName, "MONTHLY_PRICING");
                  monthlyProcess(apidetails, businessDate, secLst, mailFailureAlertMsg, serviceRequest, mailWaringAlertMsg);
               }
            }
            else
            {
               mailFailureAlertMsg.append("Ticker list not available for process \n");
               logger.info("Ticker list not available for process");
            }
         }

         logger.info("PriceProcessing.process End");
      }
      catch (Exception e)
      {
         System.out.println("PriceProcessor.process() WE R HERE..");
         logger.info("PriceProcessing.process Exception " + e);
         e.printStackTrace();
      }

      finally
      {
         try
         {
            companyName = null;
            apidetails = null;
            dbParamMap = null;
            secLst = null;
            businessDate = null;
         }
         catch (Exception e)
         {

         }

      }

   }
    /*
    * In this method we are performing daily process
    */

   public void dailyProcess(List<APIDetails> apidetails, String businessDate, List<SecMaster> secLst, StringBuilder mailFailureAlertMsg, ServiceRequest serviceRequest, StringBuilder mailWaringAlertMsg,String prev_businessDate,String ondemandrqd)
   {
      List<PriceData> pdList = null;
      HashMap<String, Object> objPriceData = null;
      String dailyReturnAlertFlag = "";
      try
      {
         logger.info("PriceProcessor.dailyProcess() Daily Process execution Start");
         for (int i1 = 0; i1 < secLst.size(); i1++)
         {
            if (secLst.get(i1).getTickerSource() != null && !secLst.get(i1).getTickerSource().isEmpty())
            {
               //deleting all rows from tmp_rbsa_daily table

               logger.info("PriceProcessor.dailyProcess() OnDemand processing for Ticker: " + secLst.get(i1).getTicker_source_name() + " required flag " + secLst.get(i1).getOnDemand()
                              +" Previous Businessdate condition available "+(secLst.get(i1).getOnDemand().equalsIgnoreCase("YES") || (!secLst.get(i1).getBusinessdate().trim().equalsIgnoreCase(prev_businessDate.trim())
                  && !secLst.get(i1).getBusinessdate().trim().equalsIgnoreCase(businessDate.trim())))+" Forceful On Demand "+ondemandrqd);
               if (secLst.get(i1).getOnDemand().equalsIgnoreCase("YES") || (!secLst.get(i1).getBusinessdate().trim().equalsIgnoreCase(prev_businessDate.trim())
                  && !secLst.get(i1).getBusinessdate().trim().equalsIgnoreCase(businessDate.trim())) || ondemandrqd.equalsIgnoreCase("ONDEMAND_YES"))
               {
                  logger.info("PriceProcessor.dailyProcess() OnDemand processing for Ticker: "+ secLst.get(i1).getTicker_source_name()+" initiated " );
                  onDemandProcessing(apidetails, businessDate, secLst.get(i1), mailFailureAlertMsg, serviceRequest, mailWaringAlertMsg);
               }
               else
               {
                  priceDataDao.delete();
                  objPriceData = priceService.getPrice(apidetails, PriceProcessConst.DAILY, businessDate, secLst.get(i1).getTicker_source_name(), secLst.get(i1).getTickerSource(), serviceRequest);

                  if (objPriceData.get("status").toString().equalsIgnoreCase("failure"))
                  {
                     logger.info("PriceProcessor.dailyProcess() Daily Process Price Value Missing for Ticker:" + secLst.get(i1).getTicker_source_name() + ",generated holiday data");
//            Call holiday data generation Procedure
                     priceDataDao.GetDailyMissingData(businessDate, secLst.get(i1).getTicker_source_name());
                     mailWaringAlertMsg.append("Daily Process Price Value Missing for Ticker:" + secLst.get(i1).getTicker_source_name() + ",generated holiday data\n");
                     if (secLst.get(i1).getExchange_required().equalsIgnoreCase("Y"))
                     {
                        priceDataDao.GetExchangePriceData(secLst.get(i1).getTicker_source_name(),secLst.get(i1).getDest_currency());
                     }
                     dailyReturnAlertFlag = priceDataDao.callProcedure(PriceProcessConst.DAILY, businessDate, secLst.get(i1).getTicker_source_name(),secLst.get(i1).getDest_currency());
                  }
                  else
                  {
                     logger.info("PriceProcessor.dailyProcess() getting Price Value for Ticker:" + secLst.get(i1).getTicker_source_name());
                     pdList = (List<PriceData>) objPriceData.get("priceData");

                     if (!pdList.get(0).getBusinessDate().equals(businessDate))
                     {
                        if (pdList.get(0).getClosePrice() == null || pdList.get(0).getClosePrice().equals(0.0) || pdList.get(0).getClosePrice().equals(""))
                        {
                           mailWaringAlertMsg.append("Price value getting 0 for ticker:" + secLst.get(i1).getTicker_source_name() + "\n");
                        }
                     }
                     try
                     {
                        priceDataDao.insertBatch(pdList, secLst.get(i1).getDest_currency());
                        if (secLst.get(i1).getExchange_required().equalsIgnoreCase("Y"))
                        {
                           priceDataDao.GetExchangePriceData(secLst.get(i1).getTicker_source_name(),secLst.get(i1).getDest_currency());
                        }
                        dailyReturnAlertFlag = priceDataDao.callProcedure(PriceProcessConst.DAILY, businessDate, secLst.get(i1).getTicker_source_name(),secLst.get(i1).getDest_currency());
                     }
                     catch (Exception e)
                     {
                        mailFailureAlertMsg.append("Daily Process Error while tmp_rbsa_daily to rbsa_daily transfer for Ticker:" + secLst.get(i1).getTicker_source_name() + " \n");
                        logger.error("PriceProcessor.dailyProcess() Daily Process Error while tmp_rbsa_daily to rbsa_daily transfer " + e.getMessage());
                        e.printStackTrace();
                     }
                     if (dailyReturnAlertFlag != null && dailyReturnAlertFlag != "" && dailyReturnAlertFlag.equalsIgnoreCase("send alert"))
                     {
                        mailWaringAlertMsg.append("Daily Process daliy return is higher than setted thresold limit for Ticker:" + secLst.get(i1).getTicker_source_name() + " \n");
                        logger.error("PriceProcessor.dailyProcess() Daily Process daliy return is higher than setted thresold limit for" + secLst.get(i1).getTicker_source_name());
                     }
                  }
                  objPriceData = null;
               }
            }
            else
            {
               mailFailureAlertMsg.append("Source Details are not available for Ticker " + secLst.get(i1).getTicker_source_name() + " \n");
               logger.info("PriceProcessor.dailyProcess() Source Details are not available for Ticker " + secLst.get(i1).getTicker_source_name());
            }
         }
         // List is inserted in tmp_rbsa_daily table
         if (mailFailureAlertMsg.length() == 0)
         {
            // code to call daily_price_processor procedure(in this we are calculating daily return and inserting values in rbsa_daily table)
            try
            {
               //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table and invdb.inv_date_table)
               priceDataDao.callEodProcedure(PriceProcessConst.DAILY, businessDate);
            }
            catch (Exception e)
            {
               mailFailureAlertMsg.append("Daily EOD procedure calling issue \n");
               logger.error("PriceProcessor.dailyProcess() EOD process issue " + e.getMessage());
               e.printStackTrace();
            }
         }
         else
         {
            mailFailureAlertMsg.append("EOD procedure not called due to occured issues \n");
            logger.error("PriceProcessor.dailyProcess() EOD procedure not called due to occured issues");
         }
         logger.info("PriceProcessor.dailyProcess() Daily Process execution End");
      }
      catch (Exception e)
      {
         mailFailureAlertMsg.append("Daily process exception \n");
         logger.error("PriceProcessor.dailyProcess() Daily Process exception" + e);
         e.printStackTrace();
      }
   }

   //In this method we are performing monthly process

   public void monthlyProcess(List<APIDetails> apidetails, String businessdate, List<SecMaster> tickerList, StringBuilder mailFailureAlertMsg, ServiceRequest serviceRequest, StringBuilder mailWaringAlertMsg)
   {
      List<PriceData> pdList = null;
      HashMap<String, Object> objPriceData = null;
      Iterator<SecMaster> sec = tickerList.iterator();
      boolean isPriceAvaiForBusiDate = false;
      SecMaster secMaster = null;
      try
      {
         logger.info("PriceProcessor.monthlyProcess() Daily Process execution Start");
         while (sec.hasNext())
         {
            secMaster = (SecMaster) sec.next();
            if (secMaster.getTickerSource() != null && !secMaster.getTickerSource().isEmpty())
            {
               logger.info("PriceProcessor.monthlyProcess() getting Price Value for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
               objPriceData = priceService.getPrice(apidetails, PriceProcessConst.MONTHLY, businessdate, secMaster.getTicker_source_name(), secMaster.getTickerSource(), serviceRequest);
               if (objPriceData.get("status").toString().equalsIgnoreCase("failure"))
               {
                  mailFailureAlertMsg.append("History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + " API\n");
                  logger.info("PriceProcessor.monthlyProcess() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + " API");
               }
               else
               {
                  logger.info("PriceProcessor.monthlyProcess() History Price Values are for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                  //deleting all rows from tmp_rbsa_daily table
                  priceDataDao.delete();
//            Getting History Price Data
                  pdList = (List<PriceData>) objPriceData.get("priceData");

                  if (pdList == null || pdList.size() == 0)
                  {
                     mailFailureAlertMsg.append("History Price Values are unavailable for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
                     logger.info("PriceProcessor.monthlyProcess() History Price Values are unavailable for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                  }
                  else
                  {
                     forloop:
                     for (int i = 0; i < pdList.size(); i++)
                     {
                        if (isPriceAvaiForBusiDate == false)
                        {
                           if (pdList.get(i).getBusinessDate().equals(businessdate))
                           {
                              isPriceAvaiForBusiDate = true;
                              break forloop;
                           }
                        }

                        if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                        {
                           logger.info("PriceProcessor.monthlyProcess() Price value getting 0 for ticker:" + secMaster.getTicker_source_name());
                           mailWaringAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker_source_name() + "\n");
                        }
                     }
                     if (isPriceAvaiForBusiDate == false)
                     {
                        try
                        {
                           priceDataDao.GetDailyMissingData(businessdate, secMaster.getTicker_source_name());
                           mailWaringAlertMsg.append("Price not available for ticker:" + secMaster.getTicker_source_name() + " for businessdate :" + businessdate + "\n");
                           logger.info("PriceProcessor.monthlyProcess() Price not available for ticker:" + secMaster.getTicker_source_name() + " for businessdate :" + businessdate);
                        }
                        catch (Exception e)
                        {
                           logger.error("PriceProcessor.monthlyProcess()  Error  " + e.getMessage());
                           e.printStackTrace();
                        }
                     }

                     try
                     {
                        priceDataDao.insertBatch(pdList, secMaster.getDest_currency());
                        priceDataDao.callHolidayProcedure(pdList.get((pdList.size() - 1)).getBusinessDate(), businessdate);
                        if (secMaster.getExchange_required().equalsIgnoreCase("Y"))
                        {
                           priceDataDao.GetExchangePriceData(secMaster.getTicker_source_name(),secMaster.getDest_currency());
                        }
                        priceDataDao.callProcedure(PriceProcessConst.MONTHLY, businessdate, secMaster.getTicker_source_name(),secMaster.getDest_currency());
                     }
                     catch (Exception e)
                     {
                        mailFailureAlertMsg.append("Error while db activity for Ticker:" + secMaster.getTicker_source_name() + "\n");
                        logger.error("PriceProcessor.monthlyProcess() Error while db ativity for Ticker:" + secMaster.getTicker_source_name() + " Message" + e.getMessage());
                        e.printStackTrace();
                     }
                     if (secMaster.getRbsaFlag() != null && secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
                     {
                        try
                        {
                           rbsaCall(secMaster.getTicker_source_name());
                        }
                        catch (Exception e)
                        {
                           mailFailureAlertMsg.append("Historical RBSA process call issue for ticker " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                           logger.error("PriceProcessor.monthlyProcess() Error while db ativity RBSA process call issue for ticker " + secMaster.getTicker_source_name() + "\n" + e);
                           e.printStackTrace();
                        }
                     }
                  }
               }
            }
            else
            {
               mailFailureAlertMsg.append("Source Details are not available for Ticker " + secMaster.getTicker_source_name() + " \n");
               logger.info("PriceProcessor.dailyProcess() Source Details are not available for Ticker " + secMaster.getTicker_source_name());
            }
            secMaster = null;
         }

         if (mailFailureAlertMsg.length() == 0)
         {
            // code to call daily_price_processor procedure(in this we are calculating daily return and inserting values in rbsa_daily table)
            try
            {
               //code to call end_of_price_process procedure(in this we are updating sec_daily_info table,invessence_switch table and invdb.inv_date_table)
               priceDataDao.callEodProcedure(PriceProcessConst.MONTHLY, businessdate);
            }
            catch (Exception e)
            {
               mailFailureAlertMsg.append("Historcial EOD process issue " + e.getMessage() + "\n");
               logger.error("PriceProcessor.monthlyProcess() Historcial EOD process issue " + e.getMessage());
               e.printStackTrace();
            }
         }
         else
         {
            mailFailureAlertMsg.append("Historcial EOD procedure not called due to occured issues \n");
            logger.error("PriceProcessor.monthlyProcess() EOD procedure not called due to occured issues");
         }


         logger.info("PriceProcessor.monthlyProcess() Daily Process execution End");
      }
      catch (Exception e)
      {
         mailFailureAlertMsg.append("Historcal process exception" + e.getMessage() + "\n");
         logger.error("PriceProcessor.monthlyProcess() api exception" + e);
         e.printStackTrace();
      }
   }

   public void onDemandProcessing(List<APIDetails> apidetails, String businessDate, SecMaster secMaster, StringBuilder mailFailureAlertMsg, ServiceRequest serviceRequest, StringBuilder mailWaringAlertMsg)
   {
      List<PriceData> pdList = null;
      HashMap<String, Object> objPriceData = null;
      boolean isPriceAvaiForBusiDate = false;
      String endDate = null, startDate = null;
      try
      {
         logger.info("PriceProcessor.onDemandProcessing() OnDemand Process execution Start for " + secMaster.getTicker_source_name());

         if (secMaster.getTickerSource() != null && !secMaster.getTickerSource().isEmpty())
         {
            logger.info("PriceProcessor.onDemandProcessing() getting Price Values for Ticker: " + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
            objPriceData = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, businessDate, secMaster.getTicker_source_name(), secMaster.getTickerSource(), serviceRequest);
            if (objPriceData.get("status").toString().equalsIgnoreCase("failure"))
            {
               mailFailureAlertMsg.append("History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
               logger.info("PriceProcessor.onDemandProcessing() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
            }
            else
            {
               logger.info("PriceProcessor.onDemandProcessing() History Price Values are for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
               //deleting all rows from tmp_rbsa_daily table
               priceDataDao.delete();
//            Getting History Price Data
               pdList = (List<PriceData>) objPriceData.get("priceData");

               if (pdList == null || pdList.size() == 0)
               {
                  mailFailureAlertMsg.append("History Price Values are unavailable for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
                  logger.info("PriceProcessor.onDemandProcessing() History Price Values are unavailable for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
               }
               else
               {
                  forloop:
                  for (int i = 0; i < pdList.size(); i++)
                  {
                     if (isPriceAvaiForBusiDate == false)
                     {
                        if (pdList.get(i).getBusinessDate().equals(businessDate))
                        {
                           isPriceAvaiForBusiDate = true;
                           break forloop;
                        }
                     }
                     if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                     {
                        logger.info("PriceProcessor.onDemandProcessing() Price value getting 0 for ticker:" + secMaster.getTicker_source_name());
                        mailWaringAlertMsg.append("Price value getting 0 for ticker:" + secMaster.getTicker_source_name() + "\n");
                     }
                  }
                  if (isPriceAvaiForBusiDate == false)
                  {
                     try
                     {
                        priceDataDao.GetDailyMissingData(businessDate, secMaster.getTicker_source_name());
                        mailWaringAlertMsg.append("Price not available for ticker:" + secMaster.getTicker_source_name() + " for businessdate :" + businessDate + ",generated holiday data\n");
                        logger.info("PriceProcessor.onDemandProcessing() Price not available for ticker:" + secMaster.getTicker_source_name() + " for businessdate :" + businessDate + ",generated holiday data\n");
                     }
                     catch (Exception e)
                     {
                        logger.error("PriceProcessor.onDemandProcessing()  Error  " + e.getMessage());
                        e.printStackTrace();
                     }
                  }
                  try
                  {
                     priceDataDao.insertBatch(pdList, secMaster.getDest_currency());
                     startDate = pdList.get((pdList.size() - 1)).getBusinessDate();
                     endDate = pdList.get(0).getBusinessDate();
                     priceDataDao.callHolidayProcedure(startDate, businessDate);
                     if (secMaster.getExchange_required().equalsIgnoreCase("Y"))
                     {
                        priceDataDao.GetExchangePriceData(secMaster.getTicker_source_name(),secMaster.getDest_currency());
                     }
                     priceDataDao.callProcedure(PriceProcessConst.MONTHLY, businessDate, secMaster.getTicker_source_name(),secMaster.getDest_currency());
                  }
                  catch (Exception e)
                  {
                     mailFailureAlertMsg.append("Error while Historcal data insertion db activity " + e.getMessage() + " \n");
                     logger.error("PriceProcessor.onDemandProcessing()  Error while data insertion db activity " + e.getMessage());
                     e.printStackTrace();
                  }
                  if (secMaster.getRbsaFlag() != null && secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
                  {
                     try
                     {
                        rbsaCall(secMaster.getTicker_source_name());
                     }
                     catch (Exception e)
                     {
                        mailFailureAlertMsg.append("Ondemand RBSA process call issue for ticker " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                        logger.error("PriceProcessor.onDemandProcessing() Error while RBSA process call for ticker " + secMaster.getTicker_source_name() + "\n" + e);
                        e.printStackTrace();
                     }
                  }
               }
            }
         }
         logger.info("PriceProcessor.onDemandProcessing() OnDemand Process execution End for " + secMaster.getTicker_source_name());
      }
      catch (Exception e)
      {
         mailFailureAlertMsg.append("Ondemand Process exception" + e.getMessage() + "\n");
         logger.error("PriceProcessor.onDemandProcessing() api exception" + e);
         e.printStackTrace();
      }
   }

   // In this method we are performing onDemandProcess
   public void onDemandProcess(String ticker, ServiceRequest serviceRequest)
   {
      System.out.println("PriceProcessor.process() executing OnDemand Process");
      String companyName = ServiceParameters.COMPANY_NAME;
      System.out.println("companyName = " + companyName);
      StringBuilder mailAlertMsg = null;
      List<APIDetails> apidetails = null;
      HashMap<String, Object> objPriceData = null;
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
                           logger.info("PriceProcessor.onDemandProcess() getting Price Value for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                           objPriceData = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker_source_name(), secMaster.getTickerSource(), serviceRequest);
                           if (objPriceData.get("status").toString().equalsIgnoreCase("failure"))
                           {
                              mailAlertMsg.append("PriceProcessor.onDemandProcess() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
                              logger.info("PriceProcessor.onDemandProcess() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                           }
                           else
                           {
                              logger.info("PriceProcessor.onDemandProcess() History Price Values are for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                              //deleting all rows from tmp_rbsa_daily table
                              priceDataDao.delete();
                              pdList = (List<PriceData>) objPriceData.get("priceData");
                              if (pdList == null || pdList.size() == 0 || pdList.equals(""))
                              {
                                 mailAlertMsg.append("PriceProcessor.onDemandProcess() price data not available for ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
                              }
                              else
                              {
                                 for (int i = 0; i < pdList.size(); i++)
                                 {
                                    if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                                    {
                                       mailAlertMsg.append("PriceProcessor.onDemandProcess() Price value getting 0 for ticker:" + secMaster.getTicker_source_name() + "\n");
                                    }
                                 }
                                 try
                                 {
                                    // List is inserted in tmp_rbsa_daily table
                                    priceDataDao.insertBatch(pdList, secMaster.getDest_currency());

                                    priceDataDao.callHolidayProcedure(pdList.get(0).getBusinessDate(), priceDate);
                                    try
                                    {
                                       //code to call monthly_price_processor procedure(in this we are calculating daily return,monthly return and inserting values in rbsa_daily table)
                                       priceDataDao.callProcedure(PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker_source_name(),secMaster.getDest_currency());
                                       if (secMaster.getRbsaFlag() != null && secMaster.getRbsaFlag().equalsIgnoreCase("Y"))
                                       {
                                          try
                                          {
                                             rbsaCall(secMaster.getTicker_source_name());
                                          }
                                          catch (Exception e)
                                          {
                                             mailAlertMsg.append("PriceProcessor.onDemandProcess() RBSA process call issue for ticker " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
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
                                          mailAlertMsg.append("PriceProcessor.onDemandProcess() OnDemad price eod process issue  " + e.getMessage());
                                       }

                                    }
                                    catch (Exception e)
                                    {
                                       e.printStackTrace();
                                       mailAlertMsg.append("PriceProcessor.onDemandProcess() OnDemand price data operation issue for ticker " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                                    }
                                 }
                                 catch (Exception e)
                                 {
                                    mailAlertMsg.append("PriceProcessor.onDemandProcess() OnDemand price data upload issue " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                                 }
                              }
                           }
                        }
                        catch (Exception e)
                        {
                           System.out.println("PriceProcessor.onDemandProcess() OnDemand api Exception for ticker" + secMaster.getTicker_source_name() + "\n" + e.getMessage());
                           mailAlertMsg.append("PriceProcessor.onDemandProcess() OnDemand api Exception for ticker" + secMaster.getTicker_source_name() + "\n");
                        }
                     }
                  }
                  catch (Exception e)
                  {
                     System.out.println("PriceProcessor.onDemandProcess() Ticker Exception:" + secMaster.getTicker_source_name());
                     mailAlertMsg.append("PriceProcessor.onDemandProcess() Ticker Exception:" + secMaster.getTicker_source_name() + "\n");
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println("PriceProcessor.onDemandProcess() Exception in getting ticker from secmaster table:" + ticker);
               mailAlertMsg.append("PriceProcessor.onDemandProcess() Exception in getting ticker from secmaster table:" + ticker + "\n");
            }
         }
      }
      catch (SQLException e)
      {
         mailAlertMsg.append("PriceProcessor.onDemandProcess() Exception in getting operation name in OnDemand Process" + e.getMessage());
         e.printStackTrace();
      }

   }

   public void initialProcess(String ticker, ServiceRequest serviceRequest)
   {
      System.out.println("PriceProcessor.process() executing Initial Process");
      String companyName = ServiceParameters.COMPANY_NAME;
      System.out.println("companyName = " + companyName);
      StringBuilder mailAlertMsg = null;
      List<APIDetails> apidetails = null;
      HashMap<String, Object> objPriceData = null;
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
                           logger.info("PriceProcessor.onDemandProcess() getting Price Value for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                           objPriceData = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker_source_name(), secMaster.getTickerSource(), serviceRequest);
                           if (objPriceData.get("status").toString().equalsIgnoreCase("failure"))
                           {
                              mailAlertMsg.append("PriceProcessor.initialProcess() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");
                              logger.info("PriceProcessor.initialProcess() getting History Price Values fails for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                           }
                           else
                           {
                              logger.info("PriceProcessor.initialProcess() History Price Values are for Ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource());
                              //deleting all rows from tmp_rbsa_daily table
                              priceDataDao.delete();
                              pdList = (List<PriceData>) objPriceData.get("priceData");
                              if (pdList == null || pdList.size() == 0 || pdList.equals(""))
                              {
//                                 mailAlertMsg.append("SPY price data not available  ");
                                 mailAlertMsg.append("PriceProcessor.initialProcess() price data not available for ticker:" + secMaster.getTicker_source_name() + " Using " + secMaster.getTickerSource() + "\n");

                              }
                              else
                              {
                                 for (int i = 0; i < pdList.size(); i++)
                                 {
                                    if (pdList.get(i).getClosePrice() == null || pdList.get(i).getClosePrice().equals(0.0) || pdList.get(i).getClosePrice().equals(""))
                                    {
                                       mailAlertMsg.append("PriceProcessor.initialProcess() Price value getting 0 for ticker:" + secMaster.getTicker_source_name() + "\n");
                                    }
                                 }
                                 try
                                 {
                                    // List is inserted in tmp_rbsa_daily table
                                    priceDataDao.insertBatch(pdList, secMaster.getDest_currency());
                                    priceDataDao.callHolidayProcedure(pdList.get((pdList.size() - 1)).getBusinessDate(), priceDate);
                                    try
                                    {
                                       //code to call monthly_price_processor procedure(in this we are calculating daily return,monthly return and inserting values in rbsa_daily table)
                                       priceDataDao.callProcedure(PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker_source_name(),secMaster.getDest_currency());


                                    }
                                    catch (Exception e)
                                    {
                                       e.printStackTrace();
                                       mailAlertMsg.append("PriceProcessor.initialProcess() price data operation issue for ticker " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                                    }
                                 }
                                 catch (Exception e)
                                 {
                                    mailAlertMsg.append("PriceProcessor.initialProcess() price data upload issue " + secMaster.getTicker_source_name() + "\n" + e.getMessage() + "\n");
                                 }
                              }
                           }

//                           priceDataDao.delete();
//                           pdList = priceService.getPrice(apidetails, PriceProcessConst.ONDEMAND, priceDate, secMaster.getTicker());

                        }
                        catch (Exception e)
                        {
                           System.out.println("PriceProcessor.initialProcess() api Exception for ticker" + secMaster.getTicker_source_name() + "\n" + e.getMessage());
                           mailAlertMsg.append("PriceProcessor.initialProcess() api Exception for ticker" + secMaster.getTicker_source_name() + "\n");
                        }
                     }
                  }
                  catch (Exception e)
                  {
                     System.out.println("PriceProcessor.initialProcess()  Ticker Exception:" + secMaster.getTicker_source_name());
                     mailAlertMsg.append("PriceProcessor.initialProcess() Ticker Exception:" + secMaster.getTicker_source_name() + "\n");
                  }
               }
            }
            catch (Exception e)
            {
               System.out.println("PriceProcessor.initialProcess() Exception in getting ticker from secmaster table:" + ticker);
               mailAlertMsg.append("PriceProcessor.initialProcess() Exception in getting ticker from secmaster table:" + ticker + "\n");
            }
         }
      }
      catch (SQLException e)
      {
         mailAlertMsg.append("PriceProcessor.initialProcess() Exception in getting operation name in Initial Process" + e.getMessage());
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

}

