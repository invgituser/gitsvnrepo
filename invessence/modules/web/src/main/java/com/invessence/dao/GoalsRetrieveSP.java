package com.invessence.dao;

import com.invessence.converter.SQLData;
import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;


public class GoalsRetrieveSP extends AbsDataRetrieverByAccountNumber<ManageGoals>
{

   public GoalsRetrieveSP(DataSource dataSource)
   {
      super(dataSource, "sel_user_trade_profile", new GoalsMapper());
   }

   static class GoalsMapper implements RowMapper<ManageGoals>
   {
      @Override
      public ManageGoals mapRow(ResultSet rs, int i) throws SQLException
      {
         ManageGoals manageGoals = new ManageGoals();
         manageGoals.setAcctnum(rs.getLong("acctnum"));
         manageGoals.setAdvisor(rs.getString("advisor"));
         manageGoals.setTheme(rs.getString("theme"));
         manageGoals.setGoal(rs.getString("goal"));
         manageGoals.setAccountType(rs.getString("acctType"));
         manageGoals.setAge(rs.getInt("age"));
         manageGoals.setHorizon(rs.getInt("horizon"));
         manageGoals.setInitialInvestment(rs.getInt("initialinvestment"));
         manageGoals.setObjective(rs.getInt("longTermGoal"));
         manageGoals.setExperience(rs.getInt("experience"));
         manageGoals.setStayInvested(rs.getInt("stayInvested"));
         manageGoals.setRecurringInvestment(rs.getInt("recurringInvestment"));
         manageGoals.setDependent(rs.getInt("dependent"));
         manageGoals.setHouseholdwages(rs.getInt("householdwages"));
         manageGoals.setOtherIncome(rs.getInt("otherincome"));
         manageGoals.setMortgagePayment(rs.getInt("householdPayment"));
         manageGoals.setOtherExpense(rs.getInt("miscExpenses"));

         manageGoals.setMoneymarket(rs.getInt("moneyMarket"));
         manageGoals.setInvestment(rs.getInt("investment"));
         manageGoals.setMortgateEquity(rs.getInt("homeEquity"));
         manageGoals.setOtherSavings(rs.getInt("miscInvestment"));

         manageGoals.setAutoLoan(rs.getInt("autoLoan"));
         manageGoals.setMedical(rs.getInt("medicalDebt"));
         manageGoals.setMortgageLoan(rs.getInt("mortgageLoan"));
         manageGoals.setOtherDebt(rs.getInt("otherDebt"));

         manageGoals.setRiskIndex(rs.getInt("riskIndex"));
         manageGoals.setUserid(rs.getString("email"));
         manageGoals.setEmail(rs.getString("email"));
         manageGoals.setLastname(rs.getString("lastname"));
         manageGoals.setFirstname(rs.getString("firstname"));
         manageGoals.setRegisteredState(rs.getString("state"));
         return manageGoals;
      }
   }
}
