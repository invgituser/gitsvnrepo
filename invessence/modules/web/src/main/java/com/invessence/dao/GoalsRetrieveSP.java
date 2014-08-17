package com.invessence.dao;

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
      public ManageGoals mapRow(ResultSet resultSet, int i) throws SQLException
      {
         ManageGoals manageGoals = new ManageGoals();
         manageGoals.setAcctnum(resultSet.getLong("acctnum"));
         manageGoals.setGoal(resultSet.getString("goal"));
         manageGoals.setAccountType(resultSet.getString("acctType"));
         manageGoals.setDisplayAge(resultSet.getInt("age"));
         manageGoals.setHorizon(resultSet.getInt("horizon"));
         manageGoals.setInitialInvestment(resultSet.getInt("initialinvestment"));
         manageGoals.setObjective(resultSet.getInt("longTermGoal"));
         manageGoals.setExperience(resultSet.getInt("experience"));
         manageGoals.setStayInvested(resultSet.getInt("stayInvested"));
         manageGoals.setRecurringInvestment(resultSet.getInt("recurringInvestment"));
         manageGoals.setDependent(resultSet.getInt("dependent"));
         manageGoals.setHouseholdwages(resultSet.getInt("householdwages"));
         manageGoals.setOtherIncome(resultSet.getInt("otherincome"));
         manageGoals.setMortgagePayment(resultSet.getInt("householdPayment"));
         manageGoals.setOtherExpense(resultSet.getInt("miscExpenses"));

         manageGoals.setMoneymarket(resultSet.getInt("moneyMarket"));
         manageGoals.setInvestment(resultSet.getInt("investment"));
         manageGoals.setMortgateEquity(resultSet.getInt("homeEquity"));
         manageGoals.setOtherSavings(resultSet.getInt("miscInvestment"));

         manageGoals.setAutoLoan(resultSet.getInt("autoLoan"));
         manageGoals.setMedical(resultSet.getInt("medicalDebt"));
         manageGoals.setMortgageLoan(resultSet.getInt("mortgageLoan"));
         manageGoals.setOtherDebt(resultSet.getInt("otherDebt"));

         manageGoals.setRiskIndex(resultSet.getInt("riskIndex"));
         manageGoals.setUserid(resultSet.getString("email"));
         manageGoals.setEmail(resultSet.getString("email"));
         manageGoals.setLastname(resultSet.getString("lastname"));
         manageGoals.setFirstname(resultSet.getString("firstname"));
         manageGoals.setRegisteredState(resultSet.getString("state"));
         return manageGoals;
      }
   }
}
