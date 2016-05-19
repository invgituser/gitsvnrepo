package com.invmodel.position;

import java.util.*;

import com.invmodel.asset.data.*;
import com.invmodel.dao.data.*;
import com.invmodel.dao.invdb.*;
import com.invmodel.dao.rbsa.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.position.data.FamilyAccount;
import lpsolve.LpSolveException;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */

public class LinearOptimizer
{
   private Map<Long, FamilyAccount> familyData;

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

   public Map<Long, FamilyAccount> loadExternalPositions(Long familyacctnum)
   {
      Map<Long, FamilyAccount> data = null;
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

   public void process(Long familyacctnum, String advisor, String theme, ProfileData pdata, AssetClass aamc)
   {

      familyData = loadExternalPositions(familyacctnum);

      //HolisticOptimizedData hodata = new HolisticOptimizedData();
      int numofextacct;
      double[] accountValue = null;
      String[] tickerArray = null;
      double totalValue;

      for (Long datafamilyacctnum : familyData.keySet())
      {
         numofextacct = familyData.get(datafamilyacctnum).getAccountdetail().size();
         accountValue = familyData.get(datafamilyacctnum).getAccountValue();
         tickerArray = familyData.get(datafamilyacctnum).getTickerArray();
         totalValue = familyData.get(datafamilyacctnum).getTotalValue();

         HolisticModelOptimizer hOptimizer = HolisticModelOptimizer.getInstance();
         hOptimizer.loadFundDataFromDB(theme, tickerArray);
         hOptimizer.loadRBSATickersfromDB(theme, tickerArray);
         /*Map<String, String> allFundPrimeAssetMap;
         allFundPrimeAssetMap = hOptimizer.getAllPrimeAssetMap();

         Map<String, HolisticData> holisticdataMap;
         holisticdataMap = hOptimizer.getHolisticdataMap();

         List<String> missingPrimeAssets = new ArrayList<String>();
         List<String> includesPrimeAssets = new ArrayList<String>();
         //Collect Prime Asset Weight per fund, and create a matrix of [NUmber of P Assets]x [ Number of Funds]
         int pCol; int pRow;

         for (String pAssetClass : allFundPrimeAssetMap.keySet()) {
            for (String fTicker: holisticdataMap.keySet()){
               if (holisticdataMap.get(fTicker).getPrimeassets().containsKey(pAssetClass))   {
                  String pATicker = holisticdataMap.get(fTicker).getPrimeassets().get(pAssetClass).getTicker();
                  if(!includesPrimeAssets.contains(pATicker))
                     includesPrimeAssets.add(pATicker);
               }
            }
         }

         System.out.println("The arraylist contains the following elements: "
                               + includesPrimeAssets);
         */

         Map<String, Double> primeWeightsMap = getMapOfPrimeWeights(pdata.getAdvisor(), pdata.getTheme(), pdata, aamc);
         double[][] primeTargetWeights = getPrimeAssetWeights(primeWeightsMap);

         //Compare number of tickers in theme prime assets vs. prime assets in the funds

         PortfolioOptimizer poptimizer = PortfolioOptimizer.getInstance();
         HolisticOptimizedData hoptdata = poptimizer.getHolisticWeight(theme, tickerArray, primeTargetWeights, primeWeightsMap);
         hoptdata.setPrimeAssetInfo(primeWeightsMap);

         //This data will be based on input by fund within an account
         /*double[][] accountConstraints = new double[][] {
         {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}};
         */

         double[][] accountConstraints = familyData.get(datafamilyacctnum).getManageAccountsbyTicker();
         double[][] fundConstraints = familyData.get(datafamilyacctnum).getManageFunds();
         double[] fundWeight =   familyData.get(datafamilyacctnum).getTickerValues();
         for (int i = 0; i< tickerArray.length; i++){
            fundWeight[i] = fundWeight[i]/familyData.get(datafamilyacctnum).getTotalValue();
         }

         AllocationOptimizer allocOpt = AllocationOptimizer.getInstance();
         try
         {  double[] fundWeightsPerAccounts = null;
            int offset = 0;
               while (fundWeightsPerAccounts == null)
               {
                  double[] optFundWeigh = hoptdata.getNextWeights(hoptdata.getNextFundOffset(offset));
                  fundWeightsPerAccounts = allocOpt.AllocateToAccounts(optFundWeigh, accountValue, accountConstraints, fundConstraints, fundWeight, offset);
                  offset++;
               }
            offset = offset;
            }

         catch (LpSolveException e)
         {
            e.printStackTrace();
         }

      }
   }


   public Map<String, Double> getMapOfPrimeWeights(String advisor, String theme, ProfileData pdata, AssetClass assetClass)
   {

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
      Map<String, Double> primeWeights = new LinkedHashMap<String, Double>();
      Integer sizeofTickerList = 0;
      String addTicker = "";

      for (SecurityData sd : secCollection.getOrderedSecurityList())
      {
         addTicker = sd.getTicker();

         //if (!addTicker.toUpperCase().equals("CASH"))
         // {
            if (!tickerMap.containsKey(addTicker))
            {
               tickerMap.put(addTicker, sizeofTickerList);
               tickerList.add(addTicker);
               sizeofTickerList++;
            }
        //  }
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

         // if (!asset.getAsset().toUpperCase().equals("CASH"))
         // {
            for (PrimeAssetClassData pacd : assetdata.getOrderedPrimeAssetData())
            {
               primeWeights.put(pacd.getTicker(), (asset.getUserweight() * assetdata.getPrimeAssetweights()[offset][tickerNum++]));
            }

         // }
      }

      return primeWeights;

   }

   public double[][] getPrimeAssetWeights(Map<String, Double> primeWeights)
   {

      PortfolioOptimizer portfolioOptimizer = PortfolioOptimizer.getInstance();


      Map<String, Integer> tickerMap = new LinkedHashMap<String, Integer>();

      // Since PrimeAssetList order is different then Security List, we are putting the data in order of the security list.
      Integer sizeofPrimeTickerList = primeWeights.size();
      double[][] tmpPrimeWeights = new double[sizeofPrimeTickerList][1];
      int j = 0;
      for (String ticker : primeWeights.keySet())
      {
         if (j < sizeofPrimeTickerList)
         {
            tmpPrimeWeights[j][0] = primeWeights.get(ticker);
            j++;
         }
      }

      return tmpPrimeWeights;

   }
}
