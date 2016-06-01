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
   Map<String, FMAsset> asset;
   Map<String,String> indexMap;
   Map<String,String> performanceHeaderMap;
   Map<String, FMPerformance> performance;

   public FMData()
   {
      assetList = new ArrayList<String>();
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FMAsset>();
      performance = new LinkedHashMap<String, FMPerformance>();
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
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FMAsset>();
      performance = new LinkedHashMap<String, FMPerformance>();
   }

   public String getPerformanceKey(String index, String header) {
      return index + "." + header;
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

   public Boolean isItThisTheme(Integer riskIndex) {
      if (riskIndex >= lowRisk  && riskIndex  <= highRisk)
         return true;
      else
         return false;
   }

   public Map<String, FMAsset> getAsset()
   {
      return asset;
   }

   public void setAsset(Map<String, FMAsset> asset)
   {
      this.asset = asset;
   }

   public Map<String, FMPerformance> getPerformance()
   {
      return performance;
   }

   public void setPerformance(Map<String, FMPerformance> performance)
   {
      this.performance = performance;
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

   public Map<String, String> getPerformanceHeaderMap()
   {
      return performanceHeaderMap;
   }

   public void setPerformanceHeaderMap(Map<String, String> performanceHeaderMap)
   {
      this.performanceHeaderMap = performanceHeaderMap;
   }

   public void addAsset(FMAsset assetdata) {
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

   public void addAsset(Map<String,FMAsset> assetMap) {
      try {
         if (assetMap != null) {
            for (FMAsset asset : assetMap.values()) {
                  addAsset(asset);
            }
         }
      }
      catch (Exception ex) {

      }
   }


   public void addPerformance(FMPerformance performancedata) {
      try {
         if (performancedata != null) {
            if (performancedata.getTheme().toUpperCase().equals(theme.toUpperCase())) {
               String index = performancedata.getIndex();
               String header = performancedata.getYearname();
               if (! performanceHeaderMap.containsKey(header))
                  performanceHeaderMap.put(header, header);
               if (! indexMap.containsKey(index))
                  indexMap.put(index, index);
               String key = getPerformanceKey(index,header);
               if (! performance.containsKey(key)) {
                  performance.put(key, performancedata);
               }
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public void addPerformance(Map<String, FMPerformance> performanceMap) {
      try {
         if (performanceMap != null) {
            for (FMPerformance performance: performanceMap.values()) {
                  addPerformance(performance);
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public ArrayList<FMAsset> getAssetsData() {
      ArrayList<FMAsset> arrayList = new ArrayList<FMAsset>();
      if (getAsset() != null) {
         for (String asset: getAsset().keySet()) {
            arrayList.add(getAsset().get(asset));
         }
      }
      return arrayList;
   }

   public ArrayList<FMPortfolio> getPortfolioData() {
      ArrayList<FMPortfolio> arrayList = new ArrayList<FMPortfolio>();
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

   public Map<String,ArrayList<FMPerformance>> getPerformanceData() {
      Map<String, ArrayList<FMPerformance>> arrayMap = new HashMap<String, ArrayList<FMPerformance>>();
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            ArrayList<FMPerformance> performanceList = new ArrayList<FMPerformance>();
            for (String header: getPerformanceHeader()) {
               String key = getPerformanceKey(index, header);
               FMPerformance perfdata = performance.get(key);
               performanceList.add(perfdata);
            }
            if (performanceList.size() > 0) {
               arrayMap.put(index, performanceList);
            }
         }
      }
      return arrayMap;
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

   public ArrayList<String> getPerformanceHeader() {
      ArrayList<String> arrayList = new ArrayList<String>();
      if (getPerformanceHeaderMap() != null) {
         for (String key: getPerformanceHeaderMap().keySet()) {
            arrayList.add(key);
         }
      }
      return arrayList;
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
}
