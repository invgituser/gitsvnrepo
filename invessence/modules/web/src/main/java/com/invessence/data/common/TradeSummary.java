package com.invessence.data.common;

import java.util.*;

import com.invmodel.asset.data.Asset;
import com.invmodel.rebalance.data.TradeData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/2/15
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeSummary
{
   private String clientAccountID;
   private Long   acctnum;
   private String lastName, firstName;
   private String taxable, theme, goal;
   private String email;
   private Double totalbought, totalsold, newcash, keepLiquid;
   private Double totalNewValue, totalAllocValue, totalHoldingValue;
   private Map<String, RebalanceInfo> listofHoldingTickers = new HashMap<String, RebalanceInfo>();
   private Map<String, Asset> asset = new LinkedHashMap<String, Asset>();
   private ArrayList<TradeData> tradeDetails = new ArrayList<TradeData>();
   private TradeData cashDetail = new TradeData();

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getFullName()
   {
      return lastName + ", " + firstName;
   }

   public String getTaxable()
   {
      return taxable;
   }

   public void setTaxable(String taxable)
   {
      this.taxable = taxable;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

   public String getGoal()
   {
      return goal;
   }

   public void setGoal(String goal)
   {
      this.goal = goal;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public Map<String, Asset> getAsset()
   {
      return asset;
   }

   public void setAsset(Map<String, Asset> asset)
   {
      this.asset = asset;
   }

   public ArrayList<Asset> getManagedList(){
      ArrayList<Asset> managed = new ArrayList<Asset>();
      if (getAsset() != null) {
         for (Asset assetclass : getAsset().values())
            managed.add(assetclass);
      }

      return managed;
   }

   public Map<String, RebalanceInfo> getListofHoldingTickers()
   {
      return listofHoldingTickers;
   }

   public Double addListofHoldingTickers(String ticker, Double qty, Double value)
   {
      RebalanceInfo rinfo;
      Double newBalance = 0.0;
      Integer location = 0;
      if (listofHoldingTickers == null)
         listofHoldingTickers = new HashMap<String, RebalanceInfo>();
      if (! ticker.isEmpty()) {
         if (listofHoldingTickers.containsKey(ticker)) {
            rinfo = listofHoldingTickers.get(ticker);
            newBalance = rinfo.getValue() + value;
            rinfo.setQty(rinfo.getQty() + qty);
            rinfo.setValue(newBalance);
         }
         else {
            rinfo = new RebalanceInfo();
            location = listofHoldingTickers.size();
            rinfo.setQty(qty);
            rinfo.setValue(value);
            newBalance = value;
         }
         this.listofHoldingTickers.put(ticker,rinfo);
      }
      return newBalance;
   }

   public ArrayList<TradeData> getTradeDetails()
   {
      return tradeDetails;
   }

   public void setTradeDetails(ArrayList<TradeData> tradeDetails)
   {
      this.tradeDetails = tradeDetails;
   }

   public ArrayList<TradeData> getTradeData() {
      return tradeDetails;
   }

   public TradeData getCashDetail()
   {
      return cashDetail;
   }

   public void setCashDetail(TradeData cashDetail)
   {
      this.cashDetail = cashDetail;
   }

   public Double getTotalbought()
   {
      return totalbought;
   }

   public void setTotalbought(Double totalbought)
   {
      this.totalbought = totalbought;
   }

   public Double getTotalsold()
   {
      return totalsold;
   }

   public void setTotalsold(Double totalsold)
   {
      this.totalsold = totalsold;
   }

   public Double getNewcash()
   {
      return newcash;
   }

   public void setNewcash(Double newcash)
   {
      this.newcash = newcash;
   }

   public Double getKeepLiquid()
   {
      return keepLiquid;
   }

   public void setKeepLiquid(Double keepLiquid)
   {
      this.keepLiquid = keepLiquid;
   }

   public Double getTotalNewValue()
   {
      if (totalNewValue == null)
         return 0.0;

      return totalNewValue;
   }

   public void setTotalNewValue(Double totalNewValue)
   {
      this.totalNewValue = totalNewValue;
   }

   public Double getTotalAllocValue()
   {
      if (totalAllocValue == null)
         return 0.0;
      return totalAllocValue;
   }

   public void setTotalAllocValue(Double totalAllocValue)
   {
      this.totalAllocValue = totalAllocValue;
   }

   public Double getNewTotalValue() {
      return (this.totalNewValue - this.totalNewValue);
   }

   public Double getTotalHoldingValue()
   {
      if (totalHoldingValue == null)
         return 0.0;

      return totalHoldingValue;
   }

   public void setTotalHoldingValue(Double totalHoldingValue)
   {
      this.totalHoldingValue = totalHoldingValue;
   }

   public void addTotals() {
      Double totalHolding = 0.0, totalAlloc = 0.0, totalNewTrades = 0.0;
      // Double totalHoldingQty = 0.0, totalAllocQty = 0.0, totalNewTradesQty = 0.0;
      if (asset != null && asset.size() > 0) {

         for (String assetname : asset.keySet()) {
            totalHolding += asset.get(assetname).getHoldingValue();
            totalNewTrades += asset.get(assetname).getValue();
         }

         setTotalHoldingValue(totalHolding);
         setTotalAllocValue(totalNewTrades);
         // setTotalNewValue(totalNewTrades);
      }
   }

   public class RebalanceInfo {
      public Double qty;
      public Double value;

      public Double getQty()
      {
         return qty;
      }

      public void setQty(Double qty)
      {
         this.qty = qty;
      }

      public Double getValue()
      {
         return value;
      }

      public void setValue(Double value)
      {
         this.value = value;
      }
   }
}


