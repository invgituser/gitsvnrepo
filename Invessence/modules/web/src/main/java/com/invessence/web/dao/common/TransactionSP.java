package com.invessence.web.dao.common;

import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * Created by sagar on 10/29/2017.
 */
public class TransactionSP extends StoredProcedure
{

   public TransactionSP(DataSource datasource, String storedProcName)
   {
      super(datasource, storedProcName);

      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
      declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
      declareParameter(new SqlParameter("p_trnstatus", Types.VARCHAR));
      compile();
   }


   public Map loadDBData(Long logonid, Long acctnum, String advisor, String rep,String transactionStatus)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_rep", rep);
      inputMap.put("p_trnstatus",transactionStatus);
      return super.execute(inputMap);
   }
}
