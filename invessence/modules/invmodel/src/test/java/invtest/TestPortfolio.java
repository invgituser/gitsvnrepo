package invtest;

import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.dao.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.*;

import static java.lang.String.valueOf;

/**
 * Hello world!
 */
public class TestPortfolio
{

   public static void main(String[] args) throws Exception
   {
      testPorfolioModel(args);
   }

   public static void testPorfolioModel(String[] args) throws Exception
   {
      Integer age, duration;
      String risk;
      // Get asset allocation
      if (args.length > 0)
      {
         age = Integer.parseInt(args[0]);
      }
      else
      {
         age = 30;
      }

      if (age < 21)
      {
         age = 21;
      }
      if (age > 100)
      {
         age = 100;
      }

      // we also have risk if it is second arg
      if (args.length > 1)
      {
         risk = args[1];
      }
      else
      {
         risk = "M";
      }

      // we have horizon/duration if we have third arg.
      if (args.length > 2)
      {
         duration = Integer.parseInt(args[2]);
      }
      else
      {
         duration = 100 - age;
      }

      ProfileData profileData = new ProfileData();
      profileData.setName("Retirement");
      profileData.setAccountTaxable(false);

      profileData.setAge(40);
      profileData.setHorizon(30);
      profileData.setRiskIndex(0);

      //1 = preservation 2 = Accumulation
      profileData.setObjective(2);
      //1 = Go to Cash, 2 = Stay Invested
      profileData.setStayInvested(2);

      profileData.setInitialInvestment(100000);
      profileData.setRecurringInvestment(0);

      profileData.setDependent(2);
      profileData.setTotalIncome(120000);
      profileData.setLiquidAsset(50000);
      profileData.setTotalExpense(120000);
      profileData.setNumOfAllocation(duration);

      profileData.setRisk("M");
      profileData.adjustRiskIndex();

      AssetAllocationModel assetAllocationModel = AssetAllocationModel.getInstance();
      assetAllocationModel.setAssetdao(AssetDBCollection.getInstance());
      assetAllocationModel.setHr(HistoricalReturns.getInstance());
      AssetClass[] aamc = assetAllocationModel.getAssetDistribution(profileData);

      PortfolioModel portfolioModel = PortfolioModel.getInstance();
      portfolioModel.setMonthlyDao(DailyReturns.getInstance());
      portfolioModel.setSecurityDao(SecurityDBCollection.getInstance());
      profileData.setNumOfPortfolio(aamc.length);
      Portfolio[] pfclass = portfolioModel.getDistributionList(aamc, profileData);


      double totalMoney = 0.0;
      double totalWeight = 0.0;
      for (int i = 0; i < pfclass[0].getPortfolio().size(); i++)
      {
         PortfolioSecurityData pfList = pfclass[0].getPortfolio().get(i);
         System.out.println("Ticker: " + pfList.getTicker() +
                               ": Type->" + pfList.getAssetclass() +
                               ": SubType->" + pfList.getSubclass() +
                               ": Weight->" + valueOf(pfList.getWeightsAsInt()) +
                               ": Price->" + valueOf(pfList.getDailyprice()) +
                               ": Share->" + valueOf(pfList.getShares()) +
                               ": Money->" + valueOf(pfList.getMoney()));
         totalWeight += pfList.getWeight();
         totalMoney += pfList.getMoney();
      }
      System.out.println("\n");
      System.out.println("Total" +
                            " Weight->" + valueOf(totalWeight) +
                            " Money->" + valueOf(Math.round((totalMoney * 100.00) / 100.0)));
   }
}