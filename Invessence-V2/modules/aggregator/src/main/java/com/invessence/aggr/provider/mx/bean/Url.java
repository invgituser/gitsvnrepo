package com.invessence.aggr.provider.mx.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.*;
import com.invessence.aggr.service.IConvertToAggrBean;

/**
 * Created by abhangp on 11/23/2016.
 */
@JsonRootName("url")
public class Url implements IConvertToAggrBean<com.invessence.aggr.bean.Url>
{
   private String url;
   private String type;
   private String user_id;

   @Override
   public String toString()
   {
      return "Url{" +
         "url='" + url + '\'' +
         ", type='" + type + '\'' +
         ", user_id='" + user_id + '\'' +
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

   public String getUser_id()
   {
      return user_id;
   }

   public void setUser_id(String user_id)
   {
      this.user_id = user_id;
   }

   @Override
   public com.invessence.aggr.bean.Url convertToAggrBean()
   {
      return new com.invessence.aggr.bean.Url(this.getUrl(),this.getType(),this.getUser_id());
   }
}
