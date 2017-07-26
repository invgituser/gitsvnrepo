package com.invmodel.dao.rbsa;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;

public class HistoricalReportsReturns
{
   private static HistoricalReportsReturns instance = null;
   private final Logger logger = Logger.getLogger(HistoricalReportsReturns.class.getName());

   private int MAX_HISTORY = 300;  // Max number of monthly returns = 20 years.
   private String thisTheme;
   private Map<String, Map<String, ArrayList<Double>>> monthlyReturnsArray = new HashMap<String, Map<String, ArrayList<Double>>>();

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private DBConnectionProvider dbconnection;
   private SQLData convert;
   private DataSource ds;

   public static synchronized HistoricalReportsReturns getInstance(String theme)
   {
      if (instance == null)
      {
         instance = new HistoricalReportsReturns();
      }
      return instance;
   }

   public HistoricalReportsReturns() {
      thisTheme = null;
   }

   public HistoricalReportsReturns(String theme)
   {
      loadDataFromDB(theme);
   }

   public void refreshDataFromDB(String theme)
   {
      loadDataFromDB(theme);
   }

   private void loadDataFromDB(String theme)
   {
      thisTheme = theme;
      write.lock();
      try
      {

         logger.info("Loading Monthly Returns For reporting purpose from DB");
         // getMaxRows();
         // monthlyReturnsArray = new double[MAX_NO_TICKERS][maxNoRows];
         loadMonthlyReturnsfromDB(theme);
      }
      finally
      {
         write.unlock();
      }
   }

   private void putMonthlyReturnsArray(String name, String ticker, double value)
   {
      try
      {
         if (monthlyReturnsArray.containsKey(name))
         {
            if (monthlyReturnsArray.get(name).containsKey(ticker))
            {
               // Both displayname and ticker are available.
               if (monthlyReturnsArray.get(name).get(ticker).size() < MAX_HISTORY)
                  monthlyReturnsArray.get(name).get(ticker).add(value);
            }
            else
            {
               // displayName exists but ticker does not.
               ArrayList<Double> histReturn;
               histReturn = new ArrayList<Double>();
               histReturn.add(value);
               monthlyReturnsArray.get(name).put(ticker, histReturn);
            }
         }
         else {
            // displayName exists but ticker does not.
            ArrayList<Double> histReturn;
            histReturn = new ArrayList<Double>();
            histReturn.add(value);
            Map <String, ArrayList<Double>> tickerMap = new HashMap<String, ArrayList<Double>>();
            tickerMap.put(ticker,histReturn);
            monthlyReturnsArray.put(name, tickerMap);
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
   }

   private void loadMonthlyReturnsfromDB(String theme)
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new SQLData();
      ds = dbconnection.getMySQLDataSource();
      try
      {
         String storedProcName = "invdb.report_historical_data";
         InvModelSP sp = new InvModelSP(ds, storedProcName,6, 99);
         monthlyReturnsArray.clear();

         Map outMap = sp.reportloadMonthlyHistoricalData(theme);
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null) {
               int i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  String displayName = convert.getStrData(rs.get("displayName"));
                  Double value =  convert.getDoubleData(rs.get("monthly_return"));
                  putMonthlyReturnsArray(displayName, ticker, value);
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

   public String getThisTheme()
   {
      return thisTheme;
   }

   public Map<String, Map<String, ArrayList<Double>>> getMonthlyReturnsArrayforCharting(String name)
   {
         return monthlyReturnsArray;
   }

}
