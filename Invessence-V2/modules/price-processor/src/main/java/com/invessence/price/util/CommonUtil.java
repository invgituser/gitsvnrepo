package com.invessence.price.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.*;

public class CommonUtil
{

   static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

   public static boolean dateCompare(String pDate, String lbDate)
   {
      try
      {

         Date bdate = sdf.parse(pDate);
         Date lbdate = sdf.parse(lbDate);


         System.out.println(pDate + " : " + lbDate);
         if (bdate.after(lbdate) || pDate.equals(lbDate))
         {
            return true;
            // else if(CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(),dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString())==true)
            //Date date = formatter.parse(dateInString);
            //System.out.println(date);
            //System.out.println(formatter.format(date));
            // if(CommonUtil.dateCompare(dbParamMap.get("BUSINESS_DATE").getValue().toString(),dbParamMap.get("LAST_BDATE_OF_MONTH").getValue().toString())==false)
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

      return false;

   }

   public static void main(String[] args)
   {//System.out.println(new CommonUtil().dateCompare());
   }

   public static Object stackTraceToString(StackTraceElement[] stackTrace)
   {
      // TODO Auto-generated method stub
      return null;
   }
}
