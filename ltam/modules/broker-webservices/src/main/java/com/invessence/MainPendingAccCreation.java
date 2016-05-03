package com.invessence;

import com.invessence.ws.service.ServiceLayerImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 3/11/2016.
 */
public class MainPendingAccCreation
{
   public static void main(String[] args)
   {
      try
      {
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
         ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");
         serviceLayer.createPendingUser();
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
