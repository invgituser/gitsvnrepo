package com.invessence.dao;


import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlInOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import com.invessence.data.ManageGoals;

public class GoalsSP extends StoredProcedure
{

   public GoalsSP(DataSource datasource)
   {
      super(datasource, "web_page1_user_trade_profile");
      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlInOutParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
      declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
      declareParameter(new SqlParameter("p_goal", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctType", Types.VARCHAR));
      declareParameter(new SqlParameter("p_age", Types.INTEGER));
      declareParameter(new SqlParameter("p_horizon", Types.INTEGER));
      declareParameter(new SqlParameter("p_initialInvestment", Types.INTEGER));
      declareParameter(new SqlParameter("p_recurringInvestment", Types.INTEGER));
      declareParameter(new SqlParameter("p_experience", Types.TINYINT));
      declareParameter(new SqlParameter("p_objective", Types.TINYINT));
      declareParameter(new SqlParameter("p_investmentplan", Types.TINYINT));
      declareParameter(new SqlParameter("p_charitablegoals", Types.INTEGER));
      declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map updateGoals(ManageGoals data)
   {

      String addmodflag = "";
      int rowExists = 0;
      Map inputMap = new HashMap();

      try
      {
         if (data.getAcctnum() != null)
         {

            rowExists = checkData(data.getAcctnum());

         }

         if (rowExists != 0)
         {
            addmodflag = "M";
         }
         else
         {
            addmodflag = "A";
         }
         inputMap.put("p_addmodflag", addmodflag);
         if (data.getLogonid() != null)
         {
            inputMap.put("p_logonid", data.getLogonid());
         }

         if (data.getAcctnum() == null)
         {
            inputMap.put("p_acctnum", -1);
         }
         else
         {
            inputMap.put("p_acctnum", data.getAcctnum());
         }

         inputMap.put("p_advisor", data.getAdvisor());
         inputMap.put("p_theme", data.getTheme());
         inputMap.put("p_goal", data.getGoal());
         inputMap.put("p_acctType", data.getAccountType());
         inputMap.put("p_age", data.getAge());
         inputMap.put("p_horizon", data.getHorizon());
         inputMap.put("p_initialInvestment", data.getInitialInvestment());
         inputMap.put("p_recurringInvestment", data.getRecurringInvestment());
         inputMap.put("p_experience", data.getExperience());
         inputMap.put("p_objective", data.getObjective());
         inputMap.put("p_investmentplan", data.getStayInvested());
         inputMap.put("p_charitablegoals", 0);
         inputMap.put("p_riskIndex", data.getRiskIndex());
         return super.execute(inputMap);

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }

      return inputMap;
   }

   public int checkData(Long acctnum)
   {
      String sql = "select count(*) from user_trade_profile where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }

}
