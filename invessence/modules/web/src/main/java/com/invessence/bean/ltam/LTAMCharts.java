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

   private LineChartModel lineChart;
   private PieChartModel pieChart;
   private MeterGaugeChartModel meterGuage;
   private BarChartModel barChart;
   private BarChartModel barPerformanceChart;
   private BarChartModel riskbarChart;

   public LTAMCharts()
   {
      barPerformanceChart = new BarChartModel();
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

   public MeterGaugeChartModel getMeterGuage()
   {
      return meterGuage;
   }

   public BarChartModel getBarChart()
   {
      return barChart;
   }

   public BarChartModel getBarPerformanceChart()
   {
      return barPerformanceChart;
   }

   public void setMeterGuage(Integer pointer)
   {
      if (meterGuage == null)
         createDefaultMeterGuage();

      if (pointer < 0)
         pointer = 0;
      else if (pointer > 100)
         pointer = 100;

      meterGuage.setValue(pointer);
   }

   public void createDefaultMeterGuage() {
      List<Number> intervals = new ArrayList<Number>(){{
         add(25);
         add(42);
         add(59);
         add(76);
         add(100);
      }};
      meterGuage = new MeterGaugeChartModel(57, intervals);
      //meterGuage.setTitle("Risk");
      meterGuage.setSeriesColors("006699,FFCC00,990000,FFFFFF,000000");
      meterGuage.setShowTickLabels(false);
      //meterGuage.setLabelHeightAdjust(0);
      meterGuage.setIntervalOuterRadius(20);
   }

   public void createLineModel(Map<String, ArrayList<LTAMPerformance>> performancedata)
   {
      try
      {
         lineChart = null;
         if (performancedata == null)
            return;

         if (performancedata.size() == 0)
            return;

         Integer calendarYear, firstPoint, lastPoint = 0, maxPoints = 0;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         firstPoint = calendarYear;
         lineChart = new LineChartModel();
         LineChartSeries series = null;
         String [] seriescolor= {"#6E70FF","#82FF69","#FF4F4F","#FFBD5B","#88E9FF"};
         String color = "";
         String tempcolor;
         int indexnum = 0;
         for (String index: performancedata.keySet()) {
            int datanum = 0;
            maxPoints = (maxPoints < performancedata.get(index).size()) ? performancedata.get(index).size(): maxPoints;
            lastPoint =  firstPoint;
            for (LTAMPerformance performance: performancedata.get(index)) {
               if (indexnum > seriescolor.length)
                  tempcolor="FFFFFF";
               else
                  tempcolor=seriescolor[indexnum];

               if (datanum == 0) {
                  series = new LineChartSeries();
                  series.setLabel(performance.getIndexname());
                  if (indexnum == 0)
                     color = tempcolor.replace('#', ' ').trim();
                  else
                     color = color + "," + tempcolor.replace('#', ' ').trim();

                  color = color.trim();
               }
               if (series != null) {
                  series.set(lastPoint, performance.getPerformance());
               }
               datanum++;
               lastPoint--;  //  Decrease years...
            }
            if (series != null)  {
               lineChart.addSeries(series);
            }

            indexnum++;
         }

         lineChart.setSeriesColors(color);
         lineChart.setShowPointLabels(true);
         lineChart.setMouseoverHighlight(false);
         // lineChart.setShowDatatip(false);
         lineChart.setLegendPlacement(LegendPlacement.INSIDE);
         lineChart.setLegendPosition("ne");

         Axis xAxis = lineChart.getAxis(AxisType.X);
         xAxis.setLabel("Period");
         xAxis.setMin(lastPoint);

         Axis yAxis = lineChart.getAxis(AxisType.Y);
         //yAxis.setTickFormat("%d%");
         lineChart.setExtender("ltam_line");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         lineChart = null;
      }
   }

   public void createBarPerformance(Map<String, ArrayList<LTAMPerformance>> performancedata)
   {
      try
      {
         barPerformanceChart = null;
         if (performancedata == null)
            return;

         if (performancedata.size() == 0)
            return;

         Integer calendarYear, firstPoint, lastPoint, maxPoints = 0;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         firstPoint = calendarYear;
         barPerformanceChart = new BarChartModel();
         ChartSeries series = null;
         String color = "";
         String tempcolor;
         int indexnum = 0;
         for (String index: performancedata.keySet()) {
            int datanum = 0;
            maxPoints = (maxPoints < performancedata.get(index).size()) ? performancedata.get(index).size(): maxPoints;
            lastPoint =  firstPoint;
            for (LTAMPerformance performance: performancedata.get(index)) {

               if (datanum == 0) {
                  tempcolor=performance.getColor();
                  series = new ChartSeries();
                  if (indexnum == 0)
                     color = tempcolor.replace('#', ' ').trim();
                  else
                     color = color + "," + tempcolor.replace('#', ' ').trim();

                  color = color.trim();
               }
               if (series != null) {
                  series.setLabel(performance.getIndexname());
                  series.set(performance.getYearname(), performance.getPerformance());
               }
               datanum++;
               lastPoint--;  //  Decrease years...
            }
            if (series != null)  {
               barPerformanceChart.addSeries(series);
            }

            indexnum++;
         }

         barPerformanceChart.setSeriesColors(color);
         barPerformanceChart.setNegativeSeriesColors(color);
         barPerformanceChart.setShowPointLabels(false);
         barPerformanceChart.setMouseoverHighlight(false);
         barPerformanceChart.setShowDatatip(false);
         //barPerformanceChart.setLegendPlacement(LegendPlacement.OUTSIDE);
         //barPerformanceChart.setLegendPosition("ne");

         //Axis xAxis = barPerformanceChart.getAxis(AxisType.X);
         //xAxis.setLabel("Period");
         //xAxis.setMin(firstPoint);

         //Axis yAxis = lineChart.getAxis(AxisType.Y);
         //yAxis.setTickFormat("%d%");
         barPerformanceChart.setExtender("ltam_perf");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
         lineChart = null;
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

            int slice = 0;
            String pieseriesColors = "";
            for (String assetname : assetMap.keySet())
            {
               LTAMAsset asset = assetMap.get(assetname);
               Double weightAsPercent = asset.getWeightAsPercent();
               Double displayWeight = asset.getWeight();
               String label = asset.getDisplayname() + " - " + jutil.displayFormat(weightAsPercent, "##0.##%");
               pieChart.set(label, displayWeight);
               pieChart.setDataFormat(asset.getDisplayname());
               color = asset.getColor().replace('#',' ');
               color = color.trim();
               if (slice == 0)
                  pieseriesColors = color;
               else
                  pieseriesColors = pieseriesColors + "," + color;
               slice ++;
            }
            pieChart.setFill(true);
            // pieChart.setShowDataLabels(true);
            pieChart.setDiameter(125);
            pieChart.setSliceMargin(2);
            pieChart.setMouseoverHighlight(true);
            pieChart.setShadow(false);
            pieChart.setSeriesColors(pieseriesColors);
            pieChart.setExtender("ltam_pie");
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
            Integer calendarYear = cal.get(cal.YEAR);
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
         // barChart.setShowPointLabels(true);
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
            Integer calendarYear = cal.get(cal.YEAR);
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
