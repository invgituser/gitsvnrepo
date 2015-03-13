package com.invessence.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 8/22/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagePortfolioData
{
   private String ticker;
   private String assetclass;
   private String subclass;
   private String name;
   private Integer sortorder;
   private Double currentPrice;
   private Integer currentShare;
   private Double currentWeight;
   private Double currentValue;
   private Double currentRisk;
   private Double currentReturn;
   private Integer expectedShare;
   private Double expectedWeight;
   private Double expectedValue;
   private Double expectedRisk;
   private Double expectedReturn;

   public ManagePortfolioData()
   {
   }

   public ManagePortfolioData(String ticker, String assetclass, String subclass, String name, Integer sortorder, Double currentPrice,
                              Double currentWeight, Integer currentShare,  Double currentValue, Double currentReturn, Double currentRisk,
                              Double expectedWeight, Integer expectedShare, Double expectedValue, Double expectedReturn, Double expectedRisk)
   {
      this.ticker = ticker;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.name = name;
      this.sortorder = sortorder;
      this.currentPrice = currentPrice;
      this.currentShare = currentShare;
      this.currentWeight = currentWeight;
      this.currentValue = currentValue;
      this.currentRisk = currentRisk;
      this.currentReturn = currentReturn;
      this.expectedShare = expectedShare;
      this.expectedWeight = expectedWeight;
      this.expectedValue = expectedValue;
      this.expectedRisk = expectedRisk;
      this.expectedReturn = expectedReturn;
   }

   public ManagePortfolioData(String ticker, String assetclass, String subclass, String name, Integer sortorder, Double currentPrice, Integer currentShare, Double currentWeight, Double currentValue, Double currentRisk, Double currentReturn)
   {
      this.ticker = ticker;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.name = name;
      this.sortorder = sortorder;
      this.currentPrice = currentPrice;
      this.currentShare = currentShare;
      this.currentWeight = currentWeight;
      this.currentValue = currentValue;
      this.currentRisk = currentRisk;
      this.currentReturn = currentReturn;
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

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public Double getCurrentPrice()
   {
      return currentPrice;
   }

   public void setCurrentPrice(Double currentPrice)
   {
      this.currentPrice = currentPrice;
   }

   public Integer getCurrentShare()
   {
      return currentShare;
   }

   public void setCurrentShare(Integer currentShare)
   {
      this.currentShare = currentShare;
   }

   public Double getCurrentWeight()
   {
      return currentWeight;
   }

   public void setCurrentWeight(Double currentWeight)
   {
      this.currentWeight = currentWeight;
   }

   public Double getCurrentValue()
   {
      return currentValue;
   }

   public void setCurrentValue(Double currentValue)
   {
      this.currentValue = currentValue;
   }

   public Double getCurrentRisk()
   {
      return currentRisk;
   }

   public void setCurrentRisk(Double currentRisk)
   {
      this.currentRisk = currentRisk;
   }

   public Double getCurrentReturn()
   {
      return currentReturn;
   }

   public void setCurrentReturn(Double currentReturn)
   {
      this.currentReturn = currentReturn;
   }

   public Integer getExpectedShare()
   {
      return expectedShare;
   }

   public void setExpectedShare(Integer expectedShare)
   {
      this.expectedShare = expectedShare;
   }

   public Double getExpectedWeight()
   {
      return expectedWeight;
   }

   public void setExpectedWeight(Double expectedWeight)
   {
      this.expectedWeight = expectedWeight;
   }

   public Double getExpectedValue()
   {
      return expectedValue;
   }

   public void setExpectedValue(Double expectedValue)
   {
      this.expectedValue = expectedValue;
   }

   public Double getExpectedRisk()
   {
      return expectedRisk;
   }

   public void setExpectedRisk(Double expectedRisk)
   {
      this.expectedRisk = expectedRisk;
   }

   public Double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(Double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }
}

