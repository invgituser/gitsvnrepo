package com.invessence.web.data;

import java.io.Serializable;


public class DataPortfolio implements Serializable
{

   private static final long serialVersionUID = 1L;
   private String assetType;
   private String subclass;
   private String symbol;
   private String name;
   private String color;
   private Double price;
   private Integer sortorder;
   private Integer share;
   private Double value;
   private Double tickerWeight;
   private Double weight;
   private String isin;
   private String cusip;
   private String ric;
   private String tradeCurrency;
   private Double exchangeRate;
   private String settleCurrency;
   private Double settleShare;
   private Double settlePrice;
   private Double settleValue;

   public DataPortfolio() {}

   public DataPortfolio(String assetType, String subclass, String color,
                        String symbol, String name, Integer share, Double price,
                        Double value, Integer sortorder, Double tickerWeight, Double weight,
                        String isin, String cusip, String ric,
                        String tradeCurrency, Double exchangeRate, String settleCurrency,
                        Double settleShare, Double settlePrice, Double settleValue)
   {
      this.assetType = assetType;
      this.subclass = subclass;
      this.color = color;
      this.symbol = symbol;
      this.name = name;
      this.price = price;
      this.sortorder = sortorder;
      this.share = share;
      this.value = value;
      this.tickerWeight = tickerWeight;
      this.weight = weight;
      this.isin = isin;
      this.cusip = cusip;
      this.ric = ric;
      this.tradeCurrency = tradeCurrency;
      this.exchangeRate = exchangeRate;
      this.settleCurrency = settleCurrency;
      this.settleShare = settleShare;
      this.settlePrice = settlePrice;
      this.settleValue = settleValue;
   }

   public String getAssetType()
   {
      return assetType;
   }

   public void setAssetType(String assetType)
   {
      this.assetType = assetType;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   public Integer getShare()
   {
      return share;
   }

   public void setShare(Integer share)
   {
      this.share = share;
   }

   public Double getValue()
   {
      return value;
   }

   public void setValue(Double value)
   {
      this.value = value;
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }

   public Double getTickerWeight()
   {
      return tickerWeight;
   }

   public void setTickerWeight(Double tickerWeight)
   {
      this.tickerWeight = tickerWeight;
   }

   public String getIsin()
   {
      return isin;
   }

   public void setIsin(String isin)
   {
      this.isin = isin;
   }

   public String getCusip()
   {
      return cusip;
   }

   public void setCusip(String cusip)
   {
      this.cusip = cusip;
   }

   public String getRic()
   {
      return ric;
   }

   public void setRic(String ric)
   {
      this.ric = ric;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public String getTradeCurrency()
   {
      return tradeCurrency;
   }

   public void setTradeCurrency(String tradeCurrency)
   {
      this.tradeCurrency = tradeCurrency;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(Double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public String getSettleCurrency()
   {
      return settleCurrency;
   }

   public void setSettleCurrency(String settleCurrency)
   {
      this.settleCurrency = settleCurrency;
   }

   public Double getSettleShare()
   {
      return settleShare;
   }

   public void setSettleShare(Double settleShare)
   {
      this.settleShare = settleShare;
   }

   public Double getSettlePrice()
   {
      return settlePrice;
   }

   public void setSettlePrice(Double settlePrice)
   {
      this.settlePrice = settlePrice;
   }

   public Double getSettleValue()
   {
      return settleValue;
   }

   public void setSettleValue(Double settleValue)
   {
      this.settleValue = settleValue;
   }

   public String getNamePlusSymbol() {
      String output = null ;
      if (name != null)
         output = name;

      if (symbol != null && !symbol.equalsIgnoreCase("cash"))
      {
         if (output != null)
            output = output + " - " + symbol;
         else
            output = symbol;
      }
      return output;

   }
}
