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
   private static AssetAllocationModel instance;
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
      portfolioOptimizer = PortfolioOptimizer.getInstance();
      fixedOptimizer = FixedModelOptimizer.getInstance();
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
            //rc.client.getRisk(...);
            pdata.setMaxAssetAllocatonPoints(fixedOptimizer.getThemes(pdata.getTheme()).size() - 1);
            assetclass[0] = fixedModelAllocation(pdata);
            return assetclass;
         }
      }

      if (!portfolioOptimizer.isValidTheme(pdata.getTheme()))
      {
         pdata.setTheme(InvConst.DEFAULT_THEME);
      }

      pdata.setMaxAssetAllocatonPoints(portfolioOptimizer.getAssetAllocationPoints());
      pdata.setFixedModel(false);
      pdata.setHasReturn(true);
      pdata.setHasRisk(true);

      if (pdata.getRiskCalcMethod() == null || pdata.getRiskCalcMethod().startsWith(InvConst.CONSUMER_RISK_FORMULA))
      {
         return getAssetsInfoByIndex(pdata, pdata.getRiskIndex().intValue());
      }
      else
      {
         return getAssetsInfoByIndex(pdata, pdata.getAllocationIndex());
      }

/*
      if (pdata.getRiskCalcMethod() == null || pdata.getRiskCalcMethod().startsWith(InvConst.CONSUMER_RISK_FORMULA))
      {
         return getAssetInfoByRisk(pdata);
      }
      else
      {
         return getAssetsInfoByIndex(pdata);
      }
*/
   }


   public AssetClass[] getAssetsInfoByIndex(ProfileData pdata, Integer riskIndex)
   {
      AssetClass[] assetclass;
      String theme;
      try
      {
         Integer age = pdata.getDefaultAge();
         Integer duration = pdata.getDefaultHorizon();
         Integer stayInvested = pdata.getStayInvested();

         theme = pdata.getTheme();

         int numofAllocation = pdata.getNumOfAllocation();
         if (numofAllocation <= 0)
         {
            numofAllocation = 1;
         }
         assetclass = new AssetClass[numofAllocation];
         int counter = 0;
         int offset = (riskIndex == null) ? InvConst.ASSET_DEFAULT_POINT : riskIndex;
         while (numofAllocation > 0)
         {
            //Offset is now a slider (Just moving along the points)
            // offset = InvConst.ASSET_INTERPOLATION - offset;
            offset = (offset > InvConst.ASSET_INTERPOLATION - 1) ? InvConst.ASSET_INTERPOLATION - 1 : offset;
            offset = (offset < 0) ? 0 : offset;
            if (counter == 0)
            {
               pdata.setAllocationIndex(offset);
            }
            if (offset == 0 && pdata.getAllCashonZeroRisk())
            {
               assetclass[counter] = setToAllCash(theme, offset, duration,
                                                         age, stayInvested);
            }
            else {
               assetclass[counter] = createAssetsByIndex(theme, offset, duration,
                                                         age, stayInvested);
            }
            age++;
            duration--;
            numofAllocation--;
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

   private AssetClass createAssetsByIndex(String theme, int offset, int duration,
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

         double totalRemainWeight = 1.0;


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

            if (!assetname.equals("Cash"))
            {
               // JAV 6/2/2017
               //This is to risk adjust target date funds. Only for Invessence models
               double factor = 0.0;
               //Only themes used by Invessence
               if ( theme.contentEquals("0.Core 0.Safety 0.Income T.0.Core T.0.Income T.0.Safety"))
               {
                  factor = adjustmentFactors(duration, age, stayInvested, theme, assetname, wght);
               }

               wght = (wght + factor);

               if (wght > totalRemainWeight)
               {
                  wght = totalRemainWeight;
               }

               assetclass.getAsset(assetname).setAllocweight(wght);
               assetclass.getAsset(assetname).setUserweight(wght);
               assetclass.getAsset(assetname).setActualweight(wght);
               assetclass.getAsset(assetname).setAvgReturn(avgReturns[i]);
               if (!assetname.equals("Cash"))
               {
                  totalRemainWeight = totalRemainWeight - wght;
               }
            }
            else
            {
               if (totalRemainWeight < 0.0)
               {
                  totalRemainWeight = 0.0;
               }
               assetclass.getAsset("Cash").setAllocweight(totalRemainWeight);  // Adjust weight.
               assetclass.getAsset("Cash").setUserweight(totalRemainWeight);
               assetclass.getAsset("Cash").setActualweight(totalRemainWeight);
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

   private AssetClass setToAllCash(String theme, int offset, int duration,
                                          int age, Integer stayInvested)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         assetclass.initAssetClass(age, duration, (double) offset, stayInvested, theme);
         double wght = 1.0;

         String cash = "Cash";

         // Always add each to asset List.
         String assetcolor = portfolioOptimizer.getAssetData(theme, cash).getColor();
         assetclass.addAssetClass(cash, cash, assetcolor, 0.0, 0.0);
         assetclass.getAsset(cash).setAllocweight(wght);
         assetclass.getAsset(cash).setUserweight(wght);
         assetclass.getAsset(cash).setActualweight(wght);
         assetclass.getAsset(cash).setAvgReturn(0.0);

         return assetclass;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   public double adjustmentFactors(double duration, double age, int stayInvested, String theme, String assetname, double wght)
   {
      //JAV 6/2/2017
      // If input from user is to go to cash then risk_adjustment should be 0
      // If input from user is stay_invested then initial equity allocation is
      // Equity allocation = 100 - age
      // Adjust this number for risk tolerance
      //only risk adjust equity
      //This was an adjustment made for the USA models.
      //For target date type strategies which reduces risk over time.
      //Since we are moving this can be a separate function if we need to add to USA themes only.
      double risk_adjustment = 0.0;
      double baseNum = 1 + ((double) duration / (double) age);
      double powerNum = -1 * (duration - 1);

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

      return factor;
   }

   public AssetClass[] getAssetInfoByRisk(ProfileData pdata)
   {
      AssetClass[] assetclass;
      Double adj_riskOffet;
      String theme;
      try
      {
         Integer age = pdata.getDefaultAge();
         Integer duration = pdata.getDefaultHorizon();
         Double riskIndex = (pdata.getRiskIndex() == null) ? 0 : pdata.getRiskIndex();
         Integer stayInvested = pdata.getStayInvested();
         Integer objective = pdata.getObjective();
         adj_riskOffet = riskIndex;

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

            assetclass[counter] = createAssetsByRisk(theme, offset, duration,
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



   private AssetClass createAssetsByRisk(String theme, int offset, int duration,
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
               assetclass.getAsset(assetname).setUserweight(wght);
               assetclass.getAsset(assetname).setActualweight(wght);
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
               assetclass.getAsset("Cash").setUserweight(totalWeight);  // Adjust weight.
               assetclass.getAsset("Cash").setActualweight(totalWeight);  // Adjust weight.
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

   public AssetClass fixedModelAllocation(ProfileData pdata)
   {

      AssetClass assetclass = new AssetClass();
      try
      {
         FMData fixedModelData;
         Double wght;
         Double totalWeight = 1.0;
         Integer age =  pdata.getAge();
         Double investment = pdata.getDefaultInvestment();
         String theme =  pdata.getTheme();

         Double adjRiskOffet = pdata.getRiskIndex();
         //Age based offset
         assetclass.initAssetClass(pdata.getAge(), pdata.getDefaultHorizon(), adjRiskOffet,
                                   pdata.getStayInvested(), theme);

         if (pdata.getRiskCalcMethod().equalsIgnoreCase(InvConst.CONSUMER_RISK_FORMULA)) {
            fixedModelData = fixedOptimizer.getTheme(theme, adjRiskOffet);
         }
         else {
            fixedModelData = fixedOptimizer.getThemeByIndex(theme, pdata.getAllocationIndex());
         }

         pdata.setFixedFMModel(fixedModelData);
         if (fixedModelData != null) {
            pdata.setAllocationIndex(fixedModelData.getIndex());
            pdata.setPortfolioIndex(fixedModelData.getIndex());
            for (FMAssetData fiasset : fixedModelData.getAssetsData())
            {
               String assetname = fiasset.getAsset();
               String displayName = fiasset.getDisplayname();
               String assetcolor = fiasset.getColor();

               // Always add each to asset List.
               assetclass.addAssetClass(assetname, displayName, assetcolor, 0.0, 0.0);

               wght = fiasset.getWeight();

               if (!assetname.equalsIgnoreCase("Cash"))
               {
                  if (wght < 0.0001)
                  {
                     continue;
                  }

                  Double risk_adjustment = 0.0;

                  if (wght > totalWeight)
                  {
                     wght = totalWeight;
                  }

                  assetclass.getAsset(assetname).setAllocweight(wght);
                  assetclass.getAsset(assetname).setUserweight(wght);
                  assetclass.getAsset(assetname).setActualweight(wght);
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
                  assetclass.getAsset("Cash").setUserweight(totalWeight);  // Adjust weight.
                  assetclass.getAsset("Cash").setActualweight(totalWeight);  // Adjust weight.
                  assetclass.getAsset("Cash").setValue(totalWeight * investment);
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
