package com.invessence.price.FIS.bean;

import java.util.Date;

/**
 * Created by sagar on 4/11/2017.
 */
public class SecExchngData
{
   private String symbol;
   private double exchangeRate;
   private double reverseExchangeRate;
   private Date created;
   private Date updated;

   public SecExchngData(String symbol, double exchangeRate, Date created){

      super();
      this.symbol = symbol;
      this.exchangeRate = exchangeRate;
      this.created = created;

   }

   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   public double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public double getReverseExchangeRate()
   {
      return reverseExchangeRate;
   }

   public void setReverseExchangeRate(double reverseExchangeRate)
   {
      this.reverseExchangeRate = reverseExchangeRate;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public Date getUpdated()
   {
      return updated;
   }

   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   @Override
   public String toString()
   {
      return "SecExchngDataDao{" +
         "symbol='" + symbol + '\'' +
         ", exchangeRate=" + exchangeRate +
         ", reverseExchangeRate=" + reverseExchangeRate +
         ", created=" + created +
         ", updated=" + updated +
         '}';
   }
}
