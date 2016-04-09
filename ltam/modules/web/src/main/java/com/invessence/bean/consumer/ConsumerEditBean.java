package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
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
import com.invessence.util.Impl.PagesImpl;
import com.invessence.util.WebUtil;
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
   private String beanacctnum;
   private Boolean demomode;
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
   private AccountData accountData;

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

/*
   public void setBeanAdvisor(Long beanAdvisor)
   {
      SQLData converter = new SQLData();
      this.beanAdvisor = converter.getLongData(beanAdvisor);
   }
*/

   public String getBeanacctnum()
   {
      return beanacctnum;
   }

   public void setBeanacctnum(String beanacctnum)
   {
      this.beanacctnum = beanacctnum;
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
      RequestContext.getCurrentInstance().closeDialog("dlgWelcome");
      // webutil.redirect("/index.xhtml", null);
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

   public void nextPage()
   {
      if (pagemanager.isFirstPage()) {
         if (getInvestment() < 500.00) {
            FacesMessage message;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Min Investment of $500.00", "Min Investment of $500.00");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
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
      if (pagemanager.isLastPage()) {
         System.out.print("Forward to Gemini: Acct="
                             + getAcctnum().toString()
                             + ", advisor=" + getAdvisor()
                             + ", theme=" + getTheme());
         webutil.redirect("/pages/try/review.xhtml", null);
      }
   }

   public void preRenderView()
   {

      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            if (webutil.validatePriviledge(Const.WEB_USER)) {
               Long logonid = webutil.getLogonid();
               if (pagemanager == null) {
                  resetBean();
               }

               collectAccountData(logonid, beanacctnum);
               firstPage();
            }
         }
      }
      catch (Exception e)
      {
         webutil.redirecttoMessagePage("Error", "",
                                       "This session has expired.  Or you do not have access to this account."
         );
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
      pagemanager = new PagesImpl(6);
      pagemanager.setPage(0);
      selectedPage4Image = 0;

   }

   public void saveClientData()
   {

      try
      {
         if (demomode)
            return;

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
}

