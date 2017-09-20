package com.invessence.web.service.crm;

import java.sql.SQLException;
import com.invessence.crm.bean.*;
import com.invessence.service.bean.*;

/**
 * Created by abhangp on 1/19/2016.
 */
public interface CRMService
{
   public CRMUserDetails getUserAccDetailsByLogonId(Long logonId)throws SQLException;
   public CRMUserDetails userRegistration(CRMUserDetails crmUserDetails)throws SQLException;
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object)throws Exception;
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object)throws Exception;
}
