package com.invessence.price.yahoo.quotes.stock;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

public class StockDividend
{
  private final String symbol;
  private Calendar payDate;
  private Calendar exDate;
  private BigDecimal annualYield;
  private BigDecimal annualYieldPercent;
  
  public StockDividend(String symbol)
  {
    this.symbol = symbol;
  }
  
  public StockDividend(String symbol, Calendar payDate, Calendar exDate, BigDecimal annualYield, BigDecimal annualYieldPercent)
  {
    this(symbol);
    this.payDate = payDate;
    this.exDate = exDate;
    this.annualYield = annualYield;
    this.annualYieldPercent = annualYieldPercent;
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public Calendar getPayDate()
  {
    return this.payDate;
  }
  
  public void setPayDate(Calendar payDate)
  {
    this.payDate = payDate;
  }
  
  public Calendar getExDate()
  {
    return this.exDate;
  }
  
  public void setExDate(Calendar exDate)
  {
    this.exDate = exDate;
  }
  
  public BigDecimal getAnnualYield()
  {
    return this.annualYield;
  }
  
  public void setAnnualYield(BigDecimal annualYield)
  {
    this.annualYield = annualYield;
  }
  
  public BigDecimal getAnnualYieldPercent()
  {
    return this.annualYieldPercent;
  }
  
  public void setAnnualYieldPercent(BigDecimal annualYieldPercent)
  {
    this.annualYieldPercent = annualYieldPercent;
  }
  
  public String toString()
  {
    String payDateStr = "/";
    String exDateStr = "/";
    if (this.payDate != null) {
      payDateStr = this.payDate.getTime().toString();
    }
    if (this.exDate != null) {
      exDateStr = this.exDate.getTime().toString();
    }
    return "Pay date: " + payDateStr + ", Ex date: " + exDateStr + ", Annual yield: " + getAnnualYieldPercent() + "%";
  }
}
