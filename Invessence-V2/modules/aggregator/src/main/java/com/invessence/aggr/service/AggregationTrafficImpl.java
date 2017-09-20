package com.invessence.aggr.service;

import com.invessence.aggr.bean.*;
import com.invessence.aggr.util.AggregatorMessages;
import com.invessence.service.util.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

/**
 * Created by abhangp on 11/30/2016.
 */
@Service
public class AggregationTrafficImpl implements AggregationTraffic
{
   private static final Logger logger = Logger.getLogger(AggregationTrafficImpl.class);
   @Autowired @Qualifier("MXAggregation")
   AggregationService mxAggregationService;

   @Autowired @Qualifier("YodleeAggregation")
   AggregationService yodleeAggregationService;

   @Override
   public UserProfile registerUser(UserProfile user)   {

      try
      {
         System.out.println("AggregationTrafficImpl.registerUser");
         System.out.println("user = [" + user + "]");
         user=mxAggregationService.registerUser(user);
      }catch (Exception e){
         user.setErrorStatus(new Status(AggregatorMessages.wsIGenErrCode, AggregatorMessages.wsIGenErrMsg));
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return user;
   }

   @Override
   public UserProfile readUser(UserProfile user)
   {
      System.out.println("AggregationTrafficImpl.readUser");
      System.out.println("user = [" + user + "]");
      try
      {
         System.out.println("user = [" + user + "]");
         mxAggregationService.readUser(user);
      }catch (Exception e){
         user.setErrorStatus(new Status(AggregatorMessages.wsIGenErrCode, AggregatorMessages.wsIGenErrMsg));
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return user;
   }

   @Override
   public UserProfile updateUser(UserProfile user)
   {
      System.out.println("AggregationTrafficImpl.updateUser");
      return null;
   }

   @Override
   public UserProfile deleteUser(UserProfile user)
   {
      System.out.println("AggregationTrafficImpl.deleteUser");
      System.out.println("user = [" + user + "]");
      try{mxAggregationService.deleteUser(user);}catch (Exception e){
         user.setErrorStatus(new Status(AggregatorMessages.wsIGenErrCode, AggregatorMessages.wsIGenErrMsg));
         logger.error(e.getMessage());
         e.printStackTrace();
      }
      return user;
   }

   @Override
   public Url getWidgetUrl(UserProfile user)
   {
      Url url=null;
     try{
        url=mxAggregationService.getWidgetUrl(user);
   }catch (Exception e){
        url=new Url(null,null,null,new Status(AggregatorMessages.wsIGenErrCode, AggregatorMessages.wsIGenErrMsg));
        logger.error(e.getMessage());
        e.printStackTrace();
   }
      return url;
   }

   private AggregationService serviceDecider(){
      AggregationService  aggregationService= null;

      System.out.println("******------------------");
      String webServiceAPI= ServiceParameters.BROKER_WEBSERVICE_API;//getServiceProvider();
      if(webServiceAPI==null){
         logger.error(Constant.SERVICES.BROKER_WEBSERVICES.toString()+" API is not available");
      }else if(webServiceAPI.equals("MX")){
         System.out.println(webServiceAPI);
//         callingLayer = geminiCallingLayer; //new CallingLayerGeminiImpl(commonDao);
      }else if(webServiceAPI.equals("YODLEE")){
         System.out.println(webServiceAPI);
//         callingLayer = tdCallingLayer;//new CallingLayerTDImpl();
      }else{
         logger.error(webServiceAPI+" API is not available");
      }
      return aggregationService;
   }
}
