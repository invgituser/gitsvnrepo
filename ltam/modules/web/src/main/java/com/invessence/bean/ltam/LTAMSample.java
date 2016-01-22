package com.invessence.bean.ltam;

import java.io.Serializable;

import javax.faces.bean.*;

import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "sample")
@SessionScoped
public class LTAMSample implements Serializable
{
   private BarChartModel riskbarChart;


   public LTAMSample()
   {
      riskbarChart = buildRiskBarChart();
   }

   public BarChartModel getRiskbarChart()
   {
      return riskbarChart;
   }

   public BarChartModel buildRiskBarChart()
   {
      try
      {
         riskbarChart = new BarChartModel();
         BarChartSeries gain;
         BarChartSeries loss;
         gain = new BarChartSeries();
         loss = new BarChartSeries();
         gain.set("2014", 10);
         loss.set("2014", -5);
         gain.set("2015", 20.22);
         loss.set("2015", -10.10);
         riskbarChart.addSeries(gain);
         riskbarChart.addSeries(loss);
         riskbarChart.setShowPointLabels(true);
         // riskbarChart.setStacked(true);
         // riskbarChart.setMouseoverHighlight(false);
         //riskbarChart.setShowDatatip(true);
         riskbarChart.setSeriesColors("00006A,00D403");
         riskbarChart.setNegativeSeriesColors("D0006A,D0D403");

         riskbarChart.setExtender("riskq3");

         Axis yAxis = riskbarChart.getAxis(AxisType.Y);
         yAxis.setTickFormat("%#.2f%%");
         return riskbarChart;
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return null;
   }

}
