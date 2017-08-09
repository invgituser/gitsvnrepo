package com.invessence.crm.dao;

import com.invessence.crm.bean.UserProfile;

/**
 * Created by abhangp on 11/30/2016.
 */
public interface CRMCommonDao
{
   public UserProfile getUserAccDetailsByLogonId(UserProfile userProfile)throws Exception;
}
