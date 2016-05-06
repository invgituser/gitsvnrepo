package com.invessence.ws.provider.gemini.service;

import java.math.BigDecimal;
import java.util.Date;

import com.invessence.util.*;
import com.invessence.ws.bean.*;
import com.invessence.ws.dao.*;
import com.invessence.ws.provider.gemini.wsdl.login.*;
import com.invessence.ws.util.*;
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
   Date reqTime=null;

   private WSCommonDao wsCommonDao;
   public LoginServiceImpl(WSCommonDao wsCommonDao){
      this.wsCommonDao=wsCommonDao;
   }
   public WSCallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception
   {
      logger.info("LoginServiceImpl.loginWebUser");
      logger.debug("userAcctDetails = [" + userAcctDetails + "]");

      try
      {
         reqTime=new Date();
         loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
         WebUserResult webUserResult = loginServicesSoap.shareholderLogin(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"), new BigDecimal("1"));
         logger.debug("webUserResult = " + webUserResult);
         if (webUserResult == null || webUserResult.getErrorStatus() == null)
         {
            WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), "LOGIN_USER",reqTime, SysParameters.wsResIssueMsg);
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
         }
         else
         {
            WSRequest wsRequest = new WSRequest("S", userAcctDetails.getClientAccountID(), "LOGIN_USER", reqTime, webUserResult.getErrorStatus().getErrorMessage());
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallStatus(webUserResult.getErrorStatus().getErrorCode(), webUserResult.getErrorStatus().getErrorMessage());
         }
      }
      catch (Exception e)
      {
         WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), "LOGIN_USER",reqTime, e.getMessage());
         logger.debug("wsRequest = " + wsRequest);
         wsCommonDao.insertWSRequest(wsRequest);
         throw e;
      }
   }

   public WSCallStatus createWebUser(UserAcctDetails userAcctDetails)throws Exception
   {
      logger.info("LoginServiceImpl.createWebUser");
      logger.debug("userAcctDetails = [" + userAcctDetails + "]");
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
try{
   reqTime=new Date();
      WebUserResult webUserResult = loginServicesSoap.createShareholderWebUser
         (new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(), EncryDecryAES.decrypt(userAcctDetails.getSsn(), SysParameters.encryDecryKey),
         userAcctDetails.getMailZipCode(), userAcctDetails.getEmail(), userAcctDetails.getSecurityQuestion(),
         userAcctDetails.getSecurityAnswer(), new UnsignedByte("1"));
      logger.debug("webUserResult = " + webUserResult);
      if (webUserResult ==null || webUserResult.getErrorStatus()==null)
      {
         WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.CREATE_USER.toString(),reqTime, SysParameters.wsResIssueMsg);
         wsCommonDao.insertWSRequest(wsRequest);
         return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
      }
      else
      {
         WSRequest wsRequest = new WSRequest(webUserResult.getErrorStatus().getErrorCode()==0?"S":"F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.CREATE_USER.toString(),reqTime, webUserResult.getErrorStatus().getErrorMessage());
         wsCommonDao.insertWSRequest(wsRequest);
         return new WSCallStatus(webUserResult.getErrorStatus().getErrorCode(), webUserResult.getErrorStatus().getErrorMessage());
      }
}
catch (Exception e)
{
   WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.CREATE_USER.toString(),reqTime, e.getMessage());
   wsCommonDao.insertWSRequest(wsRequest);
   throw e;
}
   }

   public WSCallStatus isWebUserExist(UserAcctDetails userAcctDetails)throws Exception
   {

      return null;
   }

   public WSCallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception{
      logger.info("LoginServiceImpl.updateWebUserEmail");
      logger.debug("userAcctDetails = [" + userAcctDetails + "], newEmail = [" + newEmail + "]");
      try{
         reqTime=new Date();
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
      Status status = loginServicesSoap.updateWebUser(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         new WebUserRequest(newEmail));
      logger.debug("status = " + status);
      if (status==null)
      {
         WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.EMAIL_UPDATE.toString(),reqTime, SysParameters.wsResIssueMsg);
         wsCommonDao.insertWSRequest(wsRequest);
         return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
      }
      else
      {
         WSRequest wsRequest = new WSRequest(status.getErrorCode()==0?"S":"F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.EMAIL_UPDATE.toString(),reqTime, status.getErrorMessage());
         wsCommonDao.insertWSRequest(wsRequest);
         return new WSCallStatus(status.getErrorCode(), status.getErrorMessage());
      }
   }
   catch (Exception e)
   {
      WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.EMAIL_UPDATE.toString(),reqTime, e.getMessage());
      wsCommonDao.insertWSRequest(wsRequest);
      throw e;
   }
   }

   public WSCallStatus updatePasswordWithNoAuthentication(UserAcctDetails userAcctDetails,String newPwd) throws Exception{
      logger.info("LoginServiceImpl.updatePasswordWithNoAuthentication");
      logger.debug("userAcctDetails = [" + userAcctDetails + "], newPwd = [" + newPwd + "]");
      try
      {
         reqTime=new Date();
         loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
         WebUserResult webUserResult = loginServicesSoap.updatePasswordWithNoAuthentication(
            new AuthenticateLogin(userAcctDetails.getUserID(), "", userAcctDetails.getFundGroupName(), "00"),
            EncryDecryAES.decrypt(userAcctDetails.getSsn(), SysParameters.encryDecryKey), userAcctDetails.getSecurityQuestion(), userAcctDetails.getSecurityAnswer(),
            newPwd, newPwd);

//         WebUserResult webUserResult = loginServicesSoap.updatePasswordWithNoAuthentication(
//            new AuthenticateLogin(userAcctDetails.getUserID(), "", userAcctDetails.getFundGroupName(), "00"),
//            EncryDecryAES.decrypt(userAcctDetails.getSsn(), SysParameters.encryDecryKey), userAcctDetails.getSecurityQuestion(), userAcctDetails.getSecurityAnswer(),
//            EncryDecryAES.decrypt(newPwd, SysParameters.encryDecryKey), EncryDecryAES.decrypt(newPwd, SysParameters.encryDecryKey));
         logger.debug("status = " + webUserResult);
         if (webUserResult == null || webUserResult.getErrorStatus()==null)
         {
            WSRequest wsRequest = new WSRequest("F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.UPDATE_PWD_WITH_NOAUTH.toString(),reqTime, SysParameters.wsResIssueMsg);
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
         }
         else
         {
            WSRequest wsRequest = new WSRequest(webUserResult.getErrorStatus().getErrorCode()==0?"S":"F", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.UPDATE_PWD_WITH_NOAUTH.toString(),reqTime, webUserResult.getErrorStatus().getErrorMessage());
            wsCommonDao.insertWSRequest(wsRequest);
            return new WSCallStatus(webUserResult.getErrorStatus().getErrorCode(), webUserResult.getErrorStatus().getErrorMessage());
         }
      } catch (Exception e)
         {
            WSRequest wsRequest = new WSRequest("E", userAcctDetails.getClientAccountID(), WSConstants.BrokerWebServiceOperations.UPDATE_PWD_WITH_NOAUTH.toString(),reqTime, e.getMessage());
            wsCommonDao.insertWSRequest(wsRequest);
            throw e;
         }
   }
}
