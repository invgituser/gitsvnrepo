package com.invmodel.dao.rbsa;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.DBConnectionProvider;
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

   Map<String, HolisticData> holisticdataMap;
   Map<String, String> allPrimeAssetMap;

   private AssetParameters assetParameters;

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
      assetParameters = new AssetParameters();
      allPrimeAssetMap = new LinkedHashMap<String, String>();
      holisticdataMap = new HashMap<String, HolisticData>();
   }

   public Map<String, String> getAllPrimeAssetMap()
   {
      return allPrimeAssetMap;
   }

   private String getTheme(String theme) {
      if (theme == null)
         theme = "PRIME-ASSET";
      return theme;
   }

   public void loadFundDataFromDB(String theme, String[] tickers) {
      try {
         holisticdataMap.clear();       // Clear entire Hashmap to start new...
         loadRBSAfromDB(theme, tickers);

         //loadDailyReturnsfromDB(tickers);

      }
      catch (Exception ex) {

      }
   }

   public Map<String, HolisticData> getHolisticdataMap()
   {
      return holisticdataMap;
   }


   private void loadRBSAfromDB(String theme, String[] tickers)
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
               tickerList += "'" + tickers[i].trim() + "'";
            else
               tickerList += ", '" + tickers[i].trim() + "'";
            tickercount++;
         }

         String whereStatement = "";
         if (tickercount > 0) {
            whereStatement = " AND ticker in (" + tickerList + ")";
         }
         else
            return;

         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         if (theme == null || theme.isEmpty())
            theme = getTheme(null);

         theme = theme.trim();

         String sqlquery =  "SELECT ticker," +
            "indexfund, " +
            "theme, " +
            "assetclass, " +
            "primeassetclass, " +
            "sortorder, " +
            "lowerBound, " +
            "upperBound, " +
            "expectedReturn, " +
            "weight " +
            "FROM invdb.vw_funds_weights " +
            "WHERE theme = '" + theme + "' " +
            whereStatement + " \n" +
            " ORDER BY sortorder, ticker";

         statement.executeQuery(sqlquery);

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            String ticker = resultSet.getString("ticker");
            String primeAssetClass = resultSet.getString("primeassetclass");
            PrimeAssetClassData pacd = new PrimeAssetClassData(resultSet.getString("theme"),
                                                               primeAssetClass,
                                                               resultSet.getString("indexfund"),
                                                               resultSet.getString("assetclass"),
                                                               resultSet.getDouble("expectedReturn"),
                                                               resultSet.getDouble("upperBound"),
                                                               resultSet.getDouble("lowerBound"),
                                                               0.0,
                                                               resultSet.getInt("sortorder"),
                                                               resultSet.getDouble("weight"));


            //if (! primeAssetClass.toUpperCase().equals("CASH")) {
               if(!allPrimeAssetMap.containsKey(primeAssetClass)){
                  allPrimeAssetMap.put(primeAssetClass,primeAssetClass);
               }

               if (! holisticdataMap.containsKey(ticker))
               {
                  HolisticData holisticData = new HolisticData();
                  holisticData.getPrimeassets().put(primeAssetClass, pacd);
                  holisticdataMap.put(ticker, holisticData);
               }
               else
                  holisticdataMap.get(ticker).getPrimeassets().put(primeAssetClass, pacd);
            //}
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

   public void loadRBSATickersfromDB(String defaultTheme, String[] tickers)
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
               tickerList += "'" + tickers[i].trim() + "'";
            else
               tickerList += ", '" + tickers[i].trim() + "'";
            tickercount++;
         }

         String whereStatement = "";
         if (tickercount > 0) {
            whereStatement = " ticker in (" + tickerList + ")";
         }
         else
            return;

         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();

         String sqlquery =  "SELECT ticker," +
            "primeassetclass, " +
            "weight " +
            "FROM invdb.sec_rbsa " +
            "WHERE " + whereStatement + " \n" +
            " ORDER BY ticker";

         statement.executeQuery(sqlquery);

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            String ticker = resultSet.getString("ticker");
            String primeAssetClass = resultSet.getString("primeassetclass");

            PrimeAssetClassData pacd = new PrimeAssetClassData(defaultTheme,
                                                               primeAssetClass,
                                                               "",
                                                               "Unused",
                                                               0.0,
                                                               0.0,
                                                               0.0,
                                                               0.0,
                                                               99998,
                                                               resultSet.getDouble("weight"));

            //if (! primeAssetClass.toUpperCase().equals("CASH")) {
               if(!allPrimeAssetMap.containsKey(primeAssetClass)){
                  allPrimeAssetMap.put(primeAssetClass,primeAssetClass);
               }

               if (! holisticdataMap.containsKey(ticker))
               {
                  HolisticData holisticData = new HolisticData();
                  holisticData.getPrimeassets().put(primeAssetClass, pacd);
                  holisticdataMap.put(ticker, holisticData);
               }
            //}
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
         if (tickers[i].toUpperCase().equals("CASH"))
            continue;

         if (tickercount == 0)
            tickerList += "'" + tickers[i] + "'";
         else
            tickerList += ", '" + tickers[i] + "'";
         tickercount++;
      }

      String tickerWhere = "";
      if (tickercount > 0) {
         tickerWhere = " AND ticker in (" + tickerList + ") ";
      }
      else
         return;

      connection = DBConnectionProvider.getInstance().getConnection();


      statement = connection.createStatement();
      statement.executeQuery("SELECT ticker," +
                                "min(DATE_FORMAT(businessdate,'%Y%m%d')) as min_businessdate, " +
                                "max(DATE_FORMAT(businessdate,'%Y%m%d')) as max_businessdate " +
                                "FROM rbsa.rbsa_daily " +
                                "WHERE upper(ticker) not in ('CASH') " +
                                tickerWhere  +
                                " GROUP by ticker");

      resultSet = statement.getResultSet();
      resultSet.beforeFirst();
      Integer firstBdate = 0, lastBdate = 99999999;
      Integer minBDate, maxBDate;
      while (resultSet.next())
      {
         minBDate = resultSet.getInt("min_businessdate");
         maxBDate = resultSet.getInt("max_businessdate");

         firstBdate = (firstBdate < minBDate ) ? minBDate : firstBdate;
         lastBdate = (maxBDate < lastBdate ) ? maxBDate : lastBdate;
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
                                "WHERE upper(ticker) not in ('CASH') " +
                                tickerWhere  + " " +
                                minDateWhere + " " +
                                maxDateWhere + " " +
                                " order by 1,2 desc");


      resultSet = statement.getResultSet();
      resultSet.beforeFirst();
      while (resultSet.next())
      {
         String ticker = resultSet.getString("ticker");
         Double daily_return = resultSet.getDouble("daily_return");

         if (holisticdataMap.containsKey(ticker))
         {
            holisticdataMap.get(ticker).getReturns().add(daily_return);
            holisticdataMap.get(ticker).setMaxReturns(holisticdataMap.get(ticker).getMaxReturns() + 1);
         }
         else
         {
            HolisticData hdata = new HolisticData();
            hdata.getReturns().add(daily_return);
            holisticdataMap.put(ticker, hdata);
            holisticdataMap.get(ticker).setMaxReturns(1);
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

   public double[][] getDailyReturns(String [] tickers) {
      try {
         if (tickers != null) {
            Integer smallestArray = 5000;
            for (int i =0; i < tickers.length; i++) {
               String ticker = tickers[i];
               if (holisticdataMap.containsKey(ticker))
                  smallestArray =  (holisticdataMap.get(ticker).getMaxReturns() < smallestArray) ? holisticdataMap.get(ticker).getMaxReturns() : smallestArray;
            }

            double[][] listofReturns = new double[tickers.length][smallestArray];
            double value;
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

   public double [] getFundErrorVectorArray(String [] tickers,double[][] targetPrimeAssets, double[][] weights)
   {
      try {

         double[] errorDiff = new double [weights.length];
         double [][] prodMatrix = null;
         double[][] tWeight = new double[tickers.length][1];

         double [][] fundProductWeights = new double[allPrimeAssetMap.size()][holisticdataMap.size()];
         int pRow = 0;
         int pCol = 0;
         //Collect Prime Asset Weight per fund, and create a matrix of [NUmber of P Assets]x [ Number of Funds]



         for (String pAssetClass : allPrimeAssetMap.keySet()) {

               pCol = 0;
               for (String fTicker: holisticdataMap.keySet()){

                  if (holisticdataMap.get(fTicker).getPrimeassets().containsKey(pAssetClass))
                     fundProductWeights[pRow][pCol] = holisticdataMap.get(fTicker).getPrimeassets().get(pAssetClass).getWeight();
                  else
                     fundProductWeights[pRow][pCol] = 0;

                  pCol++;
               }
               pRow++;
         }

         for (int w = 0; w < weights.length; w++ ){

            for (int i = 0; i < tickers.length; i++)
            {
               tWeight[i][0] = weights[w][i];
            }

            prodMatrix = multiplyByMatrix(fundProductWeights,tWeight);

            double product = 0.0;

            for (int row = 0; row < prodMatrix.length; row++) {
               for (int col = 0; col < prodMatrix[0].length; col++)  {
                  product = product + StrictMath.pow((targetPrimeAssets[row][0]-prodMatrix[row][0]),2);
               }
            }
            double squaredErr = StrictMath.pow(product, 0.5);
            errorDiff[w] = squaredErr;
         }
         return errorDiff;
      }
      catch (Exception ex) {

      }
      return  null;
   }

   public double [][] getCoVarFunds(double[][] mrData)
   {
      try {
         double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);

         return covarianceOfFunds;
      }
      catch (Exception ex) {

      }
      return  null;
   }

   public double [][] getWeights(CapitalMarket instanceOfCapitalMarket, String[] tickers, double[][] mrData, double[][] covarianceOfFunds)
   {
      try {
         //To use these returns, call getDailyReturns with the same tickers;
         //CapitalMarket instanceOfCapitalMarket = new CapitalMarket();


         double[] expectedReturnsOfFunds = new double [tickers.length];
         double[] lowerBound = new double [tickers.length];
         double[] upperBound = new double [tickers.length];
         int t = 0;
         for (String fTicker: tickers){

            if (holisticdataMap.containsKey(fTicker)) {
               for( String pAsstName: holisticdataMap.get(fTicker).getPrimeassets().keySet()){

                  PrimeAssetClassData pAsst = holisticdataMap.get(fTicker).getPrimeassets().get(pAsstName);
                  double expRet = pAsst.getExpectedReturn();
                  double wgt = pAsst.getWeight();
                  String pAsset =  pAsst.getPrimeAssetName();

                  if (fTicker.length() > 4) {  // Currently hardcoding it for Mutual Fund
                     lowerBound[t] = 0.0;
                     upperBound[t] = 1;
                  }
                  else {
                     lowerBound[t] = pAsst.getLbConstraint();
                     upperBound[t] = pAsst.getUbConstraint();
                  }

                  expectedReturnsOfFunds[t] = expectedReturnsOfFunds[t] + expRet * wgt;
               }
            }
            else {
               expectedReturnsOfFunds[t] = 0.0;
            }

            // lowerBound[t] = 0.0;
            // upperBound[t] = 1.0;
            //if (fTicker.length()< 5)
            //upperBound[t] = 0.2;

            t++;
         }

         //Here we evaluate the maximum expected return  of  the  Portfolio's  on
         //the Efficient Frontier.


         instanceOfCapitalMarket.setConstraints(lowerBound, upperBound);

         double minReturn1 = instanceOfCapitalMarket.minFrontierReturn(expectedReturnsOfFunds);
         double maxReturn1 = instanceOfCapitalMarket.maxFrontierReturn(expectedReturnsOfFunds);
         instanceOfCapitalMarket.calculateEfficientFrontier(
            minReturn1, // minimumExpectedReturn
            maxReturn1, // maximumExpectedReturn
            covarianceOfFunds,//Covariance matrix
            expectedReturnsOfFunds, // expectedReturns
            1000, // numberInterpolationPoints
            InvConst.PORTFOLIO_PRECISION  // precision
         );

         double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();


         return weights;
      }
      catch (Exception ex) {

      }
      return  null;
   }


   public static double[][] multiplyByMatrix(double[][] m1, double[][] m2) {
      int m1ColLength = m1[0].length; // m1 columns length
      int m2RowLength = m2.length;    // m2 rows length
      if(m1ColLength != m2RowLength) return null; // matrix multiplication is not possible
      int mRRowLength = m1.length;    // m result rows length
      int mRColLength = m2[0].length; // m result columns length
      double[][] mResult = new double[mRRowLength][mRColLength];
      for(int i = 0; i < mRRowLength; i++) {          // rows from m1
         for(int j = 0; j < mRColLength; j++) {       // columns from m2
            for(int k = 0; k < m1ColLength; k++) {    // columns from m1
               mResult[i][j] += m1[i][k] * m2[k][j];
            }
         }
      }
      return mResult;
   }
}
