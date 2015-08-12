package com.invmodel.ltam.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/11/15
 * Time: 9:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMPerformancePrintData
{
   String label;
   Double value;

   public LTAMPerformancePrintData(String label, Double value)
   {
      this.label = label;
      this.value = value;
   }

   public String getLabel()
   {
      return label;
   }

   public Double getValue()
   {
      return value;
   }
}
