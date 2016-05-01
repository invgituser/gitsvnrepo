package com.invessence.ws.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.invessence.ws.bean.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/25/2016.
 *
 */

public class SPRequestAuditrial extends StoredProcedure
{
   private static final String SPROC_NAME = "ws_request_auditrial";
   public SPRequestAuditrial(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );
      declareParameter(new SqlParameter("p_id", Types.BIGINT));
      declareParameter(new SqlParameter("p_clientAccountID", Types.VARCHAR));
      declareParameter(new SqlParameter("p_reuestType", Types.VARCHAR));
      declareParameter(new SqlParameter("p_status",Types.VARCHAR));
      declareParameter(new SqlParameter("p_reqXml", Types.VARCHAR));
      declareParameter(new SqlParameter("p_resXml", Types.VARCHAR));
      declareParameter(new SqlParameter("p_reqTime", Types.TIMESTAMP));
      declareParameter(new SqlParameter("p_resTime", Types.TIMESTAMP));
      declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
      declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );  //declaring sql in parameter to pass input
      declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
      declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
      // declaring sql out parameter
      compile();
      }
      public DBResponse execute(WSRequest webRequest){
         Map inputs = new HashMap();

         inputs.put("p_id", webRequest.getId());
         inputs.put("p_clientAccountID", webRequest.getClientAccountID());
         inputs.put("p_reuestType", webRequest.getReuestType());
         inputs.put("p_status", webRequest.getStatus());
         inputs.put("p_reqXml", webRequest.getReqXml());
         inputs.put("p_resXml", webRequest.getResXml());
         inputs.put("p_reqTime", webRequest.getReqTime()==null?null: new Timestamp(webRequest.getReqTime().getTime()));
         inputs.put("p_resTime",  webRequest.getReqTime()==null?null: new Timestamp(webRequest.getResTime().getTime()));
         inputs.put("p_remarks", webRequest.getRemarks());
         inputs.put("p_opt", webRequest.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
