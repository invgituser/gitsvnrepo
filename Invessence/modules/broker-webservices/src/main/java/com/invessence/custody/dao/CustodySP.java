package com.invessence.custody.dao;

import java.sql.Types;
import java.util.*;

import com.invessence.custody.uob.data.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 10/26/2017.
 */
public class CustodySP extends StoredProcedure
{
   public CustodySP(JdbcTemplate datasource, String SPROC_NAME, Integer mode)
   {
      super(datasource, SPROC_NAME);
      switch (mode)
      {
         case 0: //File Processor Audit
            declareParameter(new SqlParameter("p_acctnum", Types.NUMERIC));
            break;
         case 1:
            declareParameter(new SqlParameter("p_acctnum", Types.NUMERIC));
            declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
            declareParameter(new SqlParameter("p_repId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_caseNumber", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisorId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctTypeId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonId", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlParameter("p_acctnum", Types.NUMERIC));
            declareParameter(new SqlParameter("p_acctOwnerId", Types.NUMERIC));
            declareParameter(new SqlParameter("p_title", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fullName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_gender", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dob", Types.VARCHAR));
            declareParameter(new SqlParameter("p_countryOfBirth", Types.VARCHAR));
            declareParameter(new SqlParameter("p_emailAddress", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ownership", Types.VARCHAR));
            declareParameter(new SqlParameter("p_logonId", Types.VARCHAR));
            break;
         case 3:
            declareParameter(new SqlParameter("p_acctnum", Types.NUMERIC));
            declareParameter(new SqlParameter("p_acctOwnerId", Types.NUMERIC));
            declareParameter(new SqlParameter("p_name", Types.VARCHAR));
            declareParameter(new SqlParameter("p_value", Types.VARCHAR));
            declareParameter(new SqlParameter("p_table", Types.VARCHAR));
            break;
         default:  // All other (no arg)
            break;
      }
   }
   public Map fetchData(Long acctNum)
   {
      Map inputMap = new LinkedHashMap();
      inputMap.put("p_acctnum", acctNum);
      return execute(inputMap);
   }

   public Map saveAcctDetails(AccountDetails accountDetails,String p_logonId)
   {
      Map inputMap = new LinkedHashMap();
      inputMap.put("p_acctnum", accountDetails.getAcctnum());
      inputMap.put("p_clientAccountID", accountDetails.getClientAccountID());
      inputMap.put("p_repId", accountDetails.getRepId());
      inputMap.put("p_caseNumber", accountDetails.getCaseNumber());
      inputMap.put("p_advisorId", accountDetails.getAdvisorId());
      inputMap.put("p_acctTypeId", accountDetails.getAcctTypeId());
      inputMap.put("p_logonId", p_logonId);
      return execute(inputMap);
   }

   public Map saveAcctHolderDetails(Long acctNum, int acctOwnerId , String p_logonId, OwnerDetails ownerDetails)
   {
      Map inputMap = new LinkedHashMap();
      inputMap.put("p_acctnum", acctNum);
      inputMap.put("p_acctOwnerId", acctOwnerId);
      inputMap.put("p_title", ownerDetails.getTitle());
      inputMap.put("p_fullName", ownerDetails.getFullName());
      inputMap.put("p_gender", ownerDetails.getGender());
      inputMap.put("p_dob", ownerDetails.getDob());
      inputMap.put("p_countryOfBirth", ownerDetails.getCountryOfBirth());
      inputMap.put("p_emailAddress", ownerDetails.getEmailAddress());
      inputMap.put("p_ownership", ownerDetails.getOwnership());
      inputMap.put("p_logonId", p_logonId);
      return execute(inputMap);
   }

   public Map saveAcctAdditionalDetails(Long acctNum,int acctOwnerId,String name,String value,String table)
   {
      Map inputMap = new LinkedHashMap();
      inputMap.put("p_acctnum", acctNum);
      inputMap.put("p_acctOwnerId", acctOwnerId);
      inputMap.put("p_name", name);
      inputMap.put("p_value", value);
      inputMap.put("p_table", table);
      return execute(inputMap);
   }


   public Map nonParamProcCall() {

      return super.execute();
   }
}
