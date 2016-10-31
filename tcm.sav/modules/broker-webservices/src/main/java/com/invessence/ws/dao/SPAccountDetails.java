package com.invessence.ws.dao;

import java.sql.Types;
import java.util.*;

import com.invessence.ws.bean.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/7/2016.
 */
public class SPAccountDetails extends StoredProcedure
{
   private static final String SPROC_NAME = "ws_acct_details_mgmt";
   public SPAccountDetails(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );
      declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
      declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));;
      declareParameter(new SqlParameter("p_status", Types.VARCHAR));
      declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
      declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );  //declaring sql in parameter to pass input
      declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
      declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
      // declaring sql out parameter
      compile();
   }
   public DBResponse execute(UserAcctDetails userAcctDetails){
      Map inputs = new HashMap();
      inputs.put("p_clientAccountID", userAcctDetails.getClientAccountID());
      inputs.put("p_pwd", userAcctDetails.getPwd());
      inputs.put("p_status", userAcctDetails.getStatus());
      inputs.put("p_remarks", userAcctDetails.getRemarks());
      inputs.put("p_opt", userAcctDetails.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
