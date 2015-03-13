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
public class PortfolioOptimizer
{

   private static PortfolioOptimizer instance = null;
   private final Logger logger = Logger.getLogger(PortfolioOptimizer.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   private Map<String, ArrayList<String>> themeAssetMap;
   private Map<String, AssetData> assetDataMap;

   public static synchronized PortfolioOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new PortfolioOptimizer();
      }

      return instance;
   }

   private PortfolioOptimizer()
   {
      super();
      themeAssetMap = new LinkedHashMap<String, ArrayList<String>>();
      assetDataMap = new HashMap<String, AssetData>();
   }

   public void loadDataFromDB(String theme) {
      write.lock();
      try {
         themeAssetMap.clear();
         assetDataMap.clear();

         logger.info("Loading ASSET information from DB");
         loadAssetDataFromDB(theme);
         logger.info("Loading Prime Asset information from DB");
         loadPrimeAssetsFromDB(theme);

      }
      catch (Exception ex) {

      }
      finally
      {
         write.unlock();
      }

   }

   public void refreshDataFromDB() {

      write.lock();
      try {
         themeAssetMap.clear();
         assetDataMap.clear();

         logger.info("Loading ASSET information from DB");
         loadAssetDataFromDB(null);
         logger.info("Loading Prime Asset information from DB");
         loadPrimeAssetsFromDB(null);
         logger.info("Optimize Asset Class");
         saveAssetWeights();
         logger.info("Optimize Prime Asset Class");
         savePrimeAssetWeights();

      }
      catch (Exception ex) {

      }
      finally
      {
         write.unlock();
      }
   }

   private String checkThemeName(String theme) {
      if (theme == null || theme.length() == 0)
         theme = "DISCARD";
      return theme.toUpperCase();
   }

   private String checkAssetName(String assetName) {
      if (assetName == null || assetName.length() == 0)
         assetName = "DISCARD";

      return assetName.toUpperCase();
   }


   private String buildAssetKey(String theme, String assetName) {

      return checkThemeName(theme) + "." + checkAssetName(assetName);
   }

   public ArrayList<String> getAdvisorList()
   {
      ArrayList<String> themeList = new ArrayList<String>();
      for (String theme : themeAssetMap.keySet())
         themeList.add(theme);
      return themeList;
   }

   public ArrayList<String> getAdvisorOrdertedAssetList(String theme)
   {
      return themeAssetMap.get(checkThemeName(theme));
   }

   public void addAssetClass(String theme, String assetName, AssetData assetData) {
      String key;

      key = buildAssetKey(theme, assetName);
      theme = checkThemeName(theme);
      if (! key.contains("DISCARD")) {
         if (! assetDataMap.containsKey(key)) {
            if (! themeAssetMap.containsKey(theme)) {
               ArrayList<String> assetList = new ArrayList<String>();
               assetList.add(assetName);
               themeAssetMap.put(theme, assetList);
            }
            else {
               themeAssetMap.get(theme).add(assetName);
            }
         }
         assetDataMap.put(key,assetData);
      }

   }

   public ArrayList<AssetData> getAssetDataList(String theme) {
      ArrayList<AssetData> assetDataList = new ArrayList<AssetData>();

      for (String assetName : themeAssetMap.get(checkThemeName(theme))) {
         String key = buildAssetKey(checkThemeName(theme), assetName);
            assetDataList.add(assetDataMap.get(key));
      }

      return assetDataList;
   }

   public AssetData getAssetData(String theme, String assetName) {
      read.lock();
      try
      {
         String key = buildAssetKey(checkThemeName(theme), assetName);
         return assetDataMap.get(key);
      }
      catch (Exception ex) {

      }
      finally {
         read.unlock();
      }
       return null;
   }


   public ArrayList<PrimeAssetClassData> getPrimeAssetDataList(String theme) {
      ArrayList<PrimeAssetClassData> primeassetdata = new ArrayList<PrimeAssetClassData>();
      read.lock();
      try
      {
         for (String asset : themeAssetMap.get(checkThemeName(theme))) {
            String key = buildAssetKey(theme,asset);
            for (String primeasset : assetDataMap.get(key).getOrderedPrimeAssetList()){
               primeassetdata.add(assetDataMap.get(key).getPrimeAssetData(primeasset));
            }
         }
      }
      catch (Exception ex) {

      }
      finally {
         read.unlock();
      }
      return primeassetdata;
   }

   public PrimeAssetClassData getPrimeAssetData(String theme, String assetname, String primeassetclass) {
      read.lock();
      try
      {
         String key = buildAssetKey(theme,assetname);
         return assetDataMap.get(key).getPrimeAssetData(primeassetclass);
      }
      catch (Exception ex) {

      }
      finally {
         read.unlock();
      }
      return null;
   }

   public String[] getOrderedAsset(String theme)
   {
      read.lock();
      try
      {
         theme = checkThemeName(theme);
         if (themeAssetMap.containsKey(theme)) {
            String[] asset = new String[themeAssetMap.get(theme).size()];
            for (int i = 0; i < themeAssetMap.get(theme).size(); i++)
            {
               String assetname = checkAssetName(themeAssetMap.get(theme).get(i));
               String key = buildAssetKey(theme, assetname);
               asset[i] = assetDataMap.get(key).getAsset();
            }
            return asset;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   private void setAssetdata(String advisor, String assetName, String displayName,
                             double lbConstraint, double ubConstraint,
                             String index, double averageReturn,
                             String color, double risk_adjustment, double end_allocation, int sortorder)
   {
      try
      {
         AssetData data = new AssetData(advisor,
                                        assetName,
                                        displayName,
                                        lbConstraint,
                                        ubConstraint,
                                        index,
                                        averageReturn,
                                        color,
                                        risk_adjustment,
                                        end_allocation,
                                        sortorder
         );
         addAssetClass(advisor, assetName, data);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   private void loadAssetDataFromDB(String theme)
   {
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         String where_clause="WHERE status in ('A') \n";
         if (theme != null && ! theme.equalsIgnoreCase("ALL")) {
            where_clause = "AND theme = '" + theme + "'\n";
         }
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT theme,\n" +
                                   "    assetclass,\n" +
                                   "    ticker,\n" +
                                   "    displayName,\n" +
                                   "    lowerBound,\n" +
                                   "    upperBound,\n" +
                                   "    color,\n" +
                                   "    averageReturn,\n" +
                                   "    riskAdjustment,\n" +
                                   "    endAllocation,\n" +
                                   "    sortorder\n" +
                                   "FROM sec_assetclass_group\n" +
                                   where_clause +
                                   "order by theme, sortOrder");

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            setAssetdata(resultSet.getString("theme"),
                         resultSet.getString("assetclass"),
                         resultSet.getString("displayName"),
                         resultSet.getDouble("lowerBound"),
                         resultSet.getDouble("upperBound"),
                         resultSet.getString("ticker"),
                         resultSet.getDouble("averageReturn"),
                         resultSet.getString("color"),
                         resultSet.getDouble("riskAdjustment"),
                         resultSet.getDouble("endAllocation"),
                         resultSet.getInt("sortorder")
            );
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

   private void setSecurityData(String theme, String primeassetname,
                               String ticker,
                               String assetname, String subassetname, String type, String style,
                               double expenseRatio, double adv3Month,
                               double aum, double beta, double riskSTD,
                               double expectedReturn,
                               double ubConstraint, double lbConstraint, double yield, int sortorder) {
      try {
         String key = buildAssetKey(theme,assetname);
         if (! key.contains("DISCARD"))  {
            PrimeAssetClassData primeassetdata = new PrimeAssetClassData
               (theme, primeassetname, ticker, assetname, subassetname,
                type, style, expenseRatio, adv3Month, aum, beta,
                riskSTD, expectedReturn, ubConstraint, lbConstraint, yield, sortorder);

            assetDataMap.get(key).addPrimeAsset(primeassetname, primeassetdata);
         }
      }
      catch (Exception ex) {
      }
   }


   private void  loadPrimeAssetsFromDB(String theme) {
      //logger.info("Loading Advisor Security from DB");
      Connection connection = null;
      Statement s = null;
      ResultSet rs = null;
      try
      {
         String where_clause="WHERE status in ('A') \n";
         if (theme != null && ! theme.equalsIgnoreCase("ALL")) {
            where_clause = "AND theme = '" + theme + "'\n";
         }
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         s = connection.createStatement();
         s.executeQuery("SELECT theme,\n" +
                           "    assetclass,\n" +
                           "    subclass,\n" +
                           "    primeassetclass,\n" +
                           "    type,\n" +
                           "    style,\n" +
                           "    sortorder,\n" +
                           "    ticker,\n" +
                           "    status,\n" +
                           "    lowerBound,\n" +
                           "    upperBound,\n" +
                           "    expenseRatio,\n" +
                           "    expectedReturn,\n" +
                           "    adv3months,\n" +
                           "    aum,\n" +
                           "    beta,\n" +
                           "    securityRiskSTD,\n" +
                           "    yield,\n" +
                           "    sortorder\n" +
                           "FROM vw_primeassets \n" +
                           where_clause +
                           "\t order by sortorder \n"
         );

         // Make sure to keep track of this position.

         rs = s.getResultSet();
         // get data row from table.
         while (rs.next())
         {
            setSecurityData(
               rs.getString("theme"), // String groupname
               rs.getString("primeassetclass"), // String theme
               rs.getString("ticker"), // String ticker
               rs.getString("assetclass"),  // String asset type
               rs.getString("subclass"), // String asset subtype , was "subclass" - changed to "subtype" JAV 1/2/2014
               rs.getString("type"),  // String type
               rs.getString("style"),  // String style
               rs.getDouble("expenseRatio"),  // double expenseRatio
               rs.getDouble("adv3months"), // double adv3Month
               rs.getDouble("aum"),          // double aum
               rs.getDouble("beta"),         // double beta
               rs.getDouble("securityRiskSTD"), // double riskSTD
               rs.getDouble("expectedReturn"), // double expectedReturn
               rs.getDouble("upperBound"), // double ubConstraint
               rs.getDouble("lowerBound"), // double lbConstraint
               rs.getDouble("yield"),//double yields
               rs.getInt("sortorder") //double sortorder
            );

         }
         //System.out.println (count + " rows were retrieved");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         DbUtils.closeQuietly(rs);
         DbUtils.closeQuietly(s);
         DbUtils.closeQuietly(connection);
      }
   }

   private void saveAssetWeights()
   {
      try
      {
         ArrayList asset = null;
         DailyReturns dailyReturns = DailyReturns.getInstance();
         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();
         if(themeAssetMap.size() > 0) {
            for (String theme : themeAssetMap.keySet() ) {
               String[] indexFund = getAssetOrderedIndex(theme);
               if (indexFund == null)
                  return;
               double[][] histReturns = dailyReturns.getDailyReturnsArray(indexFund);
               if (histReturns != null)
               {
                  //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(histReturns);
                  double[][] covarianceOfFunds = assetParameters.covarianceMatrix(histReturns);

                  // Here we evaluate the maximum expected return  of  the  Portfolio's  on
                  // the Efficient Frontier.

                  double[] lowerBound = getAssetOrderedLowerBound(theme);
                  double[] upperBound = getAssetOrderedUpperBound(theme);

                  instanceOfCapitalMarket.setConstraints(lowerBound, upperBound);

                  double[] yield = getAssetOrderedAvgReturns(theme);
                  double minReturn1 = instanceOfCapitalMarket.minFrontierReturn(yield);
                  double maxReturn1 = instanceOfCapitalMarket.maxFrontierReturn(yield);
                  instanceOfCapitalMarket.calculateEfficientFrontier(
                     minReturn1, // minimumExpectedReturn
                     maxReturn1, // maximumExpectedReturn
                     covarianceOfFunds,//Covariance matrix
                     yield, // expectedReturns
                     InvConst.ASSET_INTERPOLATION, // numberInterpolationPoints
                     InvConst.ASSET_PRECISION  // precision
                  );

                  //double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks( covarianceOfFunds);
                  double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();

                  if (weights.length > 0) {
                     int count=0;
                     for (String assetname : getAdvisorOrdertedAssetList(theme)) {
                        String key = buildAssetKey(theme,assetname);
                        double[] wght = new double[weights.length];
                        for (int i=0; i < weights.length; i++)
                           wght[i] = weights[i][count];

                        assetDataMap.get(key).setWeights(wght);
                        count++;
                     }
                  }

               }
            }
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   private double[] getAssetOrderedLowerBound(String groupname)
   {
      String theme;
      read.lock();
      try
      {
         theme = checkThemeName(groupname);
         if (themeAssetMap.containsKey(theme)) {
            double[] value = new double[themeAssetMap.get(theme).size()];
            for (int i = 0; i < themeAssetMap.get(theme).size(); i++)
            {
               String assetname = checkAssetName(themeAssetMap.get(theme).get(i));
               String key = buildAssetKey(theme, assetname);
               value[i] = assetDataMap.get(key).getLbConstraint();
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   private double[] getAssetOrderedUpperBound(String groupname)
   {
      String theme;
      read.lock();
      try
      {
         theme = checkThemeName(groupname);
         if (themeAssetMap.containsKey(theme)) {
            double[] value = new double[themeAssetMap.get(theme).size()];
            for (int i = 0; i < themeAssetMap.get(theme).size(); i++)
            {
               String assetname = checkAssetName(themeAssetMap.get(theme).get(i));
               String key = buildAssetKey(theme, assetname);
               value[i] = assetDataMap.get(key).getUbConstraint();
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   private String[] getAssetOrderedIndex(String groupname)
   {
      String theme;
      read.lock();
      try
      {
         theme = checkThemeName(groupname);
         if (themeAssetMap.containsKey(theme)) {
            String[] index = new String[themeAssetMap.get(theme).size()];
            int i=0;
            for (String assetname : themeAssetMap.get(theme))
            {
               String key = buildAssetKey(theme, assetname);
               index[i++] = assetDataMap.get(key).getIndex();
            }
            return index;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }




   private void savePrimeAssetWeights()
   {
      String groupkey = null;
      DailyReturns dailyReturns = DailyReturns.getInstance();
      try {

         Integer numofTicker;
         Integer numofPrimeAssets;
         for (String theme: themeAssetMap.keySet()) {
            for (String assetName : themeAssetMap.get(theme)) {
               String key = buildAssetKey(theme,assetName);
               ArrayList<PrimeAssetClassData> pacd = assetDataMap.get(key).getOrderedPrimeAssetData();
               if (pacd != null && pacd.size() > 0) {
                  numofTicker = pacd.size();
                     CapitalMarket capMarketData = null;
                     double[][] mrData = null;
                     double [] historicalReturns = new double[numofTicker];
                     double [] lbConstraints = new double[numofTicker];
                     double [] ubConstraints = new double[numofTicker];
                     double [] secRisk = new double[numofTicker];
                     double [] yield = new double[numofTicker];
                     String [] tickers = new String[numofTicker];

                  if (numofTicker >= 1) {
                     for (int j=0; j < numofTicker; j++) {
                        lbConstraints[j] =pacd.get(j).getLbConstraint();
                        ubConstraints[j] = pacd.get(j).getUbConstraint();
                        historicalReturns[j] = pacd.get(j).getExpectedReturn();
                        secRisk[j] = pacd.get(j).getRiskSTD();
                        yield[j] = pacd.get(j).getYield();
                        tickers[j] = pacd.get(j).getTicker();
                        if(theme.toUpperCase().contains("INCOME"))
                        {
                           historicalReturns[j]= historicalReturns[j] * yield[j];
                        }
                     }


                     mrData = dailyReturns.getDailyReturnsArray(tickers);
                     capMarketData = getCapitalMarketData(tickers, lbConstraints, ubConstraints, historicalReturns, mrData);
                  }

                     assetDataMap.get(key).setPrimeAssetreturns(getPortfolioAssetExpectedReturns(capMarketData, historicalReturns, tickers.length));
                     assetDataMap.get(key).setPrimeAssetweights(getPortfolioWeights(capMarketData, tickers.length));
                     assetDataMap.get(key).setPrimeAssetrisk(getPortfolioAssetRisk(capMarketData, numofTicker, mrData, secRisk[0]));
               }
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public double getRiskAdjustment(String theme, String assetName)
   {

      String key;
      read.lock();
      try
      {
         double value = 0.0;

         key = buildAssetKey(checkThemeName(theme), assetName);
         value =  assetDataMap.get(key).getRisk_adjustment();


         return value;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return  0.0;

   }

   public double getEnd_allocation(String theme, String assetName)
   {

      String key;
      read.lock();
      try
      {
         double value = 0.0;

            key = buildAssetKey(checkThemeName(theme), assetName);
            value =  assetDataMap.get(key).getEnd_allocation();


         return value;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return  0.0;

   }

   public double[] getAssetOrderedAvgReturns(String theme)
   {

      String key;
      read.lock();
      try
      {
         double[] value = new double[themeAssetMap.get(checkThemeName(theme)).size()];
         int i =0;
         for (String assetName : themeAssetMap.get(checkThemeName(theme))) {
            key = buildAssetKey(checkThemeName(theme), assetName);
            value[i++] =  assetDataMap.get(key).getAverageReturn();
         }

         return value;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;

   }

   public String[] getAssetOrderedColor(String theme)
   {
      String key;
      read.lock();
      try
      {
         String[] value = new String[themeAssetMap.get(checkThemeName(theme)).size()];
         int i =0;
         for (String assetName : themeAssetMap.get(checkThemeName(theme))) {
            key = buildAssetKey(checkThemeName(theme), assetName);
            value[i++] =  assetDataMap.get(key).getColor();
         }

         return value;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public double[] getAssetOrderedWeight(String theme, int offset)
   {
      String key;
      read.lock();
      try
      {
         double[] value = new double[themeAssetMap.get(checkThemeName(theme)).size()];
         int i =0;
         for (String assetName : themeAssetMap.get(checkThemeName(theme))) {
            key = buildAssetKey(checkThemeName(theme), assetName);
            value[i++] =  assetDataMap.get(key).getWeight(offset);
         }

         return value;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }


   private CapitalMarket getCapitalMarketData(String[] tickers,
                                             double[] lbConstraints, double[] ubConstraints,
                                             double[] expectedReturnsOfFunds, double[][] mrData)
   {

      double[][] weights;

      double[] risk = null;
      double[] returns = null;
      try
      {

         // Should never happen
         if (tickers == null || tickers.length < 1)
         {
            return null;
         }

         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();

         if (tickers.length > 1)
         {
            double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);
            // Here we evaluate the maximum expected return  of  the  Portfolio's  on
            // the Efficient Frontier.
            double maxReturn = instanceOfCapitalMarket.maxFrontierReturn(expectedReturnsOfFunds);

            // Here we evaluate the minimum expected return of the Portfolio's on  the
            // Efficient Frontier.
            double minReturn = instanceOfCapitalMarket.minFrontierReturn(expectedReturnsOfFunds);
            minReturn = (minReturn < 0) ? 0 : minReturn;

            //Add upperbound and lowerbound constraints
            instanceOfCapitalMarket.setConstraints(lbConstraints, ubConstraints);

            instanceOfCapitalMarket.calculateEfficientFrontier(
               0.0,0.12,
               covarianceOfFunds,//Covariance matrix
               expectedReturnsOfFunds, // expectedReturns
               InvConst.PORTFOLIO_INTERPOLATION, // numberInterpolationPoints
               InvConst.PORTFOLIO_PRECISION // precision
            );

            risk = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(covarianceOfFunds);
         }

         return instanceOfCapitalMarket;


      }
      catch (Exception e)
      {
         e.printStackTrace();
         weights = new double[1][1];
         weights[0][0] = 1.0;
         return null;
      }


   }

   private double[][] getPortfolioWeights(CapitalMarket capMarketData, Integer numTickers)
   {
      double[][] weights;

      try
      {
         if (numTickers == 1)
         {
            weights = new double[InvConst.PORTFOLIO_INTERPOLATION][numTickers];
            for (int i = 0; i < InvConst.PORTFOLIO_INTERPOLATION; i++)
            {
                 weights[i][0] = 1.0;
            }
            return weights;
         }
         else
         {
            weights = capMarketData.getEfficientFrontierAssetWeights();
            return weights;
         }
      }
      catch (Exception e)
      {

         e.printStackTrace();

         weights = new double[1][1];
         weights[0][0] = 1.0;
         return weights;

      }
   }

   private double[] getPortfolioAssetRisk(CapitalMarket capMarketData, Integer numTickers, double[][] mrData, double secRisk)
   {
      double[] risk = null;

      AssetParameters assetParameters = new AssetParameters();

      try
      {
         if (numTickers == 1)
         {
            risk = new double[InvConst.PORTFOLIO_INTERPOLATION];
            for (int i = 0; i < InvConst.PORTFOLIO_INTERPOLATION; i++){
               risk[i] = secRisk;
            }
            return risk;
         }
         else
         {

            double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);
            risk = capMarketData.getEfficientFrontierPortfolioRisks(covarianceOfFunds);
            for (int i = 0; i < risk.length; ++i)
            {
               risk[i] = StrictMath.sqrt(253.0) * risk[i];
            }
         }
         return risk;

      }
      catch (Exception e)
      {

         e.printStackTrace();

         risk = new double[1];
         risk[0] = 0.0;
         return risk;

      }
   }

   private double[] getPortfolioAssetExpectedReturns(CapitalMarket capMarketData, double[] histReturns, Integer numTickers)
   {
      double[] assetReturns = null;

      try
      {
         if (numTickers == 1)
         {
            assetReturns = new double[InvConst.PORTFOLIO_INTERPOLATION];
            for (int j = 0; j < InvConst.PORTFOLIO_INTERPOLATION; j++)
            {
               assetReturns[j] = histReturns[0];
            }
            return assetReturns;
         }
         else
         {
              assetReturns = capMarketData.getEfficientFrontierExpectedReturns();
         }

         return assetReturns;


      }
      catch (Exception e)
      {

         e.printStackTrace();

         assetReturns = new double[1];
         assetReturns[0] = 0.0;
         return assetReturns;

      }
   }

   private double[][] getCoVariance(double[][] historicalReturns) throws Exception
   {
      AssetParameters instanceOfAssetParameters = new AssetParameters();

      double[][] coVarMatrix = instanceOfAssetParameters.covarianceMatrix(historicalReturns);

      return coVarMatrix;
   }
}
