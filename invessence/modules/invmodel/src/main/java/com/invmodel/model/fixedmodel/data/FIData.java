package com.invmodel.model.fixedmodel.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class FIData
{
   private String theme;
   private String level;
   private String displayname;
   private Integer sortorder;
   private Integer lowRisk;
   private Integer highRisk;
   ArrayList<String> assetList;
   Map<String, FIAsset> asset;
   Map<String,String> indexMap;
   Map<String,String> performanceHeaderMap;
   Map<String, FIPerformance> performance;

   public FIData()
   {
      assetList = new ArrayList<String>();
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FIAsset>();
      performance = new LinkedHashMap<String, FIPerformance>();
   }

   public FIData(String theme, String level, String displayname, Integer sortorder,
                 Integer lowRisk, Integer highRisk)
   {
      this.theme = theme;
      this.level = level;
      this.displayname = displayname;
      this.sortorder = sortorder;
      this.lowRisk = lowRisk;
      this.highRisk = highRisk;
      assetList = new ArrayList<String>();
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, FIAsset>();
      performance = new LinkedHashMap<String, FIPerformance>();
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

   public Integer getLowRisk()
   {
      return lowRisk;
   }

   public void setLowRisk(Integer lowRisk)
   {
      this.lowRisk = lowRisk;
   }

   public Integer getHighRisk()
   {
      return highRisk;
   }

   public void setHighRisk(Integer highRisk)
   {
      this.highRisk = highRisk;
   }

   public Boolean isThisTheme(Integer riskIndex) {
      if (riskIndex >= lowRisk  && riskIndex  <= highRisk)
         return true;
      else
         return false;
   }

   public Map<String, FIAsset> getAsset()
   {
      return asset;
   }

   public void setAsset(Map<String, FIAsset> asset)
   {
      this.asset = asset;
   }

   public Map<String, FIPerformance> getPerformance()
   {
      return performance;
   }

   public void setPerformance(Map<String, FIPerformance> performance)
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

   public void addAsset(FIAsset assetdata) {
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

   public void addAsset(Map<String,FIAsset> assetMap) {
      try {
         if (assetMap != null) {
            for (FIAsset asset : assetMap.values()) {
                  addAsset(asset);
            }
         }
      }
      catch (Exception ex) {

      }
   }


   public void addPerformance(FIPerformance performancedata) {
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

   public void addPerformance(Map<String, FIPerformance> performanceMap) {
      try {
         if (performanceMap != null) {
            for (FIPerformance performance: performanceMap.values()) {
                  addPerformance(performance);
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public ArrayList<FIAsset> getAssetsData() {
      ArrayList<FIAsset> arrayList = new ArrayList<FIAsset>();
      if (getAsset() != null) {
         for (String asset: getAsset().keySet()) {
            arrayList.add(getAsset().get(asset));
         }
      }
      return arrayList;
   }

   public ArrayList<FIPortfolio> getPortfolioData() {
      ArrayList<FIPortfolio> arrayList = new ArrayList<FIPortfolio>();
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

   public Map<String,ArrayList<FIPerformance>> getPerformanceData() {
      Map<String, ArrayList<FIPerformance>> arrayMap = new HashMap<String, ArrayList<FIPerformance>>();
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            ArrayList<FIPerformance> performanceList = new ArrayList<FIPerformance>();
            for (String header: getPerformanceHeader()) {
               String key = getPerformanceKey(index, header);
               FIPerformance perfdata = performance.get(key);
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

   public ArrayList<ArrayList<FIPerformancePrintData>> getPrintedPerformanceData() {
      ArrayList<ArrayList<FIPerformancePrintData>> arrayLists = new ArrayList<ArrayList<FIPerformancePrintData>>();
      ArrayList<FIPerformancePrintData> performanceList;
      int indexnum;
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            performanceList = new ArrayList<FIPerformancePrintData>();
            indexnum=0;
            for (String header: getPerformanceHeader()) {
               String key = getPerformanceKey(index, header);
               if (indexnum == 0) {
                  FIPerformancePrintData printData = new FIPerformancePrintData(null,index);
                  performanceList.add(printData);
               }
               String value = performance.get(key).getPerformance().toString();
               FIPerformancePrintData printData = new FIPerformancePrintData(header,value);
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

   public ArrayList<FIPrefPrintData> getPrintedPerfData() {
      ArrayList<FIPrefPrintData> arrayLists = new ArrayList<FIPrefPrintData>();
      int indexnum;
      if (getPerformance() != null) {
         for (String index : getPerformanceIndex()) {
            indexnum=0;
            FIPrefPrintData prefData = new FIPrefPrintData();
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
