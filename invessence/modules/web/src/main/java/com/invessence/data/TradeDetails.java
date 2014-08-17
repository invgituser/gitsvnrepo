package com.invessence.data;

import java.io.Serializable;

import com.invessence.converter.JavaUtil;

/**
 * Created with IntelliJ IDEA.
 * User: pichaimanir
 * Date: 8/19/13
 * Time: 4:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TradeDetails implements Serializable
{
   private JavaUtil javaUtil = new JavaUtil();
   private Long acctnum;
   private String clientAccountID;
   private String name;
   private String tradedate;
   private String tradedirection;
   private String assetclass;
   private String ticker;
   private String accttype;
   private Integer posqty, newqty, tradeqty, adjustedqty;
   private Double tradeprice;
   private Double pricePerShare, gainloss, pnl;
   private Double posamount, newamount, tradeamount, runningCash;
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

   public Integer getPosqty()
   {
      return posqty;
   }

   public String getDisplayPosqty() {
      return javaUtil.displayFormat(getPosqty(),"##,###,###,###");
   }

   public void setPosqty(Integer posqty)
   {
      this.posqty = posqty;
   }

   public Integer getNewqty()
   {
      return newqty;
   }

   public String getDisplayNewqty() {
      return javaUtil.displayFormat(getNewqty(),"##,###,###,###");
   }

   public void setNewqty(Integer newqty)
   {
      this.newqty = newqty;
   }

   public Integer getTradeqty()
   {
      return tradeqty;
   }

   public String getDisplayTradeqty() {
      return javaUtil.displayFormat(getTradeqty(),"##,###,###,###");
   }

   public void setTradeqty(Integer tradeqty)
   {
      this.tradeqty = tradeqty;
   }

   public Integer getAdjustedqty()
   {
      return adjustedqty;
   }

   public void setAdjustedqty(Integer adjustedqty)
   {
      this.adjustedqty = adjustedqty;
   }

   public Double getTradeprice()
   {
      return tradeprice;
   }

   public void setTradeprice(Double tradeprice)
   {
      this.tradeprice = tradeprice;
   }

   public Double getPricePerShare()
   {
      return pricePerShare;
   }

   public void setPricePerShare(Double pricePerShare)
   {
      this.pricePerShare = pricePerShare;
   }

   public Double getGainloss()
   {
      return gainloss;
   }

   public void setGainloss(Double gainloss)
   {
      this.gainloss = gainloss;
   }

   public Double getPnl()
   {
      return pnl;
   }

   public void setPnl(Double pnl)
   {
      this.pnl = pnl;
   }

   public Double getPosamount()
   {
      return posamount;
   }

   public void setPosamount(Double posamount)
   {
      this.posamount = posamount;
   }

   public String getDisplayPosamount()
   {
      return javaUtil.displayFormat(getPosamount(), "##,###,###,###.00");
   }

   public Double getNewamount()
   {
      return newamount;
   }

   public void setNewamount(Double newamount)
   {
      this.newamount = newamount;
   }

   public String getDisplayNewamount()
   {
      return javaUtil.displayFormat(getNewamount(), "##,###,###,###.00");
   }

   public Double getTradeamount()
   {
      return tradeamount;
   }

   public void setTradeamount(Double tradeamount)
   {
      this.tradeamount = tradeamount;
   }

   public String getDisplayTradeamount()
   {
      return javaUtil.displayFormat(getTradeamount(),"##,###,###,###.00");
   }

   public Double getRunningCash()
   {
      return runningCash;
   }

   public void setRunningCash(Double runningCash)
   {
      this.runningCash = runningCash;
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
