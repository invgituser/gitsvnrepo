package com.invessence.service;

import java.math.BigDecimal;
import java.util.List;

import com.invessence.bean.*;
import com.invessence.service.*;
import com.ws.gemini.service.*;
import com.ws.gemini.wsdl.login.*;
import com.ws.gemini.wsdl.transaction.TransactionInfo;
import org.apache.axis.constants.Use;
import org.apache.axis.types.UnsignedByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service
public class CallingLayerGeminiImpl implements CallingLayer
{

   LoginService loginService;
   AccountService accountService;
   TransactionService transactionService;
   String encryDecryKey="aRXDugfr4WQpVrxu";


   public CallStatus createUser(UserAcctDetails userAcctDetails)throws Exception
   {
      loginService=new LoginServiceImpl();
      return loginService.createWebUser(userAcctDetails);
   }

   public CallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {
      loginService=new LoginServiceImpl();
      return loginService.updateWebUserEmail(userAcctDetails,newEmail);
   }

   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails)throws Exception
   {
      accountService = new AccountServiceImpl();
      CallStatus callStatus = null;
      UserAcctExt userAcctExt = new UserAcctExt();
      userAcctExt.setClientAccountID(userAcctDetails.getClientAccountID());
      callStatus = accountService.getMailingAddress(userAcctDetails, userAcctExt);
      if (callStatus.getErrorCode() == 0)
      {
         callStatus = accountService.getAccountInfo(userAcctDetails, userAcctExt);
         if (callStatus.getErrorCode() == 0)
         {
            return userAcctExt;
         }
         else
         {
            return null;
         }
      }
      else
      {
         return null;
      }
   }

   @Override
   public List<BankAcctDetails> getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception
   {
      accountService = new AccountServiceImpl();
      List<BankAcctDetails> userBankAcctDetails = null;
      userBankAcctDetails = accountService.getUserBankAcctDetails(userAcctDetails);
      return userBankAcctDetails;
   }

   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception
   {
      accountService=new AccountServiceImpl();
      return accountService.updateMailingAddress(userAcctDetails,mailingAddress);
   }

   @Override
   public CallStatus fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String accountNumber) throws Exception
   {
      transactionService=new TransactionServiceImpl();
      return transactionService.fundAccount(userAcctDetails, fundID, amount, accountNumber);
   }

   @Override
   public CallStatus fundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, double amount, String accountNumber) throws Exception
   {
      transactionService=new TransactionServiceImpl();
      return transactionService.fundTransfer(userAcctDetails, fromFundID, toFundID, amount, accountNumber);
   }


//
//   public CallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception{
//      String userId;
//      loginService=new LoginServiceImpl();
//      return loginService.loginWebUser(userAcctDetails);
//
//   }
//
//   public CallStatus isUserExist(UserAcctDetails userAcctDetails)throws Exception
//   {
//      return loginService.createWebUser(userAcctDetails);
//   }
//
//   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
//   {
//      accountService=new AccountServiceImpl();
//      return null;//accountService.getMailingAddress(userAcctDetails);
//   }

}
