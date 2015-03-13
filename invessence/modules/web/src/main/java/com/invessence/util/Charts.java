package com.invessence.util;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invmodel.asset.data.*;
import com.invmodel.portfolio.data.Portfolio;
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
   private String pieseriesColorStr;
   private String[] pieseriesColors;
   private Integer calendarYear, minYearPoint, maxYearPoint, minGrowth, maxGrowth,legendXrotation;

   private CartesianChartModel lineChart = null;
   private PieChartModel pieChart = null;

   public CartesianChartModel getLineChart()
   {
      return lineChart;
   }

   public PieChartModel getPieChart()
   {
      return pieChart;
   }

   public String getPieseriesColorStr()
   {
      return pieseriesColorStr;
   }

   public String[] getPieseriesColors()
   {
      return pieseriesColors;
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

   public void createLineModel(Portfolio[] portfolio, Integer displayYears)
   {
      Integer year;
      Integer noOfYlabels = 0;
      Integer totalYlabels = 0;
      Integer yIncrement = 1;
      Integer MAXPOINTONGRAPH = 30;
      Long moneyInvested;
      Long moneyPnL;
      Double dividingFactor = 1.0;

      lineChart = new CartesianChartModel();
      try
      {
         if (portfolio == null)
            return;

         if (portfolio.length < 2)
            return;


         ChartSeries totalGrowth = new ChartSeries();
         ChartSeries totalInvested = new ChartSeries();
         ChartSeries lower = new ChartSeries();
         ChartSeries upper = new ChartSeries();

         //growth.setLabel("Growth");
         totalGrowth.setLabel("Growth");
         totalInvested.setLabel("Invested");
         lower.setLabel("Lower");
         upper.setLabel("Upper");
         yIncrement = (int) (((double) portfolio.length) / ((double) MAXPOINTONGRAPH));
         yIncrement = yIncrement + 1;  // offset by 1
         noOfYlabels = (int) (((double) portfolio.length) / ((double) yIncrement)) % MAXPOINTONGRAPH;
         // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
         noOfYlabels = (noOfYlabels == 0) ? portfolio.length : noOfYlabels;
         if (noOfYlabels <= 10)
         {
            legendXrotation = 0;
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
         totalYlabels = portfolio.length - 1;
         Calendar cal = Calendar.getInstance();
         calendarYear = cal.get(cal.YEAR);
         minYearPoint = calendarYear;
         maxYearPoint = minYearPoint + totalYlabels;
         minGrowth = (int) portfolio[0].getActualInvestments() - 5000;
         maxGrowth = 0;
         while (y <= totalYlabels)
         {
            year = calendarYear + y;
            moneyInvested = Math.round(portfolio[y].getActualInvestments() / dividingFactor);
            moneyPnL = Math.round(portfolio[y].getTotalMoney() / dividingFactor);
            // System.out.println("Year:" + year.toString() + ", Value=" + yearlyGrowthData[y][2]);
            maxGrowth = (maxGrowth > (int) (portfolio[y].getUpperTotalMoney()/dividingFactor)) ? maxGrowth : (int)(portfolio[y].getUpperTotalMoney()/dividingFactor);
            // growth.set(year, portfolio[y].getTotalCapitalGrowth());
            totalGrowth.set(year.toString(), moneyPnL);
            totalInvested.set(year.toString(), moneyInvested);
            // Double lowerMoney = (portfolio[y].getLowerTotalMoney() < moneyInvested) ? moneyInvested : portfolio[y].getLowerTotalMoney();
            Double lowerMoney = portfolio[y].getLowerTotalMoney();
            lower.set(year.toString(),lowerMoney/dividingFactor);
            upper.set(year.toString(),portfolio[y].getUpperTotalMoney()/dividingFactor);
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
         //lineModel.addSeries(growth);
         lineChart.addSeries(totalGrowth);
         lineChart.addSeries(totalInvested);
         // lineChart.addSeries(lower);
         // lineChart.addSeries(upper);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

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
         pieseriesColors = new String[assetInfo.size()];
         for (int i = 0; i < assetInfo.size(); i++)
         {
            Asset asset = assetInfo.get(i);
            String assetname = asset.getAsset();
            Double weight = asset.getActualweight();
            String label = assetname + " - " + jutil.displayFormat(weight, "##0.##%");
            pieChart.set(label, weight);
            color = asset.getColor().replace('#', ' ');
            color.trim();
            pieseriesColors[i] = color;
            if (i == 0)
            {
               pieseriesColorStr = color;
            }
            else
            {
               pieseriesColorStr = pieseriesColorStr + ", " + color;
            }
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

      return;
   }

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
            minYearPoint = calendarYear;
            maxYearPoint = minYearPoint;
            int slice = 0;
            pieseriesColors = new String[assetdata.getOrderedAsset().size()];
            for (String assetname : assetdata.getOrderedAsset())
            {
               Asset asset = assetdata.getAsset(assetname);
               Double weight = asset.getActualweight();
               String label = assetname + " - " + jutil.displayFormat(weight, "##0.##%");
               pieChart.set(label, weight);
               color = asset.getColor().replace('#',' ');
               color.trim();
               pieseriesColors[slice] = color;
               if (slice == 0)
               {
                  pieseriesColorStr = color;
               }
               else
               {
                  pieseriesColorStr = pieseriesColorStr + ", " + color;
               }
               slice ++;
            }
         }

      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

}
