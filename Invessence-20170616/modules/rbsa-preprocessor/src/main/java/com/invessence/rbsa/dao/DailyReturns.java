package com.invessence.rbsa.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import org.apache.commons.dbutils.DbUtils;

public class DailyReturns
{
   private static final int MAX_NO_ASSETS = 30; // Max number of indexname supported
   private static final int MAX_NO_RETURNS = 5000;
   private static final int FIRST_ELEMENT = 0; // Ticker Position;

   private int maxNoIndex = 0;  // Max number of Index
   private int maxNoRows = 0;  // Max number of returns
   // Map object contains indexname, indexname location [0], and total# of returns in [1]
   private Map<String, int[]> dailyReturnsMap = new HashMap<String, int[]>();
   private ArrayList<String> orderedIndex = new ArrayList<String>();
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
         orderedIndex.clear();
         getMaxRows();
         maxNoIndex = (maxNoIndex == 0) ? 1 : maxNoIndex;
         maxNoRows = (maxNoRows == 0) ? 1 : maxNoRows;
         dailyReturnsArray = new double[maxNoIndex][maxNoRows];
         loadDailyReturnsfromDB();
      }
      finally
      {
         write.unlock();
      }
   }

   public int getMaxNoIndex()
   {
      return maxNoIndex;
   }

   public int getMaxNoRows()
   {
      return maxNoRows;
   }

   private void getMaxRows()
   {

      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      Integer totalRows;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("select ticker, count(*) as total from vw_daily_returns group by ticker");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         maxNoRows = MAX_NO_RETURNS;
         maxNoIndex = 0;
         while (resultSet.next())
         {
            totalRows  = resultSet.getInt("total");
            if (totalRows != null)
               maxNoRows = (totalRows < maxNoRows) ? totalRows : maxNoRows;
            maxNoIndex++;
         }

         maxNoRows = (maxNoRows > MAX_NO_RETURNS) ? MAX_NO_RETURNS : maxNoRows;
         maxNoIndex = (maxNoIndex > MAX_NO_ASSETS) ? MAX_NO_ASSETS : maxNoIndex;
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

   private void putDailyReturnsArray(int indexnamePos, int arrayPos, double value)
   {
      try
      {
         if (arrayPos < maxNoRows)
         {
            arrayPos = (arrayPos > maxNoRows - 1) ? maxNoRows - 1 : arrayPos;
            indexnamePos = (indexnamePos > MAX_NO_ASSETS - 1) ? MAX_NO_ASSETS - 1 : indexnamePos;
            dailyReturnsArray[indexnamePos][arrayPos] = value;
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
         statement.executeQuery("SELECT ticker, monthly_return as monthly_return FROM vw_monthly_returns order by sortorder, date desc");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         int count = 0;
         int[] no_of_returns;
         while (resultSet.next())
         {
            String indexname = resultSet.getString("ticker");
            if (dailyReturnsMap.containsKey(indexname))
            {
               no_of_returns = dailyReturnsMap.get(indexname);
               no_of_returns[second_element] = no_of_returns[second_element] + 1;
               dailyReturnsMap.put(indexname, no_of_returns);
               putDailyReturnsArray(dailyReturnsMap.get(indexname)[FIRST_ELEMENT], no_of_returns[second_element], resultSet.getDouble("monthly_return"));
            }
            else
            {
               int[] indexnameReturns = new int[max_element];
               indexnameReturns[FIRST_ELEMENT] = count;
               indexnameReturns[second_element] = 0;
               orderedIndex.add(count,indexname);
               dailyReturnsMap.put(indexname, indexnameReturns);
               putDailyReturnsArray(indexnameReturns[FIRST_ELEMENT], indexnameReturns[second_element], resultSet.getDouble("monthly_return"));
               count++;
            }
         }
         maxNoIndex = count;
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

   public double[] getDailyReturnsArraybyTicker(int indexnamePos)
   {
      read.lock();
      try
      {
         return dailyReturnsArray[indexnamePos];
      }
      finally
      {
         read.unlock();
      }
   }

   public double[][] getAllIndexReturnsArray() {
      return dailyReturnsArray;
   }


   public double[][] getDailyReturnsArray(String[] indexnameList)
   {
      double[][] indexnameListArrary = new double[indexnameList.length][maxNoRows + 1];

      read.lock();
      try
      {
         //Iterator it = dailyReturnsMap.keySet().iterator();
         int count = 0;
         int indexnamePos;
         while (count < indexnameList.length)
         {
            String key = indexnameList[count];
            if (dailyReturnsMap.containsKey(key))
            {
               indexnamePos = dailyReturnsMap.get(key)[FIRST_ELEMENT];
               indexnameListArrary[count] = getDailyReturnsArraybyTicker(indexnamePos);
            }
            else
            {
               double[] empty = new double[]{1.0};
               indexnameListArrary[count] = empty;
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
      return indexnameListArrary;
   }

   public String getIndexName(Integer indexpos) {
      return orderedIndex.get(indexpos);
   }
}
