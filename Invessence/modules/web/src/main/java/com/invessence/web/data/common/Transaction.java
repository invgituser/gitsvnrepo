package com.invessence.web.data.common;

import java.io.Serializable;

/**
 * Created by sagar on 10/27/2017.
 */
public class Transaction implements Serializable
{
   private Long sortorder;
   private Long logonid;
   private Long acctnum;
   private Long instrumentid;
   private String ticker;
   private String assetclass;
   private String assetName;
   private String subclass;
   private String color;
   private String name;
   private Integer qty;
   private Double weight;
   private String subclassName;

   private String clientAccountID;
   private String accountAlias;
   private String currencyPrimary;
   private String description;
   private String side;
   private Double costBasisPrice;
   private Double costBasisMoney;
   private Double markPrice;
   private Double positionValue;
   private Double fifoPnlUnrealized;
   private String levelOfDetail;

   private String firstname, lastname,fullname;
   private String dateOpened;
   private Double yield;
   private Double expenseRatio;
   private Double risk;
   private Double fees;
   private Double goalAmount;
   private String tradeCurrency;
   private Double exchangeRate;
   private String settleCurrency;
   private Double settleQty;
   private Double settlePrice;
   private Double settleMoney;
   private Double settlePnL;
   private Double settleCostBasisMoney;
   private Double settleMarkPrice,price,netAmount,commission,otherFees,fxRateToBase,settleNetAmount,settleFees;
   private String rep,theme,isin,confirmNumber,transactionSource,transactionType,transactionStatus,controlNumber,tradeDate,settDate,voidDate,comment,tradedCurrency;
   private int quantity;

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public Long getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Long sortorder)
   {
      this.sortorder = sortorder;
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

   public Long getInstrumentid()
   {
      return instrumentid;
   }

   public void setInstrumentid(Long instrumentid)
   {
      this.instrumentid = instrumentid;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getAssetName()
   {
      return assetName;
   }

   public void setAssetName(String assetName)
   {
      this.assetName = assetName;
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public Integer getQty()
   {
      return qty;
   }

   public void setQty(Integer qty)
   {
      this.qty = qty;
   }

   public Double getWeight()
   {
      return weight;
   }

   public void setWeight(Double weight)
   {
      this.weight = weight;
   }

   public String getSubclassName()
   {
      return subclassName;
   }

   public void setSubclassName(String subclassName)
   {
      this.subclassName = subclassName;
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


   public String getDescription()
   {
      return description;
   }

   public void setDescription(String description)
   {
      this.description = description;
   }

   public String getSide()
   {
      return side;
   }

   public void setSide(String side)
   {
      this.side = side;
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

   public Double getMarkPrice()
   {
      return markPrice;
   }

   public void setMarkPrice(Double markPrice)
   {
      this.markPrice = markPrice;
   }

   public Double getPositionValue()
   {
      return positionValue;
   }

   public void setPositionValue(Double positionValue)
   {
      this.positionValue = positionValue;
   }

   public Double getFifoPnlUnrealized()
   {
      return fifoPnlUnrealized;
   }

   public void setFifoPnlUnrealized(Double fifoPnlUnrealized)
   {
      this.fifoPnlUnrealized = fifoPnlUnrealized;
   }

   public String getLevelOfDetail()
   {
      return levelOfDetail;
   }

   public void setLevelOfDetail(String levelOfDetail)
   {
      this.levelOfDetail = levelOfDetail;
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

   public String getFullname()
   {
      return fullname;
   }

   public void setFullname(String fullname)
   {
      this.fullname = fullname;
   }

   public String getDateOpened()
   {
      return dateOpened;
   }

   public void setDateOpened(String dateOpened)
   {
      this.dateOpened = dateOpened;
   }

   public Double getYield()
   {
      return yield;
   }

   public void setYield(Double yield)
   {
      this.yield = yield;
   }

   public Double getExpenseRatio()
   {
      return expenseRatio;
   }

   public void setExpenseRatio(Double expenseRatio)
   {
      this.expenseRatio = expenseRatio;
   }

   public Double getRisk()
   {
      return risk;
   }

   public void setRisk(Double risk)
   {
      this.risk = risk;
   }

   public Double getFees()
   {
      return fees;
   }

   public void setFees(Double fees)
   {
      this.fees = fees;
   }

   public Double getGoalAmount()
   {
      return goalAmount;
   }

   public void setGoalAmount(Double goalAmount)
   {
      this.goalAmount = goalAmount;
   }

   public String getTradeCurrency()
   {
      return tradeCurrency;
   }

   public void setTradeCurrency(String tradeCurrency)
   {
      this.tradeCurrency = tradeCurrency;
   }

   public Double getExchangeRate()
   {
      return exchangeRate;
   }

   public void setExchangeRate(Double exchangeRate)
   {
      this.exchangeRate = exchangeRate;
   }

   public String getSettleCurrency()
   {
      return settleCurrency;
   }

   public void setSettleCurrency(String settleCurrency)
   {
      this.settleCurrency = settleCurrency;
   }

   public Double getSettleQty()
   {
      return settleQty;
   }

   public void setSettleQty(Double settleQty)
   {
      this.settleQty = settleQty;
   }

   public Double getSettlePrice()
   {
      return settlePrice;
   }

   public void setSettlePrice(Double settlePrice)
   {
      this.settlePrice = settlePrice;
   }

   public Double getSettleMoney()
   {
      return settleMoney;
   }

   public void setSettleMoney(Double settleMoney)
   {
      this.settleMoney = settleMoney;
   }

   public Double getSettlePnL()
   {
      return settlePnL;
   }

   public void setSettlePnL(Double settlePnL)
   {
      this.settlePnL = settlePnL;
   }

   public Double getSettleCostBasisMoney()
   {
      return settleCostBasisMoney;
   }

   public void setSettleCostBasisMoney(Double settleCostBasisMoney)
   {
      this.settleCostBasisMoney = settleCostBasisMoney;
   }

   public Double getSettleMarkPrice()
   {
      return settleMarkPrice;
   }

   public void setSettleMarkPrice(Double settleMarkPrice)
   {
      this.settleMarkPrice = settleMarkPrice;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   public Double getNetAmount()
   {
      return netAmount;
   }

   public void setNetAmount(Double netAmount)
   {
      this.netAmount = netAmount;
   }

   public Double getCommission()
   {
      return commission;
   }

   public void setCommission(Double commission)
   {
      this.commission = commission;
   }

   public Double getOtherFees()
   {
      return otherFees;
   }

   public void setOtherFees(Double otherFees)
   {
      this.otherFees = otherFees;
   }

   public Double getFxRateToBase()
   {
      return fxRateToBase;
   }

   public void setFxRateToBase(Double fxRateToBase)
   {
      this.fxRateToBase = fxRateToBase;
   }

   public Double getSettleNetAmount()
   {
      return settleNetAmount;
   }

   public void setSettleNetAmount(Double settleNetAmount)
   {
      this.settleNetAmount = settleNetAmount;
   }

//   public Double getSettleFees()
//   {
//      return settleFees;
//   }
//
//   public void setSettleFees(Double settleFees)
//   {
//      this.settleFees = settleFees;
//   }

   public String getRep()
   {
      return rep;
   }

   public void setRep(String rep)
   {
      this.rep = rep;
   }

   public String getTheme()
   {
      return theme;
   }

   public void setTheme(String theme)
   {
      this.theme = theme;
   }

//   public String getIsin()
//   {
//      return isin;
//   }
//
//   public void setIsin(String isin)
//   {
//      this.isin = isin;
//   }

   public String getConfirmNumber()
   {
      return confirmNumber;
   }

   public void setConfirmNumber(String confirmNumber)
   {
      this.confirmNumber = confirmNumber;
   }

   public String getTransactionSource()
   {
      return transactionSource;
   }

   public void setTransactionSource(String transactionSource)
   {
      this.transactionSource = transactionSource;
   }

   public String getTransactionType()
   {
      return transactionType;
   }

   public void setTransactionType(String transactionType)
   {
      this.transactionType = transactionType;
   }

   public String getTransactionStatus()
   {
      return transactionStatus;
   }

   public void setTransactionStatus(String transactionStatus)
   {
      this.transactionStatus = transactionStatus;
   }

   public String getControlNumber()
   {
      return controlNumber;
   }

   public void setControlNumber(String controlNumber)
   {
      this.controlNumber = controlNumber;
   }

   public String getTradeDate()
   {
      return tradeDate;
   }

   public void setTradeDate(String tradeDate)
   {
      this.tradeDate = tradeDate;
   }

   public String getSettDate()
   {
      return settDate;
   }

   public void setSettDate(String settDate)
   {
      this.settDate = settDate;
   }

   public String getVoidDate()
   {
      return voidDate;
   }

   public void setVoidDate(String voidDate)
   {
      this.voidDate = voidDate;
   }

   public String getComment()
   {
      return comment;
   }

   public void setComment(String comment)
   {
      this.comment = comment;
   }

   public String getTradedCurrency()
   {
      return tradedCurrency;
   }

   public void setTradedCurrency(String tradedCurrency)
   {
      this.tradedCurrency = tradedCurrency;
   }

   public int getQuantity()
   {
      return quantity;
   }

   public void setQuantity(int quantity)
   {
      this.quantity = quantity;
   }
}
