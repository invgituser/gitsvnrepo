package com.invmodel.dao.rbsa;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;
import com.invmodel.performance.data.*;

public class HistoricalReportsReturns
{
   private static HistoricalReportsReturns instance = null;
   private final Logger logger = Logger.getLogger(HistoricalReportsReturns.class.getName());

   private int MAX_HISTORY = 300;  // Max number of monthly returns = 20 years.
   private String thisTheme;
   private Map<String, OptPerformanceMasterData> monthlyReturnsArray = new HashMap<String, OptPerformanceMasterData>();

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private DBConnectionProvider dbconnection;
   private SQLData convert;
   private DataSource ds;

   public HistoricalReportsReturns()
   {
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

   private void putMonthlyReturnsArray(String ticker,
                                       String name,
                                       String color,
                                       String currency,
                                       String businesdate,
                                       Double returns)
   {
      try
      {
         if (monthlyReturnsArray.containsKey(name))
         {
            // displayName exists then add to list
            monthlyReturnsArray.get(name).addPerformanceHistDataArrayList(ticker, name, businesdate, returns);
         }
         else
         {
            // displayName does exists
            OptPerformanceMasterData histReturn = new OptPerformanceMasterData(ticker, name, color, currency);
            histReturn.addPerformanceHistDataArrayList(ticker, name, businesdate, returns);;
            monthlyReturnsArray.put(name, histReturn);
         }
      }
      catch (
         Exception e)

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
         InvModelSP sp = new InvModelSP(ds, storedProcName, 6, 99);
         monthlyReturnsArray.clear();

         Map outMap = sp.reportloadMonthlyHistoricalData(theme);
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)
            {
               int i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  String displayName = convert.getStrData(rs.get("displayName"));
                  String color = convert.getStrData(rs.get("color"));
                  String currency = convert.getStrData(rs.get("baseCurrency"));
                  String businessdate = convert.getStrData(rs.get("businessdate"));
                  Double monthlyReturn = convert.getDoubleData(rs.get("daily_returns"));
                  putMonthlyReturnsArray(displayName, ticker, color, currency, businessdate, monthlyReturn);
                  i++;
               }
            }
         }
      }
      catch (Exception ex)
      {
      }
      finally
      {
      }
   }

   public void fetchHistoricalReportsReturns(String theme)
   {
      if (theme == null)
      {
         return;
      }

      if (monthlyReturnsArray == null)
      {
         loadMonthlyReturnsfromDB(theme);
      }
      else if (!theme.equalsIgnoreCase(getThisTheme()))
      {
         loadMonthlyReturnsfromDB(theme);
      }
   }

   public String getThisTheme()
   {
      return thisTheme;
   }

   public Map<String, OptPerformanceMasterData> getMonthlyReturnsArrayforCharting(String name)
   {

      return monthlyReturnsArray;
   }

}
