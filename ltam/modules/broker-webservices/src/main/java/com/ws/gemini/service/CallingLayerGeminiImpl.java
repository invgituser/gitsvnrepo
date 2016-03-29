package com.ws.gemini.service;

import java.math.BigDecimal;

import com.invessence.bean.*;
import com.invessence.service.*;
import com.ws.gemini.wsdl.login.*;
import org.apache.axis.types.UnsignedByte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 3/11/2016.
 */

@Service
public class CallingLayerGeminiImpl implements CallingLayer
{

   LoginService loginService;
   AccountService accountService;
   String encryDecryKey="aRXDugfr4WQpVrxu";

   public CallStatus loginWebUser(UserAcctDetails userAcctDetails) throws Exception{
      String userId;
      loginService=new LoginServiceImpl();
      return loginService.loginWebUser(userAcctDetails);

   }

   public CallStatus createWebUser(UserAcctDetails userAcctDetails)throws Exception
   {
      loginService=new LoginServiceImpl();
      return loginService.createWebUser(userAcctDetails);
   }

//   public void createPendingWebUser(UserAcctDetails userAcctDetails)
//   {
//      StringBuilder emailAlertMessage=new StringBuilder();
//      try
//      {
//         servicesSoap = locator.getLoginServicesSoap();
//         List<UserAcctDetails> uadLst = commonDao.getPendingUserAccDetails();//getUserAccDetailsByWhereClause(""/*"where email='"+emailAddress+"'"*/);
//         Iterator<UserAcctDetails> itr = uadLst.iterator();
//         int i = 1;
//         String securityQuestion = "secQuest";
//         String securityAnswer = "secAnsw";
//         String fundGroupName = "landenburgfund";
//         String password="test01";
//         while (itr.hasNext())
//         {
//            UserAcctDetails uad = (UserAcctDetails) itr.next();
//            System.out.println(">> " + uad.toString());
//            String userId = "inv_" + uad.getClientAccountID().trim();
//            ExistResult existResult = servicesSoap.isWebUserExists(new AuthenticateLogin(userId, "", fundGroupName, ""));
//            System.out.println(existResult.toString());
//            System.out.println("AuthenticateLogin:[UserId:"+userId+", Password:"+password+", FundGroupName:"+fundGroupName+", AllowableShareClassList:00]" +
//                                  "accountNumber:"+uad.getClientAccountID()+", ssnorTin:"+EncryptionDecryptionAES.decrypt(uad.getSsn(), encryDecryKey)+
//                                  ",zipCode:"+uad.getMailZipCode()+", emailAddress:"+uad.getEmail()+", securityQuestion:"+securityQuestion+", " +
//                                  "securityAnswer:"+securityAnswer+", accessLinkedAccounts:"+new UnsignedByte("1"));
//
//            if (existResult.isIsExist() == false)
//            {
//
//               WebUserResult webUserResult = servicesSoap.createShareholderWebUser
//                  (new AuthenticateLogin(userId, password, fundGroupName, "00"),
//                   uad.getClientAccountID(), EncryptionDecryptionAES.decrypt(uad.getSsn(), encryDecryKey),
//                   uad.getMailZipCode(), uad.getEmail(), securityQuestion, securityAnswer, new UnsignedByte("1"));
//               if (webUserResult.getErrorStatus().getErrorCode() == 0)
//               {
//                  uad.setUserID(userId);
//                  uad.setPwd(password);
//                  uad.setSecurityQuestion(securityQuestion);
//                  uad.setSecurityAnswer(securityAnswer);
//                  uad.setFundGroupName(fundGroupName);
//                  uad.setStatus("S");
//                  uad.setRemarks(webUserResult.getErrorStatus().getErrorMessage());
//               }
//               else
//               {
//                  uad.setStatus("E");
//                  uad.setRemarks(webUserResult.getErrorStatus().getErrorMessage());
//                  emailAlertMessage.append(uad.getClientAccountID()+":"+webUserResult.getErrorStatus().getErrorMessage()+"\n");
//               }
//               System.out.println(webUserResult.toString());
//            }
//            else if (existResult.isIsExist() == true)
//            {
//               uad.setStatus("E");
//               uad.setRemarks("User already exist");
//               emailAlertMessage.append(uad.getClientAccountID()+":"+uad.getRemarks()+"\n");
//
//               //commonDao.updatePendingUserAccDetails(userAcctDetails);
//            }
//            try
//            {
//               commonDao.updatePendingUserAccDetails(uad);
//            }
//            catch (Exception e)
//            {
//               e.printStackTrace();
//            }
//            if (i <= 20)
//            {
////               WebUserResult webUserResult = servicesSoap.createShareholderWebUser(
////                  new AuthenticateLogin(userId,"test01",fundGroupName,"00"),
////                  userAcctDetails.getClientAccountID(), EncryptionDecryptionAES.decrypt(userAcctDetails.getSsn(),encryDecryKey), userAcctDetails.getMailZipCode(), userAcctDetails.getEmail(),
////                  securityQuestion, securityAnswer, new UnsignedByte("1"));
////               System.out.println(webUserResult.toString());
//            }
//
////            if(i==1){
////               if(existResult.isIsExist()==false){
////                  WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin(userId,"test01","landenburgfund","00"), userAcctDetails.getClientAccountID(), EncryptionDecryptionAES.decrypt(userAcctDetails.getSsn(),encryDecryKey), userAcctDetails.getMailZipCode(), emailAddress, securityQuestion, securityAnswer, new UnsignedByte("1"));
////                  System.out.println(webUserResult.toString());
////               }else if(existResult.isIsExist()==true){
////
////               }
////            }
//
//            System.out.println("");
//
//            i++;
//         }
//
//         //new AuthenticateLogin("test.larie", "test01", "landenburgfund", "00");
//      }
//      catch (Exception e)
//      {
//         e.printStackTrace();
//      }
//   }

   public CallStatus isWebUserExist(UserAcctDetails userAcctDetails)throws Exception
   {
      return loginService.createWebUser(userAcctDetails);
   }

   public CallStatus updateWebUserEmail(UserAcctDetails userAcctDetails, String newEmail) throws Exception
   {
      loginService=new LoginServiceImpl();
      return loginService.updateWebUserEmail(userAcctDetails,newEmail);
   }

   public CallStatus updateMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      accountService=new AccountServiceImpl();
      return accountService.updateMailingAddress(userAcctDetails);
   }

   public CallStatus getMailingAddress(UserAcctDetails userAcctDetails) throws Exception
   {
      accountService=new AccountServiceImpl();
      return accountService.getMailingAddress(userAcctDetails);
   }
}
