package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.SqlInOutParameter;
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
 * Time: 2:41 PM
 * To change this template use File | Settings | File Templates.
 * Date      Name          Comment
 * --------- ------------- --------------
 * 11/27/13  Prashant      Not used.  Only using debt Liability page.
 */
public class IncomeAndSavingsSP extends StoredProcedure
{
/*    Not used Nov. 27th, 2013 - Prashant

   public IncomeAndSavingsSP(DataSource datasource)
   {
      super(datasource, "sp_acct_risk_data_add_mod");

      declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
      declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
      declareParameter(new SqlParameter("p_attributename", Types.VARCHAR));
      declareParameter(new SqlParameter("p_charvalue", Types.VARCHAR));
      declareParameter(new SqlParameter("p_numvalue", Types.INTEGER));
      declareParameter(new SqlParameter("p_datevalue", Types.DATE));
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
      Map inputCurrentIncomeMap = new HashMap();
      inputCurrentIncomeMap.put("p_addmodflag", addmodflag);
      inputCurrentIncomeMap.put("p_acctnum", data.getAcctnum());
      inputCurrentIncomeMap.put("p_attributename", "CurrentIncome");
      inputCurrentIncomeMap.put("p_charvalue", null);
      inputCurrentIncomeMap.put("p_numvalue", data.getCurrentIncome());
      inputCurrentIncomeMap.put("p_datevalue", null);

      Map inputTexRateMap = new HashMap();
      inputTexRateMap.put("p_addmodflag", addmodflag);
      inputTexRateMap.put("p_acctnum", data.getAcctnum());
      inputTexRateMap.put("p_attributename", "TaxRate");
      inputTexRateMap.put("p_charvalue", null);
      inputTexRateMap.put("p_numvalue", data.getTaxrate());
      inputTexRateMap.put("p_datevalue", null);

      Map inputAnnuitiesMap = new HashMap();
      inputAnnuitiesMap.put("p_addmodflag", addmodflag);
      inputAnnuitiesMap.put("p_acctnum", data.getAcctnum());
      inputAnnuitiesMap.put("p_attributename", "Annuities");
      inputAnnuitiesMap.put("p_charvalue", null);
      inputAnnuitiesMap.put("p_numvalue", data.getAnnuities());
      inputAnnuitiesMap.put("p_datevalue", null);

      Map inputInvestmentPropertiesMap = new HashMap();
      inputInvestmentPropertiesMap.put("p_addmodflag", addmodflag);
      inputInvestmentPropertiesMap.put("p_acctnum", data.getAcctnum());
      inputInvestmentPropertiesMap.put("p_attributename", "InvestmentProperties");
      inputInvestmentPropertiesMap.put("p_charvalue", null);
      inputInvestmentPropertiesMap.put("p_numvalue", data.getInvProperties());
      inputInvestmentPropertiesMap.put("p_datevalue", null);

      Map inputMoneyMarketMap = new HashMap();
      inputMoneyMarketMap.put("p_addmodflag", addmodflag);
      inputMoneyMarketMap.put("p_acctnum", data.getAcctnum());
      inputMoneyMarketMap.put("p_attributename", "MoneyMarket");
      inputMoneyMarketMap.put("p_charvalue", null);
      inputMoneyMarketMap.put("p_numvalue", data.getMoneyMarket());
      inputMoneyMarketMap.put("p_datevalue", null);

      Map inputMutualFundsMap = new HashMap();
      inputMutualFundsMap.put("p_addmodflag", addmodflag);
      inputMutualFundsMap.put("p_acctnum", data.getAcctnum());
      inputMutualFundsMap.put("p_attributename", "MutualFunds");
      inputMutualFundsMap.put("p_charvalue", null);
      inputMutualFundsMap.put("p_numvalue", data.getMutualfunds());
      inputMutualFundsMap.put("p_datevalue", null);

      Map inputInsurancePolicyMap = new HashMap();
      inputInsurancePolicyMap.put("p_addmodflag", addmodflag);
      inputInsurancePolicyMap.put("p_acctnum", data.getAcctnum());
      inputInsurancePolicyMap.put("p_attributename", "InsurancePolicy");
      inputInsurancePolicyMap.put("p_charvalue", null);
      inputInsurancePolicyMap.put("p_numvalue", data.getLifeInsurance());
      inputInsurancePolicyMap.put("p_datevalue", null);

      Map inputOtherSavingsMap = new HashMap();
      inputOtherSavingsMap.put("p_addmodflag", addmodflag);
      inputOtherSavingsMap.put("p_acctnum", data.getAcctnum());
      inputOtherSavingsMap.put("p_attributename", "OtherSavings");
      inputOtherSavingsMap.put("p_charvalue", null);
      inputOtherSavingsMap.put("p_numvalue", data.getOtherSavings());
      inputOtherSavingsMap.put("p_datevalue", null);

      super.execute(inputCurrentIncomeMap);
      super.execute(inputTexRateMap);
      super.execute(inputAnnuitiesMap);
      super.execute(inputInvestmentPropertiesMap);
      super.execute(inputMoneyMarketMap);
      super.execute(inputMutualFundsMap);
      super.execute(inputInsurancePolicyMap);
      super.execute(inputOtherSavingsMap);
   }
*/

   public int checkData(Long acctnum)
   {
      String sql = "select count(*) from acct_risk_data where acctnum = ? and attributename = 'OtherSavings'";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }
}
