package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpSession;

import com.invessence.constant.*;
import com.invessence.converter.*;
import com.invessence.dao.consumer.*;
import com.invessence.data.common.*;
import com.invessence.util.*;
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

@ManagedBean(name = "cepb")
@SessionScoped
public class ConsumerEditProfileBean extends CustomerData implements Serializable
{
   private Long beanAcctnum;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs = true, disabledetailtabs = true, disablesaveButton = true;
   private Boolean prefVisible = true;
   private Integer canOpenAccount;
   private Boolean welcomeDialog = true;
   private Boolean displayGoalGraph = false,
      displayGoalText = false;

   private Integer prefView = 0;
   private String whichChart;

   private Integer imageSelected = 0;
   private JavaUtil jutil = new JavaUtil();
   private Charts charts = new Charts();

   private USMaps usstates = USMaps.getInstance();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;

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

   public void setMessageText(WebMessage messageText)
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

   public Charts getCharts()
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

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (!getWebutil().isUserLoggedIn())
            {
               getWebutil().redirect("/login.xhtml", null);
            }
            loadBasketInfo();
            whichChart = "pie";
            setPrefView(0);
            if (getWebutil().hasAccess("Advisor") || getWebutil().hasAccess("Admin"))
            {
               setRiskCalcMethod("A");
            }
            else
            {
               setRiskCalcMethod("C");
            }

            disablegraphtabs = true;
            disabledetailtabs = true;
            if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
            {
               loadData(getBeanAcctnum());
            }
            else
            {
               loadNewClientData();
            }

            canOpenAccount = initCanOpenAccount();
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
      setRiskCalcMethod("C");
      formEdit = true;
   }

   public void onChangeValue()
   {
      setRiskCalcMethod("C");
      formEdit = true;
      offsetRiskIndex();
      createAssetPortfolio(1);
   }

   public void onGoalChangeValue()
   {
      calculateGoal();
   }

   public void calculateGoal()
   {
      if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
      {
         setRiskCalcMethod("C");
         formEdit = true;
         getGoalData().setTerm(getHorizon().doubleValue());
         offsetRiskIndex();
         createAssetPortfolio(1);
         //if (getPortfolioData() != null) {
         //charts.createGoalChart(getPerformanceData(), getGoalData());
         //}
         saveProfile();
      }
   }

   public void onTaxStrategy()
   {
      formEdit = true;
      setAccountType();
      setRiskCalcMethod("C");
      offsetRiskIndex();
      loadBasketInfo();
      createAssetPortfolio(1);
   }

   public void selectedGoalType(Integer item)
   {

      if (item == null)
      {
         item = 0;
      }

      formEdit = true;
      imageSelected = item;
      setHorizon(20);
      switch (imageSelected)
      {
         case 1:
            setGoal("Growth");
            break;
         case 2:
            setGoal("Income");
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

   public void selectedGoal()
   {

      formEdit = true;
      if (getGoal().toUpperCase().contains("RETIRE"))
      {
         if (getAge() == null)
         {
            setHorizon(20);
         }
         else if (getAge() < 65)
         {
            setHorizon(65 - getAge());
         }
         else
         {
            setHorizon(2);
         }
      }
      else
      {
         if (getGoal().toUpperCase().contains("SAFETY"))
         {
            setHorizon(3);
         }
         else
         {
            setHorizon(20);
         }
      }
      loadBasketInfo();
      createAssetPortfolio(1);
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
         setGoal(getAdvisorBasket().get(getTheme()));
         setBasket(getTheme());
      }
   }

   private void resetDataForm()
   {
      disablegraphtabs = true;
      disabledetailtabs = true;
      displayGoalGraph = false;
      displayGoalText = false;
      resetCustomerData();
   }

   private void loadBasketInfo()
   {
      if (getAccountTaxable())
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
         // selectFirstBasket(); // DO this only first time.
      }
      else
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
         // selectFirstBasket();  // DO this only first time.
      }
   }

   private void loadNewClientData()
   {

      resetDataForm();
      try
      {
         UserInfoData uid = getWebutil().getUserInfoData();
         if (uid != null)
         {
            setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
            setLogonid(uid.getLogonID());
         }
         listDAO.getNewClientProfileData((CustomerData) this.getInstance());
         setDefaults();
         loadBasketInfo();
         selectFirstBasket();
         createAssetPortfolio(1); // Build default chart for the page...
         // RequestContext.getCurrentInstance().execute("custProfileDialog.show()");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void loadData(Long acctnum)
   {

      resetDataForm();
      try
      {
         if (getWebutil().isUserLoggedIn())
         {
            if (getWebutil().hasRole(WebConst.ROLE_OWNER) ||
               getWebutil().hasRole(WebConst.ROLE_ADVISOR) ||
               getWebutil().hasRole(WebConst.ROLE_ADMIN))
            {
               UserInfoData uid = getWebutil().getUserInfoData();
               if (uid != null)
               {
                  setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
                  setLogonid(uid.getLogonID());
               }
               setAcctnum(acctnum);
               listDAO.getProfileData(getInstance());
               loadBasketInfo();
            }
         }
         createAssetPortfolio(1);
         formEdit = false;
      }
      catch (Exception ex)
      {
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
      resetAllocationIndex();
      createAssetPortfolio(1); // Build default chart for the page...
   }

   public void doPortfolioReset()
   {
      resetPortfolioIndex();
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
/*
         if (getGoal().toUpperCase().contains("INCOME"))
            setTheme("0.Income");
         else if (getGoal().toUpperCase().contains("SAFETY"))
            setTheme("0.Safety");
         else
            setTheme("0.Core");
*/

         if (getTheme() == null || getTheme().isEmpty())
         {
            setTheme(InvConst.DEFAULT_THEME);
         }

         setNumOfAllocation(noOfYears);
         setNumOfPortfolio(noOfYears);
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
               buildPerformanceData();
               // charts.createLineModel(getPerformanceData());
               // if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
               charts.createGoalChart(getPerformanceData(), getGoalData());
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

      if (getPortfolioName() == null)
      {
         setPortfolioName(getLastname() + "-" + getGoal());
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
      if (getRiskCalcMethod() == null || getRiskCalcMethod().toUpperCase().startsWith("C"))
      {
         resetAllocationIndex();
         resetPortfolioIndex();
      }

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
               validate = validateProfile(); // Check if session is still valid.  If not, redirect to logon

               if (validate)
               {
                  // setDefaults();
                  acctnum = saveDAO.saveProfileData(getInstance());
                  if (acctnum > 0)
                  {
                     setAcctnum(acctnum);
                     saveDAO.saveFinancials(getInstance());
                     saveDAO.saveRiskProfile(getInstance());
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
         getWebutil().alertSupport("ConsumerEdit.saveprofile", "Error:ConsumerEdit.SaveProfile",
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
         getWebutil().redirect("/pages/consumer/funding.xhtml?acct=" + getAcctnum(), null);
         //getWebutil().redirect("/pages/consumer/cto/cto.xhtml?acct="+getAcctnum(), null);
         // }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         ex.printStackTrace();
         getWebutil().alertSupport("ConsumerEdit.fundaccount", "Error:ConsumerEdit.FundAccount",
                                   "error.fundaccount", stackTrace);
      }

   }

   public void resetForm()
   {
      try
      {
         setRiskCalcMethod("C");

         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
         {
            loadData(getBeanAcctnum());
         }
         else
         {
            loadNewClientData();
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         getWebutil().alertSupport("Consumer.addGoals", "Error:Consumer.addGoals",
                                   "error.addGoals", stackTrace);
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
            if (getWebutil().isWebProdMode())
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
      getWebutil().redirect(url, null);
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

   @PostConstruct
   public void init()
   {
      try
      {
         // Since this is used by both try and Actual, we'll handle the add/save in SaveProfile function...
         // getWebutil().validatePriviledge(Const.ROLE_OWNER);
         whichChart = "pie";
         pTab = 0;
         rTab = 0;
         mTab = 0;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
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

   public String getGoalAdjustment()
   {
      if ((!getGoalData().getReachable()))
      {
         return jutil.displayFormat(getGoalData().getCalcRecurringAmount(), "$###,###,###");
      }
      return "";
   }
}

