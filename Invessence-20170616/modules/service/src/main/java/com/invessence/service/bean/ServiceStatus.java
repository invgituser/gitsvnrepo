package com.invessence.service.bean;

/**
 * Created by abhangp on 01/18/2017.
 */
public class ServiceStatus implements java.io.Serializable {
   private Integer errorCode;
   private String errorMessage;

   public ServiceStatus(Integer errorCode, String errorMessage)
   {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
   }

   public Integer getErrorCode()
   {
      return errorCode;
   }

   public void setErrorCode(Integer errorCode)
   {
      this.errorCode = errorCode;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }

   @Override
   public String toString()
   {
      return "Status{" +
         "errorCode=" + errorCode +
         ", errorMessage='" + errorMessage + '\'' +
         '}';
   }
}
