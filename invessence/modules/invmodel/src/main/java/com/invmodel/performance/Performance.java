package com.invmodel.performance;

/**
 * User: Jigar
 * Date: 6/21/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 *
 * Objectives:
 * Create asset allocation for the investment horizon
 * Create portfolio for each of the years
 * Calculate the growth rates, returns, risk and value of the portfolio for each year
 *
 */

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.performance.data.PerformanceData;
import com.invmodel.portfolio.data.Portfolio;


//Formula to convert longTermReturns to daily returns
//Effective rate for period = ((1 + annual rate)^(1 / # of periods)) – 1
//Effective rate for period  = Math.pow((1.0+ daily rate), 1/365.0)-1.0;

//Convert returns back to annualized returns
//Effective rate for period  = Math.pow((1.0+ daily rate), 365.0)-1.0;


public class Performance
{
   //private assetdao = AssetDBCollection.getInstance();
   //private SecurityDBCollection securityDao = SecurityDBCollection.getInstance();
   //private DailyReturns DailyDao = DailyReturns.getInstance();

   private static Performance instance = null;

   public static synchronized Performance getInstance()
   {
      if (instance == null)
      {
         instance = new Performance();
      }

      return instance;
   }

   public Performance()
   {

   }

   public void getPortfolioData(AssetClass[] assetData, Portfolio[] portfolioClass)
   {

      for (int investmentYear = 0; investmentYear < assetData.length; investmentYear++)
      {

         double totalMoney = portfolioClass[investmentYear].getTotalMoney();
         double totalCapitalGrowth = portfolioClass[investmentYear].getTotalCapitalGrowth();
         double totalInvested = portfolioClass[investmentYear].getActualInvestments();
         double expectedPortReturns = portfolioClass[investmentYear].getExpReturns();
         double totalPortfolioRisk = portfolioClass[investmentYear].getTotalRisk();

      }
   }

   public double[][] assetAllacationWeights(AssetClass[] aamc) throws Exception
   {
      int noOfAssetTypes = aamc[0].getOrderedAsset().size();
      double[][] assetWeight = new double[aamc.length][noOfAssetTypes];

      for (int a = 0; a < aamc.length; a++)
      {
         for (int i = 0; i < (noOfAssetTypes); i++)
         {
            String assetName = aamc[a].getOrderedAsset().get(i);
            assetWeight[a][i] = aamc[a].getAsset(assetName).getUserweight();
         }

      }

      return assetWeight;
   }

   public PerformanceData[] getPortfolioPerformance(AssetClass[] assetData, Portfolio[] portfolioData, int numOfYears)
   {

      PerformanceData[] perfData = null;

      try
      {
         for (int year = 0; year < numOfYears; year++)
         {

            perfData[year] = new PerformanceData();

            double investmentCapital = portfolioData[year].getTotalMoney();

            perfData[year].setTotalCapitalWithGains(investmentCapital);

            double portRisk = 0.0;
            double portReturns = 0.0;
            for (int numOfAssets = 0; numOfAssets < assetData[year].getOrderedAsset().size(); numOfAssets++)
            {

               String assetName = assetData[year].getOrderedAsset().get(numOfAssets);

               portRisk = portRisk + assetData[year].getAsset(assetName).getRisk() * assetData[year].getAsset(assetName).getUserweight();
               portReturns = portReturns + assetData[year].getAsset(assetName).getExpectedReturn() * assetData[year].getAsset(assetName).getUserweight();

            }
            perfData[year].setInvestmentRisk(portRisk);

            perfData[year].setInvestmentReturns(portReturns);

         }

         return perfData;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public double[][] assetPerformanceData(double invCapital, double yearlyReinvestments, AssetClass[] aamc) throws Exception
   {


      int noOfAssetTypes = aamc[0].getOrderedAsset().size();
      String[] header = new String[]{"Year", "AvgYearReturns", "Savings"};
      double[][] assetPerfData = new double[aamc.length][header.length];

/*
        double assetWeight[] = new double[noOfAssetTypes];

        for (int i = 0; i < (noOfAssetTypes); i++) {
            String assetName = aamc[0].getOrderedAsset().get(i);
            assetWeight[i] = aamc[0].getAssetWeight(assetName);
        }
*/

      for (int a = 0; a < aamc.length; a++)
      {
         double avgReturns = avgYearReturns(aamc[a]);
         assetPerfData[a][0] = 0; //Year
         assetPerfData[a][1] = avgReturns;
         assetPerfData[a][2] = invCapital;

         invCapital = invCapital + yearlyReinvestments;

         invCapital = calcAssetValue(invCapital, avgReturns);
      }

      return assetPerfData;
   }

   public double avgYearReturns(AssetClass assetclass) throws Exception
   {

      //String[] orderedAssets = assetclass.getOrderedAsset();


      int noOfAssetTypes = assetclass.getOrderedAsset().size();
      double avgYearReturns = 0.0;
      double assetWeight[] = new double[noOfAssetTypes];

      for (int i = 0; i < (noOfAssetTypes); i++)
      {
         String assetName = assetclass.getOrderedAsset().get(i);
         double avgReturns = assetclass.getAsset(assetName).getAvgReturn();
         double weight = (assetclass.getAsset(assetName).getUserweight());

         assetWeight[i] = weight;

         //Weighted average return
         avgYearReturns = avgYearReturns + weight * avgReturns;
         //avgYearReturns =  avgYearReturns  +  weight* avgReturns[0];
      }

      //Adjust expense ratio
      avgYearReturns = avgYearReturns - InvConst.MNGT_FEES;

      return avgYearReturns;
   }


   public double calcAssetValue(double invCapital, double avgYearReturns) throws Exception
   {

      double intEarned = 0;

      intEarned = avgYearReturns * invCapital;
      invCapital = invCapital + intEarned;

      return invCapital;
   }

    /*
    public double[] getPortfolioRiskReturn(String tax, AssetClass[] aamc) throws Exception{
        AssetParameters assetParameters = new AssetParameters();
        AssetDBCollection assetdao = AssetDBCollection.getInstance();

        double[][] covarianceOfFunds = null;

        try {
            // assetdao.loadDataFromDB();  // Load data from database.
            String[] indexFund = assetdao.getAssetOrderedIndex();
            HistoricalReturns hr = new HistoricalReturns();

            hr.loadHistoricalReturns();

            double[][] histReturns = hr.getHistoricalReturnsArray(indexFund);
            if (histReturns != null) {

                covarianceOfFunds = assetParameters.covarianceMatrix(histReturns);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] orderedAssets = assetdao.getOrderedAsset();

        double portfolioRisk[] = new double[aamc.length] ;

        double  assetWeight[] = new double[orderedAssets.length];

        for (int a=0; a<aamc.length; a++){
            for (int i = 0; i < (orderedAssets.length); i++) {
                String assetName = orderedAssets[i];
                double weight = (aamc[a].getAssetWeight(assetName));

                assetWeight[i] = weight;
            }

            portfolioRisk[a] = assetParameters.portfolioRisk(assetWeight,covarianceOfFunds);

        }

        return portfolioRisk;
    }

    */

    /*

    public double getSecPortRisk(String[] tickers, double[] secWeight) throws Exception{
        AssetParameters assetParameters = new AssetParameters();

        double[][] mrData = DailyDao.getDailyReturnsArray(tickers);
        double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);

        double portfolioRisk = assetParameters.portfolioRisk(secWeight,covarianceOfFunds);

        return portfolioRisk;
    }

*/


   public static double getAvgReturns(double weights, double expectedReturns)
   {

      double avgReturns = 0.0;

      avgReturns = (avgReturns + weights * expectedReturns);

      return avgReturns;
   }

}
