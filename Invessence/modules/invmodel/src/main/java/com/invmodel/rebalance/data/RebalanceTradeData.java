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
   private Long acctnum;
   private String clientAccountID;
   private String lastname;
   private String firstname;
   private String taxable;
   private String advisor;
   private String rep;
   private String assetclass;
   private String subclass;
   private String color;

   private String ticker;
   private String secname;
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

   private Integer sortorder;



   public RebalanceTradeData()
   {
   }

   public RebalanceTradeData(Long acctnum, String clientAccountID,
                             String lastname, String firstname, String taxable, String advisor, String rep,
                             String assetclass, String subclass, String color,
                             String ticker, String secname,
                             Double qty, Double money, Double curPrice,
                             String holdingTicker, Double holdingQty, Double holdingPrice, Double holdingValue,
                             Double newQty, Double newValue,
                             String tradeType, String reason, Integer sortorder)
   {
      this.acctnum = acctnum;
      this.clientAccountID = clientAccountID;
      this.lastname = lastname;
      this.firstname = firstname;
      this.taxable = taxable;
      this.advisor = advisor;
      this.rep = rep;
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.color = color;
      this.ticker = ticker;
      this.secname = secname;
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
      this.sortorder = sortorder;
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

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getTaxable()
   {
      return taxable;
   }

   public void setTaxable(String taxable)
   {
      this.taxable = taxable;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
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

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getSecname()
   {
      return secname;
   }

   public void setSecname(String secname)
   {
      this.secname = secname;
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

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
   }
}
