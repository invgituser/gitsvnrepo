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
      {0, 7, 14, 25, 28, 0, 0, 0, 0, 0}, // Q5
      {0, 14, 21, 28, 28, 0, 0, 0, 0, 0}, // Q6
      {0, 7, 14, 25, 28, 0, 0, 0, 0, 0}, // Q7
      {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // Q8
      {0, 1, 2, 3, 4, 5, 6, 7, 8, 9}, // Q9
   };

   private ArrayList<Map<String, String>> risk_Choices;
   private String[] risk_Question;

   private static String[][] _Questions = {
      {"What is your employment situation?", "5"},
      {"Do you have any other sources of income?", "2"},
      {"What is your financial situation, including expenses, loans, savings for retirement and emergencies?", "5"},
      {"What level of investment risk are you willing to take? ", "5"},
      {"What best describes your investment approach?", "5"},
      {"What level of volatility are you prepared to accept?", "4"},
      {"What is your long-term investment intention?", "4"}
   };


   private static String[][] _Choices = {
      {
         "My job is very secure.",
         "My job security could change quickly.",
         "I am not working or retired but have secure income.",
         "I feel my job is not secure.",
         "I am currently unemployed or retired."
      },
      {
         "Yes.",
         "No."
      },
      {
         "I feel very secure about all my financial needs.",
         "I feel fairly secure.",
         "I feel secure for the next 12 months.",
         "I don’t feel very secure.",
         "I don’t feel at all secure."
      },
      {
         "Highest.",
         "High.",
         "Moderate.",
         "Low.",
         "Lowest."
      },
      {
         "Aggressive – long-term growth.",
         "Moderately aggressive – long-term growth.",
         "Conservative – preserve capital more than growth.",
         "Ultra conservative – safe with very low risk.",
         "No risk – all cash."
      },
      {
         "High – I am comfortable with volatility.",
         "Moderate – Up to half volatile and half balanced.",
         "Medium – Stable annual return with low volatility.",
         "None – No volatility."
      },
      {
         "Majority of my assets at higher risk, for greater returns.",
         "More than half my assets at higher risk, for greater returns.",
         "Up to half my assets at higher risk, to balance stability with greater returns.",
         "Less than a quarter of my assets at higher risk, to keep up with inflation.",
         "None of my assets at high risk, to preserve capital."
      },
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