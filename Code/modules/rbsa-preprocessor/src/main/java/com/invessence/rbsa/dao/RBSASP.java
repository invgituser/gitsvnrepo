package com.invessence.rbsa.dao;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.rbsa.processors.data.Allocations;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/22/14
 * Time: 10:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RBSASP extends StoredProcedure
{
   public RBSASP(DataSource datasource, String storedProcName, int which)
   {
      super(datasource, storedProcName);
      switch (which) {
         case 0:
            break;
         case 1:
            declareParameter(new SqlParameter("p_fundName", Types.VARCHAR));
            break;
         case 2:
            declareParameter(new SqlParameter("p_fundName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_indexfund", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.DOUBLE));
            break;
         case 3:
            declareParameter(new SqlParameter("inputTicker", Types.VARCHAR));
            declareParameter(new SqlParameter("indexName", Types.VARCHAR));
            break;
         default:
            break;
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void deleteRBSAData(String fundName)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_fundName", fundName);
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveRBSAData(String fundName, String indexfund, double weight)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_fundName", fundName);
      inputMap.put("p_indexfund", indexfund);
      inputMap.put("p_weight", weight);
      super.execute(inputMap);

   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map loadRBSAData(String ticker, String indexfund)
   {
      Map inputMap = new HashMap();
      inputMap.put("inputTicker", ticker);
      inputMap.put("indexName", indexfund);
      return super.execute(inputMap);

   }


}
