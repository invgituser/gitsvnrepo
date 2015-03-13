package com.invessence.data;

import java.util.*;

import com.invmodel.asset.data.Asset;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 10/14/14
 * Time: 1:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class ManagedAssetList
{
   Map<String, Asset> assetList = new HashMap<String, Asset>();
   Map<String, ManagedSubclassData> subassetList = new HashMap<String, ManagedSubclassData>();
   ArrayList<String> orderedAssets;
   ArrayList<String> orderedSubclass;

   public ManagedAssetList()
   {
   }

   public void addAsset(Asset asset) {
      if (asset != null) {
         if (! assetList.containsKey(asset.getAsset())) {
            if (orderedAssets == null)
               orderedAssets = new ArrayList<String>();
            orderedAssets.add(asset.getAsset());
         }
         assetList.put(asset.getAsset(), asset);
      }
   }

   public void addSubclass(ManagedSubclassData msd) {
      if (msd != null) {
         if (! assetList.containsKey(msd.getName()))
            orderedSubclass.add(msd.getName());
         subassetList.put(msd.getName(), msd);
      }
   }

   public void clearAll() {
      if (assetList != null)
         assetList.clear();
      if (subassetList != null)
         subassetList.clear();
      if (orderedAssets != null)
         orderedAssets.clear();
      if (orderedSubclass != null)
         orderedSubclass.clear();
   }

}
