package com.invessence.eod.web;

import java.io.*;
import java.net.*;
import java.util.*;


public class WebFetch
{

   String csvString;
   URL url = null;
   URLConnection urlConn = null;

   public List<Map<String, String>> fetchTickerInfo(List<Map<String, String>> data)
   {
      List<Map<String, String>> tickerInfos = new LinkedList<Map<String, String>>();
      for (Map<String, String> stringObjectMap : data)
      {
         tickerInfos.add(getQuote(stringObjectMap.get("ticker")));
      }
      return tickerInfos;
   }

   public Map<String, String> getQuote(String symbol)
   {
      Map<String, String> dataElement = new HashMap<String, String>();

      try
      {
         InputStreamReader inStream;

         String urlStr = WebConst.WEB_URL + symbol + WebConst.STOCK_FETCH_INFO;
         url = new URL(urlStr);

         urlConn = url.openConnection();
         inStream = new InputStreamReader(urlConn.getInputStream());
         BufferedReader buff = new BufferedReader(inStream);

         // get the quote as a csv string
         csvString = buff.readLine();

         // parse the csv string
         csvString = csvString.replaceAll("\"", "");
         StringTokenizer tokenizer = new StringTokenizer(csvString, ",");

         int numelement = 0;
         while (tokenizer.hasMoreElements())
         {
            dataElement.put(WebConst.quoteList[numelement++], tokenizer.nextToken());
         }

         inStream.close();
         buff.close();
      }
      catch (MalformedURLException e)
      {
         System.out.println("Please check the spelling of the URL:"
                               + e.toString());
         System.exit(-1);
      }
      catch (IOException e1)
      {
         System.out.println("Can't read from the Internet: " +
                               e1.toString());
         System.exit(-1);
      }
      catch (Exception e)
      {
         System.out.println("Other type of error, when attempting to access web: " +
                               e.toString());
         System.exit(-1);
      }
      return dataElement;
   }
}