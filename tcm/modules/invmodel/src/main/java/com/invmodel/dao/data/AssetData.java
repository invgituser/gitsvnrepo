package com.invmodel.dao.data;

import java.io.Serializable;
import java.util.*;

public class AssetData implements Serializable
{
   private String theme = "";
   private String asset = "";
   private String displayname = "";
   private double lbConstraint = 0.0;
   private double ubConstraint = 0.0;
   private String index = "";
   private double averageReturn = 0.0;
   private String color = "";
   private double risk_adjustment = 0.0;
   private double end_allocation = 0.0;
   private int sortorder = 0;
   private double[] weights;
   //  private ArrayList<SubAssetClassData> subAsset = new ArrayList<SubAssetClassData>();

   private ArrayList<String> primeAssetMap;
   private Map<String, PrimeAssetClassData> primeAssetDataMap;

   // This is a short cut to getting ordered Information, instead of looking for appropriate index value to key.
   private double[][] primeAssetweights;
   private double[] primeAssetrisk;
   private double[] primeAssetreturns;


   public AssetData()
   {
      super();
      primeAssetMap = new ArrayList<String>();
      primeAssetDataMap = new HashMap<String, PrimeAssetClassData>();
   }

   public AssetData(String theme, String asset, String displayname, double lbConstraint, double ubConstraint,
                    String index, double averageReturn,
                    String color, double risk_adjustment, double end_allocation, int sortorder
                    //, SubAssetClassData subAsset
   )
   {
      super();
      primeAssetMap = new ArrayList<String>();
      primeAssetDataMap = new HashMap<String, PrimeAssetClassData>();
      this.theme = theme;
      this.asset = asset;
      this.displayname = displayname;
      this.lbConstraint = lbConstraint;
      this.ubConstraint = ubConstraint;
      this.index = index;
      this.averageReturn = averageReturn;
      this.color = color;
      this.risk_adjustment = risk_adjustment;
      this.end_allocation = end_allocation;
      this.weights = null;
      this.sortorder = sortorder;
/*
        if (subAsset != null) {
            setSubAsset(subAsset);
        }
*/
   }

   private String checkAssetName(String assetName) {
      if (assetName == null || assetName.length() == 0)
         assetName = "DISCARD";

      return assetName.toUpperCase();
   }

   private String checkPrimeAssetName(String primeassetname) {
      if (primeassetname == null || primeassetname.length() == 0)
         primeassetname = "DISCARD";

      return primeassetname.toUpperCase();
   }

   private String buildPrimeAssetKey(String assetName, String primeassetname) {

      return checkAssetName(assetName) + "." + checkPrimeAssetName(primeassetname);
   }

   public String getGroupname()
   {
      return theme;
   }

   public void setGroupname(String theme)
   {
      this.theme = theme;
   }

   public String getAsset()
   {
      return asset;
   }

   public void setAsset(String asset)
   {
      this.asset = asset;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
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

   public String getIndex()
   {
      return index;
   }

   public void setIndex(String index)
   {
      this.index = index;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.index = color;
   }

   public double getAverageReturn()
   {
      return averageReturn;
   }

   public void setAverageReturn(double averageReturn)
   {
      this.averageReturn = averageReturn;
   }

   public double getRisk_adjustment()
   {
      return risk_adjustment;
   }

   public void setRisk_adjustment(double risk_adjustment)
   {
      this.risk_adjustment = risk_adjustment;
   }

   public double getEnd_allocation()
   {
      return end_allocation;
   }

   public void setEnd_allocation(double end_allocation)
   {
      this.end_allocation = end_allocation;
   }

   public double[] getWeights()
   {
      return weights;
   }

   public void setWeights(double[] weights)
   {
      this.weights = weights;
   }

   public double getWeight(int offset)
   {
      if (weights == null)
         return 0.0;

      if (offset >= 0 && offset <= weights.length)
      {
         return weights[offset];
      }
      else
      {
         return 0.0;
      }
   }

   public ArrayList<PrimeAssetClassData> getOrderedPrimeAssetData()
   {
      ArrayList<PrimeAssetClassData> pcadList = new ArrayList<PrimeAssetClassData>();
      for (String primeasset : getOrderedPrimeAssetList() ) {
         pcadList.add(getPrimeAssetData(primeasset));
      }
      return pcadList;
   }

   public ArrayList<String> getOrderedPrimeAssetList()
   {
      return primeAssetMap;
   }


   public PrimeAssetClassData getPrimeAssetData(String primeassetName)
   {
      return primeAssetDataMap.get(buildPrimeAssetKey(asset,primeassetName));
   }

   public ArrayList<String> getOrderedPrimeAssetTickers()
   {
      ArrayList<String> tickerList = new ArrayList<String>();
      for (String primeasset : getOrderedPrimeAssetList() ) {
            tickerList.add(getPrimeAssetData(primeasset).getTicker());
      }
      return tickerList;
   }

   public void addPrimeAsset(String primeAssetName, PrimeAssetClassData primeassetSecurity) {
      String key;

      key = buildPrimeAssetKey(asset,primeAssetName);
      if (! primeAssetDataMap.containsKey(key)) {
         primeAssetMap.add(checkPrimeAssetName(primeAssetName));  // Add new item to List
         primeAssetDataMap.put(key, primeassetSecurity);
      }
   }

   public double[][] getPrimeAssetweights()
   {
      return primeAssetweights;
   }

   public void setPrimeAssetweights(double[][] primeAssetweights)
   {
      this.primeAssetweights = primeAssetweights;
   }

   public double[] getPrimeAssetrisk()
   {
      return primeAssetrisk;
   }

   public void setPrimeAssetrisk(double[] primeAssetrisk)
   {
      this.primeAssetrisk = primeAssetrisk;
   }

   public double[] getPrimeAssetreturns()
   {
      return primeAssetreturns;
   }

   public void setPrimeAssetreturns(double[] primeAssetreturns)
   {
      this.primeAssetreturns = primeAssetreturns;
   }
}
