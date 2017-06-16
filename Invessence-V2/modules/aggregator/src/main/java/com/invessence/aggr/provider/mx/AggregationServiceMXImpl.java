package com.invessence.aggr.provider.mx;

import com.invessence.aggr.bean.*;
import com.invessence.aggr.bean.Url;
import com.invessence.aggr.provider.mx.bean.*;
import com.invessence.aggr.service.AggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 11/25/2016.
 */
@Service("MXAggregation")
public class AggregationServiceMXImpl implements AggregationService
{
   @Autowired
   MXAPIRepository mxAPIRepository;

   @Override
   public UserProfile registerUser(UserProfile userProfile) throws Exception
   {
      System.out.println("userProfile = [" + userProfile + "]");
      User user= userProfile.convertToMXBean();
      userProfile=mxAPIRepository.registerUser(userProfile);

      return userProfile;
   }

   @Override
   public UserProfile readUser(UserProfile userProfile) throws Exception
   {
      mxAPIRepository.readUser(userProfile.getUserName());
      return null;
   }

   @Override
   public UserProfile updateUser(UserProfile userProfile) throws Exception
   {
      mxAPIRepository.updateUser(userProfile.getUserName());
      return null;
   }

   @Override
   public UserProfile deleteUser(UserProfile userProfile) throws Exception
   {
      mxAPIRepository.deleteUser(userProfile.getUserName());
      return null;
   }

   @Override
   public Url getWidgetUrl(UserProfile userProfile) throws Exception
   {
      Url url=mxAPIRepository.getWidgetUrls(userProfile);
      return url;

   }
}
