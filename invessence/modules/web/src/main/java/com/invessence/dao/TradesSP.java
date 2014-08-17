package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class TradesSP extends StoredProcedure
{

   public TradesSP(DataSource datasource)
   {
      super(datasource, "sel_trades");

      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getTrades(Long p_logonid, Long p_acctnum)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_logonid", p_logonid);
      inputMap.put("p_acctnum", p_acctnum);
      return super.execute(inputMap);
   }

}
