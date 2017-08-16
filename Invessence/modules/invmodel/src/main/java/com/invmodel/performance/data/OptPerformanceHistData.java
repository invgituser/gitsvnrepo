package com.invmodel.performance.data;

/**
 * Created by prashant on 8/2/2017.
 */
public class OptPerformanceHistData
{
   String ticker;
   String businessdate;
   Double returns;
   Double weight;
   Double money;

   public OptPerformanceHistData()
   {
   }

   public OptPerformanceHistData(String ticker, String businessdate, Double returns)
   {
      this.ticker = ticker;
      this.businessdate = businessdate;
      this.returns = returns;
   }

   public OptPerformanceHistData(String ticker, String businessdate, Double returns, Double weight, Double money)
   {
      this.ticker = ticker;
      this.businessdate = businessdate;
      this.returns = returns;
      this.weight = weight;
      this.money = money;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getBusinessdate()
   {
      return businessdate;
   }

   public void setBusinessdate(String businessdate)
   {
      this.businessdate = businessdate;
   }

   public Double getReturns()
   {
      return returns;
   }

   public void setReturns(Double returns)
   {
      this.returns = returns;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public Double getMoney()
   {
      return money;
   }

   public void setMoney(Double money)
   {
      this.money = money;
   }
}
