package com.invmodel.ltam;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.ltam.dao.LTAMDao;
import com.invmodel.ltam.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMOptimizer
{
   private static LTAMOptimizer instance = null;
   private final Logger logger = Logger.getLogger(LTAMOptimizer.class.getName());

   private Map<String, LTAMTheme> themesMap;
   private LTAMDao ltamdao;

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();


   public static synchronized LTAMOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new LTAMOptimizer();
      }

      return instance;
   }

   private LTAMOptimizer()
   {
      super();
      ltamdao = LTAMDao.getInstance();
   }

   private String getThemeKey(String theme) {
       if (theme == null)
          return "DEFAULT";
      else
          return theme.toUpperCase();
   }

   private String getAssetKey(String theme, String asset) {
      if (asset == null)
         asset = "DEFAULT";

      return (getThemeKey(theme) + "." + asset.toUpperCase());
   }

   private String getSubAssetKey(String theme, String asset, String subasset) {
      if (subasset == null)
         subasset = "DEFAULT";

      return (getAssetKey(theme, asset) + "." + subasset.toUpperCase());
   }

   public void refreshDataFromDB() {
      write.lock();
      try {
         logger.info("Load LTAM Themes");
         themesMap = ltamdao.loadLTAMThemes();
         logger.info("Load LTAM Assets");
         ltamdao.loadLTAMAssets(themesMap);
         logger.info("Load LTAM SubAsset");
         ltamdao.loadLTAMPortfolios(themesMap);
         logger.info("Load LTAM Performance Data");
         ltamdao.loadLTAMPerformance(themesMap);
      }
      catch (Exception ex) {

      }
      finally
      {
         write.unlock();
      }

   }

   public ArrayList<LTAMTheme> getThemes() {
      ArrayList<LTAMTheme> arrayList = new ArrayList<LTAMTheme>();
      if (themesMap != null) {
         for (String theme: themesMap.keySet()) {
            arrayList.add(themesMap.get(theme));
         }
      }
      return arrayList;
   }

   public ArrayList<LTAMAsset> getAssets(String theme) {
      ArrayList<LTAMAsset> arrayList = new ArrayList<LTAMAsset>();
      if (theme != null) {
         if (themesMap != null) {
            if (themesMap.get(theme).getAsset() != null) {
               for (String asset: themesMap.get(theme).getAsset().keySet()) {
                  arrayList.add(themesMap.get(theme).getAsset().get(asset));
               }
            }
         }

      }
      return arrayList;
   }

   public ArrayList<LTAMPortfolio> getPortfolios(String theme) {
      ArrayList<LTAMPortfolio> arrayList = new ArrayList<LTAMPortfolio>();
      if (theme != null) {
         if (themesMap != null) {
            if (themesMap.get(theme).getAsset() != null) {
               for (String asset: themesMap.get(theme).getAsset().keySet()) {
                  if (themesMap.get(theme).getAsset().get(asset).getPortfolio() != null) {
                     for (String portfolio: themesMap.get(theme).getAsset().get(asset).getPortfolio().keySet()) {
                        arrayList.add(themesMap.get(theme).getAsset().get(asset).getPortfolio().get(portfolio));
                     }

                  }
               }
            }
         }
      }
      return arrayList;
   }

   public ArrayList<LTAMPerformance> getPerformanceData(String theme) {
      ArrayList<LTAMPerformance> arrayList = new ArrayList<LTAMPerformance>();
      if (theme != null) {
         if (themesMap != null) {
            if (themesMap.get(theme).getPerformance() != null) {
               for (String key: themesMap.get(theme).getPerformance().keySet()) {
                  arrayList.add(themesMap.get(theme).getPerformance().get(key));
               }
            }
         }
      }
      return arrayList;
   }

   public ArrayList<String> getPerformanceIndex(String theme) {
      ArrayList<String> arrayList = new ArrayList<String>();
      if (theme != null) {
         if (themesMap != null) {
            if (themesMap.get(theme).getIndexMap() != null) {
               for (String key: themesMap.get(theme).getIndexMap().keySet()) {
                  arrayList.add(key);
               }
            }
         }
      }
      return arrayList;
   }

   public ArrayList<String> getPerformanceHeader(String theme) {
      ArrayList<String> arrayList = new ArrayList<String>();
      if (theme != null) {
         if (themesMap != null) {
            if (themesMap.get(theme).getPerformanceHeaderMap() != null) {
               for (String key: themesMap.get(theme).getPerformanceHeaderMap().keySet()) {
                  arrayList.add(key);
               }
            }
         }
      }
      return arrayList;
   }
}
