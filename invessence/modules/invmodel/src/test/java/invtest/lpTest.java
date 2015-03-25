package invtest;

import com.invmodel.dao.*;
import lpsolve.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 2/21/15
 * Time: 10:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class lpTest
{




   public static void main(String[] args) {

      try {



         HolisticModelOptimizer hoptimizer = HolisticModelOptimizer.getInstance();
         String [] tickers =  {"FFKEX", "OAKIX", "TWGTX","IEF","IVW","MDY"};
         String primeAssets = "PRIME-ASSET";

         //Compute minimum error vector by comparing to target and find the best weight fit
         double[][] optPAweights = hoptimizer.getFundOptimalWeight(tickers, primeAssets);


         //double[][] productMatrix = multiplyByMatrix(multiplicand, multiplier);
         System.out.println("#2\n" + toString(optPAweights));

         double product = 1.0;

         for (int row = 0; row < optPAweights.length; row++)
            for (int col = 0; col < optPAweights[0].length; col++)  {

               product = product* optPAweights[row][col];

         }
         System.out.println(product);

         int numFunds = tickers.length;
         double[] acctW = new double[] {0.4,0.3,0.3};
         int numAccounts = acctW.length;
         //PRIME ASSET exposure can not be larger than the account exposure.
         //If PRIME ASSET funds are in IRA and it has only a 20% value than the upperbound for these
         //funds must be 0.2 or below combined
         //May have to throw out some solutions of efficient frontier where the combined numbers are higher
         //than 20%
         //Also we mau want to consturct a fundConstaint matrix similar to accountConstraint.

         double[] optFundWeight = new double[] {0.50,0.15,0.0,0.25,0.05,0.05};
         double[][] accountConstraints = new double[][] {{1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
                                                         {0,0,0,0,0,0,1,1,1,1,1,1,0,0,0,0,0,0},
                                                         {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1}};
         //1	0	0	1	0	0	1	0	0	0	0	0	0	0	0	0	0	0
         //0	1	0	0	1	0	0	1	0	0	0	0	0	0	0	0	0	0
         //0	0	0	0	0	0	0	0	0	0	0	1	0	0	1	0	0	1


         //If return is not 0 then failed
         double[] fundWeightPerAccounts = hoptimizer.AllocateToAccounts(optFundWeight, acctW, accountConstraints);

      }
      catch (LpSolveException e) {
         e.printStackTrace();
      }
   }

   public static String toString(double[][] m) {
      String result = "";
      for(int i = 0; i < m.length; i++) {
         for(int j = 0; j < m[i].length; j++) {
            result += String.format("%11.2f", m[i][j]);
         }
         result += "\n";
      }
      return result;
   }
}

