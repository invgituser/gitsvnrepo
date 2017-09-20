package com.invessence.service.bean;

/**
 * Created by abhangp on 1/17/2017.
 */
public class ExceptionExternal
{
   private String service;
   private String vendor;
   private String displayErrMsg;
   private String vendorErrCode;
   private String vendorErrMsg;
   private String status;

   public String getService()
   {
      return service;
   }

   public void setService(String service)
   {
      this.service = service;
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }

   public String getDisplayErrMsg()
   {
      return displayErrMsg;
   }

   public void setDisplayErrMsg(String displayErrMsg)
   {
      this.displayErrMsg = displayErrMsg;
   }

   public String getVendorErrCode()
   {
      return vendorErrCode;
   }

   public void setVendorErrCode(String vendorErrCode)
   {
      this.vendorErrCode = vendorErrCode;
   }

   public String getVendorErrMsg()
   {
      return vendorErrMsg;
   }

   public void setVendorErrMsg(String vendorErrMsg)
   {
      this.vendorErrMsg = vendorErrMsg;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   @Override
   public String toString()
   {
      return "ExceptionExternal{" +
         "service='" + service + '\'' +
         ", vendor='" + vendor + '\'' +
         ", displayErrMsg='" + displayErrMsg + '\'' +
         ", vendorErrCode='" + vendorErrCode + '\'' +
         ", vendorErrMsg='" + vendorErrMsg + '\'' +
         ", status='" + status + '\'' +
         '}';
   }
}
