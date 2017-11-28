package com.invmodel.risk.data;

import com.invessence.converter.SQLData;

/**
 * Created by prashant on 11/9/2017.
 */
public class UserRiskData
{
   private Integer sortorder;
   private String key;
   private String answerStr;
   private Integer answerInt;
   private Double answerDouble;
   private Boolean answerBoolean;
   private String answerType;
   private Double riskScore;

   public UserRiskData()
   {
   }

   public UserRiskData(Integer sortorder, String key, String answer, String answerType, Double riskScore)
   {
      this.sortorder = sortorder;
      this.key = key;
      this.riskScore = riskScore;
      setAnswer(answer, answerType);
   }

   public Integer getSortorder()
   {
      return sortorder;
   }

   public void setSortorder(Integer sortorder)
   {
      this.sortorder = sortorder;
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
      if (answerInt == null && answerDouble != null) {
         return answerDouble.intValue();
      }
      return answerInt;
   }

   public void setAnswerInt(Integer answerInt)
   {
      this.answerInt = answerInt;
   }

   public Double getAnswerDouble()
   {
      if (answerDouble == null && answerInt != null) {
         return answerInt.doubleValue();
      }
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
