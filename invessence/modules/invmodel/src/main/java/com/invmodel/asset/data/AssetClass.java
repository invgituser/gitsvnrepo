package com.invmodel.asset.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.invmodel.utils.XMLBuilder.buildElement;

public class AssetClass
{
   private Map<String, Asset> assetclass = new HashMap<String, Asset>();
   private ArrayList<String> orderedAsset = new ArrayList<String>();
   private double cashWeight = 1.0;
   private Integer age;
   private Integer horizon;
   private Integer risk;
   private Double riskOffset;
   private Integer stayInvested;


   public AssetClass()
   {
      super();
      // TODO Auto-generated constructor stub
   }

   public AssetClass(Integer age, Integer horizon, Double riskOffset, Integer stayInvested,
                     String asset, double weight, double avgReturn, String color)
   {
      super();
      this.age = age;
      this.horizon = horizon;
      this.riskOffset = riskOffset;
      this.stayInvested = stayInvested;
      addAssetClass(asset, weight, avgReturn, color);
   }

   public void initAssetClass(Integer age, Integer horizon, Double riskOffset, Integer stayInvested)
   {
      this.age = age;
      this.horizon = horizon;
      this.riskOffset = riskOffset;
      this.stayInvested = stayInvested;
   }

   public void addAssetClass(String asset, double weight, double avgReturn, String color)
   {
      if (!assetclass.containsKey(asset))
      {
         Asset data = new Asset(asset, weight, avgReturn, color);
         cashWeight = cashWeight - weight;
         assetclass.put(asset, data);
         orderedAsset.add(asset);
      }
      else
      {
         Asset data = assetclass.get(asset);
         this.cashWeight = cashWeight + data.getWeight(); // Restore the old one
         data.setWeight(weight);
         data.setAvgReturn(avgReturn);
         data.setColor(color);
         data.setRisk(0.0);
         data.setExpectedReturn(0.0);
         this.cashWeight = cashWeight - weight;
         this.assetclass.put(asset, data);
      }
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

   public Integer getRisk()
   {
      return risk;
   }

   public void setRisk(Integer risk)
   {
      this.risk = risk;
   }

   public Double getRiskOffset()
   {
      return riskOffset;
   }

   public void setRiskOffset(Double riskOffset)
   {
      this.riskOffset = riskOffset;
   }

   public int getCashWeight()
   {
      if (assetclass.containsKey("Cash"))
      {
         return ((int) assetclass.get("Cash").getWeight());
      }
      return 0;
   }

   public void setCashWeight(double cashWeight)
   {
      this.cashWeight = cashWeight;
   }

   public Integer getStayInvested()
   {
      return stayInvested;
   }

   public void setStayInvested(Integer stayInvested)
   {
      this.stayInvested = stayInvested;
   }

   public Asset getAsset(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset));
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public void setAssetWeight(String asset, double weight)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            assetclass.get(asset).setWeight(weight);
         }
/*
         else
         {
            addAssetClass(asset, weight, 1.0, "");
         }
*/
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public Double getTotalAssetWeight()
   {
      try
      {
         String assetname;
         Double totalWeight = 0.0;
         for (int i = 0; i < this.getOrderedAsset().size();i++) {
            assetname = this.getOrderedAsset().get(i);
            totalWeight = totalWeight + this.getAsset(assetname).getWeight();
         }
         return totalWeight;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }


   public void setAssetActualWeight(String asset, double weight)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            assetclass.get(asset).setActualweight(weight);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public double getAssetWeight(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getWeight());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }

   public double getAssetActualWeight(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getActualweight());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }

   public int getAssetRoundedWeight(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return ((int) assetclass.get(asset).getWeight());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0;
   }

   public int getAssetRoundedActualWeight(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getRoundedActualWeight());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0;
   }

   public void setAssetRisk(String asset, double risk)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            assetclass.get(asset).setRisk(risk);
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public double getAssetRisk(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getRisk());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }

   public void setAssetExpectedReturns(String asset, double expReturns)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            assetclass.get(asset).setExpectedReturn(expReturns);
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public double getAssetExpectedReturns(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getExpectedReturn());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }

   public void setAssetAverageReturns(String asset, double avgReturn)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            assetclass.get(asset).setAvgReturn(avgReturn);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public double getAssetAverageReturns(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getAvgReturn());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return 0.0;
   }


   public String getAssetColor(String asset)
   {
      try
      {
         if (assetclass.containsKey(asset))
         {
            return (assetclass.get(asset).getColor());
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return "";
   }


   @SuppressWarnings("rawtypes")
   @Override
   public String toString()
   {
      try
      {
         Iterator it = this.assetclass.keySet().iterator();
         String assetList = "";
         while (it.hasNext())
         {
            String ticker = (String) it.next();
            assetList = assetList + this.assetclass.get(ticker).toString() + "\n";
         }
         return assetList;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return "";
   }

   @SuppressWarnings("rawtypes")
   public String toXml()
   {
      String xmlData = "";
      try
      {
         Iterator it = this.assetclass.keySet().iterator();
         while (it.hasNext())
         {
            String ticker = (String) it.next();
            xmlData = xmlData + buildElement("AssetClass", this.assetclass.get(ticker).toXml()) + "\n";
            xmlData = xmlData + "\n";
         }
         return xmlData;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return buildElement("AssetAllocation", xmlData);

   }

   public ArrayList<String> getOrderedAsset()
   {
      return orderedAsset;
   }


}
