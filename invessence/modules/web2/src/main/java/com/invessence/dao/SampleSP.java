package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class SampleSP extends StoredProcedure
{

   public SampleSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_addmod", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_status", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ibacctnum", Types.VARCHAR));
            break;
         default:
      }
      compile();
   }


   public Map addData(Map datainput)
   {

      Map inputMap = new HashMap();

      inputMap.put("p_addmod", datainput.get("p_addmod"));
      inputMap.put("p_acctnum", datainput.get("p_acctnum"));
      inputMap.put("p_status", datainput.get("p_status"));
      inputMap.put("p_ibacctnum", datainput.get("p_ibacctnum"));

      return super.execute(inputMap);
   }

}
