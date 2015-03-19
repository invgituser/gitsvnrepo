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
   private AssetParameters assetParameters = new AssetParameters();
   private PortfolioOptimizer portfolioOptimizer;

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

   public void setPortfolioOptimizer(PortfolioOptimizer portfolioOptimizer)
   {
      this.portfolioOptimizer = portfolioOptimizer;
   }

   public AssetClass[] getAdvisorAssetsInfo(ProfileData pdata)
   {
      AssetClass[] assetclass;
      String theme;
      try
      {
         Integer age = pdata.getDefaultAge();
         Integer duration = pdata.getDefaultHorizon();
         Integer stayInvested = pdata.getStayInvested();

         pdata.taxRate();

         theme = pdata.getTheme();
         if (theme == null || theme.length() == 0)
            theme = InvConst.DEFAULT_THEME;

         // If taxable account and theme is not taxable, them make it so.
         if (pdata.getAccountTaxable()) {
            if (! theme.startsWith("T."))
               theme = "T." + theme; // Note: allocation tries to determine, if this taxable theme is not defined, then it will use the CORE
         }

         int numofAllocation = pdata.getNumOfAllocation();
         if (numofAllocation <= 0)
            numofAllocation = 1;
         assetclass = new AssetClass[numofAllocation];
         int counter = 0;
         int offset = (pdata.getAllocationIndex() == null) ? InvConst.ASSET_DEFAULT_POINT : pdata.getAllocationIndex();
         while (numofAllocation > 0)
         {
            //Offset is now a slider (Just moving along the points)
            offset = (offset > InvConst.ASSET_INTERPOLATION - 1) ? InvConst.ASSET_INTERPOLATION - 1 : offset;
            offset = (offset < 0) ? 0 : offset;
            assetclass[counter] = noAdjustDurationRisk(theme, offset, duration,
                                                     age, stayInvested);
            age++;
            duration--;
            numofAllocation--;
            offset ++;
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

   private AssetClass noAdjustDurationRisk(String theme, int offset, int duration,
                                         int age, Integer stayInvested)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         assetclass.initAssetClass(age, duration, (double) offset, stayInvested, theme);
         ArrayList<String> orderedAssets = portfolioOptimizer.getAdvisorOrdertedAssetList(theme);
         double[] avgReturns = portfolioOptimizer.getAssetOrderedAvgReturns(theme);
         double[] weights = portfolioOptimizer.getAssetOrderedWeight(theme, offset);
         duration = (duration > InvConst.MAX_DURATION) ? InvConst.MAX_DURATION : duration;
         double wght = 0.0;
         double risk_adjustment = 0.0;
         double totalWeight = 1.0;
         double baseNum = 1 + ((double) duration / (double) age);
         double powerNum = -1 * (duration - 1);

         // Currently we are using Fixed Risk Adjustments,  We'll change this to get the data from DB
         // and store in assets Class.

         for (int i = 0; i < (orderedAssets.size()); i++)
         {
            String assetname = orderedAssets.get(i);
            String displayName =  portfolioOptimizer.getAssetData(theme, assetname).getDisplayname();
            String assetcolor = portfolioOptimizer.getAssetData(theme, assetname).getColor();

            // Always add each to asset List.
            assetclass.addAssetClass(assetname , displayName, assetcolor, 0.0, 0.0);

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

/*             Do not adjust base on age or stay invested.
               if (stayInvested == 1)
               {
                  risk_adjustment = portfolioOptimizer.getRiskAdjustment(theme,assetname);
               }
               else
               {

                  risk_adjustment = portfolioOptimizer.getEnd_allocation(theme,assetname);
               }

               double factor = (risk_adjustment - wght) * Math.pow(baseNum, powerNum);
*/
               //wght = (wght + factor);

               if (wght > totalWeight)
                  wght = totalWeight;

               assetclass.getAsset(assetname).setAllocweight(wght);
               assetclass.getAsset(assetname).setAvgReturn(avgReturns[i]);
               if (!assetname.equals("Cash"))
               {
                  totalWeight = totalWeight - wght;
               }
            }
            else
            {
               if (totalWeight < 0.0)
                  totalWeight = 0.0;
               assetclass.getAsset("Cash").setAllocweight(totalWeight);  // Adjust weight.
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

   public AssetClass[] getConsumerAssetInfo(ProfileData pdata)
   {
      AssetClass[] assetclass;
      Double adj_riskOffet;
      String theme;
      try
      {
         Integer age = pdata.getDefaultAge();
         Integer duration = pdata.getDefaultHorizon();
         Integer riskIndex = (pdata.getRiskIndex() == null) ? 0 : pdata.getRiskIndex();
         Integer stayInvested = pdata.getStayInvested();
         Integer objective = pdata.getObjective();
         adj_riskOffet = calc_riskOffset(age,duration,riskIndex);

         pdata.taxRate();

         theme = pdata.getTheme();
         if (theme == null || theme.length() == 0)
            theme = InvConst.DEFAULT_THEME;

         // If taxable account and theme is not taxable, them make it so.
         if (pdata.getAccountTaxable()) {
            if (! theme.startsWith("T."))
               theme = "T." + theme; // Note: allocation tries to determine, if this taxable theme is not defined, then it will use the CORE
         }

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

            assetclass[counter] = adjustDurationRisk(theme, offset, duration,
                                                     age, stayInvested);
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


   private AssetClass adjustDurationRisk(String theme, int offset, int duration,
                                         int age, Integer stayInvested)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         assetclass.initAssetClass(age, duration, (double) offset, stayInvested, theme);
         ArrayList<String> orderedAssets = portfolioOptimizer.getAdvisorOrdertedAssetList(theme);
         double[] avgReturns = portfolioOptimizer.getAssetOrderedAvgReturns(theme);
         double[] weights = portfolioOptimizer.getAssetOrderedWeight(theme, offset);
         duration = (duration > InvConst.MAX_DURATION) ? InvConst.MAX_DURATION : duration;
         double wght = 0.0;
         double risk_adjustment = 0.0;
         double totalWeight = 1.0;
         double baseNum = 1 + ((double) duration / (double) age);
         double powerNum = -1 * (duration - 1);

         // Currently we are using Fixed Risk Adjustments,  We'll change this to get the data from DB
         // and store in assets Class.

         for (int i = 0; i < (orderedAssets.size()); i++)
         {
            String assetname = orderedAssets.get(i);
            String displayName =  portfolioOptimizer.getAssetData(theme, assetname).getDisplayname();
            String assetcolor = portfolioOptimizer.getAssetData(theme, assetname).getColor();

            // Always add each to asset List.
            assetclass.addAssetClass(assetname , displayName, assetcolor, 0.0, 0.0);

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
                  risk_adjustment = portfolioOptimizer.getRiskAdjustment(theme,assetname);
               }
               else
               {

                  risk_adjustment = portfolioOptimizer.getEnd_allocation(theme,assetname);
               }

               double factor = (risk_adjustment - wght) * Math.pow(baseNum, powerNum);
               wght = (wght + factor);

               if (wght > totalWeight)
                  wght = totalWeight;

               assetclass.getAsset(assetname).setAllocweight(wght);
               assetclass.getAsset(assetname).setAvgReturn(avgReturns[i]);
               if (!assetname.equals("Cash"))
               {
                  totalWeight = totalWeight - wght;
               }
            }
            else
            {
               if (totalWeight < 0.0)
                  totalWeight = 0.0;
               assetclass.getAsset("Cash").setAllocweight(totalWeight);  // Adjust weight.
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

         //pdata.offsetRiskIndex();
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
            aac.getAsset(assetname).setUserweight(0.0);
         }
         // Now set it to user value.
         int counter = userAsset.size();
         for (int loop = 0; loop < counter; loop++)
         {
            assetname = userAsset.get(loop).getAsset();
            aac.getAsset(assetname).setUserweight(userAsset.get(loop).getUserweight());
         }
      }
   }


}