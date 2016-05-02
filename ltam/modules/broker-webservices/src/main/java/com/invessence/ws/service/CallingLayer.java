package com.invessence.ws.service;

import com.invessence.ws.bean.*;

/**
 * Created by abhangp on 3/22/2016.
 */
public interface CallingLayer
{
   //LoginServices
   public WSCallStatus createUser(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception;


   //AccountServices
   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception;
   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails)throws Exception;

   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception;

   //TransactionServices
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber) throws Exception;
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber) throws Exception;


   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception;

   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception;
//   public WSCallStatus isUserExist(UserAcctDetails userAcctDetails) throws Exception;
//   public WSCallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;

}
