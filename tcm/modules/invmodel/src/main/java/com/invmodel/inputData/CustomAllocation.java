package com.invmodel.inputData;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/18/14
 * Time: 5:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomAllocation
{
   private String assetclass;
   private String subclass;
   private double risk;

   public CustomAllocation()
   {
   }

   public CustomAllocation(String assetclass, String subclass, double risk)
   {
      this.assetclass = assetclass;
      this.subclass = subclass;
      this.risk = risk;
   }

   public String getAssetclass()
   {
      return assetclass;
   }

   public void setAssetclass(String assetclass)
   {
      this.assetclass = assetclass;
   }

   public String getSubclass()
   {
      return subclass;
   }

   public void setSubclass(String subclass)
   {
      this.subclass = subclass;
   }

   public double getRisk()
   {
      return risk;
   }

   public void setRisk(double risk)
   {
      this.risk = risk;
   }
}
