package com.invessence;

import com.invessence.service.*;
import com.invessence.util.SysParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by abhangp on 3/11/2016.
 */
@Component
public class CallingProgramme
{
   @Autowired
   ServiceLayer serviceLayer;
   public void createPendingWebUser(){
      try
      {
         serviceLayer.createPendingUser();
      }catch(Exception e){
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

         //serviceLayer.loginUser("310100018");
         //serviceLayer.createUser("310100028","secQuest","secAnsw");

         //serviceLayer.createPendingUser();
         //serviceLayer.updateEmail("310100018","javyas@gmail.com");
        //serviceLayer.getMailingAddress("310100016");
         //serviceLayer.updateMailingAddress("310100016","","","",(short)1,"","","","");
         //serviceLayer.fundAccount("310100016",900, 1, "6105640720");
         //serviceLayer.fundTransfer("310100016",900, 903, 1, "6105640720");
         serviceLayer.getUserBankAcctDetails("310100016");
      }catch(Exception e){
         e.printStackTrace();
      }
   }
}
