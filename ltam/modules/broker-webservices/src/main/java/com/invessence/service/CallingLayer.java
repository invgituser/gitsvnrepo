package com.invessence.service;

import java.util.List;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/22/2016.
 */
public interface CallingLayer
{
   //LoginServices
   public CallStatus createUser(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception;


   //AccountServices
   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception;
   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails)throws Exception;

   public List<BankAcctDetails> getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception;

   //TransactionServices
   public CallStatus fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String accountNumber) throws Exception;
   public CallStatus fundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, double amount, String accountNumber) throws Exception;


//   public CallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception;
//   public CallStatus isUserExist(UserAcctDetails userAcctDetails) throws Exception;
//   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;

}
