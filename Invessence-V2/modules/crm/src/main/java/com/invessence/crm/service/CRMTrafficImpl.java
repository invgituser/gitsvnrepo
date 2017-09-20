package com.invessence.crm.service;

import com.invessence.crm.bean.*;
import com.invessence.crm.util.CRMMessages;
import com.invessence.service.bean.*;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 11/30/2016.
 */
@Service
public class CRMTrafficImpl implements CRMTraffic
{
   private static final Logger logger = Logger.getLogger(CRMTrafficImpl.class);
   @Autowired @Qualifier("RedTailCRM")
   CRMService redTailCRMService;

   @Autowired @Qualifier("SalesForceCRM")
   CRMService salesForceCRMService;

   @Override
   public WSCallResult authentication(ServiceRequest serviceRequest, Object object)
   {
      System.out.println("CRMTrafficImpl.authentication");
      System.out.println("serviceRequest = [" + serviceRequest + "], object = [" + object + "]");
      try
      {
         return redTailCRMService.authentication(serviceRequest,object);
      }catch (Exception e){
//         user.setErrorStatus(new Status(CRMMessages.wsIGenErrCode, CRMMessages.wsIGenErrMsg));
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return null;
   }

   @Override
   public WSCallResult ssoLogin(ServiceRequest serviceRequest, Object object)
   {
      System.out.println("CRMTrafficImpl.ssoLogin");
      System.out.println("serviceRequest = [" + serviceRequest + "], object = [" + object + "]");
      try
      {
         return redTailCRMService.ssoLogin(serviceRequest,object);
      }catch (Exception e){
//         user.setErrorStatus(new Status(CRMMessages.wsIGenErrCode, CRMMessages.wsIGenErrMsg));
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return null;
   }

   private CRMService serviceDecider(){
      CRMService crmService= null;

      System.out.println("******------------------");
      String webServiceAPI= ServiceParameters.BROKER_WEBSERVICE_API;//getServiceProvider();
      if(webServiceAPI==null){
         logger.error(Constant.SERVICES.BROKER_WEBSERVICES.toString()+" API is not available");
      }else if(webServiceAPI.equals("RedTail")){
         System.out.println(webServiceAPI);
//         callingLayer = geminiCallingLayer; //new CallingLayerGeminiImpl(commonDao);
      }else if(webServiceAPI.equals("YODLEE")){
         System.out.println(webServiceAPI);
//         callingLayer = tdCallingLayer;//new CallingLayerTDImpl();
      }else{
         logger.error(webServiceAPI+" API is not available");
      }
      return crmService;
   }
}
