package com.invessence.ws.bean;

/**
 * Created by abhangp on 4/29/2016.
 */
public class TransactionDetails
{
   private String transactionId;

   public TransactionDetails(String transactionId)
   {
      this.transactionId = transactionId;
   }

   public String getTransactionId()
   {
      return transactionId;
   }

   @Override
   public String toString()
   {
      return "TransactionDetails{" +
         "transactionId='" + transactionId + '\'' +
         '}';
   }

   public void setTransactionId(String transactionId)
   {
      this.transactionId = transactionId;
   }
}
