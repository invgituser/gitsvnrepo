package com.invessence.crm.provider.redTail;

import com.invessence.crm.bean.*;
import com.invessence.crm.provider.redTail.bean.*;
import com.invessence.crm.service.CRMService;
import com.invessence.service.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 11/25/2016.
 */
@Service("RedTailCRM")
public class CRMServiceRedTailImpl implements CRMService
{
   @Autowired
   RedTailAPIRepository redTailAPIRepository;

//   @Override
//   public UserProfile registerUser(UserProfile userProfile) throws Exception
//   {
//      System.out.println("userProfile = [" + userProfile + "]");
//      User user= userProfile.convertToRedTailBean();
//      userProfile=redTailAPIRepository.registerUser(userProfile);
//
//      return userProfile;
//   }
//
//   @Override
//   public UserProfile readUser(UserProfile userProfile) throws Exception
//   {
//      redTailAPIRepository.readUser(userProfile.getUserName());
//      return null;
//   }
//
//   @Override
//   public UserProfile updateUser(UserProfile userProfile) throws Exception
//   {
//      redTailAPIRepository.updateUser(userProfile.getUserName());
//      return null;
//   }
//
//   @Override
//   public UserProfile deleteUser(UserProfile userProfile) throws Exception
//   {
//      redTailAPIRepository.deleteUser(userProfile.getUserName());
//      return null;
//   }
//
//   @Override
//   public Url getWidgetUrl(UserProfile userProfile) throws Exception
//   {
//      Url url=redTailAPIRepository.getWidgetUrls(userProfile);
//      return url;
//
//   }

   @Override
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object) throws Exception
   {
      WSCallResult wsCallResult=null;
      AuthResponse authResponse=redTailAPIRepository.authentication(serviceRequest, (User)object);
      if(authResponse==null){
         wsCallResult = new WSCallResult(new WSCallStatus(1,authResponse.getMessage()),authResponse);
      }else if(authResponse!=null && authResponse.getStatus()!=null && authResponse.getStatus().trim().equalsIgnoreCase("1")){
         wsCallResult = new WSCallResult(new WSCallStatus(0,"Success"),authResponse);
      }else{
         wsCallResult = new WSCallResult(new WSCallStatus(1,authResponse.getMessage()==null?"WebService response issue":authResponse.getMessage()),authResponse);
      }
      return wsCallResult;
   }

   @Override
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object) throws Exception
   {
      WSCallResult wsCallResult=null;
      AuthResponse authResponse=redTailAPIRepository.ssoLogin(serviceRequest,(User)object);
      if(authResponse==null){
         wsCallResult = new WSCallResult(new WSCallStatus(1,authResponse.getMessage()),authResponse);
      }else if(authResponse!=null && authResponse.getStatus()!=null && authResponse.getStatus().trim().equalsIgnoreCase("1")){
         wsCallResult = new WSCallResult(new WSCallStatus(0,"Success"),authResponse);
      }else{
         wsCallResult = new WSCallResult(new WSCallStatus(1,authResponse.getMessage()==null?"WebService response issue":authResponse.getMessage()),authResponse);
      }
      return wsCallResult;
   }
}
