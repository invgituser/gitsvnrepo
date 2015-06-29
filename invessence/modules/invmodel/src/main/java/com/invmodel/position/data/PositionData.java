package com.invmodel.position.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class PositionData
{
   Long familyacctnum;
   Double totalValue;
   Double totalCostbasisValue;
   Double totalPnl;
   Double totalGainLoss;
   Double totalInvested;
   Double totalCash;

   Map<String, PositionDetailData> tickerdetail = null; // Key: Ticker, PositonDetail (Summed up)
   Map<String, ArrayList<PositionDetailData>> accountdetail = null; // Key: Acct#, PositonDetail (Individual Trades)
   Map<String, PositionDetailData> assetdetail = null; // Key: AssetClass, PositonDetail (Summed up)
   Map<String, PositionDetailData> primeassetdetail = null;

   public PositionData(Long familyacctnum)
   {
      this.familyacctnum = familyacctnum;
      totalValue = 0.0;
      totalCostbasisValue = 0.0;
      totalPnl = 0.0;
      totalGainLoss = 0.0;
      totalInvested = 0.0;
      totalCash = 0.0;

   }

   public Long getLogonid()
   {
      return familyacctnum;
   }

   public Double getTotalValue()
   {
      return totalValue;
   }

   public Double getTotalCostbasisValue()
   {
      return totalCostbasisValue;
   }

   public Double getTotalPnl()
   {
      return totalPnl;
   }

   public Double getTotalGainLoss()
   {
      return totalGainLoss;
   }

   public Double getTotalInvested()
   {
      return totalInvested;
   }

   public Double getTotalCash()
   {
      return totalCash;
   }

   public Map<String, PositionDetailData> getTickerdetail()
   {
      return tickerdetail;
   }

   public Map<String, ArrayList<PositionDetailData>> getAccountdetail()
   {
      return accountdetail;
   }

   public Map<String, PositionDetailData> getAssetdetail()
   {
      return assetdetail;
   }

   public Map<String, PositionDetailData> getPrimeassetdetail()
   {
      return primeassetdetail;
   }

   public void addInfo(String external_acct,
                       Long acctnum,
                       String ticker,
                       String name,
                       String index,
                       String assetclass,
                       String primeassetclass,
                       Integer shares,
                       Double price,
                       Double value,
                       Double costbasisValue,
                       Double pnl,
                       Double gainloss)
   {
      PositionDetailData pdd = new PositionDetailData( external_acct,
                                                       acctnum,
                                                       ticker,
                                                       name,
                                                       index,
                                                       assetclass,
                                                       primeassetclass,
                                                       shares,
                                                       price,
                                                       value,
                                                       costbasisValue,
                                                       pnl,
                                                       gainloss);


      if (tickerdetail == null)  // Key: Ticker, PositonDetail (Summed up)
         tickerdetail = new HashMap<String, PositionDetailData>();

      if (accountdetail == null)  // Key: Acct#, PositonDetail (Individual Trades)
       accountdetail = new HashMap<String, ArrayList<PositionDetailData>>();

      if (assetdetail == null)   // Key: AssetClass, PositonDetail (Summed up)
         assetdetail = new HashMap<String, PositionDetailData>();

      if (primeassetdetail == null)
         primeassetdetail = new HashMap<String, PositionDetailData>();

      // First add this trade to Account Mapping...
      if (! accountdetail.containsKey(external_acct)) {
         ArrayList<PositionDetailData> arrayposlist = new ArrayList<PositionDetailData>();
         arrayposlist.add(pdd);
         accountdetail.put(external_acct, arrayposlist);
      }
      else {
         accountdetail.get(external_acct).add(pdd);
      }

      if (! tickerdetail.containsKey(ticker)) {
        tickerdetail.put(ticker,pdd);
      }
      else {
         tickerdetail.get(ticker).addDetailData(shares,
                                                value,
                                                costbasisValue,
                                                pnl,
                                                gainloss);
      }

      if (! assetdetail.containsKey(assetclass)) {
         assetdetail.put(assetclass,pdd);
      }
      else {
         assetdetail.get(assetclass).addDetailData(shares,
                                                value,
                                                costbasisValue,
                                                pnl,
                                                gainloss);
      }

      if (! primeassetdetail.containsKey(primeassetclass)) {
         primeassetdetail.put(primeassetclass,pdd);
      }
      else {
         primeassetdetail.get(primeassetclass).addDetailData(shares,
                                                   value,
                                                   costbasisValue,
                                                   pnl,
                                                   gainloss);
      }

      totalValue += value;
      totalCostbasisValue += costbasisValue;
      totalPnl += pnl;
      totalGainLoss += gainloss;
      if (ticker.toUpperCase().equals("CASH"))
         totalCash += value;
      else
         totalInvested += value;

   }

   public String[] getTickerArray() {
      String [] tickerArray = null;
      try {
         int numoftickers = tickerdetail.size();
         tickerArray = new String[numoftickers];
         int i = 0;
         for (String ticker: tickerdetail.keySet()) {
            tickerArray[i] = ticker;
            i++;
         }
      }
      catch (Exception ex) {

      }
      return tickerArray;
   }

   public double[] getAccountValue() {
      double [] accountValue = null;
      try {
         int numofacct = accountdetail.size();
         accountValue = new double[numofacct];
         int i = 0;
         for (String extacct: accountdetail.keySet()) {
            accountValue[i] = 0.0;
            for (PositionDetailData pdd : accountdetail.get(extacct)) {
               accountValue[i] += pdd.getValue();
            }
            i++;
         }
      }
      catch (Exception ex) {

      }
      return accountValue;
   }
}
