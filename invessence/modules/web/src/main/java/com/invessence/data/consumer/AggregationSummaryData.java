package com.invessence.data.consumer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

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
    private Double costBasisMoney;
    private Double positionValue;
    private Double fifoPnlUnrealized;
    private Double percentAllocation;
    private Map<String, AggregationSummaryData> alternateMap;

    public AggregationSummaryData() {
    }

    public AggregationSummaryData(String key, String info,
                                  Double costBasisMoney, Double positionValue,
                                  Double fifoPnlUnrealized, Double percentAllocation) {
        this.key = key;
        this.info = info;
        this.costBasisMoney = costBasisMoney;
        this.positionValue = positionValue;
        this.fifoPnlUnrealized = fifoPnlUnrealized;
        this.percentAllocation = percentAllocation;
        alternateMap = null;
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

    public Double getPercentAllocation() {
        return percentAllocation;
    }

    public void setPercentAllocation(Double percentAllocation) {
        this.percentAllocation = percentAllocation;
    }

    public ArrayList<AggregationSummaryData> getAlternateList() {
        if (alternateMap != null) {
            ArrayList<AggregationSummaryData> arrayList = new ArrayList<AggregationSummaryData>();
            for (AggregationSummaryData data: alternateMap.values()) {
                arrayList.add(data);
            }
            return arrayList;
        }
        return null;
    }

    public Map<String, AggregationSummaryData> getAlternateMap() {
        return alternateMap;
    }

    public Double alternatePos(String altkey) {
        if (alternateMap != null) {
            if (alternateMap.containsKey(altkey))
                return alternateMap.get(altkey).getPositionValue();
            else
                return 0.0;
        }
        else
            return 0.0;
    }

    public void addAlternateMap(String alternateKey, String info,
                                 Double costBasisMoney, Double positionValue,
                                 Double fifoPnlUnrealized, Double percentAllocation
    ) {
        if (alternateKey != null) {
            if (alternateMap == null) {
                alternateMap = new LinkedHashMap<String, AggregationSummaryData>();
            }

            AggregationSummaryData data;
            Double newposition, newpercent;
            if (alternateMap.containsKey(alternateKey)) {
                data = alternateMap.get(alternateKey);
                newposition = data.getPositionValue() + positionValue;
                data.setCostBasisMoney(data.getCostBasisMoney() + costBasisMoney);
                data.setPositionValue(newposition);
                data.setFifoPnlUnrealized(data.getFifoPnlUnrealized() + fifoPnlUnrealized);
                data.setPercentAllocation(percentAllocation);
            }
            else {
                data = new AggregationSummaryData(alternateKey, info,
                        costBasisMoney, positionValue,
                        fifoPnlUnrealized, percentAllocation);
            }
            alternateMap.put(alternateKey, data);
        }
    }

}
