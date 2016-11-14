package com.invessence.web.data.common;

import java.util.*;

import javax.faces.bean.ManagedProperty;

import com.invessence.converter.JavaUtil;
import com.invessence.emailer.data.MsgData;
import com.invessence.web.data.*;
import com.invessence.web.util.*;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.ModelUtil;
import com.invmodel.performance.ProjectionReport;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.*;
import org.apache.commons.logging.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomerData extends ProfileData
{
   protected final Log logger = LogFactory.getLog(getClass());
   private JavaUtil javautil = new JavaUtil();
   private CustomerData manageGoalinstance = null;

   private String advisorDisplayName;
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

   private String model      = "D";
   private Integer assetyear = 0;
   private String registeredState;
   private Boolean userAssetOverride = false;

   private String portfolioName;

   private String email, firstname, lastname;
   public List<DataPortfolio> displayPortfolioList = new ArrayList<DataPortfolio>();
   public DataPortfolio selectedPortfolio;
   public List<DataPortfolio> selectedPortfolioList = null;
   public Double assetAllocationTotal = 0.0;
   public Double totalSharesAllocated = 0.0;
   public Double totalMoneyAllocated = 0.0;

   private Double  managedassetAllocationTotal = 0.0;
   private Double managedtotalMoney = 0.0;


   private String externalPositionFile;

   //private TreeNode subclassDisplayNode;
   //private TreeNode[] subclassFilterNode;
   public Map<String, ManagedSubclassData> subassetList = new HashMap<String, ManagedSubclassData>();
   public ArrayList<ManagedSubclassData> orderedSubclass;
   public List<PortfolioSubclass> excludedSubAsset = new ArrayList<PortfolioSubclass>();

   public Map<String, String> advisorBasket;
   private Boolean managed, editable;


   @ManagedProperty("#{webutil}")
   public WebUtil webutil;

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   public UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   @ManagedProperty("#{modelUtil}")
   public ModelUtil modelUtil;
   public void setModelUtil(ModelUtil modelUtil)
   {
      this.modelUtil = modelUtil;
   }
/*
   @ManagedProperty("#{assetAllocationModel}")
   public AssetAllocationModel allocModel;

   @ManagedProperty("#{portfolioModel}")
   public PortfolioModel portfolioModel;

   @ManagedProperty("#{projectionReport}")
   public ProjectionReport projectionReport;
*/

   public Log getLogger()
   {
      return logger;
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

   public ModelUtil getModelUtil()
   {
      return modelUtil;
   }

   public Boolean getManaged()
   {
      if (managed == null)
         return false;
      return managed;
   }

   public void setManaged(Boolean managed)
   {
      this.managed = managed;
   }

   public Boolean getEditable()
   {
      if (editable == null)
         return true;
      return editable;
   }

   public void setEditable(Boolean editable)
   {
      this.editable = editable;
   }

   /*
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

   public ProjectionReport getProjectionReport()
   {
      return projectionReport;
   }

   public void setProjectionReport(ProjectionReport projectionReport)
   {
      this.projectionReport = projectionReport;
   }
*/

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

   public Double getEstimatedGoal() {
      Double value = getInvestment().doubleValue();
      Integer finalyear;
      if (getProjectionData() != null) {
         finalyear = getProjectionData().length;
         value = getProjectionData()[finalyear-1].getTotalCapitalWithGains();
      }
      return value;
   }

   public String getAdvisorDisplayName()
   {
      return advisorDisplayName;
   }

   public void setAdvisorDisplayName(String advisorDisplayName)
   {
      this.advisorDisplayName = advisorDisplayName;
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

      if (advisorBasket == null) {
         advisorBasket = new HashMap<String,String>();
      }
      else {
         advisorBasket.clear();
      }
      resetAdvisor();  // Reset Advisor
   }

   public void resetAdvisor() {

      if (webutil != null) {
         if (webutil.isUserLoggedIn()) {
            setAdvisor(webutil.getUserInfoData().getAdvisor());
            setRep(webutil.getUserInfoData().getRep());
         }
         else {
            if (webutil.getUiprofile() != null) {
               setAdvisor(webutil.getUiprofile().getAdvisor());
               setRep(webutil.getUiprofile().getRep());
            }
         }
      }
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
      setAssetData	(	newgoals.getAssetData	());
      setPortfolioData	(	newgoals.getPortfolioData	());
      setFirstname(newgoals.getFirstname());
      setLastname(newgoals.getLastname());
      setRegisteredState(newgoals.getRegisteredState());
      setUserAssetOverride(false);
      setName(newgoals.getFirstname() + " " + newgoals.getLastname());
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

/*
   public void resetAllocationIndex() {
      setAllocationIndex(allocModel.getAllocationIndex(getProfileInstance());;
   }

   public void resetPortfolioIndex() {
      setPortfolioIndex(portfolioModel.getPortfolioIndex(getProfileInstance()));
   }
*/
   public void selectFirstBasket()
   {
      if (getTheme() == null) {
         if (getAdvisorBasket() != null) {
            for (String theme : getAdvisorBasket().keySet()) {
               setTheme(theme);  // Set the first one...
               setBasket(getAdvisorBasket().get(getTheme()));
               break;
            }
         }
      }

      // If it is still null, then set it to default
      if (getTheme() == null)
      {
         if (getAccountTaxable())
         {
            setTheme(InvConst.DEFAULT_TAXABLE_THEME);
            setBasket(InvConst.DEFAULT_TAXABLE_BASKET);
         }
         else
         {
            setTheme(InvConst.DEFAULT_THEME);
            setBasket(InvConst.DEFAULT_BASKET);
         }
      }
   }

   public void buildAssetClass() {
      AssetClass[] aamc;
      try {
         setAssetData(null);
         aamc = modelUtil.buildAllocation(getProfileInstance());
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
            pfclass = modelUtil.buildPortfolio(aamc,
                                      getProfileInstance());
            if (pfclass != null)
            {
               setPortfolioData(pfclass);
               // Now refresh the Display List
               loadPortfolioList(displayYear);
            }
            rollupAssetClass(pfclass[0]);
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void buildGoalsData() {

      Integer numOfYears = 20;
      if (getGoalData() != null && getGoalData().getTerm() != null) {
         numOfYears = getGoalData().getTerm().intValue();
      }
      else {
         if (getHorizon() == null) {
               numOfYears = ((65 - getAge()) > 20) ? 20 : (65 - getAge());
         }
         else {
            numOfYears = getHorizon();
         }

         if (numOfYears < 5)
            numOfYears = 5;
      }


      ArrayList<ProjectionData[]> perfData = modelUtil.buildProjectionData(getProfileInstance());
      setProjectionData(perfData.get(0));
      modelUtil.goalTracking(getProfileInstance());

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

   public void rollupAssetClass(Portfolio pfclass) {
      Map<String, Asset> tallyAssetclass = new LinkedHashMap<String, Asset>();
      if (pfclass != null) {
         for (PortfolioSecurityData seclist: pfclass.getPortfolio()) {
            String assetname = seclist.getAssetclass();
            Double wght = seclist.getWeight();
            Double money = seclist.getMoney();
            String color = seclist.getColor();

            if (! tallyAssetclass.containsKey(assetname)) {
               Asset asset = new Asset();
               asset.setAsset(assetname);
               asset.setColor(color);
               asset.setActualweight(wght);
               asset.setAllocweight(wght);
               asset.setValue(money);
               tallyAssetclass.put(assetname,asset);
            }
            else {
               Asset asset = tallyAssetclass.get(assetname);
               Double newwght =  wght + asset.getActualweight();
               asset.setActualweight(newwght);
               asset.setAllocweight(newwght);
               asset.setValue(money + asset.getValue());
               tallyAssetclass.put(assetname,asset);
            }
         }

         if (tallyAssetclass.size() > 0) {
            recreateEditableAsset();
            AssetClass[] aamc = new AssetClass[tallyAssetclass.size()];
            Integer i = 0;
            aamc[i] = new AssetClass();
            for (Asset asset : tallyAssetclass.values()) {
               setEditableAsset(asset);
               aamc[i].addAssetClass(asset.getAsset(),asset.getDisplayName(),asset.getColor(),
                                     asset.getAllocweight(), asset.getAvgReturn());
            }
            setAssetData(aamc);
         }
      }
   }

   public Boolean getHasPosition()
   {
      if (getClientAccountID() != null && getClientAccountID().length() > 0)
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public String getDisplayActiveAcctNum()
   {
      if (getClientAccountID() != null && getClientAccountID().length() > 0)
      {
         return getClientAccountID();
      }
      else
      {
         if (getManaged()) {
               return ("Pending");
         }
      }
      return getAcctnum().toString();
   }


}
