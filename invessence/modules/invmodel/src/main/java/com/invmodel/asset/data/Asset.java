package com.invmodel.asset.data;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;

public class
   Asset
{
   private String asset = "";
   private double weight = 0.0;        // Stored in format ###.##
   private double actualweight = 0.0;  // This is re-calculated when the portfolio is created.
   private double avgReturn = 0.0;
   private double risk = 0.0;
   private double expectedReturn = 0.0;
   private String color = "";


   public Asset()
   {
   }

   public Asset(String asset, double actualweight, double avgReturn, String color)
   {
      super();
      setAsset(asset);
      setActualweight(actualweight);
      setAvgReturn(avgReturn);
      setColor(color);
   }

   public Asset(String asset, double actualweight, double avgReturn, String color, double risk, double expectedReturn)
   {
      super();
      setAsset(asset);
      setActualweight(actualweight);
      setAvgReturn(avgReturn);
      setColor(color);
      setExpectedReturn(expectedReturn);
      setRisk(risk);
   }

   private Double double_round(Double value) {
      try {
         Double tmp = ((Math.round(value * 1000.00)) / 1000.00); // First round to thousand's point.
         return ((Math.round(tmp * 100.00)) / 100.00); // now round to 100th point
      }
      catch (Exception ex) {

      }
      return ((Math.round(value * 100.00)) / 100.00);
   }

   public double getWeight()
   {
      // Version Advisor 1.4 (July 17, 2014)
      // Round the number to two digits.
      if (weight >= 100.00)
         return 100.00;
      else
         return (weight);

   }

   public void setWeight(double weight)
   {
      // Expecting this as percent, such as 23.678952,
      // Actual is saved as 0.23678952, therefore we devide by 100.
      setActualweight(weight / 100.00);
   }

   private void saveWeight(double weight)
   {
      this.weight = Math.round(weight * 100.00);
   }

   public double getActualweight()
   {
      // Version Advisor 1.4 (July 17, 2014)
      // Round the number to two digits.
      return actualweight;

 /*     if (actualweight >= 1.00)
         return 1.00;
      else {
         return (Math.round(actualweight * 10.00) / 10.00);
      }*/
   }

   public void setActualweight(double actualweight)
   {
      Integer tmp;

      // Round the number to two digits only.
      this.actualweight = actualweight;
/*
      if (actualweight == 0.0)
         this.actualweight = actualweight;
      else
         this.actualweight = double_round(actualweight);
*/
      saveWeight(actualweight);
   }

   public int getRoundedActualWeight()
   {
      return (int) Math.round(getWeight());
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
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

   @Override
   public String toString()
   {
      try
      {
         String str = this.asset + ":" + valueOf(getActualweight()) + "," + this.color;
         return str;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return this.asset;
   }

   public String toXml()
   {
      String xmlData = "";
      try
      {
         xmlData = xmlData + buildElement("Asset", this.asset) +
            buildElement("Weight", valueOf(getWeight())) +
            buildElement("Color", this.color);
         return buildElement("Asset", xmlData);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("Asset", this.asset));
   }
}
