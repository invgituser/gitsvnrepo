package com.invmodel.model.fixedmodel.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FIAsset
{
   private String theme;
   private String asset;
   private String displayname;
   private Double weight;
   private String color;
   private Integer sortorder;
   ArrayList<String> portfolioList;
   Map<String, FIPortfolio> portfolio;

   public FIAsset()
   {
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, FIPortfolio>();
   }

   public FIAsset(String theme, String asset, String displayname, Double weight, String color, Integer sortorder)
   {
      this.theme = theme;
      this.asset = asset;
      this.displayname = displayname;
      this.weight = weight;
      this.color = color;
      this.sortorder = sortorder;
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, FIPortfolio>();

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

   public Map<String, FIPortfolio> getPortfolio()
   {
      return portfolio;
   }

   public void setPortfolio(Map<String, FIPortfolio> portfolio)
   {
      this.portfolio = portfolio;
   }

   public void addPortfolio(FIPortfolio portfoliodata) {
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

   public void addPortfolio(Map<String, FIPortfolio> portfolioMap) {
      try {
         if (portfolioMap != null) {
            for (FIPortfolio pdata : portfolioMap.values())
               addPortfolio(pdata);
         }
      }
      catch (Exception ex) {
      }
   }

}
