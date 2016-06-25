package com.invessence.web.data.consumer.inv;

import java.util.*;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.CustomerData;
import com.invessence.web.data.consumer.RiskCalculator;
import com.invmodel.Const.InvConst;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/13/16
 * Time: 9:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class INVRiskCalculator implements RiskCalculator
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



   public INVRiskCalculator()
   {
      this.numberofQuestions = 1;
      resetAllData();
   }

   public INVRiskCalculator(Integer numberofQuestions)
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
      Double riskIndex = 0.0;
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
               riskIndex = (riskIndex > value) ? riskIndex : value;
            }
         }
         setFinaRiskOffet(riskIndex.intValue());
         return riskIndex.intValue();
      }
      catch (Exception ex) {
         setFinaRiskOffet(0);
         return 0;
      }
   }

   // Deprecated: 2016-06-14
   @Override
   public Integer convertRiskWeight2Index(Double weight)
   {
      // Risk Weight is from 0 to 28, where  28 = low risk, 0 = high risk.
      // Return Index range is from 0 to 10. NOTE: 0 = low risk, and 10 = high risk.
      Integer value = 0;
      try
      {
         value = (int) (10.0 - (Math.round(weight / 2.9)));
      }
      catch (Exception ex)
      {
         value = 0;
      }
      return (value);
   }

   @Override
   public Integer getRiskOffset(Integer[] choices)
   {
      Double riskIndex = 0.0;
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
               riskIndex = (riskIndex > value) ? riskIndex : value;
            }
         }
         setFinaRiskOffet(riskIndex.intValue());
         return riskIndex.intValue();
      }
      catch (Exception ex) {
         setFinaRiskOffet(0);
         return 0;
      }
   }


   // Deprecated: 2016-06-14
   @Override
   public Double convertIndex2RiskWeight(Double index)
   {
      // Index range is from 0 to 10. NOTE: 0 = low risk, and 10 = high risk.
      // Returns Risk Weight from 0 to 28.  28 = low risk, 0 = high risk.
      Double value;
      value = 28 - ((2.0 * index) + Math.round(index / 1.2));
      return value;
   }

   // Deprecated: 2016-06-14
   @Override
   public Double offsetRiskIndex(CustomerData cdata)
   {
      Double riskOffset = 0.0;
      Double currentAssets;
      Double currentLiabilities;
      double dToEqtRatio;

/*    Prashant 5/8/2015 - We are using portfolio to determin if it is income or growth.
      // If objective is income preservation of asset, set the riskIndex to less half if smaller than half
      if (getObjective() == 1)
      {
         if (riskIndex < InvConst.MAX_RISK_OFFSET / 2)
         {
            riskIndex = InvConst.MAX_RISK_OFFSET / 2;
            setRiskIndex(riskIndex);
         }
      }
*/

      //12 months of liquid cash to meet expenses
      currentAssets = ((double) cdata.getTotalIncome() * 0.7 + cdata.getLiquidAsset());

      // 12 months of total liabilities expenses
      currentLiabilities = (double) cdata.getDependent() * cdata.getYearly() * InvConst.MONTHLY_CHILD_COST + cdata.getTotalExpense();


      dToEqtRatio = 0;
      riskOffset = 1.0 * getRiskOffset(cdata.getRiskAnswers());

      if (currentAssets > 0)
      {
         dToEqtRatio = currentLiabilities / currentAssets;
      }

      Integer dToEqtRatioReliable = 1;

      if (currentAssets < InvConst.MIN_CURRENT_ASSET)
      {
         dToEqtRatioReliable = 0;
      }

      if (dToEqtRatioReliable >= 1)
      {
         if (dToEqtRatio > 1.5)
         {
            riskOffset = InvConst.MAX_RISK_OFFSET.doubleValue();
         }
         else if (dToEqtRatio > 0.5)
         {
            riskOffset = riskOffset + (dToEqtRatio - 0.5) * (InvConst.MAX_RISK_OFFSET - riskOffset);
         }
      }

      if (riskOffset > InvConst.MAX_RISK_OFFSET)
      {
         return (InvConst.MAX_RISK_OFFSET).doubleValue();
      }
      else
      {
         return (riskOffset);
      }
   }

   // Deprecated: 2016-06-14
   @Override
   public Double calcRiskOffset(Integer age, Integer horizon, Double riskIndex)
   {
      Double adjRiskpoint;
      double baseNum = 1.0 + ((double) horizon / (double) age);
      double powerNum = -1.0 * ((double) horizon - 1.0);
      try
      {

         //pdata.offsetRiskIndex();
         Double riskOffset;
         if (riskIndex == null)
         {
            riskOffset = 0.0;
         }
         else
         {
            riskOffset = riskIndex;
         }


         adjRiskpoint = ((InvConst.MAX_RISK_OFFSET.doubleValue() - riskOffset) / InvConst.MAX_RISK_OFFSET);

         //This creates a very conservative portfolio
         //double adj = adj_riskpoint *(1 - Math.pow(baseNum, powerNum));
         adjRiskpoint = adjRiskpoint * (1.0 - Math.pow(baseNum, powerNum));

         return adjRiskpoint;
      }

      catch (Exception ex)
      {
         System.out.println("Exception on RiskOffer" + ex.getMessage());
         ex.printStackTrace();
      }
      return (0.0);
   }


}
