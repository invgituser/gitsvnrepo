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

import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.performance.data.PerformanceData;
import com.invmodel.portfolio.data.Portfolio;
import com.invmodel.inputData.*;


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

       double portGrowth = 0.0;
      double investmentCapital = 0.0;
      double totalcapitalgain = 0.0;
      PerformanceData prevYearPerformanceData;

      try
      {
         numOfYears = (numOfYears <= 0) ? 1 : numOfYears;
         PerformanceData[] perfData = new PerformanceData[numOfYears];
         Double investmentReturn, investmentRisk, invested, recurring, totalCost;
         perfData[0] = new PerformanceData();
         invested = portfolioClass[currentYear].getTotalMoney();
         recurring = portfolioClass[currentYear].getRecurInvestments();
         investmentReturn = portfolioClass[currentYear].getExpReturns();
         investmentRisk = portfolioClass[currentYear].getTotalRisk();
         totalCost = portfolioClass[currentYear].getTotalCost();
         perfData[0].setTotalCapitalWithGains(invested);
         perfData[0].setTotalCost(0.0);
         perfData[0].setInvestmentReturns(investmentReturn);
         perfData[0].setInvestmentRisk(investmentRisk);
         perfData[0].setUpperBand1(invested);
         perfData[0].setUpperBand2(invested);
         perfData[0].setLowerBand1(invested);
         perfData[0].setLowerBand2(invested);
         perfData[0].setInvestedCapital(invested);
         perfData[0].setRecurInvestments(recurring);

         for (int year = 1; year < numOfYears; year++)
         {
            perfData[year] = new PerformanceData();
            prevYearPerformanceData =  perfData[year-1];



            investmentCapital = prevYearPerformanceData.getTotalCapitalWithGains() +
               prevYearPerformanceData.getRecurInvestments();
            Double cost = (totalCost / invested) * investmentCapital;
            perfData[year].setTotalCost(cost + prevYearPerformanceData.getTotalCost());

            totalcapitalgain = (investmentReturn * investmentCapital + investmentCapital - cost);
            portGrowth = investmentReturn * investmentCapital + portGrowth - cost;

            perfData[year].setTotalCapitalWithGains(totalcapitalgain);

            perfData[year].setInvestedCapital(prevYearPerformanceData.getInvestedCapital() +
                                                 prevYearPerformanceData.getRecurInvestments());
            perfData[year].setRecurInvestments(prevYearPerformanceData.getRecurInvestments());

            perfData[year].setInvestmentReturns(investmentReturn);

            perfData[year].setInvestmentRisk(investmentRisk);


            double upper1 = ( investmentRisk * investmentCapital ) + investmentCapital;
            double upper2 = ( 2 * investmentRisk * investmentCapital ) + investmentCapital;
            double lower1 = ( -1 * investmentRisk * investmentCapital ) + investmentCapital;
            double lower2 = ( -2 * investmentRisk * investmentCapital) + investmentCapital;

            perfData[year].setUpperBand1(upper1);
            perfData[year].setUpperBand2(upper2);
            perfData[year].setLowerBand1(lower1);
            perfData[year].setLowerBand2(lower2);
            perfData[year].setTotalGains(portGrowth);
         }

         return perfData;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public void calcGrowthInfo(PerformanceData[] performancedata, int years, ProfileData pfdata)
   {
      int termyear = 0;

      Double goalAmount, interestRate, actualInitialAmount, recurringAmount, calcRecurringAmount;
      ArrayList<Double> growth = new ArrayList<Double>();
      try
      {
         if (pfdata.getGoalData() == null)
         {
            return;
         }

         if (performancedata == null)
         {
            return;
         }

         termyear = (years < performancedata.length) ? years : performancedata.length;


         if (pfdata.getGoalData().getGoalDesired() == null)
         {
            goalAmount = performancedata[termyear - 1].getLowerBand1();
         }
         else
         {
            goalAmount = pfdata.getGoalData().getGoalDesired();
         }

         interestRate = performancedata[0].getInvestmentReturns();
         actualInitialAmount = performancedata[0].getInvestedCapital();
         recurringAmount = performancedata[0].getRecurInvestments();

         pfdata.getGoalData().setRisk(performancedata[0].getInvestmentRisk());
         pfdata.getGoalData().setInterestrate(performancedata[0].getInvestmentReturns());
         pfdata.getGoalData().setActualInitialAmount(actualInitialAmount);
         pfdata.getGoalData().setActualRecurringAmount(recurringAmount);
         pfdata.getGoalData().setTerm((double) termyear);

            Double n1 = Math.pow((1.0 + interestRate), termyear);
            Double n2 = actualInitialAmount * n1;
            //Double n3 = goalAmount - n2;
            Double n3 = goalAmount - performancedata[termyear - 1].getLowerBand2();
            Double numerator = interestRate * n3;
            Double d1 = Math.pow((1 + interestRate), (termyear + 1));
            Double denominator = (d1 - 1 - interestRate);

            calcRecurringAmount = numerator / denominator;

            pfdata.getGoalData().setCalcRecurringAmount(calcRecurringAmount);

            if (calcRecurringAmount <= 0 || calcRecurringAmount <= recurringAmount)
            {
               pfdata.getGoalData().setReachable(true);
            }
            else
            {
               if (pfdata.getGoalData().getGoalDesired() <= performancedata[termyear-1].getTotalCapitalWithGains()) {
                  pfdata.getGoalData().setReachable(true);
               }
               else {
                  pfdata.getGoalData().setReachable(false);
               }
            }
      }
      catch (Exception ex)
      {

      }
   }

   private void fillData(PerformanceData[] performancedata, int years, ProfileData pfdata) {
      pfdata.getGoalData().setYearGoalAchieved(years);

      if (years > performancedata.length - 1)
         years = performancedata.length - 1;

      for (int year = 0; year <= years; year++)
      {
         performancedata[year].setGoalsrequired(performancedata[year].getTotalCapitalWithGains());
         pfdata.getGoalData().addGrowthData(performancedata[year].getTotalCapitalWithGains());
      }
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
