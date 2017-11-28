package com.invmodel.risk.data;

import java.util.*;

/**
 * Created by prashant on 11/9/2017.
 */
public class AdvisorRiskMapping
{
   private Integer riskQuestion;
   private Integer numOfWeights;
   private Integer knockoutQuestion;
   private Double defaultWeight;
   private Map<Integer, Double> weights;

   public AdvisorRiskMapping()
   {
      this.riskQuestion = 0;
      this.numOfWeights = 0;
      this.knockoutQuestion = 0;
      this.defaultWeight = 0.0;
      this.weights = new HashMap<Integer, Double>();
   }

   public AdvisorRiskMapping(Integer riskQuestion,
                             Integer numOfWeights,
                             Integer knockoutQuestion,
                             Double defaultWeight,
                             Map<Integer, Double> weights)
   {
      this.riskQuestion = riskQuestion;
      this.numOfWeights = numOfWeights;
      this.knockoutQuestion = knockoutQuestion;
      this.defaultWeight = defaultWeight;
      if (weights == null) {
         this.weights = new HashMap<Integer, Double>();
      }
      else
      {
         this.weights = weights;
      }
   }

   public Integer getRiskQuestion()
   {
      return riskQuestion;
   }

   public void setRiskQuestion(Integer riskQuestion)
   {
      this.riskQuestion = riskQuestion;
   }

   public Integer getNumOfWeights()
   {
      return numOfWeights;
   }

   public void setNumOfWeights(Integer numOfWeights)
   {
      this.numOfWeights = numOfWeights;
   }

   public Integer getKnockoutQuestion()
   {
      return knockoutQuestion;
   }

   public void setKnockoutQuestion(Integer knockoutQuestion)
   {
      this.knockoutQuestion = knockoutQuestion;
   }

   public Double getDefaultWeight()
   {
      return defaultWeight;
   }

   public void setDefaultWeight(Double defaultWeight)
   {
      this.defaultWeight = defaultWeight;
   }

   public Double getWeight(Integer num)
   {
      if (num < 1 || num > numOfWeights)
         return 0.0;
      if (weights.containsKey(num))
         return weights.get(num);
      return 0.0;
   }

   public Double getRiskWeight(Integer num)
   {
      if (num > 0 && num <= numOfWeights)
         if (weights.containsKey(num))
            return weights.get(num);
      return getDefaultWeight();
   }

   /*
   public void setWeights(Map<Integer, Integer> weights)
   {
      this.weights = weights;
   }
   */

   public void setWeights(Integer num, Double  weights)
   {
      this.weights.put(num, weights);
   }


}
