package com.invessence.dao;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class CommonSP extends StoredProcedure
{

   public CommonSP(DataSource datasource, String sp_name)
   {
      super(datasource, sp_name);
      if (sp_name.equalsIgnoreCase("sp_validate_state")) {
         declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
         declareParameter(new SqlParameter("p_state", Types.VARCHAR));
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public String getStateData(Long acctnum, String state)
   {
      String info = "";
      Map inputMap = new HashMap();
      inputMap.put("p_logonid", acctnum);
      inputMap.put("p_state", state);
      Map outMap = super.execute(inputMap);
      if (outMap != null)
      {
         ArrayList<Map<String, Object>> rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
         int i = 0;
         for (Map<String, Object> map : rows)
         {
            Map rs = (Map) rows.get(i);
            info=getStrData(rs.get("license"));
            i++;
            break;
         }
      }
      return info;
   }

   private String getStrData(Object dataobj)
   {
      String val = null;
      try
      {
         if (dataobj != null)
         {
            val = dataobj.toString();
         }
         else
            val = "";
      }
      catch (Exception ex)
      {
         return "";
      }
      return val;
   }
}
