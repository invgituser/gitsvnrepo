package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;

public class DailyReturns
{
   private static final int MAX_NO_TICKERS = 100; // Max number of tickers supported
   private static final int FIRST_ELEMENT = 0; // Ticker Position;

   private int maxNoRows = 1000;  // Max number of returns
   // Map object contains tickers, ticker location [0], and total# of returns in [1]
   private Map<String, int[]> dailyReturnsMap = new HashMap<String, int[]>();
   private double[][] dailyReturnsArray; // Note: This array is allocated on constructor
   // private List<List<Double>> arraylistofdailyReturns = new ArrayList<List<Double>>();
   private static DailyReturns instance = null;
   private final Logger logger = Logger.getLogger(DailyReturns.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized DailyReturns getInstance()
   {
      if (instance == null)
      {
         instance = new DailyReturns();
      }
      return instance;
   }

   public DailyReturns()
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
         dailyReturnsMap.clear();
         getMaxRows();
         dailyReturnsArray = new double[MAX_NO_TICKERS][maxNoRows];
         loadDailyReturnsfromDB();
      }
      finally
      {
         write.unlock();
      }
   }

   private void getMaxRows()
   {

      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("select min(MR.total) + 1 as max_rows from\n" +
                                   "\t(select ticker, count(*) as total from vw_daily_returns group by ticker) MR");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            maxNoRows = resultSet.getInt("max_rows");
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         DbUtils.closeQuietly(resultSet);
         DbUtils.closeQuietly(statement);
         DbUtils.closeQuietly(connection);
      }
   }

   private void putDailyReturnsArray(int tickerPos, int arrayPos, double value)
   {
      try
      {
         if (arrayPos < maxNoRows)
         {
            arrayPos = (arrayPos > maxNoRows - 1) ? maxNoRows - 1 : arrayPos;
            tickerPos = (tickerPos > MAX_NO_TICKERS - 1) ? MAX_NO_TICKERS - 1 : tickerPos;
            dailyReturnsArray[tickerPos][arrayPos] = value;
         }
      }
      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }
   }

   private void loadDailyReturnsfromDB()
   {
      int max_element = 2; // Total number of elements on Map object.
      int second_element = 1; // Number of rows;
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         dailyReturnsMap.clear();       // Clear entire Hashmap to start new...
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT ticker, seqno, daily_return FROM vw_daily_returns order by ticker, seqno desc");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         int count = 0;
         int[] no_of_returns;
         while (resultSet.next())
         {
            String ticker = resultSet.getString("ticker");
            if (dailyReturnsMap.containsKey(ticker))
            {
               no_of_returns = dailyReturnsMap.get(ticker);
               no_of_returns[second_element] = no_of_returns[second_element] + 1;
               dailyReturnsMap.put(ticker, no_of_returns);
               putDailyReturnsArray(dailyReturnsMap.get(ticker)[FIRST_ELEMENT], no_of_returns[second_element], resultSet.getDouble("daily_return"));
            }
            else
            {
               int[] tickerReturns = new int[max_element];
               tickerReturns[FIRST_ELEMENT] = count;
               tickerReturns[second_element] = 0;
               dailyReturnsMap.put(ticker, tickerReturns);
               putDailyReturnsArray(tickerReturns[FIRST_ELEMENT], tickerReturns[second_element], resultSet.getDouble("daily_return"));
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
         DbUtils.closeQuietly(resultSet);
         DbUtils.closeQuietly(statement);
         DbUtils.closeQuietly(connection);
      }
   }

   public double[] getDailyReturnsArraybyTicker(int tickerPos)
   {
      read.lock();
      try
      {
         return dailyReturnsArray[tickerPos];
      }
      finally
      {
         read.unlock();
      }
   }

   public double[][] getDailyReturnsArray(String[] tickerList)
   {
      double[][] tickerListArrary = new double[tickerList.length][maxNoRows + 1];

      read.lock();
      try
      {
         //Iterator it = dailyReturnsMap.keySet().iterator();
         int count = 0;
         int tickerPos;
         while (count < tickerList.length)
         {
            String key = tickerList[count];
            if (dailyReturnsMap.containsKey(key))
            {
               tickerPos = dailyReturnsMap.get(key)[FIRST_ELEMENT];
               tickerListArrary[count] = getDailyReturnsArraybyTicker(tickerPos);
            }
            else
            {
               double[] empty = new double[]{1.0};
               tickerListArrary[count] = empty;
            }
            count++;
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
      return tickerListArrary;
   }
}
