package com.invessence.data.consumer;

import com.invessence.converter.JavaUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/6/15
 * Time: 4:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AggregationData {
    private ArrayList<AggregationDetailData> aggregationDetailDataArray; // Map of Accounts with details

    private Map<String,String> siteLogo;                               // Map of Logo
    private Map<String,AssetMaster> assetMasterMap;                    // Map of Colors

    private Map<String, AggregationSummaryData> totalTypeMap;      // Array of Level for total
    private Map<String, AggregationSummaryData> totalSiteMap;      // Array of Sites + Accounts for total
    private Map<String, AggregationSummaryData> totalAssetMap;     // Array of Site + Asset for total

    private ArrayList<AggregationSummaryData> assetList;           // Array of Assets for total

    private Double  grandTotalAssetValue;
    private Double  grandTotalLiabilityValue;
    private Double  grandAbsTotalValue;


    public AggregationData() {
        aggregationDetailDataArray = new ArrayList<AggregationDetailData>();
        assetMasterMap = new LinkedHashMap<String,AssetMaster>();
        grandTotalAssetValue = 0.0;
        grandTotalLiabilityValue = 0.0;
        grandAbsTotalValue = 0.0;

    }

    public void addData(Integer sortorder, String src, String sitename, String siteid, String datatype,
                        Long acctnum, String clientAccountID, String acctname,
                        String currencyPrimary, String assetClass, String color, String subclass,
                        Double fxRateToBase, String symbol, String description, String reportDate, String side,
                        Integer quantity, Double costBasisPrice, Double costBasisMoney, Double markPrice,
                        Double positionValue, Double fifoPnlUnrealized) {

        datatype = JavaUtil.UppercaseFirstLetters(datatype);
        assetClass = JavaUtil.UppercaseFirstLetters(assetClass);
        subclass = JavaUtil.UppercaseFirstLetters(subclass);

        if (side != null && side.toUpperCase().startsWith("S")) {
            costBasisMoney = costBasisMoney * -1;
            positionValue = positionValue * -1;
            fifoPnlUnrealized = positionValue - costBasisMoney;
        }

        addSiteLogo(sitename, siteid);

        if (aggregationDetailDataArray == null) {
            aggregationDetailDataArray = new ArrayList<AggregationDetailData>();
        }

        AggregationDetailData aggrData = new AggregationDetailData(sortorder,  src,
                sitename, siteid, datatype,
                acctnum,  clientAccountID,  acctname,
                currencyPrimary,  assetClass,  color,  subclass,
                fxRateToBase,  symbol,  description,  reportDate,  side,
                quantity,  costBasisPrice,  costBasisMoney,  markPrice,
                positionValue,  fifoPnlUnrealized);

        aggregationDetailDataArray.add(aggrData);

        if (datatype.toUpperCase().equals("INVESTMENT") || datatype.toUpperCase().equals("CASH")) {
            grandTotalAssetValue = addDoubleValue(grandTotalAssetValue, Math.abs(positionValue));
        }
        else {
            if (positionValue < 0.0) {
                grandTotalLiabilityValue = addDoubleValue(grandTotalLiabilityValue, Math.abs(positionValue));
            }
        }

        grandAbsTotalValue = addDoubleValue(grandAbsTotalValue, Math.abs(positionValue));


    }

    public void addAssetMasterData(String assetclass, String subclass,
                                   String color, Integer sortorder) {
        if (assetMasterMap == null) {
            assetMasterMap = new LinkedHashMap<String, AssetMaster>();
        }

        if (! assetMasterMap.containsKey(assetclass)) {
            AssetMaster assetMasterData = new AssetMaster(assetclass, subclass, color, sortorder);
            assetMasterMap.put(assetclass,assetMasterData);
        }


    }

    public void addTotal() {
        totalTypeMap = getAllDataTypes("TYPE");
        totalSiteMap = getAllDataTypes("SITE");
        totalAssetMap = getAllDataTypes("ASSETS");
        assetList = buildAssetList();
    }

    public Double getPercentValue(Double value1, Double value2) {
        if (value1 == null)
            return 0.0;

        if (value2 == null)
            return 100.0;

        return Math.abs(value1) / Math.abs(value2);
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

    public Double getGrandTotalAssetValue() {
        return grandTotalAssetValue;
    }

    public Double getGrandTotalLiabilityValue() {
        return grandTotalLiabilityValue;
    }

    public Double getGrandAbsTotalValue() {
        return grandAbsTotalValue;
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

    public String getAssetColor(String assetclass) {

        if (assetclass != null) {
            if (assetMasterMap.containsKey(assetclass)) {
                return assetMasterMap.get(assetclass).getColor();
            }
        }
        return "#F5F5F5";
    }

    public String getLogo(String sitename) {
        if (siteLogo.containsKey(sitename))
            return siteLogo.get(sitename);
        return null;
    }

    private void buildMap(Map<String, AggregationSummaryData> dataMap,
                         String datakey, String info, String subListKey,
                         AggregationDetailData detail) {

        Double costBasisMoney;
        Double positionValue;
        Double fifoPnlUnrealized;
        Double percentAllocation;
        AggregationSummaryData summaryData;
        String color;


        color = null;
        if (assetMasterMap != null) {
            if (assetMasterMap.containsKey(subListKey))
                color = assetMasterMap.get(subListKey).getColor();
            else
                color = null;
        }
        if (dataMap.containsKey(datakey)) {
            // If data found, then add total and add detail record
            summaryData = dataMap.get(datakey);
            costBasisMoney = addDoubleValue(detail.getCostBasisMoney(), summaryData.getCostBasisMoney());
            positionValue = addDoubleValue(detail.getPositionValue(), summaryData.getPositionValue());
            fifoPnlUnrealized = addDoubleValue(detail.getFifoPnlUnrealized(), summaryData.getFifoPnlUnrealized());
            percentAllocation = getPercentValue(positionValue,grandAbsTotalValue);
            summaryData.setCostBasisMoney(costBasisMoney);
            summaryData.setPositionValue(positionValue);
            summaryData.setFifoPnlUnrealized(fifoPnlUnrealized);
            summaryData.setPercentAllocation(percentAllocation);
            summaryData.addAlternateMap(subListKey, color,
                    detail.getCostBasisMoney(), detail.getPositionValue(),
                    detail.getFifoPnlUnrealized(), percentAllocation);
            dataMap.put(datakey, summaryData);
        }
        else {
            // Create new Summary record
            positionValue = detail.getPositionValue();
            percentAllocation = getPercentValue(positionValue, grandAbsTotalValue);
            summaryData = new AggregationSummaryData(datakey, info,
                                detail.getCostBasisMoney(),
                                detail.getPositionValue(),
                                detail.getFifoPnlUnrealized(),
                                percentAllocation);
            summaryData.addAlternateMap(subListKey, color,
                    detail.getCostBasisMoney(), detail.getPositionValue(),
                    detail.getFifoPnlUnrealized(), percentAllocation);
            dataMap.put(datakey, summaryData);
        }
    }

    private ArrayList<AggregationSummaryData> buildArrayList(Map<String, AggregationSummaryData> dataMap) {
       ArrayList<AggregationSummaryData> arrayList = new ArrayList<AggregationSummaryData>();
       try {
           if (dataMap != null) {
              for (AggregationSummaryData data: dataMap.values()) {
                  arrayList.add(data);
              }
           }
           return arrayList;
       }
       catch (Exception ex) {
          return null;
       }
    }

    public Map<String, AggregationSummaryData> getAllDataTypes(String dataname) {
        String datakey;
        String info;
        String subListKey;
        AggregationDetailData aggrData;

        Integer datapos = 9;

        if (dataname.toUpperCase().equals("ASSETS"))
            datapos = 0;
        else
        if (dataname.toUpperCase().equals("SITE"))
            datapos = 1;
        else
        if (dataname.toUpperCase().equals("TYPE"))
            datapos = 2;

        Map<String, AggregationSummaryData> dataMap = new HashMap<String, AggregationSummaryData>();

        try {
            datakey = null;
            info = null;
            subListKey = null;
            for (AggregationDetailData detail : aggregationDetailDataArray) {
                switch (datapos) {
                    case 0:
                        if (detail.getDatatype().toUpperCase().equals("INVESTMENT")) {
                            datakey = detail.getSitename();
                            info = detail.getSiteid();
                            subListKey = detail.getAssetClass();
                        }
                        else
                            continue;
                        break;
                    case 1:
                        datakey = detail.getSitename();
                        info = detail.getSiteid();
                        subListKey = detail.getAcctname();
                        break;
                    case 2:
                        datakey = detail.getDatatype();
                        info = detail.getSitename();
                        subListKey = detail.getSitename();
                        break;
                    default:
                        break;
                }
                if (datakey != null) {
                    buildMap(dataMap, datakey, info, subListKey, detail);
                }
            }
        }
        catch (Exception ex) {
            return null;
        }
        return dataMap;
    }

    public ArrayList<AggregationSummaryData> getTotalTypeArray() {
        return buildArrayList(totalTypeMap);
    }

    public ArrayList<AggregationSummaryData> getTotalSiteArray() {
        return buildArrayList(totalSiteMap);
    }

    public ArrayList<AggregationSummaryData> getTotalAssetArray() {
        return buildArrayList(totalAssetMap);
    }

    public Map<String, AggregationSummaryData> getTotalTypeMap() {
        return totalTypeMap;
    }

    public Map<String, AggregationSummaryData> getTotalSiteMap() {
        return totalSiteMap;
    }

    public Map<String, AggregationSummaryData> getTotalAssetMap() {
        return totalAssetMap;
    }

    public Map<String, AssetMaster> getAssetMasterMap() {
        return assetMasterMap;
    }

    public ArrayList<AggregationSummaryData> getAssetList() {
        return assetList;
    }

    public ArrayList<AssetMaster> getAssetMasterList() {
        if (assetMasterMap == null)
            return null;

        ArrayList<AssetMaster> arrayList = new ArrayList<AssetMaster>();
        for (String key: assetMasterMap.keySet()) {
            arrayList.add(assetMasterMap.get(key));
        }
        return arrayList;
    }

    public ArrayList<AggregationSummaryData> buildAssetList() {
        if (assetMasterMap == null)
            return null;

        ArrayList<AggregationSummaryData> arrayList = new ArrayList<AggregationSummaryData>();
        AggregationSummaryData data;
        Double posValue;
        Double percent;
        for (String assetname : assetMasterMap.keySet()) {
            data = new AggregationSummaryData(
                    assetname, assetMasterMap.get(assetname).getColor(),
                    0.0,
                    0.0,
                    0.0,
                    0.0);
            posValue = 0.0;
            percent = 0.0;
            if (totalAssetMap != null) {
                // For every sitename, find its assetclass.
                for (String sitename : totalAssetMap.keySet()) {
                    if (totalAssetMap.get(sitename).getAlternateMap() != null) {
                        if (totalAssetMap.get(sitename).getAlternateMap().containsKey(assetname)) {
                            posValue = posValue + totalAssetMap.get(sitename).getAlternateMap().get(assetname).getPositionValue();
                        }
                    }
                }
                data.setPositionValue(posValue);
                data.setPercentAllocation(posValue / getGrandTotalAssetValue());
            }

            arrayList.add(data);
        }
        return arrayList;

    }

}
