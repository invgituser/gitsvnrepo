package com.invmodel.rebalance.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 11/24/14
 * Time: 2:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExecutedTradesData implements Comparable<ExecutedTradesData>
{
   private String tradeID;
   private String ticker;
   private String dateExecuted;
   private Integer qty;
   private Double tradePrice;
   private Double proceed;
   private Double tradeFee;
   private Integer lastExecuted;

   public ExecutedTradesData()
   {
   }

   public ExecutedTradesData(String tradeID, String ticker, String dateExecuted,
                             Integer qty, Double tradePrice, Double proceed, Double tradeFee, Integer lastExecuted)
   {
      this.tradeID = tradeID;
      this.ticker = ticker;
      this.dateExecuted = dateExecuted;
      this.qty = qty;
      this.tradePrice = tradePrice;
      this.proceed = proceed;
      this.tradeFee = tradeFee;
      this.lastExecuted = lastExecuted;
   }

   public String getTradeID()
   {
      return tradeID;
   }

   public void setTradeID(String tradeID)
   {
      this.tradeID = tradeID;
   }

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public String getDateExecuted()
   {
      return dateExecuted;
   }

   public void setDateExecuted(String dateExecuted)
   {
      this.dateExecuted = dateExecuted;
   }

   public Integer getQty()
   {
      return qty;
   }

   public void setQty(Integer qty)
   {
      this.qty = qty;
   }

   public Double getTradePrice()
   {
      return tradePrice;
   }

   public void setTradePrice(Double tradePrice)
   {
      this.tradePrice = tradePrice;
   }

   public Double getProceed()
   {
      return proceed;
   }

   public void setProceed(Double proceed)
   {
      this.proceed = proceed;
   }

   public Double getTradeFee()
   {
      return tradeFee;
   }

   public void setTradeFee(Double tradeFee)
   {
      this.tradeFee = tradeFee;
   }

   public Integer getLastExecuted()
   {
      return lastExecuted;
   }

   public void setLastExecuted(Integer lastExecuted)
   {
      this.lastExecuted = lastExecuted;
   }

   @Override
   public int compareTo(ExecutedTradesData compareExecutedTrades) {

      String compareDate = ((ExecutedTradesData) compareExecutedTrades).getDateExecuted();
      Integer compareValue = Integer.valueOf(compareDate);
      Integer thisValue = Integer.valueOf(getDateExecuted());

      //ascending order
      //return (thisValue - compareValue);

      //descending order
      return compareValue - thisValue;

   }

}
