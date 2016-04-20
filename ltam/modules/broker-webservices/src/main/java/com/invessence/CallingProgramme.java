package com.invessence;

import com.invessence.ws.bean.WSCallStatus;
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
         //serviceLayer.loginUser("310100018");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");

         //serviceLayer.createPendingUser();
         //wsCallStatus=serviceLayer.updateEmail("310100018","javyas@gmail.com");
         //wsCallStatus=serviceLayer.getMailingAddress("310100016");
         //wsCallStatus=serviceLayer.updateMailingAddress("310100016","JAIME", "L", "DESMOND","415 WEST 57TH STREET", "APT 1B NEW YORK, NY 10019","","10019",(short)0,"2012142104","2124062680","","scott.spratlen@thegeminicompanies.com");
         //wsCallStatus=serviceLayer.fundAccount("310100016",900, 1, "6105640720");
         wsCallStatus=serviceLayer.fullFundTransfer("310100016", 900, 903, "6105640720");
         //wsCallStatus=serviceLayer.getUserBankAcctDetails("310100016");
         System.out.println("wsCalltatus = " + wsCallStatus);
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
