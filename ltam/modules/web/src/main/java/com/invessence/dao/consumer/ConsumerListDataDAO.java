package com.invessence.dao.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.data.consumer.*;
import com.invessence.util.EmailMessage;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "consumerListDataDAO")
@SessionScoped
public class ConsumerListDataDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public List<DashboardData> getClientProfileData(Long logonid) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_ClientProfileData2",0);
      Map outMap = sp.loadClientProfileData(logonid);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null) {
            List<DashboardData> listProfiles = new ArrayList<DashboardData>();
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
      }
      return null;
   }

   public PositionSummaryData getPositionData(Long logonid, Long acctnum ) {
      DataSource ds = getDataSource();
      ConsumerListSP sp = new ConsumerListSP(ds, "sel_position",1);
      Map outMap = sp.getPositionData(logonid, acctnum);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null ) {
            PositionSummaryData data = new PositionSummaryData();
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               data.addPosition(
                  convert.getIntData(rs.get("sortorder")),
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("clientAccountID")),
                  convert.getStrData(rs.get("name")),
                  convert.getStrData(rs.get("repName")),
                  convert.getStrData(rs.get("description")),
                  convert.getStrData(rs.get("theme")),
                  convert.getStrData(rs.get("dateOpened")),
                  convert.getStrData(rs.get("assetClass")),
                  convert.getStrData(rs.get("assetname")),
                  convert.getStrData(rs.get("subasset")),
                  convert.getStrData(rs.get("displayname")),
                  convert.getStrData(rs.get("color")),
                  convert.getStrData(rs.get("status")),
                  convert.getDoubleData(rs.get("allocation")),
                  convert.getStrData(rs.get("reportDate")),
                  convert.getStrData(rs.get("side")),
                  convert.getDoubleData(rs.get("quantity")),
                  convert.getDoubleData(rs.get("costBasisPrice")),
                  convert.getDoubleData(rs.get("costBasisMoney")),
                  convert.getDoubleData(rs.get("markPrice")),
                  convert.getDoubleData(rs.get("positionValue"))
               );
               i++;
            }
            return data;
         }
      }
      return null;
   }

}