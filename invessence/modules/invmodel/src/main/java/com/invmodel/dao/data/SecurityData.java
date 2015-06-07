package com.invmodel.dao.data;

import com.google.common.base.Joiner;

import java.util.Arrays;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;


public class SecurityData
{
   private String advisor = "";
   private String theme = "";
   private String ticker = "";
   private String name = "";
   private String assetclass = "";
   private String primeassetclass = "";
   private String subassetclass = "";
   private String type = "";
   private String style;
   private double dailyprice = 0.0;
   private int    sortorder = 0;
   private double rbsaWeight = 0.0;
   private String assetcolor = "";
   private String primeassetcolor = "";

   public SecurityData()
   {
      super();
   }

   public SecurityData(String advisor, String theme, String ticker, String name,
                       String assetclass, String primeassetclass, String subassetclass, String type, String style,
                       double dailyprice, int sortorder, double rbsaWeight,
                       String assetcolor, String primeassetcolor)
   {
      super();
      resetSecurityData(advisor, theme, ticker, name,
                        assetclass, primeassetclass, subassetclass, type, style,
                        dailyprice, sortorder, rbsaWeight,
                        assetcolor, primeassetcolor);
   }

   public SecurityData resetSecurityData(String advisor, String theme, String ticker, String name,
                                         String assetclass, String primeassetclass, String subassetclass, String type, String style,
                                         double dailyprice, int sortorder, double rbsaWeight,
                                         String assetcolor, String primeassetcolor)
   {
      this.advisor = advisor;
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.primeassetclass = primeassetclass;
      this.subassetclass = subassetclass;
      this.type = type;
      this.style = style;
      this.dailyprice = dailyprice;
      this.sortorder = sortorder;
      this.rbsaWeight = rbsaWeight;
      this.assetcolor = assetcolor;
      this.primeassetcolor = primeassetcolor;
      return this;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public String getTicker()
   {
      return ticker;
   }

   public String getName()
   {
      return name;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public String getPrimeassetclass()
   {
      return primeassetclass;
   }

   public String getSubassetclass()
   {
      return subassetclass;
   }

   public String getType()
   {
      return type;
   }

   public String getStyle()
   {
      return style;
   }

   public double getDailyprice()
   {
      return dailyprice;
   }

   public int getSortorder()
   {
      return sortorder;
   }

   public double getRbsaWeight()
   {
      return rbsaWeight;
   }

   public String getAssetcolor()
   {
      return assetcolor;
   }

   public String getPrimeassetcolor()
   {
      return primeassetcolor;
   }

   public String getHeader()
   {
      String str = Joiner.on(",").join(Arrays.asList("Ticker", "Name",
                                                     "Assetclass", "AssetSubType", "Price", "Sortorder"));
      return str;
   }


   @Override
   public String toString()
   {
      try
      {
         String str = this.ticker + ":" + Joiner.on(",").join(Arrays.asList(getTicker(),
                                                                            getName(),
                                                                            getAssetclass(),
                                                                            getSubassetclass(),
                                                                            getDailyprice(),
                                                                            getSortorder()
                                                                            ));
         return str;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return this.ticker;
   }

   public String toXml()
   {
      String xmlData = "";
      try
      {
         xmlData = xmlData + buildElement("Ticker", getTicker()) +
            buildElement("Name", getName()) +
            buildElement("AssetType", getAssetclass()) +
            buildElement("AssetSubtype", getSubassetclass()) +
            buildElement("DailyPrice", valueOf(getDailyprice())) +
            buildElement("Sortorder", valueOf(getSortorder()));
         return buildElement("SecurityInfo", xmlData);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("SecurityInfo", this.toString()));
   }

}
