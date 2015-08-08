package com.invmodel.ltam.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMAsset
{
   private String theme;
   private String asset;
   private String displayname;
   private Double weight;
   private String color;
   private Integer sortorder;
   ArrayList<String> portfolioList;
   Map<String, LTAMPortfolio> portfolio;

   public LTAMAsset()
   {
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, LTAMPortfolio>();
   }

   public LTAMAsset(String theme, String asset, String displayname, Double weight, String color, Integer sortorder)
   {
      this.theme = theme;
      this.asset = asset;
      this.displayname = displayname;
      this.weight = weight;
      this.color = color;
      this.sortorder = sortorder;
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, LTAMPortfolio>();

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

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public Double getWeight()
   {
      return weight;
   }

   public Double getWeightAsPercent()
   {
      return weight / 100.0;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public Map<String, LTAMPortfolio> getPortfolio()
   {
      return portfolio;
   }

   public void setPortfolio(Map<String, LTAMPortfolio> portfolio)
   {
      this.portfolio = portfolio;
   }

   public void addPortfolio(LTAMPortfolio portfoliodata) {
      try {
         if (portfoliodata != null) {
            if (portfoliodata.getAsset().toUpperCase().equals(asset.toUpperCase())) {
               String portfolioname = portfoliodata.getSubasset();
               if (! portfolio.containsKey(portfolioname)) {
                  portfolioList.add(portfolioname);
                  portfolio.put(portfolioname, portfoliodata);
               }
            }
         }

      }
      catch (Exception ex) {
      }
   }

   public void addPortfolio(Map<String, LTAMPortfolio> portfolioMap) {
      try {
         if (portfolioMap != null) {
            for (LTAMPortfolio pdata : portfolioMap.values())
               addPortfolio(pdata);
         }
      }
      catch (Exception ex) {
      }
   }

}
