package com.invmodel.dao.rbsa;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;
import com.invmodel.dao.data.HolisticData;
import org.apache.commons.dbutils.DbUtils;

public class HistoricalDailyReturns
{
   private static HistoricalDailyReturns instance = null;
   private final Logger logger = Logger.getLogger(HistoricalDailyReturns.class.getName());

   private int TICKER_ELEMENT = 0;  // Max number of daily returns = 20 years.
   private int DATA_SIZE_ELEMENT = 1;  // Max number of daily returns = 20 years.
   private int MAX_TICKERS = 500;  // Max number of daily returns = 20 years.
   private int MAX_RETURNS = 1000;  // Max number of daily returns = 20 years.
   private Integer maxticker = 0, maxreturns = MAX_RETURNS;
   private Map<String, Integer[]> dailyReturnsTickerMap = new HashMap<String, Integer[]>();
   private double[][] dailyReturnsArrayData; // Note: This array is allocated during load.

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
      super();
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

   private void putDailyReturnsArray(String key, double value)
   {
      try
      {
         Integer arrayPos = 0, tickerPos;
         if (dailyReturnsTickerMap.containsKey(key)) {
            tickerPos = dailyReturnsTickerMap.get(key)[TICKER_ELEMENT];     // Array Position
            arrayPos = dailyReturnsTickerMap.get(key)[DATA_SIZE_ELEMENT] + 1;  // Last position in Array
         }
         else {
            Integer[] matrix = new Integer[2];
            tickerPos = maxticker++;
            matrix[0] = tickerPos;
            matrix[1] = 0;
            dailyReturnsTickerMap.put(key,matrix);
         }

         if (tickerPos < MAX_TICKERS && arrayPos < MAX_RETURNS)
         {
            dailyReturnsArrayData[tickerPos][arrayPos] = value;
            dailyReturnsTickerMap.get(key)[1] = arrayPos;
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
         read.lock();
         String storedProcName = "invdb.sel_daily_prime_historical_returns";
         InvModelSP sp = new InvModelSP(ds, storedProcName,3, 99);
         dailyReturnsTickerMap.clear();

         ArrayList<Map<String, Object>> rows;
         Map outMap = sp.dhloadDailyHistoricalData();
         if (outMap != null)
         {
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null) {
               maxticker = 0;
               maxreturns = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(maxticker);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  String tradeCurrency = convert.getStrData(rs.get("tradeCurrency"));
                  Integer value =  convert.getIntData(rs.get("maxrows"));
                  maxreturns = (value > maxreturns) ? value : maxreturns;
                  maxticker++;
               }
            }

            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-2");
            if (rows != null) {
               int i = 0;
               maxreturns = (maxreturns > MAX_RETURNS) ? MAX_RETURNS : maxreturns;
               dailyReturnsArrayData = new double[maxticker][maxreturns];
               maxticker = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));
                  String tradeCurrency = convert.getStrData(rs.get("tradeCurrency"));
                  Double value =  convert.getDoubleData(rs.get("daily_return"));
                  String key = ticker + '.' + tradeCurrency;
                  putDailyReturnsArray(key, value);
                  i++;
               }
            }

         }
      }
      catch (Exception ex) {
      }
      finally
      {
         read.unlock();
      }
   }

   public double[] getDailyReturnsArraybyTicker(String ticker, String dest_currency)
   {
      read.lock();
      try
      {
         int tickerPos = 0;

         String key = ticker + "." + dest_currency;
         // For Cash use the BIL data
         if (ticker.toUpperCase().equals("CASH" + "." + dest_currency)) {
            key = "BIL.N" + "." + dest_currency;
         }

         if (dailyReturnsTickerMap.containsKey(key)) {
            tickerPos =  dailyReturnsTickerMap.get(key)[TICKER_ELEMENT];
            return dailyReturnsArrayData[tickerPos];
         }
         else {
            return ( new double[maxreturns] );
         }
      }
      finally
      {
         read.unlock();
      }
   }

   public double[][] getDailyReturnsArray(String[] tickerList, String dest_currency)
   {
      Integer tickersize = dailyReturnsTickerMap.size();
      Integer numofreturns = maxreturns;
      double[][] tickerListArrary = new double[tickerList.length][maxreturns];

      try
      {
         //Iterator it = dailyReturnsMap.keySet().iterator();
         int count = 0;
         while (count < tickerList.length)
         {
            String ticker = tickerList[count];
            tickerListArrary[count] = getDailyReturnsArraybyTicker(ticker, dest_currency);
            count++;
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
      return tickerListArrary;
   }

}
