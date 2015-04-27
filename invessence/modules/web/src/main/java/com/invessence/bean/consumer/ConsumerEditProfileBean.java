package com.invessence.bean.consumer;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpSession;

import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.data.advisor.AdvisorData;
import com.invessence.data.common.*;
import com.invessence.util.*;
import com.invmodel.Const.InvConst;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "cepb")
@SessionScoped
public class ConsumerEditProfileBean extends AdvisorData implements Serializable
{
   private Long  beanAcctnum;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs = true
      , disabledetailtabs = true
      , disablesaveButton = true;
   private Boolean prefVisible = true;

   private Integer imageSelected = 0;

   private Charts charts = new Charts();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   public Long getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(Long beanAcctnum)
   {
      SQLData converter = new SQLData();
      this.beanAcctnum = converter.getLongData(beanAcctnum);
   }

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Boolean getDisablegraphtabs()
   {
      return disablegraphtabs;
   }

   public Boolean getDisabledetailtabs()
   {
      return disabledetailtabs;
   }

   public Boolean getDisablesaveButton()
   {
      return disablesaveButton;
   }

   public Boolean getPrefVisible()
   {
      return prefVisible;
   }

   public void setPrefVisible(Boolean prefVisible)
   {
      this.prefVisible = prefVisible;
   }

   public Charts getCharts()
   {
      return charts;
   }

   public Integer getImageSelected()
   {
      return imageSelected;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (getWebutil().hasAccess("Advisor") || getWebutil().hasAccess("Admin"))
               setRiskCalcMethod("A");
            else
               setRiskCalcMethod("C");

            if (getBeanAcctnum() != null && getBeanAcctnum() > 0L) {
               loadData(getBeanAcctnum());
            }
            else {
               loadNewClientData();
            }
         }
      }
      catch (Exception e)
      {
         resetDataForm();
      }
   }

   @PostConstruct
   public void init()
   {
      try
      {
         getWebutil().validatePriviledge(Const.ROLE_OWNER);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
         if (event.getNewValue() != null && event.getOldValue() != null) {
            if (! event.getNewValue().equals(event.getOldValue()))
               formEdit=true;
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void onChange() {
      setRiskCalcMethod("C");
      formEdit = true;
   }

   public void selectedGoalType(Integer item) {

      if (item == null)
         item = 0;

      formEdit = true;
      imageSelected = item;
      setHorizon(20);
      switch (imageSelected) {
         case 1:
            setGoal("Growth");
            break;
         case 2:
            setGoal("Income");
            setTheme("0.Income");
            break;
         case 3:
            setGoal("Safety");
            setHorizon(3);
            break;
         default:
            setGoal("Growth");
      }

      loadBasketInfo();
      createAssetPortfolio(1);
   }

   public void selectedGoal() {

      formEdit = true;
      if (getGoal().toUpperCase().contains("RETIRE")) {
         if (getAge() == null)
            setHorizon(20);
         else if (getAge() < 65)
            setHorizon(65 - getAge());
         else
            setHorizon(2);
      }
      else {
         if (getGoal().toUpperCase().contains("SAFETY"))
            setHorizon(3);
         else
            setHorizon(20);
      }
      loadBasketInfo();
      createAssetPortfolio(1);
   }

   public void handleFileUpload(FileUploadEvent event) {
      setExternalPositionFile(event.getFile().getFileName());
   }

   public void askRiskQuestions() {
      RequestContext.getCurrentInstance().openDialog("riskQuestionDialog");
   }

   public void selectedActionBasket() {
      getExcludedSubAsset().clear();
      createAssetPortfolio(1);
   }

   private void resetDataForm() {
      disablegraphtabs = true;
      disabledetailtabs = true;
      resetManagedGoalData();
   }

   private void loadBasketInfo() {
      if (getAccountTaxable())
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
      else
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
   }

   private void loadNewClientData() {

      resetAdvisorData();
      try {
         UserInfoData uid = getWebutil().getUserInfoData();
         if (uid != null) {
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         listDAO.getNewClientProfileData((ManageGoals) this.getInstance());
         setAge(30);
         setInitialInvestment(100000);
         setHorizon(20);
         setGoal("Growth");
         setAccountTaxable(false);
         resetAllocationIndex();
         resetPortfolioIndex();
         loadBasketInfo();
         createAssetPortfolio(1); // Build default chart for the page...
         // RequestContext.getCurrentInstance().execute("custProfileDialog.show()");
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void loadData(Long acctnum) {

      resetAdvisorData();
      try {
         UserInfoData uid = getWebutil().getUserInfoData();
         if (uid != null) {
            setAdvisor(uid.getGroupname()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         setAcctnum(acctnum);
         listDAO.getProfileData((ManageGoals) this.getInstance());
         loadBasketInfo();

         createAssetPortfolio(1);
         formEdit = false;
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

/*
   public void onAllocSlider(ValueChangeEvent event) {
      if (event.getNewValue() == null)
      {
         return;
      }

      setRiskCalcMethod("A");
      createAssetPortfolio(1);

   }

   public void onPortfolioSlider(ValueChangeEvent event) {
      if (event.getNewValue() == null)
      {
         return;
      }

      setRiskCalcMethod("A");
      createPortfolio(1);
   }
*/

   public void onAllocSlider(SlideEndEvent event) {
      // setAge(event.getValue());
      setRiskCalcMethod("A");
      setAllocationIndex(event.getValue());
      createAssetPortfolio(1);
      formEdit = true;
   }

   public void onPortfolioSlider(SlideEndEvent event) {
      //setDefaultRiskIndex(event.getValue());
      setRiskCalcMethod("A");
      setPortfolioIndex(event.getValue());
      createPortfolio(1);
      formEdit = true;
   }

   public void refresh() {
      createAssetPortfolio(1);
   }

   public void consumerRefresh() {
      setRiskCalcMethod("C");
      createAssetPortfolio(1);
      formEdit = true;
   }

   private void createAssetPortfolio(Integer noOfYears) {

      try {
         setNumOfAllocation(noOfYears);
         setNumOfPortfolio(noOfYears);
         buildAssetClass();
         buildPortfolio();

         createCharts();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void createPortfolio(Integer noOfYears) {

      try {
         setNumOfPortfolio(noOfYears);
         buildPortfolio();

         createCharts();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void createCharts() {

      try {
         formEdit = true;
         charts.setMeterGuage(getMeterRiskIndicator());
         if (getAssetData() != null) {
            charts.createPieModel(getAssetData(),0);
         }

         if (getPortfolioData() != null) {
            charts.createLineModel(getPortfolioData(), getPortfolioData().length);
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }


   public Boolean validateProfile() {
      try {
         String message = null;

         if (getAge() == null)
            message = "Age is required<br/>";
         if (getInitialInvestment() == null)
            message = "Initial Investment Amount needs to be defined<br/>";
         if (getRiskIndex() == null)
            message = "Risk has to be defined.<br/>";
         if (getEmail() == null)
            message = "Customer profile has to be created.<br/>";

         if (message != null) {
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Error", "Incomplete Form " + message));
            return false;
         }
      }
      catch (Exception ex) {
         FacesContext context = FacesContext.getCurrentInstance();

         context.addMessage(null, new FacesMessage("Error", "Serious Error " + "System Error: " + ex.getMessage()));
         return false;
      }
      return true;
   }

   private void setDefaults() {
      if (getAccountTaxable())
         setAccountType("Taxable");
      else
         setAccountType("Non-Taxable");

      if (getGoal() == null)
         setGoal("Growth");

      if (getPortfolioName() == null)  {
         setPortfolioName(getLastname() + "-" + getGoal());
      }

   }

   public void saveProfile() {
      long acctnum;
      Boolean validate = false;
      try
      {
         if (formEdit)  {
            validate = validateProfile();

            if (validate) {
               setDefaults();
               acctnum = saveDAO.saveProfileData(getInstance());
               setAcctnum(acctnum);
               saveDAO.saveRiskProfile(getInstance());
            }
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         getWebutil().alertSupport("managegoals.addGoals", "Error:managegoals.addGoals",
                                   "error.addGoals", stackTrace);
      }

   }

   public void resetForm() {
      try
      {
         setRiskCalcMethod("C");

         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L) {
            loadData(getBeanAcctnum());
         }
         else {
            loadNewClientData();
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         getWebutil().alertSupport("managegoals.addGoals", "Error:managegoals.addGoals",
                                   "error.addGoals", stackTrace);
      }

   }

   public void savePrefProfile(ActionEvent event) {
      createAssetPortfolio(1);
      saveProfile();
      formEdit = false;
   }

   public void savePanelProfile() {
      saveProfile();
      formEdit = false;
      // RequestContext.getCurrentInstance().openDialog("/pages/consumer/fundingDialog.xhtml");
   }

   public Integer getCanOpenAccount() {
      try
      {
         String license;
         if (getWebEnvironment())
         {
            if (getLogonid() == null)
            {
               return -1;
            }
            license = listDAO.validateState(getLogonid(), getRegisteredState());
            if (license == null || license.equalsIgnoreCase("quota"))
            {
               return 1;
            }
            else
            {
               return 0;
            }
         }
         return 2;
      }
      catch (Exception ex)
      {
         return -99;
      }
   }

   public String getForwardInstructions() {
      String msg;
      switch (getCanOpenAccount()) {
         case -1:
            msg = "Unfortunately, we <u>cannot open an account at this time</u>.\n" +
               "<p>You are currently not logged on to the system.  Either your session has expired or you have reached this page in error</p>";
            break;
         case 0:
            msg = "<p>You are being forwarded to <strong>Interactive Broker</strong> to open an account.</p>\n" +
               "<p>You will be logged off this site.</p>";
            break;
         case 1:
            msg = "We are in the <strong>process of registering in your state</strong>.\n" +
               "Unfortunately, we <u>cannot open an account at this time</u>.";
            break;
         case 2:
            msg = "Unfortunately, we <u>cannot open an account at this time</u>.";
            break;
         case -99:
            msg = "Unfortunately, we <u>cannot open an account at this time</u>.\n" +
            "<p>Please contact support desk.  Phone number and email is listed at top of the page.</p>";
            break;
         default:
            msg = "Unfortunately, we <u>cannot open an account at this time</u>.";
            break;
      }
      return msg;
   }

   public void forwardToIB() {

      if (getCanOpenAccount() == 0) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
         httpSession.invalidate();
         String url=getIblink() + "externalId=" + getAcctnum();
         getWebutil().redirect(url, null);
      }
   }

}

