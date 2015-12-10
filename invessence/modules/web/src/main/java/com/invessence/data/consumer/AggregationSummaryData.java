package com.invessence.data.consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/6/15
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class AggregationSummaryData {
    private String key;
    private String info;
    private Integer quantity;
    private Double costBasisMoney;
    private Double positionValue;
    private Double fifoPnlUnrealized;

    public AggregationSummaryData() {
    }

    public AggregationSummaryData(String key, String info,
                                  Integer quantity, Double costBasisMoney, Double positionValue, Double fifoPnlUnrealized) {
        this.key = key;
        this.info = info;
        this.quantity = quantity;
        this.costBasisMoney = costBasisMoney;
        this.positionValue = positionValue;
        this.fifoPnlUnrealized = fifoPnlUnrealized;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getCostBasisMoney() {
        return costBasisMoney;
    }

    public void setCostBasisMoney(Double costBasisMoney) {
        this.costBasisMoney = costBasisMoney;
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
}
