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

   public Asset(String asset, double weight, double avgReturn, String color)
   {
      super();
      setAsset(asset);
      setActualweight(weight);
      setAvgReturn(avgReturn);
      setColor(color);
   }

   public Asset(String asset, double weight, double avgReturn, String color, double risk, double expectedReturn)
   {
      super();
      setAsset(asset);
      setActualweight(weight);
      setAvgReturn(avgReturn);
      setColor(color);
      setExpectedReturn(expectedReturn);
      setRisk(risk);
   }

   public double getWeight()
   {
      return weight;
   }

   public void setWeight(double weight)
   {
      // Expecting this as percent, such as 23.678952,
      // Actual is saved as 0.23678952, therefore we devide by 100.
      setActualweight(weight / 100.00);
   }

   private void saveWeight(double weight)
   {
      this.weight = Math.round(weight * 10000.00) / 100.00;
   }

   public double getActualweight()
   {
      return actualweight;
   }

   public void setActualweight(double actualweight)
   {
      this.actualweight = actualweight;
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
