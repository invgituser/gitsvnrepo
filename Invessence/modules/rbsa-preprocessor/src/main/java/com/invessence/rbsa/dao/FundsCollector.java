package com.invessence.rbsa.dao;

import java.io.Serializable;
import java.util.*;
import java.util.logging.Logger;
import java.sql.*;
import java.util.concurrent.locks.*;

import com.joptimizer.util.Utils;
import org.apache.commons.dbutils.DbUtils;

public class FundsCollector implements Serializable{
   private final Logger logger = Logger.getLogger(FundsCollector.class.getName());
    private String fundName;
    private ArrayList<Double> fundReturns;

    public FundsCollector() {
        super();
    }

   public FundsCollector(String ticker) {
      super();
      loadFundsData(ticker);
   }

   public String getFundName()
   {
      return fundName;
   }

   public void setFundName(String fundName)
   {
      this.fundName = fundName;
   }

   public void loadFundsData(String ticker)
    {
      logger.info("Loading FUNDS information from DB");
      Connection connection = null;
      Statement statement = null;
      ResultSet resultSet = null;
      try
      {
         // Select data from the database
         connection = DBConnectionProvider.getInstance().getConnection();
         statement = connection.createStatement();
         String where_clause="";
         if (ticker != null && ! ticker.equalsIgnoreCase("All")) {
            where_clause = "WHERE ticker = '" + ticker + "'" + "\n";
         }
         statement.executeQuery("SELECT ticker, date, monthly_return as monthly_return FROM rbsa.vw_funds_history \n" +
                                   where_clause +
                                   "order by ticker, date desc" );

         resultSet = statement.getResultSet();
         resultSet.beforeFirst();

         setFundName(ticker);
         fundReturns = new ArrayList<Double>();
         Integer counter=0;
         while (resultSet.next())
         {
            fundReturns.add(counter, resultSet.getDouble("monthly_return"));
            counter++;
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

   public double[] fundReturnsArray(Integer numOfDays) {
      double [] fundArray = null;

      try {
         if (fundReturns != null) {
            fundArray = new double[numOfDays+1];
            for (int i=0; i < numOfDays+1;i++) {
               //if (i==0)
                  //fundArray[i] = 1.0;
               //else
                  //fundArray[i] = fundReturns.get(i-1);
                  fundArray[i] = fundReturns.get(i);
            }
         }
      }
      catch (Exception ex) {
         System.out.println("Error fetching funds Returns");
      }
      return fundArray;
   }

   public Integer numOfElements() {
      try {
         if (fundReturns != null) {
            return fundReturns.size();
         }
      }
      catch (Exception ex) {
         System.out.println("Error fetching funds Returns");
      }
      return 0;
   }




}
