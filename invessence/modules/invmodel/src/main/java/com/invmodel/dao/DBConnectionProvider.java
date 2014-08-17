package com.invmodel.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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
            connectionUrl = dbProperties.getProperty("jdbc.url") +
                    "?user=" + dbProperties.getProperty("jdbc.username") +
                    "&password=" + dbProperties.getProperty("jdbc.password");

            Class.forName(dbProperties.getProperty("jdbc.driverClassName")).newInstance();
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
}

