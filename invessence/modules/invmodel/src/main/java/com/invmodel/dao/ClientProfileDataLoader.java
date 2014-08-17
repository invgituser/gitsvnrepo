package com.invmodel.dao;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.dao.data.ClientProfileData;
import org.apache.commons.dbutils.DbUtils;


public class ClientProfileDataLoader
{
   private static ClientProfileDataLoader instance = null;
   //private Map<String, ArrayList<String>> accountTypeList = new HashMap<String, ArrayList<String>>(); // Key=Asset, Value=TickerList;
   //private Map<String, ArrayList<String>> accountIdList = new HashMap<String, ArrayList<String>>(); // Key=SubAsset, Value=TickerList;
   private Map<String, ClientProfileData> clientList = new HashMap<String, ClientProfileData>(); //Key=Ticker, Value=Data;
   private final Logger logger = Logger.getLogger(ClientProfileDataLoader.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized ClientProfileDataLoader getInstance()
   {
      if (instance == null)
      {
         instance = new ClientProfileDataLoader();
      }

      return instance;
   }

   private ClientProfileDataLoader()
   {
      super();
      // TODO Auto-generated constructor stub
      loadDataFromDB();
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

   private void setClientProfileData(String name, String taxType, String goal,
                                     String IB_acctNum,  int age, int duration, int riskIndex,
                                     boolean stayInvested,double totalCapital, String dateCreated, double totalIncome)
   {
      try
      {
         if (!clientList.containsKey(IB_acctNum))
         {
            ClientProfileData clientId = new ClientProfileData(name, taxType, goal,
                                                     IB_acctNum, age, duration, riskIndex,
                                                     stayInvested, totalCapital, dateCreated, totalIncome);
            clientList.put(IB_acctNum, clientId);

         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void loadDataFromDB()
   {
      clientList.clear();
      //accountTypeList.clear();
      //accountIdList.clear();

      logger.info("Loading Securities from DB");
      Connection connection = null;
      Statement s = null;
      ResultSet rs = null;
      try
      {


         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         s = connection.createStatement();
         s.executeQuery(
            "\tSELECT\n" +
               "\tname,\n" +
               "\ttaxType,\n" +
               "\tgoal,\n" +
               "\tIB_acctNum,\n" +
               "\tage,\n" +
               "\thorizon,\n" +
               "\triskIndex,\n" +
               "\tstayInvested,\n" +
               "\ttotalCapital,\n" +
               "\tdateCreated,\n" +
               "\ttotalIncomeAnnulized\n"  +
               " FROM vw_account_rebalance_JV "
         );

         // Make sure to keep track of this position.

         rs = s.getResultSet();
         // get data row from table.
         while (rs.next())
         {
            setClientProfileData(rs.getString("name"), // String Client name
                                 rs.getString("taxType"), //
                                 rs.getString("goal"),  //
                                 rs.getString("IB_acctNum"), //
                                 rs.getInt("age"), //
                                 rs.getInt("horizon"),  //
                                 rs.getInt("riskIndex"), //
                                 rs.getBoolean("stayInvested") ,
                                 rs.getDouble("totalCapital"), //
                                 rs.getString("dateCreated") ,
                                 rs.getDouble("totalIncomeAnnulized")

            );

         }
         //System.out.println (count + " rows were retrieved");
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         DbUtils.closeQuietly(rs);
         DbUtils.closeQuietly(s);
         DbUtils.closeQuietly(connection);
      }
   }

   public Map<String, ClientProfileData> getClientAccountTypeList()
   {
      read.lock();
      try
      {

         Map<String, ClientProfileData> typeClientList = new HashMap<String, ClientProfileData>();
         for (String ibAcct: clientList.keySet())
         {
            //if (clientList.get(ibAcct).getTaxType().equalsIgnoreCase(accountType))
            typeClientList.put(ibAcct,clientList.get(ibAcct));
            //if (!clientList.get(ibAcct).getTaxType().equalsIgnoreCase(accountType))
            //   typeClientList.put(ibAcct,clientList.get(ibAcct));
         }
         return typeClientList;
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



}
