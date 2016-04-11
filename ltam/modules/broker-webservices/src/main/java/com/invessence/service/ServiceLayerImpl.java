package com.invessence.service;

import java.net.UnknownHostException;
import java.util.*;

import com.invessence.bean.*;
import com.invessence.dao.WSCommonDao;
import com.invessence.util.SysParameters;
import com.ws.td.service.ServiceLayerTDImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service
public class ServiceLayerImpl implements ServiceLayer
{
   CallingLayer callingLayer;
   @Autowired
   WSCommonDao commonDao;
   String encryDecryKey="aRXDugfr4WQpVrxu";



   public void createPendingUser()
   {
      StringBuilder emailAlertMessage=new StringBuilder();
      try
      {
         List<UserAcctDetails> uadLst = commonDao.getPendingUserAccDetails();//getUserAccDetailsByWhereClause(""/*"where email='"+emailAddress+"'"*/);
         if(uadLst==null || uadLst.size()==0){
            emailAlertMessage.append("User details not available for Pending accounts creation at Gemini end.\n");
         }else
         {
            int i = 1;
            String password = null;
            callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();

               password = "test01";
               //userId = "inv_" + userAcctDetails.getClientAccountID();
               userAcctDetails.setPwd(password);
               userAcctDetails.setFundGroupName(SysParameters.fundGroupName);

               try
               {
                  CallStatus callStatus = callingLayer.createUser(userAcctDetails);
                  if (callStatus == null)
                  {
                     emailAlertMessage.append("Service calling issue.\n");
                  }
                  else
                  {
                     if (callStatus.getErrorCode() == 0)
                     {
                        userAcctDetails.setPwd(password);
                        userAcctDetails.setStatus("A");
                        userAcctDetails.setRemarks(callStatus.getErrorMessage());
                        try
                        {
                           UserAcctExt userAcctExt = callingLayer.getAcctExtInfo(userAcctDetails);
                           if (userAcctExt == null)
                           {
                              emailAlertMessage.append(userAcctDetails.getClientAccountID() + ": issue while fetching extention info\n");
                           }
                           else
                           {
                              commonDao.insertAccountExtInfo(userAcctExt);
                           }
                           System.out.println(userAcctExt.toString());
                        }
                        catch (Exception e)
                        {
                           e.printStackTrace();
                        }
                     }
                     else
                     {
                        userAcctDetails.setStatus("E");
                        userAcctDetails.setRemarks(callStatus.getErrorMessage());
                        emailAlertMessage.append(userAcctDetails.getClientAccountID() + ":" + userAcctDetails.getRemarks() + "\n");
                     }
                     try
                     {
                        commonDao.updatePendingUserAccDetails(userAcctDetails);
                     }
                     catch (Exception e)
                     {
                        emailAlertMessage.append(e.getMessage() + "\n");
                        //e.printStackTrace();
                     }
                  }
               }catch(Exception e){
                  if(e.getCause() instanceof UnknownHostException)
                  {
                     emailAlertMessage.append("Service is not accessible UnknownHostException \n");
                     break;
                     //System.out.println("ervice is not accessible UnknownHostException" + e.getMessage());
                  }
                  //e.printStackTrace();
               }


//               if (i <= 20)
//               {
////               WebUserResult webUserResult = servicesSoap.createShareholderWebUser(
////                  new AuthenticateLogin(userId,"test01",fundGroupName,"00"),
////                  uad.getClientAccountID(), EncryptionDecryptionAES.decrypt(uad.getSsn(),encryDecryKey), uad.getMailZipCode(), uad.getEmail(),
////                  securityQuestion, securityAnswer, new UnsignedByte("1"));
////               System.out.println(webUserResult.toString());
//               }

//            if(i==1){
//               if(existResult.isIsExist()==false){
//                  WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin(userId,"test01","landenburgfund","00"), uad.getClientAccountID(), EncryptionDecryptionAES.decrypt(uad.getSsn(),encryDecryKey), uad.getMailZipCode(), emailAddress, securityQuestion, securityAnswer, new UnsignedByte("1"));
//                  System.out.println(webUserResult.toString());
//               }else if(existResult.isIsExist()==true){
//
//               }
//            }
//               i++;
            }
         }
         //new AuthenticateLogin("test.larie", "test01", "landenburgfund", "00");
      }
      catch (Exception e)
      {
         emailAlertMessage.append(e.getMessage()+"\n");
//         e.printStackTrace();
      }finally
      {
         System.out.println("emailAlertMessage :"+emailAlertMessage);
      }
   }

   public CallStatus updateEmail(String clientAccountID, String newEmail)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{

            CallStatus callStatus = callingLayer.updateUserEmail(userAcctDetails, newEmail);
            if (callStatus == null)
            {

            }else{
               if(callStatus.getErrorCode()==0){
                  try
                  {
                     commonDao.updateUserEmail(userAcctDetails,newEmail);
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                  }

               }else{
                   emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
               }
            }

            return callStatus;
      }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   public CallStatus updateMailingAddress(String clientAccountID,
                                          String nameLines, String addressLines, String postalZip, short countryCode,
                                          String voicePhone,String altPhone, String faxPhone,String emailAddress)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{
            UserAddress mailingAddress=null;//new UserAddress(nameLines, addressLines, postalZip, countryCode,
                                                    //voicePhone,altPhone, faxPhone,emailAddress);
            CallStatus callStatus = callingLayer.updateMailingAddress(userAcctDetails, mailingAddress);
            if (callStatus == null)
            {

            }else{
               if(callStatus.getErrorCode()==0){

               }else{
                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
               }
            }

            return callStatus;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public List<BankAcctDetails> getUserBankAcctDetails(String clientAccountID)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{
            List<BankAcctDetails> userBankAcctDetails = callingLayer.getUserBankAcctDetails(userAcctDetails);
            if (userBankAcctDetails == null || userBankAcctDetails.size()==0)
            {
               return null;
            }else{
               
               Iterator<BankAcctDetails> itr=userBankAcctDetails.iterator();
               while(itr.hasNext()){
                  BankAcctDetails bd=(BankAcctDetails)itr.next();
                  System.out.println("bd.toString() = " + bd.toString());
               }
               return userBankAcctDetails;
            }


         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public CallStatus fundAccount(String clientAccountID, int fundID, double amount, String accountNumber)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{

            CallStatus callStatus = callingLayer.fundAccount(userAcctDetails, fundID, amount, accountNumber);
            if (callStatus == null)
            {

            }else{
               if(callStatus.getErrorCode()==0){

               }else{
                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
               }
            }

            return callStatus;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   @Override
   public CallStatus fundTransfer(String clientAccountID, int fromFundID, int toFundID, double amount, String accountNumber)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{

            CallStatus callStatus = callingLayer.fundTransfer(userAcctDetails, fromFundID, toFundID, amount, accountNumber);
            if (callStatus == null)
            {

            }else{
               if(callStatus.getErrorCode()==0){

               }else{
                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
               }
            }

            return callStatus;
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;

   }

   private CallingLayer getCallingLayer(){
      String API="GEMINI";
      if(API.equals("GEMINI"))
      {
         callingLayer = new CallingLayerGeminiImpl();
      }else  if(API.equals("TD")){
         callingLayer = new ServiceLayerTDImpl();
      }
      return callingLayer;
   }


//   public CallStatus loginUser(String clientAccountID){
//      try
//      {
//         String userId;
//         StringBuilder emailAlertMessage=new StringBuilder();
//         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
//         if(userAcctDetails==null){
//            System.out.println("User Account Details not available in DB");
//         }else{
//            userAcctDetails.setFundGroupName("landenburgfund");
//            userAcctDetails.setPwd("test01");
//            CallStatus callStatus= callingLayer.loginUser(userAcctDetails);
//            if(callStatus==null){
//               return new CallStatus(222,"Somthing wrong at service API site");
//            }else{
//
//            }
//         }
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//      return null;
//   }

//   public CallStatus createUser(String clientAccountID, String securityQuestion, String securityAnswer)
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
//            CallStatus callStatus = callingLayer.loginUser(userAcctDetails);
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

//   public CallStatus isUserExist(String userId)
//   {
//      return null;
//   }

// public CallStatus getMailingAddress(String clientAccountID)
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
//            CallStatus callStatus = callingLayer.getMailingAddress(userAcctDetails);
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
