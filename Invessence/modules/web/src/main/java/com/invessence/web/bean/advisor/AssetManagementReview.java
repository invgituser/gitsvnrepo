package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import com.invessence.web.bean.consumer.tcm.TCMCharts;
import com.invessence.web.constant.WebConst;
import com.invessence.web.dao.advisor.AdvisorListDataDAO;
import com.invessence.web.data.common.*;
import com.invessence.web.data.consumer.tcm.TCMCustomer;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.fixedmodel.data.FMData;
import com.invmodel.performance.data.ProjectionData;
import org.primefaces.event.SlideEndEvent;

/**
 * Created by sagar on 8/13/2017.
 */

@ManagedBean(name = "asstMgmtRvw")
@SessionScoped
public class AssetManagementReview extends TCMCustomer implements Serializable
{

   private TCMCharts charts = new TCMCharts();
   ArrayList<FMData> fmDataArrayList;
   LinkedHashMap<String, FMData> fmDataMap;
   String newLongDesc;
   private String performanceChart;
   @ManagedProperty("#{advisorListDataDAO}")
   private AdvisorListDataDAO advisorListDataDAO;
   private List<AdvisorBasket> listBasket;
   private List<AssetFileUploadList> listApproveTemplate,listValidateTemplate;
   private boolean performanceAproved,showApproveTempDD,projectionAproved;
   private String selApprovTheme;
   private boolean showReviewPan;


   public void preRenderView()
   {

      try
      {
//         if (!FacesContext.getCurrentInstance().isPostback())
//         {
//         }

         listBasket=advisorListDataDAO.getAdvisorTheme("BB");
         listValidateTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Validate Success");
//         selApprovTheme="0.BB";
//         if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(WebConst.ASSET)==null ){
            showReviewPan=true;
//            if(listValidateTemplate!=null && listValidateTemplate.size()>0)
//            {
//               selApprovTheme =listValidateTemplate.get(0).getTemplatename();
//            }else{
//               selApprovTheme="0.BB";
//            }
            riskCalculator.setNumberofQuestions(3);
//         whichChart = "pie";
//         disablegraphtabs = true;
//         disabledetailtabs = true;
//         setNewapp("N");
//         beanAcctnum = null;
         setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
//         riskCalculator.setInvestmentobjective("Retirement");
         resetAdvisor();
         loadBasketInfo();
         riskCalculator.setRiskFormula("C");
         initialInvestment = 100000;

         onGoalChangeValue("Other");
         riskCalculator.setRiskAge(30);

//         riskCalculator.setInvestmentobjective("Retirement");
         setHorizon(30);
         Double riskIndex = riskCalculator.calculateRisk();
//         createDynaAssetPortfolio(1, riskIndex, "T_2108");
            createDynaAssetPortfolio(1, riskIndex, selApprovTheme);
         calcProjectionChart();
         doProjectionChart();


//         createDynaPerformanceAssetPortfolio(1, riskIndex, "T_2108");
            createDynaPerformanceAssetPortfolio(1, riskIndex, selApprovTheme);
         doPerformanceFinalpage();
//            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put(WebConst.ASSET,"Hi");
//         }
      }
      catch (Exception e)
      {
//         resetDataForm();
      }
   }

   public void onRevTempChange()
   {
      if(selApprovTheme!=null && !selApprovTheme.equalsIgnoreCase("0"))
      {
         showReviewPan = true;
         preRenderView();
      }else{
         showReviewPan = true;
      }
   }

   public void createDynaPerformanceAssetPortfolio(Integer noOfYears, Double riskIndex, String strTheme)
   {

      try
      {
         setRiskIndex(riskIndex);
//         super.createAssetPortfolio(noOfYears, riskIndex);
         createDynaAssetPortfolio(1, riskIndex, selApprovTheme);
//         createDynaAssetPortfolio(1, riskIndex, "T_2108");


         setFixedModelPortfolioList(strTheme);
         setFmDataLinkedHashMap(strTheme);
         fmDataArrayList = getFixedModelPortfolioList();
         fmDataMap = getFmDataLinkedHashMap();

         if (getFixedModelName() != null)
         {
            setPortfolioName(getFixedModelName());
            newLongDesc = fmDataMap.get(getPortfolioName()).getDescription();

         }
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
         // charts.setMeterGuage(getMeterRiskIndicator());
         if (getAssetData() != null)
         {
            charts.createPieModel(getAssetData(), 0);
            // charts.createBarChart(getAssetData(), 0);
         }
         else
         {
            charts.resetCharts();
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void onGoalChangeValue(String strGoal)
   {
      riskCalculator.setRiskAge(null);
      riskCalculator.setRetireAge(null);
      riskCalculator.setRiskHorizon(30);
      riskCalculator.setInvestmentobjective(strGoal);
      setHorizon(30);
   }

   public void doPerformanceFinalpage()
   {
      if (getFixedModel())
      {
         ProjectionData[] performancedata = modelUtil.buildPerformanceChart((ProfileData) getInstance(), getFixedFMModel());
         charts.createProjectionHighChart(performancedata,
                                          getHorizon(),
                                          getAge(),
                                          riskCalculator.getRetireAge(),
                                          null);
      }
   }

   public void doProjectionChart()
   {
      String event = riskCalculator.getAns5();
      Integer whichslide = null;

      if ((event != null) && (!event.isEmpty()))
      {
         whichslide = webutil.getConverter().getIntData(event);
      }

      if (whichslide == null)
      {
         if (riskCalculator.getAns4() != null && !riskCalculator.getAns4().isEmpty())
         {
            whichslide = webutil.getConverter().getIntData(riskCalculator.getAns4()); // See comment below, we are offsetting it by one.
         }
         else
         {
            whichslide = 0;
         }
      }

      if (whichslide > 0)
      { // Answers are stored in 1 to 5.  Whereas array is from 0-4
         whichslide -= 1;   // We have to offset the slider by 1 if > 0
      }
      //  Calls for Projection creation chart by using HighChart
      if (getProjectionDatas() != null)
      {
         if (getProjectionDatas().size() > 0)
         {
            Integer portfolioID = getProjectionDatas().size() - 1;
            charts.createProjectionHighChart(getProjectionDatas().get(whichslide),
                                             getHorizon(),
                                             getAge(),
                                             riskCalculator.getRetireAge(),
                                             getProjectionDatas().get(portfolioID));
         }

      }
      Double riskIndex = riskCalculator.calculateRisk();
      createDynaAssetPortfolio(1, riskIndex, selApprovTheme);
//      createProjectionAssetPortfolio(1, riskIndex, "T_2108");
   }

   public void createProjectionAssetPortfolio(Integer noOfYears, Double riskIndex,String Theme)
   {

      try
      {
         setRiskIndex(riskIndex);
         super.createDynaAssetPortfolio(noOfYears, riskIndex,Theme);

         setFixedModelPortfolioList(Theme);
         setFmDataLinkedHashMap(Theme);
         fmDataArrayList = getFixedModelPortfolioList();
         fmDataMap = getFmDataLinkedHashMap();

         if (getFixedModelName() != null)
         {
            setPortfolioName(getFixedModelName());
            newLongDesc = fmDataMap.get(getPortfolioName()).getDescription();

         }
         createCharts();
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public String getAns5Tag1(Integer which)
   {
      if (which != null)
      {
         if (riskCalculator.getAns5() != null && riskCalculator.getAns5().equalsIgnoreCase(which.toString()))
         {
            return "triangleShape Fleft triangleShape_Selected";
         }
      }
      return "triangleShape Fleft";
   }

   public void setAnswer5(Integer ans)
   {

      if (ans != null && ans > 0)
      {
         riskCalculator.setAns5(ans.toString());
         doProjectionChart();
      }
   }

   public String getAns5Tag2(Integer which)
   {
      if (which != null)
      {
         if (riskCalculator.getAns5() != null && riskCalculator.getAns5().equalsIgnoreCase(which.toString()))
         {
            return "Container90 ProjectionSlabHeight ProjectionSlabHeight_Selected";
         }
      }
      return "Container90 ProjectionSlabHeight";
   }

   public void onAllocSlider(SlideEndEvent event)
   {
      // setAge(event.getValue());

      setRiskCalcMethod(WebConst.ADVISOR_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      setAllocationIndex(event.getValue());
//      createAssetPortfolio(1, riskIndex);
      createDynaPerformanceAssetPortfolio(1, riskIndex, selApprovTheme);
//      createDynaPerformanceAssetPortfolio(1, riskIndex, "T_2108");

      if (getFixedModelName() != null)
      {
         newLongDesc = fmDataMap.get(getFixedModelName()).getDescription();
      }
      doPerformanceFinalpage();
   }
   public void showTemplateForApprove(){
      System.out.println("In checkbox ");
      showApproveTempDD=false;
      System.out.println("performanceAproved "+performanceAproved);
      System.out.println("projectionAproved "+projectionAproved);
      System.out.println("Out checkbox ");
      if(performanceAproved){
         advisorListDataDAO.updateTemplateStatus("Predefined",selApprovTheme,"projection","Y");
      }else{
         advisorListDataDAO.updateTemplateStatus("Predefined",selApprovTheme,"projection","N");
      }

      if(projectionAproved){
         advisorListDataDAO.updateTemplateStatus("Predefined",selApprovTheme,"perormance","Y");
      }else{
         advisorListDataDAO.updateTemplateStatus("Predefined",selApprovTheme,"perormance","N");
      }

      listApproveTemplate=advisorListDataDAO.collectUpdatedThemeList("Predefined","Verified");
   }

   public void doAllocReset()
   {
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      createDynaPerformanceAssetPortfolio(1, riskIndex, selApprovTheme); // Build default chart for the page...
//      createDynaPerformanceAssetPortfolio(1, riskIndex, "0.BB"); // Build default chart for the page...
      setSliderAllocationIndex(getAllocationIndex());
      doPerformanceFinalpage();

   }

   public TCMCharts getCharts()
   {
      return charts;
   }

   public void setCharts(TCMCharts charts)
   {
      this.charts = charts;
   }

   public ArrayList<FMData> getFmDataArrayList()
   {
      return fmDataArrayList;
   }

   public void setFmDataArrayList(ArrayList<FMData> fmDataArrayList)
   {
      this.fmDataArrayList = fmDataArrayList;
   }

   public LinkedHashMap<String, FMData> getFmDataMap()
   {
      return fmDataMap;
   }

   public void setFmDataMap(LinkedHashMap<String, FMData> fmDataMap)
   {
      this.fmDataMap = fmDataMap;
   }

   public String getNewLongDesc()
   {
      return newLongDesc;
   }

   public void setNewLongDesc(String newLongDesc)
   {
      this.newLongDesc = newLongDesc;
   }

   public String getPerformanceChart()
   {
      return performanceChart;
   }

   public void setPerformanceChart(String performanceChart)
   {
      this.performanceChart = performanceChart;
   }

   public AdvisorListDataDAO getAdvisorListDataDAO()
   {
      return advisorListDataDAO;
   }

   public void setAdvisorListDataDAO(AdvisorListDataDAO advisorListDataDAO)
   {
      this.advisorListDataDAO = advisorListDataDAO;
   }

   public List<AdvisorBasket> getListBasket()
   {
      return listBasket;
   }

   public void setListBasket(List<AdvisorBasket> listBasket)
   {
      this.listBasket = listBasket;
   }

   public boolean isPerformanceAproved()
   {
      return performanceAproved;
   }

   public void setPerformanceAproved(boolean performanceAproved)
   {
      this.performanceAproved = performanceAproved;
   }

   public boolean isShowApproveTempDD()
   {
      return showApproveTempDD;
   }

   public void setShowApproveTempDD(boolean showApproveTempDD)
   {
      this.showApproveTempDD = showApproveTempDD;
   }

   public boolean isProjectionAproved()
   {
      return projectionAproved;
   }

   public void setProjectionAproved(boolean projectionAproved)
   {
      this.projectionAproved = projectionAproved;
   }

   public List<AssetFileUploadList> getListApproveTemplate()
   {
      return listApproveTemplate;
   }

   public void setListApproveTemplate(List<AssetFileUploadList> listApproveTemplate)
   {
      this.listApproveTemplate = listApproveTemplate;
   }

   public List<AssetFileUploadList> getListValidateTemplate()
   {
      return listValidateTemplate;
   }

   public void setListValidateTemplate(List<AssetFileUploadList> listValidateTemplate)
   {
      this.listValidateTemplate = listValidateTemplate;
   }

   public String getSelApprovTheme()
   {
      return selApprovTheme;
   }

   public void setSelApprovTheme(String selApprovTheme)
   {
      this.selApprovTheme = selApprovTheme;
   }

   public boolean isShowReviewPan()
   {
      return showReviewPan;
   }

   public void setShowReviewPan(boolean showReviewPan)
   {
      this.showReviewPan = showReviewPan;
   }
}
