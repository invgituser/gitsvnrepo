package com.invessence.ws.bean;

import java.io.Serializable;

/**
 * Created by abhangp on 3/28/2016.
 */
public class UserAddress implements Serializable
{
   private int mailingAddressId;
   private String firstName, middleName, lastName;
   private String addressLine1, addressLine2, addressLine3;
   private String city, state;
//   private String nameLines;
//   private String addressLines;
   private String postalZip;
   private short countryCode;
   private String voicePhone;
   private String altPhone;
   private String faxPhone;
   private String emailAddress;
   private String mailingAddressType;
   private String entityIdentifier;

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return state;
   }

   public void setState(String state)
   {
      this.state = state;
   }

   public UserAddress(String firstName, String middleName, String lastName,
                      String addressLine1, String addressLine2, String addressLine3, String city, String  state, String postalZip, short countryCode,
                      String voicePhone, String altPhone, String faxPhone, String emailAddress, int mailingAddressId, String mailingAddressType, String entityIdentifier)
   {
      this.city = city;
      this.state = state;
      this.firstName = firstName;
      this.middleName = middleName;
      this.lastName = lastName;
      this.addressLine1 = addressLine1;
      this.addressLine2 = addressLine2;
      this.addressLine3 = addressLine3;
      this.altPhone = altPhone;
      this.countryCode = countryCode;
      this.emailAddress = emailAddress;
      this.faxPhone = faxPhone;
      this.postalZip = postalZip;
      this.voicePhone = voicePhone;
      this.mailingAddressId =mailingAddressId;
      this.mailingAddressType=mailingAddressType;
      this.entityIdentifier=entityIdentifier;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleName()
   {
      return middleName;
   }

   public void setMiddleName(String middleName)
   {
      this.middleName = middleName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getAddressLine1()
   {
      return addressLine1;
   }

   public void setAddressLine1(String addressLine1)
   {
      this.addressLine1 = addressLine1;
   }

   public String getAddressLine2()
   {
      return addressLine2;
   }

   public void setAddressLine2(String addressLine2)
   {
      this.addressLine2 = addressLine2;
   }

   public String getAddressLine3()
   {
      return addressLine3;
   }

   public void setAddressLine3(String addressLine3)
   {
      this.addressLine3 = addressLine3;
   }

   public String getEntityIdentifier()
   {
      return entityIdentifier;
   }

   public void setEntityIdentifier(String entityIdentifier)
   {
      this.entityIdentifier = entityIdentifier;
   }

   public int getMailingAddressId()
   {
      return mailingAddressId;
   }

   public void setMailingAddressId(int mailingAddressId)
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

//   public String getAddressLines()
//   {
//      return addressLines;
//   }
//
//   public void setAddressLines(String addressLines)
//   {
//      this.addressLines = addressLines;
//   }

   public String getAltPhone()
   {
      return altPhone;
   }

   public void setAltPhone(String altPhone)
   {
      this.altPhone = altPhone;
   }

   public short getCountryCode()
   {
      return countryCode;
   }

   public void setCountryCode(short countryCode)
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

   public String getFaxPhone()
   {
      return faxPhone;
   }

   public void setFaxPhone(String faxPhone)
   {
      this.faxPhone = faxPhone;
   }

//   public String getNameLines()
//   {
//      return nameLines;
//   }
//
//   public void setNameLines(String nameLines)
//   {
//      this.nameLines = nameLines;
//   }

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
