package com.invessence.rbsa.dao;

import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.rbsa.dao.data.*;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/22/14
 * Time: 10:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class RBSADAO
{
   private static RBSADAO instance;
   DBConnectionProvider dbconnection = null;
   SQLData convert = null;
   DataSource ds = null;

   public static synchronized RBSADAO getInstance()
   {
      if (instance == null)
      {
         instance = new RBSADAO();
      }

      return instance;
   }

   private RBSADAO()
   {
      dbconnection = DBConnectionProvider.getInstance();
      convert = new SQLData();
      ds = dbconnection.getMySQLDataSource();
   }

   public void deleteRBSAData(String fundName) {
      // DataSource ds = getDs();
      String storedProcName = "rbsa.del_rbsa_data";
      RBSASP sp = new RBSASP(ds, storedProcName,1);
      sp.deleteRBSAData(fundName);
   }

   public void saveRBSAData(RBSAData data) {
      // DataSource ds = getDs();
      String storedProcName;
      String fundName;
      RBSASP sp;
      if (data != null) {
         storedProcName = "rbsa.del_rbsa_data";
         sp = new RBSASP(ds, storedProcName,1);
         sp.deleteRBSAData(data.getFundName());

         storedProcName = "rbsa.save_rbsa_data";
         sp = new RBSASP(ds, storedProcName,2);
         Double value;
         fundName = data.getFundName();
            for (String indexFund : data.getSolution().keySet()) {
               value = data.getSolution().get(indexFund);
               if (value >= 0.0099)   // Save only if it has some significance.
                  sp.saveRBSAData(fundName,indexFund,data.getSolution().get(indexFund));
               //else
               //   sp.saveRBSAData(fundName,indexFund,0.0);
            }

         storedProcName = "rbsa.save_rbsa_data";
         sp = new RBSASP(ds, storedProcName,2);
         fundName = data.getFundName();
         sp.saveRBSAData(fundName,"_TE",data.getTrackingError());
      }
   }

   public Map<String, ReturnInfo> loadRBSAData(String ticker, String index) {
      // DataSource ds = getDs();
      String storedProcName;
      RBSASP sp;
      try {
         if (ticker != null && index != null) {
            storedProcName = "rbsa.sel_rbsa_fund_monthly_returns";
            sp = new RBSASP(ds, storedProcName,3);
            Map outMap = sp.loadRBSAData(ticker, index);
            ReturnInfo returnInfo;
            Map <String, ReturnInfo> data = new HashMap<String, ReturnInfo>();
            if (outMap != null)
            {
               ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
               int i = 0;
               String src, dataTicker;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  src = convert.getStrData(rs.get("src"));
                  if (! src.equalsIgnoreCase("F")) {
                     dataTicker = index;
                  }
                  else
                     dataTicker = convert.getStrData(rs.get("ticker"));

                  if (! data.containsKey(dataTicker))
                     returnInfo = new ReturnInfo();
                  else
                     returnInfo = data.get(dataTicker);

                  returnInfo.addData(convert.getStrData(rs.get("ticker")),
                                     convert.getStrData(rs.get("businessdate")),
                                     convert.getDoubleData(rs.get("monthly_return")));

                  data.put(dataTicker,returnInfo);
                  i++;
               }
               return data;
            }
         }
      }
      catch (Exception ex) {

      }
      finally
      {

      }
     return null;
   }

}
