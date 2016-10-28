package com.invessence.web.bean.consumer.ltam;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import org.primefaces.model.chart.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

public class PositionCharts implements Serializable
{
   JavaUtil jutil = new JavaUtil();

   private PieChartModel summaryPieChart;

/*

   public PositionCharts()
   {
      summaryPieChart = null;
   }

   public PieChartModel getSummaryPieChart()
   {
      return summaryPieChart;
   }

   public void createSummaryPieModel(Map summaryData)
   {
      String color;
      summaryPieChart = new PieChartModel();
      try
      {
         if (summaryData == null)
         {
            return;
         }

         if (summaryData != null && summaryData.size() >= 0)
         {
            Map<String, PositionData> assetMap = (Map<String, PositionData>) summaryData;

            int slice = 0;
            String pieseriesColors = "";
            for (String assetname : assetMap.keySet())
            {
               PositionData data = assetMap.get(assetname);
               Double displayWeight = data.getAllocation();
               String label = data.getAssetname();
               summaryPieChart.set(label, displayWeight);
               summaryPieChart.setDataFormat(label);
               color = data.getColor().replace('#', ' ');
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
            summaryPieChart.setSeriesColors(pieseriesColors);
            summaryPieChart.setExtender("ltam_pos_pie");
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }
*/


}
