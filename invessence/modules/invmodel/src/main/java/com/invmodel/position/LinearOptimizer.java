package com.invmodel.position;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.dao.data.*;
import com.invmodel.dao.invdb.*;
import com.invmodel.dao.rbsa.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.position.data.PositionData;
import com.invmodel.rebalance.data.CurrentHolding;
import com.invmodel.utils.MergeSort;
import lpsolve.LpSolveException;
import webcab.lib.finance.portfolio.CapitalMarket;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LinearOptimizer
{
   private Map<Long, PositionData> position;

   private static LinearOptimizer instance = null;
   private InvModelDAO invModelDAO = new InvModelDAO();

   private HolisticModelOptimizer hoptimizer;
   private HistoricalDailyReturns historicaldailyreturns;

   private LinearOptimizer()
   {
      super();
      hoptimizer = HolisticModelOptimizer.getInstance();
      historicaldailyreturns = HistoricalDailyReturns.getInstance();
   }

   public static synchronized LinearOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new LinearOptimizer();
      }

      return instance;
   }

   public Map<Long, PositionData> loadExternalPositions(Long familyacctnum) {
      Map<Long, PositionData> data = null;
      try
      {
         data = invModelDAO.loadAllExternalPositions(familyacctnum);
         return data;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (data);
   }

   public void process(Long familyacctnum, String advisor, String theme, double[][] primeTargetWeights) {

      position = loadExternalPositions(familyacctnum);

      HolisticOptimizedData hodata = new HolisticOptimizedData();
      int numofextacct;
      double[] accountValue = null; String[] tickerArray = null; double totalValue;

      for (Long datafamilyacctnum : position.keySet())
      {
         numofextacct = position.get(datafamilyacctnum).getAccountdetail().size();
         accountValue = position.get(datafamilyacctnum).getAccountValue();
         tickerArray = position.get(datafamilyacctnum).getTickerArray();
         totalValue = position.get(datafamilyacctnum).getTotalValue();
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

      //To use these returns, call getDailyReturns with the same tickers;
      // optimizer.loadFundDataFromDB(tickers);

      hoptimizer.loadFundDataFromDB(theme, tickerArray);
      CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
      double[][] mrData = historicaldailyreturns.getDailyReturnsArray(tickerArray);
      double [][] coVarFunds = hoptimizer.getCoVarFunds(mrData);
      double[][] weights = hoptimizer.getWeights(instanceOfCapitalMarket, tickerArray, mrData, coVarFunds);
      double[] risk = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(coVarFunds);
      double[] portReturns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();

      //Compute minimum error vector by comparing to target and find the best weight fit
      double[] errorDiff = hoptimizer.getFundErrorVectorArray(tickerArray,primeTargetWeights , weights);

      MergeSort mms = MergeSort.getInstance();
      int[] fundOffset = new int[errorDiff.length];
      for (int i = 0; i<errorDiff.length; i++){
         fundOffset[i]=i;
      }

      //Sort the squared error terms, and also the index which will point to the weights, risk and returns.
      mms.sort(errorDiff,fundOffset);

      //PRIME ASSET exposure can not be larger than the account exposure.
      //If PRIME ASSET funds are in IRA and it has only a 20% value than the upperbound for these
      //funds must be 0.2 or below combined
      //May have to throw out some solutions of efficient frontier where the combined numbers are higher
      //than 20%
      //Also we mau want to consturct a fundConstaint matrix similar to accountConstraint.


      double[] optFundWeight = new double[weights[0].length];
      for(int i=0; i<weights[0].length; i++){
         optFundWeight[i] = weights[fundOffset[0]][i];
      }

      hodata.setRbsatickers(tickerArray);
      hodata.setOffset(fundOffset[0]);
      hodata.setOptimizedWeights(optFundWeight);
      hodata.setRisk(risk);
      hodata.setPortReturns(portReturns);

      //This data will be based on input by fund within an account
      /*double[][] accountConstraints = new double[][] {
         {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}};*/

      double[][] accountConstraints = new double[accountValue.length][accountValue.length*tickerArray.length];

      for (int r = 0; r<accountValue.length; r++) {
         int colN = 0;
         for (int a = 0; a<accountValue.length; a++) {
            for (int f = 0; f<tickerArray.length; f++){

               accountConstraints[r][colN] = 0;

               if (r == a){

                  if (a < accountValue.length-1) {
                     if (f < 9)
                        accountConstraints[r][colN] = 1;
                     else
                        accountConstraints[r][colN] = 0;
                  }

                  else if (a == accountValue.length-1) {
                     if (f > 8)
                        accountConstraints[r][colN] = 1;
                     else
                        accountConstraints[r][colN] = 0;
                  }
               }
               else
                  accountConstraints[r][colN] = 0;
               colN++;
            }
         }
      }


      AllocationOptimizer allocOpt = AllocationOptimizer.getInstance();
      try
      {
         double[] fundWeightsPerAccounts = allocOpt.AllocateToAccounts(optFundWeight, accountValue, accountConstraints);
      }
      catch (LpSolveException e)
      {
         e.printStackTrace();
      }
   }


   public Map<String,Double>  getMapOfPrimeWeights(String advisor, String theme, ProfileData pdata, AssetClass assetClass){

      PortfolioOptimizer portfolioOptimizer = PortfolioOptimizer.getInstance();

      SecurityCollection secCollection = null;
      // Now collect all securities for this theme;
      if (secCollection == null)
      {
         secCollection = new SecurityCollection();
      }
      // if security of this theme is already loaded, then don't reload.
      // NOTE: If they individual choose the non-taxable, but is taxable, then load the taxable strategy.
      if (!secCollection.getThemeLoaded().equalsIgnoreCase(theme))
      {
         secCollection.loadDataFromDB(advisor, theme);
      }

      Map<String, Integer> tickerMap = new LinkedHashMap<String, Integer>();
      ArrayList<String> tickerList = new ArrayList<String>();
      Map<String,Double> primeWeights = new LinkedHashMap<String,Double>();
      Integer sizeofTickerList = 0;
      String addTicker = "";

      for (SecurityData sd: secCollection.getOrderedSecurityList()) {
         addTicker = sd.getTicker();
         if (!addTicker.toUpperCase().equals("CASH")) {
            if (! tickerMap.containsKey(addTicker)) {
               tickerMap.put(addTicker,sizeofTickerList);
               tickerList.add(addTicker);
               sizeofTickerList++;
            }
         }
      }

      PortfolioModel portfolioModel = new PortfolioModel();
      int offset = portfolioModel.getPortfolioIndex(pdata);

      // secCollection.doCustomSQLQuery(advisor, theme, tickerList); // Use this to load Security details for given Tickers
      for (String assetname : portfolioOptimizer.getAdvisorOrdertedAssetList(theme))
      {
         int tickerNum = 0;
         AssetData assetdata = portfolioOptimizer.getAssetData(theme, assetname);
         Asset asset = assetClass.getAsset(assetname);
         asset.setActualweight(0.0);
         asset.setValue(0.0);

         if (!asset.getAsset().toUpperCase().equals("CASH"))
         {
            for (PrimeAssetClassData pacd : assetdata.getOrderedPrimeAssetData())
            {
               primeWeights.put(pacd.getTicker(), (asset.getUserweight() * assetdata.getPrimeAssetweights()[offset][tickerNum++]));
            }

         }
      }

      return primeWeights;

   }

   public double[][] getPrimeAssetWeights(Map<String,Double> primeWeights){

      PortfolioOptimizer portfolioOptimizer = PortfolioOptimizer.getInstance();


      Map<String, Integer> tickerMap = new LinkedHashMap<String, Integer>();

      // Since PrimeAssetList order is different then Security List, we are putting the data in order of the security list.
      Integer sizeofPrimeTickerList = primeWeights.size();
      double[][] tmpPrimeWeights = new double[sizeofPrimeTickerList][1];
      int j = 0;
      for (String ticker: primeWeights.keySet())
      {
         if (j < sizeofPrimeTickerList) {
            tmpPrimeWeights[j][0] = primeWeights.get(ticker);
            j++;
         }
      }

     return tmpPrimeWeights;

   }
}
