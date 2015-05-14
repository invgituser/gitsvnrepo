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


public class PortfolioPerformance
{
   //private assetdao = AssetDBCollection.getInstance();
   //private SecurityDBCollection securityDao = SecurityDBCollection.getInstance();
   //private DailyReturns DailyDao = DailyReturns.getInstance();

   private static PortfolioPerformance instance = null;

   public static synchronized PortfolioPerformance getInstance()
   {
      if (instance == null)
      {
         instance = new PortfolioPerformance();
      }

      return instance;
   }

   public PortfolioPerformance()
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

   public PerformanceData[] getPortfolioPerformance(Portfolio[] portfolioClass, int numOfYears, int currentYear)
   {

      PerformanceData[] perfData = new PerformanceData[numOfYears];
      double portGrowth = 0.0;
      double investmentCapital = 0.0;

      try
      {

         perfData[0] = new PerformanceData();
         perfData[0].setInvestmentCapital(portfolioClass[currentYear].getTotalMoney());
         perfData[0].setInvestmentCost(portfolioClass[currentYear].getTotalCost());
         perfData[0].setInvestmentReturns(portfolioClass[currentYear].getExpReturns());
         perfData[0].setInvestmentRisk(portfolioClass[currentYear].getTotalRisk());
         perfData[0].setUpperBand1(0);
         perfData[0].setUpperBand2(0);
         perfData[0].setLowerBand1(0);
         perfData[0].setLowerBand2(0);

         for (int year = 1; year < numOfYears; year++)
         {
            perfData[year] = new PerformanceData();


            investmentCapital = perfData[year-1].getInvestmentCapital();

            perfData[year].setInvestmentCost((portfolioClass[currentYear].getTotalCost() /
                                                portfolioClass[currentYear].getTotalMoney() )* investmentCapital +
                                                perfData[year-1].getInvestmentCost());

            perfData[year].setInvestmentCapital(portfolioClass[currentYear].getExpReturns() *
                                                   investmentCapital + investmentCapital -
                                                   (portfolioClass[currentYear].getTotalCost() /
                                                      portfolioClass[currentYear].getTotalMoney() )*
                                                      investmentCapital);

            perfData[year].setInvestmentReturns(portfolioClass[currentYear].getExpReturns());

            perfData[year].setInvestmentRisk(portfolioClass[currentYear].getTotalRisk());

            portGrowth = portfolioClass[currentYear].getExpReturns() * investmentCapital + portGrowth -
               (portfolioClass[currentYear].getTotalCost() / portfolioClass[currentYear].getTotalMoney() )*
                  investmentCapital;

            double upper1 = ( portfolioClass[currentYear].getTotalRisk() * investmentCapital ) + investmentCapital;
            double upper2 = ( 2 * portfolioClass[currentYear].getTotalRisk() * investmentCapital ) + investmentCapital;
            double lower1 = ( -1 * portfolioClass[currentYear].getTotalRisk() * investmentCapital ) + investmentCapital;
            double lower2 = ( -2 * portfolioClass[currentYear].getTotalRisk() * investmentCapital) + investmentCapital;

            perfData[year].setUpperBand1(upper1);
            perfData[year].setUpperBand2(upper2);
            perfData[year].setLowerBand1(lower1);
            perfData[year].setLowerBand2(lower2);
            perfData[year].setInvevestmentEarnings(portGrowth);

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

   public static double getAvgReturns(double weights, double expectedReturns)
   {

      double avgReturns = 0.0;

      avgReturns = (avgReturns + weights * expectedReturns);

      return avgReturns;
   }

}
