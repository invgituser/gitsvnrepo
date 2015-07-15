package com.invessence.data.advisor;

import java.util.*;
import javax.faces.bean.*;

import com.invessence.data.common.CustomerData;
import com.invmodel.portfolio.data.PortfolioSubclass;
import org.primefaces.model.*;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 7/28/14
 * Time: 9:25 AM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "treeAssetFilter")
@SessionScoped
public class TreeAssetFilter
{
   Map<String, TreeNode> assetTreeNodeMap = new HashMap<String, TreeNode>();
   Map<String, TreeNode> childTreeNodeMap = new HashMap<String, TreeNode>();

   public TreeNode createNewTreeNodes(AdvisorData advisordata)
   {
      TreeNode root;
      root = new DefaultTreeNode(new AssetClassFilter("DataKey","Assets", null, null, null, null, null, null), null);
      try
      {
         AssetClassFilter assetclassfilter;
         PortfolioSubclass pfsubclass;
         TreeNode topLevel;
         TreeNode sublevel;
         Double totalValue;
         Integer datayear = advisordata.getAssetyear();

         for (int loop=0; loop < advisordata.getAssetData()[advisordata.getAssetyear()].getOrderedAsset().size(); loop++) {
            String name = advisordata.getAssetData()[datayear].getOrderedAsset().get(loop);
            Double weight = advisordata.getAssetData()[datayear].getAsset(name).getUserweight();
            String color = advisordata.getAssetData()[datayear].getAsset(name).getColor();
            Double risk = advisordata.getAssetData()[datayear].getAsset(name).getRisk();
            Double expectedReturn = advisordata.getAssetData()[datayear].getAsset(name).getExpectedReturn();
            totalValue = 0.0;
            assetclassfilter = new AssetClassFilter(name, name, null, color, weight, null, risk, expectedReturn);
            ArrayList<String> subclasslist = advisordata.getPortfolioData()[datayear].getAssetMapList(name);
            if (subclasslist != null) {
               topLevel =  new DefaultTreeNode(assetclassfilter, root);
               String subclassname;
               if (subclasslist.size() == 1) {
                  subclassname = subclasslist.get(0);
                  String pfsubkey = advisordata.getPortfolioData()[datayear].getsubclasskey(name,subclassname);
                  pfsubclass = advisordata.getPortfolioData()[datayear].getSubclassMap().get(pfsubkey);
                  if (pfsubclass != null)
                     totalValue = totalValue + pfsubclass.getMoney();
               }
               else {
                  for (int secondloop = 0; secondloop < subclasslist.size(); secondloop++) {
                     subclassname = subclasslist.get(secondloop);
                     String pfsubkey = advisordata.getPortfolioData()[datayear].getsubclasskey(name,subclassname);
                     pfsubclass = advisordata.getPortfolioData()[datayear].getSubclassMap().get(pfsubkey);

                     if (pfsubclass != null) {
                        sublevel = new DefaultTreeNode(
                           new AssetClassFilter(pfsubkey, pfsubclass.getSubasset(), name, pfsubclass.getColor(),
                                                pfsubclass.getWeight(), pfsubclass.getMoney(), null, null),
                           topLevel);
                        totalValue = totalValue + pfsubclass.getMoney();
                     }
                  }
               }
               totalValue = Math.round(totalValue * 100.00) / 100.00;
               assetclassfilter.setValue(totalValue);
            }
         }

      }
      catch (Exception ex)
      {
        ex.printStackTrace();
      }
      return root;
   }

   public TreeNode loadFilteredTreeNodes(CustomerData mgoal)
   {
      TreeNode root;
      root = new DefaultTreeNode(new AssetClassFilter("DataKey","Assets", null, null, null, null, null, null), null);
      try
      {
         AssetClassFilter assetclassfilter;
         PortfolioSubclass pfsubclass;
         TreeNode topLevel;
         TreeNode sublevel;
         Double totalValue;

         for (int loop=0; loop < mgoal.getAssetData()[mgoal.getAssetyear()].getOrderedAsset().size(); loop++) {
            String name = mgoal.getAssetData()[mgoal.getAssetyear()].getOrderedAsset().get(loop);
            Double weight = mgoal.getAssetData()[mgoal.getAssetyear()].getAsset(name).getUserweight();
            String color = mgoal.getAssetData()[mgoal.getAssetyear()].getAsset(name).getColor();
            Double risk = mgoal.getAssetData()[mgoal.getAssetyear()].getAsset(name).getRisk();
            Double expectedReturn = mgoal.getAssetData()[mgoal.getAssetyear()].getAsset(name).getExpectedReturn();
            totalValue = 0.0;
            assetclassfilter = new AssetClassFilter(name, name, null, color, weight, null, risk, expectedReturn);
            ArrayList<String> subclasslist = mgoal.getPortfolioData()[mgoal.getAssetyear()].getAssetMapList(name);
            if (subclasslist != null) {
               topLevel =  new DefaultTreeNode(assetclassfilter, root);
               String subclassname;
               if (subclasslist.size() == 1) {
                  subclassname = subclasslist.get(0);
                  String pfsubkey = mgoal.getPortfolioData()[mgoal.getAssetyear()].getsubclasskey(name,subclassname);
                  pfsubclass = mgoal.getPortfolioData()[mgoal.getAssetyear()].getSubclassMap().get(pfsubkey);
                  if (pfsubclass != null)
                     totalValue = totalValue + pfsubclass.getMoney();
               }
               else {
                  for (int secondloop = 0; secondloop < subclasslist.size(); secondloop++) {
                     subclassname = subclasslist.get(secondloop);
                     String pfsubkey = mgoal.getPortfolioData()[mgoal.getAssetyear()].getsubclasskey(name,subclassname);
                     pfsubclass = mgoal.getPortfolioData()[mgoal.getAssetyear()].getSubclassMap().get(pfsubkey);

                     if (pfsubclass != null) {
                        sublevel = new DefaultTreeNode(
                           new AssetClassFilter(pfsubkey, pfsubclass.getSubasset(), name, pfsubclass.getColor(),
                                                pfsubclass.getWeight(), pfsubclass.getMoney(), null, null),
                           topLevel);
                        totalValue = totalValue + pfsubclass.getMoney();
                     }
                  }
               }
               assetclassfilter.setValue(totalValue);
            }
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      return root;
   }
}
