package com.invmodel.dao.rbsa;

import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import lpsolve.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/24/15
 * Time: 5:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class AllocationOptimizer
{

   private static AllocationOptimizer instance = null;
   private final Logger logger = Logger.getLogger(AllocationOptimizer.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized AllocationOptimizer getInstance()
   {
      if (instance == null)
      {
         instance = new AllocationOptimizer();
      }

      return instance;
   }

   private AllocationOptimizer()
   {
      super();
   }

   public double[] AllocateToAccounts(double[] optFundWeight, double[] acctW, double[][] accountConstraints,
                                      double[][] fundConstraints, double[] fundWeights,
                                      int offset) throws LpSolveException
   {
      //This is a solver which figures out how to allocate funds to various accounts.
      //Solver requires number of inequalities and constraints to come up with an optimal solution
      //on allocating number of funds to accounts.

      //fund weights are passed to the solver once the optimalFundWeight is picked from the
      // efficient frontier in getOptimalFundWeight method

      //account weights are passed to solver based on what percent of dollars are in each account

      //objective function is a double[] of 1. Number of 1 must match number of variables trying to solve.

      LpSolve lp;
      int Ncol, ret = 0;
      int j = 0;

      //We will build the model row by row
      //So we start with creating a model with 0 rows and n variable columns
      //Number of variables = (number of accounts * number of funds)

      int numF = optFundWeight.length;
      int numA = acctW.length;

      Ncol = numA * numF;

       /* create space large enough for one row */
      int[] colno = new int[Ncol];
      //double[] row = new double[Ncol];

      /* there are account*Funds number of variables in the model */
      lp = LpSolve.makeLp(0, Ncol);
      if (lp.getLp() == 0)
         ret = 1; /* couldn't construct a new model... */

      if (ret == 0)
      {
         //The solver is solving n varibales to find an optimal allocation of funds across
         //number of accounts. Number of variables which must be solved equal
         //funds*accounts. If there are 4 accounts and 5 funds then number of variables
         // would be 20.

         // let us name our variables. Not required, but can be useful for debugging
         // Default is c1, c2, c3...each fund has a weight in the account
         //Naming w1A1 is fund1's weight in Account 1...we are solving for all
         //the weights in various accounts
         int col = 0;
         for (int act = 1; act <= numA; act++)
         {
            for (int fund = 1; fund <= numF; fund++)
            {
               col++;
               lp.setColName(col, "W" + fund + "A" + act);
            }
         }

         //makes building the model faster if it is done rows by row
         lp.setAddRowmode(true);

         double[] row = new double[Ncol];

         //Add first constraint, sum of all the variables must be less than equal to 1
         //w1A1+w1A2+w1A3+w2A1+w2A2+w2A3...WiAj <=1
         for (j = 0; j < Ncol; j++)
         {
            colno[j] = j + 1; /* first column */
            row[j] = 1.0;
         }
         //add the row constarint to the lpsolve
         lp.addConstraintex(j, row, colno, LpSolve.LE, 1.0);
      }

      if (ret == 0)
      {
         //Set each variable constraints
         //Number of variables are equal to funds*accounts
         // WiAj >= 0.0;
         //Number of equations = number of variables = Ncol;
         //If there are 2 funds and 3 accounts then there are 6 variables we are solving for
         // example 1 0 0 0 0 0;
         // example 0 1 0 0 0 0;


         int c = 0;
         int offsetLocal = 0;

         for (int a = 0; a < numA; a++)
         {
            for (int f = 0; f < numF; ++f)
            {
               double[] row = new double[Ncol];
               offsetLocal = a * numF;
               row[offsetLocal + f] = accountConstraints[a][offsetLocal + f];
               // add the row to lpsolve
               lp.addConstraintex(c, row, colno, LpSolve.GE, 0.0);

            }

         }

         /*double[] row = new double[Ncol];
         for (int r = 0; r < numF * numA; r++)
         {
            for (c = 0; c < Ncol; c++)
            {

               colno[c] = c + 1; *//* first column *//*
               if (c == r)
               {

                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }
            *//* add the row to lpsolve *//*
            lp.addConstraintex(c, row, colno, LpSolve.GE, 0.0);
         }
*/
         /* add the row to lpsolve */
      }

      if (ret == 0)
      {
         //Set up account level constraints
         //Number of constraints = number of accounts
         // Allocate only value available in the account to various funds available
         // Example 2 funds in three accounts with value of 0.2, 0.4, and 0.4
         //w1A1+w2A1+w3A1+0+0 <= 0.2 (example)
         //1 1 0 0 0 0 LE 0.2
         //0 0 1 1 0 0 LE 0.4
         //0 0 0 0 1 1 LE 0.4

         //row array is what we need to establish to include funds in an account
         //by setting value to 1 we include the fund otherwise we skip the fund.
         //accW array is the weight of each account, which is simply $account/$total in all accounts

         //calculate account weight
         double totalAmount = 0.0;
         for (int w = 0; w < acctW.length; w++){
            totalAmount = totalAmount + acctW[w];
         }

         double [] accountWeight = new double [acctW.length];

         for (int w = 0; w < acctW.length; w++){
            accountWeight[w]= acctW[w]/totalAmount;
         }

         int c = 0;

         for (int r = 0; r < numA; r++){

            /*for(c = 0; c< Ncol; c++){

               int acctN = c/numA;
               int index = numA * acctN + r;

               // first column
               colno[c] = c+1;
               if (c == index) {
                  row[c] = 1.0;
               }
               else
                  row[c] = 0.0;
            }*/

            for(c = 0; c< Ncol; c++){
               // first column
               colno[c] = c+1;
            }
            /* add the row to lpsolve */
            //May want some accounts to be fixed

            if(offset > 10)
               lp.addConstraintex(c, accountConstraints[r], colno, LpSolve.LE, accountWeight[r]);
            else
               lp.addConstraintex(c, accountConstraints[r], colno, LpSolve.EQ, accountWeight[r]);
            //lp.addConstraintex(c, row, colno, LpSolve.EQ, acctW[r]);
         }
      }

      if(ret == 0) {
         //Set fund level consraints
         //all funds must add to 1
         //Number of contstraints equal to number of funds
         //if there were two funds with fund weights of 0.4 and 0.6
         //construct first row (w1A1+0+w1A2+0+w1A3+0 <=0.4)
         //construct second row (0+w2A1+0+w2A2+0+w2A3 <=0.6)

         int c = 0;
         //double[] row = new double[Ncol];
         for (int f = 0; f<numF; f++) {
            //to comapre with fund weight variable
            //String varfundStr = "W" + f;

            c =0;
            //Weight variable for funds are by accounts
            //w1A1+w2A1+w3A1...w1A3+w2A3+w3A3...
            for (int r = 0; r< numA; r++){
               //int column = r+1;

               for(int n = 0; n< numF; n++){
                  //int k = n+1;
                  //String colName = "W" + k;
                  colno[c] = c+1; /* first column */
                  // if (lp.getColName(c + 1).equals(colName))
                  //   row[c] = 1.0;
                  //if (c>= r*numA && c < ((r+1)*numA)) {
                  //if(colName.equals(varfundStr) )
                  //   row[c] = 1.0;
                  //else
                  //   row[c] = 0.0;
                  c++;
               }
            }
            /* add the row to lpsolve */
            //May want some funds weights tobe fixed
            //lp.addConstraintex(c, row, colno, LpSolve.LE, optFundWeight[f-1]);


            if(offset > 10)
               lp.addConstraintex(c, fundConstraints[f], colno, LpSolve.LE, optFundWeight[f]);
            else
               lp.addConstraintex(c, fundConstraints[f], colno, LpSolve.EQ, optFundWeight[f]);
         }
      }

      if(ret == 0) {
         //Create an objective function for the solver
         lp.setAddRowmode(false); /* rowmode should be turned off again when done building the model */

         /* set the objective function W1A1 + W1A2 +...+ WiAj */
         //row arry will be filled with 1. length is number of variables.
         double[] row = new double[Ncol];

         j = 0;
         for (j = 0; j< Ncol; j++){
            colno[j] = j+1; /* first column */
            row[j] = 1.0;
         }
         /* set the objective in lpsolve */
         lp.setObjFnex(j, row, colno);
         //lp.setObjFnex(j, objF, colno);
      }

      if(ret == 0) {
         /* set the object direction to maximize */
         lp.setMaxim();

         /* just out of curiosity, now generate the model in lp format in file model.lp */
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

      double[] row = new double[Ncol];
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

      if (ret == 0)
      {
         return (row);
      }
      else
         return null;

   }
}
