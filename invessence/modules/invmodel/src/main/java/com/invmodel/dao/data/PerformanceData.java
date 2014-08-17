package com.invmodel.dao.data;

public class PerformanceData {
    private String name = "";
    private double avgPerformance = 0.0;
    private double highPerformance = 0.0;
    private double lowPerformance = 0.0;
    private int duration = 0;

    public PerformanceData() {
    }

    public PerformanceData(String name, double avgPerformance,
                           double highPerformance, double lowPerformance, int duration) {
        super();
        this.name = name;
        this.avgPerformance = avgPerformance;
        this.highPerformance = highPerformance;
        this.lowPerformance = lowPerformance;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAvgPerformance() {
        return avgPerformance;
    }

    public void setAvgPerformance(double avgPerformance) {
        this.avgPerformance = avgPerformance;
    }

    public double getHighPerformance() {
        return highPerformance;
    }

    public void setHighPerformance(double highPerformance) {
        this.highPerformance = highPerformance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
