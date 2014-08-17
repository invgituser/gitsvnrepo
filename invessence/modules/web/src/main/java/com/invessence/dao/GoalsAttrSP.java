package com.invessence.dao;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.invessence.data.ManageGoals;

public class GoalsAttrSP extends StoredProcedure
{

   public GoalsAttrSP(DataSource datasource)
   {
      super(datasource, "sp_acct_risk_data_sel");


      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_attributename", Types.VARCHAR));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map getGoalsAttr(Long acctnum, String attributeName)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_attributename", attributeName);
      return super.execute(inputMap);

   }

   public int getLogonID(String userid)
   {
      String sql = "select logonid from user_logon where userid = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{userid});
   }
}
