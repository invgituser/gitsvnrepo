package com.invessence.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.invessence.shared.io.BufferedFileFactory;

public class TradeWriter
{
   public static final String HEADER = "Action,Quantity,Symbol,SecType,Exchange,Currency,TimeInForce,OrderType,LmtPrice,BasketTag,Account,Profile,OrderRef";
   public static final String LINE_SEPARATOR = System.getProperty("line.separator");


   public void downloadTradesAllocationFile(List<Map<String, Object>> data, String tradeFile, String allocationFile, String direction)
   {
      try
      {

         Map<String, List<TradeAccountInfo>> sharesByAccountNum = processData(data, direction);
         //fileName = getFileNameWithDateAndTime(tradeFile);
         writeTradesFile(sharesByAccountNum, tradeFile);

         //fileName = getFileNameWithDateAndTime(allocationFile);
         writeAllocationFile(sharesByAccountNum, allocationFile);
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
               public String apply(com.invessence.io.TradeWriter.TradeAccountInfo tradeAccountInfo)
               {
                  return ticker + "," + tradeAccountInfo.accountNum + "," + tradeAccountInfo.quantity;
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
         System.out.print("Generating Trades file: " + filepath + " ... ");
         writer = new BufferedFileFactory().getBufferedWriter(filepath);
         writeWithNewLine(writer, HEADER);

         for (Map.Entry<String, List<TradeAccountInfo>> entry : sharesByAccountNum.entrySet())
         {
            writeWithNewLine(writer, prepareTradesLine(entry.getKey(), entry.getValue(), dateString));
         }
         System.out.println("Done");
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
                                                  qty,
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
         sum += tradeAccountInfo.quantity;
      }

      return sum;
   }

   private Map<String, List<TradeAccountInfo>> processData(List<Map<String, Object>> data, String direction)
   {
      Map<String, List<TradeAccountInfo>> sharesByAccount = new HashMap<String, List<TradeAccountInfo>>();
      for (Map<String, Object> row : data)
      {
         Integer quantity = (Integer) row.get("qty");
         String accountNum = (String) row.get("clientAccountID");
         String ticker = (String) row.get("ticker");

         // Note this process is called only for Buy or Sell.  So, add to this list only one type.
         // Add to list if it is BUY.
         if (direction.startsWith("B") && quantity > 0) {
            List<TradeAccountInfo> tradeAccountInfos = sharesByAccount.get(ticker);
            if (tradeAccountInfos == null)
            {
               tradeAccountInfos = new LinkedList<TradeAccountInfo>();
               sharesByAccount.put(ticker, tradeAccountInfos);
            }
            tradeAccountInfos.add(new TradeAccountInfo(accountNum, quantity));
         }
         // Only add to list sells. of the flag is sell.
         if (direction.startsWith("S") && quantity < 0) {
            List<TradeAccountInfo> tradeAccountInfos = sharesByAccount.get(ticker);
            if (tradeAccountInfos == null)
            {
               tradeAccountInfos = new LinkedList<TradeAccountInfo>();
               sharesByAccount.put(ticker, tradeAccountInfos);
            }
            tradeAccountInfos.add(new TradeAccountInfo(accountNum, quantity));
         }

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
      String accountNum;
      Integer quantity;

      TradeAccountInfo(String accountNum, Integer quantity)
      {
         this.accountNum = accountNum;
         this.quantity = quantity;
      }
   }
}
