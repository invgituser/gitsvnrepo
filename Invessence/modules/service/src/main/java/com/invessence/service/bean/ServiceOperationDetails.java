package com.invessence.service.bean;

import java.util.Map;

/**
 * Created by abhangp on 4/20/2016.
 */
public class ServiceOperationDetails
{
   private String company, service, serviceStatus, operation, vendor, operationStatus, refValue;
   private int priority;

   public ServiceOperationDetails(String company, String service, String serviceStatus, String operation, String vendor, String operationStatus, String refValue, int priority)
   {
      this.company = company;
      this.service = service;
      this.serviceStatus = serviceStatus;
      this.operation = operation;
      this.vendor = vendor;
      this.operationStatus = operationStatus;
      this.refValue = refValue;
      this.priority = priority;
   }

   public String getRefValue()
   {
      return refValue;
   }

   public void setRefValue(String refValue)
   {
      this.refValue = refValue;
   }

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
      return "ServiceOperationDetails{" +
         "company='" + company + '\'' +
         ", service='" + service + '\'' +
         ", serviceStatus='" + serviceStatus + '\'' +
         ", operation='" + operation + '\'' +
         ", vendor='" + vendor + '\'' +
         ", operationStatus='" + operationStatus + '\'' +
         ", refValue='" + refValue + '\'' +
         ", priority=" + priority +
         '}';
   }
}
