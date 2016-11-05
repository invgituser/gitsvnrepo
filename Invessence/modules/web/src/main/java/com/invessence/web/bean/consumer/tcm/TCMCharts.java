package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.GoalsData;
import com.invmodel.performance.data.ProjectionData;
import org.primefaces.model.chart.*;
import com.google.gson.Gson;  //JSon for HighChart

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class TCMCharts extends InvessenceCharts implements Serializable
{
   private Integer maxGraghPlot;
   private Integer avgPerformance = 0;
   private Integer poorPerformance = 0;

   // HighChart Start
   private String yearChart;
   private String resultChart;

   public Integer getMaxGraghPlot()
   {
      return maxGraghPlot;
   }

   public void setMaxGraghPlot(Integer maxGraghPlot)
   {
      this.maxGraghPlot = maxGraghPlot;
   }

   public Integer getAvgPerformance()
   {
      return avgPerformance;
   }

   public void setAvgPerformance(Integer avgPerformance)
   {
      this.avgPerformance = avgPerformance;
   }

   public Integer getPoorPerformance()
   {
      return poorPerformance;
   }

   public void setPoorPerformance(Integer poorPerformance)
   {
      this.poorPerformance = poorPerformance;
   }

   public String getYearChart()
   {
      return yearChart;
   }

   public void setYearChart(String yearChart)
   {
      this.yearChart = yearChart;
   }

   public String getResultChart()
   {
      return resultChart;
   }

   public void setResultChart(String resultChart)
   {
      this.resultChart = resultChart;
   }

   // New method implements : Projection chart creation by using HighChart
   public void createProjectionHighChart(ProjectionData[] projectionData, Integer horizon, Integer currAge, Integer ageSeries, ProjectionData[] projectionDataAggressive)
   {
      //System.out.println("projectionData :"+projectionData+" :horizon:"+horizon+":currAge:"+currAge+":ageSeries:"+ageSeries);
      //System.out.println("projectionData.length :"+projectionData.length);
      currAge = (currAge == null) ? 30 : currAge;
      horizon = (horizon == null) ? 1 : horizon;
      Integer year;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 31;
      Long moneyInvested;
      Long money;
      Double dividingFactor = 1.0;

      goalChart = null;
      try
      {
         if (projectionData == null)
         {
            return;
         }

         if (projectionData.length < 2)
         {
            return;
         }


         goalChart = new LineChartModel();
         LineChartSeries totalGrowth = new LineChartSeries();
         // LineChartSeries totalInvested = new LineChartSeries();
         LineChartSeries lower1 = new LineChartSeries();
         LineChartSeries lower2 = new LineChartSeries();
         LineChartSeries upper1 = new LineChartSeries();
         LineChartSeries upper2 = new LineChartSeries();

         //growth.setLabel("Growth");
         totalGrowth.setLabel("Growth");
         // totalInvested.setLabel("Invested");
         lower1.setLabel("Lower1");
         lower2.setLabel("Lower2");
         upper1.setLabel("Upper1");
         upper2.setLabel("Upper2");

         totalYlabels = (horizon < 1) ? 1 : ((horizon > MAXPOINTONGRAPH) ? MAXPOINTONGRAPH : horizon);
         // yIncrement = (int) ((totalYlabels) / ((double) horizon));
         yIncrement = 1;  // offset by 1
         noOfYlabels = (int) (totalYlabels / ((double) yIncrement) % horizon);
         // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
         noOfYlabels = (noOfYlabels == 0) ? projectionData.length : noOfYlabels;
         if (noOfYlabels <= 10)
         {
            legendXrotation = 15;
         }
         else if (noOfYlabels < 15)
         {
            legendXrotation = 30;
         }
         else
         {
            legendXrotation = 90;
         }

         int y = 0;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + totalYlabels;
         Integer lowervalue = (int) ((double) projectionData[0].getLowerBand2() * .10);
         minGrowth = ((int) projectionData[0].getLowerBand2() - lowervalue < 0) ? 0 : (int) projectionData[0].getLowerBand2() - lowervalue;
         maxGrowth = 0;
         maxGraghPlot = 0;
         Double tmpvalue;
         Double temvalueLower1;
         Double temvalueLower2;
         Double temvalueUpper1;
         Double temvalueUpper2;
         Double temValueAvg;

         ArrayList goalLowerValue = new ArrayList();
         ArrayList goalUpperValue = new ArrayList();
         ArrayList goalYearValue = new ArrayList();
         ArrayList goalAvgValue = new ArrayList();
         ArrayList goalAgeSeries = new ArrayList();

         ArrayList lstLower = null;
         ArrayList lstUpper = null;

         while (y <= totalYlabels)
         {
            lstLower = new ArrayList();
            lstUpper = new ArrayList();

            year = calendarYear + y;
            ageSeries = currAge + y;
            // moneyInvested = Math.round(projectionData[y].getInvestedCapital() / dividingFactor);
            money = Math.round(projectionData[y].getUpperBand2() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > money.intValue()) ? maxGrowth : money.intValue();
            // growth.set(year, portfolio[y].getTotalCapitalGrowth());
            tmpvalue = (Math.round((projectionData[y].getTotalCapitalWithGains() / dividingFactor) * 100.0)) / 100.0;
            temValueAvg = tmpvalue;
            totalGrowth.set(year.toString(), tmpvalue);
            // totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            tmpvalue = (Math.round((projectionData[y].getLowerBand1() / dividingFactor) * 100.0)) / 100.0;
            temvalueLower1 = tmpvalue;
            lower1.set(year.toString(), tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getLowerBand2() / dividingFactor) * 100.0)) / 100.0;
            temvalueLower2 = tmpvalue;
            lower2.set(year.toString(), tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getUpperBand1() / dividingFactor) * 100.0)) / 100.0;
            temvalueUpper1 = tmpvalue;
            upper1.set(year.toString(), tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getUpperBand2() / dividingFactor) * 100.0)) / 100.0;
            temvalueUpper2 = tmpvalue;

            upper2.set(year.toString(), tmpvalue);
            // If incrementing anything other then 1, then make sure that last year is displayed.
            if (y >= totalYlabels) // If last point is plotted, then quit.
            {
               Integer lastpoint = totalYlabels;
               avgPerformance = (int) ((Math.round((projectionData[lastpoint].getTotalCapitalWithGains() / dividingFactor) * 100.0)) / 100.0);
               poorPerformance = (int) ((Math.round((projectionData[lastpoint].getLowerBand2() / dividingFactor) * 100.0)) / 100.0);
            }
            lstLower.add(temvalueLower1);
            lstLower.add(temvalueUpper1);
            lstUpper.add(temvalueLower2);
            lstUpper.add(temvalueUpper2);


            goalYearValue.add(year);
            goalAgeSeries.add(ageSeries);
            goalLowerValue.add(lstLower);
            goalUpperValue.add(lstUpper);
            goalAvgValue.add((int) Math.floor(temValueAvg));

            y += yIncrement;
         }

         Integer digits = maxGrowth.toString().length();
         Double scale = Math.pow(10, digits - 1);

         maxGrowth = (int) ((Math.ceil(maxGrowth.doubleValue() / scale)) * scale);
         maxGraghPlot = (int) ((Math.round((projectionDataAggressive[totalYlabels].getUpperBand2() / dividingFactor) * 100.0)) / 100.0);

         HashMap chartMap = new HashMap();

         chartMap.put("goalYearValue", goalYearValue);
         chartMap.put("goalLowerValue", goalLowerValue);
         chartMap.put("goalUpperValue", goalUpperValue);
         chartMap.put("goalAvgValue", goalAvgValue);
         chartMap.put("minGrowth", minGrowth);
         chartMap.put("maxGraghPlot", maxGraghPlot);
         chartMap.put("goalAgeSeries", goalAgeSeries);

         resultChart = new Gson().toJson(chartMap);
         //System.out.println("resultChart = " + resultChart);
         // goalChart.addSeries(growth);
         goalChart.addSeries(totalGrowth);
         // goalChart.addSeries(totalInvested);
         goalChart.addSeries(lower2);
         goalChart.addSeries(lower1);
         goalChart.addSeries(upper1);
         goalChart.addSeries(upper2);
         goalChart.setSeriesColors("00FF00,7C8686,009ABB,009ABB,7C8686");
         //goalChart.setSeriesColors("009ABB,009ABB");
         goalChart.setShowPointLabels(true);
         goalChart.setMouseoverHighlight(false);
         goalChart.setShowDatatip(false);

         Axis xAxis = goalChart.getAxis(AxisType.X);
         xAxis.setLabel("Years");
         xAxis.setMin(calendarYear);
         xAxis.setMax(maxYearPoint);
         xAxis.setTickFormat("%d");
         // xAxis.setTickInterval("1");
         // xAxis.setTickAngle(90);

         Axis yAxis = goalChart.getAxis(AxisType.Y);
         //yAxis.setLabel("Projection");
         // yAxis.setMin(minGrowth);
         // yAxis.setMax(maxGrowth);
         yAxis.setTickFormat("$%'d");
         goalChart.setExtender("goals_extensions");

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         goalChart = null;
      }
   }
   //End HighChart implementation
}
