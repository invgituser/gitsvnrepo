package com.ltammodel.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/11/15
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMPerformancePrintData
{
   String header;
   String value;
   String type;

   public LTAMPerformancePrintData(String header, String value, String type)
   {
      this.header = header;
      this.value = value;
      this.type = type;
   }

   public String getHeader()
   {
      return header;
   }

   public String getValue()
   {
      return value;
   }

   public Boolean isColor() {
      if (this.type.toUpperCase().startsWith("C"))
         return true;
      else
         return false;
   }

   public Boolean isIndex() {
      if (this.type.toUpperCase().startsWith("I"))
         return true;
      else
         return false;
   }

   public Boolean isValue() {
      if (this.type.toUpperCase().startsWith("V"))
         return true;
      else
         return false;
   }
}
