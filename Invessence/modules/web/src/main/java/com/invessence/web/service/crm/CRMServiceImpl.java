package com.invessence.web.service.crm;

import java.sql.SQLException;
import com.invessence.crm.bean.*;
import com.invessence.crm.service.CRMTraffic;
import com.invessence.service.bean.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 1/19/2016.
 */
@Service("crmService")
public class CRMServiceImpl implements CRMService
{
   private static final Logger logger = Logger.getLogger(CRMDao.class);

   @Autowired
   CRMDao crmDao;

   @Autowired
   CRMTraffic crm;

   @Override
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object) throws Exception
   {
      return crm.authentication(serviceRequest, object);
   }

   @Override
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object) throws Exception
   {
      return crm.ssoLogin(serviceRequest, object);
   }

   @Override
   public CRMUserDetails getUserAccDetailsByLogonId(Long logonId) throws SQLException
   {
      return crmDao.getUserAccDetailsByLogonId(logonId);
   }
//
//   @Override
//   public boolean insertUserAcctDetails(UserAcctDetails userAcctDetails) throws SQLException
//   {
//      return aggregationDao.insertUserAcctDetails(userAcctDetails);
//   }
//
   @Override
   public CRMUserDetails userRegistration(CRMUserDetails crmUserDetails) throws SQLException
   {
      crmDao.insertUserAcctDetails(crmUserDetails);
      return crmUserDetails;
   }
//
//   @Override
//   public Url getWidget(UserAcctDetails userAcctDetails, String mode) throws SQLException
//   {
//      UserProfile userProfile=new UserProfile(userAcctDetails.getLogonid(), userAcctDetails.getCrmUserId(), userAcctDetails.getCrmPwd(),
//                                              userAcctDetails.getEmail(), userAcctDetails.getFirstname(), userAcctDetails.getLastname(), new Credentials("INVESSENCE", "PASSWORD", "BUILDINGBANJAMINS"), mode);
//
//      Url url= aggregator.getWidgetUrl(userProfile);
//      return url;
//   }
}
