package com.invessence.web.service.crm;

import java.sql.Types;
import java.util.*;

import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/7/2016.
 */
public class SPCRMUserMgmt extends StoredProcedure
{
   private static final String SPROC_NAME = "spcrm_user_mgmt";
   public SPCRMUserMgmt(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );

      declareParameter(new SqlParameter("p_logonid", Types.NUMERIC));
      declareParameter(new SqlParameter("p_userid", Types.VARCHAR));
      declareParameter(new SqlParameter("p_pwd", Types.VARCHAR));
      declareParameter(new SqlParameter("p_email",Types.VARCHAR));
      declareParameter(new SqlParameter("p_userKey",Types.VARCHAR));
      declareParameter(new SqlParameter("p_status", Types.VARCHAR));
      declareParameter(new SqlParameter("p_created", Types.TIMESTAMP));
      declareParameter(new SqlParameter("p_createdBy", Types.VARCHAR));
      declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );  //declaring sql in parameter to pass input
      declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
      declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
      // declaring sql out parameter
      compile();
      }
      public DBResponse execute(CRMUserDetails userAcctExt){
         Map inputs = new HashMap();
         inputs.put("p_logonid", userAcctExt.getLogonid());
         inputs.put("p_userid", userAcctExt.getCrmUserId());
         inputs.put("p_pwd", userAcctExt.getCrmPwd());
         inputs.put("p_email",userAcctExt.getEmail());
         inputs.put("p_userKey",userAcctExt.getCrmUserKey());
         inputs.put("p_status", userAcctExt.getCrmStatus());
         inputs.put("p_created", new Date());
         inputs.put("p_createdBy", userAcctExt.getLogonid());
         inputs.put("p_opt", userAcctExt.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"), results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
