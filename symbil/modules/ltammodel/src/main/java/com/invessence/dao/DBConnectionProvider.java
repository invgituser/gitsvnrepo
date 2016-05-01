package com.invessence.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBConnectionProvider {
    private static DBConnectionProvider instance = null;
    private String connectionUrl;

    public synchronized static DBConnectionProvider getInstance() {
        if (instance == null) {
            instance = new DBConnectionProvider();
        }
        return instance;
    }

    private DBConnectionProvider() {
        setUpDBConnectionURL();
    }

    private void setUpDBConnectionURL() {
        Properties dbProperties = new Properties();

        try {

            dbProperties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
            connectionUrl = dbProperties.getProperty("ltam.url") +
                    "?user=" + dbProperties.getProperty("ltam.username") +
                    "&password=" + dbProperties.getProperty("ltam.password");

            Class.forName(dbProperties.getProperty("ltam.driverClassName")).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

   public DataSource getMySQLDataSource() {
      Properties dbProperties = new Properties();
      MysqlDataSource mysqlDS = null;
      try {
         dbProperties.load(getClass().getClassLoader().getResourceAsStream("db.properties"));
         mysqlDS = new MysqlDataSource();
         mysqlDS.setURL(dbProperties.getProperty("ltam.url"));
         mysqlDS.setUser(dbProperties.getProperty("ltam.username"));
         mysqlDS.setPassword(dbProperties.getProperty("ltam.password"));
      } catch (IOException e) {
         e.printStackTrace();
      }
      return mysqlDS;
   }
}

