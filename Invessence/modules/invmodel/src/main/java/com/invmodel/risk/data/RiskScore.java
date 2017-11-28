package com.invmodel.risk.data;

import java.util.*;

/**
 * Created by prashant on 11/12/2017.
 */
public class RiskScore
{
   Integer year;
   String calcFormula;
   Boolean allCashFlag;
   Double score;
   Double standardScore;
   Double assetScore;
   Double portfolioScore;
   Double adjustment;

   public RiskScore()
   {
   }

   public RiskScore(Integer year, String calcFormula, Boolean allCashFlag,
                    Double score, Double standardScore, Double assetScore, Double portfolioScore, Double adjustment)
   {
      this.year = year;
      this.calcFormula = calcFormula;
      this.allCashFlag = allCashFlag;
      this.score = score;
      this.standardScore = standardScore;
      this.assetScore = assetScore;
      this.portfolioScore = portfolioScore;
      this.adjustment = adjustment;
   }

   public Integer getYear()
   {
      return year;
   }

   public void setYear(Integer year)
   {
      this.year = year;
   }

   public String getCalcFormula()
   {
      return calcFormula;
   }

   public void setCalcFormula(String calcFormula)
   {
      this.calcFormula = calcFormula;
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
