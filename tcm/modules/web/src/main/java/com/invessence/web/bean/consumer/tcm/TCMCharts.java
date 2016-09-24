package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
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

public class TCMCharts implements Serializable
{
   JavaUtil jutil = new JavaUtil();
   private Integer year;
   private Integer calendarYear, minYearPoint, maxYearPoint, minGrowth, maxGrowth,legendXrotation,maxGraghPlot;

   private LineChartModel lineChart;
   private PieChartModel pieChart;
   private MeterGaugeChartModel meterGuage;
   private BarChartModel barChart;
   private LineChartModel goalChart;
   private BarChartModel riskbarChart;

   private Integer avgPerformance = 0;
   private Integer poorPerformance = 0;

   // HighChart Start
   private String yearChart;
   private String resultChart;

   // HighChart End

   public TCMCharts()
   {
        resetCharts();
   }

   public CartesianChartModel getLineChart()
   {
      return lineChart;
   }

   public PieChartModel getPieChart()
   {
      return pieChart;
   }

   public Integer getYear()
   {
      return year;
   }

   public Integer getCalendarYear()
   {
      return calendarYear;
   }

   public void setCalendarYear(Integer calendarYear)
   {
      this.calendarYear = calendarYear;
   }

   public Integer getMinYearPoint()
   {
      return minYearPoint;
   }

   public Integer getMaxYearPoint()
   {
      return maxYearPoint;
   }

   public Integer getMinGrowth()
   {
      return minGrowth;
   }

   public Integer getMaxGrowth()
   {
      return maxGrowth;
   }

   public Integer getLegendXrotation()
   {
      return legendXrotation;
   }

   public MeterGaugeChartModel getMeterGuage()
   {
      return meterGuage;
   }

   public BarChartModel getBarChart()
   {
      return barChart;
   }

   public LineChartModel getGoalChart()
   {
      return goalChart;
   }

   public void setGoalChart(LineChartModel goalChart)
   {
      this.goalChart = goalChart;
   }

   public BarChartModel getRiskbarChart()
   {
      return riskbarChart;
   }

   public void setMeterGuage(Integer pointer)
   {
      if (meterGuage == null)
         createDefaultMeterGuage();
      this.meterGuage.setValue(pointer);
   }

   public Integer getAvgPerformance()
   {
      return avgPerformance;
   }

   public Integer getPoorPerformance()
   {
      return poorPerformance;
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
   public Integer getMaxGraghPlot()
   {
      return maxGraghPlot;
   }

   public void setMaxGraghPlot(Integer maxGraghPlot)
   {
      this.maxGraghPlot = maxGraghPlot;
   }

   public void createDefaultMeterGuage() {
      List<Number> intervals = new ArrayList<Number>(){{
         add(16);
         add(32);
         add(50);
      }};
      this.meterGuage = new MeterGaugeChartModel(24, intervals);
      // this.meterGuage.setTitle("Risk");
      this.meterGuage.setSeriesColors("006699, FFCC00, 990000");
      this.meterGuage.setShowTickLabels(false);
      // this.meterGuage.setLabelHeightAdjust(-25);
      this.meterGuage.setIntervalOuterRadius(20);
   }

   public void createLineModel(ProjectionData[] projectionData)
   {
      Integer year;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 30;
      Long moneyInvested;
      Long money;
      Double dividingFactor = 1.0;

      lineChart = new LineChartModel();
      try
      {
         if (projectionData == null)
            return;

         if (projectionData.length < 2)
            return;


         LineChartSeries totalGrowth = new LineChartSeries();
         LineChartSeries totalInvested = new LineChartSeries();
         LineChartSeries lower1 = new LineChartSeries();
         LineChartSeries lower2 = new LineChartSeries();
         LineChartSeries upper1 = new LineChartSeries();
         LineChartSeries upper2 = new LineChartSeries();

         //growth.setLabel("Growth");
         totalGrowth.setLabel("Growth");
         totalInvested.setLabel("Invested");
         lower1.setLabel("Lower1");
         lower2.setLabel("Lower2");
         upper1.setLabel("Upper1");
         upper1.setLabel("Upper2");

         yIncrement = (int) (((double) projectionData.length) / ((double) MAXPOINTONGRAPH));
         yIncrement = yIncrement + 1;  // offset by 1
         noOfYlabels = (int) (((double) projectionData.length) / ((double) yIncrement)) % MAXPOINTONGRAPH;
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
         totalYlabels = projectionData.length - 1;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + totalYlabels;
         Integer lowervalue =  (int) ((double) projectionData[0].getLowerBand2() * .10);
         minGrowth = ((int) projectionData[0].getLowerBand2() - lowervalue < 0) ? 0 : (int) projectionData[0].getLowerBand2() - lowervalue;
         maxGrowth = 0;
         while (y <= totalYlabels)
         {
            year = calendarYear + y;
            // moneyInvested = Math.round(projectionData[y].getInvestedCapital() / dividingFactor);
            money = Math.round(projectionData[y].getUpperBand2() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > money.intValue()) ? maxGrowth : money.intValue();
            // growth.set(year, portfolio[y].getTotalCapitalGrowth());
            // totalGrowth.set(year.toString(), moneyPnL);
            // totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            lower1.set(year.toString(), projectionData[y].getLowerBand1()/dividingFactor);
            lower2.set(year.toString(), projectionData[y].getLowerBand2()/dividingFactor);
            upper1.set(year.toString(), projectionData[y].getUpperBand1()/dividingFactor);
            upper2.set(year.toString(), projectionData[y].getUpperBand2()/dividingFactor);
            // If incrementing anything other then 1, then make sure that last year is displayed.
            if (y == totalYlabels)
            {
               y++;  // If last point is plotted, then quit.
            }
            else
            {
               y = ((y + yIncrement) > totalYlabels) ? y = totalYlabels : y + yIncrement;
            }
         }

         Integer digits = maxGrowth.toString().length();
         Double scale = Math.pow(10, digits - 1);

         maxGrowth = (int) ((Math.ceil(maxGrowth.doubleValue() / scale)) * scale);
         // lineModel.addSeries(growth);
         // lineChart.addSeries(totalGrowth);
         // lineChart.addSeries(totalInvested);
         lineChart.addSeries(lower2);
         //lineChart.addSeries(lower1);
         //lineChart.addSeries(upper1);
         lineChart.addSeries(upper2);
         lineChart.setSeriesColors("7C8686,7C8686");
         lineChart.setShowPointLabels(true);
         lineChart.setMouseoverHighlight(false);
         lineChart.setShowDatatip(false);

         Axis xAxis = lineChart.getAxis(AxisType.X);
         xAxis.setLabel("Years");
         xAxis.setMin(calendarYear);
         xAxis.setMax(maxYearPoint);
         xAxis.setTickFormat("%d");
         // xAxis.setTickInterval("1");
         // xAxis.setTickAngle(90);

         Axis yAxis = lineChart.getAxis(AxisType.Y);
         //yAxis.setLabel("Projection");
         // yAxis.setMin(minGrowth);
         // yAxis.setMax(maxGrowth);
         yAxis.setTickFormat("$%'d");
         lineChart.setExtender("line_extensions");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   // New method implements : Projection chart creation by using HighChart
   public void createProjectionHighChart(ProjectionData[] projectionData, Integer horizon, Integer currAge,Integer ageSeries, ProjectionData[] projectionDataAggressive)
   {
      //System.out.println("projectionData :"+projectionData+" :horizon:"+horizon+":currAge:"+currAge+":ageSeries:"+ageSeries);
      //System.out.println("projectionData.length :"+projectionData.length);
      currAge = (currAge == null) ? 30: currAge;
      horizon = (horizon == null) ? 10 : horizon;
      Integer year;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 35;
      Long moneyInvested;
      Long money;
      Double dividingFactor = 1.0;

      goalChart = null;
      try
      {
         if (projectionData == null)  {
            return;
         }

         if (projectionData.length < 2)
            return;


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

         totalYlabels = (horizon < 10) ? 10 : ((horizon > MAXPOINTONGRAPH) ? MAXPOINTONGRAPH : horizon);
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
         Integer lowervalue =  (int) ((double) projectionData[0].getLowerBand2() * .10);
         minGrowth = ((int) projectionData[0].getLowerBand2() - lowervalue < 0) ? 0 : (int) projectionData[0].getLowerBand2() - lowervalue;
         maxGrowth = 0;
         maxGraghPlot =0;
         Double tmpvalue;
         Double temvalueLower1;
         Double temvalueLower2;
         Double temvalueUpper1;
         Double temvalueUpper2;
         Double temValueAvg;

         ArrayList goalLowerValue=new ArrayList();
         ArrayList goalUpperValue=new ArrayList();
         ArrayList goalYearValue=new ArrayList();
         ArrayList goalAvgValue=new ArrayList();
         ArrayList goalAgeSeries=new ArrayList();

         ArrayList lstLower=null;
         ArrayList lstUpper=null;

         while (y < totalYlabels)
         {
            lstLower=new ArrayList();
            lstUpper=new ArrayList();

            year = calendarYear + y;
            ageSeries = currAge + y;
            // moneyInvested = Math.round(projectionData[y].getInvestedCapital() / dividingFactor);
            money = Math.round(projectionData[y].getUpperBand2() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > money.intValue()) ? maxGrowth : money.intValue();
            // growth.set(year, portfolio[y].getTotalCapitalGrowth());
            tmpvalue = (Math.round((projectionData[y].getTotalCapitalWithGains()/dividingFactor) * 100.0))/100.0;
            temValueAvg = tmpvalue;
            totalGrowth.set(year.toString(), tmpvalue);
            // totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            tmpvalue = (Math.round((projectionData[y].getLowerBand1()/dividingFactor) * 100.0))/100.0;
            temvalueLower1 = tmpvalue;
            lower1.set(year.toString(),tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getLowerBand2()/dividingFactor) * 100.0))/100.0;
            temvalueLower2 = tmpvalue;
            lower2.set(year.toString(), tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getUpperBand1()/dividingFactor) * 100.0))/100.0;
            temvalueUpper1 = tmpvalue;
            upper1.set(year.toString(), tmpvalue);
            tmpvalue = (Math.round((projectionData[y].getUpperBand2()/dividingFactor) * 100.0))/100.0;
            temvalueUpper2 = tmpvalue;

            upper2.set(year.toString(), tmpvalue);
            // If incrementing anything other then 1, then make sure that last year is displayed.
            if (y+1 >= totalYlabels) // If last point is plotted, then quit.
            {
               Integer lastpoint = totalYlabels - 1;
               avgPerformance = (int) ((Math.round((projectionData[lastpoint].getTotalCapitalWithGains()/dividingFactor) * 100.0))/100.0);
               poorPerformance = (int) ((Math.round((projectionData[lastpoint].getLowerBand2()/dividingFactor) * 100.0))/100.0);
            }
            lstLower.add(temvalueLower1);
            lstLower.add(temvalueUpper1);
            lstUpper.add(temvalueLower2);
            lstUpper.add(temvalueUpper2);


            goalYearValue.add(year);
            goalAgeSeries.add(ageSeries);
            goalLowerValue.add(lstLower);
            goalUpperValue.add(lstUpper);
            goalAvgValue.add((int)Math.floor(temValueAvg));

            y += yIncrement;
         }

         Integer digits = maxGrowth.toString().length();
         Double scale = Math.pow(10, digits - 1);

         maxGrowth = (int) ((Math.ceil(maxGrowth.doubleValue() / scale)) * scale);
         maxGraghPlot = (int)((Math.round((projectionDataAggressive[totalYlabels - 1].getUpperBand2()/dividingFactor) * 100.0))/100.0);

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

   public void createPieModel(AssetClass[] assetclasses, Integer offset)
   {
      String color;
      pieChart = new PieChartModel();
      try {
         if (assetclasses == null)
            return;

         if (assetclasses != null && assetclasses.length >= offset) {
            AssetClass assetdata = assetclasses[offset];

            Calendar cal = Calendar.getInstance();
            calendarYear = cal.get(cal.YEAR);
            int slice = 0;
            String pieseriesColors = "";
            for (String assetname : assetdata.getOrderedAsset())
            {
               Asset asset = assetdata.getAsset(assetname);
               Double weight = asset.getActualweight();
               String label = assetname + " - " + jutil.displayFormat(weight, "##0.##%");
               weight = weight * 100;
               pieChart.set(label, weight);
               color = asset.getColor().replace('#',' ');
               color = color.trim();
               if (slice == 0)
                  pieseriesColors = color;
               else
                  pieseriesColors = pieseriesColors + "," + color;
               slice ++;
            }
            pieChart.setFill(true);
            pieChart.setShowDataLabels(false);
            pieChart.setDiameter(150);
            pieChart.setSeriesColors(pieseriesColors);
            pieChart.setExtender("pie_extensions");
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
         pieChart = null;
      }
   }

   public void createBarChart(AssetClass[] assetclasses, Integer offset)
   {
      String color;
      if (assetclasses == null)
         return;
      if (assetclasses.length <= 0)
         return;

      barChart = new BarChartModel();
      try {
         String pieseriesColors = "";
         Integer maxAllocated = 0;
         if (assetclasses != null && assetclasses.length >= offset) {
            AssetClass assetdata = assetclasses[offset];
            Calendar cal = Calendar.getInstance();
            calendarYear = cal.get(cal.YEAR);
            ChartSeries[] series = new ChartSeries[assetdata.getOrderedAsset().size()];
            for (int i = 0; i < assetdata.getOrderedAsset().size(); i++)
            {
               String assetname = assetdata.getOrderedAsset().get(i);
               Asset asset = assetdata.getAsset(assetname);
               series[i] = new ChartSeries();
               series[i].setLabel(assetname);
               //Double weight = asset.getActualweight() * 100;
               Double money = asset.getValue();
               series[i].set(calendarYear, money);
               //maxAllocated = (maxAllocated < weight.intValue()) ? weight.intValue() + 5 : maxAllocated;
               maxAllocated = (maxAllocated < money.intValue()) ? money.intValue() + 1000 : maxAllocated;
               color = asset.getColor().replace('#', ' ');
               color = color.trim();
               if (i == 0)
               {
                  pieseriesColors = color.trim();
               }
               else
               {
                  pieseriesColors = pieseriesColors + "," + color;
               }
               barChart.addSeries(series[i]);
            }
         }
         barChart.setSeriesColors(pieseriesColors);
         barChart.setExtender("bar_extensions");
         //barChart.setLegendPosition("ne");
         //barChart.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
         barChart.setMouseoverHighlight(false);
         barChart.setShowDatatip(false);
         barChart.setShowPointLabels(true);
         Axis xAxis = barChart.getAxis(AxisType.X);
         // xAxis.setLabel("Assets");
         // xAxis.setTickAngle(30);
         // xAxis.setTickFormat();

         Axis yAxis = barChart.getAxis(AxisType.Y);
         // yAxis.setLabel("Allocated");
         yAxis.setMin(0);
         yAxis.setTickFormat("$%'d");
      }
      catch (Exception ex) {
         ex.printStackTrace();
         barChart = null;
      }
   }

   public void createRiskBarChart(Integer selected)
   {
      String color;
      try
      {
         riskbarChart = new BarChartModel();
         BarChartSeries gain = new BarChartSeries();
         BarChartSeries loss = new BarChartSeries();
         BarChartSeries sgain = new BarChartSeries();
         BarChartSeries sloss = new BarChartSeries();
         String label;
         Integer gainval, lossval;
         for (int i=1; i < 6; i++)
         {
            label = null;
            gainval = null;
            lossval = null;
            switch (i) {
               case 1:
                  label = "Conservative";
                  gainval = 14;
                  lossval = -4;
                  break;
               case 2:
                  label = "Moderately Conservative";
                  gainval = 22;
                  lossval = -10;
                  gain.set(label, 22);
                  loss.set(label,-10);
                  break;
               case 3:
                  label = "Moderate";
                  gainval = 29;
                  lossval = -16;
                  break;
               case 4:
                  label = "Moderately Aggressive";
                  gainval = 35;
                  lossval = -23;
                  break;
               case 5:
                  label = "Aggressive";
                  gainval = 45;
                  lossval = -34;
                  break;
            }

            // Selected start from 1 to 5 (i loop is also from 1 to 5
            if (selected == i) {
               sgain.set(label, gainval);
               sloss.set(label, lossval);
            }
            else {
               gain.set(label, gainval);
               loss.set(label, lossval);

            }

         }
         riskbarChart.addSeries(gain);
         riskbarChart.addSeries(loss);
         riskbarChart.addSeries(sgain);
         riskbarChart.addSeries(sloss);
         riskbarChart.setShowPointLabels(true);
         riskbarChart.setStacked(true);
         riskbarChart.setMouseoverHighlight(false);
         riskbarChart.setShowDatatip(false);
         riskbarChart.setSeriesColors("00D404,00D404,0000FF,0000FF");
         riskbarChart.setNegativeSeriesColors("00D404,D40000,FF0000,FF0000");
         Axis yAxis = riskbarChart.getAxis(AxisType.Y);
         yAxis.setTickFormat("%#.0f%%");

         // riskbarChart.setExtender("riskq");

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }


   public void resetCharts() {
      pieChart = null;
      lineChart = null;
      meterGuage = null;
      barChart = null;
      goalChart = null;

   }


}
