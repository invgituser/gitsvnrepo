package com.invessence.ws.provider.td.docusign;

import com.invessence.ws.bean.WSCallStatus;
import com.invessence.ws.service.ServiceLayerImpl;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 8/18/2016.
 */

public class TestClass
{
   public static void main(String[] args)
   {
      WSCallStatus wsCallStatus=null;
      ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
      ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayer");

      System.out.println(serviceLayer.processDCRequest(new Long(2638),1));
      //418	2364	1
   }
}
