package com.invessence.constant;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 5/18/16
 * Time: 9:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class WebConst
{
   public final static String DEFAULT_THEME = "modena";
   public final static String DEFAULT_LOGO = "images/logo/LOGO.png";
   public final static String DEFAULT_LOGOLIB = "invessence";

   public static String LOGONID_PARAM = "p_logonid";
   public static String ACCTNO_PARAM = "p_acctnum";
   public static String USER_INFO = "userinfo";
   public static String USERLOGON_ATTEMPTS = "attempts";
   public static String USERLOGON_ACCTTYPE = "accttype";
   public static String USERLOGON_ACCESS = "access";
   public static Integer MAX_ATTEMPTS = 4; // Offset from 0, therefore 2 = 3 attempts
   public static String DB_FUNCTION_ADD = "A";
   public static String DB_FUNCTION_MOD = "M";


   public final static String URL_HOME = "index.html";

   public final static String ROLE_ADMIN = "ADMIN";
   public final static String ROLE_SALES = "SALES";
   public final static String ROLE_SUPPORT = "SUPPORT";
   public final static String ROLE_ADVISOR = "ADVISOR";
   public final static String DEVEL_ADVISOR = "DEVELOPER";

   public final static String ROLE_OWNER = "OWNER";
   public final static String ROLE_USER = "USER";

   public final static String WEB_CONSUMER = "User";
   public final static String WEB_ALL = "ALL";
   public final static String WEB_USER = "USER";
   public final static String WEB_ADVISOR = "Advisor";
   public final static String WEB_ADMIN = "ADMIN";

}
