package com.invessence.bean;

/**
 * Created by abhangp on 3/22/2016.
 */
public class WSCallStatus implements java.io.Serializable {
   private int errorCode;

   private String errorMessage;

   @Override
   public String toString()
   {
      return "WSCallStatus{" +
         "errorCode=" + errorCode +
         ", errorMessage='" + errorMessage + '\'' +
         '}';
   }

   public int getErrorCode()
   {
      return errorCode;
   }

   public void setErrorCode(int errorCode)
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

   public WSCallStatus() {

   }

   public WSCallStatus(
      int errorCode,
      String errorMessage) {
      this.errorCode = errorCode;
      this.errorMessage = errorMessage;
   }

}
