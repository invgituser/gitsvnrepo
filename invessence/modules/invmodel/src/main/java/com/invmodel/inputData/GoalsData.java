package com.invmodel.inputData;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/24/15
 * Time: 11:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class GoalsData
{
   private Double goalDesired;
   private Boolean reachable;
   private Double term;
   private Double interestRate;
   private Double risk;
   private Double actualInitialAmount;
   private Double actualRecurringAmount;
   private Integer yearGoalAchieved;
   private Double calcInitialAmount;
   private Double calcRecurringAmount;
   private ArrayList<Double> estimatedGrowth;
   private ArrayList<Double> upperGrowth;
   private ArrayList<Double> lowerGrowth;

   public GoalsData()
   {
      term = 0.0;
      estimatedGrowth = new ArrayList<Double>();
      upperGrowth = new ArrayList<Double>();
      lowerGrowth = new ArrayList<Double>();
   }

   public Boolean getReachable()
   {
      if (reachable == null) {
         return false;
      }
      return reachable;
   }

   public void setReachable(Boolean reachable)
   {
      this.reachable = reachable;
   }

   public Double getTerm()
   {
      return term;
   }

   public void setTerm(Double term)
   {
      this.term = term;
   }

   public Double getInterestrate()
   {
      return interestRate;
   }

   public void setInterestrate(Double interestrate)
   {
      this.interestRate = interestrate;
   }

   public Double getRisk()
   {
      return risk;
   }

   public void setRisk(Double risk)
   {
      this.risk = risk;
   }

   public Double getGoalDesired()
   {
      return goalDesired;
   }

   public void setGoalDesired(Double goalDesired)
   {
      this.goalDesired = goalDesired;
   }

   public Double getInterestRate()
   {
      return interestRate;
   }

   public void setInterestRate(Double interestRate)
   {
      this.interestRate = interestRate;
   }

   public Double getActualInitialAmount()
   {
      return actualInitialAmount;
   }

   public void setActualInitialAmount(Double actualInitialAmount)
   {
      this.actualInitialAmount = actualInitialAmount;
   }

   public Double getActualRecurringAmount()
   {
      return actualRecurringAmount;
   }

   public void setActualRecurringAmount(Double actualRecurringAmount)
   {
      this.actualRecurringAmount = actualRecurringAmount;
   }

   public Integer getYearGoalAchieved()
   {
      return yearGoalAchieved;
   }

   public void setYearGoalAchieved(Integer yearGoalAchieved)
   {
      this.yearGoalAchieved = yearGoalAchieved;
   }

   public Double getCalcInitialAmount()
   {
      return calcInitialAmount;
   }

   public void setCalcInitialAmount(Double calcInitialAmount)
   {
      if (calcInitialAmount != null)
         this.calcInitialAmount = (double) Math.round(calcInitialAmount);
   }

   public Double getCalcRecurringAmount()
   {
      return calcRecurringAmount;
   }

   public void setCalcRecurringAmount(Double calcRecurringAmount)
   {
      this.calcRecurringAmount = (double) Math.round(calcRecurringAmount);
   }

   public ArrayList<Double> getEstimatedGrowth()
   {
      return estimatedGrowth;
   }

   public ArrayList<Double> getLowerGrowth()
   {
      return lowerGrowth;
   }

   public ArrayList<Double> getUpperGrowth()
   {
      return upperGrowth;
   }

   public void clearGrowthData() {
      estimatedGrowth.clear();
      upperGrowth.clear();
      lowerGrowth.clear();
   }

   public void addGrowthData(Double estimated) {
      Integer pos;
      Double upper, lower;
      if (estimated != null) {
         pos = estimatedGrowth.size();
         estimatedGrowth.add(pos,estimated);
         if (pos == 0) {
            upper = estimated;
            lower = estimated;
            setCalcInitialAmount(estimated);
         }
         else {
            upper = ( 2 * risk * estimated ) + estimated;
            lower = ( -2 * risk * estimated ) + estimated;
         }
         upperGrowth.add(pos,upper);
         lowerGrowth.add(pos,lower);
      }
   }


}
