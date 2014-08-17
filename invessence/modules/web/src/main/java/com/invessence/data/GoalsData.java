package com.invessence.data;

import java.io.Serializable;
import java.net.URLEncoder;

import com.invmodel.asset.data.AssetClass;
import com.invmodel.performance.Performance;
import com.invmodel.portfolio.data.Portfolio;

public class GoalsData implements Serializable
{

   private static final long serialVersionUID = -8865736220436234701L;

   private String name;
   private Integer age;
   private Integer horizon;
   private String risk;
   private Double initialInvestment;
   private Double recurringInvestment;
   private Double riskOffset;

   private AssetClass assetData[];
   private Portfolio[] portfolioData;
   Double[][] yearlyGrowthData;

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public String getRisk()
   {
      return risk;
   }

   public void setRisk(String risk)
   {
      this.risk = risk;
   }

   public Double getInitialInvestment()
   {
      return initialInvestment;
   }

   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment.doubleValue();
   }

   public Double getRecurringInvestment()
   {
      return recurringInvestment;
   }

   public void setRecurringInvestment(Integer recurringInvestment)
   {
      this.recurringInvestment = recurringInvestment.doubleValue();
   }

   public Double getRiskOffset()
   {
      return riskOffset;
   }

   public void setRiskOffset(Integer riskOffset)
   {
      this.riskOffset = riskOffset.doubleValue();
   }

   public AssetClass[] getAssetData()
   {
      return assetData;
   }

   public void setAssetData(AssetClass[] assetData)
   {
      this.assetData = assetData;
   }

   public Portfolio[] getPortfolioData()
   {
      return portfolioData;
   }

   public void setPortfolioData(Portfolio[] portfolioData)
   {
      this.portfolioData = portfolioData;
   }

   public Double[][] getYearlyGrowthData()
   {
      return yearlyGrowthData;
   }

   public void setYearlyGrowthData(Double[][] yearlyGrowthData)
   {
      this.yearlyGrowthData = yearlyGrowthData;
   }
}
