package com.invessence.dao.ltam;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.bean.consumer.ClientBean;
import com.invessence.converter.SQLData;
import com.invessence.dao.advisor.AdvisorListSP;
import com.invessence.data.common.CustomerData;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "ltamListDataDAO")
@SessionScoped
public class LTAMListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<LTAMCustomerData> getClientProfileData(Long logonid, Long acctnum) {
      DataSource ds = getDataSource();
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
      DataSource ds = getDataSource();
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