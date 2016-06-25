package com.invmodel.model;

import java.util.ArrayList;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.dao.invdb.SecurityCollection;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.performance.ForwardProjection;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.Portfolio;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/20/16
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModelUtil
{
   AssetAllocationModel assetmodel;
   PortfolioModel portfolioModel;
   ForwardProjection projectiondata;
   private static ModelUtil instance;

   public static synchronized ModelUtil getInstance()
   {
      if (instance == null)
      {
         instance = new ModelUtil();
      }

      return instance;
   }

   public ModelUtil()
   {
      super();
   }

   public void setAssetmodel(AssetAllocationModel assetmodel)
   {
      this.assetmodel = assetmodel;
   }

   public void setPortfolioModel(PortfolioModel portfolioModel)
   {
      this.portfolioModel = portfolioModel;
   }

   public void setProjectiondata(ForwardProjection projectiondata)
   {
      this.projectiondata = projectiondata;
   }

   public AssetClass[] buildAllocation(ProfileData pdata)
   {
      if (pdata == null)
      {
         return null;
      }

      return assetmodel.buildAllocation(pdata);

   }



   public Portfolio[] buildPortfolio(AssetClass[] assetData, ProfileData profileData)
   {
         if (assetData == null)
         {
            return null;
         }

         if (profileData == null)
         {
            return null;
         }


      return portfolioModel.buildPortfolio(assetData, profileData);
   }

   public ArrayList<ProjectionData[]> buildProjectionData(ProfileData pdata) {
      if (projectiondata == null)
         return null;
       return projectiondata.buildProjectionData(pdata);
   }

   public void goalTracking(ProfileData pdata) {
      projectiondata.goalTracking(pdata);
   }
}
