package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.LTAMOptimizer;
import com.invessence.bean.ltam.LTAMCharts;
import com.invessence.constant.Const;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.ConsumerListDataDAO;
import com.invessence.dao.ltam.*;
import com.invessence.data.LTAMTheme;
import com.invessence.data.common.AccountData;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import org.primefaces.context.RequestContext;
import org.primefaces.event.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ceb")
@SessionScoped
public class ConsumerEditBean extends LTAMCustomerData implements Serializable
{
   private String beanaction;
   private String beanacctnum;
   private String formmode;
   private String beanTimeToSaveID;
   private String beanAdvisor;
   private String beanRep;
   private String beanAmount;
   private String beanfirstname;
   private String beanlastname;
   private Boolean disableInvestment;  // Not USED:  Display Investment as editable mode or not?
   private Boolean displayGraphs, reviewPage, displayMeter;
   SQLData converter = new SQLData();
   private PagesImpl pagemanager;
   private Integer ltammenu;
   private LTAMCharts ltamcharts;
   private LTAMTheme theme;
   private Map<String,String> displaythememap;
   private ArrayList<LTAMTheme> themeList;
   private String selectedPie;
   private Integer selectedPage4Image;
   private AccountData accountData;
   private LTAMCustomerData origCustomerData;

   @ManagedProperty("#{ltamOptimizer}")
   private LTAMOptimizer ltamoptimizer;

   public void setLtamoptimizer(LTAMOptimizer ltamoptimizer)
   {
      this.ltamoptimizer = ltamoptimizer;
   }

   @ManagedProperty("#{ltamListDataDAO}")
   private LTAMListDataDAO listDAO;

   public void setListDAO(LTAMListDataDAO listDAO)
   {
      this.listDAO = listDAO;
   }

   @ManagedProperty("#{ltamSaveDataDAO}")
   private LTAMSaveDataDAO saveDAO;

   public void setSaveDAO(LTAMSaveDataDAO saveDAO)
   {
      this.saveDAO = saveDAO;
   }

   @ManagedProperty("#{consumerListDataDAO}")
   private ConsumerListDataDAO consumerListDataDAO;
   public void setConsumerListDataDAO(ConsumerListDataDAO consumerListDataDAO)
   {
      this.consumerListDataDAO = consumerListDataDAO;
   }

   @ManagedProperty("#{webutil}")
   WebUtil webutil;
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

   /*
   public void setBeanAdvisor(Long beanAdvisor)
   {
      SQLData converter = new SQLData();
      this.beanAdvisor = converter.getLongData(beanAdvisor);
   }
   */

   public LTAMCustomerData getOrigCustomerData()
   {
      return origCustomerData;
   }

   public Boolean getIsEditMode() {
      if (formmode != null && formmode.equalsIgnoreCase("Edit"))
          return true;
      else
          return false;
   }

   public Boolean getIsVisitorMode() {
      if (formmode == null )
      return true;
      if (formmode.equalsIgnoreCase("Edit") ||  formmode.equalsIgnoreCase("TimeToSave"))
         return false;
      else
         return true;
   }

   public Boolean getIsTimeToSaveMode() {
      if (formmode != null && formmode.equalsIgnoreCase("TimeToSave"))
         return true;
      else
         return false;
   }

   public String getFormmode()
   {
      return formmode;
   }

   public String getBeanaction()
   {
      return beanaction;
   }

   public void setBeanaction(String beanaction)
   {
      this.beanaction = beanaction;
   }

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
   }

   public String getBeanTimeToSaveID()
   {
      return beanTimeToSaveID;
   }

   public void setBeanTimeToSaveID(String beanTimeToSaveID)
   {
      this.beanTimeToSaveID = beanTimeToSaveID;
      setTimeToSaveID(beanTimeToSaveID);
      formmode = "TimeToSave";
   }

   public String getBeanAdvisor()
   {
      return beanAdvisor;
   }

   public void setBeanAdvisor(String beanAdvisor)
   {
      this.beanAdvisor = beanAdvisor;
      setAdvisor(beanAdvisor);
   }

   public String getBeanRep()
   {
      return beanRep;
   }

   public void setBeanRep(String beanRep)
   {
      this.beanRep = beanRep;
      setRep(beanRep);
   }

   public String getBeanAmount()
   {
      return beanAmount;

   }

   public void setBeanAmount(String beanAmount)
   {
      this.beanAmount = beanAmount;
      try
      {
         Double value = converter.getDoubleData(beanAmount);
         setInvestment(value);
      }
      catch (Exception ex)
      {

      }

   }

   public String getBeanfirstname()
   {
      return beanfirstname;
   }

   public void setBeanfirstname(String beanfirstname)
   {
      this.beanfirstname = beanfirstname;
      setFirstname(beanfirstname);
   }

   public String getBeanlastname()
   {
      return beanlastname;
   }

   public void setBeanlastname(String beanlastname)
   {
      this.beanlastname = beanlastname;
      setLastname(beanlastname);
   }

   public Boolean getDisableInvestment()
   {
      return disableInvestment;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public Boolean getDisplayGraphs()
   {
      return displayGraphs;
   }

   public Boolean getReviewPage()
   {
      return reviewPage;
   }

   public Boolean getDisplayMeter()
   {
      return displayMeter;
   }

   public void setDisplayGraphs(Boolean value)
   {
      displayGraphs = value;
   }

   public LTAMCharts getLtamcharts()
   {
      return ltamcharts;
   }

   public ArrayList<LTAMTheme> getThemeList()
   {
      return themeList;
   }

   public Map<String, String> getDisplaythememap()
   {
      return displaythememap;
   }

   public Integer getLtammenu()
   {
      if (pagemanager == null)
         return 0;
      else
         return pagemanager.getPage();

   }

   public void exitPage()
   {
      uiLayout.goToStartPage();
   }

   public void firstPage()
   {
      reviewPage = false;
      displayMeter = false;
      setDisplayGraphs(false);
      pagemanager.setPage(0);
      // webutil.redirect("/start.xhtml", null);

   }

   public void prevPage()
   {
      reviewPage = false;
      pagemanager.prevPage();
      if (pagemanager.isFirstPage())
      {
         setDisplayGraphs(false);
         displayMeter = false;
      }
   }

   public void transact()
   {

   }

   public void nextPage()
   {
      if (pagemanager.isFirstPage()) {
         if (getIsEditMode()) {
           if (getFormula() != null && getFormula().equalsIgnoreCase("D")) {
              pagemanager.setPage(6);
              doCharts();
              webutil.redirect("/pages/consumer/confirm.xhtml", null);
              return;
           }
         }
         else {
            if (getInvestment() < 500.00) {
               FacesMessage message;
               message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Min Investment of $500.00", "Min Investment of $500.00");
               FacesContext.getCurrentInstance().addMessage(null, message);
               return;
            }
         }
      }
      setDisplayGraphs(true);
      displayMeter = true;
      if (pagemanager.isNextToLastPage()) {
         reviewPage = true;
      }
      doCharts();
      saveClientData();
      pagemanager.nextPage();
      if (getIsEditMode() && pagemanager.getPage() >= 6) {
            System.out.print("Exchange Fund: Acct="
                                + getAcctnum().toString()
                                + ", advisor=" + getAdvisor()
                                + ", theme=" + getTheme());
             webutil.redirect("/pages/consumer/confirm.xhtml", null);
             return;
            // pagemanager.setPage(6);
         }
         if (pagemanager.isLastPage()) {
            System.out.print("Forward to Gemini: Acct="
                                + getAcctnum().toString()
                                + ", advisor=" + getAdvisor()
                                + ", theme=" + getTheme());
            webutil.redirect("/pages/consumer/review.xhtml", null);
            return;
         }
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (pagemanager == null) {
               resetBean();
            }

            if (beanaction != null) {
               if (beanaction.equalsIgnoreCase("N")) {
                  beanacctnum = null;  // Reset the Account Number (Due to Session cache)
               }
            }

            // If beanacctnum is null or empty, then it must be a visitor
            if (beanacctnum != null && ! beanacctnum.isEmpty()) {
               if (webutil.validatePriviledge(Const.WEB_USER)) {
                  formmode="Edit";
                  pagemanager = new PagesImpl(9);
                  displayMeter = true;
                  Long logonid = webutil.getLogonid();
                  collectAccountData(logonid, beanacctnum);
               }
            }
            else {
               pagemanager = new PagesImpl(6);
               if (beanaction != null) {
                  formmode = "New";
                  if (webutil.validatePriviledge(Const.WEB_USER)) {
                     Long logonid = webutil.getLogonid();
                     setLogonid(logonid);
                  }
               }
               else {
                  if (beanTimeToSaveID != null && (! beanTimeToSaveID.isEmpty()))
                     formmode = "TimeToSave";
                  else
                     formmode="Visitor";
                  setLogonid(null);
                  resetBean();
                  selectedPage4Image = 0;
                  saveVisitor();
               }
               doCharts();
            }

            if (pagemanager != null && (! pagemanager.isFirstPage())) {
               firstPage();
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void collectAccountData(Long logonid, String beanacctnum)
   {
      try {
         if (beanacctnum != null && ! beanacctnum.isEmpty()) {
            Long acctnum = converter.getLongData(beanacctnum);
            if (acctnum != null && acctnum > 0) {
               resetBean();
               accountData = consumerListDataDAO.getAccountData(logonid, acctnum);
               if (accountData == null) {
                  webutil.redirect("/access-denied.xhtml", null);
               }
               else {
                  // Keep the original copy
                  origCustomerData = new LTAMCustomerData();
                  origCustomerData.copyData(accountData);
                  if (origCustomerData != null) {
                     origCustomerData.setThemeData(ltamoptimizer.getTheme(origCustomerData.getTheme()));
                  }
                  copyData(accountData);
                  doCharts();
               }
            }
         }
      }
      catch (Exception ex) {
         webutil.redirecttoMessagePage("Warning", "",
                                       "There is no data associated with this account number. <br/>" +
                                          "Please contact support."
         );
      }
   }

   public void resetBean()
   {
      resetAllData();
      displayGraphs = false;
      reviewPage = false;
      displayMeter = false;
      selectedPage4Image = 0;

      setAdvisor(beanAdvisor);

      setRep(beanRep);

      if (beanTimeToSaveID != null) {
         setTimeToSaveID(beanTimeToSaveID);
         setFirstname(beanfirstname);
         setLastname(beanlastname);
         Double value = converter.getDoubleData(beanAmount);
         setInvestment(value);
         disableInvestment = true;
      }
      else
      {
         disableInvestment = false;
      }
      if (ltamoptimizer != null) {
         themeList = ltamoptimizer.getThemes();
         displaythememap = ltamoptimizer.getDisplayThemes();
      }

   }

   private void saveVisitor()
   {

      try
      {
         if (! webutil.isWebProdMode())  {
            setLogonid(0L);
            return;
         }

         setIpaddress(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));
         if (getIsEditMode() || getFormmode().equalsIgnoreCase("new")) {
            setLogonid(webutil.getLogonid());
            return;
         }

         Long logonid = null;
         if (getLogonid() == null || getLogonid() == 0L)
         {
            if (saveDAO != null) {
               logonid = saveDAO.saveLTAMVisitor(getInstance());
            }
         }

         if (logonid == null)
         {
            setLogonid(0L);
         }
         else
         {
            setLogonid(logonid);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveClientData()
   {

      try
      {
         if (getIsEditMode()) {
            return;
         }
         Long acctnum = null;
         // if (webutil.isWebProdMode())
         acctnum = saveDAO.saveLTAMUserData(getInstance());
         if (acctnum == null)
         {
            setLogonid(0L);
         }
         else
         {
            setAcctnum(acctnum);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void startOver(ActionEvent actionEvent)
   {
      startOver();
   }

   public void startOver()
   {
      reviewPage = false;
      displayMeter = false;
      setDisplayGraphs(false);
      pagemanager.setPage(0);
      webutil.redirect("/pages/consumer/cedit.xhtml", null);
   }

   public void forwardData()
   {

      try
      {
         setForwarded("now");
         saveClientData();
         Map<String,String> args = new LinkedHashMap<String, String>();
         args.put("TimeToSaveUserId", getTimeToSaveID());
         args.put("InvestorName", getFirstname() + " " + getLastname());
         args.put("FundSelection", getTheme());
         args.put("DollarAmount", getInvestment().toString());
         args.put("UniqueUserIdentifier", getAcctnum().toString());
         args.put("RepReferralId", getAdvisor());
         //webutil.redirect(webutil.getUiLayout().getUiprofile().getForwardURL(), args);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void chartOriginalData()
   {
      try
      {
         if (ltamcharts == null)
         {
            ltamcharts = new LTAMCharts();
         }
         origCustomerData.setPieChart(ltamcharts.createPieModel(origCustomerData.getThemeData().getAsset()));
      }
      catch (Exception ex)
      {
      }
   }

   public void selectedTheme()
   {
      try
      {
            setThemeData(theme);
            if (ltamcharts == null)
            {
               ltamcharts = new LTAMCharts();
            }

            setPieChart(ltamcharts.createPieModel(getThemeData().getAsset()));
            if (displayGraphs) {
               if (ltamcharts.getRiskbarChart() == null)
                  ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
            }
            if (reviewPage)
            {
               // ltamcharts.setMeterGuage(getRiskIndex());
               // ltamcharts.createDonutChart(getThemeData().getAsset());
               ltamcharts.createBarPerformance(theme.getPerformanceData());
               // ArrayList<ArrayList<LTAMPerformancePrintData>> myMap = theme.getPrintedPerformanceData();
            }
      }
      catch (Exception ex)
      {
      }
   }

   public void doCharts()
   {
      try
      {
         setRiskIndex(calcRiskIndex());
         theme = ltamoptimizer.getTheme(getRiskIndex());
         if (theme != null)
         {
            setTheme(theme.getTheme());
            setThemeData(theme);
            if (ltamcharts == null)
            {
               ltamcharts = new LTAMCharts();
            }

            setPieChart(ltamcharts.createPieModel(getThemeData().getAsset()));
            if (displayGraphs) {
               if (ltamcharts.getRiskbarChart() == null)
                  ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
            }
            if (reviewPage)
            {
               // ltamcharts.setMeterGuage(getRiskIndex());
               // ltamcharts.createDonutChart(getThemeData().getAsset());
               ltamcharts.createBarPerformance(theme.getPerformanceData());
               // ArrayList<ArrayList<LTAMPerformancePrintData>> myMap = theme.getPrintedPerformanceData();
            }
         }
      }
      catch (Exception ex)
      {
      }
   }

   public void riskChartSelected(ItemSelectEvent event) {
      if (event != null) {
         Integer answer;
         switch (event.getItemIndex()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
               answer = event.getItemIndex() + 1;
               setAns5(answer);
            default:

         }
      }
   }

   public String getSelectedPie()
   {
      return selectedPie;
   }

   public void showpieSliceInfo(ItemSelectEvent event) {
      if (event != null) {
         System.out.println("Pie Slice Selected");
         selectedPie = "Selected";
      }
   }

   public void hidepieSliceInfo(ItemSelectEvent event) {
      if (event != null) {
         System.out.println("Pie Slice De-seletected");
         selectedPie="";
      }
   }
   public void setImage(Integer ans) {
      setAns4(ans);
      selectedPage4Image = 0;
   }

   public void setHoverImage(Integer ans) {
      selectedPage4Image = ans;
   }

   public Boolean isImageSelected(Integer which) {
      if (getAns4() == null)
         return false;

      if (selectedPage4Image != null && selectedPage4Image != 0) {
         if (selectedPage4Image.equals(which))
            return true;
      }

      if (getAns4().equals(which))
         return true;
      else
         return false;
   }

   public void onSlideEnd(SlideEndEvent event) {
      if (event != null) {
         setAns6(event.getValue());
      }
   }

   public void setRiskQ5Image(Integer which) {
      if (getAns5() == null)
         return;

      setAns5(which);
   }

   public void processTransfer() {
      FacesMessage message;
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Transaction", "Transaction Processed.");
      FacesContext.getCurrentInstance().addMessage(null, message);
   }



}

