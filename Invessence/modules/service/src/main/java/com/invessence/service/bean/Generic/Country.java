package com.invessence.service.bean.Generic;

/**
 * Created by abhangp on 11/22/2017.
 */
public class Country
{
   private String iso;
   private String name;
   private String nicename;
   private String iso3;
   private Short numcode;
   private Integer phonecode;
   private String status;

   @Override
   public String toString()
   {
      return "Country{" +
         "iso='" + iso + '\'' +
         ", name='" + name + '\'' +
         ", nicename='" + nicename + '\'' +
         ", iso3='" + iso3 + '\'' +
         ", numcode=" + numcode +
         ", phonecode=" + phonecode +
         ", status='" + status + '\'' +
         '}';
   }

   public void setNumcode(Short numcode)
   {
      this.numcode = numcode;
   }

   public String getStatus()
   {
      return status;
   }

   public void setStatus(String status)
   {
      this.status = status;
   }

   public String getIso()
   {
      return iso;
   }

   public void setIso(String iso)
   {
      this.iso = iso;
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getNicename()
   {
      return nicename;
   }

   public void setNicename(String nicename)
   {
      this.nicename = nicename;
   }

   public String getIso3()
   {
      return iso3;
   }

   public void setIso3(String iso3)
   {
      this.iso3 = iso3;
   }


   public Integer getPhonecode()
   {
      return phonecode;
   }

   public void setPhonecode(Integer phonecode)
   {
      this.phonecode = phonecode;
   }
}
