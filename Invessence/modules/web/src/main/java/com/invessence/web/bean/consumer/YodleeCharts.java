package com.invessence.web.bean.consumer;

import java.io.Serializable;
import java.util.*;

import com.invessence.converter.JavaUtil;
import com.invessence.web.data.consumer.AggregationSummaryData;
import com.invessence.web.data.consumer.AssetMaster;
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
    private BarChartModel ydl_typeModel;
    private BarChartModel ydl_assetModel;

    public YodleeCharts() {
        ydl_typeModel = null;
        ydl_assetModel = null;
    }

    public BarChartModel getYdl_typeModel() {
        return ydl_typeModel;
    }

    public BarChartModel getYdl_assetModel() {
        return ydl_assetModel;
    }

    public DonutChartModel showDonutDetail(Map<String, AggregationSummaryData> aggrDataMap,
                                          ArrayList<AssetMaster> assetmaster,
                                          Integer posTotal, Integer grandAssetTotal) {
        String color = "";
        String seriesColor = "";
        DonutChartModel donutModel = null;;
        try {
            if (aggrDataMap == null) {
                return donutModel;
            }

            AggregationSummaryData aggrData;
            Double value1, value2;
            if (aggrDataMap != null && aggrDataMap.size() >= 0) {
                donutModel = new DonutChartModel();
                int slice = 0;
                Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
                // Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
                for (AssetMaster asset : assetmaster) {
                    String label = asset.getAssetclass();
                    if (aggrDataMap.containsKey(label)) {
                        aggrData = aggrDataMap.get(label);
                        value1 = Double.valueOf(Math.round((Math.abs(aggrData.getPositionValue()) / Math.abs(posTotal)) * 100.0));
                        //value2 = Double.valueOf(Math.round((Math.abs(aggrData.getPositionValue()) / Math.abs(grandAssetTotal)) * 100.0));
                    }
                    else {
                        value1 = 0.0;
                        //value2 = 0.0;
                    }
                    circle1.put(label, value1/100.0);
                    //circle2.put(label, value2/100.0);
                    color = asset.getColor().replace('#', ' ');
                    color = color.trim();
                    if (seriesColor.isEmpty()) {
                        seriesColor = seriesColor.concat(color);
                    }
                    else {
                        seriesColor = seriesColor.concat("," + color);
                    }
                    slice++;
                }
                donutModel.addCircle(circle1);
                //donutModel.addCircle(circle2);
                donutModel.setSeriesColors(seriesColor);
                donutModel.setSliceMargin(2);
                donutModel.setShowDataLabels(true);
                donutModel.setExtender("ydldonut_details");
            }
            else {
                donutModel = null;
            }

        } catch (Exception ex) {
            donutModel = null;
        }
        return donutModel;
    }

    public PieChartModel showPieSummary(ArrayList<AggregationSummaryData> aggrDataMap) {
        String color = "";
        String seriesColor = "";
        PieChartModel pieModel = null;
        try {
            if (aggrDataMap == null) {
                return pieModel;
            }

            AggregationSummaryData aggrData;
            Double value1; //, value2;
            if (aggrDataMap != null && aggrDataMap.size() >= 0) {
                pieModel = new PieChartModel();
                int slice = 0;
                //Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
                //Map<String, Number> circle2 = new LinkedHashMap<String, Number>();
                for (AggregationSummaryData asset : aggrDataMap) {
                    String label = asset.getKey();
                    value1 = Double.valueOf(Math.round(Math.abs(asset.getPercentAllocation())* 100.0));
                    pieModel.set(label, value1);
                    color = asset.getInfo().replace('#', ' ');
                    color = color.trim();
                    if (seriesColor.isEmpty()) {
                        seriesColor = seriesColor.concat(color);
                    }
                    else {
                        seriesColor = seriesColor.concat("," + color);
                    }
                    slice++;
                }
                pieModel.setSeriesColors(seriesColor);
                pieModel.setSliceMargin(5);
                pieModel.setShowDataLabels(true);
                pieModel.setExtender("ydlpie_summary");
            }
            else {
                pieModel = null;
            }

        } catch (Exception ex) {
            pieModel = null;
        }
        return pieModel;
    }

    public void createTypeModel(ArrayList<AggregationSummaryData> aggrDataList) {
        try {
            if (aggrDataList == null) {
                ydl_typeModel = null;
                return;
            }

            if (aggrDataList != null && aggrDataList.size() >= 0) {
                ydl_typeModel = new BarChartModel();
                int slice = 0;
                ChartSeries[] series = new ChartSeries[1];
                series[slice] = new ChartSeries();
                for (AggregationSummaryData aggrData : aggrDataList) {
                    String label = aggrData.getKey();
                    Double value = aggrData.getPositionValue();
                    // series[slice] = new ChartSeries();
                    series[slice].setLabel(label);
                    series[slice].set(label,value);
                    //ydl_typeModel.addSeries(series[slice]);
                    //slice++;
                }
                ydl_typeModel.addSeries(series[0]);

                // ydl_typeModel.setLegendPosition("s");
                // ydl_typeModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
                ydl_typeModel.setShowPointLabels(true);
                ydl_typeModel.setMouseoverHighlight(false);
                ydl_typeModel.setShowDatatip(false);
                // Axis xAxis = ydl_typeModel.getAxis(AxisType.X);
                // xAxis.setLabel("Assets");
                // xAxis.setTickAngle(45);
                // xAxis.setTickFormat();
                Axis yAxis = ydl_typeModel.getAxis(AxisType.Y);
                yAxis.setTickFormat("$%'d");
                ydl_typeModel.setExtender("ydlbar_types");

            }
            else {
                ydl_typeModel = null;
            }

        } catch (Exception ex) {
            ydl_typeModel = null;
        }
    }

    public void createAssetModel(Map<String, AggregationSummaryData> assetData, Map<String, AssetMaster> assetMasterMap) {
        try {
            if (assetData == null) {
                ydl_assetModel = null;
                return;
            }

            if (assetMasterMap != null && assetMasterMap.size() >= 0) {
                ydl_assetModel = new BarChartModel();
                int slice = 0;
                int numberofInvestment =  assetData.size();
                ChartSeries[] series = new ChartSeries[numberofInvestment] ;
                for (int i=0; i<numberofInvestment; i++) {
                    series[i] = new ChartSeries();
                }

                for (String assetname : assetMasterMap.keySet()) {
                    slice = 0;

                    for (String sitename : assetData.keySet()) {
                        series[slice].setLabel(sitename);
                        Map<String, AggregationSummaryData> dataMap = assetData.get(sitename).getAlternateMap();
                        if (dataMap.containsKey(assetname)) {
                            series[slice].set(assetname,dataMap.get(assetname).getPositionValue());
                        }
                        else {
                            series[slice].set(assetname,0.0);
                        }
                        slice++;
                    }
                }

                for (int i=0; i < numberofInvestment; i++) {
                    ydl_assetModel.addSeries(series[i]);
                }
                ydl_typeModel.setLegendPosition("se");
                ydl_typeModel.setLegendPlacement(LegendPlacement.OUTSIDEGRID);
                ydl_assetModel.setShowPointLabels(true);
                ydl_assetModel.setMouseoverHighlight(false);
                ydl_assetModel.setShowDatatip(false);
                Axis xAxis = ydl_assetModel.getAxis(AxisType.X);
                // xAxis.setLabel("Assets");
                xAxis.setTickAngle(45);
                // xAxis.setTickFormat();
                Axis yAxis = ydl_assetModel.getAxis(AxisType.Y);
                yAxis.setTickFormat("$%'d");
                ydl_assetModel.setExtender("ydlbar_summary");

            }
            else {
                ydl_assetModel = null;
            }

        } catch (Exception ex) {
            ydl_assetModel = null;
        }
    }


}
