package com.invessence;

import com.invessence.dao.CommonDao;
import com.invessence.service.*;
import com.ws.gemini.service.CallingLayerGeminiImpl;
import com.ws.td.service.ServiceLayerTDImpl;
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
         serviceLayer.createPendingWebUser();
      }catch(Exception e){
         e.printStackTrace();
      }
   }

   public void call(){
      try
      {
         System.out.println("CallingProgramme call");
         StringBuilder emailAlertMessage;

         //serviceLayer.loginWebUser("310100018");
         //serviceLayer.createWebUser("310100028","secQuest","secAnsw");

         //serviceLayer.createPendingWebUser();
         //serviceLayer.updateEmail("310100018","javyas@gmail.com");
        serviceLayer.getMailingAddress("310100018");
         serviceLayer.updateMailingAddress("310100018");
      }catch(Exception e){
         e.printStackTrace();
      }
   }
}
