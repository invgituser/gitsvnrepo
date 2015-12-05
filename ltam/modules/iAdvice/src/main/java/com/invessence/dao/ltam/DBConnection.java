package com.invessence.dao.ltam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/23/15
 * Time: 10:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class DBConnection
{
   private static DBConnection instance = null;
   private String connectionUrl;

   public synchronized static DBConnection getInstance()
   {
      if (instance == null)
      {
         instance = new DBConnection();
      }
      return instance;
   }

   private DBConnection()
   {
      setUpDBConnectionURL();
   }

   private void setUpDBConnectionURL()
   {
      Properties dbProperties = new Properties();

      try
      {

         dbProperties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
         connectionUrl = dbProperties.getProperty("jdbc.url") +
            "&user=" + dbProperties.getProperty("jdbc.username") +
            "&password=" + dbProperties.getProperty("jdbc.password");

         Class.forName(dbProperties.getProperty("jdbc.driverClassName")).newInstance();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public Connection getConnection()
   {
      try
      {
         return DriverManager.getConnection(connectionUrl);
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public DataSource getMySQLDataSource()
   {
      Properties dbProperties = new Properties();
      MysqlDataSource mysqlDS = null;
      try
      {
         dbProperties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
         mysqlDS = new MysqlDataSource();
         mysqlDS.setURL(dbProperties.getProperty("jdbc.url"));
         mysqlDS.setUser(dbProperties.getProperty("jdbc.username"));
         mysqlDS.setPassword(dbProperties.getProperty("jdbc.password"));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      return mysqlDS;
   }

}
