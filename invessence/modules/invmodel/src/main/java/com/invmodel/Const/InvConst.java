package com.invmodel.Const;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/9/13
 * Time: 6:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class InvConst
{
   // Used in Asset Model
   public static String DEFAULT_ADVISOR="INVESSENCE";
   public static String DEFAULT_THEME="BALANCE";

   public static Double MNGT_FEES = 0.0025;
   public static double MIN_MNGT_FEES_DOLLARS = 250.0;

   public static Integer ASSET_INTERPOLATION = 80;
   public static Double ASSET_PRECISION = 0.00001;

   public static Integer MAX_DURATION = 80;
   public static Integer MAX_RISK_OFFSET = 28;

   // Used in Portfolio Model
   public static int PORTFOLIO_INTERPOLATION = 500;
   public static Double PORTFOLIO_PRECISION = 0.00001;
   public static double PORTFOLIO_MNAGEMENT_FEES = 0.0045;

   public static double MIN_CURRENT_ASSET = 10000.0;

   public static double MONTHLY_CHILD_COST = 300;


}
