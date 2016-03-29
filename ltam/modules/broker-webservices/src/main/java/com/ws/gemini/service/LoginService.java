package com.ws.gemini.service;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface LoginService
{
   public CallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus createWebUser(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus isWebUserExist(UserAcctDetails userAcctDetails) throws Exception;
   public CallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception;
}
