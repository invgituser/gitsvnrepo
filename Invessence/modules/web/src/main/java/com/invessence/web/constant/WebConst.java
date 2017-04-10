package com.invessence.web.constant;

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
   public final static String ACCESS_USER_FULL = "F";
   // public final static String ACCESS_USER_VIEW = "V"; By default you have view privileges if data is entered in DB user_access_role

   public final static String WEB_CONSUMER = "User";
   public final static String WEB_ALL = "ALL";
   public final static String WEB_USER = "USER";
   public final static String WEB_ADVISOR = "Advisor";
   public final static String WEB_ADMIN = "ADMIN";

/*
   public final static String HTML_WELCOME = "HTML.WELCOME";
   public final static String HTML_LOCKED = "HTML.LOCKED";
   public final static String HTML_RESET = "HTML.RESET";
   public final static String HTML_WELCOME_ADV = "HTML.WELCOME.ADV";
   public final static String HTML_INVOICE = "HTML.INVOICE";
   public final static String HTML_SURVEY = "HTML.SURVEY";
*/
   public final static String EMAIL_ACTIVATE = "EMAIL_ACTIVATE";
   public final static String EMAIL_LOCKED = "EMAIL_LOCKED";
   public final static String EMAIL_RESET = "EMAIL_RESET";
   public final static String EMAIL_WELCOME = "EMAIL_WELCOME";
   public final static String EMAIL_WELCOME_ADV = "EMAIL_WELCOME_ADV";

   public final static String EMAIL_ACTIVATE_SUBJECT = "EMAIL.ACTIVATE.SUBJECT";
   public final static String EMAIL_LOCKED_SUBJECT = "EMAIL.LOCKED.SUBJECT";
   public final static String EMAIL_RESET_SUBJECT = "EMAIL.RESET.SUBJECT";
   public final static String EMAIL_WELCOME_SUBJECT = "EMAIL.WELCOME.SUBJECT";
   public final static String EMAIL_WELCOME_ADV_SUBJECT = "EMAIL.WELCOME.SUBJECT";

   public final static String HTML_BASE_PATH = "HTML.BASE.PATH";
   public final static String HTML_ACTIVATED = "HTML.ACTIVATED";
   public final static String HTML_WELCOME = "HTML.WELCOME";
   public final static String HTML_LOCKED = "HTML.LOCKED";
   public final static String HTML_RESET = "HTML.RESET";
   public final static String HTML_WELCOME_ADV = "HTML.WELCOME.ADV";
   public final static String HTML_INVOICE = "HTML.INVOICE";
   public final static String HTML_SURVEY = "HTML.SURVEY";

}
