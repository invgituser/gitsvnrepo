package com.invmodel.model.fixedmodel.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FMData
{
   private String theme;
   private String level;
   private Integer index;
   private String displayname;
   private String description;
   private Integer sortorder;
   private Double lowRisk;
   private Double highRisk;
   private Double expectedreturn;
   private Double expectedrisk;
   ArrayList<String> assetList;
   Map<String, FMAssetData> asset;
   Map<String,String> indexMap;
   FMProjection performanceData;

   public FMData()
   {
      assetList = new ArrayList<String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FMAssetData>();
      performanceData = null;
   }

   public FMData(String theme, String level, Integer index,
                 String displayname, Integer sortorder,
                 Double lowRisk, Double highRisk,
                 Double expectedreturn, Double expectedrisk,
                 String description)
   {
      this.theme = theme;
      this.level = level;
      this.index = index;
      this.displayname = displayname;
      this.sortorder = sortorder;
      this.lowRisk = lowRisk;
      this.highRisk = highRisk;
      this.expectedreturn = expectedreturn;
      this.expectedrisk = expectedrisk;
      this.description = description;
      assetList = new ArrayList<String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FMAssetData>();
      performanceData = null;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getLevel()
   {
      return level;
   }

   public void setLevel(String level)
   {
      this.level = level;
   }

   public Integer getIndex()
   {
      return index;
   }

   public void setIndex(Integer index)
   {
      this.index = index;
   }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public Double getLowRisk()
   {
      return lowRisk;
   }

   public void setLowRisk(Double lowRisk)
   {
      this.lowRisk = lowRisk;
   }

   public Double getHighRisk()
   {
      return highRisk;
   }

   public void setHighRisk(Double highRisk)
   {
      this.highRisk = highRisk;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public Double getExpectedreturn()
   {
      return expectedreturn;
   }

   public void setExpectedreturn(Double expectedreturn)
   {
      this.expectedreturn = expectedreturn;
   }

   public Double getExpectedrisk()
   {
      return expectedrisk;
   }

   public void setExpectedrisk(Double expectedrisk)
   {
      this.expectedrisk = expectedrisk;
   }

   public Boolean isItThisTheme(Double riskIndex) {
      if (riskIndex > lowRisk  && riskIndex  <= highRisk)
         return true;
      else
         return false;
   }

   public Map<String, FMAssetData> getAsset()
   {
      return asset;
   }

   public void setAsset(Map<String, FMAssetData> asset)
   {
      this.asset = asset;
   }

   public FMProjection getPerformance()
   {
      return performanceData;
   }

   public void setPerformanceData(FMProjection performanceData) {
      this.performanceData = performanceData;
   }

   public ArrayList<String> getAssetList()
   {
      return assetList;
   }

   public void setAssetList(ArrayList<String> assetList)
   {
      this.assetList = assetList;
   }

   public Map<String, String> getIndexMap()
   {
      return indexMap;
   }

   public void setIndexMap(Map<String, String> indexMap)
   {
      this.indexMap = indexMap;
   }

   public void addAsset(FMAssetData assetdata) {
      try {
         if (assetdata != null) {
            if (assetdata.getTheme().toUpperCase().equals(theme.toUpperCase())) {
               String assetname = assetdata.getAsset();
               if (! asset.containsKey(assetname)) {
                  assetList.add(assetname);
                  asset.put(assetname, assetdata);
               }
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public void addAsset(Map<String,FMAssetData> assetMap) {
      try {
         if (assetMap != null) {
            for (FMAssetData asset : assetMap.values()) {
                  addAsset(asset);
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public ArrayList<FMAssetData> getAssetsData() {
      ArrayList<FMAssetData> arrayList = new ArrayList<FMAssetData>();
      if (getAsset() != null) {
         for (String asset: getAsset().keySet()) {
            arrayList.add(getAsset().get(asset));
         }
      }
      return arrayList;
   }

   public ArrayList<FMPortfolioData> getPortfolioData() {
      ArrayList<FMPortfolioData> arrayList = new ArrayList<FMPortfolioData>();
      if (getAsset() != null) {
         for (String asset: getAsset().keySet()) {
            if (getAsset().get(asset).getPortfolio() != null) {
               for (String portfolio: getAsset().get(asset).getPortfolio().keySet()) {
                  arrayList.add(getAsset().get(asset).getPortfolio().get(portfolio));
               }
            }
         }
      }
      return arrayList;
   }

   public ArrayList<FMProjectionData> getPerformanceData() {
      if (performanceData != null) {
         performanceData.getProjectionData(getLevel());
      }
      return null;
   }

   public ArrayList<String> getPerformanceIndex() {
      ArrayList<String> arrayList = new ArrayList<String>();
      if (getIndexMap() != null) {
         for (String key: getIndexMap().keySet()) {
            arrayList.add(key);
         }
      }
      return arrayList;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o)
      {
         return true;
      }
      if (o == null || getClass() != o.getClass())
      {
         return false;
      }

      FMData fmData = (FMData) o;

      if (theme != null ? !theme.equals(fmData.theme) : fmData.theme != null)
      {
         return false;
      }
      if (level != null ? !level.equals(fmData.level) : fmData.level != null)
      {
         return false;
      }
      if (index != null ? !index.equals(fmData.index) : fmData.index != null)
      {
         return false;
      }
      if (displayname != null ? !displayname.equals(fmData.displayname) : fmData.displayname != null)
      {
         return false;
      }
      if (description != null ? !description.equals(fmData.description) : fmData.description != null)
      {
         return false;
      }
      if (sortorder != null ? !sortorder.equals(fmData.sortorder) : fmData.sortorder != null)
      {
         return false;
      }
      if (lowRisk != null ? !lowRisk.equals(fmData.lowRisk) : fmData.lowRisk != null)
      {
         return false;
      }
      if (highRisk != null ? !highRisk.equals(fmData.highRisk) : fmData.highRisk != null)
      {
         return false;
      }
      if (expectedreturn != null ? !expectedreturn.equals(fmData.expectedreturn) : fmData.expectedreturn != null)
      {
         return false;
      }
      if (expectedrisk != null ? !expectedrisk.equals(fmData.expectedrisk) : fmData.expectedrisk != null)
      {
         return false;
      }
      if (assetList != null ? !assetList.equals(fmData.assetList) : fmData.assetList != null)
      {
         return false;
      }
      if (asset != null ? !asset.equals(fmData.asset) : fmData.asset != null)
      {
         return false;
      }
      if (indexMap != null ? !indexMap.equals(fmData.indexMap) : fmData.indexMap != null)
      {
         return false;
      }
      return performanceData != null ? performanceData.equals(fmData.performanceData) : fmData.performanceData == null;

   }

   @Override
   public int hashCode()
   {
      int result = theme != null ? theme.hashCode() : 0;
      result = 31 * result + (level != null ? level.hashCode() : 0);
      result = 31 * result + (index != null ? index.hashCode() : 0);
      result = 31 * result + (displayname != null ? displayname.hashCode() : 0);
      result = 31 * result + (description != null ? description.hashCode() : 0);
      result = 31 * result + (sortorder != null ? sortorder.hashCode() : 0);
      result = 31 * result + (lowRisk != null ? lowRisk.hashCode() : 0);
      result = 31 * result + (highRisk != null ? highRisk.hashCode() : 0);
      result = 31 * result + (expectedreturn != null ? expectedreturn.hashCode() : 0);
      result = 31 * result + (expectedrisk != null ? expectedrisk.hashCode() : 0);
      result = 31 * result + (assetList != null ? assetList.hashCode() : 0);
      result = 31 * result + (asset != null ? asset.hashCode() : 0);
      result = 31 * result + (indexMap != null ? indexMap.hashCode() : 0);
      result = 31 * result + (performanceData != null ? performanceData.hashCode() : 0);
      return result;
   }

/*
   public ArrayList<ArrayList<String>> getPrintedPerformanceData() {

      try {
         ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

         if (getPerformanceHeaderMap() != null) {
            ArrayList<String> data = new ArrayList<String>();
            data.add("");  // Space for Indexname
            for (String key: getPerformanceHeaderMap().keySet()) {
               data.add(key);
            }
            list.add(data);
         }

         // Now add each Index
         if (getIndexMap() != null) {
            for (String indexname: getIndexMap().keySet()) {
               ArrayList<String> data = new ArrayList<String>();
               if (getPerformanceHeaderMap() != null) {
                  data.add(indexname);
                  for (String header: getPerformanceHeaderMap().keySet()) {
                     String key = indexname + "." + header;
                     data.add(performance.get(key).getPerformance().toString());
                  }
                  list.add(data);
               }
            }
         }

         return list;
      }
      catch (Exception ex) {
         return null;
      }
   }
*/

/*
   public ArrayList<ArrayList<FMPerformancePrintData>> getPrintedPerformanceData() {
      ArrayList<ArrayList<FMPerformancePrintData>> arrayLists = new ArrayList<ArrayList<FMPerformancePrintData>>();
      ArrayList<FMPerformancePrintData> performanceList;
      int indexnum;
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            performanceList = new ArrayList<FMPerformancePrintData>();
            indexnum=0;
            for (String header: getPerformanceHeader()) {
               String key = getPerformanceKey(index, header);
               if (indexnum == 0) {
                  FMPerformancePrintData printData = new FMPerformancePrintData(null,index);
                  performanceList.add(printData);
               }
               String value = performance.get(key).getPerformance().toString();
               FMPerformancePrintData printData = new FMPerformancePrintData(header,value);
               performanceList.add(printData);
               indexnum++;
            }
            if (performanceList.size() > 0) {
               arrayLists.add(performanceList);
            }
         }
      }
      return arrayLists;
   }

   public ArrayList<FMPrefPrintData> getPrintedPerfData() {
      ArrayList<FMPrefPrintData> arrayLists = new ArrayList<FMPrefPrintData>();
      int indexnum;
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            indexnum=0;
            FMPrefPrintData prefData = new FMPrefPrintData();
            for (String header: getPerformanceHeader()) {
               String key = getPerformanceKey(index, header);
               if (indexnum == 0) {
                  prefData.addData(getPerformance().get(key).getIndexname(), getPerformance().get(key).getColor());
               }
               Double value = performance.get(key).getPerformance();
               prefData.addData(header, value, indexnum+1);
               indexnum++;
            }
            if (indexnum > 0) {
               arrayLists.add(prefData);
            }
         }
      }
      return arrayLists;
   }
*/
}
