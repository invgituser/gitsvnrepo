package com.invmodel.risk.data;

import java.util.*;

import com.invmodel.risk.dao.RiskFetchDAO;

/**
 * Created by prashant on 11/9/2017.
 */
public class UserRisk
{
   private String advisor;
   private Long acctnum;
   private Integer knockout;
   private Map<String, UserRiskData> riskData;
   private Map<Integer, RiskScore> riskScores;
   private AdvisorRiskMaster advisorRiskMaster;

   private RiskFetchDAO riskfetchDAO;

   public UserRisk()
   {
      riskfetchDAO = new RiskFetchDAO();
      riskData = new HashMap<String, UserRiskData>();
      riskScores = new HashMap<Integer, RiskScore>();
      knockout = 0;
      advisorRiskMaster = new AdvisorRiskMaster();
   }

   public UserRisk(AdvisorRiskMaster advisorRiskMaster)
   {
      riskfetchDAO = new RiskFetchDAO();
      riskData = new HashMap<String, UserRiskData>();
      riskScores = new HashMap<Integer, RiskScore>();
      this.knockout = 0;
      this.advisorRiskMaster = advisorRiskMaster;
      this.advisor = advisorRiskMaster.getAdvisor();
      initUserRiskDefault();
   }

   public UserRisk(String advisor, Long acctnum)
   {
      riskfetchDAO = new RiskFetchDAO();
      riskData = new HashMap<String, UserRiskData>();
      riskScores = new HashMap<Integer, RiskScore>();
      this.knockout = 0;
      fetchUserRiskData(advisor, acctnum);
   }

   private void initUserRiskDefault()
   {
      if (this.advisorRiskMaster != null)
      {
         Map<String, AdvisorRiskMasterData> riskMasterMap = advisorRiskMaster.getAdvisorMasterdata();
         if (riskMasterMap != null)
         {
            for (AdvisorRiskMasterData data : riskMasterMap.values())
            {
               if (data.getSaveforUser())
               {
                  if (riskData.containsKey(data.getKey()))
                  {
                     setAnswer(data.getKey(), data.getDefaultStrValue(), data.getDataType());
                  }
                  else {
                     UserRiskData userdata = new UserRiskData(data.getSortorder(), data.getKey(), data.getDefaultStrValue(), data.getDataType(), null);
                     riskData.put(data.getKey(), userdata);
                  }
               }
            }
         }

         Map<Integer, AdvisorRiskMapping> riskMappingMap = advisorRiskMaster.getAdvisorMappings();
         if (riskMappingMap != null)
         {
            for (AdvisorRiskMapping data : riskMappingMap.values())
            {
               String key = RiskConst.RISKQUESTIONKEY + data.getRiskQuestion().toString();
               Integer sortorder = RiskConst.RISKQSORTNUM + data.getRiskQuestion();
               if (riskData.containsKey(key))
               {
                  setAnswer(key, "0", "I", data.getDefaultWeight());
               }
               else {
                  UserRiskData userdata = new UserRiskData(sortorder, key, "0", "I", null);
                  riskData.put(key, userdata);

               }
            }
         }
      }
   }

   public void fetchUserRiskData(String advisor, Long acctnum)
   {
      setAdvisor(advisor);
      setAcctnum(acctnum);
      initUserRiskDefault();
      collectUserData();
   }

   public void collectUserData() {
      riskfetchDAO.fetchRiskData(this);
      riskfetchDAO.fetchRiskScores(this);
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public AdvisorRiskMaster getAdvisorRiskMaster()
   {
      return advisorRiskMaster;
   }

   public void setAdvisorRiskMaster(AdvisorRiskMaster advisorRiskMaster)
   {
      this.advisorRiskMaster = advisorRiskMaster;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Integer getKnockout()
   {
      return knockout;
   }

   public void addKnockout()
   {
      this.knockout = (this.knockout + 1 > this.riskScores.size()) ? this.riskScores.size() : this.knockout + 1;
   }

   public void removeKnockout()
   {
      this.knockout = ((this.knockout - 1) < 0) ? 0 : this.knockout - 1;
   }

   public Double getRiskScore(Integer year)
   {
      if (knockout > 0)
      {
         if (riskScores != null)
         {
            if (year > 0 && year < riskScores.size())
            {
               return riskScores.get(year).getScore();
            }
         }
      }
      return 0.0;
   }

   public ArrayList<RiskScore> getALLRiskScores()
   {
      ArrayList<RiskScore> list = new ArrayList<RiskScore>();
      for (RiskScore score : riskScores.values())
      {
         list.add(score);
      }
      return list;
   }

   public RiskScore getRiskScoreObj(Integer year)
   {
      if (riskScores != null)
      {
         if (riskScores.containsKey(year))
         {
            return riskScores.get(year);
         }
      }
      return null;
   }


   public void setRiskScores(Integer datevalue, String calcFormula,
                             Boolean allCashFlag, Double score,
                             Double standardScore,
                             Double assetScore,
                             Double portfolioScore,
                             Double adjustment)
   {
      if (riskScores != null)
      {
         RiskScore thisScore = new RiskScore(datevalue, calcFormula,
                                             allCashFlag,
                                             score,
                                             standardScore,
                                             assetScore,
                                             portfolioScore,
                                             adjustment);

         riskScores.put(datevalue, thisScore);
      }
   }

   public void setScore(Integer year, Double score)
   {
      if (riskScores.containsKey(year))
      {
         riskScores.get(year).setScore(score);
      }
      else
      {
         setRiskScores(year, getCalcFormula(),
                       false,
                       score, score, score, score, 0.0);
      }
   }

   public Double getScore(Integer year)
   {
      if (riskScores.containsKey(year))
      {
         return riskScores.get(year).getScore();
      }
      return 0.0;
   }

   public void setAssetScore(Integer year, Double score)
   {
      if (riskScores.containsKey(year))
      {
         riskScores.get(year).setAssetScore(score);
      }
      else
      {
         setRiskScores(year, getCalcFormula(),
                       false,
                       score, score, score, score, 0.0);
      }
   }

   public Double getAssetScore(Integer year)
   {
      if (year >= 0 && year < riskScores.size())
      {
         return riskScores.get(year).getAssetScore();
      }
      return 0.0;
   }

   public void setPortfolioScore(Integer year, Double score)
   {
      if (riskScores.containsKey(year))
      {
         riskScores.get(year).setPortfolioScore(score);
      }
      else
      {
         setRiskScores(year, getCalcFormula(),
                       false,
                       score, score, score, score, 0.0);
      }
   }

   public Double getPortfolioScore(Integer year)
   {
      if (year >= 0 && year < riskScores.size())
      {
         return riskScores.get(year).getPortfolioScore();
      }
      return 0.0;
   }

   public void setStandardScore(Integer year, Double score)
   {
      if (riskScores.containsKey(year))
      {
         riskScores.get(year).setStandardScore(score);
      }
      else
      {
         setRiskScores(year, getCalcFormula(),
                       false,
                       score, score, score, score, 0.0);
      }
   }

   public Double getStandardScore(Integer year)
   {
      if (year >= 0 && year < riskScores.size())
      {
         return riskScores.get(year).getStandardScore();
      }
      return 0.0;
   }

   public void setAllCashFlag(Integer year, Boolean thisflag)
   {
      if (riskScores.containsKey(year))
      {
         riskScores.get(year).setAllCashFlag(thisflag);
      }
      else
      {
         setRiskScores(year, getCalcFormula(),
                       thisflag,
                       0.0, 0.0, 0.0, 0.0, 0.0);
      }
   }

   public Boolean getAllCashFlag(Integer year)
   {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getAllCashFlag();
      }
      return false;
   }


   // -------------------------------------------


   public Map<String, UserRiskData> getRiskData()
   {
      return riskData;
   }

   private void setRiskData(String key, String answer, String answerType, Double score)
   {
      if (riskData.containsKey(key))
      {
         riskData.get(key).setAnswer(answer, answerType);
         riskData.get(key).setRiskScore(score);
      }
      else
      {
         UserRiskData data = new UserRiskData(RiskConst.CUSTOMSORTNUM, key, answer, answerType, score);
         riskData.put(key, data);
      }
   }

   public Integer getRiskQuestion() {
      if (riskData.containsKey(RiskConst.RISKQUESTIONS)) {
         return riskData.get(RiskConst.RISKQUESTIONS).getAnswerInt();
      }
      return 0;
   }
   public String getAnswer(String key)
   {
      if (riskData.containsKey(key))
      {
         return riskData.get(key).getAnswer();
      }
      return null;
   }

   public void setAnswer(String key, String answer, String answerType)
   {
      if (riskData != null)
      {
         if (riskData.containsKey(key))
         {
            riskData.get(key).setAnswer(answer, answerType);
         }
         else
         {
            setRiskData(key, answer, answerType, null);
         }
      }
   }

   public void setAnswer(String key, String answer, String answerType, Double riskScore)
   {
      if (riskData != null)
      {
         if (riskData.containsKey(key))
         {
            riskData.get(key).setAnswer(answer, answerType);
            riskData.get(key).setRiskScore(riskScore);
         }
         else
         {
            setRiskData(key, answer, answerType, riskScore);
         }
      }
   }

   public Integer getAge()
   {
      return riskData.get(RiskConst.AGE).getAnswerInt();
   }

   public Integer getDefaultAge()
   {
      Integer value = getAge();
      return (value == null || value < 0) ? 30 : value;
   }

   public Integer getHorizon()
   {
      return riskData.get(RiskConst.HORIZON).getAnswerInt();
   }

   public Integer getDefaultHorizon()
   {
      Integer value = getHorizon();
      return (value == null || value < 0) ? 35 : value;
   }

   public Double getRecurringInvestment()
   {
      Double investment = riskData.get(RiskConst.RECURRINGINVESTMENT).getAnswerDouble();
      investment = (investment == null || investment < 0) ? 0 : investment;
      String term = riskData.get(RiskConst.RECURRINGTERM).getAnswer();
      if (term != null)
      {
         Integer multiplier = 0;

         if (term.equalsIgnoreCase(RiskConst.TERMS_MONTHLY))
         {
            multiplier = 12;
         }
         if (term.equalsIgnoreCase(RiskConst.TERMS_YEARLY))
         {
            multiplier = 1;
         }
         if (term.equalsIgnoreCase(RiskConst.TERMS_QUATERLY))
         {
            multiplier = 4;
         }
         if (term.equalsIgnoreCase(RiskConst.TERMS_WEEKLY))
         {
            multiplier = 52;
         }

         investment = investment * multiplier;
      }
      return investment;
   }

   public String getCalcFormula()
   {
      String formula = riskData.get(RiskConst.CALCFORMULA).getAnswer();
      if (formula == null)
      {
         return RiskConst.CALCFORMALA_C;
      }
      return formula;
   }

   public Integer convertCalcFormula2Int(String formula)
   {
      // String formula = riskData.get(RiskConst.CALCFORMULA).getAnswer();
      if (formula != null)
      {
         if (formula.equalsIgnoreCase(RiskConst.CALCFORMALA_C))
         {
            return 1;
         }
         if (formula.equalsIgnoreCase(RiskConst.CALCFORMALA_A))
         {
            return 2;
         }
         if (formula.equalsIgnoreCase(RiskConst.CALCFORMALA_D))
         {
            return 3;
         }
      }
      return 1;
   }

   public Double getKeepLiquidCash()
   {
      Double value = riskData.get(RiskConst.KEEPLIQUID).getAnswerDouble();
      return ((value == null || value < 0.0) ? 0.0 : value);
   }

   public Integer getRiskAnswer(Integer question)
   {
      if (riskData != null)
      {
         String key = RiskConst.RISKQUESTIONKEY + question.toString();
         if (riskData.containsKey(key))
         {
            return riskData.get(key).getAnswerInt();
         }
      }
      return 0;
   }

   public Double getRiskAnswerWeight(Integer question)
   {
      if (riskData != null)
      {
         String key = RiskConst.RISKQUESTIONKEY + question.toString();
         if (riskData.containsKey(key))
         {
            return riskData.get(key).getRiskScore();
         }
      }
      return 0.0;
   }

   public void setRiskAnswer(Integer question, Integer answer, Double weight)
   {
      if (riskData != null)
      {
         String key = RiskConst.RISKQUESTIONKEY + question.toString();
         if (riskData.containsKey(key))
         {
            setRiskData(key, answer.toString(), "I", weight);
         }
         else {
            Integer sortnum = RiskConst.RISKQSORTNUM + question;
            UserRiskData userdata = new UserRiskData(sortnum, key, answer.toString(), "I", weight );
            riskData.put(key,userdata);
         }
      }
   }

   public void setAge(Integer age)
   {
      Integer prevanswer;
      if (riskData.containsKey(RiskConst.AGE))
      {
         prevanswer = riskData.get(RiskConst.AGE).getAnswerInt();
         if (prevanswer != age)
         {
            setAnswer(RiskConst.AGE, age.toString(), "I", 0.0);
         }
      }
   }

   public void setHorizon(Integer horizon)
   {
      Integer prevanswer;
      if (riskData.containsKey(RiskConst.HORIZON))
      {
         prevanswer = riskData.get(RiskConst.HORIZON).getAnswerInt();
         if (prevanswer != horizon)
         {
            setAnswer(RiskConst.AGE, horizon.toString(), "I", 0.0);
         }
      }
   }

}
