package com.invmodel.rebalance.data;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant Mehta
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentHolding implements Serializable
{

   private Long logonid;
   private Long acctnum;
   private String clientAccountID;
   private String tradeCurrency;
   private String ticker;
   private String settleCurrency;
   private Double exchangeRate;

   private Double totalAllocation;
   private Double totalvalue;
   private Double totalmoney;
   private Double totalpnl;
   private Double totalYield;
   private Double totalExpenseRatio;
   private Double totalRisk;
   private Double totalFees;
   private Double shortGains;
   private Double longGains;
   private Double shortLoss;
   private Double longLoss;
   private Double cashAvailable;

   private List<HoldingData> holdingList;

   private Map<String, HoldingData> holdingDataMap = new HashMap<String, HoldingData>();

   public CurrentHolding()
   {
   }

   public Double getCashAvailable()
   {
      return ((cashAvailable == null) ? 0.0 : cashAvailable);
   }

   public void setCashAvailable(Double cashAvailable)
   {
      this.cashAvailable = cashAvailable;
   }

   public Double getShortGains()
   {
      return ((shortGains == null) ? 0.0 : shortGains);
   }

   public void setShortGains(Double shortGains)
   {
      this.shortGains = shortGains;
   }

   public Double getLongGains()
   {
      return ((longGains == null) ? 0.0 : longGains);
   }

   public void setLongGains(Double longGains)
   {
      this.longGains = longGains;
   }

   public Double getShortLoss()
   {
      return ((shortLoss == null) ? 0.0 : shortLoss);
   }

   public void setShortLoss(Double shortLoss)
   {
      this.shortLoss = shortLoss;
   }

   public Double getLongLoss()
   {
      return ((longLoss == null) ? 0.0 : longLoss);
   }

   public void setLongLoss(Double longLoss)
   {
      this.longLoss = longLoss;
   }


   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getClientAccountID()
   {
      return clientAccountID;
   }

   public void setClientAccountID(String clientAccountID)
   {
      this.clientAccountID = clientAccountID;
   }

   public List<HoldingData> getHoldingList()
   {
      return holdingList;
   }

   public void setHoldingList(List<HoldingData> holdingList)
   {
      this.holdingList = holdingList;
   }

   public Map<String, HoldingData> getHoldingDataMap()
   {
      return holdingDataMap;
   }

   public void setHoldingDataMap(Map<String, HoldingData> holdingDataMap)
   {
      this.holdingDataMap = holdingDataMap;
   }

   public Double getTotalAllocation()
   {
      return ((totalAllocation == null) ? 0.0 : totalAllocation);
   }

   public void setTotalAllocation(Double totalAllocation)
   {
      this.totalAllocation = totalAllocation;
   }

   public Double getTotalvalue()
   {
      return ((totalvalue == null) ? 0.0 : totalvalue);
   }

   public void setTotalvalue(Double totalvalue)
   {
      this.totalvalue = totalvalue;
   }

   public Double getTotalmoney()
   {
      return ((totalmoney == null) ? 0.0 : totalmoney);
   }

   public void setTotalmoney(Double totalmoney)
   {
      this.totalmoney = totalmoney;
   }

   public Double getTotalpnl()
   {
      return ((totalpnl == null) ? 0.0 : totalpnl);
   }

   public void setTotalpnl(Double totalpnl)
   {
      this.totalpnl = totalpnl;
   }

   public Double getTotalYield()
   {
      return ((totalYield == null) ? 0.0 : totalYield);
   }

   public void setTotalYield(Double totalYield)
   {
      this.totalYield = totalYield;
   }

   public Double getTotalExpenseRatio()
   {
      return ((totalExpenseRatio == null) ? 0.0 : totalExpenseRatio);
   }

   public void setTotalExpenseRatio(Double totalExpenseRatio)
   {
      this.totalExpenseRatio = totalExpenseRatio;
   }

   public Double getTotalRisk()
   {
      return ((totalRisk == null) ? 0.0 : totalRisk);
   }

   public void setTotalRisk(Double totalRisk)
   {
      this.totalRisk = totalRisk;
   }

   public Double getTotalFees()
   {
      return ((totalFees == null) ? 0.0 : totalFees);
   }

   public void setTotalFees(Double totalFees)
   {
      this.totalFees = totalFees;
   }

   public String getTradeCurrency()
   {
      return tradeCurrency;
   }

   public void setTradeCurrency(String tradeCurrency)
   {
      this.tradeCurrency = tradeCurrency;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getSettleCurrency()
   {
      return settleCurrency;
   }

   public void setSettleCurrency(String settleCurrency)
   {
      this.settleCurrency = settleCurrency;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(Double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public void addTotals()
   {
      Integer rows;
      try
      {
         totalAllocation = 0.0;
         totalvalue = 0.0;
         totalmoney = 0.0;
         totalpnl = 0.0;
         totalRisk = 0.0;
         totalYield = 0.0;
         totalExpenseRatio = 0.0;
         totalFees = 0.0;

         if (holdingList == null)
         {
            return;
         }
         rows = holdingList.size();
         int counter=0;
         holdingDataMap.clear();
         String ticker;
         for (int loop = 0; loop < rows; loop++)
         {
            HoldingData data = holdingList.get(loop);
            ticker = data.getTicker();
            if(!holdingDataMap.containsKey(ticker)){
                holdingDataMap.put(ticker,data);
            }
            totalAllocation = totalAllocation + data.getWeight();
            totalmoney = totalmoney + data.getCostBasisMoney();
            totalvalue = totalvalue + data.getPositionValue();
            totalpnl = totalpnl + data.getFifoPnlUnrealized();
         }
      }
      catch (Exception ex)
      {
         System.out.println("Error when attempting to addTotal(position)" + ex.getMessage());
         ex.printStackTrace();
      }

   }

}
