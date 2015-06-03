package com.invmodel.dao.rbsa;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;

public class HistoricalDailyReturns
{
   private static HistoricalDailyReturns instance = null;
   private final Logger logger = Logger.getLogger(HistoricalDailyReturns.class.getName());

   private int MAX_HISTORY = 1000;  // Max number of daily returns = 20 years.
   private Map<String, ArrayList<Double>> dailyReturnsArray = new HashMap<String, ArrayList<Double>>();

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private DBConnectionProvider dbconnection;
   private SQLData convert;
   private DataSource ds;

   public static synchronized HistoricalDailyReturns getInstance()
   {
      if (instance == null)
      {
         instance = new HistoricalDailyReturns();
      }
      return instance;
   }

   public HistoricalDailyReturns()
   {
      loadDataFromDB();
   }

   public void refreshDataFromDB()
   {
      loadDataFromDB();
   }

   private void loadDataFromDB()
   {
      write.lock();
      try
      {

         logger.info("Loading Daily Returns from DB");
         // getMaxRows();
         // dailyReturnsArray = new double[MAX_NO_TICKERS][maxNoRows];
         loadDailyReturnsfromDB();
      }
      finally
      {
         write.unlock();
      }
   }

   private void putDailyReturnsArray(String ticker, double value)
   {
      try
      {
         if (dailyReturnsArray.containsKey(ticker)) {
            if (dailyReturnsArray.get(ticker).size() < MAX_HISTORY)
               dailyReturnsArray.get(ticker).add(value);
         }
         else {
            ArrayList<Double> histReturn;
            histReturn = new ArrayList<Double>();
            histReturn.add(value);
            dailyReturnsArray.put(ticker,histReturn);
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
   }

   private void loadDailyReturnsfromDB()
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new SQLData();
      ds = dbconnection.getMySQLDataSource();
      try
      {
         String storedProcName = "rbsa.sel_daily_historical_returns";
         InvModelSP sp = new InvModelSP(ds, storedProcName,3, 99);
         dailyReturnsArray.clear();

         Map outMap = sp.dhloadDailyHistoricalData();
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null) {
               int i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  Double value =  convert.getDoubleData(rs.get("daily_return"));
                  putDailyReturnsArray(ticker, value);
                  i++;
               }
            }
         }
      }
      catch (Exception ex) {
      }
      finally
      {
      }
   }

   public Double[] getDailyReturnsArraybyTicker(String ticker)
   {
      read.lock();
      try
      {
         return (Double []) dailyReturnsArray.get(ticker).toArray();
      }
      finally
      {
         read.unlock();
      }
   }

   public double[][] getDailyReturnsArray(String[] tickerList)
   {
      read.lock();
      double[][] tickerListArrary = null;
      try
      {
         int maxNoReturns = MAX_HISTORY;
         if (tickerList != null && tickerList.length > 0) {
            if (dailyReturnsArray.containsKey(tickerList[0])) {
               maxNoReturns = dailyReturnsArray.get(tickerList[0]).size();
            }

            tickerListArrary = new double[tickerList.length][maxNoReturns];
            int count = 0;
            while (count < tickerList.length)
            {
               String key = tickerList[count];
               if (dailyReturnsArray.containsKey(key))
               {
                  for (int numvalue=0; numvalue < dailyReturnsArray.get(key).size(); numvalue++) {
                     tickerListArrary[count][numvalue] = dailyReturnsArray.get(key).get(numvalue);
                  }
               }
               count++;
            }
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
      finally
      {
         read.unlock();
      }

      if (tickerListArrary == null)
         return new double[tickerList.length][MAX_HISTORY];
      else
         return tickerListArrary;
   }


}
