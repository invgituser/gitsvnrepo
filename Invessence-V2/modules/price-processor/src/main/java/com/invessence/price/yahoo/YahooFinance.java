package com.invessence.price.yahoo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import com.invessence.price.yahoo.histquotes.HistQuotesRequest;
import com.invessence.price.yahoo.histquotes.HistoricalQuote;
import com.invessence.price.yahoo.histquotes.Interval;
import com.invessence.price.yahoo.quotes.fx.FxQuote;
import com.invessence.price.yahoo.quotes.fx.FxQuotesRequest;
import com.invessence.price.yahoo.quotes.stock.StockQuotesData;
import com.invessence.price.yahoo.quotes.stock.StockQuotesRequest;
import com.invessence.price.yahoo.quotes.stock.StockStats;

public class YahooFinance
{
  public static final String QUOTES_BASE_URL = "http://finance.yahoo.com/d/quotes.csv";
  public static final String HISTQUOTES_BASE_URL = "http://ichart.yahoo.com/table.csv";
  public static final String QUOTES_CSV_DELIMITER = ",";
  public static final String TIMEZONE = "America/New_York";
  public static final Logger logger = Logger.getLogger(YahooFinance.class.getName());
  
  public static Stock get(String symbol)
    throws IOException
  {
    return get(symbol, false);
  }
  
  public static Stock get(String symbol, boolean includeHistorical)
    throws IOException
  {
    Map<String, Stock> result = getQuotes(symbol, includeHistorical);
    return (Stock)result.get(symbol);
  }
  
  public static Stock get(String symbol, Interval interval)
    throws IOException
  {
    return get(symbol, HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
  }
  
  public static Stock get(String symbol, Calendar from)
    throws IOException
  {
    return get(symbol, from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
  }
  
  public static Stock get(String symbol, Calendar from, Interval interval)
    throws IOException
  {
    return get(symbol, from, HistQuotesRequest.DEFAULT_TO, interval);
  }
  
  public static Stock get(String symbol, Calendar from, Calendar to)
    throws IOException
  {
    return get(symbol, from, to, HistQuotesRequest.DEFAULT_INTERVAL);
  }
  
  public static Stock get(String symbol, Calendar from, Calendar to, Interval interval)
    throws IOException
  {
    Map<String, Stock> result = getQuotes(symbol, from, to, interval);
    return (Stock)result.get(symbol);
  }
  
  public static Map<String, Stock> get(String[] symbols)
    throws IOException
  {
    return get(symbols, false);
  }
  
  public static Map<String, Stock> get(String[] symbols, boolean includeHistorical)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), includeHistorical);
  }
  
  public static Map<String, Stock> get(String[] symbols, Interval interval)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), HistQuotesRequest.DEFAULT_FROM, HistQuotesRequest.DEFAULT_TO, interval);
  }
  
  public static Map<String, Stock> get(String[] symbols, Calendar from)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, HistQuotesRequest.DEFAULT_INTERVAL);
  }
  
  public static Map<String, Stock> get(String[] symbols, Calendar from, Interval interval)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), from, HistQuotesRequest.DEFAULT_TO, interval);
  }
  
  public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), from, to, HistQuotesRequest.DEFAULT_INTERVAL);
  }
  
  public static Map<String, Stock> get(String[] symbols, Calendar from, Calendar to, Interval interval)
    throws IOException
  {
    return getQuotes(Utils.join(symbols, ","), from, to, interval);
  }
  
  public static FxQuote getFx(String symbol)
    throws IOException
  {
    FxQuotesRequest request = new FxQuotesRequest(symbol);
    return (FxQuote)request.getSingleResult();
  }
  
  public static Map<String, FxQuote> getFx(String[] symbols)
    throws IOException
  {
    FxQuotesRequest request = new FxQuotesRequest(Utils.join(symbols, ","));
    List<FxQuote> quotes = request.getResult();
    Map<String, FxQuote> result = new HashMap();
    for (FxQuote quote : quotes) {
      result.put(quote.getSymbol(), quote);
    }
    return result;
  }
  
  private static Map<String, Stock> getQuotes(String query, boolean includeHistorical)
    throws IOException
  {
    StockQuotesRequest request = new StockQuotesRequest(query);
    List<StockQuotesData> quotes = request.getResult();
    Map<String, Stock> result = new HashMap();
    for (StockQuotesData data : quotes)
    {
      Stock s = data.getStock();
      result.put(s.getSymbol(), s);
    }
    if (includeHistorical) {
      for (Stock s : result.values()) {
        s.getHistory();
      }
    }
    return result;
  }
  
  private static Map<String, Stock> getQuotes(String query, Calendar from, Calendar to, Interval interval)
    throws IOException
  {
    Map<String, Stock> stocks = getQuotes(query, false);
    stocks = fetchHistoricalQuotes(stocks, from, to, interval);
    return stocks;
  }
  
  private static Map<String, Stock> fetchHistoricalQuotes(Map<String, Stock> stocks, Calendar from, Calendar to, Interval interval)
    throws IOException
  {
    for (Stock s : stocks.values()) {
      s.getHistory(from, to, interval);
    }
    return stocks;
  }
  
  public static void main(String[] args) {

		try{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");	
		
		Stock stock1 = YahooFinance.get("FFKEX");
		StockStats ss= stock1.getStats();
		//System.out.println(ss.getSharesOutstanding());
		//BigDecimal price1 = stock1.getQuote(true).getPrice();
		//System.out.println(price1+" :price1 ");
		//FxQuote fq= YahooFinance.getFx("INTC");
		//System.out.println(fq.getPrice());
		System.out.println(stock1.getStockExchange());
		Calendar from = new GregorianCalendar(2015,9,5);// Calendar.getInstance();
		Calendar to = new GregorianCalendar(2015,12,20);// Calendar.getInstance();//2007-05-30
		//from.add(Calendar.YEAR, -10); // from 5 years ago
		List<HistoricalQuote> lst= stock1.getHistory(from,to, Interval.DAILY);
		Iterator<HistoricalQuote> itr=lst.iterator();
		while (itr.hasNext()) {
			HistoricalQuote historicalQuote = (HistoricalQuote) itr.next();
//		GregorianCalendar newGregCal = new GregorianCalendar(
//					historicalQuote.getDate().get(Calendar.YEAR),
//					historicalQuote.getDate().get(Calendar.MONTH),
//					historicalQuote.getDate().get(Calendar.DAY_OF_MONTH)
//				 );
			System.out.print("Date : " + sdf.format(historicalQuote.getDate().getTime()));
			System.out.print(" : "+historicalQuote.getDate().get(Calendar.DAY_OF_MONTH)+"-"+historicalQuote.getDate().get(Calendar.MONTH)+"-"+historicalQuote.getDate().get(Calendar.YEAR));
			System.out.println(" : "+historicalQuote.getClose());
			
			logger.info("Date : " + sdf.format(historicalQuote.getDate().getTime()));
			logger.info(" : "+historicalQuote.getDate().get(Calendar.DAY_OF_MONTH)+"-"+historicalQuote.getDate().get(Calendar.MONTH)+"-"+historicalQuote.getDate().get(Calendar.YEAR));
			logger.info(" : "+historicalQuote.getClose());
			
			
		}
		stock1.print();
		
//		Stock tesla = YahooFinance.get("TSLA", true);
//		System.out.println(tesla.getHistory());
//		
			
	/*		Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -5); // from 5 years ago
			 
			Stock google = YahooFinance.get("GOOG", from, to, Interval.WEEKLY);*/
			
			
/*			Calendar from = Calendar.getInstance();
			Calendar to = Calendar.getInstance();
			from.add(Calendar.YEAR, -1); // from 5 years ago
			 
			Stock google = YahooFinance.get("GOOG", from, to, Interval.DAILY);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}
}
  }
