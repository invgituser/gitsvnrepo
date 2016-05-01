package com.invessence.ws.provider.gemini.service;

import com.invessence.ws.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface AccountService
{
   public WSCallStatus getAccountInfo(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception;
   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception;
   public WSCallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallStatus getMailingAddress(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception;
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails) throws Exception;

   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception;
}
