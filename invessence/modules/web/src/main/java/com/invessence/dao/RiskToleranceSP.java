package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public class RiskToleranceSP extends StoredProcedure
{
   public RiskToleranceSP(DataSource datasource)
   {
      super(datasource, "sp_user_risk_questions");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_ans1", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans2", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans3", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans4", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans5", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans6", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans7", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans8", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans9", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans10", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans11", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans12", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans13", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans14", Types.TINYINT));
      declareParameter(new SqlParameter("p_ans15", Types.TINYINT));
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void updateGoals(ManageGoals data)
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
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_ans1", data.getSelectedchoice1());
      inputMap.put("p_ans2", data.getSelectedchoice2());
      inputMap.put("p_ans3", data.getSelectedchoice3());
      inputMap.put("p_ans4", data.getSelectedchoice4());
      inputMap.put("p_ans5", data.getSelectedchoice5());
      inputMap.put("p_ans6", data.getSelectedchoice6());
      inputMap.put("p_ans7", data.getSelectedchoice7());
      inputMap.put("p_ans8", data.getSelectedchoice8());
      inputMap.put("p_ans9", data.getSelectedchoice9());
      inputMap.put("p_ans10", data.getSelectedchoice10());
      inputMap.put("p_ans11", data.getSelectedchoice11());
      inputMap.put("p_ans12", data.getSelectedchoice12());
      inputMap.put("p_ans13", data.getSelectedchoice13());
      inputMap.put("p_ans14", data.getSelectedchoice14());
      inputMap.put("p_ans15", data.getSelectedchoice15());

      super.execute(inputMap);

   }

   public int checkData(Long acctnum)
   {
      String sql = "select count(*) from user_risk_questions where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
