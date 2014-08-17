package com.invmodel.dao;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.data.*;
import org.apache.commons.dbutils.DbUtils;


public class AssetDBCollection implements Serializable
{
   private Map<String, ArrayList<AssetData>> advisorlist = new HashMap<String, ArrayList<AssetData>>();
   private Integer maxAsset = 1;
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
   }

   private String getAssetKey(String groupname) {
      String groupkey;
      if (groupname == null)
         groupkey = InvConst.DEFAULT_ADVISOR;
      else
         groupkey = groupname.toUpperCase();

      return groupkey;
   }

   private void add2AssetList(String groupname, AssetData data) {
      ArrayList<AssetData> savedData;
      String key;
      Integer numofasset;
      try {
         key = getAssetKey(groupname);
         if (advisorlist.containsKey(key)) {
            savedData = advisorlist.get(key);
            savedData.add(data);
            numofasset = savedData.size();
            maxAsset = (numofasset > maxAsset) ? numofasset : maxAsset;
            advisorlist.put(key,savedData);
         }
         else {
            savedData = new ArrayList<AssetData>();
            savedData.add(data);
            numofasset = savedData.size();
            maxAsset = (numofasset > maxAsset) ? numofasset : maxAsset;
            advisorlist.put(key,savedData);
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
         return advisorlist.get(key);
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
         if (advisorlist.containsKey(key)) {
            String[] oAsset = new String[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               asset = advisorlist.get(key).get(i).getAsset();
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
                                              end_allocation
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
      advisorlist.clear();

      logger.info("Loading ASSET information from DB");
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         statement.executeQuery("SELECT groupname, assetclass, description, assetLevel, " +
                                "sortOrder, lowerBound, upperBound, color, " +
                                "indexFund, averageReturn, riskAdjustment, endAllocation " +
                                "FROM vw_assetmapping_group " +
                                "order by groupname, AssetLevel, sortOrder");

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();
         while (resultSet.next())
         {
            switch (resultSet.getInt("assetLevel"))
            {
               case 1:
                     setAssetdata(resultSet.getString("groupname"),
                                  resultSet.getString("assetclass"),
                                  resultSet.getString("description"),
                                  resultSet.getDouble("lowerBound"),
                                  resultSet.getDouble("upperBound"),
                                  resultSet.getString("indexFund"),
                                  resultSet.getDouble("averageReturn"),
                                  resultSet.getString("color"),
                                  resultSet.getDouble("riskAdjustment"),
                                  resultSet.getDouble("endAllocation")
                                  );
                     break;
               default:
                  break;

            }
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
         if (advisorlist.containsKey(key)) {
            double[] value = new double[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               value[i] = advisorlist.get(key).get(i).getLbConstraint();
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
         if (advisorlist.containsKey(key)) {
            double[] value = new double[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               value[i] = advisorlist.get(key).get(i).getUbConstraint();
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
         if (advisorlist.containsKey(key)) {
            double[] value = new double[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               value[i] = advisorlist.get(key).get(i).getAverageReturn();
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
         if (advisorlist.containsKey(key)) {
            String[] color = new String[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               color[i] = advisorlist.get(key).get(i).getColor();
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
         if (advisorlist.containsKey(key)) {
            String[] index = new String[advisorlist.get(key).size()];
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               index[i] = advisorlist.get(key).get(i).getIndex();
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
         if (advisorlist.containsKey(key)) {
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               if (advisorlist.get(key).get(i).getAsset().equalsIgnoreCase(asset)) {
                  risk = advisorlist.get(key).get(i).getRisk_adjustment();
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
         if (advisorlist.containsKey(key)) {
            for (int i = 0; i < advisorlist.get(key).size(); i++)
            {
               if (advisorlist.get(key).get(i).getAsset().equalsIgnoreCase(asset)) {
                  risk = advisorlist.get(key).get(i).getEnd_allocation();
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


}
