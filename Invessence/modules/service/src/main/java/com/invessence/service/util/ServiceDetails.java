package com.invessence.service.util;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

import com.invessence.service.bean.*;
import com.invessence.service.bean.Generic.Country;
import com.invessence.service.bean.docuSign.*;
import com.invessence.service.bean.documentServices.iText.*;
import com.invessence.service.bean.fileProcessor.*;
import com.invessence.service.dao.ServiceDao;
import org.apache.log4j.Logger;

/**
 * Created by abhangp on 5/13/2016.
 */
//@Configuration
public class ServiceDetails
{
   private static final Logger logger = Logger.getLogger(ServiceDetails.class);
   //final String className="com.invessence.service.util.ServiceParameters"; //this.getClass().getName();

   //@Autowired
   private ServiceDao wsCommonDao;
   private List<String> services;


   private static Map<String, Map<String, Map<String, Map<String,Map<String,ServiceConfigDetails>>>>> serviceConfigDetails;

   private static Map<String, Map<String, Map<String, ServiceConfigDetails>>> serviceConfigDetailsMap;
   public static Map<String, Map<String, Map<String,ServiceOperationDetails>>> serviceDetailsMap;
   public static Map<String, Map<String, WebConfigDetails>> webSiteConfigDetailsMap; // Web Site details object
   public static Map<String, Map<String,Object>> additionalDetails=null;
   public static Map<String, Object> genericDetails=null;

   private String productName;
   public ServiceDetails(ServiceDao wsCommonDao){
      this.wsCommonDao=wsCommonDao;

      initConfigDetails(null);
   }

   public ServiceDetails(ServiceDao wsCommonDao, List<String> service){
      this.wsCommonDao=wsCommonDao;
      this.services=service;

      initConfigDetails(service);
   }
   private void initConfigDetails(List<String> service){
      setGenericDetails();
      setServiceConfigDetails(service);
   }

   public static Map<String, Map<String, Map<String, Map<String, Object>>>> productConfigDetails=null;

   private void setServiceConfigDetails(List<String> service)
   {
      System.out.println("SysParameters.setServiceConfigDetails");
      try
      {
         List<ServiceConfigDetails> serviceDetailsList = wsCommonDao.getServiceConfigDetails(service);
         if (serviceDetailsList == null || serviceDetailsList.size() == 0)
         {
            logger.error("Configuration details are not available");
         }
         else
         {
            Iterator<ServiceConfigDetails> itr = serviceDetailsList.iterator();
            Map<String, Map<String, Map<String, Map<String, Object>>>> productDetails = new LinkedHashMap<>();
            Map<String, Map<String, Map<String, Object>>> serviceConfigDetails = null;
            Map<String, Map<String, Object>> vendorDetails = null;
            Map<String, Object> propDetails = null;
            Map<String, Map<String, ServiceConfigDetails>> modeDetails = null;
            Map<String, ServiceConfigDetails> configDetails = null;

            String companyKey = null, serviceKey = null, vendorKey = null, modeKey = null;
            ServiceConfigDetails servDetails = null;
            while (itr.hasNext())
            {
               servDetails = (ServiceConfigDetails) itr.next();
//               System.out.println("servDetails = " + servDetails);
               if (companyKey == null)
               {
                  companyKey = servDetails.getCompany();
                  serviceKey = servDetails.getService();
                  vendorKey = servDetails.getVendor();
                  modeKey = servDetails.getMode();
//                  System.out.println(companyKey+" : "+serviceKey+" : "+vendorKey+" : "+modeKey);

                  serviceConfigDetails = new LinkedHashMap<String, Map<String, Map<String, Object>>>();
                  vendorDetails = new LinkedHashMap<String, Map<String, Object>>();
                  propDetails = new LinkedHashMap<String, Object>();
                  modeDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();
                  configDetails = new LinkedHashMap<String, ServiceConfigDetails>();

                  configDetails.put(servDetails.getName(), servDetails);
               }
               else if (servDetails.getCompany().equalsIgnoreCase(companyKey))//Company Equal
               {
                  if (servDetails.getService().equalsIgnoreCase(serviceKey))//Service Equal
                  {
                     if (servDetails.getVendor().equalsIgnoreCase(vendorKey))//Vendor Equal
                     {
                        if (servDetails.getMode().equalsIgnoreCase(modeKey)) //Mode Equal
                        {
                           configDetails.put(servDetails.getName(), servDetails);
                        }
                        else if (!servDetails.getMode().equalsIgnoreCase(modeKey))//Mode Not Equal
                        {
                           modeDetails.put(modeKey, configDetails);

                           modeKey = servDetails.getMode();

                           configDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                           configDetails.put(servDetails.getName(), servDetails);
                        }
                     }
                     else if (!servDetails.getVendor().equalsIgnoreCase(vendorKey))//Vendor Not Equal
                     {
                        modeDetails.put(modeKey, configDetails);
                        propDetails.put(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString(), modeDetails);
                        propDetails.put(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString(), wsCommonDao.getExceptionExternalDetails(serviceKey, vendorKey));
                        propDetails.put(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()));
                        propDetails.put(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()));
                        propDetails.put(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()));

                        vendorDetails.put(vendorKey, propDetails);

                        modeKey = servDetails.getMode();
                        vendorKey = servDetails.getVendor();

                        configDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                        configDetails.put(servDetails.getName(), servDetails);
                        modeDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();
                        propDetails = new LinkedHashMap<String, Object>();
                     }
                  }
                  else if (!servDetails.getService().equalsIgnoreCase(serviceKey))//Service Not Equal
                  {
                     modeDetails.put(modeKey, configDetails);
                     propDetails.put(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString(), modeDetails);
                     propDetails.put(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString(), wsCommonDao.getExceptionExternalDetails(serviceKey, vendorKey));
                     propDetails.put(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()));
                     propDetails.put(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()));
                     propDetails.put(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()));

                     vendorDetails.put(vendorKey, propDetails);
                     serviceConfigDetails.put(serviceKey, vendorDetails);

                     modeKey = servDetails.getMode();
                     vendorKey = servDetails.getVendor();
                     serviceKey = servDetails.getService();

                     configDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                     configDetails.put(servDetails.getName(), servDetails);
                     modeDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();
                     propDetails = new LinkedHashMap<String, Object>();
                     vendorDetails = new LinkedHashMap<String, Map<String, Object>>();
                  }

               }
               else if (!servDetails.getCompany().equalsIgnoreCase(companyKey))//Company Not Equal
               {
                  modeDetails.put(modeKey, configDetails);
                  propDetails.put(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString(), modeDetails);
                  propDetails.put(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString(), wsCommonDao.getExceptionExternalDetails(serviceKey, vendorKey));
                  propDetails.put(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()));
                  propDetails.put(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()));
                  propDetails.put(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()));

                  vendorDetails.put(vendorKey, propDetails);
                  serviceConfigDetails.put(serviceKey, vendorDetails);
                  productDetails.put(companyKey, serviceConfigDetails);

                  modeKey = servDetails.getMode();
                  vendorKey = servDetails.getVendor();
                  serviceKey = servDetails.getService();
                  companyKey = servDetails.getCompany();

                  configDetails = new LinkedHashMap<String, ServiceConfigDetails>();
                  configDetails.put(servDetails.getName(), servDetails);
                  modeDetails = new LinkedHashMap<String, Map<String, ServiceConfigDetails>>();
                  propDetails = new LinkedHashMap<String, Object>();
                  vendorDetails = new LinkedHashMap<String, Map<String, Object>>();
                  serviceConfigDetails = new LinkedHashMap<String, Map<String, Map<String, Object>>>();
               }
            }
            if (modeDetails != null)
            {
               modeDetails.put(modeKey, configDetails);
               propDetails.put(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString(), modeDetails);
               propDetails.put(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString(), wsCommonDao.getExceptionExternalDetails(serviceKey, vendorKey));
               propDetails.put(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()));
               propDetails.put(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()));
               propDetails.put(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString(), loadServiceDetails(companyKey, serviceKey, Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()));

               vendorDetails.put(vendorKey, propDetails);
               serviceConfigDetails.put(serviceKey, vendorDetails);
               productDetails.put(companyKey, serviceConfigDetails);

               configDetails = null;
               modeDetails = null;
               vendorDetails = null;
               serviceConfigDetails = null;
            }

            System.out.println("************************************************************");
            System.out.println("**** Service Configuration Details ****");
            System.out.println("************************************************************");
            Iterator<Map.Entry<String, Map<String, Map<String, Map<String, Object>>>>> entries1 = productDetails.entrySet().iterator();
            while (entries1.hasNext())
            {
               Map.Entry<String, Map<String, Map<String, Map<String, Object>>>> entry1 = entries1.next();
               Iterator<Map.Entry<String, Map<String, Map<String, Object>>>> entries2 = entry1.getValue().entrySet().iterator();
               while (entries2.hasNext())
               {
                  Map.Entry<String, Map<String, Map<String, Object>>> entry2 = entries2.next();

                  Iterator<Map.Entry<String, Map<String, Object>>> entries3 = entry2.getValue().entrySet().iterator();
                  while (entries3.hasNext())
                  {
                     Map.Entry<String, Map<String, Object>> entry3 = entries3.next();

                     Iterator<Map.Entry<String, Object>> entries4 = entry3.getValue().entrySet().iterator();
                     while (entries4.hasNext())
                     {
                        Map.Entry<String, Object> entry4 = entries4.next();
                        if (entry4.getKey().equalsIgnoreCase(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString()))
                        {
                           Map<String, Map<String, ServiceConfigDetails>> scd = ((Map<String, Map<String, ServiceConfigDetails>>) entry4.getValue());

//                           System.out.println("-------------------------------------------------------------------------------------------------");
//                           System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t Mode = " + entry4.getKey());
//                           System.out.println("-------------------------------------------------------------------------------------------------");
                           Iterator<Map.Entry<String, Map<String, ServiceConfigDetails>>> entries5 = scd.entrySet().iterator();
                           while (entries5.hasNext())
                           {
                              Map.Entry<String, Map<String, ServiceConfigDetails>> entry5 = entries5.next();
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t Mode = " + entry5.getKey());
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              Iterator<Map.Entry<String, ServiceConfigDetails>> entries6 = entry5.getValue().entrySet().iterator();
                              while (entries6.hasNext())
                              {
                                 Map.Entry<String, ServiceConfigDetails> entry6 = entries6.next();
                                 System.out.println(entry6.getKey() + " = " + ((ServiceConfigDetails) entry6.getValue()).getValue());

                              }
                              if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.PRICING.toString()))
                              {
                                 System.out.println("**********************************");
                              }
                           }
                        }
                        else if (entry4.getKey().equalsIgnoreCase(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()))
                        {
                           if (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0)
                           {
                              Map<String,ServiceOperationDetails> scd = (Map<String, ServiceOperationDetails>) entry4.getValue();
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t " + entry4.getKey());
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              Iterator<Map.Entry<String,ServiceOperationDetails>> entries5 = scd.entrySet().iterator();
                              while (entries5.hasNext())
                              {
                                 Map.Entry<String,ServiceOperationDetails> entry5 = entries5.next();
                                 System.out.println(entry5.getKey() + " = " + (ServiceOperationDetails) entry5.getValue());
                              }
                              System.out.println("**---------------------------------------------------------------------------------------------**");
                           }
                        }
                        else if (entry4.getKey().equalsIgnoreCase(Constant.SERVICES_DETAILS.EXCEPTION_DETAILS.toString()))
                        {
                           if (entry4 != null && entry4.getValue() != null && ((List) entry4.getValue()).size() > 0)
                           {
                              List<ExceptionExternal> scd = (List<ExceptionExternal>) entry4.getValue();
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t " + entry4.getKey());
                              System.out.println("-------------------------------------------------------------------------------------------------");
                              Iterator<ExceptionExternal> entries5 = scd.iterator();
                              while (entries5.hasNext())
                              {
                                 ExceptionExternal entry5 = entries5.next();
                                 System.out.println(entry5.getVendor() + " : " + entry5.getVendorErrCode() + "  :  " + entry5.getVendorErrMsg() + "  :  " + entry5.getDisplayErrMsg());
//                                 Iterator<Map.Entry<String, ExceptionExternal>> entries6 = entry5.getValue().entrySet().iterator();
//                                 while (entries6.hasNext())
//                                 {
//                                    Map.Entry<String, ExceptionExternal> entry6 = entries6.next();
//                                    System.out.println(entry6.getKey() + " = " + (ExceptionExternal) entry6.getValue());
//
//                                 }
                              }
                           }
                        }else if (entry4.getKey().equalsIgnoreCase(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()))
                        {
                           if (entry4 != null && entry4.getValue() != null)
                           {
                              if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.DOCUMENT_SERVICES.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                              {
                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();

                                 LinkedHashMap<String, LinkedList<PDFFileDetails>> scd = (LinkedHashMap<String, LinkedList<PDFFileDetails>>) mapObject.get(Constant.ADDITIONAL_DETAILS.PDF_FILE_DETAILS.toString());
                                 if(scd!=null && scd.size()>0)
                                 {
                                    Iterator<Map.Entry<String, LinkedList<PDFFileDetails>>> entries5 = scd.entrySet().iterator();
                                    while (entries5.hasNext())
                                    {
                                       Map.Entry<String, LinkedList<PDFFileDetails>>entry5=entries5.next();
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t" + entry4.getKey() + "\t" + Constant.ADDITIONAL_DETAILS.PDF_FILE_DETAILS.toString()+"\t ProcessId = " + entry5.getKey());
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                                       Iterator<PDFFileDetails> entries6 = entry5.getValue().iterator();
                                       while (entries6.hasNext())
                                       {
                                          PDFFileDetails entry6 = entries6.next();
                                          System.out.println(entry6);
                                       }
                                    }
                                 }
                              }
                              else if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.FILE_PROCESS.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                              {
                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();

                                 LinkedHashMap<String, LinkedList<FileDetails>> scd = (LinkedHashMap<String, LinkedList<FileDetails>>) mapObject.get(Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
                                 if(scd!=null && scd.size()>0)
                                 {
                                    Iterator<Map.Entry<String, LinkedList<FileDetails>>> entries5 = scd.entrySet().iterator();
                                    while (entries5.hasNext())
                                    {
                                       Map.Entry<String, LinkedList<FileDetails>>entry5=entries5.next();
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t" + entry4.getKey() + "\t" + Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString()+"\t ProcessId = " + entry5.getKey());
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                                       Iterator<FileDetails> entries6 = entry5.getValue().iterator();
                                       while (entries6.hasNext())
                                       {
                                          FileDetails entry6 = entries6.next();
                                          System.out.println(entry6);
                                       }
                                    }
                                 }
                              }
                              else if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.DOCUSIGN_SERVICES.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                              {
                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();
                                 Map<String, Map<String,DCTemplateDetails>> scd = (Map<String, Map<String,DCTemplateDetails>>) mapObject.get(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString());
                                 if(scd!=null && scd.size()>0)
                                 {
                                    Iterator<Map.Entry<String, Map<String,DCTemplateDetails>>> entries5 = scd.entrySet().iterator();
                                    while (entries5.hasNext())
                                    {
                                       Map.Entry<String, Map<String,DCTemplateDetails>> entry5 = entries5.next();
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");
                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t " + entry4.getKey() +"\t"+ Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString() +"\t Mode = " + entry5.getKey());
                                       System.out.println("-----------------------------------------------------------------------------------------------------------------------");

                                       Iterator<Map.Entry<String,DCTemplateDetails>> entries6 = entry5.getValue().entrySet().iterator();

                                       while (entries6.hasNext())
                                       {
                                          Map.Entry<String,DCTemplateDetails> entry6 = entries6.next();
                                          System.out.println(entry6.getKey() + " = " + (DCTemplateDetails) entry6.getValue());
                                       }
                                    }
                                 }
                              }
                           }
                        }
                        else if (entry4.getKey().equalsIgnoreCase(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()))
                        {
                           if (entry4 != null && entry4.getValue() != null)
                           {
//                              if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.DOCUMENT_SERVICES.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
//                              {
//                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();
//                                 Map<String, Map<String,PDFFileRules>> scd = (Map<String, Map<String,PDFFileRules>>) mapObject.get(Constant.COMMON_DETAILS.PDF_FILE_RULES.toString());
//                                 if(scd!=null && scd.size()>0)
//                                 {
//                                    Iterator<Map.Entry<String, Map<String,PDFFileRules>>> entries5 = scd.entrySet().iterator();
//                                    while (entries5.hasNext())
//                                    {
//                                       Map.Entry<String, Map<String,PDFFileRules>> entry5 = entries5.next();
//                                       System.out.println("-------------------------------------------------------------------------------------------------");
//                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t " + entry4.getKey() +"\t"+ Constant.COMMON_DETAILS.PDF_FILE_RULES.toString() +"\t Mode = " + entry5.getKey());
//                                       System.out.println("-------------------------------------------------------------------------------------------------");
//
//                                       Iterator<Map.Entry<String,PDFFileRules>> entries6 = entry5.getValue().entrySet().iterator();
//
//                                       while (entries6.hasNext())
//                                       {
//                                          Map.Entry<String,PDFFileRules> entry6 = entries6.next();
//                                          System.out.println(entry6.getKey() + " = " + (PDFFileRules) entry6.getValue());
//                                       }
//                                    }
//                                 }
//                              }
                               if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.DOCUMENT_SERVICES.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                           {
                              Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();
                              Map<String,Map<String, List<PDFFileRules>>> scd = (Map<String,Map<String, List<PDFFileRules>>>) mapObject.get(Constant.COMMON_DETAILS.PDF_FILE_RULES.toString());
                              if(scd!=null && scd.size()>0)
                              {
                                 Iterator<Map.Entry<String, Map<String,List<PDFFileRules>>>> entries5 = scd.entrySet().iterator();
                                 while (entries5.hasNext())
                                 {
                                    Map.Entry<String, Map<String,List<PDFFileRules>>> entry5 = entries5.next();
                                    Iterator<Map.Entry<String,List<PDFFileRules>>> entries6 = entry5.getValue().entrySet().iterator();
                                    while (entries6.hasNext())
                                    {

                                       Map.Entry<String, List<PDFFileRules>>entry6=entries6.next();
                                       System.out.println("------------------------------------------------------------------------------------------------------------------------------------");
                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t" + entry4.getKey() + "\t" + Constant.COMMON_DETAILS.PDF_FILE_RULES.toString()+ "\t" + entry5.getKey()+ "\t" + entry6.getKey());
                                       System.out.println("------------------------------------------------------------------------------------------------------------------------------------");

                                       Iterator<PDFFileRules> entries7 = entry6.getValue().iterator();
                                       while (entries7.hasNext())
                                       {
                                          PDFFileRules entry7 = entries7.next();
                                          System.out.println(entry7);
                                       }
                                    }
                                 }
                              }
                           }
                              else if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.FILE_PROCESS.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                              {
                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();
                                 Map<String, Map<String,FileRules>> scd = (Map<String, Map<String,FileRules>>) mapObject.get(Constant.COMMON_DETAILS.FILE_RULES.toString());
                                 if(scd!=null && scd.size()>0)
                                 {
                                    Iterator<Map.Entry<String, Map<String,FileRules>>> entries5 = scd.entrySet().iterator();
                                    while (entries5.hasNext())
                                    {
                                       Map.Entry<String, Map<String,FileRules>> entry5 = entries5.next();
                                       System.out.println("-------------------------------------------------------------------------------------------------");
                                       System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t " + entry4.getKey() +"\t"+ Constant.COMMON_DETAILS.FILE_RULES.toString() +"\t Mode = " + entry5.getKey());
                                       System.out.println("-------------------------------------------------------------------------------------------------");

                                       Iterator<Map.Entry<String,FileRules>> entries6 = entry5.getValue().entrySet().iterator();

                                       while (entries6.hasNext())
                                       {
                                          Map.Entry<String,FileRules> entry6 = entries6.next();
                                          System.out.println(entry6.getKey() + " = " + (FileRules) entry6.getValue());
                                       }
                                    }
                                 }
                              }
                              else if (entry2.getKey().equalsIgnoreCase(Constant.SERVICES.DOCUSIGN_SERVICES.toString()) && (entry4 != null && entry4.getValue() != null && ((Map) entry4.getValue()).size() > 0))
                              {
                                 Map<String, Object> mapObject= (Map<String, Object>)entry4.getValue();
                                 Map<String,Map<String, List<DCTemplateMapping>>> scd = (Map<String,Map<String, List<DCTemplateMapping>>>) mapObject.get(Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString());
                                 if(scd!=null && scd.size()>0)
                                 {
                                    Iterator<Map.Entry<String, Map<String,List<DCTemplateMapping>>>> entries5 = scd.entrySet().iterator();
                                    while (entries5.hasNext())
                                    {
                                       Map.Entry<String, Map<String,List<DCTemplateMapping>>> entry5 = entries5.next();
                                       Iterator<Map.Entry<String,List<DCTemplateMapping>>> entries6 = entry5.getValue().entrySet().iterator();
                                       while (entries6.hasNext())
                                       {

                                          Map.Entry<String, List<DCTemplateMapping>>entry6=entries6.next();
                                          System.out.println("-------------------------------------------------------------------------------------------------");
                                          System.out.println("Product = " + entry1.getKey() + "\t Service = " + entry2.getKey() + "\t API = " + entry3.getKey() + "\t" + entry4.getKey() + "\t" + Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString()+ "\t" + entry5.getKey()+ "\t" + entry6.getKey());
                                          System.out.println("-------------------------------------------------------------------------------------------------");

                                          Iterator<DCTemplateMapping> entries7 = entry6.getValue().iterator();
                                          while (entries7.hasNext())
                                          {
                                             DCTemplateMapping entry7 = entries7.next();
                                             System.out.println(entry7);
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
            productConfigDetails = productDetails;
            System.out.println("::"+getConfigProperty("BUILDINGBENJAMINS","PRICING","UAT","DESTINATION.DIRECTORY"));
            System.out.println("::"+getConfigProperty("UOB","DOCUSIGN-SERVICES","UAT","ACCOUNT_ID"));
            System.out.println("::"+getServiceProvider("BUILDINGBENJAMINS",Constant.SERVICES.BROKER_WEBSERVICES.toString()));
         }
      }catch(Exception e){
         logger.error("Exception while loading service details");
         logger.error(e.getMessage());
         e.printStackTrace();
      }

   }

   private void setGenericDetails(){
      System.out.println("ServiceDetails.setGenericDetails");
      try
      {
         if(genericDetails==null){genericDetails=new LinkedHashMap<>();}
         Map<String,Country> countryDetails = wsCommonDao.getCountryDetails();
         if(countryDetails.size()>0)
         {
            genericDetails.put(Constant.GENERIC_DETAILS.COUNTRY.toString(),countryDetails );
         }
//         List<LookupDetails> lookupDetails = wsCommonDao.getLookupDetails();
//         if(lookupDetails.size()>0)
//         {
//            genericDetails.put(Constant.GENERIC_DETAILS.LOOKUP_DETAILS.toString(),lookupDetails );
//         }
//         List<ExceptionInternal> exceptionInternalDetails = wsCommonDao.getExceptionInternalDetails();
//         if(exceptionInternalDetails.size()>0)
//         {
//            genericDetails.put(Constant.GENERIC_DETAILS.COMMON_EXCEPTION_DETAILS.toString(),exceptionInternalDetails );
//         }
         System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
      }catch(Exception e){
         logger.error("Exception while loading GenericDetails");
         logger.error(e.getMessage());
         e.printStackTrace();
      }
   }

   private void loadGenericDetails(){
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

   private Object loadServiceDetails(String product, String service, String type){
      System.out.println("ServiceDetails.loadServiceDetails");
      Object returnObject=null;
      LinkedHashMap<String, Object> returnMap=null;
      try
      {
         if(Constant.SERVICES.DOCUMENT_SERVICES.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.ADDITIONAL_DETAILS.PDF_FILE_DETAILS.toString());
            returnMap.put(Constant.ADDITIONAL_DETAILS.PDF_FILE_DETAILS.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES.DOCUMENT_SERVICES.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.COMMON_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.COMMON_DETAILS.PDF_FILE_RULES.toString());
            returnMap.put(Constant.COMMON_DETAILS.PDF_FILE_RULES.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES.FILE_PROCESS.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());
            returnMap.put(Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES.FILE_PROCESS.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.COMMON_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.COMMON_DETAILS.FILE_RULES.toString());
            returnMap.put(Constant.COMMON_DETAILS.FILE_RULES.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES.DOCUSIGN_SERVICES.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.COMMON_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString());
            returnMap.put(Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES.DOCUSIGN_SERVICES.toString().equalsIgnoreCase(service)&& Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString().equalsIgnoreCase(type)){
            returnMap=new LinkedHashMap<>();
            returnObject=wsCommonDao.getCommonDetails(product,service,type, Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString());
            returnMap.put(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString(),returnObject);
            return returnMap;
         }else if(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString().equalsIgnoreCase(type)){
            return wsCommonDao.getCommonDetails(product,service,type, Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString());
         }
      }
      catch (SQLException e)
      {
         e.printStackTrace();
      }
      return returnMap;
   }
   private void loadOtherDetails(){

   }

//   public void loadAdditionalDetails(String service){
//      System.out.println("ServiceParameters.loadAdditionalDetails");
//      System.out.println("service = [" + service + "]");
//      Map<String, Object> innerAdditionalDetails=null;
//      if(service.equals(Constant.SERVICES.DOCUSIGN_SERVICES.toString())){
//
//         try
//         {
//            Map<String,DCTemplateDetails> dcTemplateDetails = wsCommonDao.getDCTemplateDetails(SERVICE_MODE, COMPANY_NAME);
//            if(dcTemplateDetails==null || dcTemplateDetails.size()<=0){
//               logger.debug("DC Template Details are not available");
//            }
//            else
//            {
//               if(innerAdditionalDetails==null){innerAdditionalDetails=new LinkedHashMap<>();}
//
//               Iterator<Map.Entry<String, DCTemplateDetails>> dcTemplateDetailsIterator = dcTemplateDetails.entrySet().iterator();
//               while (dcTemplateDetailsIterator.hasNext())
//               {
//                  Map.Entry<String, DCTemplateDetails> dcTemplateDetail = (Map.Entry<String, DCTemplateDetails>) dcTemplateDetailsIterator.next();
//                  System.out.println("dcTemplate = " + dcTemplateDetail);
//                  Map<String, List<DCTemplateMapping>> dcTemplateMappings = wsCommonDao.getDCTemplateMapping(SERVICE_MODE, COMPANY_NAME, dcTemplateDetail.getValue().getTempCode());
//
////                  while (dcTemplateMappingIterator.hasNext())
////                  {
////                     DCTemplateMapping dcTemplateMapping = (DCTemplateMapping) dcTemplateMappingIterator.next();
////                  }
//                  if(dcTemplateMappings !=null &&dcTemplateMappings.size()>0){
//                     HashMap hm=new HashMap<String, Map<String, Map<String, List<DCTemplateMapping>>>>();
//                     hm.put(dcTemplateDetail.getValue().getTempCode(),dcTemplateMappings);
//
//                     dcTemplateDetail.getValue().setDcTemplateMappings(dcTemplateMappings);
//                  }
////                  System.out.println("dcTemplateDetail = " + dcTemplateDetail);
//               }
//               innerAdditionalDetails.put(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString(),dcTemplateDetails);
//            }
//
//            Map<String,DCDocumentDetails> dcDocumentDetails = wsCommonDao.getDCDocumentDetails(SERVICE_MODE, COMPANY_NAME);
//            if(dcDocumentDetails==null || dcDocumentDetails.size()<=0){
//               logger.debug("DC Document Details are not available");
//            }
//            else
//            {
//               if(innerAdditionalDetails==null){innerAdditionalDetails=new LinkedHashMap<>();}
//
//               Iterator<Map.Entry<String, DCDocumentDetails>> dcDocumentDetailsIterator = dcDocumentDetails.entrySet().iterator();
//               while (dcDocumentDetailsIterator.hasNext())
//               {
//                  Map.Entry<String, DCDocumentDetails> dcDocumentDetail = (Map.Entry<String, DCDocumentDetails>) dcDocumentDetailsIterator.next();
//                  System.out.println("dcDocument = " + dcDocumentDetail);
//                  Map<String, List<DCDocumentMapping>> dcDocumentMappings = wsCommonDao.getDCDocumentMapping(SERVICE_MODE, COMPANY_NAME, dcDocumentDetail.getValue().getDocCode());
//
////                  while (dcDocumentMappingIterator.hasNext())
////                  {
////                     DCDocumentMapping dcDocumentMapping = (DCDocumentMapping) dcDocumentMappingIterator.next();
////                  }
//                  if(dcDocumentMappings !=null &&dcDocumentMappings.size()>0){
//                     HashMap hm=new HashMap<String, Map<String, Map<String, List<DCDocumentMapping>>>>();
//                     hm.put(dcDocumentDetail.getValue().getDocCode(),dcDocumentMappings);
//
//                     dcDocumentDetail.getValue().setDcDocumentMappings(dcDocumentMappings);
//                  }
////                  System.out.println("dcDocumentDetail = " + dcDocumentDetail);
//               }
//               innerAdditionalDetails.put(Constant.ADDITIONAL_DETAILS.DOCUMENT_DETAILS.toString(),dcDocumentDetails);
//            }
//            if(additionalDetails==null){additionalDetails=new LinkedHashMap<>();}
//            additionalDetails.put(service,innerAdditionalDetails);
//
//            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//            System.out.println(additionalDetails);
//         }catch(Exception e){
//            logger.error("Exception while loading service details");
//            logger.error(e.getMessage());
//            e.printStackTrace();
//         }
//      }
//   }

//   private void validateConfigProperties(Map<String, Map<String, Map<String, ServiceConfigDetails>>> serviceConfigDetails){
//      logger.info("*****************************************************************");
//      logger.info("Services configuration properties validations start");
//      if(serviceConfigDetails==null){
//         logger.error("Configuration properties are empty");
//      }else{
//         if(serviceConfigDetails.containsKey(Constant.SERVICES.PRICING.toString())){
//            logger.info("Validating Configuration Properties for " + Constant.SERVICES.PRICING.toString());
//         }
//         if(serviceConfigDetails.containsKey(Constant.SERVICES.DOWNLOAD_SERVICES.toString())){
//            ServiceValidator.validateBrokerService(Constant.DOWNLOAD_SERVICES.GEMINI.toString());
//         }
//
//         if(serviceConfigDetails.containsKey(Constant.SERVICES.BROKER_WEBSERVICES.toString())){
//            if(serviceConfigDetails.get(Constant.SERVICES.BROKER_WEBSERVICES.toString()).containsKey(Constant.BROKER_WEBSERVICES.GEMINI.toString())){
//               ServiceValidator.validateBrokerWebService(Constant.BROKER_WEBSERVICES.GEMINI.toString());
//            }else if(serviceConfigDetails.get(Constant.SERVICES.BROKER_WEBSERVICES.toString()).containsKey(Constant.BROKER_WEBSERVICES.TD.toString())){
//               ServiceValidator.validateBrokerService(Constant.BROKER_WEBSERVICES.TD.toString());
//            }
//         }
//      }
//      logger.info("*****************************************************************");
////      Iterator<Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>>> entries = serviceConfigDetails.entrySet().iterator();
////      while (entries.hasNext()) {
////         Map.Entry<String, Map<String, Map<String, ServiceConfigDetails>>> entry = entries.next();
////         System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
////      }
//   }

   //@Autowired
//   public void setServiceOperationDetails(List<String> service) {
//      System.out.println("SysParameters.setServiceOperationDetails");
//      try{
//         loadPrimaryData();
//         List<ServiceOperationDetails> serviceDetailsList=wsCommonDao.getServiceOperationDetails(SERVICE_MODE, COMPANY_NAME, service);
//         if(serviceDetailsList==null ||serviceDetailsList.size()==0){
//            logger.error("Configuration details are not available for company="+ COMPANY_NAME);
//         }else
//         {
//            Iterator<ServiceOperationDetails> itr = serviceDetailsList.iterator();
//            Map<String, Map<String, Map<String, ServiceOperationDetails>>> serviceDetails = new LinkedHashMap<String, Map<String, Map<String, ServiceOperationDetails>>>();
//            Map<String, Map<String, ServiceOperationDetails>> apiDetails = null;
//            Map<String, ServiceOperationDetails> listOfOperation = null;
//            String serviceKey = null, apiKey = null;
//            ServiceOperationDetails servDetails = null;
//
//            while (itr.hasNext())
//            {
//               servDetails = (ServiceOperationDetails) itr.next();
////            System.out.println("servDetails = " + servDetails);
//               if (serviceKey == null)
//               {
//                  serviceKey = servDetails.getService();
//                  apiKey = servDetails.getVendor();
//                  apiDetails = new LinkedHashMap<String, Map<String, ServiceOperationDetails>>();
//
//                  listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
//                  listOfOperation.put(servDetails.getOperation(),servDetails);
//
//               }
//               else if (servDetails.getService().equalsIgnoreCase(serviceKey))
//               {
//                  if (servDetails.getVendor().equalsIgnoreCase(apiKey))
//                  {
//                     listOfOperation.put(servDetails.getOperation(),servDetails);
//                  }
//                  else if (!servDetails.getVendor().equalsIgnoreCase(apiKey))
//                  {
//
//                     apiDetails.put(apiKey, listOfOperation);
//
//                     apiKey = servDetails.getVendor();
//                     listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
//                     listOfOperation.put(servDetails.getOperation(),servDetails);
//                  }
//
//               }
//               else if (!servDetails.getService().equalsIgnoreCase(serviceKey))
//               {
////               apiDetails=new LinkedHashMap<>();
//                  apiDetails.put(apiKey, listOfOperation);
//                  serviceDetails.put(serviceKey, apiDetails);
//
//                  serviceKey = servDetails.getService();
////               apiDetails=new HashMap<>();
////               apiDetails.put(apiKey,listOfOperation);
//
//                  apiKey = servDetails.getVendor();
//                  apiDetails = new LinkedHashMap<String, Map<String, ServiceOperationDetails>>();
//                  listOfOperation = new LinkedHashMap<String, ServiceOperationDetails>();
//                  listOfOperation.put(servDetails.getOperation(),servDetails);
//               }
//               //System.out.println("servDetails = " + servDetails);
//
//            }
//            if (apiDetails != null)
//            {
//               apiDetails.put(apiKey, listOfOperation);
//               serviceDetails.put(serviceKey, apiDetails);
//            }
//
//            System.out.println("************************************************************");
//            System.out.println("**** Operation Details ****");
//            System.out.println("************************************************************");
//            System.out.println("------------------------------------------------------------");
//            Iterator<Map.Entry<String, Map<String, Map<String,ServiceOperationDetails>>>> entries = serviceDetails.entrySet().iterator();
//            while (entries.hasNext())
//            {
//               Map.Entry<String, Map<String, Map<String,ServiceOperationDetails>>> entry = entries.next();
////            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//               Iterator<Map.Entry<String, Map<String,ServiceOperationDetails>>> entries2 = entry.getValue().entrySet().iterator();
//               while (entries2.hasNext())
//               {
//                  Map.Entry<String, Map<String,ServiceOperationDetails>> entry2 = entries2.next();
//                  System.out.println("Service = " + entry.getKey() + "\t Vendor = " + entry2.getKey());
//                  System.out.println("------------------------------------------------------------");
//                  //Iterator<ServiceOperationDetails> entries3 = entry2.getValue().iterator();
//                  Iterator<Map.Entry<String,ServiceOperationDetails>> entries3 = entry2.getValue().entrySet().iterator();
//
//                  while (entries3.hasNext())
//                  {
//                     Map.Entry<String,ServiceOperationDetails> entry3 = entries3.next();
//
//                     System.out.println("Operation = " + entry3.getKey() + ", Status = " + entry3.getValue().getOperationStatus() + ", Vendor = " + entry3.getValue().getVendor());
//                  }
//               }
//               System.out.println("------------------------------------------------------------");
//            }
//
//            System.out.println("*****************************");
//            ServiceDetails.serviceDetailsMap = serviceDetails;
//            System.out.println("serviceDetails = " + serviceDetails);
//            System.out.println("*****************************");
//
//            //setWebServiceAPI(); // setting  broker-webservice vendor
//         }
//      }catch(Exception e){
//         logger.error("Exception while loading service details");
//         logger.error(e.getMessage());
//         e.printStackTrace();
//      }
//
//   }

   public static String getConfigProperty(String product, String service, String mode, String property)
   {
      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Configuration details not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Configuration details not available for Product:" + product+",Service:" + service);
         }
         else
         {
            String provider=getServiceProvider(product,service);
            if (productConfigDetails.get(product).get(service).get(provider) == null)
            {
               logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
            }
            else
            {
               if (productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString()) == null)
               {
                  logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
               }
               else
               {
                  LinkedHashMap<String, Map<String, ServiceConfigDetails>> tmp= (LinkedHashMap<String, Map<String, ServiceConfigDetails>>)productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString());
                  if (tmp.get(mode) == null)
                  {
                     logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider+", Mode:" + mode);
                  }
                  else
                  {
                     if (tmp.get(mode).get(property) == null)
                     {
                        logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider+", Mode:" + mode+", Property:" + property);
                     }
                     else
                     {
                        System.out.println("Property "+property+" : " +tmp.get(mode).get(property).getValue());
                        return tmp.get(mode).get(property).getValue();
                     }
                  }
               }
            }
         }
      }
      return null;
   }

   public static String getConfigProperty(String product, String service, String mode, String provider, String property)
   {
      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Configuration details not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Configuration details not available for Product:" + product+",Service:" + service);
         }
         else
         {
            if (productConfigDetails.get(product).get(service).get(provider) == null)
            {
               logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
            }
            else
            {
               if (productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString()) == null)
               {
                  logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
               }
               else
               {
                  LinkedHashMap<String, Map<String, ServiceConfigDetails>> tmp= (LinkedHashMap<String, Map<String, ServiceConfigDetails>>)productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.CONFIG_DETAILS.toString());
                  if (tmp.get(mode) == null)
                  {
                     logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider+", Mode:" + mode);
                  }
                  else
                  {
                     if (tmp.get(mode).get(property) == null)
                     {
                        logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider+", Mode:" + mode+", Property:" + property);
                     }
                     else
                     {
                        System.out.println("Property "+property+" : " +tmp.get(mode).get(property).getValue());
                        return tmp.get(mode).get(property).getValue();
                     }
                  }
               }
            }
         }
      }
      return null;
   }

   public static String getServiceProvider(String product, String service)
   {
      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Value not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Value not available for Service:" + service);
         }
         else
         {
            Map<String, Map<String, Object>> providerDetails = productConfigDetails.get(product).get(service);

            if (providerDetails.size() > 1)
            {
               System.out.println("More than one provider available.");
            }
            Iterator<Map.Entry<String, Map<String, Object>>> itr=providerDetails.entrySet().iterator();
            while(itr.hasNext()){
               Map.Entry<String, Map<String, Object>> provider=itr.next();
               return provider.getKey();

            }
            //LinkedHashMap<String, Map<String, Object>>()=
                     }
      }

//      if (serviceConfigDetailsMap.get(service) == null)
//      {
//         logger.debug("Value not available for Service:" + service);
//      }
//      else
//      {
//         Map.Entry<String, Map<String, ServiceConfigDetails>> keySet=serviceConfigDetailsMap.get(service).entrySet().iterator().next();
//         return keySet.getKey();
//      }
      return null;
   }
   public static LinkedHashMap<String, ServiceOperationDetails> getOperationDetails(String product, String service)
   {
      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Configuration details not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Configuration details not available for Product:" + product+",Service:" + service);
         }
         else
         {
            String provider=getServiceProvider(product,service);
            if (productConfigDetails.get(product).get(service).get(provider) == null)
            {
               logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
            }
            else
            {
               if (productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString()) == null)
               {
                  logger.debug("Operation details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
               }
               else
               {
                  LinkedHashMap<String, ServiceOperationDetails> tmp= (LinkedHashMap<String, ServiceOperationDetails>)productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.OPERATION_DETAILS.toString());
                     return tmp;

               }
            }
         }
      }
      return null;
   }
   public static Object getAdditionalDetails(String product, String service, String mode, String property)
   {
      System.out.println("ServiceDetails.getAdditionalDetails");
      System.out.println("product = [" + product + "], service = [" + service + "], mode = [" + mode + "], property = [" + property + "]");

      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Configuration details not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Configuration details not available for Product:" + product+",Service:" + service);
         }
         else
         {
            String provider=getServiceProvider(product,service);
            if (productConfigDetails.get(product).get(service).get(provider) == null)
            {
               logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
            }
            else
            {
               if (productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString()) == null)
               {
                  logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
               }
               else
               {

                  Map<String, Object> tmp= (Map<String, Object>)productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.ADDITIONAL_DETAILS.toString());
                  if (tmp.get(property) == null)
                  {
                     logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider+", Mode:" + mode);
                  }
                  else
                  {
                     if (property.equalsIgnoreCase(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString()))
                     {
                        Map<String, Map<String, DCTemplateDetails>> scd = (Map<String, Map<String, DCTemplateDetails>>) tmp.get(Constant.ADDITIONAL_DETAILS.TEMPLATE_DETAILS.toString());
                        return scd.get(mode);
                     }
                     else if (property.equalsIgnoreCase(Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString()))
                     {
                        LinkedHashMap<String, LinkedList<FileDetails>> scd = (LinkedHashMap<String, LinkedList<FileDetails>>) tmp.get(Constant.ADDITIONAL_DETAILS.FILE_DETAILS.toString());



                        return scd; //scd.get(mode);
                     }
                  }
               }
            }
         }
      }
      return null;
   }
   public static Object getCommonDetails(String product, String service, String property)
   {
      if (productConfigDetails.get(product) == null)
      {
         logger.debug("Configuration details not available for Product:" + product);
      }
      else
      {
         if (productConfigDetails.get(product).get(service) == null)
         {
            logger.debug("Configuration details not available for Product:" + product+",Service:" + service);
         }
         else
         {
            String provider=getServiceProvider(product,service);
            if (productConfigDetails.get(product).get(service).get(provider) == null)
            {
               logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
            }
            else
            {
               if (productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString()) == null)
               {
                  logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
               }
               else
               {

                  Map<String, Object> tmp= (Map<String, Object>)productConfigDetails.get(product).get(service).get(provider).get(Constant.SERVICES_DETAILS.COMMON_DETAILS.toString());
                  if (tmp.get(property) == null)
                  {
                     logger.debug("Configuration details not available for Product:" + product+", Service:" + service+", Provider:" + provider);
                  }
                  else
                  {
                     if (property.equalsIgnoreCase(Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString()))
                     {
                        Map<String, List<DCTemplateMapping>> scd = (Map<String, List<DCTemplateMapping>>) tmp.get(Constant.COMMON_DETAILS.DOCUSIGN_MAPPING.toString());
                        return scd;
                     }else if (property.equalsIgnoreCase(Constant.COMMON_DETAILS.FILE_RULES.toString()))
                     {
                        Map<String, Map<String,FileRules>> scd = (Map<String, Map<String,FileRules>>) tmp.get(Constant.COMMON_DETAILS.FILE_RULES.toString());
                        return scd;
                     }else if (property.equalsIgnoreCase(Constant.COMMON_DETAILS.PDF_FILE_RULES.toString()))
                     {
                        Map<String, List<PDFFileRules>> scd = (Map<String, List<PDFFileRules>>) tmp.get(Constant.COMMON_DETAILS.PDF_FILE_RULES.toString());
                        return scd;
                     }
                  }
               }
            }
         }
      }
      return null;
   }
   public static String getConfigProperty(String service, String provider, String property)
   {

      if (serviceConfigDetailsMap.get(service) == null)
      {
         logger.debug("Value not available for Service:" + service);
      }
      else
      {
         if (serviceConfigDetailsMap.get(service).get(provider) == null)
         {
            logger.debug("Value not available for Provider:" + provider);
         }
         else
         {
            if (serviceConfigDetailsMap.get(service).get(provider).get(property) == null)
            {
               logger.debug("Value not available for Property:" + property);
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
         logger.debug("Value not available for Service:" + service);
      }
      else
      {
         Map.Entry<String, Map<String, ServiceConfigDetails>> keySet=serviceConfigDetailsMap.get(service).entrySet().iterator().next();
         return keySet.getKey();
      }
      return null;
   }



   //public static final String BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY ="aRXDugfr4WQpVrxu";
   //String BROKER_SERVICES_GEMINI_ENCRY_DECRY_KEY="GEMINI-KEY";

}
