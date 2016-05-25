package com.invessence.price.yahoo.quotes.fx;

import java.io.IOException;
import java.math.BigDecimal;

public class FxQuote
{
  private String symbol;
  private BigDecimal price;
  
  public FxQuote(String symbol)
  {
    this.symbol = symbol;
    this.price = BigDecimal.ZERO;
  }
  
  public FxQuote(String symbol, BigDecimal price)
  {
    this.symbol = symbol;
    this.price = price;
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public void setSymbol(String symbol)
  {
    this.symbol = symbol;
  }
  
  public BigDecimal getPrice()
  {
    return this.price;
  }
  
  public BigDecimal getPrice(boolean refresh)
    throws IOException
  {
    if (refresh)
    {
      FxQuotesRequest request = new FxQuotesRequest(this.symbol);
      this.price = ((FxQuote)request.getSingleResult()).getPrice();
    }
    return this.price;
  }
  
  public void setPrice(BigDecimal price)
  {
    this.price = price;
  }
  
  public String toString()
  {
    return this.symbol + ": " + this.price;
  }
}
