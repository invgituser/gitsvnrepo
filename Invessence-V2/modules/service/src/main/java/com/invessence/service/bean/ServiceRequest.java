package com.invessence.service.bean;

/**
 * Created by abhangp on 3/14/2017.
 */
public class ServiceRequest
{
   private String product;
   private String mode;
   private String processId;

   public ServiceRequest(String product, String mode)
   {
      this.product = product;
      this.mode = mode;
   }

   public ServiceRequest(String product, String mode, String processId)
   {
      this.product = product;
      this.mode = mode;
      this.processId = processId;
   }

   public String getProcessId()
   {
      return processId;
   }

   public void setProcessId(String processId)
   {
      this.processId = processId;
   }

   public String getProduct()
   {
      return product;
   }

   public void setProduct(String product)
   {
      this.product = product;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   @Override
   public String toString()
   {
      return "ServiceRequest{" +
         "product='" + product + '\'' +
         ", mode='" + mode + '\'' +
         ", processId='" + processId + '\'' +
         '}';
   }
}
