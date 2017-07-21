package com.invessence.web.dao.consumer;


import java.sql.Types;
import java.util.*;
import javax.sql.DataSource;

import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.CTO.ClientData;
import com.invessence.web.data.consumer.RiskCalculator;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.portfolio.data.*;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.object.StoredProcedure;

@SuppressWarnings({"unchecked", "DuplicateStringLiteralInspection"})
public class ConsumerSaveSP extends StoredProcedure
{

   public ConsumerSaveSP(DataSource datasource, String sp_name, Integer mode)
   {
      super(datasource, sp_name);
      switch (mode) {
         case 0:  // save_user_trade_profile
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlInOutParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_portfolioName", Types.VARCHAR));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_theme", Types.VARCHAR));
            declareParameter(new SqlParameter("p_goal", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctType", Types.VARCHAR));
            declareParameter(new SqlParameter("p_taxable", Types.VARCHAR));
            declareParameter(new SqlParameter("p_age", Types.INTEGER));
            declareParameter(new SqlParameter("p_horizon", Types.INTEGER));
            declareParameter(new SqlParameter("p_initialInvestment", Types.INTEGER));
            declareParameter(new SqlParameter("p_recurringInvestment", Types.INTEGER));
            declareParameter(new SqlParameter("p_experience", Types.TINYINT));
            declareParameter(new SqlParameter("p_objective", Types.TINYINT));
            declareParameter(new SqlParameter("p_investmentplan", Types.TINYINT));
            declareParameter(new SqlParameter("p_charitablegoals", Types.INTEGER));
            declareParameter(new SqlParameter("p_keepLiquid", Types.INTEGER));
            declareParameter(new SqlParameter("p_riskIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_riskCalcMethod", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_portfolioIndex", Types.INTEGER));
            declareParameter(new SqlParameter("p_goalDesired", Types.DOUBLE));
            break;
         case 1:  // save_user_financial_data
            declareParameter(new SqlParameter(	"p_acctnum"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_dependent"	, Types.INTEGER	))	;
            declareParameter(new SqlParameter(	"p_estdDependentExpense"	, Types.BIGINT	))	;

            declareParameter(new SqlParameter(	"p_householdwages"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_otherincome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_bonusincome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_interestincome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_dividentincome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_rentalIncome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalIncome"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalIncomeAnnulized"	, Types.BIGINT	))	;

            declareParameter(new SqlParameter(	"p_householdPayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_otherPropertiesPayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_automobilePayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_medicalPayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_federaltaxes"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_stateTaxes"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_propertyTax"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_otherPropertyTax"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_homeInsurance"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_lifeInsurance"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_autoInsurance"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_educationPayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_creditCardPayment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_miscExpenses"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalExpense"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalExpenseAnnulized"	, Types.BIGINT	))	;

            declareParameter(new SqlParameter(	"p_homeEquity"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_autoValue"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_moneyMarket"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_checkingAcct"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_savingAcct"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_investment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_equityOtherProperties"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_retirementInvestement"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_miscInvestment"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalAsset"	, Types.BIGINT	))	;

            declareParameter(new SqlParameter(	"p_mortgageLoan"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_autoLoan"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_educationLoan"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_creditCardDebt"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_otherPropertiesLoan"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_medicalDebt"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_otherDebt"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_totalDebt"	, Types.BIGINT	))	;

            declareParameter(new SqlParameter(	"p_liquidnetworth"	, Types.BIGINT	))	;
            declareParameter(new SqlParameter(	"p_networth"	, Types.BIGINT	))	;
            break;
         case 2: // updt_user_risk_index
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_investmentgoal", Types.VARCHAR));
            declareParameter(new SqlParameter("p_age", Types.TINYINT));
            declareParameter(new SqlParameter("p_retireage", Types.TINYINT));
            declareParameter(new SqlParameter("p_retired", Types.TINYINT));
            declareParameter(new SqlParameter("p_horizon", Types.TINYINT));
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
            declareParameter(new SqlParameter("p_formula", Types.VARCHAR));
            declareParameter(new SqlParameter("p_r1", Types.TINYINT));
            declareParameter(new SqlParameter("p_r2", Types.TINYINT));
            declareParameter(new SqlParameter("p_r3", Types.TINYINT));
            declareParameter(new SqlParameter("p_r4", Types.TINYINT));
            declareParameter(new SqlParameter("p_r5", Types.TINYINT));
            declareParameter(new SqlParameter("p_r6", Types.TINYINT));
            declareParameter(new SqlParameter("p_r7", Types.TINYINT));
            declareParameter(new SqlParameter("p_r8", Types.TINYINT));
            declareParameter(new SqlParameter("p_r9", Types.TINYINT));
            declareParameter(new SqlParameter("p_r10", Types.TINYINT));
            declareParameter(new SqlParameter("p_r11", Types.TINYINT));
            declareParameter(new SqlParameter("p_r12", Types.TINYINT));
            declareParameter(new SqlParameter("p_r13", Types.TINYINT));
            declareParameter(new SqlParameter("p_r14", Types.TINYINT));
            declareParameter(new SqlParameter("p_r15", Types.TINYINT));
            declareParameter(new SqlParameter("p_totalRisk", Types.TINYINT));
            break;
         case 3:   // Not used (Open)
            break;
         case 4:   // del_asset_alloc
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.TINYINT));
            break;
         case 5:  // sp_asset_alloc_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_assetclass", Types.VARCHAR));
            declareParameter(new SqlParameter("p_themecode", Types.VARCHAR));
            declareParameter(new SqlParameter("p_allocationmodel", Types.VARCHAR));
            declareParameter(new SqlParameter("p_assetyear", Types.INTEGER));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_weight", Types.FLOAT));
            break;
         case 6:  // del_virtual_portfolio
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         case 7:  // sp_virtual_portfolio_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_itemnum", Types.INTEGER));
            declareParameter(new SqlParameter("p_ticker", Types.VARCHAR));
            declareParameter(new SqlParameter("p_active", Types.VARCHAR));
            declareParameter(new SqlParameter("p_qty", Types.INTEGER));
            declareParameter(new SqlParameter("p_weightByAsset", Types.FLOAT));
            declareParameter(new SqlParameter("p_tradeprice", Types.FLOAT));
            declareParameter(new SqlParameter("p_investmentvalue", Types.DOUBLE));
            //declareParameter(new SqlParameter("p_weightByPortfolio", Types.DOUBLE));
            break;
         case 8: // sp_clientinfo_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_prefix", Types.VARCHAR));
            declareParameter(new SqlParameter("p_lastname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_middlename", Types.VARCHAR));
            declareParameter(new SqlParameter("p_firstname", Types.VARCHAR));
            declareParameter(new SqlParameter("p_suffix", Types.VARCHAR));
            declareParameter(new SqlParameter("p_address", Types.VARCHAR));
            declareParameter(new SqlParameter("p_address2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_city", Types.VARCHAR));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            declareParameter(new SqlParameter("p_country", Types.VARCHAR));
            declareParameter(new SqlParameter("p_zip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_addressalt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_address2alt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_cityalt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_statealt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_countryalt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_zipalt", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dob", Types.DATE));
            declareParameter(new SqlParameter("p_maritalstatus", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dependents", Types.VARCHAR));
            declareParameter(new SqlParameter("p_gender", Types.VARCHAR));
            declareParameter(new SqlParameter("p_citizensip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ssn", Types.VARCHAR));
            declareParameter(new SqlParameter("p_altselect", Types.TINYINT));
            break;
         case 9:   // sp_clientinfo_mod
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_dob", Types.DATE));
            declareParameter(new SqlParameter("p_maritalstatus", Types.VARCHAR));
            declareParameter(new SqlParameter("p_dependents", Types.VARCHAR));
            declareParameter(new SqlParameter("p_gender", Types.VARCHAR));
            declareParameter(new SqlParameter("p_citizensip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ssn", Types.VARCHAR));
            break;
         case 10: // sp_createTrades
            declareParameter(new SqlParameter("p_acctnum", Types.VARCHAR));
            break;
         case 11: // sp_clientinfo_add_mod
            declareParameter(new SqlParameter("p_addmodflag", Types.VARCHAR));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_logonid", Types.BIGINT));
            declareParameter(new SqlParameter("p_empstatus", Types.VARCHAR));
            declareParameter(new SqlParameter("p_employer", Types.VARCHAR));
            declareParameter(new SqlParameter("p_natureofbusiness", Types.VARCHAR));
            declareParameter(new SqlParameter("p_occupation", Types.VARCHAR));
            declareParameter(new SqlParameter("p_address", Types.VARCHAR));
            declareParameter(new SqlParameter("p_address2", Types.VARCHAR));
            declareParameter(new SqlParameter("p_city", Types.VARCHAR));
            declareParameter(new SqlParameter("p_state", Types.VARCHAR));
            declareParameter(new SqlParameter("p_country", Types.VARCHAR));
            declareParameter(new SqlParameter("p_zip", Types.VARCHAR));
            break;
         case 12:
            declareParameter(new SqlInOutParameter("p_demoid", Types.BIGINT));
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            declareParameter(new SqlParameter("p_advisor", Types.VARCHAR));
            declareParameter(new SqlParameter("p_rep", Types.VARCHAR));
            declareParameter(new SqlParameter("p_ip", Types.VARCHAR));
            declareParameter(new SqlParameter("p_source", Types.VARCHAR));
            declareParameter(new SqlParameter("p_data", Types.VARCHAR));
            break;
         case 13: // save_user_trade_profile_audit
            declareParameter(new SqlParameter("p_acctnum", Types.BIGINT));
            break;
         default:
      }
      compile();
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public int checkProfileData(Long acctnum)
   {
      String sql = "select count(*) from user_trade_profile where acctnum = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{acctnum});
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveProfileData(CustomerData data)
   {

      Double goalDesired = 0.0;
      Map inputMap = new HashMap();

      try
      {
         if (data.getLogonid() == null) {
            inputMap.put("p_logonid", 0L);
         }
         else{
            inputMap.put("p_logonid", data.getLogonid());
         }

         if (data.getAcctnum() == null) {
            inputMap.put("p_acctnum", 0L);
         }
         else{
            inputMap.put("p_acctnum", data.getAcctnum());
         }

         inputMap.put("p_portfolioName", data.getPortfolioName());
         inputMap.put("p_advisor", data.getAdvisor());
         inputMap.put("p_rep", data.getRep());
         inputMap.put("p_firstname", data.getFirstname());
         inputMap.put("p_lastname", data.getLastname());
         inputMap.put("p_theme", data.getTheme());
         inputMap.put("p_goal", data.getGoal());
         inputMap.put("p_acctType",data.getAccountType());

         if (data.isAccountTaxable()) {
            inputMap.put("p_taxable","Y");
            // inputMap.put("p_acctType", "Taxable");
         }
         else {
            inputMap.put("p_taxable","N");
            // inputMap.put("p_acctType", "Non-Taxable");
         }

         inputMap.put("p_age", data.getDefaultAge());
         inputMap.put("p_horizon", data.getDefaultHorizon());
         inputMap.put("p_initialInvestment", data.getDefaultInvestment());
         inputMap.put("p_recurringInvestment", data.getRecurringInvestment());
         inputMap.put("p_experience", data.getExperience());
         inputMap.put("p_objective", data.getObjective());
         inputMap.put("p_investmentplan", data.getStayInvested());
         inputMap.put("p_charitablegoals", 0);
         inputMap.put("p_keepLiquid", data.getKeepLiquid());
         inputMap.put("p_riskIndex", data.getRiskIndex());
         inputMap.put("p_riskCalcMethod", data.getRiskCalcMethod());
         inputMap.put("p_allocIndex", data.getAllocationIndex());
         inputMap.put("p_portfolioIndex", data.getPortfolioIndex());
         if (data.getGoalData() != null)
            goalDesired = data.getGoalData().getGoalDesired();
         inputMap.put("p_goalDesired", goalDesired);

         return super.execute(inputMap);

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }

      return inputMap;
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveFinancials(CustomerData data)
   {

      String addmodflag;
      Map inputMap = new HashMap();




      inputMap.put(	"p_acctnum"	,	data.getAcctnum());
      inputMap.put(	"p_dependent"	,	data.getAccountFinancials().getDependent());
      inputMap.put(	 "p_estdDependentExpense"	,	 data.getAccountFinancials().getEstdDependentExpense());

      inputMap.put(	 "p_householdwages" 	,	 data.getAccountFinancials().getHouseholdwages());
      inputMap.put(	 "p_otherincome"	,	 data.getAccountFinancials().getOtherincome());
      inputMap.put(	 "p_bonusincome"	,	 data.getAccountFinancials().getBonusincome());
      inputMap.put(	 "p_interestincome"	,	 data.getAccountFinancials().getInterestincome());
      inputMap.put(	 "p_dividentincome"	,	 data.getAccountFinancials().getDependent());
      inputMap.put(	 "p_rentalIncome"	,	 data.getAccountFinancials().getRentalIncome());
      inputMap.put(	 "p_totalIncome"	,	 data.getAccountFinancials().getTotalIncome());
      inputMap.put(	 "p_totalIncomeAnnulized"	,	 data.getAccountFinancials().getTotalIncomeAnnulized());

      inputMap.put(	 "p_householdPayment"	,	 data.getAccountFinancials().getHouseholdPayment());
      inputMap.put(	 "p_otherPropertiesPayment"	,	 data.getAccountFinancials().getOtherPropertiesPayment());
      inputMap.put(	 "p_automobilePayment"	,	 data.getAccountFinancials().getAutomobilePayment());
      inputMap.put(	 "p_medicalPayment"	,	 data.getAccountFinancials().getMedicalDebt());
      inputMap.put(	 "p_federaltaxes"	,	 data.getAccountFinancials().getFederaltaxes());
      inputMap.put(	 "p_stateTaxes"	,	 data.getAccountFinancials().getStateTaxes());
      inputMap.put(	 "p_propertyTax"	,	 data.getAccountFinancials().getPropertyTax());
      inputMap.put(	 "p_otherPropertyTax"	,	 data.getAccountFinancials().getOtherPropertyTax());

       inputMap.put(	 "p_homeInsurance"	,	 data.getAccountFinancials().getHomeInsurance());
      inputMap.put(	 "p_lifeInsurance"	,	 data.getAccountFinancials().getLifeInsurance());
      inputMap.put(	 "p_autoInsurance"	,	 data.getAccountFinancials().getAutoInsurance());
      inputMap.put(	 "p_educationPayment"	,	 data.getAccountFinancials().getEducationPayment());
      inputMap.put(	 "p_creditCardPayment"	,	 data.getAccountFinancials().getCreditCardPayment());
      inputMap.put(	 "p_miscExpenses"	,	 data.getAccountFinancials().getMiscExpenses());
      inputMap.put(	 "p_totalExpense"	,	 data.getAccountFinancials().getTotalExpense());
      inputMap.put(	 "p_totalExpenseAnnulized"	,	 data.getAccountFinancials().getTotalExpenseAnnulized());

       inputMap.put(	 "p_homeEquity"	,	 data.getAccountFinancials().getHomeEquity());
      inputMap.put(	 "p_autoValue"	,	 data.getAccountFinancials().getAutoValue());
      inputMap.put(	 "p_moneyMarket"	,	 data.getAccountFinancials().getMoneyMarket());
      inputMap.put(	 "p_checkingAcct"	,	 data.getAccountFinancials().getCheckingAcct());
      inputMap.put(	 "p_savingAcct"	,	 data.getAccountFinancials().getSavingAcct());
      inputMap.put(	 "p_investment"	,	 data.getAccountFinancials().getInvestment());
      inputMap.put(	 "p_equityOtherProperties"	,	 data.getAccountFinancials().getEquityOtherProperties());
      inputMap.put(	 "p_retirementInvestement"	,	 data.getAccountFinancials().getRetirementInvestement());
      inputMap.put(	 "p_miscInvestment"	,	 data.getAccountFinancials().getMiscInvestment());
      inputMap.put(	 "p_totalAsset"	,	 data.getAccountFinancials().getTotalAsset());

      inputMap.put(	 "p_mortgageLoan"	,	 data.getAccountFinancials().getMortgageLoan());
      inputMap.put(	 "p_autoLoan"	,	 data.getAccountFinancials().getAutoLoan());
      inputMap.put(	 "p_educationLoan"	,	 data.getAccountFinancials().getEducationLoan());
      inputMap.put(	 "p_creditCardDebt"	,	 data.getAccountFinancials().getCreditCardDebt());
      inputMap.put(	 "p_otherPropertiesLoan"	,	 data.getAccountFinancials().getOtherPropertiesLoan());
      inputMap.put(	 "p_medicalDebt"	,	 data.getAccountFinancials().getMedicalDebt());
      inputMap.put(	 "p_otherDebt"	,	 data.getAccountFinancials().getOtherDebt());
      inputMap.put(	 "p_totalDebt"	,	 data.getAccountFinancials().getTotalDebt());

      inputMap.put(	 "p_liquidnetworth"	,	 data.getAccountFinancials().getLiquidnetworth());
      inputMap.put(	 "p_networth"	,	 data.getAccountFinancials().getNetworth());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveRiskProfile(Long acctnum, RiskCalculator data)
   {

      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", acctnum);
      inputMap.put("p_investmentgoal", data.getInvestmentobjective());
      inputMap.put("p_age", setDefaultRisk(data.getRiskAge()));
      inputMap.put("p_retireage", setDefaultRisk(data.getRetireAge()));
      inputMap.put("p_retired", setDefaultRisk(data.getRetired()));
      inputMap.put("p_horizon", setDefaultRisk(data.getRiskHorizon()));
      inputMap.put("p_ans1", setDefaultRisk(data.getAnswerValue(1)));
      inputMap.put("p_ans2", setDefaultRisk(data.getAnswerValue(2)));
      inputMap.put("p_ans3", setDefaultRisk(data.getAnswerValue(3)));
      inputMap.put("p_ans4", setDefaultRisk(data.getAnswerValue(4)));
      inputMap.put("p_ans5", setDefaultRisk(data.getAnswerValue(5)));
      inputMap.put("p_ans6", setDefaultRisk(data.getAnswerValue(6)));
      inputMap.put("p_ans7", setDefaultRisk(data.getAnswerValue(7)));
      inputMap.put("p_ans8", setDefaultRisk(data.getAnswerValue(8)));
      inputMap.put("p_ans9", setDefaultRisk(data.getAnswerValue(9)));
      inputMap.put("p_ans10", setDefaultRisk(data.getAnswerValue(10)));
      inputMap.put("p_ans11", setDefaultRisk(data.getAnswerValue(11)));
      inputMap.put("p_ans12", setDefaultRisk(data.getAnswerValue(12)));
      inputMap.put("p_ans13", setDefaultRisk(data.getAnswerValue(13)));
      inputMap.put("p_ans14", setDefaultRisk(data.getAnswerValue(14)));
      inputMap.put("p_ans15", setDefaultRisk(data.getAnswerValue(15)));
      inputMap.put("p_formula", data.getRiskFormula());
      inputMap.put("p_r1", setDefaultRisk(data.getRiskValue(1)));
      inputMap.put("p_r2", setDefaultRisk(data.getRiskValue(2)));
      inputMap.put("p_r3", setDefaultRisk(data.getRiskValue(3)));
      inputMap.put("p_r4", setDefaultRisk(data.getRiskValue(4)));
      inputMap.put("p_r5", setDefaultRisk(data.getRiskValue(5)));
      inputMap.put("p_r6", setDefaultRisk(data.getRiskValue(6)));
      inputMap.put("p_r7", setDefaultRisk(data.getRiskValue(7)));
      inputMap.put("p_r8", setDefaultRisk(data.getRiskValue(8)));
      inputMap.put("p_r9", setDefaultRisk(data.getRiskValue(9)));
      inputMap.put("p_r10", setDefaultRisk(data.getRiskValue(10)));
      inputMap.put("p_r11", setDefaultRisk(data.getRiskValue(11)));
      inputMap.put("p_r12", setDefaultRisk(data.getRiskValue(12)));
      inputMap.put("p_r13", setDefaultRisk(data.getRiskValue(13)));
      inputMap.put("p_r14", setDefaultRisk(data.getRiskValue(14)));
      inputMap.put("p_r15", setDefaultRisk(data.getRiskValue(15)));
      inputMap.put("p_totalRisk", setDefaultRisk(data.getTotalRisk().intValue()));

      super.execute(inputMap);

   }

   private Integer setDefaultRisk(Integer riskValue) {
      return ((riskValue == null) ? 0:  riskValue);
   }

   public void deleteAllocation(CustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_allocationmodel", 'D');
      inputMap.put("p_assetyear", data.getAssetyear());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveAllocation(CustomerData data)
   {

      String addmodflag;
      int rowExists = checkProfileData(data.getAcctnum());
      if (rowExists != 0)
      {
         addmodflag = "M";
      }
      else
      {
         addmodflag = "A";
      }

      AssetClass aac[] = data.getAssetData();
      int rowSize = aac[0].getOrderedAsset().size();

      for (int loop = 0; loop < rowSize; loop++)
      {
         Map inputAssetMap = new HashMap();
         String assetname = aac[0].getOrderedAsset().get(loop);
         inputAssetMap.put("p_addmodflag", addmodflag);
         inputAssetMap.put("p_acctnum", data.getAcctnum());
         inputAssetMap.put("p_assetclass", assetname);
         inputAssetMap.put("p_themecode", data.getTheme());
         inputAssetMap.put("p_allocationmodel", "D");
         inputAssetMap.put("p_assetyear", data.getAssetyear());
         inputAssetMap.put("p_active", "A");
         inputAssetMap.put("p_weight", data.getAssetData()[0].getAsset(assetname).getUserweight());
         super.execute(inputAssetMap);
      }
   }

   public void deletePortfolio(CustomerData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void savePortfolio(CustomerData data)
   {

      String addmodflag = "A";

      Portfolio pfclass[] = data.getPortfolioData();
      if (pfclass == null)
         return;
      int rowSize = pfclass[0].getPortfolio().size();

      for (int loop = 0; loop < rowSize; loop++)
      {
         PortfolioSecurityData pfList = pfclass[0].getPortfolio().get(loop);
         Map inputPortfolioMap = new HashMap();
         inputPortfolioMap.put("p_addmodflag", addmodflag);
         inputPortfolioMap.put("p_acctnum", data.getAcctnum());
         inputPortfolioMap.put("p_itemnum", loop + 1);
         inputPortfolioMap.put("p_ticker", pfList.getTicker());
         inputPortfolioMap.put("p_active", (data.getManaged() ? "A": ""));
         inputPortfolioMap.put("p_qty", pfList.getShares());
         inputPortfolioMap.put("p_weightByAsset", pfList.getWeight());
         inputPortfolioMap.put("p_tradeprice", pfList.getDailyprice());
         inputPortfolioMap.put("p_investmentvalue", pfList.getMoney());
         //inputPortfolioMap.put("p_weightByPortfolio", pfList.getTickerWeights());

         super.execute(inputPortfolioMap);
      }
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void createTrades(Long acctnum)
   {
      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      super.execute(inputMap);
   }


   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveClientInfo(ClientData data)
   {
      String addmodflag;
      Map inputMap = new HashMap();
      int rowExists = checkClientData(data.getLogonid());
      if (rowExists != 0)
      {
         addmodflag = "M";

      }
      else
      {
         addmodflag = "A";
      }
      System.out.println("addmodflag :" + addmodflag);

      inputMap.put("p_addmodflag", addmodflag);
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_prefix", data.getPrefix());
      inputMap.put("p_lastname", data.getLastName());
      inputMap.put("p_middlename", data.getMiddleName());
      inputMap.put("p_firstname", data.getFirstName());
      inputMap.put("p_suffix", data.getSuffix());
      inputMap.put("p_address", data.getAddress1());
      inputMap.put("p_address2", data.getAddress2());
      inputMap.put("p_city", data.getCity());
      inputMap.put("p_state", data.getStateName());
      inputMap.put("p_country", data.getCountry());
      inputMap.put("p_zip", data.getZipCode());
      inputMap.put("p_addressalt", data.getMailingAddress1());
      inputMap.put("p_address2alt", data.getMailingAddress2());
      inputMap.put("p_cityalt", data.getMailingCity());
      inputMap.put("p_statealt", data.getMailingStateName());
      inputMap.put("p_countryalt", data.getMailingCountry());
      inputMap.put("p_zipalt", data.getMailingZipCode());
      inputMap.put("p_dob", data.getDateOfBirth());
      inputMap.put("p_maritalstatus", data.getMaritalStatus());
      inputMap.put("p_dependents",data.getDependents());
      inputMap.put("p_gender", data.getGender());
      inputMap.put("p_citizensip", data.getCountryOfCitizenship());
      inputMap.put("p_ssn", data.getSocialSecurity());
      inputMap.put("p_altselect", data.isChecked());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveClientInfo2(ClientData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_dob", data.getDateOfBirth());
      inputMap.put("p_maritalstatus", data.getMaritalStatus());
      inputMap.put("p_dependents", data.getDependents());
      inputMap.put("p_gender", data.getGender());
      inputMap.put("p_citizensip", data.getCountryOfCitizenship());
      inputMap.put("p_ssn", data.getSocialSecurity());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void saveClientEmpInfo(ClientData data)
   {
      String addmodflag;
      Map inputMap = new HashMap();
      int rowExists = checkClientEmpData(data.getLogonid());
      if (rowExists != 0)
      {
         addmodflag = "M";

      }
      else
      {
         addmodflag = "A";
      }
      System.out.println("addmodflag :" + addmodflag);

      inputMap.put("p_addmodflag", addmodflag);
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_logonid", data.getLogonid());
      inputMap.put("p_empstatus", data.getEmploymentStatus());
      inputMap.put("p_employer", data.getEmployerName());
      inputMap.put("p_natureofbusiness", data.getNatureOfBusiness());
      inputMap.put("p_occupation", data.getOccupation());
      inputMap.put("p_address", data.getEmployerAddress1());
      inputMap.put("p_address2", data.getEmployerAddress2());
      inputMap.put("p_city", data.getEmployerCity());
      inputMap.put("p_state", data.getEmployerStateName());
      inputMap.put("p_country", data.getEmployerCountry());
      inputMap.put("p_zip", data.getEmployerZipCode());
      super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public int checkClientData(Long logonid)
   {
      String sql = "select count(*) from client_info where logonid = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{logonid});
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public int checkClientEmpData(Long logonid)
   {
      String sql = "select count(*) from client_emp_info where logonid = ?";
      return getJdbcTemplate().queryForInt(sql, new Object[]{logonid});
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public Map saveVisitor(UserData data)
   {
      Map inputMap = new HashMap();
      inputMap.put("p_demoid", data.getAcctnum());
      inputMap.put("p_acctnum", data.getAcctnum());
      inputMap.put("p_advisor", data.getAdvisor());
      inputMap.put("p_rep", data.getRep());
      inputMap.put("p_ip", data.getIp());
      inputMap.put("p_source", data.getLeadsource());
      inputMap.put("p_data", data.getEmail());
      return super.execute(inputMap);
   }

   @SuppressWarnings({"unchecked", "rawtypes"})
   public void archiveAllProfileData(Long acctnum)
   {
      Map inputMap = new HashMap();

      inputMap.put("p_acctnum", acctnum);
      super.execute(inputMap);
   }

}
