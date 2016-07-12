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
public class TCMRiskCalculator implements RiskCalculator
{
   private static Double riskValueMatrix[][] = {
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #0 Used as default.
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #1 (Corresponds to Age)
      {0.0, 30.00, 24.00, 12.00, 6.00, 0.00, 0.0, 0.0, 0.0, 0.0}, // Q2 (Corresponds to Horizon)
      {0.0, 5.0, 4.0, 2.5, 1.25, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q3 Rest below is customizable
      {0.0, 5.0, 4.0, 2.5, 1.25, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q4
      {0.0, 5.0, 4.0, 3.0, 2.00, 1.0, 0.0, 0.0, 0.0, 0.0}, // Q5
      {0.0, 14.0, 21.0, 28.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q6
      {0.0, 7.0, 14.0, 25.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q7
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q8
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q9
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q10
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q11
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q12
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q13
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q14
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}  // Q15
    };
   private Integer numberofQuestions;
   private String riskFormula;
   private Double[] riskValues;     // NOTE: Q1 = Position 1.  Zero is of default.
   private String[] answers;
   private Integer riskAge;
   private Integer retireAge;
   private Integer riskHorizon;
   private Double totalRisk;
   SQLData converter = new SQLData();



   public TCMRiskCalculator()
   {
      this.numberofQuestions = 1;
      resetAllData();
   }

   public TCMRiskCalculator(Integer numberofQuestions)
   {
      this.numberofQuestions =  numberofQuestions;
      resetAllData();
   }

   public Integer getNumberofQuestions()
   {
      return numberofQuestions;
   }

   public void setNumberofQuestions(Integer numberofQuestions)
   {
      this.numberofQuestions = numberofQuestions;
   }

   public Double[] getRiskValues()
   {
      return riskValues;
   }

   public void setRiskValues(Double[] riskValues)
   {
      this.riskValues = riskValues;
   }

   public String[] getAnswers()
   {
      return answers;
   }

   public void setAnswers(String[] answers)
   {
      this.answers = answers;
   }

   public Integer getHorizon2Index(Integer value)
   {
      if (value == null)
         return 0;

      if (value < 3)
         return 1;

      if (value < 5)
         return 2;

      if (value < 10)
         return 3;

      if (value < 15)
         return 4;

      return 5;
   }

   public String getAns1() {
      return getAnswers()[1];
   }

   public void setAns1(String value) {
      setAnswer(1,value);
   }

   public String getAns2() {
      return getAnswers()[2];
   }

   public void setAns2(String value) {
      setAnswer(2,value);
   }
   public String getAns3() {
      return getAnswers()[3];
   }

   public void setAns3(String value) {
      setAnswer(3,value);
   }
   public String getAns4() {
      return getAnswers()[4];
   }

   public void setAns4(String value) {
      setAnswer(4,value);
   }
   public String getAns5() {
      return getAnswers()[5];
   }

   public void setAns5(String value) {
      setAnswer(5,value);
   }
   public String getAns6() {
      return getAnswers()[6];
   }

   public void setAns6(String value) {
      setAnswer(6,value);
   }
   public String getAns7() {
      return getAnswers()[7];
   }

   public void setAns7(String value) {
      setAnswer(7,value);
   }
   public String getAns8() {
      return getAnswers()[8];
   }

   public void setAns8(String value) {
      setAnswer(8,value);
   }
   public String getAns9() {
      return getAnswers()[9];
   }

   public void setAns9(String value) {
      setAnswer(9,value);
   }
   public String getAns10() {
      return getAnswers()[10];
   }

   public void setAns10(String value) {
      setAnswer(10,value);
   }
   public String getAns111() {
      return getAnswers()[11];
   }

   public void setAns11(String value) {
      setAnswer(11,value);
   }
   public String getAns12() {
      return getAnswers()[12];
   }

   public void setAns12(String value) {
      setAnswer(12,value);
   }
   public String getAns13() {
      return getAnswers()[13];
   }

   public void setAns13(String value) {
      setAnswer(13,value);
   }
   public String getAns14() {
      return getAnswers()[14];
   }

   public void setAns14(String value) {
      setAnswer(14,value);
   }
   public String getAns15() {
      return getAnswers()[15];
   }

   public void setAns15(String value) {
      setAnswer(15,value);
   }


   @Override
   public String getRiskFormula()
   {
      return riskFormula;
   }

   @Override
   public void setRiskFormula(String value)
   {
      this.riskFormula = value;
   }

   @Override
   public Integer getRiskAge()
   {
      return riskAge;
   }

   @Override
   public void setRiskAge(Integer value)
   {
      this.riskAge = value;
      if (value != null) {
         answers[1] = value.toString();
      }
   }

   @Override
   public Integer getRetireAge()
   {
      return retireAge;
   }

   @Override
   public void setRetireAge(Integer value)
   {
      this.retireAge = value;
   }

   @Override
   public Integer getRiskHorizon()
   {
      return riskHorizon;
   }

   @Override
   public void setRiskHorizon(Integer value)
   {
      this.riskHorizon = value;
      if (value != null) {
         answers[2] = value.toString();
      }
   }

   public Double calculateRisk(String goal)
   {
      Double calcRisk = 0.0;
      Double adjustRisk = 0.0;
      Integer maxScore = 100;
      Double agePowerValue = 1.9;
      Double ageWeight = 1.0;
      try
      {
         if (riskFormula != null && riskFormula.equalsIgnoreCase("C")) {
            if (numberofQuestions == null) {
               setTotalRisk(0.0);
               return 0.0;
            }

            Integer ageValue = (getRiskAge() == null) ? 30 : getRiskAge();
            Double agePowerFunction = Math.pow((ageValue.doubleValue()/maxScore),agePowerValue);

            Double value;
            for (int loop = 1; loop < numberofQuestions + 1; loop++)
            {
               value = 0.0;
               switch (loop) {
                  case 1:
                     value = Math.min(maxScore * agePowerFunction, ageWeight * maxScore );
                     break;
                  case 2:
                     value = riskValueMatrix[loop][getHorizon2Index(getRiskHorizon())];
                     break;
                  default:
                     if (answers[loop] != null) {
                        Integer ansvalue = converter.getIntData(answers[loop]);
                        if (ansvalue > 0 && ansvalue < riskValueMatrix[loop].length) {
                           value = riskValueMatrix[loop][ansvalue];
                        }
                     }
                     break;
               }
               riskValues[loop] = value;
               calcRisk += value;
            }

            if (goal.equalsIgnoreCase("Retirement")) {
               calcRisk = calcRisk/maxScore;
               calcRisk = (calcRisk > maxScore) ? maxScore : calcRisk;
               calcRisk = (maxScore - calcRisk < 0.0) ? 0.0 : maxScore - calcRisk + 20;
               adjustRisk = 0.0;
               for (int loop = 1; loop < numberofQuestions + 1; loop++)
               {
                  value = 0.0;
                  switch (loop) {
                     case 1:
                        value = (maxScore - calcRisk < 0.0) ? 0.0 : maxScore - calcRisk + 20;
                        adjustRisk = value;
                        break;
                     default:
                        value = riskValues[loop] / 100.0;
                        adjustRisk -= value;
                  }
                  riskValues[loop] = value;
               }

            }
            else {
               for (int loop = 1; loop < numberofQuestions + 1; loop++)
               {
                  value = 0.0;
                  switch (loop) {
                     case 1:
                        adjustRisk = 120.0;
                        break;
                     default:
                        adjustRisk -= riskValues[loop];
                  }
               }
               adjustRisk  = adjustRisk / 100.0;

            }
            setTotalRisk(adjustRisk);
            return adjustRisk;
         }
         else {
            return totalRisk;
         }
      }
      catch (Exception ex) {
         setTotalRisk(0.0);
         return 0.0;
      }
   }

   @Override
   public Double getTotalRisk()
   {
      return totalRisk;
   }

   @Override
   public void setTotalRisk(Double value)
   {
      totalRisk = value;
   }

   @Override
   public Integer getRiskValue(Integer index) {
      if (index < 1)
         return 0;

      if (index > riskValueMatrix.length)
         return 0;

      if (riskValues[index] == null)
         return 0;

      return riskValues[index].intValue();
   }

   @Override
   public Integer getAnswerValue(Integer index) {
      if (index < 1)
         return 0;

      if (index > riskValueMatrix.length)
         return 0;

      if (answers[index] == null)
         return 0;

      return converter.getIntData(answers[index]);
   }

   @Override
   public void setAnswer(Integer index, String value) {
      if (index < 1)
         return;

      if (index > riskValueMatrix.length)
         return;

      answers[index] = value;
   }


   @Override
   public void resetAllData() {
      riskValues = new Double[riskValueMatrix.length];
      answers = new String[riskValueMatrix.length];
      riskAge = 30;
      riskHorizon = 10;
      totalRisk = 0.0;
      riskFormula="C";
   }

   @Override
   public Integer convertRiskWeight2Index(Double weight) {
      Integer value;
      try {
         Double dvalue = weight/100.0;
         value = dvalue.intValue();
         return value;

      }
      catch (Exception ex) {
         return 0;
      }
   }

   @Override
   public Double convertIndex2RiskWeight(Double index) {
      Double value;
      try {
         value = index*100.0;
         return value;
      }
      catch (Exception ex) {
         return 0.0;
      }

   }
}