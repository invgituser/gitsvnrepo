package com.invessence.custody.dao;

import java.sql.*;
import java.util.*;

import com.invessence.custody.data.AORequestAudit;
import com.invessence.ws.bean.DBResponse;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/25/2016.
 *
 */

public class SPAORequestAuditrial extends StoredProcedure
{
   private static final String SPROC_NAME = "dc_request_auditrial";
   public SPAORequestAuditrial(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );
      //id, requestId, eventNum, acctnum, dcRequest, dcResponce, status, remarks, reqTime, resTime, id, id
      declareParameter(new SqlParameter("p_id", Types.BIGINT));
      declareParameter(new SqlParameter("p_product", Types.VARCHAR));
      declareParameter(new SqlParameter("p_mode", Types.VARCHAR));
      declareParameter(new SqlParameter("p_requestIds", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctNum", Types.VARCHAR));
      declareParameter(new SqlParameter("p_eventNum", Types.VARCHAR));
      declareParameter(new SqlParameter("p_envelopId",Types.VARCHAR));
      declareParameter(new SqlParameter("p_status",Types.VARCHAR));
      declareParameter(new SqlParameter("p_dcRequest", Types.VARCHAR));
      declareParameter(new SqlParameter("p_dcResponce", Types.VARCHAR));
      declareParameter(new SqlParameter("p_reqTime", Types.TIMESTAMP));
      declareParameter(new SqlParameter("p_resTime", Types.TIMESTAMP));
      declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
      declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );  //declaring sql in parameter to pass input
      declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
      declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
      // declaring sql out parameter
      compile();
      }

      public DBResponse execute(AORequestAudit aoRequest){
         Map inputs = new HashMap();
         inputs.put("p_id", aoRequest.getId());
         inputs.put("p_product", aoRequest.getProduct());
         inputs.put("p_mode", aoRequest.getMode());
         inputs.put("p_requestIds", aoRequest.getRequestIds());
         inputs.put("p_acctNum", aoRequest.getAcctnum());
         inputs.put("p_eventNum", aoRequest.getEventNum());
         inputs.put("p_envelopId", aoRequest.getEnvelopId());
         inputs.put("p_status", aoRequest.getStatus());
         inputs.put("p_dcRequest", aoRequest.getDcRequest());
         inputs.put("p_dcResponce", aoRequest.getDcResponce());
         inputs.put("p_reqTime", aoRequest.getReqTime()==null?null: new Timestamp(aoRequest.getReqTime().getTime()));
         inputs.put("p_resTime", aoRequest.getResTime()==null?null: new Timestamp(aoRequest.getResTime().getTime()));
         inputs.put("p_remarks", aoRequest.getRemarks());
         inputs.put("p_opt", aoRequest.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
