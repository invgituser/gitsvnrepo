package com.invessence.data.advisor;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/29/15
 * Time: 8:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class AdvisorTheme
{
   Map<String, ArrayList<AssetData>> assetdataMap;
   Map<String, ArrayList<PrimeAssetData>> primeassetdataMap;

   Map<String, String> theme;

   public AdvisorTheme()
   {
      assetdataMap = new HashMap<String, ArrayList<AssetData>>();
      primeassetdataMap = new HashMap<String, ArrayList<PrimeAssetData>>();
      theme = new HashMap<String, String>();
   }

   public Map<String, ArrayList<AssetData>> getAssetdataMap()
   {
      return assetdataMap;
   }

   public Map<String, ArrayList<PrimeAssetData>> getPrimeassetdataMap()
   {
      return primeassetdataMap;
   }

   public ArrayList<AssetData> getAssetdata(String theme, Boolean taxable)
   {
      if (theme == null)
      {
         return null;
      }

      if (assetdataMap.containsKey(theme))
      {
         return assetdataMap.get(theme);
      }
      else
      {
         return null;
      }
   }

   public ArrayList<PrimeAssetData> getPrimeassetdata(String theme, Boolean taxable)
   {
      if (theme == null)
      {
         return null;
      }

      if (primeassetdataMap.containsKey(theme))
      {
         return primeassetdataMap.get(theme);
      }
      else
      {
         return null;
      }
   }

   public Map<String, String> getTheme()
   {
      return theme;
   }

   public Map<String, String> getTheme(Boolean taxable)
   {
      Map<String, String> filteredTheme = null;
      if (taxable != null) {
         filteredTheme = new HashMap<String, String>();
         for (String key: theme.keySet()) {
            if (taxable) {
               if (key.toUpperCase().startsWith("T"))
                  filteredTheme.put(key, theme.get(key));
            }
            else {
               if (! key.toUpperCase().startsWith("T")) {
               filteredTheme.put(key, theme.get(key));
               }
            }
         }
      }
      return filteredTheme;
   }

   public void addAssetData(String theme, String themename,
                            String status, String assetclass,
                            String displayName, String indexticker,
                            Integer sortorder, Double lowerbound,
                            Double upperbound, Double endAllocation,
                            Double riskAdjustment, String color)
   {

      if (assetdataMap.containsKey(theme))
      {
         AssetData asset = new AssetData(theme, themename,
                                         status, assetclass,
                                         displayName, indexticker,
                                         sortorder, lowerbound,
                                         upperbound, endAllocation,
                                         riskAdjustment, color);
         assetdataMap.get(theme).add(asset);
      }
      else
      {
         ArrayList<AssetData> assetlist = new ArrayList<AssetData>();
         AssetData asset = new AssetData(theme, themename,
                                         status, assetclass,
                                         displayName, indexticker,
                                         sortorder, lowerbound,
                                         upperbound, endAllocation,
                                         riskAdjustment, color);
         assetlist.add(asset);
         assetdataMap.put(theme, assetlist);
      }

      if (! this.theme.containsKey(theme)) {
         this.theme.put(theme, themename);
      }

   }

   public void addPrimeAssetData(String theme, String assetclass, String primeassetclass,
                                 String ticker, String active, Integer sortorder,
                                 Double lowerbound, Double upperbound, Double expectedReturn)
   {

      if (primeassetdataMap.containsKey(theme))
      {
         PrimeAssetData primeasset = new PrimeAssetData(theme, assetclass, primeassetclass,
                                                        ticker, active, sortorder,
                                                        lowerbound, upperbound, expectedReturn);
         primeassetdataMap.get(theme).add(primeasset);
      }
      else
      {
         ArrayList<PrimeAssetData> primeassetlist = new ArrayList<PrimeAssetData>();
         PrimeAssetData primeasset = new PrimeAssetData(theme, assetclass, primeassetclass,
                                                        ticker, active, sortorder,
                                                        lowerbound, upperbound, expectedReturn);
         primeassetlist.add(primeasset);
         primeassetdataMap.put(theme, primeassetlist);
      }

   }
}
