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
            logger.error("Mandatory configuration properties are not available for +" + Constant.SERVICES.BROKER_SERVICES.toString()+" and "+Constant.BROKER_WEBSERVICES.TD.toString());
            return false;
         }else
         {
            return true;
         }

      }else if(Constant.BROKER_WEBSERVICES.TD.toString().equals(vendor)){
         logger.info("Validating Configuration Properties for "+Constant.SERVICES.BROKER_WEBSERVICES.toString()+" and "+Constant.BROKER_WEBSERVICES.TD.toString());
      }else{
         logger.error("Not supporting "+vendor+" for" + Constant.SERVICES.BROKER_SERVICES.toString());
      }
     return false; 
   }

   public static boolean validateBrokerService(String vendor){

      if(Constant.BROKER_WEBSERVICES.GEMINI.toString().equals(vendor))
      {
         logger.info("Validating Configuration Properties for " + Constant.SERVICES.BROKER_SERVICES.toString());
         if ((ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_HOST == null || ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_HOST.equals("")) ||
            (ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_USERNAME == null || ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_USERNAME.equals("")) ||
            (ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_PASSWORD == null || ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_PASSWORD.equals("")) ||
            (ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_SRC_DIRECTORY == null || ServiceParameters.BROKER_SERVICES_GEMINI_SFTP_SRC_DIRECTORY.equals("")) ||
            (ServiceParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY == null || ServiceParameters.BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY.equals(""))
            )
         {
            logger.error("Mandatory configuration properties are not available for +" + Constant.SERVICES.BROKER_SERVICES.toString());
            return false;
         }
         else
         {
            return true;
         }
      }else{
         logger.error("Not supporting "+vendor+" for" + Constant.SERVICES.BROKER_SERVICES.toString());

      }
      return false;
   }
}
