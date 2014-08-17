package invtest;

import com.invmodel.asset.*;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.*;
import com.invmodel.portfolio.data.*;
import com.invmodel.dao.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import static java.lang.String.valueOf;

/**
 * Hello world!
 */
public class TestDistribution
{
   private static TestDistribution instance = null;

   public static synchronized TestDistribution getInstance()
   {
      if (instance == null)
      {
         instance = new TestDistribution();
      }

      return instance;
   }


   /**
    * @param args
    */
   public static void main(String[] args) throws Exception
   {
      //getPortfolioAllocation(args);
      //testAllocation(args);
      //testPorfolioModel(args);
      //testPorfolioModel_noargs();
      testPerformanceModel(args);
      //testAllocationV2(args);
   }

   public static void testPerformanceModel(String[] args) throws Exception
   {
      AssetDBCollection assetdao = AssetDBCollection.getInstance();
      SecurityDBCollection securityDao = SecurityDBCollection.getInstance();
      ClientProfileDataLoader clientDao = ClientProfileDataLoader.getInstance();


      int age, duration;
      String risk, tax;

      Integer invCapital, riskOffset;
      Integer yearlyInvestments;
      Integer stayInvested;
      String name = "";
      ProfileData profileData = new ProfileData();

      tax = "No";

      profileData.setName("Retirement");
      profileData.setAdvisor("Sam");
      profileData.setTheme("Balance");

      profileData.setAge(30);
      age = profileData.getAge();

      profileData.setHorizon(30);
      duration = profileData.getHorizon();

      profileData.setAccountTaxable(false    );
      profileData.setRiskIndex(4);

      //1 = preservation 2 = Accumulation
      profileData.setObjective(2);
      //2 = Go to Cash, 1 = Stay Invested
      profileData.setStayInvested(1);

      profileData.setInitialInvestment(100000);
      invCapital = profileData.getInitialInvestment();
      profileData.setRecurringInvestment(0);

      profileData.setDependent(2);
      profileData.setTotalIncome(120000);
      profileData.setLiquidAsset(50000);
      profileData.setTotalExpense(120);
      profileData.setNumOfAllocation(duration);

      profileData.setRisk("M");
      risk = profileData.getRisk();

      profileData.adjustRiskIndex();

      AssetAllocationModel assetAllocationModel = AssetAllocationModel.getInstance();
      assetAllocationModel.setAssetdao(AssetDBCollection.getInstance());
      assetAllocationModel.setHr(HistoricalReturns.getInstance());
      AssetClass[] aamc = assetAllocationModel.getAssetDistribution(profileData);

      profileData.setNumOfPortfolio(aamc.length);
      String[] orderedAssets = assetdao.getOrderedAsset(profileData.getAdvisor());

      //Create a file for initial asset allocation
      createFirstAssetAllocationChart(tax, duration, age, risk, invCapital, assetdao, aamc, orderedAssets);

      PortfolioModel portfolioModel = PortfolioModel.getInstance();
      portfolioModel.setMonthlyDao(DailyReturns.getInstance());
      portfolioModel.setSecurityDao(SecurityDBCollection.getInstance());
      profileData.setNumOfPortfolio(aamc.length);
      Portfolio[] pfclass = portfolioModel.getDistributionList(aamc, profileData);

      //Create a assetPerformanceFile
      createAssetPerformanceFile(tax, pfclass, aamc);

      createHoldingsFile(pfclass, tax, aamc, profileData);
   }

   public static void createHoldingsFile(Portfolio[] pfclass, String tax, AssetClass[] aamc, ProfileData profileData) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      double totalMoney = 0.0;

      double[] portfolioRisk = new double[aamc.length];
      double[] portfolioReturn = new double[aamc.length];
      double[] secPortfolioRisk = new double[aamc.length];

      if (tax.equals("No"))
      {
         fileName = "iraPortfolioHoldings.csv";
      }
      else
      {
         fileName = "taxablePortfolioHoldings.csv";
      }

      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      writer.println("Year" + "," + "Ticker" + "," + "Sub Asset Class" + "," + "Weight per Asset" + "," + "Price" + "," + "Shares" +
                        "," + "Investment Value" + "," + "Weight per portfolio" + "," + "New Investemnets" + "," + "Total Assets" +
                        "," + "Expected Return" + "," + "Yield" + "," + "Sec Risk");

      //for(int year = aamc.length-1; year >=0; year--)  {

      int year = 0;
      profileData.setNumOfPortfolio(aamc.length);
      //pfclass = PortfolioModel.getInstance().getDistributionList(aamc, profileData);

      for (year = 0; year < aamc.length; year++)
      {
         System.out.println(year);

         PortfolioSecurityData[] pfList = new PortfolioSecurityData[aamc.length];

         //pfclass[year] = PortfolioModel.getInstance().getDistribution(aamc[year], year, duration, invCapital, riskOffset);

         for (int i = 0; i < pfclass[year].getPortfolio().size(); i++)
         {
            pfList[year] = pfclass[year].getPortfolio().get(i);


            totalMoney += pfList[year].getMoney();
         }

         String ticker;
         String[] tickerList = new String[pfclass[year].getPortfolio().size()];
         double[] secWeight = new double[pfclass[year].getPortfolio().size()];


         for (int i = 0; i < pfclass[year].getPortfolio().size(); i++)
         {
            pfList[year] = pfclass[year].getPortfolio().get(i);

            tickerList[i] = pfList[year].getTicker();
            secWeight[i] = (pfList[year].getMoney() / totalMoney);


            if (pfList[year].getTicker().equals("BIL"))
            {
               ticker = "Cash";
            }
            else
            {
               ticker = pfList[year].getTicker();
            }

            writer.println(year +
                              "," + ticker +
                              "," + valueOf(pfList[year].getAssetclass()) +
                              "," + valueOf(pfList[year].getSubclass()) +
                              "," + valueOf(pfList[year].getWeightsAsInt()) +
                              "," + valueOf(pfList[year].getDailyprice()) +
                              "," + valueOf(pfList[year].getShares()) +
                              "," + valueOf(pfList[year].getMoney()) +
                              "," + valueOf(pfList[year].getMoney() / totalMoney) +
                              "," + valueOf(profileData.getRecurringInvestment()) +
                              "," + totalMoney +
                              "," + valueOf(pfList[year].getExpectedReturn())+
                              "," + valueOf(pfList[year].getYield())+
                              "," + valueOf(pfList[year].getSecRisk()));
         }

         //portfolioRisk = pf.getPortfolioRiskReturn(tax, aamc);
         //portfolioReturn = pf.avgYearReturns(aamc, );
         //secPortfolioRisk[year] = pf.getSecPortRisk(tickerList, secWeight);


         totalMoney = 0;
      }

      writer.close();

      createRiskReturnFile(tax, portfolioRisk, portfolioReturn, secPortfolioRisk);
   }


   public static void createRiskReturnFile(String tax, double[] portfolioRisk, double[] portfolioReturn, double[] secWgtRisk) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      if (tax.equals("No"))
      {
         fileName = "iraPortRisk.csv";
      }
      else
      {
         fileName = "taxablePortRisk.csv";
      }

      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      writer.println("Portfolio Risk" + ", Security Weighted Risk" + ", Portfolio Return");

      //File is created in TestDistribution
      for (int y = 0; y < portfolioReturn.length; y++)
      {
         writer.println(portfolioRisk[y] + "," + secWgtRisk[y] + "," + portfolioReturn[y]);
      }

      writer.close();
   }

   public static void createAssetPerformanceFile(String tax, Portfolio[] pfclass, AssetClass[] aamc) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      if (tax.equals("No"))
      {
         fileName = "iraAssetsGrowth.csv";
      }
      else
      {
         fileName = "taxedAssetsGrowth.csv";
      }
      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      DecimalFormat df = new DecimalFormat("#.##");

      int y = 0;
      for (y = 0; y < aamc.length; y++)
      {
         if (y == 0)
         {
            writer.println("Year" + "," + "AvgYearReturns" + "," + "Savings" + "," + "Domestic" + "International" + "," + "Bond" + "," + "Commodity" + "," + "Cash");
         }

         writer.println(y + "," + df.format(pfclass[y].getExpReturns()) + "," + df.format(pfclass[y].getTotalMoney()) + ","
                           + df.format(aamc[y].getAssetActualWeight("Domestic")) + ","
                           + df.format(aamc[y].getAssetActualWeight("International")) + ","
                           + df.format(aamc[y].getAssetActualWeight("Bond")) + ","
                           + df.format(aamc[y].getAssetActualWeight("Commodity")) + ","
                           + df.format(aamc[y].getAssetActualWeight("Cash")));
      }

      writer.println();
      writer.close();
   }

   public static void createFirstAssetAllocationChart(String tax, int duration, int age, String risk,
                                                      double invCapital, AssetDBCollection assetdao,
                                                      AssetClass[] aamc, String[] orderedAssets) throws Exception
   {

      //Create a file for initial asset allocation

      String fileName;
      PrintWriter writer = null;

      if (tax.equals("No"))
      {
         fileName = "iraAssets.csv";
      }
      else
      {
         fileName = "taxableAssets.csv";
      }
      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      writer.println("Profile: Age " + String.valueOf(age) + ", Risk " + risk +
                        ", Duration " + String.valueOf(duration) + ", Investment Capital " + String.valueOf(invCapital) + ", Tax rate 30% " + tax);
      writer.println("Assets, Weights");

      for (int i = 0; i < (orderedAssets.length); i++)
      {
         String assetName = orderedAssets[i];
         writer.println(assetName + ", " + aamc[0].getAssetActualWeight(assetName));
      }

      writer.println();
      writer.close();

   }

   public PrintWriter getFileHandle(String tax, String fileName)
   {
      File file;
      PrintWriter writer = null;

      try
      {

         if (tax.equals("No"))
         {
            file = new File("C:/Users/Jigar/Work Related/RiverFrontAdvisors/Clients/" + fileName);
         }
         else
         {
            file = new File("C:/Users/Jigar/Work Related/RiverFrontAdvisors/Clients/" + fileName);
         }

         //file = new RandomAccessFile ("filename.ext","rw");
         file.getParentFile().mkdirs();
         writer = new PrintWriter(file);
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return writer;
   }

}
