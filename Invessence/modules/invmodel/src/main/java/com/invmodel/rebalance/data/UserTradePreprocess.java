package com.invmodel.rebalance.data;

/**
 * Created by prashant on 11/2/2017.
 */
public class UserTradePreprocess
{
   private String 	advisor	;
   private String 	clientAccountID	;
   private Long 	acctnum	;
   private String 	processed	;
   private String 	tradeDate	;
   private String 	tradeCurrency	;
   private String 	assetclass	;
   private String 	subclass	;
   private String 	color	;
   private String 	holdingTicker	;
   private Double 	curQty	;
   private Double 	curPrice	;
   private Double 	curValue	;
   private String 	newTicker	;
   private Double 	newQty	;
   private Double 	newPrice	;
   private Double 	newValue	;
   private String 	settleCurrency	;
   private Double 	settleCurQty	;
   private Double 	settleCurPrice	;
   private Double 	settleCurValue	;
   private Double 	exchangeRate	;
   private Double 	settleNewQty	;
   private Double 	settleNewPrice	;
   private Double 	settleNewValue	;
   private String    tradeType;
   private String 	reason	;

   public UserTradePreprocess()
   {
   }

   public UserTradePreprocess(String advisor, String clientAccountID, Long acctnum,
                              String processed, String tradeDate, String tradeCurrency,
                              String assetclass, String subclass, String color,
                              String holdingTicker, Double curQty, Double curPrice, Double curValue,
                              String newTicker, Double newQty, Double newPrice, Double newValue,
                              String settleCurrency, Double settleCurQty, Double settleCurPrice, Double settleCurValue,
                              Double exchangeRate,Double settleNewQty, Double settleNewPrice, Double settleNewValue,
                              String tradeType, String reason)
   {
      this.advisor = advisor;
      this.clientAccountID = clientAccountID;
      this.acctnum = acctnum;
      this.processed = processed;
      this.tradeDate = tradeDate;
      this.tradeCurrency = tradeCurrency;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.color = color;
      this.holdingTicker = holdingTicker;
      this.curQty = curQty;
      this.curPrice = curPrice;
      this.curValue = curValue;
      this.newTicker = newTicker;
      this.newQty = newQty;
      this.newPrice = newPrice;
      this.newValue = newValue;
      this.settleCurrency = settleCurrency;
      this.settleCurQty = settleCurQty;
      this.settleCurPrice = settleCurPrice;
      this.settleCurValue = settleCurValue;
      this.exchangeRate = exchangeRate;
      this.settleNewQty = settleNewQty;
      this.settleNewPrice = settleNewPrice;
      this.settleNewValue = settleNewValue;
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

   public String getProcessed()
   {
      return processed;
   }

   public void setProcessed(String processed)
   {
      this.processed = processed;
   }

   public String getTradeDate()
   {
      return tradeDate;
   }

   public void setTradeDate(String tradeDate)
   {
      this.tradeDate = tradeDate;
   }

   public String getTradeCurrency()
   {
      return tradeCurrency;
   }

   public void setTradeCurrency(String tradeCurrency)
   {
      this.tradeCurrency = tradeCurrency;
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

   public String getHoldingTicker()
   {
      return holdingTicker;
   }

   public void setHoldingTicker(String holdingTicker)
   {
      this.holdingTicker = holdingTicker;
   }

   public Double getCurQty()
   {
      return curQty;
   }

   public void setCurQty(Double curQty)
   {
      this.curQty = curQty;
   }

   public Double getCurPrice()
   {
      return curPrice;
   }

   public void setCurPrice(Double curPrice)
   {
      this.curPrice = curPrice;
   }

   public Double getCurValue()
   {
      return curValue;
   }

   public void setCurValue(Double curValue)
   {
      this.curValue = curValue;
   }

   public String getNewTicker()
   {
      return newTicker;
   }

   public void setNewTicker(String newTicker)
   {
      this.newTicker = newTicker;
   }

   public Double getNewQty()
   {
      return newQty;
   }

   public void setNewQty(Double newQty)
   {
      this.newQty = newQty;
   }

   public Double getNewPrice()
   {
      return newPrice;
   }

   public void setNewPrice(Double newPrice)
   {
      this.newPrice = newPrice;
   }

   public Double getNewValue()
   {
      return newValue;
   }

   public void setNewValue(Double newValue)
   {
      this.newValue = newValue;
   }

   public String getSettleCurrency()
   {
      return settleCurrency;
   }

   public void setSettleCurrency(String settleCurrency)
   {
      this.settleCurrency = settleCurrency;
   }

   public Double getSettleCurQty()
   {
      return settleCurQty;
   }

   public void setSettleCurQty(Double settleCurQty)
   {
      this.settleCurQty = settleCurQty;
   }

   public Double getSettleCurPrice()
   {
      return settleCurPrice;
   }

   public void setSettleCurPrice(Double settleCurPrice)
   {
      this.settleCurPrice = settleCurPrice;
   }

   public Double getSettleCurValue()
   {
      return settleCurValue;
   }

   public void setSettleCurValue(Double settleCurValue)
   {
      this.settleCurValue = settleCurValue;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(Double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public Double getSettleNewQty()
   {
      return settleNewQty;
   }

   public void setSettleNewQty(Double settleNewQty)
   {
      this.settleNewQty = settleNewQty;
   }

   public Double getSettleNewPrice()
   {
      return settleNewPrice;
   }

   public void setSettleNewPrice(Double settleNewPrice)
   {
      this.settleNewPrice = settleNewPrice;
   }

   public Double getSettleNewValue()
   {
      return settleNewValue;
   }

   public void setSettleNewValue(Double settleNewValue)
   {
      this.settleNewValue = settleNewValue;
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
