package com.invmodel.portfolio;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.dao.data.*;
import com.invmodel.dao.invdb.*;
import com.invmodel.dao.rbsa.HolisticModelOptimizer;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.data.Portfolio;

public class PortfolioModel
{

   //private SecurityDBCollection securityDao;
   private SecurityCollection secCollection;
   private PortfolioOptimizer portfolioOptimizer;

   public PortfolioModel()
   {
   }

   public void setPortfolioOptimizer(PortfolioOptimizer portfolioOptimizer)
   {
      this.portfolioOptimizer = portfolioOptimizer;
   }

   public void setSecurityDao(SecurityCollection secCollection)
   {
      this.secCollection = secCollection;
   }

   public double calcAssetValue(double invCapital, double avgYearReturns) throws Exception
   {

      double intEarned = 0;

      intEarned = avgYearReturns * invCapital;
      invCapital = invCapital + intEarned;

      return invCapital;
   }

   public Portfolio[] buildPortfolio(AssetClass[] assetData, ProfileData profileData)
   {
      Integer duration;
      Double invCapital;
      Double keepLiquidCash;
      Double reinvestment = 0.0;
      String advisor;
      String theme;

      try
      {
         if (assetData == null)
         {
            return null;
         }

         if (profileData == null)
         {
            return null;
         }

         // 04-15-14 Added this logic to use the actual Cash instead of entered on screen!
         invCapital = profileData.getDefaultInvestment().doubleValue();

         // 04-15-14 Keep Liquid Cash as required.
         keepLiquidCash = 0.0;
         //05-14-15 changed, should be set in the database with setKeepLiquid
         if (invCapital < 100000)
         {
            profileData.setKeepLiquid(((int) InvConst.MIN_LIQUID_CASH));
         }

         if (profileData.getKeepLiquid() != null)
         {
            keepLiquidCash = profileData.getKeepLiquid().doubleValue();
         }

         profileData.setKeepLiquid(keepLiquidCash.intValue());

         if (invCapital < 0.0)
         {
            invCapital = 0.0;
         }

         theme = profileData.getTheme();
         advisor = profileData.getAdvisor();

         // NOTE:  If Theme is default, then use Invessence - Core portfolio.
         // However, the Asset Weights may have been defined by Advisor's mapping.
         if (theme == null || theme.isEmpty())
         {
            theme = InvConst.DEFAULT_THEME;
         }

         if (advisor == null || advisor.isEmpty())
         {
            advisor = InvConst.INVESSENCE_ADVISOR;
         }

         // Taxable Strategy Introduced 2/9/2015
         if (profileData.getAccountTaxable())
         {
            if (!theme.toUpperCase().startsWith("T."))
            {
               theme = "T." + theme;
            }
         }


         ArrayList<String> assetList = assetData[0].getOrderedAsset();
         Double riskOffset = assetData[0].getRiskOffset();

         duration = profileData.getDefaultHorizon();


         double totalIncEarned = 0.0;
         double investment = invCapital - keepLiquidCash;

         if (profileData.getRecurringInvestment() == null)
         {
            reinvestment = 0.0;
         }
         else
         {
            reinvestment = profileData.getRecurringInvestment().doubleValue();
         }

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

         if (profileData.getRiskCalcMethod() == null || profileData.getRiskCalcMethod().startsWith("C"))
         {
            return getConsumerPortfolio(assetData, profileData,
                                        advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }
         else
         {
            return getAdvisorPortfolio(assetData, profileData,
                                       advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }

      }
      catch (Exception ex)
      {

      }
      return null;
   }

   public Integer getPortfolioIndex(ProfileData pdata)
   {

      if (pdata == null)
      {
         return InvConst.PORTFOLIO_DEFAULT_POINT;
      }

      if (pdata.getAssetData() == null)
      {
         return InvConst.PORTFOLIO_DEFAULT_POINT;
      }

      Double riskOffset = pdata.getAssetData()[0].getRiskOffset();
      Integer duration = pdata.getDefaultHorizon();

      int offset = (int) (StrictMath.sqrt(StrictMath.pow(duration, 2.0) - StrictMath.pow((double) 0, 2.0))) * (InvConst.PORTFOLIO_INTERPOLATION / duration);
      offset = (int) (riskOffset * (double) offset);
      offset = (offset > InvConst.PORTFOLIO_INTERPOLATION - 1) ? InvConst.PORTFOLIO_INTERPOLATION - 1 : offset;
      if (offset < 0)
      {
         offset = 0;
      }

      return offset;
   }

   private Portfolio[] getAdvisorPortfolio(AssetClass[] assetData, ProfileData profileData,
                                           String advisor, String theme, Double invCapital, Double investment,
                                           Double reinvestment, Double keepLiquidCash, Integer duration, Double riskOffset)
   {
      String assetName;

      try
      {
         double actualInvestment = investment;

         //Asset data is for each year of investment horizon
         int years = (profileData.getNumOfPortfolio() == null || profileData.getNumOfPortfolio() == 0) ? profileData.getAssetData().length : profileData.getNumOfPortfolio();
         years = (profileData.getAssetData().length < years) ? profileData.getAssetData().length : years;
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
            createPortfolio(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                            investment, investmentYear, profileData, offset);

            // Total Money = Investment + Performance
            portfolioclass[investmentYear].setTotalMoney(investment);
            actualInvestment += reinvestment;

            // Total Money = Investment + Performance
            if (investmentYear == 0)
            {
               portfolioclass[investmentYear].setTotalMoney(investment + keepLiquidCash);
            }
            else
            {
               portfolioclass[investmentYear].setTotalMoney(investment);
            }

            if (investmentYear > 0)
            {
               portfolioclass[investmentYear].setUpperTotalMoney((2 * portfolioclass[investmentYear - 1].getTotalRisk() * investment)
                                                                    + investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment -
                                                                    (2 * portfolioclass[investmentYear - 1].getTotalRisk() * investment));
            }
            else
            {
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

   private Portfolio[] getConsumerPortfolio(AssetClass[] assetData, ProfileData profileData,
                                            String advisor, String theme, Double invCapital, Double investment,
                                            Double reinvestment, Double keepLiquidCash, Integer duration, Double riskOffset)
   {
      String assetName;

      try
      {
         double actualInvestment = investment;

         //Asset data is for each year of investment horizon
         int years = (profileData.getNumOfPortfolio() == null || profileData.getNumOfPortfolio() == 0) ? profileData.getAssetData().length : profileData.getNumOfPortfolio();
         years = (profileData.getAssetData().length < years) ? profileData.getAssetData().length : years;
         Portfolio[] portfolioclass = new Portfolio[years];
         for (int investmentYear = 0; investmentYear < years; investmentYear++)
         {
            portfolioclass[investmentYear] = new Portfolio();
            portfolioclass[investmentYear].setTheme(theme);

            //JAV added keepLiquid
            portfolioclass[investmentYear].setCashMoney(actualInvestment);


            int offset;
            double riskScore = profileData.getRiskIndex();
            riskScore = StrictMath.sqrt(1.0 - (riskScore / (double) InvConst.MAX_RISK_OFFSET));
            offset = (int) (InvConst.PORTFOLIO_INTERPOLATION * riskScore);
            //offset = (int) (StrictMath.sqrt(StrictMath.pow(duration, 2.0) - StrictMath.pow((double) investmentYear, 2.0)))*(InvConst.PORTFOLIO_INTERPOLATION/duration);
            //offset = (int) (riskOffset * (double) offset);
            offset = (offset > InvConst.PORTFOLIO_INTERPOLATION - 1) ? InvConst.PORTFOLIO_INTERPOLATION - 1 : offset;
            if (offset < 0)
            {
               offset = 0;
            }

            if (investmentYear == 0)
            {
               profileData.setPortfolioIndex(offset);
            }

            // Actual Investment is investment and recurring the next year.  Does not contain the returns.
            portfolioclass[investmentYear].setActualInvestments(actualInvestment);
            createPortfolio(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                            investment, investmentYear, profileData, offset);

            // Total Money = Investment + Performance
            if (investmentYear == 0)
            {
               portfolioclass[investmentYear].setTotalMoney(investment + keepLiquidCash);
            }
            else
            {
               portfolioclass[investmentYear].setTotalMoney(investment);
            }

            portfolioclass[investmentYear].setRecurInvestments(reinvestment);
            actualInvestment += reinvestment;

            if (investmentYear > 0)
            {
               portfolioclass[investmentYear].setUpperTotalMoney((2 * portfolioclass[investmentYear - 1].getTotalRisk() * investment)
                                                                    + investment);
               portfolioclass[investmentYear].setLowerTotalMoney(investment -
                                                                    (2 * portfolioclass[investmentYear - 1].getTotalRisk() * investment));
            }
            else
            {
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

   private void createPortfolio(String advisor, String theme,
                                AssetClass assetClass, Portfolio pclass, double investment,
                                int year, ProfileData pdata, int offset)
   {
      try
      {
         if (theme.toLowerCase().contains("mfs"))
         {
            createPortfolioWithMFS(advisor, theme, assetClass, pclass,
                                   investment, year, pdata, offset);
            return;

         }

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

               if (assetname.equalsIgnoreCase("CASH"))
               {
                  SecurityData sd = secCollection.getSecurity("CASH");
                  assetWgt = (amount_remain + pdata.getKeepLiquid()) / investment;
                  double cash = amount_remain + pdata.getKeepLiquid();
                  investByAsset = amount_remain;

                  totalPortfolioWeight = assetWgt;
                  pclass.setPortfolio(sd.getTicker(), sd.getName(), asset.getColor(),
                                      sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getSubassetclass(),
                                      1.0, assetWgt,
                                      0.0, 0.0, 0.0, 0.0,
                                      cash, cash, 999999, assetWgt);
                  pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                        asset.getColor(),
                                        assetWgt, amount_remain, true);
                  portfolioRisk = portfolioRisk + assetdata.getPrimeAssetrisk()[offset] * totalPortfolioWeight;
                  portfolioReturns = portfolioReturns + assetdata.getPrimeAssetreturns()[offset] * totalPortfolioWeight;
                  break;
               }

               ticker_weight = assetWgt * primeAssetWeights[offset][tickerNum];
               if (secCollection.getOrderedSecurityList(advisor, theme, primeassetclass) != null)
               {
                  for (SecurityData sd : secCollection.getOrderedSecurityList(advisor, theme, primeassetclass))
                  {
                     PrimeAssetClassData pacd = portfolioOptimizer.getPrimeAssetData(theme, assetname, primeassetclass);
                     double price = sd.getDailyprice();
                     double rbsa_weight = ticker_weight * sd.getRbsaWeight();  // RBSA PREP WORK:  Currently all have rate of 1
                     // If there is no weight, just skip this ticker all together.
                     double shares = 0.0, money = 0.0;
                     if (rbsa_weight > 0.0 && price > 0.0)
                     {
                        shares = Math.round(((investment * rbsa_weight) / price) - 0.5);
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
                                               price, rbsa_weight,
                                               0.0, 0.0, 0.0, 0.0,
                                               shares, money, pacd.getSortorder(), totalPortfolioWeight);
                           pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                                 asset.getColor(),
                                                 totalPortfolioWeight, money, true);


                           secExpense = secExpense + 0.0 * rbsa_weight;
                        }
                        amount_remain = amount_remain - money;
                        pclass.setCashMoney(amount_remain);
                        portfolioRisk = portfolioRisk + assetdata.getPrimeAssetrisk()[offset] * totalPortfolioWeight;
                        double pAssetreturns = assetdata.getPrimeAssetreturns()[offset];
                        portfolioReturns = portfolioReturns + assetdata.getPrimeAssetreturns()[offset] * totalPortfolioWeight;

                        ticker_weight = ticker_weight - rbsa_weight;
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

   private void createPortfolioWithMFS(String advisor, String theme,
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


         Map<String, String> tickerMap = new LinkedHashMap<String, String>();
         ArrayList<String> tickerList = new ArrayList<String>();
         ArrayList<Double> primeWeights = new ArrayList<Double>();
         Integer sizeofTickerList = 0;

         // secCollection.doCustomSQLQuery(advisor, theme, tickerList); // Use this to load Security details for given Tickers
         for (String assetname : portfolioOptimizer.getAdvisorOrdertedAssetList(theme))
         {
            int tickerNum = 0;
            AssetData assetdata = portfolioOptimizer.getAssetData(theme, assetname);
            Asset asset = assetClass.getAsset(assetname);
            assetClass.getAsset(assetname).setValue(0);

            if (!asset.getAsset().toUpperCase().equals("CASH"))
            {
               for (String primeassetclass : assetdata.getOrderedPrimeAssetList())
               {
                  for (SecurityData sd : secCollection.getOrderedSecurityList(advisor, theme, primeassetclass))
                  {
                     if (!tickerMap.containsKey(sd.getTicker()))
                     {
                        if (!sd.getTicker().toUpperCase().equals("CASH"))
                        {
                           sizeofTickerList++;
                           tickerMap.put(sd.getTicker(), sd.getTicker());
                        }
                     }
                  }

                  primeWeights.add(asset.getUserweight() * assetdata.getPrimeAssetweights()[offset][tickerNum++]);
               }

            }

         }

         String[] tickers = new String[sizeofTickerList];
         int j = 0;
         for (String ticker : tickerMap.keySet())
         {
            if (!ticker.toUpperCase().equals("CASH"))
            {
               if (j < sizeofTickerList)
               {
                  tickers[j] = ticker;
                  tickerList.add(ticker);
                  j++;
               }
            }
         }

         double[][] tmpPrimeWeights = new double[primeWeights.size()][1];

         for (j = 0; j < primeWeights.size(); j++)
         {
            tmpPrimeWeights[j][0] = primeWeights.get(j);
         }

         double[] optFundWeight = portfolioOptimizer.getHolisticWeight(theme, tickers, tmpPrimeWeights);

         // Now that we have optomized Portfolio, let's do the allocation and rollup to appropriate AssetClass and PrimeAssetClass
         double investByAsset = 0.0;
         double cash = investment;
         String ticker;
         AssetClass[] newAssetclass = new AssetClass[1];
         SecurityData sd;

         Map<String, Asset> newAssets = new HashMap<String, Asset>();
         for (Integer i = 0; i < optFundWeight.length; i++)
         {
            ticker = tickers[i]; // NOTE: Tickers are in same order as weights...
            ticker_weight = optFundWeight[i];
            HolisticModelOptimizer hoptimizer = portfolioOptimizer.getHoptimizer();
            sd = secCollection.getSecurity(ticker);
            AssetData assetdata = portfolioOptimizer.getAssetData(theme, sd.getAssetclass());
            if (! assetClass.getAssetclass().containsKey(sd.getAssetclass())) {
               assetClass.addAssetClass(sd.getAssetclass(),sd.getAssetclass(),sd.getAssetcolor(),0.0,0.0);
            }
            Asset asset = assetClass.getAsset(sd.getAssetclass());
            for (PrimeAssetClassData pacd : hoptimizer.getHolisticdataMap().get(ticker).getPrimeassets().values()) {
               if (! sd.getTicker().toUpperCase().equals("CASH"))
               {
                  double shares = 0.0, money = 0.0;
                  double rbsa_weight = pacd.getWeight() * ticker_weight;
                  Double price = sd.getDailyprice();
                  if (rbsa_weight > 0.0 && price > 0.0)
                  {
                     shares = Math.round(((investment * rbsa_weight) / price) - 0.5);
                     money = shares * price;

                     // Only create this portfolio if there are shares and money
                     if ((shares > 0.0) && (money > 0.0))
                     {
                        totalPortfolioWeight = 0.0;
                        if (investment > 0.0)
                        {
                           totalPortfolioWeight = money / investment;
                        }

                        pclass.setPortfolio(sd.getTicker(), sd.getName(), sd.getAssetcolor(),
                                            sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getPrimeassetclass(),
                                            price, rbsa_weight,
                                            0.0, 0.0, 0.0, 0.0,
                                            shares, money, sd.getSortorder(), totalPortfolioWeight);
                        pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                              sd.getAssetcolor(),
                                              totalPortfolioWeight, money, true);


                        secExpense = secExpense + 0.0 * rbsa_weight;
                     }
                     cash = cash - money;
                     pclass.setCashMoney(cash);
                     //portfolioRisk = portfolioRisk + assetdata.getPrimeAssetrisk()[offset] * totalPortfolioWeight;
                     //double pAssetreturns =  assetdata.getPrimeAssetreturns()[offset];
                     //portfolioReturns = portfolioReturns + assetdata.getPrimeAssetreturns()[offset] * totalPortfolioWeight;

                     asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
                     asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);
                     Double moneyInvestedinThisAsset = asset.getValue() + money;
                     asset.setValue(moneyInvestedinThisAsset);
                     asset.setActualweight(moneyInvestedinThisAsset / investment);

                  }
               }
            }
         }
         // Add remining into cash...
         if (cash > 0.0)  {
            ticker = "Cash";
            sd = secCollection.getSecurity(ticker);;
            Asset asset = assetClass.getAsset(sd.getAssetclass());
            pclass.setPortfolio(sd.getTicker(), sd.getName(), sd.getAssetcolor(),
                                sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getSubassetclass(),
                                1.0, 1.0,
                                0.0, 0.0, 0.0, 0.0,
                                cash, cash, sd.getSortorder(), totalPortfolioWeight);
            pclass.addSubclassMap(sd.getAssetclass(), sd.getSubassetclass(),
                                  asset.getColor(),
                                  totalPortfolioWeight, cash, true);
         }


      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
