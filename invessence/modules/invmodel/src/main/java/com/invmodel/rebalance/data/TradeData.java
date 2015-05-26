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
   private Double qty;
   private Double money;
   private Double curPrice;

   private String holdingTicker;
   private Double holdingQty;
   private Double holdingPrice, holdingValue;
   private Double holdingWeight, costBasisValue;

   private String allocTicker;
   private Double allocQty;
   private Double allocPrice, allocValue;
   private Double allocWeight;

   private String tradeType;
   private String reason;
   private String created;
   private double cashAvaailable;

   public TradeData()
   {
   }

   public TradeData(String advisor, String clientAccountID, Long acctnum, String assetclass, String subclass, String ticker,
                    Double qty, Double curPrice, Double money,
                    String holdingTicker, Double holdingQty, Double holdingPrice, Double holdingValue,
                    Double holdingWeight, Double costBasisValue,
                    String allocTicker, Double allocQty, Double allocPrice, Double allocValue, Double allocWeight,
                    String tradeType, String reason, double cashAvaailable)
   {
      this.advisor = advisor;
      this.clientAccountID = clientAccountID;
      this.acctnum = acctnum;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.ticker = ticker;
      this.qty = qty;
      this.money = money;
      this.curPrice = curPrice;
      this.holdingTicker = holdingTicker;
      this.holdingQty = holdingQty;
      this.holdingPrice = holdingPrice;
      this.holdingValue = holdingValue;
      this.holdingWeight = holdingWeight;
      this.costBasisValue = costBasisValue;
      this.allocTicker = allocTicker;
      this.allocQty = allocQty;
      this.allocPrice = allocPrice;
      this.allocValue = allocValue;
      this.allocWeight = allocWeight;
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

   public Double getHoldingWeight()
   {
      return holdingWeight;
   }

   public void setHoldingWeight(Double holdingWeight)
   {
      this.holdingWeight = holdingWeight;
   }

   public Double getCostBasisValue()
   {
      return costBasisValue;
   }

   public void setCostBasisValue(Double costBasisValue)
   {
      this.costBasisValue = costBasisValue;
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

   public String getCreated()
   {
      return created;
   }

   public void setCreated(String created)
   {
      this.created = created;
   }
}
