package com.invmodel.dao.data;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/4/15
 * Time: 3:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class HolisticData
{
   String ticker;
   ArrayList<PrimeAssetClassData> primeassets = new ArrayList<PrimeAssetClassData>();
   ArrayList<Double> returns = new ArrayList<Double>();
   Integer maxReturns = 0;
   double totalrbsaweight;


   public String getTicker()
   {
      return ticker;
   }

   public void setTicker(String ticker)
   {
      this.ticker = ticker;
   }

   public ArrayList<PrimeAssetClassData> getPrimeassets()
   {
      return primeassets;
   }

   public void setPrimeassets(ArrayList<PrimeAssetClassData> primeassets)
   {
      this.primeassets = primeassets;
   }

   public ArrayList<Double> getReturns()
   {
      return returns;
   }

   public void setReturns(ArrayList<Double> returns)
   {
      this.returns = returns;
   }

   public Integer getMaxReturns()
   {
      return maxReturns;
   }

   public void setMaxReturns(Integer maxReturns)
   {
      this.maxReturns = maxReturns;
   }

   public double getTotalrbsaweight()
   {
      return totalrbsaweight;
   }

   public void setTotalrbsaweight(double totalrbsaweight)
   {
      this.totalrbsaweight = totalrbsaweight;
   }
}
