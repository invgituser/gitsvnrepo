package com.invessence.web.data;

import java.io.Serializable;


public class DataPortfolio implements Serializable
{

   /**
    *
    */
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

   public DataPortfolio() {}

   public DataPortfolio(String assetType, String subclass, String color,
                        String symbol, String name, Integer share, Double price,
                        Double value, Integer sortorder, Double tickerWeight, Double weight)
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

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public String getNamePlusSymbol() {
      String output = null ;
      if (this.name != null)
         output = this.name;

      if (this.symbol != null && ! this.symbol.equalsIgnoreCase("cash"))
      {
         if (output != null)
            output = output + " - " + this.symbol;
         else
            output = this.symbol;
      }
      return output;

   }
}
