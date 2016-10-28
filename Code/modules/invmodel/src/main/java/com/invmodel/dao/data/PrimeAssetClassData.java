package com.invmodel.dao.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/24/15
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimeAssetClassData
{
   private String theme;
   private String primeAssetName;
   private String ticker = "";
   private String assetclass = "";
   private String subassetclass = "";
   private String type = "";
   private String style;
   private double expenseRatio = 0.0;
   private double adv3Month = 0.0;
   private double aum = 0.0;
   private double beta = 0.0;
   private double riskSTD = 0.0;
   private double expectedReturn = 0.0;
   private double ubConstraint = 0.0;
   private double lbConstraint = 0.0;
   private double yield = 0.0;
   private int sortorder = 0;
   private double weight;
   private double rbsaweight;

   public PrimeAssetClassData(String theme, String primeAssetName, String ticker, String assetclass, String subassetclass, String type, String style, double expenseRatio, double adv3Month, double aum, double beta, double riskSTD, double expectedReturn, double ubConstraint, double lbConstraint, double yield, int sortorder)
   {
      this.theme = theme;
      this.primeAssetName = primeAssetName;
      this.ticker = ticker;
      this.assetclass = assetclass;
      this.subassetclass = subassetclass;
      this.type = type;
      this.style = style;
      this.expenseRatio = expenseRatio;
      this.adv3Month = adv3Month;
      this.aum = aum;
      this.beta = beta;
      this.riskSTD = riskSTD;
      this.expectedReturn = expectedReturn;
      this.ubConstraint = ubConstraint;
      this.lbConstraint = lbConstraint;
      this.yield = yield;
      this.sortorder = sortorder;
   }

   public PrimeAssetClassData(String primeAssetName)
   {
      this.primeAssetName = checkPrimeName(primeAssetName);
   }

   public PrimeAssetClassData(String theme, String primeAssetName,
                              String ticker, String assetclass,
                              double expectedReturn, double ubConstraint, double lbConstraint,
                              double yield, int sortorder, double weight)
   {
      this.theme = theme;
      this.primeAssetName = primeAssetName;
      this.ticker = ticker;
      this.assetclass = assetclass;
      this.expectedReturn = expectedReturn;
      this.ubConstraint = ubConstraint;
      this.lbConstraint = lbConstraint;
      this.yield = yield;
      this.sortorder = sortorder;
      this.weight = weight;
   }


   private String checkPrimeName(String primeAssetName) {
      if (primeAssetName == null || primeAssetName.length() == 0)
         primeAssetName = "UNUSED";

      return primeAssetName.toUpperCase();
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getPrimeAssetName()
   {
      return primeAssetName;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubassetclass()
   {
      return subassetclass;
   }

   public void setSubassetclass(String subassetclass)
   {
      this.subassetclass = subassetclass;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getStyle()
   {
      return style;
   }

   public void setStyle(String style)
   {
      this.style = style;
   }

   public double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public double getAdv3Month()
   {
      return adv3Month;
   }

   public void setAdv3Month(double adv3Month)
   {
      this.adv3Month = adv3Month;
   }

   public double getAum()
   {
      return aum;
   }

   public void setAum(double aum)
   {
      this.aum = aum;
   }

   public double getBeta()
   {
      return beta;
   }

   public void setBeta(double beta)
   {
      this.beta = beta;
   }

   public double getRiskSTD()
   {
      return riskSTD;
   }

   public void setRiskSTD(double riskSTD)
   {
      this.riskSTD = riskSTD;
   }

   public double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }

   public double getUbConstraint()
   {
      return ubConstraint;
   }

   public void setUbConstraint(double ubConstraint)
   {
      this.ubConstraint = ubConstraint;
   }

   public double getLbConstraint()
   {
      return lbConstraint;
   }

   public void setLbConstraint(double lbConstraint)
   {
      this.lbConstraint = lbConstraint;
   }

   public double getYield()
   {
      return yield;
   }

   public void setYield(double yield)
   {
      this.yield = yield;
   }

   public int getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(int sortorder)
   {
      this.sortorder = sortorder;
   }

   public double getWeight()
   {
      return weight;
   }

   public void setWeight(double weight)
   {
      this.weight = weight;
   }

   public double getRbsaweight()
   {
      return rbsaweight;
   }

   public void setRbsaweight(double rbsaweight)
   {
      this.rbsaweight = rbsaweight;
   }
}
