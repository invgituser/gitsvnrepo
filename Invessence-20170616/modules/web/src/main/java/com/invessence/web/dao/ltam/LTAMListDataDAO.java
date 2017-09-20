package com.invessence.web.dao.ltam;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.consumer.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "ltamListDataDAO")
@SessionScoped
public class LTAMListDataDAO extends JdbcDaoSupport implements Serializable
{
   private static LTAMListDataDAO instance = null;
   DBConnection dbconnection;
   SQLData convert;
   DataSource localds;

   public static synchronized LTAMListDataDAO getInstance()
   {
      if (instance == null)
      {
         instance = new LTAMListDataDAO();
      }

      return instance;
   }

   public LTAMListDataDAO()
   {
      dbconnection = DBConnection.getInstance();
      convert = new SQLData();
      localds = dbconnection.getMySQLDataSource();
   }

   private DataSource getDS() {
      DataSource datasource;

      datasource = getDataSource();
      if (datasource == null)
         datasource = localds;

      return datasource;

   }

   public List<LTAMCustomerData> getClientProfileData(Long logonid, Long acctnum) {
      DataSource ds = getDS();
      LTAMListSP sp = new LTAMListSP(ds, "sel_ltam_ClientProfileData",0);
      List<LTAMCustomerData> listProfiles = new ArrayList<LTAMCustomerData>();
      Map outMap = sp.loadClientProfileData(logonid, acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            LTAMCustomerData data = new LTAMCustomerData();

            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }

   public void getProfileData(LTAMCustomerData data) {
      DataSource ds = getDS();
      LTAMListSP sp = new LTAMListSP(ds, "sel_ltam_AccountProfile",1);
      Map outMap = sp.loadClientProfileData(data);
      String action;
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows == null)
               return;
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               i++;
               break;  // Only load the first account info.
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}