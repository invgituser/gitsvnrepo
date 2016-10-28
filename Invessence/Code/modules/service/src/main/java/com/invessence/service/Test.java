package com.invessence.service;

import com.invessence.service.util.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 5/17/2016.
 */
public class Test
{
   public static void main(String[] args)
   {
      try
      {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("serviceConfig.xml");
         TestImpl serviceLayer = (TestImpl) context.getBean("testImpl");
         System.out.println(ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()));
         System.out.println(ServiceParameters.getConfigProperty(Constant.SERVICES.EMAIL_SERVICE.toString(),ServiceParameters.getServiceProvider(Constant.SERVICES.EMAIL_SERVICE.toString()),"HOST"));
   }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }
}
