package com.invmodel.riskCalculator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/11/13
 * Time: 2:49 PM
 * To change this template use File | Settings | File Templates.
 */

public class RiskIndex
{
   private static Integer risk_Value_Matrix[][] = {
      {0, 1, 2, 3, 14, 0, 0, 0, 0, 0}, // Question #1
      {0, 4, 0, 0, 0, 0, 0, 0, 0, 0}, // Q2
      {0, 1, 2, 3, 14, 0, 0, 0, 0, 0}, // Q3
      {0, 7, 14, 21, 28, 0, 0, 0, 0, 0}, // Q4
      {0, 7, 14, 28, 28, 0, 0, 0, 0, 0}, // Q5
      {0, 14, 21, 28, 28, 0, 0, 0, 0, 0}, // Q6
      {0, 7, 14, 25, 25, 0, 0, 0, 0, 0}, // Q7
      {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // Q8
      {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // Q9
   };

   private ArrayList<Map<String, String>> risk_Choices;
   private String[] risk_Question;

   private static String[][] _Questions = {
      {"Which of the following statements best describes your employment situation?", "5"},
      {"Do you have any other sources of income?", "2"},
      {"Which of the following statements best describes your financial situation? Please consider your regular living expenses and your ability to repay outstanding loans as well as saving for retirement and emergencies.", "5"},
      {"What level of risk are you willing to take to get a higher return on your investment? ", "5"},
      {"Which of the following statements best describes your investment approach? (If you don't currently have any investments, choose the response that best describes how you think you would like to manage your investments.)", "5"},
      {"Which of the following statements best describes your attitude toward the level of risk or volatility that you are prepared to live with during the time these assets will be invested?", "4"},
      {"Which of the following statements best describes your investment philosophy?", "4"}
   };


   private static String[][] _Choices = {
      {  "My job is very secure and my current income covers my expenses.",
         "For now my job is secure but this could change quickly.",
         "I am currently not working or retired but I have secure sources of income that cover my expenses.",
         "I feel my job is not secure.",
         "I am currently unemployed, retired, or stay at home and I rely on my savings for income and emergency cash needs."
      },
      {  "I have enough other sources of income to manage my current expenses.",
         "I don’t have any other sources of income.",
      },
      {  "I feel very secure about all my financial needs.",
         "I feel my financial situation is stable and I won’t need to use my investment to meet any current expenses. However, I may need to access these funds in an emergency.",
         "I don’t need to use my investment for current expenses now but I may need to do that in the next 12 months.",
         "I am not very confident in my financial situation. I am concerned that I am spending more than I am saving and that I may need to use my investment for current expenses.",
         "I definitely need my investment to supplement my income."
      },
      {
         "Highest",
         "High",
         "Moderate",
         "Low",
         "Lowest"
      },
      {
         "My investments tend to be aggressive. My objectives are long term and I am willing to take on some risk for a greater expected return.  I don't often make changes to my portfolio unless my reasons for investing have changed.",
         "I tend to choose moderately aggressive investment funds for long-term growth.  I understand this means that the value of my portfolio may fluctuate more than the market because I’m investing in riskier securities.",
         "Most of my investments tend to be conservative, such as bond mutual funds or large-cap dividend-paying stocks, with a focus on preserving capital over growth.",
         "All of my investments to date have been in cash because I’m not sure what I should be investing in.",
         "All of my investments to date have been in cash because I need the security."
      },
      {
         "I am comfortable with volatility and prefer more aggressive investments. I understand that in the short term this strategy may result in a decline in market value but I prefer having a better long-term chance for greater returns and capital gains.",
         "I am comfortable that the value of my assets may fluctuate daily. I would prefer that up to half of my assets be invested in high volatility equity securities and the balance invested in other securities. ",
         "I would feel most comfortable investing in assets that tend to generate a more stable return year-to-year, as opposed to assets that fluctuate with more volatility.",
         "I prefer very little volatility."
      },
      {
         "I have a more aggressive, long term investment horizon.  I prefer to invest the majority of my assets in the equity market, as I am comfortable this is the best strategy for producing greater returns over the long run.",
         "I understand the opportunity for greater returns does come with taking a higher level of risk and am prepared to do so with more than half of my assets.",
         "I understand the opportunity for greater returns does come with taking a higher level of risk and am comfortable doing so with up to half of my assets.",
         "I am comfortable taking a higher level of risk with less than quarter of my assets in order to keep up with inflation."

      }
   };

   static <K, V extends Comparable<? super V>>
   SortedSet<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map)
   {
      SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
         new Comparator<Map.Entry<K, V>>()
         {
            @Override
            public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2)
            {
               return e1.getValue().compareTo(e2.getValue());
            }
         }
      );
      sortedEntries.addAll(map.entrySet());
      return sortedEntries;
   }

   public RiskIndex()
   {
      try
      {
         risk_Question = new String[_Questions.length];
         risk_Choices = new ArrayList<Map<String, String>>();
         for (Integer question_num = 0; question_num < _Questions.length; question_num++)
         {
            risk_Question[question_num] = _Questions[question_num][0];
            Integer num_choices = Integer.parseInt(_Questions[question_num][1]);
            Map<String, String> map = new TreeMap<String, String>();
            for (Integer choice_num = 0; choice_num < num_choices; choice_num++)
            {
               map.put(choice_num.toString(), _Choices[question_num][choice_num]);
            }
            risk_Choices.add(question_num, map);
         }
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public Integer getNumOfQuestions() {
      return _Questions.length;
   }

   public String getQuestion(Integer question_number)
   {
      String question;
      try
      {
         if (question_number == null || question_number < 0 || question_number > _Questions.length)
         {
            question_number = 0;
         }

         question = risk_Question[question_number];

         return (question);

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting Questions in RiskConstData:" + ex.getMessage());
         question = "Default";
      }
      return question;

   }

   public String getChoiceText(Integer question_number, Integer choice_number)
   {
      String ans;
      try
      {
         if (question_number == null || question_number < 0 || question_number > _Questions.length)
         {
            question_number = 0;
         }

         if (choice_number == null || choice_number < 0) {
            choice_number = 0;
         }

         ans = _Choices[question_number][choice_number];

         return (ans);

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting Choices in RiskIndex:" + ex.getMessage());
         ans = "This choice is no longer valid.";
      }
      return ans;
   }

   public Map<String, String> getChoices(Integer question_number)
   {
      Map<String, String> map;
      try
      {
         if (question_number == null || question_number < 0 || question_number > _Questions.length)
         {
            question_number = 0;
         }

         map = risk_Choices.get(question_number);

         return (map);

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting Choices in RiskConstData:" + ex.getMessage());
         map = new HashMap<String, String>();
         map.put("Default", "0");
      }
      return map;
   }

   public Integer getRiskOffset(Integer[][] choice)
   {
      Integer riskIndex = 0;
      try
      {

         if (choice == null)
         {
            return (0);
         }

         Integer num_of_loop = (choice.length > _Questions.length) ? _Questions.length : choice.length;
         for (int loop = 0; loop < num_of_loop; loop++)
         {
            if (choice[loop][0] != null)
            {
               Integer question_num = choice[loop][0];
               Integer ans_index = choice[loop][1];
               Integer value = risk_Value_Matrix[question_num][ans_index];
               riskIndex = (riskIndex > value) ? riskIndex : value;

            }
         }
         return riskIndex;

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting risk_Value_Matrix in RiskConstData:" + ex.getMessage());
      }
      return risk_Value_Matrix[0][0];
   }

   public Integer getRiskOffset(Integer[] choice)
   {
      Integer riskIndex = 0;
      try
      {

         if (choice == null)
         {
            return (0);
         }

         Integer num_of_loop = (choice.length > _Questions.length) ? _Questions.length : choice.length;
         for (int loop = 0; loop < num_of_loop; loop++)
         {
            if (choice[loop] != null)
            {
               Integer ans_index = choice[loop];
               Integer value = risk_Value_Matrix[loop][ans_index];
               riskIndex = (riskIndex > value) ? riskIndex : value;

            }
         }
         return riskIndex;

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting risk_Value_Matrix in RiskConstData:" + ex.getMessage());
      }
      return risk_Value_Matrix[0][0];
   }
}