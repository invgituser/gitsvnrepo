package com.invmodel.risk.dao;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by prashant on 11/10/2017.
 */
public class RiskFetchSP extends StoredProcedure
{

   public RiskFetchSP(DataSource datasource, String storedProcName, int process)
   {
      super(datasource, storedProcName);
      switch (process)
      {
         case 1:
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 3:
            break;
         case 4:
            break;
         case 5:
            break;
         default: // All others with no args.
      }
      compile();
   }

/*
   private ArrayList<Object> fetchMap(Map outMap)
   {
      ArrayList<Object> data = null;
      if (outMap != null)
      {
         data = new ArrayList<Object>();
         for (Object resultKey : outMap.keySet())
         {
            String key = resultKey.toString();
            if (key.contains("result-set"))
            {
               data.add(outMap.get(resultKey));
            }
         }
      }
      return data;
   }
*/

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map fetchRiskMaster(String advisor)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map fetchRiskMapping(String advisor)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map fetchRiskData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map fetchRiskScores(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

}
