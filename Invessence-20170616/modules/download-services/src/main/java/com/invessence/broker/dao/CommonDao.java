package com.invessence.broker.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.broker.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface CommonDao
{
/* Code removed: Prashant 30-Oct-2016
   public List<BrokerHostDetails> getBrokerHostDetails(String where)throws SQLException;
*/
   public List<DownloadFileDetails> getDownloadFileDetails(String where)throws SQLException;
   public Map<String, DBParameters> getDBParametres() throws SQLException;
   public boolean insertBatch(final List<String[]> dataArrLst, String sql, String proc) throws SQLException;
   public void truncateTable(String tableName) throws SQLException;
   public void callProcedure(String proc) throws SQLException;
}
