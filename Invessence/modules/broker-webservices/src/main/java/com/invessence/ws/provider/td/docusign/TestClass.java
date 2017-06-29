package com.invessence.ws.provider.td.docusign;

import com.invessence.service.bean.ServiceRequest;
import com.invessence.ws.bean.*;
import com.invessence.ws.provider.td.docusign.web.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 8/18/2016.
 */

public class TestClass
{
   public static void main(String[] args) throws Exception
   {
      WSCallStatus wsCallStatus=null;
      ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("webServicesConfig.xml");
      DCWebLayerClass dcWebLayer= (DCWebLayerClassImpl) context.getBean("dcWebLayerClass");
      WSCallResult wsCallResult= dcWebLayer.processDCRequest(new ServiceRequest("BUILDINGBENJAMINS", "UAT"),
                                                             new Long(3104), 1);
      System.out.println("wsCallResult = " + wsCallResult);
//      wsCallResult= dcWebLayer.processDCRequest(new ServiceRequest("TCM", "PROD"),
//                                                             new Long(2959), 2);//BUILDINGBENJAMINS //ACTUL.PROD
//      System.out.println("wsCallResult = " + wsCallResult);
//      WSCallResult wsCallResult2= dcWebLayer.processDCRequest(new ServiceRequest("TCM", "UAT"),new Long(3144), 2);//BUILDINGBENJAMINS
//      System.out.println("wsCallResult = " + wsCallResult2);
//      ServiceLayerImpl serviceLayer = (ServiceLayerImpl) context.getBean("serviceLayer");
//      WSCallResult wsCallResult1=serviceLayer.processDCRequest(new ServiceRequest("BUILDINGBENJAMINS", "UAT"), (List<DCRequest>)wsCallResult.getGenericObject());
//      if(wsCallResult1.getWSCallStatus().getErrorCode()==0){
//         DCResponse dcResponse=(DCResponse)wsCallResult1.getGenericObject();
//         System.out.println("dcResponse = " + dcResponse);
//      }
//      System.out.println(); // 2334
//      System.out.println(serviceLayer.processDCRequest(new ServiceRequest("BUILDINGBENJAMINS", "PROD"), (List<DCRequest>)wsCallResult.getGenericObject())); // 2334
//      418	2364	1
   }
}
