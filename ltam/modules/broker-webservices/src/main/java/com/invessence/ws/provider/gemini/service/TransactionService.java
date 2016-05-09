package com.invessence.ws.provider.gemini.service;

import com.invessence.ws.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface TransactionService
{
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception;
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception;
}
