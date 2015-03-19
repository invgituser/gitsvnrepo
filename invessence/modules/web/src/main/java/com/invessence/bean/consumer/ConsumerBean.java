package com.invessence.bean.consumer;

import java.io.Serializable;
import java.text.NumberFormat;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpSession;

import com.invessence.constant.Const;
import com.invessence.converter.*;
import com.invessence.dao.common.CommonDAO;
import com.invessence.dao.consumer.*;
import com.invessence.data.*;
import com.invessence.data.common.ManageGoals;
import com.invessence.util.*;
import com.invmodel.portfolio.data.Portfolio;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 2:56 PM
 * To change this template use File | Settings | File Templates.
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "cBean")
@SessionScoped
public class ConsumerBean extends ManageGoals implements Serializable
{

   private static final long serialVersionUID = 1000L;

   private String beanAcctnum;

   private boolean tabenable = true;
   private boolean formDirty = false;
   private String currentTab;
   private String license;

   //DI via Spring
   @ManagedProperty("#{consumerSaveDataDAO}")
   ConsumerSaveDataDAO consumerSaveDAO;

   @ManagedProperty("#{commonDAO}")
   CommonDAO commonDao;

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   private WebUtil webutil = new WebUtil();
   private Charts charts = new Charts();


   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public void setConsumerSaveDAO(ConsumerSaveDataDAO consumerSaveDAO)
   {
      this.consumerSaveDAO = consumerSaveDAO;
   }

   public void setCommonDao(CommonDAO commonDao)
   {
      this.commonDao = commonDao;
   }

   public String getBeanAcctnum()
   {
      return beanAcctnum;
   }

   public void setBeanAcctnum(String beanAcctnum)
   {
      this.beanAcctnum = beanAcctnum;
   }

   public Charts getCharts()
   {
      return charts;
   }

   public void preRenderView()
   {

      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            SQLData converter = new SQLData();
            Long acctnum = converter.getLongData(beanAcctnum);
            if (acctnum != null && acctnum > 0L) {
               findGoals(webutil.getLogonid(), acctnum);
            }
            else {
               resetConsumerBean();

            }
         }

      }
      catch (Exception e)
      {
         resetConsumerBean();
      }
   }

   @PostConstruct
   public void init()
   {
      try
      {
         webutil.validatePriviledge(Const.ROLE_OWNER);
      }
      catch (Exception e)
      {
         e.printStackTrace();
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

   public String getCurrentTab()
   {
      return currentTab;
   }

   public void setCurrentTab(String currentTab)
   {
      this.currentTab = currentTab;
   }

   //  Main Programs starts here ...


   public String addGoals(String currentTab)
   {
      try {
         long acctnum;
         if (currentTab.equals("tab1") && formDirty)
         {
            acctnum = consumerSaveDAO.saveProfileData(getInstance());
            setAcctnum(acctnum);
         }
         return "success";
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("managegoals.addGoals", "Error:managegoals.addGoals",
                      "error.addGoals", stackTrace);
         return "failed";
      }
   }


   public String addAssetAndLiabilities(String currentTab)
   {
      try
      {
         if (currentTab.equals("tab2") && formDirty)
         {
            consumerSaveDAO.saveFinancials(getInstance());
         }
         return "success";
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.addAssetAndLiabilities", "Error:Goals(addAsset)",
                      "error.addAssetAndLiabilities", stackTrace);
         return "failed";
      }
   }

   public String addRiskTolerance(String currentTab)
   {
      try
      {
         if (currentTab.equals("tab3") && formDirty)
         {
            consumerSaveDAO.saveRiskProfile(getInstance());
         }
         return "success";
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         alertSupport("ManageGoalsBean.addRiskTolerance", "Error:Goals(addRisk)",
                      "error.addRiskTolerance", stackTrace);
         return "failed";
      }
   }


   public String resetConsumerBean()
   {
      resetManagedGoalData();
      setTabenable(true);
      setCurrentTab("tab1");
      setLogonid(webutil.getLogonid());
      return "success";
   }

   public String findGoals(Long logonid, Long acctnum)
   {
      try
      {
         if (acctnum != null)
         {
            ManageGoals newgoals = commonDao.getSingleAccounts(acctnum);
            if (newgoals != null) {
               copyData(newgoals);
            }
            setTabenable(false);
            setCurrentTab("tab1");
            createAssetPortfolio(getHorizon());
         }
      }
      catch (Exception ex) {

      }
      return "success";
   }

   public void onGoalChange() {
      if (getAccountType().toUpperCase().contains("INCOME"))
         setTheme("0.Income");
      else
         setTheme(null);
      // determineTaxable(getGoal(), getAccountType());
   }


   private void formChanged() {
      Integer riskvalue = 0;
      try {
         if (formDirty)
         {
            riskvalue = getRiskdata().getRiskOffset(selectedChoices());
            setRiskIndex(riskvalue);
            offsetRiskIndex();
            addGoals(getCurrentTab());
            addAssetAndLiabilities(getCurrentTab());
            addRiskTolerance(getCurrentTab());
            createAssetPortfolio(getHorizon());
            saveAllocation();
            savePortfolio();
            formDirty = false;
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
               formDirty = true;
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   private void createAssetPortfolio(Integer noOfYears) {

      try {
         setNumOfAllocation(noOfYears);
         setNumOfPortfolio(noOfYears);
         buildConsumerPortfolio();
         createCharts(noOfYears);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void createCharts(Integer offset) {

      try {
         if (getAssetData() != null) { // Create chart should always be created for first year.
            charts.createPieModel(getAssetData(), 0);
         }

         if (getPortfolioData() != null) {
            charts.createLineModel(getPortfolioData(), getPortfolioData().length);
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }


   public String saveAllocation()
   {
      try
      {
         consumerSaveDAO.saveAllocation(getInstance());
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

   public void refreshChart(SlideEndEvent event)
   {

      setCalendarYear(event.getValue());
      Integer offset = getCalendarYear() - charts.getMinYearPoint();

      if (getAssetData() != null) {
         charts.createPieModel(getAssetData(), offset);
      }
   }

   // Portfolio Model
   private Integer totalshare;
   private Double totalvalue;

   public String getPopulatetotalshare()
   {
      String strShare = NumberFormat.getIntegerInstance().format(totalshare);
      return strShare;
   }

   public String savePortfolio()
   {
      consumerSaveDAO.savePortfolio(getInstance());
      return "success";
   }

   private Boolean canOpenAccount = null;

   private void initAccountInfo() {
      try {
         if (getWebEnvironment() != null & getWebEnvironment().equalsIgnoreCase("Prod")) {
            if (getRegisteredState() == null && getLogonid() == null)
               setCanOpenAccount(false);
            if (getRegisteredState() == null) {
               license = commonDao.validateState(getLogonid(),null);
               if (license == null || license.equalsIgnoreCase("quota"))
                  setCanOpenAccount(false);
               else
                  setCanOpenAccount(true);
            }
            if (getRegisteredState() != null) {
               license = commonDao.validateState(null, getRegisteredState());
               if (license == null || license.equalsIgnoreCase("quota"))
                  setCanOpenAccount(false);
               else
                  setCanOpenAccount(true);
            }
         }
            else {
               setCanOpenAccount(false);
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
         getWebutil().redirect(getIblink(),null);
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
         data.setMsg(messageText.buildInternalMessage(message_line, new Object[]{msg}));
         messageText.writeMessage("Error", data);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


}
