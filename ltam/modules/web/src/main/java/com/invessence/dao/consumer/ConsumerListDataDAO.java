package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.consumer.DashboardData;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerListDataDAO")
@SessionScoped
public class ConsumerListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<DashboardData> getClientProfileData(Long logonid) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData2",0);
      List<DashboardData> listProfiles = new ArrayList<DashboardData>();
      Map outMap = sp.loadClientProfileData(logonid);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            DashboardData data = new DashboardData(
               convert.getLongData(rs.get("logonid")),
               convert.getLongData(rs.get("acctnum")),
               convert.getStrData(rs.get("functionid")),
               convert.getStrData(rs.get("role")),
               convert.getStrData(rs.get("privileges")),
               convert.getStrData(rs.get("firstName")),
               convert.getStrData(rs.get("lastName")),
               convert.getDoubleData(rs.get("investment")),
               convert.getDoubleData(rs.get("riskIndex")),
               convert.getStrData(rs.get("managed")),
               convert.getStrData(rs.get("dateOpened")),
               convert.getStrData(rs.get("clientAccountID")),
               convert.getStrData(rs.get("description"))
            );

            listProfiles.add(i, data);
            i++;
         }
         return listProfiles;
      }
      return null;
   }


}