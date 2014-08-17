package com.invessence.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: ramesh
 * Date: 8/22/13
 * Time: 12:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagePortfolio implements Serializable
{

   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private String stock;
   private String subclass;
   private String symbol;
   private String name;
   private Double price;
   private Integer sortorder;
   private Integer share;
   private Double value;
   private Integer totalshare;
   private Double totalvalue;

   private Integer age;
   private Integer horizon;
   private String risk;
   private Double initialinvestment;

   private String stockscolor;
   private String bondscolor;
   private String commoditiescolor;
   private String cashcolor;
   private Integer slices;

   public ManagePortfolio(String stock, String subclass, String symbol, String name, Integer share, Double price, Double value, Integer sortorder, Integer totalshare, Double totalvalue)
   {
      this.stock = stock;
      this.subclass = subclass;
      this.symbol = symbol;
      this.name = name;
      this.price = price;
      this.sortorder = sortorder;
      this.share = share;
      this.value = value;
      this.totalshare = totalshare;
      this.totalvalue = totalvalue;
   }

   public String getStock()
   {
      return stock;
   }

   public void setStock(String stock)
   {
      this.stock = stock;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
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

   public Integer getTotalshare()
   {
      return totalshare;
   }

   public void setTotalshare(Integer totalshare)
   {
      this.totalshare = totalshare;
   }

   public Double getTotalvalue()
   {
      return totalvalue;
   }

   public void setTotalvalue(Double totalvalue)
   {
      this.totalvalue = totalvalue;
   }

   public Integer getAge()
   {
      return age;
   }

   public void setAge(Integer age)
   {
      this.age = age;
   }

   public Integer getHorizon()
   {
      return horizon;
   }

   public void setHorizon(Integer horizon)
   {
      this.horizon = horizon;
   }

   public String getRisk()
   {
      return risk;

   }

   public void setRisk(String risk)
   {
      this.risk = risk;
   }

   public Double getInitialinvestment()
   {
      return initialinvestment;
   }

   public void setInitialinvestment(Double initialinvestment)
   {
      this.initialinvestment = initialinvestment;
   }

   public String getStockscolor()
   {
      return stockscolor;
   }

   public void setStockscolor(String stockscolor)
   {
      this.stockscolor = stockscolor;
   }

   public String getBondscolor()
   {
      return bondscolor;
   }

   public void setBondscolor(String bondscolor)
   {
      this.bondscolor = bondscolor;
   }

   public String getCommoditiescolor()
   {
      return commoditiescolor;
   }

   public void setCommoditiescolor(String commoditiescolor)
   {
      this.commoditiescolor = commoditiescolor;
   }

   public String getCashcolor()
   {
      return cashcolor;
   }

   public void setCashcolor(String cashcolor)
   {
      this.cashcolor = cashcolor;
   }

   public Integer getSlices()
   {
      return slices;
   }

   public void setSlices(Integer slices)
   {
      this.slices = slices;
   }
}

