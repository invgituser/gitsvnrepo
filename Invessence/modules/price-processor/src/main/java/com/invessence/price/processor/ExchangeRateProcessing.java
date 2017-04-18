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
   EmailCreator emailCreator;
   @Autowired
   DBParametersDao dbParametersDao;
   @Autowired
   SecExchangeDao secExchangeMaster;
   @Autowired
   CallingExchangeService objExchangeService;

   List<SecExchangeMaster> symLst = null;
   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   SimpleDateFormat switchFormat = new SimpleDateFormat("yyyyMMdd");

   public boolean process(String company, String mode) throws SQLException
   {
      boolean bFlag = false;
      String URL = null;
      String exchangeDate = null;
      Map<String, DBParameters> dbParamMap = null;
      StringBuilder mailAlertMsg = null;
      DailyRates dailyExchangeData = null;
      ServiceRequest serviceRequest = null;
      List<HistoricalDataRates> historicalExchangeData = null;
      HistoricalData objDailyData = null;
      try
      {
         mailAlertMsg = new StringBuilder();
         serviceRequest = new ServiceRequest(company, mode);
         logger.info(" ExchangeRateProcessing process Start");
         dbParamMap = dbParametersDao.getDBParametres();
         if (dbParamMap == null && dbParamMap.size() == 0)
         {
            mailAlertMsg.append("ExchangeRateProcessing.process() DB parameters are not available \n");
            logger.info("ExchangeRateProcessing.process() DB parameters are not available");
         }
         else
         {
            exchangeDate = sdf.format(switchFormat.parse(dbParamMap.get("PRICE_DATE").getValue().toString()));
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
                     logger.info(" ExchangeRateProcessing.process() requesting URL " + URL);
                     historicalExchangeData = null;
                     try
                     {
                        historicalExchangeData = objExchangeService.getHistoricalExchangeRate(URL, symLst.get(i).getSymbol() + "=", exchangeDate);
                     }
                     catch (Exception e)
                     {
                        mailAlertMsg.append("ExchangeRateProcessing.process() Error while API call historical " + e.getMessage() + " \n");
                        logger.error("ExchangeRateProcessing.process() Error while API call historical " + e.getMessage());
                        e.printStackTrace();
                     }
                     try
                     {
                        secExchangeMaster.insertBatch(historicalExchangeData, symLst.get(i).getSymbol());
                     }
                     catch (Exception e)
                     {
                        mailAlertMsg.append("ExchangeRateProcessing.process() Error while db insertion historical " + e.getMessage() + " \n");
                        logger.error("ExchangeRateProcessing.process() Error while db insertion historical" + e.getMessage());
                        e.printStackTrace();
                     }
                  }
                  else
                  {
                     URL = ServiceDetails.getConfigProperty(serviceRequest.getProduct(), Constant.SERVICES.PRICING.toString(), serviceRequest.getMode(), Constant.PRICING.FIS.toString(), "DAILY.URL");
                     logger.info(" ExchangeRateProcessing.process() requesting URL " + URL);

                     objDailyData = null;
                     try
                     {
                        objDailyData = objExchangeService.getDailyExchangeRate(URL, symLst.get(i).getSymbol() + "=", exchangeDate);
                     }
                     catch (Exception e)
                     {
                        mailAlertMsg.append("ExchangeRateProcessing.process() Error while API call daily " + e.getMessage() + " \n");
                        logger.error("ExchangeRateProcessing.process() Error while API call daily " + e.getMessage());
                        e.printStackTrace();
                     }
                     try
                     {
//                        dailyExchangeData = objDailyData;
                        secExchangeMaster.delete(symLst.get(i).getSymbol(),exchangeDate);
                        secExchangeMaster.insert(objDailyData, symLst.get(i).getSymbol());
                     }
                     catch (Exception e)
                     {
                        mailAlertMsg.append("ExchangeRateProcessing.process() Error while db insertion daily " + e.getMessage() + " \n");
                        logger.error("ExchangeRateProcessing.process() Error while db insertion daily" + e.getMessage());
                        e.printStackTrace();
                     }
                  }
               }
               bFlag = true;
               logger.info(" ExchangeRateProcessing.process() return flag " + bFlag);
            }
            else
            {
               mailAlertMsg.append("ExchangeRateProcessing.process() exchange symbols are not available \n");
               logger.info("ExchangeRateProcessing.process() exchange symbols are not available");
            }
            logger.info(" ExchangeRateProcessing.process() End ");
         }
      }
      catch (Exception e)
      {
         bFlag = false;
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
         try
         {
            URL = null;
            exchangeDate = null;
            dbParamMap = null;
            mailAlertMsg = null;
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
