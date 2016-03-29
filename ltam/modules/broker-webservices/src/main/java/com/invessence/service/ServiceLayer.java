package com.invessence.service;

import com.invessence.bean.CallStatus;

/**
 * Created by abhangp on 3/11/2016.
 */
public interface ServiceLayer
{
   public CallStatus loginWebUser(String clientAccountID);
   public CallStatus createWebUser(String clientAccountID,String securityQuestion,String securityAnswer);
   public void createPendingWebUser();
   public CallStatus isWebUserExist(String userId);
   public CallStatus updateEmail(String clientAccountID, String newEmail);
   public CallStatus updateMailingAddress(String clientAccountID);
   public CallStatus getMailingAddress(String clientAccountID);
}
