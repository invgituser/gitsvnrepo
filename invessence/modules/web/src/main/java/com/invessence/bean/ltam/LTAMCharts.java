package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invmodel.asset.data.*;
import com.invmodel.ltam.data.*;
import com.invmodel.performance.data.PerformanceData;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class LTAMCharts implements Serializable
{
   JavaUtil jutil = new JavaUtil();
   private Integer year;
   private Integer calendarYear, minYearPoint, maxYearPoint, minGrowth, maxGrowth,legendXrotation;

   private LineChartModel lineChart;
   private PieChartModel pieChart;
   private MeterGaugeChartModel meterGuage;
   private BarChartModel barChart;
   private BarChartModel riskbarChart;

   public LTAMCharts()
   {
      pieChart = new PieChartModel();
      lineChart = new LineChartModel();
      createDefaultMeterGuage();
      barChart = new BarChartModel();
      riskbarChart = new BarChartModel();
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

   public void setMeterGuage(Integer pointer)
   {
      if (meterGuage == null)
         createDefaultMeterGuage();
      this.meterGuage.setValue(pointer);
   }

   public void createDefaultMeterGuage() {
      List<Number> intervals = new ArrayList<Number>(){{
         add(16);
         add(32);
         add(50);
      }};
      meterGuage = new MeterGaugeChartModel(24, intervals);
      meterGuage.setTitle("Risk");
      meterGuage.setSeriesColors("006699, FFCC00, 990000");
      meterGuage.setShowTickLabels(false);
      // this.meterGuage.setLabelHeightAdjust(-25);
      meterGuage.setIntervalOuterRadius(20);
   }

   public void createLineModel(PerformanceData[] performanceData)
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
         if (performanceData == null)
            return;

         if (performanceData.length < 2)
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

         yIncrement = (int) (((double) performanceData.length) / ((double) MAXPOINTONGRAPH));
         yIncrement = yIncrement + 1;  // offset by 1
         noOfYlabels = (int) (((double) performanceData.length) / ((double) yIncrement)) % MAXPOINTONGRAPH;
         // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
         noOfYlabels = (noOfYlabels == 0) ? performanceData.length : noOfYlabels;
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
         totalYlabels = performanceData.length - 1;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + totalYlabels;
         Integer lowervalue =  (int) ((double) performanceData[0].getLowerBand2() * .10);
         minGrowth = ((int) performanceData[0].getLowerBand2() - lowervalue < 0) ? 0 : (int) performanceData[0].getLowerBand2() - lowervalue;
         maxGrowth = 0;
         while (y <= totalYlabels)
         {
            year = calendarYear + y;
            // moneyInvested = Math.round(performanceData[y].getInvestedCapital() / dividingFactor);
            money = Math.round(performanceData[y].getUpperBand2() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > money.intValue()) ? maxGrowth : money.intValue();
            // growth.set(year, portfolio[y].getTotalCapitalGrowth());
            // totalGrowth.set(year.toString(), moneyPnL);
            // totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            lower1.set(year.toString(),performanceData[y].getLowerBand1()/dividingFactor);
            lower2.set(year.toString(),performanceData[y].getLowerBand2()/dividingFactor);
            upper1.set(year.toString(),performanceData[y].getUpperBand1()/dividingFactor);
            upper2.set(year.toString(),performanceData[y].getUpperBand2()/dividingFactor);
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


   public void createPieModel(Map assetdata)
   {
      String color;
      pieChart = new PieChartModel();
      try {
         if (assetdata == null)
            return;

         if (assetdata != null && assetdata.size() >= 0) {
            Map<String, LTAMAsset> assetMap = (Map<String, LTAMAsset>) assetdata;

            Calendar cal = Calendar.getInstance();
            calendarYear = cal.get(cal.YEAR);
            int slice = 0;
            String pieseriesColors = "";
            for (String assetname : assetMap.keySet())
            {
               LTAMAsset asset = assetMap.get(assetname);
               Double weightAsPercent = asset.getWeightAsPercent();
               Double displayWeight = asset.getWeight();
               String label = assetname + " - " + jutil.displayFormat(weightAsPercent, "##0.##%");
               pieChart.set(label, displayWeight);
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
            // pieChart.setExtender("ltam_pie");
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void createBarChart(Map assetdata, Double moneyInvested)
   {
      String color;
      if (assetdata == null)
         return;
      if (assetdata.size() <= 0)
         return;

      barChart = new BarChartModel();
      try {
         String barseriesColors = "";
         Integer maxAllocated = 0;
         if (assetdata != null && assetdata.size() >= 0) {
            Map<String, LTAMAsset> assetMap = (Map<String, LTAMAsset>) assetdata;
            Calendar cal = Calendar.getInstance();
            calendarYear = cal.get(cal.YEAR);
            ChartSeries[] series = new ChartSeries[assetdata.size()];
            int i = 0;
            for (String assetname: assetMap.keySet())
            {
               LTAMAsset asset = assetMap.get(assetname);
               series[i] = new ChartSeries();
               series[i].setLabel(assetname);
               Double weightAsPercent = asset.getWeightAsPercent();
               // Double displayWeight = asset.getWeight();
               Double money = Math.round(moneyInvested * weightAsPercent)/100.0;
               series[i].set(calendarYear, money);
               //maxAllocated = (maxAllocated < weight.intValue()) ? weight.intValue() + 5 : maxAllocated;
               maxAllocated = (maxAllocated < money.intValue()) ? money.intValue() + 1000 : maxAllocated;
               color = asset.getColor().replace('#', ' ');
               color = color.trim();
               if (i == 0)
               {
                  barseriesColors = color.trim();
               }
               else
               {
                  barseriesColors = barseriesColors + "," + color;
               }
               barChart.addSeries(series[i]);
               i++;
            }
         }
         barChart.setSeriesColors(barseriesColors);
         barChart.setExtender("ltam_bar");
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
      }
   }

   public void createRiskBarChart(ArrayList<LTAMTheme> themedata)
   {
      String color;
      if (themedata == null)
         return;
      if (themedata.size() <= 0)
         return;

      riskbarChart = new BarChartModel();
      try {
         String barseriesColors = "";
         Integer maxAllocated = 0;
         Integer minAllocated = 0;
         if (themedata != null && themedata.size() >= 0) {
            Calendar cal = Calendar.getInstance();
            calendarYear = cal.get(cal.YEAR);
            ChartSeries[] series = new ChartSeries[themedata.size()];
            int i = 0;
            Double weight;
            for (LTAMTheme theme: themedata)
            {    // For every theme, there is gain and loss, so show both graphs.
               for (int j=0; j<2 ; j++) {
                  switch (j) {
                     case 0:
                        weight = theme.getGain();
                        color = "009ABB";
                        break;
                     case 1:
                        weight = theme.getLoss();
                        color = "FFFFCC";
                        break;
                     default:
                        weight = 0.0;
                        color = "FFFFFF";

                  }
                  series[i] = new ChartSeries();
                  series[i].setLabel(theme.getDisplayname());
                  series[i].set(theme.getTheme(), weight);
                  maxAllocated = (maxAllocated < weight.intValue()) ? weight.intValue() + 5 : maxAllocated;
                  color = color.trim();
                  if (i == 0 && j == 0)
                  {
                     barseriesColors = color.trim();
                  }
                  else
                  {
                     barseriesColors = barseriesColors + "," + color;
                  }
                  barChart.addSeries(series[i]);
               }
               i++;
            }
         }
         barChart.setSeriesColors(barseriesColors);
         barChart.setStacked(true);
         barChart.setExtender("ltam_bar");
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
         yAxis.setTickFormat("%d");
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}
