package com.ws.gemini.service;

import java.math.BigDecimal;
import java.util.*;

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

   public CallStatus getAccountInfo(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception
   {
      UserAcctDetails acctDetails=null;
      servicesSoap = servicesLocator.getAccountServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      AccountInfoResult accountInfoResult= servicesSoap.getAccountInfo(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),true);
      System.out.println(accountInfoResult.toString());
      if(accountInfoResult==null || accountInfoResult.getErrorStatus()==null){
         return null;
      }else{
         userAcctExt.setDateOfBirth(accountInfoResult.getDateOfBirth().getTime());
         userAcctExt.setAccountType(""+accountInfoResult.getAccountType());
         return new CallStatus(accountInfoResult.getErrorStatus().getErrorCode(),accountInfoResult.getErrorStatus().getErrorMessage());
      }

//      if(accountInfoResult==null)
//      {
//         return acctDetails;
//      }else{
//         acctDetails=userAcctDetails;
//         userAcctExt.setDateOfBirth(accountInfoResult.getDateOfBirth().getTime());
//         userAcctExt.setAccountType(""+accountInfoResult.getAccountType());
//         return acctDetails;
//      }
   }

   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception{
      servicesSoap = servicesLocator.getAccountServicesSoap();
      //getAccountInfo(userAcctDetails);
      //UserAddress userAddress=getMailingAddress(userAcctDetails);

//      if(userAddress==null){
//         return  null;
//      }else
//      {
         System.out.println("AuthenticateLogin:[UserId:" + userAcctDetails.getUserID() + ", Password:" + userAcctDetails.getPwd() + ", FundGroupName:" + userAcctDetails.getFundGroupName() + ", AllowableShareClassList:00]");

         MailingAddressesRequest mailingAddressesRequest = new MailingAddressesRequest(mailingAddress.getMailingAddressId(), mailingAddress.getNameLines(), mailingAddress.getAddressLines(),
                                                                                       mailingAddress.getPostalZip(), mailingAddress.getCountryCode(), mailingAddress.getVoicePhone(),
                                                                                       mailingAddress.getAltPhone(), mailingAddress.getFaxPhone(), mailingAddress.getEmailAddress(),
                                                                                       new UnsignedByte(mailingAddress.getMailingAddressType()), userAcctDetails.getClientAccountID());
         Status status = servicesSoap.updateMailingAddresses(
            new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
            userAcctDetails.getClientAccountID(),
            new MailingAddressesRequest());
         System.out.println(status.toString());
         if (status == null)
         {
            return null;
         }
         else
         {
            return new CallStatus(status.getErrorCode(), status.getErrorMessage());
         }
//      }
   }

   public CallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception
   {
      UserAddress userAddress=null;
      MailingAddressesResult mailingAddressesResult=null;
      servicesSoap = servicesLocator.getAccountServicesSoap();

      System.out.println("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      mailingAddressesResult= servicesSoap.getMailingAddress(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         new UnsignedByte(1),new UnsignedByte(1),true);
      System.out.println(mailingAddressesResult.toString());
      if (mailingAddressesResult == null)
      {
         return null;
      }
      else
      {
         userAcctExt.setMailingAddressId(mailingAddressesResult.getMailingAddressId());
         userAcctExt.setMailingAddressType(""+mailingAddressesResult.getMailingAddressType());
         return new CallStatus(mailingAddressesResult.getErrorStatus().getErrorCode(), mailingAddressesResult.getErrorStatus().getErrorMessage());
      }
      //      if(mailingAddressesResult==null)
//      {
//         return userAddress;
//      }else{
//         userAcctExt.setMailingAddressId(mailingAddressesResult.getMailingAddressId());
//         userAcctExt.setAccountType(""+mailingAddressesResult.getMailingAddressType());
//         return new UserAddress(mailingAddressesResult.getNameLines(), mailingAddressesResult.getAddressLines(),
//                                mailingAddressesResult.getPostalZip(), mailingAddressesResult.getCountryCode(),
//                                mailingAddressesResult.getVoicePhone(),mailingAddressesResult.getAltPhone(),
//                                mailingAddressesResult.getFaxPhone(),mailingAddressesResult.getEmailAddress(),
//                                mailingAddressesResult.getMailingAddressId(),""+mailingAddressesResult.getMailingAddressType(),
//                                mailingAddressesResult.getEntityIdentifier());
//      }


   }

   @Override
   public List<BankAcctDetails> getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception
   {

      List<BankAcctDetails> bankAcctDetailsList=new ArrayList<>();
      AchPayeeResult arrAchPayeeResult[]=null;
      servicesSoap = servicesLocator.getAccountServicesSoap();

      AchPayeeCollectionResult achPayeeCollectionResult= servicesSoap.getAchPayeeCollection(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
                                                                                            userAcctDetails.getClientAccountID());

      System.out.println("AccountServiceImpl.getAchPayeeCollection");
      System.out.println("achPayeeCollectionResult.getAchPayee().length = " + achPayeeCollectionResult.getAchPayee().length);
      if(achPayeeCollectionResult==null || achPayeeCollectionResult.getAchPayee()==null){
         return  null;
      }else if(achPayeeCollectionResult.getAchPayee().length>0){
         arrAchPayeeResult= achPayeeCollectionResult.getAchPayee();
         for (int i=0; i<arrAchPayeeResult.length; i++) {
            AchPayeeResult achPayeeResult=arrAchPayeeResult[i];
            BankAcctDetails bankAcctDetails= new BankAcctDetails(achPayeeResult.getAccountNumber(),achPayeeResult.getAchPayeeId(),achPayeeResult.getBankName(),achPayeeResult.getBankRoutingNumber(),achPayeeResult.getBankAccountNumber()
            ,achPayeeResult.getNameOnAccount(),Integer.parseInt(achPayeeResult.getBankAccountType().toString()),achPayeeResult.getAchPayeeId(),achPayeeResult.getAchPayeeId(),
                                achPayeeResult.getPowerAgentUserId(),achPayeeResult.getIsAccountPayeeBeingUsed());

            bankAcctDetailsList.add(bankAcctDetails);
         }
         System.out.println("achPayeeResults[0] = " + arrAchPayeeResult.toString());
         return bankAcctDetailsList;

      }
      return null;
   }

   public AchPayeeResult getAchPayeeCollection(UserAcctDetails userAcctDetails, String accountNumber) throws Exception
   {
      AchPayeeResult achPayeeResult=null;
      servicesSoap = servicesLocator.getAccountServicesSoap();

      AchPayeeCollectionResult achPayeeCollectionResult= servicesSoap.getAchPayeeCollection(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
                                   userAcctDetails.getClientAccountID());

      System.out.println("AccountServiceImpl.getAchPayeeCollection");
      System.out.println("achPayeeCollectionResult.getAchPayee().length = " + achPayeeCollectionResult.getAchPayee().length);
      if(achPayeeCollectionResult==null || achPayeeCollectionResult.getAchPayee()==null){
         return  null;
      }else if(achPayeeCollectionResult.getAchPayee().length>0){
         for (int i=0; i<achPayeeCollectionResult.getAchPayee().length; i++)
         {
            achPayeeResult = achPayeeCollectionResult.getAchPayee()[i];
            if(achPayeeResult.getAccountNumber().trim().equals(accountNumber.trim())){
               return achPayeeResult;
            }
            System.out.println("achPayeeResults[0] = " + achPayeeResult.toString());
         }
      }
      return achPayeeResult;
   }


}
