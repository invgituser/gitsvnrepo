package com.invessence.price.FIS.bean;

import javax.xml.bind.annotation.*;


/**
 * Created by sagar on 4/7/2017.
 */
@XmlRootElement(namespace = "http://91.212.43.32/XML/",name = "QUOTE")

public class DailyRates
{
   private String status;
   private String ticker;
   private String symbol;
   private String name;
   private String last;
   private String time;
   private String date;
   private String bid;
   private String ask;
   private String high;
   private String low;
   private String open;
   private String close;
   private String histclosedate;
   private String pclose;
   private String volume;

   @XmlAttribute( name = "status" )
   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   @XmlAttribute( name = "ticker" )
   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   @XmlAttribute( name = "symbol" )
   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "name" )
   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "last" )
   public String getLast()
   {
      return last;
   }

   public void setLast(String last)
   {
      this.last = last;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "time" )
   public String getTime()
   {
      return time;
   }

   public void setTime(String time)
   {
      this.time = time;
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

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "date")
   public String getDate()
   {
      return date;
   }

   public void setDate(String date)
   {
      this.date = date;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "histclosedate")
   public String getHistclosedate()
   {
      return histclosedate;
   }

   public void setHistclosedate(String histclosedate)
   {
      this.histclosedate = histclosedate;
   }

   @XmlElement(namespace = "http://91.212.43.32/XML/", name = "pclose")
   public String getPclose()
   {
      return pclose;
   }

   public void setPclose(String pclose)
   {
      this.pclose = pclose;
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
      return "DailyRates{" +
         "status='" + status + '\'' +
         ", ticker='" + ticker + '\'' +
         ", symbol='" + symbol + '\'' +
         ", name='" + name + '\'' +
         ", last='" + last + '\'' +
         ", time='" + time + '\'' +
         ", date='" + date + '\'' +
         ", bid='" + bid + '\'' +
         ", ask='" + ask + '\'' +
         ", high='" + high + '\'' +
         ", low='" + low + '\'' +
         ", open='" + open + '\'' +
         ", close='" + close + '\'' +
         ", histclosedate='" + histclosedate + '\'' +
         ", pclose='" + pclose + '\'' +
         ", volume='" + volume + '\'' +
         '}';
   }
}
