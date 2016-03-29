package com.ws.gemini.service;

import java.math.BigDecimal;

import com.invessence.bean.*;
import com.invessence.util.EncryDecryAES;
import com.ws.gemini.wsdl.login.*;
import org.apache.axis.types.UnsignedByte;

/**
 * Created by abhangp on 3/28/2016.
 */
public class LoginServiceImpl implements LoginService
{
   LoginServicesLocator loginServicesLocator = new LoginServicesLocator();
   LoginServicesSoap_PortType loginServicesSoap = null;

   String encryDecryKey="aRXDugfr4WQpVrxu";
   //String encryDecryKey="GEMINI-KEY";

   public CallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception{
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      WebUserResult webUserResult = loginServicesSoap.shareholderLogin(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"), new BigDecimal("1"));
      System.out.println(webUserResult.toString());
      if (webUserResult.getErrorStatus()==null)
      {
         return null;
      }
      else
      {
         return new CallStatus(webUserResult.getErrorStatus().getErrorCode(),webUserResult.getErrorStatus().getErrorMessage());
      }
   }

   public CallStatus createWebUser(UserAcctDetails userAcctDetails)throws Exception
   {

//      ExistResult existResult = servicesSoap.isWebUserExists(new AuthenticateLogin(userAcctDetails.getUserID(), "", userAcctDetails.getFundGroupName(), ""));
//      System.out.println(existResult.toString());
      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]" +
                            "accountNumber:"+userAcctDetails.getClientAccountID()+", ssnorTin:"+ EncryDecryAES.decrypt(userAcctDetails.getSsn(), encryDecryKey)+
                            ",zipCode:"+userAcctDetails.getMailZipCode()+", emailAddress:"+userAcctDetails.getEmail()+", securityQuestion:"+userAcctDetails.getSecurityQuestion()+", " +
                            "securityAnswer:"+userAcctDetails.getSecurityAnswer()+", accessLinkedAccounts:"+new UnsignedByte("1"));

//      if (existResult.isIsExist() == false)
      String userId;
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
//      {
         WebUserResult webUserResult = loginServicesSoap.createShareholderWebUser
            (new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
             userAcctDetails.getClientAccountID(), EncryDecryAES.decrypt(userAcctDetails.getSsn(), encryDecryKey),
             userAcctDetails.getMailZipCode(), userAcctDetails.getEmail(), userAcctDetails.getSecurityQuestion(),
             userAcctDetails.getSecurityAnswer(), new UnsignedByte("1"));
         System.out.println(webUserResult.toString());
         if (webUserResult.getErrorStatus()==null)
         {
            return null;
         }
         else
         {
            return new CallStatus(webUserResult.getErrorStatus().getErrorCode(),webUserResult.getErrorStatus().getErrorMessage());
         }
//      }
//      else if (existResult.isIsExist() == true)
//      {
//         return new CallStatus(1,"User already exist");
//      }
 //     return null;
   }

   public CallStatus isWebUserExist(UserAcctDetails userAcctDetails)throws Exception
   {

      return null;
   }

   public CallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception{

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]" +
         "WebUserRequest [EMailAddress:"+newEmail+"]");
      loginServicesSoap = loginServicesLocator.getLoginServicesSoap();
      Status status = loginServicesSoap.updateWebUser(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         new WebUserRequest(newEmail));
      System.out.println(status.toString());
      if (status==null)
      {
         return null;
      }
      else
      {
         return new CallStatus(status.getErrorCode(),status.getErrorMessage());
      }
   }
}
