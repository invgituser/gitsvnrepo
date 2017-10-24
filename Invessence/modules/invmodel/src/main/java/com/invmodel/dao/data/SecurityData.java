package com.invmodel.dao.data;

import com.google.common.base.Joiner;
import com.invmodel.Const.InvConst;

import java.util.Arrays;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;


public class SecurityData
{
   private String advisor;
   private String theme;
   private String ticker;
   private String name;
   private String assetclass;
   private String primeassetclass;
   private String type;
   private String style;
   private double dailyprice;
   private int    sortorder;
   private double rbsaWeight;
   private String assetcolor;
   private String primeassetcolor;
   private String securityAssetClass;
   private String securitySubAssetClass;
   private String isin;
   private String cusip;
   private String ric;
   private String settleCurrency;
   private String tradeCurrency;
   private Double exchangeRate;
   private Double settlePrice;

   public SecurityData()
   {
      super();
      advisor = "";
      theme = "";
      ticker = "";
      name = "";
      assetclass = "";
      primeassetclass = "";
      type = "";
      style = "";
      dailyprice = 0.0;
      sortorder = 0;
      rbsaWeight = 0.0;
      assetcolor = "";
      primeassetcolor = "";
      securityAssetClass = "";
      securitySubAssetClass = "";
      isin = "";
      cusip = "";
      ric = "";
      settleCurrency = InvConst.MASTER_CURRENCY;
      tradeCurrency = InvConst.MASTER_CURRENCY;
      exchangeRate = 1.0;

   }

   public SecurityData(String advisor, String theme, String ticker, String name,
                       String assetclass, String primeassetclass, String type, String style,
                       double dailyprice, int sortorder, double rbsaWeight,
                       String assetcolor, String primeassetcolor,
                       String securityAssetClass, String securitySubAssetClass,
                       String isin, String cusip, String ric,
                       String tradeCurrency, String settleCurrency, Double exchangeRate,
                       Double settlePrice)
   {
      super();
      resetSecurityData(advisor, theme, ticker, name,
                        assetclass, primeassetclass, type, style,
                        dailyprice, sortorder, rbsaWeight,
                        assetcolor, primeassetcolor,
                        securityAssetClass, securitySubAssetClass,
                        isin, cusip, ric,
                        tradeCurrency, settleCurrency,  exchangeRate, settlePrice
                        );
   }

   public SecurityData resetSecurityData(String advisor, String theme, String ticker, String name,
                                         String assetclass, String primeassetclass, String type, String style,
                                         double dailyprice, int sortorder, double rbsaWeight,
                                         String assetcolor, String primeassetcolor,
                                         String securityAssetClass, String securitySubAssetClass,
                                         String isin, String cusip, String ric,
                                         String tradeCurrency, String settleCurrency,  Double exchangeRate,
                                         Double settlePrice)
   {
      this.advisor = advisor;
      this.ticker = ticker;
      this.name = name;
      this.assetclass = assetclass;
      this.primeassetclass = primeassetclass;
      this.type = type;
      this.style = style;
      this.dailyprice = dailyprice;
      this.sortorder = sortorder;
      this.rbsaWeight = rbsaWeight;
      this.assetcolor = assetcolor;
      this.primeassetcolor = primeassetcolor;
      this.securityAssetClass = securityAssetClass;
      this.securitySubAssetClass = securitySubAssetClass;
      this.isin = isin;
      this.cusip = cusip;
      this.ric = ric;
      this.tradeCurrency = tradeCurrency;
      this.settleCurrency = settleCurrency;
      this.exchangeRate = exchangeRate;
      this.settlePrice = settlePrice;
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

   public String getTheme()
   {
      return theme;
   }

   public String getSecurityAssetClass()
   {
      return securityAssetClass;
   }

   public String getSecuritySubAssetClass()
   {
      return securitySubAssetClass;
   }

   public String getIsin()
   {
      return isin;
   }

   public String getCusip()
   {
      return cusip;
   }

   public String getRic()
   {
      return ric;
   }

   public String getSettleCurrency()
   {
      return settleCurrency;
   }

   public String getTradeCurrency()
   {
      return tradeCurrency;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public Double getSettlePrice()
   {
      return settlePrice;
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
         String str = ticker + ":" + Joiner.on(",").join(Arrays.asList(getTicker(),
                                                                       getName(),
                                                                       getAssetclass(),
                                                                       getPrimeassetclass(),
                                                                       getDailyprice(),
                                                                       getSortorder()
                                                                            ));
         return str;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return ticker;
   }

   public String toXml()
   {
      String xmlData = "";
      try
      {
         xmlData = xmlData + buildElement("Ticker", getTicker()) +
            buildElement("Name", getName()) +
            buildElement("AssetType", getAssetclass()) +
            buildElement("AssetSubtype", getPrimeassetclass()) +
            buildElement("DailyPrice", valueOf(getDailyprice())) +
            buildElement("Sortorder", valueOf(getSortorder()));
         return buildElement("SecurityInfo", xmlData);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("SecurityInfo", toString()));
   }

}
