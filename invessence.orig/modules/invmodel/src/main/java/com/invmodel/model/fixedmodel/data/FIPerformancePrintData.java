package com.invmodel.model.fixedmodel.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/11/15
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class FIPerformancePrintData
{
   String header;
   String value;

   public FIPerformancePrintData(String header, String value)
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
