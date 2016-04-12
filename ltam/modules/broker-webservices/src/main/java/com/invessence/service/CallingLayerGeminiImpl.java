package com.invessence.service;

import java.util.List;

import com.invessence.bean.*;
import com.invessence.util.SysParameters;
import com.ws.gemini.service.*;
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
   public WSCallStatus fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String accountNumber) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      transactionService=new TransactionServiceImpl();
      return transactionService.fundAccount(userAcctDetails, fundID, amount, accountNumber);
   }

   @Override
   public WSCallStatus fundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, double amount, String accountNumber) throws Exception
   {
      userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
      transactionService=new TransactionServiceImpl();
      return transactionService.fundTransfer(userAcctDetails, fromFundID, toFundID, amount, accountNumber);
   }


//
//   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception{
//      String userId;
//      loginService=new LoginServiceImpl();
//      return loginService.loginWebUser(userAcctDetails);
//
//   }
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
