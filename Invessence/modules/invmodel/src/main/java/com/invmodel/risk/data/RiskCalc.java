package com.invmodel.risk.data;

import java.util.*;

import com.invmodel.risk.dao.RiskFetchDAO;

/**
 * Created by prashant on 11/14/2017.
 */
public class RiskCalc
{
   private RiskFetchDAO riskfetchDAO = new RiskFetchDAO();

   public UserRisk userRisk;

   private Calendar cal = Calendar.getInstance();

   public RiskCalc()
   {
      this.userRisk = null;
   }

   public RiskCalc(UserRisk userRisk)
   {
      this.userRisk = userRisk;
   }

   public RiskCalc(UserRisk userRisk, Long acctnum)
   {
      this.userRisk = userRisk;
      userRisk.setAcctnum(acctnum);
   }

   private String getDateValue(Integer increment)
   {
      Integer year = cal.get(cal.YEAR);
      Integer month = cal.get(cal.MONTH);
      Integer date = cal.get(cal.DATE);
      increment = ((increment == null) ? 0 : ((increment < 0) ? 0 : increment));
      year = year + increment;
      return (year.toString() + month.toString() + date.toString());
   }

   public void setAssetRisk(Integer year, Double score)
   {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setAssetScore(year, score);
      }
   }

   public void setPortfolioRisk(Integer year, Double score)
   {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setPortfolioScore(year, score);
      }
   }

   public void setStandardScore(Integer year, Double score)
   {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setStandardScore(year, score);
      }
   }

   public void setAllCashFlag(Integer year, Boolean allCashFlag)
   {
      if (userRisk != null)
      {
         year = (year == null) ? 0 : year;
         userRisk.setAllCashFlag(year, allCashFlag);
      }
   }

   public ArrayList<RiskScore> getAllScores()
   {
      if (userRisk != null)
      {
         userRisk.getALLRiskScores();
      }
      return null;
   }

   public Boolean isCalcFormula_C()
   {
      if (userRisk != null)
      {
         if (userRisk.getAnswer(RiskConst.CALCFORMULA) != null)
         {
            return userRisk.getAnswer(RiskConst.CALCFORMULA).equalsIgnoreCase(RiskConst.CALCFORMALA_C);
         }
      }
      return true;
   }

   public Boolean getKnockOutFlag()
   {
      try
      {
         return (userRisk.getKnockout() > 0) ? true : false;
      }
      catch (Exception ex)
      {
         return false;
      }

   }

   public Double getScore(Integer year)
   {
      Double score;
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
      {
         return 0.0;
      }

      if (userRisk != null)
      {
         return userRisk.getScore(year);
      }
      return 0.0;
   }

   public Double getStandardScore(Integer year)
   {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
      {
         return 0.0;
      }

      if (userRisk != null)
      {
         return userRisk.getStandardScore(year);
      }
      return 0.0;
   }

   public Double getAssetScore(Integer year)
   {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
      {
         return 0.0;
      }

      if (userRisk != null)
      {
         return userRisk.getAssetScore(year);
      }
      return 0.0;
   }

   public Double getPortfolioScore(Integer year)
   {
      year = (year == null) ? 0 : year;
      if (getKnockOutFlag())
      {
         return 0.0;
      }

      if (userRisk != null)
      {
         return userRisk.getPortfolioScore(year);
      }
      return 0.0;
   }

   public Integer getCalcMethod()
   {
      if (userRisk != null)
      {
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_STANDARD))
         {
            return 1;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_AGETIME))
         {
            return 2;
         }
         if (userRisk.getAnswer(RiskConst.CALCMETHOD).equalsIgnoreCase(RiskConst.CALCMETHOD_CUSTOM))
         {
            return 9;
         }
      }
      return 0;
   }


   public Double ageTimeFormula(Integer age, Integer horizon)
   {
      Double value;
      Double agePowerValue = 1.7;
      Double ageWeight = 1.0;
      Double maxDuration = 15.0; // This could be a constant
      Double maxScore = 100.0;
      try
      {
         String advisor = userRisk.getAdvisor();
         if (userRisk != null && userRisk.getAdvisorRiskMaster() != null)
         {
            if (userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().containsKey(RiskConst.AGEPOWERVALUE))
            {
               agePowerValue = userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().get(RiskConst.AGEPOWERVALUE).getDefaultDoubleValue();
            }
            if (userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().containsKey(RiskConst.AGEWEIGHT))
            {
               ageWeight = userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().get(RiskConst.AGEWEIGHT).getDefaultDoubleValue();
            }
            if (userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().containsKey(RiskConst.MAXDURATION))
            {
               maxDuration = userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().get(RiskConst.MAXDURATION).getDefaultDoubleValue();
            }
            if (userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().containsKey(RiskConst.MAXSCORE))
            {
               maxScore = userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().get(RiskConst.MAXSCORE).getDefaultDoubleValue();
            }
         }

         age = (age == null) ? 30 : age;
         horizon = (horizon == null) ? 1 : horizon;
         Double horPowerAdj = horizon / maxDuration;
         if (horPowerAdj > 1.0)
         {
            horPowerAdj = 1.0;
         }
         agePowerValue = agePowerValue * horPowerAdj;

         value = Math.pow((age.doubleValue() / maxScore), agePowerValue);
         value = Math.min(maxScore * value, ageWeight * maxScore);
         value = (value > maxScore) ? maxScore : value;

         //JAV 6/2/2017 when Horizon is 1 its aways cash type portfolio
         if (horizon == 1)
         {
            value = 100.0;
         }
         return value;
      }
      catch (Exception ex)
      {
         value = 0.0;
      }
      return value;
   }

   public Double getUserRiskQuestionsWeight(Integer question)
   {
      return userRisk.getRiskAnswerWeight(question);
   }

   public void setQuestionsRisk(Integer question, Integer answer, Double weight)
   {
      Integer prevanswer;
      Integer knockoutanswer;
      if (userRisk != null && userRisk.getAdvisorRiskMaster() != null && userRisk.getAdvisorRiskMaster().getAdvisorMasterdata() != null)
      {
         if (question == 0) {
            userRisk.setRiskAnswer(question, answer, weight);
         }
         else
         {
            Integer lastQuestion = userRisk.getRiskQuestion();
            if (question > 0 && question <= lastQuestion)
            {
               knockoutanswer = userRisk.getAdvisorRiskMaster().getAdvisorMappings().get(question).getKnockoutQuestion();
               prevanswer = userRisk.getRiskAnswer(question);
               weight = (weight == null) ? userRisk.getAdvisorRiskMaster().getAdvisorMappings().get(question).getWeight(answer) : weight;
               if (prevanswer != answer)
               {
                  if (prevanswer == knockoutanswer && knockoutanswer > 0)
                  {
                     userRisk.removeKnockout();

                  }
                  else
                  {
                     if (answer == knockoutanswer && knockoutanswer > 0)
                     {
                        userRisk.addKnockout();
                        weight = 0.0;
                     }
                  }
                  userRisk.setRiskAnswer(question, answer, weight);
               }
            }
         }
      }
   }

   public void setRisk0(Double score)
   {
      if (userRisk != null)
      {
         // Double score = ageTimeFormula(age, userRisk.getHorizon());
         setQuestionsRisk(0, 0, score);
      }
   }

   private void calcAgeTime(Integer year, Integer age, Integer horizon)
   {
      Double maxScore = 100.0;
      Double value = 0.0;
      try
      {

         if (userRisk == null)
         {
            return;
         }

         if (userRisk.getKnockout() > 0)
         {
            userRisk.setAllCashFlag(year, true);
         }

         if (userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().containsKey(RiskConst.MAXSCORE))
         {
            maxScore = userRisk.getAdvisorRiskMaster().getAdvisorMasterdata().get(RiskConst.MAXSCORE).getDefaultDoubleValue();
         }

         Integer numberofQuestions = userRisk.getRiskQuestion();
         Double tempWeight = 0.0;
         value = ageTimeFormula(age, horizon);
         setRisk0(value); // Save this as default value for Risk 0 as starting point.
         // The reason, we are starting with Zero, is because the Zero represents Age/Horizon Risk default.
         for (int loop = 1; loop <= numberofQuestions + 1; loop++)
         {
            tempWeight = userRisk.getRiskAnswerWeight(loop);
            if (tempWeight != null)
            {
               value = (tempWeight > value) ? tempWeight : value;
            }
         }

         value = (value < 0.0) ? 0.0 : value;
         value = (value > maxScore) ? maxScore : value;
         value = (maxScore - value);
         userRisk.setRiskScores(year, userRisk.getCalcFormula(), false,
                                value, value, value, value, 0.0
         );

      }
      catch (Exception ex)
      {
         userRisk.setRiskScores(year, userRisk.getCalcFormula(), true,
                                value, value, value, value, 0.0
         );
      }

   }

   public void calculate(Integer years)
   {
      Integer age;
      Integer horizon;
      if (isCalcFormula_C())
      {
         years = (years == null || years < 1) ? 1 : years;
         age = userRisk.getAge();
         horizon = userRisk.getHorizon();

         for (Integer thisyear = 0 ; thisyear <= years - 1 ; thisyear++)  {
            switch (getCalcMethod())
            {
               case 0:
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 8:
                  calcAgeTime(thisyear, age, horizon);
                  break;
               case 9:
               default:
                  userRisk.setRiskScores(thisyear, userRisk.getCalcFormula(), false,
                                         0.0, 0.0, 0.0, 0.0, 0.0);
                  break;
            }
            age++;
            horizon--;
         }
      }
   }

   public void calculate() {
      calculate(0);
   }
}
