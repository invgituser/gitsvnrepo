package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class RiskIndexSP extends StoredProcedure
{
   public RiskIndexSP(DataSource datasource)
   {
      super(datasource, "web_page3_user_trade_profile");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_riskIndex", Types.TINYINT));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updateRiskIndex(ManageGoals data)
   {

      String addmodflag;
      int rowExists = checkData(data.getAcctnum());
      if (rowExists != 0)
      {
         addmodflag = "M";
      }
      else
      {
         addmodflag = "A";
      }
      Map inputMap = new HashMap();
      inputMap.put("p_addmodflag", addmodflag);
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_riskIndex", data.getRiskIndex());

      super.execute(inputMap);

   }

   public int checkData(Long acctnum)
   {
      String sql = "select count(*) from user_trade_profile where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
