package com.invessence.rbsa.dao.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/2/15
 * Time: 9:01 PM
 * To change this template use File | Settings | File Templates.
 */
public class ReturnsData
{
   private String ticker;
   private String businessdate;
   private double value;

   public ReturnsData(String ticker, String businessdate, double value)
   {
      this.ticker = ticker;
      this.businessdate = businessdate;
      this.value = value;
   }

   public String getBusinessdate()
   {
      return businessdate;
   }

   public double getValue()
   {
      return value;
   }
}
