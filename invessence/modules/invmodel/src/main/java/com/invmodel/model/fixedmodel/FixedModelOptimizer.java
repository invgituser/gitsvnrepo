package com.invmodel.model.fixedmodel;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.dao.invdb.FixedModelDao;
import com.invmodel.model.fixedmodel.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixedModelOptimizer
{
   private static FixedModelOptimizer instance = null;
   private final Logger logger = Logger.getLogger(FixedModelOptimizer.class.getName());

   private Map<String, FIData> themesMap;
   private FixedModelDao ltamdao;

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();


   public static synchronized FixedModelOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new FixedModelOptimizer();
      }

      return instance;
   }

   private FixedModelOptimizer()
   {
      super();
      ltamdao = FixedModelDao.getInstance();
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
         logger.info("Load Fixed Module");
         themesMap = ltamdao.load_fixedmodule();
         logger.info("Load Fixed Module Assets");
         ltamdao.load_fixedmodule_assets(themesMap);
         logger.info("Load Fixed Module SubAsset");
         ltamdao.load_fixedmodule_subassets(themesMap);
         logger.info("Load Fixed Module Performance Data");
         ltamdao.load_fixedmodule_performance(themesMap);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      finally
      {
         write.unlock();
      }

   }

   public ArrayList<FIData> getThemes() {
      ArrayList<FIData> arrayList = new ArrayList<FIData>();
      if (themesMap != null) {
         for (String theme: themesMap.keySet()) {
            arrayList.add(themesMap.get(theme));
         }
      }
      return arrayList;
   }

   public FIData getTheme(Integer riskIndex) {
      FIData thisTheme = null;
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

}
