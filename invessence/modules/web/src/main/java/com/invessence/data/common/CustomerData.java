package com.invessence.data.common;

import java.util.*;

import javax.faces.bean.ManagedProperty;

import com.invessence.converter.JavaUtil;
import com.invessence.data.*;
import com.invessence.util.*;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.performance.PortfolioPerformance;
import com.invmodel.performance.data.PerformanceData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.*;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerData extends ProfileData
{
   private JavaUtil javautil = new JavaUtil();
   private CustomerData manageGoalinstance = null;

   @ManagedProperty("#{assetAllocationModel}")
   private AssetAllocationModel allocModel;
   private Boolean managed;

   @ManagedProperty("#{portfolioModel}")
   private PortfolioModel portfolioModel;

   @ManagedProperty("#{portfolioPerformance}")
   private PortfolioPerformance portfolioPerformance;

   private String userid;
   private String addmodflag;
   private String dateOpened;
   private String created;

   // Income/Expenses
   private Integer householdwages;
   private Integer mortgagePayment;
   private Integer otherIncome;
   private Integer otherExpense;

   // Asset/Liability
   private Integer moneymarket;
   private Integer autoLoan;
   private Integer investment;
   private Integer medical;
   private Integer mortgateEquity;
   private Integer mortgageLoan;
   private Integer otherSavings;
   private Integer otherDebt;
   private Double  stock;
   private Double  bond;
   private Double  accrual;


   private String selectedchoice1;
   private String selectedchoice2;
   private String selectedchoice3;
   private String selectedchoice4;
   private String selectedchoice5;
   private String selectedchoice6;
   private String selectedchoice7;
   private String selectedchoice8;
   private String selectedchoice9;
   private String selectedchoice10;
   private String selectedchoice11;
   private String selectedchoice12;
   private String selectedchoice13;
   private String selectedchoice14;
   private String selectedchoice15;

   private String model      = "D";
   private Integer assetyear = 0;
   private String registeredState;
   private Boolean userAssetOverride = false;

   private String portfolioName;

   private String email, firstname, lastname;
   private List<DataPortfolio> displayPortfolioList = new ArrayList<DataPortfolio>();
   private DataPortfolio selectedPortfolio;
   private List<DataPortfolio> selectedPortfolioList = null;
   private Double assetAllocationTotal = 0.0;
   private Double totalSharesAllocated = 0.0;
   private Double totalMoneyAllocated = 0.0;

   private Double  managedassetAllocationTotal = 0.0;
   private Double managedtotalMoney = 0.0;


   private String externalPositionFile;

   //private TreeNode subclassDisplayNode;
   //private TreeNode[] subclassFilterNode;
   Map<String, ManagedSubclassData> subassetList = new HashMap<String, ManagedSubclassData>();
   ArrayList<ManagedSubclassData> orderedSubclass;
   private List<PortfolioSubclass> excludedSubAsset = new ArrayList<PortfolioSubclass>();

   private Map<String, String> advisorBasket;


   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }


   public CustomerData()
   {
      super();
      this.manageGoalinstance = this;
   }

   public CustomerData getInstance()
   {
      return manageGoalinstance;
   }
   public Boolean getManaged()
   {
      return managed;
   }

   public void setManaged(Boolean managed)
   {
      this.managed = managed;
   }

   public AssetAllocationModel getAllocModel()
   {
      return allocModel;
   }

   public void setAllocModel(AssetAllocationModel allocModel)
   {
      this.allocModel = allocModel;
   }

   public PortfolioModel getPortfolioModel()
   {
      return portfolioModel;
   }

   public void setPortfolioModel(PortfolioModel portfolioModel)
   {
      this.portfolioModel = portfolioModel;
   }

   public PortfolioPerformance getPortfolioPerformance()
   {
      return portfolioPerformance;
   }

   public void setPortfolioPerformance(PortfolioPerformance portfolioPerformance)
   {
      this.portfolioPerformance = portfolioPerformance;
   }

   public Double getTotalRisk() {
      Double value = 0.0;
      if (getPortfolioData() != null) {
        if (getAssetyear() != null && getPortfolioData().length > getAssetyear())
           value = getPortfolioData()[getAssetyear()].getTotalRisk();
        else
           value = getPortfolioData()[0].getTotalRisk();
      }
      return value;
   }

   public Double getTotalExpectedReturns() {
      Double value = 0.0;
      if (getPortfolioData() != null) {
         if (getAssetyear() != null && getPortfolioData().length > getAssetyear())
            value = getPortfolioData()[getAssetyear()].getExpReturns();
         else
            value = getPortfolioData()[0].getExpReturns();
      }
      return value;
   }

   public ProfileData getSubInstance()
   {
      return manageGoalinstance;
   }

   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
   }

   public String getAddmodflag()
   {
      return addmodflag;
   }

   public void setAddmodflag(String addmodflag)
   {
      this.addmodflag = addmodflag;
   }


   public String getAcctstatus()
   {
      return (getManaged() ? "Active" : "Pending");
   }

   public String getDisplayDateOpened()
   {
      return javautil.displayDateFormat(dateOpened);
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }

   public Integer getHouseholdwages()
   {
      return householdwages;
   }

   public void setHouseholdwages(Integer householdwages)
   {
      this.householdwages = householdwages;
      setCurrentIncome(convertNumber(householdwages));
      addTotalIncome();
   }

   public Integer getMortgagePayment()
   {
      return mortgagePayment;
   }

   public void setMortgagePayment(Integer mortgagePayment)
   {
      this.mortgagePayment = mortgagePayment;
      addTotalExpense();
   }

   public Integer getOtherIncome()
   {
      return otherIncome;
   }

   public void setOtherIncome(Integer otherIncome)
   {
      this.otherIncome = otherIncome;
      addTotalIncome();
   }

   public Integer getOtherExpense()
   {
      return otherExpense;
   }

   public void setOtherExpense(Integer otherExpense)
   {
      this.otherExpense = otherExpense;
      addTotalExpense();
   }

   public Integer getMoneymarket()
   {
      return moneymarket;
   }

   public void setMoneymarket(Integer moneymarket)
   {
      this.moneymarket = moneymarket;
      addTotalAsset();
   }

   public Integer getAutoLoan()
   {
      return autoLoan;
   }

   public void setAutoLoan(Integer autoLoan)
   {
      this.autoLoan = autoLoan;
      addTotalLiability();
   }

   public Integer getInvestment()
   {
      return investment;
   }

   public void setInvestment(Integer investment)
   {
      this.investment = investment;
      addTotalAsset();
   }

   public Integer getMedical()
   {
      return medical;
   }

   public void setMedical(Integer medical)
   {
      this.medical = medical;
      addTotalLiability();
   }

   public Integer getMortgateEquity()
   {
      return mortgateEquity;
   }

   public void setMortgateEquity(Integer mortgateEquity)
   {
      this.mortgateEquity = mortgateEquity;
      addTotalAsset();
   }

   public Integer getMortgageLoan()
   {
      return mortgageLoan;
   }

   public void setMortgageLoan(Integer mortgageLoan)
   {
      this.mortgageLoan = mortgageLoan;
      addTotalLiability();
   }

   public Integer getOtherSavings()
   {
      return otherSavings;
   }

   public void setOtherSavings(Integer otherSavings)
   {
      this.otherSavings = otherSavings;
      addTotalAsset();
   }

   public Integer getOtherDebt()
   {
      return otherDebt;
   }

   public void setOtherDebt(Integer otherDebt)
   {
      this.otherDebt = otherDebt;
      addTotalLiability();
   }

   public Double getStock()
   {
      return stock;
   }

   public void setStock(Double stock)
   {
      this.stock = stock;
   }

   public Double getBond()
   {
      return bond;
   }

   public void setBond(Double bond)
   {
      this.bond = bond;
   }

   public Double getAccrual()
   {
      return accrual;
   }

   public void setAccrual(Double accrual)
   {
      this.accrual = accrual;
   }

   public Integer convertNumber(Integer num) {
      if (num == null)
         return 0;
      else
         return num;
   }

   public void addTotalIncome() {
       setTotalIncome((convertNumber(getHouseholdwages())+ convertNumber(getOtherIncome())));
   }

   public void addTotalExpense() {
      setTotalExpense((convertNumber(getMortgagePayment()) + convertNumber(getOtherExpense())));

   }

   public void addTotalAsset() {
      setLiquidAsset(convertNumber(getMoneymarket()));
      setTotalAsset(convertNumber(getMortgateEquity()) + convertNumber(getInvestment()) +
                       convertNumber(getOtherSavings()) + convertNumber(getMoneymarket()));

   }

   public void addTotalLiability() {
       setTotalLiability(convertNumber(getMortgageLoan()) + convertNumber(getAutoLoan()) +
                            convertNumber(getMedical()) + convertNumber(getOtherDebt()));
   }

   public String getSelectedchoice1()
   {
      return selectedchoice1;
   }

   public void setSelectedchoice1(String selectedchoice)
   {
      this.selectedchoice1 = selectedchoice;
      setRiskAnswers(0,selectedchoice);
   }

   public String getSelectedchoice2()
   {
      return selectedchoice2;
   }

   public void setSelectedchoice2(String selectedchoice)
   {
      this.selectedchoice2 = selectedchoice;
      setRiskAnswers(1,selectedchoice);
   }

   public String getSelectedchoice3()
   {
      return selectedchoice3;
   }

   public void setSelectedchoice3(String selectedchoice)
   {
      this.selectedchoice3 = selectedchoice;
      setRiskAnswers(2,selectedchoice);
   }

   public String getSelectedchoice4()
   {
      return selectedchoice4;
   }

   public void setSelectedchoice4(String selectedchoice)
   {
      this.selectedchoice4 = selectedchoice;
      setRiskAnswers(3,selectedchoice);
   }

   public String getSelectedchoice5()
   {
      return selectedchoice5;
   }

   public void setSelectedchoice5(String selectedchoice)
   {
      this.selectedchoice5 = selectedchoice;
      setRiskAnswers(4,selectedchoice);
   }

   public String getSelectedchoice6()
   {
      return selectedchoice6;
   }

   public void setSelectedchoice6(String selectedchoice)
   {
      this.selectedchoice6 = selectedchoice;
      setRiskAnswers(5,selectedchoice);
   }

   public String getSelectedchoice7()
   {
      return selectedchoice7;
   }

   public void setSelectedchoice7(String selectedchoice)
   {
      this.selectedchoice7 = selectedchoice;
      setRiskAnswers(6,selectedchoice);
   }


   public String getSelectedchoice8()
   {
      return selectedchoice8;
   }

   public void setSelectedchoice8(String selectedchoice)
   {
      this.selectedchoice8 = selectedchoice;
      setRiskAnswers(7,selectedchoice);
   }

   public String getSelectedchoice9()
   {
      return selectedchoice9;
   }

   public void setSelectedchoice9(String selectedchoice)
   {
      this.selectedchoice9 = selectedchoice;
      setRiskAnswers(8,selectedchoice);
   }

   public String getSelectedchoice10()
   {
      return selectedchoice10;
   }

   public void setSelectedchoice10(String selectedchoice)
   {
      this.selectedchoice10 = selectedchoice;
      setRiskAnswers(9,selectedchoice);
   }

   public String getSelectedchoice11()
   {
      return selectedchoice11;
   }

   public void setSelectedchoice11(String selectedchoice)
   {
      this.selectedchoice11 = selectedchoice;
      setRiskAnswers(10,selectedchoice);
   }

   public String getSelectedchoice12()
   {
      return selectedchoice12;
   }

   public void setSelectedchoice12(String selectedchoice)
   {
      this.selectedchoice12 = selectedchoice;
      setRiskAnswers(11,selectedchoice);
   }

   public String getSelectedchoice13()
   {
      return selectedchoice13;
   }

   public void setSelectedchoice13(String selectedchoice)
   {
      this.selectedchoice13 = selectedchoice;
      setRiskAnswers(12,selectedchoice);
   }

   public String getSelectedchoice14()
   {
      return selectedchoice14;
   }

   public void setSelectedchoice14(String selectedchoice)
   {
      this.selectedchoice14 = selectedchoice;
      setRiskAnswers(13,selectedchoice);
   }

   public String getSelectedchoice15()
   {
      return selectedchoice15;
   }

   public void setSelectedchoice15(String selectedchoice)
   {
      this.selectedchoice15 = selectedchoice;
      setRiskAnswers(14,selectedchoice);
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(String model)
   {
      this.model = model;
   }

   public Integer getAssetyear()
   {
      return assetyear;
   }

   public void setAssetyear(Integer assetyear)
   {
      this.assetyear = assetyear;
   }

   public String getPortfolioName()
   {
      return portfolioName;
   }

   public void setPortfolioName(String portfolioName)
   {
      this.portfolioName = portfolioName;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getFullName() {
      String name = null;
      if (getLastname() != null)
         name = getLastname();

      if (getFirstname() != null) {
         if (name != null)
            name = name + ", " + getFirstname();
         else
            name =  getFirstname();
      }

      return name;
   }

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

      setSelectedchoice1	(null);
      setSelectedchoice2	(null);
      setSelectedchoice3	(null);
      setSelectedchoice4	(null);
      setSelectedchoice5	(null);
      setSelectedchoice6	(null);
      setSelectedchoice7	(null);
      setSelectedchoice8	(null);
      setSelectedchoice9	(null);
      setSelectedchoice10	(null);
      setSelectedchoice11	(null);
      setSelectedchoice12	(null);
      setSelectedchoice13	(null);
      setSelectedchoice14	(null);
      setSelectedchoice15	(null);

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

   public Integer[] selectedChoices()
   {
         Integer no_of_questions = 7;
         Integer[] selectedChoices = new Integer[]{null, null, null, null, null, null, null};
         Integer riskIndex = 0;

         if (getSelectedchoice1() != null)
         {
            String choice = getSelectedchoice1();
            selectedChoices[0] = Integer.parseInt(getSelectedchoice1());
         }

         if (getSelectedchoice2() != null)
         {
            String choice = getSelectedchoice2();
            selectedChoices[1] = Integer.parseInt(getSelectedchoice2());

         }

         if (getSelectedchoice3() != null)
         {
            String choice = getSelectedchoice3();
            selectedChoices[2] = Integer.parseInt(getSelectedchoice3());
         }

         if (getSelectedchoice4() != null)
         {
            String choice = getSelectedchoice4();
            selectedChoices[3] = Integer.parseInt(getSelectedchoice4());
         }

         if (getSelectedchoice5() != null)
         {
            String choice = getSelectedchoice5();
            selectedChoices[4] = Integer.parseInt(getSelectedchoice5());
         }

         if (getSelectedchoice6() != null)
         {
            String choice = getSelectedchoice6();
            selectedChoices[5] = Integer.parseInt(getSelectedchoice6());
         }

         if (getSelectedchoice7() != null)
         {
            String choice = getSelectedchoice7();
            selectedChoices[6] = Integer.parseInt(getSelectedchoice7());
         }

         return selectedChoices;
   }

   public String getHorizonQuestion()
   {
      String question = "How many years do you plan to invest?";
      if (getGoal() != null) {
         if (getGoal().equalsIgnoreCase("retirement"))
            return "Number of years to your retirement?";
         if (getGoal().equalsIgnoreCase("home"))
            return "How many years until you purchase your home?";
         if (getGoal().equalsIgnoreCase("wedding"))
            return "Number of years to your wedding?";
         if (getGoal().equalsIgnoreCase("automobile"))
            return "How many years until you purchase automobile?";
         if (getGoal().equalsIgnoreCase("education"))
            return "How many years to your child's college graduation?";
      }
      return question;
   }


   public String getRegisteredState()
   {
      return registeredState;
   }

   public void setRegisteredState(String registeredState)
   {
      this.registeredState = registeredState;
   }

   public Boolean getUserAssetOverride()
   {
      return userAssetOverride;
   }

   public void setUserAssetOverride(Boolean userAssetOverride)
   {
      this.userAssetOverride = userAssetOverride;
   }

   public List<DataPortfolio> getDisplayPortfolioList()
   {
      return displayPortfolioList;
   }

   public void setDisplayPortfolioList(List<DataPortfolio> displayPortfolioList)
   {
      this.displayPortfolioList = displayPortfolioList;
   }


   public Double getAssetAllocationTotal()
   {
      return assetAllocationTotal;
   }

   public void setAssetAllocationTotal(Double assetAllocationTotal)
   {
      this.assetAllocationTotal = assetAllocationTotal;
   }

   public Double getTotalSharesAllocated()
   {
      return totalSharesAllocated;
   }

   public void setTotalSharesAllocated(Double totalSharesAllocated)
   {
      this.totalSharesAllocated = totalSharesAllocated;
   }

   public Double getTotalMoneyAllocated() {
      return this.totalMoneyAllocated;
   }

   public void setTotalMoneyAllocated(Double totalMoneyAllocated)
   {
      this.totalMoneyAllocated = totalMoneyAllocated;
   }

   public Double getManagedassetAllocationTotal()
   {
      return managedassetAllocationTotal;
   }

   public void setManagedassetAllocationTotal(Double managedassetAllocationTotal)
   {
      this.managedassetAllocationTotal = managedassetAllocationTotal;
   }

   public Double getManagedtotalMoney()
   {
      return managedtotalMoney;
   }

   public void setManagedtotalMoney(Double managedtotalMoney)
   {
      this.managedtotalMoney = managedtotalMoney;
   }

   public Double getActualInvested() {
      if (getManaged())
         if (getManagedtotalMoney() > 0.0)
            return getManagedtotalMoney();

      return getInitialInvestment().doubleValue();
   }

   public DataPortfolio getSelectedPortfolio()
   {
      return selectedPortfolio;
   }

   public void setSelectedPortfolio(DataPortfolio selectedPortfolio)
   {
      this.selectedPortfolio = selectedPortfolio;
   }

   public void resetAllocationIndex() {
      setAllocationIndex(allocModel.getAllocationIndex((ProfileData) this.getInstance()));;
   }

   public void resetPortfolioIndex() {
      setPortfolioIndex(portfolioModel.getPortfolioIndex((ProfileData) this.getInstance()));
   }

   public void buildAssetClass() {
      AssetClass[] aamc;
      try {
         setAssetData(null);
         aamc = allocModel.buildAllocation((ProfileData) this.getInstance());
         if (aamc != null)  {
            setAssetData(aamc);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void buildPortfolio() {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      MsgData data = new MsgData();
      try
      {
         Integer displayYear = 0;
         aamc = getAssetData();
         if (aamc != null)
         {
            pfclass = portfolioModel.buildPortfolio(aamc,
                                      (ProfileData) getInstance());
            if (pfclass != null)
            {
               setPortfolioData(pfclass);

               // Now refresh the Display List
               loadPortfolioList(displayYear);
            }

            if (getUserAssetOverride())
               getAllocModel().overrideAssetWeight(aamc[displayYear], this.getEditableAsset());
            totalAssetClassWeights(aamc[displayYear].getAssetclass(), displayYear);

         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public PerformanceData[] buildPerformanceData() {
      Integer numOfYears = getAge();
      if (numOfYears == null)
         numOfYears = 20;
      else {
         numOfYears = ((65 - numOfYears) > 20) ? 20 : (65 - numOfYears);
         if (numOfYears < 5)
            numOfYears = 5;
      }
      PerformanceData[] perfData = portfolioPerformance.getPortfolioPerformance(getPortfolioData(), numOfYears,0);
      return perfData;
   }

   public void totalAssetClassWeights(Map<String, Asset> assetdata, Integer offset)
   {
      Double totalAlloc;
      Double assetWeight;
      String assetname;
      Double cashAlloc = 0.0;
      Double adjustment = 0.0;

      if (getEditableAsset() == null) {
         recreateEditableAsset();
      }
      else {
         if (getAssetData() != null)  {
            getEditableAsset().clear();
            totalAlloc = 0.0;
            for (int loop=0; loop < getAssetData()[offset].getOrderedAsset().size(); loop++) {
               assetname = getAssetData()[offset].getOrderedAsset().get(loop);
               Asset asset = assetdata.get(assetname);
               setEditableAsset(asset);
               assetWeight = asset.getActualweight();
               totalAlloc =  totalAlloc + assetWeight;
            }
            totalAlloc = (Math.round(totalAlloc) * 100.0);
            setAssetAllocationTotal(totalAlloc);
         }
      }
   }

   public void reloadManagedAssetClass(Map<String,Asset> assetdata, int year)
   {
      Double totalAlloc = 0.0;
      String assetname;
      Asset asset;
      String cashAsset = "Cash";
      Double cashAlloc = 0.0;
      Double adjustment = 0.0;

      setManagedassetAllocationTotal(0.0);
      setManagedtotalMoney(0.0);
      if (getEditableAsset() != null) {
         if (getAssetData() != null)  {
            Integer numOfAsset = getAssetData().length;
            if (numOfAsset >= year) {
               setManagedassetAllocationTotal(0.0);
               setManagedtotalMoney(0.0);
               for (int loop=0; loop < getAssetData()[year].getOrderedAsset().size(); loop++) {
                  assetname = getAssetData()[year].getOrderedAsset().get(loop);
                  asset = getAssetData()[year].getAsset(assetname);
                  if (assetdata.containsKey(assetname)) {
                     asset.setHoldingweight(assetdata.get(assetname).getHoldingweight());
                     asset.setHoldingRisk(assetdata.get(assetname).getHoldingRisk());
                     asset.setHoldingReturn(assetdata.get(assetname).getHoldingReturn());
                     asset.setHoldingValue(assetdata.get(assetname).getHoldingValue());
                     setManagedassetAllocationTotal(getManagedassetAllocationTotal() + assetdata.get(assetname).getHoldingweight());
                     setManagedtotalMoney(getManagedtotalMoney() + assetdata.get(assetname).getHoldingValue() );
                  }
                  else {
                     asset.setHoldingweight(0.0);
                     asset.setHoldingRisk(0.0);
                     asset.setHoldingReturn(0.0);
                     asset.setHoldingValue(0.0);
                  }
               }
            }
         }
      }
   }

   public void loadPortfolioList(Integer dataYear)
   {
         Double totalMoney=0.0;
         Double addedShares = 0.0;
         Double addedTotalMoney=0.0;
         Double weight=0.0;
         if (getDisplayPortfolioList() == null) {
            this.displayPortfolioList = new ArrayList<DataPortfolio>();
         }

         if (getPortfolioData() != null) {
            this.displayPortfolioList.clear();
            int rowSize = getPortfolioData()[dataYear].getPortfolio().size();
            totalMoney = getPortfolioData()[dataYear].getTotalMoney();
            for (int loop = 0; loop < rowSize; loop++)
            {
               PortfolioSecurityData pfList = getPortfolioData()[dataYear].getPortfolio().get(loop);
               if (totalMoney == 0)
                   weight=0.0;
               else
                    weight = pfList.getMoney()/totalMoney;
               addedTotalMoney += pfList.getMoney();
               addedShares +=  pfList.getShares();
               DataPortfolio dp = new DataPortfolio(pfList.getAssetclass(), pfList.getSubclass(), pfList.getColor(),
                                                    pfList.getTicker(), pfList.getName(), (int) pfList.getShares(),
                                                    pfList.getDailyprice(), pfList.getMoney(), pfList.getSortorder(),
                                                    pfList.getTickerWeights(), weight);
               getDisplayPortfolioList().add(loop, dp);
            }
            addedTotalMoney = Math.round(addedTotalMoney * 100.00) / 100.00; // round off..
            setTotalMoneyAllocated(addedTotalMoney);
            setTotalSharesAllocated(addedShares);
         }
   }

   public List<DataPortfolio> getSelectedPortfolioList()
   {
      return selectedPortfolioList;
   }

   public void setSelectedPortfolioList(List<DataPortfolio> selectedPortfolioList)
   {
      this.selectedPortfolioList = selectedPortfolioList;
   }
   public List<PortfolioSubclass> getExcludedSubAsset()
   {
      return excludedSubAsset;
   }

   public void setExcludedSubAsset(List<PortfolioSubclass> excludedSubAsset)
   {
      this.excludedSubAsset = excludedSubAsset;
   }

   public Map<String, ManagedSubclassData> getSubassetList()
   {
      return subassetList;
   }

   public void setSubassetList(Map<String, ManagedSubclassData> subassetList)
   {
      this.subassetList = subassetList;
   }

   public ArrayList<ManagedSubclassData> getOrderedSubclass()
   {
      return orderedSubclass;
   }

   public void setOrderedSubclass(ArrayList<ManagedSubclassData> orderedSubclass)
   {
      this.orderedSubclass = orderedSubclass;
   }

   public String getExternalPositionFile()
   {
      return externalPositionFile;
   }

   public void setExternalPositionFile(String externalPositionFile)
   {
      this.externalPositionFile = externalPositionFile;
   }

   public Map<String,String> getAdvisorBasket()
   {
      return advisorBasket;
   }

   public void setAdvisorBasket(Map<String,String> advisorBasket)
   {
      this.advisorBasket = advisorBasket;
   }

   public String getThisBasket()
   {
      return getBasket();
   }

   // This happens in dropdown.  They select the KEY, so we are setting both KEY and value.
   public void setThisBasket(String value)
   {
      if (value == null) {
         setBasket(value, InvConst.DEFAULT_BASKET);
         setTheme(InvConst.DEFAULT_THEME);
      }
      else {
         if (this.advisorBasket.containsKey(value)) {
            setBasket(value, this.advisorBasket.get(value));
            setTheme(value);
         }
      }
   }

}
