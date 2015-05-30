package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.data.*;
import lpsolve.*;
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
   Map<String, String> allPrimeAssetMap = new LinkedHashMap<String, String>();

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

   private String getTheme(String theme) {
      if (theme == null)
         theme = "PRIME-ASSET";
      return theme;
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
         String theme = getTheme(null);

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
                                   whereStatement + " \n" +
                                 "UNION \n" +
                                   "SELECT ticker as ticker," +
                                   " ticker as indexfund, " +
                                   " theme, " +
                                   " assetclass, " +
                                   " primeassetclass, " +
                                   " sortorder, " +
                                   " lowerBound, " +
                                   " upperBound, " +
                                   " expectedReturn, " +
                                   " 1.0 as weight " +
                                   " FROM sec_prime_asset_group " +
                                   " WHERE  theme = '" + theme + "' "  +
                                   " AND status in ('A') " +
                                   " ORDER BY ticker, sortorder");
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
      statement.executeQuery("SELECT ticker, daily_return FROM rbsa.vw_rbsa_daily_returns " + whereStatement +" order by ticker, seqno desc");
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

   public double [] getFundErrorVectorArray(String [] tickers,double[][] targetOptProd, double[][] weights)
   {
      try {

         double[] errorDiff = new double [weights.length];
         double [][] prodMatrix = null;
         for (int w = 0; w < weights.length; w++ ){
            double [][] fundProductWeights = new double[allPrimeAssetMap.size()][holisticdataMap.size()];
            int pRow = 0;
            int pCol = 0;

            double[][] tWeight = new double[tickers.length][1];
            for (int i = 0; i < tickers.length; i++) {
                  tWeight[i][0] = weights[w][i];
            }

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

            prodMatrix = multiplyByMatrix(fundProductWeights,tWeight);

            double product = 0.0;

            for (int row = 0; row < prodMatrix.length; row++) {
               for (int col = 0; col < prodMatrix[0].length; col++)  {
                  product = product + StrictMath.pow((targetOptProd[row][0]-prodMatrix[row][0]),2);
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
         //To use these returns, call getDailyReturns with the same tickers;
         //CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();

         //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(mrData);
         double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);

         return covarianceOfFunds;
      }
      catch (Exception ex) {

      }
      return  null;
   }

   public double [][] getData(String [] tickers)
   {
      loadFundDataFromDB(tickers);
      double[][] mrData = null;
      mrData = getDailyReturns(tickers);
      return mrData;
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

            for( String pAsstName: holisticdataMap.get(fTicker).getPrimeassets().keySet()){

               PrimeAssetClassData pAsst = holisticdataMap.get(fTicker).getPrimeassets().get(pAsstName);
               double expRet = pAsst.getExpectedReturn();
               double wgt = pAsst.getWeight();
               String pAsset =  pAsst.getPrimeAssetName();

               expectedReturnsOfFunds[t] = expectedReturnsOfFunds[t] + expRet * wgt;
            }

            lowerBound[t] = 0.0;
            upperBound[t] = 1.0;
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
            InvConst.ASSET_PRECISION  // precision
         );

         double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();


         return weights;
      }
      catch (Exception ex) {

      }
      return  null;
   }

   public double [] getRisk(String [] tickers,CapitalMarket instanceOfCapitalMarket)
   {
      try {

         Integer numofTicker = tickers.length;

         // loadPrimeAssetDataFromDB(pAssets);
         loadFundDataFromDB(tickers);
         double[][] mrData = null;
         mrData = getDailyReturns(tickers);

         AssetParameters assetParameters = new AssetParameters();

         double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);

         double[] risk = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(covarianceOfFunds);

         return risk;
      }
      catch (Exception ex) {

      }
      return  null;
   }

   public double [] getExpReturns(String [] tickers,CapitalMarket instanceOfCapitalMarket)
   {
      double[]expReturns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();
      return expReturns;
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
