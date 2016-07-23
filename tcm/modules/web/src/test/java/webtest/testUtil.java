package webtest;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/13/14
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 3/13/14
 * Time: 5:45 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;

import com.invessence.web.data.consumer.tcm.TCMCustomer;
import com.invessence.web.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.String.valueOf;

public class testUtil
{
   @Autowired
   private static WebUtil webutil;

   public static void main(String[] args) throws Exception
   {
      // testRedirect(args);
      testRiskModel(args);
   }

   public static void testRedirect(String[] args) {
      String URL="http://www.yahoo.com?redirect";
      Map <String,String> obj = new HashMap<String, String>();
      obj.put("key1","Value1");
      obj.put("key2","Value2");
      obj.put("key3","Value3");
      webutil.redirect(URL,obj);

   }

   public static void testRiskModel(String[] args) {
      TCMCustomer custdata = new TCMCustomer();
      String goal = "";

      for (Integer g=0; g < 2; g++) {
         switch (g) {
            case 0:
               goal = "Retirement";
            default:
               goal = "Other";
         }
         for (Integer age=20; age < 100; age += 5) {
            custdata.riskCalculator.setRiskAge(age);
            for (Integer horizon=1; horizon < 31; horizon += 3) {
               custdata.riskCalculator.setRiskHorizon(horizon);
               for (Integer ans3=1; ans3 < 6; ans3++) {
                  for (Integer ans4=1; ans4 < 6; ans4++) {
                     for (Integer ans5=1; ans5 < 6; ans5++) {
                        custdata.riskCalculator.setAns3(ans3.toString());
                        custdata.riskCalculator.setAns4(ans4.toString());
                        custdata.riskCalculator.setAns5(ans5.toString());
                        Double riskIdex = custdata.riskCalculator.calculateRisk(goal);
                        System.out.println("Value =" +
                                              age.toString() + "," +
                                              horizon.toString() + "," +
                                              ans3.toString() + "," +
                                              ans4.toString() + "," +
                                              ans5.toString() + "---->" +
                                              "Answer: " + riskIdex.toString()
                        );
                     }

                  }

               }
            }
         }
      }
   }

}



