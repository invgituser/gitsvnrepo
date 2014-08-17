package com.invessence.bean;

import java.io.File;
import java.io.FileInputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class DynamicImageBean {

    private StreamedContent chart;

    public DynamicImageBean() {
        try {
            //Chart
            JFreeChart jfreechart = ChartFactory.createPieChart("Turkish Cities", createDataset(), true, true, false);
            File chartFile = new File("dynamichart");
            ChartUtilities.saveChartAsPNG(chartFile, jfreechart, 375, 300);
            chart = new DefaultStreamedContent(new FileInputStream(chartFile), "image/png");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public StreamedContent getChart() {
        return chart;
    }

    private PieDataset createDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Istanbul", new Double(45.0));
        dataset.setValue("Ankara", new Double(15.0));
        dataset.setValue("Izmir", new Double(25.2));
        dataset.setValue("Antalya", new Double(14.8));

        return dataset;
    }
}