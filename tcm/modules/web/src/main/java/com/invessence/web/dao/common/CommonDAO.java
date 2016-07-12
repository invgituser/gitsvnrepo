package com.invessence.web.dao.common;

import java.io.Serializable;
import java.util.*;
import javax.faces.bean.*;
import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

@ManagedBean(name = "commonDAO")
@ApplicationScoped
public class CommonDAO extends JdbcDaoSupport implements Serializable
{
   SQLData convert = new SQLData();

   public void saveUserIDPwd(UserData data) {

   }

   public String validateState(Long logonid, String state) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sp_validate_state",1);
      Map outMap = sp.validateState(logonid, state);
      String info="quota";
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            info=convert.getStrData(rs.get("license"));
            i++;
            break;
         }
      }
      return info;

   }

   public ArrayList<NotificationData> getNotification(Long logonid, String messageType, String status)
   {
      DataSource ds = getDataSource();

      CommonSP sp = new CommonSP(ds, "sel_notification", 2);
      ArrayList<NotificationData> notificationList = new ArrayList<NotificationData>();

      Map outMap = sp.getNotification(logonid, messageType, status);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         if (rows != null)
         {
            int i = 0;
            for (Map<String, Object> map : rows)
            {
               Map rs = (Map) rows.get(i);
               NotificationData ndata = new NotificationData(
                  convert.getLongData(rs.get("messageid")),
                  convert.getStrData(rs.get("status")),
                  convert.getLongData(rs.get("advisorlogonid")),
                  convert.getStrData(rs.get("advisor")),
                  convert.getLongData(rs.get("acctnum")),
                  convert.getStrData(rs.get("noticetype")),
                  convert.getStrData(rs.get("tagid")),
                  convert.getStrData(rs.get("alertdatetime")),
                  convert.getStrData(rs.get("message"))
                  // convert.getStrData(rs.get("created")),

               );

               notificationList.add(i, ndata);
               i++;
            }

         }
      }
      return notificationList;

   }

   public void saveNotice(NotificationData data) {
      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sp_advisor_notification",3);
      sp.saveNotice(data);
   }

   public Map<String, Integer> getNotificationInfo(Long logonid) {
      if (logonid == null)
         return null;

      DataSource ds = getDataSource();
      CommonSP sp = new CommonSP(ds, "sel_notificationInfo",99);
      Map outMap = sp.getNotificationInfo(logonid);
      Map<String, Integer> statInfo = new HashMap<String, Integer>();
      try {
         if (outMap != null)
         {
            ArrayList<Map<String, Object>> rows;
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)  {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  statInfo.put(convert.getStrData(rs.get("src")),
                               convert.getIntData(rs.get("value")));
                  i++;
               }
            }
         }
         return statInfo;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

}