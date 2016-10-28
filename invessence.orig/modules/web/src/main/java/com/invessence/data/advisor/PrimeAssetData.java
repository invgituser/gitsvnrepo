package com.invessence.data.advisor;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/29/15
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class PrimeAssetData
{
   String theme;
   String assetclass;
   String primeassetclass;
   String ticker;
   String active;
   Integer sortorder;
   Double lowerbound;
   Double upperbound;
   Double expectedReturn;

   public PrimeAssetData()
   {
   }

   public PrimeAssetData(String theme, String assetclass, String primeassetclass, String ticker, String active, Integer sortorder, Double lowerbound, Double upperbound, Double expectedReturn)
   {
      this.theme = theme;
      this.assetclass = assetclass;
      this.primeassetclass = primeassetclass;
      this.ticker = ticker;
      this.active = active;
      this.sortorder = sortorder;
      this.lowerbound = lowerbound;
      this.upperbound = upperbound;
      this.expectedReturn = expectedReturn;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getPrimeassetclass()
   {
      return primeassetclass;
   }

   public void setPrimeassetclass(String primeassetclass)
   {
      this.primeassetclass = primeassetclass;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getActive()
   {
      return active;
   }

   public void setActive(String active)
   {
      this.active = active;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public Double getLowerbound()
   {
      return lowerbound;
   }

   public void setLowerbound(Double lowerbound)
   {
      this.lowerbound = lowerbound;
   }

   public Double getUpperbound()
   {
      return upperbound;
   }

   public void setUpperbound(Double upperbound)
   {
      this.upperbound = upperbound;
   }

   public Double getExpectedReturn()
   {
      return expectedReturn;
   }

   public void setExpectedReturn(Double expectedReturn)
   {
      this.expectedReturn = expectedReturn;
   }
}
