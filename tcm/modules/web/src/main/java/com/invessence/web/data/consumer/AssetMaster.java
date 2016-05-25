package com.invessence.web.data.consumer;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/18/15
 * Time: 12:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class AssetMaster {
    private String assetclass;
    private String subclass;
    private String color;
    private Integer sortorder;

    public AssetMaster() {
    }

    public AssetMaster(String assetclass, String subclass, String color, Integer sortorder) {
        this.assetclass = assetclass;
        this.subclass = subclass;
        this.color = color;
        this.sortorder = sortorder;
    }

    public String getAssetclass() {
        return assetclass;
    }

    public void setAssetclass(String assetclass) {
        this.assetclass = assetclass;
    }

    public String getSubclass() {
        return subclass;
    }

    public void setSubclass(String subclass) {
        this.subclass = subclass;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSortorder() {
        return sortorder;
    }

    public void setSortorder(Integer sortorder) {
        this.sortorder = sortorder;
    }
}
