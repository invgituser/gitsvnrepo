package com.invmodel.model.fixedmodel.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMAssetData
{
   private String theme;
   private String level;
   private String asset;
   private String displayname;
   private Double weight;
   private String color;
   private Integer sortorder;
   ArrayList<String> portfolioList;
   Map<String, FMPortfolioData> portfolio;

   public FMAssetData()
   {
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, FMPortfolioData>();
   }

   public FMAssetData(String theme, String level, String asset, String displayname, Double weight, String color, Integer sortorder)
   {
      this.theme = theme;
      this.level = level;
      this.asset = asset;
      this.displayname = displayname;
      this.weight = weight;
      this.color = color;
      this.sortorder = sortorder;
      portfolioList = new ArrayList<String>();
      portfolio = new LinkedHashMap<String, FMPortfolioData>();

   }

   public String getTheme()
   {
      return theme;
   }

   public String getLevel()
   {
      return level;
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

   public Map<String, FMPortfolioData> getPortfolio()
   {
      return portfolio;
   }

   public void setPortfolio(Map<String, FMPortfolioData> portfolio)
   {
      this.portfolio = portfolio;
   }

   public void addPortfolio(FMPortfolioData portfoliodata) {
      try {
         if (portfoliodata != null) {
            if (portfoliodata.getAsset().toUpperCase().equals(asset.toUpperCase())) {
               String portfolioname = portfoliodata.getTicker();
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

   public void addPortfolio(Map<String, FMPortfolioData> portfolioMap) {
      try {
         if (portfolioMap != null) {
            for (FMPortfolioData pdata : portfolioMap.values())
               addPortfolio(pdata);
         }
      }
      catch (Exception ex) {
      }
   }

}
