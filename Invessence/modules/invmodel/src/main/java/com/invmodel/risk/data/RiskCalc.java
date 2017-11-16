package com.invmodel.risk.data;

import java.util.*;

import com.invmodel.risk.dao.RiskFetchDAO;

/**
 * Created by prashant on 11/14/2017.
 */
public class RiskCalc
{
   private RiskFetchDAO riskfetchDAO = new RiskFetchDAO();

   private String advisor;
   public UserRisk userRisk;
   private AdvisorRiskMaster advisorRiskMaster;

   private Calendar cal = Calendar.getInstance();

   public RiskCalc()
   {
      advisor = null;
      userRisk = new UserRisk();
      advisorRiskMaster = new AdvisorRiskMaster();
   }

   public RiskCalc(String advisor) {
      this.advisor = advisor;
      userRisk = new UserRisk();
      initAdvisorMaster(advisor);
      initUserRiskDefault(advisor);
   }

   public RiskCalc(String advisor, Long acctnum) {
      this.advisor = advisor;

      initAdvisorMaster(advisor);
      fetchUserRiskData(advisor, acctnum);
   }

   private String getDateValue(Integer increment) {
      Integer year = cal.get(cal.YEAR);
      Integer month = cal.get(cal.MONTH);
      Integer date = cal.get(cal.DATE);
      increment  = ((increment == null) ? 0 : ((increment < 0) ? 0 : increment));
      year = year + increment;
      return (year.toString() + month.toString() + date.toString());
   }

   private void initAdvisorMaster(String advisor) {
      advisorRiskMaster = riskfetchDAO.fetchRiskMaster(advisor);
   }

   private void initUserRiskDefault(String advisor) {
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

   public void fetchUserRiskData(String advisor, Long acctnum) {
      userRisk = new UserRisk();
      userRisk.setAdvisor(advisor);
      userRisk.setAcctnum(acctnum);
      initUserRiskDefault(advisor);
      riskfetchDAO.fetchRiskData(userRisk);
      riskfetchDAO.fetchRiskData(userRisk);
   }

   public void setRiskAnswer(Integer question, String value) {
      if (question <= 0)
         return;

      if (advisorRiskMaster == null)
         return;

      if (question > advisorRiskMaster.getAdvisorMappings(advisor).size())
         return;

      if (userRisk != null)
      {
         String key = RiskConst.RISKQUESTIONKEY + question.toString();
         userRisk.setAnswer(key, value, "I");
         if (advisorRiskMaster.getAdvisorMappings(advisor).get(question).getKnockoutQuestion() == question)
            setAllCashFlag(0, true);
      }
   }

   public void setRiskAnswer(String key, String data, String datatype) {
      if (userRisk != null)
         userRisk.setAnswer(key, data, datatype);
   }

   public void setAssetRisk(Integer year, Double score) {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setAssetScore(year,score);
      }
   }

   public void setPortfolioRisk(Integer year, Double score) {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setPortfolioScore(year,score);
      }
   }

   public void setStandardScore(Integer year, Double score) {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setStandardScore(year,score);
      }
   }

   public void setAllCashFlag(Integer year, Boolean allCashFlag){
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setAllCashFlag(year,allCashFlag);
      }
   }

  public ArrayList<RiskScore> getAllScores() {
      if (userRisk != null)
      {
         userRisk.getALLRiskScores();
      }
      return null;
   }

   public Boolean isCalcMethod_C() {
      if (userRisk != null) {
         if (userRisk.getAnswer(RiskConst.CALCMETHOD) != null) {
            return userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCFORMALA_C);
         }
      }
      return true;
   }

   public Boolean getKnockOutFlag() {
      try
      {
         return (userRisk.getKnockout() > 0) ? true : false;
      }
      catch (Exception ex){
         return false;
      }

   }

   public Double getScore(Integer year) {
      Double score;
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
         return 0.0;

      if (userRisk != null) {
         return userRisk.getScore(year);
      }
      return 0.0;
   }

   public Double getStandardScore(Integer year) {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
         return 0.0;

      if (userRisk != null) {
            return userRisk.getStandardScore(year);
      }
      return 0.0;
   }

   public Double getAssetScore(Integer year) {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
         return 0.0;

      if (userRisk != null) {
         return userRisk.getAssetScore(year);
      }
      return 0.0;
   }

   public Double getPortfolioScore(Integer year) {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
         return 0.0;

      if (userRisk != null) {
         return userRisk.getPortfolioScore(year);
      }
      return 0.0;
   }

   public Integer getCalcFormula() {
      if (userRisk != null) {
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_AGE)) {
            return 1;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_AGETIME)) {
            return 2;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_GOAL)) {
            return 3;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_AGETIMEGOAL)) {
            return 4;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_CUSTOM)) {
            return 9;
         }
      }
      return 0;
   }

   public Double calculate(Integer year) {
      try
      {
         if (isCalcMethod_C())
         {
            year = (year == null) ? 0: year;
            Integer age, horizon;
            String goal;
            Double investment, recurring;
            Integer recurringTerm;
            switch (getCalcFormula()) {
               case 0:
                  break;
               case 1:
                  break;
               case 2:
                  break;
               case 3:
                  break;
               case 4:
                  break;
               case 5:
                  break;
               case 6:
               case 7:
               case 8:
               case 9:
               default:
                  break;
            }
         }
         else
         {
            return getStandardScore(0);
         }
      }
      catch (Exception ex) {
         return 0.0;
      }
      return 0.0;
   }


}
