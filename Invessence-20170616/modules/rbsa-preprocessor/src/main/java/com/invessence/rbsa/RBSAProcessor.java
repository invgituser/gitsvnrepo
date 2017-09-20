package com.invessence.rbsa;

import com.invessence.rbsa.dao.*;
import com.joptimizer.functions.*;
import com.joptimizer.optimizers.*;
import com.joptimizer.util.Utils;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import webcab.lib.finance.portfolio.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 9/16/14
 * Time: 9:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class RBSAProcessor
{
   private Utils jOptimizerUtils = new Utils();
   DailyReturns assetsReturns;
   private FundsCollector funds;
   private double[][] etfs = null;
   private Integer numofIndex, maxRowsofIndex;
   private ConvexMultivariateRealFunction[] inequalities;
   private OptimizationRequest optimizerRequest;

   public RBSAProcessor() {
      super();
      initProcess();

   }

   private void initProcess() {

      assetsReturns = new DailyReturns();
      numofIndex = assetsReturns.getMaxNoIndex();
      maxRowsofIndex = assetsReturns.getMaxNoRows();
      etfs = assetsReturns.getAllIndexReturnsArray();
      double[][] G1, G2;
      double[] h1,h2;
      G1 = identityMatrix(numofIndex,-1.0);
      G2 = identityMatrix(numofIndex,1.0);
      h1 = fillMatrix(numofIndex,0.0);
      h2 = fillMatrix(numofIndex,1.0);

      inequalities = new ConvexMultivariateRealFunction[numofIndex*2];
      //inequalities
      int counter = 0;
      for (int i=0; i<numofIndex; i++) {
         inequalities[counter++] = new LinearMultivariateRealFunction(G1[i], h1[i]);
         inequalities[counter++] = new LinearMultivariateRealFunction(G2[i], h2[i]);
      }
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
      double [] etfInitialPoints;

      try {
         this.funds = new FundsCollector(fundName);
         numofDays = this.funds.numOfElements();
         numofDays = (maxRowsofIndex < numofDays) ? maxRowsofIndex: numofDays;
         //numofDays=500;
         AMatrix = buildAMatrix(numofIndex,numofDays,etfs);
         etfInitialPoints = fillIntialElements(numofIndex, numofDays, 1.0/numofIndex.doubleValue());
         PMatrix = buildPIdentityMatrix(numofIndex, numofDays);
         qVector = fillMatrix(numofIndex+numofDays,0.0);
         bVector = this.funds.fundReturnsArray(numofDays);

         //optimization problem

         // Sample: PMatrix = new double[][] {{ 1., 0.4 }, { 0.4, 1. }};
         PDQuadraticMultivariateRealFunction objectiveFunction = new PDQuadraticMultivariateRealFunction(PMatrix, qVector, r);
         optimizerRequest = new OptimizationRequest();
         // optimizerRequest.setInteriorPointMethod(JOptimizer.BARRIER_METHOD);
         optimizerRequest.setF0(objectiveFunction);
         optimizerRequest.setInitialPoint(etfInitialPoints);
         optimizerRequest.setToleranceFeas(1.E-12);
         optimizerRequest.setTolerance(1.E-12);
         //optimizerRequest.setFi(inequalities); //if you want x>0 and y>0
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
         solution = new double[numofIndex];
         String indexname;
         for (int num=0; num < numofIndex; num++) {
            solution[num] = opt.getOptimizationResponse().getSolution()[num];
            if (assetsReturns != null)
               indexname = assetsReturns.getIndexName(num);
            else
               indexname = "Unknown";

            System.out.println(num + " ) " + indexname + " =" + solution[num]);

         }

         System.out.println("Loaded Solutions!");
         return solution;

      }
      catch (Exception ex) {

      }

      try {
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
      return null;
   }

   private double[][] identityMatrix(Integer numOfElements, Double value) {
      double [][] identity = new double[numOfElements][numOfElements];
      try {
         identity = jOptimizerUtils.createConstantDiagonalMatrix(numOfElements,value);
      }
      catch (Exception ex) {

      }
      return identity;
   }

   private double[] fillMatrix(Integer numOfElements, Double value) {

      double[] matrix = null;
      try {
         matrix = new double[numOfElements];
         for (int i=0; i < numOfElements; i++) {
             matrix[i] = value;
         }
      }
      catch (Exception ex) {

      }
      return matrix;
   }

   private double[] fillIntialElements(Integer numOfElements, Integer numofDays, Double value) {

      double[] matrix = null;
      try {
         matrix = new double[numOfElements + numofDays];
         for (int i=0; i < numOfElements; i++) {
            matrix[i] = value;
         }
      }
      catch (Exception ex) {

      }
      return matrix;
   }

   private double[][] buildPIdentityMatrix(Integer numOfIndex, Integer numofDays) {
      Integer row =0,column=0;

      try {
         double[][] pIdentityMatrix = identityMatrix(numOfIndex+numofDays,1.0);

         for (column=0; column < numOfIndex;column++) {
            for (row=0; row < numOfIndex ; row++) {
                     if (row == column)
                        pIdentityMatrix[column][row] = 0.0;
               }
            }
         return pIdentityMatrix;
      }
      catch (Exception ex) {
         System.out.println("Error creating A Matrix ->column=" + column.toString() + " ->row=" + row.toString());
         ex.printStackTrace();

      }
      return null;
   }

   private double[][] buildAMatrix(Integer numOfIndex, Integer numofDays, double[][] indexFunds) {
      int row =0,column=0;
      int fundpos=0;

      try {
         double[][] matrix = new double[numofDays+1][numOfIndex+numofDays];
         int dayOffset = numOfIndex;
         for (row=0; row < numofDays+1 ; row++) {
            for (column=0; column < numOfIndex+numofDays;column++) {
               switch (row) {
                  case 0:
                           if (column < numOfIndex )
                              matrix[row][column] = 1.0;
                           break;
                  default:
                     if (column < numOfIndex) {
                        matrix[row][column] = indexFunds[fundpos][row-1];
                        if (fundpos < numOfIndex-1)
                           fundpos++;
                     }
                     else if (column == dayOffset)
                           matrix[row][column] = 1.0;
                     break;
               }
            }
            fundpos=0;
            if (row > 0)
               dayOffset++;

         }
         return matrix;
      }
      catch (Exception ex) {
         System.out.println("Error creating A Matrix ->row=" + row + " ->column=" + column);
         ex.printStackTrace();

      }
      return null;
   }

   public static void main(String[] args)
   {
      ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/spring/camel-context.xml");
      try {

         Thread.sleep(50000);
         applicationContext.close();
      } catch (Exception e) {
         e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
      }
   }


}
