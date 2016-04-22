package com.invessence.constant;

public class Const
{
   public static String MENU_HOME = "Home";
   public final static String COMPANY_NAME = "Symbil";
   public final static String DEFAULT_THEME = "modena";
   public final static String DEFAULT_LOGO = "images/logo/Symbil.png";
   public final static String DEFAULT_LOGOLIB = "invessence";
   public final static String DEFAULT_ADVISOR = "9AK";

   public static String LOGONID_PARAM = "p_logonid";
   public static String ACCTNO_PARAM = "p_acctnum";
   public static String USER_INFO = "userinfo";
   public static String USERLOGON_ATTEMPTS = "attempts";
   public static String USERLOGON_ACCTTYPE = "accttype";
   public static String USERLOGON_ACCESS = "access";
   public static Integer MAX_ATTEMPTS = 4; // Offset from 0, therefore 2 = 3 attempts
   public static String DB_FUNCTION_ADD = "A";
   public static String DB_FUNCTION_MOD = "M";


   //public final static String WEBSITE_URL= "http://invessence.com";
   public static String MAIL_SENDER = "noreply@symbil.com";
   public static String MAIL_SUPPORT = "support@symbil.com";

   public final static String URL_HOME = "index.html";

   public final static String ROLE_ADMIN = "ADMIN";
   public final static String ROLE_USER = "USER";
   public final static String ROLE_SALES = "SALES";
   public final static String ROLE_SUPPORT = "SUPPORT";
   public final static String ROLE_ADVISOR = "ADVISOR";
   public final static String ROLE_DEVELOPER = "DEVELOPER";

   public final static String WEB_ALL = "ALL";
   public final static String WEB_USER = "USER";
   public final static String WEB_ADVISOR = "Advisor";
   public final static String WEB_ADMIN = "ADMIN";
   public final static String WEB_INTERNAL = "Symbil";

   public final static String WEB_MODE = "PROD";


   public enum Services {
      PRICING("PRICING"),BROKER_WEBSERVICES("BROKER-WEBSERVICES");
      private String value;

      private Services(String value) {
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
