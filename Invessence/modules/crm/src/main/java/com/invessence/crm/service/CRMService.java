package com.invessence.crm.service;

import com.invessence.crm.bean.*;
import com.invessence.service.bean.*;

/**
 * Created by abhangp on 11/25/2016.
 */
public interface CRMService
{
//   public UserProfile registerUser(UserProfile user) throws Exception;
//   public UserProfile readUser(UserProfile user) throws Exception;
//   public UserProfile updateUser(UserProfile user) throws Exception;
//   public UserProfile deleteUser(UserProfile user) throws Exception;
//   public Url getWidgetUrl(UserProfile user) throws Exception;
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object)throws Exception;
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object)throws Exception;
}
