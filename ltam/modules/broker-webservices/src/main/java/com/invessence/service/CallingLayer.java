package com.invessence.service;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/22/2016.
 */
public interface CallingLayer
{
   public CallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus createWebUser(UserAcctDetails userAcctDetails) throws Exception;
//   public void createPendingWebUser(UserAcctDetails userAcctDetails);
   public CallStatus isWebUserExist(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception;
   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception;
}
