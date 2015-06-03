package invtest;

import com.invmodel.dao.rbsa.*;
import com.invmodel.utils.MergeSort;
import lpsolve.*;
import webcab.lib.finance.portfolio.CapitalMarket;


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

         String [] tickAcct1 =  {"FCNTX", "OAKIX", "LSHIX"};
         //String [] tickAcct1 =   {"FFKEX", "OAKIX", "TWGTX"};
         String [] tickAcct4 =  {"VNQ","TLT","IEF","SHY","BIL","IAU","IVW","IVE","VTV","MDY","PFF","IWM","JNK","EMLC","VCLT","VWO","EFA"};
         //

         double acct1=100000, acct2=100000, acct3=300000, acct4=500000;
         //double acct1=400000, acct2=400000, acct3=200000;
         double totalValue = acct1 + acct2 + acct3 + acct4;
         //double totalValue = acct1 + acct2 + acct3;
         String [] tickers = concatStringArrays(tickAcct1, tickAcct4);
         //String [] tickers = concatStringArrays(tickAcct1, tickAcct4);

         double[] acctW = new double[] {acct1/totalValue, acct2/totalValue, acct3/totalValue, acct4/totalValue};
         //double[] acctW = new double[] {acct1/totalValue, acct2/totalValue, acct3/totalValue};

         String primeAssets = "PRIME-ASSET";
         double[][] targetPAssetAllocation = {{0.01},{0.26},{0.0},{0.11},{0.04},{0.07},{0.0},{0.04},{0.05},{0.12},{0.13},{0.07},{0.02},
            {0.01},{0.0},{0.07}};
         double targetOptProd = targetPAssetAllocation[0][0] *
            targetPAssetAllocation[1][0] *
            targetPAssetAllocation[2][0];

         //To use these returns, call getDailyReturns with the same tickers;
         double[][] mrData = hoptimizer.getData(tickers);
         double [][] coVarFunds = hoptimizer.getCoVarFunds(mrData);
         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         double[][] weights = hoptimizer.getWeights(instanceOfCapitalMarket, tickers, mrData, coVarFunds);
         double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks(coVarFunds);
         double[] portReturns = instanceOfCapitalMarket.getEfficientFrontierExpectedReturns();

         //Compute minimum error vector by comparing to target and find the best weight fit
         double[] errorDiff = hoptimizer.getFundErrorVectorArray(tickers,  targetPAssetAllocation, weights);

         MergeSort mms = MergeSort.getInstance();
         int[] fundOffset = new int[errorDiff.length];
         for (int i = 0; i<errorDiff.length; i++){
              fundOffset[i]=i;
         }

         //Sort the squared error terms, and also the index which will point to the weights, risk and returns.
         mms.sort(errorDiff,fundOffset);

         /*for(double i:errorDiff){
            System.out.print(i);
            System.out.print(" ");
            System.out.println(i);

         }*/

         //PRIME ASSET exposure can not be larger than the account exposure.
         //If PRIME ASSET funds are in IRA and it has only a 20% value than the upperbound for these
         //funds must be 0.2 or below combined
         //May have to throw out some solutions of efficient frontier where the combined numbers are higher
         //than 20%
         //Also we mau want to consturct a fundConstaint matrix similar to accountConstraint.


         double[] optFundWeight = new double[weights[0].length];
         double[][] accountConstraints = new double[acctW.length][acctW.length*tickers.length];
         for(int i=0; i<weights[0].length; i++){
            optFundWeight[i] = weights[fundOffset[0]][i];
         }

         for (int r = 0; r<acctW.length; r++) {
            int colN = 0;
            for (int a = 0; a<acctW.length; a++) {
               for (int f = 0; f<tickers.length; f++){

                  accountConstraints[r][colN] = 0;

                  if (r == a){

                     if (a < acctW.length-1) {
                        if (f < 9)
                           accountConstraints[r][colN] = 1;
                        else
                           accountConstraints[r][colN] = 0;
                     }

                     else if (a == acctW.length-1) {
                        if (f > 8)
                           accountConstraints[r][colN] = 1;
                        else
                           accountConstraints[r][colN] = 0;
                     }
                  }
                  else
                     accountConstraints[r][colN] = 0;
                  colN++;
               }
            }
         }
         /*double[][] accountConstraints = new double[][] {
            {1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1}};*/

         AllocationOptimizer allocOpt = AllocationOptimizer.getInstance();
         double[] fundWeightsPerAccounts = allocOpt.AllocateToAccounts(optFundWeight, acctW, accountConstraints);
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

