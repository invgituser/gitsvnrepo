package com.invessence.data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class Position implements Serializable
{

   private Long logonid;
   private Long acctnum;
   private Long instrumentid;
   private String ticker;
   private String assetclass;
   private String subclass;
   private String color;
   private String name;
   private Integer qty;
   private Double weight;
   private Double origPrice;
   private Double origInvested;
   private Double price;
   private Double invested;
   private Double weightByPortfolio;

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

   private String firstname, lastname;
   private String IBacctnum;

   public Position()
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

   public Double getWeightAsPercent() {
      if (getWeight() != null)
         return (getWeight() * 100);
      else
         return 0.0;
   }

   public Double getOrigPrice()
   {
      return origPrice;
   }

   public void setOrigPrice(Double origPrice)
   {
      this.origPrice = origPrice;
   }

   public Double getOrigInvested()
   {
      return origInvested;
   }

   public void setOrigInvested(Double origInvested)
   {
      this.origInvested = origInvested;
   }

   public Double getWeightByPortfolio()
   {
      return weightByPortfolio;
   }

   public void setWeightByPortfolio(Double weightByPortfolio)
   {
      this.weightByPortfolio = weightByPortfolio;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   public Double getInvested()
   {
      return invested;
   }

   public void setInvested(Double invested)
   {
      this.invested = invested;
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

   public String getCurrencyPrimary()
   {
      return currencyPrimary;
   }

   public void setCurrencyPrimary(String currencyPrimary)
   {
      this.currencyPrimary = currencyPrimary;
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

   public String getIBacctnum()
   {
      return IBacctnum;
   }

   public void setIBacctnum(String IBacctnum)
   {
      this.IBacctnum = IBacctnum;
   }
}
