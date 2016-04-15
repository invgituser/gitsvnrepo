package com.invessence.ws.util;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by abhangp on 4/4/2016.
 */
@Configuration
//@PropertySource("classpath:systemParameters.properties")
public class SysParameters
{

//   @Bean
//   public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//      return new PropertySourcesPlaceholderConfigurer();
//   }

   public static String webServiceAPI;
   public static String geminiEndPointUrl;
   public static String tdEndPointUrl;


   public static String fundGroupName="landenburgfund";
   public static int wsResIssueCode=501;
   public static String wsResIssueMsg="WebService response issue";


   public static String getUserAccDetailsByAccNumber;
   public static String getPendingUserAccDetails;
   public static String updatePendingUserAccDetailsOnSuccess;
   public static String updatePendingUserAccDetailsOnFailure;
   public static String updateUserEmail;
   public static String getAccountExtInfo;

   @Autowired
   public void setWebServiceAPI(@Value("${webServiceAPI}") String webServiceAPI)
   {
      SysParameters.webServiceAPI = webServiceAPI;
      System.out.println("webServiceAPI = " + webServiceAPI);
   }

   @Autowired
   public void setGetAccountExtInfo(@Value("${getAccountExtInfo}")String getAccountExtInfo)
   {
      SysParameters.getAccountExtInfo = getAccountExtInfo;
   }
   @Autowired
   public void setGetPendingUserAccDetails(@Value("${getPendingUserAccDetails}")String getPendingUserAccDetails)
   {
      SysParameters.getPendingUserAccDetails = getPendingUserAccDetails;
   }
   @Autowired
   public void setGetUserAccDetailsByAccNumber(@Value("${getUserAccDetailsByAccNumber}")String getUserAccDetailsByAccNumber)
   {
      SysParameters.getUserAccDetailsByAccNumber = getUserAccDetailsByAccNumber;
   }
   @Autowired
   public void setUpdatePendingUserAccDetailsOnSuccess(@Value("${updatePendingUserAccDetailsOnSuccess}")String updatePendingUserAccDetailsOnSuccess)
   {
      SysParameters.updatePendingUserAccDetailsOnSuccess = updatePendingUserAccDetailsOnSuccess;
   }


   @Autowired
   public void setUpdatePendingUserAccDetailsOnFailure(@Value("${updatePendingUserAccDetailsOnFailure}")String updatePendingUserAccDetailsOnFailure)
   {
      SysParameters.updatePendingUserAccDetailsOnFailure = updatePendingUserAccDetailsOnFailure;
   }



   @Autowired
   public void setUpdateUserEmail(@Value("${updateUserEmail}")String updateUserEmail)
   {
      SysParameters.updateUserEmail = updateUserEmail;
   }

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
