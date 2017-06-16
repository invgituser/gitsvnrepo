package com.invessence.fileProcessor;

import com.invessence.fileProcessor.service.FileProcessor;
import com.invessence.service.bean.ServiceRequest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
   This class is use to call method process() of PriceProcessor.class
 */
public class MainFileProcessor
{

   public static void main(String[] args)
   {
      try
      {
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("fileProcessorConfig.xml");
         FileProcessor pp = context.getBean(FileProcessor.class);
         System.out.println("************** Execution Start **************");
         if(args.length>=3)
         {
            ServiceRequest serviceRequest = new ServiceRequest(args[0], args[1], args[2]);
            System.out.println("serviceRequest = " + serviceRequest);

            pp.process(serviceRequest);
         }else{
            System.out.println("Required parameters are not entered!");
         }
         context.close();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }

   }
}
