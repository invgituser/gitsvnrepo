package com.invmodel.model.fixedmodel;

import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.dao.invdb.FixedModelDao;
import com.invmodel.model.fixedmodel.data.*;
import com.invmodel.performance.FMProjectionReport;

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

   private Map<String, ArrayList<FMData>> fixedThemeMap;
   private Map<String, FMData> themesMap;
   private FixedModelDao fixedModelDao;
   private FMProjectionReport projectionReport;
   private Map<String, Boolean> canCreateProjection;
   private Map<String,FMProjection> fmprojection;

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
      fixedModelDao = FixedModelDao.getInstance();
      projectionReport = new FMProjectionReport();
      fmprojection = null;
   }

   private void allocateTheme() {
      if (fixedThemeMap == null) {
         fixedThemeMap = new HashMap<String, ArrayList<FMData>>();
      }

      if (themesMap == null) {
         themesMap = new HashMap<String, FMData>();
      }
   }
   private String getThemeKey(String theme) {
       if (theme == null)
          return "FIXED";
      else
          return theme.toUpperCase();
   }

   private String getAssetKey(String theme, String asset) {
      if (asset == null)
         asset = "FIXED";

      return (getThemeKey(theme) + "." + asset.toUpperCase());
   }

   private String getSubAssetKey(String theme, String asset, String subasset) {
      if (subasset == null)
         subasset = "FIXED";

      return (getAssetKey(theme, asset) + "." + subasset.toUpperCase());
   }

   public FMProjectionReport getProjectionReport()
   {
      return projectionReport;
   }

   public Boolean canCreateProjection(String theme)
   {
      if (canCreateProjection == null)
         return false;

      if (theme == null)
         return false;

      if (canCreateProjection.containsKey(theme.toUpperCase()))
         return true;

      return false;
   }

   public void refreshDataFromDB() {
      write.lock();
      try {
         allocateTheme();
         fixedThemeMap.clear();
         logger.info("Load Fixed Module");
         fixedModelDao.load_fixedmodule(instance);
         logger.info("Load Fixed Module Assets");
         fixedModelDao.load_fixedmodule_assets(themesMap);
         logger.info("Load Fixed Module SubAsset");
         fixedModelDao.load_fixedmodule_subassets(themesMap);
         fmprojection = fixedModelDao.load_fixedmodule_projection();
         // logger.info("Load Fixed Module Projection Data");
         // fixedModelDao.load_fixedmodule_performance(themesMap);
         canDoProjection();


      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      finally
      {
         write.unlock();
      }

   }

   private void canDoProjection()
   {
      canCreateProjection = new HashMap<String, Boolean>();
         for (String theme : getListOfThemes())
         {
            Boolean cando = true;
            for (FMData data : getThemes(theme))
            {
               if (data.getExpectedreturn() == null || data.getExpectedrisk() == null)
               {
                  cando = false;
               }
               else if (data.getExpectedreturn() == 0.0 || data.getExpectedrisk() == 0.0)
               {
                  cando = false;
               }
            }
            canCreateProjection.put(theme, cando);
         }
   }

   public Boolean isThisFixedTheme(String theme) {
      if (fixedThemeMap == null)
         return false;
      if (theme == null)
         return false;
      if (fixedThemeMap.containsKey(theme.toUpperCase()))
         return true;
      else
         return false;
   }

   public ArrayList<String> getListOfThemes() {
      ArrayList<String> arrayList = new ArrayList<String>();
      if (fixedThemeMap != null) {
         for (String themename: fixedThemeMap.keySet()) {
            arrayList.add(themename);
         }
      }
      return arrayList;
   }

   public ArrayList<FMData> getAllThemes() {
      ArrayList<FMData> arrayList = new ArrayList<FMData>();
      if (themesMap != null) {
         for (FMData data: themesMap.values()) {
               arrayList.add(data);
         }
      }
      return arrayList;
   }

   public ArrayList<FMData> getThemePortfolios(String theme) {
      return getThemes(theme);
   }


   public ArrayList<FMData> getThemes(String theme) {
      ArrayList<FMData> arrayList = new ArrayList<FMData>();
      if (theme != null) {
         String localTheme = theme.toUpperCase();
         if (isThisFixedTheme(localTheme)) {
            for (FMData data: fixedThemeMap.get(localTheme)) {
               arrayList.add(data);
            }
         }
      }
      return arrayList;
   }

   public FMData getTheme(String theme, Double riskIndex) {
      FMData thisTheme = null;
      if (theme != null) {
         String localTheme = theme.toUpperCase();
         if (fixedThemeMap != null) {
            for (FMData data: fixedThemeMap.get(localTheme)) {
               if (data.isItThisTheme(riskIndex)) {
                  thisTheme = data;
                  break;
               }
            }
         }
      }
      return thisTheme;
   }

   public FMData getThemeByIndex(String theme, Integer pos) {
      FMData thisTheme = null;
      if (theme != null) {
         String localTheme = theme.toUpperCase();
         if (fixedThemeMap != null) {
            if (pos > getThemes(localTheme).size()) {
               thisTheme = getThemes(localTheme).get(0);
            }
            else {
               thisTheme = getThemes(localTheme).get(pos);
            }
         }
      }
      return thisTheme;
   }

   public FMData getThemeByPortfolioName(String theme, String portfolioName) {
      if (theme != null) {
         if (fixedThemeMap != null) {
            for(FMData fmData: getThemes(theme))
            {
               if(fmData.getDisplayname().equalsIgnoreCase(portfolioName))
                  return fmData;
            }
         }
      }
      return null;
   }

   public void addTheme(FMData FMData) {
      try {
         if (FMData != null) {
            String themeName = FMData.getTheme().toUpperCase();
            String themeLevel = FMData.getLevel().toUpperCase();

            allocateTheme();

            if (fixedThemeMap.containsKey(themeName)) {
               Integer last = fixedThemeMap.get(themeName).size();
               FMData.setIndex(last);  // Index is from 0 to x-1
               fixedThemeMap.get(themeName).add(FMData);
            }
            else {
               ArrayList<FMData> data = new ArrayList<FMData>();
               FMData.setIndex(0);
               data.add(FMData);
               fixedThemeMap.put(themeName,data);
            }

            themesMap.put(themeName + "." + themeLevel, FMData);
         }
      }
      catch (Exception ex) {

      }
   }

   public Map<String, ArrayList<FMProjectionData>> getFmprojection(String theme)
   {
      if (theme != null) {
         if (fmprojection.containsKey(theme)) {
            return fmprojection.get(theme).getData();
         }
      }
      return null;
   }
}
