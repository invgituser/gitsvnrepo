package com.invmodel.dao.invdb;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;
import com.invmodel.model.fixedmodel.data.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/27/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class FixedModelDao extends JdbcDaoSupport
{
      private static FixedModelDao instance = null;
      DBConnectionProvider dbconnection;
      SQLData convert;
      DataSource ds;

   public static synchronized FixedModelDao getInstance()
   {
      if (instance == null)
      {
         instance = new FixedModelDao();
      }

      return instance;
   }

   private FixedModelDao()
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new SQLData();
      ds = dbconnection.getMySQLDataSource();
   }

   public Map<String, FIData> load_fixedmodule()
      {
         // DataSource ds = getDs();
         String storedProcName = "sel_fixedmodule";
         FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);
         Map<String, FIData> themes = new LinkedHashMap<String, FIData>();

         Map outMap = sp.loadLTAMThemes();

         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            if (rows != null) {
               for (Map<String, Object> map : rows) {
                  Map rs = (Map) rows.get(i);
                  String theme = convert.getStrData(rs.get("theme"));
                  FIData themeData = new FIData(
                     theme,
                     convert.getStrData(rs.get("level")),
                     convert.getStrData(rs.get("displayname")),
                     convert.getIntData(rs.get("sortorder")),
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

   public void load_fixedmodule_assets(Map<String, FIData> themeMap)
   {
      // DataSource ds = getDs();
      if (themeMap == null)
         return;

      String storedProcName = "sel_fixedmodule_assets";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);
      Map outMap = sp.loadLTAMAsset();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               FIAsset asset = new FIAsset(
                  theme,
                  convert.getStrData(rs.get("asset")),
                  convert.getStrData(rs.get("displayname")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getStrData(rs.get("color")),
                  convert.getIntData(rs.get("sortorder"))
               );
               if (themeMap.containsKey(theme)) {
                  FIData themedata = themeMap.get(theme);
                  themedata.addAsset(asset);
               }
               i++;
            }
         }
      }
   }

   public void load_fixedmodule_subassets(Map<String, FIData> themeMap)
   {

      if (themeMap == null)
         return;

      // DataSource ds = getDs();
      String storedProcName = "sel_fixedmodule_subassets";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);

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
               FIPortfolio portfolio = new FIPortfolio(
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

   public void load_fixedmodule_performance(Map<String, FIData> themeMap)
   {

      if (themeMap == null)
         return;

      // DataSource ds = getDs();
      String storedProcName = "sel_fixedmodule_performance";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);

      Map outMap = sp.loadLTAMPerformance();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               FIPerformance performance = new FIPerformance(
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
