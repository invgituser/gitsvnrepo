package com.invessence.eod.web;

public class WebConst
{
   /*
   public static final String [] quoteList = {  "TICKER"
                                               ,"Trade_Date"
                                               ,"Last_Price"
                                               ,"Book_Value"
                                               ,"EPS_Current"
                                               ,"Market_Cap"
                                               ,"OpenDate"
                                               ,"ExDivident_Date"
                                               ,"Price_EPS"
                                               ,"Divident"
                                               ,"Avg_Daily_Vol"
                                               ,"Divident_Share"
                                               ,"Earning_Share"
                                               ,"EPS_Next"
                                               ,"Prev_Close"
                                               ,"Price_Sales"
                                               ,"PE_Ratio"
                                               ,"PEG_Ratio"
                                               ,"Yr_Target_Price"
                                               ,"EPS_NextQ"
                                               ,"EBITDA"
                                               ,"Price_Book"
                                               ,"Divident_PayDate"
                                               ,"Price_EPS_Yr"
                                               ,"Volume"
                                               ,"Xchange"
                                               };
   public static final String WEB_URL = "http://quote.yahoo.com/d/quotes.csv?s=";
   public static final String STOCK_FETCH_INFO = "&f=sd1l1b4e7j1oqr7ya2dee8pp5rr5t8e9j4p6r1r6vx";
   */
   public static final String[] quoteList = {
      "ticker"
      , "trade_date"
      , "open_price"
      , "high_price"
      , "low_price"
      , "close_price"
      , "volume"
      , "prev_close"
   };
   public static final String WEB_URL = "http://quote.yahoo.com/d/quotes.csv?s=";
   public static final String STOCK_FETCH_INFO = "&f=sd1ohgl1vp";
   //public static final String STOCK_FETCH_INFO = "&f=sd1l1";
}
