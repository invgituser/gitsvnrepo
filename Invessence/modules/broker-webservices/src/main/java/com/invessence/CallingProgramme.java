package com.invessence;

import com.invessence.custody.uob.UOBDataMaster;
import com.invessence.custody.uob.dao.UOBDaoImpl;
import com.invessence.ws.bean.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 3/11/2016.
 */

public class CallingProgramme
{
   public static void main(String[] args)
   {
      try
      {

         WSCallStatus wsCallStatus=null;
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
         UOBDaoImpl uobDao = (UOBDaoImpl) context.getBean("uobDaoImpl");
         UOBDataMaster uobDataMaster=(UOBDataMaster) uobDao.fetch(new Long(123));
         System.out.println("uobDataMaster = " + uobDataMaster);

//         ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");
//         serviceLayer.toTestAPI();
         //serviceLayer.loginUser("310100020");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");
         //System.out.println("PWD: "+EncryDecryAES.encrypt("test01",SysParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY));
//        serviceLayer.createPendingUser();
  //          wsCallStatus=serviceLayer.updateEmail("310100016","jaimedesmond5@hotmail.com");
//         WSCallResult wsCallResult=serviceLayer.getMailingAddress("310100043");
//         System.out.println("wsCallResult = " + wsCallResult);
//         mailingAddressesResult = MailingAddressesResult{mailingAddressId=44979, nameLines='DEEPAK SURI', addressLines='2210 246TH PL NE
//         SAMMAMISH, WA 98074', postalZip='98074', countryCode=0, voicePhone='6044361296               ', mailingAddressType=1, altPhone='', faxPhone='', entityIdentifier='310100043           ', errorStatus=Status{errorCode=0, errorMessage='Done'}, emailAddress='DSURI@TELUS.NET                                   '}
//       wsCallStatus=serviceLayer.updateMailingAddress("310100043","'DEEPAK", "", "SURI","2210 246TH PL NE SAMMAMISH", "WA 98074","","SAMMAMISH","WA","98074",(short)0,"6044361296","","","DSURI@TELUS.NET");

//        wsCallStatus=serviceLayer.updateMailingAddress("310100016","JAIME", "L", "DESMOND","415 WEST 57TH STREET", "APT 1B NEW YORK, NY 10019","","Man Haten","NY","10019",(short)0,"2012142104","2124062680","","scott.spratlen@thegeminicompanies.com");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100016",900, 1, "6105640720");
//         System.out.println("wsCallResult = " + wsCallResult);
//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100016", 903, 900, "6105640720");
//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100018", 900, 909, "4302705871");
//         System.out.println("wsCallResult = " + wsCallResult);
//         serviceLayer.getUserBankAcctDetails("310100046");
//           WSCallResult wsCallResult=serviceLayer.fundAccount("310100045",906, 1, "564803");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100043",906, 1, "2427206518");

//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100043", 906, 903, "2427206518");
//         serviceLayer.resetPassword("310100043");
         //serviceLayer.loginUser("310100043");
//         serviceLayer.getUserBankAcctDetails("310100043");
//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer ("310100020",900,903,"000214128918");//("310100018", 900, 909, "4302705871");
//         System.out.println("wsCallResult = " + wsCallResult);

//                  serviceLayer.getUserBankAcctDetails("310100046");

       //  WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100046", 906, 903, "358233");

//         serviceLayer.getUserBankAcctDetails("310100020");
        //WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100020", 900, 903, "000214128918");

//         serviceLayer.getUserBankAcctDetails("310100017");
         //WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100017", 912, 900, "6105640720");
//         serviceLayer.getUserBankAcctDetails("310100028");

//         serviceLayer.getUserBankAcctDetails("310100041");
//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100041", 900, 903, "003811916157");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100041",903, 1, "003811916157");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100028",903, 1, "6105640720");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100046",903, 1, "358233");
//
//         serviceLayer.getUserBankAcctDetails("310100028");
//         serviceLayer.loginUser("310100033");
         //serviceLayer.resetPassword("310100046");
//         serviceLayer.loginUser("310100046");
//         System.out.println("-------------------------------------------------------------------");
//         System.out.println("wsCalltatus = " + wsCallStatus);
//         System.out.println("isServiceActive"+serviceLayer.isServiceActive());
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
