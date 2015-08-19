package com.invessence.data.ltam;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/20/15
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMRiskData
{
   private Integer[] riskValues; // NOTE: Q1 = Position 1.  Zero is of default.
   private Integer riskIndex;
   private Integer ans1;
   private Integer ans2;
   private Integer ans3;
   private Integer ans4;
   private Integer ans5;
   private Integer ans6;
   private static Integer riskValueMatrix[][] = {
      {0, 0, 0, 0, 0, 0,0,0,0},   // Question #0 - Not used
      {6, 10, 8, 6, 4, 2,0,0,0},   // Question #1 - Based on answers (sorted)
      {9, 3, 6, 9, 12, 15,0,0,0},  // Q2
      {6, 2, 4, 6, 8, 10,0,0,0},   // Q3
      {9, 3, 6, 9, 12, 15,0,0,0},    // Q4
      {9, 3, 6, 9, 12, 15,0,0,0},    // Q5
      {12, 4, 8, 12, 16, 20,0,0,0}    // Q6
   };

   public LTAMRiskData()
   {
      riskValues = new Integer[riskValueMatrix.length];
      riskIndex = 51;
      ans1 = 0;
      ans2 = 0;
      ans3 = 0;
      ans4 = 0;
      ans5 = 0;
      ans6 = 0;
   }


   public Integer getAns1()
   {
      return ans1;
   }

   public void setAns1(Integer ans)
   {
      this.ans1 = ans;
      setRiskValues(1,ans);
   }

   public Integer getAns2()
   {
      return ans2;
   }

   public void setAns2(Integer ans)
   {
      this.ans2 = ans;
      setRiskValues(2,ans);
   }

   public Integer getAns3()
   {
      return ans3;
   }

   public void setAns3(Integer ans)
   {
      this.ans3 = ans;
      setRiskValues(3,ans);
   }

   public Integer getAns4()
   {
      return ans4;
   }

   public void setAns4(Integer ans)
   {
      this.ans4 = ans;
      setRiskValues(4,ans);
   }

   public Integer getAns5()
   {
      return ans5;
   }

   public void setAns5(Integer ans)
   {
      this.ans5 = ans;
      setRiskValues(5,ans);
   }

   public Integer getAns6()
   {
      return ans6;
   }

   public void setAns6(Integer ans)
   {
      this.ans6 = ans;
      setRiskValues(6,ans);
   }

   public void setRiskValues(Integer ans, Integer value) {
      if (ans > riskValues.length)
         return;

      if (ans > riskValueMatrix.length)
         return;
      else
      if (value > riskValueMatrix[ans].length)
         riskValues[ans] = null;
      else
         riskValues[ans] = riskValueMatrix[ans][value];
   }

   public Integer[] getRiskValues() {
      return riskValues;
   }

   public Integer getRiskValue(Integer ans) {
      if (ans == null)
         return 0;

      if (riskValueMatrix == null)
         return 0;

      if (ans > riskValueMatrix.length )
         return 0;

      return riskValues[ans];
   }

   public Integer getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(Integer riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public Integer calcRiskIndex() {
      Integer riskValue=0;
      try {
         for (int i = 0; i < riskValueMatrix.length; i++) {
            if (riskValues[i] == null) {
               riskValue += riskValueMatrix[i][0];
            }
            else {
               riskValue += riskValues[i];
            }
         }
      }
      catch (Exception ex) {
         riskValue = riskIndex;
      }
      return riskValue;

   }

   public void resetAllData() {
      riskValues = new Integer[riskValueMatrix.length]; // NOTE: Q1 = Position 1.  Zero is of default.
      ans1 = 0;
      ans2 = 0;
      ans3 = 0;
      ans4 = 0;
      ans5 = 0;
      ans6 = 0;
   }
}
