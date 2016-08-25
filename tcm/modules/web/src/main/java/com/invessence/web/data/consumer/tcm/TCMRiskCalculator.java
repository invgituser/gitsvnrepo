package com.invessence.web.data.consumer.tcm;

import com.invessence.converter.*;
import com.invessence.web.data.consumer.RiskCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/13/16
 * Time: 9:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class TCMRiskCalculator extends RiskCalculator
{

   private static Double RiskMatrix[][] = {
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #0 Used as default.
      {0.0, 60.00, 48.00, 42.00, 36.00, 30.00, 24.00, 18.00, 12.00, 6.00, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #1 (Corresponds to Age)
      {0.0, 60.00, 48.00, 42.00, 36.00, 30.00, 24.00, 18.00, 12.00, 6.00, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q2 (Corresponds to Horizon)
      {0.0, 20.0, 15.0, 10.0, 5.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q3 Rest below is customizable
      {0.0, 10.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q4
      {0.0, 10.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q5
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q6
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q7
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q8
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q9
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0, 0.0, 0.0, 0.0, 0.0}, // Q10
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q11
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q12
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q13
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q14
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}  // Q15
   };

   static Integer RETIRE_AGE_MAX = 85;


   public TCMRiskCalculator()
   {
      super();
      setNumberofQuestions(1);
      setRetireAge(null);
      resetAllData();
   }

   public TCMRiskCalculator(Integer numberofQuestions)
   {
      this.numberofQuestions = numberofQuestions;
      resetAllData();
   }

   @Override
   public Integer getRiskAge()
   {
      return riskAge;
   }

   @Override
   public void setRetireAge(Integer retireAge)
   {
      this.retireAge = retireAge;
   }


   @Override
   public void setRiskHorizon(Integer value)
   {
         this.riskHorizon = value;
   }

   private void redoRiskHorizon(Integer value)
   {
      if (getRiskAge() == null)
         return;

      Integer retiredAge = (value == null) ? 0: value;
      Integer age = getRiskAge();
      Integer calcValue;
      if (getRetireAge() != null) {
         if (getRetired() == 0) {  // Not retired
            calcValue =  ((retiredAge - age) < 0) ? 0 : (retiredAge - age);
            calcValue = calcValue + ((RETIRE_AGE_MAX - retiredAge)/2);
            calcValue = (calcValue < 0) ? 0 : calcValue;
            this.riskHorizon = calcValue;
         }
         else {  // Retired.
            calcValue =  ((RETIRE_AGE_MAX - age) < 0) ? 0 : (RETIRE_AGE_MAX - age);
            calcValue = (calcValue/2);
            calcValue = (calcValue < 0) ? 0 : calcValue;
            this.riskHorizon = calcValue;
         }
      }
      else {
         this.riskHorizon = value;
      }
   }

   private Integer getTimeHorizon() {
      Integer horizon = getRiskHorizon();
      try {
         if (getInvestmentobjective() == null)
            return horizon;

         if (horizon == null)
            horizon = 0;

         if (getInvestmentobjective().equalsIgnoreCase("Retirement")) {
            if (horizon <= 3)
               return 1;
            else
            if (horizon <= 6)
               return 2;
            else
            if (horizon <= 11)
               return 3;
            else
            if (horizon <= 17)
               return 4;
            else
            if (horizon <= 24)
               return 5;
            else
            if (horizon <= 30)
               return 6;
            else
            if (horizon <= 35)
               return 7;
            else
            if (horizon <= 40)
               return 8;
            else
            if (horizon <= 45)
               return 9;
            else
               return 10;
        }
         else {
            if (horizon <= 1)
               return 1;
            else
            if (horizon <= 2)
               return 2;
            else
            if (horizon <= 4)
               return 3;
            else
            if (horizon <= 7)
               return 4;
            else
            if (horizon <= 10)
               return 5;
            else
            if (horizon <= 15)
               return 6;
            else
            if (horizon <= 20)
               return 7;
            else
            if (horizon <= 25)
               return 8;
            else
            if (horizon <= 30)
               return 9;
            else
               return 10;

         }
      }
      catch (Exception ex) {
         return 0;
      }
   }

   public Double calculateRisk()
   {
      Double calcRisk = 0.0;
      Double adjustRisk = 0.0;
      Integer scoreMeasurement = 100;
      Double agePowerValue = 1.9;
      Double ageWeight = 1.0;
      try
      {
         if (riskFormula == null)
         {
            riskFormula = "C";
         }

         if (riskFormula.equalsIgnoreCase("C"))
         {
            if (numberofQuestions == null)
            {
               setTotalRisk(0.0);
               return 0.0;
            }

            redoRiskHorizon(getRetireAge());
            Double value;
            adjustRisk = 120.0;
            Integer lookupindex;
            for (int loop = 1; loop < numberofQuestions + 1; loop++)
            {
               value = 0.0;
               switch (loop)
               {
                  case 1:
                        riskValues[loop] = getRetired().doubleValue();
                     break;
                 default:
                     lookupindex = (loop == 2) ? getTimeHorizon() : converter.getIntData(answers[loop]);
                     if (lookupindex > 0 && lookupindex < RiskMatrix[loop].length)
                     {
                        value = RiskMatrix[loop][lookupindex];
                     }
                    adjustRisk -= value;
                    riskValues[loop] = value; // Adjust the RiskValue or Retirement
                    break;
               }
            }

            adjustRisk = (adjustRisk < 0.0) ? 0.0 : adjustRisk;
            setTotalRisk(adjustRisk);
            // System.out.println("Adjusted Risk (" + goal + "):" + calcRisk.toString() + " ");
            return adjustRisk;
         }
         else
         {
            return totalRisk;
         }
      }
      catch (Exception ex)
      {
         setTotalRisk(0.0);
         return 0.0;
      }
   }


   @Override
   public void resetAllData()
   {
      riskValues = new Double[RiskMatrix.length];
      answers = new String[RiskMatrix.length];
      riskAge = 30;
      riskHorizon = 20;
      totalRisk = 0.0;
      riskFormula = "C";
   }

}