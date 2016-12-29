package com.invessence.aggr.service;

import com.invessence.aggr.bean.*;

/**
 * Created by abhangp on 11/25/2016.
 */
public interface AggregationService
{
   public UserProfile registerUser(UserProfile user) throws Exception;
   public UserProfile readUser(UserProfile user) throws Exception;
   public UserProfile updateUser(UserProfile user) throws Exception;
   public UserProfile deleteUser(UserProfile user) throws Exception;
   public Url getWidgetUrl(UserProfile user) throws Exception;
}
