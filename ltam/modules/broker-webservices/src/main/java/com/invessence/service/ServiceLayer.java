package com.invessence.service;

import java.util.List;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/11/2016.
 */
public interface ServiceLayer
{
   public void createPendingUser();
   public CallStatus updateEmail(String clientAccountID, String newEmail);
   public CallStatus updateMailingAddress(String clientAccountID,
                                          String nameLines, String addressLines, String postalZip, short countryCode,
                                          String voicePhone,String altPhone, String faxPhone,String emailAddress);

   public List<BankAcctDetails> getUserBankAcctDetails(String clientAccountID);

   public CallStatus fundAccount(String clientAccountID, int fundID, double amount, String accountNumber);
   public CallStatus fundTransfer(String clientAccountID, int fromFundID, int toFundID, double amount, String accountNumber);

   //Future required functions

   //public CallStatus loginUser(String clientAccountID);
   //public CallStatus createUser(String clientAccountID, String securityQuestion, String securityAnswer);
   //public CallStatus isUserExist(String userId);
   //public CallStatus getMailingAddress(String clientAccountID);

}
