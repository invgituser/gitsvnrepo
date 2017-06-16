package com.invessence.web.service.aggr;

import java.sql.SQLException;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface AggregationDao
{
   public UserAcctDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException;
   public boolean insertUserAcctDetails(UserAcctDetails userAcctDetails)throws SQLException;
}
