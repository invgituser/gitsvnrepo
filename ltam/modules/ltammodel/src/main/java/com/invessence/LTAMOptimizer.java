package com.invessence;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invessence.dao.LTAMDao;
import com.invessence.data.LTAMTheme;

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
         ex.printStackTrace();
      }
      finally
      {
         write.unlock();
      }

   }

   public Map<String, String> getDisplayThemes() {
      Map<String, String> map = new HashMap<String, String>();
      if (themesMap != null) {
         for (String theme: themesMap.keySet()) {
            map.put(theme, themesMap.get(theme).getDisplayname());
         }
      }
      return map;
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

   public LTAMTheme getTheme(Double riskIndex) {
      LTAMTheme thisTheme = null;
      if (themesMap != null) {
         for (String theme: themesMap.keySet()) {
            if (themesMap.get(theme).isThisTheme(riskIndex)) {
               thisTheme = themesMap.get(theme);
               break;
            }
         }
      }
      return thisTheme;
   }

   public LTAMTheme getTheme(String themename) {
      LTAMTheme thisTheme = null;
      if (themesMap != null) {
         if (themesMap.containsKey(themename)) {
            thisTheme =  themesMap.get(themename);
         }

      }
      return thisTheme;
   }
}
