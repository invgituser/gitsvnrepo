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
   //C:\Users\Jigar\Dropbox\Invessence Team Folder\Sales\UOB\Models
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

         for (Integer age = 40; age < 41; age += 5)
         {
            riskCalculator.setRiskAge(age);
            for (Integer horizon = 20; horizon < 21; horizon += 2)
            {
               riskCalculator.setRiskHorizon(horizon);
               for (Integer r9 = 3; r9 > 0; r9--)
               {  // Only three choices (Reveres order)
                  riskCalculator.setAns9(r9.toString());
                  for (Integer r8 = 1; r8 < 4; r8++)
                  {  // Only three choices
                     riskCalculator.setAns8(r8.toString());
                     for (Integer r7 = 1; r7 < 4; r7++)
                     {  // Only three choices
                        riskCalculator.setAns7(r7.toString());
                        for (Integer r6 = 1; r6 < 4; r6++)
                        {  // Only three choices
                           riskCalculator.setAns6(r6.toString());
                           for (Integer r5 = 1; r5 < 6; r5++)
                           {  // Only five choices
                              riskCalculator.setAns5(r5.toString());
                              for (Integer r4 = 1; r4 < 3; r4++)
                              {  // Only two choices
                                 riskCalculator.setAns4(r4.toString());
                                 for (Integer r3 = 1; r3 < 6; r3++)
                                 {
                                    riskCalculator.setAns3(r3.toString());

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
