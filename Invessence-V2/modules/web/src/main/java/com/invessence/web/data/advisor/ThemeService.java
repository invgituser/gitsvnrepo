package com.invessence.web.data.advisor;

import javax.faces.bean.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
@ManagedBean(name = "themeService")
@SessionScoped
public class ThemeService
{
/*
   public TreeNode createTheme(CustomerData goals)
   {
      TreeNode root;
      Subclass tmpTheme;
      TreeNode tmpNode;
      Map<String, TreeNode> assetTheme = new HashMap<String, TreeNode>();
      Map<String, TreeNode> items = new HashMap<String, TreeNode>();
      Integer weight;
      String asset;

      root = new DefaultTreeNode(new Subclass("Theme", "-", "-", "-"), null);
      items.clear();
      assetTheme.clear();

      try
      {
         if (goals == null)
         {
            return root;
         }

         if (goals.getPortfolioData() == null)
         {
            return root;
         }

         // Create a AssetClass Theme

         for (Integer i = 0; i < goals.getAssetData()[0].getOrderedAsset().size(); i++)
         {
            asset = goals.getAssetData()[0].getOrderedAsset().get(i);
            weight = goals.getAssetData()[0].getAssetRoundedWeight(asset);
*/
/*
            tmpNode = new DefaultTreeNode(new Subclass(asset, weight.toString(), asset, "-"), root);
            assetTheme.put(asset, tmpNode);
         }

         for (Integer i = 0; i < goals.getDisplayPortfolioList().size(); i++)
         {
            asset = goals.getDisplayPortfolioList().get(i).getAssetType();
            if (assetTheme.containsKey(asset)) {
               weight = (int) Math.round(goals.getDisplayPortfolioList().get(i).getTickerWeight());
               tmpTheme =  new Subclass(goals.getDisplayPortfolioList().get(i).getSubclass(),
                  weight.toString(),
                  asset,
                  goals.getDisplayPortfolioList().get(i).getSymbol());
                  tmpNode = new DefaultTreeNode(tmpTheme,assetTheme.get(asset));
            }
            else {
               asset = goals.getDisplayPortfolioList().get(i).getAssetType();
               weight = (int) Math.round(goals.getDisplayPortfolioList().get(i).getTickerWeight());
               tmpTheme =  new Subclass(goals.getDisplayPortfolioList().get(i).getSubclass(),
                                         weight.toString(),
                                         asset,
                                         goals.getDisplayPortfolioList().get(i).getSymbol());
               tmpNode = new DefaultTreeNode(tmpTheme, root);
               assetTheme.put(asset, tmpNode);
            }
         }
*//*

      }
      catch (Exception ex)
      {

      }
      return root;
   }
*/
}
