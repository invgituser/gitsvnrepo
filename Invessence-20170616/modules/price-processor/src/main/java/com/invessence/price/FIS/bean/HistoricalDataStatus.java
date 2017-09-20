package com.invessence.price.FIS.bean;

import java.util.*;
import javax.xml.bind.annotation.*;


/**
 * Created by sagar on 4/7/2017.
 */
@XmlRootElement(namespace = "http://91.212.43.32/XML/", name = "TIMESERIES")

public class HistoricalDataStatus
{
   private String status;
   private String ticker;
   private String symbol;
   private String nbpoints;
   private String timescale;
   private List<HistoricalDataRates> TimeSeriesPoint = new ArrayList<HistoricalDataRates>();

   @XmlAttribute(name = "status")
   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   @XmlAttribute(name = "ticker")
   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   @XmlAttribute(name = "symbol")
   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   @XmlAttribute(name = "nbpoints")
   public String getNbpoints()
   {
      return nbpoints;
   }

   public void setNbpoints(String nbpoints)
   {
      this.nbpoints = nbpoints;
   }


   @XmlAttribute(name = "timescale")
   public String getTimescale()
   {
      return timescale;
   }

   public void setTimescale(String timescale)
   {
      this.timescale = timescale;
   }


   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "TIMESERIESPOINT")
   public List<HistoricalDataRates> getTimeSeriesPoint()
   {
      return TimeSeriesPoint;
   }

   public void setTimeSeriesPoint(List<HistoricalDataRates> timeSeriesPoint)
   {
      TimeSeriesPoint = timeSeriesPoint;
   }

   @Override
   public String toString()
   {
      return "HistoricalExchangeRates{" +
         "status='" + status + '\'' +
         ", ticker='" + ticker + '\'' +
         ", symbol='" + symbol + '\'' +
         ", nbpoints='" + nbpoints + '\'' +
         ", timescale='" + timescale + '\'' +
         ", TimeSeriesPoint=" + TimeSeriesPoint +
         '}';
   }
}
