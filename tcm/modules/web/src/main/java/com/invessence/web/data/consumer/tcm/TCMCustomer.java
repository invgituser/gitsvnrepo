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
   Integer retireAge;
   public TCMRiskCalculator riskCalculator = new TCMRiskCalculator();
   ArrayList<ProjectionData[]> projectionDatas = null;

   public Integer getRetireAge()
   {
      return retireAge;
   }

   public void setRetireAge(Integer retireAge)
   {
      this.retireAge = retireAge;
      if (getAge() > retireAge) {
        setHorizon(1);
      }
      else {
         setHorizon(retireAge - getAge());
      }
   }

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
      calcProjectionChart();
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
      calcProjectionChart();
   }


   @Override
   public void setInitialInvestment(Integer initialInvestment)
   {
      this.initialInvestment = initialInvestment;
      calcProjectionChart();
   }

   public TCMRiskCalculator getRiskCalculator()
   {
      return riskCalculator;
   }

   public void setRiskCalculator(TCMRiskCalculator riskCalculator)
   {
      this.riskCalculator = riskCalculator;
   }

   // Override methods from Customer Data
   public String getSelectedchoice(Integer index)
   {
      return riskCalculator.getAnswers()[index];
   }

   public void setSelectedchoice(Integer index, String value)
   {
      riskCalculator.setAnswer(index,value);
   }

   // Override methods from Customer Data
   @Override
   public String getSelectedchoice1()
   {
      return selectedchoice1;
   }

   @Override
   public void setSelectedchoice1(String selectedchoice)
   {
      this.selectedchoice1 = selectedchoice;
      setSelectedchoice(1, selectedchoice);
   }

   @Override
   public String getSelectedchoice2()
   {
      return selectedchoice2;
   }

   @Override
   public void setSelectedchoice2(String selectedchoice)
   {
      this.selectedchoice2 = selectedchoice;
      setSelectedchoice(2, selectedchoice);
   }

   @Override
   public String getSelectedchoice3()
   {
      return selectedchoice3;
   }

   @Override
   public void setSelectedchoice3(String selectedchoice)
   {
      this.selectedchoice3 = selectedchoice;
      setSelectedchoice(3, selectedchoice);
   }

   @Override
   public String getSelectedchoice4()
   {
      return selectedchoice4;
   }

   @Override
   public void setSelectedchoice4(String selectedchoice)
   {
      this.selectedchoice4 = selectedchoice;
      setSelectedchoice(4, selectedchoice);
   }

   @Override
   public String getSelectedchoice5()
   {
      return selectedchoice5;
   }

   @Override
   public void setSelectedchoice5(String selectedchoice)
   {
      this.selectedchoice5 = selectedchoice;
      setSelectedchoice(5, selectedchoice);
   }

   @Override
   public String getSelectedchoice6()
   {
      return selectedchoice6;
   }

   @Override
   public void setSelectedchoice6(String selectedchoice)
   {
      this.selectedchoice6 = selectedchoice;
      setSelectedchoice(6, selectedchoice);
   }

   @Override
   public String getSelectedchoice7()
   {
      return selectedchoice7;
   }

   @Override
   public void setSelectedchoice7(String selectedchoice)
   {
      this.selectedchoice7 = selectedchoice;
      setRiskAnswers(7,selectedchoice);
   }


   @Override
   public String getSelectedchoice8()
   {
      return selectedchoice8;
   }

   @Override
   public void setSelectedchoice8(String selectedchoice)
   {
      this.selectedchoice8 = selectedchoice;
      setSelectedchoice(8, selectedchoice);
   }

   @Override
   public String getSelectedchoice9()
   {
      return selectedchoice9;
   }

   @Override
   public void setSelectedchoice9(String selectedchoice)
   {
      this.selectedchoice9 = selectedchoice;
      setSelectedchoice(9, selectedchoice);
   }

   @Override
   public String getSelectedchoice10()
   {
      return selectedchoice10;
   }

   @Override
   public void setSelectedchoice10(String selectedchoice)
   {
      this.selectedchoice10 = selectedchoice;
      setSelectedchoice(10, selectedchoice);
   }

   @Override
   public String getSelectedchoice11()
   {
      return selectedchoice11;
   }

   @Override
   public void setSelectedchoice11(String selectedchoice)
   {
      this.selectedchoice11 = selectedchoice;
      setSelectedchoice(11, selectedchoice);
   }

   @Override
   public String getSelectedchoice12()
   {
      return selectedchoice12;
   }

   @Override
   public void setSelectedchoice12(String selectedchoice)
   {
      this.selectedchoice12 = selectedchoice;
      setSelectedchoice(12, selectedchoice);
   }

   @Override
   public String getSelectedchoice13()
   {
      return selectedchoice13;
   }

   @Override
   public void setSelectedchoice13(String selectedchoice)
   {
      this.selectedchoice13 = selectedchoice;
      setSelectedchoice(13, selectedchoice);
   }

   @Override
   public String getSelectedchoice14()
   {
      return selectedchoice14;
   }

   @Override
   public void setSelectedchoice14(String selectedchoice)
   {
      this.selectedchoice14 = selectedchoice;
      setSelectedchoice(14, selectedchoice);
   }

   @Override
   public String getSelectedchoice15()
   {
      return selectedchoice15;
   }

   @Override
   public void setSelectedchoice15(String selectedchoice)
   {
      this.selectedchoice15 = selectedchoice;
      setSelectedchoice(15, selectedchoice);
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
      setSelectedchoice1(newgoals.getSelectedchoice1());
      setSelectedchoice2(newgoals.getSelectedchoice2());
      setSelectedchoice3(newgoals.getSelectedchoice3());
      setSelectedchoice4(newgoals.getSelectedchoice4());
      setSelectedchoice5(newgoals.getSelectedchoice5());
      setSelectedchoice6(newgoals.getSelectedchoice6());
      setSelectedchoice7(newgoals.getSelectedchoice7());
      setSelectedchoice8(newgoals.getSelectedchoice8());
      setSelectedchoice9(newgoals.getSelectedchoice9());
      setSelectedchoice10(newgoals.getSelectedchoice10());
      setSelectedchoice11(newgoals.getSelectedchoice11());
      setSelectedchoice12(newgoals.getSelectedchoice12());
      setSelectedchoice13(newgoals.getSelectedchoice13());
      setSelectedchoice14(newgoals.getSelectedchoice14());
      setSelectedchoice15(newgoals.getSelectedchoice15());
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
      if (getInvmodel() != null) {
         projectionDatas = getInvmodel().buildProjectionData(getProfileInstance());
      }

   }




}
