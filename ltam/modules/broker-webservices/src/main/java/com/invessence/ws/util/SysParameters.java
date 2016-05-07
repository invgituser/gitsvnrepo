package com.invessence.ws.util;

import java.util.*;

import com.invessence.constant.Const;
import com.invessence.ws.bean.*;
import com.invessence.ws.dao.WSCommonDao;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;

/**
 * Created by abhangp on 4/4/2016.
 */
@Configuration
//@PropertySource("classpath:systemParameters.properties")
public class SysParameters
{
   private static final Logger logger = Logger.getLogger(SysParameters.class);
//   @Bean
//   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//      return new PropertySourcesPlaceholderConfigurer();
//   }

   public static String webServiceAPI;
   public static String geminiEndPointUrl;
   public static String tdEndPointUrl;


   public static final String encryDecryKey="aRXDugfr4WQpVrxu";
   //String encryDecryKey="GEMINI-KEY";
   public static String fundGroupName;

   public static int wsResIssueCode;
   public static String wsResIssueMsg;

   public static int wsOperationNACode;
   public static String wsOperationNAMsg;

   public static int wsIGenErrCode;
   public static String wsIGenErrMsg;

   public static int wsEGenErrCode;
   public static String wsEGenErrMsg;

   public static int wsUserRegIssueCode;
   public static String wsUserRegIssueMsg;

   public static int wsDBErrCode;
   public static String wsDBErrMsg;


//   public static String getUserAccDetailsByAccNumber;
//   public static String getPendingUserAccDetails;
//   public static String updatePendingUserAccDetailsOnSuccess;
//   public static String updatePendingUserAccDetailsOnFailure;
//   public static String updateUserEmail;
//   public static String getAccountExtInfo;

   @Autowired
   private WSCommonDao wsCommonDao;

   public static Map<String, Map<String, List<ServiceDetails>>> serviceDetailsMap;
//   public void saveWSRequest(String status, String clientAccountID, String opt, String remarks, Date reqTime, String reqXml, Date resTime, String resXml, String requestType){
//      try{
//         wsCommonDao.insertWSRequest(new WSRequest(status, clientAccountID, opt, remarks, reqTime, reqXml, resTime, resXml, requestType));}
//      catch(Exception e){
//         e.printStackTrace();
//         logger.error(e.getMessage());
//      }
//   }
   @Autowired
   public void setServiceDetails() {
      try{
         List<ServiceDetails> serviceDetailsList=wsCommonDao.getServiceDetails(Const.COMPANY_NAME);
         Iterator<ServiceDetails>itr=serviceDetailsList.iterator();
         Map<String, Map<String, List<ServiceDetails>>> serviceDetails=new LinkedHashMap<String, Map<String, List<ServiceDetails>>>();
         Map<String, List<ServiceDetails>> apiDetails=null;
         List<ServiceDetails> listOfOperation=null;
         String serviceKey=null, apiKey=null;
         ServiceDetails servDetails=null;
         while (itr.hasNext()) {
             servDetails = (ServiceDetails) itr.next();

            if(serviceKey==null){
               serviceKey=servDetails.getService();
               apiKey=servDetails.getProvider();
               apiDetails=new LinkedHashMap<>();

               listOfOperation=new ArrayList<>();
               listOfOperation.add(servDetails);

            }else if(servDetails.getService().equalsIgnoreCase(serviceKey)){
               if(servDetails.getProvider().equalsIgnoreCase(apiKey)){
                  listOfOperation.add(servDetails);
               }else if(!servDetails.getProvider().equalsIgnoreCase(apiKey)){
                  apiDetails.put(apiKey,listOfOperation);

                  apiKey=servDetails.getProvider();
                  listOfOperation=new ArrayList<>();
                  listOfOperation.add(servDetails);
               }

            }else if(! servDetails.getService().equalsIgnoreCase(serviceKey)){
//               apiDetails=new LinkedHashMap<>();
               apiDetails.put(apiKey,listOfOperation);
               serviceDetails.put(serviceKey, apiDetails);

               serviceKey=servDetails.getService();
//               apiDetails=new HashMap<>();
//               apiDetails.put(apiKey,listOfOperation);

               apiKey=servDetails.getProvider();
               apiDetails=new LinkedHashMap<>();
               listOfOperation=new ArrayList<>();
               listOfOperation.add(servDetails);

            }
            //System.out.println("servDetails = " + servDetails);

         }
         apiDetails.put(apiKey,listOfOperation);
         serviceDetails.put(serviceKey, apiDetails);

         System.out.println("*****************************");
         System.out.println("Size of ServiceDetails Map = "+serviceDetails.size());

         Iterator<Map.Entry<String, Map<String, List<ServiceDetails>>>> entries = serviceDetails.entrySet().iterator();
         while (entries.hasNext()) {
            Map.Entry<String, Map<String, List<ServiceDetails>>> entry = entries.next();
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
         }

         System.out.println("*****************************");
         SysParameters.serviceDetailsMap =serviceDetails;

         setWebServiceAPI(); // setting  broker-webservice provider
      }catch(Exception e){
         logger.error("Exception while loading service details");
         logger.error(e.getMessage());
      }

   }

   public void setWebServiceAPI()
   {
      System.out.println("SysParameters.setWebServiceAPI");
      if(SysParameters.serviceDetailsMap.containsKey(Const.Services.BROKER_WEBSERVICES.toString())){
         Map<String, List<ServiceDetails>>sd= SysParameters.serviceDetailsMap.get(Const.Services.BROKER_WEBSERVICES.toString());
         if(sd==null || sd.size()==0){
            System.out.println("Expecting single API provider for "+Const.Services.BROKER_WEBSERVICES.toString()+" service but it gets none");
         }else if(sd.size()>1){
            System.out.println("Expecting single API provider for "+Const.Services.BROKER_WEBSERVICES.toString()+" service but it gets more the one");
            SysParameters.webServiceAPI =sd.keySet().toArray()[0].toString();
         }else if(sd.size()==1){
            SysParameters.webServiceAPI =sd.keySet().toArray()[0].toString();
         }
      }
      //SysParameters.webServiceAPI = webServiceAPI;
      System.out.println("webServiceAPI = " + webServiceAPI);
   }

   @Autowired
   public void setFundGroupName(@Value("${fundGroupName}")String fundGroupName)
   {
      SysParameters.fundGroupName = fundGroupName;
   }

   @Autowired
   public void setWsOperationNAMsgCode(@Value("${wsOperationNACode}")int wsOperationNACode)
   {
      SysParameters.wsOperationNACode = wsOperationNACode;
   }
   @Autowired
   public void setWsOperationNAMsg(@Value("${wsOperationNAMsg}")String wsOperationNAMsg)
   {
      SysParameters.wsOperationNAMsg = wsOperationNAMsg;
   }
   @Autowired
   public void setWsUserRegIssueCode(@Value("${wsUserRegIssueCode}")int wsUserRegIssueCode)
   {
      SysParameters.wsUserRegIssueCode = wsUserRegIssueCode;
   }
   @Autowired
   public void setWsUserRegIssueMsg(@Value("${wsUserRegIssueMsg}") String wsUserRegIssueMsg)
   {
      SysParameters.wsUserRegIssueMsg = wsUserRegIssueMsg;
   }
   @Autowired
   public void setWsDBErrCode(@Value("${wsDBErrCode}")int wsDBErrCode)
   {
      SysParameters.wsDBErrCode = wsDBErrCode;
   }
   @Autowired
   public void setWsDBErrMsg(@Value("${wsDBErrMsg}")String wsDBErrMsg)
   {
      SysParameters.wsDBErrMsg = wsDBErrMsg;
   }

   @Autowired
   public void setWsResIssueCode(@Value("${wsResIssueCode}") int wsResIssueCode)
   {
      SysParameters.wsResIssueCode = wsResIssueCode;
   }

   @Autowired
   public void setWsResIssueMsg(@Value("${wsResIssueMsg}") String wsResIssueMsg)
   {
      SysParameters.wsResIssueMsg = wsResIssueMsg;
   }

   @Autowired
   public void setWsIGenErrCode(@Value("${wsIGenErrCode}")int wsIGenErrCode)
   {
      SysParameters.wsIGenErrCode = wsIGenErrCode;
   }

   @Autowired
   public void setWsIGenErrMsg(@Value("${wsIGenErrMsg}")String wsIGenErrMsg)
   {
      SysParameters.wsIGenErrMsg = wsIGenErrMsg;
   }

   @Autowired
   public void setWsEGenErrCode(@Value("${wsEGenErrCode}")int wsEGenErrCode)
   {
      SysParameters.wsEGenErrCode = wsEGenErrCode;
   }

   @Autowired
   public void setWsEGenErrMsg(@Value("${wsEGenErrMsg}")String wsEGenErrMsg)
   {
      SysParameters.wsEGenErrMsg = wsEGenErrMsg;
   }
//   @Autowired
//   public void setGetAccountExtInfo(@Value("${getAccountExtInfo}")String getAccountExtInfo)
//   {
//      SysParameters.getAccountExtInfo = getAccountExtInfo;
//   }
//   @Autowired
//   public void setGetPendingUserAccDetails(@Value("${getPendingUserAccDetails}")String getPendingUserAccDetails)
//   {
//      SysParameters.getPendingUserAccDetails = getPendingUserAccDetails;
//   }
//   @Autowired
//   public void setGetUserAccDetailsByAccNumber(@Value("${getUserAccDetailsByAccNumber}")String getUserAccDetailsByAccNumber)
//   {
//      SysParameters.getUserAccDetailsByAccNumber = getUserAccDetailsByAccNumber;
//   }
//   @Autowired
//   public void setUpdatePendingUserAccDetailsOnSuccess(@Value("${updatePendingUserAccDetailsOnSuccess}")String updatePendingUserAccDetailsOnSuccess)
//   {
//      SysParameters.updatePendingUserAccDetailsOnSuccess = updatePendingUserAccDetailsOnSuccess;
//   }


//   @Autowired
//   public void setUpdatePendingUserAccDetailsOnFailure(@Value("${updatePendingUserAccDetailsOnFailure}")String updatePendingUserAccDetailsOnFailure)
//   {
//      SysParameters.updatePendingUserAccDetailsOnFailure = updatePendingUserAccDetailsOnFailure;
//   }



//   @Autowired
//   public void setUpdateUserEmail(@Value("${updateUserEmail}")String updateUserEmail)
//   {
//      SysParameters.updateUserEmail = updateUserEmail;
//   }

   @Autowired
   public void setGeminiEndPointUrl(@Value("${geminiEndPointUrl}")String geminiEndPointUrl)
   {
      SysParameters.geminiEndPointUrl = geminiEndPointUrl;
   }

   @Autowired
   public void setTdEndPointUrl(@Value("${tdEndPointUrl}")String tdEndPointUrl)
   {
      SysParameters.tdEndPointUrl = tdEndPointUrl;
   }


}
