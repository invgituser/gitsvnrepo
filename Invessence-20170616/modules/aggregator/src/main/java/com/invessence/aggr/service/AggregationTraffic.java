package com.invessence.aggr.service;

import com.invessence.aggr.bean.*;
/**
 * Created by abhangp on 11/30/2016.
 */
public interface AggregationTraffic
{
   public UserProfile registerUser(UserProfile user);
   public UserProfile readUser(UserProfile user);
   public UserProfile updateUser(UserProfile user);
   public UserProfile deleteUser(UserProfile user);
   public Url getWidgetUrl(UserProfile user);
}
