package com.invessence.ws.service;

import com.invessence.ws.bean.*;
import com.invessence.ws.util.SysParameters;
import com.invessence.ws.provider.gemini.service.*;
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

   public WSCallStatus createUser(UserAcctDetails userAcctDetails)throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      loginService=new LoginServiceImpl();
      return loginService.createWebUser(userAcctDetails);
   }

   public WSCallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      loginService=new LoginServiceImpl();
      return loginService.updateWebUserEmail(userAcctDetails,newEmail);
   }

   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception{
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      accountService = new AccountServiceImpl();
      return accountService.getMailingAddress(userAcctDetails);
   }
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception{
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      accountService = new AccountServiceImpl();
      return accountService.getAccountInfo(userAcctDetails);
   }
   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails)throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      accountService = new AccountServiceImpl();
      WSCallStatus WSCallStatus = null;
      UserAcctExt userAcctExt = new UserAcctExt();
      userAcctExt.setClientAccountID(userAcctDetails.getClientAccountID());
      WSCallStatus = accountService.getMailingAddress(userAcctDetails, userAcctExt);
      if (WSCallStatus.getErrorCode() == 0)
      {
         WSCallStatus = accountService.getAccountInfo(userAcctDetails, userAcctExt);
         if (WSCallStatus.getErrorCode() == 0)
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
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      accountService = new AccountServiceImpl();
      return accountService.getUserBankAcctDetails(userAcctDetails);
   }

   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      accountService=new AccountServiceImpl();
      return accountService.updateMailingAddress(userAcctDetails,mailingAddress);
   }

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      transactionService=new TransactionServiceImpl();
      return transactionService.fundAccount(userAcctDetails, fundID, amount, bankAccountNumber);
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      transactionService=new TransactionServiceImpl();
      return transactionService.fullFundTransfer(userAcctDetails, fromFundID, toFundID, bankAccountNumber);
   }



   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception{
      String userId;
      loginService=new LoginServiceImpl();
      return loginService.loginWebUser(userAcctDetails);

   }
//
//   public WSCallStatus isUserExist(UserAcctDetails userAcctDetails)throws Exception
//   {
//      return loginService.createWebUser(userAcctDetails);
//   }
//
//   public WSCallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
//   {
//      accountService=new AccountServiceImpl();
//      return null;//accountService.getMailingAddress(userAcctDetails);
//   }

}
