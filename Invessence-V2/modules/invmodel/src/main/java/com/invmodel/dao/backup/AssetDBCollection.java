package com.invmodel.dao.backup;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.*;
import com.invmodel.dao.data.*;
import com.invmodel.dao.invdb.DailyReturns;
import org.apache.commons.dbutils.DbUtils;
import webcab.lib.finance.portfolio.*;



public class AssetDBCollection implements Serializable
{
   private Map<String, String> advisors = new HashMap<String, String>();
   private Map<String, ArrayList<AssetData>> advisorMap = new HashMap<String, ArrayList<AssetData>>();
   private static AssetDBCollection instance = null;
   private final Logger logger = Logger.getLogger(AssetDBCollection.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized AssetDBCollection getInstance()
   {
      if (instance == null)
      {
         instance = new AssetDBCollection();
      }

      return instance;
   }

   private AssetDBCollection()
   {
      super();
      loadDataFromDB();
      saveWeights();
   }

   private String getAssetKey(String groupname) {
      String groupkey;
      if (groupname == null)
         groupkey = InvConst.INVESSENCE_ADVISOR;
      else
         groupkey = groupname.toUpperCase();

      return groupkey;
   }

   private void add2AssetList(String groupname, AssetData data) {
      ArrayList<AssetData> savedData;
      String key, assetname;
      Integer numofasset;
      try {
         groupname = getAssetKey(groupname);
         assetname = data.getAsset();

         if (assetname == null || assetname.length() == 0)
            assetname = "Default";

         if (! advisors.containsKey(groupname))
            advisors.put(groupname,groupname);

         key = groupname + "." + assetname;
         if (advisorMap.containsKey(groupname)) {
            savedData = advisorMap.get(groupname);
            savedData.add(data);
            numofasset = savedData.size();
            advisorMap.put(groupname, savedData);
         }
         else {
            savedData = new ArrayList<AssetData>();
            savedData.add(data);
            numofasset = savedData.size();
            advisorMap.put(groupname, savedData);
         }
      }
      catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   public void refreshDataFromDB()
   {
      write.lock();
      try
      {
         loadDataFromDB();
         saveWeights();
      }
      finally
      {
         write.unlock();
      }
   }

   public ArrayList<AssetData> getAssetlist(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         return advisorMap.get(key);
      }
      finally
      {
         read.unlock();
      }
   }

   public String[] getOrderedAsset(String groupname)
   {
      String key,asset;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            String[] oAsset = new String[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               asset = advisorMap.get(key).get(i).getAsset();
               oAsset[i] = asset;
            }
            return oAsset;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }


   private void setAssetdata(String groupname, String asset, String assetName, double lbConstraint, double ubConstraint,
                             String index, double averageReturn,
                             String color, double risk_adjustment, double end_allocation)
   {
      try
      {
         AssetData data = new AssetData(groupname,
                                              asset,
                                              assetName,
                                              lbConstraint,
                                              ubConstraint,
                                              index,
                                              averageReturn,
                                              color,
                                              risk_adjustment,
                                              end_allocation,
                                              0
                                              );
         add2AssetList(groupname, data);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }


   private void loadDataFromDB()
   {
      advisorMap.clear();

      logger.info("Loading ASSET information from DB");
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT theme, assetclass, displayName, " +
                                "sortOrder, lowerBound, upperBound, color, " +
                                "ticker, averageReturn, riskAdjustment, endAllocation " +
                                "FROM vw_assetmapping_group " +
                                "order by theme, sortOrder");

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            //switch (resultSet.getInt("assetLevel"))
            //{
            //   case 1:
                     setAssetdata(resultSet.getString("theme"),
                                  resultSet.getString("assetclass"),
                                  resultSet.getString("displayName"),
                                  resultSet.getDouble("lowerBound"),
                                  resultSet.getDouble("upperBound"),
                                  resultSet.getString("ticker"),
                                  resultSet.getDouble("averageReturn"),
                                  resultSet.getString("color"),
                                  resultSet.getDouble("riskAdjustment"),
                                  resultSet.getDouble("endAllocation")
                                  );
           //          break;
           //    default:
           //       break;

           // }
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         DbUtils.closeQuietly(resultSet);
         DbUtils.closeQuietly(statement);
         DbUtils.closeQuietly(connection);
      }
   }

   public double[] getAssetOrderedLowerBound(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            double[] value = new double[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               value[i] = advisorMap.get(key).get(i).getLbConstraint();
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public double[] getAssetOrderedUpperBound(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            double[] value = new double[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               value[i] = advisorMap.get(key).get(i).getUbConstraint();
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }


   public double[] getAssetOrderedAvgReturns(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            double[] value = new double[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               value[i] = advisorMap.get(key).get(i).getAverageReturn();
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public double[] getAssetOrderedWeight(String groupname, int offset)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            double[] value = new double[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               value[i] =  advisorMap.get(key).get(i).getWeight(offset);
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }


   public double[][] getAssetOrderedWeightArray(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            double[][] value = new double[advisorMap.get(key).size()][InvConst.ASSET_INTERPOLATION];

            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               for(int j = 0; j < InvConst.ASSET_INTERPOLATION; j++)
               {

                  value[i][j] =  advisorMap.get(key).get(i).getWeight(j);
               }
            }
            return value;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public String[] getAssetOrderedColor(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            String[] color = new String[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               color[i] = advisorMap.get(key).get(i).getColor();
            }
            return color;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public String[] getAssetOrderedIndex(String groupname)
   {
      String key;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            String[] index = new String[advisorMap.get(key).size()];
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               index[i] = advisorMap.get(key).get(i).getIndex();
            }
            return index;
         }
         else
            return null;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return null;
   }

   public double getRiskAdjustment(String groupname, String asset)
   {
      String key;
      double risk = 0.0;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               if (advisorMap.get(key).get(i).getAsset().equalsIgnoreCase(asset)) {
                  risk = advisorMap.get(key).get(i).getRisk_adjustment();
                  break;
               }
            }
            return risk;
         }
         else
            return 0.0;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return 0.0;
   }

   public double getEnd_allocation(String groupname, String asset)
   {
      String key;
      double risk = 0.0;
      read.lock();
      try
      {
         key = getAssetKey(groupname);
         if (advisorMap.containsKey(key)) {
            for (int i = 0; i < advisorMap.get(key).size(); i++)
            {
               if (advisorMap.get(key).get(i).getAsset().equalsIgnoreCase(asset)) {
                  risk = advisorMap.get(key).get(i).getEnd_allocation();
                  break;
               }
            }
            return risk;
         }
         else
            return 0.0;
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return 0.0;
   }

   private void saveWeights()
   {
      try
      {
         String[] asset = null;
         DailyReturns dailyReturns = DailyReturns.getInstance();
         CapitalMarket instanceOfCapitalMarket = new CapitalMarket();
         AssetParameters assetParameters = new AssetParameters();
         if(advisorMap.size() > 0) {
            for (String advisor : advisorMap.keySet() ) {

               String[] indexFund = getAssetOrderedIndex(advisor);
               double[][] histReturns = dailyReturns.getDailyReturnsArray(indexFund);
               if (histReturns != null)
               {
                  //double[] expectedReturnsOfFunds = assetParameters.expectedReturns(histReturns);
                  double[][] covarianceOfFunds = assetParameters.covarianceMatrix(histReturns);

                  // Here we evaluate the maximum expected return  of  the  Portfolio's  on
                  // the Efficient Frontier.

                  double[] lowerBound = getAssetOrderedLowerBound(advisor);
                  double[] upperBound = getAssetOrderedUpperBound(advisor);

                  instanceOfCapitalMarket.setConstraints(lowerBound, upperBound);

                  double[] yield = getAssetOrderedAvgReturns(advisor);
                  double minReturn1 = instanceOfCapitalMarket.minFrontierReturn(yield);
                  double maxReturn1 = instanceOfCapitalMarket.maxFrontierReturn(yield);
                  instanceOfCapitalMarket.calculateEfficientFrontier(
                     minReturn1, // minimumExpectedReturn
                     maxReturn1, // maximumExpectedReturn
                     covarianceOfFunds,//Covariance matrix
                     yield, // expectedReturns
                     InvConst.ASSET_INTERPOLATION, // numberInterpolationPoints
                     InvConst.ASSET_PRECISION  // precision
                  );

                  //double[] risk1 = instanceOfCapitalMarket.getEfficientFrontierPortfolioRisks( covarianceOfFunds);
                  double[][] weights = instanceOfCapitalMarket.getEfficientFrontierAssetWeights();

                  if (weights.length > 0) {
                     asset = getOrderedAsset(advisor);
                     int count=0;
                     for (String assetname : asset) {
                        String key = advisor.toUpperCase() + "." + assetname.toUpperCase();
                        AssetData adata = advisorMap.get(advisor).get(count);
                        double[] wght = new double[weights.length];
                        for (int i=0; i < weights.length; i++)
                           wght[i] = weights[i][count];

                        adata.setWeights(wght);
                        count++;
                     }
                  }

               }
            }
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }

   public ArrayList<String> getAdvisors()
   {
      ArrayList<String> advisorList = new ArrayList<String>();
      for (String advisorName: advisors.keySet())
         advisorList.add(advisorName);
      return advisorList;
   }

}
