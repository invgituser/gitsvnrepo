package com.invessence.web.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.common.CustomerData;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
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
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
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
         case 6:   // SP: sel_risk_questions
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 7:   // SP: sel_Client_active_account
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
         case 8:   // SP: sel_reportType
            break;
         case 9:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_rprtType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_DateFactor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fromDate", Types.VARCHAR));
            declareParameter(new SqlParameter("p_toDate", Types.VARCHAR));
            break;
         case 10:
            declareParameter(new SqlParameter("p_from_currency", Types.VARCHAR));
            declareParameter(new SqlParameter("p_to_currency", Types.VARCHAR));
            break;
         case 11:
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_from_currency", Types.VARCHAR));
            break;
         default:
      }
      compile();
   }

   public Map loadClientProfileData(Long logonid, Long acctnum, Integer days, String advisor, String rep)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_days", days);
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_rep", rep);
      return super.execute(inputMap);
   }

   public Map loadClientProfileData(CustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_days", null);
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_rep", data.getRep());
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
   public Map validatefundData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map getClientActiveAcctList(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }

   public Map getReportTypeList()
   {
      return super.execute();
   }

   public Map getClientReportData(Long acctnum,String reportType,String dateFactor,String fromDate,String toDate)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_rprtType", reportType);
      inputMap.put("p_DateFactor", dateFactor);
      inputMap.put("p_fromDate", fromDate);
      inputMap.put("p_toDate", toDate);
      return super.execute(inputMap);
   }
   public Map getExhangeRate(String fromCurrency, String toCurrency)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_from_currency", fromCurrency);
      inputMap.put("p_to_currency", toCurrency);
      return super.execute(inputMap);
   }

   public Map getAdvisorbaseCurrency(String advisor,String tradeCurrency)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_from_currency", tradeCurrency);
      return super.execute(inputMap);
   }


}
