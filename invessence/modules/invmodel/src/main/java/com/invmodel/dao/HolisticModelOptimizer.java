package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.data.*;
import org.apache.commons.dbutils.DbUtils;
import webcab.lib.finance.portfolio.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/24/15
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class HolisticModelOptimizer
{

   private static HolisticModelOptimizer instance = null;
   private final Logger logger = Logger.getLogger(HolisticModelOptimizer.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private PortfolioOptimizer poptimizer = PortfolioOptimizer.getInstance();
   Map<String, HolisticData> holisticdataMap = new HashMap<String, HolisticData>();
   Map<String, String> allPrimeAssetMap = new HashMap<String, String>();

   public static synchronized HolisticModelOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new HolisticModelOptimizer();
      }

      return instance;
   }

   private HolisticModelOptimizer()
   {
      super();
   }

   public Map<String, String> getAllPrimeAssetMap()
   {
      return allPrimeAssetMap;
   }

   public void loadFundDataFromDB(String[] tickers) {
      try {
         holisticdataMap.clear();       // Clear entire Hashmap to start new...
         loadRBSAfromDB(tickers);

         loadDailyReturnsfromDB(tickers);

      }
      catch (Exception ex) {

      }
   }

   public Map<String, HolisticData> getHolisticdataMap()
   {
      return holisticdataMap;
   }

   public void loadPrimeAssetDataFromDB(String theme) {
      try {
         if (theme == null)
            theme = "PRIME-ASSET";
         poptimizer.loadDataFromDB("PRIME-ASSET");
      }
      catch (Exception ex) {

      }
   }


   private void loadRBSAfromDB(String[] tickers)
   {
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {

         // Select data from the database
         String tickerList = "";
         int tickercount=0;
         for (int i = 0; i < tickers.length; i++) {
            if (tickercount == 0)
               tickerList += "'" + tickers[i] + "'";
            else
               tickerList += ", '" + tickers[i] + "'";
            tickercount++;
         }

         String whereStatement = "";
         if (tickercount > 0) {
            whereStatement = "where ticker in (" + tickerList + ")";
         }
         else
            return;

         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT ticker," +
                                   "indexfund, " +
                                   "theme, " +
                                   "assetclass, " +
                                   "primeassetclass, " +
                                   "sortorder, " +
                                   "lowerBound, " +
                                   "upperBound, " +
                                   "expectedReturn, " +
                                   "weight " +
                                   "FROM rbsa.vw_funds_weights " +
                                   whereStatement +
                                   " order by ticker, sortorder");
         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            String ticker = resultSet.getString("ticker");
            PrimeAssetClassData pacd = new PrimeAssetClassData(resultSet.getString("theme"),
                                                               resultSet.getString("primeassetclass"),
                                                               resultSet.getString("indexfund"),
                                                               resultSet.getString("assetclass"),
                                                               resultSet.getDouble("expectedReturn"),
                                                               resultSet.getDouble("upperBound"),
                                                               resultSet.getDouble("lowerBound"),
                                                               0.0,
                                                               resultSet.getInt("sortorder"),
                                                               resultSet.getDouble("weight"));

            if(!allPrimeAssetMap.containsKey(resultSet.getString("primeassetclass"))){
               allPrimeAssetMap.put(resultSet.getString("primeassetclass"),resultSet.getString("primeassetclass"));
            }

            if (! holisticdataMap.containsKey(ticker))
            {
               HolisticData holisticData = new HolisticData();
               holisticData.getPrimeassets().add(pacd);
               holisticdataMap.put(ticker, holisticData);
            }
            else
               holisticdataMap.get(ticker).getPrimeassets().add(pacd);
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

   private void loadDailyReturnsfromDB(String[] tickers)
{
   Connection connection = null;
   Statement statement = null;
   ResultSet resultSet = null;
   try
   {
       // Select data from the database
      String tickerList = "";
      int tickercount=0;
      for (int i = 0; i < tickers.length; i++) {
         if (tickercount == 0)
            tickerList += "'" + tickers[i] + "'";
         else
            tickerList += ", '" + tickers[i] + "'";
         tickercount++;
      }

      String whereStatement = "";
      if (tickercount > 0) {
         whereStatement = "where ticker in (" + tickerList + ")";
      }
      else
         return;

      connection = DBConnectionProvider.getInstance().getConnection();
      statement = connection.createStatement();
      statement.executeQuery("SELECT ticker, daily_return FROM vw_daily_returns_Holistc_Model " + whereStatement +" order by ticker, seqno desc");
      resultSet = statement.getResultSet();
      resultSet.beforeFirst();
      int retCount = 0;
      while (resultSet.next())
      {
         String ticker = resultSet.getString("ticker");
         Double daily_return = resultSet.getDouble("daily_return");

         if (holisticdataMap.containsKey(ticker))
         {
            holisticdataMap.get(ticker).getReturns().add(daily_return);
            retCount++;
            holisticdataMap.get(ticker).setMaxReturns(retCount);
         }
         else
         {
            HolisticData hdata = new HolisticData();
            hdata.getReturns().add(daily_return);
            holisticdataMap.put(ticker, hdata);
            retCount = 0;
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

   private double[][] getDailyReturns(String [] tickers) {
      try {
         if (tickers != null) {
            Integer smallestArray = 5000;
            for (String ticker : holisticdataMap.keySet()) {
               smallestArray =  (holisticdataMap.get(ticker).getMaxReturns() < smallestArray) ? holisticdataMap.get(ticker).getMaxReturns() : smallestArray;
            }

            double[][] listofReturns = new double[tickers.length][smallestArray];
            Double value;
            for (int i =0; i < tickers.length; i++) {
               if (holisticdataMap.containsKey(tickers[i])) {
                  for (int count=0; count < smallestArray; count++) {
                     value = holisticdataMap.get(tickers[i]).getReturns().get(count);
                     listofReturns[i][count] = value;
                  }
               }
            }
            return listofReturns;
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   public double [][] getFundOptimalWeight(String [] tickers, String pAssets)
   {
      try {

         Integer numofTicker = tickers.length;

         // loadPrimeAssetDataFromDB(pAssets);
         loadFundDataFromDB(tickers);
         double[][] mrData = null;
         mrData = getDailyReturns(tickers);


         //To use these returns, call getDailyReturns with the same tickers;
         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();

         double[] expectedReturnsOfFunds = new double [tickers.length];
         int t = 0;
         for (String fTicker: tickers){

            for( PrimeAssetClassData pAsst: holisticdataMap.get(fTicker).getPrimeassets()){

               double expRet = pAsst.getExpectedReturn();
               double wgt = pAsst.getWeight();
               String pAsset =  pAsst.getPrimeAssetName();

               expectedReturnsOfFunds[t] = expectedReturnsOfFunds[t] + expRet * wgt;
            }
            t++;
         }

         //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(mrData);
         double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);
         t = t +1;
         //Here we evaluate the maximum expected return  of  the  Portfolio's  on
         //the Efficient Frontier.

         //double[] lowerBound = getAssetOrderedLowerBound(theme);
         //double[] upperBound = getAssetOrderedUpperBound(theme);
         //instanceOfCapitalMarket.setConstraints(lowerBound, upperBound);

         double minReturn1 = instanceOfCapitalMarket.minFrontierReturn(expectedReturnsOfFunds);
         double maxReturn1 = instanceOfCapitalMarket.maxFrontierReturn(expectedReturnsOfFunds);
         instanceOfCapitalMarket.calculateEfficientFrontier(
            minReturn1, // minimumExpectedReturn
            maxReturn1, // maximumExpectedReturn
            covarianceOfFunds,//Covariance matrix
            expectedReturnsOfFunds, // expectedReturns
            1000, // numberInterpolationPoints
            InvConst.ASSET_PRECISION  // precision
         );

         double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();
         double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(covarianceOfFunds);
         double[] portReturns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();
         return weights;

      }
      catch (Exception ex) {

      }
      return  null;
   }

}
