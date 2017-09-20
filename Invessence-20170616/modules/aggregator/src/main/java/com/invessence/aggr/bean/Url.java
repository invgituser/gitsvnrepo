package com.invessence.aggr.bean;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by abhangp on 11/23/2016.
 */
public class Url
{
   private String url;
   private String type;
   private String userId;
   private Status errorStatus;

   public Url(String url, String type, String userId, Status errorStatus)
   {
      this.url = url;
      this.type = type;
      this.userId = userId;
      this.errorStatus = errorStatus;
   }

   public Url(String url, String type, String userId)
   {
      this.url = url;
      this.type = type;
      this.userId = userId;
   }

   @Override
   public String toString()
   {
      return "Url{" +
         "url='" + url + '\'' +
         ", type='" + type + '\'' +
         ", userId='" + userId + '\'' +
         ", errorStatus=" + errorStatus +
         '}';
   }

   public String getUrl()
   {
      return url;
   }

   public void setUrl(String url)
   {
      this.url = url;
   }

   public String getType()
   {
      return type;
   }

   public void setType(String type)
   {
      this.type = type;
   }

   public String getUserId()
   {
      return userId;
   }

   public void setUserId(String userId)
   {
      this.userId = userId;
   }

   public Status getErrorStatus()
   {
      return errorStatus;
   }

   public void setErrorStatus(Status errorStatus)
   {
      this.errorStatus = errorStatus;
   }
}
