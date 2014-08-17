package com.invessence.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.invessence.util.XMLBuilder.buildElement;
import static java.lang.String.valueOf;

@SuppressWarnings("rawtypes")
public class AssetClass {
    private Map<String, ArrayList> assetclass = new HashMap<String, ArrayList>();
    private double totalWeight = 0.0;
    public int dataElement = 2;    // Array position of Weight
    public int weightPosition = 0;    // Array position of Weight
    public int colorPosition = 1;     // Array position of Color


    public AssetClass() {
    }

    public Map<String, ArrayList> getAssetData() {
        return assetclass;
    }

    public Object getData(String asset, int position) {
        if (position > 0 && position < dataElement)
            return assetclass.get(asset).get(position);
        else
            return null;
    }

    public int getAssetWeightRounded(String asset) {
        try {
            int assetWeight = 0;
            if (assetclass.get(asset).get(weightPosition) != null) {
                assetWeight = (int) Math.round((Double) assetclass.get(asset).get(weightPosition) * 100);
            }
            return assetWeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public double getAssetWeight(String asset) {
        try {
            double assetWeight = 0.0;
            if (assetclass.get(asset).get(weightPosition) != null) {
                assetWeight = (Double) assetclass.get(asset).get(weightPosition);
            }
            return assetWeight;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void setAssetWeight(String asset, double weight) {
        try {
            ArrayList dataElement;
            if (assetclass.containsKey(asset)) {
                dataElement = assetclass.get(asset);
                dataElement.set(weightPosition, (Object) weight);
                assetclass.put(asset, dataElement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAssetColor(String asset) {
        try {
            String color = "";
            if (assetclass.get(asset).get(colorPosition) != null) {
                color = (String) assetclass.get(asset).get(colorPosition);
            }
            return color;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    public void setAssetClass(String asset, double weight, String color) {
        try {
            ArrayList dataElement = new ArrayList();
            if (!assetclass.containsKey(asset)) {
                dataElement.add(weightPosition, weight);
                addTotalWeight(weight);
                dataElement.add(colorPosition, color);
                assetclass.put(asset, dataElement);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean isExists(String asset) {
        try {
            return (assetclass.containsKey(asset));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(double totalWeight) {
        this.totalWeight = totalWeight;
    }

    public void addTotalWeight(double totalWeight) {
        this.totalWeight = this.totalWeight + totalWeight;
    }

    @Override
    public String toString() {
        try {
            String str = "";
            Iterator it = assetclass.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                String data = "";
                String tmp = "";
                for (int j = 0; j < assetclass.get(key).size(); j++) {
                    Object obj = (Object) assetclass.get(key).get(j);
                    switch (j) {
                        case 0:
                            tmp = String.valueOf(getAssetWeightRounded(key));
                            break;
                        case 1:
                            tmp = (String) obj;
                            break;
                        default:
                            tmp = (String) obj;
                    }
                    /*
					if (obj.getClass().equals(Double.TYPE)) 
						tmp = String.valueOf((double) obj);
					else
						if (obj.getClass().equals(Integer.TYPE))
							tmp = String.valueOf((int) obj);
						else
							tmp = (String) obj;
					*/
                    data = data + ((j == 0) ? tmp : "," + tmp);
                }
                str = str + key + ":" + data + "\n";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return " ";
    }

    public String toXml() {
        String xmlData = "";
        for (String keys : assetclass.keySet()) {
            xmlData = xmlData + buildElement("Asset", keys) +
                    buildElement("Weight", valueOf((Double) assetclass.get(keys).get(weightPosition))) +
                    buildElement("Color", (String) assetclass.get(keys).get(colorPosition));
        }
        return buildElement("AssetAllocation", xmlData);
    }


}
