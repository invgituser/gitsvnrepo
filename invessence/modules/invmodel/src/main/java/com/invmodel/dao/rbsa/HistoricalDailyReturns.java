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

   private void putDailyReturnsArray(String ticker, double value)
   {
      try
      {
         Integer arrayPos = 0, tickerPos;
         if (dailyReturnsTickerMap.containsKey(ticker)) {
            tickerPos = dailyReturnsTickerMap.get(ticker)[TICKER_ELEMENT];     // Array Position
            arrayPos = dailyReturnsTickerMap.get(ticker)[DATA_SIZE_ELEMENT] + 1;  // Last position in Array
         }
         else {
            Integer[] matrix = new Integer[2];
            tickerPos = maxticker++;
            matrix[0] = tickerPos;
            matrix[1] = 0;
            dailyReturnsTickerMap.put(ticker,matrix);
         }

         if (tickerPos < MAX_TICKERS && arrayPos < MAX_RETURNS)
         {
            dailyReturnsArrayData[tickerPos][arrayPos] = value;
            dailyReturnsTickerMap.get(ticker)[1] = arrayPos;
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
         String storedProcName = "rbsa.sel_daily_prime_historical_returns";
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
         read.unlock();
      }
   }

   public double[] getDailyReturnsArraybyTicker(String ticker)
   {
      read.lock();
      try
      {
         int tickerPos = 0;

         // For Cash use the BIL data
         if (ticker.toUpperCase().equals("CASH")) {
            ticker = "BIL";
         }

         if (dailyReturnsTickerMap.containsKey(ticker)) {
            tickerPos =  dailyReturnsTickerMap.get(ticker)[TICKER_ELEMENT];
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

   public double[][] getDailyReturnsArray(String[] tickerList)
   {
      Integer tickersize = dailyReturnsTickerMap.size();
      Integer numofreturns = maxreturns;
      double[][] tickerListArrary = new double[tickerList.length][maxreturns];

      read.lock();
      try
      {
         //Iterator it = dailyReturnsMap.keySet().iterator();
         int count = 0;
         while (count < tickerList.length)
         {
            String key = tickerList[count];
            tickerListArrary[count] = getDailyReturnsArraybyTicker(key);
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

   public double[][] getCustomDailyReturnsArray(String[] tickerList)
   {
      Integer tickersize = dailyReturnsTickerMap.size();
      Integer numofreturns = maxreturns;

      Map<String, ArrayList<Double>> customDataMap = new LinkedHashMap<String, ArrayList<Double>>();

      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      read.lock();
      try
      {
         String ticker;
         int tickercount=0;
         String tickerQuery="";
         for (int i = 0; i < tickerList.length; i++) {
            if (tickerList[i].toUpperCase().equals("CASH"))
               ticker="BIL";
            else
               ticker=tickerList[i];

            if (tickercount == 0)
               tickerQuery += "'" + ticker.trim() + "'";
            else
               tickerQuery += ", '" + ticker.trim() + "'";
            tickercount++;
         }

         String tickerWhere = "";
         if (tickercount > 0) {
            tickerWhere = " WHERE ticker in (" + tickerQuery + ") ";
         }
         else
            return null;

         connection = DBConnectionProvider.getInstance().getConnection();


         statement = connection.createStatement();
         statement.executeQuery("SELECT ticker," +
                                   "min(DATE_FORMAT(businessdate,'%Y%m%d')) as min_businessdate, " +
                                   "max(DATE_FORMAT(businessdate,'%Y%m%d')) as max_businessdate " +
                                   "FROM rbsa.rbsa_daily " +
                                   tickerWhere  +
                                   " GROUP by ticker");

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         Integer firstBdate = 0, lastBdate = 99999999;
         Integer minBDate, maxBDate;
         Integer numofTickers = 0;
         while (resultSet.next())
         {
            minBDate = resultSet.getInt("min_businessdate");
            maxBDate = resultSet.getInt("max_businessdate");

            firstBdate = (firstBdate < minBDate ) ? minBDate : firstBdate;
            lastBdate = (maxBDate < lastBdate ) ? maxBDate : lastBdate;
            numofTickers++;
         }

         String minDateWhere = "";
         if (firstBdate > 0) {
            minDateWhere = " AND  DATE_FORMAT(businessdate,'%Y%m%d') >= '" + firstBdate + "'";
         }

         String maxDateWhere = "";
         if (lastBdate < 99999999) {
            maxDateWhere = " AND  DATE_FORMAT(businessdate,'%Y%m%d') <= '" + lastBdate + "'";
         }

         statement = connection.createStatement();
         statement.executeQuery("SELECT ticker, " +
                                   "DATE_FORMAT(businessdate,'%Y%m%d') as businessdate, " +
                                   "daily_return as daily_return " +
                                   "FROM rbsa.rbsa_daily " +
                                   tickerWhere  + " " +
                                   minDateWhere + " " +
                                   maxDateWhere + " " +
                                   " order by 1,2 desc");


         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         Integer maxcustomdata = 0;
         while (resultSet.next())
         {
            ticker = resultSet.getString("ticker");
            Double daily_return = resultSet.getDouble("daily_return");

            if (customDataMap.containsKey(ticker))
            {
               customDataMap.get(ticker).add(daily_return);

            }
            else
            {
               ArrayList<Double> array = new ArrayList<Double>();
               array.add(daily_return);
               customDataMap.put(ticker, array);
            }

            maxcustomdata = (customDataMap.get(ticker).size() > maxcustomdata) ? customDataMap.get(ticker).size() : maxcustomdata;
         }

         double[][] customArrary = new double[customDataMap.size()][maxcustomdata];
         int loop = 0;
         for (String tick: customDataMap.keySet()) {
            for (int inner=0; inner < customDataMap.get(tick).size(); inner++ ) {
               customArrary[loop][inner] = (double) customDataMap.get(tick).get(inner);
            }
            loop++;
         }

         return customArrary;

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
      return null;
   }


}
