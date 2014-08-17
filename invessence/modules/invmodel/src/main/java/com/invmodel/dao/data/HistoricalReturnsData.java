package com.invmodel.dao.data;

public class HistoricalReturnsData {
    private String indexFund = "";
    private String date = "";
    private double lbConstraint = 0.0;
    private double ubConstraint = 0.0;
    private double avgReturns = 0.0;
    private String color = "";

    public HistoricalReturnsData() {

    }

    public HistoricalReturnsData(String indexFund, String date,
                                 double lbConstraint, double ubConstraint, double avgReturns,
                                 String color) {
        super();
        this.indexFund = indexFund;
        this.date = date;
        this.lbConstraint = lbConstraint;
        this.ubConstraint = ubConstraint;
        this.avgReturns = avgReturns;
        this.color = color;
    }

    public String getIndexFund() {
        return indexFund;
    }

    public void setIndexFund(String indexFund) {
        this.indexFund = indexFund;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getLbConstraint() {
        return lbConstraint;
    }

    public void setLbConstraint(double lbConstraint) {
        this.lbConstraint = lbConstraint;
    }

    public double getUbConstraint() {
        return ubConstraint;
    }

    public void setUbConstraint(double ubConstraint) {
        this.ubConstraint = ubConstraint;
    }

    public double getAvgReturns() {
        return avgReturns;
    }

    public void setAvgReturns(double avgReturns) {
        this.avgReturns = avgReturns;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
