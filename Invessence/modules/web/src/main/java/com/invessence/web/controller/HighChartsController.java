package com.invessence.web.controller;

import java.util.*;
import javax.faces.bean.*;

import com.invessence.web.data.common.*;
import com.invessence.web.util.WebUtil;
import com.invmodel.asset.data.*;
import com.invmodel.inputData.ProfileData;
import com.invmodel.portfolio.data.Portfolio;

/**
 * Created by Akhilesh on 2/20/2017.
 */

@SessionScoped
public class HighChartsController
{
   ProfileData profileData = new ProfileData();
   WebProfile webProfile = new WebProfile();
   @ManagedProperty("#{webutil}")
   private WebUtil webutil;
   private ArrayList<Asset> editableAsset = new ArrayList<Asset>();

   private GenerateHighChartsData generateHighChartsData = new GenerateHighChartsData();

   public ProfileData getProfileData()
   {
      return profileData;
   }

   public void setProfileData(ProfileData profileData)
   {
      this.profileData = profileData;
   }

   public WebProfile getWebProfile()
   {
      return webProfile;
   }

   public void setWebProfile(WebProfile webProfile)
   {
      this.webProfile = webProfile;
   }

   public WebUtil getWebutil()
   {
      return webutil;
   }

   public ArrayList<Asset> getEditableAsset()
   {
      return editableAsset;
   }

   public void setEditableAsset(ArrayList<Asset> editableAsset)
   {
      this.editableAsset = editableAsset;
   }

   public void setWebutil(WebUtil webutil)
   {

      this.webutil = webutil;
   }



   public String highChartrequesthandler(Portfolio[] portfolioData, AssetClass[] assetData, Map<String, String> webInfo){
      String chartJSON = "";
      if(webInfo.get("CHART.ASSET.ALLOCATION").equalsIgnoreCase("HIGHCHART.2DDONUT")){
         chartJSON =  generateHighChartsData.create2DDONUTChart(new ArrayList<Asset>(assetData[0].getAssetclass().values()));
      }
      return chartJSON;
   }

}
