package com.invessence.service.bean;

/**
 * Created by abhangp on 1/17/2017.
 */
public class ExceptionInternal
{
   private String errCode;
   private String errMsg;
   private String status;

   public String getErrCode()
   {
      return errCode;
   }

   public void setErrCode(String errCode)
   {
      this.errCode = errCode;
   }

   public String getErrMsg()
   {
      return errMsg;
   }

   public void setErrMsg(String errMsg)
   {
      this.errMsg = errMsg;
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
      return "ExceptionInternal{" +
         "errCode='" + errCode + '\'' +
         ", errMsg='" + errMsg + '\'' +
         ", status='" + status + '\'' +
         '}';
   }
}
