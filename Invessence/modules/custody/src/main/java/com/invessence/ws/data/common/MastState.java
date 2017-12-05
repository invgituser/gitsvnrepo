package com.invessence.ws.data.common;

import java.util.Date;

/**
 * Created by abhangp on 8/9/2016.
 */
public class MastState
{
   private Integer id;
   private String name;
   private String value;
   private Integer countryCode;
   private String status;
   private Date created;
   private String createdBy;
   private Date updated;
   private String updatedBy;

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getValue()
   {
      return value;
   }

   public void setValue(String value)
   {
      this.value = value;
   }

   public Integer getCountryCode()
   {
      return countryCode;
   }

   public void setCountryCode(Integer countryCode)
   {
      this.countryCode = countryCode;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public Date getCreated()
   {
      return created;
   }

   public void setCreated(Date created)
   {
      this.created = created;
   }

   public String getCreatedBy()
   {
      return createdBy;
   }

   public void setCreatedBy(String createdBy)
   {
      this.createdBy = createdBy;
   }

   public Date getUpdated()
   {
      return updated;
   }

   public void setUpdated(Date updated)
   {
      this.updated = updated;
   }

   public String getUpdatedBy()
   {
      return updatedBy;
   }

   public void setUpdatedBy(String updatedBy)
   {
      this.updatedBy = updatedBy;
   }
}
