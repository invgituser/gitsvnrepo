package com.invessence.web.bean.consumer;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.*;
import com.invmodel.performance.data.PerformanceData;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class Charts implements Serializable
{
   JavaUtil jutil = new JavaUtil();
   private Integer year;
   private Integer calendarYear, minYearPoint, maxYearPoint, minGrowth, maxGrowth,legendXrotation;

   private LineChartModel lineChart;
   private PieChartModel pieChart;
   private MeterGaugeChartModel meterGuage;
   private BarChartModel barChart;
   private LineChartModel goalChart;

   public Charts()
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
      this.meterGuage = new MeterGaugeChartModel(24, intervals);
      // this.meterGuage.setTitle("Risk");
      this.meterGuage.setSeriesColors("006699, FFCC00, 990000");
      this.meterGuage.setShowTickLabels(false);
      // this.meterGuage.setLabelHeightAdjust(-25);
      this.meterGuage.setIntervalOuterRadius(20);
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

   public void createGoalChart(PerformanceData[] performanceData, GoalsData goalData)
   {
      Integer year;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 30;
      Long moneyInvested;
      Long money;
      Double dividingFactor = 1.0;
      Boolean isthereagoal = (goalData != null) ? true: false;

      goalChart = new LineChartModel();
      try
      {
         if (performanceData == null)
            return;

         if (performanceData.length < 2)
            return;


         // LineChartSeries totalGrowth = new LineChartSeries();
         LineChartSeries totalInvested = new LineChartSeries();
         // LineChartSeries lower1 = new LineChartSeries();
         LineChartSeries lower2 = new LineChartSeries();
         // LineChartSeries upper1 = new LineChartSeries();
         LineChartSeries upper2 = new LineChartSeries();
         LineChartSeries goalline = new LineChartSeries();


         //growth.setLabel("Growth");
         // totalGrowth.setLabel("Growth");
         totalInvested.setLabel("Invested");
         // lower1.setLabel("Lower1");
         lower2.setLabel("Lower2");
         //upper1.setLabel("Upper1");
         upper2.setLabel("Upper2");
         goalline.setLabel("Goal");

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
            // totalGrowth.set(year.toString(), performanceData[y].getTotalCapitalWithGains()/dividingFactor);
            // totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            //lower1.set(year.toString(),performanceData[y].getLowerBand1()/dividingFactor);
            lower2.set(year.toString(),performanceData[y].getLowerBand2()/dividingFactor);
            //upper1.set(year.toString(),performanceData[y].getUpperBand1()/dividingFactor);
            upper2.set(year.toString(),performanceData[y].getUpperBand2()/dividingFactor);
            if (isthereagoal) {
               goalline.set(year.toString(), goalData.getGoalDesired());
            }
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
         //lineChart.addSeries(totalGrowth);
         // lineChart.addSeries(totalInvested);
         goalChart.addSeries(lower2);
         //lineChart.addSeries(lower1);
         //lineChart.addSeries(upper1);
         goalChart.addSeries(upper2);
         if (isthereagoal) {
            goalChart.addSeries(goalline);
            //goalChart.setSeriesColors("00FF00,7C8686,0000FF,0000FF,7C8686,FF0000");
            goalChart.setSeriesColors("009ABB,009ABB,BB2100");
         }
         else {
            //goalChart.setSeriesColors("00FF00,7C8686,0000FF,0000FF,7C8686");
            goalChart.setSeriesColors("009ABB,009ABB");
         }
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
      }
   }

   public void createGoalChart(GoalsData goalData)
   {
      Integer year;

      if (goalData == null) {
         goalChart = null;
      }

      Boolean isthereagoal = (goalData != null) ? true: false;

      goalChart = new LineChartModel();
      try
      {
         LineChartSeries lower = new LineChartSeries();
         LineChartSeries upper = new LineChartSeries();
         LineChartSeries goalpotential = new LineChartSeries();

         int y = 0;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + goalData.getTerm().intValue();
         Integer lowervalue =  (int) (goalData.getLowerGrowth().get(0) * .10);
         minGrowth =(int) ((goalData.getLowerGrowth().get(0) - lowervalue < 0.0) ? 0.0 : goalData.getLowerGrowth().get(0) - lowervalue);
         maxGrowth = 0;
         while (y < goalData.getTerm().intValue())
         {
            year = calendarYear + y;
            lower.set(year.toString(),goalData.getLowerGrowth().get(y));
            upper.set(year.toString(),goalData.getUpperGrowth().get(y));
            // If incrementing anything other then 1, then make sure that last year is displayed.
            goalpotential.set(year.toString(),goalData.getGoalDesired());

            y++;
         }

         Integer digits = maxGrowth.toString().length();
         Double scale = Math.pow(10, digits - 1);

         maxGrowth = (int) ((Math.ceil(maxGrowth.doubleValue() / scale)) * scale);
         goalChart.addSeries(lower);
         goalChart.addSeries(upper);
         goalChart.addSeries(goalpotential);
         goalChart.setSeriesColors("0000FF,0000FF,FF0000");

         goalChart.setShowPointLabels(true);
         goalChart.setMouseoverHighlight(false);
         goalChart.setShowDatatip(false);

         Axis xAxis = goalChart.getAxis(AxisType.X);
         xAxis.setLabel("Years");
         xAxis.setMin(calendarYear);
         xAxis.setMax(maxYearPoint);
         xAxis.setTickFormat("%d");

         Axis yAxis = goalChart.getAxis(AxisType.Y);
         yAxis.setTickFormat("$%'d");
         goalChart.setExtender("goals_extensions");
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

/*
   public void createPieModel(List<Asset> assetInfo)
   {
      String color;
      pieChart = new PieChartModel();
      try {
         if (assetInfo == null)
            return;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + assetInfo.size();
         String pieseriesColors = "";
         for (int i = 0; i < assetInfo.size(); i++)
         {
            Asset asset = assetInfo.get(i);
            String assetname = asset.getAsset();
            Double weight = asset.getActualweight();
            String label = assetname + " - " + jutil.displayFormat(weight, "##0.##%");
            pieChart.set(label, weight);
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
         }
         pieChart.setFill(true);
         pieChart.setShowDataLabels(false);
         pieChart.setDiameter(150);
         pieChart.setSeriesColors(pieseriesColors);
         pieChart.setExtender("pie_extensions");
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

      return;
   }
*/

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
