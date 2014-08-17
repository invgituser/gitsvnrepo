package invtest;

import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/2/14
 * Time: 9:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class RandomAddition
{
   static Integer [] digits={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
   static String [] number={"zero","one","two","three","four","five","six","seven","eight","nine","ten"};
   static String [] operator={"+","-"};
   static Random generator = new Random();

   public static void main(String[] args) throws Exception {
      for (int i=0; i< 100; i++)
         tryit();
   }

   static public void tryit() {

   Integer ans;
   try {
      Integer first=generator.nextInt(21);
      Integer second=generator.nextInt(11);
      Integer third=generator.nextInt(2);
      // Don't allow subtration of the first < second number
      if ((first < second) && (third > 0))
         third = 0;
      if (third > 0)
         third = 1;

      if (third == 0)
         ans = first + second;
      else
         ans = first - second;

      System.out.println("Do following:" + digits[first]
                            + " " + operator[third] + " "
                            + number[second] +
                         " -> Answer:" + ans );

   }
   catch (Exception ex) {
      ex.printStackTrace();
   }

   }
}
