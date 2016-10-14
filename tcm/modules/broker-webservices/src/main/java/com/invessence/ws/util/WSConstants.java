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
      GET_BANK_ACCT_DETAILS("GET_BANK_ACCT_DETAILS"),
      FUND_ACCOUNT("FUND_ACCOUNT"),
      FULL_FUND_TRANSFER("FULL_FUND_TRANSFER"),
      CREATE_USER("CREATE_USER"),
      GET_ACCT_INFO("GET_ACCT_INFO"),
      GET_MAILING_ADDRESS("GET_MAILING_ADDRESS"),
      UPDATE_PWD_WITH_NOAUTH("UPDATE_PWD_WITH_NOAUTH");


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

   public enum DocuSignServiceOperations {
      ACCT_APPLI_NEW("ACCT_APPLI_NEW"),
      ACCT_TRAN_NEW("ACCT_TRAN_NEW"),
      IRA_APPLI_NEW("IRA_APPLI_NEW"),
      IRA_MOVE_MONEY_NEW("IRA_MOVE_MONEY_NEW"),
      IRA_MOVE_MONEY_CHANGE("IRA_MOVE_MONEY_CHANGE"),
      IRA_MOVE_MONEY_REMOVE("IRA_MOVE_MONEY_REMOVE"),
      IRA_QRP_BENE_NEW("IRA_QRP_BENE_NEW"),
      MOVE_MONEY_NEW("MOVE_MONEY_NEW"),
      MOVE_MONEY_CHANGE("MOVE_MONEY_CHANGE"),
      MOVE_MONEY_REMOVE("MOVE_MONEY_REMOVE"),
      ELEC_FUND_TRAN_CHANGE("ELEC_FUND_TRAN_CHANGE"),
      ELEC_FUND_TRAN_NEW("ELEC_FUND_TRAN_NEW"),
      ELEC_FUND_TRAN_REPLACE("ELEC_FUND_TRAN_REPLACE"),
      TD_TRAN_NEW("TD_TRAN_NEW");

      private String value;

      private DocuSignServiceOperations(String value) {
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
