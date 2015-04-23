package com.invmodel.portfolio;

import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.dao.*;
import com.invmodel.dao.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.data.Portfolio;

public class PortfolioModel
{

   private SecurityDBCollection securityDao;
   private PortfolioOptimizer portfolioOptimizer;

   public PortfolioModel()
   {
   }

   public void setPortfolioOptimizer(PortfolioOptimizer portfolioOptimizer)
   {
      this.portfolioOptimizer = portfolioOptimizer;
   }

   public void setSecurityDao(SecurityDBCollection securityDao)
   {
      this.securityDao = securityDao;
   }

   public double calcAssetValue(double invCapital, double avgYearReturns) throws Exception
   {

      double intEarned = 0;

      intEarned = avgYearReturns * invCapital;
      invCapital = invCapital + intEarned;

      return invCapital;
   }

   public Portfolio[] buildPortfolio(AssetClass[] assetData, ProfileData profileData) {
      if (profileData == null) {
         return null;
      }
      if (profileData.getRiskCalcMethod() == null || profileData.getRiskCalcMethod().startsWith("C"))
         return getConsumerPortfolio(assetData,profileData);
      else
         return getAdvisorPortfolio(assetData,profileData);

   }

   private Portfolio[] getAdvisorPortfolio(AssetClass[] assetData, ProfileData profileData)
   {
      String assetName;

      Integer duration;
      Double riskOffset;
      Double invCapital;
      Double keepLiquidCash;
      Double reinvestment = 0.0;
      String groupname;
      String theme;


      try
      {
         if (assetData == null)
         {
            return null;
         }

         // 04-15-14 Added this logic to use the actual Cash instead of entered on screen!
         invCapital = profileData.getDefaultInvestment().doubleValue();

         // 04-15-14 Keep Liquid Cash as required.
         keepLiquidCash = 0.0;
         if (invCapital< 100000)
            keepLiquidCash = InvConst.MIN_LIQUID_CASH;

         if (profileData.getKeepLiquid() != null)
            keepLiquidCash = profileData.getKeepLiquid().doubleValue();

         profileData.setKeepLiquid(keepLiquidCash.intValue());

         if (invCapital < 0.0) {
            invCapital = 0.0;
         }

         theme = profileData.getTheme();
         groupname = profileData.getAdvisor();

         // NOTE:  If Theme is default, then use Invessence - Core portfolio.
         // However, the Asset Weights may have been defined by Advisor's mapping.
         if (theme == null || theme.isEmpty()) {
            theme = InvConst.DEFAULT_THEME;
         }

         if (groupname == null  || groupname.isEmpty()) {
            groupname = InvConst.INVESSENCE_ADVISOR;
         }

         // Taxable Strategy Introduced 2/9/2015
         if (profileData.getAccountTaxable()) {
            if (! theme.toUpperCase().startsWith("T."))
               theme = "T." + theme;
         }


         ArrayList<String> assetList = assetData[0].getOrderedAsset();
         riskOffset = assetData[0].getRiskOffset();

         duration = profileData.getDefaultHorizon();


         double totalIncEarned = 0.0;
         double investment = invCapital - keepLiquidCash;
         if (profileData.getRecurringInvestment() == null)
            reinvestment = 0.0;
         else
            reinvestment = profileData.getRecurringInvestment().doubleValue();

         // Now collect all securities for this theme;
         if (securityDao == null)
            securityDao = new SecurityDBCollection();
         // if security of this theme is already loaded, then don't reload.
         // NOTE: If they individual choose the non-taxable, but is taxable, then load the taxable strategy.
         if (! securityDao.getThemeLoaded().equalsIgnoreCase(theme))
            securityDao.loadDataFromDB(theme);

         double actualInvestment = investment;

         //Asset data is for each year of investment horizon
         int years = (profileData.getNumOfPortfolio() == null || profileData.getNumOfPortfolio() == 0) ? profileData.getAssetData().length : profileData.getNumOfPortfolio();
         years = (profileData.getAssetData().length < years ) ? profileData.getAssetData().length : years ;
         Portfolio[] portfolioclass = new Portfolio[years];
         int offset = (profileData.getPortfolioIndex() == null) ? InvConst.PORTFOLIO_DEFAULT_POINT : profileData.getPortfolioIndex();
         offset = (offset > InvConst.PORTFOLIO_INTERPOLATION - 1) ? InvConst.PORTFOLIO_INTERPOLATION - 1 : offset;
         offset = (offset < 0) ? 0 : offset;
         for (int investmentYear = 0; investmentYear < years; investmentYear++)
         {
            portfolioclass[investmentYear] = new Portfolio();
            portfolioclass[investmentYear].setTheme(theme);

            //JAV added keepLiquid
            portfolioclass[investmentYear].setCashMoney(actualInvestment);



            //offset = (int) (StrictMath.sqrt(StrictMath.pow(duration, 2.0) - StrictMath.pow((double) investmentYear, 2.0)))*(InvConst.PORTFOLIO_INTERPOLATION/duration);
            //offset = (int) (riskOffset * (double) offset);

            // Actual Investment is investment and recurring the next year.  Does not contain the returns.
            portfolioclass[investmentYear].setActualInvestments(actualInvestment);
            createPortfolio(groupname, theme, assetData[investmentYear], portfolioclass[investmentYear],
                            investment, investmentYear, profileData, offset);

            // Total Money = Investment + Performance
            portfolioclass[investmentYear].setTotalMoney(investment);
            actualInvestment += reinvestment;
            if (investmentYear > 0) {
               portfolioclass[investmentYear].setUpperTotalMoney((2*portfolioclass[investmentYear-1].getTotalRisk() * investment )
                                                                    + investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment -
                                                                    (2*portfolioclass[investmentYear-1].getTotalRisk() * investment));
            }
            else{
               portfolioclass[investmentYear].setUpperTotalMoney(investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment);

            }
            investment = portfolioclass[investmentYear].getTotalMoney() +
               portfolioclass[investmentYear].getTotalCapitalGrowth() +
               reinvestment;

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

   private Portfolio[] getConsumerPortfolio(AssetClass[] assetData, ProfileData profileData)
   {
      String assetName;

      Integer duration;
      Double riskOffset;
      Double invCapital;
      Double keepLiquidCash;
      Double reinvestment = 0.0;
      String groupname;
      String theme;


      try
      {
         if (assetData == null)
         {
            return null;
         }

         // 04-15-14 Added this logic to use the actual Cash instead of entered on screen!
         invCapital = profileData.getDefaultInvestment().doubleValue();

         // 04-15-14 Keep Liquid Cash as required.
         keepLiquidCash = 0.0;
         if (invCapital< 100000)
            keepLiquidCash = InvConst.MIN_LIQUID_CASH;

         if (profileData.getKeepLiquid() != null)
            keepLiquidCash = profileData.getKeepLiquid().doubleValue();

         profileData.setKeepLiquid(keepLiquidCash.intValue());

         if (invCapital < 0.0) {
            invCapital = 0.0;
         }

         theme = profileData.getTheme();
         groupname = profileData.getAdvisor();

         // NOTE:  If Theme is default, then use Invessence - Core portfolio.
         // However, the Asset Weights may have been defined by Advisor's mapping.
         if (theme == null || theme.isEmpty()) {
            theme = InvConst.DEFAULT_THEME;
         }

         if (groupname == null  || groupname.isEmpty()) {
            groupname = InvConst.INVESSENCE_ADVISOR;
         }

         // Taxable Strategy Introduced 2/9/2015
         if (profileData.getAccountTaxable()) {
            if (! theme.toUpperCase().startsWith("T."))
               theme = "T." + theme;
         }


         ArrayList<String> assetList = assetData[0].getOrderedAsset();
         riskOffset = assetData[0].getRiskOffset();

         duration = profileData.getDefaultHorizon();


         double totalIncEarned = 0.0;
         double investment = invCapital - keepLiquidCash;
         if (profileData.getRecurringInvestment() == null)
            reinvestment = 0.0;
         else
            reinvestment = profileData.getRecurringInvestment().doubleValue();

         // Now collect all securities for this theme;
         if (securityDao == null)
            securityDao = new SecurityDBCollection();
         // if security of this theme is already loaded, then don't reload.
         // NOTE: If they individual choose the non-taxable, but is taxable, then load the taxable strategy.
         if (! securityDao.getThemeLoaded().equalsIgnoreCase(theme))
            securityDao.loadDataFromDB(theme);

         double actualInvestment = investment;

         //Asset data is for each year of investment horizon
         int years = (profileData.getNumOfPortfolio() == null || profileData.getNumOfPortfolio() == 0) ? profileData.getAssetData().length : profileData.getNumOfPortfolio();
         years = (profileData.getAssetData().length < years ) ? profileData.getAssetData().length : years ;
         Portfolio[] portfolioclass = new Portfolio[years];
         for (int investmentYear = 0; investmentYear < years; investmentYear++)
         {
            portfolioclass[investmentYear] = new Portfolio();
            portfolioclass[investmentYear].setTheme(theme);

            //JAV added keepLiquid
            portfolioclass[investmentYear].setCashMoney(actualInvestment);


            int offset;

            offset = (int) (StrictMath.sqrt(StrictMath.pow(duration, 2.0) - StrictMath.pow((double) investmentYear, 2.0)))*(InvConst.PORTFOLIO_INTERPOLATION/duration);
            offset = (int) (riskOffset * (double) offset);
            offset = (offset > InvConst.PORTFOLIO_INTERPOLATION - 1) ? InvConst.PORTFOLIO_INTERPOLATION - 1 : offset;
            if(offset < 0)
               offset = 0;

            if (investmentYear == 0)
               profileData.setPortfolioIndex(offset);

            // Actual Investment is investment and recurring the next year.  Does not contain the returns.
            portfolioclass[investmentYear].setActualInvestments(actualInvestment);
            createPortfolio(groupname, theme, assetData[investmentYear], portfolioclass[investmentYear],
                            investment, investmentYear, profileData, offset);

            // Total Money = Investment + Performance
            portfolioclass[investmentYear].setTotalMoney(investment);
            actualInvestment += reinvestment;
            if (investmentYear > 0) {
               portfolioclass[investmentYear].setUpperTotalMoney((2*portfolioclass[investmentYear-1].getTotalRisk() * investment )
                                                                    + investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment -
                                                                    (2*portfolioclass[investmentYear-1].getTotalRisk() * investment));
            }
            else{
               portfolioclass[investmentYear].setUpperTotalMoney(investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment);

            }
            investment = portfolioclass[investmentYear].getTotalMoney() +
               portfolioclass[investmentYear].getTotalCapitalGrowth() +
               reinvestment;

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

   private void createPortfolio(String groupname, String theme,
                                AssetClass assetClass, Portfolio pclass, double investment,
                                int year, ProfileData pdata, int offset)
   {
      try
      {

         double amount_remain = investment;

         double totalPortfolioWeight = 0.0;
         double ticker_weight;
         double portfolioReturns = 0.0;
         double portfolioRisk = 0.0;
         double avgExpense = 0.0;
         double incEarned = 0.0;
         double secExpense = 0.0;

         // Prime asset class loop
         for (String assetname : portfolioOptimizer.getAdvisorOrdertedAssetList(theme))
         {
            double investByAsset = 0.0;
            AssetData assetdata = portfolioOptimizer.getAssetData(theme, assetname);
            Asset asset = assetClass.getAsset(assetname);
            double[][] primeAssetWeights = assetdata.getPrimeAssetweights();
            double assetWgt = asset.getUserweight();
            int tickerNum = 0;
            for (String primeassetclass : assetdata.getOrderedPrimeAssetList())
            {

               if (assetname.equalsIgnoreCase("CASH")) {
                  SecurityData sd = securityDao.getSecurity("CASH");
                  assetWgt = (amount_remain + pdata.getKeepLiquid())/investment;
                  double cash = amount_remain + pdata.getKeepLiquid();
                  investByAsset = amount_remain;

                  totalPortfolioWeight = assetWgt;
                  pclass.setPortfolio(sd.getTicker(), sd.getName(), asset.getColor(),
                                      sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getSubassetclass(),
                                      1.0, assetWgt, sd.getNonTaxableReturn(), sd.getExpenseRatio(),
                                      sd.getRiskSTD(), sd.getYield(), cash, cash, 999999, assetWgt);
                  pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                        asset.getColor(),
                                        assetWgt, amount_remain, true);
                  break;
               }

               ticker_weight = assetWgt * primeAssetWeights[offset][tickerNum];
               if (securityDao.getOrderedSecurityList(theme, assetname, primeassetclass) != null) {
                  for (SecurityData sd : securityDao.getOrderedSecurityList(theme, assetname, primeassetclass))
                  {
                     PrimeAssetClassData pacd = portfolioOptimizer.getPrimeAssetData(theme, assetname, primeassetclass);
                     double price = sd.getDailyprice();
                     ticker_weight = ticker_weight * sd.getRbsaweight();  // RBSA PREP WORK:  Currently all have rate of 1
                     // If there is no weight, just skip this ticker all together.
                     double shares = 0.0, money = 0.0;
                     if (ticker_weight > 0.0 && price > 0.0)
                     {
                        shares = Math.round(((investment * ticker_weight) / price) - 0.5);
                        money = shares * price;

                        // Only create this portfolio if there are shares and money
                        if ((shares > 0.0) && (money > 0.0))
                        {
                           totalPortfolioWeight = 0.0;
                           if (investment > 0.0)
                           {
                              totalPortfolioWeight = money / investment;
                           }

                           investByAsset = investByAsset + money;
                           pclass.setPortfolio(sd.getTicker(), sd.getName(), asset.getColor(),
                                               sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getSubassetclass(),
                                               price, ticker_weight, sd.getNonTaxableReturn(), sd.getExpenseRatio(),
                                               sd.getRiskSTD(), sd.getYield(), shares, money, pacd.getSortorder(), totalPortfolioWeight);
                           pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                                 asset.getColor(),
                                                 totalPortfolioWeight, money, true);


                           secExpense = secExpense + sd.getExpenseRatio() * ticker_weight;
                        }
                        amount_remain = amount_remain - money;
                        pclass.setCashMoney(amount_remain);
                        portfolioRisk = portfolioRisk + assetdata.getPrimeAssetrisk()[offset] * totalPortfolioWeight;
                        portfolioReturns = portfolioReturns + assetdata.getPrimeAssetreturns()[offset] * totalPortfolioWeight;
                     }
                  }

               }
               //look at the next ticker in primeAssetFrontier
               tickerNum++;

            }
            asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
            asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);
            asset.setValue(investByAsset);
            asset.setActualweight(investByAsset / investment);
        }
         incEarned = portfolioReturns * investment;
         pclass.setExpReturns(portfolioReturns);
         pclass.setTotalRisk(portfolioRisk);
         pclass.setTotalCapitalGrowth(incEarned);
         pclass.setAvgExpense(secExpense * investment);
        ;

         if (InvConst.MIN_MNGT_FEES_DOLLARS > InvConst.MNGT_FEES * investment)
         {
            pclass.setAvgCost(InvConst.MIN_MNGT_FEES_DOLLARS);
            pclass.setTotalCost(InvConst.MIN_MNGT_FEES_DOLLARS + secExpense * investment);
         }
         else
         {
            pclass.setAvgCost(InvConst.MNGT_FEES * investment);
            pclass.setTotalCost(InvConst.MNGT_FEES * investment + secExpense * investment);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

}
