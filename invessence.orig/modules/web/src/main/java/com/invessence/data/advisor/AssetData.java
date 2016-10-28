package com.invessence.data.advisor;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/29/15
 * Time: 7:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetData
{
   String theme;
   String themename;
   String status;
   String assetclass;
   String displayName;
   String indexticker;
   Integer sortorder;
   Double lowerbound;
   Double upperbound;
   Double endAllocation;
   Double riskAdjustment;
   String color;

   public AssetData()
   {
   }

   public AssetData(String theme, String themename,
                    String status, String assetclass, String displayName,
                    String indexticker, Integer sortorder, Double lowerbound,
                    Double upperbound, Double endAllocation, Double riskAdjustment,
                    String color)
   {
      this.theme = theme;
      this.themename = themename;
      this.status = status;
      this.assetclass = assetclass;
      this.displayName = displayName;
      this.indexticker = indexticker;
      this.sortorder = sortorder;
      this.lowerbound = lowerbound;
      this.upperbound = upperbound;
      this.endAllocation = endAllocation;
      this.riskAdjustment = riskAdjustment;
      this.color = color;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getThemename()
   {
      return themename;
   }

   public void setThemename(String themename)
   {
      this.themename = themename;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public void setDisplayName(String displayName)
   {
      this.displayName = displayName;
   }

   public String getIndexticker()
   {
      return indexticker;
   }

   public void setIndexticker(String indexticker)
   {
      this.indexticker = indexticker;
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

   public Double getEndAllocation()
   {
      return endAllocation;
   }

   public void setEndAllocation(Double endAllocation)
   {
      this.endAllocation = endAllocation;
   }

   public Double getRiskAdjustment()
   {
      return riskAdjustment;
   }

   public void setRiskAdjustment(Double riskAdjustment)
   {
      this.riskAdjustment = riskAdjustment;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }
}
