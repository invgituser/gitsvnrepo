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
      ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayerImpl");
//      serviceLayer.processDCRequest(new Long(1955),1);
//      serviceLayer.processDCRequest(new Long(1958),1);
//      serviceLayer.processDCRequest(new Long(1958),2);
//      serviceLayer.processDCRequest(new Long(1959),1);
//      serviceLayer.processDCRequest(new Long(1963),1);
//      serviceLayer.processDCRequest(new Long(1961),1);
//      serviceLayer.processDCRequest(new Long(123),11);
//      serviceLayer.processDCRequest(new Long(123),12);
//      serviceLayer.processDCRequest(new Long(123),2);
      //1929	1


//      System.out.println(serviceLayer.processDCRequest(new Long(123),2));

      System.out.println(serviceLayer.processDCRequest(new Long(123),4));
//      System.out.println(serviceLayer.processDCRequest(new Long(123),11));
//      System.out.println(serviceLayer.processDCRequest(new Long(2052),1));
//      System.out.println(serviceLayer.processDCRequest(new Long(2059),1));
//      System.out.println(serviceLayer.processDCRequest(new Long(2068),1));
   }
}
