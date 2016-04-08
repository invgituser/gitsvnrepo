package com.ws.gemini.service;

import java.util.List;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface AccountService
{
   public CallStatus getAccountInfo(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception;
   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception;
   public CallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception;
   public List<BankAcctDetails> getUserBankAcctDetails(UserAcctDetails userAcctDetails) throws Exception;
}
