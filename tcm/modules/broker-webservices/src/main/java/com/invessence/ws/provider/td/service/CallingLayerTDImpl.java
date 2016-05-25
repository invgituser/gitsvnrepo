package com.invessence.ws.provider.td.service;

import com.invessence.ws.bean.*;
import com.invessence.ws.service.*;
import com.invessence.ws.util.NoServiceSupportException;

/**
 * Created by abhangp on 3/11/2016.
 */

public class CallingLayerTDImpl implements CallingLayer
{
   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("getMailingAddress Service Not Support");
      // return null;
   }

   @Override
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

//   @Override
//   public UserAcctExt getAcctExtInfo(UserAcctDetails userAcctDetails) throws Exception
//   {
//      return null;
//   }

   @Override
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

   @Override
   public WSCallResult fundAccount(UserAcctDetails userAcctDetails, int fundID, double amount, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      throw new NoServiceSupportException("fundAccount Service Not Support");
      //return null;
   }

   @Override
   public WSCallResult fullFundTransfer(UserAcctDetails userAcctDetails, int fromFundID, int toFundID, String bankAccountNumber, UserAcctExt userAcctExt) throws Exception
   {
      throw new NoServiceSupportException("fullFundTransfer Service Not Support");
      //return null;
   }


   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception
   {
      throw new NoServiceSupportException("updateMailingAddress Service Not Support");
      // return null;
   }

   public WSCallStatus loginUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("loginUser Service Not Support");
     // return null;
   }

   public WSCallStatus createUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("createUser Service Not Support");
      //return null;
   }

   public WSCallStatus isUserExist(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("isUserExist Service Not Support");
      //return null;
   }

   public WSCallStatus updateUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {

      throw new NoServiceSupportException("updateUserEmail Service Not Support");
      //return null;
   }

   @Override
   public WSCallStatus resetPassword(UserAcctDetails userAcctDetails, String newPwd) throws Exception
   {
      return null;
   }
}
