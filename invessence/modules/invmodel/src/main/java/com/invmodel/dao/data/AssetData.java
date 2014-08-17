package com.invmodel.dao.data;

import java.io.Serializable;
import java.util.ArrayList;

public class AssetData implements Serializable{
    private String groupname = "";
    private String asset = "";
    private String displayname = "";
    private double lbConstraint = 0.0;
    private double ubConstraint = 0.0;
    private String index = "";
    private double averageReturn = 0.0;
    private String color = "";
    private double risk_adjustment=0.0;
    private double end_allocation=0.0;
//    private ArrayList<SubAssetClassData> subAsset = new ArrayList<SubAssetClassData>();

    public AssetData() {
        super();
    }

    public AssetData(String groupname, String asset, String displayname, double lbConstraint, double ubConstraint,
                     String index, double averageReturn,
                     String color, double risk_adjustment, double end_allocation
                     //, SubAssetClassData subAsset
                     ) {
        super();
        this.groupname = groupname;
        this.asset = asset;
        this.displayname = displayname;
        this.lbConstraint = lbConstraint;
        this.ubConstraint = ubConstraint;
        this.index = index;
        this.averageReturn = averageReturn;
        this.color = color;
        this.risk_adjustment = risk_adjustment;
        this.end_allocation =  end_allocation;
/*
        if (subAsset != null) {
            setSubAsset(subAsset);
        }
*/
    }

   public String getGroupname()
   {
      return groupname;
   }

   public void setGroupname(String groupname)
   {
      this.groupname = groupname;
   }

   public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

   public String getDisplayname()
   {
      return displayname;
   }

   public void setDisplayname(String displayname)
   {
      this.displayname = displayname;
   }

   public double getUbConstraint() {
        return ubConstraint;
    }

    public void setUbConstraint(double ubConstraint) {
        this.ubConstraint = ubConstraint;
    }

    public double getLbConstraint() {
        return lbConstraint;
    }

    public void setLbConstraint(double lbConstraint) {
        this.lbConstraint = lbConstraint;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.index = color;
    }

    public double getAverageReturn() {
        return averageReturn;
    }

    public void setAverageReturn(double averageReturn) {
        this.averageReturn = averageReturn;
    }

    public double getRisk_adjustment() {
        return risk_adjustment;
    }

    public void setRisk_adjustment(double risk_adjustment) {
        this.risk_adjustment = risk_adjustment;
    }

    public double getEnd_allocation() {
        return end_allocation;
    }

    public void setEnd_allocation(double end_allocation) {
        this.end_allocation = end_allocation;
    }

/*
   public ArrayList<SubAssetClassData> getSubAsset() {
        return subAsset;
    }

   public void setSubAsset(ArrayList<SubAssetClassData> subAsset)
   {
      this.subAsset = subAsset;
   }

   public void setSubAsset(SubAssetClassData subAsset) {
        try {
            if (this.subAsset == null)  // If this list is empty (Not yet created, then allocate.
                this.subAsset = new ArrayList<SubAssetClassData>();

            if (!this.subAsset.contains(subAsset))  // If the data is not already loaded, then add.
                this.subAsset.add(subAsset);

        } catch (Exception e) {

        }
    }
*/

}
