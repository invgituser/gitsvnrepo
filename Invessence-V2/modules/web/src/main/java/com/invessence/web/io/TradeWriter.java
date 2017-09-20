package com.invessence.web.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.invessence.converter.SQLData;
import com.invessence.shared.io.BufferedFileFactory;

public class TradeWriter
{
   public static final String HEADER = "Action,Quantity,Symbol,SecType,Exchange,Currency,TimeInForce,OrderType,LmtPrice,BasketTag,Account,Profile,OrderRef";
   public static final String LINE_SEPARATOR = System.getProperty("line.separator");


   public void downloadTradesAllocationFile(List<Map<String, Object>> data, String tradeFile, String allocationFile, String direction)
   {
      try
      {
         if (data != null) {
            List<Map<String, Object>> directionData = new ArrayList<Map<String, Object>>();
            Map<String, List<TradeAccountInfo>> sharesByAccount = new HashMap<String, List<TradeAccountInfo>>();
            for (Map<String, Object> row : data)
            {
               SQLData convert = new SQLData();
               String action = convert.getStrData(row.get("action"));
               if (action.toUpperCase().contains(direction.toUpperCase())) {
                  directionData.add(row);
               }
            }

            if (directionData.size() > 0) {
               Map<String, List<TradeAccountInfo>> sharesByAccountNum = processData(directionData);
               //fileName = getFileNameWithDateAndTime(tradeFile);
               writeTradesFile(sharesByAccountNum, tradeFile);

               //fileName = getFileNameWithDateAndTime(allocationFile);
               writeAllocationFile(sharesByAccountNum, allocationFile);
            }
         }
      }
      catch (Exception ex)
      {

      }

   }

   private void writeAllocationFile(Map<String, List<TradeAccountInfo>> sharesByAccountNum, String filepath)
   {
      BufferedWriter writer = null;

      try
      {
         System.out.print("Generating Allocation Profile file: " + filepath + " ... ");
         writer = new BufferedFileFactory().getBufferedWriter(filepath);
         writeWithNewLine(writer, "Group Name, Default Method");
         writer.newLine();
         writeWithNewLine(writer, "Group, Account");
         writer.newLine();
         writeWithNewLine(writer, "Profile Name, Type");
         writeWithNewLine(writer, Joiner.on("\n").join(Iterables.transform(sharesByAccountNum.keySet(), new Function<String, String>()
         {
            @Override
            public String apply(String s)
            {
               return s + "," + "Shares";
            }
         })));
         writer.newLine();
         writeWithNewLine(writer, "Profile, Account, Ratio");
         for (final String ticker : sharesByAccountNum.keySet())
         {
            writeWithNewLine(writer, Joiner.on("\n").join(Iterables.transform(sharesByAccountNum.get(ticker), new Function<TradeAccountInfo, String>()
            {
               @Override
               public String apply(com.invessence.web.io.TradeWriter.TradeAccountInfo tradeAccountInfo)
               {
                  return ticker + "," + tradeAccountInfo.clientAccountID + "," + Math.abs(tradeAccountInfo.qty);
               }
            })));
         }
         //System.out.println("Done");
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      finally
      {
         BufferedFileFactory.close(writer);
      }
   }

   private String getFileNameWithDateAndTime(String prefix)
   {
      Date date = new Date();
      SimpleDateFormat dateFormatWithDateAndTime = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      return dateFormatWithDateAndTime.format(date) + prefix + ".csv";
   }

   private void writeTradesFile(Map<String, List<TradeAccountInfo>> sharesByAccountNum, String filepath)
   {
      BufferedWriter writer = null;
      SimpleDateFormat dateFormatWithDay = new SimpleDateFormat("MMMddyyyy");
      String dateString = dateFormatWithDay.format(new Date());

      try
      {
         // System.out.print("Generating Trades file: " + filepath + " ... ");
         writer = new BufferedFileFactory().getBufferedWriter(filepath);
         writeWithNewLine(writer, HEADER);

         for (Map.Entry<String, List<TradeAccountInfo>> entry : sharesByAccountNum.entrySet())
         {
            writeWithNewLine(writer, prepareTradesLine(entry.getKey(), entry.getValue(), dateString));
         }
         // System.out.println("Done");
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      finally
      {
         BufferedFileFactory.close(writer);
      }
   }

   private String prepareTradesLine(String ticker, List<TradeAccountInfo> value, String date)
   {
      String action;
      Integer qty = sumQuantity(value);
      if (qty == 0)
      {
         return "";
      }
      else
      {
         if (qty < 0)
            action = "SELL";
         else
            action = "BUY";

         return Joiner.on(",").join(Arrays.asList(action,
                                                  Math.abs(qty),
                                                  ticker,
                                                  "STK",
                                                  "SMART/ISE",
                                                  "USD",
                                                  "DAY",
                                                  "LMT",
                                                  "",
                                                  date,
                                                  "F1230824",
                                                  ticker,
                                                  date
         ));
      }
   }

   private Integer sumQuantity(List<TradeAccountInfo> accountInfos)
   {
      int sum = 0;
      for (TradeAccountInfo tradeAccountInfo : accountInfos)
      {
         sum += tradeAccountInfo.qty;
      }

      return sum;
   }

   private Map<String, List<TradeAccountInfo>> processData(List<Map<String, Object>> data)
   {
      Map<String, List<TradeAccountInfo>> sharesByAccount = new HashMap<String, List<TradeAccountInfo>>();
      for (Map<String, Object> row : data)
      {
         SQLData convert = new SQLData();
         Integer quantity = convert.getIntData(row.get("qty"));
         String accountNum = convert.getStrData( row.get("clientAccountID") );
         String ticker = convert.getStrData(row.get("ticker"));

         // Note this process is called only for Buy or Sell.  So, add to this list only one type.
         // Add to list if it is BUY.
            List<TradeAccountInfo> tradeAccountInfos = sharesByAccount.get(ticker);
            if (tradeAccountInfos == null)
            {
               tradeAccountInfos = new LinkedList<TradeAccountInfo>();
               sharesByAccount.put(ticker, tradeAccountInfos);
            }
            tradeAccountInfos.add(new TradeAccountInfo(
               convert.getLongData(row.get("acctnum")),
               convert.getStrData(row.get("clientAccountID")),
               convert.getStrData(row.get("tradeStatus")),
               convert.getStrData(row.get("tradedate")),
               convert.getStrData(row.get("ticker")),
               convert.getStrData(row.get("action")),
               convert.getStrData(row.get("sectype")),
               convert.getStrData(row.get("exchange")),
               convert.getStrData(row.get("currency")),
               convert.getStrData(row.get("timeinforce")),
               convert.getIntData(row.get("qty")),
               convert.getDoubleData(row.get("tradeprice")),
               convert.getDoubleData(row.get("investmentamount")),
               convert.getStrData(row.get("tradeID")),
               convert.getStrData(row.get("ordertype")),
               convert.getStrData(row.get("confirmationID")),
               convert.getStrData(row.get("firmAccount")),
               convert.getStrData(row.get("created")),
               convert.getStrData(row.get("lastupdated"))
               ));
      }
      return sharesByAccount;
   }

   void writeWithNewLine(Writer writer, String line) throws IOException
   {
      if (!line.isEmpty())
      {
         writer.write(line + LINE_SEPARATOR);
      }
   }

   class TradeAccountInfo
   {
      Long acctnum;
      String clientAccountID;
      String tradeStatus;
      String tradedate;
      String ticker;
      String action;
      String sectype;
      String exchange;
      String currency;
      String timeinforce;
      Integer qty;
      Double tradepricel;
      Double investmentamount;
      String tradeID;
      String ordertype;
      String confirmationID;
      String firmAccount;
      String created;
      String lastupdated;

      TradeAccountInfo(Long acctnum, String clientAccountID,
                       String tradeStatus, String tradedate,
                       String ticker, String action, String sectype, String exchange,
                       String currency, String timeinforce,
                       Integer qty, Double tradepricel, Double investmentamount,
                       String tradeID, String ordertype, String confirmationID,
                       String firmAccount, String created, String lastupdated)
      {
         this.acctnum = acctnum;
         this.clientAccountID = clientAccountID;
         this.tradeStatus = tradeStatus;
         this.tradedate = tradedate;
         this.ticker = ticker;
         this.action = action;
         this.sectype = sectype;
         this.exchange = exchange;
         this.currency = currency;
         this.timeinforce = timeinforce;
         this.qty = qty;
         this.tradepricel = tradepricel;
         this.investmentamount = investmentamount;
         this.tradeID = tradeID;
         this.ordertype = ordertype;
         this.confirmationID = confirmationID;
         this.firmAccount = firmAccount;
         this.created = created;
         this.lastupdated = lastupdated;
      }
   }
}
