package com.invessence.converter;

import java.io.Serializable;
import java.text.*;
import java.util.Date;
import java.util.regex.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/16/14
 * Time: 6:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class JavaUtil implements Serializable
{

   public String displayFormat(Integer value, String format) {
      if (value == null) {
         return ("");
      }
      else {
         DecimalFormat df = new DecimalFormat(format);
         return df.format(value);
     }
   }

   public String displayFormat(Double value, String format) {
      if (value == null) {
         return ("");
      }
      else {
         DecimalFormat df = new DecimalFormat(format);
         return df.format(value);
      }
   }

   public String displayFormat(String value, String format) {
      if (value == null || value.isEmpty()) {
         return ("");
      }
      else {
         DecimalFormat df = new DecimalFormat(format);
         return df.format(Double.parseDouble(value));

      }
   }

   public String displayDateFormat(String input) {
      String year, month, date;
      try {
         if (input == null || input.isEmpty()) {
            return ("");
         }
         else {
            if (input.contains("/") || input.contains("-")) {
               return input;
            }
            else {
               date =  input.substring(6,8);
               month = input.substring(4,6);
               year = input.substring(0,4);
               return  month + "/" + date + "/" + year ;
            }
         }
      }
      catch (Exception ex) {
         return input;
      }
   }

   public static String UppercaseFirstLetters(String str)
   {
      if (str != null) {
         boolean prevWasWhiteSp = true;
         char[] chars = str.toCharArray();
         for (int i = 0; i < chars.length; i++) {
            if (Character.isLetter(chars[i])) {
               if (prevWasWhiteSp) {
                  chars[i] = Character.toUpperCase(chars[i]);
               }
               prevWasWhiteSp = false;
            } else {
               prevWasWhiteSp = Character.isWhitespace(chars[i]);
            }
         }
         return new String(chars);
      }
      else
         return null;
   }



   public static void main(String[] args) {
      //System.out.println(isValidDate("11/31/2016", "MM/dd/yyyy"));
      //System.out.println(isValidZipCode("98989"));
      //System.out.println(isValidSSN("343-22-4444"));
      String s="98";
      System.out.println(s==null || s.trim().equals("")?"":s.substring(0,s.length()>5?5:s.length()));
   }

   public static String compareDate(String enterDate)
   {
      String flag="";
      try{
         DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
         Date entryDate = userDateFormat.parse(enterDate);
         Date todayDate= new Date();
         todayDate = userDateFormat.parse(userDateFormat.format(todayDate));
         if(entryDate.compareTo(todayDate)>0){
            flag="A";
         }else if(entryDate.compareTo(todayDate)<0){
            flag="B";
         }else if(entryDate.compareTo(todayDate)==0){
            flag="E";
         }

      }catch(Exception ex){
         ex.printStackTrace();
      }
      return flag;
   }

   public static String checkYear(String enterDate)
   {
      String flag="";
      try{
         DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
         Date entryDate = userDateFormat.parse(enterDate);
         Date todayDate= new Date();
         todayDate = userDateFormat.parse(userDateFormat.format(todayDate));
         Long year=(todayDate.getTime()-entryDate.getTime())/(24 * 60 * 60 * 1000)/365;
         if(year>130){
            flag="F";
         }else {
            flag="S";
         }

      }catch(Exception ex){
         ex.printStackTrace();
      }
      return flag;
   }

   public static boolean checkdate(String enterDate)
   {
      Boolean flag=false;
      try{
         DateFormat userDateFormat = new SimpleDateFormat("MM/dd/yyyy");
         Date entryDate = userDateFormat.parse(enterDate);
         Date todayDate= new Date();
         if(entryDate.after(todayDate))
         {
            flag=true;
            System.out.println("entryDate is after todayDate");
         }

      }catch(Exception ex){
         ex.printStackTrace();
      }
      return flag;
   }

   public static boolean isValidDate(String value, String format) {
      Date date = null;
      try {
         SimpleDateFormat sdf = new SimpleDateFormat(format);
         date = sdf.parse(value);
         if (!value.equals(sdf.format(date))) {
            date = null;
         }
      } catch (ParseException ex) {
         ex.printStackTrace();
      }
      return date != null;
   }

   public static boolean isValidSSN(String value)
   {
      String regex = "^\\d{3}-\\d{2}-\\d{4}$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(value);
      return matcher.matches();
   }

   public static boolean isValidZipCode(String value)
   {
      String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
      Pattern pattern = Pattern.compile(regex);
      Matcher matcher = pattern.matcher(value);
      return matcher.matches();
   }
}
