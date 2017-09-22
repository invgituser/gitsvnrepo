package com.invessence.web.dao.common;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class PositionSP extends StoredProcedure
{

   public PositionSP(DataSource datasource, String storedProcName)
   {
      super(datasource, storedProcName);

      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
      declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
      compile();
   }

   public Map loadDBData(Long logonid, Long acctnum, String advisor, String rep)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_advisor", advisor);
      inputMap.put("p_rep", rep);
      return super.execute(inputMap);
   }

}
