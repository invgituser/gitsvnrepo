package com.invessence.price.processor.bean;

/**
 * Created by bhaveshy on 3/22/2016.
 */
public class APIDetails
{

   private String company;
   private String serviceName;
   private String serviceOperation;
   private String serviceProvider;
   private String status;
   private String priority;

   public APIDetails()
   {
   }

   public APIDetails(String company, String serviceName, String serviceOperation, String serviceProvider, String status, String priority)
   {
      this.company = company;
      this.serviceName = serviceName;
      this.serviceOperation = serviceOperation;
      this.serviceProvider = serviceProvider;
      this.status = status;
      this.priority = priority;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public String getServiceName()
   {
      return serviceName;
   }

   public void setServiceName(String serviceName)
   {
      this.serviceName = serviceName;
   }

   public String getServiceOperation()
   {
      return serviceOperation;
   }

   public void setServiceOperation(String serviceOperation)
   {
      this.serviceOperation = serviceOperation;
   }

   public String getServiceProvider()
   {
      return serviceProvider;
   }

   public void setServiceProvider(String serviceProvider)
   {
      this.serviceProvider = serviceProvider;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getPriority()
   {
      return priority;
   }

   public void setPriority(String priority)
   {
      this.priority = priority;
   }
}
