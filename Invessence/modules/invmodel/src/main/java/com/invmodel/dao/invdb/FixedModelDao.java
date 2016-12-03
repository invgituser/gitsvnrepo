package com.invmodel.dao.invdb;

import java.util.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.*;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
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

   public void load_fixedmodule(FixedModelOptimizer fmmodel)
      {
         // DataSource ds = getDs();
         String storedProcName = "sel_sec_fixedmodel";
         FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);

         Map outMap = sp.loadThemes();

         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            int i = 0;
            if (rows != null) {
               for (Map<String, Object> map : rows) {
                  Map rs = (Map) rows.get(i);
                  String theme = convert.getStrData(rs.get("theme"));
                  FMData themeData = new FMData(
                     theme,
                     convert.getStrData(rs.get("level")),
                     0, // During adding in buffer, we are calculalating the actual value
                     convert.getStrData(rs.get("displayname")),
                     convert.getIntData(rs.get("sortorder")),
                     convert.getDoubleData(rs.get("lowRisk")),
                     convert.getDoubleData(rs.get("highRisk")),
                     convert.getDoubleData(rs.get("expectedreturn")),
                     convert.getDoubleData(rs.get("expectedrisk")),
                     convert.getStrData(rs.get("description"))
                  );
                  fmmodel.addTheme(themeData);
                  i++;
               }
            }
         }
      }

   public void load_fixedmodule_assets(Map<String, FMData> themeMap)
   {
      // DataSource ds = getDs();
      if (themeMap == null)
         return;

      String storedProcName = "sel_sec_fixedmodel_assets";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);
      Map outMap = sp.loadAsset();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               String level = convert.getStrData(rs.get("level"));
               String key =  theme.toUpperCase() + "." + level;
               FMAssetData asset = new FMAssetData(
                  theme,
                  level,
                  convert.getStrData(rs.get("asset")),
                  convert.getStrData(rs.get("assetname")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getStrData(rs.get("color")),
                  convert.getIntData(rs.get("sortorder"))
               );
               if (themeMap.containsKey(key)) {
                  FMData themedata = themeMap.get(key);
                  themedata.addAsset(asset);
               }
               i++;
            }
         }
      }
   }

   public void load_fixedmodule_subassets(Map<String, FMData> themeMap)
   {

      if (themeMap == null)
         return;

      // DataSource ds = getDs();
      String storedProcName = "sel_sec_fixedmodel_subassets";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);

      Map outMap = sp.loadPortfolio();

      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               String level = convert.getStrData(rs.get("level"));
               String key =  theme.toUpperCase() + "." + level;
               String asset = convert.getStrData(rs.get("asset"));
               FMPortfolioData portfolio = new FMPortfolioData(
                  theme,
                  level,
                  theme,
                  asset,
                  convert.getStrData(rs.get("assetname")),       // assetname
                  convert.getStrData(rs.get("keyname")),         // ticker
                  convert.getStrData(rs.get("name")),            // name
                  convert.getStrData(rs.get("keydescription")),  // subasset
                  convert.getStrData(rs.get("keydescription")),  // displayname
                  convert.getStrData(rs.get("color")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getIntData(rs.get("sortorder"))
               );

               if (themeMap.containsKey(key)) {
                  if (themeMap.get(key).getAsset().containsKey(asset)) {
                     themeMap.get(key).getAsset().get(asset).addPortfolio(portfolio);
                  }
               }
               i++;
            }
         }
      }
   }

   public Map<String, FMProjection> load_fixedmodule_projection()
   {

      // DataSource ds = getDs();
      String storedProcName = "sel_sec_fixed_performancechart";
      FixedModelSP sp = new FixedModelSP(ds, storedProcName, 1, 0);

      Map outMap = sp.loadFixedProjectionChart();
      Map<String, FMProjection> fmprojection = new LinkedHashMap<String, FMProjection>();
      Map<String, ArrayList<FMProjectionData>> projectiondata;
      FMProjection projection;
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         if (rows != null) {
            for (Map<String, Object> map : rows) {
               Map rs = (Map) rows.get(i);
               String theme = convert.getStrData(rs.get("theme"));
               if (! fmprojection.containsKey(theme)) {
                 projection = new FMProjection();
                 projectiondata = new LinkedHashMap<String, ArrayList<FMProjectionData>>();
               }
               else {
                  projection = fmprojection.get(theme);
                  projectiondata = projection.getData();
               }

               String model = convert.getStrData(rs.get("model"));
               if (model == null)
                  continue;

               ArrayList<FMProjectionData> datamodel;
               if (projectiondata.containsKey(model)) {
                  datamodel = projectiondata.get(model);
               }
               else {
                  datamodel = new ArrayList<FMProjectionData>();
               }
               FMProjectionData data = new FMProjectionData();
               data.setModel(model);
               data.setYear(convert.getIntData(rs.get("year")));
               data.setFivepercent(convert.getDoubleData(rs.get("5percent")));
               data.setTwentyfivepercent(convert.getDoubleData(rs.get("25percent")));
               data.setFiftypercent(convert.getDoubleData(rs.get("50percent")));
               data.setSeventyfivepercent(convert.getDoubleData(rs.get("75percent")));
               data.setNintyfivepercent(convert.getDoubleData(rs.get("95percent")));
               datamodel.add(data);
               projectiondata.put(model, datamodel);
               projection.setTheme(theme);
               projection.setData(projectiondata);
               fmprojection.put(theme,projection);
               i++;
            }
         }
      }
      return fmprojection;
   }
}
