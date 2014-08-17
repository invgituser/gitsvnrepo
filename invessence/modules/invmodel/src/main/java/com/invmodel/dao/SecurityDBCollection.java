package com.invmodel.dao;

import java.sql.*;
import java.util.*;
import java.util.concurrent.locks.*;
import java.util.logging.Logger;

import com.invmodel.Const.InvConst;
import com.invmodel.dao.data.*;
import org.apache.commons.dbutils.DbUtils;
import com.invmodel.inputData.*;


public class SecurityDBCollection
{
   private static SecurityDBCollection instance = null;
   private Map<String, ArrayList<SecurityData>> advisorMap = new HashMap<String, ArrayList<SecurityData>>(); // Key=Group.Asset, Value=SecurityData;
   private Map<String, SecurityData> securityMap = new HashMap<String, SecurityData>(); // Key=Ticker, Value=SecurityData;
   private final Logger logger = Logger.getLogger(SecurityDBCollection.class.getName());

   private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
   private final Lock read = readWriteLock.readLock();
   private final Lock write = readWriteLock.writeLock();

   public static synchronized SecurityDBCollection getInstance()
   {
      if (instance == null)
      {
         instance = new SecurityDBCollection();
      }

      return instance;
   }

   private SecurityDBCollection()
   {
      super();
      loadAdvisorData();
   }

   public void refreshDataFromDB()
   {
      write.lock();
      try
      {
         loadAdvisorData();
      }
      finally
      {
         write.unlock();
      }
   }

   private String getGroupKey(String groupname, String theme, String asset)
   {
      String key;
      if (groupname == null)
      {
         groupname = InvConst.DEFAULT_ADVISOR;
      }

      if (theme == null)
      {
         theme = InvConst.DEFAULT_THEME;
      }

      //  If the asset is Cash, then use the Invessence BALANCE value.
      if (asset.equalsIgnoreCase("Cash"))
      {
         key = InvConst.DEFAULT_ADVISOR + "." + InvConst.DEFAULT_THEME + "." + asset;
      }
      else
      {
         key = groupname.toUpperCase() + "." + theme.toUpperCase() + "." + asset;
      }

      return key;
   }

   private String getSubGroupKey(String assetclass, String subclass)
   {
      String key;
      if (assetclass == null)
      {
         assetclass = "";
      }

      if (subclass == null)
      {
         subclass = "";
      }

      key = assetclass.toUpperCase() + "." + subclass.toUpperCase();

      return key;
   }

   private void setAdvisorData(String groupname, String theme,
                               Long instrumentid, String ticker, String name,
                               String assetclass, String subassetclass, String type, String style,
                               double dailyprice, double expenseRatio, double adv3Month,
                               double aum, double beta, double riskSTD,
                               double taxableReturn, double nonTaxableReturn, double ubConstraint,
                               double lbConstraint, double yield, int sortorder)
   {
      try
      {
         String groupkey;
         boolean addData;
         groupkey = getGroupKey(groupname, theme, assetclass);
         SecurityData security = new SecurityData(instrumentid, ticker, name,
                                                  assetclass, subassetclass, type, style,
                                                  dailyprice, expenseRatio, adv3Month,
                                                  aum, beta, riskSTD,
                                                  taxableReturn, nonTaxableReturn, ubConstraint,
                                                  lbConstraint, yield, sortorder);

         // NOTE:  The key will return the default Invessence.Balance.Cash for Cash Assetclass.
         if (!advisorMap.containsKey(groupkey))
         {
            // For new assetclass, create new list.
            ArrayList<SecurityData> data = new ArrayList<SecurityData>();
            data.add(security);
            advisorMap.put(groupkey, data);
         }
         else
         {
            addData = true;
            // In the case of Cash, we only want to add Invessence group.
            if (assetclass.equalsIgnoreCase("CASH"))
                 addData = false;

            if (addData) {
               ArrayList<SecurityData> data = advisorMap.get(groupkey);
               data.add(security);
               advisorMap.put(groupkey, data);
            }
          }

         // For every unique Ticker add to Security List.
         if (!securityMap.containsKey(ticker)) {
            securityMap.put(ticker,security);
         }

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   private void loadAdvisorData()
   {
      advisorMap.clear();
      securityMap.clear();

      logger.info("Loading Advisor Security from DB");
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
               "\tgroupname,\n " +
               "\ttheme,\n " +
               "\tinstrumentid,\n " +
               "\tstatus,\n " +
               "\tticker,\n " +
               "\tcusip,\n " +
               "\tisin,\n " +
               "\tname,\n " +
               "\tassetclass,\n " +
               "\tsubclass,\n " +
               "\ttype,\n " +
               "\tstyle,\n " +
               "\texpenseRatio,\n " +
               "\tlowerBoundReturn,\n " +
               "\tupperBoundReturn,\n " +
               "\tlowerBound,\n " +
               "\tupperBound,\n " +
               "\ttaxableReturn,\n " +
               "\tnontaxableReturn,\n " +
               "\tadv3months,\n " +
               "\taum,\n " +
               "\tbeta,\n " +
               "\tsecurityRiskSTD,\n " +
               "\tyield, \n " +
               "\tprice, \n " +
               "\tsortorder \n " +
               "FROM vw_securities "
         );

         // Make sure to keep track of this position.

         rs = s.getResultSet();
         // get data row from table.
         while (rs.next())
         {
            setAdvisorData(
               rs.getString("groupname"), // String groupname
               rs.getString("theme"), // String theme
               rs.getLong("instrumentid"), // Long instrumentid
               rs.getString("ticker"), // String ticker
               rs.getString("name"),  // String name
               rs.getString("assetclass"),  // String asset type
               rs.getString("subclass"), // String asset subtype , was "subclass" - changed to "subtype" JAV 1/2/2014
               rs.getString("type"),  // String type
               rs.getString("style"),  // String style
               rs.getDouble("price"), // double dailyprice
               rs.getDouble("expenseRatio"),  // double expenseRatio
               rs.getDouble("adv3months"), // double adv3Month
               rs.getDouble("aum"),          // double aum
               rs.getDouble("beta"),         // double beta
               rs.getDouble("securityRiskSTD"), // double riskSTD
               rs.getDouble("taxableReturn"), // double returns after taxes
               rs.getDouble("nontaxableReturn"), // double returns after taxes
               rs.getDouble("upperBound"), // double ubConstraint
               rs.getDouble("lowerBound"), // double lbConstraint
               rs.getDouble("yield"),//double yields
               rs.getInt("sortorder")  // int sortorder
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

   public String[] getUnfilterdSubAsset(String groupname, String theme, String assetclass) {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      String[] tmpArray = new String[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            String[] subassetList = new String[advisorMap.get(groupkey).size()];
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subassetList[i] = advisorMap.get(groupkey).get(i).getSubassetclass();
            }
            return subassetList;
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
      return tmpArray;

   }

   public String[] getAssetOrderedAssetTickers(String groupname, String theme, String assetclass,
                        Map<String, CustomAllocation> ca)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      String[] tmpArray = new String[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<String> tickerArray = new ArrayList<String>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclasskey = getSubGroupKey(advisorMap.get(groupkey).get(i).getAssetclass(),
                                            advisorMap.get(groupkey).get(i).getSubassetclass());
               if (! ca.containsKey(subclasskey)) {
                     tickerArray.add(counter, advisorMap.get(groupkey).get(i).getTicker());
                     counter++;
               }
            }
            if (tickerArray.size() > 0) {
               String[] tickerList = new String[tickerArray.size()];
               for (int i=0; i<tickerArray.size();i++)
                  tickerList[i] = tickerArray.get(i);
               return tickerList;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetLB(String groupname, String theme, String assetclass, double riskOffset,
                                          Map<String, CustomAllocation> ca, Integer duration, Boolean taxable)
   {
      read.lock();
      String groupkey;
      String subclasskey, subclass;
      Integer counter = 0;
      Integer allocated=0;
      double total = 0.0;
      Double lbValue;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> lbArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclass = advisorMap.get(groupkey).get(i).getSubassetclass();
               subclasskey = getSubGroupKey(assetclass,
                                            subclass);
               if (! ca.containsKey(subclasskey)) {
                  lbValue = advisorMap.get(groupkey).get(i).getLbConstraint();

                  if (assetclass.equalsIgnoreCase("Bond"))
                     if (taxable) {
                        if (subclass.equalsIgnoreCase("Treasury"))
                           lbValue = 0.0;
                     }
                     else  { //Not taxable
                        if (subclass.equalsIgnoreCase("Municipal"))
                           lbValue = 0.0;
                     }

                  if (lbValue > 0)
                     allocated++;
                  lbArray.add(counter,lbValue);
                  total = total +  lbValue;
                  counter++;
               }
            }

            Integer adjust = 0;
            if (total >= 1.0) { // Determine the int value of overage. ie, 1, or 2.
               adjust= (int) total;
            }

            // Since we are adding items in Arraylist, we need to convert to array.
            if (lbArray.size() > 0) {
               double[] lowerBound = new double[lbArray.size()];
               for (int i = 0; i < lbArray.size(); i++) {
                  lbValue = lbArray.get(i);
                  if (adjust > 0) {
                     if (lbValue > 0.0)
                        lbValue = (adjust / (double) allocated) - lbValue;
                  }
                  lowerBound[i] = lbValue;

               }
               return lowerBound;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetUB(String groupname, String theme, String assetclass, double riskOffset,
                                          Map<String, CustomAllocation> ca, Integer duration, Boolean taxable)
   {
      read.lock();
      String groupkey;
      double origvalue;
      String subclass, subclasskey;
      Integer counter = 0;
      Integer allocated = 0;
      double total = 0.0, ubValue = 0.0;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> ubArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclass = advisorMap.get(groupkey).get(i).getSubassetclass();
               subclasskey = getSubGroupKey(assetclass, subclass);
               if (! ca.containsKey(subclasskey)) {
                  origvalue =  advisorMap.get(groupkey).get(i).getUbConstraint();

                  ubValue = advisorMap.get(groupkey).get(i).getLbConstraint() +
                                          (origvalue * riskOffset);

                  ubValue = (ubValue > 1.0) ? 1.0 : ubValue;

                  if (assetclass.equalsIgnoreCase("Bond"))
                  {
                        // Adjust UB constraints for long term and mid term bond if horizon is short term
                        // If horizon > 10 then 100%
                        // If Long Term 10 > horizon  then double(100/(100*(10-horizon))*UB but greater than LB
                        // If Mid Term horizon <= 5 then  double(100/(100*(10-2*horizon)))*UB but greater than LB

                        if (subclass.equalsIgnoreCase("Long Term") && duration <= 10)
                        {

                           if (duration > 5 ){     //JAV changing long term bond exp, 4/1/2014

                              double tempUB = ubValue - (((100.0 - (10.0 * (double) duration)) / 100.0) * ubValue);
                              ubValue = tempUB;
                           }
                           else    {

                              ubValue = 0.0;
                           }

                        }
                        else if (subclass.equalsIgnoreCase("Mid Term") && duration <= 5)
                        {

                           double tempUB = ubValue - (((100.0 - (20.0 * (double) duration)) / 100.0) * ubValue);
                           ubValue = tempUB;
                        }

                        if (taxable)
                        {
                           if (subclass.equalsIgnoreCase("Treasury"))
                           {
                              ubValue = 0.0;
                           }
                        }
                        else
                        { //Not taxable

                           if (subclass.equalsIgnoreCase("Municipal"))
                           {
                              ubValue = 0.0;
                           }
                        }

                  }

                  if (ubValue > 0.0)
                     allocated++;

                  ubArray.add(counter,ubValue);
                  total = total +  ubValue;
                  counter++;
               }
            }

            Boolean adjust = false;
            if (total < 1.0)
               adjust = true;

            if (ubArray.size() > 0) {
               double[] upperBound = new double[advisorMap.get(groupkey).size()];
               for (int i = 0; i < counter; i++) {
                  ubValue = ubArray.get(i);
                  if (adjust) {
                     if (ubValue > 0.0)
                        ubValue = ubValue + (1.0 / (double) allocated);
                  }
                  upperBound[i] = ubValue;
               }
               return upperBound;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetExpectedReturns(String groupname, String theme, String assetclass,
                                                       Map<String, CustomAllocation> ca, Boolean taxable)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> eReturnArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclasskey = getSubGroupKey(advisorMap.get(groupkey).get(i).getAssetclass(),
                                            advisorMap.get(groupkey).get(i).getSubassetclass());
               if (! ca.containsKey(subclasskey)) {
/*
                  if (taxable)
                     expectedReturns[counter] = advisorMap.get(groupkey).get(i).getTaxableReturn();
                  else
*/
                  eReturnArray.add(counter,advisorMap.get(groupkey).get(i).getNonTaxableReturn());
                  counter++;
               }
            }

            if (eReturnArray.size() > 0) {
               double[] expectedReturns = new double[eReturnArray.size()];
               for (int i=0; i < eReturnArray.size(); i++)
                  expectedReturns[i] = eReturnArray.get(i);

               return expectedReturns;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetExpenseRatio(String groupname, String theme, String assetclass,
                                                    Map<String, CustomAllocation> ca)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> eRatioArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclasskey = getSubGroupKey(advisorMap.get(groupkey).get(i).getAssetclass(),
                                            advisorMap.get(groupkey).get(i).getSubassetclass());
               if (! ca.containsKey(subclasskey)) {
                  eRatioArray.add(counter,advisorMap.get(groupkey).get(i).getExpenseRatio());
                  counter++;
               }
            }

            if (eRatioArray.size() > 0) {
               double[] expenseRatio = new double[eRatioArray.size()];
               for (int i=0; i < eRatioArray.size(); i++)
                  expenseRatio[i] = eRatioArray.get(i);

               return expenseRatio;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetSecurityRisk(String groupname, String theme, String assetclass,
                                                    Map<String, CustomAllocation> ca)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> sRiskArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclasskey = getSubGroupKey(advisorMap.get(groupkey).get(i).getAssetclass(),
                                            advisorMap.get(groupkey).get(i).getSubassetclass());
               if (! ca.containsKey(subclasskey)) {
                  sRiskArray.add(counter, advisorMap.get(groupkey).get(i).getRiskSTD());
                  counter++;
               }
            }

            if (sRiskArray.size() > 0) {
               double[] securityRisk = new double[sRiskArray.size()];
               for (int i=0; i < sRiskArray.size(); i++)
                  securityRisk[i] = sRiskArray.get(i);

               return securityRisk;
            }
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
      return tmpArray;
   }

   public double[] getAssetOrderedAssetYield(String groupname, String theme, String assetclass,
                                             Map<String, CustomAllocation> ca, Integer duration, Boolean taxable, Double rate)
   {
      read.lock();
      String groupkey;
      String subclasskey, subclass;
      Integer counter = 0;
      Double yieldValue;
      double[] tmpArray = new double[1];
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         if (advisorMap.containsKey(groupkey))
         {
            ArrayList<Double> yieldArray = new ArrayList<Double>();
            for (int i = 0; i < advisorMap.get(groupkey).size(); i++)
            {
               subclass = advisorMap.get(groupkey).get(i).getSubassetclass();
               subclasskey = getSubGroupKey(advisorMap.get(groupkey).get(i).getAssetclass(),
                                            advisorMap.get(groupkey).get(i).getSubassetclass());
               if (! ca.containsKey(subclasskey)) {
                  yieldValue = advisorMap.get(groupkey).get(i).getYield();
                  if (taxable)
                  {
                     if (subclass.equals("Municipal"))
                     {
                        yieldValue = StrictMath.round((yieldValue / (1 - rate)) * 10000) / 10000D;
                     }
                  }
                  yieldArray.add(counter,yieldValue);
                  counter++;
               }
            }
            if (yieldArray.size() > 0) {
               double[] yield = new double[yieldArray.size()];
               for (int i=0; i < yieldArray.size(); i++)
                  yield[i] = yieldArray.get(i);

               return yield;
            }
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
      return tmpArray;
   }

   public SecurityData getSecurityInfo(String groupname, String theme, String assetclass, String ticker,
                                       Map<String, CustomAllocation> ca)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         SecurityData data = null;
         if (advisorMap.containsKey(groupkey))
         {
            if (securityMap.containsKey(ticker))
            {
               data = securityMap.get(ticker);
               subclasskey = getSubGroupKey(data.getAssetclass(),
                                            data.getSubassetclass());
               if (!ca.containsKey(subclasskey))
               {
                  return (data);
               }
            }
        }
         return data;
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

   public Double getDailyprice(String groupname, String theme, String assetclass, String ticker,
                               Map<String, CustomAllocation> ca)
   {
      read.lock();
      String groupkey;
      String subclasskey;
      Integer counter = 0;
      try
      {
         groupkey = getGroupKey(groupname, theme, assetclass);
         SecurityData data;
         if (advisorMap.containsKey(groupkey))
         {
            if (securityMap.containsKey(ticker))
            {
               data = securityMap.get(ticker);
               subclasskey = getSubGroupKey(data.getAssetclass(),
                                            data.getSubassetclass());
               if (!ca.containsKey(subclasskey))
               {
                  return (data.getDailyprice());
               }
            }
        }
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
