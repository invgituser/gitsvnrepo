package com.invessence.price.yahoo.histquotes;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HistoricalQuote
{
  private String symbol;
  private Calendar date;
  private BigDecimal open;
  private BigDecimal low;
  private BigDecimal high;
  private BigDecimal close;
  private BigDecimal adjClose;
  private long volume;
  
  public HistoricalQuote() {}
  
  public HistoricalQuote(String symbol, Calendar date, BigDecimal open, BigDecimal low, BigDecimal high, BigDecimal close, BigDecimal adjClose, long volume)
  {
    this.symbol = symbol;
    this.date = date;
    this.open = open;
    this.low = low;
    this.high = high;
    this.close = close;
    this.adjClose = adjClose;
    this.volume = volume;
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public void setSymbol(String symbol)
  {
    this.symbol = symbol;
  }
  
  public Calendar getDate()
  {
    return this.date;
  }
  
  public void setDate(Calendar date)
  {
    this.date = date;
  }
  
  public BigDecimal getOpen()
  {
    return this.open;
  }
  
  public void setOpen(BigDecimal open)
  {
    this.open = open;
  }
  
  public BigDecimal getLow()
  {
    return this.low;
  }
  
  public void setLow(BigDecimal low)
  {
    this.low = low;
  }
  
  public BigDecimal getHigh()
  {
    return this.high;
  }
  
  public void setHigh(BigDecimal high)
  {
    this.high = high;
  }
  
  public BigDecimal getClose()
  {
    return this.close;
  }
  
  public void setClose(BigDecimal close)
  {
    this.close = close;
  }
  
  public BigDecimal getAdjClose()
  {
    return this.adjClose;
  }
  
  public void setAdjClose(BigDecimal adjClose)
  {
    this.adjClose = adjClose;
  }
  
  public long getVolume()
  {
    return this.volume;
  }
  
  public void setVolume(long volume)
  {
    this.volume = volume;
  }
  
  public String toString()
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String dateStr = dateFormat.format(this.date.getTime());
    return this.symbol + "@" + dateStr + ": " + this.low + "-" + this.high + ", " + this.open + "->" + this.close + " (" + this.adjClose + ")";
  }
}
