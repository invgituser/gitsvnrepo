package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/21/13
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetAndLiabilitiesSP extends StoredProcedure
{

   public AssetAndLiabilitiesSP(DataSource datasource)
   {
      super(datasource, "web_page2_user_trade_profile");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_dependent", Types.INTEGER));
      declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
      declareParameter(new SqlParameter("p_monthlywages", Types.BIGINT));
      declareParameter(new SqlParameter("p_otherIncome", Types.BIGINT));
      declareParameter(new SqlParameter("p_mortgagePayment", Types.BIGINT));
      declareParameter(new SqlParameter("p_otherExpense", Types.BIGINT));
      declareParameter(new SqlParameter("p_moneymarket", Types.BIGINT));
      declareParameter(new SqlParameter("p_investment", Types.BIGINT));
      declareParameter(new SqlParameter("p_mortgageEquity", Types.BIGINT));
      declareParameter(new SqlParameter("p_otherSavings", Types.BIGINT));
      declareParameter(new SqlParameter("p_autoLoan", Types.BIGINT));
      declareParameter(new SqlParameter("p_medical", Types.BIGINT));
      declareParameter(new SqlParameter("p_mortgageLoan", Types.BIGINT));
      declareParameter(new SqlParameter("p_otherDebt", Types.BIGINT));


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
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_dependent", data.getDependent());
      inputMap.put("p_riskIndex", data.getRiskIndex());
      inputMap.put("p_monthlywages", data.getHouseholdwages());
      inputMap.put("p_otherIncome", data.getOtherIncome());
      inputMap.put("p_mortgagePayment", data.getMortgagePayment());
      inputMap.put("p_otherExpense", data.getOtherExpense());
      inputMap.put("p_moneymarket", data.getMoneymarket());
      inputMap.put("p_investment", data.getInvestment());
      inputMap.put("p_mortgageEquity", data.getMortgateEquity());
      inputMap.put("p_otherSavings", data.getOtherSavings());
      inputMap.put("p_autoLoan", data.getAutoLoan());
      inputMap.put("p_medical", data.getMedical());
      inputMap.put("p_mortgageLoan", data.getMortgageLoan());
      inputMap.put("p_otherDebt", data.getOtherDebt());

      super.execute(inputMap);

   }

   public int checkData(Long acctnum)
   {
      String sql = "select count(*) from user_trade_profile where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
