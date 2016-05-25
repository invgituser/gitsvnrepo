package com.invessence.rbsa.constants;

public interface RBSAServiceConstant
{
   public static String DEFAULT_ADVISOR="DEFAULT";
   public static String DEFAULT_THEME="DEFAULT";
   public static String INVESSENCE_ADVISOR="INVESSENCE";
   public static String CORE_THEME="CORE";

   public static Integer ASSET_INTERPOLATION = 80;
   public static Double ASSET_PRECISION = 0.00001;

   public static Integer MAX_DURATION = 80;
   public static Integer MAX_RISK_OFFSET = 28;

   // Used in Portfolio Model
   public static int PORTFOLIO_INTERPOLATION = 500;
   public static Double PORTFOLIO_PRECISION = 0.00001;
   public static double PORTFOLIO_MNAGEMENT_FEES = 0.0045;

   public static double MIN_CURRENT_ASSET = 10000.0;

   static final String YEAR_MONTH_DAY_FORMAT = "yyyyMMdd";
   static final String YEAR_MONTH_FORMAT = "yyyyMM";

   public static int FUND_INTERPOLATION = 200;
   public static Double FUND_TOLERANCE = 1.E-5;

   static final String[] tmp_sec_load = {
      "ticker",
      "bdate",
      "open",
      "high",
      "low",
      "close",
      "volume",
      "prevbdate",
      "prevclose"
   };

}
