package com.invmodel.portfolio.data;

import com.invmodel.Const.InvConst;

import static com.invmodel.utils.XMLBuilder.buildElement;
import static java.lang.String.valueOf;

public class PortfolioSecurityData
{

   private String ticker;
   private String name;
   private String color;
   private String type;
   private String style;
   private String assetclass;
   private String subclass;
   private double dailyprice;
   private double weight;
   private double expectedReturn;
   private double expenseRatio;
   private double secRisk;
   private double yield;
   private double shares;
   private double money;
   private int sortorder;
   private double tickerWeights;
   private String isin;
   private String cusip;
   private String ric;
   private String baseCurrency;
   private String modelCurrency;
   private Double exchangeRate;
   private Double baseShares;
   private Double basePrice;
   private Double baseMoney;

   public PortfolioSecurityData()
   {
      ticker = "";
      name = "";
      color = "";
      type = "";
      style = "";
      assetclass = "";
      subclass = "";
      dailyprice = 0.0;
      weight = 0.0;
      expectedReturn = 0.0;
      expenseRatio = 0.0;
      secRisk = 0.0;
      yield = 0.0;
      shares = 0.0;
      money = 0.0;
      sortorder = 0;
      tickerWeights = 0.0;
      isin = "";
      cusip = "";
      ric = "";
      baseCurrency = InvConst.MASTER_CURRENCY;
      modelCurrency = InvConst.MASTER_CURRENCY;
      exchangeRate = 1.0;
      baseShares = 0.0;
      basePrice = 0.0;
      baseMoney = 0.0;

   }

   public PortfolioSecurityData(String ticker, String name, String color,
                                String type, String style, String assetclass, String subclass,
                                double dailyprice, double weight, double expectedReturn, double expenseRatio,
                                double secRisk, double yield, double shares, double money, int sortorder,
                                double tickerWeight,
                                String isin, String cusip, String ric,
                                String baseCurrency, String modelCurrency, Double exchangeRate,
                                Double baseShares, Double basePrice, Double baseMoney )
   {
      super();
      resetPortfolioData(ticker, name, color,
                         type, style, assetclass, subclass,
                         dailyprice, weight, expectedReturn, expenseRatio,
                         secRisk, yield, shares, money, sortorder,
                         tickerWeight,
                         isin, cusip, ric,
                         baseCurrency, modelCurrency, exchangeRate,
                         baseShares, basePrice, baseMoney  );

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
      return money;
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
      this.tickerWeights = round(tickerWeights * 100, 2);
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

   public String getBaseCurrency()
   {
      return baseCurrency;
   }

   public void setBaseCurrency(String baseCurrency)
   {
      this.baseCurrency = baseCurrency;
   }

   public String getModelCurrency()
   {
      return modelCurrency;
   }

   public void setModelCurrency(String modelCurrency)
   {
      this.modelCurrency = modelCurrency;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(Double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public Double getBaseMoney()
   {
      return baseMoney;
   }

   public void setBaseMoney(Double baseMoney)
   {
      this.baseMoney = baseMoney;
   }

   public Double getBaseShares()
   {
      return baseShares;
   }

   public void setBaseShares(Double baseShares)
   {
      this.baseShares = baseShares;
   }

   public Double getBasePrice()
   {
      return basePrice;
   }

   public void setBasePrice(Double basePrice)
   {
      this.basePrice = basePrice;
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

   public void resetPortfolioData(String ticker, String name, String color,
                                  String type, String style, String assetclass, String subclass,
                                  double dailyprice, double weight, double expectedReturn, double expenseRatio,
                                  double secRisk, double yield, double shares, double money, int sortorder,
                                  double assetvalue,
                                  String isin, String cusip, String ric,
                                  String baseCurrency, String modelCurrency, Double exchangeRate,
                                  Double baseShares, Double basePrice, Double baseMoney)
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
      setIsin(isin);
      setCusip(cusip);
      setRic(ric);
      setBaseCurrency(baseCurrency);
      setModelCurrency(modelCurrency);
      setExchangeRate(exchangeRate);
      setBaseShares(baseShares);
      setBasePrice(basePrice);
      setBaseMoney(baseMoney);
   }

   @Override
   public String toString()
   {
      try
      {
         return getTicker() + "," +
            getName() + "," +
            getType() + "," +
            getStyle() + "," +
            getAssetclass() + "," +
            getSubclass() + "," +
            getDailyprice() + "," +
            getTickerWeights() + "," +
            getShares() + "," +
            getMoney();
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
         xmlData = xmlData + buildElement("ticker", getTicker()) +
            buildElement("name", getName()) +
            buildElement("color", getColor()) +
            buildElement("type", getType()) +
            buildElement("Style", getStyle()) +
            buildElement("Assetclass", getAssetclass()) +
            buildElement("Subclass", getSubclass()) +
            buildElement("dailyprice", valueOf(getDailyprice())) +
            buildElement("weights", valueOf(getWeight())) +
            buildElement("expectedReturn", valueOf(getExpectedReturn())) +
            buildElement("expenseRatio", valueOf(getExpenseRatio())) +
            buildElement("secRisk", valueOf(getSecRisk())) +
            buildElement("yield", valueOf(getYield())) +
            buildElement("shares", valueOf(getShares())) +
            buildElement("money", valueOf(getMoney())) +
            buildElement("sortorder", valueOf(getSortorder())) +
            buildElement("tickerWeights", valueOf(getTickerWeights())) +
            buildElement("isin", getIsin()) +
            buildElement("cusip", getCusip()) +
            buildElement("ric", getRic()) +
            buildElement("baseCurrency", getBaseCurrency()) +
            buildElement("modelCurrency", getModelCurrency()) +
            buildElement("exchangeRate", valueOf(getExchangeRate())) +
            buildElement("baseShares", valueOf(getBaseShares())) +
            buildElement("basePrice", valueOf(getBasePrice())) +
            buildElement("baseMoney", valueOf(getBaseMoney()));            ;

         return buildElement("PortfolioSecurityData", xmlData);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return (buildElement("Asset", getTicker()));
   }
}
