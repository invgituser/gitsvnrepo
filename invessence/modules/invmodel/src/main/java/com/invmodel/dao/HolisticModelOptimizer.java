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
      statement.executeQuery("SELECT ticker, daily_return FROM vw_daily_returns_Holistc_Model " + whereStatement +" order by ticker, seqno desc");
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
            //   upperBound[t] = 0.2;

            t++;
         }

         //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(mrData);
         double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);
         t = t +1;
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
         double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(covarianceOfFunds);
         double[] portReturns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();

         double [][] fundProductWeights = new double[allPrimeAssetMap.size()][holisticdataMap.size()];
         int pRow = 0;
         int pCol = 0;

         double[][] tWeight = new double[tickers.length][1];
         for (int i = 0; i < tickers.length; i++) {
               tWeight[i][0] = weights[500][i];
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

         double [][] prodMatrix = multiplyByMatrix(fundProductWeights,tWeight);

         return prodMatrix;

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
      for(int i = 0; i < mRRowLength; i++) {         // rows from m1
         for(int j = 0; j < mRColLength; j++) {     // columns from m2
            for(int k = 0; k < m1ColLength; k++) { // columns from m1
               mResult[i][j] += m1[i][k] * m2[k][j];
            }
         }
      }
      return mResult;
   }

   public double[] AllocateToAccounts(double[] optFundWeight, double[] acctW, double[][] accountConstraints) throws LpSolveException
   {
      //This is a solver which figures out how to allocate funds to various accounts.
      //Solver requires number of inequalities and constraints to come up with an optimal solution
      //on allocating number of funds to accounts.

      //fund weights are passed to the solver once the optimalFundWeight is picked from the
      // efficient frontier in getOptimalFundWeight method

      //account weights are passed to solver based on what percent of dollars are in each account

      //objective function is a double[] of 1. Number of 1 must match number of variables trying to solve.

      LpSolve lp;
      int Ncol, ret = 0;
      int j = 0;

      //We will build the model row by row
      //So we start with creating a model with 0 rows and 6 columns
      //Number of variables = (number of accounts * number of funds)

      int numF = optFundWeight.length;
      int numA = acctW.length;

      Ncol = numA*numF;

       /* create space large enough for one row */
      int[] colno = new int[Ncol];
      double[] row = new double[Ncol];

      /* there are account*Funds number of variables in the model */
      lp = LpSolve.makeLp(0, Ncol);
      if(lp.getLp() == 0)
         ret = 1; /* couldn't construct a new model... */

      if(ret == 0) {
         //The solver is solving n varibales to find an optimal allocation of funds across
         //number of accounts. Number of variables which must be solved equal
         //funds*accounts. If there are 4 accounts and 5 funds then number of variables
         // would be 20.

         // let us name our variables. Not required, but can be useful for debugging
         // Default is c1, c2, c3...each fund has a weight in the account
         //Naming w1A1 is fund1's weight in Account 1...we are solving for all
         //the weights in various accounts
         int col =0;
         for (int act = 1; act<=numA; act++) {
            for(int fund = 1; fund<= numF ; fund++){
               col++;
               lp.setColName(col, "W"+fund+"A"+act);
            }
         }

         //makes building the model faster if it is done rows by row
         lp.setAddRowmode(true);

         //Add first constraint, sum of all the variables must be less than equal to 1
         //w1A1+w1A2+w1A3+w2A1+w2A2+w2A3...WiAj <=1
         for (j = 0; j< Ncol; j++){
            colno[j] = j+1; /* first column */
            row[j] = 1.0;
         }
         //add the row constarint to the lpsolve
         lp.addConstraintex(j, row, colno, LpSolve.LE, 1.0);
      }



      if(ret == 0) {
         //Set each variable constraints
         //Number of variables are equal to funds*accounts
         // WiAj >= 0.0;
         //Number of equations = number of variables = Ncol;
         //If there are 2 funds and 3 accounts then there are 6 variables we are solving for
         // example 1 0 0 0 0 0;
         // example 0 1 0 0 0 0;

         int c = 0;
         for (int r = 0; r< numF*numA; r++){
            for(c = 0; c< Ncol; c++){

               colno[c] = c+1; /* first column */
               if (c == r) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }
            /* add the row to lpsolve */
            lp.addConstraintex(c, row, colno, LpSolve.GE, 0.0);
         }

         /* add the row to lpsolve */
      }

      if(ret == 0) {
         //Set up account level constraints
         //Number of constraints = number of accounts
         // Allocate only value available in the account to various funds available
         // Example 2 funds in three accounts with value of 0.2, 0.4, and 0.4
         //w1A1+w2A1+w3A1+0+0 <= 0.2 (example)
         //1 1 0 0 0 0 LE 0.2
         //0 0 1 1 0 0 LE 0.4
         //0 0 0 0 1 1 LE 0.4

         //row array is what we need to establish to include funds in an account
         //by setting value to 1 we include the fund otherwise we skip the fund.
         //accW array is the weight of each account, which is simply $account/$total in all accounts

         int c = 0;

         for (int r = 0; r < numA; r++){

            /*for(c = 0; c< Ncol; c++){

               int acctN = c/numA;
               int index = numA * acctN + r;

               // first column
               colno[c] = c+1;
               if (c == index) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }*/

            for(c = 0; c< Ncol; c++){
               // first column
               colno[c] = c+1;
            }
            /* add the row to lpsolve */

            lp.addConstraintex(c, accountConstraints[r], colno, LpSolve.LE, acctW[r]);
            //lp.addConstraintex(c, row, colno, LpSolve.EQ, acctW[r]);
         }
      }

      if(ret == 0) {
         //Set fund level consraints
         //all funds must add to 1
         //Number of contstraints equal to number of funds
         //if there were two funds with fund weights of 0.4 and 0.6
         //construct first row (w1A1+0+w1A2+0+w1A3+0 <=0.4)
         //construct second row (0+w2A1+0+w2A2+0+w2A3 <=0.6)

         int c = 0;

         for (int r = 0; r< numF; r++){
            for(c = 0; c< Ncol; c++){

               colno[c] = c+1; /* first column */
               int column = r+1;
               String colName = "W" + column;
               if (lp.getColName(c + 1).contains(colName))
                  row[c] = 1.0;
                  //if (c>= r*numA && c < ((r+1)*numA)) {
                  //   row[c] = 1.0;
                  //}
               else
                  row[c] = 0.0;
            }
            /* add the row to lpsolve */
            lp.addConstraintex(c, row, colno, LpSolve.LE, optFundWeight[r]);
         }
      }


      if(ret == 0) {
         //Create an objective function for the solver
         lp.setAddRowmode(false); /* rowmode should be turned off again when done building the model */

         /* set the objective function W1A1 + W1A2 +...+ WiAj */
         //row arry will be filled with 1. length is number of variables.
         j = 0;
         for (j = 0; j< Ncol; j++){
            colno[j] = j+1; /* first column */
            row[j] = 1.0;
         }
         /* set the objective in lpsolve */
         lp.setObjFnex(j, row, colno);
         //lp.setObjFnex(j, objF, colno);
      }

      if(ret == 0) {
         /* set the object direction to maximize */
         lp.setMaxim();

         /* just out of curioucity, now generate the model in lp format in file model.lp */
         lp.writeLp("model.lp");

         /* I only want to see important messages on screen while solving */
         lp.setVerbose(LpSolve.IMPORTANT);

         /* Now let lpsolve calculate a solution */

         ret = lp.solve();
         if(ret == LpSolve.OPTIMAL)
            ret = 0;
         else
            ret = 5;
      }

      if(ret == 0) {
         /* a solution is calculated, now lets get some results */

         /* objective value */
         System.out.println("Objective value: " + lp.getObjective());

         /* variable values */
         lp.getVariables(row);
         for(j = 0; j < Ncol; j++)
            System.out.println(lp.getColName(j + 1) + ": " + row[j]);

         /* we are done now */
      }

       /* clean up such that all used memory by lpsolve is freed */
      if(lp.getLp() != 0)
         lp.deleteLp();

      return(row);
   }
}
