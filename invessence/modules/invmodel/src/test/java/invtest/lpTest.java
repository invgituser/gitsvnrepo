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
         String [] tickAcct1 =  {"FFKEX", "OAKIX", "TWGTX"};
         String [] tickAcct2 =  {"FCNTX", "LEXCX", "MALOX"};
         String [] tickAcct3 =  {"LSHIX", "MEDAX", "NEZYX"};
         String [] tickAcct4 =  {"IEF","IVW","MDY"};
         double acct1=400000, acct2=250000, acct3=325000, acct4=150000;
         double totalValue = acct1 + acct2 + acct3 + acct4;
         String [] tickers = concatStringArrays(tickAcct1, tickAcct2);
         tickers = concatStringArrays(tickers, tickAcct3);
         tickers = concatStringArrays(tickers, tickAcct4);

         int numFunds = tickers.length;
         double[] acctW = new double[] {acct1/totalValue, acct2/totalValue, acct3/totalValue, acct4/totalValue};
         int numAccounts = acctW.length;

         String primeAssets = "PRIME-ASSET";
         double[][] targetPAssetAllocation = {{0.2},{0.4},{0.4}};
         double targetOptProd = targetPAssetAllocation[0][0] *
            targetPAssetAllocation[1][0] *
            targetPAssetAllocation[2][0];

         //Compute minimum error vector by comparing to target and find the best weight fit
         double[][] optPAweights = hoptimizer.getFundOptimalWeight(tickers, primeAssets);


         //double[][] productMatrix = multiplyByMatrix(multiplicand, multiplier);
         System.out.println("#2\n" + toString(optPAweights));

         double product = 1.0;

         for (int row = 0; row < optPAweights.length; row++)
            for (int col = 0; col < optPAweights[0].length; col++)  {

               product = product* optPAweights[row][col];

         }

         double squaredErr = StrictMath.pow((targetOptProd - product), 2);
         System.out.println(targetOptProd);
         System.out.println(product);
         System.out.println(squaredErr);



         //PRIME ASSET exposure can not be larger than the account exposure.
         //If PRIME ASSET funds are in IRA and it has only a 20% value than the upperbound for these
         //funds must be 0.2 or below combined
         //May have to throw out some solutions of efficient frontier where the combined numbers are higher
         //than 20%
         //Also we mau want to consturct a fundConstaint matrix similar to accountConstraint.

         //double[] optFundWeight = new double[] {0.50,0.15,0.0,0.25,0.05,0.05};
         double[] optFundWeight = new double[] {0.081,0.045,0.000,0.758,0.053,0.063};
         //{0.081,	0.045,	0.000,	0.758,	0.053,	0.063};

         double[][] accountConstraints = new double[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}};

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

   public static String[] concatStringArrays(String[] string1, String[] string2){
      String[] resultString = new String[string1.length + string2.length];
      int j = 0;
      for (int i = 0; i< string1.length; i++)
      {
         resultString[j]= string1[i];
         j++;
      }
      for (int i = 0; i< string2.length; i++)
      {
         resultString[j] = string2[i];
         j++;
      }

      return resultString;
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

