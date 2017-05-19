package com.invessence.fileProcessor.dao;

import java.sql.Types;
import java.util.*;

import com.invessence.fileProcessor.bean.FileProcessAudit;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by abhangp on 2/22/2017.
 */
public class FileProcessorSP extends StoredProcedure
{
   public FileProcessorSP(JdbcTemplate datasource, String SPROC_NAME, Integer mode)
   {
      super(datasource, SPROC_NAME);
      switch (mode)
      {
         case 0: //File Processor Audit

            declareParameter(new SqlParameter("p_id", Types.VARCHAR));
            declareParameter(new SqlParameter("p_product", Types.VARCHAR));
            declareParameter(new SqlParameter("p_mode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_process", Types.VARCHAR));
            declareParameter(new SqlParameter("p_processId", Types.VARCHAR));
            declareParameter(new SqlParameter("p_fileName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_remarks", Types.VARCHAR));
            declareParameter(new SqlParameter("p_executionTime", Types.TIMESTAMP));
            declareParameter(new SqlParameter("p_opt", Types.VARCHAR) );
            declareParameter( new SqlOutParameter("op_msgCode", Types.INTEGER ) );
            declareParameter( new SqlOutParameter("op_msg", Types.VARCHAR ) );
            break;
         default:  // All other (no arg)
            break;
      }
   }
   public Map callFileProcessorAuditSP(FileProcessAudit fileProcessAudit)
   {
      Map inputMap = new HashMap();

      inputMap.put("p_id", fileProcessAudit.getId());
      inputMap.put("p_product", fileProcessAudit.getProduct());
      inputMap.put("p_mode", fileProcessAudit.getMode());
      inputMap.put("p_process", fileProcessAudit.getProcess());
      inputMap.put("p_processId", fileProcessAudit.getProcessId());
      inputMap.put("p_fileName", fileProcessAudit.getFileName());
      inputMap.put("p_status", fileProcessAudit.getStatus());
      inputMap.put("p_remarks", fileProcessAudit.getRemarks());
      inputMap.put("p_executionTime", fileProcessAudit.getExecutionTime());
      inputMap.put("p_opt", fileProcessAudit.getOpt());

//      Map<String,Object> results = super.execute(inputs);
//      DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
      return super.execute(inputMap);
   }
   public Map nonParamProcCall() {

      return super.execute();
   }
}
