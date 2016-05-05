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
import com.invessence.converter.*;
import com.invessence.dao.consumer.*;
import com.invessence.dao.ltam.*;
import com.invessence.data.LTAMTheme;
import com.invessence.data.common.AccountData;
import com.invessence.data.consumer.TradeData;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import com.invessence.ws.bean.*;
import com.invessence.ws.service.ServiceLayer;
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
   private String selectedThemeName;
   private Map<String, String> displaythememap;
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

   @ManagedProperty("#{consumerSaveDAO}")
   private ConsumerSaveDAO saveDAO;

   public void setSaveDAO(ConsumerSaveDAO saveDAO)
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

   @ManagedProperty("#{serviceLayer}")
   private ServiceLayer serviceLayer;

   public void setServiceLayer(ServiceLayer serviceLayer)
   {
      this.serviceLayer = serviceLayer;
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

   public Boolean getIsEditMode()
   {
      if (formmode != null && formmode.equalsIgnoreCase("Edit"))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public Boolean getIsVisitorMode()
   {
      if (formmode == null)
      {
         return true;
      }
      if (formmode.equalsIgnoreCase("Edit") || formmode.equalsIgnoreCase("TimeToSave"))
      {
         return false;
      }
      else
      {
         return true;
      }
   }

   public Boolean getIsTimeToSaveMode()
   {
      if (formmode != null && formmode.equalsIgnoreCase("TimeToSave"))
      {
         return true;
      }
      else
      {
         return false;
      }
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

   public String getSelectedThemeName()
   {
      return selectedThemeName;
   }

   public void setSelectedThemeName(String selectedThemeName)
   {
      this.selectedThemeName = selectedThemeName;
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
      {
         return 0;
      }
      else
      {
         return pagemanager.getPage();
      }

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
      if (pagemanager.isFirstPage())
      {
         if (getIsEditMode())
         {
            if (getFormula() != null && getFormula().equalsIgnoreCase("D"))
            {
               pagemanager.setPage(6);
               doCharts();
               webutil.redirect("/pages/consumer/confirm.xhtml", null);
               return;
            }
         }
         else
         {
            if (getInvestment() < 500.00)
            {
               FacesMessage message;
               message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Min Investment of $500.00", "Min Investment of $500.00");
               FacesContext.getCurrentInstance().addMessage(null, message);
               return;
            }
         }
      }
      setDisplayGraphs(true);
      displayMeter = true;
      if (pagemanager.isNextToLastPage())
      {
         reviewPage = true;
      }
      doCharts();
      saveClientData();
      pagemanager.nextPage();
      if (getIsEditMode() && pagemanager.getPage() >= 6)
      {
         System.out.println("Exchange Fund: Acct=" + getAcctnum().toString()
                               + ", Ext Acct#=" + getGeminiAcctNum()
                               + ", advisor=" + getAdvisor()
                               + ", theme=" + getTheme());
         webutil.redirect("/pages/consumer/confirm.xhtml", null);
         return;
         // pagemanager.setPage(6);
      }
      if (pagemanager.isLastPage())
      {
         System.out.println("Forward to Gemini: Acct="
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
            if (beanaction != null)
            {
               if (beanaction.equalsIgnoreCase("N"))
               {
                  beanacctnum = null;  // Reset the Account Number (Due to Session cache)
               }
            }

            /* if in either New or edit mode, it is from session, therefore check if session is still valid. */
            if (beanaction != null)
            {
               if (webutil.validatePriviledge(Const.WEB_USER))
               {
                  setLogonid(webutil.getLogonid());
               }
            }

            // If beanacctnum is null or empty, then it must be a visitor
            if (beanacctnum != null && !beanacctnum.isEmpty())
            {
               if (webutil.validatePriviledge(Const.WEB_USER))
               {
                  resetBean();
                  formmode = "Edit";
                  pagemanager = new PagesImpl(9);
                  Long logonid = webutil.getLogonid();
                  collectAccountData(logonid, beanacctnum);
               }
            }
            else
            {
               if (beanaction != null) {
                  if (beanaction != null && beanaction.equalsIgnoreCase("N"))
                  {
                     pagemanager = new PagesImpl(6);
                     resetBean();
                     formmode = "New";
                     beanaction = null; // This is only needed one time.
                  }
                  if (beanaction != null && beanaction.equalsIgnoreCase("E"))
                  {
                     resetBean();
                     formmode = "Edit";
                     displayMeter = true;
                     setLogonid(webutil.getLogonid());
                  }
                  if (beanaction != null && beanaction.equalsIgnoreCase("S"))
                  {
                     beanaction = null; // Reset the Re-Start Mode
                  }
               }
               else if (beanaction == null)
               {  // This is from visitor site or just in try mode.
                  if (beanTimeToSaveID != null && (!beanTimeToSaveID.isEmpty()))
                  {
                     formmode = "TimeToSave";
                  }
                  else
                  {
                     formmode = "Visitor";
                  }
                  resetBean();
                  setLogonid(null);
                  saveVisitor();  // Only create visitor if in try mode
               }
               doCharts();
            }
            selectedPage4Image = 0;


            if (pagemanager != null && (!pagemanager.isFirstPage()))
            {
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
      try
      {
         if (beanacctnum != null && !beanacctnum.isEmpty())
         {
            Long acctnum = converter.getLongData(beanacctnum);
            if (acctnum != null && acctnum > 0)
            {
               resetBean();
               accountData = consumerListDataDAO.getAccountData(logonid, acctnum);
               if (accountData == null)
               {
                  webutil.redirect("/access-denied.xhtml", null);
               }
               else
               {
                  // Keep the original copy
                  origCustomerData = new LTAMCustomerData();
                  origCustomerData.copyData(accountData);
                  if (origCustomerData != null)
                  {
                     origCustomerData.setThemeData(ltamoptimizer.getTheme(origCustomerData.getTheme()));
                  }
                  copyData(accountData);
                  doCharts();
               }
            }
         }
      }
      catch (Exception ex)
      {
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

      if (beanTimeToSaveID != null)
      {
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
      if (ltamoptimizer != null)
      {
         themeList = ltamoptimizer.getThemes();
         displaythememap = ltamoptimizer.getDisplayThemes();
      }

   }

   private void saveVisitor()
   {

      try
      {
         setIpaddress(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));
         if (getIsEditMode() || getFormmode().equalsIgnoreCase("new"))
         {
            setLogonid(webutil.getLogonid());
            return;
         }

         Long logonid = null;
         if (getLogonid() == null || getLogonid() == 0L)
         {
            if (saveDAO != null)
            {
               logonid = saveDAO.saveVisitor(getInstance());
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
         if (getIsEditMode())
         {
            return;
         }
         Long acctnum = null;
         // if (webutil.isWebProdMode())
         acctnum = saveDAO.saveUserData(getInstance());
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
      webutil.redirect("/pages/consumer/cedit.xhtml?act=S", null);
   }

   public void forwardData()
   {

      try
      {
         setForwarded("now");
         saveClientData();
         Map<String, String> args = new LinkedHashMap<String, String>();
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

         if (selectedThemeName.equalsIgnoreCase(origCustomerData.getTheme()))
         {
            selectedThemeName = null;
            FacesMessage message;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Current and revised fund cannot be same.", "Error");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         theme = ltamoptimizer.getTheme(selectedThemeName);
         setTheme(theme.getTheme());
         setThemeData(theme);
         if (ltamcharts == null)
         {
            ltamcharts = new LTAMCharts();
         }

         setPieChart(ltamcharts.createPieModel(getThemeData().getAsset()));
         if (displayGraphs)
         {
            if (ltamcharts.getRiskbarChart() == null)
            {
               ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
            }
         }
/*
            if (reviewPage)
            {
               // ltamcharts.setMeterGuage(getRiskIndex());
               // ltamcharts.createDonutChart(getThemeData().getAsset());
               ltamcharts.createBarPerformance(theme.getPerformanceData());
               // ArrayList<ArrayList<LTAMPerformancePrintData>> myMap = theme.getPrintedPerformanceData();
            }
*/
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
         if (getIsEditMode())
         {
            if (origCustomerData != null)
            {
               if (origCustomerData.getTheme().equalsIgnoreCase(theme.getTheme()))
               {
                  setSelectedThemeName(null);
               }
               else
               {
                  setSelectedThemeName(theme.getTheme());
               }
            }
            else
            {
               setSelectedThemeName(theme.getTheme());
            }
         }
         if (theme != null)
         {
            setTheme(theme.getTheme());
            setThemeData(theme);
            if (ltamcharts == null)
            {
               ltamcharts = new LTAMCharts();
            }

            setPieChart(ltamcharts.createPieModel(getThemeData().getAsset()));
            if (displayGraphs)
            {
               if (ltamcharts.getRiskbarChart() == null)
               {
                  ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
               }
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
               setAns5(answer);
            default:

         }
      }
   }

   public String getSelectedPie()
   {
      return selectedPie;
   }

   public void showpieSliceInfo(ItemSelectEvent event)
   {
      if (event != null)
      {
         System.out.println("Pie Slice Selected");
         selectedPie = "Selected";
      }
   }

   public void hidepieSliceInfo(ItemSelectEvent event)
   {
      if (event != null)
      {
         System.out.println("Pie Slice De-seletected");
         selectedPie = "";
      }
   }

   public void setImage(Integer ans)
   {
      setAns4(ans);
      selectedPage4Image = 0;
   }

   public void setHoverImage(Integer ans)
   {
      selectedPage4Image = ans;
   }

   public Boolean isImageSelected(Integer which)
   {
      if (getAns4() == null)
      {
         return false;
      }

      if (selectedPage4Image != null && selectedPage4Image != 0)
      {
         if (selectedPage4Image.equals(which))
         {
            return true;
         }
      }

      if (getAns4().equals(which))
      {
         return true;
      }
      else
      {
         return false;
      }
   }

   public void onSlideEnd(SlideEndEvent event)
   {
      if (event != null)
      {
         setAns6(event.getValue());
      }
   }

   public void setRiskQ5Image(Integer which)
   {
      if (getAns5() == null)
      {
         return;
      }

      setAns5(which);
   }

   public void processTransfer()
   {
      FacesMessage message;
      try
      {
         if (serviceLayer == null)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web-service is down!", "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         if (!serviceLayer.isServiceActive())
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web-service is NOT active!", "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         WSCallResult serviceStatus;
         serviceStatus = serviceLayer.getUserBankAcctDetails(getGeminiAcctNum());

         if (serviceStatus == null)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Web-service is NOT active!", "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         if (serviceStatus.getWSCallStatus().getErrorCode() != 0)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, serviceStatus.getWSCallStatus().getErrorMessage(), "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         /* Note: Check service status non zero, print error message to the user */

         List<BankAcctDetails> bankaccountlist = null;
         bankaccountlist = (List<BankAcctDetails>) serviceStatus.getGenericObject();

         if (bankaccountlist == null)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to get account info from web-services, to complete the call.", "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;

         }

         String bankacct = bankaccountlist.get(0).getBankAccountNumber();
         String bankname = bankaccountlist.get(0).getBankName();

         WSCallResult wsCallResult;
         WSCallStatus wsCallStatus;
         String wstrasactionnumber = null;
         System.out.println("WebService Call:= (" + getGeminiAcctNum() + "," + origCustomerData.getFundID() +
                               "," + getFundID() + "," + bankacct + ")");

         wsCallResult = serviceLayer.fullFundTransfer(getGeminiAcctNum(), origCustomerData.getFundID(), getFundID(), bankacct);
         if (wsCallResult.getWSCallStatus().getErrorCode() != 0)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, wsCallResult.getWSCallStatus().getErrorMessage(), "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         if (wsCallResult.getGenericObject() != null)
         {
            wstrasactionnumber = ((TransactionDetails) wsCallResult.getGenericObject()).getTransactionId();
         }
/*
         wsCallStatus = serviceLayer.fullFundTransfer(getGeminiAcctNum(), origCustomerData.getFundID(), getFundID(), bankacct);
         if (wsCallStatus.getErrorCode() != 0) {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, wsCallStatus.getErrorMessage(), "Web-service");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }
*/

         Long acctnum = saveDAO.saveUserData(getInstance());

         if (acctnum == null)
         {
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Unable to save to Database!", "Database");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
         }

         JavaUtil jutil = new JavaUtil();
         String hiddenbankacct = jutil.getDisplayHiddenID(bankacct);
         TradeData tradedata = new TradeData(null, getAcctnum(), getGeminiAcctNum(),
                                             "Exchange", wstrasactionnumber, getCusip(),
                                             getInvestment(), bankname, hiddenbankacct,
                                             origCustomerData.getCusip(), null, null,
                                             getSecurityname());

         Long transactionnum = saveDAO.saveTradeInfo(tradedata);
         tradedata.setTransactionnum(transactionnum);

/*
         Map<String, String> obj = new HashMap<String, String>();
         obj.put("type", "Info");
         obj.put("title", "Thank you");
         obj.put("message", "Trasanction was processed." +
            "<br/> TransactionID " + wstrasactionnumber +
            "<br/> Your account " + getGeminiAcctNum() +
            "<br> New Fund " + getSecurityname());

         String url = "/message.xhtml";
         webutil.redirect(url, obj);
*/
         //TradeInfoBean tib = new TradeInfoBean();
         //tib.initTradeData(tradedata);
         Map<String, String> obj = new HashMap<String, String>();
         obj.put("acct", getGeminiAcctNum());
         obj.put("tran", wstrasactionnumber);
         obj.put("type", "Exchange");
         obj.put("fund", getSecurityname());
         obj.put("beanamt", jutil.displayFormat(getInvestment(), "$#,###,###.00"));
         webutil.redirect("/pages/consumer/tradeinfo.xhtml", obj);


      }
      catch (Exception ex)
      {
         message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Transaction exception was raised! Call support.", "Error");
         FacesContext.getCurrentInstance().addMessage(null, message);
         ex.printStackTrace();
      }
   }

   public void onTermChange()
   {
      //  Dummy function for now.  Later we can introduce popup of Not agreeing to the terms.
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


}

