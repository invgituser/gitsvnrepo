package com.invmodel.model;

import java.util.*;

import com.invmodel.Const.InvConst;
import com.invmodel.asset.AssetAllocationModel;
import com.invmodel.asset.data.AssetClass;
import com.invmodel.dao.invdb.SecurityCollection;
import com.invmodel.inputData.ProfileData;
import com.invmodel.model.dynamic.PortfolioOptimizer;
import com.invmodel.model.fixedmodel.FixedModelOptimizer;
import com.invmodel.model.fixedmodel.data.FMData;
import com.invmodel.performance.ForwardProjection;
import com.invmodel.performance.data.ProjectionData;
import com.invmodel.portfolio.PortfolioModel;
import com.invmodel.portfolio.data.Portfolio;
import com.invmodel.rebalance.TLHSecurityCollection;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 6/20/16
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModelUtil
{
   private AssetAllocationModel assetmodel;
   private PortfolioModel portfolioModel;
   private ForwardProjection projectiondata;
   private static ModelUtil instance;
   private PortfolioOptimizer poptimizer = null;
   private FixedModelOptimizer fixoptimizer = null;
   private TLHSecurityCollection tlhSecurityCollection = null;



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
      portfolioModel = PortfolioModel.getInstance();
      projectiondata = ForwardProjection.getInstance();
      assetmodel = AssetAllocationModel.getInstance();
      poptimizer = PortfolioOptimizer.getInstance();
      fixoptimizer = FixedModelOptimizer.getInstance();
      tlhSecurityCollection = TLHSecurityCollection.getInstance();
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
/*
      if (pdata == null)
      {
         return null;
      }
*/

      return assetmodel.buildAllocation(pdata);

   }

   public void refreshData() {
       poptimizer.refreshDataFromDB();
      fixoptimizer.refreshDataFromDB();
      tlhSecurityCollection.refreshDataFromDB();
   }

   public ArrayList<FMData> getThemePortfolios(String theme) {
      if (fixoptimizer != null ) {
         for (FMData fmData:fixoptimizer.getAllThemes())
         if (fmData.getTheme().equalsIgnoreCase(theme)) {
            return fixoptimizer.getThemePortfolios(theme);
         }
      }
      return null;
   }
   public LinkedHashMap<String,FMData> getThemePortfoliosMap(String theme) {
      if (fixoptimizer != null ) {
         LinkedHashMap<String,FMData> stringFMDataLinkedHashMap=new LinkedHashMap<String, FMData>();
         for (FMData fmData:fixoptimizer.getAllThemes())
            if (fmData.getTheme().equalsIgnoreCase(theme)) {
               stringFMDataLinkedHashMap.put(fmData.getDisplayname(),fmData);
            }
         return stringFMDataLinkedHashMap;
      }
      return null;
   }

   public FMData getThemePortfolios(String theme,String portfolioName) {
      if (fixoptimizer != null ) {
         for (FMData fmData:fixoptimizer.getAllThemes())
            if (fmData.getTheme().equalsIgnoreCase(theme)) {
            return fixoptimizer.getThemeByPortfolioName(theme,portfolioName);
         }
      }
      return null;
   }

   public Portfolio[] buildPortfolio(AssetClass[] assetData, ProfileData profileData)
   {
/*
         if (assetData == null)
         {
            return null;
         }

         if (profileData == null)
         {
            return null;
         }
*/


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
