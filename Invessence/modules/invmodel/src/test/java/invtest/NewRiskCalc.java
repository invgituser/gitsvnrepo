package invtest;

import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.ModelUtil;
import com.invmodel.portfolio.data.*;
import com.invmodel.risk.dao.RiskFetchDAO;
import com.invmodel.risk.data.*;

/**
 * Created by prashant on 11/13/2017.
 */
public class NewRiskCalc
{

   static RiskFetchDAO riskfetchDAO = new RiskFetchDAO();
   static ModelUtil modelUtil = ModelUtil.getInstance();
   static UserRisk userRisk;
   static AdvisorRiskMaster advisorRiskMaster;
   static RiskCalc riskCalc;

   public static void main(String[] args) throws Exception
   {
      testRisk(args);
   }

   public static void testRisk(String[] args) throws Exception
   {

      String advisor = "UOB";
      Long acctnum = null;

      advisorRiskMaster = new AdvisorRiskMaster(advisor);

      // Use existing account to collect data
      if (acctnum != null )
      {
         userRisk = new UserRisk(advisor, acctnum);
      }
      else {
         userRisk = new UserRisk(advisorRiskMaster);
      }

      overrideRisk(userRisk);
      riskCalc = new RiskCalc(userRisk);
      modelUtil.refreshData();

      // Data needed for Profiledata
      ProfileData profileData = new ProfileData();
      profileData.setActualInvestment(userRisk.getRiskData().get(RiskConst.INITIALINVESTMENT).getAnswerDouble());
      profileData.setAdvisor(advisor);
      profileData.setTheme(userRisk.getAnswer(RiskConst.THEME));
      // ----

      calculateRisks(10);
      AssetClass[] aamc = modelUtil.buildAllocation(userRisk, profileData);
      profileData.setAssetData(aamc);
      Portfolio[] pfclass = modelUtil.buildPortfolio(aamc, userRisk, profileData);
      profileData.setPortfolioData(pfclass);
      System.out.print("Done");
   }

   public static void calculateRisks(Integer years) {
      // riskCalc.setAgeRisk(userRisk.getAge());
      // riskCalc.setHorizonRisk(userRisk.getHorizon());
      for (Integer loop = 1; loop <= userRisk.getRiskQuestion(); loop ++) {
         riskCalc.setQuestionsRisk(loop, 1, null);
      }
      riskCalc.calculate(years);
   }


   public static void overrideRisk(UserRisk userRisk) {
      // userRisk.setAnswer(RiskConst.ADVISOR, "UOB", "T");  This is already defined in default Init process
      userRisk.setAnswer(RiskConst.THEME, "0.SGWealthSGD", "T");
      userRisk.setAnswer(RiskConst.GOAL,"Retirement","T");
      userRisk.setAnswer(RiskConst.AGE,"45","I");
      userRisk.setAnswer(RiskConst.HORIZON,"20","I");
      userRisk.setAnswer(RiskConst.TRADECURRENCY,"SGD","T");
      userRisk.setAnswer(RiskConst.SETTLECURRENCY,"SGD","T");
      userRisk.setAnswer(RiskConst.INITIALINVESTMENT,"100000","D");
      userRisk.setAnswer(RiskConst.RECURRINGINVESTMENT,"5000","D");
      userRisk.setAnswer(RiskConst.RECURRINGTERM,"YEARLY","T");
      userRisk.setAnswer(RiskConst.RECURRINGPERIOD,"1","I");
      userRisk.setAnswer(RiskConst.WITHDRAWALPERIOD,"10","I");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KNOCKOUT,"FALSE","I");
      userRisk.setAnswer(RiskConst.TAXABLE,"FALSE","B");
      userRisk.setAnswer(RiskConst.TAXRATE,"0.20","D");
      userRisk.setAnswer(RiskConst.EXPERIENCE,"1","I");

      userRisk.setAnswer(RiskConst.RISKQUESTIONS,"7","I");
      userRisk.setAnswer(RiskConst.CALCMETHOD,RiskConst.CALCMETHOD_AGETIME,"T");
      userRisk.setAnswer(RiskConst.CALCFORMULA, RiskConst.CALCFORMALA_C, "C");

   }


}
