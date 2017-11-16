package com.invmodel.risk.data;

import com.invessence.converter.SQLData;

/**
 * Created by prashant on 11/9/2017.
 */
public class UserRiskData
{
   String key;
   String answerStr;
   Integer answerInt;
   Double answerDouble;
   Boolean answerBoolean;
   String answerType;
   Double riskScore;

   public UserRiskData()
   {
   }

   public UserRiskData(String key, String answer, String answerType, Double riskScore)
   {
      this.key = key;
      this.riskScore = riskScore;
      setAnswer(answer, answerType);
   }

   public String getKey()
   {
      return key;
   }

   public void setKey(String key)
   {
      this.key = key;
   }

   public String getAnswer()
   {
      return answerStr;
   }

   public void setAnswer(String answer, String answerType)
   {
      SQLData converter = new SQLData();
      if (answerType != null) {
         if (answerType.equalsIgnoreCase("D"))
         {
            answerDouble = converter.getDoubleData(answer);
         }
         else if (answerType.equalsIgnoreCase("I")) {
            answerInt = converter.getIntData(answer);
         }
         else if (answerType.equalsIgnoreCase("B")) {
            answerBoolean = converter.getBooleanData(answer);

         }
      }
      this.answerStr = answer;
      this.answerType = answerType;
   }

   public String getAnswerStr()
   {
      return answerStr;
   }

   public void setAnswerStr(String answerStr)
   {
      this.answerStr = answerStr;
   }

   public Integer getAnswerInt()
   {
      return answerInt;
   }

   public void setAnswerInt(Integer answerInt)
   {
      this.answerInt = answerInt;
   }

   public Double getAnswerDouble()
   {
      return answerDouble;
   }

   public void setAnswerDouble(Double answerDouble)
   {
      this.answerDouble = answerDouble;
   }

   public Boolean getAnswerBoolean()
   {
      return answerBoolean;
   }

   public void setAnswerBoolean(Boolean answerBoolean)
   {
      this.answerBoolean = answerBoolean;
   }

   public String getAnswerType()
   {
      return answerType;
   }

   public void setAnswerType(String answerType)
   {
      this.answerType = answerType;
   }

   public Double getRiskScore()
   {
      return riskScore;
   }

   public void setRiskScore(Double riskScore)
   {
      this.riskScore = riskScore;
   }
}
