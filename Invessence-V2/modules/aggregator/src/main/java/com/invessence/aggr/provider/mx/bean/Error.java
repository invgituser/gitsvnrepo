package com.invessence.aggr.provider.mx.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by abhangp on 11/23/2016.
 */
@JsonRootName("error")
public class Error implements Serializable
{
   private String code;
   private Integer status;
   private String message;

   public String getCode()
   {
      return code;
   }

   public void setCode(String code)
   {
      this.code = code;
   }

   public Integer getStatus()
   {
      return status;
   }

   public void setStatus(Integer status)
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

   @Override
   public String toString()
   {
      return "Error{" +
         "code='" + code + '\'' +
         ", status='" + status + '\'' +
         ", message='" + message + '\'' +
         '}';
   }
}
