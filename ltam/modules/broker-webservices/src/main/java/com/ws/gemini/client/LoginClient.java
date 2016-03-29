package com.ws.gemini.client;

import com.ws.gemini.wsdl.login.*;
import org.apache.axis.types.UnsignedByte;

public class LoginClient
{

   public static void main(String[] args)
   {
      try
      {
         LoginServicesLocator locator = new LoginServicesLocator();
         LoginServicesSoap_PortType servicesSoap = locator.getLoginServicesSoap();
//			 UserId = "test.shareholder",
//		                Password = "test01",
//		                FundGroupName = "landenburgfund",
//		                AllowableShareClassList = "00"
         //MasterWebuserResult masterWebuserResult=servicesSoap.masterLogin(new AuthenticateLogin("test.shareholder", "test01", "landenburgfund", "00"));
//			System.out.println(masterWebuserResult.getErrorStatus());
//			System.out.println(masterWebuserResult.isUserStatus());
         //WebUserResult webUserResult =servicesSoap.shareholderLogin(new AuthenticateLogin("test.shareholder", "test01", "landenburgfund", "00"), new BigDecimal("0"));
/*
310100010
310100016
310100017
310100018
310100019
310100020
310100022
310100023
310100026
310100028

154024121
142785669
142785669
153702226
227238855
148560154
249257726
74641372
117526393
142785669


 */ //servicesSoap.updateWebUser();
//         AuthenticateLogin userLogin=new AuthenticateLogin("test.Bob", "test01", "landenburgfund", "00");
//
//         WebUserResult webUserResult = servicesSoap.createShareholderWebUser(userLogin,
//                                                                             "310100010",
//                                                                             "142785669",
//                                                                             "zipCode",
//                                                                             "email",
//                                                                             "secQuest",
//                                                                             "secAnsw",
//                                                                             new UnsignedByte("1"));
//
//
//         System.out.println(webUserResult.toString());
//
//         ExistResult existResult = servicesSoap.isWebUserExists(userLogin);
//
//         System.out.println(existResult.toString());

//         WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.larie","test01","landenburgfund","00"),"310100010","154024121","68007","lariel@geminifund.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.jaime","test01","landenburgfund","00"),"310100016","142785669","10019","jaimedesmond5@hotmail.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.jaime","test01","landenburgfund","00"),"310100017","142785669","10019","jaimedesmond5@hotmail.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.jigar","test01","landenburgfund","00"),"310100018","153702226","7928","javyas@gmail.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.chris","test01","landenburgfund","00"),"310100019","227238855","6878","c.lengle@gmail.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.john","test01","landenburgfund","00"),"310100020","148560154","7940","john@jmegan.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.pamela","test01","landenburgfund","00"),"310100022","249257726","68135","jwertheim@saionline.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.adam","test01","landenburgfund","00"),"310100023","74641372","33138","adam@ladenburg.com","secQuest","secAnsw",new UnsignedByte("1"));
         WebUserResult webUserResult = servicesSoap.createShareholderWebUser(new AuthenticateLogin("test.mark","test01","landenburgfund","00"),"310100026","117526393","33109","markze@me.com","secQuest","secAnsw",new UnsignedByte("1"));
//	        WebUserResult webUserResult = servicesSoap.createShareholderWebUser(
//              new AuthenticateLogin("test.jaime","test01","landenburgfund","00"),
//              "310100028","142785669","10019","jaimedesmond5@hotmail.com","secQuest","secAnsw",new UnsignedByte("1"));

         System.out.println(webUserResult.toString());


            ExistResult existResult = servicesSoap.isWebUserExists(new AuthenticateLogin("test.mark","","landenburgfund",""));

            System.out.println(existResult.toString());

         //servicesSoap.updateWebUserCustomValues()

//         WebUserResult webUserResult = servicesSoap.getWebUserForUserIdSSNOrTIN(new AuthenticateLogin("test.mark","","landenburgfund","00"),"117526393");
//         System.out.println(webUserResult.toString());

			/*(AuthenticateLogin userLogin,
         String accountNumber,
			String ssnorTin,
			String zipCode,
			String emailAddress,
			String securityQuestion,
			String securityAnswer,
			org.apache.axis.types.UnsignedByte accessLinkedAccounts) throws java.rmi.RemoteException;*/

      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
