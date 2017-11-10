package com.invessence.custody.dao;

import java.sql.Types;
import java.util.*;

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
         default:  // All other (no arg)
            break;
      }
   }
   public Map fetchData(Long acctNum)
   {
      Map inputMap = new LinkedHashMap();
      inputMap.put("p_acctnum", acctNum);

//      Map<String,Object> results = super.execute(inputs);
//      DBResponse dbRes= new DBResponse((int)results.get("op_msgCode"),results.get("op_msg").toString());
      return execute(inputMap);
   }
   public Map nonParamProcCall() {

      return super.execute();
   }
}
