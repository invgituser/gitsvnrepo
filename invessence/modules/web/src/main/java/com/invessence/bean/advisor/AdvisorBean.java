package com.invessence.bean.advisor;

import java.io.*;
import java.util.*;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import com.invessence.constant.Const;
import com.invessence.dao.advisor.AdvisorSaveDataDAO;
import com.invessence.data.*;
import com.invessence.data.advisor.*;
import com.invessence.util.EmailMessage;
import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.*;
import com.invmodel.portfolio.data.*;
import org.primefaces.event.*;
import org.primefaces.model.chart.*;

import static javax.faces.context.FacesContext.getCurrentInstance;

@ManagedBean(name = "advisorBean")
@SessionScoped
public class AdvisorBean extends AdvisorData implements Serializable
{
   private static final long serialVersionUID = 100001L;

   private AdvisorSaveDataDAO advisorSaveDataDAO;
   private EmailMessage messageText;
   private Integer advisorRiskIndex;
   private Boolean displayPieChart = false;

   private PieChartModel pieModel;
   private PieChartModel scpieModel;

   public AdvisorSaveDataDAO getAdvisorSaveDataDAO()
   {
      return advisorSaveDataDAO;
   }

   public void setAdvisorSaveDataDAO(AdvisorSaveDataDAO advisorSaveDataDAO)
   {
      this.advisorSaveDataDAO = advisorSaveDataDAO;
   }

   public EmailMessage getMessageText()
   {
      return messageText;
   }

   public void setMessageText(EmailMessage messageText)
   {
      this.messageText = messageText;
   }

   public Boolean getDisplayPieChart()
   {
      return displayPieChart;
   }

   public void setDisplayPieChart(Boolean displayPieChart)
   {
      this.displayPieChart = displayPieChart;
   }

   public Integer getAdvisorRiskIndex()
   {
      return advisorRiskIndex;
   }

   public void setAdvisorRiskIndex(Integer advisorRiskIndex)
   {
      Double weightedRisk;
      this.advisorRiskIndex = advisorRiskIndex;
      weightedRisk = 28 - ((2.0 * advisorRiskIndex.doubleValue()) + Math.round(advisorRiskIndex.doubleValue() / 1.2));
      setRiskIndex(weightedRisk.intValue());
   }

   @PostConstruct
   public void init()
   {
      try
      {
         HttpServletRequest req = (HttpServletRequest) getCurrentInstance().getExternalContext().getRequest();
         String userName = req.getRemoteUser();
         Long logonid = (Long) getCurrentInstance().getExternalContext().getSessionMap().get(Const.LOGONID_PARAM);
         if (logonid == null)
         {
            getCurrentInstance().getExternalContext().redirect("/advisor/login.xhtml");
         }
         else {
            UserInfoData uid = (UserInfoData)  getCurrentInstance().getExternalContext().getSessionMap().get(Const.USER_INFO);
            String groupname = uid.getGroupname();
            if (groupname == null || groupname.isEmpty())
               setAdvisor(InvConst.DEFAULT_ADVISOR);
            else
               setAdvisor(uid.getGroupname());
         }
      }
      catch (Exception ex)
      {

      }
   }


   public void refreshChart(SlideEndEvent event)
   {
      //createAssetPlan(this.getInstance());
   }

   public void changeEvent(ValueChangeEvent event)
   {
      String oldValue;
      String newValue;
      try
      {
/*
         newValue = event.getNewValue().toString();
         if (event.getOldValue() != null)
         {
            oldValue = event.getOldValue().toString();
         }
         else
            oldValue = "";

         if (! newValue.equalsIgnoreCase(oldValue)) {
            if (this.getInstance() != null)
               createAssetPlan(this.getInstance());
         }
*/
/*
         if ((getRiskIndex() != null) && (getAge() != null) && (getHorizon() != null) && getAccountType() != null) {

            if (! newValue.equalsIgnoreCase(oldValue))
               createAssetPlan(this.getInstance());
         }
*/

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public String saveProfile()
   {
      try
      {
         if (getAge() != null ||
            getHorizon() != null ||
            getInitialInvestment() != null ||
            getRiskIndex() != null)
         {
            createAssetPlan(getInstance());
            createPortfolio(getInstance());
         }
         else
         {
            FacesContext.getCurrentInstance().addMessage(null,
                                                         new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                                                          "",
                                                                          "Try Again. Please fill all data as required."));

            return "failed";
         }
      }
      catch (Exception ex)
      {

      }
      return "success";
   }

   public String saveAllocation()
   {
      try
      {
         // New recreate the new portfolio.
         createPortfolio(getInstance());
         return "success";
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return "failed";
   }

   public void createAssetPlan(ManageGoals goals)
   {
      AssetClass[] aamc;
      MsgData data = new MsgData();
      try
      {
         Integer displayYear = 0;
         setDisplayPieChart(true);
         setNumOfAllocation(1);
         setObjective(2);
         setExperience(2);
         setStayInvested(1);
         setDependent(0);
         setRisk("M");
         setTheme(getTheme());
         aamc = getAllocModel().getAssetDistribution((ProfileData) goals);
         setAssetData(aamc);
         loadEditableAssetClass(displayYear);

         // Now refresh the pages...
         createPieModel(aamc[displayYear]);

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + " - Error:AdvisorBean.createAssetPlan");
         data.setMsg(messageText.getMessagetext("error.createAssetPlan", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
      }
   }

   public void createPortfolio(ManageGoals goals)
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      MsgData data = new MsgData();
      try
      {
         Integer displayYear = 0;
         goals.setNumOfPortfolio(1);
         aamc = getAssetData();
         if (aamc != null)
         {
            pfclass = getPortfolioModel().getDistributionList(aamc,
                                                              (ProfileData) goals);
            if (pfclass != null)
            {
               setPortfolioData(pfclass);

               // Now refresh the pages...
               loadPortfolioList(displayYear);
               //createSCPieModel(pfclass[0].getSubclasslist());
            }
         }

      }
      catch (Exception ex)
      {
         String stackTrace = ex.getMessage();
         data.setSource("Internal");
         data.setSender(Const.MAIL_SENDER);
         data.setReceiver(Const.MAIL_SUPPORT);
         data.setSubject(Const.COMPANY_NAME + " - Error:AdvisorBean.createPortfolio");
         data.setMsg(messageText.getMessagetext("error.createPortfolio", new Object[]{stackTrace}));
         messageText.writeMessage("Error", data);
      }
   }

   // Asset Allocation Page Data
   private AssetClass[] aamc;

   private String seriesColor;
   private String scseriesColor;

   public String getSeriesColor()
   {
      return seriesColor;
   }

   public void setSeriesColor(String seriesColor)
   {
      this.seriesColor = seriesColor;
   }

   public String getScseriesColor()
   {
      return scseriesColor;
   }

   public void setScseriesColor(String scseriesColor)
   {
      this.scseriesColor = scseriesColor;
   }

   public PieChartModel getPieModel()
   {
      if (this.pieModel == null)
      {
         createAssetPlan(getInstance());
      }
      return pieModel;
   }

   public void setPieModel(PieChartModel pieModel)
   {
      this.pieModel = pieModel;
   }

   public PieChartModel getScpieModel()
   {
      return scpieModel;
   }

   public void setScpieModel(PieChartModel scpieModel)
   {
      this.scpieModel = scpieModel;
   }

   public void refreshPie()
   {
      loadEditableAssetClass(0);
      createPieModel(this.getInstance().getAssetData()[0]);
   }

   private void createPieModel(AssetClass aac)
   {
      String color;
      Integer slices;

      this.pieModel = new PieChartModel();
      slices = aac.getOrderedAsset().size();
      for (int i = 0; i < slices; i++)
      {
         String assetname = aac.getOrderedAsset().get(i);
         String label = assetname + " - " + aac.getAssetRoundedActualWeight(assetname) + "%";
         pieModel.set(label, aac.getAssetRoundedActualWeight(assetname));

         color = aac.getAssetColor(assetname).replace('#', ' ');
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


   private void createSCPieModel(ArrayList<PortfolioSubclass> sbc)
   {
      String color;
      Integer slices;

      this.scpieModel = new PieChartModel();
      slices = sbc.size();
      for (int i = 0; i < slices; i++)
      {
         String key = sbc.get(i).getName();
         String label = key + " - " + sbc.get(i).getRoundedWeight() + "%";
         scpieModel.set(label, sbc.get(i).getRoundedWeight());

         color = sbc.get(i).getColor().replace('#', ' ');
         color.trim();
         if (i == 0)
         {
            scseriesColor = color;
         }
         else
         {
            scseriesColor = scseriesColor + ", " + color;
         }
      }
   }

   public void excludeThese()
   {
      try
      {
         if (getExcludeList() != null)
         {
            /* Remove these from final Portfolio*/
         }

      }
      catch (Exception ex)
      {

      }
   }

   public void savePortfolio()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      String key, assetclass, subclass;
      try
      {
         if (getExcludedSubAsset() != null)
         {
            resetCustomAllocation();
            for (int i = 0; i < getExcludedSubAsset().size(); i++)
            {
               assetclass = getExcludedSubAsset().get(i).getParentclass();
               subclass = getExcludedSubAsset().get(i).getSubasset();
               addCustomAllocation(assetclass, subclass, 0.0);
            }
            createPortfolio(getInstance());
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void saveData()
   {
      AssetClass[] aamc;
      Portfolio[] pfclass;
      Map<String, CustomAllocation> excludeList;
      try
      {
         aamc = getAssetData();
         pfclass = getPortfolioData();
         excludeList = getCustomAllocations();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public String testProfile()
   {
      try
      {
         setAge(30);
         setHorizon(35);
         setInitialInvestment(100000);
         setRiskIndex(1);
         createAssetPlan(getInstance());
         createPortfolio(getInstance());
      }
      catch (Exception ex)
      {

      }
      return "success";
   }

   List<PortfolioSubclass> excludedSubAsset;

   public List<PortfolioSubclass> getExcludedSubAsset()
   {
      return excludedSubAsset;
   }

   public void setExcludedSubAsset(List<PortfolioSubclass> excludedSubAsset)
   {
      this.excludedSubAsset = excludedSubAsset;
   }


}