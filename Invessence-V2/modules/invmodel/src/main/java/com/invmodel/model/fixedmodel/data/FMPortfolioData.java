package com.invmodel.model.fixedmodel.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMPortfolioData
{
   private String theme;
   private String level;
   private String themename;
   private String asset;
   private String assetname;
   private String ticker;
   private String sec_name;
   private String subasset;
   private String displayname;
   private String color;
   private Double weight;
   private Integer sortorder;

   public FMPortfolioData()
   {
   }

   public FMPortfolioData(String theme, String level, String themename,
                          String asset, String assetname,
                          String ticker, String name,
                          String subasset, String displayname, String color,
                          Double weight, Integer sortorder)
   {
      this.theme = theme;
      this.level = level;
      this.themename = themename;
      this.asset = asset;
      this.assetname = assetname;
      this.ticker = ticker;
      this.sec_name = name;
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

   public String getLevel()
   {
      return level;
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

   public String getTicker()
   {
      return ticker;
   }

   public String getSubasset()
   {
      return subasset;
   }

   public String getSec_name()
   {
      return sec_name;
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

