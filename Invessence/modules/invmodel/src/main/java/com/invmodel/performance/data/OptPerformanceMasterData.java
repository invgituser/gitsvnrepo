package com.invmodel.performance.data;

import java.util.ArrayList;

/**
 * Created by prashant on 8/2/2017.
 */
public class OptPerformanceMasterData
{
   String ticker;
   String displayName;
   String color;
   String currency;
   Double totalReturn;
   Double invested;
   Double expectedMoney;
   ArrayList<OptPerformanceHistData> optPerformanceHistDataArrayList;
   ArrayList<OptPerformanceHistData> totalPerformanceHistDataArrayList;

   public OptPerformanceMasterData()
   {
      optPerformanceHistDataArrayList = new ArrayList<OptPerformanceHistData>();
      totalPerformanceHistDataArrayList = new ArrayList<OptPerformanceHistData>();
      totalReturn = 0.0;
      expectedMoney = 0.0;
   }

   public OptPerformanceMasterData(String ticker, String displayName, String color, String currency)
   {
      optPerformanceHistDataArrayList = new ArrayList<OptPerformanceHistData>();
      this.ticker = ticker;
      this.displayName = displayName;
      this.color = color;
      this.currency = currency;
      totalReturn = 0.0;
      expectedMoney = 0.0;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getDisplayName()
   {
      return displayName;
   }

   public void setDisplayName(String displayName)
   {
      this.displayName = displayName;
   }

   public String getName()
   {
      String name = displayName;
      String[] parts;
      if (name != null) {
         parts = name.split(".");
         if (parts.length > 1)
            return(parts[1]);
      }
      return name;

   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public String getCurrency()
   {
      return currency;
   }

   public void setCurrency(String currency)
   {
      this.currency = currency;
   }

   public Double getTotalReturn()
   {
      return totalReturn;
   }

   public void setTotalReturn(Double totalReturn)
   {
      this.totalReturn = totalReturn;
   }

   public Double getInvested()
   {
      return invested;
   }

   public Double getExpectedMoney()
   {
      return expectedMoney;
   }

   public void setExpectedMoney(Double expectedMoney)
   {
      this.expectedMoney = expectedMoney;
   }

   public void setInvested(Double invested)
   {
      this.invested = invested;
   }

   public ArrayList<OptPerformanceHistData> getOptPerformanceHistDataArrayList()
   {
      return optPerformanceHistDataArrayList;
   }

   public ArrayList<OptPerformanceHistData> getTotalPerformanceHistDataArrayList()
   {
      return totalPerformanceHistDataArrayList;
   }

   public void addPerformanceHistDataArrayList(String ticker, String displayName, String businessdate, Double returns) {
      if (this.ticker == null) {
         this.ticker = ticker;
      }
      if (this.displayName == null) {
         this.displayName = displayName;
      }
      OptPerformanceHistData optPerformanceHistData = new OptPerformanceHistData(ticker, businessdate, returns);
      optPerformanceHistDataArrayList.add(optPerformanceHistData);
   }

   public void addTotalHistDataArrayList(String ticker, String displayName, String businessdate, Double returns, Double weight, Double money) {
      if (this.ticker == null) {
         this.ticker = ticker;
      }
      if (this.displayName == null) {
         this.displayName = displayName;
      }
      OptPerformanceHistData optTotalHistData = new OptPerformanceHistData(ticker, businessdate, returns, weight, money);
      totalPerformanceHistDataArrayList.add(optTotalHistData);
   }

   public void initTotals() {
      totalPerformanceHistDataArrayList.clear();
   }
}
