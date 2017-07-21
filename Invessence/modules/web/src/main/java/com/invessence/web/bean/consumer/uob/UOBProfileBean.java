package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.*;

import com.invessence.converter.*;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invessence.web.constant.*;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.RiskCalculator;
import com.invessence.web.data.consumer.uob.UOBRiskCalculator;
import com.invessence.web.util.Impl.PagesImpl;
import com.invmodel.Const.InvConst;
import org.primefaces.component.tabview.Tab;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "uobpb")
@SessionScoped
public class UOBProfileBean extends CustomerData implements Serializable
{
   private Long beanAcctnum;
   private String backURL;
   private String newapp;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs, disabledetailtabs, disablesaveButton;
   private Boolean prefVisible;
   private Integer canOpenAccount;
   private Boolean welcomeDialog;
   private Boolean flagforInvestShow;
   private Boolean displayGoalGraph, displayGoalText;
   private String displayWhichDataPanel;

   private CustomerData origCustomerData;

   private Integer prefView;
   private String whichChart;
   private Integer pageNo;
   private Integer imageSelected;
   private JavaUtil jutil;
   private InvessenceCharts charts;
   private HighChartsController highChartsController;
   private UOBRiskCalculator riskCalculator;
   private boolean altrOnChngStrategy;
   private boolean allAnsSbmt;

   private Boolean finalCheck1, finalCheck2, confirmationCheck = false;


   public UOBProfileBean()
   {
      super();
      riskCalculator = new UOBRiskCalculator();
      highChartsController = new HighChartsController();
      charts = new InvessenceCharts();
      jutil = new JavaUtil();
      formEdit = false;
      disablegraphtabs = true;
      disabledetailtabs = true;
      disablesaveButton = true;
      prefVisible = true;
      welcomeDialog = true;
      flagforInvestShow = false;
      displayGoalGraph = false;
      displayGoalText = false;
      displayWhichDataPanel="Summary";

      setAllAnsSbmt(false);

   }

   public Integer getPageNo()
   {
      return pageNo;
   }

   public Boolean getFlagforInvestShow()
   {
      return flagforInvestShow;
   }

   public void setFlagforInvestShow(Boolean flagforInvestShow)
   {
      this.flagforInvestShow = flagforInvestShow;
   }

   public void setPageNo(Integer pageNo)
   {
      this.pageNo = pageNo;
   }

   public Long getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(Long beanAcctnum)
   {
      SQLData converter = new SQLData();
      this.beanAcctnum = converter.getLongData(beanAcctnum);
   }

   public String getBackURL()
   {
      return backURL;
   }

   public void setBackURL(String backURL)
   {
      this.backURL = backURL;
   }

   public String getNewapp()
   {
      return newapp;
   }

   public void setNewapp(String newapp)
   {
      this.newapp = newapp;
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

   public Boolean getDisplayGoalGraph()
   {
      return displayGoalGraph;
   }

   public Boolean getDisplayGoalText()
   {
      return displayGoalText;
   }

   public String getDisplayWhichDataPanel()
   {
      return (displayWhichDataPanel == null ? "Summary" : displayWhichDataPanel);
   }

   public Boolean getIsSummaryActive() {
      return (getDisplayWhichDataPanel().startsWith("S"));
   }

   public Boolean getIsDetailActive() {
      return (getDisplayWhichDataPanel().startsWith("D"));
   }

   public void setWhichData(String displayWhichDataPanel) {
      this.displayWhichDataPanel = displayWhichDataPanel;
   }

   public Integer getPrefView()
   {
      return prefView;
   }

   public String getWhichChart()
   {
      return whichChart;
   }

   public void setWhichChart(String whichChart)
   {
      this.whichChart = whichChart;
   }

   public void setPrefView(Integer prefView)
   {
      this.prefView = prefView;
   }

   public Boolean getPrefVisible()
   {
      return prefVisible;
   }

   public void setPrefVisible(Boolean prefVisible)
   {
      this.prefVisible = prefVisible;
   }

   public Integer getCanOpenAccount()
   {
      return canOpenAccount;
   }

   public InvessenceCharts getCharts()
   {
      return charts;
   }

   public USMaps getUsstates()
   {
      return usstates;
   }

   public Integer getImageSelected()
   {
      return imageSelected;
   }

   public Boolean getWelcomeDialog()
   {
      return welcomeDialog;
   }

   public UOBRiskCalculator getRiskCalculator()
   {
      return riskCalculator;
   }

   private PagesImpl pagemanager;


   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public void setPagemanager(PagesImpl pagemanager)
   {
      this.pagemanager = pagemanager;
   }
   private PagesImpl masterpagemanager;

   public PagesImpl getMasterpagemanager()
   {
      return masterpagemanager;
   }

   public void setMasterpagemanager(PagesImpl masterpagemanager)
   {
      this.masterpagemanager = masterpagemanager;
   }

   public Boolean getFormEdit()
   {
      return formEdit;
   }

   public void setFormEdit(Boolean formEdit)
   {
      this.formEdit = formEdit;
   }

   public Boolean getFinalCheck1()
   {
      return finalCheck1;
   }

   public void setFinalCheck1(Boolean finalCheck1)
   {
      this.finalCheck1 = finalCheck1;
   }

   public Boolean getFinalCheck2()
   {
      return finalCheck2;
   }

   public void setFinalCheck2(Boolean finalCheck2)
   {
      this.finalCheck2 = finalCheck2;
   }

   public Boolean getConfirmationCheck()
   {
      return confirmationCheck;
   }

   public void setConfirmationCheck(Boolean confirmationCheck)
   {
      this.confirmationCheck = confirmationCheck;
   }

   @Override
   public void setAge(Integer age)
   {
      this.age = age;
      riskCalculator.setRiskAge(age);
   }

   @Override
   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
      riskCalculator.setRiskHorizon(horizon);
   }

   @Override
   public void setRiskCalcMethod(String riskCalcMethod)
   {
      this.riskCalcMethod = riskCalcMethod;
      riskCalculator.setRiskFormula(riskCalcMethod);
   }

   public void setRiskBar(String value) {
      riskCalculator.setAnswer(9,value);
      onChangeValue();
   }

   public String getActiveRiskBar(String value) {
      if (value != null)
         if (riskCalculator.getAns9() != null)
            if (riskCalculator.getAns9().equalsIgnoreCase(value)) {
               return "selectedRiskBar";
            }
      return "";
   }

   public CustomerData getOrigCustomerData()
   {
      return origCustomerData;
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            setDisplayFTPanel(false);
            setEnableChangeStrategy(true);
            setAltrOnChngStrategy(true);
            setDoesUserHavaLogonID(false);  //  This is default, but fetchCustomer will set reset it.
            flagforInvestShow = false;

            masterpagemanager = new PagesImpl(3);
            masterpagemanager.setPage(0);
            pagemanager = new PagesImpl(9);
            if (newapp != null && newapp.startsWith("N"))
            {
               beanAcctnum = null;
            }
            else
            {
               if (!webutil.isUserLoggedIn())
               {
                  webutil.redirect("/login.xhtml", null);
                  return;
               }
            }
            riskCalculator.setNumberofQuestions(9);
            whichChart = "pie";
            setPrefView(0);
            setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);

            disablegraphtabs = true;
            disabledetailtabs = true;
            fetchClientData();

            canOpenAccount = initCanOpenAccount();
/*
            if (canOpenAccount == -1)
            {
               welcomeDialog = true;
               Map<String, Object> options = new HashMap<String, Object>();
               options.put("modal", true);
               options.put("draggable", false);
               options.put("resizable", false);
               options.put("contentHeight", 550);
               RequestContext.getCurrentInstance().openDialog("/try/tryDialog", options, null);
            }
            else
            {
               welcomeDialog = false;
            }
*/
         }
      }
      catch (Exception e)
      {
         resetDataForm();
      }
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
         if (event.getNewValue() != null && event.getOldValue() != null)
         {
            if (!event.getNewValue().equals(event.getOldValue()))
            {
               formEdit = true;
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void onChange()
   {
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      formEdit = true;
      isAllDataEntered();
   }

   public void onGoalChangeValue()
   {
      String selectedgoal;
      selectedgoal = (getGoal() == null || getGoal().isEmpty()) ? "Other" : getGoal();
      riskCalculator.setInvestmentobjective(selectedgoal);
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      isAllDataEntered();
   }


   public void onChangeValue()
   {
      formEdit = true;
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      isAllDataEntered();
   }

   public void calculateGoal()
   {
      if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
      {
         formEdit = true;
         getGoalData().setTerm(getHorizon().doubleValue());
         setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
         Double riskIndex = riskCalculator.calculateRisk();
         createAssetPortfolio(1, riskIndex);
         // if (getPortfolioData() != null) {
         // charts.createGoalChart(getProjectionData(), getGoalData());
         // }
         saveProfile(getRiskCalculator());
      }
   }

   public void onTaxStrategy()
   {
      formEdit = true;
      setAccountType();
      loadBaskets();
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
   }

   public void selectedGoal()
   {
      formEdit = true;
      if (getGoal().toUpperCase().contains("SAFETY"))
      {
         setHorizon(3);
      }
      else
      {
         setHorizon(20);
      }
   }

   public void handleFileUpload(FileUploadEvent event)
   {
      setExternalPositionFile(event.getFile().getFileName());
   }

   public void askRiskQuestions()
   {
      RequestContext.getCurrentInstance().openDialog("riskQuestionDialog");
   }


   public void selectedActionBasket()
   {
      getExcludedSubAsset().clear();
      if (getBasket() != null)
      {
         setGoal(getAdvisorBasket().get(getBasket())); // Key is the Themename, value is display
         setTheme(getBasket());                        // Set theme to the Key.  (We assigned this during selection)
         selectedGoal();
      }
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
   }

   public void selectFirstBasket()
   {
/*
      if (getAccountTaxable()) {
         setTheme(InvConst.DEFAULT_TAXABLE_THEME);
      }
      else {
         setTheme(InvConst.DEFAULT_THEME);
      }
*/

      if (getAdvisorBasket() == null)
      {
         if (getAccountTaxable())
         {
            setGoal(InvConst.DEFAULT_TAXABLE_BASKET);
            setBasket(InvConst.DEFAULT_TAXABLE_THEME);
         }
         else
         {
            setGoal(InvConst.DEFAULT_BASKET);
            setBasket(InvConst.DEFAULT_THEME);
         }
      }
      else
      {
         if (getTheme() == null)
         {
            for (String key : getAdvisorBasket().keySet())
            {
               setGoal(getAdvisorBasket().get(key));
               setBasket(key);
               setTheme(key);
               break;  // We want to select the first key on for advisor.
            }
         }
         else
         {
            setGoal(getAdvisorBasket().get(getTheme()));
            setBasket(getTheme());
         }
      }
   }

   private void resetDataForm()
   {
      disablegraphtabs = true;
      disabledetailtabs = true;
      displayGoalGraph = false;
      displayGoalText = false;
      displayWhichDataPanel="Summary";
      rTab=0;
      flagforInvestShow=false;
      riskCalculator.resetAllData();
      resetCustomerData();
      setAdvisor(webutil.getWebprofile().getDefaultAdvisor());
   }

   private void loadBaskets()
   {
      resetAdvisor();
      loadBasketInfo();
      if (getBasket() != null)
      {
         setPortfolioName(getAdvisorBasket().get(getTheme()));
      }
   }

   public void fetchClientData()
   {
      try
      {
         resetDataForm();
         setSaveVisitor(false);
         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
         {
            setDoesUserHavaLogonID(true);
            loadProfileData(getBeanAcctnum(), getRiskCalculator());
            loadRiskData(getBeanAcctnum(), getRiskCalculator());
            riskCalculator.setInvestmentobjective(getGoal());  // Goal needs to be restored to use the proper calculator
            origCustomerData = new CustomerData();
            origCustomerData.copyData(getInstance());  // Need a way to do clean copy.
            displayGoalText = true;
         }
         else
         {
            // 1) Remember we called New account from Dashboard.  So the first case is based on Dashboard.
            if(webutil.isUserLoggedIn())  {
               setDoesUserHavaLogonID(true);
               setLogonid(webutil.getLogonid());
            }
            else {
               // 2 - If user is not registered get the get New customer info.
               // NOTE: getDoesUserHavaLogonID returns false if it is null.
               setDoesUserHavaLogonID(false); // If it is null, we are forcing to be false.
               setSaveVisitor(true);
               loadNewClientData();
            }
//            loadNewClientData();

         }
         if (getManaged()) {
            setCanSaveData(false);
         }
         loadBaskets(); // Once we know about advisor, then use that info
         Double riskIndex = riskCalculator.calculateRisk();
         createAssetPortfolio(1, riskIndex);
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("Consumer.fetchClient", "Error:Consumer.fetchClient",
                              "error.fetchClient", stackTrace);
      }

   }


   @Override
   public void loadProfileData(Long acctnum, RiskCalculator riskCalculator)
   {

      try
      {
         super.loadProfileData(acctnum, riskCalculator);
         Double riskIndex = getRiskCalculator().calculateRisk();
         createAssetPortfolio(1, riskIndex);
         formEdit = false;
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public void onAllocSlider(SlideEndEvent event)
   {
      // setAge(event.getValue());
      setRiskCalcMethod(WebConst.ADVISOR_RISK_FORMULA);
      setAllocationIndex(event.getValue());
      formEdit = true;
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      setSliderAllocationIndex(getAllocationIndex());
      setFlagforInvestShow(true);
   }

   public void onPortfolioSlider(SlideEndEvent event)
   {
      //setDefaultRiskIndex(event.getValue());
      setRiskCalcMethod(WebConst.ADVISOR_RISK_FORMULA);
      setPortfolioIndex(event.getValue());
      formEdit = true;
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      // createPortfolio(1);    // Due to fixed allocaton, we have to do both (asset and portfolio)
      setFlagforInvestShow(true);
   }

   public void doAllocReset()
   {
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      setSliderAllocationIndex(getAllocationIndex());
   }

   public void doPortfolioReset()
   {
      // resetPortfolioIndex();
      createPortfolio(1); // Build default chart for the page...
   }

   public void refresh()
   {
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
   }

   public void consumerRefresh()
   {
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      formEdit = true;
      isAllDataEntered();
   }

   @Override
   public void createAssetPortfolio(Integer noOfYears, Double riskIndex)
   {

      try
      {
         super.createAssetPortfolio(noOfYears, riskIndex);
         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   @Override
   public void createPortfolio(Integer noOfYears)
   {

      try
      {
         super.createPortfolio(noOfYears);
         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void refreshChart()
   {
      if (getAssetData() != null)
      {
         charts.createPieModel(getAssetData(), 0);
         charts.createBarChart(getAssetData(), 0);
      }
   }

   private void createCharts()
   {

      try
      {
         Map<String, String> configMap = webutil.getWebprofile().getWebInfo();
         setTypeOfChart(configMap.get("CHART.ASSET.ALLOCATION"));// HIGHCHART.2DDONUT for highchart and PRIMEFACES.2DDONUT for primfaces
         if (configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("HIGHCHART.2DDONUT"))
         {
            setResultChart(highChartsController.highChartrequesthandler(getPortfolioData(), getAssetData(), configMap));
         }
         if (configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION") != null && configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.BARCHART")
            || configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.2DDONUT"))
         {

            formEdit = true;
            // charts.setMeterGuage(getMeterRiskIndicator());
            if (getAssetData() != null)
            {
               charts.createPieModel(getAssetData(), 0);
               charts.createBarChart(getAssetData(), 0);
            }
            else
            {
               charts.resetCharts();
            }

            if (getGoalData() == null || getGoalData().getGoalDesired() == null || getGoalData().getGoalDesired() == 0.0)
            {
               displayGoalGraph = false;
               displayGoalText = false;
            }
            else
            {
               displayGoalGraph = true;
               if (getPortfolioData() != null)
               {
                  buildGoalsData();
                  // charts.createLineModel(getProjectionData());
                  // if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
                  charts.createGoalChart(getProjectionData(), getGoalData());
                  if ((!getGoalData().getReachable()))
                  {
                     displayGoalText = true;
                  }
                  else
                  {
                     displayGoalText = false;
                  }
               }
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   @Override
   public void saveProfile(RiskCalculator riskCalculator)
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         if (getCanSaveData())
         {
            if (formEdit)
            {
               super.saveProfile(riskCalculator);
               formEdit = false;
            }
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("ConsumerEdit.saveprofile", "Error:ConsumerEdit.SaveProfile",
                              "error.saveprofile", stackTrace);
      }

   }


   public void savePrefProfile(ActionEvent event)
   {
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      saveProfile(getRiskCalculator());
      formEdit = false;
   }

   public void savePanelProfile()
   {
      saveProfile(getRiskCalculator());
      formEdit = false;
      // RequestContext.getCurrentInstance().openDialog("/pages/consumer/fundingDialog.xhtml");
   }

   public Integer initCanOpenAccount()
   {
      try
      {
         String license;

         if (getLogonid() == null)
         {
            return -1;
         }
         else
         {
            if (webutil.isWebProdMode())
            {
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
      }
      catch (Exception ex)
      {
         return -99;
      }
   }

   public String getForwardInstructions()
   {
      String msg;
      if (getCanOpenAccount() == null)
      {
         canOpenAccount = -1;
      }
      switch (getCanOpenAccount())
      {
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

   public void forwardToIB()
   {

      FacesContext facesContext = FacesContext.getCurrentInstance();
      HttpSession httpSession = (HttpSession) facesContext.getExternalContext().getSession(false);
      setIblink(getAccountType());
      String url = getIblink() + "&externalId=" + getAcctnum();
      webutil.redirect(url, null);
      httpSession.invalidate();

   }

   private Integer rTab = 0;


   public Integer getrTab()
   {
      return rTab;
   }

   public void setrTab(Integer rTab)
   {
      this.rTab = rTab;
   }

   public void onRTabChange(TabChangeEvent event)
   {
      Tab active = event.getTab();
      String pTabID = active.getId().toLowerCase();

      if (pTabID.equals("q1"))
      {
         rTab = 0;
      }
      if (pTabID.equals("q2"))
      {
         rTab = 1;
      }
      if (pTabID.equals("q3"))
      {
         rTab = 2;
      }
      if (pTabID.equals("q4"))
      {
         rTab = 3;
      }
      if (pTabID.equals("q5"))
      {
         rTab = 4;
      }
      if (pTabID.equals("q6"))
      {
         rTab = 5;
      }
      if (pTabID.equals("q7"))
      {
         rTab = 6;
      }
      pagemanager.setPage( 3 + rTab);
      saveProfile(getRiskCalculator());

   }

   public String getEnableNextButton()
   {
      if (pagemanager.isLastPage())
      {
         return "false";
      }
      return "true";
   }

   public String getEnablePrevButton()
   {
      if (pagemanager.getPage() == 0)
      {
         return "false";
      }
      return "true";
   }

   public void gotoPrevTab()
   {

      switch (pagemanager.getPage())
      {
         case 0:
         case 1:
         case 2:
            break;
         default:
            rTab--;
            break;
      }
      saveProfile(getRiskCalculator());
   }


   public void gotoNextPage()
   {
      Integer currentpage = pagemanager.getPage();
      if (validatePage(currentpage))
      {

         pagemanager.nextPage();

         if (pagemanager.getPage() == 3)
         {
            rTab = 0;
         }
         if (pagemanager.getPage() > 3)
         {
            rTab++;
         }
         saveProfile(getRiskCalculator());
      }
   }

   public void gotoStartOverPage()
   {
      rTab = 0;
      pagemanager.setPage(0);
   }

   public String getGoalAdjustment()
   {
      if ((!getGoalData().getReachable()))
      {
         return jutil.displayFormat(getGoalData().getCalcRecurringAmount(), "$###,###,###");
      }
      return "";
   }

   public void prevPage()
   {
      pagemanager.prevPage();
      if (pagemanager.getPage() == 3)
      {
         rTab = 0;
      }
      if (pagemanager.getPage() > 3)
      {
         rTab--;
      }
   }

   private Boolean validatePage(Integer pagenum)
   {
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      switch (pagenum)
      {
         case 0:
            if (getAge() == null || getAge() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.age.required", "Age is required", null));
            }
            if (getHorizon() == null || getHorizon() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.plantoinvestamt.requiredMsg", "Enter a number (years) for how long you plan to invest", null));
//               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.plantoinvestamt.requiredMsg", "Plan to Invest is required", null));
            }
            if (getInitialInvestment() == null || getInitialInvestment() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.investamt.requiredMsg", "Investment amount is required", null));
            }
            if (getGoal() == null || getGoal() == "")
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.goal.required", "Please choose your investment goal", null));
//               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.goal.required", "Please choose an investment strategy", null));
            }
            break;
         case 1:
            if (getAccountFinancials().getHouseholdwages() == null || getAccountFinancials().getHouseholdwages() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.wages.required", "Salary/Wages are required", null));
            }
            if (getAccountFinancials().getLiquidnetworth() == null ||getAccountFinancials().getLiquidnetworth() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.liquid.asset.required", "Liquid asset is required", null));
            }
            if (getAccountFinancials().getInvestment() == null || getAccountFinancials().getInvestment() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.other.investments.required", "Other investments are required.", null));
            }
            if (getAccountFinancials().getEquityOtherProperties() == null || getAccountFinancials().getEquityOtherProperties() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.other.investmentsProp.required", "Investment properties are required.", null));
            }


            break;
         case 2:
            if (getAccountFinancials().getTotalExpense() == null || getAccountFinancials().getTotalExpense() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.total.expenses.required", "Total expenses are required.", null));
            }
            if (getAccountFinancials().getTotalDebt() == null || getAccountFinancials().getTotalDebt() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.total.debt.required", "Total debt is required.", null));
            }
            break;
         case 3:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.employment.situation.required", "Employment situation is required.", null));
            }
            break;
         case 4:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.sources.income.required", "Sources of income is required.", null));
            }
            break;
         case 5:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.selection.required", "Select one of the option.", null));
            }
            break;
         case 6:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.level.investment.required", "Level of investment is required.", null));
            }
            break;
         case 7:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.investment.approach.required", "Investment approach is required.", null));
            }
            break;
         case 8:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.level.volatility.required", "Level of volatility is required.", null));
            }
            break;
         case 9:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.uob.long-term.investment.required", "long-term investment is required.", null));
            }
            break;
      }
      return dataOK;
   }

   public Boolean isAllDataEntered()
   {
      Boolean checkAns = true;
      for (Integer question = 1; question <= riskCalculator.getNumberofQuestions(); question++)
      {
         if (this.riskCalculator.getAnswerValue(question) == 0)
         {
            checkAns = false;
            break;
         }
      }
      setFlagforInvestShow(checkAns);
      setEnableChangeStrategy(checkAns);
      setAllAnsSbmt(checkAns);
      return checkAns;
   }

   public void saveAccount()
   {
      long acctnum;
      try
      {
         saveProfile(getRiskCalculator());

         gotoCustodyInfoForm(); // If user is already looged in then redirect to opne account page.
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         ex.printStackTrace();
         webutil.alertSupport("UOBProfile.saveAccount", "Error:UOBProfile.saveAccount",
                              "error.saveAccount", stackTrace);
      }

   }

   public void fundAccount()
   {
      long acctnum;
      try
      {
         saveProfile(getRiskCalculator());

         if (webutil.isUserLoggedIn())
         {
            uiLayout.doCustody(webutil.getLogonid(), getAcctnum());
         }
         else
         {
            // if (canOpenAccount == 0) {
            uiLayout.doMenuAction("consumer", "signup.xhtml?acct=" + getAcctnum().toString());
            //webutil.redirect("/pages/custody/td/index.xhtml?acct=" + getAcctnum(), null);
            // }
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         ex.printStackTrace();
         webutil.alertSupport("ConsumerEdit.fundaccount", "Error:ConsumerEdit.FundAccount",
                              "error.fundaccount", stackTrace);
      }

   }

   public void showFTPanel()
   {
      if (getSavedRiskFormula() == null || getSavedRiskFormula().isEmpty())
      {
         setSavedRiskFormula(getRiskCalcMethod());
         setSavedAllocSliderIndex(getAllocationIndex());
      }
      setSliderAllocationIndex(getAllocationIndex());
      setDisplayFTPanel(true);
      setEnableChangeStrategy(false);
      setAltrOnChngStrategy(false);
   }

   public void saveFTPanel() {
      setSavedRiskFormula(getRiskCalcMethod());
      setSavedAllocSliderIndex(getAllocationIndex());
      setSliderAllocationIndex(getAllocationIndex());
      savePanelProfile();
      closeFTPanel();
   }

   public void closeFTPanel() {
      setDisplayFTPanel(false);
      setEnableChangeStrategy(true);
      setAltrOnChngStrategy(true);
      // RequestContext context = RequestContext.getCurrentInstance();
      //context.execute("PF('wvfineTunePanel.hide()')");
      //context.update("fineTunePanel");
   }

   public void cancelFTPanel() {
      setRiskCalcMethod(getSavedRiskFormula());
      setSliderAllocationIndex(getSavedAllocSliderIndex());
      // riskCalculator.setRiskFormula(savedRiskFormula);
      setAllocationIndex(getSavedAllocSliderIndex());
      Double riskIndex = riskCalculator.calculateRisk();
      createAssetPortfolio(1, riskIndex);
      closeFTPanel();
   }

   public void gotoReview() {
      if (registerUser())
      {
         savePanelProfile();
         setDoesUserHavaLogonID(true);
         Double riskIndex = riskCalculator.calculateRisk();
         createAssetPortfolio(1, riskIndex);
         if (! masterpagemanager.isFirstPage())
            masterpagemanager.prevPage();
      }
   }

   public void gotoCustodyInfoForm() {
      if (!getDoesUserHavaLogonID())
      {
         // Call Register User.  If he is not registered.  If Registration fails, then stay on the same page.
         if (registerUser()) {
            uiLayout.doMenuAction("consumer", "forward.xhtml");
         }
      }
      else {
         if (getCanSaveData())
         {
            // If in Edit mode, then don't save till final changes are accounted for.
            // Need to keep the original data.
            uiLayout.doMenuAction("consumer", "forward.xhtml");
         }
         else {
            uiLayout.doMenuAction("consumer", "addon/editReview.xhtml");
            // uiLayout.doMenuAction("consumer", "editReview.xhtml");

         }
      }

   }

   public void investnow() {
      String url="https://sguat.uobkayhian.com/robo/uwealth_signup.jsp";
      String args="?roboid=" + getAcctnum() + "&email=" + getEmail();
      String command=url+args;
      uiLayout.doMenuAction(command);
   }

   private Boolean registerUser() {
      try {
         UserData userdata = new UserData();
         userdata.setFirstName(getFirstname());
         userdata.setLastName(getLastname());
         userdata.setEmail(getEmail());
         userdata.setUserID(getEmail());
         userdata.setAcctnum(getAcctnum());
         String msgheader, msg;

         if (userInfoDAO.validateUserID(userdata))
         {
            logger.debug("LOG: Validate UserID failed: " + getEmail());
            msgheader = "signup.U100";
            msg= webutil.getMessageText().getDisplayMessage(msgheader, "This Email is already registered!", null);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         }
         else
         {
            Integer myResetID = webutil.randomGenerator(0, 347896);
            userdata.setUserInfo(WebConst.ROLE_USER, getAdvisor(), getRep(), myResetID);
            long loginID = userInfoDAO.addUserInfo(userdata);

            if (loginID <= 0L)
            {
               logger.debug("ERROR: Had issue with this userid when attempting to save: " + loginID);
               msgheader = "signup.U106";
               msg = webutil.getMessageText().getDisplayMessage(msgheader, "There was some error when attempting to save this userid.  Please reach out to support desk.", null);
               webutil.redirecttoMessagePage("ERROR", msg, "Failed Signup" + msgheader);
               webutil.alertSupport("Userbean.saveUser", "Save -" + getEmail(), "Save Registration Error", null);
            }
            userdata.setLogonID(loginID);
            setLogonid(loginID);
            webutil.sendConfirmation(userdata,"W");

            setDoesUserHavaLogonID(true);
            return true;
         }
         return false;
      }
      catch (Exception ex) {
         String msgheader = "signup.EX.100";
         String msg= webutil.getMessageText().getDisplayMessage(msgheader, "Exception: Create UserID/Pwd, problem attempting to create simpleuser", null);
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msgheader));
         ex.printStackTrace();
      }
      return false;
   }

   public boolean isAltrOnChngStrategy()
   {
      return altrOnChngStrategy;
   }

   public void setAltrOnChngStrategy(boolean altrOnChngStrategy)
   {
      this.altrOnChngStrategy = altrOnChngStrategy;
   }

   public void confirmationCheckClick()
   {
      if (finalCheck1 && finalCheck2)
      {
         confirmationCheck = true;
      }
      else
      {
         confirmationCheck = false;
      }
   }

   public boolean isAllAnsSbmt()
   {
      allAnsSbmt = true;
      for (Integer question = 1; question <= riskCalculator.getNumberofQuestions(); question++)
      {
         if (this.riskCalculator.getAnswerValue(question) == 0)
         {
            allAnsSbmt = false;
            break;
         }
      }
//      if(!allAnsSbmt){
//         if(pagemanager.getPage()==riskCalculator.getNumberofQuestions()){
//            allAnsSbmt=true;
//         }
//      }
      System.out.println("allAnsSbmt~~>["+allAnsSbmt+"]");
      return allAnsSbmt;
   }

   public void setAllAnsSbmt(boolean allAnsSbmt)
   {
      this.allAnsSbmt = allAnsSbmt;
   }

   public void goBack() {
      if (backURL == null ) {
         uiLayout.defaultHome();
      }
      else {
         String[] location = backURL.split("\\.",2);
         if (! location[1].isEmpty()) {
            uiLayout.doMenuAction(location[0], location[1]);
         }
         else {
            uiLayout.doMenuAction(location[0]);
         }

      }
   }

   public void processTransfer() {
      tradeDAO.saveTradeProcessIdentifier(getAcctnum(),
                                          WebConst.TRADE_PROCESS_ALLOC,
                                          WebConst.TRADE_PROCESS_STAT_NEW,
                                          "Changed Strategy");
      setCanSaveData(true);
      saveProfile(getRiskCalculator());

      goBack();
   }
}

