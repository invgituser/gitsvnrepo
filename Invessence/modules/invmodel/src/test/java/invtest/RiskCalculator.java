package invtest;

import com.invessence.converter.SQLData;

/**
 * Created by Prashant on 12/1/2016.
 */
public class RiskCalculator
{

   public Integer numberofQuestions;
   public String riskFormula;
   public Double[] riskValues;     // NOTE: Q1 = Position 1.  Zero is of default.
   public String[] answers;
   public Integer riskAge;
   public Integer retireAge;
   public Integer riskHorizon;
   public Double totalRisk;
   public Integer retired;
   public String  investmentobjective;
   public SQLData converter = new SQLData();

   /*
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

*/

   private static Double riskValueMatrix[][] = {
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #0 Used as default.
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Question #1 (Corresponds to Age)
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q2 (Corresponds to Horizon)
      {0.0, 0.0, 4.0, 8.0, 12.0, 50.0, 0.0, 0.0, 0.0, 0.0}, // Q3 -> Q1 of Risk
      {0.0, 0.0, 16.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q4 -> Q2 of Risk
      {0.0, 0.0, 4.0, 8.0, 12.0, 50.0, 0.0, 0.0, 0.0, 0.0}, // Q5
      {0.0, 0.0, 50.0, 100.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q6
      {0.0, 0.0, 25.0, 50.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q7
      {0.0, 0.0, 50.0, 100.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q8
      {0.0, 0.0, 25.0, 75.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q9
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q10
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q11
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q12
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q13
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}, // Q14
      {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0}  // Q15
   };

   public RiskCalculator()
   {
      riskValues = new Double[20];
      answers = new String[20];
      riskAge = 30;
      riskHorizon = 20;
      totalRisk = 0.0;
      riskFormula = "C";
      retired = 0;  // same as false;  Need to use 0,1 (because of dropdown on the menu)

   }

   public Integer getNumberofQuestions()
   {
      return numberofQuestions;
   }

   public void setNumberofQuestions(Integer numberofQuestions)
   {
      this.numberofQuestions = numberofQuestions;
   }

   public String getRiskFormula()
   {
      return riskFormula;
   }

   public void setRiskFormula(String riskFormula)
   {
      this.riskFormula = riskFormula;
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

   public void setRiskAge(Integer value)
   {
      this.riskAge = value;
      if (value == null) {
         answers[1] = null;
      }
      else
      {
         answers[1] = value.toString();
      }
   }

   public Integer getRetireAge()
   {
      return retireAge;
   }

   public void setRetireAge(Integer retireAge)
   {
      this.retireAge = retireAge;
      if (retireAge != null && getRiskAge() != null) {
         if (retireAge - getRiskAge() <= 0) {
            setRiskHorizon( null );
         }
         else {
            setRiskHorizon(retireAge - getRiskAge());
         }
      }
   }

   public Integer getRiskHorizon()
   {
      return riskHorizon;
   }

   public void setRiskHorizon(Integer riskHorizon)
   {
      this.riskHorizon = riskHorizon;
   }

   public Double getTotalRisk()
   {
      return totalRisk;
   }

   public void setTotalRisk(Double totalRisk)
   {
      this.totalRisk = totalRisk;
   }

   public Integer getRetired()
   {
      return retired;
   }

   public void setRetired(Integer retired)
   {
      this.retired = retired;
      if (retired != null && retired > 1) {
         setRetireAge(null);
      }
   }

   public String getRetiredStr() {
      if (retired != null && retired == 1)
         return "Retired";
      return "Not Retired";
   }

   public Boolean isRetired() {
      if (retired != null && retired == 1)
         return true;
      return false;
   }

   public String getInvestmentobjective()
   {
      return investmentobjective;
   }

   public void setInvestmentobjective(String investmentobjective)
   {
      if (investmentobjective != null)
         this.investmentobjective = investmentobjective.toLowerCase();
      else
         this.investmentobjective = null;
   }

   public Boolean isThisRetirement()
   {
      if (investmentobjective != null && investmentobjective.startsWith("retire")) {
         return true;
      }
      return false;
   }

   public SQLData getConverter()
   {
      return converter;
   }

   public void setConverter(SQLData converter)
   {
      this.converter = converter;
   }

   public void resetAllData() {
      return;
   }
   public Integer convertRiskWeight2Index(Double weight) {
      return null;
   }
   public Double convertIndex2RiskWeight(Double index) {
      return null;
   }

   public Integer getRiskValue(Integer index)
   {
      if (index < 1)
      {
         return 0;
      }

      if (answers == null)
         return 0;

      if (index > riskValues.length)
      {
         return 0;
      }

      if (riskValues[index] == null)
      {
         return 0;
      }

      return riskValues[index].intValue();
   }


   public Integer getAnswerValue(Integer index)
   {
      if (index < 1)
      {
         return 0;
      }

      if (answers == null)
         return 0;

      if (index > answers.length)
      {
         return 0;
      }

      if (answers[index] == null)
      {
         return 0;
      }

      return converter.getIntData(answers[index]);
   }

   public void setAnswer(Integer index, String value)
   {
      if (index < 1)
      {
         return;
      }

      if (answers == null)
         return;

      if (index > answers.length)
      {
         return;
      }

      answers[index] = value;
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

   public void setRiskValue(Integer index, Double value)
   {
      if (index < 1)
      {
         return;
      }

      if (riskValues == null)
         return;

      if (index > riskValues.length)
      {
         return;
      }

      riskValues[index] = value;
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
            Double maxDuration = 7.0; // This could be a constant
            for (int loop = 1; loop < numberofQuestions + 1; loop++)
            {
               value = 0.0;
               switch (loop)
               {
                  case 1:

                     double horPowerAdj = getRiskHorizon()/maxDuration;
                     if (horPowerAdj > 1.0)
                        horPowerAdj = 1.0;
                     agePowerValue = agePowerValue*horPowerAdj;

                     answers[loop] = getRiskAge().toString();
                     //answers[loop] = getRiskHorizon().toString();
                     Integer ageValue = (getRiskAge() == null) ? 30 :  getRiskAge();
                     calcRisk = Math.pow((ageValue.doubleValue() / maxScore), agePowerValue);
                     calcRisk = Math.min(maxScore * calcRisk, ageWeight * maxScore);
                     calcRisk = (calcRisk > 100) ? 100 : calcRisk;
                     if (getRiskHorizon()== 1)
                        calcRisk = 100.0;
                     riskValues[loop] = calcRisk; // Store the value in DB
                     break;
                  case 2:
                     answers[loop] = getRiskHorizon().toString();
                     break;

                     //answers[loop] = getRiskHorizon().toString();
                     //Double calcHorizonRisk = 0.0;
                     //calcHorizonRisk = (maxScore-getRiskHorizon()*(maxScore/maxDuration)); // 80 is fixed since we are scaling risk 1 to 100
                     //riskValues[loop] = calcHorizonRisk; // Store the value in DB
                     //if (calcHorizonRisk > calcRisk)
                     //{
                     //   calcRisk = calcHorizonRisk;
                     //}
                     //break;

                  case 3:
                  case 4:
                  case 5:

                     if (answers[loop] != null && ! answers[loop].isEmpty()) {
                        Integer lookupindex = converter.getIntData(answers[loop]);
                        value = riskValueMatrix[loop][lookupindex];
                        calcRisk = calcRisk + value;
                        riskValues[loop] = value; // Store the value in DB
                     }
                     break;
                  default:
                     if (answers[loop] != null && ! answers[loop].isEmpty()) {
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
            calcRisk = (calcRisk > 99.0) ? 99.0 : calcRisk;
            setTotalRisk(100.0 - calcRisk);
         }
         return totalRisk;
      }
      catch (Exception ex)
      {
         setTotalRisk(0.0);
         return 0.0;
      }

   }

}

