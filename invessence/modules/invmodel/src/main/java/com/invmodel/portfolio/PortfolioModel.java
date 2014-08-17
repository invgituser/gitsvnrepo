package com.invmodel.portfolio;

import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.dao.*;
import com.invmodel.dao.data.SecurityData;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.data.Portfolio;
import webcab.lib.finance.portfolio.*;

public class PortfolioModel
{

   private static PortfolioModel instance = null;
   //private SecurityDBCollection securityInfo = SecurityDBCollection.getInstance();
   private SecurityDBCollection securityDao;
   private DailyReturns monthlyDao;

   private PortfolioModel()
   {
   }

   public static synchronized PortfolioModel getInstance()
   {
      if (instance == null)
      {
         instance = new PortfolioModel();
      }

      return instance;
   }

   public void setSecurityDao(SecurityDBCollection securityDao)
   {
      this.securityDao = securityDao;
   }

   public void setMonthlyDao(DailyReturns monthlyDao)
   {
      this.monthlyDao = monthlyDao;
   }

   private void initPortfolio()
   {
      // securityDao.loadDataFromDB();
      //monthlyDao.loadDailyReturnsfromDB();
   }

   public double calcAssetValue(double invCapital, double avgYearReturns) throws Exception
   {

      double intEarned = 0;

      intEarned = avgYearReturns * invCapital;
      invCapital = invCapital + intEarned;

      return invCapital;
   }

   public Portfolio[] getDistributionList(AssetClass[] assetData, ProfileData profileData)
   {
      String[] tickers;
      double investByAsset;

      String assetName;

      double[] ubConstraints;
      double[] lbConstraints;
      double[] historicalReturns = null;
      double[] yield = null;
      double[] secExpense = null;
      Integer duration;
      Double riskOffset;
      Double invCapital;
      Double keepLiquidCash;
      Double reinvestment = 0.0;
      String groupname;
      String theme;

      //double[] historicalRisk = null;
      ArrayList<double[][]> weights = new ArrayList<double[][]>();
      ArrayList<double[]> assetRisk = new ArrayList<double[]>();
      ArrayList<double[]> assetReturns = new ArrayList<double[]>();

      try
      {
         if (assetData == null)
         {
            return null;
         }
         // 04-15-14 Added this logic to use the actual Cash instead of entered on screen!
         invCapital = 1000.00;
         if (profileData.getActualInvestment() != null && profileData.getActualInvestment() != 0) {
            invCapital = profileData.getActualInvestment().doubleValue();
         }
         else {
            if (profileData.getInitialInvestment() != null)
               invCapital = profileData.getInitialInvestment().doubleValue();
         }

         if (profileData.getRecurringInvestment() == null)
         {
            reinvestment = 0.0;
         }
         else
         {
            reinvestment = profileData.getRecurringInvestment().doubleValue();
         }

         // 04-15-14 Keep Liquid Cash as required.
         keepLiquidCash = 0.0;
         if (profileData.getKeepLiquid() != null)
            keepLiquidCash = profileData.getKeepLiquid().doubleValue();

         // Need further evaluation at Asset Level (to show the proper Cash %)
         invCapital = invCapital - keepLiquidCash;
         if (invCapital < 0.0) {
            invCapital = 0.0;
         }
         groupname = profileData.getAdvisor();
         theme = profileData.getTheme();

         int years = profileData.getNumOfPortfolio();
         initPortfolio();


         ArrayList<String> assetList = assetData[0].getOrderedAsset();
         riskOffset = assetData[0].getRiskOffset();
         duration = (profileData.getHorizon() <= 0) ? 1 : profileData.getHorizon();
         double[][] mrData;
         Portfolio[] portfolioclass;
         portfolioclass = new Portfolio[assetData.length];
         // Build List of AssetClass and SubAssetclass list.
         for (int i = 0; i < assetList.size(); i++)
         {
            assetName = assetList.get(i);
            tickers = securityDao.getAssetOrderedAssetTickers(groupname, theme, assetName,profileData.getCustomAllocations());
            if (tickers == null)
               continue;
            CapitalMarket capMarketData = null;
            lbConstraints = null;
            yield = null;
            historicalReturns = null;
            mrData = null;


            //if (! assetName.equalsIgnoreCase("CASH")) {
               lbConstraints = securityDao.getAssetOrderedAssetLB(groupname, theme, assetName, riskOffset,
                                                                  profileData.getCustomAllocations(),duration,
                                                                  profileData.isAccountTaxable());

               ubConstraints = securityDao.getAssetOrderedAssetUB(groupname, theme,assetName, riskOffset,
                                                                  profileData.getCustomAllocations(),duration,
                                                                  profileData.isAccountTaxable());

               yield = securityDao.getAssetOrderedAssetYield(groupname, theme, assetName,
                                                             profileData.getCustomAllocations(), duration,
                                                             profileData.isAccountTaxable(), profileData.getTaxrate());

               historicalReturns = securityDao.getAssetOrderedAssetExpectedReturns(groupname, theme, assetName,
                                                                                   profileData.getCustomAllocations(),false);

               //adjust municipal yield to tax equivalent yield by dividing (1 - taxRate)

               mrData = monthlyDao.getDailyReturnsArray(tickers);


               // Objective = WealthPreservation = 1; WealthAccumulation = 2
               //if (profileData.getObjective() == 2){  // growth or accumulation objective
               if (assetName.equals("Bond"))
               {
                  capMarketData = getCapitalMarketData(assetName, tickers, lbConstraints, ubConstraints, yield, mrData);
                  assetReturns.add(getPortfolioAssetExpectedReturns(capMarketData, yield, assetData.length, tickers.length));
               }
               else
               {
                  capMarketData = getCapitalMarketData(assetName, tickers, lbConstraints, ubConstraints, historicalReturns, mrData);
                  assetReturns.add(getPortfolioAssetExpectedReturns(capMarketData, historicalReturns, assetData.length, tickers.length));
               }
                //}
               //else {  // income objective
               //   capMarketData = getCapitalMarketData(tickers, lbConstraints, ubConstraints, yield, mrData);
               //}

               //Asset level weights, risk and returns
            //}

            weights.add(i, getPortfolioWeights(capMarketData, assetData.length, tickers.length));
            assetRisk.add(getPortfolioAssetRisk(capMarketData, assetName, assetData.length, tickers, mrData));

         }

         double totalIncEarned = 0.0;
         double investments = invCapital;

         for (int investmentYear = 0; investmentYear < years; investmentYear++)
         {
            portfolioclass[investmentYear] = new Portfolio();
            portfolioclass[investmentYear].resetPortfolioSubclass();
            portfolioclass[investmentYear].setCashMoney(invCapital);

            double portfolioReturns = 0.0;
            double portfolioRisk = 0.0;

            double totalCost = 0.0;
            double avgExpense = 0.0;
            double avgAssetExpense = 0.0;
            double[] securityReturn;

            // Create portfolio for each asset class
            for (int i = 0; i < assetList.size(); i++)
            {
               assetName = assetList.get(i);
               avgAssetExpense = 0.0;
               tickers = securityDao.getAssetOrderedAssetTickers(groupname, theme, assetName,profileData.getCustomAllocations());
               if (tickers == null)
                  continue;

               secExpense = securityDao.getAssetOrderedAssetExpenseRatio(groupname, theme,assetName,profileData.getCustomAllocations());
               double[] secRisk = securityDao.getAssetOrderedAssetSecurityRisk(groupname, theme,assetName,profileData.getCustomAllocations());
               double[] secYield = securityDao.getAssetOrderedAssetYield(groupname, theme,assetName,
                                                                         profileData.getCustomAllocations(), duration,
                                                                         profileData.isAccountTaxable(), profileData.getTaxrate());

               // Need to load based on riskOffset
               investByAsset = assetData[investmentYear].getAssetActualWeight(assetName) * invCapital;

               securityReturn = securityDao.getAssetOrderedAssetExpectedReturns(groupname, theme, assetName,profileData.getCustomAllocations(),false);

               createPortfolio(groupname, theme,assetName,
                               assetData[investmentYear], portfolioclass[investmentYear], tickers,
                               investmentYear, duration, invCapital, investByAsset, securityReturn, secRisk, secYield, assetReturns.get(i),
                               assetRisk.get(i), weights.get(i), investments,
                               profileData);

               //calculate next year's investment based on expected returns

               portfolioRisk = portfolioRisk + (investByAsset / invCapital) * assetData[investmentYear].getAssetRisk(assetName);
               portfolioReturns = portfolioReturns + (investByAsset / invCapital) * assetData[investmentYear].getAssetExpectedReturns(assetName);

               int offset;

               if (tickers.length == 1)
               {
                  offset = 0;
               }
               else
               {
                  offset = (int) (((double) InvConst.PORTFOLIO_INTERPOLATION / (double) duration) * (double) (duration - investmentYear));
                  if (investmentYear == 0)
                  {
                     offset = offset - 1;
                  }
               }

               for (int t = 0; t < tickers.length; t++)
               {

                  avgAssetExpense = avgAssetExpense + secExpense[t] * weights.get(i)[offset][t];
               }

               avgExpense = avgExpense + avgAssetExpense * (investByAsset / invCapital);
               String[] subassetlist = securityDao.getUnfilterdSubAsset(groupname, theme,assetName);
               for (int j=0; j < subassetlist.length; j++) {
                 portfolioclass[investmentYear].addSubclassMap(assetName,subassetlist[j],
                                                               assetData[0].getAssetColor(assetName),
                                                               0.0, 0.0, false);
               }
            }

            double incEarned = 0.0;
            portfolioReturns = portfolioReturns - InvConst.PORTFOLIO_MNAGEMENT_FEES; //Subtract yearly fees
            portfolioclass[investmentYear].setExpReturns(portfolioReturns);
            portfolioclass[investmentYear].setTotalRisk(portfolioRisk);
            portfolioclass[investmentYear].setActualInvestments(investments);
            portfolioclass[investmentYear].setTotalCapitalGrowth(totalIncEarned);
            portfolioclass[investmentYear].setTotalMoney(invCapital);
            portfolioclass[investmentYear].setAvgExpense(avgExpense * invCapital);

            if (InvConst.MIN_MNGT_FEES_DOLLARS > InvConst.MNGT_FEES * invCapital)
            {

               portfolioclass[investmentYear].setAvgCost(InvConst.MIN_MNGT_FEES_DOLLARS);
               portfolioclass[investmentYear].setTotalCost(InvConst.MIN_MNGT_FEES_DOLLARS + avgExpense * invCapital);

            }
            else
            {
               portfolioclass[investmentYear].setAvgCost(InvConst.MNGT_FEES * invCapital);
               portfolioclass[investmentYear].setTotalCost(InvConst.MNGT_FEES * invCapital + avgExpense * invCapital);
            }

            // Keep track of all cash invested
            investments = investments + reinvestment;

            //Keep track of all cash invested, capital growth and income earned
            invCapital = invCapital + reinvestment;
            incEarned = portfolioReturns * invCapital;
            invCapital = invCapital + incEarned;

            // Keep track of all returns
            totalIncEarned = totalIncEarned + incEarned;

         }
         //assign total risk and expReturn JAV
         return portfolioclass;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   private void createPortfolio(String groupname, String theme, String assetName,
                                AssetClass assetData, Portfolio pclass, String[] tickers,
                                int year, int duration, double investment, double investByAsset,
                                double[] securityReturns, double[] secRisk, double[] secYield,
                                double[] assetReturns, double[] assetRisk, double[][] weights, double totalInvestment,
                                ProfileData pdata)
   {
      try
      {

         double amount_remain = pclass.getCashMoney();
         double shares = 0.0, money = 0.0;
         double assetAmountRemain = 0.0;

         int offset;

         if (tickers.length == 1)
         {
            offset = 0;
         }
         else
         {
            offset = (int) (((double) InvConst.PORTFOLIO_INTERPOLATION / (double) duration) * (double) (duration - year));
            if (year == 0)
            {
               offset = offset - 1;
            }
         }

         // Reassign weight, risk, and expected returns based on etf

         assetData.setAssetExpectedReturns(assetName, assetReturns[offset]);
         assetData.setAssetRisk(assetName, assetRisk[offset]);
         assetData.setAssetActualWeight(assetName, investByAsset / investment);
         assetAmountRemain = investByAsset;
         for (int i = 0; i < tickers.length; i++)
         {
            String ticker = tickers[i];

            SecurityData sd = securityDao.getSecurityInfo(groupname, theme, assetName, ticker, pdata.getCustomAllocations());
            if (sd == null)
              continue;  // Skip the loop. This security is excluded.
            double price = sd.getDailyprice();

            double ticker_weight = Math.round(weights[offset][i] * 100.00) / 100.00;

            shares = 0.0;
            money = 0.0;
            if (price > 0)
            {
               if (ticker_weight > 0)
               {
                  // This code was messing up the cash. Jigar 12/17/2013
                  // If allocation amount is greater then remaining, then reduce it fit the available.
                  //if (investByAsset > amount_remain)
                  //   investByAsset = amount_remain;

                  shares = Math.round(((investByAsset * ticker_weight) / price));


                  if (shares * price > assetAmountRemain)
                  {
                     shares = Math.round((assetAmountRemain / price) - 0.5);
                  }

                  if (shares > 0.0)
                  {
                     money = shares * price;
                     money = Math.round(money * 100.00) / 100.00;
                  }
                  else
                  {
                     shares = 0.0;
                  }
               }
            }

            if (sd.getType().equals("Cash"))
            {
               money = amount_remain;
               amount_remain = 0;
               assetAmountRemain = 0;
               shares = money;
            }
            else
            {
               amount_remain = amount_remain - money;
               assetAmountRemain = assetAmountRemain - money;
               double tmp = amount_remain * 100.00;
               double tmp2 = Math.round(tmp);
               double tmp3 = tmp2 / 100.00;
               //amount_remain = Math.round(amount_remain*100)/100;  // For some reason, this operation does not work.

               amount_remain = tmp3;
            }

            // Only create this portfolio if there are shares and money
            //secRisk is the sqrt(diagonal value)
            if ((shares > 0.0) && (money > 0.0))
            {
               double tickerWeight = 0;
               if (totalInvestment != 0)
               {
                  tickerWeight = money / totalInvestment;
               }

               pclass.setPortfolio(ticker, sd.getName(), assetData.getAssetColor(assetName),
                                   sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getSubassetclass(),
                                   price, ticker_weight, securityReturns[i], sd.getExpenseRatio(),
                                   secRisk[i], secYield[i], shares, money, sd.getSortorder(), tickerWeight);
               pclass.addSubclassMap(assetName, sd.getSubassetclass(),
                                     assetData.getAssetColor(assetName),
                                     tickerWeight, money, true);
            }

         }
         pclass.setCashMoney(amount_remain);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public CapitalMarket getCapitalMarketData(String asset, String[] tickers,
                                             double[] lbConstraints, double[] ubConstraints,
                                             double[] expectedReturnsOfFunds, double[][] mrData)
   {

      double[][] weights;

      double[] risk = null;
      double[] returns = null;
      //double[] expectedReturnsOfFunds = null;
      try
      {

         // Should never happen
         if (tickers == null || tickers.length < 1)
         {
            //weights = new double[1][1];
            //weights[0][0] = 1.0;
            return null;
         }

         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();

         //Want to provide the expected return data and not the historical calculated
         //Data
         //expectedReturnsOfFunds = assetParameters.expectedReturns(mrData);

         // Assets with single security such as commodities and cash
         // no reason to run optimization.
         if (asset.equalsIgnoreCase("CASH") && tickers.length == 1) {
            weights = new double[1][1];
            weights[0][0] = 1.0;
         }
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
               covarianceOfFunds,//Covariance matrix
               expectedReturnsOfFunds, // expectedReturns
               InvConst.PORTFOLIO_INTERPOLATION, // numberInterpolationPoints
               InvConst.PORTFOLIO_PRECISION // precision
            );

            risk = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(covarianceOfFunds);
            //returns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();

            // Weights of the portfolio on the Efficient Frontier.
            //weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();

			/*
            // Here we evaluate the Optimal portfolio  with  marketPortfolio at market rate of 0%
			double[] marketPortfolioWeights = instanceOfCapitalMarket.marketPortfolio(
					minReturn1, // expectedReturn
					maxReturn1,
					covarianceOfFunds,
					0.0// expected Free rate
					);

			// Here we evaluate the market portfolio  return with  marketPortfolio at market rate of 0%
			double marketPortfolioReturn = instanceOfCapitalMarket.marketPortfolioExpectedReturn(
					covarianceOfFunds,
					expectedReturnsOfFunds,
					0.0,//Market rate
					0.0001
					);

			// Here we evaluate the market portfolio risk with  marketPortfolio at market rate of 0%
			double marketPortfolioRisk = instanceOfCapitalMarket.marketPortfolioRisk(
					covarianceOfFunds,
					expectedReturnsOfFunds,
					0.0,//Market rate
					0.0001
					);

			// Here we evaluate the values of the expected returns of  the  Efficient
			// Frontier.
			double[] returns1 = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();

			 */
                /*
            } else {
                weights = new double[1][1];

                weights[0][0] = 1.0;
                */
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

   private double[][] getPortfolioWeights(CapitalMarket capMarketData, int numYears, int numTickers)
   {
      double[][] weights;

      try
      {
         if (capMarketData == null)
         {

            weights = new double[numYears][numTickers];
            for (int asset = 0; asset < numYears; asset++)
            {
               for (int t = 0; t < numTickers; t++)
               {
                  weights[asset][t] = 1.0;
               }

            }
            return weights;

         }
         else
         {

            if (numTickers > 1)
            {

               weights = capMarketData.getEfficientFrontierAssetWeights();

               return weights;
            }
            else
            {

               weights = new double[numYears][numTickers];
               for (int asset = 0; asset < numYears; asset++)
               {
                  for (int t = 0; t < numTickers; t++)
                  {
                     weights[asset][t] = 1.0;
                  }

               }
               return weights;

            }

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

   private double[] getPortfolioAssetRisk(CapitalMarket capMarketData, String assetName, int numYears, String[] tickers, double[][] mrData)
   {
      double[] risk = null;

      AssetParameters assetParameters = new AssetParameters();

      try
      {
         if (capMarketData == null)
         {

            risk = new double[1];
            risk[0] = 0.0;
            return risk;
         }
         else
         {

            if (tickers.length > 1)
            {

               double[][] covarianceOfFunds = assetParameters.covarianceMatrix(mrData);

               risk = capMarketData.getEfficientFrontierPortfolioRisks(covarianceOfFunds);

               for (int i = 0; i < risk.length; ++i)
               {

                  risk[i] = StrictMath.sqrt(253.0) * risk[i];
               }

            }
            else
            {
               risk = new double[numYears];
               for (int year = 0; year < numYears; year++)
               {
                  if (assetName.equals("Cash"))
                  {
                     risk[year] = 0.005;
                  }
                  else
                  {
                     risk[year] = 0.16;
                  }


               }
            }

            return risk;
         }

      }
      catch (Exception e)
      {

         e.printStackTrace();

         risk = new double[1];
         risk[0] = 0.0;
         return risk;

      }
   }

   private double[] getPortfolioAssetExpectedReturns(CapitalMarket capMarketData, double[] histReturns, int numYears, int numTickers)
   {
      double[] assetReturns = null;

      try
      {
         if (capMarketData == null)
         {

            assetReturns = new double[1];
            assetReturns[0] = 0.0;
            return assetReturns;

         }
         else
         {

            if (numTickers > 1)
            {
               assetReturns = capMarketData.getEfficientFrontierExpectedReturns();
            }
            else
            {

               assetReturns = new double[numYears];
               for (int year = 0; year < numYears; year++)
               {
                  assetReturns[year] = histReturns[0];

               }
            }

            return assetReturns;

         }
      }
      catch (Exception e)
      {

         e.printStackTrace();

         assetReturns = new double[1];
         assetReturns[0] = 0.0;
         return assetReturns;

      }
   }

   public double[][] getCoVariance(double[][] historicalReturns) throws Exception
   {
      AssetParameters instanceOfAssetParameters = new AssetParameters();

      double[][] coVarMatrix = instanceOfAssetParameters.covarianceMatrix(historicalReturns);

      return coVarMatrix;
   }

}
