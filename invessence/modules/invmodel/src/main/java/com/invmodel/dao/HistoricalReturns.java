package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.dao.data.HistoricalReturnsData;
import org.apache.commons.dbutils.DbUtils;

public class HistoricalReturns
{
   private static HistoricalReturns instance = null;
   private List<String> orderedindexFund = new ArrayList<String>();
   private static final int FIRST = 0; // Index Position;
   private static final int SECOND = 1; // Number of Months;
   private static final int MAX_HISTORY = 1500;
   private static final int MAX_INDICATORS = 2;
   private static final int MAX_INDEX = 20;

   // Map object contains tickers, ticker location [0], and total# of returns in [1]
   private int totalIndex = 0; // Index Position;
   private int totalYears = 0; // total# of Months;
   private Map<String, int[]> historicalReturnsMap = new HashMap<String, int[]>();
   private HistoricalReturnsData[][] historicalreturns;
   private final Logger logger = Logger.getLogger(HistoricalReturns.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized HistoricalReturns getInstance()
   {
      if (instance == null)
      {
         instance = new HistoricalReturns();
      }
      return instance;
   }

   public HistoricalReturns()
   {
      super();
      loadDataFromDB();
   }

   private void loadDataFromDB()
   {
      write.lock();
      try
      {
         logger.info("Loading Historical Returns from DB");
         // TODO Auto-generated constructor stub
         historicalreturns = new HistoricalReturnsData[MAX_INDEX][MAX_HISTORY];
         historicalReturnsMap.clear();
         orderedindexFund.clear();
         totalIndex = 0;
         totalYears = 0;
         loadHistoricalReturns();
      }
      finally
      {
         write.unlock();
      }
   }

   public void refreshDataFromDB()
   {
      loadDataFromDB();
   }

   private void putHistoricalReturnsArray(int indexPos, int arrayPos, HistoricalReturnsData data)
   {
      try
      {
         arrayPos = (arrayPos >= MAX_HISTORY) ? MAX_HISTORY - 1 : arrayPos;
         indexPos = (indexPos >= MAX_INDEX) ? MAX_INDEX - 1: indexPos;
         historicalreturns[indexPos][arrayPos] = data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   private void addNewHistoricalData(String indexFund, String date,
                                     double lbConstraint, double ubConstraint, double avgReturns,
                                     String color)
   {
      try
      {
         int indexPos = 0;
         int year = 0;
         int[] indexReturns;
         if (historicalReturnsMap.containsKey(indexFund))
         {
            indexPos = historicalReturnsMap.get(indexFund)[FIRST];
            year = historicalReturnsMap.get(indexFund)[SECOND] + 1;
         }
         else
         {
            orderedindexFund.add(indexFund);
            indexPos = totalIndex;
            totalIndex++;
         }
         // year starts with zero, but we want the totalYears to start with one.
         // If year from other index is less, then we want to keep the largest.
         totalYears = ((year + 1) > totalYears) ? (year + 1) : totalYears;
         if (!historicalReturnsMap.containsKey(indexFund))
         {
            indexReturns = new int[MAX_INDICATORS];
         }
         else
         {
            indexReturns = historicalReturnsMap.get(indexFund);
         }

         if (indexPos >= MAX_INDEX)
            return;

         if (year >= MAX_HISTORY)
            return;

         HistoricalReturnsData hrd = new HistoricalReturnsData(indexFund, date
            , lbConstraint, ubConstraint, avgReturns, color);
         putHistoricalReturnsArray(indexPos, year, hrd);

         indexReturns[FIRST] = indexPos;
         indexReturns[SECOND] = year;
         historicalReturnsMap.put(indexFund, indexReturns);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void loadHistoricalReturns()
   {
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         historicalReturnsMap.clear();  // Clear this map, so we can reallocate.
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT indexFund, seqno, monthly_return FROM vw_historical_returns order by indexFund, seqno desc");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         int[] no_of_returns;
         int count = 0;
         while (resultSet.next())
         {
            String indexFund = resultSet.getString("indexFund");
            addNewHistoricalData(indexFund, resultSet.getString("seqno"),
                                 0.0, 0.0, resultSet.getDouble("monthly_return"),
                                 "");
            count++;
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

   public double[][] getHistoricalReturnsArray(String[] indexList)
   {


      try
      {
         //Iterator it = monthlyReturnsMap.keySet().iterator();
         int count = 0;
         int indexPos;
         int maxElement = MAX_HISTORY;

         // Find the least number of array, and allocate the history based on this size.
         for (int i = 0; i < indexList.length; i++ ) {
            String key = indexList[i];
            maxElement = (historicalReturnsMap.get(key)[SECOND] < maxElement) ? historicalReturnsMap.get(key)[SECOND] : maxElement;
         }

         double[][] hrdata = new double[indexList.length][maxElement];

         for (int i = 0; i < indexList.length; i++ ) {
            String key = indexList[i];
            indexPos = historicalReturnsMap.get(key)[FIRST];
            for (int j=0 ; j < maxElement ; j++) {
               hrdata[i][j] = historicalreturns[indexPos][j].getAvgReturns();
            }
         }
         return hrdata;
      }

      catch (Exception e)
      {
         logger.severe(e.getMessage());
      }

      return null;
   }
}
