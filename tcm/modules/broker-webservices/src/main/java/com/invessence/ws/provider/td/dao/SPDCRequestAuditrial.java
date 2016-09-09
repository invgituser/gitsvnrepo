package com.invessence.ws.provider.td.dao;

import java.sql.*;
import java.util.*;

import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.bean.DCRequestAudit;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 4/25/2016.
 *
 */

public class SPDCRequestAuditrial extends StoredProcedure
{
   private static final String SPROC_NAME = "dc_request_auditrial";
   public SPDCRequestAuditrial(JdbcTemplate datasource){
      super(datasource, SPROC_NAME );
      //id, requestId, eventNum, acctnum, dcRequest, dcResponce, status, remarks, reqTime, resTime, id, id
      declareParameter(new SqlParameter("p_id", Types.BIGINT));
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
      public DBResponse execute(DCRequestAudit dcRequest){
         Map inputs = new HashMap();
         inputs.put("p_id", dcRequest.getId());
         inputs.put("p_eventNum", dcRequest.getEventNum());
         inputs.put("p_envelopId",dcRequest.getEnvelopId());
         inputs.put("p_status", dcRequest.getStatus());
         inputs.put("p_dcRequest", dcRequest.getDcRequest());
         inputs.put("p_dcResponce", dcRequest.getDcResponce());
         inputs.put("p_reqTime", dcRequest.getReqTime()==null?null: new Timestamp(dcRequest.getReqTime().getTime()));
         inputs.put("p_resTime",  dcRequest.getResTime()==null?null: new Timestamp(dcRequest.getResTime().getTime()));
         inputs.put("p_remarks", dcRequest.getRemarks());
         inputs.put("p_opt", dcRequest.getOpt());

         Map<String,Object> results = super.execute(inputs);
         DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
         return dbRes; //reading output of stored procedure using out parameters
    }

}
