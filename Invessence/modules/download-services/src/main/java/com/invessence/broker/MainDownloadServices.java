package com.invessence.broker;

import com.invessence.service.bean.ServiceRequest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 1/17/2016.
 */
public class MainDownloadServices
{

   public static void main(String[] args) {
      try {

         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("downloadServiceConf.xml");
         DownloadFileProcessor bfp = (DownloadFileProcessor)context.getBean("downloadFileProcessor");
         System.out.println("************** Execution Start **************");
         if(args.length>=2)
         {
            ServiceRequest serviceRequest = new ServiceRequest(args[0], args[1]);
            System.out.println("serviceRequest = " + serviceRequest);

            bfp.process(serviceRequest);
         }else{
            System.out.println("Required parameters are not entered!");
         }

         context.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }


}
