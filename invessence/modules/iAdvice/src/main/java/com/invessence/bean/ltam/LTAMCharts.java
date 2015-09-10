package com.invessence.bean.ltam;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.ltammodel.data.*;
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

   private PieChartModel pieChart;
   private DonutChartModel donutChart;
   private BarChartModel barPerformanceChart;
   private BarChartModel riskbarChart;


   public LTAMCharts()
   {
      pieChart = null;
      donutChart = null;
      barPerformanceChart = null;
      riskbarChart = null;
   }

   public PieChartModel getPieChart()
   {
      return pieChart;
   }

   public DonutChartModel getDonutChart()
   {
      return donutChart;
   }

   public BarChartModel getBarPerformanceChart()
   {
      return barPerformanceChart;
   }

   public BarChartModel getRiskbarChart()
   {
      return riskbarChart;
   }

   public void createBarPerformance(Map<String, ArrayList<LTAMPerformance>> performancedata)
   {
      try
      {
         barPerformanceChart = null;
         if (performancedata == null)
         {
            return;
         }

         if (performancedata.size() == 0)
         {
            return;
         }

         Integer calendarYear, firstPoint, lastPoint, maxPoints = 0;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         firstPoint = calendarYear;
         barPerformanceChart = new BarChartModel();
         String color = "";
         String tempcolor;
         int indexnum = 0;
         BarChartSeries barseries = null;
         LineChartSeries lineseries = null;
         Boolean barChartFlag = true;
         // Since Bar prints in reverse order..  We are going to reverse the data.
         ArrayList<String> performancekey = new ArrayList<String>();
/*
         for (String index : performancedata.keySet()) {
            performancekey.add(0,index); // Since we are inserting in first position, the prior one will shift up.
         }

         for (String index : performancekey)
*/
         for (String index : performancedata.keySet())
         {
            int datanum = 0;
            maxPoints = (maxPoints < performancedata.get(index).size()) ? performancedata.get(index).size() : maxPoints;
            lastPoint = firstPoint;
            for (LTAMPerformance performance : performancedata.get(index))
            {

               if (datanum == 0)
               {
                  tempcolor = performance.getColor();
                  if (indexnum == 0)
                  {
                     color = tempcolor.replace('#', ' ').trim();
                  }
                  else
                  {
                     color = color + "," + tempcolor.replace('#', ' ').trim();
                  }

                  color = color.trim();
               }
               if (performance.getPrimary().toUpperCase().equals("P"))
               {
                  if (datanum == 0)
                  {
                     barChartFlag = false;
                     lineseries = new LineChartSeries();
                     lineseries.setLabel(performance.getIndexname());
                  }
                  lineseries.set(performance.getYearname(), performance.getPerformance());
               }
               else
               {
                  if (datanum == 0)
                  {
                     barseries = new BarChartSeries();
                     barChartFlag = true;
                     barseries.setLabel(performance.getIndexname());
                  }
                  barseries.set(performance.getYearname(), performance.getPerformance());
               }
               datanum++;
            }
            lastPoint--;  //  Decrease years...
            if ((! barChartFlag) && (lineseries != null))
            {
               barPerformanceChart.addSeries(lineseries);
            }
            if (barChartFlag && (barseries != null))
            {
               barPerformanceChart.addSeries(barseries);
            }

            indexnum++;
         }

         barPerformanceChart.setSeriesColors(color);
         barPerformanceChart.setNegativeSeriesColors(color);
         // barPerformanceChart.setShowPointLabels(false);
         // barPerformanceChart.setMouseoverHighlight(false);
         // barPerformanceChart.setShowDatatip(false);
         //barPerformanceChart.setLegendPlacement(LegendPlacement.OUTSIDE);
         //barPerformanceChart.setLegendPosition("ne");

         //Axis xAxis = barPerformanceChart.getAxis(AxisType.X);
         //xAxis.setLabel("Period");
         //xAxis.setMin(firstPoint);

         //Axis yAxis = lineChart.getAxis(AxisType.Y);
         //yAxis.setTickFormat("%d%");
         barPerformanceChart.setExtender("ltam_perf");
      }

      catch (
         Exception ex
         )

      {
         ex.printStackTrace();
      }

   }

   public void createPieModel(Map assetdata)
   {
      String color;
      pieChart = new PieChartModel();
      try
      {
         if (assetdata == null)
         {
            return;
         }

         if (assetdata != null && assetdata.size() >= 0)
         {
            Map<String, LTAMAsset> assetMap = (Map<String, LTAMAsset>) assetdata;

            int slice = 0;
            String pieseriesColors = "";
            for (String assetname : assetMap.keySet())
            {
               LTAMAsset asset = assetMap.get(assetname);
               Double weightAsPercent = asset.getWeightAsPercent();
               Double displayWeight = asset.getWeight();
               String label = asset.getDisplayname();
               pieChart.set(label, displayWeight);
               pieChart.setDataFormat(asset.getDisplayname());
               color = asset.getColor().replace('#', ' ');
               color = color.trim();
               if (slice == 0)
               {
                  pieseriesColors = color;
               }
               else
               {
                  pieseriesColors = pieseriesColors + "," + color;
               }
               slice++;
            }
            pieChart.setFill(true);
            //pieChart.setShowDataLabels(true);
            pieChart.setDiameter(125);
            pieChart.setSliceMargin(2);
            pieChart.setShadow(false);
            pieChart.setSeriesColors(pieseriesColors);
            pieChart.setExtender("ltam_pie");
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void createDonutChart(Map assetdata)
   {
      String color;
      donutChart = new DonutChartModel();
      try
      {
         if (assetdata == null)
         {
            return;
         }

         if (assetdata != null && assetdata.size() >= 0)
         {
            Map<String, LTAMAsset> assetMap = (Map<String, LTAMAsset>) assetdata;

            int slice = 0;
            String pieseriesColors = "";
            Map<String, Number> circle = new LinkedHashMap<String, Number>();
            for (String assetname : assetMap.keySet())
            {
               LTAMAsset asset = assetMap.get(assetname);
               Double weightAsPercent = asset.getWeightAsPercent();
               Double displayWeight = asset.getWeight() * 100;
               String label = asset.getDisplayname();
               circle.put(label, displayWeight);
               color = asset.getColor().replace('#', ' ');
               color = color.trim();
               if (slice == 0)
               {
                  pieseriesColors = color;
               }
               else
               {
                  pieseriesColors = pieseriesColors + "," + color;
               }
               slice++;
            }
            donutChart.addCircle(circle);
            donutChart.setFill(true);
            // pieChart.setShowDataLabels(true);
            donutChart.setSliceMargin(2);
            // donutChart.setMouseoverHighlight(true);
            // donutChart.setShadow(false);
            donutChart.setSeriesColors(pieseriesColors);
            donutChart.setExtender("ltam_donut");
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void createRiskBarChart(ArrayList<LTAMTheme> themedata)
   {
      String color;
      if (themedata == null)
      {
         return;
      }
      if (themedata.size() <= 0)
      {
         return;
      }

      try
      {
         String barseriesColors = "";
         Integer maxAllocated = 0;
         Integer minAllocated = 0;
         if (themedata != null && themedata.size() >= 0)
         {
            riskbarChart = new BarChartModel();
            Calendar cal = Calendar.getInstance();
            Integer calendarYear = cal.get(cal.YEAR);
            BarChartSeries gain = new BarChartSeries();
            BarChartSeries loss = new BarChartSeries();
            int i = 0;
            Double weight;
            for (LTAMTheme theme : themedata)
            {    // For every theme, there is gain and loss, so show both graphs.
               gain.set(theme.getDisplayname(), theme.getGain());
               loss.set(theme.getDisplayname(),theme.getLoss());
            }
            riskbarChart.addSeries(gain);
            riskbarChart.addSeries(loss);
            // riskbarChart.setStacked(true);
            // riskbarChart.setMouseoverHighlight(false);
            //riskbarChart.setShowDatatip(true);
            riskbarChart.setSeriesColors("00D404,D40000");
            riskbarChart.setNegativeSeriesColors("00D404,D40000");

            riskbarChart.setExtender("ltam_riskq3");
            //riskbarChart.setLegendPlacement(LegendPlacement.OUTSIDE);
            //riskbarChart.setLegendPosition("ne");

            Axis yAxis = riskbarChart.getAxis(AxisType.Y);
            yAxis.setTickFormat("%#.2f%%");
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

}
