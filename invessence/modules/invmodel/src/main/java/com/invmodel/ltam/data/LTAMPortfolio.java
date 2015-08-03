package com.invmodel.ltam.data;

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
   private String asset;
   private String subasset;
   private String displayname;
   private String color;
   private Double weight;
   private Integer sortorder;

   public LTAMPortfolio()
   {
   }

   public LTAMPortfolio(String theme, String asset, String subasset, String displayname, String color, Double weight, Integer sortorder)
   {
      this.theme = theme;
      this.asset = asset;
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

   public void setTheme(String theme)
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

   public String getSubasset()
   {
      return subasset;
   }

   public void setSubasset(String subasset)
   {
      this.subasset = subasset;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }
}

