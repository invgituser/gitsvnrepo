package com.invessence.web.service.crm;

import java.sql.SQLException;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface CRMDao
{
   public CRMUserDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException;
   public boolean insertUserAcctDetails(CRMUserDetails crmUserDetails)throws SQLException;
}
