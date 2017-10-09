package com.invmodel.portfolio.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.invmodel.utils.XMLBuilder.buildElement;


public class Portfolio
{

   private Map<String, PortfolioSecurityData> portfolio = new HashMap<String, PortfolioSecurityData>(); //Key=Ticker, Value=Data;
   private ArrayList<String> tickers = new ArrayList<String>();
   private String theme;
   private double totalMoney = 0.0;
   private double cashMoney = 0.0;
   private double expReturns = 0.0;
   private double totalRisk = 0.0;
   private double avgCost = 0.0;
   private double avgExpense = 0.0;
   private double totalCost = 0.0;
   private double upperTotalMoney = 0.0;
   private double lowerTotalMoney = 0.0;
   private double recurInvestments = 0.0;
   private String baseCurrency;


   private double totalCapitalGrowth = 0.0;
   private double actualInvestments = 0.0;
   private Map<String, ArrayList> assetMap = new HashMap<String,ArrayList>();
   private Map<String,PortfolioSubclass> subclassMap = new HashMap<String,PortfolioSubclass>();
   private ArrayList<PortfolioSubclass> subclasslist = new ArrayList<PortfolioSubclass>();

   public Portfolio()
   {
      tickers.clear();
      portfolio.clear();
   }

   public Portfolio(String ticker, String name, String color,
                    String type, String style, String assetclass, String subclass,
                    double dailyprice, double weight, double expectedReturn, double expenseRatio,
                    double secRisk, double yield, double shares, double money, int sortorder,
                    double tickerWeight,
                    String isin, String cusip, String ric,
                    String baseCurrency, String destCurrency, Double exchangeRate,
                    Double baseShares, Double basePrice, Double baseMoney  )
   {
      try
      {
         tickers.clear();
         portfolio.clear();
         setPortfolio(ticker, name, color,
                      type, style, assetclass, subclass,
                      dailyprice, weight, expectedReturn, expenseRatio,
                      secRisk, yield, shares, money, sortorder, tickerWeight,
                      isin, cusip, ric,
                      baseCurrency, destCurrency, exchangeRate,
                      baseShares, basePrice, baseMoney);
      }
      catch (Exception e)
      {

      }
   }

   protected Portfolio(Portfolio original)
   {
      portfolio = original.portfolio;
      totalMoney = original.totalMoney;
      cashMoney = original.cashMoney;
   }

   public Portfolio copy()
   {
      return new Portfolio(this);
   }

   public String getsubclasskey(String assetclass, String subclass) {
      if (assetclass == null || assetclass.length() == 0)
         assetclass = "UNUSED";
      if (subclass == null || subclass.length() == 0)
         subclass = "UNUSED";

      return  assetclass.toUpperCase() + "." + subclass.toUpperCase();
   }

   public double getRecurInvestments()
   {
      return recurInvestments;
   }

   public void setRecurInvestments(double recurInvestments)
   {
      this.recurInvestments = recurInvestments;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   //JAV 12/14/14
   public Map<String, PortfolioSecurityData> getPortfolioMap()
   {
      return portfolio;
   }

   public double getAvgCost()
   {
      return avgCost;
   }

   public void setAvgCost(double avgCost)
   {
      this.avgCost = avgCost;
   }

   public double getAvgExpense()
   {
      return avgExpense;
   }

   public void setAvgExpense(double avgExpense)
   {
      this.avgExpense = avgExpense;
   }

   public double getTotalCost()
   {
      return totalCost;
   }

   public double getUpperTotalMoney()
   {
      return upperTotalMoney;
   }

   public void setUpperTotalMoney(double upperTotalMoney)
   {
      this.upperTotalMoney = upperTotalMoney;
   }

   public double getLowerTotalMoney()
   {
      return lowerTotalMoney;
   }

   public void setLowerTotalMoney(double lowerTotalMoney)
   {
      this.lowerTotalMoney = lowerTotalMoney;
   }

   public void setTotalCost(double totalCost)
   {
      this.totalCost = totalCost;
   }

   public void addTotalCost(double totalCost)
   {
      this.totalCost = this.totalCost + totalCost;
   }

   private ArrayList<String> getTickers()
   {
      return tickers;
   }

   public double getTotalCapitalGrowth()
   {
      return totalCapitalGrowth;
   }

   public void setTotalCapitalGrowth(double totalCapitalGrowth)
   {
      this.totalCapitalGrowth = totalCapitalGrowth;
   }

   public double getActualInvestments()
   {
      return actualInvestments;
   }

   public void setActualInvestments(double actualInvestments)
   {
      this.actualInvestments = actualInvestments;
   }

   private void setTickers(String ticker)
   {
      tickers.add(ticker);
   }

   public void setPortfolio(String ticker, String name, String color,
                            String type, String  style, String  assetclass, String  subclass,
                            double dailyprice, double weight, double expectedReturn,double expenseRatio,
                            double secRisk, double yield, double shares, double money, int sortorder,
                            double tickerWeight,
                            String isin, String cusip, String ric,
                            String baseCurrency, String destCurrency, Double exchangeRate,
                            Double baseShares, Double basePrice, Double baseMoney)
   {

      PortfolioSecurityData data;
      try
      {
         if (assetclass.equalsIgnoreCase("Cash"))
         {
            money = cashMoney;
            if (totalMoney > 0)
               tickerWeight = cashMoney/actualInvestments;
         }
         if (!portfolio.containsKey(ticker))
         {
            data = new PortfolioSecurityData(ticker, name, color,
                                             type, style, assetclass, subclass,
                                             dailyprice, weight, expectedReturn, expenseRatio,
                                             secRisk, yield, shares, money,
                                             sortorder, tickerWeight,
                                             isin, cusip, ric,
                                             baseCurrency, destCurrency, exchangeRate,
                                             baseShares, basePrice, baseMoney );
            portfolio.put(ticker, data);
            addTotalMoney(money);
            tickers.add(ticker);
         }
         else
         {
            data = portfolio.get(ticker);
            this.baseCurrency = baseCurrency;
            money = data.getMoney() + money;
            if (actualInvestments > 0) {
               tickerWeight = money/actualInvestments;
            }
            data.setMoney(money);  // Add Money
            data.setShares(data.getShares() + shares);  // Add Shared
            addTotalMoney(money); // Now, add the new one to portfolio.
            data.setTickerWeights(tickerWeight);
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void removePortfolio(String ticker)
   {

      PortfolioSecurityData data;
      if (portfolio.containsKey(ticker))
      {
         data = portfolio.get(ticker);
         addTotalMoney(data.getMoney() * -1);  // first subtract the original.
         portfolio.remove(ticker);
         tickers.remove(ticker);
      }
   }

   public double getExpReturns()
   {
      return expReturns;
   }

   public void setExpReturns(double expReturns)
   {
      this.expReturns = expReturns;
   }

   public double getTotalRisk()
   {
      return totalRisk;
   }

   public void setTotalRisk(double totalRisk)
   {
      this.totalRisk = totalRisk;
   }

   public double getTotalMoney()
   {
      return totalMoney;
   }

   public void setTotalMoney(double totalMoney)
   {
      this.totalMoney = totalMoney;
   }

   public void addTotalMoney(double totalMoney)
   {
      this.totalMoney = this.totalMoney + totalMoney;
   }

   public double getCashMoney()
   {
      return cashMoney;
   }

   public void setCashMoney(double cashMoney)
   {
      this.cashMoney = cashMoney;
   }

   public Map<String, PortfolioSubclass> getSubclassMap()
   {
      return subclassMap;
   }

   public void addSubclassMap(String assetclass, String subclass, String color, Double weight, Double money, Boolean include)
   {
      PortfolioSubclass value;
      String key;
      Double newweight, newmoney;
      ArrayList<String> assetsubclasslist;

      key = getsubclasskey(assetclass,subclass);
      if (! subclassMap.containsKey(key)) {
         value =  new PortfolioSubclass(key, assetclass, subclass, color, weight, money, include);
         subclasslist.add(value);
         subclassMap.put(key,value);
         if (! assetMap.containsKey(assetclass))
            assetsubclasslist = new ArrayList<String>();
         else
            assetsubclasslist = assetMap.get(assetclass);
         assetsubclasslist.add(subclass);
         assetMap.put(assetclass,assetsubclasslist);

      }
      else {
         value=subclassMap.get(key);
         newweight = value.getWeight() + weight;
         newmoney = value.getMoney() +  money;
         value.setColor(color);
         value.setWeight(newweight);
         value.setMoney(newmoney);
      }
    }

   public void resetPortfolioSubclass() {
        subclasslist.clear();
        subclassMap.clear();
        assetMap.clear();
   }


   public ArrayList<PortfolioSubclass> getSubclasslist()
   {
      return subclasslist;
   }

   public void setTickers(ArrayList<String> tickers)
   {
      this.tickers = tickers;
   }

   public Map<String, ArrayList> getAssetMap()
   {
      return assetMap;
   }

   public ArrayList<String> getAssetMapList(String assetname)
   {
      if (assetMap.containsKey(assetname))
         return assetMap.get(assetname);
      return null;
   }

   @SuppressWarnings("rawtypes")
   public ArrayList<PortfolioSecurityData> getPortfolio()
   {
      ArrayList<PortfolioSecurityData> dataList = new ArrayList<PortfolioSecurityData>();
         if (!portfolio.isEmpty())
         {
            PortfolioSecurityData pData;
            for (int i = 0; i < tickers.size(); i++)
            {
               String ticker = tickers.get(i);
               pData = portfolio.get(ticker);
               dataList.add(pData);
            }
            return dataList;
         }
      return dataList;
   }

   @SuppressWarnings("rawtypes")
   @Override
   public String toString()
   {
      try
      {
         Iterator it = portfolio.keySet().iterator();
         String assetList = "";
         while (it.hasNext())
         {
            String ticker = (String) it.next();
            assetList = assetList + portfolio.get(ticker).toString() + "\n";
         }
         return assetList;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return "";
   }

   @SuppressWarnings("rawtypes")
   public String toXml()
   {
      String xmlData = "";
      try
      {
         Iterator it = portfolio.keySet().iterator();
         while (it.hasNext())
         {
            String ticker = (String) it.next();
            xmlData = xmlData + buildElement("Portfolio", portfolio.get(ticker).toXml()) + "\n";
            xmlData = xmlData + "\n";
         }
         return xmlData;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return buildElement("Portfolio", xmlData);

   }

}
