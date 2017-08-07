package com.invessence.web.dao.advisor;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class AdvisorListSP extends StoredProcedure
{

   public AdvisorListSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode)
      {
         case 0: // sel_ClientProfileData
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            declareParameter(new SqlParameter("p_days", Types.INTEGER));
            declareParameter(new SqlParameter("p_filterActive", Types.VARCHAR));
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
         case 9: // sel_notification_advisor
            declareParameter(new SqlParameter("p_modeltype", Types.VARCHAR));
            break;
         case 10: // sel_notification_advisor
            declareParameter(new SqlParameter("vModel", Types.VARCHAR));
            declareParameter(new SqlParameter("vFile", Types.VARCHAR));
            declareParameter(new SqlParameter("vTheme", Types.VARCHAR));
            break;
         case 11: // sel_notification_advisor
            declareParameter(new SqlParameter("modelnm", Types.VARCHAR));
            declareParameter(new SqlParameter("templatenm", Types.VARCHAR));
            declareParameter(new SqlParameter("advisorid", Types.BIGINT));
            break;
         case 12: // sel_notification_advisor
            declareParameter(new SqlParameter("modelnm", Types.VARCHAR));
            break;
         case 13: // sel_notification_advisor
            declareParameter(new SqlParameter("modelnm", Types.VARCHAR));
            break;
         case 14:
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_crnt_theme", Types.VARCHAR));
            break;
         default:
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
      }
      compile();
   }

   public Map getListOfAccounts(Long logonid, String filter, Integer days, String filterByAmount)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_filter", filter);
      inputMap.put("p_days", days);
      inputMap.put("p_filterActive", filterByAmount);
      return super.execute(inputMap);
   }

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

   public Map getAssetModelTemplates(String modeltype)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_modeltype", modeltype);
      return super.execute(inputMap);
   }

   public Map getAssetAllocFile(String model, String file, String theme)
   {
      Map inputMap = new HashMap();
      inputMap.put("vModel", model);
      inputMap.put("vFile", file);
      inputMap.put("vTheme", theme);
      return super.execute(inputMap);
   }
   public Map getAssetAllocUpdFile(String model, String templateName, int advisorId)
   {
      Map inputMap = new HashMap();
      inputMap.put("modelnm", model);
      inputMap.put("templatenm", templateName);
      inputMap.put("advisorid", advisorId);
      return super.execute(inputMap);
   }
   public Map getAssetUpdTempFile(String model)
   {
      Map inputMap = new HashMap();
      inputMap.put("modelnm", model);
      return super.execute(inputMap);
   }
   public Map getFileLst(String model)
   {
      Map inputMap = new HashMap();
      inputMap.put("modelnm", model);
      return super.execute(inputMap);
   }

   public Map validateAssetData(String themeNew,String themeCrnt)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_theme", themeNew);
      inputMap.put("p_crnt_theme", themeCrnt);
      return super.execute(inputMap);
   }
}
