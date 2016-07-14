package com.invessence.web.data.consumer.tcm;

import java.util.ArrayList;

import com.invessence.emailer.data.MsgData;
import com.invessence.web.data.common.CustomerData;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.ProfileData;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.data.Portfolio;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/15/16
 * Time: 4:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class TCMCustomer extends CustomerData
{
   public TCMRiskCalculator riskCalculator = new TCMRiskCalculator();
   ArrayList<ProjectionData[]> projectionDatas = null;


   @Override
   public Integer getAge()
   {
      return riskCalculator.getRiskAge();
   }

   @Override
   public void setAge(Integer age)
   {
      riskCalculator.setRiskAge(age);
      this.age = age;
   }

   @Override
   public Integer getHorizon()
   {
      return riskCalculator.getRiskHorizon();
   }

   @Override
   public void setHorizon(Integer horizon)
   {
      riskCalculator.setRiskHorizon(horizon);
      this.horizon = horizon;
   }


   @Override
   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
   }

   @Override
   public void setRiskCalcMethod(String formula) {
      riskCalculator.setRiskFormula("C");
   }

   public TCMRiskCalculator getRiskCalculator()
   {
      return riskCalculator;
   }

   public void setRiskCalculator(TCMRiskCalculator riskCalculator)
   {
      this.riskCalculator = riskCalculator;
   }

   @Override
   public void resetCustomerData() {
      // Master ProfileData
      // setName	(null);  Being reset at bottom.
      resetPortfolioData();

      // Manage Goal Data.
      setAcctnum(null);
      setClientAccountID(null);
      setLogonid(null);
      setUserid(null);
      setPortfolioName(null);
      setAddmodflag(null);
      setDateOpened(null);
      setManaged(false);

      // Income/Expenses
      setHouseholdwages(null);
      setMortgagePayment	(null);
      setOtherIncome	(null);
      setOtherExpense	(null);

      // Asset/Liability
      setMoneymarket	(null);
      setAutoLoan	(null);
      setInvestment	(null);
      setMedical	(null);
      setMortgateEquity	(null);
      setMortgageLoan	(null);
      setOtherSavings	(null);
      setOtherDebt	(null);
      setStock(null);
      setBond(null);
      setAccrual(null);

      riskCalculator.resetAllData();

      setModel("D");
      setAssetyear(0);

      setEmail(null);
      setFirstname(null);
      setLastname(null);
      setRegisteredState(null);
      setUserAssetOverride(false);
      setName(null);
      setUserAssetOverride(false);

      if (displayPortfolioList != null)
         displayPortfolioList.clear();

      setSelectedPortfolio(null);

      if (selectedPortfolioList != null)
         selectedPortfolioList.clear();

      setAssetAllocationTotal(0.0);
      setTotalSharesAllocated(0.0);
      setTotalMoneyAllocated(0.0);
      setManagedassetAllocationTotal(0.0);
      setManagedtotalMoney(0.0);

      if (excludedSubAsset != null)
         excludedSubAsset.clear();
      if (subassetList != null)
         subassetList.clear();
      if (orderedSubclass != null)
         orderedSubclass.clear();

      advisorBasket.clear();

   }


   @Override
   public void copyData(CustomerData newgoals) {
      // Master ProfileData
      //setName(newgoals.getName());  Being set at bottom
      setPortfolioName(newgoals.getPortfolioName());
      setAge(newgoals.getAge());
      setHorizon	(	newgoals.getHorizon	());
      setInitialInvestment	(	newgoals.getInitialInvestment	());
      setRecurringInvestment	(	newgoals.getRecurringInvestment	());
      setExperience	(	newgoals.getExperience	());
      setObjective	(	newgoals.getObjective	());
      setStayInvested	(	newgoals.getStayInvested	());
      setCharitableGoals	(	newgoals.getCharitableGoals	());
      setDependent	(	newgoals.getDependent	());
      setCurrentIncome	(	newgoals.getCurrentIncome	());
      setTotalIncome	(	newgoals.getTotalIncome	());
      setTotalExpense	(	newgoals.getTotalExpense	());
      setTotalAsset	(	newgoals.getTotalAsset	());
      setTotalLiability	(	newgoals.getTotalLiability	());
      setAccountTaxable	(	newgoals.getAccountTaxable	());
      setTaxrate	(	newgoals.getTaxrate	());
      setRiskIndex	(	newgoals.getRiskIndex	());

      // ManageGoal Data
      setAcctnum(newgoals.getAcctnum());
      setLogonid(newgoals.getLogonid());
      setUserid(newgoals.getUserid());
      setAddmodflag(newgoals.getAddmodflag());
      setGoal  (    newgoals.getGoal());
      setAccountType(newgoals.getAccountType());
      setName(newgoals.getName());
      setHouseholdwages(newgoals.getHouseholdwages());
      setMortgagePayment(newgoals.getMortgagePayment());
      setOtherIncome(newgoals.getOtherIncome());
      setOtherExpense(newgoals.getOtherExpense());
      setMoneymarket(newgoals.getMoneymarket());
      setAutoLoan(newgoals.getAutoLoan());
      setInvestment(newgoals.getInvestment());
      setMedical(newgoals.getMedical());
      setMortgateEquity(newgoals.getMortgateEquity());
      setMortgageLoan(newgoals.getMortgageLoan());
      setOtherSavings(newgoals.getOtherSavings());
      setOtherDebt(newgoals.getOtherDebt());
      setAssetData	(	newgoals.getAssetData	());
      setPortfolioData	(	newgoals.getPortfolioData	());
      setFirstname(newgoals.getFirstname());
      setLastname(newgoals.getLastname());
      setRegisteredState(newgoals.getRegisteredState());
      setUserAssetOverride(false);
      setName(newgoals.getFirstname() + " " + newgoals.getLastname());
   }

   public ArrayList<ProjectionData[]> getProjectionDatas()
   {
      return projectionDatas;
   }

   public void calcProjectionChart() {
      if (getModelUtil() != null) {
         projectionDatas = getModelUtil().buildProjectionData(getProfileInstance());
      }

   }






}
