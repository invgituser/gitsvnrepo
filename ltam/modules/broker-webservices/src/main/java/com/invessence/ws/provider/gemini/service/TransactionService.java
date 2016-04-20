package com.invessence.ws.provider.gemini.service;

import com.invessence.ws.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface TransactionService
{
   public WSCallStatus fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber) throws Exception;
   public WSCallStatus fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber) throws Exception;
}
