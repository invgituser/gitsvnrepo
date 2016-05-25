package com.invessence.service.bean;

import java.util.Map;

/**
 * Created by abhangp on 4/20/2016.
 */
public class ServiceDetails
{
   private String company, service, serviceStatus, operation, vendor, operationStatus;
   private int priority;

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public String getService()
   {
      return service;
   }

   public void setService(String service)
   {
      this.service = service;
   }

   public String getServiceStatus()
   {
      return serviceStatus;
   }

   public void setServiceStatus(String serviceStatus)
   {
      this.serviceStatus = serviceStatus;
   }

   public String getOperation()
   {
      return operation;
   }

   public void setOperation(String operation)
   {
      this.operation = operation;
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }

   public String getOperationStatus()
   {
      return operationStatus;
   }

   public void setOperationStatus(String operationStatus)
   {
      this.operationStatus = operationStatus;
   }

   public int getPriority()
   {
      return priority;
   }

   public void setPriority(int priority)
   {
      this.priority = priority;
   }

   @Override
   public String toString()
   {
      return "ServiceDetails{" +
         "company='" + company + '\'' +
         ", service='" + service + '\'' +
         ", serviceStatus='" + serviceStatus + '\'' +
         ", operation='" + operation + '\'' +
         ", vendor='" + vendor + '\'' +
         ", operationStatus='" + operationStatus + '\'' +
         ", priority=" + priority +
         '}';
   }
}
