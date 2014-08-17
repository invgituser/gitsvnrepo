package com.invessence.dao;

import java.sql.*;
import javax.sql.DataSource;

import com.invessence.data.*;
import org.springframework.jdbc.core.RowMapper;

/**
 * Created by ramesh on 1/27/14.
 */
public class PDFDataRetrieveSP extends AbsDataRetrieverByAccountNumber<PDFData>
{

   public PDFDataRetrieveSP(DataSource dataSource)
   {
      super(dataSource, "sel_user_trade_profile", new GoalsMapper());
   }

   static class GoalsMapper implements RowMapper<PDFData>
   {
      @Override
      public PDFData mapRow(ResultSet resultSet, int i) throws SQLException
      {
         PDFData pdfData = new PDFData();
         pdfData.setName(resultSet.getString("acctType"));
         pdfData.setGoal(resultSet.getString("goal"));
         pdfData.setAccttype(resultSet.getString("acctType"));
         pdfData.setAge(resultSet.getInt("age"));
         pdfData.setHorizon(resultSet.getInt("horizon"));
         pdfData.setInitialInvestment(resultSet.getInt("initialinvestment"));
         pdfData.setObjective(resultSet.getInt("longTermGoal"));
         pdfData.setExperience(resultSet.getInt("experience"));
         pdfData.setStayInvested(resultSet.getInt("stayInvested"));
         pdfData.setRecurringInvestment(resultSet.getInt("recurringInvestment"));
         pdfData.setDependent(resultSet.getInt("dependent"));
         pdfData.setMonthlywages(resultSet.getInt("householdwages"));
         pdfData.setOtherIncome(resultSet.getInt("otherincome"));
         pdfData.setMortgagePayment(resultSet.getInt("householdPayment"));
         pdfData.setOtherExpense(resultSet.getInt("miscExpenses"));

         pdfData.setMoneymarket(resultSet.getInt("moneyMarket"));
         pdfData.setInvestment(resultSet.getInt("investment"));
         pdfData.setMortgateEquity(resultSet.getInt("homeEquity"));
         pdfData.setOtherSavings(resultSet.getInt("miscInvestment"));

         pdfData.setAutoLoan(resultSet.getInt("autoLoan"));
         pdfData.setMedical(resultSet.getInt("medicalDebt"));
         pdfData.setMortgageLoan(resultSet.getInt("mortgageLoan"));
         pdfData.setOtherDebt(resultSet.getInt("otherDebt"));

        return pdfData;
      }
   }
}
