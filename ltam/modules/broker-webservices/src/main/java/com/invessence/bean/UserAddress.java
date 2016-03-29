package com.invessence.bean;

import java.io.Serializable;

/**
 * Created by abhangp on 3/28/2016.
 */
public class UserAddress implements Serializable
{
   private String mailingAddressId;
   private String nameLines;
   private String addressLines;
   private String postalZip;
   private String countryCode;
   private String voicePhone;
   private String altPhone;
   private String faxPhone;
   private String emailAddress;
   private String mailingAddressType;
   private String entityIdentifier;


   @Override
   public String toString()
   {
      return "UserAddress{" +
         "addressLines='" + addressLines + '\'' +
         ", mailingAddressId='" + mailingAddressId + '\'' +
         ", nameLines='" + nameLines + '\'' +
         ", postalZip='" + postalZip + '\'' +
         ", countryCode='" + countryCode + '\'' +
         ", voicePhone='" + voicePhone + '\'' +
         ", altPhone='" + altPhone + '\'' +
         ", faxPhone='" + faxPhone + '\'' +
         ", emailAddress='" + emailAddress + '\'' +
         ", mailingAddressType='" + mailingAddressType + '\'' +
         ", entityIdentifier='" + entityIdentifier + '\'' +
         '}';
   }

   public String getAddressLines()
   {
      return addressLines;
   }

   public void setAddressLines(String addressLines)
   {
      this.addressLines = addressLines;
   }

   public String getAltPhone()
   {
      return altPhone;
   }

   public void setAltPhone(String altPhone)
   {
      this.altPhone = altPhone;
   }

   public String getCountryCode()
   {
      return countryCode;
   }

   public void setCountryCode(String countryCode)
   {
      this.countryCode = countryCode;
   }

   public String getEmailAddress()
   {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress)
   {
      this.emailAddress = emailAddress;
   }

   public String getEntityIdentifier()
   {
      return entityIdentifier;
   }

   public void setEntityIdentifier(String entityIdentifier)
   {
      this.entityIdentifier = entityIdentifier;
   }

   public String getFaxPhone()
   {
      return faxPhone;
   }

   public void setFaxPhone(String faxPhone)
   {
      this.faxPhone = faxPhone;
   }

   public String getMailingAddressId()
   {
      return mailingAddressId;
   }

   public void setMailingAddressId(String mailingAddressId)
   {
      this.mailingAddressId = mailingAddressId;
   }

   public String getMailingAddressType()
   {
      return mailingAddressType;
   }

   public void setMailingAddressType(String mailingAddressType)
   {
      this.mailingAddressType = mailingAddressType;
   }

   public String getNameLines()
   {
      return nameLines;
   }

   public void setNameLines(String nameLines)
   {
      this.nameLines = nameLines;
   }

   public String getPostalZip()
   {
      return postalZip;
   }

   public void setPostalZip(String postalZip)
   {
      this.postalZip = postalZip;
   }

   public String getVoicePhone()
   {
      return voicePhone;
   }

   public void setVoicePhone(String voicePhone)
   {
      this.voicePhone = voicePhone;
   }


}
