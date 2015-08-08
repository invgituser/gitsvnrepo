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
import com.invmodel.ltam.data.LTAMTheme;


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
   private String  beanAdvisor;
   private String beanRep;
   private String beanAmount;
   private Boolean disableInvestment;
   SQLData converter = new SQLData();
   private PagesImpl pagemanager;
   private Integer ltammenu;
   private LTAMCharts ltamcharts;
   private LTAMTheme theme;

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

   public String getBeanAdvisor()
   {
      return beanAdvisor;
   }

   public void setBeanAdvisor(String beanAdvisor)
   {
      this.beanAdvisor = beanAdvisor;
   }

   public String getBeanRep()
   {
      return beanRep;
   }

   public void setBeanRep(String beanRep)
   {
      this.beanRep = beanRep;
   }

   public String getBeanAmount()
   {
      return beanAmount;
   }

   public void setBeanAmount(String beanAmount)
   {
      this.beanAmount = beanAmount;
   }

   public Boolean getDisableInvestment()
   {
      return disableInvestment;
   }

   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }

   public Boolean getDisplaGraph()
   {
      if (pagemanager != null && pagemanager.getPage() > 0)
         return true;
      else
         return false;
   }

   public LTAMCharts getLtamcharts()
   {
      return ltamcharts;
   }

   public Integer getLtammenu()
   {
      switch (pagemanager.getPage()) {
         case 0:
         case 1:
         case 2:
         case 3:
            ltammenu = pagemanager.getPage();
            break;
         case 4:
         case 5:
         case 6:
         case 7:
         case 8:
         case 9:
         case 10:
            ltammenu = 4;
            break;
         default:
            ltammenu = 0;
            break;
      }
      return ltammenu;
   }

   public void prevPage() {
      pagemanager.prevPage();
   }

   public void nextPage() {
      doCharts();
      saveClientData();
      pagemanager.nextPage();
   }

   public void preRenderView()
   {
      try {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
           // resetBean();
           doCharts();
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void resetBean() {
      resetAllData();
      pagemanager = new PagesImpl(6);
      if (beanAmount != null) {
         disableInvestment = true;
         setInvestment(converter.getDoubleData(beanAmount));
      }
      else
         disableInvestment = false;

      if (beanAdvisor != null)
         setAdvisor(beanAdvisor.toString());

      if (beanRep != null)
         setRep(beanAdvisor.toString());
   }

   @PostConstruct
   public void init()
   {
      try
      {
         resetBean();
         // if (webutil.isWebProdMode())
         saveVisitor();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   private void saveVisitor() {

      try {
         setIpaddress(webutil.getClientIpAddr((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()));
         Long logonid = null;
         // if (webutil.isWebProdMode())
         if (getLogonid() == 0L) {
            logonid = saveDAO.saveLTAMVisitor(getInstance());
         }

         if (logonid == null)
            setLogonid(0L);
         else
            setLogonid(logonid);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void saveClientData() {

      try {
         Long acctnum = null;
         // if (webutil.isWebProdMode())
         acctnum = saveDAO.saveLTAMUserData(getInstance());
         if (acctnum == null)
            setLogonid(0L);
         else
            setAcctnum(acctnum);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void forwardData() {

      try {
         setForwarded("now");
         saveClientData();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void doCharts() {
       try {
          setRiskIndex(getRiskIndex());
          theme = ltamoptimizer.getTheme(getRiskIndex());
          if (theme != null) {
            setThemeData(theme);
            setTheme(theme.getTheme());
            ltamcharts = new LTAMCharts();

             ltamcharts.createRiskBarChart(ltamoptimizer.getThemes());
             ltamcharts.createPieModel(getThemeData().getAsset());
          }
       }
       catch (Exception ex) {

       }
   }



}

