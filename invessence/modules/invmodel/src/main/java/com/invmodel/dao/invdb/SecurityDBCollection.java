package com.invmodel.dao.invdb;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.DBConnectionProvider;
import com.invmodel.dao.data.SecurityData;
import org.apache.commons.dbutils.DbUtils;


public class SecurityDBCollection
{
   private Map<String, ArrayList<SecurityData>> securityPrimeAssetMap;
   private Map<String, SecurityData> securityMap;
   private ArrayList<String> orderedsecurityList;
   private String themeLoaded;

   private final Logger logger = Logger.getLogger(SecurityDBCollection.class.getName());
   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public SecurityDBCollection()
   {
      super();
      securityPrimeAssetMap = new HashMap<String, ArrayList<SecurityData>>(); // see getKey for KEY
      securityMap = new HashMap<String, SecurityData>(); // KEY = ticker
      orderedsecurityList = new ArrayList<String>();
      themeLoaded = "";
   }

   private String getKey(String theme, String assetname, String primeassetclass)
   {
      if (theme == null || theme.length() == 0)
      {
         theme = InvConst.DEFAULT_THEME;
      }
      if (assetname == null || assetname.length() == 0)
      {
         assetname = InvConst.DEFAULT_THEME;
      }
      if (primeassetclass == null || primeassetclass.length() == 0)
      {
         primeassetclass = "DISCARD";
      }
/*
      return (theme.toUpperCase() +
         "." +
         assetname.toUpperCase() +
         "." +
         primeassetclass.toUpperCase());
*/

      return (theme.toUpperCase() +
         "." +
         primeassetclass.toUpperCase());

   }

   private void setSecurityData(String groupname, String theme,
                               String ticker, String name,
                               String assetname, String primeassetclass,
                               String subassetname, String type, String style,
                               double dailyprice, double expectedReturn, double expenseRatio,
                               double riskSTD, double yield, int sortorder, double rbsaweight)
   {
      try
      {
         String groupkey, key;
         boolean addData;
         key = getKey(theme, assetname, primeassetclass);
         SecurityData security = new SecurityData(0L, ticker, name,
                                                  assetname, primeassetclass, subassetname, type, style,
                                                  dailyprice, expenseRatio, 0.0,
                                                  0.0, 0.0, riskSTD,
                                                  0.0, expectedReturn, 0.0,
                                                  0.0, yield, sortorder, rbsaweight);

         ticker = ticker.toUpperCase();
         // For every unique key add Security and ordered List.
         if (!securityPrimeAssetMap.containsKey(key)) {
            ArrayList<SecurityData> securityList = new ArrayList<SecurityData>();
            securityList.add(security);
            securityPrimeAssetMap.put(key, securityList);

            orderedsecurityList.add(key);
            themeLoaded = theme;
         }
         else {
            // Don't add the same security.
            // if (!securityMap.containsKey(ticker))
               securityPrimeAssetMap.get(key).add(security);
         }

         if (! securityMap.containsKey(ticker))
            securityMap.put(ticker,security);

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void loadDataFromDB(String theme)
   {
      if (theme == null || theme.length() == 0)
         theme = InvConst.DEFAULT_THEME;

      securityPrimeAssetMap.clear();
      orderedsecurityList.clear();
      themeLoaded = "";
      doSQLQuery(theme);

      if (securityPrimeAssetMap.size() == 0  && ! theme.equalsIgnoreCase(InvConst.DEFAULT_THEME)) {
         doSQLQuery(InvConst.DEFAULT_THEME);
      }
   }

   public void doSQLQuery(String theme) {

      //logger.info("Loading Advisor Security from DB");
      Connection connection = null;
      Statement s = null;
      ResultSet rs = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         s = connection.createStatement();
         s.executeQuery(
            "SELECT theme,\n" +
               "       assetclass,\n" +
               "       primeassetclass,\n" +
               "       status,\n" +
               "       ticker,\n" +
               "       cusip,\n" +
               "       isin,\n" +
               "       name,\n" +
               "       type,\n" +
               "       style,\n" +
               "       subclass,\n" +
               "       expenseRatio,\n" +
               "       nontaxableReturn,\n" +
               "       adv3months,\n" +
               "       aum,\n" +
               "       beta,\n" +
               "       securityRiskSTD,\n" +
               "       yield,\n" +
               "       price,\n" +
               "       rbsaweight,\n" +
               "       sortorder\n" +
               "   FROM vw_rbsa_securities \n" +
               "   WHERE theme = '" + theme +" '\n" +
               "   ORDER BY 1, 2, 3"
          );

         // Make sure to keep track of this position.

         rs = s.getResultSet();
         // get data row from table.
         while (rs.next())
         {
            setSecurityData(
               null, // String groupname
               rs.getString("theme"), // String theme
               rs.getString("ticker"), // String ticker
               rs.getString("name"),  // String name
               rs.getString("assetclass"),  // String assetclass
               rs.getString("primeassetclass"),  // String primeassetclass
               rs.getString("subclass"), // String subclass
               rs.getString("type"),  // String type
               rs.getString("style"),  // String style
               rs.getDouble("price"), // double dailyprice
               rs.getDouble("nontaxableReturn"), // double nontaxableReturn
               rs.getDouble("expenseRatio"), // double expenseRatio
               rs.getDouble("securityRiskSTD"), // double securityRiskSTD
               rs.getDouble("yield"), // double yield
               rs.getInt("sortorder"), // double sortorder
               rs.getDouble("rbsaweight") // double weight (RBSA)
            );

         }

         // For every theme, make sure to add Cash element.
         setSecurityData(
            null, // String groupname
            theme, // String theme
            "Cash", // String ticker
            "Cash",  // String name
            "Cash",  // String assetclass
            "Cash",  // String primeassetclass
            "Cash", // String subclass
            "Cash",  // String type
            "Cash",  // String style
            1.00, // double dailyprice
            0.0, // double nontaxableReturn
            0.0, // double expenseRatio
            0.0, // double securityRiskSTD
            0.0, // double yield
            99999, // double sortorder
            1.0 // double weight (RBSA)
         );

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

   public String getThemeLoaded()
   {
      return themeLoaded;
   }

   public ArrayList<SecurityData> getOrderedSecurityList() {
      read.lock();
      ArrayList<SecurityData> securityList = new ArrayList<SecurityData>();
      try {
         for (String key : orderedsecurityList) {
            for (SecurityData sd: securityPrimeAssetMap.get(key))
               securityList.add(sd);
         }
         return securityList;

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return securityList;
   }

   public ArrayList<SecurityData> getOrderedSecurityList(String theme, String assetname, String primeassetclass)
   {
      read.lock();
      String key;
      try
      {
         key = getKey(theme, assetname, primeassetclass);
         return securityPrimeAssetMap.get(key);
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

   public SecurityData getSecurity(String ticker)
   {
      read.lock();
      try
      {
         return securityMap.get(ticker.toUpperCase());
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

   public Double getDailyprice(String ticker)
   {
      read.lock();
      try
      {
         return securityMap.get(ticker.toUpperCase()).getDailyprice();
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
