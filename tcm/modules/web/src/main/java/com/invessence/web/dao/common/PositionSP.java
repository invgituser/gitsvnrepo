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

      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      compile();
   }

   public Map loadDBData(Long p_acctnum)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", p_acctnum);
      return super.execute(inputMap);
   }

}
