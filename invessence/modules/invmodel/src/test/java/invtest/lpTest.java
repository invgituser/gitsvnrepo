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

         int numFunds = 2;
         int numAccounts = 3;
         double[] fundW = new double[] {0.4,0.6};
         double[] acctW = new double[] {0.2,0.4,0.4};
         double[] objF = new double[] {1.,1.,1.,1.,1.,1.};

         HolisticModelOptimizer hoptimizer = HolisticModelOptimizer.getInstance();
         String [] tickers =  {"FFKEX", "OAKIX", "TWGTX"};
         String primeAssets = "PRIME-ASSET";




         //Compute minimum error vector by comparing to target and find the best weight fit
         double[][] productMatrix = hoptimizer.getFundOptimalWeight(tickers, primeAssets);


         //double[][] productMatrix = multiplyByMatrix(multiplicand, multiplier);
         System.out.println("#2\n" + toString(productMatrix));

         double product = 1.0;

         for (int row = 0; row < productMatrix.length; row++)
            for (int col = 0; col < productMatrix[0].length; col++)  {

               product = product* productMatrix[row][col];

         }
         System.out.println(product);


         int ret = AllocateToAccounts(objF, fundW, acctW, numFunds, numAccounts);
         //If return is not 0 then failed
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


   public static int AllocateToAccounts(double[] objF, double[] fundW, double[] acctW, int numF, int numA) throws LpSolveException {
      LpSolve lp;
      int Ncol, j, ret = 0;

      /* We will build the model row by row
          So we start with creating a model with 0 rows and 6 columns */
      //Number of variables = (number of accounts * number of funds)

      Ncol = numA*numF; /* there are 6 variables in the model */

       /* create space large enough for one row */
      int[] colno = new int[Ncol];
      double[] row = new double[Ncol];

      /* there are 6 variables in the model */
      lp = LpSolve.makeLp(0, Ncol);
      if(lp.getLp() == 0)
         ret = 1; /* couldn't construct a new model... */

      if(ret == 0) {
         /* let us name our variables. Not required, but can be useful for debugging */
         /*lp.setColName(1, "w1A1");
         lp.setColName(2, "w1A2");
         lp.setColName(3, "w1A3");
         lp.setColName(4, "w2A1");
         lp.setColName(5, "w2A2");
         lp.setColName(6, "w2A3");*/

         lp.setAddRowmode(true);  /* makes building the model faster if it is done rows by row */

         /* construct first row (w1A1+w1A2+w1A3+w2A1+w2A2+w2A3 <=1) */
         for (j = 0; j< Ncol; j++){

            colno[j] = j+1; /* first column */
            row[j] = 1.0;

            //colno[j] = 2; /* second column */
            //row[j++] = 210;
         }
         /* add the row to lpsolve */
         lp.addConstraintex(j, row, colno, LpSolve.LE, 1.0);
      }

      //Set fund level consraints
      if(ret == 0) {
         //w1 = 0.4, w2 = 0.6
         //construct second row (w1A1+w1A2+w1A3+0+0+0 <=0.4)
         //construct third row (0+0+0+w2A1+w2A2+w2A3 <=0.6)
         int c = 0;

         for (int r = 0; r< numF; r++){
            for(c = 0; c< Ncol; c++){

               colno[c] = c+1; /* first column */
               if (c>= r*numA && c < ((r+1)*numA)) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }
            /* add the row to lpsolve */
            lp.addConstraintex(c, row, colno, LpSolve.EQ, fundW[r]);
         }
      }

      if(ret == 0) {
         // WiAi >= 0.0;
         //Number of equations = number of variables = Ncol;
         // example 1 0 0 0 0 0;
         // example 0 1 0 0 0 0;

         int c = 0;
         for (int r = 0; r< numF*numA; r++){
            for(c = 0; c< Ncol; c++){

               colno[c] = c+1; /* first column */
               if (c == r) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }
            /* add the row to lpsolve */
            lp.addConstraintex(c, row, colno, LpSolve.GE, 0.0);
         }

         /* add the row to lpsolve */
      }

      if(ret == 0) {
         //Set up account level constraints
         // Allocate only value available in the account to various funds available
         // Example 2 funds in three accounts with value of 0.2, 0.4, and 0.4
         //1 0 0 1 0 0 LE 0.2
         //0 1 0 0 1 0 LE 0.4
         //0 0 1 0 0 1 LE 0.4

         int c = 0;

         for (int r = 0; r < numA; r++){
            for(c = 0; c< Ncol; c++){

               int acctN = c/numA;
               int index = numA * acctN + r;

               colno[c] = c+1; /* first column */
               if (c == index) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }
            /* add the row to lpsolve */
            lp.addConstraintex(c, row, colno, LpSolve.EQ, acctW[r]);
         }
      }

      if(ret == 0) {
         lp.setAddRowmode(false); /* rowmode should be turned off again when done building the model */

         /* set the objective function W1A1 + W1A2 +...+ WiAj */
         j = 0;
         for (j = 0; j< Ncol; j++){

            colno[j] = j+1; /* first column */
            row[j] = 1.0;

            //colno[j] = 2; /* second column */
            //row[j++] = 210;
         }



         /* set the objective in lpsolve */
         lp.setObjFnex(j, objF, colno);
      }

      if(ret == 0) {
         /* set the object direction to maximize */
         lp.setMaxim();

         /* just out of curioucity, now generate the model in lp format in file model.lp */
         lp.writeLp("model.lp");

         /* I only want to see important messages on screen while solving */
         lp.setVerbose(LpSolve.IMPORTANT);

         /* Now let lpsolve calculate a solution */
         ret = lp.solve();
         if(ret == LpSolve.OPTIMAL)
            ret = 0;
         else
            ret = 5;
      }

      if(ret == 0) {
         /* a solution is calculated, now lets get some results */

         /* objective value */
         System.out.println("Objective value: " + lp.getObjective());

         /* variable values */
         lp.getVariables(row);
         for(j = 0; j < Ncol; j++)
            System.out.println(lp.getColName(j + 1) + ": " + row[j]);

         /* we are done now */
      }

       /* clean up such that all used memory by lpsolve is freed */
      if(lp.getLp() != 0)
         lp.deleteLp();

      return(ret);
   }
}

