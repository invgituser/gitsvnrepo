package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpServletRequest;

import com.invessence.converter.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.tcm.*;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
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

@ManagedBean(name = "tcmpb")
@SessionScoped
public class TCMProfileBean extends TCMCustomer implements Serializable
{
   private Long beanAcctnum;
   private PagesImpl pagemanager;
   private Boolean formEdit = false;
   private Boolean disablegraphtabs = true, disabledetailtabs = true, disablesaveButton = true;
   private Boolean prefVisible = true;
   private Integer canOpenAccount;
   private Boolean welcomeDialog = true;
   private Boolean displayGoalGraph = false,
      displayGoalText = false;
   private String customErrorText;

   private Integer prefView = 0;
   private String whichChart;

   private Integer imageSelected = 0;
   private JavaUtil jutil = new JavaUtil();
   private TCMCharts charts = new TCMCharts();

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO listDAO;

   @ManagedProperty("#{consumerSaveDataDAO}")
   private ConsumerSaveDataDAO saveDAO;

   @ManagedProperty("#{webMessage}")
   private WebMessage messageText;

   public void setMessageText(WebMessage msg)
   {
      this.messageText = msg;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
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

   public void setListDAO(ConsumerListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   public void setSaveDAO(ConsumerSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
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

   public String getCustomErrorText()
   {
      return customErrorText;
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

   public TCMCharts getCharts()
   {
      return charts;
   }

   public Integer getImageSelected()
   {
      return imageSelected;
   }

   public String getFundButtonText()
   {
      if (webutil == null)
         return "Register";
      if (webutil.isUserLoggedIn())
         return "Open Account";
      else
         return "Register";

   }

   public Boolean getWelcomeDialog()
   {
      return welcomeDialog;
   }

   @Override
   public TCMRiskCalculator getRiskCalculator()
   {
      return riskCalculator;
   }

   public Integer getRetireAge()
   {
      if (riskCalculator == null)
      {
         return null;
      }
      return riskCalculator.getRetireAge();
   }

   public void setRetireAge(Integer retireAge)
   {
      getLogger().debug("Debug: Setting reirement Age as: " + retireAge);
      if (retireAge == null) {
         riskCalculator.setRetireAge(retireAge);
      }
      else {
         Integer riskHorizon = retireAge - getAge();
         if (riskHorizon > 0)
         {
            riskCalculator.setRetireAge(retireAge);
            // riskCalculator.setRiskHorizon(riskHorizon);  // New riskHorizon
            setHorizon(riskHorizon);                     // In Profile Class (Original)
            getLogger().debug("Debug: Risk Retirement Age Set: " + riskCalculator.getRetireAge());
            getLogger().debug("Debug: Risk Horizon Set: " + riskCalculator.getRiskHorizon());
         }
      }

   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // Page management
            pagemanager = new PagesImpl(4);
            pagemanager.setPage(0);
            setPrefView(0);

            // set dafaults
            riskCalculator.setNumberofQuestions(5);
            whichChart = "pie";
            disablegraphtabs = true;
            disabledetailtabs = true;

            // Client related data.
            setRiskCalcMethod("C");
            fetchClientData();
            canOpenAccount = initCanOpenAccount();
            welcomeDialog = false;
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
      formEdit = true;
   }

   public void onChangeValue()
   {
      formEdit = true;
      createAssetPortfolio(1);
   }

   public void onGoalChangeValue()
   {
      if (getGoal() == null || getGoal().isEmpty())
      {
         displayGoalText = false;
      }
      else
      {
         displayGoalText = true;
      }
      riskCalculator.setInvestmentobjective(getGoal());
      setHorizon(null);
   }

   public void calculateGoal()
   {
      if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
      {
         formEdit = true;
         getGoalData().setTerm(getHorizon().doubleValue());
         createAssetPortfolio(1);
      }
   }

   public void onTaxStrategy()
   {
      formEdit = true;
      setAccountType();
      loadBasketInfo();
      createAssetPortfolio(1);
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


   private void resetDataForm()
   {
      disablegraphtabs = true;
      disabledetailtabs = true;
      displayGoalGraph = false;
      displayGoalText = false;
      resetCustomerData();
      riskCalculator.resetAllData();
   }

   private void loadBasketInfo()
   {
      resetAdvisor();
      if (getAccountTaxable())
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "T"));
         selectFirstBasket(); // DO this select first Theme and Basket
     }
      else
      {
         setAdvisorBasket(listDAO.getBasket(getAdvisor(), "R"));
         selectFirstBasket();  // DO this select first Theme and Basket
      }
   }

   private void loadNewClientData()
   {

      try
      {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null)
         {
            setLogonid(uid.getLogonID());
         }
         setDefaults();
         listDAO.getNewClientProfileData((CustomerData) this.getInstance());
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
               setRep(uid.getRep()); // Portfolio solves the null issue, or blank issue.
               setLogonid(uid.getLogonID());
            }
            setAcctnum(acctnum);
            listDAO.getProfileData(getInstance());
         }
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
      createAssetPortfolio(1); // Build default chart for the page...
   }

   public void doPortfolioReset()
   {
      createPortfolio(1); // Build default chart for the page...
   }

   public void refresh()
   {
      createAssetPortfolio(1);
   }

   public void consumerRefresh()
   {
      createAssetPortfolio(1);
      formEdit = true;
   }


   private void createAssetPortfolio(Integer noOfYears)
   {

      try
      {
/*
         if (getTheme() == null || getTheme().isEmpty())
         {
            setTheme(InvConst.DEFAULT_THEME);
         }
*/

         setRiskIndex(riskCalculator.calculateRisk());
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

   public void riskChartSelected(ItemSelectEvent event)
   {
      if (event != null)
      {
         Integer answer;
         switch (event.getItemIndex())
         {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
               answer = event.getItemIndex() + 1;
               riskCalculator.setAns4(answer.toString());
            default:

         }
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

         // Note: Required is taken care by UI.  It automatically highlights the data

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

   }

   public void saveProfile()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
            // setDefaults();
            acctnum = saveDAO.saveProfileData(getInstance());
            if (acctnum > 0)
            {
               setAcctnum(acctnum);
               saveDAO.saveFinancials(getInstance());
               saveDAO.saveRiskProfile(acctnum, getRiskCalculator());
               saveDAO.saveAllocation(getInstance());
               saveDAO.savePortfolio(getInstance());
            }
            // FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data Saved", "Data Saved"));
            formEdit = false;
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
         saveProfile();
         if (webutil.isUserLoggedIn()) {
            uiLayout.doMenuAction("custody","index.xhtml?acct=" + getAcctnum().toString());
         }
         else {
            // if (canOpenAccount == 0) {
            uiLayout.doMenuAction("consumer","signup.xhtml?acct=" + getAcctnum().toString());
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

   public void fetchClientData()
   {
      try
      {
         resetDataForm();
         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
         {
            loadProfileData(getBeanAcctnum());
            loadRiskData(getBeanAcctnum());
            displayGoalText = true;
            createAssetPortfolio(1);
         }
         else
         {
            loadNewClientData();

         }

         loadBasketInfo(); // Once we know about advisor, then use that info
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("Consumer.addGoals", "Error:Consumer.addGoals",
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

   public String getGoalAdjustment()
   {
      if ((!getGoalData().getReachable()))
      {
         return jutil.displayFormat(getGoalData().getCalcRecurringAmount(), "$###,###,###");
      }
      return "";
   }

   public void startover()
   {
      pagemanager.setPage(0);
      // webutil.redirect("/start.xhtml", null);

   }

   public void prevPage()
   {
      customErrorText = null;   // If we go to previous page, then erase the error message.
      pagemanager.prevPage();
/*
      if (pagemanager.isFirstPage())
      {
         setDisplayGraphs(false);
         displayMeter = false;
      }
*/
   }

   private void addCustomErrorText(String text) {
      if (customErrorText == null) {
         customErrorText = text;
      }
      else {
         customErrorText = "<br/>" + text;

      }
   }

   public void retiredNext() {
      customErrorText = null;
      saveProfile();
      createAssetPortfolio(1);
      pagemanager.nextPage();
   }

   public void nextPage()
   {
      Boolean cangoToNext = true;
      customErrorText = null;

      switch (pagemanager.getPage())
      {
         case 0:
            if (getGoal() == null || getAge() == null || getInitialInvestment() == null)
            {
               cangoToNext = false;
               break;   // If null, then this method should not have been called.
            }
            if (getInitialInvestment() < 50000)
            {
               addCustomErrorText("Min $50,000 investment required.");
               cangoToNext = false;
            }
            if (getGoal().equalsIgnoreCase("retirement"))
            {
               if ((getAge() < 18) || (getAge() > 100))
               {
                  addCustomErrorText("Age must between 18 - 100");
                  cangoToNext = false;
               }
               if (riskCalculator.getRetireAge() == null || riskCalculator.getRetireAge() <= riskCalculator.getRiskAge())
               {
                  cangoToNext = false;
                  addCustomErrorText("Retired age must greater then current age");
               }
            }
            else {
               if (getGoal().equalsIgnoreCase("college")) {
                if (riskCalculator.getRiskHorizon() != null && riskCalculator.getRiskHorizon() > 20) {
                     cangoToNext = false;
                     addCustomErrorText("Horizon must between 1 - 20");
                  }
               }
                else {
                  if (riskCalculator.getRiskHorizon() != null && riskCalculator.getRiskHorizon() > 100) {
                     addCustomErrorText("Horizon must between 1 - 100");
                  }

               }

            }
            break;
         case 1:
            break;
         case 2:
            calcProjectionChart();
            doProjectionChart();
            break;
         case 3:
            break;
         case 4:

      }
      if (cangoToNext)
      {
         saveProfile();
         if (pagemanager.getPage() == 0) {  // This is before moving to next page. ONLY for FIRST PAGE
            if (getAcctnum() > 0)
               saveVisitor();
            createAssetPortfolio(1); // Since we are refreshing on real-time, we don't need to do it during next.
         }
         pagemanager.nextPage();
      }
      else
      {
         // Certain pages have default FacesContext to display error messages.
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(customErrorText,customErrorText));
      }
   }

   private void saveVisitor()
   {

      try
      {
         UserData data = new UserData();
         data.setIp(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));;
         data.setAcctnum(getAcctnum());
         data.setAdvisor(webutil.getUiprofile().getAdvisor());
         data.setRep(webutil.getUiprofile().getRep());
         data.setEmail(null);
         if (saveDAO != null) {
            saveDAO.saveVisitor(data);
         }
      }
      catch (Exception ex)
      {

      }
   }



   public void riskSelected(Integer value)
   {
      riskCalculator.setAns4(value.toString());
      formEdit = true;
      createAssetPortfolio(1);
   }

   public String getRiskGraphic(Integer value)
   {
      String defaultImage = "/javax.faces.resource/images/gl";
      String selectedImage = "/javax.faces.resource/images/sgl";
      String extension = ".png.xhtml?ln=tcm";

      if (riskCalculator.getAnswers()[4] == null)
      {
         return defaultImage + value.toString() + extension;
      }
      else
      {
         if (riskCalculator.getAnswers()[4].equalsIgnoreCase(value.toString()))
         {
            return selectedImage + value.toString() + extension;
         }
         else
         {
            return defaultImage + value.toString() + extension;
         }
      }

   }

   public void doProjectionChart()
   {
      String event = riskCalculator.getAns5();
      Integer whichslide = 0;

      if (event != null)
      {
         whichslide = Integer.parseInt(event);
      }

      if (whichslide > 0) { // Answers are stored in 1 to 5.  Whereas array is from 0-4
         whichslide -= 1;   // We have to offset the slider by 1 if > 0
        }
      //  Calls for Projection creation chart by using HighChart
      if (getProjectionDatas() != null) {
         Integer aggressivePortfolio = getProjectionDatas().size() - 1;
         charts.createProjectionHighChart(getProjectionDatas().get(whichslide),
                                          getHorizon(),
                                          getAge(),
                                          riskCalculator.getRetireAge(),
                                          getProjectionDatas().get(aggressivePortfolio));

      }
      setRiskCalcMethod("C");
      formEdit = true;
      createAssetPortfolio(1);
   }

   public ArrayList<TCMRiskCalculator> testArrayList = null;

   public ArrayList<TCMRiskCalculator> getTestArrayList()
   {
      return testArrayList;
   }

   public void testRiskModel()
   {
      String goal = "";
      Integer age;
      Integer retired = 0;
      Integer horizon = 1;
      String ans = "1";

      testArrayList = new ArrayList<TCMRiskCalculator>();

      for (Integer g=0; g < 3; g++) {
         switch (g) {
            case 0:
               goal = "Retirement";
               retired = 0;
               for (age=20; age < 80; age += 10) {
                  for (horizon = age + 1; horizon < 85; horizon +=5) {
                     for (Integer tc=0; tc < 3; tc++) {
                        switch (tc) {
                           case 0:
                              ans = "1";
                              break;
                           case 1:
                              ans = "3";
                              break;
                           case 2:
                              ans = "5";
                              break;
                        }
                        TCMRiskCalculator tmpRiskData = new TCMRiskCalculator(goal,
                                                                              age, retired, horizon,
                                                                              ans, ans, ans);
                        testArrayList.add(tmpRiskData);
/*
                  System.out.println("Catagory =" + goal +
                                        " values > " +
                                        age.toString() + "," +
                                        horizon.toString() + "," +
                                        ans + "," +
                                        ans + "," +
                                        ans + "---->" +
                                        "Answer: " + riskIdex.toString()

                  );

*/
                        if (testArrayList.size() > 1000)
                           return;
                     }
                  }
               }
               break;
            case 1:
               goal = "Retired";
               retired = 1;
               horizon = null;
               for (age=20; age < 80; age += 10) {
                     for (Integer tc=0; tc < 3; tc++) {
                        switch (tc) {
                           case 0:
                              ans = "1";
                              break;
                           case 1:
                              ans = "3";
                              break;
                           case 2:
                              ans = "5";
                              break;
                        }
                        TCMRiskCalculator tmpRiskData = new TCMRiskCalculator(goal,
                                                                              age, retired, horizon,
                                                                              ans, ans, ans);
                        testArrayList.add(tmpRiskData);
/*
                  System.out.println("Catagory =" + goal +
                                        " values > " +
                                        age.toString() + "," +
                                        horizon.toString() + "," +
                                        ans + "," +
                                        ans + "," +
                                        ans + "---->" +
                                        "Answer: " + riskIdex.toString()

                  );

*/
                        if (testArrayList.size() > 1000)
                           return;
                     }
               }
               break;
            default:
               retired = null;
               goal = "Other";
               age=null;
               for (horizon=1; horizon < 25; horizon += 5) {
                  for (Integer tc=0; tc < 3; tc++) {
                     switch (tc) {
                        case 0:
                           ans = "1";
                           break;
                        case 1:
                           ans = "3";
                           break;
                        case 2:
                           ans = "5";
                           break;
                     }
                     TCMRiskCalculator tmpRiskData = new TCMRiskCalculator(goal,
                                                                           age, retired, horizon,
                                                                           ans, ans, ans);
                     testArrayList.add(tmpRiskData);
/*
                  System.out.println("Catagory =" + goal +
                                        " values > " +
                                        age.toString() + "," +
                                        horizon.toString() + "," +
                                        ans + "," +
                                        ans + "," +
                                        ans + "---->" +
                                        "Answer: " + riskIdex.toString()

                  );

*/
                     if (testArrayList.size() > 1000)
                        return;
                  }
               }
               break;
         }
      }
   }

}

