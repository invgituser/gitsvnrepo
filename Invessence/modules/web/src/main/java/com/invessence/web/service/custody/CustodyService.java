package com.invessence.web.service.custody;

import com.invessence.custody.uob.UOBDataMaster;

/**
 * Created by abhangp on 11/10/2017.
 */
public interface CustodyService
{
   public void save();
   public void save1();
   public void save2();
   public void save3();
   public UOBDataMaster fetch(Long acctNum);
}
