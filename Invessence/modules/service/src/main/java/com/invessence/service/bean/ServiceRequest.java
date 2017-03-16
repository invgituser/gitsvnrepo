package com.invessence.service.bean;

/**
 * Created by abhangp on 3/14/2017.
 */
public class ServiceRequest
{
   private String product;
   private String mode;

   public ServiceRequest(String product, String mode)
   {
      this.product = product;
      this.mode = mode;
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
}
