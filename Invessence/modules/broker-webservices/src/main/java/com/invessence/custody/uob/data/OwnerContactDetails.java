package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/26/2017.
 */
public class OwnerContactDetails
{
   private String  faxNumber;
   private String  homeTelNumber;
   private String  mobNumber;
   private String  officeTelNumber;
   private String  phoneNumber;
   private String  phoneNumberNonUS;
   private String  secondPhoneNumber;
   private String  secondPhoneNumberNonUS;

   @Override
   public String toString()
   {
      return "OwnerContactDetails{" +
         "faxNumber='" + faxNumber + '\'' +
         ", homeTelNumber='" + homeTelNumber + '\'' +
         ", mobNumber='" + mobNumber + '\'' +
         ", officeTelNumber='" + officeTelNumber + '\'' +
         ", phoneNumber='" + phoneNumber + '\'' +
         ", phoneNumberNonUS='" + phoneNumberNonUS + '\'' +
         ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
         ", secondPhoneNumberNonUS='" + secondPhoneNumberNonUS + '\'' +
         '}';
   }

   public String getFaxNumber()
   {
      return faxNumber;
   }

   public void setFaxNumber(String faxNumber)
   {
      this.faxNumber = faxNumber;
   }

   public String getHomeTelNumber()
   {
      return homeTelNumber;
   }

   public void setHomeTelNumber(String homeTelNumber)
   {
      this.homeTelNumber = homeTelNumber;
   }

   public String getMobNumber()
   {
      return mobNumber;
   }

   public void setMobNumber(String mobNumber)
   {
      this.mobNumber = mobNumber;
   }

   public String getOfficeTelNumber()
   {
      return officeTelNumber;
   }

   public void setOfficeTelNumber(String officeTelNumber)
   {
      this.officeTelNumber = officeTelNumber;
   }

   public String getPhoneNumber()
   {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber)
   {
      this.phoneNumber = phoneNumber;
   }

   public String getPhoneNumberNonUS()
   {
      return phoneNumberNonUS;
   }

   public void setPhoneNumberNonUS(String phoneNumberNonUS)
   {
      this.phoneNumberNonUS = phoneNumberNonUS;
   }

   public String getSecondPhoneNumber()
   {
      return secondPhoneNumber;
   }

   public void setSecondPhoneNumber(String secondPhoneNumber)
   {
      this.secondPhoneNumber = secondPhoneNumber;
   }

   public String getSecondPhoneNumberNonUS()
   {
      return secondPhoneNumberNonUS;
   }

   public void setSecondPhoneNumberNonUS(String secondPhoneNumberNonUS)
   {
      this.secondPhoneNumberNonUS = secondPhoneNumberNonUS;
   }
}
