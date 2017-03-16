package com.invessence.web.bean.consumer.uob;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpSession;

import com.invessence.converter.*;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invessence.web.constant.*;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.inv.INVRiskCalculator;
import com.invessence.web.data.consumer.uob.UOBRiskCalculator;
import com.invessence.web.util.*;
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
   private String newapp;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs = true, disabledetailtabs = true, disablesaveButton = true;
   private Boolean prefVisible = true;
   private Integer canOpenAccount;
   private Boolean welcomeDialog = true;
   private Boolean flagforInvestShow = false;
   private Boolean displayGoalGraph = false,
      displayGoalText = false;

   private Boolean fineTunePanel = false;
   private Integer prefView = 0;
   private String whichChart;
   private Integer pageNo = 0;
   private Integer imageSelected = 0;
   private JavaUtil jutil = new JavaUtil();
   private InvessenceCharts charts = new InvessenceCharts();
   private HighChartsController highChartsController = new HighChartsController();
   private UOBRiskCalculator riskCalculator = new UOBRiskCalculator();

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

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            fineTunePanel = false;
            flagforInvestShow = false;

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
            if (webutil.hasAccess("Advisor") || webutil.hasAccess("Admin"))
            {
               setRiskCalcMethod("A");
            }
            else
            {
               setRiskCalcMethod("C");
            }

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
      riskCalculator.setRiskFormula("C");
      formEdit = true;
      isAllDataEntered();
   }

   public void onGoalChangeValue()
   {
      String selectedgoal;
      selectedgoal = (getGoal() == null || getGoal().isEmpty()) ? "Other" : getGoal();
      riskCalculator.setInvestmentobjective(selectedgoal);
      createAssetPortfolio(1);
      isAllDataEntered();
   }


   public void onChangeValue()
   {
      formEdit = true;
      riskCalculator.setRiskFormula("C");
      createAssetPortfolio(1);
      isAllDataEntered();
   }

   public void calculateGoal()
   {
      if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
      {
         formEdit = true;
         getGoalData().setTerm(getHorizon().doubleValue());
         riskCalculator.setRiskFormula("C");
         createAssetPortfolio(1);
         // if (getPortfolioData() != null) {
         // charts.createGoalChart(getProjectionData(), getGoalData());
         // }
         saveProfile();
      }
   }

   public void onTaxStrategy()
   {
      formEdit = true;
      setAccountType();
      riskCalculator.setRiskFormula("C");
      loadBaskets();
      createAssetPortfolio(1);
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
      createAssetPortfolio(1);
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
      rTab=0;
      fineTunePanel=false;
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
         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
         {
            loadProfileData(getBeanAcctnum());
            loadRiskData(getBeanAcctnum());
            riskCalculator.setInvestmentobjective(getGoal());  // Goal needs to be restored to use the proper calculator
            displayGoalText = true;
         }
         else
         {
            loadNewClientData();

         }
         loadBaskets(); // Once we know about advisor, then use that info
         createAssetPortfolio(1);
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("Consumer.fetchClient", "Error:Consumer.fetchClient",
                              "error.fetchClient", stackTrace);
      }

   }


   private void loadNewClientData()
   {

      // resetDataForm();
      try
      {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null)
         {
            setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
            setRep(uid.getRep()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         listDAO.getNewClientProfileData((CustomerData) this.getInstance());
         setDefaults();
         // loadBaskets();
         // selectFirstBasket();
         // createAssetPortfolio(1); // Build default chart for the page...
         // RequestContext.getCurrentInstance().execute("custProfileDialog.show()");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void loadProfileData(Long acctnum)
   {

      try
      {
         if (webutil.isUserLoggedIn())
         {
            UserInfoData uid = webutil.getUserInfoData();
            if (uid != null)
            {
               setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
               setLogonid(uid.getLogonID());
            }
            setAcctnum(acctnum);
            listDAO.getProfileData(getInstance());
         }
         createAssetPortfolio(1);
         formEdit = false;
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void loadRiskData(Long acctnum)
   {
      try
      {
         listDAO.getRiskProfileData(acctnum, riskCalculator);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void onAllocSlider(SlideEndEvent event)
   {
      // setAge(event.getValue());
      setRiskCalcMethod("A");
      setAllocationIndex(event.getValue());
      formEdit = true;
      createAssetPortfolio(1);
      setFlagforInvestShow(true);
   }

   public void onPortfolioSlider(SlideEndEvent event)
   {
      //setDefaultRiskIndex(event.getValue());
      setRiskCalcMethod("A");
      setPortfolioIndex(event.getValue());
      formEdit = true;
      createAssetPortfolio(1);
      // createPortfolio(1);    // Due to fixed allocaton, we have to do both (asset and portfolio)
      setFlagforInvestShow(true);
   }

   public void doAllocReset()
   {
      setRiskCalcMethod("C");
      createAssetPortfolio(1); // Build default chart for the page...
   }

   public void doPortfolioReset()
   {
      // resetPortfolioIndex();
      createPortfolio(1); // Build default chart for the page...
   }

   public void refresh()
   {
      createAssetPortfolio(1);
   }

   public void consumerRefresh()
   {
      setRiskCalcMethod("C");
      createAssetPortfolio(1);
      formEdit = true;
      isAllDataEntered();
   }


   private void createAssetPortfolio(Integer noOfYears)
   {

      try
      {
         String tTheme = getTheme();
         if (getAccountTaxable())
         {
            if (!tTheme.startsWith("T."))
            {
               setTheme("T." + tTheme);
            }
         }
         else
         {
            if (tTheme.startsWith("T."))
            {
               setTheme(tTheme.substring(2));
            }
         }
         setRiskIndex(riskCalculator.calculateRisk());
         setNumOfAllocation(1);
         setNumOfPortfolio(1);
         buildAssetClass();
         buildPortfolio();

         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void createPortfolio(Integer noOfYears)
   {

      try
      {
         setNumOfPortfolio(noOfYears);
         buildPortfolio();

         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void refreshChart()
   {
      if (whichChart.toLowerCase().equals("pie"))
      {
         charts.createPieModel(getAssetData(), 0);
      }
      else if (whichChart.toLowerCase().equals("bar"))
      {
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


   private void setAccountType()
   {
      if (getAccountTaxable())
      {
         setAccountType("Taxable");
      }
      else
      {
         setAccountType("Non-Taxable");
      }

   }

   private void setDefaults()
   {

      setPortfolioName(null);
      if (getAge() == null)
      {
         setAge(30);
      }
      if (getInitialInvestment() == null)
      {
         setInitialInvestment(100000);
      }
      if (getHorizon() == null)
      {
         setHorizon(20);
      }
      if (getGoal() == null)
      {
         setGoal("Growth");
      }

      if (getAccountType() == null)
      {
         setAccountTaxable(false);
         setAccountType();
      }

/*
      if (getRiskCalcMethod() == null || getRiskCalcMethod().toUpperCase().startsWith("C"))
      {
         resetAllocationIndex();
         resetPortfolioIndex();
      }
*/

   }

   public void saveProfile()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         if (formEdit)
         {
            // isAllDataEntered();  // Used to determine if we should turn on The InvestNow button
            // setDefaults();
            acctnum = saveDAO.saveProfileData(getInstance());
            if (acctnum > 0)
            {
               setAcctnum(acctnum);
               saveDAO.saveFinancials(getInstance());
               saveDAO.saveRiskProfile(acctnum, riskCalculator);
               saveDAO.saveAllocation(getInstance());
               saveDAO.savePortfolio(getInstance());
            }
            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", "Data Saved"));
            formEdit = false;
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
      createAssetPortfolio(1);
      saveProfile();
      formEdit = false;
   }

   public void savePanelProfile()
   {
      saveProfile();
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
      saveProfile();

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
      saveProfile();
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
         saveProfile();
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
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.age.required", "Age is required", null));
            }
            if (getHorizon() == null || getHorizon() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.plantoinvestamt.requiredMsg", "Plan to Invest is required", null));
            }
            if (getInitialInvestment() == null || getInitialInvestment() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamt.requiredMsg", "Investment amount is required", null));
            }
            if (getGoal() == null || getGoal() == "")
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.goal.required", "Please choose an investment strategy", null));
            }
            break;
         case 1:
            if (getHouseholdwages() == null || getHouseholdwages() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.wages.required", "Salary/Wages is required", null));
            }
            if (getMoneymarket() == null || getMoneymarket() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.liquid.asset.required", "Liquid Asset is required", null));
            }
            if (getInvestment() == null || getInvestment() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.other.investments.required", "Other Investments is required.", null));
            }
            break;
         case 2:
            if (getTotalExpense() == null || getTotalExpense() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.total.expenses.required", "Total Expenses is required.", null));
            }
            if (getTotalLiability() == null || getTotalLiability() == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.total.debt.required", "Total Debt is required.", null));
            }
            break;
         case 3:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.employment.situation.required", "Employment Situation is required.", null));
            }
            break;
         case 4:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sources.income.required", "Sources of income is required.", null));
            }
            break;
         case 5:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.selection.required", "Select one of the option.", null));
            }
            break;
         case 6:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.level.investment.required", "level of investment is required.", null));
            }
            break;
         case 7:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investment.approach.required", "Investment approach is required.", null));
            }
            break;
         case 8:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.level.volatility.required", "Level of volatility is required.", null));
            }
            break;
         case 9:
            if (this.riskCalculator.getAnswerValue(pagenum) == 0)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.long-term.investment.required", "long-term investment is required.", null));
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
      return checkAns;
   }

   public void fundAccount()
   {
      long acctnum;
      try
      {
         saveProfile();

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


}

