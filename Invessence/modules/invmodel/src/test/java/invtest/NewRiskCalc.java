package invtest;

import java.util.Map;

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
   static UserRisk userRisk = new UserRisk();
   static AdvisorRiskMaster advisorRiskMaster;

   public static void main(String[] args) throws Exception
   {
      testRisk(args);
   }

   public static void initAdvisorMaster(String advisor) {
      advisorRiskMaster = riskfetchDAO.fetchRiskMaster(advisor);
   }

   public static void initUserRiskDefault(String advisor) {
      if (advisorRiskMaster != null) {
         Map<String, AdvisorRiskMasterData> riskMasterMap = advisorRiskMaster.getAdvisorMasterdata(advisor);
         if (riskMasterMap != null) {
            for (AdvisorRiskMasterData data: riskMasterMap.values()) {
               userRisk.setAnswer(data.getKey(), data.getDefaultStrValue(),data.getDataType());
            }
         }
         Map<Integer, AdvisorRiskMapping> riskMappingMap = advisorRiskMaster.getAdvisorMappings(advisor);
         if (riskMappingMap != null) {
            for (AdvisorRiskMapping data: riskMappingMap.values()) {
               userRisk.setAnswer("RISK"+data.getRiskQuestion().toString(), "0","I",data.getDefaultWeight());
            }
         }
      }
   }

   public static void collectUserData() {
      riskfetchDAO.fetchRiskData(userRisk);
      riskfetchDAO.fetchRiskScores(userRisk);
   }


   public static void testRisk(String[] args) throws Exception
   {

      String advisor = "UOB";
      Long acctnum = null;

      initAdvisorMaster(advisor);

      // Use existing account to collect data
      if (acctnum != null )
      {
         userRisk.setAcctnum(null);
         collectUserData();
      }
      else {
         initUserRiskDefault(advisor);
      }

      // modelUtil.refreshData();
/*
      ProfileData profileData = new ProfileData();
      profileData.setAdvisor("UOB");
      profileData.setTheme("");
      profileData.setAge(45);
      profileData.setHorizon(1);
      profileData.setTradeCurrency("SGD");
      profileData.setExchangeRate(1.0);
      profileData.setInitialInvestment(100000);
      profileData.setSettleCurrency("SGD");
      profileData.setRiskCalcMethod("C"); //Using age based option A or C
      profileData.setNumOfAllocation(1);
      AssetClass[] aamc = modelUtil.buildAllocation(profileData);
      profileData.setAssetData(aamc);
      profileData.setNumOfPortfolio(1);
      Portfolio[] pfclass = modelUtil.buildPortfolio(aamc, profileData);
*/
   }


   public void overrideRisk(UserRisk userRisk) {
      userRisk.setAnswer(RiskConst.ADVISOR, "UOB", "T");
      userRisk.setAnswer(RiskConst.THEME, "0.SGWealthSGD", "T");
      userRisk.setAnswer(RiskConst.GOAL,"Retirement","T");
      userRisk.setAnswer(RiskConst.AGE,"45","I");
      userRisk.setAnswer(RiskConst.HORIZON,"1","I");
      userRisk.setAnswer(RiskConst.TRADECURRENCY,"SGD","T");
      userRisk.setAnswer(RiskConst.SETTLECURRENCY,"SGD","T");
      userRisk.setAnswer(RiskConst.INITIALINVESTMENT,"100000","D");
      userRisk.setAnswer(RiskConst.RECURRINGINVESTMENT,"5000","D");
      userRisk.setAnswer(RiskConst.RECURRINGTERM,"YEARLY","T");
      userRisk.setAnswer(RiskConst.RECURRINGPERIOD,"1","I");
      userRisk.setAnswer(RiskConst.WITHDRAWALPERIOD,"10","I");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KEEPLIQUID,"0","D");
      userRisk.setAnswer(RiskConst.KNOCKOUT,"FALSE","I");
      userRisk.setAnswer(RiskConst.TAXABLE,"FALSE","B");
      userRisk.setAnswer(RiskConst.TAXRATE,"0.20","D");
      userRisk.setAnswer(RiskConst.EXPERIENCE,"1","I");

      userRisk.setAnswer(RiskConst.RISKQUESTIONS,"7","I");
      userRisk.setAnswer(RiskConst.CALCMETHOD,RiskConst.CALCMETHOD_AGETIME,"T");
      userRisk.setAnswer(RiskConst.CALFORMULA,RiskConst.CALCFORMALA_C,"C");

   }


}
