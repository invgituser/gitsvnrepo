package com.invessence.web.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/14/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagedSubclassData
{
   private String name = "";
   private Double currentWeight = 0.0;
   private Double currentReturn = 0.0;
   private Double currentRisk = 0.0;
   private Double currentValue = 0.0;
   private Double expectedWeight = 0.0;
   private Double expectedReturn = 0.0;
   private Double exptectedRisk = 0.0;
   private Double exptectedValue = 0.0;

   public ManagedSubclassData()
   {
   }
   public ManagedSubclassData(String asset, Double expectedWeight, Double expectedReturn, Double exptectedRisk,
                              Double currentWeight, Double currentReturn, Double currentRisk, Double currentValue)
   {
      this.name = asset;
      this.currentWeight = currentWeight;
      this.currentReturn = currentReturn;
      this.currentRisk = currentRisk;
      this.expectedWeight = expectedWeight;
      this.expectedReturn = expectedReturn;
      this.exptectedRisk = exptectedRisk;
      this.currentValue =  currentValue;
   }

   public ManagedSubclassData(String asset,
                              Double currentWeight, Double currentRisk, Double currentReturn, Double currentValue)
   {
      this.name = asset;
      this.currentWeight = currentWeight;
      this.currentRisk = currentRisk;
      this.currentReturn = currentReturn;
      this.currentValue = currentValue;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public void setCurrentWeight(Double currentWeight)
   {
      this.currentWeight = currentWeight;
   }

   public Double getCurrentReturn()
   {
      return currentReturn;
   }

   public void setCurrentReturn(Double currentReturn)
   {
      this.currentReturn = currentReturn;
   }

   public Double getCurrentRisk()
   {
      return currentRisk;
   }

   public void setCurrentRisk(Double currentRisk)
   {
      this.currentRisk = currentRisk;
   }

   public Double getCurrentValue()
   {
      return currentValue;
   }

   public void setCurrentValue(Double currentValue)
   {
      this.currentValue = currentValue;
   }

   public Double getExpectedWeight()
   {
      return expectedWeight;
   }

   public void setExpectedWeight(Double expectedWeight)
   {
      this.expectedWeight = expectedWeight;
   }

   public Double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(Double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }

   public Double getExptectedRisk()
   {
      return exptectedRisk;
   }

   public void setExptectedRisk(Double exptectedRisk)
   {
      this.exptectedRisk = exptectedRisk;
   }

   public Double getCurrentWeight()
   {
      return currentWeight;
   }

   public Double getExptectedValue()
   {
      return exptectedValue;
   }

   public void setExptectedValue(Double exptectedValue)
   {
      this.exptectedValue = exptectedValue;
   }
}
