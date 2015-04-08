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
   private String email;
   private Double totalbought, totalsold, newcash, keepLiquid;
   private Double totalNewValue, totalHoldingValue;
   private Map<String, Asset> asset = new LinkedHashMap<String, Asset>();
   private Map<String, TradeData> tradeDetails = new LinkedHashMap<String, TradeData>();
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

   public Map<String, TradeData> getTradeDetails()
   {
      return tradeDetails;
   }

   public void setTradeDetails(Map<String, TradeData> tradeDetails)
   {
      this.tradeDetails = tradeDetails;
   }

   public ArrayList<TradeData> getTradeData() {
      ArrayList<TradeData> tradedata = new ArrayList<TradeData>();
      if (getTradeDetails() != null) {
         for (TradeData td : getTradeDetails().values())
            tradedata.add(td);
      }

      return tradedata;

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
      return totalNewValue;
   }

   public void setTotalNewValue(Double totalNewValue)
   {
      this.totalNewValue = totalNewValue;
   }

   public Double getTotalHoldingValue()
   {
      return totalHoldingValue;
   }

   public void setTotalHoldingValue(Double totalHoldingValue)
   {
      this.totalHoldingValue = totalHoldingValue;
   }
}
