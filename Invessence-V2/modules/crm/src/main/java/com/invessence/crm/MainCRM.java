package com.invessence.crm;

import com.invessence.crm.bean.*;
import com.invessence.crm.provider.redTail.bean.User;
import com.invessence.crm.service.CRMTrafficImpl;
import com.invessence.service.bean.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by abhangp on 12/1/2016.
 */
public class MainCRM
{
   public static void main(String[] args)
   {
      try
      {
         ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("crmConfig.xml");
         CRMTrafficImpl crmTraffic = (CRMTrafficImpl) context.getBean("crm");
         System.out.println("************** Execution Start **************");
         if(args.length>=3)
         {
            ServiceRequest serviceRequest = new ServiceRequest(args[0], args[1], args[2]);
            System.out.println("serviceRequest = " + serviceRequest);
            WSCallResult wsCallResult=crmTraffic.authentication(serviceRequest, new User("abhangp","Invessence@2017","16E6A8B3-4515-4BF0-AEFC-05D5B33A7EEB","2446133B-A0B2-4F1B-8112-2248B2CA5A9D"));
            System.out.println("wsCallResult = " + wsCallResult);
//            WSCallResult wsCallResult1=crmTraffic.ssoLogin(serviceRequest, new User("abhangp","Invessence@2017","16E6A8B3-4515-4BF0-AEFC-05D5B33A7EEB","2446133B-A0B2-4F1B-8112-2248B2CA5A9D"));
//            System.out.println("wsCallResult1 = " + wsCallResult1);

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
