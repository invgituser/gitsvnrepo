package com.invmodel.asset.data;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;

public class Asset
{
   private String asset          = "";
   private String displayName    = "";
   private String color          = "";
   // These values are created by Allocation Model
   private double allocweight   = 0.0;
   private double userweight    = 0.0;
   private double actualweight   = 0.0;  // This is re-calculated when the portfolio is created. Also stored as 0.##### As true %
   private double avgReturn      = 0.0;
   private double risk           = 0.0;
   private double expectedReturn = 0.0;
   private double expenseRatio   = 0.0;
   private double value          = 0.0;
   // These below items are created by Managed (Holding data)
   private double holdingweight      = 0.0;  // Stored in format ###.#########
   private double holdingReturn      = 0.0;
   private double holdingRisk        = 0.0;
   private double holdingExpenseRatio     = 0.0;
   private double holdingValue       = 0.0;

   public Asset()
   {
   }

   public Asset(String asset, String displayName, String color, double allocweight, double avgReturn)
   {
      super();
      setAsset(asset);
      this.displayName =  displayName;
      setAllocweight(allocweight);
      setAvgReturn(avgReturn);
      setColor(color);
   }

   public Asset(String asset, String color,
                double allocweight, double avgReturn, double risk, double expectedReturn, double expenseRatio, double value)
   {
      super();
      setAsset(asset);
      setAllocweight(allocweight);
      setAvgReturn(avgReturn);
      setColor(color);
      setExpectedReturn(expectedReturn);
      setRisk(risk);
      setExpenseRatio(expenseRatio);
      setValue(value);
   }

   public Asset(String asset, String displayName, String color,
                double allocweight, double avgReturn, double risk, double expectedReturn, double expenseRatio, double value,
                double holdingweight, double holdingReturn, double holdingRisk, double holdingExpenseRatio, double holdingValue)
   {
      setAsset(asset);
      this.displayName = displayName;
      setColor(color);
      setAllocweight(allocweight);
      setAvgReturn(avgReturn);
      setRisk(risk);
      setExpectedReturn(expectedReturn);
      setExpenseRatio(expenseRatio);
      setValue(value);
      setHoldingweight(holdingweight);
      setHoldingReturn(holdingReturn);
      setHoldingRisk(holdingRisk);
      setHoldingExpenseRatio(holdingExpenseRatio);
      setHoldingValue(holdingValue);
   }

   public double getDisplayActualWeight()
   {
      return ((Math.round(getActualweight() * 10000.00))/100.00);
   }

   public double getAllocweight()
   {
      return allocweight;
   }

   public void setAllocweight(double allocweight)
   {
      this.allocweight = allocweight;
      actualweight = allocweight;  // When resetting the Alloc weight, reset the actual weight as well.
   }

   public void setActualweight(double actualweight)
   {
      this.actualweight = actualweight;
   }

   public double getActualweight()
   {
      // Version Advisor 1.4 (July 17, 2014)
      // Round the number to two digits.
      return actualweight;
   }

   public double getUserEdit() {
      return actualweight;
   }

   public void setUserEdit(double userweight) {
      this.userweight = userweight / 100.0;
      this.actualweight = this.userweight;
   }

   public double getUserweight()
   {
      if (userweight > 0.0)
         return userweight;
      else
         return getAllocweight();
   }

   public void setUserweight(double userweight)
   {
      this.userweight = userweight;
      this.actualweight = this.userweight;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public String getAsset()
   {
      return asset;
   }

   public void setAsset(String asset)
   {
      this.asset = asset;
   }

   public Boolean equals(String asset)
   {
      return this.asset.equals(asset);
   }

   public double getAvgReturn()
   {
      return avgReturn;
   }

   public void setAvgReturn(double avgReturn)
   {
      this.avgReturn = avgReturn;
   }

   public double getRisk()
   {
      return risk;
   }

   public void setRisk(double risk)
   {
      this.risk = risk;
   }

   public double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }

   public double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public double getValue()
   {
      return value;
   }

   public void setValue(double value)
   {
      this.value = value;
   }

   public double getHoldingweight()
   {
      return holdingweight;
   }

   public void setHoldingweight(double holdingweight)
   {
      this.holdingweight = holdingweight;
   }

   public double getHoldingReturn()
   {
      return holdingReturn;
   }

   public void setHoldingReturn(double holdingReturn)
   {
      this.holdingReturn = holdingReturn;
   }

   public double getHoldingRisk()
   {
      return holdingRisk;
   }

   public void setHoldingRisk(double holdingRisk)
   {
      this.holdingRisk = holdingRisk;
   }

   public double getHoldingExpenseRatio()
   {
      return holdingExpenseRatio;
   }

   public void setHoldingExpenseRatio(double holdingExpenseRatio)
   {
      this.holdingExpenseRatio = holdingExpenseRatio;
   }

   public double getHoldingValue()
   {
      return holdingValue;
   }

   public void setHoldingValue(double holdingValue)
   {
      this.holdingValue = holdingValue;
   }

   @Override
   public String toString()
   {
      try
      {
         String str = asset + ":" + getActualweight() + "," + color;
         return str;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return asset;
   }

   public String toXml()
   {
      String xmlData = "";
      try
      {
         xmlData = xmlData + buildElement("Asset", asset) +
            buildElement("Weight", valueOf(getDisplayActualWeight())) +
            buildElement("Color", color);
         return buildElement("Asset", xmlData);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("Asset", asset));
   }
}
