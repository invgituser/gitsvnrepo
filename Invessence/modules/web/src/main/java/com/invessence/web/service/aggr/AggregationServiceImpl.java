package com.invessence.web.service.aggr;

import java.sql.SQLException;

import com.invessence.aggr.bean.*;
import com.invessence.aggr.service.AggregationTraffic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;

/**
 * Created by abhangp on 1/19/2016.
 */
@Service("aggregationService")
public class AggregationServiceImpl implements AggregationService
{
   private static final Logger logger = Logger.getLogger(AggregationDao.class);

   @Autowired
   AggregationDao aggregationDao;

   @Autowired
   AggregationTraffic aggregator;

   @Override
   public UserAcctDetails getUserAccDetailsByLogonId(Long logonId) throws SQLException
   {
      return aggregationDao.getUserAccDetailsByLogonId(logonId);
   }

   @Override
   public boolean insertUserAcctDetails(UserAcctDetails userAcctDetails) throws SQLException
   {
      return aggregationDao.insertUserAcctDetails(userAcctDetails);
   }

   @Override
   public UserProfile userRegistration(UserAcctDetails userAcctDetails) throws SQLException
   {
      UserProfile userProfile=new UserProfile(userAcctDetails.getLogonid(),"INV-"+userAcctDetails.getLogonid(), userAcctDetails.getAggrPwd(),
                                              userAcctDetails.getEmail(),userAcctDetails.getFirstname(),userAcctDetails.getLastname(),
                                              new Credentials("INVESSENCE", "PASSWORD", "BUILDINGBANJAMINS"), "");
      userProfile=aggregator.registerUser(userProfile);
      if(userProfile==null || userProfile.getErrorStatus()==null){
         System.out.println("Service end issue!");
      }else if(userProfile.getErrorStatus().getErrorCode()==0){
         System.out.println("Registration Success!");
         userAcctDetails.setAggrUserId(userProfile.getUserName());
         userAcctDetails.setAggrPwd(userProfile.getPwd());
         userAcctDetails.setAggrStatus("A");
         aggregationDao.insertUserAcctDetails(userAcctDetails);
      }else if(userProfile.getErrorStatus().getErrorCode()!=0){
         System.out.println("MX Registration issue!");
      }
      return userProfile;
   }

   @Override
   public Url getWidget(UserAcctDetails userAcctDetails, String mode) throws SQLException
   {
      UserProfile userProfile=new UserProfile(userAcctDetails.getLogonid(), userAcctDetails.getAggrUserId(), userAcctDetails.getAggrPwd(),
                                              userAcctDetails.getEmail(), userAcctDetails.getFirstname(), userAcctDetails.getLastname(), new Credentials("INVESSENCE", "PASSWORD", "BUILDINGBANJAMINS"), mode);

      Url url= aggregator.getWidgetUrl(userProfile);
      return url;
   }
}
