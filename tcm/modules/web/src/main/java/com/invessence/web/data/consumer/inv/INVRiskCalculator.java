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

   // Adjust for risk questionnaire
   //{0, 4, 8, 12, 50, 0, 0, 0, 0, 0}, // Q3    Add
   //{0, 16, 0, 0, 0, 0, 0, 0, 0, 0}, //  Q4      Add
   //{0, 4, 8, 12, 50, 0, 0, 0, 0, 0}, // Q5      Add
   //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q6   Compare
   //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q7   Compare
   //{0, 50, 75, 100, 100, 0, 0, 0, 0, 0}, //Q8   Compare
   //{0, 25, 50, 75, 100, 0, 0, 0, 0, 0}, // Q9   Compare
   //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, //
   //{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, //


   private Integer numberofQuestions;   // This defines the number of questions on the form.
   private String riskFormula;      // C=Consumer (By Questions), A=Advisor or D=Direct.  (
   private Double[] riskValues;     // Store each risk on DB Q1 = Position 1.  Zero is of default.
   private String[] answers;        // Each answer selected.
   private Integer riskAge;         // Age.
   private Integer retireAge;       // Calculate Horizon, if retired age is entered.
   private Integer riskHorizon;     // Horizon.  We are storing this horizon as well.
   private Double totalRisk;        // Final result to pass.
   SQLData converter = new SQLData();

   public INVRiskCalculator()
   {
      this.numberofQuestions = 1;
      resetAllData();
   }

   public INVRiskCalculator(Integer numberofQuestions)
   {
      this.numberofQuestions = numberofQuestions;
      resetAllData();
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
                     Integer ageValue = (getRiskAge() == null) ? 30 :  getRiskAge() ;
                     calcRisk = Math.pow((ageValue.doubleValue() / maxScore), agePowerValue);
                     calcRisk = Math.min(maxScore * calcRisk, ageWeight * maxScore);
                     calcRisk = calcRisk; // Divide Age Risk / 100
                     calcRisk = (calcRisk > 100) ? 100 : calcRisk;
                     riskValues[loop] = calcRisk; // Store the value in DB
                     break;
                  case 2:
                     Double calcHorizonRisk = 0.0;
                     Double maxDuration = 25.0; // This could be a constant
                     calcHorizonRisk = (maxDuration-getRiskHorizon()*(80/maxDuration)); // 80 is fixed since we are scaling risk 1 to 100
                     riskValues[loop] = calcHorizonRisk; // Store the value in DB
                     if (calcHorizonRisk > calcRisk)
                        calcRisk = calcHorizonRisk;
                     break;
                  case 3:
                  case 4:
                  case 5:
                     if (answers[loop] != null) {
                        Integer lookupindex = converter.getIntData(answers[loop]);
                        value = riskValueMatrix[loop][lookupindex];
                        calcRisk = calcRisk + value;
                        riskValues[loop] = value; // Store the value in DB
                     }
                     break;
                  default:
                     if (answers[loop] != null) {
                        Integer lookupindex = converter.getIntData(answers[loop]);
                        value = riskValueMatrix[loop][lookupindex];
                        riskValues[loop] = value; // Store the value in DB
                        if (value > calcRisk) {
                           calcRisk = value;
                        }
                     }
                     break;
               }
            }

            calcRisk = (calcRisk < 0.0) ? 0.0 : calcRisk;
            setTotalRisk(calcRisk);
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
      {
         return 0;
      }

      if (value < 3)
      {
         return 1;
      }

      if (value < 5)
      {
         return 2;
      }

      if (value < 10)
      {
         return 3;
      }

      if (value < 15)
      {
         return 4;
      }

      return 5;
   }

   public String getAns1()
   {
      return getAnswers()[1];
   }

   public void setAns1(String value)
   {
      setAnswer(1, value);
   }

   public String getAns2()
   {
      return getAnswers()[2];
   }

   public void setAns2(String value)
   {
      setAnswer(2, value);
   }

   public String getAns3()
   {
      return getAnswers()[3];
   }

   public void setAns3(String value)
   {
      setAnswer(3, value);
   }

   public String getAns4()
   {
      return getAnswers()[4];
   }

   public void setAns4(String value)
   {
      setAnswer(4, value);
   }

   public String getAns5()
   {
      return getAnswers()[5];
   }

   public void setAns5(String value)
   {
      setAnswer(5, value);
   }

   public String getAns6()
   {
      return getAnswers()[6];
   }

   public void setAns6(String value)
   {
      setAnswer(6, value);
   }

   public String getAns7()
   {
      return getAnswers()[7];
   }

   public void setAns7(String value)
   {
      setAnswer(7, value);
   }

   public String getAns8()
   {
      return getAnswers()[8];
   }

   public void setAns8(String value)
   {
      setAnswer(8, value);
   }

   public String getAns9()
   {
      return getAnswers()[9];
   }

   public void setAns9(String value)
   {
      setAnswer(9, value);
   }

   public String getAns10()
   {
      return getAnswers()[10];
   }

   public void setAns10(String value)
   {
      setAnswer(10, value);
   }

   public String getAns111()
   {
      return getAnswers()[11];
   }

   public void setAns11(String value)
   {
      setAnswer(11, value);
   }

   public String getAns12()
   {
      return getAnswers()[12];
   }

   public void setAns12(String value)
   {
      setAnswer(12, value);
   }

   public String getAns13()
   {
      return getAnswers()[13];
   }

   public void setAns13(String value)
   {
      setAnswer(13, value);
   }

   public String getAns14()
   {
      return getAnswers()[14];
   }

   public void setAns14(String value)
   {
      setAnswer(14, value);
   }

   public String getAns15()
   {
      return getAnswers()[15];
   }

   public void setAns15(String value)
   {
      setAnswer(15, value);
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
   public Integer getRiskValue(Integer index)
   {
      if (index < 1)
      {
         return 0;
      }

      if (index > riskValueMatrix.length)
      {
         return 0;
      }

      if (riskValues[index] == null)
      {
         return 0;
      }

      return riskValues[index].intValue();
   }

   @Override
   public Integer getAnswerValue(Integer index)
   {
      if (index < 1)
      {
         return 0;
      }

      if (index > riskValueMatrix.length)
      {
         return 0;
      }

      if (answers[index] == null)
      {
         return 0;
      }

      return converter.getIntData(answers[index]);
   }

   @Override
   public void setAnswer(Integer index, String value)
   {
      if (index < 1)
      {
         return;
      }

      if (index > riskValueMatrix.length)
      {
         return;
      }

      answers[index] = value;
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
