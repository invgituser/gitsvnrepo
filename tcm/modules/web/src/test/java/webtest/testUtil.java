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
         for (Integer age=20; age < 100; age += 15) {
            custdata.riskCalculator.setRiskAge(age);
            for (Integer tc=0; tc < 3; tc++) {
               Integer horizon = 1;
               String ans = "1";
               switch (tc) {
                  case 0:
                     horizon = 1;
                     ans = "1";
                     break;
                  case 1:
                     horizon = 7;
                     ans = "3";
                     break;
                  case 2:
                     horizon = 20;
                     ans = "5";
                     break;
               }
               custdata.setHorizon(horizon);
               custdata.riskCalculator.setAns3(ans);
               custdata.riskCalculator.setAns4(ans);
               custdata.riskCalculator.setAns5(ans);
               Double riskIdex = custdata.riskCalculator.calculateRisk(goal);
               System.out.println("Catagory =" + goal +
                                  " values > " +
                                     age.toString() + "," +
                                     horizon.toString() + "," +
                                     ans + "," +
                                     ans + "," +
                                     ans + "---->" +
                                     "Answer: " + riskIdex.toString()
               );
            }
         }
      }
   }

}



