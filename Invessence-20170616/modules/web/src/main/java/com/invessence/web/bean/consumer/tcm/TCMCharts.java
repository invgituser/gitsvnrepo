package com.invessence.web.bean.consumer.tcm;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invessence.web.bean.consumer.InvessenceCharts;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.data.common.*;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.GoalsData;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.data.Portfolio;
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
   private ChartData chartdata;
   private GenerateHighChartsData highCharts = new GenerateHighChartsData();

   public ChartData getChartdata()
   {
      return chartdata;
   }

   public String DonutPieChart(Portfolio[] portfolioData, AssetClass[] assetData, Map<String, String> webInfo){
      String chartJSON = "";
      if(webInfo.get("CHART.ASSET.ALLOCATION") != null &&
         webInfo.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("HIGHCHART.2DDONUT")){
         chartJSON =  highCharts.create2DDONUTChart(new ArrayList<Asset>(assetData[0].getAssetclass().values()));
      }
      return chartJSON;
   }

   // New method implements : Projection chart creation by using HighChart
   public void createProjectionHighChart(ProjectionData[] projectionData, Integer horizon, Integer currAge, Integer ageSeries, ProjectionData[] projectionDataAggressive)
   {
      chartdata = highCharts.createProjectionHighChart(projectionData,
                                                       horizon,
                                                       currAge,
                                                       ageSeries,
                                                       projectionDataAggressive);
   }
}
