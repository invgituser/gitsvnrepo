package com.invessence.ws.service;

import com.invessence.service.util.ServiceParameters;
import com.invessence.ws.bean.*;
import com.invessence.ws.dao.WSCommonDao;
import com.invessence.ws.provider.gemini.service.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service("Gemini")
public class CallingLayerGeminiImpl implements CallingLayer
{
   LoginService loginService;
   AccountService accountService;
   TransactionService transactionService;

   WSCommonDao wsCommonDao;
   public CallingLayerGeminiImpl(){
      System.out.println("CallingLayerGeminiImpl.CallingLayerGeminiImpl");
   }
   public CallingLayerGeminiImpl(WSCommonDao wsCommonDao){
      this.wsCommonDao=wsCommonDao;
   }


   @Override
   public WSCallResult processDCRequest(Long acctNum, int eventNum) throws Exception
   {
      return null;
   }

   @Override
   public WSCallResult moveMoney(Long acctNum, Integer reqId)
   {
      return null;
   }

   @Override
   public WSCallResult fundTransfer(Long acctNum, Integer reqId)
   {
      return null;
   }


   public WSCallStatus createUser(UserAcctDetails userAcctDetails)throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      loginService=new LoginServiceImpl(wsCommonDao);
      return loginService.createWebUser(userAcctDetails);
   }

   public WSCallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      loginService=new LoginServiceImpl(wsCommonDao);
      return loginService.updateWebUserEmail(userAcctDetails,newEmail);
   }

   public WSCallStatus resetPassword(UserAcctDetails userAcctDetails, String newPwd) throws Exception{
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      loginService = new LoginServiceImpl(wsCommonDao);
      return loginService.updatePasswordWithNoAuthentication(userAcctDetails, newPwd);
   }

   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception{
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      accountService = new AccountServiceImpl(wsCommonDao);
      return accountService.getMailingAddress(userAcctDetails);
   }
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception{
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      accountService = new AccountServiceImpl(wsCommonDao);
      return accountService.getAccountInfo(userAcctDetails);
   }

//   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails)throws Exception
//   {
//      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
//      accountService = new AccountServiceImpl(wsCommonDao);
//      WSCallStatus WSCallStatus = null;
//      UserAcctExt userAcctExt = new UserAcctExt();
//      userAcctExt.setClientAccountID(userAcctDetails.getClientAccountID());
//      WSCallStatus = accountService.getMailingAddress(userAcctDetails, userAcctExt);
//      if (WSCallStatus.getErrorCode() == 0)
//      {
//         WSCallStatus = accountService.getAccountInfo(userAcctDetails, userAcctExt);
//         if (WSCallStatus.getErrorCode() == 0)
//         {
//            return userAcctExt;
//         }
//         else
//         {
//            return null;
//         }
//      }
//      else
//      {
//         return null;
//      }
//   }

   @Override
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      accountService = new AccountServiceImpl(wsCommonDao);
      return accountService.getUserBankAcctDetails(userAcctDetails);
   }

   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      accountService=new AccountServiceImpl(wsCommonDao);
      return accountService.updateMailingAddress(userAcctDetails,mailingAddress);
   }

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      transactionService=new TransactionServiceImpl(wsCommonDao);
      return transactionService.fundAccount(userAcctDetails, fundID, amount, bankAccountNumber,userAcctExt);
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      transactionService=new TransactionServiceImpl(wsCommonDao);
      return transactionService.fullFundTransfer(userAcctDetails, fromFundID, toFundID, bankAccountNumber, userAcctExt);
   }



   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception{
      userAcctDetails.setFundGroupName(ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME);
      loginService=new LoginServiceImpl(wsCommonDao);
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
