package com.invessence.data.ltam;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/20/15
 * Time: 2:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class LTAMRiskQuestions
{
   private Integer[] answers = new Integer[6];
   private Integer ans1;
   private Integer ans2;
   private Integer ans3;
   private Integer ans4;
   private Integer ans5;
   private Integer ans6;

   public void init() {
      answers = new Integer[6];
   }

   public Integer[] getAnswers()
   {
      return answers;
   }

   public void setAnswers(Integer[] answers)
   {
      this.answers = answers;
   }

   public Integer getAns1()
   {
      return ans1;
   }

   public void setAns1(Integer ans1)
   {
      this.ans1 = ans1;
   }

   public Integer getAns2()
   {
      return ans2;
   }

   public void setAns2(Integer ans2)
   {
      this.ans2 = ans2;
   }

   public Integer getAns3()
   {
      return ans3;
   }

   public void setAns3(Integer ans3)
   {
      this.ans3 = ans3;
   }

   public Integer getAns4()
   {
      return ans4;
   }

   public void setAns4(Integer ans4)
   {
      this.ans4 = ans4;
   }

   public Integer getAns5()
   {
      return ans5;
   }

   public void setAns5(Integer ans5)
   {
      this.ans5 = ans5;
   }

   public Integer getAns6()
   {
      return ans6;
   }

   public void setAns6(Integer ans6)
   {
      this.ans6 = ans6;
   }

   public void setAnswer(Integer ans, Integer value) {
      if (answers == null) {
         init();
      }
      answers[ans] = value;
   }

   public Integer getAnswer(Integer ans) {
      if (answers == null)
         return 0;

      if (answers.length < ans)
         return 0;

      return answers[ans];
   }
}
