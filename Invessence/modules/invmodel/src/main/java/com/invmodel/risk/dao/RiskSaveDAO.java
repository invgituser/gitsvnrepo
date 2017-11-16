package com.invmodel.risk.dao;

import javax.sql.DataSource;

import com.invessence.converter.SQLData;
import com.invmodel.dao.DBConnectionProvider;

/**
 * Created by prashant on 11/10/2017.
 */
public class RiskSaveDAO
{
   DBConnectionProvider dbconnection = DBConnectionProvider.getInstance();
   SQLData convert = new SQLData();
   DataSource ds = dbconnection.getMySQLDataSource();

}
