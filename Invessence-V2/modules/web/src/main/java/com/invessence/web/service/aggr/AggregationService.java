package com.invessence.web.service.aggr;

import java.sql.SQLException;

import com.invessence.aggr.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface AggregationService
{
   public UserAcctDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException;
   public boolean insertUserAcctDetails(UserAcctDetails userAcctDetails)throws SQLException;
   public UserProfile userRegistration(UserAcctDetails userAcctDetails)throws SQLException;
   public Url getWidget(UserAcctDetails userAcctDetails, String mode)throws SQLException;
}
