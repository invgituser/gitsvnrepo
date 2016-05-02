package com.invessence.ws.provider.gemini.service;

import java.util.*;

import com.invessence.ws.bean.*;
import com.invessence.ws.util.SysParameters;
import com.invessence.ws.provider.gemini.wsdl.account.*;
import org.apache.axis.types.UnsignedByte;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 3/28/2016.
 */
public class AccountServiceImpl implements AccountService
{
   private static final Logger logger = Logger.getLogger(AccountServiceImpl.class);
   AccountServicesLocator servicesLocator = new AccountServicesLocator();
   AccountServicesSoap_PortType servicesSoap = null;

   String encryDecryKey="aRXDugfr4WQpVrxu";

   public WSCallStatus getAccountInfo(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception
   {
      logger.info("AccountServiceImpl.getAccountInfo");
      servicesSoap = servicesLocator.getAccountServicesSoap();
      logger.debug("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      AccountInfoResult accountInfoResult= servicesSoap.getAccountInfo(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),true);
      logger.debug("accountInfoResult = " + accountInfoResult);
      if(accountInfoResult==null || accountInfoResult.getErrorStatus()==null){
         return new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg);
      }else{
         userAcctExt.setDateOfBirth(accountInfoResult.getDateOfBirth().getTime());
         userAcctExt.setAccountType(""+accountInfoResult.getAccountType());
         return new WSCallStatus(accountInfoResult.getErrorStatus().getErrorCode(), accountInfoResult.getErrorStatus().getErrorMessage());
      }
   }

   public WSCallStatus updateMailingAddress(UserAcctDetails userAcctDetails, UserAddress mailingAddress) throws Exception{
      logger.info("AccountServiceImpl.updateMailingAddress");
      servicesSoap = servicesLocator.getAccountServicesSoap();

      logger.info("Before "+getMailingAddress(userAcctDetails,new UserAcctExt()));
      logger.info("AuthenticateLogin:[UserId:" + userAcctDetails.getUserID() + ", Password:" + userAcctDetails.getPwd() + ", FundGroupName:" + userAcctDetails.getFundGroupName() + ", AllowableShareClassList:00]");

      MailingAddressesRequest mailingAddressesRequest = new MailingAddressesRequest(mailingAddress.getMailingAddressId(), mailingAddress.getFirstName()+" "+mailingAddress.getMiddleName()+" "+mailingAddress.getLastName(),
                                                                                    mailingAddress.getAddressLine1()+" "+mailingAddress.getAddressLine2()+" "+mailingAddress.getAddressLine3()+" "+mailingAddress.getCity()+" "+mailingAddress.getState(),
                                                                                       mailingAddress.getPostalZip(), mailingAddress.getCountryCode(), mailingAddress.getVoicePhone(),
                                                                                       mailingAddress.getAltPhone(), mailingAddress.getFaxPhone(), mailingAddress.getEmailAddress(),
                                                                                       new UnsignedByte(mailingAddress.getMailingAddressType()), userAcctDetails.getClientAccountID());
      logger.info("mailingAddressesRequest = " + mailingAddressesRequest);
      Status status = servicesSoap.updateMailingAddresses(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         mailingAddressesRequest);
      logger.info("After "+getMailingAddress(userAcctDetails,new UserAcctExt()));
      logger.info(status);
      if (status == null)
      {
         return new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg);
      }
      else
      {
         return new WSCallStatus(status.getErrorCode(), status.getErrorMessage());
      }
   }

   public WSCallStatus addAccountForCommonMailing(UserAcctDetails userAcctDetails) throws Exception
   {
      return null;
   }

   public WSCallStatus getMailingAddress(UserAcctDetails userAcctDetails, UserAcctExt userAcctExt) throws Exception
   {
      logger.info("AccountServiceImpl.getMailingAddress");
      servicesSoap = servicesLocator.getAccountServicesSoap();

      logger.info("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      MailingAddressesResult mailingAddressesResult= servicesSoap.getMailingAddress(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         new UnsignedByte(1),new UnsignedByte(1),true);
      logger.info("mailingAddressesResult = " + mailingAddressesResult);
      if (mailingAddressesResult == null)
      {
         return new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg);
      }
      else
      {
         userAcctExt.setMailingAddressId(mailingAddressesResult.getMailingAddressId());
         userAcctExt.setMailingAddressType(""+mailingAddressesResult.getMailingAddressType());
         return new WSCallStatus(mailingAddressesResult.getErrorStatus().getErrorCode(), mailingAddressesResult.getErrorStatus().getErrorMessage());
      }
   }
   public WSCallResult getMailingAddress(UserAcctDetails userAcctDetails) throws Exception{
      logger.info("AccountServiceImpl.getMailingAddress");
      servicesSoap = servicesLocator.getAccountServicesSoap();

      logger.info("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      MailingAddressesResult mailingAddressesResult= servicesSoap.getMailingAddress(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),
         new UnsignedByte(1),new UnsignedByte(1),true);
      logger.info("mailingAddressesResult = " + mailingAddressesResult);
      if (mailingAddressesResult == null || mailingAddressesResult.getErrorStatus()==null)
      {
         return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg),null);
      }else if(mailingAddressesResult.getErrorStatus().getErrorCode()==0){
         UserAcctExt userAcctExt=new UserAcctExt();
         userAcctExt.setMailingAddressId(mailingAddressesResult.getMailingAddressId());
         userAcctExt.setMailingAddressType(""+mailingAddressesResult.getMailingAddressType());
         return new WSCallResult(new WSCallStatus(mailingAddressesResult.getErrorStatus().getErrorCode(), mailingAddressesResult.getErrorStatus().getErrorMessage()),userAcctExt);
      }
      else
      {
         return new WSCallResult(new WSCallStatus(mailingAddressesResult.getErrorStatus().getErrorCode(), mailingAddressesResult.getErrorStatus().getErrorMessage()),null);
      }
   }
   public WSCallResult getAccountInfo(UserAcctDetails userAcctDetails) throws Exception{
      logger.info("AccountServiceImpl.getAccountInfo");
      servicesSoap = servicesLocator.getAccountServicesSoap();
      logger.debug("AuthenticateLogin:[UserId:"+userAcctDetails.getUserID()+", Password:"+userAcctDetails.getPwd()+", FundGroupName:"+userAcctDetails.getFundGroupName()+", AllowableShareClassList:00]");

      AccountInfoResult accountInfoResult= servicesSoap.getAccountInfo(
         new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
         userAcctDetails.getClientAccountID(),true);
      logger.debug("accountInfoResult = " + accountInfoResult);
      if(accountInfoResult==null || accountInfoResult.getErrorStatus()==null){
         return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg),null);
      }else if(accountInfoResult.getErrorStatus().getErrorCode()==0){
         UserAcctExt userAcctExt=new UserAcctExt();
         userAcctExt.setDateOfBirth(accountInfoResult.getDateOfBirth().getTime());
         userAcctExt.setAccountType(""+accountInfoResult.getAccountType());
         return new WSCallResult(new WSCallStatus(accountInfoResult.getErrorStatus().getErrorCode(), accountInfoResult.getErrorStatus().getErrorMessage()),userAcctExt);
      }else{
         return new WSCallResult(new WSCallStatus(accountInfoResult.getErrorStatus().getErrorCode(), accountInfoResult.getErrorStatus().getErrorMessage()),null);
      }
   }

   @Override
   public WSCallResult getUserBankAcctDetails(UserAcctDetails userAcctDetails)throws Exception
   {
      logger.info("AccountServiceImpl.getUserBankAcctDetails");
      List<BankAcctDetails> bankAcctDetailsList=null;
      AchPayeeResult arrAchPayeeResult[]=null;
      servicesSoap = servicesLocator.getAccountServicesSoap();
      AchPayeeCollectionResult achPayeeCollectionResult= servicesSoap.getAchPayeeCollection(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
                                                                                            userAcctDetails.getClientAccountID());
      logger.info("SOAP"+servicesSoap);
      //_call.getMessageContext().getRequestMessage().getSOAPPartAsString();
      logger.info("achPayeeCollectionResult = " + achPayeeCollectionResult);
      if(achPayeeCollectionResult==null || achPayeeCollectionResult.getAchPayee()==null){
         return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode,SysParameters.wsResIssueMsg),bankAcctDetailsList);
      }else if(achPayeeCollectionResult.getAchPayee()==null || achPayeeCollectionResult.getAchPayee().length==0){
         return new WSCallResult(new WSCallStatus(achPayeeCollectionResult.getErrorStatus().getErrorCode(),achPayeeCollectionResult.getErrorStatus().getErrorMessage()),bankAcctDetailsList);
      } else if(achPayeeCollectionResult.getAchPayee().length>0){
         bankAcctDetailsList=new ArrayList<>();
         arrAchPayeeResult= achPayeeCollectionResult.getAchPayee();
         for (int i=0; i<arrAchPayeeResult.length; i++) {
            AchPayeeResult achPayeeResult=arrAchPayeeResult[i];
            BankAcctDetails bankAcctDetails= new BankAcctDetails(achPayeeResult.getAccountNumber(),achPayeeResult.getAchPayeeId(),achPayeeResult.getBankName(),achPayeeResult.getBankRoutingNumber(),achPayeeResult.getBankAccountNumber()
            ,achPayeeResult.getNameOnAccount(),Integer.parseInt(achPayeeResult.getBankAccountType().toString()),achPayeeResult.getAchPayeeId(),achPayeeResult.getAchPayeeId(),
                                achPayeeResult.getPowerAgentUserId(),achPayeeResult.getIsAccountPayeeBeingUsed());

            bankAcctDetailsList.add(bankAcctDetails);
         }
         logger.info("achPayeeResults = " + arrAchPayeeResult);
         return new WSCallResult(new WSCallStatus(achPayeeCollectionResult.getErrorStatus().getErrorCode(),achPayeeCollectionResult.getErrorStatus().getErrorMessage()),bankAcctDetailsList);

      }
      return new WSCallResult(new WSCallStatus(achPayeeCollectionResult.getErrorStatus().getErrorCode(),achPayeeCollectionResult.getErrorStatus().getErrorMessage()),null);

   }

   public AchPayeeResult getAchPayeeCollection(UserAcctDetails userAcctDetails, String bankAccountNumber) throws Exception
   {
      logger.info("AccountServiceImpl.getAchPayeeCollection");
      servicesSoap = servicesLocator.getAccountServicesSoap();
      AchPayeeResult achPayeeResult=null;

      AchPayeeCollectionResult achPayeeCollectionResult= servicesSoap.getAchPayeeCollection(new AuthenticateLogin(userAcctDetails.getUserID(), userAcctDetails.getPwd(), userAcctDetails.getFundGroupName(), "00"),
                                   userAcctDetails.getClientAccountID());
      logger.info("achPayeeCollectionResult = " + achPayeeCollectionResult);
      if(achPayeeCollectionResult==null || achPayeeCollectionResult.getAchPayee()==null){
         return  null;
      }else if(achPayeeCollectionResult.getAchPayee().length>0){
         for (int i=0; i<achPayeeCollectionResult.getAchPayee().length; i++)
         {
            AchPayeeResult achPayee = achPayeeCollectionResult.getAchPayee()[i];
            if(achPayee.getBankAccountNumber().trim().equals(bankAccountNumber.trim())){
               return achPayee;
            }
         }
      }
      return achPayeeResult;
   }


}
