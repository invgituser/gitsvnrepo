package com.invessence.web.dao.common;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.common.NotificationData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class CommonSP extends StoredProcedure
{

   public CommonSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            break;
         case 1: // SP: sp_validate_state
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            break;
         case 2: // SP: sel_notification
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_messagetype", Types.VARCHAR));
            declareParameter(new SqlParameter("p_archive", Types.VARCHAR));
            break;
         case 3: // SP: sp_advisor_notification
            declareParameter(new SqlParameter("p_messageid", Types.BIGINT));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisorlogonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_noticetype", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tagid", Types.VARCHAR));
            declareParameter(new SqlParameter("p_alertdatetime", Types.VARCHAR));
            declareParameter(new SqlParameter("p_message", Types.VARCHAR));
            break;
         case 4: // SP: sel_web_site_info
            declareParameter(new SqlParameter("p_url", Types.VARCHAR));
            break;
         case 5: // SP: sel_advisor_web_info
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            break;
         case 99: // SP: sel_notificationInfo
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         default:
      }
      compile();
   }

   public Map collectProfileData(Long logonid, Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map validateState(Long acctnum, String state)
   {
      String info = "";
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", acctnum);
      inputMap.put("p_state", state);
      return super.execute(inputMap);
   }

   public Map getNotification(Long acctnum, String messagetype, String status)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", acctnum);
      inputMap.put("p_messagetype", messagetype);
      inputMap.put("p_archive", status);
      return super.execute(inputMap);
   }

   public void saveNotice(NotificationData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_messageid", data.getMessageid());
      inputMap.put("p_status", data.getStatus());
      inputMap.put("p_advisorlogonid", data.getAdvisorlogonid());
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_noticetype", data.getNoticetype());
      inputMap.put("p_tagid", data.getTagid());
      inputMap.put("p_alertdatetime", data.getBusinessdate());
      inputMap.put("p_message", data.getMessage());
      super.execute(inputMap);
   }

   public Map getNotificationInfo(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", acctnum);
      return super.execute(inputMap);
   }

   public Map getWebSiteInfo(String url)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_url", url);
      return super.execute(inputMap);
   }

   public Map getAdvisorWebInfo(String advisor)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      return super.execute(inputMap);
   }
}
