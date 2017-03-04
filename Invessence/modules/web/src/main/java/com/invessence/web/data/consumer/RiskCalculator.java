package com.invessence.web.data.consumer;

import com.invessence.converter.SQLData;
import com.invessence.web.data.common.CustomerData;
import com.invmodel.Const.InvConst;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/14/16
 * Time: 1:11 PM
 * To change this template use File | Settings | File Templates.
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
      if ((index < 1) || ( index > answers.length ))
      {
         return 0;
      }

      if (answers == null)
         return 0;

      if (answers[index] == null || answers[index].isEmpty())
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

}
