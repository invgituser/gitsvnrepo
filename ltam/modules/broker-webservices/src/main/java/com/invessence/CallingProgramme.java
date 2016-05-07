package com.invessence;

import com.invessence.util.EncryDecryAES;
import com.invessence.ws.bean.*;
import com.invessence.ws.service.*;
import com.invessence.ws.util.SysParameters;
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
         ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");
//         serviceLayer.toTestAPI();
         //serviceLayer.loginUser("310100020");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");
         //System.out.println("PWD: "+EncryDecryAES.encrypt("test01",SysParameters.encryDecryKey));
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

                  serviceLayer.getUserBankAcctDetails("310100046");
//           WSCallResult wsCallResult=serviceLayer.fundAccount("310100046",903, 1, "358233");
       //  WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100046", 906, 903, "358233");

//         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100020", 903, 900, "000214128918");
         System.out.println("-------------------------------------------------------------------");
         System.out.println("wsCalltatus = " + wsCallStatus);
         System.out.println("isServiceActive"+serviceLayer.isServiceActive());
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }


   public void call(){
      try
      {
         System.out.println(SysParameters.geminiEndPointUrl);
         System.out.println("CallingProgramme call");
         StringBuilder
            emailAlertMessage;


      }catch(Exception e){
         e.printStackTrace();
      }
   }
}
