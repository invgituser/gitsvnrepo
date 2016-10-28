package com.invessence.rbsa.dao.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/13/15
 * Time: 2:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class RBSAData
{
   private String fundName;
   Map<String, Double> solution = new HashMap<String, Double>();
   private Integer maxNumofWeights = 0;
   private Double trackingError;

   public RBSAData()
   {
      maxNumofWeights = 0;
   }

   public RBSAData(String fundName)
   {
      this.fundName = fundName;
      maxNumofWeights = 0;
   }

   public String getFundName()
   {
      return fundName;
   }

   public void setFundName(String fundName)
   {
      this.fundName = fundName;
   }

   public Map<String, Double> getSolution()
   {
      return solution;
   }

   public void setSolution(String indexfund, Double weight)
   {
      if (! solution.containsKey(indexfund))
         maxNumofWeights++;
      solution.put(indexfund,weight);

   }

   public Integer getMaxNumofWeights()
   {
      return maxNumofWeights;
   }

   public Double getTrackingError()
   {
      return trackingError;
   }

   public void setTrackingError(Double trackingError)
   {
      this.trackingError = trackingError;
   }
}
