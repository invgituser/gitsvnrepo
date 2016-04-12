package com.ws.gemini.service;

import com.invessence.bean.*;

/**
 * Created by abhangp on 3/28/2016.
 */
public interface LoginService
{
   public WSCallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallStatus createWebUser(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallStatus isWebUserExist(UserAcctDetails userAcctDetails) throws Exception;
   public WSCallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception;
}
