package com.invessence.web.data.common;

import java.util.*;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import com.invessence.converter.JavaUtil;
import com.invessence.emailer.data.MsgData;
import com.invessence.web.constant.*;
import com.invessence.web.dao.common.UserInfoDAO;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.*;
import com.invessence.web.util.*;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.ModelUtil;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.data.*;
import org.apache.commons.logging.*;

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

   @ManagedProperty("#{webutil}")
   public WebUtil webutil;

   @ManagedProperty("#{uiLayout}")
   public UILayout uiLayout;

   @ManagedProperty("#{modelUtil}")
   public ModelUtil modelUtil;

   @ManagedProperty("#{consumerListDataDAO}")
   public ConsumerListDataDAO listDAO;

   @ManagedProperty("#{userInfoDAO}")
   public UserInfoDAO userInfoDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   public ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{webMessage}")
   public WebMessage messageText;

   private JavaUtil javautil = new JavaUtil();

   public USMaps usstates = USMaps.getInstance();

   private CustomerData manageGoalinstance;

   private Integer numofaccounts;
   private String advisorDisplayName;
   private String userid;
   private String addmodflag;
   private String dateOpened;
   private String role, privileges;
   private String created;
   private String phone;

   private AccountFinancials accountFinancials;

   private Integer assetyear = 0;
   private String registeredState;
   private Boolean userAssetOverride;

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
   private Boolean managed, editable, isUnopened;
   public String managedFlag, currentStatus;
   private Integer sliderAllocationIndex, sliderPortfolioIndex;

   private Boolean enableChangeStrategy;
   private Boolean displayFTPanel;
   private String savedRiskFormula;
   private Integer savedAllocSliderIndex;
   private Boolean doesUserHavaLogonID;


   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
   }

   public void setModelUtil(ModelUtil modelUtil)
   {
      this.modelUtil = modelUtil;
   }

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public void setUserInfoDAO(UserInfoDAO userInfoDAO)
   {
      this.userInfoDAO = userInfoDAO;
   }

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public void setMessageText(WebMessage messageText)
   {
      this.messageText = messageText;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public UILayout getUiLayout()
   {
      return uiLayout;
   }

   public ConsumerListDataDAO getListDAO()
   {
      return listDAO;
   }

   public ConsumerSaveDataDAO getSaveDAO()
   {
      return saveDAO;
   }

   public WebMessage getMessageText()
   {
      return messageText;
   }

   public JavaUtil getJavautil()
   {
      return javautil;
   }

   public USMaps getUsstates()
   {
      return usstates;
   }

   public void setUsstates(USMaps usstates)
   {
      this.usstates = usstates;
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
      displayPortfolioList = new ArrayList<DataPortfolio>();
      selectedPortfolio = new DataPortfolio();
      selectedPortfolioList = null;
      subassetList = new HashMap<String, ManagedSubclassData>();
      orderedSubclass = new ArrayList<ManagedSubclassData>();
      excludedSubAsset = new ArrayList<PortfolioSubclass>();
      accountFinancials = new AccountFinancials();

      // resetCustomerData();
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
      return ((managed == null) ? false : managed);
   }

   public void setManaged(Boolean managed)
   {
      this.managed = managed;
   }

   public Boolean isUnopened()
   {
      return (isUnopened == null)? false : isUnopened;
   }

   public Boolean getUnopened()
   {
      return isUnopened;
   }

   public void setUnopened(Boolean unopened)
   {
      isUnopened = unopened;
   }

   public Boolean getEditable()
   {
      return ((editable == null)? true : editable);
   }

   public void setEditable(Boolean editable)
   {
      this.editable = editable;
   }

   public Boolean getCanIEditAccount() {
      try {
         if (webutil.isUserLoggedIn()) {
            if (getRole().equalsIgnoreCase(WebConst.ROLE_OWNER) ||
               (getRole().equalsIgnoreCase(WebConst.ROLE_USER) && getPrivileges().equalsIgnoreCase(WebConst.ACCESS_USER_FULL)))
               return true;
         }
         return false;
      }
      catch (Exception ex) {
         return false;
      }
   }

   public String getManagedFlag()
   {
      return managedFlag;
   }

   public void setManagedFlag(String managedFlag)
   {
      this.managedFlag = managedFlag;
   }

   public String getCurrentStatus()
   {
      return currentStatus;
   }

   public void setCurrentStatus(String currentStatus)
   {
      this.currentStatus = currentStatus;
   }

   public Integer getSliderAllocationIndex()
   {
      return sliderAllocationIndex;
   }

   public void setSliderAllocationIndex(Integer sliderAllocationIndex)
   {
      this.sliderAllocationIndex = sliderAllocationIndex;
   }

   public Integer getSliderPortfolioIndex()
   {
      return sliderPortfolioIndex;
   }

   public void setSliderPortfolioIndex(Integer sliderPortfolioIndex)
   {
      this.sliderPortfolioIndex = sliderPortfolioIndex;
   }

   public Boolean getEnableChangeStrategy()
   {
      return enableChangeStrategy;
   }

   public void setEnableChangeStrategy(Boolean enableChangeStrategy)
   {
      this.enableChangeStrategy = enableChangeStrategy;
   }

   public Boolean getDisplayFTPanel()
   {
      return displayFTPanel;
   }

   public void setDisplayFTPanel(Boolean displayFTPanel)
   {
      this.displayFTPanel = displayFTPanel;
   }

   public void setSavedRiskFormula(String savedRiskFormula)
   {
      this.savedRiskFormula = savedRiskFormula;
   }

   public void setDoesUserHavaLogonID(Boolean doesUserHavaLogonID)
   {
      this.doesUserHavaLogonID = doesUserHavaLogonID;
   }

   public void setSavedAllocSliderIndex(Integer savedAllocSliderIndex)
   {
      this.savedAllocSliderIndex = savedAllocSliderIndex;
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

   public String getSavedRiskFormula()
   {
      return savedRiskFormula;
   }

   public Integer getSavedAllocSliderIndex()
   {
      return savedAllocSliderIndex;
   }

   public Boolean getDoesUserHavaLogonID()
   {
      return ((doesUserHavaLogonID == null) ? false : doesUserHavaLogonID);
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
      Double value = accountFinancials.getInvestment().doubleValue();
      Integer finalyear;
      if (getProjectionData() != null) {
         finalyear = getProjectionData().length;
         value = getProjectionData()[finalyear-1].getTotalCapitalWithGains();
      }
      return value;
   }

   public Integer getNumofaccounts()
   {
      return numofaccounts;
   }

   public void setNumofaccounts(Integer numofaccounts)
   {
      this.numofaccounts = numofaccounts;
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

   public String getRole()
   {
      return role;
   }

   public void setRole(String role)
   {
      this.role = role;
   }

   public String getPrivileges()
   {
      return privileges;
   }

   public void setPrivileges(String privileges)
   {
      this.privileges = privileges;
   }

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }


   public Integer convertNumber(Integer num) {
      if (num == null)
         return 0;
      else
         return num;
   }

   public AccountFinancials getAccountFinancials()
   {
      return accountFinancials;
   }

   public void setAccountFinancials(AccountFinancials accountFinancials)
   {
      this.accountFinancials = accountFinancials;
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

      // Manage Goal Data.
      if (getDoesUserHavaLogonID()) {
         resetPortfolio();
      }
      else
      {
         // Master ProfileData
         // setName	(null);  Being reset at bottom.
         resetPortfolioData();
         // setAcctnum(null);
         // setClientAccountID(null);
         // setLogonid(null);
         setUserid(null);
         setPortfolioName(null);
         setAddmodflag(null);
         setDateOpened(null);
         setManaged(false);
         setRole(null);
         setPrivileges(null);
         setNumofaccounts(0);

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
         sliderAllocationIndex = 0;
         sliderPortfolioIndex = 0;

         setSavedRiskFormula(null);
         setSavedAllocSliderIndex(null);

         accountFinancials = new AccountFinancials();
         resetAdvisor();  // Reset Advisor
      }

   }

   public void resetAdvisor() {

      if (webutil != null)
      {
         if (webutil.isUserLoggedIn())
         {
            if (getAdvisor() == null)
               setAdvisor(webutil.getUserInfoData().getAdvisor());
            if (getRep() == null)
               setRep(webutil.getUserInfoData().getRep());
         }
         else
         {
            if (webutil.getWebprofile() != null)
            {
               if (getAdvisor() == null || getAdvisor().isEmpty())
                  setAdvisor(webutil.getWebprofile().getDefaultAdvisor());
               if (getRep() == null || getRep().isEmpty())
                  setRep(webutil.getWebprofile().getDefaultRep());
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
      setAccountTaxable	(	newgoals.getAccountTaxable	());
      setTaxrate	(	newgoals.getTaxrate	());
      setRiskIndex	(	newgoals.getRiskIndex	());

      // ManageGoal Data
      setAcctnum(newgoals.getAcctnum());
      setLogonid(newgoals.getLogonid());
      setUserid(newgoals.getUserid());
      setAdvisor(newgoals.getAdvisor());
      setRep(newgoals.getRep());
      setAddmodflag(newgoals.getAddmodflag());
      setGoal  (    newgoals.getGoal());
      setAccountType(newgoals.getAccountType());
      setName(newgoals.getName());
      setAssetData	(	newgoals.getAssetData	());
      setPortfolioData	(	newgoals.getPortfolioData	());
      setFirstname(newgoals.getFirstname());
      setLastname(newgoals.getLastname());
      setRegisteredState(newgoals.getRegisteredState());
      setUserAssetOverride(false);
      setName(newgoals.getFirstname() + " " + newgoals.getLastname());

      accountFinancials = newgoals.accountFinancials;
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
      return (userAssetOverride == null ? false :  userAssetOverride);
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

   public void loadBasketInfo()
   {
      if (getAccountTaxable())
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
      }
      else
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
      }

      if (getTheme() != null)
      {
         setBasket(getAdvisorBasket().get(getTheme()));
      }
      else
      {
         selectFirstBasket(); // DO this only first time.
      }

      if (getBasket() != null)
      {
         setPortfolioName(getAdvisorBasket().get(getTheme()));
      }
   }


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
      Double totalMoney = 0.0;
      if (pfclass != null) {
         for (PortfolioSecurityData seclist: pfclass.getPortfolio()) {
            String assetname = seclist.getAssetclass();
            Double wght = seclist.getWeight();
            Double money = seclist.getMoney();
            String color = seclist.getColor();
            Double summoney = 0.0;

            totalMoney += money;

            if (! tallyAssetclass.containsKey(assetname)) {
               Asset asset = new Asset();
               Double newwght =  money / totalMoneyAllocated;
               asset.setAsset(assetname);
               asset.setColor(color);
               asset.setValue(money);
               asset.setActualweight(newwght);
               asset.setUserweight(newwght);
               asset.setAllocweight(newwght);
               asset.setValue(money);
               tallyAssetclass.put(assetname,asset);
            }
            else {
               Asset asset = tallyAssetclass.get(assetname);
               summoney = money + asset.getValue();
               Double newwght =  summoney / totalMoneyAllocated;
               asset.setActualweight(newwght);
               asset.setUserweight(newwght);
               asset.setAllocweight(wght + asset.getAllocweight());
               asset.setValue(summoney);
               tallyAssetclass.put(assetname,asset);
            }
         }

         // After the tally is done, lets reallocate to whole
         if (tallyAssetclass.size() > 0) {
            recreateEditableAsset();
            AssetClass[] aamc = new AssetClass[tallyAssetclass.size()];
            Integer i = 0;
            aamc[i] = new AssetClass();
            for (Asset assetdata : tallyAssetclass.values()) {
               setEditableAsset(assetdata);
               Asset origAssetData =  aamc[i].getAssetclass().get(assetdata.getAsset());
               if (origAssetData != null)
               {
                  origAssetData.setUserweight(assetdata.getUserweight());
                  origAssetData.setActualweight(assetdata.getActualweight());
                  origAssetData.setValue(assetdata.getValue());
               }
               else {
                  aamc[i].addAssetClass(assetdata.getAsset(),assetdata.getDisplayName(),assetdata.getColor(),
                                        assetdata.getAllocweight(), assetdata.getAvgReturn());
                  aamc[i].getAssetclass().get(assetdata.getAsset()).setValue(assetdata.getValue());
                  aamc[i].getAssetclass().get(assetdata.getAsset()).setUserweight(assetdata.getUserweight());
                  aamc[i].getAssetclass().get(assetdata.getAsset()).setActualweight(assetdata.getActualweight());

               }
            }
            aamc[i].setTotalInvested(totalMoney);
            setAssetData(aamc);
         }
      }
   }

   public Boolean getHasClientID() {

      return (getClientAccountID() != null && ! getClientAccountID().isEmpty());


   }

   public Boolean getHasPosition()
   {
      if ((getClientAccountID() != null)
         && (getActualInvestment() != null && getActualInvestment() > 0.0))
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
      if (getManaged()) {
         return getClientAccountID();
      }
      else
      {
         return getCurrentStatus();
      }
   }





}
