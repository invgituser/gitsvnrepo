package invtest;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.*;
import com.invmodel.asset.data.*;
import com.invmodel.dao.invdb.*;
import com.invmodel.inputData.*;
import com.invmodel.model.ModelUtil;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.performance.*;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.*;
import com.invmodel.portfolio.data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.String.valueOf;

/**
 * Hello world!
 */
public class TestDistribution
{
   private static TestDistribution instance = null;
   private static String datadir = "C:/Users/Jigar/Work Related/RiverFrontAdvisors/";
   private static RiskCalculator riskCalculator = new RiskCalculator();

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


   public static Double calcRisk(int age, int horizon,
                                      String ans1,
                                      String ans2, String ans3,String ans4,String ans5,String ans6,String ans7,String ans8,String ans9)
   {
      riskCalculator.setRiskFormula("C");
      riskCalculator.setNumberofQuestions(9);
      riskCalculator.setRiskAge(age);
      riskCalculator.setRiskHorizon(horizon);
      riskCalculator.setAns3(ans1);
      riskCalculator.setAns4(ans2);
      riskCalculator.setAns5(ans3);
      riskCalculator.setAns6(ans4);
      riskCalculator.setAns7(ans5);
      riskCalculator.setAns8(ans6);
      riskCalculator.setAns9(ans7);
      riskCalculator.setAns10(ans8);
      riskCalculator.setAns11(ans9);
      return riskCalculator.calculateRisk();
   }
   public static void testPerformanceModel(String[] args) throws Exception
   {
      //AssetDBCollection assetdao = AssetDBCollection.getInstance();

      // doMatrixMultiplication();

      ModelUtil modelUtil = ModelUtil.getInstance();
      modelUtil.refreshData();

      int age, duration;
      String risk, tax;

      Integer invCapital, riskOffset;
      Integer yearlyInvestments;
      Integer stayInvested;
      String name = "";
      ProfileData profileData = new ProfileData();

      tax = "No";

      profileData.setName("Retirement");
      //profileData.setAdvisor("PrimeAsset");
      //profileData.setTheme("0.Income");
      profileData.setAdvisor("UOB");
      profileData.setTheme("0.SGWealth");
      profileData.setAccountTaxable(false);

      profileData.setAge(45);
      age = profileData.getAge();

      profileData.setHorizon(1);
      duration = profileData.getHorizon();

      // profileData.setAccountTaxable(false);

/*
      //1 = preservation 2 = Accumulation
      profileData.setObjective(2);
      //2 = Go to Cash, 1 = Stay Invested
      profileData.setStayInvested(1);
*/

      profileData.setInitialInvestment(100000);
      invCapital = profileData.getInitialInvestment();
      // profileData.setRecurringInvestment(5000);

/*
      profileData.setDependent(0);
      profileData.setTotalIncome(120000);
      profileData.setLiquidAsset(5000000);
      profileData.setTotalExpense(0);
      profileData.setNumOfAllocation(duration);
*/

      profileData.setRiskCalcMethod("C"); //Using age based option A or C

      profileData.setAllocationIndex(50);  // When flag is A
      profileData.setPortfolioIndex(45);

      //profileData.offsetRiskIndex();

      profileData.setNumOfAllocation(1);
      // Use this for calculating risk based on questions
      profileData.setRiskIndex(calcRisk(age, duration, "1", "1", "1", "1", "1", "1", "1", "1", "1"));
      // Use this for calculating slider
      //profileData.setRiskIndex(39.0);

      AssetClass[] aamc = modelUtil.buildAllocation(profileData);
      profileData.setAssetData(aamc);

      profileData.setNumOfPortfolio(1);

      calculateRiskIndex(profileData);

      Portfolio[] pfclass = modelUtil.buildPortfolio(aamc, profileData);

      tax = "No";
      //createAssetPerformanceFile(tax, pfclass, aamc, age);

      createHoldingsFile(pfclass, tax, aamc, profileData);

      ArrayList<ProjectionData[]> prjctdata = null;
      if (profileData.getFixedModel()) {
         prjctdata = modelUtil.buildProjectionData(profileData);

      }
      else {
         prjctdata = modelUtil.buildProjectionData(profileData);

         ProjectionData[] perfData = prjctdata.get(0);
         // calcGrowthInfo(perfData, perfData.length, profileData);
         // Create a assetPerformanceFile
         //createPerformanceDataFile(perfData, profileData.getGoalData());
      }
      //writeForwardPerformanceFile("FowardPerformance",prjctdata);

   }

   public static void calculateRiskIndex(ProfileData profileData)
   {

      // Adjust for risk questionnaire
      //{0, 4, 8, 12, 50, 0, 0, 0, 0, 0}, // Q3    Add
      //{0, 16, 0, 0, 0, 0, 0, 0, 0, 0}, //  Q4      Add
      //{0, 4, 8, 12, 50, 0, 0, 0, 0, 0}, // Q5      Add
      //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q6   Compare
      //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q7   Compare
      //{0, 50, 75, 100, 100, 0, 0, 0, 0, 0}, //Q8   Compare
      //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q9   Compare
      //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, //
      //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, //


      Double q3=new Double(0); Double q3_wgt = new Double(0.0);
      Double q4 =new Double(1); Double q4_wgt = new Double(0.0);
      Double q5=new Double(2); Double q5_wgt = new Double(0.0);

      Double q6=new Double(4); Double q6_wgt = new Double(0.0);
      Double q7=new Double(4); Double q7_wgt = new Double(0.0);
      Double q8=new Double(4); Double q8_wgt = new Double(0.0);
      Double q9=new Double(4); Double q9_wgt = new Double(0.0);


      int offset;
      Double calcRisk = 0.0;
      Integer maxScore = 100;
      Double agePowerValue = 1.7;
      Double ageWeight = 1.0;
      Double value = 0.0;

      // Calculate age based risk score
         Integer ageValue = (profileData.getAge() == null) ? 30 :  profileData.getAge() ;
         calcRisk = Math.pow((ageValue.doubleValue() / maxScore), agePowerValue);
         calcRisk = Math.min(maxScore * calcRisk, ageWeight * maxScore);
         calcRisk = calcRisk; // Divide Age Risk / 100
         calcRisk = (calcRisk > 100) ? 100 : calcRisk;
         //calcRisk = (1 - calcRisk < 0.0) ? 0.0 : 1 - calcRisk;


         //if (calcRisk > 100.0)
         //   calcRisk = 100.0;

      // Adjust the risk score based on duration

      Double calcHorizonRisk = 0.0;
      Double maxDuration = 15.0; // This could be a constant
      double horPowerAdj = profileData.getHorizon()/maxDuration;
      if (horPowerAdj > 1.0)
         horPowerAdj = 1.0;
      agePowerValue = agePowerValue*horPowerAdj;

      calcRisk = Math.pow((ageValue.doubleValue() / maxScore), agePowerValue);
      calcRisk = Math.min(maxScore * calcRisk, ageWeight * maxScore);
      calcRisk = calcRisk; // Divide Age Risk / 100
      calcRisk = (calcRisk > 100) ? 100 : calcRisk;

      //calcHorizonRisk = (maxDuration-profileData.getHorizon()*(80/maxDuration)); // 80 is fixed since we are scaling risk 1 to 100

      if (calcHorizonRisk > calcRisk)
         calcRisk = calcHorizonRisk;



      //Define operation
      // Question 3 ** Add **
      calcRisk = calcRisk + q3_wgt * q3;

      // Question 4 ** Add **
      calcRisk = calcRisk + q4_wgt * q4;

      // Question 5 ** Add
      calcRisk = calcRisk + q5_wgt * q5;


      // Question 6,7,8,9 ** compare **
      if (q6_wgt * q6 > calcRisk)
         calcRisk = q6_wgt * q6;

      if (q7_wgt * q7 > calcRisk)
         calcRisk = q7_wgt * q7;

      if (q8_wgt * q8 > calcRisk)
         calcRisk = q8_wgt * q8;

      if (q9_wgt * q9 > calcRisk)
         calcRisk = q9_wgt * q9;

      // Store the score in an index of 1 to 100
      // Save for Asset Allocation riskIndex
      // Adjust for portfolio allocation for additional risk
      // Save Portfolio Allocation riskIndex
      // Save rest of the profileData


      profileData.setRiskIndex(101.0 - calcRisk);
      profileData.setAllocationIndex(101 - calcRisk.intValue());  // When flag is A
      profileData.setPortfolioIndex(101 - calcRisk.intValue());

   }

   public static void createRandomNumbers()
   {

      Double mean=new Double(0.07);
      Double stDev=new Double(0.08);
      Double randNorDist=new Double(0.0);

      Random rand = new Random();
      // return


      String fileName;
      PrintWriter writer = null;
      fileName = "normalDistribution" + ".csv";

      writer = TestDistribution.getInstance().getFileHandle("No", fileName);
      writer.println("Count" + "," + "Number" );


      for (int i = 0; i < 10000; i++)
      {
         //System.out.print(i);
         //System.out.println(randNorDist);

         randNorDist= stDev * rand.nextGaussian() + mean;

         if (randNorDist <= (3.0*stDev+mean) && randNorDist >= (-3.0*stDev+mean))
            writer.println(i + "," + randNorDist );
      }

      writer.close();
   }

   public static void doMatrixMultiplication()
   {
      for (int j = 0; j < 100000; j++)
      {
         for (int i = 0; i < 100000; i++)
         {

          int W = i*j;

         }
      System.out.println(j);
      }


   }
   /*public static void createWeightFile(PortfolioOptimizer assetDao){

      String fileName;
      PrintWriter writer = null;

      fileName = "PortfolioWeights" + ".csv";
      writer = TestDistribution.getInstance().getFileHandle("No", fileName);

      int count = 0;
      String []tickers = secDao.getAssetOrderedAssetTickers(null, theme, null, null);
      String [] index = assetDao.getAdvisorAssetList(groupName);

      writer.print("count");


      for(int i = 0; i < index.length; i++) {
         writer.print("," + index[i]);
      }
      for(int i = 0; i < tickers.length; i++) {
         writer.print("," + tickers[i]);
      }

      writer.print("," + "portfolio Returns" + "," + "Portfolio Risk");
      writer.println();

      double[][] weights = secDao.getThemeWeights(groupName,theme);
      double[] portRisk = secDao.getThemeRisk(groupName,theme);
      double[] portReturns = secDao.getThemeReturns(groupName, theme);
      double[][] assetWeight = assetDao.getAssetOrderedWeightArray(groupName);

      for(count = 0; count< portReturns.length; count++)
      {
         writer.print(count);
         for(int i = 0; i < assetWeight.length; i++)
         {
            writer.print("," + assetWeight[i][count]);
         }

         for(int i = 0; i < tickers.length; i++)
         {
            writer.print("," + weights[count][i]);
         }

         writer.print("," + portReturns[count] + "," + portRisk[count] );

         writer.println();
      }

      writer.close();
   }*/

   public static void testRiskCalc()
   {
      ArrayList<String> result =new ArrayList<String>();
      result.add("Age" + "," +
                    "Horizon" + "," +
                    "ans3" + "," +
                    "ans4" + "," +
                    "ans5" + "," +
                    "ans6" + "," +
                    "ans7" + "," +
                    "ans8" + "," +
                    "ans9" + "," +
                    "value1" + "," +
                    "value2" + "," +
                    "value3" + "," +
                    "value4" + "," +
                    "value5" + "," +
                    "value6" + "," +
                    "value7" + "," +
                    "value8" + "," +
                    "value9" + "," +
                    "Total"
      );
      riskCalculator = new RiskCalculator();
      try {
         riskCalculator.setRiskFormula("C");
         riskCalculator.setNumberofQuestions(9);
         for (Integer age = 20; age < 101; age+=10) {
            riskCalculator.setRiskAge(age);
            for (Integer horizon=5; horizon < 21; horizon+=5) {
               riskCalculator.setRiskHorizon(horizon);
               for (Integer r3=0; r3 < 6; r3+=2) {
                  riskCalculator.setAns3(r3.toString());
                  for (Integer r4=0; r4 < 2; r4++) {  // Only two choices
                     riskCalculator.setAns4(r4.toString());
                     for (Integer r5=0; r5 < 6; r5+=2) {  // Only five choices
                        riskCalculator.setAns5(r5.toString());
                        for (Integer r6=0; r6 < 6; r6+=2) {  // Only five choices
                           riskCalculator.setAns6(r6.toString());
                           for (Integer r7=0; r7 < 6; r7+=2) {  // Only five choices
                              riskCalculator.setAns7(r7.toString());
                              for (Integer r8=0; r8 < 6; r8+=2) {  // Only five choices
                                 riskCalculator.setAns8(r8.toString());
                                 for (Integer r9=0; r9 < 6; r9+=2) {  // Only five choices
                                    riskCalculator.setAns9(r9.toString());
                                    riskCalculator.calculateRisk();
                                    result.add(riskCalculator.getAns1() + "," +
                                                  riskCalculator.getAns2() + "," +
                                                  riskCalculator.getAns3() + "," +
                                                  riskCalculator.getAns4() + "," +
                                                  riskCalculator.getAns5() + "," +
                                                  riskCalculator.getAns6() + "," +
                                                  riskCalculator.getAns7() + "," +
                                                  riskCalculator.getAns8() + "," +
                                                  riskCalculator.getAns9() + "," +
                                                  riskCalculator.getRiskValue(1) + "," +
                                                  riskCalculator.getRiskValue(2) + "," +
                                                  riskCalculator.getRiskValue(3) + "," +
                                                  riskCalculator.getRiskValue(4) + "," +
                                                  riskCalculator.getRiskValue(5) + "," +
                                                  riskCalculator.getRiskValue(6) + "," +
                                                  riskCalculator.getRiskValue(7) + "," +
                                                  riskCalculator.getRiskValue(8) + "," +
                                                  riskCalculator.getRiskValue(9) + "," +
                                                  riskCalculator.getTotalRisk());
                                 }
                              }
                           }
                        }
                     }
                  }
               }

            }
         }
         //createRiskCalcFile(result);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   public static void writeForwardPerformanceFile(String filename, ArrayList<ProjectionData[]> projarray) {

      PrintWriter writer = null;
      if (filename == null)
         return;

      if (projarray == null)
         return;

      // Print Header Info

      for (Integer i = 0; i < projarray.size(); i++) {
         // Print Header Info
         writer = TestDistribution.getInstance().getFileHandle("No", filename + i.toString() + ".csv");
         writer.println("Year" +
                           "," + "Theme" +
                           "," + "Return" +
                           "," + "Risk" +
                           "," + "Investment" +
                           "," + "Recurring" +
                           "," + "Capital" +
                           "," + "Upper1" +
                           "," + "Lower1" +
                           "," + "Upper2" +
                           "," + "Lower2");
         for (Integer j=0; j < projarray.get(i).length ; j++) {
            ProjectionData projdata = projarray.get(i)[j];
            writer.println(projdata.getYear().toString() +
                              "," + projdata.getTheme() +
                              "," + projdata.getInvestmentReturns() +
                              "," + projdata.getInvestmentRisk() +
                              "," + projdata.getInvestedCapital() +
                              "," + projdata.getRecurInvestments() +
                              "," + projdata.getTotalCapitalWithGains() +
                              "," + projdata.getUpperBand1() +
                              "," + projdata.getLowerBand1() +
                              "," + projdata.getUpperBand2() +
                              "," + projdata.getLowerBand2());

         }
         // Print Footer Info
         writer.println("Footer" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "" +
                           "," + "");
         writer.close();



      }
   }

   public static void createHoldingsFile(Portfolio[] pfclass, String tax, AssetClass[] aamc, ProfileData profileData) throws Exception
   {

      Double mean=new Double(0.0);
      Double stDev=new Double(0.0);
      Double randNorDist=new Double(0.0);

      Random rand = new Random();
      // return
      randNorDist= stDev * rand.nextGaussian() + mean;

      String fileName;
      PrintWriter writer = null;

      double totalMoney = 0.0;

      double[] portfolioRisk = new double[aamc.length];
      double[] portfolioReturn = new double[aamc.length];
      double[] secPortfolioRisk = new double[aamc.length];

      if (tax.equals("No"))
      {
         fileName = "dev_iraPortfolioHoldings";

         fileName = fileName + profileData.getAge() + ".csv";
      }
      else
      {
         fileName = "taxablePortfolioHoldings.csv";
      }

      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      writer.println("Year" +
                        "," + "Ticker" +
                        "," + "Asset Class" +
                        "," + "Sub Asset" +
                        "," + "Weight per Asset" +
                        "," + "Price" +
                        "," + "Shares" +
                        "," + "Investment Value" +
                        "," + "Weight per portfolio" +
                        "," + "New Investments" +
                        "," + "Total Assets" +
                        "," + "Expected Return" +
                        "," + "Yield" +
                        "," + "Sec Risk");

      //for(int year = aamc.length-1; year >=0; year--)  {

      int year = 0;
      profileData.setNumOfPortfolio(aamc.length);
      //pfclass = PortfolioModel.getInstance().getDistributionList(aamc, profileData);

      for (year = 0; year < pfclass.length; year++)
      {


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

         System.out.print(year);
         System.out.println("-----------");
         for (int i = 0; i < pfclass[year].getPortfolio().size(); i++)
         {
            pfList[year] = pfclass[year].getPortfolio().get(i);

            tickerList[i] = pfList[year].getTicker();
            secWeight[i] = (pfList[year].getMoney() / totalMoney);

            ticker = pfList[year].getTicker();

            //System.out.println(pfList[year].getWeight());

            writer.println(year +
                              "," + ticker +
                              "," + valueOf(pfList[year].getAssetclass()) +
                              "," + valueOf(pfList[year].getSubclass()) +
                              "," + valueOf(pfList[year].getWeight()) +
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

      //createRiskReturnFile(tax, portfolioRisk, portfolioReturn, secPortfolioRisk);
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

   public static void createAssetPerformanceFile(String tax, Portfolio[] pfclass, AssetClass[] aamc, int age) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      if (tax.equals("No"))
      {
         fileName = "dev_iraAssetsGrowth";
         fileName = fileName + age + ".csv";
      }
      else
      {
         fileName = "taxedAssetsGrowth.csv";
      }
      writer = TestDistribution.getInstance().getFileHandle(tax, fileName);

      DecimalFormat df = new DecimalFormat("#.###");

      int y = 0;
      for (y = 0; y < pfclass.length; y++)
      {
         if (y == 0)
         {
            writer.println("Year" + "," + "AvgYearReturns" + "," + "AvgYearRisk" + "," + "Savings" + "," +
                              "Domestic" +","+ "International" + "," + "Bond" + "," + "Commodity" + "," +
                              "Cash" + "," + "2Sigma Error" + "," + "Upper" + "," + "Lower");
         }

         double sigmaError = (pfclass[y].getTotalRisk()*2*pfclass[y].getTotalMoney());

         writer.println(y + "," + df.format(pfclass[y].getExpReturns()) + ","
                           + df.format(pfclass[y].getTotalRisk()) + ","
                           + df.format(pfclass[y].getTotalMoney()) + ","
                           + df.format(aamc[y].getAsset("Domestic").getUserweight()) + ","
                           + df.format(aamc[y].getAsset("International").getUserweight()) + ","
                           + df.format(aamc[y].getAsset("Bond").getUserweight()) + ","
                           + df.format(aamc[y].getAsset("Commodity").getUserweight()) + ","
                           + df.format(aamc[y].getAsset("Cash").getUserweight()) + ","
                           + sigmaError + ","
                           + df.format(pfclass[y].getUpperTotalMoney()) + ","
                           + df.format(pfclass[y].getLowerTotalMoney()));

      }

      writer.println();
      writer.close();
   }

   public static void createPerformanceDataFile(ProjectionData[] pdata, GoalsData goalsData) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      fileName = "performanceData.csv";
      writer = TestDistribution.getInstance().getFileHandle("No", fileName);

      DecimalFormat df = new DecimalFormat("#.###");

      int y = 0;
      for (y = 0; y < pdata.length; y++)
      {
         if (y == 0)
         {
            writer.println("Year" + "," +
                            "investmentRisk" + "," +
                              "investmentReturns" + "," +
                              "totalCost" + "," +
                              "totalCapitalWithGains" +","+
                              "investedCapital" + "," +
                              "recurInvestments" + "," +
                              "investmentYield" + "," +
                              "totalGains" + "," +
                              "upperBand1" + "," +
                              "upperBand2" + "," +
                              "lowerBand1" + "," +
                              "lowerBand2" + "," +
                              "goalsrequired" + "," +
                              "NewRecurring"
            );
         }

         writer.println(y + "," +
                           df.format(pdata[y].getInvestmentRisk()) + "," +
                           df.format(pdata[y].getInvestmentReturns()) + "," +
                           df.format(pdata[y].getTotalCost()) + "," +
                           df.format(pdata[y].getTotalCapitalWithGains()) + "," +
                           df.format(pdata[y].getInvestedCapital()) + "," +
                           df.format(pdata[y].getRecurInvestments()) + "," +
                           df.format(pdata[y].getInvestmentYield()) + "," +
                           df.format(pdata[y].getTotalGains()) + "," +
                           df.format(pdata[y].getUpperBand1()) + "," +
                           df.format(pdata[y].getUpperBand2()) + "," +
                           df.format(pdata[y].getLowerBand1()) + "," +
                           df.format(pdata[y].getLowerBand2()) + "," +
                           df.format(goalsData.getGoalDesired()) + "," +
                           df.format(goalsData.getCalcRecurringAmount())
                        );

      }

      writer.println();
      writer.close();
   }

   public static void createFirstAssetAllocationChart(String tax, int duration, int age, String risk,
                                                      double invCapital,
                                                      AssetClass[] aamc, PortfolioOptimizer portfolioOptimizers) throws Exception
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

      for (String assetName : portfolioOptimizers.getAdvisorOrdertedAssetList("0.Core"))
      {
         writer.println(assetName + ", " + aamc[0].getAsset(assetName).getUserweight());
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
            file = new File(datadir + "Testing/" + fileName);
         }
         else
         {
            file = new File(datadir + fileName);
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
