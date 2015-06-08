package com.invmodel.dao.invdb;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.DBConnectionProvider;
import com.invmodel.dao.data.SecurityData;
import org.apache.commons.dbutils.DbUtils;


public class SecurityCollection
{
   private Map<String, ArrayList<SecurityData>> seclistByKeyMap;            // Key: advisor, theme, primeasset
   private Map<String, SecurityData> listbyAdvisorPrimeassetMap; // Key: ticker , contains multiple by primeasset.
   private Map<String, SecurityData> listofOrderedSecurity;                 // Key: ticker

   private String themeLoaded;

   private final Logger logger = Logger.getLogger(SecurityCollection.class.getName());
   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public SecurityCollection()
   {
      super();
      seclistByKeyMap = new LinkedHashMap<String, ArrayList<SecurityData>>();
      listbyAdvisorPrimeassetMap = new LinkedHashMap<String, SecurityData>();
      listofOrderedSecurity = new LinkedHashMap<String, SecurityData>();
      themeLoaded = "";
   }

   private String getPrimaryKey(String advisor, String theme, String primeassetclass)
   {
      if (advisor == null || advisor.isEmpty()) {
         advisor = InvConst.INVESSENCE_ADVISOR;
      }

      if (theme == null || theme.length() == 0)
      {
         theme = InvConst.DEFAULT_THEME;
      }

      if (primeassetclass == null || primeassetclass.length() == 0)
      {
         primeassetclass = "DISCARD";
      }

      return (advisor.toUpperCase() +
         "." +
         theme.toUpperCase() +
         "." +
         primeassetclass.toUpperCase());

   }

   private String getSecondaryKey(String advisor, String primeassetclass, String ticker)
   {
      if (advisor == null || advisor.isEmpty()) {
         advisor = InvConst.INVESSENCE_ADVISOR;
      }

      if (primeassetclass == null || primeassetclass.length() == 0)
      {
         primeassetclass = "DISCARD";
      }

      return (advisor.toUpperCase() +
         "." +
         primeassetclass.toUpperCase() +
      "." +
         ticker.toUpperCase()
      );

   }

   private String getThirdKey(String ticker)
   {
      if (ticker == null || ticker.isEmpty())
      {
         ticker = "DISCARD";
      }

      return (ticker.toUpperCase());

   }

   private void setSecurityData(String advisor, String theme,
                               String ticker, String name,
                               String assetname, String primeassetclass,
                               String type, String style,
                               double dailyprice, int sortorder, double rbsaweight,
                               String assetcolor, String primeassetcolor,
                               String secAssetClass, String secSubAssetClass)
   {
      try
      {
         String primaryKey, secondaryKey, thirdKey;
         boolean addData;
         primaryKey = getPrimaryKey(advisor, theme, primeassetclass);
         secondaryKey = getSecondaryKey(advisor, primeassetclass, ticker);
         thirdKey = getThirdKey(ticker);
         SecurityData security = new SecurityData(advisor, theme, ticker, name,
                                                  assetname, primeassetclass,  type, style,
                                                  dailyprice, sortorder, rbsaweight,
                                                  assetcolor, primeassetcolor,
                                                  secAssetClass, secSubAssetClass);



         if (seclistByKeyMap.containsKey(primaryKey))
            seclistByKeyMap.get(primaryKey).add(security);
         else {
            ArrayList<SecurityData> sklist = new ArrayList<SecurityData>();
            sklist.add(security);
            seclistByKeyMap.put(primaryKey, sklist);
         }

         if ( !listbyAdvisorPrimeassetMap.containsKey(secondaryKey)) {
            listbyAdvisorPrimeassetMap.put(secondaryKey, security);
         }

         if (! listofOrderedSecurity.containsKey(thirdKey)) {
            listofOrderedSecurity.put(thirdKey, security);
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public void loadDataFromDB(String advisor, String theme)
   {
      if (theme == null || theme.isEmpty())
         theme = InvConst.DEFAULT_THEME;

      doSQLQuery(advisor, theme);
   }

   public void doSQLQuery(String advisor, String theme) {

      //logger.info("Loading Advisor Security from DB");
      seclistByKeyMap.clear();
      listbyAdvisorPrimeassetMap.clear();
      listofOrderedSecurity.clear();
      themeLoaded = "";

      Connection connection = null;
      Statement s = null;
      ResultSet rs = null;
      Boolean addedCash = false;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         s = connection.createStatement();
         s.executeQuery(
            "SELECT advisor," +
               "    theme," +
               "    assetclass," +
               "    primeassetclass," +
               "    ticker," +
               "    name," +
               "    type," +
               "    style," +
               "    price," +
               "    rbsaweight," +
               "    sortorder," +
               "    assetcolor," +
               "    primeassetcolor," +
               "    secAssetClass," +
               "    secSubAssetClass" +
               "   FROM invdb.vw_securities_by_theme " +
               "   WHERE theme = '" + theme +" '" +
               "   AND advisor = '" + advisor +" '" +
               "   ORDER BY sortorder"
          );

         // Make sure to keep track of this position.

         rs = s.getResultSet();
         // get data row from table.
         while (rs.next())
         {
            String ticker =  rs.getString("ticker");

            if (ticker.toUpperCase().equals("CASH")) // Mark Cash processed
               addedCash = true;

            setSecurityData(
               rs.getString("advisor"), // String advisor
               rs.getString("theme"), // String theme
               rs.getString("ticker"), // String ticker
               rs.getString("name"),  // String name
               rs.getString("assetclass"),  // String assetclass
               rs.getString("primeassetclass"),  // String primeassetclass
               rs.getString("type"),  // String type
               rs.getString("style"),  // String style
               rs.getDouble("price"), // double dailyprice
               rs.getInt("sortorder"), // double sortorder
               rs.getDouble("rbsaweight"), // double weight (RBSA)
               rs.getString("assetcolor"),  // String assetcolor
               rs.getString("primeassetcolor"),  // String primeassetcolor
               rs.getString("secAssetClass"),  // String primeassetcolor
               rs.getString("secSubAssetClass")  // String primeassetcolor
            );

         }

         // For every theme, make sure to add Cash element.
         if (! addedCash)
            setSecurityData(
               advisor, // String groupname
               theme, // String theme
               "Cash", // String ticker
               "Cash",  // String name
               "Cash",  // String assetclass
               "Cash",  // String primeassetclass
               "Cash",  // String type
               "Cash",  // String style
               1.00, // double dailyprice
               99999, // double sortorder
               1.0, // double weight (RBSA)
               "#ffffff",  // assetcolor
               "#ffffff",   // primeassetcolor
               "Cash",  // String secAssetClass
               "Cash"  // String secSubAssetClass
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

   private String buildTickersString(ArrayList<String> tickers, String seperator) {
      String tickerList = "";
      try {

         int tickercount=0;
         for (String ticker : tickers) {
            if (ticker.toUpperCase().equals("CASH"))
               continue;

            if (tickercount == 0)
               tickerList += "'" + ticker.trim() + "'";
            else
               tickerList += seperator.trim() + " '" + ticker.trim() + "'";
            tickercount++;

            if (tickercount > 99)
               break;
         }
      }
      catch (Exception ex) {

      }
      return tickerList;
   }

   public void doCustomSQLQuery(String advisor, String theme, ArrayList<String> tickers) {

      //logger.info("Loading Advisor Security from DB");
      seclistByKeyMap.clear();
      listbyAdvisorPrimeassetMap.clear();
      listofOrderedSecurity.clear();
      themeLoaded = "";

      Connection connection = null;
      Statement s = null;
      ResultSet rs = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         s = connection.createStatement();

         String tickerQueryList = buildTickersString(tickers, ",");
         String tickerWhere = "";
         if ((tickerQueryList != null) && (! tickerQueryList.isEmpty())) {
            tickerWhere = " AND ticker in (" + tickerQueryList + ") ";
         }
         else
            return;

         s.executeQuery(
            "SELECT advisor," +
               "     " + theme + "' as theme," +
               "    assetclass," +
               "    primeassetclass," +
               "    ticker," +
               "    name," +
               "    type," +
               "    style," +
               "    price," +
               "    rbsaweight," +
               "    sortorder," +
               "    assetcolor," +
               "    primeassetcolor," +
               "    secAssetClass," +
               "    secSubAssetClass" +
               "   FROM invdb.vw_sec_rbsa_master " +
               "   WHERE advisor = '" + advisor + "'" +
               "  "+ tickerWhere + "'\n" +
               "   ORDER BY sortorder"
         );


         // Make sure to keep track of this position.

         rs = s.getResultSet();

         // get data row from table.
         while (rs.next())
         {
            String ticker =  rs.getString("ticker");

            setSecurityData(
               rs.getString("advisor"), // String advisor
               rs.getString("theme"), // String theme
               rs.getString("ticker"), // String ticker
               rs.getString("name"),  // String name
               rs.getString("assetclass"),  // String assetclass
               rs.getString("primeassetclass"),  // String primeassetclass
               rs.getString("type"),  // String type
               rs.getString("style"),  // String style
               rs.getDouble("price"), // double dailyprice
               rs.getInt("sortorder"), // double sortorder
               rs.getDouble("rbsaweight"), // double weight (RBSA)
               rs.getString("assetcolor"),  // String assetcolor
               rs.getString("primeassetcolor"),  // String primeassetcolor
               rs.getString("secAssetClass"),  // String primeassetcolor
               rs.getString("secSubAssetClass")  // String primeassetcolor
            );
         }
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

   public ArrayList<SecurityData> getOrderedSecurityList()
   {
      read.lock();
      ArrayList<SecurityData> secOrderedList = new ArrayList<SecurityData>();
      try
      {
         for (String key: listofOrderedSecurity.keySet())
            secOrderedList.add(listofOrderedSecurity.get(key));
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
      return secOrderedList;
   }

   public ArrayList<SecurityData> getOrderedSecurityList(String advisor, String theme, String primeassetclass)
   {
      read.lock();
      String key;
      try
      {
         key = getPrimaryKey(advisor, theme, primeassetclass);
         return seclistByKeyMap.get(key);
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
         String key = getThirdKey(ticker);
         return listofOrderedSecurity.get(key);
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

   public SecurityData getSecurity(String advisor, String primeassetclass, String ticker)
   {
      read.lock();
      try
      {
         String key = getSecondaryKey(advisor, primeassetclass, ticker);
         return listbyAdvisorPrimeassetMap.get(key);
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
