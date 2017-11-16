package com.invmodel.risk.data;

import java.util.*;

/**
 * Created by prashant on 11/9/2017.
 */
public class UserRisk
{
   String advisor;
   Long acctnum;
   Integer knockout;
   Map<String, UserRiskData> riskData;
   ArrayList<RiskScore> riskScores;

   public UserRisk()
   {
      riskData = new HashMap<String, UserRiskData>();
      riskScores = new ArrayList<RiskScore>();
      knockout = 0;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
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
       return riskScores;
   }

   public RiskScore getRiskScoreObj(Integer year)
   {
      if (riskScores != null)
      {
         if ((year > 0 && year < riskScores.size()))
         {
            return riskScores.get(year);
         }
      }
      return null;
   }


   public void setRiskScores(String datevalue, Boolean allCashFlag, Double score,
                             Double standardScore,
                             Double assetScore,
                             Double portfolioScore,
                             Double adjustment)
   {
      if (riskScores != null)
      {
         RiskScore thisScore = new RiskScore(datevalue, allCashFlag,
                                             score,
                                             standardScore,
                                             assetScore,
                                             portfolioScore,
                                             adjustment);

         riskScores.add(thisScore);
         // If any flag is set, then mark the master record as such.
         if (allCashFlag)
         {
            addKnockout();
         }
         else
         {
            removeKnockout();
         }

      }
   }

   public void setScore(Integer year, Double score)
   {
      if (year > 0 && year < riskScores.size())
      {
         RiskScore riskScore = riskScores.get(year);
         riskScore.setScore(score);
      }
   }

   public Double getScore(Integer year) {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getScore();
      }
      return 0.0;
   }

   public void setAssetScore(Integer year, Double score)
   {
      if (year > 0 && year < riskScores.size())
      {
         RiskScore riskScore = riskScores.get(year);
         riskScore.setAssetScore(score);
      }
   }

   public Double getAssetScore(Integer year) {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getAssetScore();
      }
      return 0.0;
   }

   public void setPortfolioScore(Integer year, Double score)
   {
      if (year > 0 && year < riskScores.size())
      {
         RiskScore riskScore = riskScores.get(year);
         riskScore.setPortfolioScore(score);
      }
   }

   public Double getPortfolioScore(Integer year) {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getPortfolioScore();
      }
      return 0.0;
   }

   public void setStandardScore(Integer year, Double score)
   {
      if (year > 0 && year < riskScores.size())
      {
         RiskScore riskScore = riskScores.get(year);
         riskScore.setStandardScore(score);
      }
   }

   public Double getStandardScore(Integer year) {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getStandardScore();
      }
      return 0.0;
   }

   public void setAllCashFlag(Integer year, Boolean thisflag)
   {
      if (year > 0 && year < riskScores.size())
      {
         RiskScore riskScore = riskScores.get(year);
         Boolean getOrigFlag = riskScore.getAllCashFlag();
         riskScore.setAllCashFlag(thisflag);
         if (thisflag != getOrigFlag)
         {
            if (thisflag)
            {
               addKnockout();
            }
            else
            {
               removeKnockout();
            }
         }
      }
   }

   public Boolean getAllCashFlag(Integer year) {
      if (year > 0 && year < riskScores.size())
      {
         return riskScores.get(year).getAllCashFlag();
      }
      return false;
   }

   // -------------------------------------------

   private void setRiskData(String key, String answer, String answerType, Double score)
   {
      if (riskData.containsKey(key))
      {
         riskData.get(key).setAnswer(answer, answerType);
         riskData.get(key).setRiskScore(score);
      }
      else
      {
         UserRiskData data = new UserRiskData(key, answer, answerType, score);
         riskData.put(key, data);
      }
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
}
