package com.invessence.bean;

import com.invessence.bo.*;
import com.invessence.constant.Const;
import com.invessence.data.*;
import com.invessence.util.*;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.*;
import com.invmodel.riskCalculator.RiskIndex;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;
import org.primefaces.model.chart.*;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.*;
import java.io.*;
import java.text.*;
import java.util.*;
import static javax.faces.context.FacesContext.getCurrentInstance;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 * To change this template use File | Settings | File Templates.
 */
public class ManageGoalsBean extends ManageGoals implements Serializable
{

   private static final long serialVersionUID = 1000L;
   UserValidation uval = new UserValidation();

   private Long beanacctnum = null;
   private Long beanlogonid = null;

   private boolean tabenable = true;
   private boolean formDirty = false;
   private String currentTab;

   //DI via Spring
   GoalsBo goalsBo;
   RiskIndex riskdata = new RiskIndex();
   private EmailMessage messageText;
   private String license;

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public RiskIndex getRiskdata()
   {
      return riskdata;
   }

   public void setRiskdata(RiskIndex riskdata)
   {
      this.riskdata = riskdata;
   }

   public Long getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(Long beanacctnum)
   {
      if (beanacctnum != null)
      {
         this.beanacctnum =  beanacctnum;
         setAcctnum(beanacctnum);
      }
   }

   public Long getBeanlogonid()
   {
      return beanlogonid;
   }

   public void setBeanlogonid(Long beanlogonid)
   {
      if (beanlogonid != null)
      {
         this.beanlogonid =  beanlogonid;
         setLogonid(beanlogonid);
      }
   }

   public boolean isTabenable()
   {
      return tabenable;
   }

   public boolean getTabenable()
   {
      return tabenable;
   }

   public void setTabenable(boolean tabenable)
   {
      this.tabenable = tabenable;
   }

   public boolean isFormDirty()
   {
      return formDirty;
   }

   public void setFormDirty(boolean formDirty)
   {
      this.formDirty = formDirty;
   }

   public String getCurrentTab()
   {
      return currentTab;
   }

   public void setCurrentTab(String currentTab)
   {
      this.currentTab = currentTab;
   }


   public void setGoalsBo(GoalsBo goalsBo)
   {
      this.goalsBo = goalsBo;
   }

   //  Main Programs starts here ...


   public String addGoals(String currentTab, boolean isFormDirty)
   {
      try {
         if (currentTab.equals("tab1") && isFormDirty)
         {
            if (getLogonid() == null)
            {
               beanlogonid = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM);
               setBeanlogonid(beanlogonid);
            }
            beanacctnum = goalsBo.addGoals(getInstance());

            setBeanacctnum(beanacctnum);
            return ("success");
         }
         else
         {
            return "success";
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("managegoals.addGoals", "Error:managegoals.addGoals",
                      "error.addGoals", stackTrace);
         return "failed";
      }
   }


   public String addAssetAndLiabilities(String currentTab, boolean isFormDirty)
   {
      try
      {
         if (currentTab.equals("tab2") && isFormDirty)
         {
            goalsBo.addAssetAndLiabilities(getInstance());
            return "success";
         }
         else
         {
            return "success";
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.addAssetAndLiabilities", "Error:Goals(addAsset)",
                      "error.addAssetAndLiabilities", stackTrace);
         return "failed";
      }
   }

   public String addRiskTolerance(String currentTab, boolean isFormDirty)
   {
      Integer[] selectedChoices;
      Integer riskvalue = 0;

      try
      {
         if (currentTab.equals("tab3") && isFormDirty)
         {
            goalsBo.addRiskTolerance(getInstance());
            return "success";
         }
         else
         {
            return "success";
         }
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.addRiskTolerance", "Error:Goals(addRisk)",
                      "error.addRiskTolerance", stackTrace);
         return "failed";
      }
   }

   public String testCharts()
   {

      setName("Prashant");
      setAge(30);
      setHorizon(30);
      setInitialInvestment(100000);
      setRecurringInvestment(10000);
      setRiskIndex(14);
      setInvestment(1);
      createAssetPlan(getInstance());
      return "success";

   }


   public String resetGoalsBean()
   {
      resetManagedGoalData();
      setBeanacctnum(null);
      setBeanlogonid(null);
      setTabenable(true);
      setCurrentTab("tab1");
      return "success";
   }

   public String findGoals(Long logonid, Long acctnum)
   {
      try
      {
         if (acctnum == null)
         {
            acctnum = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.ACCTNO_PARAM);
            logonid = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM);
         }
         setAcctnum(acctnum);
         setLogonid(logonid);
      }
      catch (Exception ex)
      {
         return "failed";
      }
      if (acctnum != null)
      {
         setAcctnum(acctnum);
         setLogonid(logonid);
         ManageGoals newgoals = goalsBo.findGoals(getInstance());
         copyData(newgoals);
         setTabenable(false);
         setCurrentTab("tab1");
         findRiskTolerance();
         createAssetPlan(getInstance());
      }


      return "success";
   }


   public String findRiskTolerance()
   {
      ManageGoals goals2 = goalsBo.findRiskTolerance(getInstance());
      setSelectedchoice1(goals2.getSelectedchoice1());
      setSelectedchoice2(goals2.getSelectedchoice2());
      setSelectedchoice3(goals2.getSelectedchoice3());
      setSelectedchoice4(goals2.getSelectedchoice4());
      setSelectedchoice5(goals2.getSelectedchoice5());
      setSelectedchoice6(goals2.getSelectedchoice6());
      setSelectedchoice7(goals2.getSelectedchoice7());
      return "success";
   }

   private void formChanged() {
      Integer riskvalue = 0;
      try {
         if (isFormDirty())
         {
            riskvalue = riskdata.getRiskOffset(selectedChoices());
            setRiskIndex(riskvalue);
            adjustRiskIndex();
            addGoals(getCurrentTab(), isFormDirty());
            addAssetAndLiabilities(getCurrentTab(), isFormDirty());
            addRiskTolerance(getCurrentTab(), isFormDirty());
            createAssetPlan(getInstance());
            saveAllocation();
            savePortfolio();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   public String onFlowProcess(FlowEvent event)
   {
      String toTab, fromTab;

      try {
         fromTab = event.getOldStep();
         // System.out.println("Moving to tab ->" + toTab);
         toTab = event.getNewStep();
         // Only create asset, if we changed values in tab1,2,or 3.  Otherwise skip.
         if (getCurrentTab().equals("tab1") || getCurrentTab().equals("tab2") || getCurrentTab().equals("tab3"))
         {
               formChanged();
         }
         setFormDirty(false);
         setCurrentTab(toTab);
      }
      catch (Exception ex) {
         toTab = "tab1";
         setCurrentTab(toTab);
      }
      return toTab;
   }

   public void onTabChange(TabChangeEvent event)
   {
      String toTab;
      Integer riskvalue = 0;

      try
      {
         toTab = event.getTab().getId();
         // System.out.println("Moving to tab ->" + toTab);
         // Only create asset, if we changed values in tab1,2,or 3.  Otherwise skip.
         if (getCurrentTab().equals("tab1") || getCurrentTab().equals("tab2") || getCurrentTab().equals("tab3"))
         {
               formChanged();
         }
         setFormDirty(false);
         setCurrentTab(toTab);
      }
      catch (Exception ex)
      {
         toTab = "tab1";
         setCurrentTab(toTab);
      }
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue = null;
      String newValue = null;
      try
      {
         if (!formDirty)
         {
            if (event.getNewValue() == null)
            {
               return;
            }

            oldValue = "";
            if (event.getOldValue() != null)
            {
               oldValue = event.getOldValue().toString();
            }

            try
            {
               newValue = event.getNewValue().toString();
               Integer decimalPosition = newValue.indexOf(".0");
               if (decimalPosition > 0)
               {
                  newValue = newValue.substring(0, decimalPosition);
               }
            }
            catch (Exception ex)
            {
               newValue = event.getNewValue().toString();
            }
            // This is to ignore all already selected items.
            if (!(oldValue.equals(newValue)))
            {
               setFormDirty(true);
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void dummy()
   {
      //System.out.println("DO NOTHING");
      return;
   }

   private AssetAllocationModel allocModel;
   private PortfolioModel portfolioModel;


   public void setAllocModel(AssetAllocationModel allocModel)
   {
      this.allocModel = allocModel;
   }

   public void setPortfolioModel(PortfolioModel portfolioModel)
   {
      this.portfolioModel = portfolioModel;
   }

   public void createAssetPlan(ManageGoals goals)
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      Integer years;
      MsgData data = new MsgData();
      try
      {
         goals.setAdvisor(InvConst.INVESSENCE_ADVISOR);
         goals.setTheme(InvConst.DEFAULT_THEME);
         goals.setNumOfAllocation(goals.getHorizon());
         aamc = allocModel.getAssetDistribution((ProfileData) goals);
         setAssetData(aamc);

         goals.setNumOfPortfolio(aamc.length);
         pfclass = portfolioModel.getDistributionList(aamc,
                                                      (ProfileData) goals);
         setPortfolioData(pfclass);

         // Now refresh the pages...
         if (aamc != null)
            createAllocation();
         if (pfclass != null)
            createPortfolio();

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.createAssetPlan", "Error:Goals(createAsset)",
                      "error.createAssetPlan", stackTrace);
      }

   }

   // Asset Allocation Page Data
   private AssetClass[] aamc;
   private PieChartModel pieModel;
   private CartesianChartModel lineModel;

   private String headerText;
   private Integer minYear = 2013;
   private Integer maxYear = 2015;
   private Integer year = 2013;
   private Integer minY = 0;
   private Integer maxY = 1000000;
   private String seriesColor;
   private Integer legendXrotation;
   private Integer slices;

   public PieChartModel getPieModel()
   {
      return pieModel;
   }

   public void setPieModel(PieChartModel pieModel)
   {
      this.pieModel = pieModel;
   }

   public CartesianChartModel getLineModel()
   {
      return lineModel;
   }

   public void setLineModel(CartesianChartModel lineModel)
   {
      this.lineModel = lineModel;
   }

   public String getHeaderText()
   {
      return headerText;
   }

   public Integer getMinYear()
   {
      return minYear;
   }

   public void setMinYear(Integer minYear)
   {
      this.minYear = minYear;
   }

   public Integer getMaxYear()
   {
      return maxYear;
   }

   public void setMaxYear(Integer maxYear)
   {
      this.maxYear = maxYear;
   }

   public Integer getCalendarYear()
   {
      return year;
   }

   public void setCalendarYear(Integer year)
   {
      this.year = year;
   }

   public Integer getMinY()
   {
      return minY;
   }

   public void setMinY(Integer minY)
   {
      this.minY = minY;
   }

   public Integer getMaxY()
   {
      return maxY;
   }

   public void setMaxY(Integer maxY)
   {
      this.maxY = maxY;
   }

   public void setMaxY(Double maxY)
   {
      this.maxY = maxY.intValue();
   }

   public String getSeriesColor()
   {
      return seriesColor;
   }

   public void setSeriesColor(String seriesColor)
   {
      this.seriesColor = seriesColor;
   }

   public Integer getLegendXrotation()
   {
      return legendXrotation;
   }

   public void setLegendXrotation(Integer legendXrotation)
   {
      this.legendXrotation = legendXrotation;
   }

   public Integer getSlices()
   {
      return slices;
   }

   public void setSlices(Integer slices)
   {
      this.slices = slices;
   }

   public void setHeaderText(String name)
   {
      this.headerText = name.trim() + " Profile";
   }

   public AssetClass[] getAamc()
   {
      return aamc;
   }

   public void setAamc(AssetClass[] aamc)
   {
      this.aamc = aamc;
   }

   public void createAllocation()
   {
      int firstyear = 0;

      try {
         try
         {
            Calendar cal = Calendar.getInstance();
            setMinYear(cal.get(cal.YEAR));
            setMaxYear(getMinYear() + getHorizon());
            setCalendarYear(this.minYear);
         }
         catch (Exception ex)
         {
            System.out.println("Manage AllocationBean, getting Calender Year");
         }

         if (getName() == null)
         {
            setHeaderText("Your");
         }
         else
         {
            setHeaderText(getName());
         }
         aamc = getAssetData();
         setMaxYear(getMinYear() + (aamc.length - 1));

         setSlices(aamc[firstyear].getOrderedAsset().size());
         setMinY(getInitialInvestment());

         createPieModel(aamc[firstyear]);       //
         createLineModel();

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("managegoals.createAllocation", "Error:Goals(createAllocation)",
                      "error.createAllocation", stackTrace);
      }
   }

   public String saveAllocation()
   {
      try
      {
         goalsBo.saveAllocation(getInstance());
         return "success";
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.saveAllocation", "Error:Goals(saveAllocation)",
                      "error.saveAllocation", stackTrace);
         return "failed";
      }
   }

   private void createLineModel()
   {
      Integer year;
      Long maxGrowth = 0L;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 30;
      Long moneyInvested;
      Long moneyPnL;
      Portfolio[] portfolio;
      Double dividingFactor = 1.0;

      try
      {
         this.lineModel = new CartesianChartModel();
         portfolio = getPortfolioData();

         ChartSeries totalGrowth = new ChartSeries();
         ChartSeries totalInvested = new ChartSeries();

         //growth.setLabel("Growth");
         totalGrowth.setLabel("Growth");
         totalInvested.setLabel("Invested");
         yIncrement = (int) (((double) portfolio.length) / ((double) MAXPOINTONGRAPH));
         yIncrement = yIncrement + 1;  // offset by 1
         noOfYlabels = (int) (((double) portfolio.length) / ((double) yIncrement)) % MAXPOINTONGRAPH;
         // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
         noOfYlabels = (noOfYlabels == 0) ? portfolio.length : noOfYlabels;
         if (noOfYlabels <= 10)
         {
            this.legendXrotation = 0;
         }
         else if (noOfYlabels < 15)
         {
            this.legendXrotation = 30;
         }
         else
         {
            this.legendXrotation = 90;
         }

         int y = 0;
         totalYlabels = portfolio.length - 1;
         while (y <= totalYlabels)
         {
            year = getMinYear() + y;
            moneyInvested = Math.round(portfolio[y].getActualInvestments() / dividingFactor);
            moneyPnL = Math.round(portfolio[y].getTotalMoney() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > moneyPnL) ? maxGrowth : moneyPnL;
            //growth.set(year, portfolio[y].getTotalCapitalGrowth());
            totalGrowth.set(year.toString(), moneyPnL);
            totalInvested.set(year.toString(), moneyInvested);
            // If incrementing anything other then 1, then make sure that last year is displayed.
            if (y == totalYlabels)
            {
               y++;  // If last point is plotted, then quit.
            }
            else
            {
               y = ((y + yIncrement) > totalYlabels) ? y = totalYlabels : y + yIncrement;
            }
         }

         Integer digits = maxGrowth.toString().length();
         Double scale = Math.pow(10, digits - 1);

         setMaxY(((Math.ceil(maxGrowth / scale)) * scale));
         //lineModel.addSeries(growth);
         lineModel.addSeries(totalGrowth);
         lineModel.addSeries(totalInvested);
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.createLineModel", "Error:Goals(createLineChart)",
                      "error.createLineModel", stackTrace);
      }
   }


   private void createPieModel(AssetClass aac)
   {
      String color;
      this.pieModel = new PieChartModel();
      for (int i = 0; i < getSlices(); i++)
      {
         String assetname = aac.getOrderedAsset().get(i);
         String label = assetname + " - " + aac.getAssetRoundedActualWeight(assetname) + "%";
         pieModel.set(label, aac.getAssetRoundedActualWeight(assetname));
         color = aac.getAssetColor(assetname);//.replace('#',' ');
         color.trim();
         if (i == 0)
         {
            seriesColor = color;
         }
         else
         {
            seriesColor = seriesColor + ", " + color;
         }
      }
   }

   public void refreshChart(AssetClass aac)
   {
      createPieModel(aac);
   }

   public void refreshChart(SlideEndEvent event)
   {

      setCalendarYear(event.getValue());
      Integer offset = getCalendarYear() - getMinYear();

      createPieModel(aamc[offset]);
   }

   // Portfolio Model
   private PortfolioBo portfolioBo;
   private Portfolio[] pfclass;

   private Integer totalshare;
   private Double totalvalue;

   public void createPortfolio()
   {
      Integer data_year = 0;
      try
      {
         this.pfclass = getPortfolioData();
         if (this.pfclass != null)
         {
            loadPortfolioList(data_year);
         }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.createPortfolio", "Error:Goals(createPortfolio)",
         "error.createPortfolio", stackTrace);
      }
   }

   public String getPopulatetotalshare()
   {
      String strShare = NumberFormat.getIntegerInstance().format(totalshare);
      return strShare;
   }

   public String savePortfolio()
   {
      goalsBo.addPortfolio(getInstance());
      return "success";
   }

   private Boolean canOpenAccount = null;

   private void initAccountInfo() {
      try {
         if (getRegisteredState() == null && getLogonid() == null)
            setCanOpenAccount(false);
         if (getRegisteredState() == null) {
            license = goalsBo.validateState(getLogonid(),null);
            if (license == null || license.equalsIgnoreCase("quota"))
               setCanOpenAccount(false);
            else
               setCanOpenAccount(true);
         }
         if (getRegisteredState() != null) {
            license = goalsBo.validateState(null, getRegisteredState());
            if (license == null || license.equalsIgnoreCase("quota"))
               setCanOpenAccount(false);
            else
               setCanOpenAccount(true);

         }
      }
      catch (Exception ex) {
         setCanOpenAccount(false);
      }

   }

   public void setCanOpenAccount(Boolean canOpenAccount)
   {
            this.canOpenAccount = canOpenAccount;
   }

   public Boolean getCanOpenAccount() {
         if (this.canOpenAccount == null)
            initAccountInfo();
         return this.canOpenAccount;
   }

   public Boolean getCanNotOpenAccount() {
      if (getCanOpenAccount() == null)
         return true;
      if (getCanOpenAccount())
         return false;
      else
         return true;
   }

   public String getForwardInstructions() {
      String msg;
      if (getCanOpenAccount()) {
         msg = "<p>You are being forwarded to <strong>Interactive Broker</strong> to open an account.</p>\n" +
            "<p>You will be logged off this site.</p>";
      }
      else {
         msg = "We are in the <strong>process of registering in your state</strong>.\n" +
            "Unfortunately, we <u>cannot open an account at this time</u>.";

      }
      return msg;
   }

   public String getYesText() {
      if (getCanOpenAccount())
         return "Continue";
      else
         return "Ok, I'll wait.";
   }

   public void forwardToIB() {

      if (getCanOpenAccount()) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpSession httpSession = (HttpSession)facesContext.getExternalContext().getSession(false);
         httpSession.invalidate();
         uval.redirect(getIblink(),null);
      }

   }

   public String chooseDialog() {
      if (canOpenAccount) {
         RequestContext.getCurrentInstance().openDialog("/static/openIBAccount.xhtml");
         return "success";
      }
      return "failed";
   }

   public void dialogActionOK(SelectEvent event) {

   }

   private void alertSupport(String module, String subject,
                             String message_line, String stacktrace)
   {
      MsgData data = new MsgData();
      String msg;
      try
      {
         if (messageText == null)
         {
            System.out.println("Email alert system is down!!!!!!");
            FacesContext.getCurrentInstance().getExternalContext().redirect("/message.xhtml?message=System error:  Error code (addGoals failure)");
         }
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + "[ " + subject + " ]");
         msg = "User: " + getUserid() + "\n\n" + stacktrace;
         data.setMsg(messageText.getMessagetext(message_line, new Object[]{msg}));
         messageText.writeMessage("Error", data);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


}
