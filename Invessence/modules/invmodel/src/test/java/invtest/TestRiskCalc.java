package invtest;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import com.invmodel.asset.data.AssetClass;
import com.invmodel.inputData.*;
import com.invmodel.model.ModelUtil;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.data.*;

import static java.lang.String.valueOf;

/**
 * Hello world!
 */
public class TestRiskCalc
{
   private static TestRiskCalc instance = null;
   private static String datadir = "C:/Users/Jigar/Work Related/RiverFrontAdvisors/";
   private static RiskCalculator riskCalculator = new RiskCalculator();

   /**
    * @param args
    */
   public static void main(String[] args) throws Exception
   {
      testRiskCalcMain();
   }


   public static void testRiskCalcMain()
   {
      ArrayList<String> result =new ArrayList<String>();
      result.add("Age" + "," +
                    "Horizon" + "," +
                    "ans3" + "," +
                    "ans4" + "," +
                    "ans5" + "," +
                    "ans6" + "," +
                    "ans7" + "," +
                    "ans8" + "," +
                    "ans9" + "," +
                    "value1" + "," +
                    "value2" + "," +
                    "value3" + "," +
                    "value4" + "," +
                    "value5" + "," +
                    "value6" + "," +
                    "value7" + "," +
                    "value8" + "," +
                    "value9" + "," +
                    "Total"
      );
      riskCalculator = new RiskCalculator();
      try {
         riskCalculator.setRiskFormula("C");
         riskCalculator.setNumberofQuestions(9);
         for (Integer age = 20; age < 80; age+=10) {
            riskCalculator.setRiskAge(age);
            for (Integer horizon=5; horizon < 21; horizon+=5) {
               riskCalculator.setRiskHorizon(horizon);
               for (Integer r3=0; r3 < 6; r3+=2) {
                  riskCalculator.setAns3(r3.toString());
                  for (Integer r4=0; r4 < 2; r4++) {  // Only two choices
                     riskCalculator.setAns4(r4.toString());
                     for (Integer r5=0; r5 < 6; r5+=2) {  // Only five choices
                        riskCalculator.setAns5(r5.toString());
                        for (Integer r6=0; r6 < 6; r6+=2) {  // Only five choices
                           riskCalculator.setAns6(r6.toString());
                           for (Integer r7=0; r7 < 6; r7+=2) {  // Only five choices
                              riskCalculator.setAns7(r7.toString());
                              for (Integer r8=0; r8 < 6; r8+=2) {  // Only five choices
                                 riskCalculator.setAns8(r8.toString());
                                 for (Integer r9=0; r9 < 6; r9+=2) {  // Only five choices
                                    riskCalculator.setAns9(r9.toString());
                                    riskCalculator.calculateRisk();
                                    result.add(riskCalculator.getAns1() + "," +
                                                  riskCalculator.getAns2() + "," +
                                                  riskCalculator.getAns3() + "," +
                                                  riskCalculator.getAns4() + "," +
                                                  riskCalculator.getAns5() + "," +
                                                  riskCalculator.getAns6() + "," +
                                                  riskCalculator.getAns7() + "," +
                                                  riskCalculator.getAns8() + "," +
                                                  riskCalculator.getAns9() + "," +
                                                  riskCalculator.getRiskValue(1) + "," +
                                                  riskCalculator.getRiskValue(2) + "," +
                                                  riskCalculator.getRiskValue(3) + "," +
                                                  riskCalculator.getRiskValue(4) + "," +
                                                  riskCalculator.getRiskValue(5) + "," +
                                                  riskCalculator.getRiskValue(6) + "," +
                                                  riskCalculator.getRiskValue(7) + "," +
                                                  riskCalculator.getRiskValue(8) + "," +
                                                  riskCalculator.getRiskValue(9) + "," +
                                                  riskCalculator.getTotalRisk());
                                 }
                              }
                           }
                        }
                     }
                  }
               }

            }
         }
         createRiskCalcFile(result);
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }

   }

   public static void createRiskCalcFile(ArrayList<String> lines) throws Exception
   {

      String fileName;
      PrintWriter writer = null;

      fileName = "RiskCalc.csv";

      writer = getFileHandle(fileName);

      //File is created in TestDistribution
      for (String line: lines)
      {
         writer.println(line);
      }

      writer.close();
   }

   public static PrintWriter getFileHandle(String fileName)
   {
      File file;
      PrintWriter writer = null;

      try
      {

         file = new File(datadir + fileName);

         //file = new RandomAccessFile ("filename.ext","rw");
         file.getParentFile().mkdirs();
         writer = new PrintWriter(file);
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      return writer;
   }

}
