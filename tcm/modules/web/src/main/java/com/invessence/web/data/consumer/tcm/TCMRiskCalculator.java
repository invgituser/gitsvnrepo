package com.invessence.web.data.consumer.tcm;

import com.invessence.converter.*;
import com.invessence.web.data.common.CustomerData;
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
      {0.0, 1.0, 2.0, 3.0, 14.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #1 (Corresponds to Age)
      {0.0, 4.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q2 (Corresponds to Horizon)
      {0.0, 1.0, 2.0, 3.0, 14.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q3 Rest below is customizable
      {0.0, 7.0, 14.0, 21.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q4
      {0.0, 7.0, 14.0, 25.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q5
      {0.0, 14.0, 21.0, 28.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q6
      {0.0, 7.0, 14.0, 25.0, 28.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q7
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q8
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q9
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q10
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q11
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q12
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q13
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q14
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q15
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q16
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q17
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q18
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q19
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q20
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}  // Q21
   };
   private Integer numberofQuestions;
   private Double[] riskValues;     // NOTE: Q1 = Position 1.  Zero is of default.
   private String[] answers;
   private Integer riskAge;
   private Integer riskHorizon;
   private Integer finaRiskOffet;
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

   public Integer getRiskAge()
   {
      return riskAge;
   }

   public void setRiskAge(Integer riskAge)
   {
      this.riskAge = riskAge;
   }

   public Integer getRiskHorizon()
   {
      return riskHorizon;
   }

   public void setRiskHorizon(Integer riskHorizon)
   {
      this.riskHorizon = riskHorizon;
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
   public Integer getRiskOffset()
   {
      Integer riskIndex = 0;
      try
      {
         if (numberofQuestions == null) {
            setFinaRiskOffet(0);
            return 0;
         }
         for (int loop = 0; loop < numberofQuestions; loop++)
         {
            if (answers[loop] != null)
            {
               String ansstr = answers[loop];
               Integer ansvalue = converter.getIntData(ansstr);
               Double value = riskValueMatrix[loop][ansvalue];
               riskValues[loop] = value;
               riskIndex += value.intValue();
            }
         }
         setFinaRiskOffet(riskIndex);
         return riskIndex;
      }
      catch (Exception ex) {
         setFinaRiskOffet(0);
         return 0;
      }

   }

   public Integer getFinaRiskOffet()
   {
      return finaRiskOffet;
   }

   public void setFinaRiskOffet(Integer finaRiskOffet)
   {
      this.finaRiskOffet = finaRiskOffet;
   }

   public void resetAllData() {
      riskValues = new Double[riskValueMatrix.length];
      answers = new String[riskValueMatrix.length];
   }

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

   // Deprecated: 2016-06-14
   @Override
   public Integer getRiskOffset(Integer[] choice)
   {
      Integer riskIndex = 0;
      try
      {
         if (numberofQuestions == null) {
            setFinaRiskOffet(0);
            return 0;
         }
         for (int loop = 0; loop < numberofQuestions; loop++)
         {
            if (answers[loop] != null)
            {
               String ansstr = answers[loop];
               Integer ansvalue = converter.getIntData(ansstr);
               Double value = riskValueMatrix[loop][ansvalue];
               riskValues[loop] = value;
            }
         }
         return riskIndex;
      }
      catch (Exception ex) {
         setFinaRiskOffet(0);
         return 0;
      }
   }

   // Deprecated: 2016-06-14
   @Override
   public Double offsetRiskIndex(CustomerData cdata) {
      return 0.0;
   }

   // Deprecated: 2016-06-14
   @Override
   public Double calcRiskOffset(Integer age, Integer horizon, Double riskIndex) {
      return 0.0;
   }
}