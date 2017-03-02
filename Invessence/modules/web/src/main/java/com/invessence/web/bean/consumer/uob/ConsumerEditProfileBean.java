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
public class ConsumerEditProfileBean extends CustomerData implements Serializable
{
   private Long beanAcctnum;
   private String newapp;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs = true, disabledetailtabs = true, disablesaveButton = true;
   private Boolean prefVisible = true;
   private Integer canOpenAccount;
   private Boolean welcomeDialog = true;
   private Boolean displayGoalGraph = false,
      displayGoalText = false;

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
   public void setAge(Integer age) {
      this.age = age;
      riskCalculator.setRiskAge(age);
   }

   @Override
   public void setHorizon(Integer horizon) {
      this.horizon = horizon;
      riskCalculator.setRiskHorizon(horizon);
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(4);
            if (newapp != null && newapp.startsWith("N")) {
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
            riskCalculator.setNumberofQuestions(7);
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
   }

   public void onGoalChangeValue()
   {
      String selectedgoal;
      selectedgoal = (getGoal() == null || getGoal().isEmpty()) ? "Other" : getGoal();
      riskCalculator.setInvestmentobjective(selectedgoal);
      createAssetPortfolio(1);
   }


   public void onChangeValue()
   {
      formEdit = true;
      riskCalculator.setRiskFormula("C");
      createAssetPortfolio(1);
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
      loadBasketInfo();
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
      resetCustomerData();
      setAdvisor(webutil.getWebprofile().getDefaultAdvisor());
   }

   private void loadBasketInfo()
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
         loadBasketInfo(); // Once we know about advisor, then use that info
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
         // loadBasketInfo();
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
      createAssetPortfolio(1);
      formEdit = true;
   }

   public void onPortfolioSlider(SlideEndEvent event)
   {
      //setDefaultRiskIndex(event.getValue());
      setRiskCalcMethod("A");
      setPortfolioIndex(event.getValue());
      createAssetPortfolio(1);
      // createPortfolio(1);    // Due to fixed allocaton, we have to do both (asset and portfolio)
      formEdit = true;
   }

   public void doAllocReset()
   {
      // resetAllocationIndex();
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
   }


   private void createAssetPortfolio(Integer noOfYears)
   {

      try
      {
         String tTheme = getTheme();
         if (getAccountTaxable()) {
            if (! tTheme.startsWith("T.")) {
               setTheme("T." + tTheme);
            }
         }
         else {
            if (tTheme.startsWith("T.")) {
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
         if(configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("HIGHCHART.2DDONUT")){
            setResultChart(highChartsController.highChartrequesthandler(getPortfolioData(),getAssetData(),configMap));
         }
         if(configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION") != null && configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.BARCHART")
            || configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.2DDONUT")){

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


   public Boolean validateProfile()
   {
      try
      {
         String message = null;

         if (getAge() == null)
         {
            message = "Age is required<br/>";
         }
         if (getInitialInvestment() == null)
         {
            message = "Initial Investment Amount needs to be defined<br/>";
         }
         if (getRiskIndex() == null)
         {
            message = "Risk has to be defined.<br/>";
         }
         if (getEmail() == null)
         {
            message = "Customer profile has to be created.<br/>";
         }

         if (message != null)
         {
            FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Error", "Incomplete Form " + message));
            return false;
         }
      }
      catch (Exception ex)
      {
         FacesContext context = FacesContext.getCurrentInstance();

         context.addMessage(null, new FacesMessage("Error", "Serious Error " + "System Error: " + ex.getMessage()));
         return false;
      }
      return true;
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

      if (getLastname() != null)
      {
         setPortfolioName(getLastname() + "-" + getGoal());
      }
      else {
         setPortfolioName(null);
      }
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

   public void tryProceed()
   {
      Boolean validate = false;
      try
      {
         String msg = "";
         if (getLastname() == null || getLastname().isEmpty())
         {
            msg = "lastname is required!";
         }
         if (getFirstname() == null || getFirstname().isEmpty())
         {
            msg = (msg.isEmpty()) ? "lastname is required!" : msg + "<br/>firstname is required!";
         }
         if (getEmail() == null || getEmail().isEmpty())
         {
            msg = (msg.isEmpty()) ? "Email is required!" : msg + "<br/>Email is required!";
         }

         if (!msg.isEmpty())
         {
            FacesContext.getCurrentInstance().addMessage("tryMsg", new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
         }
         else
         {
            RequestContext.getCurrentInstance().execute("PF('tryDialog').hide()");
         }

      }
      catch (Exception ex)
      {

      }
   }

   public void saveProfile()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         if (formEdit == null)
         {
            validate = validateProfile(); // Redirect to logon window.
         }
         else
         {
            if (formEdit)
            {
               //validate = validateProfile(); // Check if session is still valid.  If not, redirect to logon
               validate = true;
               if (validate)
               {
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
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("ConsumerEdit.saveprofile", "Error:ConsumerEdit.SaveProfile",
                              "error.saveprofile", stackTrace);
      }

   }

   public void fundAccount()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         validate = validateProfile();

         if (validate)
         {
            saveProfile();
         }
         // if (canOpenAccount == 0) {
         webutil.redirect("/pages/consumer/funding.xhtml?acct=" + getAcctnum(), null);
         //getWebutil().redirect("/pages/consumer/cto/cto.xhtml?acct="+getAcctnum(), null);
         // }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         ex.printStackTrace();
         webutil.alertSupport("ConsumerEdit.fundaccount", "Error:ConsumerEdit.FundAccount",
                              "error.fundaccount", stackTrace);
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

   private Integer pTab = 0, rTab = 0;
   private Integer mTab = 0;


   public Integer getpTab()
   {
      return pTab;
   }

   public void setpTab(Integer pTab)
   {
      this.pTab = pTab;
   }

   public Integer getrTab()
   {
      return rTab;
   }

   public void setrTab(Integer rTab)
   {
      this.rTab = rTab;
   }

   public void onPTabChange(TabChangeEvent event)
   {
      Tab active = event.getTab();
      String pTabID = active.getId().toLowerCase();

      if (pTabID.equals("p1"))
      {
         pTab = 0;
      }
      if (pTabID.equals("p2"))
      {
         pTab = 1;
      }
      if (pTabID.equals("p3"))
      {
         pTab = 2;
      }
      if (pTabID.equals("p4"))
      {
         pTab = 3;
      }
      if (pTabID.equals("p5"))
      {
         pTab = 4;
         // setShowGoalChart(true);
      }
      saveProfile();

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
      saveProfile();

   }

   public String getEnableNextButton()
   {
/*    Removing Goal section page to bottom
      if (pTab == 4)
         return "false";
*/
      if (pTab >= 3 && rTab >= 6)
      {
         return "false";
      }
      return "true";
   }

   public String getEnablePrevButton()
   {
      if (pTab == 0)
      {
         return "false";
      }
      return "true";
   }

   public void gotoPrevTab()
   {
      switch (rTab)
      {
         case 0:
            switch (pTab)
            {
               case 0:
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
                  pTab--;
               default:
                  break;
            }
            return;
         case 1:
         case 2:
         case 3:
         case 4:
         case 5:
         case 6:
         case 7:
         default:
            rTab--;
            break;
      }
      saveProfile();
   }

   public void gotoNextTab()
   {
      switch (pTab)
      {
         case 0:
         case 1:
         case 2:
            pTab++;
            rTab = 0;
            break;
         case 3:
         default:
            if (rTab >= 6)
            {
               pTab++;
               rTab = 0;

            }
            else
            {
               rTab++;
            }
            break;

      }
      saveProfile();
   }

   public void gotoNextPage()
{
   Integer currentpage = pagemanager.getPage();
   //if(!validatePage(currentpage)){

   //}else{
      pagemanager.nextPage();

      if (rTab >= 6)
      {
         pTab++;
         rTab = 0;

      }
      else
      {
         rTab++;
      }

      if(pagemanager.getPage()== 3){
         rTab = 0;
      }
  // }
   saveProfile();
}

   public void gotoStartOverPage()
   {
      pagemanager.setPage(0);
      rTab = 0;
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
      rTab--;
      pagemanager.prevPage();
   }

   private Boolean validatePage(Integer pagenum)
   {
      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);
      switch (pagenum)
   {
      case 0:
         if(getAge() == null || getAge() == 0){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.age.required", "Age is required", null));
         }
         if(getHorizon() == null || getHorizon() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.plantoinvestamt.requiredMsg", "Plan to Invest is required", null));
         }
         if(getInitialInvestment() == null || getInitialInvestment() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investamt.requiredMsg", "Investment amount is required", null));
         }
         if(getGoal() == null || getGoal() == "" ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.goal.required", "Please choose an investment strategy", null));
         }
         break;
      case 1:
         if(getHouseholdwages() == null || getHouseholdwages() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.wages.required", "Salary/Wages is required", null));
         }
         if(getMoneymarket() == null || getMoneymarket() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.liquid.asset.required", "Liquid Asset is required", null));
         }
         if(getInvestment() == null || getInvestment() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.other.investments.required", "Other Investments is required.", null));
         }
         break;
      case 2:
         if(getHouseholdwages() == null || getHouseholdwages() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.total.expenses.required", "Total Expenses is required.", null));
         }
         if(getOtherDebt() == null || getOtherDebt() == 0 ){
            dataOK = false;
            pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.total.debt.required", "Total Debt is required.", null));
         }
         break;
      case 3:case 4:
         // tab 0
         if(rTab != null && rTab == 0){
            if(this.riskCalculator != null){
               if(this.riskCalculator.getAns3() == null || this.riskCalculator.getAns3().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.employment.situation.required", "Employment Situation is required.", null));
               }
            }
         }
         // tab 1
         if(rTab != null && rTab == 1){
            if(this.riskCalculator != null){
               if( this.riskCalculator.getAns4() == null || this.riskCalculator.getAns4().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.sources.income.required", "Sources of income is required.", null));
               }
            }
         }
         // tab 2
         if(rTab != null && rTab == 2){
            if(this.riskCalculator != null){
               if( this.riskCalculator.getAns5() == null || this.riskCalculator.getAns5().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.selection.required", "Select one of the option.", null));
               }
            }
         }
         // tab 3
         if(rTab != null && rTab == 3){
            if(this.riskCalculator != null){
               if( this.riskCalculator.getAns6() == null || this.riskCalculator.getAns6().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.level.investment.required", "level of investment is required.", null));
               }
            }
         }
         // tab 4
         if(rTab != null && rTab == 4){
            if(this.riskCalculator != null){
               if(this.riskCalculator.getAns7() == null || this.riskCalculator.getAns7().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investment.approach.required", "Investment approach is required.", null));
               }
            }
         }
         // tab 5
         if(rTab != null && rTab == 5){
            if(this.riskCalculator != null){
               if(this.riskCalculator.getAns8() == null || this.riskCalculator.getAns8().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.level.volatility.required", "Level of volatility is required.", null));
               }
            }
         }
         // tab 6
         if(rTab != null && rTab == 6){
            if(this.riskCalculator != null){
               if( this.riskCalculator.getAns9() == null || this.riskCalculator.getAns9().equals("")){
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.long-term.investment.required", "long-term investment is required.", null));
               }
            }
         }
         break;

   }
            return dataOK;
   }

}

