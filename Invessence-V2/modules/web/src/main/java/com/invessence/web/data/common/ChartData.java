package com.invessence.web.data.common;

import java.util.HashMap;

import org.primefaces.model.chart.*;

/**
 * Created by prashant on 2/28/2017.
 */
public class ChartData
{
   private String chartType;
   private String highChartresultSet;
   private Object primefaceChartObj;
   HashMap<String, Object> chartMap = new HashMap<String, Object>();


   public ChartData()
   {
      chartMap = new HashMap<String, Object>();
   }

   public String getChartType()
   {
      return chartType;
   }

   public void setChartType(String chartType)
   {
      this.chartType = chartType;
   }

   public String getHighChartresultSet()
   {
      return highChartresultSet;
   }

   public void setHighChartresultSet(String highChartresultSet)
   {
      this.highChartresultSet = highChartresultSet;
   }

   public Object getPrimefaceChartObj()
   {
      return primefaceChartObj;
   }

   public void setPrimefaceChartObj(Object primefaceChartObj)
   {
      this.primefaceChartObj = primefaceChartObj;
   }

   public HashMap getChartMap()
   {
      return chartMap;
   }

   public void setChartMap(HashMap chartMap)
   {
      this.chartMap = chartMap;
   }



   public PieChartModel getPieChartModel()
   {
      return (PieChartModel) primefaceChartObj;
   }

   public LineChartModel getLineChartModel()
   {
      return (LineChartModel) primefaceChartObj;
   }

   public BarChartModel getBarChartModel()
   {
      return (BarChartModel) primefaceChartObj;
   }

   public Object getChartData() {
      if (chartType != null) {
         if (chartType.contains("HIGH"))
            return highChartresultSet;
         else
            return primefaceChartObj;
      }
      return null;
   }

   public Object getChartMapData(String key)
   {
      if (chartMap != null) {
         if (chartMap.containsKey(key))
            return chartMap.get(key);
      }
      return null;

   }

   public Integer getIntChartData(String key) {
      Object value = getChartMapData(key);
      if (value != null) {
         Double dValue =  getDoubleChartData(key);
         return dValue.intValue();
      }
      return null;
   }

   public Double getDoubleChartData(String key) {
      Object value = getChartMapData(key);
      if (value != null) {
         return Double.parseDouble(value.toString()) ;
      }
      return null;
   }

   public String getStrChartData(String key) {
      Object value = getChartMapData(key);
      if (value != null) {
         return value.toString();
      }
      return null;
   }
}
