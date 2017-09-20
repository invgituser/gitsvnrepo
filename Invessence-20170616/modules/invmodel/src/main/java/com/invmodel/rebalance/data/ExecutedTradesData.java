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
   private Long acctnum;
   private String clientAccountID;
   private String ticker;
   private String confirmNumber;
   private String transactionSource;
   private String transactionType;
   private String transactionStatus;
   private String controlNumber;
   private Double qty;
   private Double tradePrice;
   private Double netAmount;
   private Double commission;
   private Double otherFees;

   private String tradeDate;
   private String settDate;
   private String voidDate;

   private String currencyPrimary;
   private Double fxRateToBase;

   private Integer daysExecuted;

   public ExecutedTradesData()
   {
   }

   public ExecutedTradesData(Long acctnum, String clientAccountID,
                             String ticker, String confirmNumber, String transactionSource, String transactionType,
                             String transactionStatus, String controlNumber,
                             Double qty, Double tradePrice, Double netAmount, Double commission, Double otherFees,
                             String tradeDate, String settDate, String voidDate,
                             String currencyPrimary, Double fxRateToBase,
                             Integer daysExecuted)
   {
      this.acctnum = acctnum;
      this.clientAccountID = clientAccountID;
      this.ticker = ticker;
      this.confirmNumber = confirmNumber;
      this.transactionSource = transactionSource;
      this.transactionType = transactionType;
      this.transactionStatus = transactionStatus;
      this.controlNumber = controlNumber;
      this.qty = qty;
      this.tradePrice = tradePrice;
      this.netAmount = netAmount;
      this.commission = commission;
      this.otherFees = otherFees;
      this.tradeDate = tradeDate;
      this.settDate = settDate;
      this.voidDate = voidDate;
      this.currencyPrimary = currencyPrimary;
      this.fxRateToBase = fxRateToBase;
      this.daysExecuted = daysExecuted;
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

   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

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

   public Double getQty()
   {
      return qty;
   }

   public void setQty(Double qty)
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

   public String getCurrencyPrimary()
   {
      return currencyPrimary;
   }

   public void setCurrencyPrimary(String currencyPrimary)
   {
      this.currencyPrimary = currencyPrimary;
   }

   public Double getFxRateToBase()
   {
      return fxRateToBase;
   }

   public void setFxRateToBase(Double fxRateToBase)
   {
      this.fxRateToBase = fxRateToBase;
   }

   public Integer getDaysExecuted()
   {
      return daysExecuted;
   }

   public void setDaysExecuted(Integer daysExecuted)
   {
      this.daysExecuted = daysExecuted;
   }

   @Override
   public int compareTo(ExecutedTradesData compareExecutedTrades) {

      String compareDate = ((ExecutedTradesData) compareExecutedTrades).getTradeDate();
      Integer compareValue = Integer.valueOf(compareDate);
      Integer thisValue = Integer.valueOf(getTradeDate());

      //ascending order
      //return (thisValue - compareValue);

      //descending order
      return compareValue - thisValue;

   }

}
