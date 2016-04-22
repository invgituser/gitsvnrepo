package com.invessence.ws.util;

/**
 * Created by abhangp on 4/7/2016.
 */
public class WSConstants
{
   public final static String dbInsertOpt ="INSERT";
   public final static String dbUpdateOpt="UPDATE";
   public final static String dbDeleteOpt="DELETE";

   public final static String succesResult="SUCCESS";
   public final static String failureResult="FAILURE";

   public enum BrokerWebServiceOperations {
      //PRICING("PRICING"),BROKER_WEBSERVICES("BROKER-WEBSERVICES");
      EMAIL_UPDATE("EMAIL_UPDATE"),
      MAILING_ADDRESS_UPDATE("MAILING_ADDRESS_UPDATE"),
      FUND_ACCOUNT("FUND_ACCOUNT"),
      FUND_TRANSFER("FUND_TRANSFER");
      private String value;

      private BrokerWebServiceOperations(String value) {
         this.value = value;
      }
      private String getValue() {
         return value;
      }

      @Override
      public String toString() {
         return this.getValue();
      }
   }

}
