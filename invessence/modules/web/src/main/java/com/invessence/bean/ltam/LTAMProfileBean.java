package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.*;
import javax.servlet.http.*;

import com.invessence.constant.*;
import com.invessence.converter.SQLData;
import com.invessence.dao.consumer.*;
import com.invessence.dao.ltam.*;
import com.invessence.data.common.*;
import com.invessence.data.ltam.LTAMCustomerData;
import com.invessence.util.*;
import com.invessence.util.Impl.PagesImpl;
import com.invmodel.Const.InvConst;
import com.invmodel.ltam.LTAMOptimizer;
import com.invmodel.ltam.data.*;


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
   private String beanAdvisor;
   private String beanRep;
   private String beanAmount;
   private String beanfirstname;
   private String beanlastname;
   private Boolean visitorMode;
   private Boolean disableInvestment;
   private Boolean displayGraphs, reviewPage, displayMeter;
   SQLData converter = new SQLData();
   private PagesImpl pagemanager;
   private Integer ltammenu;
   private LTAMCharts ltamcharts;
   private LTAMTheme theme;
   private ArrayList<LTAMTheme> themeList;

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

   @ManagedProperty("#{emailMessage}")
   private EmailMessage messageText;

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
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

   public void firstPage()
   {
      reviewPage = false;
      displayMeter = false;
      setDisplayGraphs(false);
      pagemanager.setPage(0);
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

   }

   public void preRenderView()
   {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            // pagemanager.setPage(0);
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
      if (beanAmount != null)
      {
         disableInvestment = true;
         setInvestment(converter.getDoubleData(beanAmount));
      }
      else
      {
         disableInvestment = false;
      }

      if (beanAdvisor != null)
      {
         setAdvisor(beanAdvisor);
      }

      if (beanRep != null)
      {
         setRep(beanRep);
      }

      if (beanAmount != null) {
         setFirstname(beanfirstname);
         setLastname(beanlastname);
         Double value = converter.getDoubleData(beanAmount);
         setInvestment(value);
      }
   }

   @PostConstruct
   public void init()
   {
      try
      {
         resetBean();
         // if (webutil.isWebProdMode())
         doCharts();
      }
      catch (Exception e)
      {
         e.printStackTrace();
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
            logonid = saveDAO.saveLTAMVisitor(getInstance());
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
               ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
               ltamcharts.createLineModel(theme.getPerformanceData());
               ltamcharts.createBarPerformance(theme.getPerformanceData());
               ArrayList<ArrayList<LTAMPerformancePrintData>> myMap = theme.getPrintedPerformanceData();
            }
         }
      }
      catch (Exception ex)
      {
      }
   }

}

