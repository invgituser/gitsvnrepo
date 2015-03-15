package com.invessence.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/27/14
 * Time: 8:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReportData {
    String key;
    String filename;
    String reporttype;
    String location;
    String created;

    public ReportData() {
    }

    public ReportData(String key, String filename, String reporttype, String location, String created) {
        this.key = key;
        this.filename = filename;
        this.reporttype = reporttype;
        this.location = location;
        this.created = created;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getReporttype() {
        return reporttype;
    }

    public void setReporttype(String reporttype) {
        this.reporttype = reporttype;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }
}
