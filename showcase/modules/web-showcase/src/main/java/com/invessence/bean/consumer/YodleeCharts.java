package com.invessence.bean.consumer;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invessence.data.consumer.AggregationSummaryData;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.*;
import com.invmodel.performance.data.PerformanceData;
import com.invmodel.portfolio.data.Portfolio;
import org.primefaces.model.chart.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/20/14
 * Time: 10:03 AM
 * To change this template use File | Settings | File Templates.
 */

@ManagedBean(name = "yodleeCharts")
@SessionScoped
public class YodleeCharts implements Serializable {
    JavaUtil jutil = new JavaUtil();
    PieChartModel pieChart;
    private HorizontalBarChartModel horizontalBarModel;

    public YodleeCharts() {
        pieChart = null;
        horizontalBarModel = null;
    }

    public PieChartModel getPieChart() {
        return pieChart;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    public void createPieModel(ArrayList<AggregationSummaryData> aggrDataList) {
        String color;
        try {
            if (aggrDataList == null) {
                pieChart = null;
                return;
            }

            if (aggrDataList != null && aggrDataList.size() >= 0) {
                pieChart = new PieChartModel();
                int slice = 0;
                for (AggregationSummaryData aggrData : aggrDataList) {
                    String label = aggrData.getKey();
                    Double value = Math.abs(aggrData.getPositionValue());
                    pieChart.set(label, value);
                    slice++;
                }
                pieChart.setFill(true);
                // pieChart.setShowDataLabels(true);
                pieChart.setDiameter(250);
                pieChart.setLegendPosition("e");
                pieChart.setExtender("ydl_pie_extensions");
            }
            else {
                pieChart = null;
            }

        } catch (Exception ex) {
            pieChart = null;
        }
    }

    public void createBarModel(ArrayList<AggregationSummaryData> aggrDataList) {
        String color, barcolor;
        try {
            if (aggrDataList == null) {
                horizontalBarModel = null;
                return;
            }

            if (aggrDataList != null && aggrDataList.size() >= 0) {
                horizontalBarModel = new HorizontalBarChartModel();
                ChartSeries asset = new ChartSeries();
                barcolor="";
                int slice = 0;
                for (AggregationSummaryData aggrData : aggrDataList) {
                    String label = aggrData.getKey();
                    Double value = Math.abs(aggrData.getPositionValue());
                    asset.setLabel(label);
                    asset.set(label,value);
                    color = aggrData.getInfo().trim();
                    if (slice == 0)
                    {
                        barcolor = color.trim();
                    }
                    else
                    {
                        barcolor = barcolor + "," + color;
                    }

                    slice++;
                }
                horizontalBarModel.addSeries(asset);
                //horizontalBarModel.setLegendPosition("e");
                horizontalBarModel.setShowPointLabels(true);
                horizontalBarModel.setMouseoverHighlight(false);
                horizontalBarModel.setShowDatatip(false);
                horizontalBarModel.setSeriesColors(barcolor);
                Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
                xAxis.setMin(0);
                xAxis.setTickFormat("$%'d");
                // horizontalBarModel.setExtender("ydl_bar_extensions");
            }
            else {
                horizontalBarModel = null;
            }

        } catch (Exception ex) {
            horizontalBarModel = null;
        }
    }


}
