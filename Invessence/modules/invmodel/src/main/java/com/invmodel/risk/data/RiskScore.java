package com.invmodel.risk.data;

import java.util.*;

/**
 * Created by prashant on 11/12/2017.
 */
public class RiskScore
{
   String keydate;
   Boolean allCashFlag;
   Double score;
   Double standardScore;
   Double assetScore;
   Double portfolioScore;
   Double adjustment;

   public RiskScore()
   {
   }

   public RiskScore(String keydate, Boolean allCashFlag,
                    Double score, Double standardScore, Double assetScore, Double portfolioScore, Double adjustment)
   {
      this.keydate = keydate;
      this.allCashFlag = allCashFlag;
      this.score = score;
      this.standardScore = standardScore;
      this.assetScore = assetScore;
      this.portfolioScore = portfolioScore;
      this.adjustment = adjustment;
   }

   public String getYear()
   {
      return keydate;
   }

   public void setYear(String year)
   {
      this.keydate = year;
   }

   public Boolean getAllCashFlag()
   {
      return allCashFlag;
   }

   public void setAllCashFlag(Boolean allCashFlag)
   {
      this.allCashFlag = allCashFlag;
   }

   public Double getScore()
   {
      return score;
   }

   public void setScore(Double score)
   {
      this.score = score;
   }

   public Double getStandardScore()
   {
      return standardScore;
   }

   public void setStandardScore(Double standardScore)
   {
      this.standardScore = standardScore;
   }

   public Double getAssetScore()
   {
      return assetScore;
   }

   public void setAssetScore(Double assetScore)
   {
      this.assetScore = assetScore;
   }

   public Double getPortfolioScore()
   {
      return portfolioScore;
   }

   public void setPortfolioScore(Double portfolioScore)
   {
      this.portfolioScore = portfolioScore;
   }

   public Double getAdjustment()
   {
      return adjustment;
   }

   public void setAdjustment(Double adjustment)
   {
      this.adjustment = adjustment;
   }
}
