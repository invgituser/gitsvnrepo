package com.invmodel.ltam.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMTheme
{
   private String theme;
   private String displayname;
   private Integer sortorder;
   private Double gain;
   private Double loss;
   ArrayList<String> assetList;
   Map<String, LTAMAsset> asset;
   Map<String,String> indexMap;
   Map<String,String> performanceHeaderMap;
   Map<String, LTAMPerformance> performance;

   public LTAMTheme()
   {
      assetList = new ArrayList<String>();
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, LTAMAsset>();
      performance = new LinkedHashMap<String, LTAMPerformance>();
   }

   public LTAMTheme(String theme, String displayname, Integer sortorder, Double gain, Double loss)
   {
      this.theme = theme;
      this.displayname = displayname;
      this.sortorder = sortorder;
      this.gain = gain;
      this.loss = loss;
      assetList = new ArrayList<String>();
      performanceHeaderMap = new LinkedHashMap<String, String>();
      indexMap = new LinkedHashMap<String, String>();
      asset = new LinkedHashMap<String, LTAMAsset>();
      performance = new LinkedHashMap<String, LTAMPerformance>();
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
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

   public Double getGain()
   {
      return gain;
   }

   public void setGain(Double gain)
   {
      this.gain = gain;
   }

   public Double getLoss()
   {
      return loss;
   }

   public void setLoss(Double loss)
   {
      this.loss = loss;
   }

   public Map<String, LTAMAsset> getAsset()
   {
      return asset;
   }

   public void setAsset(Map<String, LTAMAsset> asset)
   {
      this.asset = asset;
   }

   public Map<String, LTAMPerformance> getPerformance()
   {
      return performance;
   }

   public void setPerformance(Map<String, LTAMPerformance> performance)
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

   public void addAsset(LTAMAsset assetdata) {
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

   public void addAsset(Map<String,LTAMAsset> assetMap) {
      try {
         if (assetMap != null) {
            for (LTAMAsset asset : assetMap.values()) {
                  addAsset(asset);
            }
         }
      }
      catch (Exception ex) {

      }
   }


   public void addPerformance(LTAMPerformance performancedata) {
      try {
         if (performancedata != null) {
            if (performancedata.getTheme().toUpperCase().equals(theme.toUpperCase())) {
               String index = performancedata.getIndex();
               String header = performancedata.getYearname();
               if (! performanceHeaderMap.containsKey(header))
                  performanceHeaderMap.put(header, header);
               if (! indexMap.containsKey(index))
                  indexMap.put(index, index);
               String key = index + "." + header;
               if (! performance.containsKey(key)) {
                  performance.put(key, performancedata);
               }
            }
         }
      }
      catch (Exception ex) {

      }
   }

   public void addPerformance(Map<String, LTAMPerformance> performanceMap) {
      try {
         if (performanceMap != null) {
            for (LTAMPerformance performance: performanceMap.values()) {
                  addPerformance(performance);
            }
         }
      }
      catch (Exception ex) {

      }
   }
}
