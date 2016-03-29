package com.ws.gemini.service;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface AccountService
{
   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
}
