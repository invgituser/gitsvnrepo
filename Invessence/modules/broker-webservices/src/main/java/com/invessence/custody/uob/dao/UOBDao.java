package com.invessence.custody.uob.dao;

/**
 * Created by abhangp on 10/26/2017.
 */
public interface UOBDao
{
   public void save();
   public Object fetch(Long acctNum);
}
