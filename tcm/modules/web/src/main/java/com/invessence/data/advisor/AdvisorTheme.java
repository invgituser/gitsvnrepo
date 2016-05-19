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

   Map<String, String> themeMap;
   Map<String, String> assetnamesMap;
   Map<String, String> primeassetnameMap;
   Map<String, String> tickerMap;

   public AdvisorTheme()
   {
      assetdataMap = new HashMap<String, ArrayList<AssetData>>();
      primeassetdataMap = new HashMap<String, ArrayList<PrimeAssetData>>();
      themeMap = new HashMap<String, String>();
      assetnamesMap = new HashMap<String, String>();
      primeassetnameMap = new HashMap<String, String>();
      tickerMap = new HashMap<String, String>();
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
      return themeMap;
   }

   public Map<String, String> getTheme(Boolean taxable)
   {
      Map<String, String> filteredThemeMap = null;
      if (taxable != null) {
         filteredThemeMap = new HashMap<String, String>();
         for (String key: themeMap.keySet()) {
            if (taxable) {
               if (key.toUpperCase().startsWith("T"))
                  filteredThemeMap.put(key, themeMap.get(key));
            }
            else {
               if (! key.toUpperCase().startsWith("T")) {
                  filteredThemeMap.put(key, themeMap.get(key));
               }
            }
         }
      }
      return filteredThemeMap;
   }

   public Map<String, String> getAssetnamesMap()
   {
      return assetnamesMap;
   }

   public Map<String, String> getPrimeassetnameMap()
   {
      return primeassetnameMap;
   }

   public Map<String, String> getTickerMap()
   {
      return tickerMap;
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

      if (! this.themeMap.containsKey(theme)) {
         this.themeMap.put(theme, themename);
      }

      if (! assetnamesMap.containsKey(assetclass))
         assetnamesMap.put(assetclass, displayName);


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

      if (! primeassetnameMap.containsKey(primeassetclass)) {
         primeassetnameMap.put(primeassetclass, primeassetclass);
      }

      if (! tickerMap.containsKey(ticker))
         tickerMap.put(ticker, ticker);

   }
}
