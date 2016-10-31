package com.invessence.service.util;

import org.apache.log4j.Logger;

/**
 * Created by abhangp on 5/20/2016.
 */
public class ServiceValidator
{
   private static final Logger logger = Logger.getLogger(ServiceValidator.class);
   public static boolean validateBrokerWebService(String vendor){
      
      if(Constant.BROKER_WEBSERVICES.GEMINI.toString().equals(vendor)){
         logger.info("Validating Configuration Properties for "+Constant.SERVICES.BROKER_WEBSERVICES.toString()+" and "+Constant.BROKER_WEBSERVICES.GEMINI.toString());
         if((ServiceParameters.BROKER_WEBSERVICES_GEMINI_WS_END_POINT_URL ==null ||ServiceParameters.BROKER_WEBSERVICES_GEMINI_WS_END_POINT_URL .equals("")) ||
            (ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME ==null ||ServiceParameters.BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME .equals("")) ||
            (ServiceParameters.BROKER_WEBSERVICES_GEMINI_ENCRY_DECRY_KEY ==null ||ServiceParameters.BROKER_WEBSERVICES_GEMINI_ENCRY_DECRY_KEY .equals(""))
            ){
            logger.error("Mandatory configuration properties are not available for +" + Constant.SERVICES.DOWNLOAD_SERVICES.toString()+" and "+Constant.BROKER_WEBSERVICES.TD.toString());
            return false;
         }else
         {
            return true;
         }

      }else if(Constant.BROKER_WEBSERVICES.TD.toString().equals(vendor)){
         logger.info("Validating Configuration Properties for "+Constant.SERVICES.BROKER_WEBSERVICES.toString()+" and "+Constant.BROKER_WEBSERVICES.TD.toString());
      }else{
         logger.error("Not supporting "+vendor+" for" + Constant.SERVICES.DOWNLOAD_SERVICES.toString());
      }
     return false; 
   }

   public static boolean validateBrokerService(String vendor){

      String sftpHost=ServiceParameters.getConfigProperty(Constant.SERVICES.DOWNLOAD_SERVICES.toString(), ServiceParameters.BROKER_WEBSERVICE_API, "SFTP_HOST");
      String sftpUserName=ServiceParameters.getConfigProperty(Constant.SERVICES.DOWNLOAD_SERVICES.toString(), ServiceParameters.BROKER_WEBSERVICE_API, "SFTP_USERNAME");
      String sftpPassword=ServiceParameters.getConfigProperty(Constant.SERVICES.DOWNLOAD_SERVICES.toString(), ServiceParameters.BROKER_WEBSERVICE_API, "SFTP_PASSWORD");
      String sftpSourceDirectory=ServiceParameters.getConfigProperty(Constant.SERVICES.DOWNLOAD_SERVICES.toString(), ServiceParameters.BROKER_WEBSERVICE_API, "SFTP_SRC_DIRECTORY");
      String sftpEncrDecrKey=ServiceParameters.getConfigProperty(Constant.SERVICES.DOWNLOAD_SERVICES.toString(), ServiceParameters.BROKER_WEBSERVICE_API, "ENCRY_DECRY_KEY");


      System.out.println("sftpHost = " + sftpHost);
      System.out.println("sftpUserName = " + sftpUserName);
      System.out.println("sftpPassword = " + sftpPassword);
      System.out.println("sftpSourceDirectory = " + sftpSourceDirectory);
      System.out.println("sftpEncrDecrKey = " + sftpEncrDecrKey);

      if(Constant.BROKER_WEBSERVICES.GEMINI.toString().equals(vendor) || Constant.BROKER_WEBSERVICES.TD.toString().equals(vendor))
      {
         logger.info("Validating Configuration Properties for " + Constant.SERVICES.DOWNLOAD_SERVICES.toString());
         if ((sftpHost == null || sftpHost.trim().equals("")) ||
            (sftpUserName == null || sftpUserName.trim().equals("")) ||
            (sftpPassword == null || sftpPassword.trim().equals("")) ||
            (sftpSourceDirectory == null || sftpSourceDirectory.trim().equals("")) ||
            (sftpEncrDecrKey == null || sftpEncrDecrKey.trim().equals("")))
         {
            logger.error("Mandatory configuration properties are not available for +" + Constant.SERVICES.DOWNLOAD_SERVICES.toString());
            return false;
         }
         else
         {
            return true;
         }

      }
      else{
         logger.error("Not supporting "+vendor+" for" + Constant.SERVICES.DOWNLOAD_SERVICES.toString());

      }
      return false;
   }
}
