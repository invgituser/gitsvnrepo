package com.invessence.data.common;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 4/13/16
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceResult
{
   private Boolean status;
   private String message;

   public ServiceResult()
   {
      status = false;
      message = "Un-initalized, object.";
   }

   public ServiceResult(Boolean status, String message)
   {
      this.status = status;
      this.message = message;
   }

   public Boolean getStatus()
   {
      return status;
   }

   public void setStatus(Boolean status)
   {
      this.status = status;
   }

   public String getMessage()
   {
      return message;
   }

   public void setMessage(String message)
   {
      this.message = message;
   }
}
