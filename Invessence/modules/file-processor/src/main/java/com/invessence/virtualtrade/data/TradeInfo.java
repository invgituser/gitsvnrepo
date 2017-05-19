package com.invessence.virtualtrade.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/23/14
 * Time: 10:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class TradeInfo
{
   public String action;
   public Integer quantity;
   public String symbol, secType, exchange, currency, timeInForce;
   public String orderType, lmtPrice, basketTag, account, profile, orderRef;

   public TradeInfo(String action, Integer quantity, String symbol, String secType, String exchange, String currency, String timeInForce, String orderType, String lmtPrice, String basketTag, String account, String profile, String orderRef)
   {
      this.action = action;
      this.quantity = quantity;
      this.symbol = symbol;
      this.secType = secType;
      this.exchange = exchange;
      this.currency = currency;
      this.timeInForce = timeInForce;
      this.orderType = orderType;
      this.lmtPrice = lmtPrice;
      this.basketTag = basketTag;
      this.account = account;
      this.profile = profile;
      this.orderRef = orderRef;
   }
}
