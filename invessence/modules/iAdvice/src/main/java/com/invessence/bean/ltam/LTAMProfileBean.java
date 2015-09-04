package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.servlet.http.*;

import com.invessence.converter.SQLData;
import com.invessence.dao.ltam.*;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import com.ltammodel.LTAMOptimizer;
import com.ltammodel.data.*;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ItemSelectEvent;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/4/15
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "ltamprofile")
@SessionScoped
public class LTAMProfileBean extends LTAMCustomerData implements Serializable
{
   private String beanTimeToSaveID;
   private String beanAdvisor;
   private String beanRep;
   private String beanAmount;
   private String beanfirstname;
   private String beanlastname;
   private Boolean welcomeDialog; // Flag to Display Welcome message if from Advisor Link
   private Boolean visitorMode; // Defines Is it from TimeToSave or Advisor
   private Boolean disableInvestment;  // Not USED:  Display Investment as editable mode or not?
   private Boolean displayGraphs, reviewPage, displayMeter;
   SQLData converter = new SQLData();
   private PagesImpl pagemanager;
   private Integer ltammenu;
   private LTAMCharts ltamcharts;
   private LTAMTheme theme;
   private ArrayList<LTAMTheme> themeList;
   private String selectedPie;
   private Integer selectedPage4Image;

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


   WebUtil webutil = new WebUtil();

/*
   public void setBeanAdvisor(Long beanAdvisor)
   {
      SQLData converter = new SQLData();
      this.beanAdvisor = converter.getLongData(beanAdvisor);
   }
*/

   public Boolean getVisitorMode()
   {
      return visitorMode;
   }

   public String getBeanTimeToSaveID()
   {
      return beanTimeToSaveID;
   }

   public void setBeanTimeToSaveID(String beanTimeToSaveID)
   {
      this.beanTimeToSaveID = beanTimeToSaveID;
      setTimeToSaveID(beanTimeToSaveID);
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
         visitorMode = false;
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

   public Boolean getWelcomeDialog()
   {
      return welcomeDialog;
   }

   public Boolean getDisplayRiskMeter()
   {
      if (getAge() == null)
      {
         return false;
      }

      if (getAge() > 0)
      {
         return true;
      }
      else
      {
         return false;
      }
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

   public Integer getLtammenu()
   {
      if (pagemanager == null)
         return 0;
      else
         return pagemanager.getPage();

   }


   public void startPage()
   {
      reviewPage = false;
      displayMeter = false;
      setDisplayGraphs(false);
      pagemanager.setPage(0);
      welcomeDialog = false;
      RequestContext.getCurrentInstance().closeDialog("dlgWelcome");
      // webutil.redirect("/index.xhtml", null);
   }


   public void firstPage()
   {
      reviewPage = false;
      displayMeter = false;
      setDisplayGraphs(false);
      pagemanager.setPage(0);
      webutil.redirect("/start.xhtml", null);

   }

   public void prevPage()
   {
      reviewPage = false;
      pagemanager.prevPage();
      if (pagemanager.getPage() == 0)
      {
         setDisplayGraphs(false);
         displayMeter = false;
      }
   }

   public void nextPage()
   {
      //if (pagemanager.isNextToLastPage())
      {
         setDisplayGraphs(true);
      }
      if (pagemanager.isNextToLastPage()) {
         reviewPage = true;
      }
      displayMeter = true;
      doCharts();
      saveClientData();
      pagemanager.nextPage();
      if (pagemanager.isLastPage()) {
         webutil.redirect("/review.xhtml", null);
      }
   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // pagemanager.setPage(0);
            if (getBeanAmount() != null) {
               welcomeDialog = false;
            }
            else {
               welcomeDialog = true;
            }
            // resetBean();
            // if (webutil.isWebProdMode())
            selectedPage4Image = 0;
            doCharts();
            saveVisitor();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void resetBean()
   {
      visitorMode = true;
      resetAllData();
      displayGraphs = false;
      reviewPage = false;
      displayMeter = false;
      pagemanager = new PagesImpl(6);
      pagemanager.setPage(0);
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
         welcomeDialog = false;
      }
      else
      {
         welcomeDialog = true;
         disableInvestment = false;
      }

   }

   private void saveVisitor()
   {

      try
      {
         setIpaddress(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));
         Long logonid = null;
         // if (webutil.isWebProdMode())
         if (getLogonid() == 0L)
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
         webutil.redirect("http://test.geminifund.com/newaccountswizardnew/pages/testFormPost.aspx", args);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void doCharts()
   {
      try
      {
         setRiskIndex(calcRiskIndex());
         theme = ltamoptimizer.getTheme(getRiskIndex());
         themeList = ltamoptimizer.getThemes();
         if (theme != null)
         {
            setTheme(theme.getTheme());
            setThemeData(theme);
            if (ltamcharts == null)
            {
               ltamcharts = new LTAMCharts();
            }

            ltamcharts.createPieModel(getThemeData().getAsset());
            if (displayGraphs)
            {
               // ltamcharts.setMeterGuage(getRiskIndex());
               ltamcharts.createDonutChart(getThemeData().getAsset());
               //ltamcharts.createRiskBarChart();
               ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
               ltamcharts.createBarPerformance(theme.getPerformanceData());
               ArrayList<ArrayList<LTAMPerformancePrintData>> myMap = theme.getPrintedPerformanceData();
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


}

