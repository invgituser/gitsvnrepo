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
   private Double[] riskValues; // NOTE: Q1 = Position 1.  Zero is of default.
   private Double riskIndex;
   private Integer ageforRisk;
   private Integer ans1;
   private Integer ans2;
   private Integer ans3;
   private Integer ans4;
   private Integer ans5;
   private Integer ans6;
   private static Double maxScore = 100.0;
   private static Double maxAgeRiskWeight = 0.60;


   /* Formula to calc the values:
      Total number if points: eg 100
      Max risk assigned to age: eg 60 or .6
      Number of answ for each questions: eg: 5
      value 1: = 100*(1.0-0.6)/5, eg: 8.0
      value 2: = 3 * value1/4   , eg: 6.0
      value 3: = 2 * value1/4   , eg: 4.0
      value 4: = value1/4       , eg: 2.0
      value 5: = 0              , eg: 0
    */
   private static Double riskValueMatrix[][] = {
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0},   // Question #0 - Not used
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,0.0,0.0},   // Question #1 - is related to age.  We are now calc, ths risk on age.
      {0.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0,0.0,0.0},       // Q2
      {0.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0,0.0,0.0,0.0},   // Q3
      {0.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0,0.0,0.0,0.0},   // Q4
      {0.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0,0.0,0.0,0.0},   // Q5
      {0.0, 8.0, 6.0, 4.0, 2.0, 0.0, 0.0,0.0,0.0,0.0}    // Q6
   };



   public LTAMRiskData()
   {
      resetAllData();
   }

   public Integer getAgeforRisk()
   {
      return ageforRisk;
   }

   public void setAgeforRisk(Integer ageforRisk)
   {
      this.ageforRisk = ageforRisk;
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

   public String getTextAns6() {
      String textAns = "";
      if (getAns6() != null) {
         switch (getAns6()) {
            case 1:
               textAns = "Low";
               break;
            case 2:
               textAns = "Moderate";
               break;
            case 3:
               textAns = "Moderately High";
               break;
            case 4:
               textAns = "High";
               break;
            case 5:
               textAns = "Very High";
               break;
            default:
               textAns = "";
               break;
         }
      }
      return textAns;
   }

   public void setDefaultAns6(Integer ans)
   {
      if (ans6 == null || ans6 == 0)
         setAns6(ans);
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

   public Double[] getRiskValues() {
      return riskValues;
   }

   public Double getRiskValue(Integer ans) {
      if (ans == null)
         return 0.0;

      if (riskValueMatrix == null)
         return 0.0;

      if (ans > riskValueMatrix.length )
         return 0.0;

      return riskValues[ans];
   }

   public Double getRiskIndex()
   {
      return riskIndex;
   }

   public void setRiskIndex(Double riskIndex)
   {
      this.riskIndex = riskIndex;
   }

   public Double calcRiskIndex() {
      Double newRiskValue=0.0;
      try {
         if (ageforRisk != null && ageforRisk >= 0) {
            newRiskValue = Math.min((maxScore *
                                 Math.pow((ageforRisk.doubleValue()/ maxScore),2)),
                                 (maxScore * maxAgeRiskWeight));
         }

         // Note:  Although questons are weighted, we have calculated the fix value of each ans. (pre calculated).
         for (int i = 0; i < riskValueMatrix.length; i++) {
            if (riskValues[i] == null) {
               newRiskValue += 0.0; // If not entered, then assume highest risk
            }
            else {
               newRiskValue += riskValues[i];
            }
         }
      }
      catch (Exception ex) {
         newRiskValue = riskIndex;
      }
      finally
      {
         newRiskValue = ((maxScore - newRiskValue) < 0.0) ? 0.0 : (maxScore - newRiskValue);  // Offset the risk from Max point

         return newRiskValue;
      }


   }

   public void resetAllData() {
      riskValues = new Double[riskValueMatrix.length]; // NOTE: Q1 = Position 1.  null is of default.
      riskIndex = 0.0;
      ans1 = null;
      ans2 = null;
      ans3 = null;
      ans4 = null;
      ans5 = null;
      ans6 = null; // Default: it because of slider
   }
}
