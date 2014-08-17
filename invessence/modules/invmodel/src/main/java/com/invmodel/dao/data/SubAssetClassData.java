package com.invmodel.dao.data;

import java.util.ArrayList;

public class SubAssetClassData {
    private String parentclass = null;
    private String subasset = "";
    private String color = "";
    private String subassetDisplayName = "";
    private Boolean include;

    public SubAssetClassData() {
        super();
    }

   public SubAssetClassData(String parentclass, String subasset,
                            String color, String subassetDisplayName,
                            Boolean include)
   {
      this.parentclass = parentclass;
      this.subasset = subasset;
      this.color = color;
      this.subassetDisplayName = subassetDisplayName;
      this.include = include;
   }

   public String getParentclass()
   {
      return parentclass;
   }

   public void setParentclass(String parentclass)
   {
      this.parentclass = parentclass;
   }

   public String getSubasset()
   {
      return subasset;
   }

   public void setSubasset(String subasset)
   {
      this.subasset = subasset;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public String getSubassetDisplayName()
   {
      return subassetDisplayName;
   }

   public void setSubassetDisplayName(String subassetDisplayName)
   {
      this.subassetDisplayName = subassetDisplayName;
   }

   public Boolean getInclude()
   {
      return include;
   }

   public void setInclude(Boolean include)
   {
      this.include = include;
   }
}
