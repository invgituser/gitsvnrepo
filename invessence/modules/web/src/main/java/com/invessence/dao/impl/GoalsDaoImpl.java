package com.invessence.dao.impl;

import java.io.Serializable;
import java.util.*;

import com.invessence.dao.*;
import com.invessence.data.*;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class GoalsDaoImpl extends JdbcDaoSupport implements Serializable, GoalsDao
{

   /**
    *
    */
   private static final long serialVersionUID = 4864374021670809836L;

   public Long addGoals(ManageGoals goals)
   {

      GoalsSP goalsSP = new GoalsSP(getDataSource());
      Map goalsOutMap = goalsSP.updateGoals(goals);
      Long acctnum = ((Long) goalsOutMap.get("p_acctnum")).longValue();
      goals.setAcctnum(acctnum);
      return (acctnum);
   }

   public void addAssetAndLiabilities(ManageGoals goals)
   {

      AssetAndLiabilitiesSP assetAndLiabilitiesSP = new AssetAndLiabilitiesSP(getDataSource());
      assetAndLiabilitiesSP.updateGoals(goals);
   }

   public void addRiskTolerance(ManageGoals goals)
   {

      RiskIndexSP riskIndexSP = new RiskIndexSP(getDataSource());
      riskIndexSP.updateRiskIndex(goals);
      RiskToleranceSP riskToleranceSP = new RiskToleranceSP(getDataSource());
      riskToleranceSP.updateGoals(goals);
   }

   public ManageGoals findGoals(ManageGoals goals)
   {
      GoalsRetrieveSP goalsRetrieveSP = new GoalsRetrieveSP(getDataSource());
      return goalsRetrieveSP.execute(goals.getAcctnum(), goals);
   }

   public PDFData getPDFData(Long accountNum)
   {
      PDFDataRetrieveSP pdfDataRetrieveSP = new PDFDataRetrieveSP(getDataSource());
      PDFData pdfData = new PDFData();
      return pdfDataRetrieveSP.execute(accountNum, pdfData);
   }


   public ManageGoals findRiskTolerance(ManageGoals goals)
   {
      RiskRetrieveSP riskRetrieveSP = new RiskRetrieveSP(getDataSource());
      return riskRetrieveSP.execute(goals.getAcctnum(), goals);
   }

   public void saveAllocation(ManageGoals goals)
   {
      AllocationSP allocationSP = new AllocationSP(getDataSource());

      int row = allocationSP.checkAllocation(goals.getAcctnum());
      if (row > 0){
         // System.out.println("Deleting Allocation Record.");
         AllocationDelSP allocationDelSP = new AllocationDelSP(getDataSource());
         allocationDelSP.deleteAllocation(goals);
      }
      allocationSP.updateAllocation(goals);
   }

   public void addPortfolio(ManageGoals goals)
   {

      PortfolioSP portfolioSP = new PortfolioSP(getDataSource());

      int row = portfolioSP.checkPortfolio(goals.getAcctnum());
      if (row > 0){
         //System.out.println("Deleting Portfolio Record.");
         PortfolioDelSP portfolioDelSP = new PortfolioDelSP(getDataSource());
         portfolioDelSP.deletePortfolio(goals);
      }
      portfolioSP.updatePortfolio(goals);
   }

   public String validateState(Long acctnum, String state)
   {

      CommonSP commonSP = new CommonSP(getDataSource(), "sp_validate_state");
      String info = commonSP.getStateData(acctnum, state);
      return (info);
   }
}

