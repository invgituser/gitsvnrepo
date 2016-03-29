package com.invessence.service;

import java.math.BigDecimal;
import java.util.*;
import javax.xml.rpc.Call;

import com.invessence.bean.*;
import com.invessence.dao.CommonDao;
import com.ws.gemini.service.CallingLayerGeminiImpl;
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
   CommonDao commonDao;
   String encryDecryKey="aRXDugfr4WQpVrxu";


   public CallStatus loginWebUser(String clientAccountID){
      try
      {
         String userId;
         StringBuilder emailAlertMessage=new StringBuilder();
         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{
            userAcctDetails.setFundGroupName("landenburgfund");
            userAcctDetails.setPwd("test01");
            CallStatus callStatus= callingLayer.loginWebUser(userAcctDetails);
            if(callStatus==null){
               return new CallStatus(222,"Somthing wrong at service API site");
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

   public CallStatus createWebUser(String clientAccountID, String securityQuestion, String securityAnswer)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{
            String fundGroupName = "landenburgfund";
            String password="test01";
            String userId="inv_"+userAcctDetails.getClientAccountID();

            CallStatus callStatus = callingLayer.loginWebUser(userAcctDetails);
            if (callStatus == null)
            {

            }else{
               if(callStatus.getErrorCode()==0){
                  userAcctDetails.setUserID(userId);
                  userAcctDetails.setPwd(password);
                  userAcctDetails.setSecurityQuestion(securityQuestion);
                  userAcctDetails.setSecurityAnswer(securityAnswer);
                  userAcctDetails.setFundGroupName(fundGroupName);
                  userAcctDetails.setStatus("S");
                  userAcctDetails.setRemarks(callStatus.getErrorMessage());
               }else{
                  userAcctDetails.setStatus("E");
                  userAcctDetails.setRemarks(callStatus.getErrorMessage());
                  emailAlertMessage.append(userAcctDetails.getClientAccountID()+":"+userAcctDetails.getRemarks()+"\n");
                }
            }
            try
            {
               commonDao.updatePendingUserAccDetails(userAcctDetails);
            }
            catch (Exception e)
            {
               e.printStackTrace();
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

   public void createPendingWebUser()
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
            String fundGroupName = null;
            String password = null;
            String userId = null;
            callingLayer = getCallingLayer();
            Iterator<UserAcctDetails> itr = uadLst.iterator();
            while (itr.hasNext())
            {
               UserAcctDetails userAcctDetails = (UserAcctDetails) itr.next();
               fundGroupName = "landenburgfund";
               password = "test01";
               userId = "inv_" + userAcctDetails.getClientAccountID();

               CallStatus callStatus = callingLayer.createWebUser(userAcctDetails);
               if (callStatus == null)
               {
                  emailAlertMessage.append("Service calling issue.\n");
               }
               else
               {
                  if (callStatus.getErrorCode() == 0)
                  {
                     userAcctDetails.setUserID(userId);
                     userAcctDetails.setPwd(password);
                     userAcctDetails.setFundGroupName(fundGroupName);
                     userAcctDetails.setStatus("A");
                     userAcctDetails.setRemarks(callStatus.getErrorMessage());
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
                     emailAlertMessage.append(e.getMessage()+"\n");
                     //e.printStackTrace();
                  }
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

   public CallStatus isWebUserExist(String userId)
   {
      return null;
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

            CallStatus callStatus = callingLayer.updateWebUserEmail(userAcctDetails, newEmail);
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

   public CallStatus updateMailingAddress(String clientAccountID)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{

            CallStatus callStatus = callingLayer.updateMailingAddress(userAcctDetails);
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

   public CallStatus getMailingAddress(String clientAccountID)
   {
      try
      {
         StringBuilder emailAlertMessage=new StringBuilder();
         callingLayer=getCallingLayer();

         UserAcctDetails userAcctDetails=commonDao.getUserAccDetailsByAccNumber(clientAccountID);
         if(userAcctDetails==null){
            System.out.println("User Account Details not available in DB");
         }else{

            CallStatus callStatus = callingLayer.getMailingAddress(userAcctDetails);
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
}
