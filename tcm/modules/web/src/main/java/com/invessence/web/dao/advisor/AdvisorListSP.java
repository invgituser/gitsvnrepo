package com.invessence.web.dao.advisor;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class AdvisorListSP extends StoredProcedure
{

   public AdvisorListSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0: // sel_ClientProfileData2
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 1: // sel_AdvisorAcctProfile
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 2: // sel_AdvisorBaskets
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_strategy", Types.VARCHAR));
            break;
         case 3: // sel_asset_alloc
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 4: // sel_ExcludedSubclass
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 5: // sel_advisorDashBoard
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 6: // advisor_sel_assetclass
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 7: // advisor_sel_primeassetclass
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 8: // sel_notification_advisor
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_messagetype", Types.VARCHAR));
            declareParameter(new SqlParameter("p_archive", Types.VARCHAR));
            break;
         default:
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectProfileData(Long logonid, Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getProfileData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map collectBasket(String advisor, String strategy)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_strategy", strategy);
      return super.execute(inputMap);
   }

   public Map collectAllocation(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map collectSubClassExclusionList(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map collectDashBoardData(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }

   public Map collectAssetClass(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }

   public Map collectPrimeAssetClass(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }

   public Map getAdvisorNotification(Long logonid, String messageType, String status)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_messagetype", messageType);
      inputMap.put("p_archive", status);
      return super.execute(inputMap);
   }

   public Map getAdvisorNotificationInfo(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }
}
