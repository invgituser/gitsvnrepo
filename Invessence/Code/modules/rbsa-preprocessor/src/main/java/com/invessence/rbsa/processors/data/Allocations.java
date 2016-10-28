package com.invessence.rbsa.processors.data;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 12/28/14
 * Time: 7:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Allocations
{
   private String advisor;
   private String asset;
   private String index;
   private double[] weight;

   public Allocations()
   {
   }

   public Allocations(String advisor, String asset, String index, double[] weight)
   {
      this.advisor = advisor;
      this.asset = asset;
      this.index =  index;
      this.weight = weight;
   }

   public String getAdvisor()
   {
      return advisor;
   }

   public void setAdvisor(String advisor)
   {
      this.advisor = advisor;
   }

   public String getAsset()
   {
      return asset;
   }

   public void setAsset(String asset)
   {
      this.asset = asset;
   }

   public String getIndex()
   {
      return index;
   }

   public void setIndex(String index)
   {
      this.index = index;
   }

   public double[] getWeight()
   {
      return weight;
   }

   public void setWeight(double[] weight)
   {
      this.weight = weight;
   }
}
