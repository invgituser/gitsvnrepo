package com.invessence.web.data.consumer.uob;

import com.invessence.web.data.consumer.RiskCalculator;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/13/16
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class UOBRiskCalculator extends RiskCalculator
{
   /*
   Explanation:  Array[0] is used for default as start up for each answers.  Start mode.
       Array[1] is reserved for Age.  Either it is fixed value or it is formula
       (if formula, then change the calculate function).
       Array[2] is for horizon.  Same as age (Either fixed or formula)
       Array[3] - Array[15] are based on answers to the security questions.
          - Each answer has weighted risk.
          - If answers are forrmula, then do all calucations in this calculate method.
    */

   private static Double riskValueMatrix[][] = {
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #0 Used as default.
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #1 (Corresponds to Age)
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q2 (Corresponds to Horizon)
      {0.0, 0.0, 4.0, 8.0, 12.0, 50.0, 0.0, 0.0, 0.0, 0.0}, // Q3 Rest below is customizable
      {0.0, 0.0, 16.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q4
      {0.0, 0.0, 4.0, 8.0, 12.0, 50.0, 0.0, 0.0, 0.0, 0.0}, // Q5
      {0.0, 0.0, 25.0, 50.0, 75.0, 100.0, 0.0, 0.0, 0.0, 0.0}, // Q6
      {0.0, 0.0, 25.0, 50.0, 75.0, 100.0, 0.0, 0.0, 0.0, 0.0}, // Q7
      {0.0, 0.0, 25.0, 50.0, 75.0, 100.0, 0.0, 0.0, 0.0, 0.0}, // Q8
      {0.0, 0.0, 25.0, 50.0, 75.0, 100.0, 0.0, 0.0, 0.0, 0.0}, // Q9
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q10
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q11
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q12
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q13
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q14
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}  // Q15
   };


   public UOBRiskCalculator()
   {
      riskValues = new Double[20];
      answers = new String[20];
      riskAge = 30;
      riskHorizon = 20;
      totalRisk = 0.0;
      riskFormula = "C";
      retired = 0;  // same as false;  Need to use 0,1 (because of dropdown on the menu)
   }

   public Double calculateRisk()
   {
      Double calcRisk = 0.0;
      Integer maxScore = 100;
      Double agePowerValue = 1.7;
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


            Double value;
            for (int loop = 1; loop < numberofQuestions + 1; loop++)
            {
               value = 0.0;
               switch (loop)
               {
                  case 1:
                     Double maxDuration = 15.0; // This could be a constant
                     double horPowerAdj = getRiskHorizon() / maxDuration;
                     if (horPowerAdj > 1.0)
                     {
                        horPowerAdj = 1.0;
                     }
                     agePowerValue = agePowerValue * horPowerAdj;

                     answers[loop] = getRiskAge().toString();
                     //answers[loop] = getRiskHorizon().toString();
                     Integer ageValue = (getRiskAge() == null) ? 30 : getRiskAge();
                     calcRisk = Math.pow((ageValue.doubleValue() / maxScore), agePowerValue);
                     calcRisk = Math.min(maxScore * calcRisk, ageWeight * maxScore);
                     calcRisk = (calcRisk > 100) ? 100 : calcRisk;
                     riskValues[loop] = calcRisk; // Store the value in DB
                     break;
                  case 2:
                     answers[loop] = getRiskHorizon().toString();
                     break;
                  /*case 2:
                     answers[loop] = getRiskHorizon().toString();
                     Double calcHorizonRisk = 0.0;
                     Double maxDuration = 25.0; // This could be a constant
                     calcHorizonRisk = (maxDuration-getRiskHorizon()*(80/maxDuration)); // 80 is fixed since we are scaling risk 1 to 100
                     riskValues[loop] = calcHorizonRisk; // Store the value in DB
                     if (calcHorizonRisk > calcRisk)
                     {
                        calcRisk = calcHorizonRisk;
                     }
                     break;
                     */
                  case 3:
                  case 4:
                  case 5:

                     if (answers[loop] != null && !answers[loop].isEmpty())
                     {
                        Integer lookupindex = converter.getIntData(answers[loop]);
                        value = riskValueMatrix[loop][lookupindex];
                        calcRisk = calcRisk + value;
                        riskValues[loop] = value; // Store the value in DB
                     }
                     break;
                  default:
                     if (answers[loop] != null && !answers[loop].isEmpty())
                     {
                        Integer lookupindex = converter.getIntData(answers[loop]);
                        value = riskValueMatrix[loop][lookupindex];
                        riskValues[loop] = value; // Store the value in DB
                        if (value > calcRisk)
                        {
                           calcRisk = value;
                        }
                     }
                     break;
               }
            }

            calcRisk = (calcRisk < 0.0) ? 0.0 : calcRisk;
            calcRisk = (calcRisk > 99.0) ? 99.0 : calcRisk;
            setTotalRisk(100.0 - calcRisk);
            return calcRisk;
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
      riskValues = new Double[riskValueMatrix.length];
      answers = new String[riskValueMatrix.length];
      riskAge = 30;
      riskHorizon = 10;
      totalRisk = 0.0;
      riskFormula = "C";
   }

   @Override
   public Integer convertRiskWeight2Index(Double weight)
   {
      Integer value;
      try
      {
         Double dvalue = weight / 100.0;
         value = dvalue.intValue();
         return value;

      }
      catch (Exception ex)
      {
         return 0;
      }
   }

   @Override
   public Double convertIndex2RiskWeight(Double index)
   {
      Double value;
      try
      {
         value = index * 100.0;
         return value;
      }
      catch (Exception ex)
      {
         return 0.0;
      }

   }
}
