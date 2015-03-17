package com.invessence.rbsa;

import com.invessence.rbsa.dao.*;
import com.invessence.rbsa.dao.data.RBSAData;
import com.joptimizer.functions.ConvexMultivariateRealFunction;
import com.joptimizer.functions.LinearMultivariateRealFunction;
import com.joptimizer.functions.PDQuadraticMultivariateRealFunction;
import com.joptimizer.optimizers.JOptimizer;
import com.joptimizer.optimizers.OptimizationRequest;
import com.joptimizer.util.Utils;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/16/14
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 * Modified by Leon on 9/29/2014.
 */

public class RBSA2 {
   // private Utils jOptimizerUtils;
   //DailyReturns assetsReturns;
   private ReturnsCollector returnsCollector;
   private double[][] etfs = null;
   private Integer numofIndex, maxRowsofIndex;
   private ConvexMultivariateRealFunction[] inequalities;
   private OptimizationRequest optimizerRequest;
   RBSADAO rbsaDAO = RBSADAO.getInstance();


   public RBSA2() {
      super();
      // jOptimizerUtils = new Utils();
      // initProcess();

   }

/*
   private void initProcess() {

      assetsReturns = new DailyReturns();
      numofIndex = assetsReturns.getMaxNoIndex();
      maxRowsofIndex = assetsReturns.getMaxNoRows();
      etfs = assetsReturns.getAllIndexReturnsArray();

   }
*/


   public RBSAData optimizeSecurity(String fundName) {
      // Objective function
      double[] fundsData;
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
         String primeasset = "PRIME-ASSET";
         this.returnsCollector = new ReturnsCollector(fundName, primeasset);
         // this.funds = new FundsCollector(fundName);
         numofDays = returnsCollector.getNoofFundsData(fundName);
         maxRowsofIndex = returnsCollector.getNoofIndexData(primeasset);
         numofIndex = returnsCollector.getNoofIndex(primeasset);
         numofDays = (maxRowsofIndex < numofDays) ? maxRowsofIndex : numofDays;
         numofDays = (numofDays > 60)? 60:numofDays;
         etfs =  returnsCollector.getAllIndexReturnsArray(primeasset,numofDays);
         AMatrix = buildAMatrix(numofIndex, numofDays, etfs);
         // etfInitialPoints = fillIntialElements(numofIndex, numofDays, 1.0/numofIndex.doubleValue());
         PMatrix = buildPIdentityMatrix(numofIndex, numofDays);
         qVector = fillMatrix(numofIndex + numofDays, 0.0);
         fundsData = returnsCollector.getFundReturnsData(fundName, numofDays);
         bVector = buildbVector(numofDays, fundsData);

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

         etfInitialPoints = fillIntialElements(numofIndex, numofDays, 1.0 / numofIndex.doubleValue(), etfs, fundsData);


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
         } catch (Exception oex) {
            oex.printStackTrace();
         }

         RBSAData rbsadata = new RBSAData(fundName);
         for (int num = 0; num < numofIndex; num++) {
            if (returnsCollector != null) {
               for (String index : returnsCollector.getOrderedIndex(primeasset)) {
                  rbsadata.setSolution(index, opt.getOptimizationResponse().getSolution()[num]);
                  num++;
               }
            }
         }

         double  [] fundWeights = new double[numofDays];

         for (int num = 0; num < numofDays; num++) {
            fundWeights[num] = opt.getOptimizationResponse().getSolution()[numofIndex + num];
            sum_of_errors = sum_of_errors + fundWeights[num];
         }

         avg_error = sum_of_errors / numofDays;

         for (int num = 0; num < numofDays; num++) {
            sum_squared_devs = sum_squared_devs + Math.pow((fundWeights[num] - avg_error),2);
         }

         var = sum_squared_devs / numofDays;
         ann_var = var * 12;
         ann_te = Math.pow(ann_var,0.5);

         rbsadata.setTrackingError(ann_te);

         rbsaDAO.saveRBSAData(rbsadata);

         return rbsadata;

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
         if (value == 0.0)
            return identity;
         else {
            for (int i=0; i < numOfElements; i++) {
               identity[i][i] = value;
            }
         }
         // identity = jOptimizerUtils.createConstantDiagonalMatrix(numOfElements, value);
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
         //identity = jOptimizerUtils.createConstantDiagonalMatrix(numOfIndex, value);
         identity = identityMatrix(numOfIndex, value);

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
