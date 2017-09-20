package com.invessence.aggr.dao;

import com.invessence.aggr.bean.UserProfile;

/**
 * Created by abhangp on 11/30/2016.
 */
public interface AggrCommonDao
{
   public UserProfile getUserAccDetailsByLogonId(UserProfile userProfile)throws Exception;
}
