package com.invessence.price.yahoo.quotes.stock;

import com.invessence.price.yahoo.Stock;
import com.invessence.price.yahoo.Utils;
import com.invessence.price.yahoo.exchanges.ExchangeTimeZone;
import com.invessence.price.yahoo.quotes.QuotesProperty;

public class StockQuotesData
{
  private final String[] data;
  
  public StockQuotesData(String[] data)
  {
    this.data = data;
  }
  
  public String getValue(QuotesProperty property)
  {
    int i = StockQuotesRequest.DEFAULT_PROPERTIES.indexOf(property);
    if ((i >= 0) && (i < this.data.length)) {
      return this.data[i];
    }
    return null;
  }
  
  public StockQuote getQuote()
  {
    String symbol = getValue(QuotesProperty.Symbol);
    StockQuote quote = new StockQuote(symbol);
    
    quote.setPrice(Utils.getBigDecimal(getValue(QuotesProperty.LastTradePriceOnly)));
    quote.setLastTradeSize(Utils.getInt(getValue(QuotesProperty.LastTradeSize)));
    quote.setAsk(Utils.getBigDecimal(getValue(QuotesProperty.AskRealtime), getValue(QuotesProperty.Ask)));
    quote.setAskSize(Utils.getInt(getValue(QuotesProperty.AskSize)));
    quote.setBid(Utils.getBigDecimal(getValue(QuotesProperty.BidRealtime), getValue(QuotesProperty.Bid)));
    quote.setBidSize(Utils.getInt(getValue(QuotesProperty.BidSize)));
    quote.setOpen(Utils.getBigDecimal(getValue(QuotesProperty.Open)));
    quote.setPreviousClose(Utils.getBigDecimal(getValue(QuotesProperty.PreviousClose)));
    quote.setDayHigh(Utils.getBigDecimal(getValue(QuotesProperty.DaysHigh)));
    quote.setDayLow(Utils.getBigDecimal(getValue(QuotesProperty.DaysLow)));
    
    quote.setTimeZone(ExchangeTimeZone.getStockTimeZone(symbol));
    quote.setLastTradeTime(Utils.parseDateTime(getValue(QuotesProperty.LastTradeDate), getValue(QuotesProperty.LastTradeTime), quote.getTimeZone()));
    
    quote.setYearHigh(Utils.getBigDecimal(getValue(QuotesProperty.YearHigh)));
    quote.setYearLow(Utils.getBigDecimal(getValue(QuotesProperty.YearLow)));
    quote.setPriceAvg50(Utils.getBigDecimal(getValue(QuotesProperty.FiftydayMovingAverage)));
    quote.setPriceAvg200(Utils.getBigDecimal(getValue(QuotesProperty.TwoHundreddayMovingAverage)));
    
    quote.setVolume(Utils.getLong(getValue(QuotesProperty.Volume)));
    quote.setAvgVolume(Utils.getLong(getValue(QuotesProperty.AverageDailyVolume)));
    
    return quote;
  }
  
  public StockStats getStats()
  {
    String symbol = getValue(QuotesProperty.Symbol);
    StockStats stats = new StockStats(symbol);
    
    stats.setMarketCap(Utils.getBigDecimal(getValue(QuotesProperty.MarketCapitalization)));
    stats.setSharesFloat(Utils.getLong(getValue(QuotesProperty.SharesFloat)));
    stats.setSharesOutstanding(Utils.getLong(getValue(QuotesProperty.SharesOutstanding)));
    stats.setSharesOwned(Utils.getLong(getValue(QuotesProperty.SharesOwned)));
    
    stats.setEps(Utils.getBigDecimal(getValue(QuotesProperty.DilutedEPS)));
    stats.setPe(Utils.getBigDecimal(getValue(QuotesProperty.PERatio)));
    stats.setPeg(Utils.getBigDecimal(getValue(QuotesProperty.PEGRatio)));
    
    stats.setEpsEstimateCurrentYear(Utils.getBigDecimal(getValue(QuotesProperty.EPSEstimateCurrentYear)));
    stats.setEpsEstimateNextQuarter(Utils.getBigDecimal(getValue(QuotesProperty.EPSEstimateNextQuarter)));
    stats.setEpsEstimateNextYear(Utils.getBigDecimal(getValue(QuotesProperty.EPSEstimateNextYear)));
    
    stats.setPriceBook(Utils.getBigDecimal(getValue(QuotesProperty.PriceBook)));
    stats.setPriceSales(Utils.getBigDecimal(getValue(QuotesProperty.PriceSales)));
    stats.setBookValuePerShare(Utils.getBigDecimal(getValue(QuotesProperty.BookValuePerShare)));
    
    stats.setOneYearTargetPrice(Utils.getBigDecimal(getValue(QuotesProperty.OneyrTargetPrice)));
    stats.setEBITDA(Utils.getBigDecimal(getValue(QuotesProperty.EBITDA)));
    stats.setRevenue(Utils.getBigDecimal(getValue(QuotesProperty.Revenue)));
    
    return stats;
  }
  
  public StockDividend getDividend()
  {
    String symbol = getValue(QuotesProperty.Symbol);
    StockDividend dividend = new StockDividend(symbol);
    
    dividend.setPayDate(Utils.parseDividendDate(getValue(QuotesProperty.DividendPayDate)));
    dividend.setExDate(Utils.parseDividendDate(getValue(QuotesProperty.ExDividendDate)));
    dividend.setAnnualYield(Utils.getBigDecimal(getValue(QuotesProperty.TrailingAnnualDividendYield)));
    dividend.setAnnualYieldPercent(Utils.getBigDecimal(getValue(QuotesProperty.TrailingAnnualDividendYieldInPercent)));
    
    return dividend;
  }
  
  public Stock getStock()
  {
    String symbol = getValue(QuotesProperty.Symbol);
    Stock stock = new Stock(symbol);
    
    stock.setName(getValue(QuotesProperty.Name));
    stock.setCurrency(getValue(QuotesProperty.Currency));
    stock.setStockExchange(getValue(QuotesProperty.StockExchange));
    
    stock.setQuote(getQuote());
    stock.setStats(getStats());
    stock.setDividend(getDividend());
    
    return stock;
  }
}
