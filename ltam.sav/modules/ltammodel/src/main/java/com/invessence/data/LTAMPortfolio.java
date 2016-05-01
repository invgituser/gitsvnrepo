package com.invessence.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMPortfolio
{
   private String theme;
   private String themename;
   private String asset;
   private String assetname;
   private String subasset;
   private String displayname;
   private String color;
   private Double weight;
   private Integer sortorder;

   public LTAMPortfolio()
   {
   }

   public LTAMPortfolio(String theme, String themename, String asset, String assetname,
                        String subasset, String displayname, String color,
                        Double weight, Integer sortorder)
   {
      this.theme = theme;
      this.themename = themename;
      this.asset = asset;
      this.assetname = assetname;
      this.subasset = subasset;
      this.displayname = displayname;
      this.color = color;
      this.weight = weight;
      this.sortorder = sortorder;
   }

   public String getTheme()
   {
      return theme;
   }

   public String getThemename()
   {
      return themename;
   }

   public String getAsset()
   {
      return asset;
   }

   public String getAssetname()
   {
      return assetname;
   }

   public String getSubasset()
   {
      return subasset;
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

   public Double getWeightAsPercent() {
      if (weight != null)
         return weight / 100.0;
      else
         return 0.0;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }
}

