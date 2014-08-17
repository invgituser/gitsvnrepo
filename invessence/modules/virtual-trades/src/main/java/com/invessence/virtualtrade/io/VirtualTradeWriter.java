package com.invessence.virtualtrade.io;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.google.common.base.*;
import com.google.common.collect.Iterables;
import com.invessence.shared.io.BufferedFileFactory;

public class VirtualTradeWriter
{
   public static final String HEADER = "Action,Quantity,Symbol,SecType,Exchange,Currency,TimeInForce,OrderType,LmtPrice,BasketTag,Account,Profile,OrderRef";
   public static final String LINE_SEPARATOR = System.getProperty("line.separator");


   public void downloadTradesAllocationFile(List<Map<String, Object>> data)
   {

      String userHomeDir = System.getProperty("user.home");
      String fileName, filepath;
      Map<String, List<AccountInfo>> sharesByAccountNum = processData(data);
      fileName = getFileNameWithDateAndTime("_TRADES");
      filepath = userHomeDir + File.separator + fileName;
      writeTradesFile(sharesByAccountNum, filepath);

      fileName = getFileNameWithDateAndTime("_ALLOCATION_Profile");
      filepath = userHomeDir + File.separator + fileName;

      writeAllocationFile(sharesByAccountNum, filepath);
   }

   public void writeVirtualTrades(List<Map<String, Object>> data)
   {
      String userHomeDir = System.getProperty("user.home");
      String fileName, filepath;
      Map<String, List<AccountInfo>> sharesByAccountNum = processData(data);
      fileName = getFileNameWithDateAndTime("_TRADES");
      filepath = userHomeDir + File.separator + fileName;
      writeTradesFile(sharesByAccountNum, filepath);

      fileName = getFileNameWithDateAndTime("_ALLOCATION_Profile");
      filepath = userHomeDir + File.separator + fileName;

      writeAllocationFile(sharesByAccountNum, filepath);
   }

   private void writeAllocationFile(Map<String, List<AccountInfo>> sharesByAccountNum, String filepath)
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
            public String apply(java.lang.String s)
            {
               return s + "," + "Shares";
            }
         })));
         writer.newLine();
         writeWithNewLine(writer, "Profile, Account, Ratio");
         for (final String ticker : sharesByAccountNum.keySet())
         {
            writeWithNewLine(writer, Joiner.on("\n").join(Iterables.transform(sharesByAccountNum.get(ticker), new Function<AccountInfo, String>()
            {
               @Override
               public String apply(com.invessence.virtualtrade.io.VirtualTradeWriter.AccountInfo accountInfo)
               {
                  return ticker + "," + accountInfo.accountNum + "," + accountInfo.quantity;
               }
            })));
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

   private String getFileNameWithDateAndTime(String prefix)
   {
      Date date = new Date();
      SimpleDateFormat dateFormatWithDateAndTime = new SimpleDateFormat("yyyy-MMM-dd HH-mm-ss");
      return dateFormatWithDateAndTime.format(date) + prefix + ".csv";
   }

   private void writeTradesFile(Map<String, List<AccountInfo>> sharesByAccountNum, String filepath)
   {
      BufferedWriter writer = null;
      SimpleDateFormat dateFormatWithDay = new SimpleDateFormat("MMMddyyyy");
      String dateString = dateFormatWithDay.format(new Date());

      try
      {
         System.out.print("Generating Trades file: " + filepath + " ... ");
         writer = new BufferedFileFactory().getBufferedWriter(filepath);
         writeWithNewLine(writer, HEADER);

         for (Map.Entry<String, List<AccountInfo>> entry : sharesByAccountNum.entrySet())
         {
            writeWithNewLine(writer, prepareTradesLine(entry.getKey(), entry.getValue(), dateString, "BUY"));
            writeWithNewLine(writer, prepareTradesLine(entry.getKey(), entry.getValue(), dateString, "SELL"));
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

   private String prepareTradesLine(String ticker, List<AccountInfo> value, String date, String action)
   {
      return Joiner.on(",").join(Arrays.asList(action,
                                               sumQuantity(value, action),
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

   private String sumQuantity(List<AccountInfo> accountInfos, String action)
   {
      int sum = 0;
      for (AccountInfo accountInfo : accountInfos)
      {
         if (action.startsWith("B")) {
            if (accountInfo.quantity > 0)
               sum += accountInfo.quantity;
         }
         else if (action.startsWith("S")) {
            if (accountInfo.quantity < 0)
               sum += Math.abs(accountInfo.quantity);
         }

      }

      return Integer.toString(sum);
   }

   private Map<String, List<AccountInfo>> processData(List<Map<String, Object>> data)
   {
      Map<String, List<AccountInfo>> sharesByAccount = new HashMap<String, List<AccountInfo>>();
      for (Map<String, Object> row : data)
      {
         String accountNum = (String) row.get("acctnum");
         String ticker = (String) row.get("ticker");
         Integer quantity = (Integer) row.get("qty");

         List<AccountInfo> accountInfos = sharesByAccount.get(ticker);
         if (accountInfos == null)
         {
            accountInfos = new LinkedList<AccountInfo>();
            sharesByAccount.put(ticker, accountInfos);
         }
         accountInfos.add(new AccountInfo(accountNum, quantity));
      }
      return sharesByAccount;
   }

   void writeWithNewLine(Writer writer, String line) throws IOException
   {
      writer.write(line + LINE_SEPARATOR);
   }

   class AccountInfo
   {
      String accountNum;
      Integer quantity;

      AccountInfo(String accountNum, Integer quantity)
      {
         this.accountNum = accountNum;
         this.quantity = quantity;
      }
   }
}
