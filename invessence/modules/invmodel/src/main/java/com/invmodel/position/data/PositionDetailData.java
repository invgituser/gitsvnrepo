package com.invmodel.position.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/19/15
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class PositionDetailData
{
   String external_acct;
   Long   acctnum;
   String ticker;
   String name;
   String index;
   String assetclass;
   String primeassetclass;
   Integer shares;
   Double price;
   Double value;
   Double costbasisValue;
   Double pnl;
   Double gainloss;

   public PositionDetailData()
   {
   }

   public PositionDetailData(String external_acct, Long acctnum, String ticker, String name,
                             String index, String assetclass, String primeassetclass,
                             Integer shares, Double price, Double value, Double costbasisValue,
                             Double pnl, Double gainloss)
   {
      this.external_acct = external_acct;
      this.acctnum = acctnum;
      this.ticker = ticker;
      this.name = name;
      this.index = index;
      this.assetclass = assetclass;
      this.primeassetclass = primeassetclass;
      this.shares = shares;
      this.price = price;
      this.value = value;
      this.costbasisValue = costbasisValue;
      this.pnl = pnl;
      this.gainloss = gainloss;
   }

   public String getExternal_acct()
   {
      return external_acct;
   }

   public void setExternal_acct(String external_acct)
   {
      this.external_acct = external_acct;
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

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getIndex()
   {
      return index;
   }

   public void setIndex(String index)
   {
      this.index = index;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getPrimeassetclass()
   {
      return primeassetclass;
   }

   public void setPrimeassetclass(String primeassetclass)
   {
      this.primeassetclass = primeassetclass;
   }

   public Integer getShares()
   {
      return shares;
   }

   public void setShares(Integer shares)
   {
      this.shares = shares;
   }

   public Double getPrice()
   {
      return price;
   }

   public void setPrice(Double price)
   {
      this.price = price;
   }

   public Double getValue()
   {
      return value;
   }

   public void setValue(Double value)
   {
      this.value = value;
   }

   public Double getCostbasisValue()
   {
      return costbasisValue;
   }

   public void setCostbasisValue(Double costbasisValue)
   {
      this.costbasisValue = costbasisValue;
   }

   public Double getPnl()
   {
      return pnl;
   }

   public void setPnl(Double pnl)
   {
      this.pnl = pnl;
   }

   public Double getGainloss()
   {
      return gainloss;
   }

   public void setGainloss(Double gainloss)
   {
      this.gainloss = gainloss;
   }

   public void addDetailData(Integer shares, Double value, Double costbasisValue,
                             Double pnl, Double gainloss) {

         setShares(getShares() + shares);
         setValue(getValue() + value);
         setCostbasisValue(getCostbasisValue() + costbasisValue);
         setPnl(getPnl() + pnl);
         setGainloss(getGainloss() + gainloss);
   }
}


