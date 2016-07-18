package com.invessence.web.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.bean.consumer.ClientBean;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.consumer.RiskCalculator;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class ConsumerListSP extends StoredProcedure
{

   public ConsumerListSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:   // SP: sel_ClientProfileData2
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_days", Types.INTEGER));
            break;
         case 1:   // SP: sel_NewAccountProfile
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 2:   // SP: sel_newClient
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 3:   // SP: sel_newClient
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            break;
         case 4:   // SP: sel_newClient
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_fromdate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_todate", Types.VARCHAR));
            break;
         case 5:   // SP: sel_risk_questions
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadClientProfileData(Long logonid, Long acctnum, Integer days)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_days", days);
      return super.execute(inputMap);
   }

   public Map loadClientProfileData(CustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_days", null);
      return super.execute(inputMap);
   }


   public Map getNewClientProfileData(CustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_rep", data.getRep());
      inputMap.put("p_logonid", data.getLogonid());
      return super.execute(inputMap);
   }


   public Map validateState(Long logonid, String state)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_state", state);
      return super.execute(inputMap);
   }

   public Map loadReports(Long logonid, String fromDate, String toDate)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_fromdate", fromDate);
      inputMap.put("p_todate", toDate);
      return super.execute(inputMap);
   }

   public Map loadRiskProfileData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }


}
