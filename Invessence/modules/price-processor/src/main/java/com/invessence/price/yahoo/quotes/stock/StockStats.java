package com.invessence.price.yahoo.quotes.stock;

import java.math.BigDecimal;

import com.invessence.price.yahoo.Utils;

public class StockStats
{
  private final String symbol;
  private BigDecimal marketCap;
  private long sharesFloat;
  private long sharesOutstanding;
  private long sharesOwned;
  private BigDecimal eps;
  private BigDecimal pe;
  private BigDecimal peg;
  private BigDecimal epsEstimateCurrentYear;
  private BigDecimal epsEstimateNextQuarter;
  private BigDecimal epsEstimateNextYear;
  private BigDecimal priceBook;
  private BigDecimal priceSales;
  private BigDecimal bookValuePerShare;
  private BigDecimal revenue;
  private BigDecimal EBITDA;
  private BigDecimal oneYearTargetPrice;
  
  public StockStats(String symbol)
  {
    this.symbol = symbol;
  }
  
  public BigDecimal getROE()
  {
    return Utils.getPercent(this.EBITDA, this.marketCap);
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public BigDecimal getMarketCap()
  {
    return this.marketCap;
  }
  
  public void setMarketCap(BigDecimal marketCap)
  {
    this.marketCap = marketCap;
  }
  
  public long getSharesFloat()
  {
    return this.sharesFloat;
  }
  
  public void setSharesFloat(long sharesFloat)
  {
    this.sharesFloat = sharesFloat;
  }
  
  public long getSharesOutstanding()
  {
    return this.sharesOutstanding;
  }
  
  public void setSharesOutstanding(long sharesOutstanding)
  {
    this.sharesOutstanding = sharesOutstanding;
  }
  
  public long getSharesOwned()
  {
    return this.sharesOwned;
  }
  
  public void setSharesOwned(long sharesOwned)
  {
    this.sharesOwned = sharesOwned;
  }
  
  public BigDecimal getEps()
  {
    return this.eps;
  }
  
  public void setEps(BigDecimal eps)
  {
    this.eps = eps;
  }
  
  public BigDecimal getPe()
  {
    return this.pe;
  }
  
  public void setPe(BigDecimal pe)
  {
    this.pe = pe;
  }
  
  public BigDecimal getPeg()
  {
    return this.peg;
  }
  
  public void setPeg(BigDecimal peg)
  {
    this.peg = peg;
  }
  
  public BigDecimal getEpsEstimateCurrentYear()
  {
    return this.epsEstimateCurrentYear;
  }
  
  public void setEpsEstimateCurrentYear(BigDecimal epsEstimateCurrentYear)
  {
    this.epsEstimateCurrentYear = epsEstimateCurrentYear;
  }
  
  public BigDecimal getEpsEstimateNextQuarter()
  {
    return this.epsEstimateNextQuarter;
  }
  
  public void setEpsEstimateNextQuarter(BigDecimal epsEstimateNextQuarter)
  {
    this.epsEstimateNextQuarter = epsEstimateNextQuarter;
  }
  
  public BigDecimal getEpsEstimateNextYear()
  {
    return this.epsEstimateNextYear;
  }
  
  public void setEpsEstimateNextYear(BigDecimal epsEstimateNextYear)
  {
    this.epsEstimateNextYear = epsEstimateNextYear;
  }
  
  public BigDecimal getPriceBook()
  {
    return this.priceBook;
  }
  
  public void setPriceBook(BigDecimal priceBook)
  {
    this.priceBook = priceBook;
  }
  
  public BigDecimal getPriceSales()
  {
    return this.priceSales;
  }
  
  public void setPriceSales(BigDecimal priceSales)
  {
    this.priceSales = priceSales;
  }
  
  public BigDecimal getBookValuePerShare()
  {
    return this.bookValuePerShare;
  }
  
  public void setBookValuePerShare(BigDecimal bookValuePerShare)
  {
    this.bookValuePerShare = bookValuePerShare;
  }
  
  public BigDecimal getRevenue()
  {
    return this.revenue;
  }
  
  public void setRevenue(BigDecimal revenue)
  {
    this.revenue = revenue;
  }
  
  public BigDecimal getEBITDA()
  {
    return this.EBITDA;
  }
  
  public void setEBITDA(BigDecimal EBITDA)
  {
    this.EBITDA = EBITDA;
  }
  
  public BigDecimal getOneYearTargetPrice()
  {
    return this.oneYearTargetPrice;
  }
  
  public void setOneYearTargetPrice(BigDecimal oneYearTargetPrice)
  {
    this.oneYearTargetPrice = oneYearTargetPrice;
  }
  
  public String toString()
  {
    return "EPS: " + this.eps + ", PE: " + this.pe + ", PEG: " + this.peg;
  }
}
