package com.invessence.price.FIS.bean;

import javax.xml.bind.annotation.*;

/**
 * Created by sagar on 4/6/2017.
 */

@XmlRootElement(namespace = "http://91.212.43.32/XML/", name = "INFOTECXML")
public class HistoricalData
{
   private HistoricalDataStatus HistoricalExchangeRates;

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "TIMESERIES")
   public HistoricalDataStatus getHistoricalExchangeRates()
   {
      return HistoricalExchangeRates;
   }

   public void setHistoricalExchangeRates(HistoricalDataStatus historicalExchangeRates)
   {
      HistoricalExchangeRates = historicalExchangeRates;
   }

   @Override
   public String toString()
   {
      return "HistoricalExchange{" +
         "HistoricalExchangeRates=" + HistoricalExchangeRates +
         '}';
   }
}