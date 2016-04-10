package com.invessence.util;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Configuration;

/**
 * Created by abhangp on 4/4/2016.
 */
@Configuration
public class SysParameters
{
   public static String geminiEndPointUrl;
   public static String tdEndPointUrl;

   public static String fundGroupName="landenburgfund";


   public static String getUserAccDetailsByAccNumber;
   public static String getPendingUserAccDetails;
   public static String updatePendingUserAccDetails;
   public static String updateUserEmail;
   public static String getAccountExtInfo;

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
   public void setUpdatePendingUserAccDetails(@Value("${updatePendingUserAccDetails}")String updatePendingUserAccDetails)
   {
      SysParameters.updatePendingUserAccDetails = updatePendingUserAccDetails;
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
