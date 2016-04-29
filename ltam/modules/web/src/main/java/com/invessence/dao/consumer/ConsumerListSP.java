package com.invessence.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class ConsumerListSP extends StoredProcedure
{

   public ConsumerListSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:   // SP: sel_ltam_ClientProfileData
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 1:   // SP: sel_ltam_AccountProfile
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 2:   // SP: sel_ClientProfileData2
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            declareParameter(new SqlParameter("p_days", Types.INTEGER));
            break;
         case 3:   // SP: sel_position
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 4:   // SP: sel_AccountInfo
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadClientProfileData(Long logonid, String filter, Integer days)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_filter", filter);
      inputMap.put("p_days", days);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getPositionData(Long logonid, Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map loadAccountInfo(Long logonid, Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }


}
