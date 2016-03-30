package com.invmodel.asset;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.data.*;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.*;
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
   private FixedModelOptimizer fixedOptimizer;

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

   public void setFixedOptimizer(FixedModelOptimizer fixedOptimizer)
   {
      this.fixedOptimizer = fixedOptimizer;
   }

   public AssetClass[] buildAllocation(ProfileData pdata)
   {
      if (pdata == null)
      {
         return null;
      }

      if (fixedOptimizer != null)
      {
         if (fixedOptimizer.isThisFixedTheme(pdata.getTheme()))
         {
            pdata.setFixedModel(true);
            pdata.setNumOfAllocation(1);
            AssetClass[] assetclass = new AssetClass[pdata.getNumOfAllocation()];
            pdata.setMaxAssetAllocatonPoints(fixedOptimizer.getThemes(pdata.getTheme()).size());
            assetclass[0] = fixedModelAllocation(pdata);

            return assetclass;
         }
      }

      if (!portfolioOptimizer.isValidTheme(pdata.getTheme()))
      {
         pdata.setTheme(InvConst.DEFAULT_THEME);
      }

      pdata.setMaxAssetAllocatonPoints(portfolioOptimizer.getAssetAllocationPoints());
      if (pdata.getRiskCalcMethod() == null || pdata.getRiskCalcMethod().startsWith("C"))
      {
         return getConsumerAssetInfo(pdata);
      }
      else
      {
         return getAdvisorAssetsInfo(pdata);
      }
   }

   public Integer getAllocationIndex(ProfileData pdata)
   {

      if (pdata == null)
      {
         return InvConst.ASSET_DEFAULT_POINT;
      }

      Integer age = pdata.getDefaultAge();
      Integer duration = pdata.getDefaultHorizon();
      Integer riskIndex = (pdata.getRiskIndex() == null) ? 0 : pdata.getRiskIndex();
      Double adj_riskOffet = calc_riskOffset(age, duration, riskIndex);

      //Age based offset
      int offset = (int) (100 - ((age < 21) ? 21 : ((age > 100) ? 100 : age)));
      offset = (int) (offset * adj_riskOffet);
      return offset;
   }

   private AssetClass[] getAdvisorAssetsInfo(ProfileData pdata)
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

         int numofAllocation = pdata.getNumOfAllocation();
         if (numofAllocation <= 0)
         {
            numofAllocation = 1;
         }
         assetclass = new AssetClass[numofAllocation];
         int counter = 0;
         int offset = (pdata.getAllocationIndex() == null) ? InvConst.ASSET_DEFAULT_POINT : pdata.getAllocationIndex();
         while (numofAllocation > 0)
         {
            //Offset is now a slider (Just moving along the points)
            // offset = InvConst.ASSET_INTERPOLATION - offset;
            offset = (offset > InvConst.ASSET_INTERPOLATION - 1) ? InvConst.ASSET_INTERPOLATION - 1 : offset;
            offset = (offset < 0) ? 0 : offset;
            assetclass[counter] = noAdjustDurationRisk(theme, offset, duration,
                                                       age, stayInvested);
            age++;
            duration--;
            numofAllocation--;
            offset++;
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
            String displayName = portfolioOptimizer.getAssetData(theme, assetname).getDisplayname();
            String assetcolor = portfolioOptimizer.getAssetData(theme, assetname).getColor();

            // Always add each to asset List.
            assetclass.addAssetClass(assetname, displayName, assetcolor, 0.0, 0.0);

            wght = weights[i];
            if (wght < 0.0001)
            {
               continue;
            }

            // If input from user is to go to cash then risk_adjustment should be 0
            // If input from user is stay_invested then initial equity allocation is
            // Equity allocation = 100 - age
            // Adjust this number for risk tolerance

            //only risk adjust equity


            if (!assetname.equals("Cash"))
            {

               // Do not adjust base on age or stay invested.
               if (stayInvested == 1)
               {
                  risk_adjustment = portfolioOptimizer.getRiskAdjustment(theme, assetname);
               }
               else
               {

                  risk_adjustment = portfolioOptimizer.getEnd_allocation(theme, assetname);
               }

               double factor = (risk_adjustment - wght) * Math.pow(baseNum, powerNum);
               wght = (wght + factor);


               if (wght > totalWeight)
               {
                  wght = totalWeight;
               }

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
               {
                  totalWeight = 0.0;
               }
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

   private AssetClass[] getConsumerAssetInfo(ProfileData pdata)
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
         adj_riskOffet = calc_riskOffset(age, duration, riskIndex);

         pdata.taxRate();

         theme = pdata.getTheme();

         int numofAllocation = pdata.getNumOfAllocation();
         if (numofAllocation <= 0)
         {
            numofAllocation = 1;
         }
         assetclass = new AssetClass[numofAllocation];
         int counter = 0;
         while (numofAllocation > 0)
         {
            //Age based offset
            int offset = (int) (100 - ((age < 21) ? 21 : ((age > 100) ? 100 : age)));
            //JAV 8/28/2013
            offset = (int) (offset * adj_riskOffet);
            if (counter == 0)
            {
               pdata.setAllocationIndex(offset);
            }

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
            String displayName = portfolioOptimizer.getAssetData(theme, assetname).getDisplayname();
            String assetcolor = portfolioOptimizer.getAssetData(theme, assetname).getColor();

            // Always add each to asset List.
            assetclass.addAssetClass(assetname, displayName, assetcolor, 0.0, 0.0);

            wght = weights[i];
            if (wght < 0.0001)
            {
               continue;
            }

            // If input from user is to go to cash then risk_adjustment should be 0
            // If input from user is stay_invested then initial equity allocation is
            // Equity allocation = 100 - age
            // Adjust this number for risk tolerance

            //only risk adjust equity


            if (!assetname.equals("Cash"))
            {

               if (stayInvested == 1)
               {
                  risk_adjustment = portfolioOptimizer.getRiskAdjustment(theme, assetname);
               }
               else
               {

                  risk_adjustment = portfolioOptimizer.getEnd_allocation(theme, assetname);
               }

               double factor = (risk_adjustment - wght) * Math.pow(baseNum, powerNum);
               wght = (wght + factor);

               if (wght > totalWeight)
               {
                  wght = totalWeight;
               }

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
               {
                  totalWeight = 0.0;
               }
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
         {
            riskOffset = 0;
         }
         else
         {
            riskOffset = riskIndex;
         }


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

   public void overrideAssetWeight(AssetClass aac, List<Asset> userAsset)
   {
      String assetname;

      if (userAsset == null)
      {
         return;
      }

      if (aac != null)
      {
         // First reset all weight to zero for AssetClass, else it may not add to 100
         for (int loop = 0; loop < aac.getOrderedAsset().size(); loop++)
         {
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

   private AssetClass fixedModelAllocation(ProfileData pdata)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         FMData fixedModelData;
         Double wght;
         Double totalWeight = 1.0;
         Integer age =  pdata.getAge();
         Double investment = pdata.getActualInvestment();
         String theme =  pdata.getTheme();

         Double adj_riskOffet = calc_riskOffset(age, pdata.getDefaultHorizon(), pdata.getRiskIndex());
         //Age based offset
         Integer offset = (int) (100 - ((age < 21) ? 21 : ((age > 100) ? 100 : age)));
         //JAV 8/28/2013
         offset = (int) (offset * adj_riskOffet);
         assetclass.initAssetClass(pdata.getAge(), pdata.getDefaultHorizon(), offset.doubleValue(),
                                   pdata.getStayInvested(), theme);

         if (pdata.getRiskCalcMethod().equalsIgnoreCase("C")) {
            fixedModelData = fixedOptimizer.getTheme(theme, offset.intValue());
         }
         else {
            fixedModelData = fixedOptimizer.getThemeByIndex(theme, pdata.getAllocationIndex());
         }

         if (fixedModelData != null) {
            pdata.setAllocationIndex(fixedModelData.getIndex());
            for (FMAsset fiasset : fixedModelData.getAssetsData())
            {
               String assetname = fiasset.getAsset();
               String displayName = fiasset.getDisplayname();
               String assetcolor = fiasset.getColor();

               // Always add each to asset List.
               assetclass.addAssetClass(assetname, displayName, assetcolor, 0.0, 0.0);

               wght = fiasset.getWeight();

               if (wght >= 1.0)
               {
                  wght = wght / 100.0;
               }

               if (wght < 0.0001)
               {
                  continue;
               }

               if (!assetname.equalsIgnoreCase("Cash"))
               {

                  Double risk_adjustment = 0.0;

                  if (wght > totalWeight)
                  {
                     wght = totalWeight;
                  }

                  assetclass.getAsset(assetname).setAllocweight(wght);
                  assetclass.getAsset(assetname).setValue(wght * investment);
                  assetclass.getAsset(assetname).setAvgReturn(0.0);
                  if (!assetname.equals("Cash"))
                  {
                     totalWeight = totalWeight - wght;
                  }
               }
               else
               {
                  if (totalWeight < 0.0)
                  {
                     totalWeight = 0.0;
                  }
                  assetclass.getAsset("Cash").setAllocweight(totalWeight);  // Adjust weight.
                  assetclass.getAsset("Cash").setHoldingValue(totalWeight * investment);
                  // allocation Cash here with color.
                  //assetclass.addAssetClass(assetname, totalWeight, 0.0, assetcolor[i]);
               }
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

}
