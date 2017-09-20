package com.invessence.price.FIS.bean;

/**
 * Created by sagar on 4/10/2017.
 */
public class SecExchangeMaster
{
   private String symbol;
   private String source;
   private String fromCurrency;
   private String toCurrency;
   private String description;
   private String onDemand;
   private String exchangeDate;

   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   public String getSource()
   {
      return source;
   }

   public void setSource(String source)
   {
      this.source = source;
   }

   public String getFromCurrency()
   {
      return fromCurrency;
   }

   public void setFromCurrency(String fromCurrency)
   {
      this.fromCurrency = fromCurrency;
   }

   public String getToCurrency()
   {
      return toCurrency;
   }

   public void setToCurrency(String toCurrency)
   {
      this.toCurrency = toCurrency;
   }

   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getOnDemand()
   {
      return onDemand;
   }

   public void setOnDemand(String onDemand)
   {
      this.onDemand = onDemand;
   }

   public String getExchangeDate()
   {
      return exchangeDate;
   }

   public void setExchangeDate(String exchangeDate)
   {
      this.exchangeDate = exchangeDate;
   }

   @Override
   public String toString()
   {
      return "SecExchangeMaster{" +
         "symbol='" + symbol + '\'' +
         ", source='" + source + '\'' +
         ", fromCurrency='" + fromCurrency + '\'' +
         ", toCurrency='" + toCurrency + '\'' +
         ", description='" + description + '\'' +
         ", onDemand='" + onDemand + '\'' +
         ", exchangeDate='" + exchangeDate + '\'' +
         '}';
   }
}
