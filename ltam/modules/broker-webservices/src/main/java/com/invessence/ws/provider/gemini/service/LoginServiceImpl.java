package com.invessence.ws.provider.gemini.service;

import java.math.BigDecimal;

import com.invessence.util.*;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.gemini.wsdl.login.*;
import com.invessence.ws.util.SysParameters;
import org.apache.axis.types.UnsignedByte;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 3/28/2016.
 */
public class LoginServiceImpl implements LoginService
{
   private static final Logger logger = Logger.getLogger(LoginServiceImpl.class);
   LoginServicesLocator loginServicesLocator = new LoginServicesLocator();
   LoginServicesSoap_PortType loginServicesSoap = null;

   public WSCallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception{
      System.out.println("LoginServiceImpl.loginWebUser");
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      WebUserResult webUserResult = loginServicesSoap.shareholderLogin(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"), new BigDecimal("1"));
      System.out.println("webUserResult = " + webUserResult.toString());
      if (webUserResult==null || webUserResult.getErrorStatus()==null)
      {
         return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
      }
      else
      {
         return new WSCallStatus(webUserResult.getErrorStatus().getErrorCode(), webUserResult.getErrorStatus().getErrorMessage());
      }
   }

   public WSCallStatus createWebUser(UserAcctDetails userAcctDetails)throws Exception
   {
      System.out.println("LoginServiceImpl.createWebUser");
      System.out.println("userAcctDetails = [" + userAcctDetails + "]");
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();

      WebUserResult webUserResult = loginServicesSoap.createShareholderWebUser
         (new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(), EncryDecryAES.decrypt(userAcctDetails.getSsn(), SysParameters.encryDecryKey),
         userAcctDetails.getMailZipCode(), userAcctDetails.getEmail(), userAcctDetails.getSecurityQuestion(),
         userAcctDetails.getSecurityAnswer(), new UnsignedByte("1"));
      System.out.println("webUserResult = " + webUserResult.toString());
      if (webUserResult ==null || webUserResult.getErrorStatus()==null)
      {
         return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
      }
      else
      {
         return new WSCallStatus(webUserResult.getErrorStatus().getErrorCode(), webUserResult.getErrorStatus().getErrorMessage());
      }
   }

   public WSCallStatus isWebUserExist(UserAcctDetails userAcctDetails)throws Exception
   {

      return null;
   }

   public WSCallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception{
      System.out.println("LoginServiceImpl.updateWebUserEmail");
      System.out.println("userAcctDetails = [" + userAcctDetails + "], newEmail = [" + newEmail + "]");
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
      Status status = loginServicesSoap.updateWebUser(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         new WebUserRequest(newEmail));
      System.out.println("status = " + status);
      if (status==null)
      {
         return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
      }
      else
      {
         return new WSCallStatus(status.getErrorCode(), status.getErrorMessage());
      }
   }
}
