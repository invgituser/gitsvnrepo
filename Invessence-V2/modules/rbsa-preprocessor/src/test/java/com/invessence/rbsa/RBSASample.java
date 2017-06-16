package com.invessence.rbsa;

import com.joptimizer.functions.*;
import com.joptimizer.optimizers.*;
import com.joptimizer.util.Utils;
import cern.colt.matrix.DoubleFactory1D;
import cern.colt.matrix.DoubleFactory2D;
import cern.colt.matrix.DoubleMatrix1D;
import cern.colt.matrix.DoubleMatrix2D;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/22/14
 * Time: 7:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class RBSASample
{
      private Utils jOptimizerUtils = new Utils();

      public static void main(String[] args) throws Exception
      {
         testSample();
         //originalCode();
      }

      public static void testSample() {
         // Objective function

         //Assume 2 Etfs and 4 days
         double[][] P = new double[][]
            {{ 0.0, 0.0,      0.0, 0.0, 0.0, 0.0 },
               { 0.0, 0.0,      0.0, 0.0, 0.0, 0.0 },
               { 0.0, 0.0,      1.0, 0.0, 0.0, 0.0 },
               { 0.0, 0.0,      0.0, 1.0, 0.0, 0.0 },
               { 0.0, 0.0,      0.0, 0.0, 1.0, 0.0 },
               { 0.0, 0.0,      0.0, 0.0, 0.0, 1.0 }
            };
         double[] qVector = new double[] { 0.0, 0.0,     0.0, 0.0, 0.0, 0.0 };
         PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(P, qVector, 0.0);

         //equalities
         //A = [ETF + n] [1 + n]
         double[][] A = new double[][]
            {{1.0,1.0,  0.0,0.0,0.0,0.0},
               {0.2,0.5,  1.0,0.0,0.0,0.0},
               {0.3,0.6,  0.0,1.0,0.0,0.0},
               {0.4,0.7,  0.0,0.0,1.0,0.0},
               {0.2,0.4,  0.0,0.0,0.0,1.0},

            };
         double[] b = new double[]{1.0,     0.2,0.4,0.8,0.6};

         //inequalities
         ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[4];
         inequalities[0] = new LinearMultivariateRealFunction(new double[]{1.0, 0.0}, -1.5);
         inequalities[1] = new LinearMultivariateRealFunction(new double[]{0.0, 1.0}, -1.5);
         inequalities[2] = new LinearMultivariateRealFunction(new double[]{-1.0, 0.0}, .4);
         inequalities[3] = new LinearMultivariateRealFunction(new double[]{0.0, -1.0}, .4);

         //double [][]PMatrix1 = jOptimizerUtils.createConstantDiagonalMatrix(2,1.0);
         //double [][]PMatrix2 = jOptimizerUtils.createConstantDiagonalMatrix(2,-1.0);
         //double []q1 = {0.0, 0.0};
         // double []q2 = {1.0, 1.0};
         // inequalities[0] = QuadraticMultivariateRealFunction(PMatrix1, q1, 0.0);

         //double[] etfInitial = new double[]{0.5,0.5,     -0.15,-0.05,0.25,0.3};
         double[] etfInitial = new double[]{0.5,0.5,     0.0,0.0,0.0,0.0};

         //optimization problem
         OptimizationRequest or = new OptimizationRequest();
         //or.setInteriorPointMethod(JOptimizer.BARRIER_METHOD);
         or.setF0(objectiveFunction);
         or.setInitialPoint(etfInitial);
         or.setFi(inequalities); //if you want x>0 and y>0
         or.setA(A);
         or.setB(b);
         or.setToleranceFeas(1.E-9);
         or.setTolerance(1.E-9);

         //optimization
         JOptimizer opt = new JOptimizer();
         opt.setOptimizationRequest(or);

         DoubleMatrix1D X0 = or.getInitialPoint();
         for (int j = 0; j < or.getFi().length; j++) {
            // must be strictly feasible
            System.out.println("Ans>=" + or.getFi()[j].value(X0.toArray()));
         }

         try {
            int returnCode = opt.optimize();
         }
         catch (Exception ex) {
            ex.printStackTrace();
         }
         double []sol = opt.getOptimizationResponse().getSolution();
         System.out.println("done!");
      }

   public static void originalCode() {
      // Objective function
      double[][] P = new double[][] {{ 1., 0.4 }, { 0.4, 1. }};
      PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(P, null, 0);

      //equalities
      double[][] A = new double[][]{{1,1}};
      double[] b = new double[]{1};

      //inequalities
      ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[2];
      inequalities[0] = new LinearMultivariateRealFunction(new double[]{-1, 0}, 0);
      inequalities[1] = new LinearMultivariateRealFunction(new double[]{0, -1}, 0);

      //optimization problem
      OptimizationRequest or = new OptimizationRequest();
      or.setF0(objectiveFunction);
      or.setInitialPoint(new double[] { 0.1, 0.9});
      or.setFi(inequalities); //if you want x>0 and y>0
      or.setA(A);
      or.setB(b);
      or.setToleranceFeas(1.E-12);
      or.setTolerance(1.E-12);

      //optimization
      JOptimizer opt = new JOptimizer();
      opt.setOptimizationRequest(or);
      try {
         int returnCode = opt.optimize();
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      double[] sol = opt.getOptimizationResponse().getSolution();
      System.out.println("done!");
   }
}
