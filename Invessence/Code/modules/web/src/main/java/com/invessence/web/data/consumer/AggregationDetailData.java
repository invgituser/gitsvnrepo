package com.invessence.web.data.consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/6/15
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AggregationDetailData {
    private Integer sortorder;
    private String src;
    private String sitename;
    private String siteid;
    private String datatype;
    private Long acctnum;
    private String clientAccountID;
    private String acctname;
    private String currencyPrimary;
    private String assetClass;
    private String color;
    private String subclass;
    private Double fxRateToBase;
    private String symbol;
    private String description;
    private String reportDate;
    private String side;
    private Integer quantity;
    private Double costBasisPrice;
    private Double costBasisMoney;
    private Double markPrice;
    private Double positionValue;
    private Double fifoPnlUnrealized;
    private Double percentAllocation;

    public AggregationDetailData() {
    }

    public AggregationDetailData(Integer sortorder, String src, String sitename, String siteid, String datatype,
                                 Long acctnum, String clientAccountID, String acctname,
                                 String currencyPrimary, String assetClass, String color, String subclass,
                                 Double fxRateToBase, String symbol, String description, String reportDate, String side,
                                 Integer quantity, Double costBasisPrice, Double costBasisMoney, Double markPrice,
                                 Double positionValue, Double fifoPnlUnrealized) {
        this.sortorder = sortorder;
        this.src = src;
        this.sitename = sitename;
        this.siteid = siteid;
        this.datatype = datatype;
        this.acctnum = acctnum;
        this.clientAccountID = clientAccountID;
        this.acctname = acctname;
        this.currencyPrimary = currencyPrimary;
        this.assetClass = assetClass;
        this.color = color;
        this.subclass = subclass;
        this.fxRateToBase = fxRateToBase;
        this.symbol = symbol;
        this.description = description;
        this.reportDate = reportDate;
        this.side = side;
        this.quantity = quantity;
        this.costBasisPrice = costBasisPrice;
        this.costBasisMoney = costBasisMoney;
        this.markPrice = markPrice;
        this.positionValue = positionValue;
        this.fifoPnlUnrealized = fifoPnlUnrealized;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSitename() {
        return sitename;
    }

    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSiteid() {
        return siteid;
    }

    public void setSiteid(String siteid) {
        this.siteid = siteid;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Long getAcctnum() {
        return acctnum;
    }

    public void setAcctnum(Long acctnum) {
        this.acctnum = acctnum;
    }

    public String getClientAccountID() {
        return clientAccountID;
    }

    public void setClientAccountID(String clientAccountID) {
        this.clientAccountID = clientAccountID;
    }

    public String getAcctname() {
        return acctname;
    }

    public void setAcctname(String acctname) {
        this.acctname = acctname;
    }

    public String getCurrencyPrimary() {
        return currencyPrimary;
    }

    public void setCurrencyPrimary(String currencyPrimary) {
        this.currencyPrimary = currencyPrimary;
    }

    public String getAssetClass() {
        return assetClass;
    }

    public void setAssetClass(String assetClass) {
        this.assetClass = assetClass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public Double getFxRateToBase() {
        return fxRateToBase;
    }

    public void setFxRateToBase(Double fxRateToBase) {
        this.fxRateToBase = fxRateToBase;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCostBasisPrice() {
        return costBasisPrice;
    }

    public void setCostBasisPrice(Double costBasisPrice) {
        this.costBasisPrice = costBasisPrice;
    }

    public Double getCostBasisMoney() {
        return costBasisMoney;
    }

    public void setCostBasisMoney(Double costBasisMoney) {
        this.costBasisMoney = costBasisMoney;
    }

    public Double getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(Double markPrice) {
        this.markPrice = markPrice;
    }

    public Double getPositionValue() {
        return positionValue;
    }

    public void setPositionValue(Double positionValue) {
        this.positionValue = positionValue;
    }

    public Double getFifoPnlUnrealized() {
        return fifoPnlUnrealized;
    }

    public void setFifoPnlUnrealized(Double fifoPnlUnrealized) {
        this.fifoPnlUnrealized = fifoPnlUnrealized;
    }

    public Double getPercentAllocation() {
        return percentAllocation;
    }

    public void setPercentAllocation(Double percentAllocation) {
        this.percentAllocation = percentAllocation;
    }

    public AggregationDetailData copyData() {
        try {
            AggregationDetailData newData = new AggregationDetailData(
                    this.sortorder, this.src, this.sitename, this.siteid, this.datatype,
                    this.acctnum, this.clientAccountID, this.acctname,
                    this.currencyPrimary, this.assetClass, this.color, this.subclass,
                    this.fxRateToBase, this.symbol, this.description, this.reportDate, this.side,
                    this.quantity, this.costBasisPrice, this.costBasisMoney, this.markPrice,
                    this.positionValue, this.fifoPnlUnrealized);
            newData.setPercentAllocation(this.percentAllocation);
            return newData;
        }
        catch (Exception ex) {
            return null;
        }
    }
}
