package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.HttpServletRequest;

import com.invessence.converter.*;
import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.consumer.*;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.tcm.*;
import com.invessence.web.util.*;
import com.invessence.web.util.Impl.PagesImpl;
import com.invmodel.Const.InvConst;
import com.invmodel.model.fixedmodel.data.FMData;
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
   private String newapp;
   private Long beanAcctnum;
   private PagesImpl pagemanager;
   private Boolean formEdit = false;
   private Boolean formPortfolioEdit = false;
   private Boolean disablegraphtabs = true, disabledetailtabs = true, disablesaveButton = true;
   private Boolean prefVisible = true;
   private Integer canOpenAccount;
   private Boolean welcomeDialog = true;
   private Boolean displayGoalGraph = false,
      displayGoalText = false;
   private String customErrorText;
   private String selectedThemeName="";

   private Integer prefView = 0;
   private String whichChart;
   private String formula="";
   private String acceptterms="";

   private Integer imageSelected = 0;
   private JavaUtil jutil = new JavaUtil();
   private TCMCharts charts = new TCMCharts();
   private CustomerData origCustomerData;
   ArrayList<FMData> fmDataArrayList;
   LinkedHashMap<String, FMData> fmDataMap;
   String longDes;
   String newLongDesc;
   private Boolean finalCheck1,finalCheck2,confirmationCheck=false ;

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public String getNewapp()
   {
      return newapp;
   }

   public void setNewapp(String newapp)
   {
      this.newapp = newapp;
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

   public Boolean getFormPortfolioEdit()
   {
      return formPortfolioEdit;
   }

   public void setFormPortfolioEdit(Boolean formPortfolioEdit)
   {
      this.formPortfolioEdit = formPortfolioEdit;
   }
   public String getSelectedThemeName()
   {
      return selectedThemeName;
   }

   public void setSelectedThemeName(String selectedThemeName)
   {
      this.selectedThemeName = selectedThemeName;
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

   public String selectThemeDesc()
   {
     return getModelUtil().getThemePortfolios(getInstance().getTheme(),origCustomerData.getPortfolioName()).getDescription();
   }
   public void confirmationCheckClick()
   {
      if(finalCheck1 && finalCheck2)
         confirmationCheck= true;
      else
         confirmationCheck=false;

   }
   public void selectedTheme()
   {
      try
      {
         if (selectedThemeName == null)
         {
            return;
         }

         if (selectedThemeName.isEmpty())
         {
            selectedThemeName = null;
            FacesMessage message;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please choose one of the fund.", "Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         if (selectedThemeName.equalsIgnoreCase(origCustomerData.getPortfolioName()))
         {
            selectedThemeName = null;
            FacesMessage message;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Current and revised fund cannot be same.", "Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }
         newLongDesc=fmDataMap.get(selectedThemeName).getDescription();
         editAssetPortfolio(1);

      }
      catch (Exception ex)
      {
      }
   }
   public String getFormula()
   {
      return formula;
   }

   public void setFormula(String formula)
   {
      this.formula = formula;
      if (formula != null && ! formula.isEmpty()) {
         if (! formula.equalsIgnoreCase("D")) {
            riskCalculator.setRiskFormula("C");
         }
         else {
            riskCalculator.setRiskFormula("D");
         }
      }
   }

   public String getAcceptterms()
   {
      return acceptterms;
   }

   public void setAcceptterms(String acceptterms)
   {
      this.acceptterms = acceptterms;
   }


   public CustomerData getOrigCustomerData()
   {
      return origCustomerData;
   }

   public ArrayList<FMData> getFmDataArrayList()
   {
      return fmDataArrayList;
   }

   public LinkedHashMap<String, FMData> getFmDataMap()
   {
      return fmDataMap;
   }

   public String getLongDes()
   {
      return longDes;
   }

   public String getNewLongDesc()
   {
      return newLongDesc;
   }

   public String getFundButtonText()
   {

      if (webutil == null)
      {
         return "Save Recommendations";
      }
      if (webutil.isUserLoggedIn())
      {
         return "Open Account";
      }
      else
      {
         return "Save Recommendations";
      }

   }

   public String getAns5Tag1(Integer which)
   {
      if (which != null) {
         if (riskCalculator.getAns5() != null && riskCalculator.getAns5().equalsIgnoreCase(which.toString()))
            return "triangleShape Fleft triangleShape_Selected";
      }
      return "triangleShape Fleft";
   }
   public void exitPage()
   {
      uiLayout.goToStartPage();
   }
   public String getAns5Tag2(Integer which)
   {
      if (which != null) {
         if (riskCalculator.getAns5() != null &&  riskCalculator.getAns5().equalsIgnoreCase(which.toString()))
            return "Container90 ProjectionSlabHeight ProjectionSlabHeight_Selected";
      }
      return "Container90 ProjectionSlabHeight";
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
            formPortfolioEdit=false;

            // set dafaults
            riskCalculator.setNumberofQuestions(5);
            whichChart = "pie";
            disablegraphtabs = true;
            disabledetailtabs = true;

            if (newapp != null && newapp.startsWith("N")) {
               beanAcctnum = null;
            }

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

   public void portfolioRenderView()
   {
      String msgheader;
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // Check if the user is logged in?  If so, then they can access this page.
            if (!webutil.validatePriviledge(WebConst.ROLE_USER))
            {
               return;
            }


            selectedThemeName="";
            finalCheck1=false;
            finalCheck2=false;
            confirmationCheck=false;
            acceptterms="";
            formula="";
            newLongDesc="";
            formPortfolioEdit=true;
            // Page management
            pagemanager = new PagesImpl(6);
            pagemanager.setPage(0);
            setPrefView(0);

            // set dafaults
            riskCalculator.setNumberofQuestions(5);
            whichChart = "pie";
            disablegraphtabs = true;
            disabledetailtabs = true;

            if (newapp != null && newapp.startsWith("N")) {
               beanAcctnum = null;
            }

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
         riskCalculator.setRiskAge(null);
         riskCalculator.setRetireAge(null);
         riskCalculator.setRiskHorizon(null);
         customErrorText = null;
      }
      riskCalculator.setInvestmentobjective(getGoal());
      setHorizon(null);
   }

   public void setAnswer5(Integer ans) {

     if (ans != null && ans > 0) {
        riskCalculator.setAns5(ans.toString());
        doProjectionChart();
     }
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
            setFixedModelPortfolioList(getInstance().getTheme());
            setFmDataLinkedHashMap(getInstance().getTheme());
            origCustomerData = new CustomerData();
            origCustomerData.copyData(getInstance());
            fmDataArrayList=getFixedModelPortfolioList();
            fmDataMap=getFmDataLinkedHashMap();
            longDes=fmDataMap.get(origCustomerData.getPortfolioName()).getDescription();
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

   private void editAssetPortfolio(Integer noOfYears)
   {

      try
      {

         riskCalculator.setRiskFormula("D");
         riskCalculator.setTotalRisk(fmDataMap.get(selectedThemeName).getHighRisk());
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

   private void setDefaults()
   {
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
   }

   public void saveProfile()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         // setDefaults();
         setPortfolioName(getFixedModelName());
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


   public void savePortfolio()
   {
      long acctnum;
      Boolean validate = false;
      try
      {
         // setDefaults();
         setPortfolioName(getFixedModelName());
         acctnum = saveDAO.editProfileData(getInstance());
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
   public void onTermChange()
   {

   }
   public void startover()
   {
      pagemanager.setPage(0);
      fetchClientData();
      selectedThemeName="";
      newLongDesc="";
      // webutil.redirect("/start.xhtml", null);

   }
   public void agreeTerms()
   {
      FacesMessage message;
      if (getAcceptterms() != null && getAcceptterms().equalsIgnoreCase("Y"))
      {
         nextPage();
         return;
      }
      else
      {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot continue.  Either accept the term, or go back to dashboard.", "Error");
         FacesContext.getCurrentInstance().addMessage(null, message);
         return;
      }
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

   private void addCustomErrorText(String text)
   {
      if (customErrorText == null)
      {
         customErrorText = text;
      }
      else
      {
         customErrorText = customErrorText + "<br/>" +text;


      }
   }

   public void retiredNext()
   {
      customErrorText = null;
      saveProfile();
      createAssetPortfolio(1);
      pagemanager.nextPage();
   }

   private Boolean validatePage(Integer pagenum) {

      Boolean dataOK = true;
      pagemanager.clearErrorMessage(pagenum);

      if (pagenum == null)
         return false;

      switch (pagenum)
      {
         case 0: // Accttype page

            if (getGoal() == null || getInitialInvestment() == null)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investment.required", "Min $2,000 investment required", new Object[]{"$2,000"}));
            }
            if (getInitialInvestment() != null && getInitialInvestment() < 2000)
            {
               dataOK = false;
               pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.investment.constraint", "Min $2,000 investment required", new Object[]{"$2,000"}));
            }

            if (getGoal() != null)
            {
               if(this.getAccountType()!=null){
                  if(this.getAccountType().equalsIgnoreCase("Traditional IRA")||this.getAccountType().equalsIgnoreCase("Roth IRA")
                     ||this.getAccountType().equalsIgnoreCase("SEP IRA")||this.getAccountType().equalsIgnoreCase("Roll Over IRA")){
                     if (!getGoal().equalsIgnoreCase("retirement"))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.accounttype.retirement", "Goal cannot be changed as this is already an IRA. Please reset to Retirement.", new Object[]{getGoal()}));
                     }
                  }
               }
               if (getGoal().equalsIgnoreCase("retirement"))
               {
                  if (riskCalculator.getRiskAge() == null)
                  {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.age.constraint", "Age must be between 18 and 100 years.", null));
                  }
                  else
                  {
                     if ((getAge() < 18) || (getAge() > 100))
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.age.constraint", "Age must be between 18 and 100 years.", null));
                     }
                  }

                  if (!riskCalculator.isRetired())
                  {
                     if (riskCalculator.getRetireAge() == null)
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.retireage.required", "When do you plan to retire?", null));
                     }
                     else if (riskCalculator.getRetireAge() <= riskCalculator.getRiskAge())
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.retireage.constraint", "Retirement age must be greater than current age.", null));
                     }
                     else if (riskCalculator.getRetireAge() > 100)
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.retireage.constraint2", "Retirement age must be less than 100 years.", null));
                     }
                  }
               }
               else
               {
                  if (getGoal().equalsIgnoreCase("college"))
                  {
                     if (riskCalculator.getRiskHorizon() == null) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.college.required", "When does your child plan to attend college?", null));
                     }
                     else if (riskCalculator.getRiskHorizon() < 1 || riskCalculator.getRiskHorizon() > 18)
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.college.constraint", "Number of years to invest must be between 1 and 18 years.", null));
                     }
                  }
                  else
                  {
                     if (riskCalculator.getRiskHorizon() == null) {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.otherhorizon.required", "When do you plan to use these funds?", null));
                     }
                     else if (riskCalculator.getRiskHorizon() < 1 || riskCalculator.getRiskHorizon() > 100)
                     {
                        dataOK = false;
                        pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.otherhorizon.constraint", "Number of years to invest must be between 1 and 100 years.", null));
                     }
                  }
               }
            }
            break;
            case 1: // Investment Choices
               if (riskCalculator.getAns3() == null || riskCalculator.getAns3().isEmpty()) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.risk.radio.required", "Please choose one of these choices.", null));
               }
               break;
            case 2: // Investment Risk
                  if (riskCalculator.getAns4() == null || riskCalculator.getAns4().isEmpty()) {
                     dataOK = false;
                     pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.risk.meter.required", "Please select the image that best represents your tolerance for risk.", null));
                  }
                  break;
            case 3: // Investment Projection
               if (riskCalculator.getAns5() == null || riskCalculator.getAns5().isEmpty()) {
                  dataOK = false;
                  pagemanager.setErrorMessage(webutil.getMessageText().getDisplayMessage("validator.risk.projection.required", "Please choose an investment strategy.", null));
               }
               break;
      }

      return dataOK;
   }

   public void processTransfer()
   {
      setPortfolioName(selectedThemeName);
      savePortfolio();
      uiLayout.goToStartPage();
   }
   public void nextPage()
   {
      Boolean cangoToNext = true;
      customErrorText = null;
      Integer currentpage=pagemanager.getPage();
      if(formPortfolioEdit){
         pagemanager.clearErrorMessage(currentpage);
         currentpage=currentpage-1;
      }

      switch (currentpage)
      {
         case 0 :
            cangoToNext = validatePage(currentpage);
            break;
         case 1:
            cangoToNext = validatePage(currentpage);
            break;
         case 2:
            cangoToNext = validatePage(currentpage);
            if (cangoToNext) {
               calcProjectionChart();
               doProjectionChart();
            }
            break;
         case 3:
            cangoToNext = validatePage(currentpage);
            break;
         case 4:
            cangoToNext = validatePage(currentpage);
            break;

      }
      if (cangoToNext)
      {
         if (currentpage == 0)
            createAssetPortfolio(1); // Since we are refreshing on real-time, we don't need to do it during next.

         // User is in EDIT mode, then they must be logged, therefore, they are not visitor anymore.
         if(!formPortfolioEdit)
         {
            saveProfile();
            if (currentpage == 0)
            {  // This is before moving to next page. ONLY for FIRST PAGE
               if (getAcctnum() != null || getAcctnum() >= 0)
               {   // We need to record the account number...  SP: If data was already saved, then it skips
                  saveVisitor();
               }
            }
         }

         if((getFormula() != null && getFormula().equalsIgnoreCase("Q")) && pagemanager.getPage() == 0 && formPortfolioEdit)
            pagemanager.nextPage();
         else  if((getFormula() != null && getFormula().equalsIgnoreCase("D")) && pagemanager.getPage() == 0 && formPortfolioEdit)
            pagemanager.setPage(5);
         else
            pagemanager.nextPage();
         if(pagemanager.getPage()==5 && getFormula().equalsIgnoreCase("Q"))
         {
            selectedThemeName=getFixedModelName();
            newLongDesc=fmDataMap.get(selectedThemeName).getDescription();
         }
      }
      else
      {
         // Certain pages have default FacesContext to display error messages.
         FacesContext context = FacesContext.getCurrentInstance();
         context.addMessage(null, new FacesMessage(customErrorText, customErrorText));
      }
   }

   private void saveVisitor()
   {

      try
      {
         UserData data = new UserData();
         data.setIp(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));
         ;
         data.setAcctnum(getAcctnum());
         data.setAdvisor(webutil.getWebprofile().getDefaultAdvisor());
         data.setRep(webutil.getWebprofile().getDefaultRep());
         data.setEmail(null);
         if (saveDAO != null)
         {
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
      Integer whichslide = null;

      if ( (event != null) && (! event.isEmpty()) )
      {
         whichslide = webutil.getConverter().getIntData(event);
      }

      if (whichslide == null) {
         if (riskCalculator.getAns4() != null && ! riskCalculator.getAns4().isEmpty()) {
            whichslide = webutil.getConverter().getIntData(riskCalculator.getAns4()); // See comment below, we are offsetting it by one.
         }
         else
         {
            whichslide = 0;
         }
      }

      if (whichslide > 0)
      { // Answers are stored in 1 to 5.  Whereas array is from 0-4
         whichslide -= 1;   // We have to offset the slider by 1 if > 0
      }
      //  Calls for Projection creation chart by using HighChart
      if (getProjectionDatas() != null)
      {
         if (getProjectionDatas().size() > 0) {
         Integer portfolioID = getProjectionDatas().size() - 1;
         charts.createProjectionHighChart(getProjectionDatas().get(whichslide),
                                          getHorizon(),
                                          getAge(),
                                          riskCalculator.getRetireAge(),
                                          getProjectionDatas().get(portfolioID));
         }

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

      for (Integer g = 0; g < 3; g++)
      {
         switch (g)
         {
            case 0:
               goal = "Retirement";
               retired = 0;
               for (age = 20; age < 80; age += 10)
               {
                  for (horizon = age + 1; horizon < 85; horizon += 5)
                  {
                     for (Integer tc = 0; tc < 3; tc++)
                     {
                        switch (tc)
                        {
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
                        {
                           return;
                        }
                     }
                  }
               }
               break;
            case 1:
               goal = "Retired";
               retired = 1;
               horizon = null;
               for (age = 20; age < 80; age += 10)
               {
                  for (Integer tc = 0; tc < 3; tc++)
                  {
                     switch (tc)
                     {
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
                     {
                        return;
                     }
                  }
               }
               break;
            default:
               retired = null;
               goal = "Other";
               age = null;
               for (horizon = 1; horizon < 25; horizon += 5)
               {
                  for (Integer tc = 0; tc < 3; tc++)
                  {
                     switch (tc)
                     {
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
                     {
                        return;
                     }
                  }
               }
               break;
         }
      }
   }

}

