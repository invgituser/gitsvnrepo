package com.invessence.web.data;

import java.io.Serializable;


/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeDetails implements Serializable
{
   private Long acctnum;
   private String clientAccountID;
   private String name;
   private String tradedate;
   private String tradedirection;
   private String assetclass;
   private String ticker;
   private String accttype;
   private Double currentqty, costBasisPrice, costBasisMoney;
   private Double currentValue, pnl;
   private Double newqty, newValue;
   private Double tradeqty, adjustedQty, tradeprice, tradeValue, priceperShare;
   private Double realizedPnL, tradedPnL,runningCashBal;
   private Double percentAllocated;
   private Integer sortOrder;

   public TradeDetails()
   {
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getTradedate()
   {
      return tradedate;
   }

   public void setTradedate(String tradedate)
   {
      this.tradedate = tradedate;
   }

   public String getTradedirection()
   {
      return tradedirection;
   }

   public void setTradedirection(String tradedirection)
   {
      this.tradedirection = tradedirection;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getAccttype()
   {
      return accttype;
   }

   public void setAccttype(String accttype)
   {
      this.accttype = accttype;
   }

   public Double getCurrentqty()
   {
      return currentqty;
   }

   public void setCurrentqty(Double currentqty)
   {
      this.currentqty = currentqty;
   }

   public Double getCostBasisPrice()
   {
      return costBasisPrice;
   }

   public void setCostBasisPrice(Double costBasisPrice)
   {
      this.costBasisPrice = costBasisPrice;
   }

   public Double getCostBasisMoney()
   {
      return costBasisMoney;
   }

   public void setCostBasisMoney(Double costBasisMoney)
   {
      this.costBasisMoney = costBasisMoney;
   }

   public Double getCurrentValue()
   {
      return currentValue;
   }

   public void setCurrentValue(Double currentValue)
   {
      this.currentValue = currentValue;
   }

   public Double getPnl()
   {
      return pnl;
   }

   public void setPnl(Double pnl)
   {
      this.pnl = pnl;
   }

   public Double getNewqty()
   {
      return newqty;
   }

   public void setNewqty(Double newqty)
   {
      this.newqty = newqty;
   }

   public Double getNewValue()
   {
      return newValue;
   }

   public void setNewValue(Double newValue)
   {
      this.newValue = newValue;
   }

   public Double getTradeqty()
   {
      return tradeqty;
   }

   public void setTradeqty(Double tradeqty)
   {
      this.tradeqty = tradeqty;
   }

   public Double getAdjustedQty()
   {
      return adjustedQty;
   }

   public void setAdjustedQty(Double adjustedQty)
   {
      this.adjustedQty = adjustedQty;
   }

   public Double getTradeprice()
   {
      return tradeprice;
   }

   public void setTradeprice(Double tradeprice)
   {
      this.tradeprice = tradeprice;
   }

   public Double getTradeValue()
   {
      return tradeValue;
   }

   public void setTradeValue(Double tradeValue)
   {
      this.tradeValue = tradeValue;
   }

   public Double getPriceperShare()
   {
      return priceperShare;
   }

   public void setPriceperShare(Double priceperShare)
   {
      this.priceperShare = priceperShare;
   }

   public Double getRealizedPnL()
   {
      return realizedPnL;
   }

   public void setRealizedPnL(Double realizedPnL)
   {
      this.realizedPnL = realizedPnL;
   }

   public Double getTradedPnL()
   {
      return tradedPnL;
   }

   public void setTradedPnL(Double tradedPnL)
   {
      this.tradedPnL = tradedPnL;
   }

   public Double getRunningCashBal()
   {
      return runningCashBal;
   }

   public void setRunningCashBal(Double runningCashBal)
   {
      this.runningCashBal = runningCashBal;
   }

   public Double getPercentAllocated()
   {
      return percentAllocated;
   }

   public void setPercentAllocated(Double percentAllocated)
   {
      this.percentAllocated = percentAllocated;
   }

   public Integer getSortOrder()
   {
      return sortOrder;
   }

   public void setSortOrder(Integer sortOrder)
   {
      this.sortOrder = sortOrder;
   }
}
