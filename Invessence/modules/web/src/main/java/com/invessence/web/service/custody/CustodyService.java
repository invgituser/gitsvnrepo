package com.invessence.web.service.custody;

import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.custody.uob.data.*;

/**
 * Created by abhangp on 11/10/2017.
 */
public interface CustodyService
{
   public void saveAcctDetails(AccountDetails accountDetails, String p_logonId);
   public void saveAccountHolderDtls(Long acctNum, int acctOwnerId , String p_logonId, OwnerDetails ownerDeta);
   public void saveAdditionalDtls(Long acctNum,int acctOwnerId,String name,String value,String table);
   public void save3();
   public UOBDataMaster fetch(Long acctNum,boolean isJoint);
}
