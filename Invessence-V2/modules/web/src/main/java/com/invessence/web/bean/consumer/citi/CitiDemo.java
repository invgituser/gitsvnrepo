package com.invessence.web.bean.consumer.citi;

import java.io.Serializable;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.converter.JavaUtil;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invessence.web.constant.WebConst;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.uob.UOBRiskCalculator;
import com.invessence.web.util.Impl.PagesImpl;

/**
 * Created by prashant on 6/5/2017.
 */

@ManagedBean(name = "citidemo")
@SessionScoped
public class CitiDemo extends CustomerData implements Serializable
{
   private String linkedinUserID, linkedinPassword;
   private Double watsonRiskScore;
   private Boolean isProfileAvailable;
   private String selectedSecurity;

   private Integer whichPortfolio;
   private Boolean formEdit;
   private Boolean displayGoalGraph, displayGoalText;
   private HighChartsController highChartsController;
   private InvessenceCharts charts;
   private String watsonISINURL;

   private PagesImpl pagemanager;


   public PagesImpl getPagemanager()
   {
      return pagemanager;
   }
   public void setPagemanager(PagesImpl pagemanager)
   {
      this.pagemanager = pagemanager;
   }

   public Boolean getProfileAvailable()
   {
      return isProfileAvailable;
   }

   public void setProfileAvailable(Boolean profileAvailable)
   {
      isProfileAvailable = profileAvailable;
   }

   @PostConstruct
   public void init()
   {
      highChartsController = new HighChartsController();
      charts = new InvessenceCharts();
      formEdit = false;
      displayGoalGraph = false;
      displayGoalText = false;
      whichPortfolio = 0;
   }

   public String getLinkedinUserID()
   {
      return linkedinUserID;
   }

   public void setLinkedinUserID(String linkedinUserID)
   {
      this.linkedinUserID = linkedinUserID;
   }

   public String getLinkedinPassword()
   {
      return linkedinPassword;
   }

   public void setLinkedinPassword(String linkedinPassword)
   {
      this.linkedinPassword = linkedinPassword;
   }

   public Double getWatsonRiskScore()
   {
      return watsonRiskScore;
   }

   public void setWatsonRiskScore(Double watsonRiskScore)
   {
      this.watsonRiskScore = watsonRiskScore;
   }

   public String getSelectedSecurity()
   {
      return selectedSecurity;
   }

   public void setSelectedSecurity(String selectedSecurity)
   {
      this.selectedSecurity = selectedSecurity;
   }

   public Boolean getFormEdit()
   {
      return formEdit;
   }

   public void setFormEdit(Boolean formEdit)
   {
      this.formEdit = formEdit;
   }

   public Boolean getDisplayGoalGraph()
   {
      return displayGoalGraph;
   }

   public void setDisplayGoalGraph(Boolean displayGoalGraph)
   {
      this.displayGoalGraph = displayGoalGraph;
   }

   public Boolean getDisplayGoalText()
   {
      return displayGoalText;
   }

   public void setDisplayGoalText(Boolean displayGoalText)
   {
      this.displayGoalText = displayGoalText;
   }

   public Integer getWhichPortfolio()
   {
      return whichPortfolio;
   }
   public void setWhichPortfolio(Integer whichPortfolio)
   {
      this.whichPortfolio = whichPortfolio;
   }
   public String getSelectedPortfolioStyle(Integer thisPortfolio)
   {
      if (thisPortfolio == null)
         thisPortfolio = 0;

      if (getWhichPortfolio() == thisPortfolio)
         return "CompBlock active";
      else
         return "CompBlock";
   }

   public String getActivePage(Integer thisPage)
   {
      Integer showPage = getPagemanager().getPage();
      if (showPage == null)
         showPage = 0;

      if (showPage <= thisPage)
         return "active";
      else
         return "";
   }

   public Integer getConservativeNum() {
      return (webutil.converter.getIntData(getWatsonRiskScore() - 10.0));
   }
   public Integer getRecommendedNum() {
      return (webutil.converter.getIntData(getWatsonRiskScore()));
   }
   public Integer getAggressiveNum() {
      return (webutil.converter.getIntData(getWatsonRiskScore() + 10.0));
   }

   public String getWatsonISINURL()
   {
      return watsonISINURL;
   }

   public void setWatsonISINURL(String watsonISINURL)
   {
      this.watsonISINURL = watsonISINURL;
   }

   public void logon() {
      uiLayout.doMenuAction("/pages/consumer/citi/demo/demo.xhtml");
   }

   public void preRender() {
      try
      {
         if (!FacesContext.getCurrentInstance().isPostback())
         {
            pagemanager = new PagesImpl(9);
            setRiskCalcMethod(WebConst.ADVISOR_RISK_FORMULA);
            if(isProfileAvailable==null || isProfileAvailable==false)
            {
               watsonRiskScore = 45.0;
            }
            setRiskIndex(getWatsonRiskScore());
            fetchClientData();
            createAssetPortfolio(getWatsonRiskScore());
         }

      }
      catch (Exception ex) {

      }

   }

   public void fetchClientData()
   {
      try
      {
         resetDataForm();
         loadNewClientData();
         loadBaskets(); // Once we know about advisor, then use that info
      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         webutil.alertSupport("Consumer.addGoals", "Error:Consumer.addGoals",
                              "error.addGoals", stackTrace);
      }

   }

   private void resetDataForm()
   {
      init();
      resetCustomerData();
   }

   private void loadBaskets()
   {
      resetAdvisor();
      loadBasketInfo();
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
         listDAO.getNewClientProfileData(getInstance());
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



   public void selectPortolio(Integer portfolioNum) {
      if (portfolioNum == null)
         portfolioNum = 0;

      switch (portfolioNum){
         case 0:
            setWhichPortfolio(0);
            createAssetPortfolio(getWatsonRiskScore());
            break;
         case 1:
            setWhichPortfolio(1);
            createAssetPortfolio(getConservativeNum().doubleValue());
            break;
         case 2:
            setWhichPortfolio(2);
            createAssetPortfolio(getAggressiveNum().doubleValue());
            break;
         default:
            setWhichPortfolio(0);
            createAssetPortfolio(getWatsonRiskScore());
            break;
      }
   }

   private void createAssetPortfolio(Double riskScore)
   {

      try
      {
         String tTheme = getTheme();
         if (getAccountTaxable())
         {
            if (!tTheme.startsWith("T."))
            {
               setTheme("T." + tTheme);
            }
         }
         else
         {
            if (tTheme.startsWith("T."))
            {
               setTheme(tTheme.substring(2));
            }
         }
         setRiskIndex(riskScore);
         setNumOfAllocation(1);
         setNumOfPortfolio(1);
         buildAssetClass();
         buildPortfolio();

         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   private void createCharts()
   {

      try
      {
         Map<String, String> configMap = webutil.getWebprofile().getWebInfo();
         setTypeOfChart(configMap.get("CHART.ASSET.ALLOCATION"));// HIGHCHART.2DDONUT for highchart and PRIMEFACES.2DDONUT for primfaces
         if (configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("HIGHCHART.2DDONUT"))
         {
            setResultChart(highChartsController.highChartrequesthandler(getPortfolioData(), getAssetData(), configMap));
         }
         if (configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION") != null && configMap.get("CHART.ASSET.ALLOCATION") != null &&
            configMap.get("CHART.RECOMMENDED.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.BARCHART")
            || configMap.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("PRIMEFACES.2DDONUT"))
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

            if (getGoalData() == null || getGoalData().getGoalDesired() == null || getGoalData().getGoalDesired() == 0.0)
            {
               displayGoalGraph = false;
               displayGoalText = false;
            }
            else
            {
               displayGoalGraph = true;
               if (getPortfolioData() != null)
               {
                  buildGoalsData();
                  // charts.createLineModel(getProjectionData());
                  // if (getGoalData() != null && getGoalData().getGoalDesired() != null && getGoalData().getGoalDesired() > 0.0)
                  charts.createGoalChart(getProjectionData(), getGoalData());
                  displayGoalText = (!getGoalData().getReachable());
               }
            }
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public void showLink(String secID)
   {
      watsonISINURL = getWebutil().getWebprofile().getWebInfo().get("WATSON.API.URL").toString()+"rest/discovery/getDocument/"+secID;
      pagemanager.nextPage();
   }


}
