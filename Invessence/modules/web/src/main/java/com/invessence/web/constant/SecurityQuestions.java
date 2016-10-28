package com.invessence.web.constant;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/12/14
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class SecurityQuestions
{

   // Set of personal questions.
   private static String[][] _Questions = {
      {  "Home town/city at age 10?",
         "Name of favorite teacher?",
         "Name of first pet?",
         "Name your favorite US President?",
         "How much money I need to retire?",
         "Name of your mentor?"
      },
      {
         "Favorite sports team?",
         "Favorite TV show?",
         "Favorite movie?",
         "Favorite food?",
         "Favorite vacation spot?",
         "Favorite restaurant?"
      },
      {
         "Who is your best friend?",
         "Make of your first car?",
         "Where did you meet your spouse?",
         "What is your favorite novel?",
         "Name your favorite song?",
         "Who is Godfather of your first child?",
         "Name of first boyfriend/girlfriend?"
      }
      };

   public Map<String, String> getQuestion(Integer question_number)
   {
      Map<String, String> map = new LinkedHashMap<String, String>();
      try
      {
         if (question_number == null || question_number < 0 || question_number >= _Questions.length)
         {
            question_number = 0;
         }

         for (Integer question_num = 0; question_num < _Questions[question_number].length; question_num++) {
            map.put(question_num.toString(), _Questions[question_number][question_num]);
         }

         return (map);

      }
      catch (Exception ex)
      {
         System.out.println("Error: Getting Choices in Questions:" + ex.getMessage());
         map.put("0", "?");
      }
      return map;
   }

}
