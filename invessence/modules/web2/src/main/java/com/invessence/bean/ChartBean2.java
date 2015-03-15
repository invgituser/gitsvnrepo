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
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
@RequestScoped
public class ChartBean2 implements Serializable {
   private List<ChartInfo> chartInfos;

   public ChartBean2() {
      chartInfos = new ArrayList<ChartInfo>();
      chartInfos.add(new ChartInfo("Dogs", 2, "0x00ff00"));
      // Uncomment the following line and everything still works. The birds will still be red.
      chartInfos.add(new ChartInfo("Cats", 1, "0x0000ff"));
      chartInfos.add(new ChartInfo("Birds", 1, "0xff0000"));
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
      return pieModel;
   }

}