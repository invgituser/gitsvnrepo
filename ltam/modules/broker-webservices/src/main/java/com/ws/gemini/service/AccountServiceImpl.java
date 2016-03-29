package com.ws.gemini.service;

import java.math.BigDecimal;
import com.invessence.bean.*;
import com.ws.gemini.wsdl.account.*;
import org.apache.axis.types.UnsignedByte;

/**
 * Created by abhangp on 3/28/2016.
 */
public class AccountServiceImpl implements AccountService
{
   AccountServicesLocator servicesLocator = new AccountServicesLocator();
   AccountServicesSoap_PortType servicesSoap = null;

   String encryDecryKey="aRXDugfr4WQpVrxu";

   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails) throws Exception{
      servicesSoap = servicesLocator.getAccountServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");
      /*int mailingAddressId,
           java.lang.String nameLines,
           java.lang.String addressLines,
           java.lang.String postalZip,
           short countryCode,
           java.lang.String voicePhone,
           java.lang.String altPhone,
           java.lang.String faxPhone,
           java.lang.String emailAddress,
           org.apache.axis.types.UnsignedByte mailingAddressType,
           java.lang.String entityIdentifier)*/
      new MailingAddressesRequest();
      Status status = servicesSoap.updateMailingAddresses(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         new MailingAddressesRequest());
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

   public CallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      servicesSoap = servicesLocator.getAccountServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");
      /*int mailingAddressId,
           java.lang.String nameLines,
           java.lang.String addressLines,
           java.lang.String postalZip,
           short countryCode,
           java.lang.String voicePhone,
           java.lang.String altPhone,
           java.lang.String faxPhone,
           java.lang.String emailAddress,
           org.apache.axis.types.UnsignedByte mailingAddressType,
           java.lang.String entityIdentifier)*/
      new MailingAddressesRequest();
      MailingAddressesResult mailingAddressesResult= servicesSoap.getMailingAddress(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         new UnsignedByte(1),new UnsignedByte(1),true);
      System.out.println(mailingAddressesResult.toString());
//      if (status==null)
//      {
//         return null;
//      }
//      else
//      {
//         return new CallStatus(status.getErrorCode(),status.getErrorMessage());
//      }
      return null;
   }


}
