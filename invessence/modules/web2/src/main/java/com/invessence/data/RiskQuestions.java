package com.invessence.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/8/14
 * Time: 10:46 AM
 * To change this template use File | Settings | File Templates.
 */

public class RiskQuestions
{
   private Integer      riskquestNum;
   private String       riskquestion;
   private ArrayList<RiskChoice> listofchoices;

   public RiskQuestions(Integer riskquestNum, String riskquestion, String[] choices, Integer[] riskWeight)
   {
      this.riskquestNum = riskquestNum;
      this.riskquestion = riskquestion;
      listofchoices = new ArrayList<RiskChoice>();
      if (choices != null && riskWeight != null) {
         if (choices.length <= riskWeight.length)
            for (int i=0; i < choices.length; i++) {
              RiskChoice rc = new RiskChoice(choices[i], riskWeight[i]);
              listofchoices.add(i, rc);
            }
      }
   }

   public Integer getRiskquestNum()
   {
      return riskquestNum;
   }

   public void setRiskquestNum(Integer riskquestNum)
   {
      this.riskquestNum = riskquestNum;
   }

   public String getQuestion()
   {
      return riskquestion;
   }

   public void setQuestion(String riskquestion)
   {
      this.riskquestion = riskquestion;
   }

   public List<RiskChoice> getChoices()
   {
      return listofchoices;
   }

   public void setChoices(String[] choices, Integer[] riskWeight)
   {
      try {
         if (listofchoices == null)
            listofchoices = new ArrayList<RiskChoice>();
         else
            listofchoices.clear();

         for (int i=0; i < choices.length; i++) {
            RiskChoice rc = new RiskChoice(choices[i], riskWeight[i]);
            listofchoices.add(i, rc);
         }
      }
      catch (Exception ex) {

      }
   }

   public Integer getRiskWeight(Integer selectedChoice)
   {
      try {
         if (listofchoices == null)
            return 0;

         if (listofchoices.isEmpty())
            return 0;

         if (listofchoices.size() < selectedChoice)
            return 0;

         return listofchoices.get(selectedChoice).getRiskWeight();

      }
      catch (Exception ex) {
         return 0;
      }
   }

}

class RiskChoice {
   private String choice;
   private Integer riskWeight;

   RiskChoice(String choice, Integer riskWeight)
   {
      this.choice = choice;
      this.riskWeight = riskWeight;
   }

   String getChoice()
   {
      return choice;
   }

   void setChoice(String choice)
   {
      this.choice = choice;
   }

   Integer getRiskWeight()
   {
      return riskWeight;
   }

   void setRiskWeight(Integer riskWeight)
   {
      this.riskWeight = riskWeight;
   }
}

