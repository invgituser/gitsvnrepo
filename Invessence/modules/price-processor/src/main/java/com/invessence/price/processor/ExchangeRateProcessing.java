package com.invessence.price.processor;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.invessence.emailer.util.EmailCreator;
import com.invessence.price.FIS.bean.*;
import com.invessence.price.FIS.dao.*;
import com.invessence.price.processor.Service.CallingExchangeService;
import com.invessence.price.processor.bean.DBParameters;
import com.invessence.price.processor.dao.*;
import com.invessence.service.bean.ServiceRequest;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sagar on 4/10/2017.
 */

@Component
public class ExchangeRateProcessing
{
   private static final Logger logger = Logger.getLogger(PriceProcessing.class);
   @Autowired
   DBParametersDao dbParametersDao;
   @Autowired
   SecExchangeDao secExchangeMaster;
   @Autowired
   CallingExchangeService objExchangeService;

   List<SecExchangeMaster> symLst = null;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat switchFormat = new SimpleDateFormat("yyyyMMdd");

   public boolean process(String company, String mode, StringBuilder mailFailureAlertMsg, StringBuilder mailWaringAlertMsg) throws SQLException
   {
      boolean bFlag = true;
      String URL = null;
      String exchangeDate = null;
      Map<String, DBParameters> dbParamMap = null;
      DailyRates dailyExchangeData = null;
      ServiceRequest serviceRequest = null;
      List<HistoricalDataRates> historicalExchangeData = null;
      HistoricalData objDailyData = null;
      try
      {
         serviceRequest = new ServiceRequest(company, mode);
         logger.info(" ExchangeRateProcessing process Start");
         dbParamMap = dbParametersDao.getDBParametres();
         if (dbParamMap == null && dbParamMap.size() == 0)
         {
            mailFailureAlertMsg.append("ExchangeRateProcessing.process() DB parameters are not available \n");
            logger.error("ExchangeRateProcessing.process() DB parameters are not available");
            bFlag = false;
         }
         else
         {
            exchangeDate = sdf.format(switchFormat.parse(dbParamMap.get("BUSINESS_DATE").getValue().toString()));
            logger.info("ExchangeRateProcessing.process()  exchangeDate : " + exchangeDate);
            symLst = secExchangeMaster.getSymbol();

            if (symLst != null && symLst.size() > 0)
            {

               logger.info(" ExchangeRateProcessing.process() currency symbol list size " + symLst.size());
               for (int i = 0; i < symLst.size(); i++)
               {
                  logger.info(" ExchangeRateProcessing.process() Ondemand required " + symLst.get(i).getOnDemand().equalsIgnoreCase("Y") + " for Symbol " + symLst.get(i).getSymbol());
                  if (symLst.get(i).getOnDemand().equalsIgnoreCase("Y") || symLst.get(i).getOnDemand().equalsIgnoreCase("YES"))
                  {
                     URL = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.FIS.toString(), "HISTORY.URL");
                     logger.info(" ExchangeRateProcessing.process() Historical requesting URL " + URL);
                     historicalExchangeData = null;
                     try
                     {
                        historicalExchangeData = objExchangeService.getHistoricalExchangeRate(URL, symLst.get(i).getSymbol() + "=", exchangeDate);
                     }
                     catch (Exception e)
                     {
                        mailFailureAlertMsg.append("Historical Error while API call for " + symLst.get(i).getSymbol() + " \n");
                        logger.error("ExchangeRateProcessing.process() Historical Error while API call historical for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                        e.printStackTrace();
                     }
                     if (historicalExchangeData == null)
                     {
                        mailFailureAlertMsg.append("ExchangeRateProcessing.process() Historical  No Exchange data found for  " + symLst.get(i).getSymbol() + " \n");
                        logger.error("ExchangeRateProcessing.process() Historical No Exchange data found for  " + symLst.get(i).getSymbol() + " \n");
                     }
                     else
                     {
                        try
                        {
                           secExchangeMaster.insertBatch(historicalExchangeData, symLst.get(i).getSymbol());
                        }
                        catch (Exception e)
                        {
                           mailFailureAlertMsg.append("Historical Error while db insertion for " + symLst.get(i).getSymbol() + " \n");
                           logger.error("ExchangeRateProcessing.process() Historical Error while db insertion for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                           e.printStackTrace();
                        }
                        try
                        {
                           secExchangeMaster.callHolidayProcedure(historicalExchangeData.get(historicalExchangeData.size() - 1).getDate(), exchangeDate, symLst.get(i).getSymbol());
                          // mailWaringAlertMsg.append("Historical holiday data generated for " + symLst.get(i).getSymbol() + " \n");
                        }
                        catch (Exception e)
                        {
                           mailFailureAlertMsg.append("Historical Error while holiday data generation for " + symLst.get(i).getSymbol() + " \n");
                           logger.error("ExchangeRateProcessing.process()  Historical Error while holiday data generation for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                           e.printStackTrace();
                        }
                     }
                  }
                  else
                  {
                     URL = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.FIS.toString(), "DAILY.URL");
                     logger.info(" ExchangeRateProcessing.process() Daily Api requesting URL " + URL);

                     objDailyData = null;
                     try
                     {
                        objDailyData = objExchangeService.getDailyExchangeRate(URL, symLst.get(i).getSymbol() + "=", exchangeDate);
                     }
                     catch (Exception e)
                     {
                        mailFailureAlertMsg.append("Daily Error while API call  for " + symLst.get(i).getSymbol() + " \n");
                        logger.error("ExchangeRateProcessing.process() Daily Error while API call  for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                        e.printStackTrace();
                     }
                     if (objDailyData == null)
                     {
                        logger.error("ExchangeRateProcessing.process()  Daily  No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + " \n");
                        try
                        {
                           secExchangeMaster.delete(symLst.get(i).getSymbol(), exchangeDate);
                           secExchangeMaster.GetDailyMissingData(exchangeDate, symLst.get(i).getSymbol());
                           mailWaringAlertMsg.append("Daily No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + ",generated holiday exchange data \n");
                           logger.error("ExchangeRateProcessing.process() Daily No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + ",generated holiday exchange data \n");
                        }
                        catch (Exception e)
                        {
                           mailFailureAlertMsg.append("ExchangeRateProcessing.process() Daily Error while holiday exchange data generation for " + symLst.get(i).getSymbol() + "-" + e.getMessage() + " \n");
                           logger.error("ExchangeRateProcessing.process() Daily Error while holiday exchange data generation for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                           e.printStackTrace();
                        }
                     }
                     else
                     {
                        if(objDailyData.getHistoricalExchangeRates().getTimeSeriesPoint().get(0).getDate().equalsIgnoreCase(exchangeDate))
                        {
                           try
                           {
                              secExchangeMaster.delete(symLst.get(i).getSymbol(), exchangeDate);
                              secExchangeMaster.insert(objDailyData, symLst.get(i).getSymbol());
                           }
                           catch (Exception e)
                           {
                              mailFailureAlertMsg.append("ExchangeRateProcessing.process() Daily Error while db insertion for " + symLst.get(i).getSymbol() + "-" + e.getMessage() + " \n");
                              logger.error("ExchangeRateProcessing.process() Daily Error while db insertion daily for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                              e.printStackTrace();
                           }
                        }else{
                           logger.error("ExchangeRateProcessing.process()  Daily  No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + " \n");
                           try
                           {
                              secExchangeMaster.delete(symLst.get(i).getSymbol(), exchangeDate);
                              secExchangeMaster.GetDailyMissingData(exchangeDate, symLst.get(i).getSymbol());
                              mailWaringAlertMsg.append("Daily No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + ",generated holiday exchange data \n");
                              logger.info("ExchangeRateProcessing.process() Daily No Exchange data found for  " + symLst.get(i).getSymbol() + " exchangeDate " + exchangeDate + ",generated holiday exchange data \n");
                           }
                           catch (Exception e)
                           {
                              mailFailureAlertMsg.append("Daily Error while holiday exchange data generation for " + symLst.get(i).getSymbol() + "-" + e.getMessage() + " \n");
                              logger.error("ExchangeRateProcessing.process() Daily Error while holiday exchange data generation for " + symLst.get(i).getSymbol() + "-" + e.getMessage());
                              e.printStackTrace();
                           }
                        }
                     }
                  }
               }
            }
            else
            {
               mailFailureAlertMsg.append("Exchange symbols are not available \n");
               logger.info("ExchangeRateProcessing.process() exchange symbols are not available");
            }
            if (mailFailureAlertMsg.length() > 0)
            {
               bFlag = false;
            }
            logger.info(" ExchangeRateProcessing.process() return flag " + bFlag);
            logger.info(" ExchangeRateProcessing.process() End ");
         }
      }
      catch (Exception e)
      {
         bFlag = false;
         logger.error(" ExchangeRateProcessing.process() processing erro " + e);
         e.printStackTrace();
      }
      finally
      {
         try
         {
            URL = null;
            exchangeDate = null;
            dbParamMap = null;
            dailyExchangeData = null;
            serviceRequest = null;
            historicalExchangeData = null;
            objDailyData = null;
         }
         catch (Exception e)
         {
         }
      }
      return bFlag;
   }
}
