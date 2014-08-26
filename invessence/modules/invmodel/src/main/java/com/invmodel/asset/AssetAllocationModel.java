package com.invmodel.asset;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.dao.*;
import com.invmodel.inputData.ProfileData;
import webcab.lib.finance.portfolio.*;

//------------------------------
// To Do
// When loading asset level data we should also load other data such as their name, cost, lower and upper
// bound constraints, every thing that is specific to each assets
//
// Added riskIndex and TOTAL_RISK_INDEX -JAV 8/29/2013
//-------------------------------

public class AssetAllocationModel
{
   private static AssetAllocationModel instance = null;
   private CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
   private AssetParameters assetParameters = new AssetParameters();
   private AssetDBCollection assetdao;
   private HistoricalReturns hr;

   public static synchronized AssetAllocationModel getInstance()
   {
      if (instance == null)
      {
         instance = new AssetAllocationModel();
      }

      return instance;
   }

   private AssetAllocationModel()
   {
   }

   public void setAssetdao(AssetDBCollection assetdao)
   {
      this.assetdao = assetdao;
   }

   public void setHr(HistoricalReturns hr)
   {
      this.hr = hr;
   }

   private void initAllocation(String groupname)
   {
      try
      {
         // assetdao.loadDataFromDB();  // Load data from database.
         String[] indexFund = assetdao.getAssetOrderedIndex(groupname);

         //hr.loadHistoricalReturns();

         double[][] histReturns = hr.getHistoricalReturnsArray(indexFund);
         if (histReturns != null)
         {
            //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(histReturns);
            double[] expectedReturnsOfFunds = assetdao.getAssetOrderedAvgReturns(groupname);
            double[][] covarianceOfFunds = assetParameters.covarianceMatrix(histReturns);

            // Here we evaluate the maximum expected return  of  the  Portfolio's  on
            // the Efficient Frontier.
            double maxReturn1 = instanceOfCapitalMarket.maxFrontierReturn(expectedReturnsOfFunds);
            // Here we evaluate the minimum expected return of the Portfolio's on  the
            // Efficient Frontier.
            double minReturn1 = instanceOfCapitalMarket.minFrontierReturn(expectedReturnsOfFunds);

            //Set lower and upper bound conditions


            double[] lowerBound = assetdao.getAssetOrderedLowerBound(groupname);
            double[] upperBound = assetdao.getAssetOrderedUpperBound(groupname);


            instanceOfCapitalMarket.setConstraints(lowerBound, upperBound);

            instanceOfCapitalMarket.calculateEfficientFrontier(
               minReturn1, // minimumExpectedReturn
               maxReturn1, // maximumExpectedReturn
               covarianceOfFunds,//Covariance matrix
               expectedReturnsOfFunds, // expectedReturns
               InvConst.ASSET_INTERPOLATION, // numberInterpolationPoints
               InvConst.ASSET_PRECISION  // precision
            );

            //double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks( covarianceOfFunds);

         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   public AssetClass[] getAssetDistribution(ProfileData pdata)
   {
      AssetClass[] assetclass;
      Double adj_riskOffet;
      try
      {
         Integer age = (pdata.getAge() == null) ? 30 : pdata.getAge();
         Integer adjduration;
         if ((70 - age) > 0)
            adjduration = 70 - age;
         else
            adjduration = 1;
         Integer duration = (pdata.getHorizon() == null) ? adjduration : pdata.getHorizon();
         duration = (duration <= 0) ? 1: duration;
         Integer riskIndex = (pdata.getRiskIndex() == null) ? 0 : pdata.getRiskIndex();
         Integer stayInvested = pdata.getStayInvested();
         Integer objective = pdata.getObjective();
         adj_riskOffet = calc_riskOffset(age,duration,riskIndex);

         // Objective = 1 then preservation of assets   JAV 1/7/2014
         //if (objective == 1 )
         //   adj_riskOffet = 0.5 * adj_riskOffet;

         //Set tax rate
         pdata.taxRate();

         initAllocation(pdata.getAdvisor());

         // Weights of the portfolio on the Efficient Frontier.
         double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();

         int numofAllocation = pdata.getNumOfAllocation();
         if (numofAllocation <= 0)
            numofAllocation = 1;
         assetclass = new AssetClass[numofAllocation];
         int counter = 0;
          while (numofAllocation > 0)
         {
            //Age based offset
            int offset = (int) (100 - ((age < 21) ? 21 : ((age > 100) ? 100 : age)));

            //JAV 8/28/2013
            offset = (int) (offset * adj_riskOffet);
            assetclass[counter] = adjustDurationRisk(pdata.getAdvisor(), weights[offset], duration,
                                                     age, adj_riskOffet, stayInvested);

            duration--;
            numofAllocation--;
            age++;
            counter++;
         }
         return assetclass;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   private AssetClass adjustDurationRisk(String groupname, double[] weights, int duration,
                                         int age, Double riskOffset, Integer stayInvested)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         String[] orderedAssets = assetdao.getOrderedAsset(groupname);
         String[] assetcolor = assetdao.getAssetOrderedColor(groupname);
         double[] avgReturns = assetdao.getAssetOrderedAvgReturns(groupname);
         duration = (duration > InvConst.MAX_DURATION) ? InvConst.MAX_DURATION : duration;
         double wght = 0.0;
         double risk_adjustment = 0.0;
         double totalWeight = 1.0;
         double baseNum = 1 + ((double) duration / (double) age);
         double powerNum = -1 * (duration - 1);

         // Currently we are using Fixed Risk Adjustments,  We'll change this to get the data from DB
         // and store in assets Class.

         assetclass.initAssetClass(age, duration, riskOffset, stayInvested);
         for (int i = 0; i < (orderedAssets.length); i++)
         {
            String assetname = orderedAssets[i];

            // Always add each to asset List.
            assetclass.addAssetClass(assetname, 0.0, 0.0, assetcolor[i]);

            wght = weights[i];
            if (wght < 0.0001)
               continue;

            // If input from user is to go to cash then risk_adjustment should be 0
            // If input from user is stay_invested then initial equity allocation is
            // Equity allocation = 100 - age
            // Adjust this number for risk tolerance

            //only risk adjust equity


            if (!assetname.equals("Cash"))
            {

               if (stayInvested == 1)
               {
                  risk_adjustment = assetdao.getRiskAdjustment(groupname,assetname);
               }
               else
               {

                  risk_adjustment = assetdao.getEnd_allocation(groupname,assetname);
               }

               double factor = (risk_adjustment - wght) * Math.pow(baseNum, powerNum);
               wght = (wght + factor);

               if (wght > totalWeight)
                  wght = totalWeight;

               assetclass.setAssetActualWeight(assetname,wght);
               assetclass.setAssetAverageReturns(assetname,avgReturns[i]);
               if (!assetname.equals("Cash"))
               {
                  totalWeight = totalWeight - wght;
               }
            }
            else
            {
               if (totalWeight < 0.0)
                  totalWeight = 0.0;
               assetclass.setAssetActualWeight("Cash", totalWeight);  // Adjust weight.
               // allocation Cash here with color.
               //assetclass.addAssetClass(assetname, totalWeight, 0.0, assetcolor[i]);
            }
         }

         return assetclass;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   private Double calc_riskOffset(Integer age, Integer horizon, Integer riskIndex)
   {
      Double adj_riskpoint;
      double baseNum = 1.0 + ((double) horizon / (double) age);
      double powerNum = -1.0 * ((double) horizon - 1.0);
      try
      {

         //pdata.adjustRiskIndex();
         Integer riskOffset;
         if (riskIndex == null)
            riskOffset = 0;
         else
            riskOffset = riskIndex;


         adj_riskpoint = ((InvConst.MAX_RISK_OFFSET.doubleValue() - riskOffset.doubleValue()) / InvConst.MAX_RISK_OFFSET);

         //This creates a very conservative portfolio
         //double adj = adj_riskpoint *(1 - Math.pow(baseNum, powerNum));
         adj_riskpoint = adj_riskpoint * (1.0 - Math.pow(baseNum, powerNum));


         return adj_riskpoint;
      }

      catch (Exception ex)
      {
         System.out.println("Exception on RiskOffer" + ex.getMessage());
      }
      return (0.0);
   }

   public void overrideAssetWeight(AssetClass aac, List<Asset> userAsset) {
      String assetname;

      if (userAsset == null) {
         return;
      }

      if (aac != null) {
         // First reset all weight to zero for AssetClass, else it may not add to 100
         for (int loop = 0; loop < aac.getOrderedAsset().size(); loop++) {
            assetname = aac.getOrderedAsset().get(loop);
            aac.setAssetActualWeight(assetname,0.0);
         }
         // Now set it to user value.
         int counter = userAsset.size();
         for (int loop = 0; loop < counter; loop++)
         {
            assetname = userAsset.get(loop).getAsset();
            aac.setAssetActualWeight(assetname, userAsset.get(loop).getActualweight());
         }
      }
   }

}