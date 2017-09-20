package com.invessence.service.util;

import java.util.*;

import com.invessence.service.bean.*;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 1/18/2017.
 */
public class ExceptionHandler
{
   private static final Logger logger = Logger.getLogger(ExceptionHandler.class);
   public ServiceStatus exceptionHandler(String product, String service, String vendor, ServiceStatus apiServiceStatus){
      ServiceStatus serviceStatus=null;
      if(product == null && service==null && vendor==null && apiServiceStatus ==null){

      }else
      {
         if (ServiceDetails.productConfigDetails.containsKey(product))
         {
            Map<String, Map<String, Map<String, Object>>> serviceConfigDetails=ServiceDetails.productConfigDetails.get(product);
            if (serviceConfigDetails.containsKey(service))
            {
               Map<String, Map<String, Object>> vendorDetails=serviceConfigDetails.get(service);
               if (vendorDetails.containsKey(vendor))
               {
                  Map<String, Object> propDetails =vendorDetails.get(vendor);
                  if (propDetails.containsKey(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString()))
                  {
                     List<ExceptionExternal> scd=(List<ExceptionExternal>)propDetails.get(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString());
                     if (scd==null || scd.size()==0)
                     {
                        logger.info("External Exception List is empty.");
                     }
                     else
                     {
                        serviceStatus=getResponseStatus(scd, apiServiceStatus);
                     }
                  }
                  else
                  {
                     logger.info("Data not available in productConfigDetails for :"+Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString());
                  }
               }
               else
               {
                  logger.info("Data not available in productConfigDetails for Vendor :"+vendor);
               }
            }
            else
            {
               logger.info("Data not available in productConfigDetails for Service :"+service);
            }
         }
         else
         {
            logger.info("Data not available in productConfigDetails for Product :"+product);
         }
      }
         return serviceStatus;
   }

   private ServiceStatus getResponseStatus(List<ExceptionExternal> scd, ServiceStatus apiServiceStatus)
   {
      ServiceStatus serviceStatus=null;
      Iterator<ExceptionExternal>  entries5 = scd.iterator();
      boolean isErrCodeFound=false, isErrMsgFound=false;


   for(ExceptionExternal extExcp : scd ) {
         System.out.println(extExcp.getVendorErrCode()+" : "+apiServiceStatus.getErrorCode());
         if(extExcp.getVendorErrCode().equals(""+apiServiceStatus.getErrorCode())){
            System.out.println("Fields are Same!");
            serviceStatus= new ServiceStatus(apiServiceStatus.getErrorCode(), extExcp.getDisplayErrMsg());
            isErrCodeFound=true;
            break;
         }
      }
      if(isErrCodeFound==false){
      for(ExceptionExternal extExcp : scd ) {
            System.out.println(extExcp.getVendorErrCode()+" :: "+apiServiceStatus.getErrorCode());
            if(extExcp.getVendorErrMsg().equals(apiServiceStatus.getErrorMessage())){
               System.out.println("Fields are Same!");
               serviceStatus= new ServiceStatus(apiServiceStatus.getErrorCode(), extExcp.getDisplayErrMsg());
               isErrMsgFound=true;
               break;
            }
         }
      }
      if(isErrCodeFound == false && isErrMsgFound ==false){
         for(ExceptionExternal extExcp : scd ) {
            System.out.println(extExcp.getVendorErrCode()+" ::: CATCHALL");
            if(extExcp.getVendorErrCode().equals("CATCHALL")){
               System.out.println("Fields are Same!");
               serviceStatus= new ServiceStatus(apiServiceStatus.getErrorCode(), extExcp.getDisplayErrMsg());
               break;
            }
         }
      }

      return serviceStatus;

   }

}
