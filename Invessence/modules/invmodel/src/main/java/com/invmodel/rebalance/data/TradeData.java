package com.invmodel.rebalance.data;

/**
 * Created with IntelliJ IDEA.
 * User: Jigar
 * Date: 11/19/14
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class TradeData
{
   private String advisor;
   private String clientAccountID;
   private Long acctnum;
   private String assetclass;
   private String subclass;
   private String color;
   private String ticker;
   private String tradeDate;
   private String tradeCurrency;
   private Double qty;
   private Double money;
   private Double curPrice;

   private String allocTicker;
   private Double allocQty;
   private Double allocPrice, allocValue;
   private Double allocWeight;

   private String settleCurrency;
   private Double exchangeRate;
   private Double settleCurQty;
   private Double settleCurMoney;
   private Double settleCurPrice;

   private String tradeType;
   private String reason;
   private Double cashAvaailable;


   public TradeData()
   {
   }

   public TradeData(String advisor, String clientAccountID, Long acctnum,
                    String assetclass, String subclass, String color, String ticker,
                    String tradeCurrency, Double qty, Double curPrice, Double money,
                    String allocTicker, Double allocQty, Double allocPrice, Double allocValue, Double allocWeight,
                    String settleCurrency, Double exchangeRate, Double settleCurQty, Double settleCurMoney, Double settleCurPrice,
                    String tradeType, String reason, Double cashAvaailable)
   {
      this.advisor = advisor;
      this.clientAccountID = clientAccountID;
      this.acctnum = acctnum;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.color = color;
      this.ticker = ticker;
      this.tradeCurrency = tradeCurrency;
      this.qty = qty;
      this.money = money;
      this.curPrice = curPrice;
      this.allocTicker = allocTicker;
      this.allocQty = allocQty;
      this.allocPrice = allocPrice;
      this.allocValue = allocValue;
      this.allocWeight = allocWeight;
      this.settleCurrency = settleCurrency;
      this.exchangeRate = exchangeRate;
      this.settleCurQty = settleCurQty;
      this.settleCurMoney = settleCurMoney;
      this.settleCurPrice = settleCurPrice;
      this.tradeType = tradeType;
      this.reason = reason;
      this.cashAvaailable = cashAvaailable;
   }

   public double getCashAvaailable()
   {
      return cashAvaailable;
   }

   public void setCashAvaailable(double cashAvaailable)
   {
      this.cashAvaailable = cashAvaailable;
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

   public void setIbAccountNumber(String ibAccountNumber) {
      setClientAccountID(ibAccountNumber);
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

   public String getAllocTicker()
   {
      return allocTicker;
   }

   public void setAllocTicker(String allocTicker)
   {
      this.allocTicker = allocTicker;
   }

   public Double getAllocQty()
   {
      return allocQty;
   }

   public void setAllocQty(Double allocQty)
   {
      this.allocQty = allocQty;
   }

   public Double getAllocPrice()
   {
      return allocPrice;
   }

   public void setAllocPrice(Double allocPrice)
   {
      this.allocPrice = allocPrice;
   }

   public Double getAllocValue()
   {
      return allocValue;
   }

   public void setAllocValue(Double allocValue)
   {
      this.allocValue = allocValue;
   }

   public Double getAllocWeight()
   {
      return allocWeight;
   }

   public void setAllocWeight(Double allocWeight)
   {
      this.allocWeight = allocWeight;
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

   public Double getSettleCurQty()
   {
      return settleCurQty;
   }

   public void setSettleCurQty(Double settleCurQty)
   {
      this.settleCurQty = settleCurQty;
   }

   public Double getSettleCurMoney()
   {
      return settleCurMoney;
   }

   public void setSettleCurMoney(Double settleCurMoney)
   {
      this.settleCurMoney = settleCurMoney;
   }

   public Double getSettleCurPrice()
   {
      return settleCurPrice;
   }

   public void setSettleCurPrice(Double settleCurPrice)
   {
      this.settleCurPrice = settleCurPrice;
   }

   public void setCashAvaailable(Double cashAvaailable)
   {
      this.cashAvaailable = cashAvaailable;
   }

   public String getTradeType()
   {
      return tradeType;
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
