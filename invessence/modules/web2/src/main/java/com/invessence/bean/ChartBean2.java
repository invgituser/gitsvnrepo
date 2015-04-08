package com.invessence.bean;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/4/14
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.invessence.data.ChartInfo;
import org.primefaces.model.chart.*;

@ManagedBean
@RequestScoped
public class ChartBean2 implements Serializable {
   private List<ChartInfo> chartInfos;
   private Integer num1, num2, num3;

   public Integer getNum1()
   {
      return num1;
   }

   public void setNum1(Integer num1)
   {
      this.num1 = num1;
   }

   public Integer getNum2()
   {
      return num2;
   }

   public void setNum2(Integer num2)
   {
      this.num2 = num2;
   }

   public Integer getNum3()
   {
      return num3;
   }

   public void setNum3(Integer num3)
   {
      this.num3 = num3;
   }

   public ChartBean2() {
      chartInfos = new ArrayList<ChartInfo>();
      chartInfos.add(new ChartInfo("Dogs", 2, "000000"));
      // Uncomment the following line and everything still works. The birds will still be red.
      chartInfos.add(new ChartInfo("Cats", 1, "00ABff"));
      chartInfos.add(new ChartInfo("Birds", 1, "ffffff"));
   }

   public List<ChartInfo> getAnimals() {
      return chartInfos;
   }

   public String getChartSeriesColors() {
      String chartSeriesColors = "";

      for (int i = 0; i < chartInfos.size(); i++) {
         chartSeriesColors += chartInfos.get(i).getColor();
         if (i < chartInfos.size() - 1)
            chartSeriesColors += ",";
      }

      return chartSeriesColors;
   }

   private PieChartModel pieModel;
   public PieChartModel getPieModel()
   {
      pieModel = new PieChartModel();
      for (int i = 0; i < chartInfos.size(); i++) {
         pieModel.set(chartInfos.get(i).getName(), chartInfos.get(i).getCount());
      }
      //pieModel.setSeriesColors(getChartSeriesColors());
      return pieModel;
   }

   public MeterGaugeChartModel getMeterGuage() {
      MeterGaugeChartModel model;
      List<Number> intervals = new ArrayList<Number>(){{
         add(1);
         add(2);
         add(3);
         add(4);
      }};
      model = new MeterGaugeChartModel(3, intervals);
/*
      model.setSeriesColors("66cc66,93b75f,E7E658,cc6666");
      model.setGaugeLabel("Risk");
      model.setGaugeLabelPosition("bottom");
      model.setShowTickLabels(false);
      model.setLabelHeightAdjust(-25);
      model.setIntervalOuterRadius(25);
*/
      return model;
   }

}