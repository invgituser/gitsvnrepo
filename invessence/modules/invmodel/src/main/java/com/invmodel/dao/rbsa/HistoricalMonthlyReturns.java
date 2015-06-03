package com.invmodel.dao.rbsa;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.invmodel.dao.*;
import com.invessence.converter.SQLData;

public class HistoricalMonthlyReturns
{
   private static HistoricalMonthlyReturns instance = null;
   private final Logger logger = Logger.getLogger(HistoricalMonthlyReturns.class.getName());

   private int MAX_HISTORY = 300;  // Max number of monthly returns = 20 years.
   private Map<String, ArrayList<Double>> monthlyReturnsArray = new HashMap<String, ArrayList<Double>>();

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private DBConnectionProvider dbconnection;
   private SQLData convert;
   private DataSource ds;

   public static synchronized HistoricalMonthlyReturns getInstance()
   {
      if (instance == null)
      {
         instance = new HistoricalMonthlyReturns();
      }
      return instance;
   }

   public HistoricalMonthlyReturns()
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

         logger.info("Loading Monthly Returns from DB");
         // getMaxRows();
         // monthlyReturnsArray = new double[MAX_NO_TICKERS][maxNoRows];
         loadMonthlyReturnsfromDB();
      }
      finally
      {
         write.unlock();
      }
   }

   private void putMonthlyReturnsArray(String ticker, double value)
   {
      try
      {
         if (monthlyReturnsArray.containsKey(ticker)) {
            if (monthlyReturnsArray.get(ticker).size() < MAX_HISTORY)
               monthlyReturnsArray.get(ticker).add(value);
         }
         else {
            ArrayList<Double> histReturn;
            histReturn = new ArrayList<Double>();
            histReturn.add(value);
            monthlyReturnsArray.put(ticker,histReturn);
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
   }

   private void loadMonthlyReturnsfromDB()
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new com.invessence.converter.SQLData();
      ds = dbconnection.getMySQLDataSource();
      try
      {
         String storedProcName = "rbsa.sel_monthly_historical_returns";
         InvModelSP sp = new InvModelSP(ds, storedProcName,4, 99);
         monthlyReturnsArray.clear();

         Map outMap = sp.mhloadMonthlyHistoricalData();
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null) {
               int i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  Double value =  convert.getDoubleData(rs.get("monthly_return"));
                  putMonthlyReturnsArray(ticker, value);
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

   public ArrayList<Double> getMonthlyReturnsArraybyTicker(String ticker)
   {
      read.lock();
      try
      {
         return monthlyReturnsArray.get(ticker);
      }
      finally
      {
         read.unlock();
      }
   }

}
