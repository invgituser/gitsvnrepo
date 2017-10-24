package com.invmodel.dao.invdb;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.invessence.converter.*;
import com.invessence.converter.SQLData;
import com.invmodel.Const.InvConst;
import com.invmodel.dao.*;
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

   private String getKeyByAdvisorThemePrimeAsset(String advisor, String theme, String primeassetclass)
   {
      if (advisor == null || advisor.isEmpty())
      {
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

   private String getKeyByAdvisorPrimeasset(String advisor, String primeassetclass)
   {
      if (advisor == null || advisor.isEmpty())
      {
         advisor = InvConst.INVESSENCE_ADVISOR;
      }

      if (primeassetclass == null || primeassetclass.length() == 0)
      {
         primeassetclass = "DISCARD";
      }

      return (advisor.toUpperCase() +
         "." +
         primeassetclass.toUpperCase()
      );

   }

   private String getKeyByTicker(String ticker)
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
                                String secAssetClass, String secSubAssetClass,
                                String isin, String cusip, String ric,
                                String tradeCurrency, String settleCurrency, Double exchangeRate,
                                Double settlePrice)
   {
      try
      {
         String primaryKey, secondaryKey, thirdKey;
         boolean addData;
         primaryKey = getKeyByAdvisorThemePrimeAsset(advisor, theme, primeassetclass);
         secondaryKey = getKeyByAdvisorPrimeasset(advisor, primeassetclass);
         thirdKey = getKeyByTicker(ticker);
         SecurityData security = new SecurityData(advisor, theme, ticker, name,
                                                  assetname, primeassetclass, type, style,
                                                  dailyprice, sortorder, rbsaweight,
                                                  assetcolor, primeassetcolor,
                                                  secAssetClass, secSubAssetClass,
                                                  isin, cusip, ric,
                                                  tradeCurrency, settleCurrency, exchangeRate, settlePrice);


         if (seclistByKeyMap.containsKey(primaryKey))
         {
            seclistByKeyMap.get(primaryKey).add(security);
         }
         else
         {
            ArrayList<SecurityData> sklist = new ArrayList<SecurityData>();
            sklist.add(security);
            seclistByKeyMap.put(primaryKey, sklist);
         }

         if (!listbyAdvisorPrimeassetMap.containsKey(secondaryKey))
         {
            listbyAdvisorPrimeassetMap.put(secondaryKey, security);
         }

         if (!listofOrderedSecurity.containsKey(thirdKey))
         {
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
      {
         theme = InvConst.DEFAULT_THEME;
      }

      doSQLQuery(advisor, theme);
   }

   public void doSQLQuery(String advisor, String theme)
   {


      DBConnectionProvider dbconnection;
      SQLData convert;
      DataSource ds;

      try
      {
         dbconnection = DBConnectionProvider.getInstance();
         convert = new com.invessence.converter.SQLData();
         ds = dbconnection.getMySQLDataSource();
         read.lock();
         String storedProcName = "invdb.sel_securities_by_theme";
         InvModelSP sp = new InvModelSP(ds, storedProcName, 7, 99);

         //logger.info("Loading Advisor Security from DB");
         seclistByKeyMap.clear();
         listbyAdvisorPrimeassetMap.clear();
         listofOrderedSecurity.clear();
         themeLoaded = theme;
         Boolean addedCash = false;
         String defaultCurrency = null;

         ArrayList<Map<String, Object>> rows;
         Map outMap = sp.collectSecurityData(advisor, theme);
         if (outMap != null)
         {
            rows = (ArrayList<Map<String, Object>>) outMap.get("#result-set-1");
            if (rows != null)
            {
               Integer i = 0;
               for (Map<String, Object> map : rows)
               {
                  Map rs = (Map) rows.get(i);
                  String ticker = convert.getStrData(rs.get("ticker"));

                  if (ticker.toUpperCase().equals("CASH")) // Mark Cash processed
                  {
                     addedCash = true;
                  }

                  if (defaultCurrency == null) {
                     // Since converts all the data to destCurrency, we set as default. For default Cash.
                     defaultCurrency = convert.getStrData(rs.get("destCurrency"));
                  }

                  setSecurityData(
                     convert.getStrData(rs.get("advisor")), // String advisor
                     convert.getStrData(rs.get("theme")), // String theme
                     convert.getStrData(rs.get("ticker")), // String ticker
                     convert.getStrData(rs.get("name")),  // String name
                     convert.getStrData(rs.get("assetclass")),  // String assetclass
                     convert.getStrData(rs.get("primeassetclass")),  // String primeassetclass
                     convert.getStrData(rs.get("type")),  // String type
                     convert.getStrData(rs.get("style")),  // String style
                     convert.getDoubleData(rs.get("price")), // double dailyprice
                     convert.getIntData(rs.get("sortorder")), // double sortorder
                     convert.getDoubleData(rs.get("rbsaweight")), // double weight (RBSA)
                     convert.getStrData(rs.get("assetcolor")),  // String assetcolor
                     convert.getStrData(rs.get("primeassetcolor")),  // String primeassetcolor
                     convert.getStrData(rs.get("secAssetClass")),  // String secAssetClass
                     convert.getStrData(rs.get("secSubAssetClass")),  // String secSubAssetClass
                     convert.getStrData(rs.get("isin")),  // String isin
                     convert.getStrData(rs.get("cusip")),  // String cusip
                     convert.getStrData(rs.get("ric")),  // String ric
                     convert.getStrData(rs.get("tradeCurrency")),  // String tradeCurrency
                     convert.getStrData(rs.get("settleCurrency")),  // String settleCurrency
                     convert.getDoubleData(rs.get("exchangeRate")),  // Double exchangeRate
                     convert.getDoubleData(rs.get("settlePrice")) // Double settlePrice
                  );
                  i++;

               }

               // For every theme, make sure to add Cash element.
               if (!addedCash)
               {
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
                     "Cash", // String secSubAssetClass
                     "Cash",  // String isin
                     "Cash",  // String cusip
                     "Cash", // String ric
                     defaultCurrency,  // String baseCurency
                     defaultCurrency,  // String destCurrency
                     1.0,  // Double exchangeRate
                     1.0 // Double settlePrice
                  );
               }
            }
         }

      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
      finally
      {
         read.unlock();
      }
   }

   private String buildTickersString(ArrayList<String> tickers, String seperator)
   {
      String tickerList = "";
      try
      {

         int tickercount = 0;
         for (String ticker : tickers)
         {
            if (ticker.toUpperCase().equals("CASH"))
            {
               continue;
            }

            if (tickercount == 0)
            {
               tickerList += "'" + ticker.trim() + "'";
            }
            else
            {
               tickerList += seperator.trim() + " '" + ticker.trim() + "'";
            }
            tickercount++;

            if (tickercount > 99)
            {
               break;
            }
         }
      }
      catch (Exception ex)
      {

      }
      return tickerList;
   }

/*
   public void doCustomSQLQuery(String advisor, String theme, ArrayList<String> tickers) {

      //logger.info("Loading Advisor Security from DB");
      seclistByKeyMap.clear();
      listbyAdvisorPrimeassetMap.clear();
      listofOrderedSecurity.clear();
      themeLoaded = theme;

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
               "    '" + theme + "' as theme," +
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
*/

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
         for (String key : listofOrderedSecurity.keySet())
         {
            secOrderedList.add(listofOrderedSecurity.get(key));
         }
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
         key = getKeyByAdvisorThemePrimeAsset(advisor, theme, primeassetclass);
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
         String key = getKeyByTicker(ticker);
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

   public SecurityData getSecurity(String advisor, String primeassetclass)
   {
      read.lock();
      try
      {
         String key = getKeyByAdvisorPrimeasset(advisor, primeassetclass);
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
