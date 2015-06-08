package com.invmodel.portfolio.data;

import java.util.*;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;

public class PortfolioSecurityData
{

   private String ticker = "";
   private String name = "";
   private String color = "";
   private String type = "";
   private String style = "";
   private String assetclass = "";
   private String subclass = "";
   private double dailyprice = 0.0;
   private double weight = 0.0;
   private double expectedReturn = 0.0;
   private double expenseRatio;
   private double secRisk = 0.0;
   private double yield = 0.0;
   private double shares = 0.0;
   private double money = 0.0;
   private int sortorder = 0;
   private double tickerWeights = 0.0;

   public PortfolioSecurityData()
   {
   }

   public PortfolioSecurityData(String ticker, String name, String color,
                                String type, String style, String assetclass, String subclass,
                                double dailyprice, double weight, double expectedReturn, double expenseRatio,
                                double secRisk, double yield, double shares, double money, int sortorder,
                                double tickerWeight)
   {
      super();
      setTicker(ticker);
      setName(name);
      setColor(color);
      setType(type);
      setStyle(style);
      setAssetclass(assetclass);
      setSubclass(subclass);
      setDailyprice(dailyprice);
      setWeight(weight);
      setExpectedReturn(expectedReturn);
      setExpenseRatio(expenseRatio);
      setSecRisk(secRisk);
      setYield(yield);
      setShares(shares);
      setMoney(money);
      setSortorder(sortorder);
      setTickerWeights(tickerWeight);
   }


   public String getTicker()
   {
      return ticker;
   }

   private void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getName()
   {
      return name;
   }

   private void setName(String name)
   {
      this.name = name;
   }

   public String getType()
   {
      return type;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   private void setType(String type)
   {
      this.type = type;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public String getStyle()
   {
      return style;
   }

   public void setStyle(String style)
   {
      this.style = style;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public double getDailyprice()
   {
      return dailyprice;
   }

   private void setDailyprice(double dailyprice)
   {
      this.dailyprice = round(dailyprice, 4);
   }

   public double getWeight()
   {
      return weight;
   }

   public int getWeightsAsInt()
   {
      return (int) Math.round(weight * 100.00);
   }

   public double getExpectedReturn()
   {
      return expectedReturn;
   }

   private void setExpectedReturn(double expectedReturn)
   {
      this.expectedReturn = round(expectedReturn, 2);
   }

   public double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public double getSecRisk()
   {
      return secRisk;
   }

   public void setSecRisk(double secRisk)
   {
      this.secRisk = round(secRisk, 2);
   }

   public double getYield()
   {
      return yield;
   }

   public void setYield(double yield)
   {
      this.yield = round(yield, 3);
   }

   private void setWeight(double weight)
   {
      this.weight = round(weight, 4);
   }

   public double getShares()
   {
      return shares;
   }

   public void setShares(double shares)
   {
      this.shares = round(shares, 2);
   }

   public double getMoney()
   {
      return this.money;
   }

   public void setMoney(double money)
   {
      this.money = round(money, 2);
   }

   public int getSortorder()
   {
      return sortorder;
   }

   private void setSortorder(int sortorder)
   {
      this.sortorder = sortorder;
   }

   public double getTickerWeights()
   {
      return tickerWeights;
   }

   public void setTickerWeights(double tickerWeights)
   {
      this.tickerWeights = round(tickerWeights *100, 2);
   }


   public double round(double value, int digits)
   {
      double calcValue;
      double power;
      try
      {
         if (digits > 8)
         {
            power = Math.pow(10, 8);
         }
         else
         {
            power = Math.pow(10, digits);
         }
         calcValue = (Math.round(value * power) / power);
         return calcValue;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return value;
   }


   public PortfolioSecurityData resetPortfolioData(String ticker, String name, String color,
                                                   String type, String style, String assetclass, String subclass,
                                                   double dailyprice, double weight, double expectedReturn, double expenseRatio,
                                                   double secRisk, double yield, double shares, double money, int sortorder,
                                                   double assetvalue)
   {
      setTicker(ticker);
      setName(name);
      setColor(color);
      setType(type);
      setStyle(style);
      setAssetclass(assetclass);
      setSubclass(subclass);
      setDailyprice(dailyprice);
      setWeight(weight);
      setExpectedReturn(expectedReturn);
      setExpenseRatio(expenseRatio);
      setSecRisk(secRisk);
      setYield(yield);
      setShares(shares);
      setMoney(money);
      setSortorder(sortorder);
      setTickerWeights(assetvalue);
      return this;
   }

   @SuppressWarnings("UnusedDeclaration")
   public ArrayList<String> getPortfolioDataAsArray()
   {
      try
      {
         ArrayList<String> data = new ArrayList<String>();
         data.add(ticker);
         data.add(name);
         data.add(type);
         data.add(style);
         data.add(assetclass);
         data.add(subclass);
         data.add(valueOf(this.dailyprice));
         data.add(valueOf(getWeightsAsInt()));
         data.add(valueOf(this.shares));
         data.add(valueOf(this.money));
         data.add(valueOf(this.sortorder));
         data.add(valueOf(this.tickerWeights));
         return data;
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
      return null;
   }

   public ArrayList<String> getPortfolioDataHeader()
   {
      try
      {
         ArrayList<String> data = new ArrayList<String>();
         data.add("Ticker");
         data.add("Name");
         data.add("Type");
         data.add("Style");
         data.add("Assetclass");
         data.add("Subclass");
         data.add("Price");
         data.add("Weight");
         data.add("Shares");
         data.add("Value");
         data.add("Sortorder");
         return data;
      }
      catch (Exception e)
      {
       e.printStackTrace();
      }
      return null;
   }

   @Override
   public String toString()
   {
      try
      {
         return getTicker() + "," +
            getName()  + "," +
            getType() + "," +
            getStyle() + "," +
            getAssetclass() + "," +
            getSubclass() + "," +
            valueOf(getDailyprice()) + "," +
            valueOf(getTickerWeights()) + "," +
            valueOf(getShares()) + "," +
            valueOf(getMoney());
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
         xmlData = xmlData + buildElement("ticker", getTicker()) +
            buildElement("name", getName()) +
            buildElement("type", getType()) +
            buildElement("Style", getStyle()) +
            buildElement("Assetclass", getAssetclass()) +
            buildElement("Subclass", getSubclass()) +
            buildElement("dailyprice", valueOf(getDailyprice())) +
            buildElement("weights", valueOf(getTickerWeights())) +
            buildElement("shares", valueOf(getShares())) +
            buildElement("money", valueOf(getMoney())) +
            buildElement("sortorder", valueOf(getSortorder()));
         return buildElement("PortfolioSecurityData", xmlData);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("Asset", getTicker()));
   }
}
