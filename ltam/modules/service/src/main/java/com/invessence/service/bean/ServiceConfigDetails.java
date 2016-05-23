package com.invessence.service.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by abhangp on 5/13/2016.
 */
public class ServiceConfigDetails implements Serializable
{
   private String mode, company, vendor, service, name, value, encrFlag;
   private Date created, updated;

   @Override
   public String toString()
   {
      return "ServiceConfigDetails{" +
         "company='" + company + '\'' +
         ", SERVICE_MODE='" + mode + '\'' +
         ", vendor='" + vendor + '\'' +
         ", name='" + name + '\'' +
         ", value='" + value + '\'' +
         ", encrFlag='" + encrFlag + '\'' +
         ", created=" + created +
         ", updated=" + updated +
         '}';
   }

   public String getService()
   {
      return service;
   }

   public void setService(String service)
   {
      this.service = service;
   }

   public String getCompany()
   {
      return company;
   }

   public void setCompany(String company)
   {
      this.company = company;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public String getEncrFlag()
   {
      return encrFlag;
   }

   public void setEncrFlag(String encrFlag)
   {
      this.encrFlag = encrFlag;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getMode()
   {
      return mode;
   }

   public void setMode(String mode)
   {
      this.mode = mode;
   }

   public Date getUpdated()
   {
      return updated;
   }

   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   public String getValue()
   {
      return value;
   }

   public void setValue(String value)
   {
      this.value = value;
   }

   public String getVendor()
   {
      return vendor;
   }

   public void setVendor(String vendor)
   {
      this.vendor = vendor;
   }
}
