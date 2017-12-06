package com.invessence.web.service.custody;

import java.util.Map;

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
   public void saveEmploymentDtls(Long acctNum, int acctOwnerId , String p_logonId, OwnerDetails ownerDetails);
   public void saveAddressDtls(Long acctNum, int acctOwnerId , String p_logonId, OwnerDetails ownerDetails);
   public UOBDataMaster fetch(Long acctNum,boolean isJoint);
   public void saveSetMiscDtls(Long acctNum, int acctOwnerId,int id,String category,String name,String value);
   public void deleteSetMiscDtls(Long acctNum, int acctOwnerId ,String category);
   public void saveAcctAdditionalDtls(Long acctNum,String name,String value,String table);
   public Map<String,String> fetchSalesRepList(String advisor);
}
