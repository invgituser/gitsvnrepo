package com.invessence.price.yahoo.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.TimeZone;

import com.invessence.price.yahoo.Utils;

public class StockQuote
{
  private final String symbol;
  private TimeZone timeZone;
  private BigDecimal ask;
  private int askSize;
  private BigDecimal bid;
  private int bidSize;
  private BigDecimal price;
  private int lastTradeSize;
  private String lastTradeDateStr;
  private String lastTradeTimeStr;
  private Calendar lastTradeTime;
  private BigDecimal lastTradePriceOnly;
  private BigDecimal open;
  private BigDecimal previousClose;
  private BigDecimal dayLow;
  private BigDecimal dayHigh;
  private BigDecimal yearLow;
  private BigDecimal yearHigh;
  private BigDecimal priceAvg50;
  private BigDecimal priceAvg200;
  private long volume;
  private long avgVolume;
  
  public StockQuote(String symbol)
  {
    this.symbol = symbol;
  }
  
  public BigDecimal getChange()
  {
    return this.price.subtract(this.previousClose);
  }
  
  public BigDecimal getChangeInPercent()
  {
    return Utils.getPercent(getChange(), this.previousClose);
  }
  
  public BigDecimal getChangeFromYearLow()
  {
    return this.price.subtract(this.yearLow);
  }
  
  public BigDecimal getChangeFromYearLowInPercent()
  {
    return Utils.getPercent(getChangeFromYearLow(), this.yearLow);
  }
  
  public BigDecimal getChangeFromYearHigh()
  {
    return this.price.subtract(this.yearHigh);
  }
  
  public BigDecimal getChangeFromYearHighInPercent()
  {
    return Utils.getPercent(getChangeFromYearHigh(), this.yearHigh);
  }
  
  public BigDecimal getChangeFromAvg50()
  {
    return this.price.subtract(this.priceAvg50);
  }
  
  public BigDecimal getChangeFromAvg50InPercent()
  {
    return Utils.getPercent(getChangeFromAvg50(), this.priceAvg50);
  }
  
  public BigDecimal getChangeFromAvg200()
  {
    return this.price.subtract(this.priceAvg200);
  }
  
  public BigDecimal getChangeFromAvg200InPercent()
  {
    return Utils.getPercent(getChangeFromAvg200(), this.priceAvg200);
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public BigDecimal getAsk()
  {
    return this.ask;
  }
  
  public void setAsk(BigDecimal ask)
  {
    this.ask = ask;
  }
  
  public int getAskSize()
  {
    return this.askSize;
  }
  
  public void setAskSize(int askSize)
  {
    this.askSize = askSize;
  }
  
  public BigDecimal getBid()
  {
    return this.bid;
  }
  
  public void setBid(BigDecimal bid)
  {
    this.bid = bid;
  }
  
  public int getBidSize()
  {
    return this.bidSize;
  }
  
  public void setBidSize(int bidSize)
  {
    this.bidSize = bidSize;
  }
  
  public BigDecimal getPrice()
  {
    return this.price;
  }
  
  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }
  
  
  public BigDecimal getLastTradePriceOnly() {
	return this.lastTradePriceOnly;
	}
	
	public void setLastTradePriceOnly(BigDecimal lastTradePriceOnly) {
		this.lastTradePriceOnly = lastTradePriceOnly;
	}

public int getLastTradeSize()
  {
    return this.lastTradeSize;
  }
  
  public void setLastTradeSize(int lastTradeSize)
  {
    this.lastTradeSize = lastTradeSize;
  }
  
  public String getLastTradeDateStr()
  {
    return this.lastTradeDateStr;
  }
  
  public void setLastTradeDateStr(String lastTradeDateStr)
  {
    this.lastTradeDateStr = lastTradeDateStr;
  }
  
  public String getLastTradeTimeStr()
  {
    return this.lastTradeTimeStr;
  }
  
  public void setLastTradeTimeStr(String lastTradeTimeStr)
  {
    this.lastTradeTimeStr = lastTradeTimeStr;
  }
  
  public Calendar getLastTradeTime()
  {
    return this.lastTradeTime;
  }
  
  public void setLastTradeTime(Calendar lastTradeTime)
  {
    this.lastTradeTime = lastTradeTime;
  }
  
  public Calendar getLastTradeTime(TimeZone timeZone)
  {
    return Utils.parseDateTime(this.lastTradeDateStr, this.lastTradeTimeStr, timeZone);
  }
  
  public TimeZone getTimeZone()
  {
    return this.timeZone;
  }
  
  public void setTimeZone(TimeZone timeZone)
  {
    this.timeZone = timeZone;
  }
  
  public BigDecimal getOpen()
  {
    return this.open;
  }
  
  public void setOpen(BigDecimal open)
  {
    this.open = open;
  }
  
  public BigDecimal getPreviousClose()
  {
    return this.previousClose;
  }
  
  public void setPreviousClose(BigDecimal previousClose)
  {
    this.previousClose = previousClose;
  }
  
  public BigDecimal getDayLow()
  {
    return this.dayLow;
  }
  
  public void setDayLow(BigDecimal dayLow)
  {
    this.dayLow = dayLow;
  }
  
  public BigDecimal getDayHigh()
  {
    return this.dayHigh;
  }
  
  public void setDayHigh(BigDecimal dayHigh)
  {
    this.dayHigh = dayHigh;
  }
  
  public BigDecimal getYearLow()
  {
    return this.yearLow;
  }
  
  public void setYearLow(BigDecimal yearLow)
  {
    this.yearLow = yearLow;
  }
  
  public BigDecimal getYearHigh()
  {
    return this.yearHigh;
  }
  
  public void setYearHigh(BigDecimal yearHigh)
  {
    this.yearHigh = yearHigh;
  }
  
  public BigDecimal getPriceAvg50()
  {
    return this.priceAvg50;
  }
  
  public void setPriceAvg50(BigDecimal priceAvg50)
  {
    this.priceAvg50 = priceAvg50;
  }
  
  public BigDecimal getPriceAvg200()
  {
    return this.priceAvg200;
  }
  
  public void setPriceAvg200(BigDecimal priceAvg200)
  {
    this.priceAvg200 = priceAvg200;
  }
  
  public long getVolume()
  {
    return this.volume;
  }
  
  public void setVolume(long volume)
  {
    this.volume = volume;
  }
  
  public long getAvgVolume()
  {
    return this.avgVolume;
  }
  
  public void setAvgVolume(long avgVolume)
  {
    this.avgVolume = avgVolume;
  }
  
  public String toString()
  {
    return "Ask: " + this.ask + ", Bid: " + this.bid + ", Price: " + this.price + ", Prev close: " + this.previousClose;
  }
}
