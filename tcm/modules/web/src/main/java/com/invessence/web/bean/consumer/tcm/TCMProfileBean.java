package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;

import com.invessence.converter.*;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.tcm.*;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
import com.invmodel.Const.InvConst;
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

   @ManagedProperty("#{webutil}")
   private WebUtil webutil;

   public void setWebutil(WebUtil webutil)
   {
      this.webutil = webutil;
   }

   @ManagedProperty("#{uiLayout}")
   UILayout uiLayout;

   public void setUiLayout(UILayout uiLayout)
   {
      this.uiLayout = uiLayout;
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

   public Boolean getWelcomeDialog()
   {
      return welcomeDialog;
   }

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
      Integer riskHorizon = retireAge - getAge();
      if (riskHorizon > 0)
      {
         riskCalculator.setRetireAge(retireAge);
         riskCalculator.setRiskHorizon(riskHorizon);  // New riskHorizon
         setHorizon(riskHorizon);                     // In Profile Class (Original)
      }

   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(4);
            pagemanager.setPage(0);
            riskCalculator.setNumberofQuestions(5);
            loadBasketInfo();
            whichChart = "pie";
            setPrefView(0);
            setRiskCalcMethod("C");
            disablegraphtabs = true;
            disabledetailtabs = true;
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
      setRiskCalcMethod("C");
      formEdit = true;
   }

   public void onChangeValue()
   {
      setRiskCalcMethod("C");
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
      setHorizon(null);
   }

   public void calculateGoal()
   {
      if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
      {
         riskCalculator.setRiskFormula("C");
         formEdit = true;
         getGoalData().setTerm(getHorizon().doubleValue());
         createAssetPortfolio(1);
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
      if (getTheme() == null)
      {
         if (getAccountTaxable())
         {
            // setGoal(InvConst.DEFAULT_TAXABLE_BASKET);
            setBasket(InvConst.DEFAULT_TAXABLE_THEME);
         }
         else
         {
            // setGoal(InvConst.DEFAULT_BASKET);
            setBasket(InvConst.DEFAULT_THEME);
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
      riskCalculator.resetAllData();
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

      try
      {
         UserInfoData uid = webutil.getUserInfoData();
         if (uid != null)
         {
            setAdvisor(uid.getAdvisor()); // Portfolio solves the null issue, or blank issue.
            setRep(uid.getRep());
            setLogonid(uid.getLogonID());
         }
         setDefaults();
         listDAO.getNewClientProfileData((CustomerData) this.getInstance());
         loadBasketInfo();
         selectFirstBasket();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void loadData(Long acctnum)
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
            loadBasketInfo();
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
      setRiskCalcMethod("C");
      createAssetPortfolio(1);
      formEdit = true;
   }


   private void createAssetPortfolio(Integer noOfYears)
   {

      try
      {
         if (getTheme() == null || getTheme().isEmpty())
         {
            setTheme(InvConst.DEFAULT_THEME);
         }

         setRiskIndex(riskCalculator.calculateRisk(getGoal()));
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
         if (formEdit)
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
         // if (canOpenAccount == 0) {
         webutil.redirect("/pages/custody/td/cto.xhtml?acct=" + getAcctnum(), null);
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

   public void fetchClientData()
   {
      try
      {
         resetDataForm();
         if (getBeanAcctnum() != null && getBeanAcctnum() > 0L)
         {
            loadData(getBeanAcctnum());
            loadRiskData(getBeanAcctnum());

         }
         else
         {
            loadNewClientData();

         }
         createAssetPortfolio(1);
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
      pagemanager.prevPage();
/*
      if (pagemanager.isFirstPage())
      {
         setDisplayGraphs(false);
         displayMeter = false;
      }
*/
   }

   public void nextPage()
   {
      Boolean cangoToNext = true;
      FacesContext context = FacesContext.getCurrentInstance();
      String msg = "", header = "";

      switch (pagemanager.getPage())
      {
         case 0:
            if (getGoal() == null || getAge() == null || getInitialInvestment() == null)
            {
               break;   // If null, then this method should not have been called.
            }
            if ((getAge() < 18) || (getAge() > 100))
            {
               msg = "Age must between 18 - 100";
               cangoToNext = false;
            }
            if (getInitialInvestment() < 50000)
            {
               msg += "Min $50,000 investment required.";
               cangoToNext = false;
            }
            if (getGoal().equalsIgnoreCase("retirement"))
            {
               if (riskCalculator.getRetireAge() <= getAge())
               {
                  msg = "Retirement age must greater than current age.";
                  cangoToNext = false;
               }
            }
            break;
         case 1:
            break;
         case 2:
            calcProjectionChart();
            doProjectionChart(null);
            break;
         case 3:
            break;
         case 4:

      }
      if (cangoToNext)
      {
         saveProfile();
         createAssetPortfolio(1);
         pagemanager.nextPage();
      }
      else
      {
         context.addMessage(null, new FacesMessage(msg,msg));
      }
   }

   public void riskSelected(Integer value)
   {
      riskCalculator.setAns4(value.toString());
      setRiskCalcMethod("C");
      formEdit = true;
      setRiskIndex(riskCalculator.calculateRisk(getGoal()));
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


   public void doProjectionChart(SlideEndEvent event)
   {
      Integer whichslide = 0;
      if (event != null)
      {
         whichslide = event.getValue();
         riskCalculator.setAns5(whichslide.toString());
      }

      charts.createProjectionChart(getProjectionDatas().get(whichslide), getHorizon());
      setRiskCalcMethod("C");
      formEdit = true;
      setRiskIndex(riskCalculator.calculateRisk(getGoal()));
      createAssetPortfolio(1);

      //  Calls for Projection creation chart by using HighChart
      charts.createProjectionHighChart(getProjectionDatas().get(whichslide), getHorizon(),getAge(),riskCalculator.getRetireAge());

   }

   public void testRiskModel()
   {
      String goal = "";

      for (Integer g=0; g < 2; g++) {
         switch (g) {
            case 0:
               goal = "Retirement";
               break;
            default:
               goal = "Other";
               break;
         }
         Integer horizon = 1;
         String ans = "1";
         for (Integer age=20; age < 100; age += 15) {
            riskCalculator.setRiskAge(age);
            for (Integer tc=0; tc < 3; tc++) {
               switch (tc) {
                  case 0:
                     horizon = 1;
                     ans = "1";
                     break;
                  case 1:
                     horizon = 7;
                     ans = "3";
                     break;
                  case 2:
                     horizon = 20;
                     ans = "5";
                     break;
               }
               riskCalculator.setRiskHorizon(horizon);
               riskCalculator.setAns3(ans);
               riskCalculator.setAns4(ans);
               riskCalculator.setAns5(ans);
               Double riskIdex = riskCalculator.calculateRisk(goal);
               System.out.println("Catagory =" + goal +
                                     " values > " +
                                     age.toString() + "," +
                                     horizon.toString() + "," +
                                     ans + "," +
                                     ans + "," +
                                     ans + "---->" +
                                     "Answer: " + riskIdex.toString()
               );
            }
         }
      }
   }

}

