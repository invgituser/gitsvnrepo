package com.invessence.price.FIS.bean;

import javax.xml.bind.annotation.*;


/**
 * Created by sagar on 4/7/2017.
 */
@XmlRootElement(namespace = "http://91.212.43.32/XML/", name = "TIMESERIESPOINT")

public class HistoricalDataRates
{
   private String last;
   private String date;
   private String bid;
   private String ask;
   private String high;
   private String low;
   private String open;
   private String close;
   private String volume;

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "last")
   public String getLast()
   {
      return last;
   }

   public void setLast(String last)
   {
      this.last = last;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "date")
   public String getDate()
   {
      return date;
   }

   public void setDate(String date)
   {
      this.date = date;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "bid")
   public String getBid()
   {
      return bid;
   }

   public void setBid(String bid)
   {
      this.bid = bid;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "ask")
   public String getAsk()
   {
      return ask;
   }

   public void setAsk(String ask)
   {
      this.ask = ask;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "high")
   public String getHigh()
   {
      return high;
   }

   public void setHigh(String high)
   {
      this.high = high;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "low")
   public String getLow()
   {
      return low;
   }

   public void setLow(String low)
   {
      this.low = low;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "open")
   public String getOpen()
   {
      return open;
   }

   public void setOpen(String open)
   {
      this.open = open;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "close")
   public String getClose()
   {
      return close;
   }

   public void setClose(String close)
   {
      this.close = close;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "volume")
   public String getVolume()
   {
      return volume;
   }

   public void setVolume(String volume)
   {
      this.volume = volume;
   }

   @Override
   public String toString()
   {
      return "HistoricalDataRates{" +
         "last='" + last + '\'' +
         ", date='" + date + '\'' +
         ", bid='" + bid + '\'' +
         ", ask='" + ask + '\'' +
         ", high='" + high + '\'' +
         ", low='" + low + '\'' +
         ", open='" + open + '\'' +
         ", close='" + close + '\'' +
         ", volume='" + volume + '\'' +
         '}';
   }
}
