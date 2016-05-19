package com.invessence.rbsa;

import com.invessence.rbsa.dao.DailyReturns;
import com.invessence.rbsa.dao.FundsCollector;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.functions.PDQuadraticMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;
import com.joptimizer.util.Utils;

/**
 * Modified by Leon on 9/29/2014.
 */

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/16/14
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */

public class RBSA3 {
   DailyReturns assetsReturns;
   private Utils jOptimizerUtils = new Utils();
   private FundsCollector funds;
   private double[][] etfs = null;
   private Integer numofIndex, maxRowsofIndex;
   private ConvexMultivariateRealFunction[] inequalities;
   private OptimizationRequest optimizerRequest;

   public RBSA3() {
      super();
      initProcess();

   }

   private void initProcess() {

      assetsReturns = new DailyReturns();
      numofIndex = assetsReturns.getMaxNoIndex();
      maxRowsofIndex = assetsReturns.getMaxNoRows();
      etfs = assetsReturns.getAllIndexReturnsArray();

   }

   public double[] optimizeSecurity(String fundName) {
      // Objective function
      double[] solution;
      double[][] PMatrix;
      double[] qVector;
      double r = 0.0;
      double[][] AMatrix;
      double[] bVector;
      int numofDays;
      double[] etfInitialPoints;

      double[][] G1, G2;
      double[] h1, h2;

      double  sum_of_errors = 0;
      double  avg_error = 0;
      double  sum_squared_devs = 0;
      double  var=0;
      double  ann_var = 0;
      double  ann_te = 0;

      try {
         this.funds = new FundsCollector(fundName);
         numofDays = this.funds.numOfElements();
         numofDays = (maxRowsofIndex < numofDays) ? maxRowsofIndex : numofDays;
         numofDays = (numofDays > 60)? 60:numofDays;

         AMatrix = buildAMatrix(numofIndex, numofDays, etfs);
         // etfInitialPoints = fillIntialElements(numofIndex, numofDays, 1.0/numofIndex.doubleValue());
         PMatrix = buildPIdentityMatrix(numofIndex, numofDays);
         qVector = fillMatrix(numofIndex + numofDays, 0.0);
         bVector = buildbVector(numofDays, this.funds.fundReturnsArray(numofDays));

         G1 = buildGMatrix(numofIndex, numofDays, -1.0);
         G2 = buildGMatrix(numofIndex, numofDays, 1.0);
         h1 = fillMatrix(numofIndex, 0.0);
         h2 = fillMatrix(numofIndex, -1.0);

         //inequalities
         ConvexMultivariateRealFunction[] inequalities = new ConvexMultivariateRealFunction[numofIndex * 2];

         int counter = 0;
         for (int i = 0; i < numofIndex; i++) {
            inequalities[counter++] = new LinearMultivariateRealFunction(G1[i], h1[i]);
            inequalities[counter++] = new LinearMultivariateRealFunction(G2[i], h2[i]);
         }

         etfInitialPoints = fillIntialElements(numofIndex, numofDays, 1.0 / numofIndex.doubleValue(), etfs, this.funds.fundReturnsArray(numofDays));


         //optimization problem

         // Sample: PMatrix = new double[][] {{ 1., 0.4 }, { 0.4, 1. }};
         PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(PMatrix, qVector, r);
         optimizerRequest = new OptimizationRequest();
         optimizerRequest.setInteriorPointMethod(JOptimizer.BARRIER_METHOD);
         optimizerRequest.setF0(objectiveFunction);
         optimizerRequest.setInitialPoint(etfInitialPoints);
         //optimizerRequest.setToleranceFeas(1.E-9);
         //optimizerRequest.setTolerance(1.E-9);
         optimizerRequest.setToleranceFeas(1.E-7);
         optimizerRequest.setTolerance(1.E-7);
         optimizerRequest.setFi(inequalities); //if you want x>0 and y>0
         optimizerRequest.setA(AMatrix);
         optimizerRequest.setB(bVector);

         //optimization
         //opt = new JOptimizer();
         JOptimizer opt = new JOptimizer();
         opt.setOptimizationRequest(optimizerRequest);
         try {
            int returnCode = opt.optimize();
         }
         catch (Exception oex) {
            oex.printStackTrace();
         }
         solution = new double[numofIndex+numofDays];
         String indexname;
         for (int num = 0; num < numofIndex; num++) {
            solution[num] = opt.getOptimizationResponse().getSolution()[num];
            if (assetsReturns != null)
               indexname = assetsReturns.getIndexName(num);
            else
               indexname = "Unknown";

            System.out.println(num + " ) " + indexname + " =" + solution[num]);

         }

         for (int num = 0; num < numofDays; num++) {
            solution[numofIndex + num] = opt.getOptimizationResponse().getSolution()[numofIndex + num];
            sum_of_errors = sum_of_errors + solution[numofIndex + num];
         }

         avg_error = sum_of_errors / numofDays;

         for (int num = 0; num < numofDays; num++) {
            sum_squared_devs = sum_squared_devs + Math.pow((solution[numofIndex + num] - avg_error),2);
         }

         var = sum_squared_devs / numofDays;
         ann_var = var * 252;
         ann_te = Math.pow(ann_var,0.5);

         System.out.println("Annualized Tracking Error: " + ann_te);


         System.out.println("Loaded Solutions!");
         return solution;

      } catch (Exception ex) {

      }

      try {
      } catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   private double[][] identityMatrix(Integer numOfElements, Double value) {
      double[][] identity = new double[numOfElements][numOfElements];
      try {
         identity = jOptimizerUtils.createConstantDiagonalMatrix(numOfElements, value);
      } catch (Exception ex) {

      }
      return identity;
   }

   private double[] fillMatrix(Integer numOfElements, Double value) {

      double[] matrix = null;
      try {
         matrix = new double[numOfElements];
         for (int i = 0; i < numOfElements; i++) {
            matrix[i] = value;
         }
      } catch (Exception ex) {
      }
      return matrix;
   }

   private double[] fillIntialElements(Integer numofIndex, Integer numofDays, Double value, double[][] daily_etf_returns, double[] daily_fund_returns) {

      double[] vector = null;
      double[] port_return = null;
      try {
         vector = new double[numofIndex + numofDays];
         port_return = new double[numofDays];
         for (int k = 0; k < numofIndex; k++) {
            vector[k] = value;
         }

         for (int i = 0; i < numofDays; i++) {
            port_return[i] = 0.0;
            for (int k = 0; k < numofIndex; k++) {
               port_return[i] = port_return[i] + value * daily_etf_returns[k][i];
            }
         }

         for (int i = numofIndex; i < numofIndex + numofDays; i++) {
            vector[i] = daily_fund_returns[i - numofIndex] - port_return[i - numofIndex];
         }

      } catch (Exception ex) {

      }
      return vector;
   }

   private double[][] buildPIdentityMatrix(Integer numOfIndex, Integer numofDays) {
      Integer row = 0, column = 0;

      try {
         double[][] pIdentityMatrix = identityMatrix(numOfIndex + numofDays, 1.0);

         for (column = 0; column < numOfIndex; column++) {
            for (row = 0; row < numOfIndex; row++) {
               if (row == column)
                  pIdentityMatrix[column][row] = 0.0;
            }
         }
         return pIdentityMatrix;
      } catch (Exception ex) {
         System.out.println("Error creating A Matrix ->column=" + column.toString() + " ->row=" + row.toString());
         ex.printStackTrace();

      }
      return null;
   }

   private double[][] buildAMatrix(Integer numOfIndex, Integer numofDays, double[][] indexFunds) {
      int row = 0, column = 0;
      int fundpos = 0;

      try {
         double[][] matrix = new double[numofDays + 1][numOfIndex + numofDays];
         int dayOffset = numOfIndex;
         for (row = 0; row < numofDays + 1; row++) {
            for (column = 0; column < numOfIndex + numofDays; column++) {
               switch (row) {
                  case 0:
                     if (column < numOfIndex)
                        matrix[row][column] = 1.0;
                     break;
                  default:
                     if (column < numOfIndex) {
                        matrix[row][column] = indexFunds[fundpos][row - 1];
                        if (fundpos < numOfIndex - 1)
                           fundpos++;
                     } else if (column == dayOffset)
                        matrix[row][column] = 1.0;
                     break;
               }
            }
            fundpos = 0;
            if (row > 0)
               dayOffset++;

         }
         return matrix;
      } catch (Exception ex) {
         System.out.println("Error creating A Matrix ->row=" + row + " ->column=" + column);
         ex.printStackTrace();

      }
      return null;
   }

   private double[] buildbVector(Integer numofDays, double[] value) {

      double[] vector = null;

      try {
         vector = new double[1 + numofDays];
         for (int i = 0; i <= numofDays; i++) {
            if (i == 0)
               vector[i] = 1.0;
            else
               vector[i] = value[i -1];
         }
         return vector;
      } catch (Exception ex) {

      }
      return null;
   }

   private double[][] buildGMatrix(Integer numOfIndex, Integer numofDays, Double value) {
      int row = 0, column = 0;

      double[][] matrix = new double[numOfIndex][numOfIndex + numofDays];
      double[][] identity = new double[numOfIndex][numOfIndex];
      try {
         identity = jOptimizerUtils.createConstantDiagonalMatrix(numOfIndex, value);

         for (row = 0; row < numofIndex; row++) {
            for (column = 0; column < numOfIndex + numofDays; column++) {
               if (column < numOfIndex)
                  matrix[row][column] = identity[row][column];
               else
                  matrix[row][column] = 0.0;
            }
         }
         return matrix;

      } catch (Exception ex) {
         System.out.println("Error creating G Matrix ->row=" + row + " ->column=" + column);
         ex.printStackTrace();
      }
      return null;
   }

}
