package com.invessence.dao.advisor;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.data.admin.AdminTradeClient;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;


public class AdvisorListSP extends StoredProcedure
{

   public AdvisorListSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_filter", Types.VARCHAR));
            break;
         case 1:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 2:
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            break;
         case 3:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 4:
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map collectProfileData(Long acctnum, String filter)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_filter", filter);
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getProfileData(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map collectBasket(String advisor)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_advisor", advisor);
      return super.execute(inputMap);
   }

   public Map collectAllocation(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

   public Map collectSubClassExclusionList(Long acctnum)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      return super.execute(inputMap);
   }

}
