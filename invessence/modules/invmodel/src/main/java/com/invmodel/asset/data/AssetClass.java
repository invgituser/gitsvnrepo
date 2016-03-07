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
   private String theme;
   private Integer age;
   private Integer horizon;
   private Integer risk;
   private Double riskOffset;
   private Integer stayInvested;
   private Double totalInvested = 0.0;
   private Double percentequity = 0.0;
   private Double percentfixedIncome = 0.0;
   private Double percentcash = 0.0;


   public AssetClass()
   {
      super();
      // TODO Auto-generated constructor stub
   }

   public AssetClass(Integer age, Integer horizon, Double riskOffset, Integer stayInvested,
                     String theme, String asset, String displayName, String color, double weight, double avgReturn)
   {
      super();
      this.age = age;
      this.horizon = horizon;
      this.theme = theme;
      this.riskOffset = riskOffset;
      this.stayInvested = stayInvested;
      addAssetClass(asset, displayName, color, weight, avgReturn);
   }

   public void initAssetClass(Integer age, Integer horizon, Double riskOffset, Integer stayInvested, String theme)
   {
      this.age = age;
      this.horizon = horizon;
      this.riskOffset = riskOffset;
      this.stayInvested = stayInvested;
      this.theme = theme;
   }

   public void addAssetClass(String asset, String displayName, String color, double weight, double avgReturn)
   {
      if (!assetclass.containsKey(asset))
      {
         Asset data = new Asset(asset, displayName, color, weight, avgReturn);
         assetclass.put(asset, data);
         orderedAsset.add(asset);
      }
      else
      {
         Asset data = assetclass.get(asset);
         data.setAllocweight(weight);
         data.setAvgReturn(avgReturn);
         data.setColor(color);
         this.assetclass.put(asset, data);
      }
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public Map<String,Asset> getAssetclass() {
       return this.assetclass;
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

   public int getCashAllocWeight()
   {
      if (assetclass.containsKey("Cash"))
      {
         return ((int) assetclass.get("Cash").getAllocweight());
      }
      return 0;
   }

   public int getCashActualWeight()
   {
      if (assetclass.containsKey("Cash"))
      {
         return ((int) assetclass.get("Cash").getActualweight());
      }
      return 0;
   }

   public Integer getStayInvested()
   {
      return stayInvested;
   }

   public void setStayInvested(Integer stayInvested)
   {
      this.stayInvested = stayInvested;
   }

   public double getTotalInvested()
   {
      return totalInvested;
   }

   public void setTotalInvested(double totalInvested)
   {
      this.totalInvested = totalInvested;
   }

   public void setTotalInvested(Double totalInvested)
   {
      this.totalInvested = totalInvested;
   }

   public Double getPercentequity()
   {
      return percentequity;
   }

   public void setPercentequity(Double percentequity)
   {
      this.percentequity = percentequity;
   }

   public Double getPercentfixedIncome()
   {
      return percentfixedIncome;
   }

   public void setPercentfixedIncome(Double percentfixedIncome)
   {
      this.percentfixedIncome = percentfixedIncome;
   }

   public Double getPercentcash()
   {
      return percentcash;
   }

   public void setPercentcash(Double percentcash)
   {
      this.percentcash = percentcash;
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
