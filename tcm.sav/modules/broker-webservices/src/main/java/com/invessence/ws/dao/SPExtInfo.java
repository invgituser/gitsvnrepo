package com.invessence.ws.dao;

import java.sql.Types;
import java.util.*;

import com.invessence.ws.bean.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/7/2016.
 */
public class SPExtInfo extends StoredProcedure
{
   private static final String SPROC_NAME = "ws_ext_info";
   public SPExtInfo(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );
      declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
      declareParameter(new SqlParameter("p_accountType", Types.VARCHAR));
      declareParameter(new SqlParameter("p_dateOfBirth",Types.DATE));
      declareParameter(new SqlParameter("p_mailingAddressId", Types.VARCHAR));
      declareParameter(new SqlParameter("p_mailingAddressType", Types.VARCHAR));
      declareParameter(new SqlParameter("p_status", Types.VARCHAR));
      declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
      declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );  //declaring sql in parameter to pass input
      declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
      declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
      // declaring sql out parameter
      compile();
      }
      public DBResponse execute(UserAcctExt userAcctExt){
         Map inputs = new HashMap();
         inputs.put("p_clientAccountID", userAcctExt.getClientAccountID());
         inputs.put("p_accountType", userAcctExt.getAccountType());
         inputs.put("p_dateOfBirth",userAcctExt.getDateOfBirth());
         inputs.put("p_mailingAddressId", userAcctExt.getMailingAddressId());
         inputs.put("p_mailingAddressType", userAcctExt.getMailingAddressType());
         inputs.put("p_status", userAcctExt.getStatus());
         inputs.put("p_remarks", userAcctExt.getRemarks());
         inputs.put("p_opt", userAcctExt.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
