package com.invessence.ws.service;

import java.net.UnknownHostException;
import java.util.*;

import com.invessence.constant.Const;
import com.invessence.util.*;
import com.invessence.ws.bean.*;
import com.invessence.ws.dao.WSCommonDao;
import com.invessence.ws.provider.td.service.ServiceLayerTDImpl;
import com.invessence.ws.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service
public class ServiceLayerImpl implements ServiceLayer
{
   private static final Logger logger = Logger.getLogger(ServiceLayerImpl.class);
   @Autowired
   WSCommonDao commonDao;
   @Autowired
   EmailCreator emailCreator;

   CallingLayer callingLayer;

   public void createPendingUser()
   {
      logger.info("ServiceLayerImpl.createPendingUser");
      StringBuilder emailAlertMessage=new StringBuilder();
      try
      {
         List<UserAcctDetails> uadLst = commonDao.getPendingUserAccDetails();//getUserAccDetailsByWhereClause(""/*"where email='"+emailAddress+"'"*/);
         if(uadLst==null || uadLst.size()==0){
            logger.info("User details not available for accounts creation = " + uadLst.size());
            emailAlertMessage.append("User details not available for accounts creation.\n");
         }else
         {
            String password = null;
            callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();

               password = RandomPwdCreator.passGenerator();
               //randomPWD = EncryDecryAES.encrypt(RandomPwdCreator.passGenerator(),SysParameters.encryDecryKey);

               //userId = "inv_" + userAcctDetails.getClientAccountID();
               userAcctDetails.setPwd(password);
               userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
               System.out.println("userAcctDetails = " + userAcctDetails);
               try
               {
                  WSCallStatus WSCallStatus = callingLayer.createUser(userAcctDetails);
                  if (WSCallStatus == null)
                  {
                     emailAlertMessage.append("Service calling issue.\n");
                  }
                  else
                  {
                     if (WSCallStatus.getErrorCode() == 0)
                     {
                        userAcctDetails.setOpt(WSConstants.succesResult);
                        userAcctDetails.setStatus("A");
                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
                        try
                        {
                           commonDao.updatePendingUserAccDetails(userAcctDetails);
                           getExternalInfo(userAcctDetails, emailAlertMessage, callingLayer);
                        }
                        catch (Exception e)
                        {
                           logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                           emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                           //e.printStackTrace();
                        }
                     }
                     else
                     {
                        userAcctDetails.setOpt(WSConstants.failureResult);
                        userAcctDetails.setStatus("E");
                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
                        emailAlertMessage.append(userAcctDetails.getClientAccountID() + ":" + userAcctDetails.getRemarks() + "\n");
                        try
                        {
                           commonDao.updatePendingUserAccDetails(userAcctDetails);
                        }
                        catch (Exception e)
                        {
                           logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                           emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                           //e.printStackTrace();
                        }
                     }
                  }
               }catch(Exception e){
                  if(e.getCause() instanceof UnknownHostException)
                  {
                     emailAlertMessage.append("Service is not accessible UnknownHostException \n");
                     break;
                  }
               }
            }
         }
         callingLayer = getCallingLayer();
         loadExternalInfoProcess(emailAlertMessage, callingLayer);
       }
      catch (Exception e)
      {
         emailAlertMessage.append(e.getMessage()+"\n");
//         e.printStackTrace();
      }finally
      {
         logger.info("emailAlertMessage :"+emailAlertMessage);
         if(emailAlertMessage.length()>0){
            try
            {
               logger.info("Sending email to support team");
               emailCreator.sendToSupport("ERR", "Pending User's Account Creation Process", emailAlertMessage.toString());
            }catch (Exception e)
            {
               logger.error("Issue while sending an email \n"+e.getMessage());
               //logger.error(e.getStackTrace());
            }
         }
      }
   }
   public void loadExternalInfoProcess(StringBuilder emailAlertMessage, CallingLayer callingLayer)
   {
      logger.info("ServiceLayerImpl.loadExternalInfoProcess");
      try
      {
         List<UserAcctDetails> uadLst = commonDao.getPendingUserExtAccDetails();//getUserAccDetailsByWhereClause(""/*"where email='"+emailAddress+"'"*/);
         if(uadLst==null || uadLst.size()==0){
            logger.info("User details not available for loading external information = " + uadLst.size());
            emailAlertMessage.append("User details not available for loading external information.\n");
         }else
         {
            //callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();
               userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
               System.out.println("userAcctDetails = " + userAcctDetails);

               UserAcctExt extInfo= getExternalInfo(userAcctDetails, emailAlertMessage, callingLayer);

               System.out.println("extInfo = " + extInfo);
            }
         }
      }
      catch (Exception e)
      {
         emailAlertMessage.append(e.getMessage()+"\n");
      }
   }
   private UserAcctExt getExternalInfo(UserAcctDetails userAcctDetails, StringBuilder emailAlertMessage, CallingLayer callingLayer){
      System.out.println("ServiceLayerImpl.getExternalInfo");
      UserAcctExt userAcctExt =new UserAcctExt();
      userAcctDetails.setOpt(WSConstants.failureResult);
      userAcctExt.setStatus("P");
      boolean userAcctExtInfoFlag=false;
      try{
         //UserAcctExt userAcctExt =null;

         WSCallResult maillingAddress=callingLayer.getMailingAddress(userAcctDetails);//getMailingAddress(clientAccountID);
         userAcctExt.setClientAccountID(userAcctDetails.getClientAccountID());
         if(maillingAddress==null || maillingAddress.getGenericObject()==null){
            userAcctExt=null;
            logger.warn(userAcctDetails.getClientAccountID() + ": Not Found extra user account information from getMailingAddress API");
            emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Not Found extra user account information from getMailingAddress API \n");
         }else{
            UserAcctExt maillingAddressUserAcctExt=(UserAcctExt)maillingAddress.getGenericObject();
            userAcctExt.setMailingAddressId(maillingAddressUserAcctExt.getMailingAddressId());
            userAcctExt.setMailingAddressType(maillingAddressUserAcctExt.getMailingAddressType());
            try{

               WSCallResult accountInfo=callingLayer.getAccountInfo(userAcctDetails);//getAccountInfo(clientAccountID);

               if(accountInfo==null || accountInfo.getGenericObject()==null){
                  userAcctExt=null;
                  logger.warn(userAcctDetails.getClientAccountID() + ": Not Found extra user account information from getAccountInfo API");
                  emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Not Found extra user account information from getAccountInfo API \n");
               }else{
                  UserAcctExt accountInfoUserAcctExt=(UserAcctExt)accountInfo.getGenericObject();
                  userAcctExt.setDateOfBirth(accountInfoUserAcctExt.getDateOfBirth());
                  userAcctExt.setAccountType(accountInfoUserAcctExt.getAccountType());
                  userAcctExtInfoFlag=true;
               }
            }catch(Exception e){
               logger.error(userAcctDetails.getClientAccountID() + ": Issue while fetching extra user account information from API\n"+e.getMessage()+"\n");
               emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while fetching extra user account information from getMailingAddress API\n"+e.getMessage()+"\n");
               e.printStackTrace();
            }
         }
      }catch(Exception e){
         logger.error(userAcctDetails.getClientAccountID() + ": Issue while fetching extra user account information from API\n"+e.getMessage()+"\n");
         emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while fetching extra user account information from getAccountInfo API\n"+e.getMessage()+"\n");
         e.printStackTrace();
      }

      if(userAcctExtInfoFlag==true)
      {
         userAcctExt.setOpt(WSConstants.succesResult);
         userAcctExt.setStatus("A");
      }
         try
         {
            commonDao.insertAccountExtInfo(userAcctExt);
         }
         catch (Exception e)
         {
            logger.error(userAcctDetails.getClientAccountID() + ": Issue while storing extra user account information in DB\n");
            emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while storing extra user account information in DB\n");
            e.printStackTrace();
         }
      return userAcctExt;
   }

   public WSCallResult getMailingAddress(String clientAccountID)
   {

      logger.info("ServiceLayerImpl.getMailingAddress");
      logger.info("clientAccountID = [" + clientAccountID + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.GET_MAILING_ADDRESS)){
         callingLayer = getCallingLayer();
         if(callingLayer==null){
            logger.warn("Calling Service object creation issue");
            return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
         }else
         {
            try
            {
               UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
               if (userAcctDetails == null)
               {
                  logger.warn("User details not available in DB");
                  return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg),null);
               }
               else
               {
                  logger.debug("userAcctDetails = " + userAcctDetails);
                  if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                  {
                     try
                     {
                        WSCallResult wsCallResult = callingLayer.getMailingAddress(userAcctDetails);
                        if (wsCallResult == null || wsCallResult.getWSCallStatus()==null)
                        {
                           logger.warn(SysParameters.wsResIssueMsg);
                           return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
                        }else if (wsCallResult.getWSCallStatus().getErrorCode()== 0)
                        {
                           return wsCallResult;
                        }else
                        {
                           return wsCallResult;
                        }
                     }
                     catch (Exception e)
                     {
                        logger.error(e.getMessage());
                        return new WSCallResult(new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg),null);
                     }
                  }
                  else
                  {
                     logger.warn(SysParameters.wsUserRegIssueMsg);
                     return new WSCallResult(new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg),null);
                  }
               }
            }
            catch (Exception e)
            {
               logger.error(e.getMessage());
               return new WSCallResult(new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg),null);
            }
         }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallResult(new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg),null);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
      }
   }
   private WSCallResult getAccountInfo(String clientAccountID)
   {

      logger.info("ServiceLayerImpl.getAccountInfo");
      logger.info("clientAccountID = [" + clientAccountID + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.GET_ACCT_INFO)){
         callingLayer = getCallingLayer();
         if(callingLayer==null){
            logger.warn("Calling Service object creation issue");
            return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
         }else
         {
            try
            {
               UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
               if (userAcctDetails == null)
               {
                  logger.warn("User details not available in DB");
                  return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg),null);
               }
               else
               {
                  logger.debug("userAcctDetails = " + userAcctDetails);
                  if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                  {
                     try
                     {
                        WSCallResult wsCallResult = callingLayer.getAccountInfo(userAcctDetails);
                        if (wsCallResult == null || wsCallResult.getWSCallStatus()==null)
                        {
                           logger.warn(SysParameters.wsResIssueMsg);
                           return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
                        }else if (wsCallResult.getWSCallStatus().getErrorCode()== 0)
                        {
                           return wsCallResult;
                        }else
                        {
                           return wsCallResult;
                        }
                     }
                     catch (Exception e)
                     {
                        logger.error(e.getMessage());
                        return new WSCallResult(new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg),null);
                     }
                  }
                  else
                  {
                     logger.warn(SysParameters.wsUserRegIssueMsg);
                     return new WSCallResult(new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg),null);
                  }
               }
            }
            catch (Exception e)
            {
               logger.error(e.getMessage());
               return new WSCallResult(new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg),null);
            }
         }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallResult(new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg),null);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
      }
   }

   public WSCallStatus updateEmail(String clientAccountID, String newEmail)
   {
      logger.info("ServiceLayerImpl.updateEmail");
      logger.info("clientAccountID = [" + clientAccountID + "], newPwd = [" + newEmail + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.EMAIL_UPDATE)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {

                           WSCallStatus wsCallStatus = callingLayer.updateUserEmail(userAcctDetails, newEmail);
                           if (wsCallStatus == null)
                           {
                              logger.warn(SysParameters.wsResIssueMsg);
                              return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
                           }else
                           {
                              return wsCallStatus;
                           }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
      }
   }

   public WSCallStatus updateMailingAddress(String clientAccountID,
                                            String firstName,String middleName,String lastName,
                                            String addressLine1, String addressLine2,String addressLine3,
                                            String city, String  state,String postalZip, short countryCode,
                                            String voicePhone, String altPhone, String faxPhone, String emailAddress)
   {

      logger.info("ServiceLayerImpl.updateMailingAddress");
      logger.info("clientAccountID = [" + clientAccountID + "], firstName = [" + firstName + "], middleName = [" + middleName + "], lastName = [" + lastName + "], addressLine1 = [" + addressLine1 + "], addressLine2 = [" + addressLine2 + "], addressLine3 = [" + addressLine3 + "], postalZip = [" + postalZip + "], countryCode = [" + countryCode + "], voicePhone = [" + voicePhone + "], altPhone = [" + altPhone + "], faxPhone = [" + faxPhone + "], emailAddress = [" + emailAddress + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.MAILING_ADDRESS_UPDATE)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {
                        try
                        {
                           UserAcctExt userAcctExt=commonDao.getAccountExtInfo(clientAccountID);
                           if (userAcctExt == null ||  userAcctExt.getMailingAddressType()==null)
                           {
                              logger.warn("User extention details not available in DB");
                              return new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg);
                           }
                           else
                           {
                              logger.debug("userAcctExt = " + userAcctExt);
                              try
                              {
                                 UserAddress mailingAddress = new UserAddress(firstName, middleName, lastName,
                                                                              addressLine1, addressLine2, addressLine3, city, state, postalZip, countryCode,
                                                                              voicePhone, altPhone, faxPhone, emailAddress, userAcctExt.getMailingAddressId(), userAcctExt.getMailingAddressType(), clientAccountID);
                                 WSCallStatus wsCallStatus = callingLayer.updateMailingAddress(userAcctDetails, mailingAddress);
                                 if (wsCallStatus == null)
                                 {
                                    logger.warn(SysParameters.wsResIssueMsg);
                                    return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
                                 }else if (wsCallStatus.getErrorCode()== 0)
                                 {
                                    return wsCallStatus;
                                 }else
                                 {
                                    return wsCallStatus;
                                 }
                              }
                              catch (Exception e)
                              {
                                 logger.error(e.getMessage());
                                 return new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg);
                              }
                           }
                     }
                     catch (Exception e)
                     {
                        logger.error(e.getMessage());
                        return new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg);
                     }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
      }
//      try
//      {
//         StringBuilder emailAlertMessage=new StringBuilder();
//         callingLayer=getCallingLayer();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            logger.info("User Account Details not available in DB");
//         }else{
//            UserAcctExt userAcctExt=commonDao.getAccountExtInfo(clientAccountID);
//            logger.info("userAcctExt.toString() = " + userAcctExt.toString());
//
//            UserAddress mailingAddress= new UserAddress(firstName,middleName,lastName,
//                                                        addressLine1, addressLine2,addressLine3, postalZip, countryCode,
//                                                        voicePhone,altPhone, faxPhone,emailAddress,userAcctExt.getMailingAddressId(),userAcctExt.getMailingAddressType(),clientAccountID);
//            logger.info("mailingAddress = " + mailingAddress.toString());
//            WSCallStatus WSCallStatus = callingLayer.updateMailingAddress(userAcctDetails, mailingAddress);
//            if (WSCallStatus == null)
//            {
//
//            }else{
//               if(WSCallStatus.getErrorCode()==0){
//
//               }else{
//                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
//               }
//            }
//
//            return WSCallStatus;
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;
   }

   @Override
   public WSCallResult getUserBankAcctDetails(String clientAccountID)
   {

      logger.info("ServiceLayerImpl.getUserBankAcctDetails");
      logger.info("clientAccountID = [" + clientAccountID + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.GET_BANK_ACCT_DETAILS)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg),null);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {
                        try
                        {
                           WSCallResult wsCallResult = callingLayer.getUserBankAcctDetails(userAcctDetails);
                           if (wsCallResult == null || wsCallResult.getWSCallStatus()==null)
                           {
                              logger.warn(SysParameters.wsResIssueMsg);
                              return new WSCallResult(new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
                           }else if (wsCallResult.getWSCallStatus().getErrorCode()== 0)
                           {
                              return wsCallResult;
                           }else
                           {
                              return wsCallResult;
                           }
                        }
                        catch (Exception e)
                        {
                           logger.error(e.getMessage());
                           return new WSCallResult(new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg),null);
                        }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallResult(new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg),null);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallResult(new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg),null);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallResult(new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg),null);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallResult(new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
      }
//      logger.info("ServiceLayerImpl.getUserBankAcctDetails");
//      try
//      {
//         logger.debug("clientAccountID = [" + clientAccountID + "]");
//         StringBuilder emailAlertMessage=new StringBuilder();
//         callingLayer=getCallingLayer();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            logger.info("User Account Details not available in DB");
//         }else{
//            try
//            {
//               WSCallResult wsCallResult = callingLayer.getUserBankAcctDetails(userAcctDetails);
//               System.out.println("wsCallResult = [" + wsCallResult.toString() + "]");
//               return wsCallResult;
////               if (wsCallResult.getGenericObject() == null)
////               {
////                  return null;
////               }
////               else
////               {
////
////                  Iterator<BankAcctDetails> itr = ((List<BankAcctDetails>)wsCallResult.getGenericObject()).iterator();
////                  while (itr.hasNext())
////                  {
////                     BankAcctDetails bd = (BankAcctDetails) itr.next();
////                     System.out.println("bd.toString() = " + bd.toString());
////                  }
////                  return wsCallResult;
////               }
//            }catch (Exception e){
//               logger.error(e.getMessage());
//               e.printStackTrace();
//            }
//         }
//      }
//      catch (Exception e)
//      {
//         logger.error(e.getMessage());
//         e.printStackTrace();
//      }
//      return null;
   }

   @Override
   public WSCallResult fundAccount(String clientAccountID, int fundID, double amount, String bankAccountNumber)
   {

      logger.info("ServiceLayerImpl.fundAccount");
      logger.info("clientAccountID = [" + clientAccountID + "], fundID = [" + fundID + "], amount = [" + amount + "], bankAccountNumber = [" + bankAccountNumber + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.FUND_ACCOUNT)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg),null);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {
                        try
                        {
                           WSCallResult wsCallResult = callingLayer.fundAccount(userAcctDetails, fundID, amount, bankAccountNumber);
                           if (wsCallResult == null || wsCallResult.getWSCallStatus()==null)
                           {
                              logger.warn(SysParameters.wsResIssueMsg);
                              return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
                           }else if (wsCallResult.getWSCallStatus().getErrorCode()== 0)
                           {
                              return wsCallResult;
                           }else
                           {
                              return wsCallResult;
                           }
                        }
                        catch (Exception e)
                        {
                           logger.error(e.getMessage());
                           return new WSCallResult( new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg),null);
                        }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallResult( new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg),null);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallResult( new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg),null);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallResult( new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg),null);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
      }

//
//      try
//      {
//         StringBuilder emailAlertMessage=new StringBuilder();
//         callingLayer=getCallingLayer();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            System.out.println("User Account Details not available in DB");
//         }else{
//
//            WSCallStatus WSCallStatus = callingLayer.fundAccount(userAcctDetails, fundID, amount, bankAccountNumber);
//            if (WSCallStatus == null)
//            {
//
//            }else{
//               if(WSCallStatus.getErrorCode()==0){
//
//               }else{
//                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
//               }
//            }
//
//            return WSCallStatus;
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;

   }

   @Override
   public WSCallResult fullFundTransfer(String clientAccountID, int fromFundID, int toFundID, String bankAccountNumber)
   {
      logger.info("ServiceLayerImpl.fullFundTransfer");
      logger.info("clientAccountID = [" + clientAccountID + "], fromFundID = [" + fromFundID + "], toFundID = [" + toFundID + "], bankAccountNumber = [" + bankAccountNumber + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.FULL_FUND_TRANSFER)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg),null);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {
                        try
                        {
                           WSCallResult wsCallResult = callingLayer.fullFundTransfer(userAcctDetails, fromFundID, toFundID, bankAccountNumber);
                           if (wsCallResult == null || wsCallResult.getWSCallStatus()==null)
                           {
                              logger.warn(SysParameters.wsResIssueMsg);
                              return new WSCallResult( new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg),null);
                           }else if (wsCallResult.getWSCallStatus().getErrorCode()== 0)
                           {
                              return wsCallResult;
                           }else
                           {
                              return wsCallResult;
                           }
                        }
                        catch (Exception e)
                        {
                           logger.error(e.getMessage());
                           return new WSCallResult( new WSCallStatus(SysParameters.wsEGenErrCode, SysParameters.wsEGenErrMsg),null);
                        }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallResult( new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg),null);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallResult( new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg),null);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallResult( new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg),null);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallResult( new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg),null);
      }
//      try
//      {
//         StringBuilder emailAlertMessage=new StringBuilder();
//         callingLayer=getCallingLayer();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            System.out.println("User Account Details not available in DB");
//         }else{
//
//            WSCallStatus WSCallStatus = callingLayer.fullFundTransfer(userAcctDetails, fromFundID, toFundID, bankAccountNumber);
//            if (WSCallStatus == null)
//            {
//
//            }else{
//               if(WSCallStatus.getErrorCode()==0){
//
//               }else{
//                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
//               }
//            }
//
//            return WSCallStatus;
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;

   }

   @Override
   public boolean isServiceActive()
   {
//      System.out.println("Const.Services.BROKER_WEBSERVICES: "+Const.Services.BROKER_WEBSERVICES.toString());
//      System.out.println("Const.Services.BROKER_WEBSERVICES: "+Const.Services.BROKER_WEBSERVICES);
//      System.out.println("*****************************");
//      System.out.println("Size of Map"+SysParameters.serviceDetailsMap.size());
//
//      Iterator<Map.Entry<String, Map<String, List<ServiceDetails>>>> entries = SysParameters.serviceDetailsMap.entrySet().iterator();
//      while (entries.hasNext()) {
//         Map.Entry<String, Map<String, List<ServiceDetails>>> entry = entries.next();
//         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//      }
//
//      System.out.println("*****************************");
//      System.out.println(SysParameters.serviceDetailsMap.containsKey(Const.Services.PRICING.toString()));
      if(SysParameters.serviceDetailsMap==null){
         return false;
      }else if(SysParameters.serviceDetailsMap.containsKey(Const.Services.BROKER_WEBSERVICES.toString())){
         //System.out.println(getServiceProvider());
            return true;
      }
      return false;
   }

   @Override
   public WSCallStatus resetPassword(String clientAccountID)
   {
      logger.info("ServiceLayerImpl.resetPassword");
      logger.info("clientAccountID = [" + clientAccountID + "]");
      try
      {
         if (isServiceOprationActive(WSConstants.BrokerWebServiceOperations.UPDATE_PWD_WITH_NOAUTH)){
            callingLayer = getCallingLayer();
            if(callingLayer==null){
               logger.warn("Calling Service object creation issue");
               return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
            }else
            {
               try
               {
                  UserAcctDetails userAcctDetails = commonDao.getUserAccDetailsByAccNumber(clientAccountID);
                  String password = RandomPwdCreator.passGenerator();
//                  String randomPWD = EncryDecryAES.encrypt(password,SysParameters.encryDecryKey);
                  userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
                  if (userAcctDetails == null)
                  {
                     logger.warn("User details not available in DB");
                     return new WSCallStatus(SysParameters.wsIGenErrCode, SysParameters.wsIGenErrMsg);
                  }
                  else
                  {
                     logger.debug("userAcctDetails = " + userAcctDetails);
                     if (userAcctDetails.getStatus().equalsIgnoreCase("A"))
                     {

                        WSCallStatus wsCallStatus = callingLayer.resetPassword(userAcctDetails, password);
                        if (wsCallStatus == null)
                        {
                           logger.warn(SysParameters.wsResIssueMsg);
                           return new WSCallStatus(SysParameters.wsResIssueCode, SysParameters.wsResIssueMsg);
                        }else
                        {
                           if (wsCallStatus.getErrorCode() == 0)
                           {
                              userAcctDetails.setOpt(WSConstants.succesResult);
                              userAcctDetails.setPwd(password);
                              userAcctDetails.setStatus("A");
                              userAcctDetails.setRemarks(wsCallStatus.getErrorMessage());
                              try
                              {
                                 commonDao.updatePendingUserAccDetails(userAcctDetails);
                              }
                              catch (Exception e)
                              {
                                 logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                               }
                           }
                           return wsCallStatus;
                        }
                     }
                     else
                     {
                        logger.warn(SysParameters.wsUserRegIssueMsg);
                        return new WSCallStatus(SysParameters.wsUserRegIssueCode, SysParameters.wsUserRegIssueMsg);
                     }
                  }
               }
               catch (Exception e)
               {
                  logger.error(e.getMessage());
                  return new WSCallStatus(SysParameters.wsDBErrCode, SysParameters.wsDBErrMsg);
               }
            }
         }else{
            logger.warn(SysParameters.wsOperationNAMsg);
            return new WSCallStatus(SysParameters.wsOperationNACode,SysParameters.wsOperationNAMsg);
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage());
         return new WSCallStatus(SysParameters.wsIGenErrCode,SysParameters.wsIGenErrMsg);
      }
   }


   public void resetPassword()
   {
      logger.info("ServiceLayerImpl.resetPassword");
      StringBuilder emailAlertMessage=new StringBuilder();
      try
      {
        List<UserAcctDetails> uadLst = commonDao.getUserAccDetailsByWhereClause("where status ='A'");//"where status ='A'"

         if(uadLst==null || uadLst.size()==0){
            logger.info("User details not available for password reset = " + uadLst.size());
            emailAlertMessage.append("User details not available for password reset.\n");
         }else
         {
            String password = null;
            String randomPWD=null;
            callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();
               password = RandomPwdCreator.passGenerator();
               randomPWD = EncryDecryAES.encrypt(password,SysParameters.encryDecryKey);
               userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
               System.out.println("userAcctDetails = " + userAcctDetails);
               try
               {
                  WSCallStatus WSCallStatus = callingLayer.resetPassword(userAcctDetails, randomPWD);
                  if (WSCallStatus == null)
                  {
                     emailAlertMessage.append("Service calling issue.\n");
                  }
                  else
                  {
                     if (WSCallStatus.getErrorCode() == 0)
                     {
                        userAcctDetails.setOpt(WSConstants.succesResult);
                        userAcctDetails.setStatus("A");
                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
                        try
                        {
                           commonDao.updatePendingUserAccDetails(userAcctDetails);
                        }
                        catch (Exception e)
                        {
                           logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                           emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
                        }
                     }
//                     else
//                     {
//                        userAcctDetails.setOpt(WSConstants.failureResult);
//                        userAcctDetails.setStatus("E");
//                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
//                        emailAlertMessage.append(userAcctDetails.getClientAccountID() + ":" + userAcctDetails.getRemarks() + "\n");
//                        try
//                        {
//                           commonDao.updatePendingUserAccDetails(userAcctDetails);
//                        }
//                        catch (Exception e)
//                        {
//                           logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
//                           emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
//                        }
//                     }
                  }
               }catch(Exception e){
                  if(e.getCause() instanceof UnknownHostException)
                  {
                     emailAlertMessage.append("Service is not accessible UnknownHostException \n");
                     break;
                  }
               }
            }
         }
      }
      catch (Exception e)
      {
         emailAlertMessage.append(e.getMessage()+"\n");
      }finally
      {
         logger.info("emailAlertMessage :"+emailAlertMessage);
         if(emailAlertMessage.length()>0){
            try
            {
               logger.info("Sending email to support team");
               emailCreator.sendToSupport("ERR", "Reset Password Process", emailAlertMessage.toString());
            }catch (Exception e)
            {
               logger.error("Issue while sending an email \n"+e.getMessage());
            }
         }
      }
   }


   public void toTestAPI()
   {
      logger.info("ServiceLayerImpl.toTestAPI");
      try
      {
         List<UserAcctDetails> uadLst = commonDao.getUserAccDetailsByWhereClause("where status ='A'");//"where status ='A'"
         if(uadLst==null || uadLst.size()==0){
            logger.info("User details not available toTestAPI = " + uadLst.size());
         }else
         {
            callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();
               //randomPWD = EncryDecryAES.encrypt(RandomPwdCreator.passGenerator(),SysParameters.encryDecryKey);

               userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
               System.out.println("userAcctDetails = " + userAcctDetails);
               try
               {
                  WSCallStatus WSCallStatus =callingLayer.loginUser(userAcctDetails);
                  System.out.println("WSCallStatus = " + WSCallStatus);
//                  WSCallStatus WSCallStatus = callingLayer.createUser(userAcctDetails);
//                  if (WSCallStatus == null)
//                  {
//                     logger.warn("Service calling issue.\n");
//                  }
//                  else
//                  {
//                     if (WSCallStatus.getErrorCode() == 0)
//                     {
//                        userAcctDetails.setOpt(WSConstants.succesResult);
//                        userAcctDetails.setStatus("A");
//                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
////                        try
////                        {
////                           commonDao.updatePendingUserAccDetails(userAcctDetails);
////                        }
////                        catch (Exception e)
////                        {
////                           logger.error(userAcctDetails.getClientAccountID() + ": Issue while updating user account information in DB\n" +e.getMessage()+"\n");
////                        }
//                     }
//                     else
//                     {
//                        userAcctDetails.setOpt(WSConstants.failureResult);
//                        userAcctDetails.setStatus("E");
//                        userAcctDetails.setRemarks(WSCallStatus.getErrorMessage());
//                     }
//                  }
               }catch(Exception e){
                  logger.error(e.getMessage()+" \n");
                  if(e.getCause() instanceof UnknownHostException)
                  {
                     logger.error("Service is not accessible UnknownHostException \n");
                     break;
                  }
                  //e.printStackTrace();
               }
            }
         }
      }
      catch (Exception e)
      {
         logger.error(e.getMessage()+"\n");
      }
   }


   public boolean isServiceOprationActive(WSConstants.BrokerWebServiceOperations operation)
   {
      boolean result=false;
      if(SysParameters.serviceDetailsMap.containsKey(Const.Services.BROKER_WEBSERVICES.toString())){
        List<ServiceDetails>sd= SysParameters.serviceDetailsMap.get(Const.Services.BROKER_WEBSERVICES.toString()).get(SysParameters.webServiceAPI);
         Iterator<ServiceDetails> itr=sd.iterator();
         while(itr.hasNext()){
            ServiceDetails sdi=itr.next();
            if(sdi.getOperation().equalsIgnoreCase(operation.toString())){
               result =true;
               break;
            }
         }
      }
      return result;
   }

//   public String getServiceProvider()
//   {
//      System.out.println("ServiceLayerImpl.getServiceProvider");
//      if(SysParameters.serviceDetailsMap.containsKey(Const.Services.BROKER_WEBSERVICES.toString())){
//         Map<String, List<ServiceDetails>>sd= SysParameters.serviceDetailsMap.get(Const.Services.BROKER_WEBSERVICES.toString());
//         if(sd==null || sd.size()==0){
//            System.out.println("Expecting single API provider for "+Const.Services.BROKER_WEBSERVICES.toString()+" service but it gets none");
//         }else if(sd.size()>1){
//            System.out.println("Expecting single API provider for "+Const.Services.BROKER_WEBSERVICES.toString()+" service but it gets more the one");
//            return sd.keySet().toArray()[0].toString();
//         }else if(sd.size()==1){
//            return sd.keySet().toArray()[0].toString();
//         }
//      }
//      return  null;
//   }

   private CallingLayer getCallingLayer(){
      //SysParameters.webServiceAPI
      String webServiceAPI=SysParameters.webServiceAPI;//getServiceProvider();
      if(webServiceAPI==null){
         logger.error(Const.Services.BROKER_WEBSERVICES.toString()+" API is not available");
      }else if(webServiceAPI.equals("GEMINI")){
         callingLayer = new CallingLayerGeminiImpl(commonDao);
      }else if(webServiceAPI.equals("TD")){
         callingLayer = new ServiceLayerTDImpl();
      }else{
         logger.error(webServiceAPI+" API is not available");
      }
      return callingLayer;
   }


   public WSCallStatus loginUser(String clientAccountID){
      try
      {
         String userId;
         StringBuilder emailAlertMessage=new StringBuilder();
         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{
            userAcctDetails.setFundGroupName("landenburgfund");
            //userAcctDetails.setPwd("test01");
            WSCallStatus callStatus= getCallingLayer().loginUser(userAcctDetails);
            if(callStatus==null){
               return new WSCallStatus(222,"Somthing wrong at service API site");
            }else{

            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

//   public WSCallStatus createUser(String clientAccountID, String securityQuestion, String securityAnswer)
//   {
//      try
//      {
//         StringBuilder emailAlertMessage=new StringBuilder();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            System.out.println("User Account Details not available in DB");
//         }else{
//            String password="test01";
//            String userId="inv_"+userAcctDetails.getClientAccountID();
//
//            WSCallStatus callStatus = callingLayer.loginUser(userAcctDetails);
//            if (callStatus == null)
//            {
//
//            }else{
//               if(callStatus.getErrorCode()==0){
//                  userAcctDetails.setUserID(userId);
//                  userAcctDetails.setPwd(password);
//                  userAcctDetails.setSecurityQuestion(securityQuestion);
//                  userAcctDetails.setSecurityAnswer(securityAnswer);
//                  userAcctDetails.setFundGroupName(SysParameters.fundGroupName);
//                  userAcctDetails.setStatus("S");
//                  userAcctDetails.setRemarks(callStatus.getErrorMessage());
//               }else{
//                  userAcctDetails.setStatus("E");
//                  userAcctDetails.setRemarks(callStatus.getErrorMessage());
//                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
//                }
//            }
//            try
//            {
//               commonDao.updatePendingUserAccDetails(userAcctDetails);
//            }
//            catch (Exception e)
//            {
//               e.printStackTrace();
//            }
//            return callStatus;
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;
//   }

//   public WSCallStatus isUserExist(String userId)
//   {
//      return null;
//   }

// public WSCallStatus getMailingAddress(String clientAccountID)
//   {
//      try
//      {
//         StringBuilder emailAlertMessage=new StringBuilder();
//         callingLayer=getCallingLayer();
//
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            System.out.println("User Account Details not available in DB");
//         }else{
//
//            WSCallStatus callStatus = callingLayer.getMailingAddress(userAcctDetails);
//            if (callStatus == null)
//            {
//
//            }else{
//               if(callStatus.getErrorCode()==0){
//
//               }else{
//                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
//               }
//            }
//
//            return callStatus;
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;
//   }

}
