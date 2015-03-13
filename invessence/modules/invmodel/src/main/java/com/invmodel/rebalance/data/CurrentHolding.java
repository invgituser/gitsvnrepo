package com.invmodel.rebalance.data;

import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class CurrentHolding implements Serializable
{

   private Long logonid;
   private Long acctnum;
   private String clientAccountID;
   private String accountAlias;
   private String firstname, lastname;
   private String dateOpened;

   private List<HoldingData> holdingList;

   private Map<String, HoldingData> holdingDataMap = new HashMap<String, HoldingData>();

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

   public Double getCashAvailable()
   {
      return cashAvailable;
   }

   public void setCashAvailable(Double cashAvailable)
   {
      this.cashAvailable = cashAvailable;
   }

   public Double getShortGains()
   {
      if (shortGains == null)
         return 0.0;
      return shortGains;
   }

   public void setShortGains(Double shortGains)
   {
      this.shortGains = shortGains;
   }

   public Double getLongGains()
   {
      if (longGains == null)
         return 0.0;
      return longGains;
   }

   public void setLongGains(Double longGains)
   {
      this.longGains = longGains;
   }

   public Double getShortLoss()
   {
      if (shortLoss == null)
         return 0.0;
      return shortLoss;
   }

   public void setShortLoss(Double shortLoss)
   {
      this.shortLoss = shortLoss;
   }

   public Double getLongLoss()
   {
      if (longLoss == null)
         return 0.0;
      return longLoss;
   }

   public void setLongLoss(Double longLoss)
   {
      this.longLoss = longLoss;
   }

   public CurrentHolding()
   {
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

   public String getAccountAlias()
   {
      return accountAlias;
   }

   public void setAccountAlias(String accountAlias)
   {
      this.accountAlias = accountAlias;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
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
      return totalAllocation;
   }

   public void setTotalAllocation(Double totalAllocation)
   {
      this.totalAllocation = totalAllocation;
   }

   public Double getTotalvalue()
   {
      return totalvalue;
   }

   public void setTotalvalue(Double totalvalue)
   {
      this.totalvalue = totalvalue;
   }

   public Double getTotalmoney()
   {
      return totalmoney;
   }

   public void setTotalmoney(Double totalmoney)
   {
      this.totalmoney = totalmoney;
   }

   public Double getTotalpnl()
   {
      return totalpnl;
   }

   public void setTotalpnl(Double totalpnl)
   {
      this.totalpnl = totalpnl;
   }

   public Double getTotalYield()
   {
      return totalYield;
   }

   public void setTotalYield(Double totalYield)
   {
      this.totalYield = totalYield;
   }

   public Double getTotalExpenseRatio()
   {
      return totalExpenseRatio;
   }

   public void setTotalExpenseRatio(Double totalExpenseRatio)
   {
      this.totalExpenseRatio = totalExpenseRatio;
   }

   public Double getTotalRisk()
   {
      return totalRisk;
   }

   public void setTotalRisk(Double totalRisk)
   {
      this.totalRisk = totalRisk;
   }

   public Double getTotalFees()
   {
      return totalFees;
   }

   public void setTotalFees(Double totalFees)
   {
      this.totalFees = totalFees;
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
