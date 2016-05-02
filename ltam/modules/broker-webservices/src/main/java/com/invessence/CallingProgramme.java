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
         //serviceLayer.loginUser("310100016");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");
         //System.out.println("PWD: "+EncryDecryAES.encrypt("test01",SysParameters.encryDecryKey));
        serviceLayer.createPendingUser();
         //wsCallStatus=serviceLayer.updateEmail("310100020","john@jmegan.com");
//         //wsCallStatus=serviceLayer.getMailingAddress("310100016");
       // wsCallStatus=serviceLayer.updateMailingAddress("310100016","JAIME", "L", "DESMOND","415 WEST 57TH STREET", "APT 1B NEW YORK, NY 10019","","Man Haten","NY","10019",(short)0,"2012142104","2124062680","","scott.spratlen@thegeminicompanies.com");
//         WSCallResult wsCallResult=serviceLayer.fundAccount("310100016",900, 1, "6105640720");
//         System.out.println("wsCallResult = " + wsCallResult);
         //WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100016", 903, 900, "6105640720");
  //       WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100018", 900, 909, "4302705871");
   //      System.out.println("wsCallResult = " + wsCallResult);
         //serviceLayer.getUserBankAcctDetails("310100016");
         WSCallResult wsCallResult=serviceLayer.fullFundTransfer("310100018", 900, 909, "4302705871");
         System.out.println("wsCallResult = " + wsCallResult);
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
