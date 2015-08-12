package com.invessence.bean.ltam;

import java.util.ArrayList;

import com.invmodel.ltam.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/9/15
 * Time: 11:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMAllocationData
{
   private String theme;
   private ArrayList<LTAMAllocatedAsset> ltamAllocatedAssetList;
   private ArrayList<LTAMAllocatedSubAsset> ltamAllocatedSubAssetList;

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public ArrayList<LTAMAllocatedAsset> getLtamAllocatedAssetList()
   {
      return ltamAllocatedAssetList;
   }

   public void setLtamAllocatedAssetList(ArrayList<LTAMAllocatedAsset> ltamAllocatedAssetList)
   {
      this.ltamAllocatedAssetList = ltamAllocatedAssetList;
   }

   public ArrayList<LTAMAllocatedSubAsset> getLtamAllocatedSubAssetList()
   {
      return ltamAllocatedSubAssetList;
   }

   public void setLtamAllocatedSubAssetList(ArrayList<LTAMAllocatedSubAsset> ltamAllocatedSubAssetList)
   {
      this.ltamAllocatedSubAssetList = ltamAllocatedSubAssetList;
   }

   public void copyAssetAllocation(ArrayList<LTAMAsset> assetdatalist, Double invested) {
      if (assetdatalist == null)
         return;

      if (ltamAllocatedAssetList == null) {
         ltamAllocatedAssetList = new ArrayList<LTAMAllocatedAsset>();
      }
      else
         ltamAllocatedAssetList.clear();
      Double money;
      if (invested == null) {
         invested = 0.0;
      }
      Double remain = invested;
      for (LTAMAsset singleassetdata : assetdatalist) {
         money =  singleassetdata.getWeightAsPercent() * invested;
         if (singleassetdata.getAsset().toUpperCase().contains("CASH")) {
            money =  remain;
         }
         else {
            remain -= money;
         }
         LTAMAllocatedAsset ltamallocatedAssetData = new LTAMAllocatedAsset(singleassetdata.getTheme(),
                                                                            singleassetdata.getAsset(),
                                                                            singleassetdata.getDisplayname(),
                                                                            singleassetdata.getColor(),
                                                                            singleassetdata.getWeight(),
                                                                            money);
         ltamAllocatedAssetList.add(ltamallocatedAssetData);
      }

   }

   public void copyAssetSubAllocation(ArrayList<LTAMPortfolio> subassetdatalist, Double invested) {
      if (subassetdatalist == null)
         return;

      if (ltamAllocatedSubAssetList == null)
         ltamAllocatedSubAssetList = new ArrayList<LTAMAllocatedSubAsset>();
      else
         ltamAllocatedSubAssetList.clear();

      Double money;
      if (invested == null) {
         invested = 0.0;
      }
      Double remain = invested;
      for (LTAMPortfolio singlesubassetdata : subassetdatalist) {
         money =  singlesubassetdata.getWeightAsPercent() * invested;
         if ((remain - money) < 0) {
            money = remain;
         }
         remain -= money;
         LTAMAllocatedSubAsset ltamAllocatedSubAssetdata = new LTAMAllocatedSubAsset(singlesubassetdata.getThemename(),
                                                                            singlesubassetdata.getAssetname(),
                                                                            singlesubassetdata.getDisplayname(),
                                                                            singlesubassetdata.getColor(),
                                                                            singlesubassetdata.getWeight(),
                                                                            money);
         ltamAllocatedSubAssetList.add(ltamAllocatedSubAssetdata);
      }
   }

   public class  LTAMAllocatedAsset {
      private String theme;
      private String asset;
      private String displayname;
      private String color;
      private Double weight;
      private Double money;

      public LTAMAllocatedAsset()
      {
      }

      public LTAMAllocatedAsset(String theme, String asset, String displayname, String color,
                                 Double weight, Double money)
      {
         this.theme = theme;
         this.asset = asset;
         this.displayname = displayname;
         this.color = color;
         this.weight = weight;
         this.money = money;
      }

      public String getTheme()
      {
         return theme;
      }

      public String getAsset()
      {
         return asset;
      }

      public String getDisplayname()
      {
         return displayname;
      }

      public String getColor()
      {
         return color;
      }

      public Double getWeight()
      {
         if (weight != null)
            return weight;
         else
            return 0.0;
      }

      public Double getWeightAsPercent()
      {
         if (weight != null)
            return weight / 100.0;
         else
            return 0.0;
      }


      public Double getMoney()
      {
         if (money != null)
            return money;
         else
            return 0.0;
      }
   }

   public class  LTAMAllocatedSubAsset {
      private String themename;
      private String assetname;
      private String subassetname;
      private String color;
      private Double weight;
      private Double money;

      public LTAMAllocatedSubAsset()
      {
      }

      public LTAMAllocatedSubAsset(String themename, String assetname, String subassetname, String color,
                                   Double weight, Double money)
      {
         this.themename = themename;
         this.assetname = assetname;
         this.subassetname = subassetname;
         this.color = color;
         this.weight = weight;
         this.money = money;
      }

      public String getThemename()
      {
         return themename;
      }

      public String getAssetname()
      {
         return assetname;
      }

      public String getSubassetname()
      {
         return subassetname;
      }

      public String getColor()
      {
         return color;
      }

      public Double getWeight()
      {
         if (weight != null)
            return weight;
         else
            return 0.0;
      }

      public Double getWeightAsPercent()
      {
         if (weight != null)
            return weight / 100.0;
         else
            return 0.0;
      }


      public Double getMoney()
      {
         if (money != null)
            return money;
         else
            return 0.0;
      }
   }


}
