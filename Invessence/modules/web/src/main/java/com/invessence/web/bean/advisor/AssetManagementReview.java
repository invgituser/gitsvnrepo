package com.invessence.web.bean.advisor;

import java.io.Serializable;
import java.util.*;

import javax.faces.bean.*;

import com.invessence.web.bean.consumer.tcm.TCMCharts;
import com.invessence.web.constant.WebConst;
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
public class AssetManagementReview  extends TCMCustomer implements Serializable
{

   private TCMCharts charts = new TCMCharts();
   ArrayList<FMData> fmDataArrayList;
   LinkedHashMap<String, FMData> fmDataMap;
   String newLongDesc;
   private String performanceChart;
   public void preRenderView()
   {

      try
      {
//         if (!FacesContext.getCurrentInstance().isPostback())
//         {
//         }

         riskCalculator.setNumberofQuestions(5);
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
         initialInvestment=100000;

         onGoalChangeValue("Other");
         riskCalculator.setRiskAge(20);

//         riskCalculator.setInvestmentobjective("Retirement");
         setHorizon(20);
         Double riskIndex = riskCalculator.calculateRisk();
         createDynaAssetPortfolio(20, riskIndex,"0.BB");
         calcProjectionChart();
         doProjectionChart();


         createDynaPerformanceAssetPortfolio(20, riskIndex,"0.BB");
         doPerformanceFinalpage();
      }
      catch (Exception e)
      {
//         resetDataForm();
      }
   }

   public void createDynaPerformanceAssetPortfolio(Integer noOfYears, Double riskIndex,String strTheme)
   {

      try
      {
         setRiskIndex(riskIndex);
         super.createAssetPortfolio(noOfYears, riskIndex);

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
         riskCalculator.setRiskHorizon(20);
      riskCalculator.setInvestmentobjective(strGoal);
      setHorizon(20);
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
      createDynaAssetPortfolio(20, riskIndex,"0.BB");
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
      createDynaPerformanceAssetPortfolio(20, riskIndex,"0.BB");
      if (getFixedModelName() != null)
         newLongDesc = fmDataMap.get(getFixedModelName()).getDescription();
      doPerformanceFinalpage();
   }

   public void doAllocReset()
   {
      setRiskCalcMethod(WebConst.CONSUMER_RISK_FORMULA);
      Double riskIndex = riskCalculator.calculateRisk();
      createDynaPerformanceAssetPortfolio(20, riskIndex,"0.BB"); // Build default chart for the page...
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
}
