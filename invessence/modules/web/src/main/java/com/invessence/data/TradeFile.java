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
public class TradeFile implements Serializable
{
   public static final String HEADER = "Action,Quantity,Symbol,SecType,Exchange,Currency,TimeInForce,OrderType,LmtPrice,BasketTag,Account,Profile,OrderRef";
   public String action,quantity,symbol,secType,exchange,currency,timeInForce,orderType,lmtPrice,basketTag,account,profile,orderRef;

   public TradeFile()
   {
   }

   public String getAction()
   {
      return action;
   }

   public void setAction(String action)
   {
      this.action = action;
   }

   public String getQuantity()
   {
      return quantity;
   }

   public void setQuantity(String quantity)
   {
      this.quantity = quantity;
   }

   public String getSymbol()
   {
      return symbol;
   }

   public void setSymbol(String symbol)
   {
      this.symbol = symbol;
   }

   public String getSecType()
   {
      return secType;
   }

   public void setSecType(String secType)
   {
      this.secType = secType;
   }

   public String getExchange()
   {
      return exchange;
   }

   public void setExchange(String exchange)
   {
      this.exchange = exchange;
   }

   public String getCurrency()
   {
      return currency;
   }

   public void setCurrency(String currency)
   {
      this.currency = currency;
   }

   public String getTimeInForce()
   {
      return timeInForce;
   }

   public void setTimeInForce(String timeInForce)
   {
      this.timeInForce = timeInForce;
   }

   public String getOrderType()
   {
      return orderType;
   }

   public void setOrderType(String orderType)
   {
      this.orderType = orderType;
   }

   public String getLmtPrice()
   {
      return lmtPrice;
   }

   public void setLmtPrice(String lmtPrice)
   {
      this.lmtPrice = lmtPrice;
   }

   public String getBasketTag()
   {
      return basketTag;
   }

   public void setBasketTag(String basketTag)
   {
      this.basketTag = basketTag;
   }

   public String getAccount()
   {
      return account;
   }

   public void setAccount(String account)
   {
      this.account = account;
   }

   public String getProfile()
   {
      return profile;
   }

   public void setProfile(String profile)
   {
      this.profile = profile;
   }

   public String getOrderRef()
   {
      return orderRef;
   }

   public void setOrderRef(String orderRef)
   {
      this.orderRef = orderRef;
   }
}
