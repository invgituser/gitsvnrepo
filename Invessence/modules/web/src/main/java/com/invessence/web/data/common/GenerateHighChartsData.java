package com.invessence.web.data.common;

import java.util.*;
import javax.faces.bean.*;

import com.google.api.client.util.ArrayMap;
import com.google.gson.Gson;
import com.invessence.web.controller.HighChartsController;
import com.invessence.web.data.DataPortfolio;
import com.invmodel.asset.data.Asset;
import com.invmodel.performance.data.ProjectionData;
import org.primefaces.model.chart.*;

/**
 * Created by Akhilesh on 2/20/2017.
 */

@ManagedBean(name = "generateHighChartsData")
@SessionScoped
public class GenerateHighChartsData
{

   ArrayList<Map> consolidateAssetAndSubAssetList;
   private ChartData chartdata;

   public ArrayList<Map> getConsolidateAssetAndSubAssetList()
   {
      return consolidateAssetAndSubAssetList;
   }

   public void setConsolidateAssetAndSubAssetList(ArrayList<Map> consolidateAssetAndSubAssetList)
   {
      this.consolidateAssetAndSubAssetList = consolidateAssetAndSubAssetList;
   }

   /**
    * this method is for generate arraylist for donut SingleLAYER
    *
    * @param assetList list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChart(ArrayList<Asset> assetList)
   {
      ArrayList<Map> list = new ArrayList();

      for (Asset stringArrayListOne : assetList)
      {
         ArrayMap<String, Object> map = new ArrayMap<String, Object>();
         map.put("name", stringArrayListOne.getAsset());
         map.put("y", new Double(stringArrayListOne.getActualweight() * 100));
         map.put("drilldown", stringArrayListOne.getAsset());
         map.put("color", stringArrayListOne.getColor());
         map.put("amount", (int) stringArrayListOne.getValue());
         list.add(map);
      }

      return new Gson().toJson(list);
      //return list;
   }

   /**
    * this method is for generate arraylist for piechart DoubleLAYER
    *
    * @param assetList list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChart2Layer(ArrayList<Asset> assetList)
   {
      ArrayList<Map> list = new ArrayList();
      ArrayMap<String, Object> map = new ArrayMap<String, Object>();
      map.put("categories", getAssetData(assetList));
      list.add(map);
      return new Gson().toJson(list);
   }

   /**
    * this method is for generate arraylist for donutchart DoubleLAYER
    *
    * @param subAssetlist list Of asset
    * @return ArrayList<map>
    */
   public String create2DDONUTChartForDetailLayer(List<DataPortfolio> subAssetlist)
   {
      ArrayList<Map> list = new ArrayList();
      for (DataPortfolio stringArrayListOne : subAssetlist)
      {
         ArrayMap<String, Object> map = new ArrayMap<String, Object>();
         map.put("y", getAssetTotalWeight(stringArrayListOne.getAssetType(), subAssetlist));
         map.put("color", stringArrayListOne.getColor());
         map.put("drilldown", getdrilldownFor2dDonut(stringArrayListOne.getAssetType(), subAssetlist));
         list.add(map);
      }
      ArrayList<Map> list2 = new ArrayList();
      ArrayMap<String, Object> map = new ArrayMap<String, Object>();
      map.put("assetList", getConsolidateAssetAndSubAssetList());// for previous chart
      //map.put("assetList",getAssetData(this.getEditableAsset())); for 2d donut chart
      map.put("subAssetList", list);
      list2.add(map);
      return new Gson().toJson(map);
   }

   /**
    * this method is for generate arraylist for piechart DoubleLAYER
    *
    * @param subAssetlist list Of asset
    * @return ArrayList<map>
    */
   public String createPIEChartForDetailLayer(List<DataPortfolio> subAssetlist)
   {
      ArrayList<Map> list = new ArrayList();

      if (false)
      {
         for (DataPortfolio stringArrayListOne : subAssetlist)
         {
            ArrayMap<String, Object> map = new ArrayMap<String, Object>();
            map.put("y", getAssetTotalWeight(stringArrayListOne.getAssetType(), subAssetlist));
            map.put("color", stringArrayListOne.getColor());
            map.put("drilldown", getdrilldownFor2dDonut(stringArrayListOne.getAssetType(), subAssetlist));
            list.add(map);
         }
      }
      else
      {
         for (DataPortfolio stringArrayListOne : subAssetlist)
         {
            ArrayMap<String, Object> map = new ArrayMap<String, Object>();
            map.put("name", stringArrayListOne.getAssetType());
            map.put("id", stringArrayListOne.getAssetType());
            map.put("data", internalData(map.get("name").toString(), subAssetlist));
            list.add(map);
         }
      }
      ArrayList<Map> list2 = new ArrayList();
      ArrayMap<String, Object> map = new ArrayMap<String, Object>();
      map.put("assetList", getConsolidateAssetAndSubAssetList());// for previous chart
      // map.put("assetList",getAssetData(this.getEditableAsset())); for 2d donut chart
      map.put("subAssetList", list);
      list2.add(map);
      return new Gson().toJson(map);
   }

   // New method implements : Projection chart creation by using HighChart
   public ChartData createProjectionHighChart(ProjectionData[] projectionData,
                                              Integer horizon, Integer currAge, Integer ageSeries,
                                              ProjectionData[] projectionDataAggressive)
   {
      chartdata = new ChartData();
      Integer calendarYear, minYearPoint, maxYearPoint, minGrowth, maxGrowth, legendXrotation;
      Integer maxGraghPlot;
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

      if (projectionData == null)
      {
         return null;
      }

      if (projectionData.length < 2)
      {
         return null;
      }

      totalYlabels = (horizon < 1) ? 1 : ((horizon > MAXPOINTONGRAPH) ? MAXPOINTONGRAPH : horizon);
      // yIncrement = (int) ((totalYlabels) / ((double) horizon));
      yIncrement = 1;  // offset by 1
      noOfYlabels = (int) (totalYlabels / ((double) yIncrement) % horizon);
      // Mod returns 0 at its interval.  So on 30, we want to rotate it 90.
      noOfYlabels = (noOfYlabels == 0) ? projectionData.length : noOfYlabels;

      int y = 0;
      Calendar cal = Calendar.getInstance();
      calendarYear = cal.get(cal.YEAR);
      minYearPoint = calendarYear;
      maxYearPoint = minYearPoint + totalYlabels;
      Integer lowervalue = (int) ((double) projectionData[0].getLowerBand2() * .10);
      minGrowth = ((int) projectionData[0].getLowerBand2() - lowervalue < 0) ? 0 : (int) projectionData[0].getLowerBand2() - lowervalue;
      maxGrowth = 0;
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

      chartdata.setChartType("HIGHCHART.PROJECTION");
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
         tmpvalue = (Math.round((projectionData[y].getLowerBand1() / dividingFactor) * 100.0)) / 100.0;
         temvalueLower1 = tmpvalue;
         tmpvalue = (Math.round((projectionData[y].getLowerBand2() / dividingFactor) * 100.0)) / 100.0;
         temvalueLower2 = tmpvalue;
         tmpvalue = (Math.round((projectionData[y].getUpperBand1() / dividingFactor) * 100.0)) / 100.0;
         temvalueUpper1 = tmpvalue;
         tmpvalue = (Math.round((projectionData[y].getUpperBand2() / dividingFactor) * 100.0)) / 100.0;
         temvalueUpper2 = tmpvalue;

         // If incrementing anything other then 1, then make sure that last year is displayed.
         if (y >= totalYlabels) // If last point is plotted, then quit.
         {
            Integer lastpoint = totalYlabels;
            chartdata.getChartMap().put("AvgPerformance", ((Math.round((projectionData[lastpoint].getTotalCapitalWithGains() / dividingFactor) * 100.0)) / 100.0));
            chartdata.getChartMap().put("PoorPerformance", ((Math.round((projectionData[lastpoint].getLowerBand2() / dividingFactor) * 100.0)) / 100.0));
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
      if (projectionDataAggressive != null)
      {
         maxGraghPlot = (int) ((Math.round((projectionDataAggressive[totalYlabels].getUpperBand2() / dividingFactor) * 100.0)) / 100.0);
      }else {
         maxGraghPlot = (int) ((Math.round((projectionData[totalYlabels].getUpperBand2() / dividingFactor) * 100.0)) / 100.0);
      }

      HashMap<String, Object> chartMap = new HashMap<String, Object>();

      chartMap.put("goalYearValue", goalYearValue);
      chartMap.put("goalLowerValue", goalLowerValue);
      chartMap.put("goalUpperValue", goalUpperValue);
      chartMap.put("goalAvgValue", goalAvgValue);
      chartMap.put("minGrowth", minGrowth);
      chartMap.put("maxGraghPlot", maxGraghPlot);
      chartMap.put("goalAgeSeries", goalAgeSeries);

      chartdata.setHighChartresultSet(new Gson().toJson(chartMap));

      return chartdata;
   }
   //End HighChart implementation


   public ArrayList internalData(String assetType, List<DataPortfolio> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      ArrayMap<String, Object> map = new ArrayMap<String, Object>();
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         ArrayList list = new ArrayList();
         if (stringArrayList.getAssetType().equalsIgnoreCase(assetType))
         {
            list.add(stringArrayList.getSymbol());
            list.add(stringArrayList.getWeight());
            list1.add(list);
         }
      }
      return list1;
   }

   public ArrayList getdrilldownFor2dDonut(String assetType, List<DataPortfolio> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      ArrayMap<String, Object> map = new ArrayMap<String, Object>();
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         ArrayList list = new ArrayList();
         if (stringArrayList.getAssetType().equalsIgnoreCase(assetType))
         {
            map.put("name", stringArrayList.getAssetType());
            map.put("categories", getSubAssetNameData(edittableAsset));
            map.put("data", getSubAssetWeightData(edittableAsset));
            list1.add(map);
         }
      }
      return list1;
   }

   public ArrayList getAssetData(ArrayList<Asset> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      for (Asset stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getAsset());
      }
      return list1;
   }

   public ArrayList getSubAssetNameData(List<DataPortfolio> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      for (DataPortfolio stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getSymbol());
      }
      return list1;
   }

   public ArrayList getSubAssetWeightData(List<DataPortfolio> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      for (DataPortfolio stringArrayListOne : edittableAsset)
      {
         list1.add(stringArrayListOne.getWeight());
      }
      return list1;
   }

   public Double getAssetTotalWeight(String assetType, List<DataPortfolio> edittableAsset)
   {
      ArrayList list1 = new ArrayList();
      Double totalWeight = 0d;
      for (DataPortfolio stringArrayList : edittableAsset)
      {
         if (stringArrayList.getAssetType().equalsIgnoreCase(assetType))
         {
            totalWeight = totalWeight + stringArrayList.getWeight();
         }
      }
      return totalWeight;
   }


}
