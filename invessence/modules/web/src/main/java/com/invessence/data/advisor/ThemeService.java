package com.invessence.data.advisor;

import java.util.*;

import javax.faces.bean.*;

import com.invessence.data.*;
import org.primefaces.model.*;


/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 1/9/14
 * Time: 8:58 PM
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("UnusedDeclaration")
@ManagedBean(name = "themeService")
@ApplicationScoped
public class ThemeService
{
/*
   public TreeNode createTheme(ManageGoals goals)
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

         for (Integer i = 0; i < goals.getPortfolioList().size(); i++)
         {
            asset = goals.getPortfolioList().get(i).getAssetType();
            if (assetTheme.containsKey(asset)) {
               weight = (int) Math.round(goals.getPortfolioList().get(i).getTickerWeight());
               tmpTheme =  new Subclass(goals.getPortfolioList().get(i).getSubclass(),
                  weight.toString(),
                  asset,
                  goals.getPortfolioList().get(i).getSymbol());
                  tmpNode = new DefaultTreeNode(tmpTheme,assetTheme.get(asset));
            }
            else {
               asset = goals.getPortfolioList().get(i).getAssetType();
               weight = (int) Math.round(goals.getPortfolioList().get(i).getTickerWeight());
               tmpTheme =  new Subclass(goals.getPortfolioList().get(i).getSubclass(),
                                         weight.toString(),
                                         asset,
                                         goals.getPortfolioList().get(i).getSymbol());
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
