package com.invessence;

import com.invessence.ws.service.*;
import com.invessence.ws.util.SysParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 3/11/2016.
 */

public class CallingProgramme
{
   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
         ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");
         //serviceLayer.loginUser("310100018");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");

         //serviceLayer.createPendingUser();
         //serviceLayer.updateEmail("310100018","javyas@gmail.com");
         //serviceLayer.getMailingAddress("310100016");
         //serviceLayer.updateMailingAddress("310100016","JAIME", "L", "DESMOND","415 WEST 57TH STREET", "APT 1B NEW YORK, NY 10019","","10019",(short)0,"2012142104","2124062680","","scott.spratlen@thegeminicompanies.com");
         //serviceLayer.fundAccount("310100016",900, 1, "6105640720");
         //serviceLayer.fundTransfer("310100016",903, 900, 1, "6105640720");
         serviceLayer.getUserBankAcctDetails("310100016");
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
