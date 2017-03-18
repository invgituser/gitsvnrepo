package com.invessence.service.util;

import java.lang.reflect.Field;
import java.util.*;

import com.invessence.service.bean.*;
import com.invessence.service.bean.docuSign.*;
import com.invessence.service.dao.ServiceDao;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 5/13/2016.
 */
//@Configuration
public class ServiceParameters
{
   private static final Logger logger = Logger.getLogger(ServiceParameters.class);
   protected static Properties parameters=null;
   private static String SERVICE_MODE =null;
   public static String COMPANY_NAME;
   final String className="com.invessence.service.util.ServiceParameters"; //this.getClass().getName();

   private String product;
   private List<String> propLoadParam;
   private ServiceDao wsCommonDao;
   public ServiceParameters(List<String> propLoadParam,String product, ServiceDao wsCommonDao){
      System.out.println("ServiceParameters.ServiceParameters");
      this.propLoadParam=propLoadParam;
      this.product=product;
      this.wsCommonDao=wsCommonDao;
      System.out.println("propLoadParam = [" + propLoadParam + "], product = [" + product + "]");
      String propLoadParam1="";
      setServiceConfigDetails(propLoadParam);
      //setServiceOperationDetails(propLoadParam);
//      if(propLoadParam.contains("ALL")){
//         //setWebSiteConfigDetails();
//      }
   }

   static{
      System.out.println("SysParameters.static initializer");
      parameters=new Properties();
      try {
         parameters.load(ServiceParameters.class.getResourceAsStream("/service.properties"));
         SERVICE_MODE =parameters.getProperty("service.mode.value");
         System.out.println("SERVICE_MODE :"+ SERVICE_MODE);
      }catch (Exception e) {
         System.out.println("Message Property file reading time error.");
         e.printStackTrace();
      }
   }

   private static Map<String, Map<String, Map<String, ServiceConfigDetails>>> serviceConfigDetailsMap;
   public static Map<String, Map<String, Map<String,ServiceOperationDetails>>> serviceDetailsMap;
   public static Map<String, Map<String, WebConfigDetails>> webSiteConfigDetailsMap; // Web Site details object
   public static Map<String, Map<String,Object>> additionalDetails=null;
   public static Map<String, Object> genericDetails=null;

   private void loadPrimaryData(){

      if(COMPANY_NAME==null){
         setSwitchDetails();
      }
      if(genericDetails==null){
         loadGenericDetails();
      }
   }

   public void setSwitchDetails() {
      System.out.println("SysParameters.setSwitchDetails");

      Map<String, SwitchDetails> sitchDetails= wsCommonDao.getSwitchDetails();
      Iterator<Map.Entry<String, SwitchDetails>> iterator = sitchDetails.entrySet().iterator() ;
      while(iterator.hasNext()){
         Map.Entry<String, SwitchDetails> switchEntry = iterator.next();
         System.out.println(switchEntry.getKey() +" :: "+ (switchEntry.getValue()).getValue());
         try
         {
            setStaticValue(className, switchEntry.getKey(), (switchEntry.getValue()).getValue());}
         catch(Exception e){
            e.printStackTrace();
         }
      }
   }

//   @Autowired
   public void setWebSiteConfigDetails() {
      System.out.println("ServiceParameters.setWebSiteConfigDetails");
      try{
         loadPrimaryData();
         List<WebConfigDetails> serviceDetailsList=wsCommonDao.getWebServiceDetails(SERVICE_MODE, COMPANY_NAME);
         if(serviceDetailsList==null ||serviceDetailsList.size()==0){
            logger.error("Configuration details are not available fro service.mode=" + SERVICE_MODE+" and company="+ COMPANY_NAME);
         }else{

            Iterator<WebConfigDetails> itr = serviceDetailsList.iterator();
            Map<String, Map<String, WebConfigDetails>> apiDetails = new LinkedHashMap<>();
           // Map<String,Map<String, WebConfigDetails>> apiDetails = null;
            Map<String, WebConfigDetails> mapOfWebDetails = null;
            String companyKey = null;
            WebConfigDetails servDetails = null;
            while (itr.hasNext())
            {
               servDetails = (WebConfigDetails) itr.next();
//            System.out.println("** servDetails = " + servDetails);

               if (companyKey == null)
               {
                  companyKey = servDetails.getVendor();
                  mapOfWebDetails = new LinkedHashMap<String, WebConfigDetails>();

                  mapOfWebDetails.put(servDetails.getName(), servDetails);
               }else if (servDetails.getVendor().equalsIgnoreCase(companyKey))
               {
                  mapOfWebDetails.put(servDetails.getName(), servDetails);
               }
               else if (!servDetails.getVendor().equalsIgnoreCase(companyKey))
               {
                  apiDetails.put(companyKey, mapOfWebDetails);

                  companyKey = servDetails.getVendor();
                  mapOfWebDetails = new LinkedHashMap<String, WebConfigDetails>();
                  mapOfWebDetails.put(servDetails.getName(), servDetails);

               }
               //System.out.println("servDetails = " + servDetails);

            }
            if (apiDetails != null)
            {
               apiDetails.put(companyKey, mapOfWebDetails);
            }

            System.out.println("************************************************************");
            System.out.println("**** Web Site Configuration Details ****");
            System.out.println("************************************************************");
            System.out.println("SERVICE_MODE = " + SERVICE_MODE);
            System.out.println("COMPANY_NAME = " + COMPANY_NAME);
            System.out.println("------------------------------------------------------------");

            Iterator<Map.Entry<String, Map<String, WebConfigDetails>>> entries = apiDetails.entrySet().iterator();
            while (entries.hasNext())
            {
               Map.Entry<String, Map<String, WebConfigDetails>> entry = entries.next();
               System.out.println("Company Id = " + entry.getKey());
               System.out.println("------------------------------------------------------------");
               Iterator<Map.Entry<String, WebConfigDetails>> entries2 = entry.getValue().entrySet().iterator();
               while (entries2.hasNext())
               {
                  Map.Entry<String, WebConfigDetails> entry2 = entries2.next();
                  System.out.println("Name = " + entry2.getValue().getName() + "\t Value = " + entry2.getValue().getValue());
               }
               System.out.println("------------------------------------------------------------");
            }

            System.out.println("***********************************************************");
            webSiteConfigDetailsMap = apiDetails;
//            System.out.println(toString());
//            validateConfigProperties(serviceConfigDetails);
//            setWebServiceAPI(); // setting  broker-webservice vendor
         }
      }catch(Exception e){
         logger.error("Exception while loading service details");
         logger.error(e.getMessage());
         e.printStackTrace();
      }

   }

//   @Autowired
   public void setServiceConfigDetails(List<String> service) {
      System.out.println("SysParameters.setServiceConfigDetails");
      try{
         loadPrimaryData();
         List<ServiceConfigDetails> serviceDetailsList=wsCommonDao.getServiceConfigDetails(SERVICE_MODE, COMPANY_NAME, service);
         if(serviceDetailsList==null ||serviceDetailsList.size()==0){
            logger.error("Configuration details are not available fro service.mode=" + SERVICE_MODE+" and company="+ COMPANY_NAME);
         }else{

            Iterator<ServiceConfigDetails> itr = serviceDetailsList.iterator();
            Map<String, Map<String, Map<String, ServiceConfigDetails>>> serviceConfigDetails = new LinkedHashMap<String, Map<String, Map<String, ServiceConfigDetails>>>();
            Map<String, Map<String, ServiceConfigDetails>> apiDetails = null;
            Map<String, ServiceConfigDetails> mapOfConfigDetails = null;
            String serviceKey = null, apiKey = null;
            ServiceConfigDetails servDetails = null;
            while (itr.hasNext())
            {
               servDetails = (ServiceConfigDetails) itr.next();

//            System.out.println("servDetails = " + servDetails);
//               try
//               {
//                  setStaticValue(className, servDetails.getService().replaceAll("-", "_") + "_" + servDetails.getVendor().replaceAll("-", "_") + "_" + servDetails.getName(), servDetails.getValue());
//               }
//               catch (Exception e)
//               {
//                  logger.error("Issue while setting the value for field " + servDetails.getVendor() + "_" + servDetails.getName());
//                  logger.error(e.getClass());
////               e.printStackTrace();
////               System.out.println("------------------------------------");
//               }
               if (serviceKey == null)
               {
                  serviceKey = servDetails.getService();
                  apiKey = servDetails.getVendor();
                  apiDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();

                  mapOfConfigDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                  mapOfConfigDetails.put(servDetails.getName(), servDetails);

               }
               else if (servDetails.getService().equalsIgnoreCase(serviceKey))
               {
                  if (servDetails.getVendor().equalsIgnoreCase(apiKey))
                  {
                     mapOfConfigDetails.put(servDetails.getName(), servDetails);
                  }
                  else if (!servDetails.getVendor().equalsIgnoreCase(apiKey))
                  {
                     apiDetails.put(apiKey, mapOfConfigDetails);

                     apiKey = servDetails.getVendor();
                     mapOfConfigDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                     mapOfConfigDetails.put(servDetails.getName(), servDetails);
                  }

               }
               else if (!servDetails.getService().equalsIgnoreCase(serviceKey))
               {
//               apiDetails=new LinkedHashMap<>();
                  apiDetails.put(apiKey, mapOfConfigDetails);
                  serviceConfigDetails.put(serviceKey, apiDetails);

                  serviceKey = servDetails.getService();
//               apiDetails=new HashMap<>();
//               apiDetails.put(apiKey,listOfOperation);

                  apiKey = servDetails.getVendor();
                  apiDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();
                  mapOfConfigDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                  mapOfConfigDetails.put(servDetails.getName(), servDetails);

               }
               //System.out.println("servDetails = " + servDetails);

            }
            if (apiDetails != null)
            {
               apiDetails.put(apiKey, mapOfConfigDetails);
               serviceConfigDetails.put(serviceKey, apiDetails);
            }

            System.out.println("************************************************************");
            System.out.println("**** Configuration Details ****");
            System.out.println("************************************************************");
            System.out.println("SERVICE_MODE = " + SERVICE_MODE);
            System.out.println("COMPANY_NAME = " + COMPANY_NAME);
            System.out.println("------------------------------------------------------------");
//
//         while (itr.hasNext()) {
//            servDetails = (ServiceConfigDetails) itr.next();
////            System.out.println("servDetails = " + servDetails);
//            try
//            {
//               getStaticValue(className, servDetails.getVendor() + "_" + servDetails.getName());
//            }catch(Exception e){
//               logger.error("Issue while getting the value of field "+servDetails.getVendor() + "_" + servDetails.getName());
//               logger.error(e.getClass());
////               e.printStackTrace();
////               System.out.println("------------------------------------");
//            }
//         }

            Iterator<Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>>> entries = serviceConfigDetails.entrySet().iterator();
            while (entries.hasNext())
            {
               Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>> entry = entries.next();
               //loadAdditionalDetails(entry.getKey());
               Iterator<Map.Entry<String, Map<String, ServiceConfigDetails>>> entries2 = entry.getValue().entrySet().iterator();
               while (entries2.hasNext())
               {
                  Map.Entry<String, Map<String, ServiceConfigDetails>> entry2 = entries2.next();
                  System.out.println("Service = " + entry.getKey() + "\t Vendor = " + entry2.getKey());
                  System.out.println("------------------------------------------------------------");
                  Iterator<Map.Entry<String, ServiceConfigDetails>> entries3 = entry2.getValue().entrySet().iterator();
                  while (entries3.hasNext())
                  {
                     Map.Entry<String, ServiceConfigDetails> entry3 = entries3.next();
                     System.out.println(entry3.getKey() + " = " + ((ServiceConfigDetails)entry3.getValue()).getValue());
//                     try
//                     {
//                        Object confProp = getStaticValue(className, entry.getKey().replaceAll("-", "_") + "_" + entry2.getKey().replaceAll("-", "_") + "_" + entry3.getKey());
//                        System.out.println(entry3.getKey() + " = " + confProp);
//                     }
//                     catch (Exception e)
//                     {
//                        logger.error("Issue while getting the value of field " + entry2.getKey() + "_" + entry3.getKey() + " : " + e.getClass());
////                     logger.error(e.getClass());
////               e.printStackTrace();
////               System.out.println("------------------------------------");
//                     }
                  }
               }
               System.out.println("------------------------------------------------------------");
            }

            System.out.println("***********************************************************");
            serviceConfigDetailsMap = serviceConfigDetails;
            System.out.println(toString());
            validateConfigProperties(serviceConfigDetails);
            setWebServiceAPI(); // setting  broker-webservice vendor

         }
      }catch(Exception e){
         logger.error("Exception while loading service details");
         logger.error(e.getMessage());
         e.printStackTrace();
      }

   }

   public void loadGenericDetails(){
      System.out.println("ServiceParameters.loadGenericDetails");
         try
         {
            List<LookupDetails> lookupDetails = wsCommonDao.getLookupDetails();
            if(lookupDetails.size()>0)
            {
               if(genericDetails==null){genericDetails=new LinkedHashMap<>();}
               genericDetails.put(Constant.GENERIC_DETAILS.LOOKUP_DETAILS.toString(),lookupDetails );
            }
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            }catch(Exception e){
            logger.error("Exception while loading GenericDetails");
            logger.error(e.getMessage());
            e.printStackTrace();
         }
   }

   public void loadAdditionalDetails(String service){
      System.out.println("ServiceParameters.loadAdditionalDetails");
      System.out.println("service = [" + service + "]");
      Map<String, Object> innerAdditionalDetails=null;
      if(service.equals(Constant.SERVICES.DOCUSIGN_SERVICES.toString())){

         try
         {
            Map<String,DCTemplateDetails> dcTemplateDetails = wsCommonDao.getDCTemplateDetails(SERVICE_MODE, COMPANY_NAME);
            if(dcTemplateDetails==null || dcTemplateDetails.size()<=0){
               logger.debug("DC Template Details are not available");
            }
            else
            {
               if(innerAdditionalDetails==null){innerAdditionalDetails=new LinkedHashMap<>();}

               Iterator<Map.Entry<String, DCTemplateDetails>> dcTemplateDetailsIterator = dcTemplateDetails.entrySet().iterator();
               while (dcTemplateDetailsIterator.hasNext())
               {
                  Map.Entry<String, DCTemplateDetails> dcTemplateDetail = (Map.Entry<String, DCTemplateDetails>) dcTemplateDetailsIterator.next();
                  System.out.println("dcTemplate = " + dcTemplateDetail);
                  Map<String, List<DCTemplateMapping>> dcTemplateMappings = wsCommonDao.getDCTemplateMapping(SERVICE_MODE, COMPANY_NAME, dcTemplateDetail.getValue().getTempCode());

//                  while (dcTemplateMappingIterator.hasNext())
//                  {
//                     DCTemplateMapping dcTemplateMapping = (DCTemplateMapping) dcTemplateMappingIterator.next();
//                  }
                  if(dcTemplateMappings !=null &&dcTemplateMappings.size()>0){
                     HashMap hm=new HashMap<String, Map<String, Map<String, List<DCTemplateMapping>>>>();
                     hm.put(dcTemplateDetail.getValue().getTempCode(),dcTemplateMappings);

                     dcTemplateDetail.getValue().setDcTemplateMappings(dcTemplateMappings);
                  }
//                  System.out.println("dcTemplateDetail = " + dcTemplateDetail);
               }
               innerAdditionalDetails.put(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString(),dcTemplateDetails);
            }

            Map<String,DCDocumentDetails> dcDocumentDetails = wsCommonDao.getDCDocumentDetails(SERVICE_MODE, COMPANY_NAME);
            if(dcDocumentDetails==null || dcDocumentDetails.size()<=0){
               logger.debug("DC Document Details are not available");
            }
            else
            {
               if(innerAdditionalDetails==null){innerAdditionalDetails=new LinkedHashMap<>();}

               Iterator<Map.Entry<String, DCDocumentDetails>> dcDocumentDetailsIterator = dcDocumentDetails.entrySet().iterator();
               while (dcDocumentDetailsIterator.hasNext())
               {
                  Map.Entry<String, DCDocumentDetails> dcDocumentDetail = (Map.Entry<String, DCDocumentDetails>) dcDocumentDetailsIterator.next();
                  System.out.println("dcDocument = " + dcDocumentDetail);
                  Map<String, List<DCDocumentMapping>> dcDocumentMappings = wsCommonDao.getDCDocumentMapping(SERVICE_MODE, COMPANY_NAME, dcDocumentDetail.getValue().getDocCode());

//                  while (dcDocumentMappingIterator.hasNext())
//                  {
//                     DCDocumentMapping dcDocumentMapping = (DCDocumentMapping) dcDocumentMappingIterator.next();
//                  }
                  if(dcDocumentMappings !=null &&dcDocumentMappings.size()>0){
                     HashMap hm=new HashMap<String, Map<String, Map<String, List<DCDocumentMapping>>>>();
                     hm.put(dcDocumentDetail.getValue().getDocCode(),dcDocumentMappings);

                     dcDocumentDetail.getValue().setDcDocumentMappings(dcDocumentMappings);
                  }
//                  System.out.println("dcDocumentDetail = " + dcDocumentDetail);
               }
               innerAdditionalDetails.put(Constant.ADDITIONAL_DETAILS.DOCUMENT_DETAILS.toString(),dcDocumentDetails);
            }
            if(additionalDetails==null){additionalDetails=new LinkedHashMap<>();}
            additionalDetails.put(service,innerAdditionalDetails);

            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            System.out.println(additionalDetails);
         }catch(Exception e){
            logger.error("Exception while loading service details");
            logger.error(e.getMessage());
            e.printStackTrace();
         }
      }
   }

   private void validateConfigProperties(Map<String, Map<String, Map<String, ServiceConfigDetails>>> serviceConfigDetails){
      logger.info("*****************************************************************");
      logger.info("Services configuration properties validations start");
      if(serviceConfigDetails==null){
         logger.error("Configuration properties are empty");
      }else{
         if(serviceConfigDetails.containsKey(Constant.SERVICES.PRICING.toString())){
            logger.info("Validating Configuration Properties for " + Constant.SERVICES.PRICING.toString());
         }
         if(serviceConfigDetails.containsKey(Constant.SERVICES.DOWNLOAD_SERVICES.toString())){
            ServiceValidator.validateBrokerService(Constant.DOWNLOAD_SERVICES.GEMINI.toString());
         }

         if(serviceConfigDetails.containsKey(Constant.SERVICES.BROKER_WEBSERVICES.toString())){
            if(serviceConfigDetails.get(Constant.SERVICES.BROKER_WEBSERVICES.toString()).containsKey(Constant.BROKER_WEBSERVICES.GEMINI.toString())){
               ServiceValidator.validateBrokerWebService(Constant.BROKER_WEBSERVICES.GEMINI.toString());
            }else if(serviceConfigDetails.get(Constant.SERVICES.BROKER_WEBSERVICES.toString()).containsKey(Constant.BROKER_WEBSERVICES.TD.toString())){
               ServiceValidator.validateBrokerService(Constant.BROKER_WEBSERVICES.TD.toString());
            }
         }
      }
      logger.info("*****************************************************************");
//      Iterator<Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>>> entries = serviceConfigDetails.entrySet().iterator();
//      while (entries.hasNext()) {
//         Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>> entry = entries.next();
//         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//      }
   }

   //@Autowired
   public void setServiceOperationDetails(List<String> service) {
      System.out.println("SysParameters.setServiceOperationDetails");
      try{
         loadPrimaryData();
         List<ServiceOperationDetails> serviceDetailsList=wsCommonDao.getServiceOperationDetails(SERVICE_MODE, COMPANY_NAME, service);
         if(serviceDetailsList==null ||serviceDetailsList.size()==0){
            logger.error("Configuration details are not available for company="+ COMPANY_NAME);
         }else
         {
            Iterator<ServiceOperationDetails> itr = serviceDetailsList.iterator();
            Map<String, Map<String, Map<String, ServiceOperationDetails>>> serviceDetails = new LinkedHashMap<String, Map<String, Map<String, ServiceOperationDetails>>>();
            Map<String, Map<String, ServiceOperationDetails>> apiDetails = null;
            Map<String, ServiceOperationDetails> listOfOperation = null;
            String serviceKey = null, apiKey = null;
            ServiceOperationDetails servDetails = null;

            while (itr.hasNext())
            {
               servDetails = (ServiceOperationDetails) itr.next();
//            System.out.println("servDetails = " + servDetails);
               if (serviceKey == null)
               {
                  serviceKey = servDetails.getService();
                  apiKey = servDetails.getVendor();
                  apiDetails = new LinkedHashMap<String, Map<String, ServiceOperationDetails>>();

                  listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
                  listOfOperation.put(servDetails.getOperation(),servDetails);

               }
               else if (servDetails.getService().equalsIgnoreCase(serviceKey))
               {
                  if (servDetails.getVendor().equalsIgnoreCase(apiKey))
                  {
                     listOfOperation.put(servDetails.getOperation(),servDetails);
                  }
                  else if (!servDetails.getVendor().equalsIgnoreCase(apiKey))
                  {

                     apiDetails.put(apiKey, listOfOperation);

                     apiKey = servDetails.getVendor();
                     listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
                     listOfOperation.put(servDetails.getOperation(),servDetails);
                  }

               }
               else if (!servDetails.getService().equalsIgnoreCase(serviceKey))
               {
//               apiDetails=new LinkedHashMap<>();
                  apiDetails.put(apiKey, listOfOperation);
                  serviceDetails.put(serviceKey, apiDetails);

                  serviceKey = servDetails.getService();
//               apiDetails=new HashMap<>();
//               apiDetails.put(apiKey,listOfOperation);

                  apiKey = servDetails.getVendor();
                  apiDetails = new LinkedHashMap<String, Map<String, ServiceOperationDetails>>();
                  listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
                  listOfOperation.put(servDetails.getOperation(),servDetails);
               }
               //System.out.println("servDetails = " + servDetails);

            }
            if (apiDetails != null)
            {
               apiDetails.put(apiKey, listOfOperation);
               serviceDetails.put(serviceKey, apiDetails);
            }

            System.out.println("************************************************************");
            System.out.println("**** Operation Details ****");
            System.out.println("************************************************************");
            System.out.println("------------------------------------------------------------");
            Iterator<Map.Entry<String, Map<String, Map<String,ServiceOperationDetails>>>> entries = serviceDetails.entrySet().iterator();
            while (entries.hasNext())
            {
               Map.Entry<String, Map<String, Map<String,ServiceOperationDetails>>> entry = entries.next();
//            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
               Iterator<Map.Entry<String, Map<String,ServiceOperationDetails>>> entries2 = entry.getValue().entrySet().iterator();
               while (entries2.hasNext())
               {
                  Map.Entry<String, Map<String,ServiceOperationDetails>> entry2 = entries2.next();
                  System.out.println("Service = " + entry.getKey() + "\t Vendor = " + entry2.getKey());
                  System.out.println("------------------------------------------------------------");
                  //Iterator<ServiceOperationDetails> entries3 = entry2.getValue().iterator();
                  Iterator<Map.Entry<String,ServiceOperationDetails>> entries3 = entry2.getValue().entrySet().iterator();

                  while (entries3.hasNext())
                  {
                     Map.Entry<String,ServiceOperationDetails> entry3 = entries3.next();

                     System.out.println("Operation = " + entry3.getKey() + ", Status = " + entry3.getValue().getOperationStatus() + ", Vendor = " + entry3.getValue().getVendor());
                  }
               }
               System.out.println("------------------------------------------------------------");
            }

            System.out.println("*****************************");
            ServiceParameters.serviceDetailsMap = serviceDetails;
            System.out.println("serviceDetails = " + serviceDetails);
            System.out.println("*****************************");

            //setWebServiceAPI(); // setting  broker-webservice vendor
         }
      }catch(Exception e){
         logger.error("Exception while loading service details");
         logger.error(e.getMessage());
         e.printStackTrace();
      }

   }

   public static Object getStaticValue(final String className, final String fieldName) throws SecurityException, NoSuchFieldException, ClassNotFoundException,
      IllegalArgumentException, IllegalAccessException {

      // Get the private field
      final Field field = Class.forName(className).getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Return the Obect corresponding to the field
      return field.get(Class.forName(className));
   }
   public static void setStaticValue(final String className, final String fieldName, final Object newValue) throws SecurityException, NoSuchFieldException,
      ClassNotFoundException, IllegalArgumentException, IllegalAccessException
   {
      // Get the private String field
      System.out.println("className = [" + className + "], fieldName = [" + fieldName + "], newValue = [" + newValue + "]");
      final Field field = Class.forName(className).getDeclaredField(fieldName);
      // Allow modification on the field
      field.setAccessible(true);
      // Get
      final Object oldValue = field.get(Class.forName(className));
      // Sets the field to the new value
      field.set(oldValue, newValue);
   }


   public void setWebServiceAPI()
   {
      System.out.println("ServiceParameters.setWebServiceAPI");
      if(serviceConfigDetailsMap.containsKey(Constant.SERVICES.BROKER_WEBSERVICES.toString())){
         Map<String, Map<String, ServiceConfigDetails>> sd= serviceConfigDetailsMap.get(Constant.SERVICES.BROKER_WEBSERVICES.toString());
         if(sd==null || sd.size()==0){
            System.out.println("Expecting single API vendor for "+ Constant.SERVICES.BROKER_WEBSERVICES.toString()+" service but it gets none");
         }else if(sd.size()>1){
            System.out.println("Expecting single API vendor for "+ Constant.SERVICES.BROKER_WEBSERVICES.toString()+" service but it gets more the one");
            ServiceParameters.BROKER_WEBSERVICE_API =sd.keySet().toArray()[0].toString();
         }else if(sd.size()==1){
            ServiceParameters.BROKER_WEBSERVICE_API =sd.keySet().toArray()[0].toString();
         }
      }
      if(serviceConfigDetailsMap.containsKey(Constant.SERVICES.DOWNLOAD_SERVICES.toString())){
         Map<String, Map<String, ServiceConfigDetails>> sd= serviceConfigDetailsMap.get(Constant.SERVICES.DOWNLOAD_SERVICES.toString());
         if(sd==null || sd.size()==0){
            System.out.println("Expecting single API vendor for "+ Constant.SERVICES.DOWNLOAD_SERVICES.toString()+" service but it gets none");
         }else if(sd.size()>1){
            System.out.println("Expecting single API vendor for "+ Constant.SERVICES.DOWNLOAD_SERVICES.toString()+" service but it gets more the one");
            ServiceParameters.DOWNLOAD_SERVICE_API =sd.keySet().toArray()[0].toString();
         }else if(sd.size()==1){
            ServiceParameters.DOWNLOAD_SERVICE_API =sd.keySet().toArray()[0].toString();
         }
      }
      //ServiceParameters.BROKER_WEBSERVICE_API = BROKER_WEBSERVICE_API;
      System.out.println("DOWNLOAD_SERVICE_API = " + DOWNLOAD_SERVICE_API);
      System.out.println("BROKER_WEBSERVICE_API = " + BROKER_WEBSERVICE_API);

   }

   public static String getConfigProperty(String service, String provider, String property)
   {

      if (serviceConfigDetailsMap.get(service) == null)
      {
         logger.debug("Value not available for Service :" + service);
      }
      else
      {
         if (serviceConfigDetailsMap.get(service).get(provider) == null)
         {
            logger.debug("Value not available for Provider :" + provider);
         }
         else
         {
            if (serviceConfigDetailsMap.get(service).get(provider).get(property) == null)
            {
               logger.debug("Value not available for Property :" + property);
            }
            else
            {
               return serviceConfigDetailsMap.get(service).get(provider).get(property).getValue();
            }
         }
      }
      return null;
   }
   public static String getServiceProvider(String service)
   {

      if (serviceConfigDetailsMap.get(service) == null)
      {
         logger.debug("Value not available for Service :" + service);
      }
      else
      {
         Map.Entry<String, Map<String, ServiceConfigDetails>> keySet=serviceConfigDetailsMap.get(service).entrySet().iterator().next();

         return keySet.getKey();

      }
      return null;
   }


   //BROKER-WEBSERVICES
   public static String BROKER_WEBSERVICES_GEMINI_WS_END_POINT_URL =null;
   public static String BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME =null;
   public static String BROKER_WEBSERVICES_GEMINI_ENCRY_DECRY_KEY =null;

   //BROKER-SERVICES SFTP
   public static String BROKER_SERVICES_GEMINI_SFTP_HOST =null;
   public static String BROKER_SERVICES_GEMINI_SFTP_USERNAME =null;
   public static String BROKER_SERVICES_GEMINI_SFTP_PASSWORD =null;
   public static String BROKER_SERVICES_GEMINI_SFTP_SRC_DIRECTORY =null;
   public static String BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY =null;

   public static String BROKER_WEBSERVICE_API;
   public static String DOWNLOAD_SERVICE_API;
   public static String tdEndPointUrl;

   //public static final String BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY ="aRXDugfr4WQpVrxu";
   //String BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY="GEMINI-KEY";

}
