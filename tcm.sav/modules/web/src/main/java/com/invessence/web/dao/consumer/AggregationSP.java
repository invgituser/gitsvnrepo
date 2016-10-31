package com.invessence.web.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class AggregationSP extends StoredProcedure
{

   public AggregationSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:   // SP: sel_consolidated_position
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            break;
          default:
      }
      compile();
   }

   public Map loadDetailData(Long logonid)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", logonid);
      return super.execute(inputMap);
   }

}
