package com.invessence.dao;

import com.invessence.data.ManageGoals;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetAndLiabilitiesRetrieveSP extends AbsDataRetrieverByAccountNumber<ManageGoals>
{

   public AssetAndLiabilitiesRetrieveSP(DataSource dataSource)
   {
      super(dataSource, "sel_user_trade_profile", new AssetAndLiabilitiesMapper());
   }

   static class AssetAndLiabilitiesMapper implements RowMapper<ManageGoals>
   {
      @Override
      public ManageGoals mapRow(ResultSet resultSet, int i) throws SQLException
      {
         ManageGoals manageGoals = new ManageGoals();
         manageGoals.setDependent(resultSet.getInt("dependent"));

         manageGoals.setHouseholdwages(resultSet.getInt("householdwages"));
         manageGoals.setOtherIncome(resultSet.getInt("otherincome"));
         manageGoals.setMortgagePayment(resultSet.getInt("householdPayment"));
         manageGoals.setOtherExpense(resultSet.getInt("miscExpenses"));

         manageGoals.setMoneymarket(resultSet.getInt("moneymarket"));
         manageGoals.setInvestment(resultSet.getInt("investment"));
         manageGoals.setMortgateEquity(resultSet.getInt("mortgateEquity"));
         manageGoals.setOtherSavings(resultSet.getInt("miscInvestment"));

         manageGoals.setMortgageLoan(resultSet.getInt("mortgageLoan"));
         manageGoals.setMedical(resultSet.getInt("medical"));
         manageGoals.setAutoLoan(resultSet.getInt("autoLoan"));
         manageGoals.setOtherDebt(resultSet.getInt("otherDebt"));

         return manageGoals;
      }
   }
}
