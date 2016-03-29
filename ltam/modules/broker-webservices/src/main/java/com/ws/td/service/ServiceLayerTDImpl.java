package com.ws.td.service;

import com.invessence.bean.*;
import com.invessence.service.*;
import com.invessence.util.NoServiceSupportException;

/**
 * Created by abhangp on 3/11/2016.
 */

public class ServiceLayerTDImpl implements CallingLayer
{
   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("getMailingAddress Service Not Support");
      // return null;
   }

   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("updateMailingAddress Service Not Support");
      // return null;
   }

   public CallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("loginWebUser Service Not Support");
     // return null;
   }

   public CallStatus createWebUser(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("createWebUser Service Not Support");
      //return null;
   }

   public CallStatus isWebUserExist(UserAcctDetails userAcctDetails) throws Exception
   {
      throw new NoServiceSupportException("isWebUserExist Service Not Support");
      //return null;
   }

   public CallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {

      throw new NoServiceSupportException("updateWebUserEmail Service Not Support");
      //return null;
   }
}
