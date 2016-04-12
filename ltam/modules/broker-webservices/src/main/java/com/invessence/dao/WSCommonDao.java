package com.invessence.dao;

import java.sql.SQLException;
import java.util.*;

import com.invessence.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface WSCommonDao
{
   public UserAcctDetails getUserAccDetailsByAccNumber(String accountNumber)throws SQLException;
   public List<UserAcctDetails> getUserAccDetailsByWhereClause(String where)throws SQLException;
   public List<UserAcctDetails> getPendingUserAccDetails()throws SQLException;
   public boolean updatePendingUserAccDetails(UserAcctDetails userAcctDetails)throws SQLException;
   public boolean updateUserEmail(UserAcctDetails userAcctDetails, String newEmail)throws SQLException;

   public UserAcctExt getAccountExtInfo(String accountNumber)throws SQLException;
   public boolean insertAccountExtInfo(UserAcctExt userAcctExt)throws SQLException;
   public boolean updateAccountExtInfo(UserAcctExt userAcctExt)throws SQLException;

   public List<BrokerHostDetails> getBrokerHostDetails()throws SQLException;
   public BrokerHostDetails getBrokerHostDetails(String where)throws SQLException;
   public void insertBatch(final List<String[]> dataArrLst, String sql, String proc) throws SQLException;
   public void truncateTable(String tableName) throws SQLException;
   public void callEODProcess(String proc) throws SQLException;
}
