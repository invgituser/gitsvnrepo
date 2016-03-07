package com.invessence.price.yahoo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.invessence.price.yahoo.histquotes.HistQuotesRequest;
import com.invessence.price.yahoo.histquotes.HistoricalQuote;
import com.invessence.price.yahoo.histquotes.Interval;
import com.invessence.price.yahoo.quotes.stock.StockDividend;
import com.invessence.price.yahoo.quotes.stock.StockQuote;
import com.invessence.price.yahoo.quotes.stock.StockQuotesData;
import com.invessence.price.yahoo.quotes.stock.StockQuotesRequest;
import com.invessence.price.yahoo.quotes.stock.StockStats;

public class Stock
{
  private final String symbol;
  private String name;
  private String currency;
  private String stockExchange;
  private StockQuote quote;
  private StockStats stats;
  private StockDividend dividend;
  private List<HistoricalQuote> history;
  
  public Stock(String symbol)
  {
    this.symbol = symbol;
  }
  
  private void update()
    throws IOException
  {
    StockQuotesRequest request = new StockQuotesRequest(this.symbol);
    StockQuotesData data = (StockQuotesData)request.getSingleResult();
    if (data != null)
    {
      setQuote(data.getQuote());
      setStats(data.getStats());
      setDividend(data.getDividend());
      YahooFinance.logger.log(Level.INFO, "Updated Stock with symbol: {0}", this.symbol);
    }
    else
    {
      YahooFinance.logger.log(Level.SEVERE, "Failed to update Stock with symbol: {0}", this.symbol);
    }
  }
  
  public StockQuote getQuote()
  {
    return this.quote;
  }
  
  public StockQuote getQuote(boolean refresh)
    throws IOException
  {
    if (refresh) {
      update();
    }
    return this.quote;
  }
  
  public void setQuote(StockQuote quote)
  {
    this.quote = quote;
  }
  
  public StockStats getStats()
  {
    return this.stats;
  }
  
  public StockStats getStats(boolean refresh)
    throws IOException
  {
    if (refresh) {
      update();
    }
    return this.stats;
  }
  
  public void setStats(StockStats stats)
  {
    this.stats = stats;
  }
  
  public StockDividend getDividend()
  {
    return this.dividend;
  }
  
  public StockDividend getDividend(boolean refresh)
    throws IOException
  {
    if (refresh) {
      update();
    }
    return this.dividend;
  }
  
  public void setDividend(StockDividend dividend)
  {
    this.dividend = dividend;
  }
  
  public List<HistoricalQuote> getHistory()
    throws IOException
  {
	  System.out.println("DEFAULT_FROM :"+HistQuotesRequest.DEFAULT_FROM);
    if (this.history != null) {
      return this.history;
    }
    return getHistory(HistQuotesRequest.DEFAULT_FROM);
  }
  
  public List<HistoricalQuote> getHistory(Interval interval)
    throws IOException
  {
    return getHistory(HistQuotesRequest.DEFAULT_FROM, interval);
  }
  
  public List<HistoricalQuote> getHistory(Calendar from)
    throws IOException
  {
    return getHistory(from, HistQuotesRequest.DEFAULT_TO);
  }
  
  public List<HistoricalQuote> getHistory(Calendar from, Interval interval)
    throws IOException
  {
    return getHistory(from, HistQuotesRequest.DEFAULT_TO, interval);
  }
  
  public List<HistoricalQuote> getHistory(Calendar from, Calendar to)
    throws IOException
  {
    return getHistory(from, to, Interval.MONTHLY);
  }
  
  public List<HistoricalQuote> getHistory(Calendar from, Calendar to, Interval interval)
    throws IOException
  {
    HistQuotesRequest hist = new HistQuotesRequest(this.symbol, from, to, interval);
    setHistory(hist.getResult());
    return this.history;
  }
  
  public void setHistory(List<HistoricalQuote> history)
  {
    this.history = history;
  }
  
  public String getSymbol()
  {
    return this.symbol;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String getCurrency()
  {
    return this.currency;
  }
  
  public void setCurrency(String currency)
  {
    this.currency = currency;
  }
  
  public String getStockExchange()
  {
    return this.stockExchange;
  }
  
  public void setStockExchange(String stockExchange)
  {
    this.stockExchange = stockExchange;
  }
  
  public String toString()
  {
    return this.symbol + ": " + this.quote.getPrice();
  }
  
  public void print()
  {
    System.out.println(this.symbol);
    System.out.println("--------------------------------");
    for (Field f : getClass().getDeclaredFields()) {
      try
      {
        System.out.println(f.getName() + ": " + f.get(this));
      }
      catch (IllegalArgumentException ex)
      {
        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (IllegalAccessException ex)
      {
        Logger.getLogger(Stock.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    System.out.println("--------------------------------");
  }
}
