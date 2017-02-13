package com.invmodel.portfolio;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.dao.data.*;
import com.invmodel.dao.invdb.*;
import com.invmodel.dao.rbsa.HolisticModelOptimizer;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.*;
import com.invmodel.portfolio.data.Portfolio;

public class PortfolioModel
{

   //private SecurityDBCollection securityDao;
   private static  PortfolioModel instance;
   private SecurityCollection secCollection;
   private PortfolioOptimizer portfolioOptimizer;
   private FixedModelOptimizer fixedOptimizer;

   public static synchronized PortfolioModel getInstance()
   {
      if (instance == null)
      {
         instance = new PortfolioModel();
      }

      return instance;
   }

   public PortfolioModel()
   {
      portfolioOptimizer = PortfolioOptimizer.getInstance();
      fixedOptimizer = FixedModelOptimizer.getInstance();
      secCollection = new SecurityCollection();
   }

   public void setPortfolioOptimizer(PortfolioOptimizer portfolioOptimizer)
   {
      this.portfolioOptimizer = portfolioOptimizer;
   }

   public void setSecurityDao(SecurityCollection secCollection)
   {
      this.secCollection = secCollection;
   }

   public void setFixedOptimizer(FixedModelOptimizer fixedOptimizer)
   {
      this.fixedOptimizer = fixedOptimizer;
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

         if (fixedOptimizer != null)
         {
            if (fixedOptimizer.isThisFixedTheme(theme))
            {
               profileData.setFixedModel(true);
               profileData.setNumOfPortfolio(1);
               Portfolio[] portfolio = new Portfolio[profileData.getNumOfPortfolio()];
               profileData.setMaxPortfolioAllocationPoints(fixedOptimizer.getThemes(theme).size() - 1);
               portfolio[0] = createFixedPortfolio(assetData, profileData);

               return portfolio;
            }
         }

         // NOTE:  If Theme is default, then use Invessence - Core portfolio.
         // However, the Asset Weights may have been defined by Advisor's mapping.
         if (!portfolioOptimizer.isValidTheme(theme))
         {
            theme = InvConst.DEFAULT_THEME;
         }

         if (advisor == null || advisor.isEmpty())
         {
            advisor = InvConst.INVESSENCE_ADVISOR;
         }
         profileData.setMaxPortfolioAllocationPoints(InvConst.PORTFOLIO_INTERPOLATION - 1);
         if (profileData.getRiskCalcMethod() == null || profileData.getRiskCalcMethod().startsWith("C"))
         {
            return getPortfolioByIndex(assetData, profileData, profileData.getRiskIndex().intValue(),
                                      advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }
         else
         {
            return getPortfolioByIndex(assetData, profileData, profileData.getPortfolioIndex(),
                                       advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }

/*
         if (profileData.getRiskCalcMethod() == null || profileData.getRiskCalcMethod().startsWith("C"))
         {
            return getPortfolioByRisk(assetData, profileData,
                                      advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }
         else
         {
            return getPortfolioByIndex(assetData, profileData,
                                       advisor, theme, invCapital, investment, reinvestment, keepLiquidCash, duration, riskOffset);
         }
*/

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

   private Portfolio[] getPortfolioByIndex(AssetClass[] assetData, ProfileData profileData, Integer riskIndex,
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
         int offset = (riskIndex == null) ? InvConst.PORTFOLIO_DEFAULT_POINT : riskIndex;
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
            if (theme.toLowerCase().contains("mfs"))
            {
               createPortfolioWithMFS(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                                      investment, investmentYear, profileData, offset);
            }
            else {
               createPortfolio(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                               investment, investmentYear, profileData, offset);

            }

            // Total Money = Investment + Projection
            portfolioclass[investmentYear].setTotalMoney(investment);
            actualInvestment += reinvestment;

            // Total Money = Investment + Projection
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

   private Portfolio[] getPortfolioByRisk(AssetClass[] assetData, ProfileData profileData,
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
            if (theme.toLowerCase().contains("mfs"))
            {
               createPortfolioWithMFS(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                                      investment, investmentYear, profileData, offset);
            }
            else {
               createPortfolio(advisor, theme, assetData[investmentYear], portfolioclass[investmentYear],
                               investment, investmentYear, profileData, offset);
            }

            // Total Money = Investment + Projection
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

         double amount_remain = investment;

         double totalPortfolioWeight = 0.0;
         double ticker_weight;
         double portfolioReturns = 0.0;
         double portfolioRisk = 0.0;
         double avgExpense = 0.0;
         double incEarned = 0.0;
         double secExpense = 0.0;

         double investByAsset = 0.0;
         AssetData assetdata;
         Asset asset;
         double[][] primeAssetWeights;
         double assetWgt;
         // Prime asset class loop
         for (String assetname : portfolioOptimizer.getAdvisorOrdertedAssetList(theme))
         {
            investByAsset = 0.0;
            assetdata = portfolioOptimizer.getAssetData(theme, assetname);
            asset = assetClass.getAsset(assetname);
            primeAssetWeights = assetdata.getPrimeAssetweights();
            assetWgt = asset.getUserweight();
            int tickerNum = 0;
            if (! assetname.equalsIgnoreCase("CASH"))
            {
               for (String primeassetclass : assetdata.getOrderedPrimeAssetList())
               {
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
                                                  sd.getType(), sd.getStyle(),
                                                  sd.getSecurityAssetClass(), sd.getSecuritySubAssetClass(),
                                                  price, rbsa_weight,
                                                  0.0, 0.0, 0.0, 0.0,
                                                  shares, money, pacd.getSortorder(), totalPortfolioWeight);
                              pclass.addSubclassMap(sd.getSecurityAssetClass(), sd.getSecuritySubAssetClass(),
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

               if (assetdata.getPrimeAssetreturns() != null)
               {
                  asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
                  asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);
               }
               asset.setValue(investByAsset);
               asset.setActualweight(investByAsset / investment);
            }
         }

         if (amount_remain >= 0)
         {
            SecurityData sd = secCollection.getSecurity("CASH");
            assetdata = portfolioOptimizer.getAssetData(theme, "Cash");
            asset = assetClass.getAsset("Cash");;
            assetWgt = (amount_remain + pdata.getKeepLiquid()) / investment;
            double cash = amount_remain + pdata.getKeepLiquid();
            investByAsset = amount_remain;

            totalPortfolioWeight = assetWgt;
            pclass.setPortfolio(sd.getTicker(), sd.getName(), asset.getColor(),
                                sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getPrimeassetclass(),
                                1.0, assetWgt,
                                0.0, 0.0, 0.0, 0.0,
                                cash, cash, 999999, assetWgt);
            pclass.addSubclassMap(sd.getAssetclass(), sd.getPrimeassetclass(),
                                  asset.getColor(),
                                  assetWgt, amount_remain, true);
            if ( assetdata.getPrimeAssetrisk() != null)
            {
               portfolioRisk = portfolioRisk + assetdata.getPrimeAssetrisk()[offset] * totalPortfolioWeight;
               portfolioReturns = portfolioReturns + assetdata.getPrimeAssetreturns()[offset] * totalPortfolioWeight;
            }

            if (assetdata.getPrimeAssetreturns() != null)
            {
               asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
               asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);
            }
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


         Map<String, Integer> tickerMap = new LinkedHashMap<String, Integer>();
         ArrayList<String> tickerList = new ArrayList<String>();
         Map<String, Double> primeWeights = new LinkedHashMap<String, Double>();
         Integer sizeofTickerList = 0;
         String addTicker = "";

         for (SecurityData sd : secCollection.getOrderedSecurityList())
         {
            addTicker = sd.getTicker();
            if (!addTicker.toUpperCase().equals("CASH"))
            {
               if (!tickerMap.containsKey(addTicker))
               {
                  tickerMap.put(addTicker, sizeofTickerList);
                  tickerList.add(addTicker);
                  sizeofTickerList++;
               }
            }
         }

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

         String[] tickers = new String[sizeofTickerList];
         int j = 0;
         for (String ticker : tickerList)
         {
            if (j < sizeofTickerList)
            {
               tickers[j] = ticker;
               j++;
            }
         }

         // Since PrimeAssetList order is different then Security List, we are putting the data in order of the security list.
         Integer sizeofPrimeTickerList = primeWeights.size();
         double[][] tmpPrimeWeights = new double[sizeofPrimeTickerList][1];
         for (String ticker : primeWeights.keySet())
         {
            if (tickerMap.containsKey(ticker))
            {
               if (tickerMap.get(ticker) <= sizeofPrimeTickerList)
               {
                  j = tickerMap.get(ticker);
                  tmpPrimeWeights[j][0] = primeWeights.get(ticker);
               }
            }
         }

         HolisticOptimizedData hoptdata = portfolioOptimizer.getHolisticWeight(theme, tickers, tmpPrimeWeights, primeWeights);
         hoptdata.setPrimeAssetInfo(primeWeights);

         // Now that we have optomized Portfolio, let's do the allocation and rollup to appropriate AssetClass and PrimeAssetClass
         double investByAsset = 0.0;
         double cash = investment;
         String ticker;
         AssetClass[] newAssetclass = new AssetClass[1];
         SecurityData sd;

         Map<String, Asset> newAssets = new HashMap<String, Asset>();
         for (Integer i = 0; i < hoptdata.getRbsatickers().length; i++)
         {
            ticker = hoptdata.getRbsatickers()[i]; // NOTE: Tickers are in same order as weights...
            ticker_weight = hoptdata.getOptimizedWeights()[i];
            HolisticModelOptimizer hoptimizer = portfolioOptimizer.getHoptimizer();
            double shares = 0.0, money = 0.0;
            double rbsa_weight = 0.0;
            double price = 0.0;
            for (PrimeAssetClassData pacd : hoptimizer.getHolisticdataMap().get(ticker).getPrimeassets().values())
            {
               sd = secCollection.getSecurity(theme, pacd.getTicker());
               if (sd == null)
               {
                  sd = secCollection.getSecurity(ticker);
               }

               if (!sd.getTicker().toUpperCase().equals("CASH"))
               {

                  rbsa_weight = pacd.getWeight() * ticker_weight;
                  price = sd.getDailyprice();
                  if (rbsa_weight > 0.0 && price > 0.0)
                  {
                     shares = Math.round(((investment * rbsa_weight) / price) - 0.5);
                     money = shares * price;
                     if (shares > 0 && money > 0.0)
                     {
                        String assetname = sd.getAssetclass();
                        AssetData assetdata = portfolioOptimizer.getAssetData(theme, assetname);
                        if (!assetClass.getAssetclass().containsKey(assetname))
                        {
                           assetClass.addAssetClass(assetname, assetname, sd.getAssetcolor(), 0.0, 0.0);
                        }
                        Asset asset = assetClass.getAsset(assetname);
                        Double moneyInvestedinThisAsset = asset.getValue() + money;
                        asset.setValue(moneyInvestedinThisAsset);
                        asset.setActualweight(moneyInvestedinThisAsset / investment);
                        // asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
                        // asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);

                        pclass.setPortfolio(sd.getTicker(), sd.getName(), sd.getAssetcolor(),
                                            sd.getType(), sd.getStyle(),
                                            sd.getSecurityAssetClass(), sd.getSecuritySubAssetClass(),
                                            price, rbsa_weight,
                                            0.0, 0.0, 0.0, 0.0,
                                            shares, money, sd.getSortorder(), totalPortfolioWeight);
                        pclass.addSubclassMap(sd.getSecurityAssetClass(), sd.getSecurityAssetClass(),
                                              sd.getAssetcolor(),
                                              totalPortfolioWeight, money, true);


                        secExpense = secExpense + 0.0 * rbsa_weight;

                        cash = cash - money;
                        pclass.setCashMoney(cash);
                     }
                  }
               }
            }
         }
         // Add remining into cash...
         if (cash > 0.0)
         {
            ticker = "Cash";
            sd = secCollection.getSecurity(ticker);

            Asset asset = assetClass.getAsset(sd.getAssetclass());
            // asset.setExpectedReturn(assetdata.getPrimeAssetreturns()[offset]);
            // asset.setRisk(assetdata.getPrimeAssetrisk()[offset]);
            Double moneyInvestedinThisAsset = asset.getValue() + cash;
            asset.setValue(moneyInvestedinThisAsset);
            asset.setActualweight(moneyInvestedinThisAsset / investment);
            pclass.setPortfolio(sd.getTicker(), sd.getName(), sd.getAssetcolor(),
                                sd.getType(), sd.getStyle(), sd.getAssetclass(), sd.getPrimeassetclass(),
                                1.0, 1.0,
                                0.0, 0.0, 0.0, 0.0,
                                cash, cash, sd.getSortorder(), totalPortfolioWeight);
            pclass.addSubclassMap(sd.getAssetclass(), sd.getPrimeassetclass(),
                                  asset.getColor(),
                                  totalPortfolioWeight, cash, true);
         }

         pclass.setTotalRisk(hoptdata.getRiskOffset(hoptdata.getOffset()));
         pclass.setExpReturns(hoptdata.getPortReturnsOffset(hoptdata.getOffset()));
         pclass.setTotalCapitalGrowth(0.0);
         pclass.setAvgExpense(0.0 * investment);
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

   private Portfolio createFixedPortfolio(AssetClass[] assetData, ProfileData pdata)
   {

      String theme = pdata.getTheme();
      Double price;
      String type, style;
      String assetname, subclass, color, sec_name;
      Integer sortorder;
      Portfolio portfolio = new Portfolio();
      double totalWeight = 1.0;
      double investment = pdata.getDefaultInvestment();
      double amount_remain = investment;

      portfolio.setTheme(pdata.getTheme());
      portfolio.setCashMoney(investment);
      FMData fixedModelData;
      if (pdata.getRiskCalcMethod().equalsIgnoreCase("C"))
      {
         fixedModelData = fixedOptimizer.getTheme(theme, assetData[0].getRiskOffset());
      }
      else
      {
         fixedModelData = fixedOptimizer.getThemeByIndex(theme, pdata.getPortfolioIndex());
      }

      pdata.setFixedFMModel(fixedModelData);
      if (fixedModelData != null)
      {
         if (fixedModelData.getExpectedreturn() != null && fixedModelData.getExpectedreturn() != 0.0)
         {
            pdata.setHasReturn(true);
            portfolio.setExpReturns(fixedModelData.getExpectedreturn());
         }
         else
         {
            pdata.setHasReturn(false);
         }

         if (fixedModelData.getExpectedrisk() != null && fixedModelData.getExpectedrisk() != 0.0)
         {
            pdata.setHasRisk(true);
            portfolio.setTotalRisk(fixedModelData.getExpectedrisk());
         }
         else
         {
            pdata.setHasRisk(false);
         }

         pdata.setAllocationIndex(fixedModelData.getIndex());
         pdata.setPortfolioIndex(fixedModelData.getIndex());
         for (FMPortfolioData portfoliodata : fixedModelData.getPortfolioData())
         {
            Double wght = portfoliodata.getWeight();
            String ticker = portfoliodata.getTicker();
            double shares = 0.0;

            if (wght < 0)
            {
               wght = 0.0;
            }
            price = 100.00;
            type = portfoliodata.getAsset();
            style = portfoliodata.getDisplayname();
            subclass = portfoliodata.getDisplayname();
            assetname = portfoliodata.getAsset();
            color = portfoliodata.getColor();
            sortorder = portfoliodata.getSortorder();
            sec_name = portfoliodata.getSec_name();


            if (ticker.equalsIgnoreCase("Cash"))
            {
               wght = totalWeight;
            }
            else
            {
               if ((totalWeight - wght) < 0.0)
               {
                  wght = totalWeight;
               }
            }
            totalWeight -= wght;
            if (price == null || price == 0.0)
            {
               shares = 1.0;
            }
            else
            {
               shares = Math.round(((investment * wght) / price) - 0.5);
            }

            Double money = wght * investment;
            Double totalPortfolioWeight = money / investment;
            portfolio.setPortfolio(ticker, sec_name, color,
                                   type, style, assetname, subclass,
                                   price, wght,
                                   0.0, 0.0, 0.0, 0.0,
                                   shares, money, sortorder, totalPortfolioWeight);
            portfolio.addSubclassMap(assetname, subclass,
                                     color,
                                     totalPortfolioWeight, amount_remain, true);
            amount_remain = amount_remain - money;
            portfolio.setCashMoney(amount_remain);
         }
         if (!portfolio.getPortfolioMap().containsKey("Cash"))
         {
            Double money = totalWeight * investment;
            portfolio.setPortfolio("Cash", "Cash", null,
                                   null, null, "Cash", "Cash",
                                   1.0, totalWeight,
                                   0.0, 0.0, 0.0, 0.0,
                                   money, money, 999999, totalWeight);
            portfolio.addSubclassMap("Cash", "Cash",
                                     null,
                                     totalWeight, money, true);

         }
         portfolio.setExpReturns(fixedModelData.getExpectedreturn());
         portfolio.setTotalRisk(fixedModelData.getExpectedrisk());
         portfolio.setTotalCapitalGrowth(0.0);
         portfolio.setAvgExpense(0.0);

      }

      return portfolio;
   }

}
