package com.invmodel.ltam.dao;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;
import com.invmodel.ltam.data.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMDao  extends JdbcDaoSupport
{
      private static LTAMDao instance = null;
      DBConnectionProvider dbconnection;
      SQLData convert;
      DataSource ds;

   public static synchronized LTAMDao getInstance()
   {
      if (instance == null)
      {
         instance = new LTAMDao();
      }

      return instance;
   }

   private LTAMDao()
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new SQLData();
      ds = dbconnection.getMySQLDataSource();
   }

   public Map<String, LTAMTheme> loadLTAMThemes()
      {
         // DataSource ds = getDs();
         String storedProcName = "ltam.sel_themes";
         LTAMSP sp = new LTAMSP(ds, storedProcName, 1, 0);
         Map<String, LTAMTheme> themes = new LinkedHashMap<String, LTAMTheme>();

         Map outMap = sp.loadLTAMThemes();

         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            if (rows != null) {
               for (Map<String, Object> map : rows) {
                  Map rs = (Map) rows.get(i);
                  String theme = convert.getStrData(rs.get("theme"));
                  LTAMTheme themeData = new LTAMTheme(
                     theme,
                     convert.getStrData(rs.get("displayname")),
                     convert.getIntData(rs.get("sortorder")),
                     convert.getDoubleData(rs.get("gain")),
                     convert.getDoubleData(rs.get("loss")),
                     convert.getIntData(rs.get("lowRisk")),
                     convert.getIntData(rs.get("highRisk"))
                  );
                  themes.put(theme, themeData);
                  i++;
               }
            }
         }
         return themes;

      }

   public void loadLTAMAssets(Map<String, LTAMTheme> themeMap)
   {
      // DataSource ds = getDs();
      if (themeMap == null)
         return;

      String storedProcName = "ltam.sel_assets";
      LTAMSP sp = new LTAMSP(ds, storedProcName, 1, 0);
      Map outMap = sp.loadLTAMAsset();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               LTAMAsset asset = new LTAMAsset (
                  theme,
                  convert.getStrData(rs.get("asset")),
                  convert.getStrData(rs.get("displayname")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getStrData(rs.get("color")),
                  convert.getIntData(rs.get("sortorder"))
               );
               if (themeMap.containsKey(theme)) {
                  LTAMTheme themedata = themeMap.get(theme);
                  themedata.addAsset(asset);
               }
               i++;
            }
         }
      }
   }

   public void loadLTAMPortfolios(Map<String, LTAMTheme> themeMap)
   {

      if (themeMap == null)
         return;

      // DataSource ds = getDs();
      String storedProcName = "ltam.sel_subassets";
      LTAMSP sp = new LTAMSP(ds, storedProcName, 1, 0);

      Map outMap = sp.loadLTAMPortfolio();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               String asset = convert.getStrData(rs.get("asset"));
               LTAMPortfolio portfolio = new LTAMPortfolio (
                  theme,
                  convert.getStrData(rs.get("themename")),
                  asset,
                  convert.getStrData(rs.get("assetname")),
                  convert.getStrData(rs.get("subasset")),
                  convert.getStrData(rs.get("displayname")),
                  convert.getStrData(rs.get("color")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getIntData(rs.get("sortorder"))
               );
               if (themeMap.containsKey(theme)) {
                  if (themeMap.get(theme).getAsset().containsKey(asset)) {
                     themeMap.get(theme).getAsset().get(asset).addPortfolio(portfolio);
                  }
               }
               i++;
            }
         }
      }
   }

   public void loadLTAMPerformance(Map<String, LTAMTheme> themeMap)
   {

      if (themeMap == null)
         return;

      // DataSource ds = getDs();
      String storedProcName = "ltam.sel_performance";
      LTAMSP sp = new LTAMSP(ds, storedProcName, 1, 0);

      Map outMap = sp.loadLTAMPerformance();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               LTAMPerformance performance = new LTAMPerformance (
                  theme,
                  convert.getStrData(rs.get("index")),
                  convert.getStrData(rs.get("indexname")),
                  convert.getIntData(rs.get("sortorder")),
                  convert.getStrData(rs.get("displayYear")),
                  convert.getStrData(rs.get("color")),
                  convert.getDoubleData(rs.get("performance"))
               );
               if (themeMap.containsKey(theme)) {
                  themeMap.get(theme).addPerformance(performance);
               }
               i++;
            }
         }
      }
   }
}
