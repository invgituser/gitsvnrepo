package com.invessence.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.TradeData;
import com.invessence.data.ltam.LTAMCustomerData;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;


public class ConsumerSaveSP extends StoredProcedure
{

   public ConsumerSaveSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:  // save_visitor
            declareParameter(new SqlInOutParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_timeToSaveID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_source", Types.VARCHAR));
            declareParameter(new SqlParameter("p_data", Types.VARCHAR));
            break;
         case 1:  // save_acct_info
            declareParameter(new SqlInOutParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_timeToSaveID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_email", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_investment", Types.DOUBLE));
            declareParameter(new SqlParameter("p_age", Types.INTEGER));
            declareParameter(new SqlParameter("p_horizon", Types.INTEGER));
            declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_dateSent", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ans1", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk1", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans2", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk2", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans3", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk3", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans4", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk4", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans5", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk5", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans6", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk6", Types.INTEGER));
            declareParameter(new SqlParameter("p_ans7", Types.INTEGER));
            declareParameter(new SqlParameter("p_risk7", Types.INTEGER));
            declareParameter(new SqlParameter("p_formula", Types.VARCHAR));
            break;
         case 2: // save_acct_info_ack
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ext_acctnum", Types.VARCHAR));
            declareParameter(new SqlOutParameter("p_msg", Types.VARCHAR));
            break;
         case 3:   // SP: sp_update_addressInfo
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_middle", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_add3", Types.VARCHAR));
            declareParameter(new SqlParameter("p_city", Types.VARCHAR));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            declareParameter(new SqlParameter("p_zip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_country", Types.VARCHAR));
            declareParameter(new SqlParameter("p_primaryphone", Types.VARCHAR));
            declareParameter(new SqlParameter("p_secondaryphone", Types.VARCHAR));
            declareParameter(new SqlParameter("p_workphone", Types.VARCHAR));
            break;
         case 4:   // SP: save_TradeInfo
            declareParameter(new SqlOutParameter("p_transactionnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_tradetype", Types.VARCHAR));
            declareParameter(new SqlParameter("p_webservicenum", Types.VARCHAR));
            declareParameter(new SqlParameter("p_cusip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_investment", Types.DOUBLE));
            declareParameter(new SqlParameter("p_bankname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_bankinfo", Types.VARCHAR));
            declareParameter(new SqlParameter("p_info1", Types.VARCHAR));
            declareParameter(new SqlParameter("p_info2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_info3", Types.VARCHAR));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveVisitor(LTAMCustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid",0L);
      inputMap.put("p_timeToSaveID",data.getTimeToSaveID());
      inputMap.put("p_advisor",data.getAdvisor());
      inputMap.put("p_rep",data.getRep());
      inputMap.put("p_ip",data.getIpaddress());
      inputMap.put("p_source",data.getSource());
      inputMap.put("p_data",data.getInvestment());

      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveUserData(LTAMCustomerData data)
   {
      if (data == null) {
         return null;
      }
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum",data.getAcctnum());
      inputMap.put("p_logonid",data.getLogonid());
      inputMap.put("p_timeToSaveID",data.getTimeToSaveID());
      inputMap.put("p_advisor",data.getAdvisor());
      inputMap.put("p_rep",data.getRep());
      inputMap.put("p_email","");
      inputMap.put("p_firstName",data.getFirstname());
      inputMap.put("p_lastName",data.getLastname());
      inputMap.put("p_acctType",data.getAccttype());
      inputMap.put("p_theme",data.getTheme());
      inputMap.put("p_investment",data.getInvestment());
      inputMap.put("p_age",data.getAge());
      inputMap.put("p_horizon",data.getHorizon());
      inputMap.put("p_riskIndex",data.getRiskIndex());
      inputMap.put("p_dateSent",data.getForwarded());
      inputMap.put("p_ans1",data.getAns1());
      inputMap.put("p_risk1",data.getRiskValue(1));
      inputMap.put("p_ans2",data.getAns2());
      inputMap.put("p_risk2",data.getRiskValue(2));
      inputMap.put("p_ans3",data.getAns3());
      inputMap.put("p_risk3",data.getRiskValue(3));
      inputMap.put("p_ans4",data.getAns4());
      inputMap.put("p_risk4",data.getRiskValue(4));
      inputMap.put("p_ans5",data.getAns5());
      inputMap.put("p_risk5",data.getRiskValue(5));
      inputMap.put("p_ans6",data.getAns6());
      inputMap.put("p_risk6",data.getRiskValue(6));
      inputMap.put("p_ans7",0);
      inputMap.put("p_risk7",0);
      inputMap.put("p_formula",data.getFormula());
      return super.execute(inputMap);
   }

   public Map savePostBack(String myacctnum, String advisor, String ext_acctnum)
   {
      if (myacctnum == null) {
         return null;
      }
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", myacctnum);
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_ext_acctnum", ext_acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveAddress(AccountData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_firstname", data.getFirstname());
      inputMap.put("p_middle", data.getMiddle());
      inputMap.put("p_lastname", data.getLastname());
      inputMap.put("p_add1", data.getMailAddrs1());
      inputMap.put("p_add2", data.getMailAddrs2());
      inputMap.put("p_add3", data.getMailAddrs3());
      inputMap.put("p_city", data.getMailCity());
      inputMap.put("p_state", data.getMailState());
      inputMap.put("p_zip", data.getMailZipCode());
      inputMap.put("p_country", data.getMailCountry());
      inputMap.put("p_primaryphone", data.getPrimaryPhoneNbr());
      inputMap.put("p_secondaryphone", data.getSecondayPhoneNbr());
      inputMap.put("p_workphone", data.getWorkPhoneNbr());
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveTradeInfo(TradeData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_transactionnum", data.getTransactionnum());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_clientAccountID", data.getClientAccountID());
      inputMap.put("p_tradetype", data.getTradetype());
      inputMap.put("p_webservicenum", data.getWebservicenum());
      inputMap.put("p_cusip", data.getCusip());
      inputMap.put("p_investment", data.getInvestment());
      inputMap.put("p_bankname", data.getBankname());
      inputMap.put("p_bankinfo", data.getBankinfo());
      inputMap.put("p_info1", data.getInfo1());
      inputMap.put("p_info2", data.getInfo2());
      inputMap.put("p_info3", data.getInfo3());
      return super.execute(inputMap);
   }



}
