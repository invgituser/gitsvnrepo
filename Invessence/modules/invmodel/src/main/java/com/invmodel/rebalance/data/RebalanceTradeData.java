package com.invmodel.rebalance.data;

/**
 * Created with IntelliJ IDEA.
 * User: Jigar
 * Date: 11/19/14
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class RebalanceTradeData
{
   private String advisor;
   private String clientAccountID;
   private Long acctnum;
   private String ticker;
   private String assetclass;
   private String subclass;
   private String color;
   private Double qty;
   private Double money;
   private Double curPrice;

   private String holdingTicker;
   private Double holdingQty;
   private Double holdingPrice, holdingValue;

   private Double newQty;
   private Double newValue;

   private String tradeType;
   private String reason;

   public RebalanceTradeData()
   {
   }

   public RebalanceTradeData(String advisor, String clientAccountID, Long acctnum, String ticker,
                             String assetclass, String subclass, String color,
                             Double qty, Double money, Double curPrice,
                             String holdingTicker, Double holdingQty, Double holdingPrice, Double holdingValue,
                             Double newQty, Double newValue,
                             String tradeType, String reason)
   {
      this.advisor = advisor;
      this.clientAccountID = clientAccountID;
      this.acctnum = acctnum;
      this.ticker = ticker;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.color = color;
      this.qty = qty;
      this.money = money;
      this.curPrice = curPrice;
      this.holdingTicker = holdingTicker;
      this.holdingQty = holdingQty;
      this.holdingPrice = holdingPrice;
      this.holdingValue = holdingValue;
      this.newQty = newQty;
      this.newValue = newValue;
      this.tradeType = tradeType;
      this.reason = reason;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

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

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public String getColor()
   {
      return color;
   }

   public void setColor(String color)
   {
      this.color = color;
   }

   public Double getQty()
   {
      return qty;
   }

   public void setQty(Double qty)
   {
      this.qty = qty;
   }

   public Double getMoney()
   {
      return money;
   }

   public void setMoney(Double money)
   {
      this.money = money;
   }

   public Double getCurPrice()
   {
      return curPrice;
   }

   public void setCurPrice(Double curPrice)
   {
      this.curPrice = curPrice;
   }

   public String getHoldingTicker()
   {
      return holdingTicker;
   }

   public void setHoldingTicker(String holdingTicker)
   {
      this.holdingTicker = holdingTicker;
   }

   public Double getHoldingQty()
   {
      return holdingQty;
   }

   public void setHoldingQty(Double holdingQty)
   {
      this.holdingQty = holdingQty;
   }

   public Double getHoldingPrice()
   {
      return holdingPrice;
   }

   public void setHoldingPrice(Double holdingPrice)
   {
      this.holdingPrice = holdingPrice;
   }

   public Double getHoldingValue()
   {
      return holdingValue;
   }

   public void setHoldingValue(Double holdingValue)
   {
      this.holdingValue = holdingValue;
   }

   public Double getNewQty()
   {
      return newQty;
   }

   public void setNewQty(Double newQty)
   {
      this.newQty = newQty;
   }

   public Double getNewValue()
   {
      return newValue;
   }

   public void setNewValue(Double newValue)
   {
      this.newValue = newValue;
   }

   public String getTradeType()
   {
      return tradeType;
   }

   public void setTradeType(String tradeType)
   {
      this.tradeType = tradeType;
   }

   public String getReason()
   {
      return reason;
   }

   public void setReason(String reason)
   {
      this.reason = reason;
   }
}
