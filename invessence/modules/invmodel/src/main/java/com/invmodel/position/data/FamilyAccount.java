package com.invmodel.position.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 11:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class FamilyAccount
{
   Long familyacctnum;
   Double totalValue;
   Double totalCostbasisValue;
   Double totalPnl;
   Double totalGainLoss;
   Double totalInvested;
   Double totalCash;
   boolean managed;

   Map<String, PositionDetailData> tickerdetail = null; // Key: Ticker, PositonDetail (Summed up)
   Map<String, Map<String, PositionDetailData>> accountdetail = null; // Key: Acct#, PositonDetail (Individual Trades)
   Map<String, PositionDetailData> assetdetail = null; // Key: AssetClass, PositonDetail (Summed up)
   Map<String, PositionDetailData> primeassetdetail = null;

   public FamilyAccount(Long familyacctnum)
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

   public boolean isManaged()
   {
      return managed;
   }

   public void setManaged(boolean managed)
   {
      this.managed = managed;
   }

   public Map<String, PositionDetailData> getTickerdetail()
   {
      return tickerdetail;
   }

   public Map<String, Map<String, PositionDetailData>> getAccountdetail()
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
                       Double gainloss,
                       boolean managed)
   {


      if (tickerdetail == null)  // Key: Ticker, PositonDetail (Summed up)
         tickerdetail = new LinkedHashMap<String, PositionDetailData>();

      if (accountdetail == null)  // Key: Acct#, PositonDetail (Individual Trades)
       accountdetail = new LinkedHashMap<String, Map<String, PositionDetailData>>();

      if (assetdetail == null)   // Key: AssetClass, PositonDetail (Summed up)
         assetdetail = new LinkedHashMap<String, PositionDetailData>();

      if (primeassetdetail == null)
         primeassetdetail = new LinkedHashMap<String, PositionDetailData>();

      // First add this trade to Account Mapping...
      String acctTickerKey;
      acctTickerKey = external_acct + "." + ticker;
      if (! accountdetail.containsKey(external_acct)) {
         Map<String, PositionDetailData> arrayposlist = new LinkedHashMap<String, PositionDetailData>();
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
                                                          gainloss,
                                                          managed);
         arrayposlist.put(ticker, pdd);
         accountdetail.put(external_acct, arrayposlist);
      }
      else {
         if (accountdetail.get(external_acct).containsKey(ticker)) {
            PositionDetailData tickerPdd = accountdetail.get(external_acct).get(ticker);
            tickerPdd.addDetailData(shares,
                                    value,
                                    costbasisValue,
                                    pnl,
                                    gainloss);
            accountdetail.get(external_acct).put(ticker,tickerPdd);
         }
         else {
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
                                                             gainloss,
                                                             managed);
            accountdetail.get(external_acct).put(ticker, pdd);
         }
      }


      if (! tickerdetail.containsKey(ticker)) {
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
                                                          gainloss,
                                                          managed);
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
                                                          gainloss,
                                                          managed);
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
                                                          gainloss,
                                                          managed);
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
            for (PositionDetailData pdd : accountdetail.get(extacct).values()) {
               accountValue[i] += pdd.getValue();
            }
            i++;
         }
      }
      catch (Exception ex) {

      }
      return accountValue;
   }

   public double[][] getManageAccountsbyTicker() {
      double [][] value = null;
      try {

         //This data will be based on input by fund within an account
         /* Example: 4 accounts, 12 tickers: 1 = managed, 0 = non-managed.
            In the example below, all four accounts can manage all 12 tickers.
         double[][] accountConstraints = new double[][] {
         {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
         {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}};
         */

         int numofacct = accountdetail.size();
         int numTickers = tickerdetail.size();
         int offset = 0;
         value = new double[numofacct][numTickers*numofacct];

         int i = 0;
         for (String extacct : accountdetail.keySet())
         {
            int j = 0;
            for (String ticker : tickerdetail.keySet())
            {
               offset = i*numTickers;
                  if (accountdetail.get(extacct).containsKey(ticker))
                  {
                     value[i][offset + j] = (accountdetail.get(extacct).get(ticker).isManage()) ? 1 : 0;
                  }
                  else
                  {
                     value[i][offset + j] = 0;
                  }
               j++;
            }
            i++;
         }
      }
      catch (Exception ex)
      {

      }
      return value;
   }

   public double[][] getAccountTickerValues() {
      double [][] value = null;
      try {
         /* Example: 4 accounts, 12 tickers:.
            In this example, we are passing the values regardless of it is managed on not..
         */

         int numofacct = accountdetail.size();
         int numTickers = tickerdetail.size();
         int offset = 0;
         value = new double[numofacct][numTickers*numofacct];

         int i = 0;
         for (String extacct : accountdetail.keySet())
         {
            int j = 0;
            for (String ticker : tickerdetail.keySet())
            {
               offset = i*numTickers;
               if (accountdetail.get(extacct).containsKey(ticker))
               {
                  value[i][offset + j] = accountdetail.get(extacct).get(ticker).getValue();
               }
               j++;
            }
            i++;
         }
      }
      catch (Exception ex)
      {

      }
      return value;
   }
}
