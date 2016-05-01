package com.invessence.data;

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

   public String getAsset()
   {
      return asset;
   }

   public String getDisplayname()
   {
      return displayname;
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

   public String getColor()
   {
      return color;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public ArrayList<String> getPortfolioList()
   {
      return portfolioList;
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
