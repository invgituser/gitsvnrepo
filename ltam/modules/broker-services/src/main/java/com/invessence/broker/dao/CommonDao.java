package com.invessence.broker.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.broker.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface CommonDao
{
   public List<BrokerHostDetails> getBrokerHostDetails(String where)throws SQLException;
   public List<DownloadFileDetails> getDownloadFileDetails(String where)throws SQLException;
   public Map<String, DBParameters> getDBParametres() throws SQLException;
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc) throws SQLException;
   public void truncateTable(String tableName) throws SQLException;
   public void callEODProcess(String proc) throws SQLException;
}
