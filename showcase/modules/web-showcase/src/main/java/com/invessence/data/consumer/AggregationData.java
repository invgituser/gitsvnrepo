package com.invessence.data.consumer;

import com.invessence.converter.JavaUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/6/15
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AggregationData {
    private Map<String,ArrayList<AggregationDetailData>> aggregationDetailDataArrayMap; // Map of Accounts with details
    private Map<String,Map<String,AggregationSummaryData>> aggregationLevelArrayMap;      // Map of Sites with details
    private Map<String,Map<String,AggregationSummaryData>> aggregationSiteArrayMap;      // Map of Sites with details
    private Map<String,Map<String,AggregationSummaryData>> aggregationAssetArrayMap;     // Map of Asset with details
    private Map<String,Map<String,AggregationSummaryData>> aggregationSubAssetArrayMap;  // Map of Subasset with details

    private Map<String,String> siteLogo;                               // Map of Logo
    private Map<String,AggregationSummaryData> totalLevelArrayMap;     // Map of Level for total
    private Map<String,AggregationSummaryData> totalSiteArrayMap;      // Map of Sites for total
    private Map<String,AggregationSummaryData> totalAssetArrayMap;     // Map of Asset for total
    private Map<String,AggregationSummaryData> totalSubAssetArrayMap;  // Map of Subasset for total

    private Integer grandTotalQuantity;
    private Double  grandTotalCostBasisMoney;
    private Double  grandTotalPositionValue;
    private Double  grandTotalFifoPnlUnrealized;

    public AggregationData() {
        aggregationDetailDataArrayMap = new HashMap<String, ArrayList<AggregationDetailData>>();
        aggregationLevelArrayMap = new HashMap<String, Map<String,AggregationSummaryData>>();
        aggregationSiteArrayMap = new HashMap<String, Map<String,AggregationSummaryData>>();
        aggregationAssetArrayMap = new HashMap<String, Map<String,AggregationSummaryData>>();
        aggregationSubAssetArrayMap = new HashMap<String, Map<String,AggregationSummaryData>>();
        totalLevelArrayMap = new HashMap<String, AggregationSummaryData>();
        totalSiteArrayMap = new HashMap<String, AggregationSummaryData>();
        totalAssetArrayMap = new HashMap<String, AggregationSummaryData>();
        totalSubAssetArrayMap = new HashMap<String, AggregationSummaryData>();
    }

    public void addData(Integer sortorder, String sitename, String siteid,
                        Long acctnum, String clientAccountID, String acctname,
                        String currencyPrimary, String assetClass, String color, String subclass,
                        Double fxRateToBase, String symbol, String description, String reportDate, String side,
                        Integer quantity, Double costBasisPrice, Double costBasisMoney, Double markPrice,
                        Double positionValue, Double fifoPnlUnrealized, String levelOfDetail) {

        levelOfDetail = JavaUtil.UppercaseFirstLetters(levelOfDetail);
        assetClass = JavaUtil.UppercaseFirstLetters(assetClass);
        subclass = JavaUtil.UppercaseFirstLetters(subclass);

        if (side != null && side.toUpperCase().startsWith("S")) {
            quantity = quantity * -1;
            costBasisMoney = costBasisMoney * -1;
            positionValue = positionValue * -1;
            fifoPnlUnrealized = positionValue - costBasisMoney;
        }

        addToLevel(levelOfDetail, sitename,
                   quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        addToSite(sitename, acctname,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        addToAsset(assetClass, sitename,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        addToSubAsset(subclass, description,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);

        totalByLevel(levelOfDetail, sitename,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        totalBySite(sitename, acctname,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        totalByAsset(assetClass, color,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        totalBySubAsset(subclass, description,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);

        addSiteLogo(sitename, siteid);
        addToDetail(sortorder,  sitename, siteid,
                acctnum,  clientAccountID,  acctname,
                currencyPrimary,  assetClass,  color,  subclass,
                fxRateToBase,  symbol,  description,  reportDate,  side,
                quantity,  costBasisPrice,  costBasisMoney,  markPrice,
                positionValue,  fifoPnlUnrealized,  levelOfDetail );

        grandTotalQuantity = addIntValue(grandTotalQuantity,quantity);
        grandTotalCostBasisMoney = addDoubleValue(grandTotalCostBasisMoney, costBasisMoney);
        grandTotalPositionValue = addDoubleValue(grandTotalPositionValue, positionValue);
        grandTotalFifoPnlUnrealized = addDoubleValue(grandTotalFifoPnlUnrealized, fifoPnlUnrealized);


    }

    public ArrayList<AggregationDetailData> getAggregationDetailDataArray(String clientAccountID) {
        if (aggregationDetailDataArrayMap == null)
            return null;
        return aggregationDetailDataArrayMap.get(clientAccountID);
    }

    public ArrayList<AggregationSummaryData> getAggregationLevelArray(String levelname) {
        if (aggregationLevelArrayMap == null)
            return null;
        if (! aggregationLevelArrayMap.containsKey(levelname))
            return null;

        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: aggregationLevelArrayMap.get(levelname).keySet()) {
                alist.add(aggregationLevelArrayMap.get(levelname).get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getAggregationSiteArray(String sitename) {
        if (aggregationSiteArrayMap == null)
            return null;
        if (! aggregationSiteArrayMap.containsKey(sitename))
            return null;
        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: aggregationSiteArrayMap.get(sitename).keySet()) {
            alist.add(aggregationSiteArrayMap.get(sitename).get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getAggregationSubAssetArray(String assetclass) {
        if (aggregationAssetArrayMap == null)
            return null;
        if (! aggregationAssetArrayMap.containsKey(assetclass))
            return null;

        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: aggregationAssetArrayMap.get(assetclass).keySet()) {
            alist.add(aggregationAssetArrayMap.get(assetclass).get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getAggregationAssetArray(String subclass) {
        if (aggregationSubAssetArrayMap == null)
            return null;
        if (! aggregationSubAssetArrayMap.containsKey(subclass))
            return null;

        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: aggregationSubAssetArrayMap.get(subclass).keySet()) {
            alist.add(aggregationSubAssetArrayMap.get(subclass).get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getTotalLevelArray() {
        if (totalLevelArrayMap == null)
            return null;
        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: totalLevelArrayMap.keySet()) {
            alist.add(totalLevelArrayMap.get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getTotalSiteArray() {
        if (totalSiteArrayMap == null)
            return null;
        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: totalSiteArrayMap.keySet()) {
            alist.add(totalSiteArrayMap.get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getTotalAssetArray() {
        if (totalAssetArrayMap == null)
            return null;
        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: totalAssetArrayMap.keySet()) {
            alist.add(totalAssetArrayMap.get(key));
        }
        return alist;
    }

    public ArrayList<AggregationSummaryData> getTotalSubAssetArray() {
        if (totalSubAssetArrayMap == null)
            return null;
        ArrayList<AggregationSummaryData> alist = new ArrayList<AggregationSummaryData>();
        for (String key: totalSubAssetArrayMap.keySet()) {
            alist.add(totalSubAssetArrayMap.get(key));
        }
        return alist;
    }

    public Integer getGrandTotalQuantity() {
        return grandTotalQuantity;
    }

    public Double getGrandTotalCostBasisMoney() {
        return grandTotalCostBasisMoney;
    }

    public Double getGrandTotalPositionValue() {
        return grandTotalPositionValue;
    }

    public Double getGrandTotalFifoPnlUnrealized() {
        return grandTotalFifoPnlUnrealized;
    }

    public Double getPercentValue(Double value1, Double value2) {
        if (value1 == null)
            return 0.0;

        if (value2 == null)
            return 100.0;

        return Math.abs(value1) / Math.abs(value2);
    }

    private void addToDetail(Integer sortorder, String sitename, String siteid,
                             Long acctnum, String clientAccountID, String acctname,
                             String currencyPrimary, String assetClass, String color, String subclass,
                             Double fxRateToBase, String symbol, String description, String reportDate, String side,
                             Integer quantity, Double costBasisPrice, Double costBasisMoney, Double markPrice,
                             Double positionValue, Double fifoPnlUnrealized, String levelOfDetail) {

        ArrayList<AggregationDetailData> aggregationDetailDataList;
        AggregationDetailData aggregationDetailData = new AggregationDetailData(
                 sortorder,  sitename, siteid,
                 acctnum,  clientAccountID,  acctname,
                 currencyPrimary,  assetClass,  color,  subclass,
                 fxRateToBase,  symbol,  description,  reportDate,  side,
                 quantity,  costBasisPrice,  costBasisMoney,  markPrice,
                 positionValue,  fifoPnlUnrealized,  levelOfDetail
        );

        if (aggregationDetailDataArrayMap.containsKey(clientAccountID)) {
            aggregationDetailDataList = aggregationDetailDataArrayMap.get(clientAccountID);
        }
        else {
            aggregationDetailDataList = new ArrayList<AggregationDetailData>();
        }
        aggregationDetailDataList.add(aggregationDetailData);
        aggregationDetailDataArrayMap.put(clientAccountID,aggregationDetailDataList);
    }

    private Integer addIntValue(Integer value1, Integer value2) {
        if (value1 == null && value2 == null) {
            return null;
        }
        if (value2 == null) {
            return value1;
        }
        if (value1 == null) {
            return value2;
        }
        return value1 + value2;

    }

    private Double addDoubleValue(Double value1, Double value2) {
        if (value1 == null && value2 == null) {
            return null;
        }
        if (value2 == null) {
            return value1;
        }
        if (value1 == null) {
            return value2;
        }
        return value1 + value2;

    }

    private void addToLevel(String level, String info,
                               Integer quantity, Double costBasisMoney,
                               Double positionValue, Double fifoPnlUnrealized) {

        AggregationSummaryData aggrData1;
        Map<String,AggregationSummaryData> siteMap;
        if (aggregationLevelArrayMap.containsKey(level)) {
            if (aggregationLevelArrayMap.get(level).containsKey(info)) {
                siteMap = aggregationLevelArrayMap.get(level);
                aggrData1 = aggregationLevelArrayMap.get(level).get(info);
                aggrData1.setQuantity(addIntValue(quantity, aggrData1.getQuantity()));
                aggrData1.setCostBasisMoney(addDoubleValue(costBasisMoney, aggrData1.getCostBasisMoney()));
                aggrData1.setPositionValue(addDoubleValue(positionValue, aggrData1.getPositionValue()));
                aggrData1.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggrData1.getFifoPnlUnrealized()));
                siteMap.put(info,aggrData1);
                aggregationLevelArrayMap.put(level,siteMap);
                return;
            }
            siteMap = aggregationLevelArrayMap.get(level);
        }
        else {
            siteMap = new HashMap<String, AggregationSummaryData>();
        }
        aggrData1 = new AggregationSummaryData(level, info,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        siteMap.put(info,aggrData1);
        aggregationLevelArrayMap.put(level,siteMap);
    }

    private void addToSite(String sitename, String info,
                           Integer quantity, Double costBasisMoney,
                           Double positionValue, Double fifoPnlUnrealized) {
        AggregationSummaryData aggrData1;
        Map<String,AggregationSummaryData> siteMap;
        if (aggregationSiteArrayMap.containsKey(sitename)) {
            if (aggregationSiteArrayMap.get(sitename).containsKey(info)) {
                siteMap = aggregationSiteArrayMap.get(sitename);
                aggrData1 = aggregationSiteArrayMap.get(sitename).get(info);
                aggrData1.setQuantity(addIntValue(quantity, aggrData1.getQuantity()));
                aggrData1.setCostBasisMoney(addDoubleValue(costBasisMoney, aggrData1.getCostBasisMoney()));
                aggrData1.setPositionValue(addDoubleValue(positionValue, aggrData1.getPositionValue()));
                aggrData1.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggrData1.getFifoPnlUnrealized()));
                siteMap.put(info,aggrData1);
                aggregationSiteArrayMap.put(sitename,siteMap);
                return;
            }
            siteMap = aggregationSiteArrayMap.get(sitename);
        }
        else {
            siteMap = new HashMap<String, AggregationSummaryData>();
        }
        aggrData1 = new AggregationSummaryData(sitename, info,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        siteMap.put(info,aggrData1);
        aggregationSiteArrayMap.put(sitename,siteMap);
    }

    private void addToAsset(String assetClass, String info,
                           Integer quantity, Double costBasisMoney,
                           Double positionValue, Double fifoPnlUnrealized) {

        AggregationSummaryData aggrData1;
        Map<String,AggregationSummaryData> siteMap;
        if (aggregationAssetArrayMap.containsKey(assetClass)) {
            if (aggregationAssetArrayMap.get(assetClass).containsKey(info)) {
                siteMap = aggregationAssetArrayMap.get(assetClass);
                aggrData1 = aggregationAssetArrayMap.get(assetClass).get(info);
                aggrData1.setQuantity(addIntValue(quantity, aggrData1.getQuantity()));
                aggrData1.setCostBasisMoney(addDoubleValue(costBasisMoney, aggrData1.getCostBasisMoney()));
                aggrData1.setPositionValue(addDoubleValue(positionValue, aggrData1.getPositionValue()));
                aggrData1.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggrData1.getFifoPnlUnrealized()));
                siteMap.put(info,aggrData1);
                aggregationAssetArrayMap.put(assetClass,siteMap);
                return;
            }
            siteMap = aggregationAssetArrayMap.get(assetClass);
        }
        else {
            siteMap = new HashMap<String, AggregationSummaryData>();
        }
        aggrData1 = new AggregationSummaryData(assetClass, info,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        siteMap.put(info,aggrData1);
        aggregationAssetArrayMap.put(assetClass,siteMap);
    }

    private void addToSubAsset(String subclass, String info,
                            Integer quantity, Double costBasisMoney,
                            Double positionValue, Double fifoPnlUnrealized) {

        AggregationSummaryData aggrData1;
        Map<String,AggregationSummaryData> siteMap;
        if (aggregationSubAssetArrayMap.containsKey(subclass)) {
            if (aggregationSubAssetArrayMap.get(subclass).containsKey(info)) {
                siteMap = aggregationSubAssetArrayMap.get(subclass);
                aggrData1 = aggregationSubAssetArrayMap.get(subclass).get(info);
                aggrData1.setQuantity(addIntValue(quantity, aggrData1.getQuantity()));
                aggrData1.setCostBasisMoney(addDoubleValue(costBasisMoney, aggrData1.getCostBasisMoney()));
                aggrData1.setPositionValue(addDoubleValue(positionValue, aggrData1.getPositionValue()));
                aggrData1.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggrData1.getFifoPnlUnrealized()));
                siteMap.put(info,aggrData1);
                aggregationSubAssetArrayMap.put(subclass,siteMap);
                return;
            }
            siteMap = aggregationSubAssetArrayMap.get(subclass);
        }
        else {
            siteMap = new HashMap<String, AggregationSummaryData>();
        }
        aggrData1 = new AggregationSummaryData(subclass, info,
                quantity, costBasisMoney, positionValue, fifoPnlUnrealized);
        siteMap.put(info,aggrData1);
        aggregationSubAssetArrayMap.put(subclass,siteMap);
    }


    private void totalByLevel(String levelname, String info,
                             Integer quantity, Double costBasisMoney,
                             Double positionValue, Double fifoPnlUnrealized) {
        AggregationSummaryData aggr2Data;
        if (totalLevelArrayMap.containsKey(levelname)) {
            aggr2Data = totalLevelArrayMap.get(levelname);
            aggr2Data.setQuantity(addIntValue(quantity, aggr2Data.getQuantity()));
            aggr2Data.setCostBasisMoney(addDoubleValue(costBasisMoney, aggr2Data.getCostBasisMoney()));
            aggr2Data.setPositionValue(addDoubleValue(positionValue, aggr2Data.getPositionValue()));
            aggr2Data.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggr2Data.getFifoPnlUnrealized()));
        }
        else {
            aggr2Data = new AggregationSummaryData(levelname, info,
                    quantity, costBasisMoney,
                    positionValue,fifoPnlUnrealized);
            totalLevelArrayMap.put(levelname, aggr2Data);
        }
    }

    private void totalBySite(String sitename, String info,
                           Integer quantity, Double costBasisMoney,
                           Double positionValue, Double fifoPnlUnrealized) {
        AggregationSummaryData aggr2Data;
            if (totalSiteArrayMap.containsKey(sitename)) {
                aggr2Data = totalSiteArrayMap.get(sitename);
                aggr2Data.setQuantity(addIntValue(quantity, aggr2Data.getQuantity()));
                aggr2Data.setCostBasisMoney(addDoubleValue(costBasisMoney, aggr2Data.getCostBasisMoney()));
                aggr2Data.setPositionValue(addDoubleValue(positionValue, aggr2Data.getPositionValue()));
                aggr2Data.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggr2Data.getFifoPnlUnrealized()));
            }
        else {
                aggr2Data = new AggregationSummaryData(sitename, info,
                        quantity, costBasisMoney,
                        positionValue,fifoPnlUnrealized);
                totalSiteArrayMap.put(sitename, aggr2Data);
            }
    }

    private void totalByAsset(String assetclass, String info,
                             Integer quantity, Double costBasisMoney,
                             Double positionValue, Double fifoPnlUnrealized) {
        AggregationSummaryData aggr2Data;
        if (totalAssetArrayMap.containsKey(assetclass)) {
            aggr2Data = totalAssetArrayMap.get(assetclass);
            aggr2Data.setQuantity(addIntValue(quantity, aggr2Data.getQuantity()));
            aggr2Data.setCostBasisMoney(addDoubleValue(costBasisMoney, aggr2Data.getCostBasisMoney()));
            aggr2Data.setPositionValue(addDoubleValue(positionValue, aggr2Data.getPositionValue()));
            aggr2Data.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggr2Data.getFifoPnlUnrealized()));
        }
        else {
            aggr2Data = new AggregationSummaryData(assetclass, info,
                    quantity, costBasisMoney,
                    positionValue,fifoPnlUnrealized);
            totalAssetArrayMap.put(assetclass, aggr2Data);
        }
    }

    private void totalBySubAsset(String subclass, String info,
                              Integer quantity, Double costBasisMoney,
                              Double positionValue, Double fifoPnlUnrealized) {
        AggregationSummaryData aggr2Data;
        if (totalSubAssetArrayMap.containsKey(subclass)) {
            aggr2Data = totalSubAssetArrayMap.get(subclass);
            aggr2Data.setQuantity(addIntValue(quantity, aggr2Data.getQuantity()));
            aggr2Data.setCostBasisMoney(addDoubleValue(costBasisMoney, aggr2Data.getCostBasisMoney()));
            aggr2Data.setPositionValue(addDoubleValue(positionValue, aggr2Data.getPositionValue()));
            aggr2Data.setFifoPnlUnrealized(addDoubleValue(fifoPnlUnrealized, aggr2Data.getFifoPnlUnrealized()));
        }
        else {
            aggr2Data = new AggregationSummaryData(subclass, info,
                    quantity, costBasisMoney,
                    positionValue,fifoPnlUnrealized);
            totalSubAssetArrayMap.put(subclass, aggr2Data);
        }
    }

    private void addSiteLogo(String sitename, String siteid) {
        try {
            if (! siteLogo.containsKey(sitename)) {
                if (siteid != null && siteid.length() > 0)
                    siteLogo.put(sitename, sitename + siteid);
            }
        }
        catch (Exception ex) {
            return;
        }
    }

    public String getLogo(String sitename) {
        if (siteLogo.containsKey(sitename))
            return siteLogo.get(sitename);
        return null;
    }

}
