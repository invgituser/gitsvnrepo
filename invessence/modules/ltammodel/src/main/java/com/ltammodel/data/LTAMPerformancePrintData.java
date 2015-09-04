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

   public LTAMPerformancePrintData(String header, String value)
   {
      this.header = header;
      this.value = value;
   }

   public String getHeader()
   {
      return header;
   }

   public String getValue()
   {
      return value;
   }
}
