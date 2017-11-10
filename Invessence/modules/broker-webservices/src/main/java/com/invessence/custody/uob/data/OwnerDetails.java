package com.invessence.custody.uob.data;

/**
 * Created by abhangp on 10/26/2017.
 */
public class OwnerDetails
{
   private Long acctnum;
   private Integer acctOwnerId;
   private String title;
   private String firstName;
   private String midInitial;
   private String lastName;
   private String fullName;
   private String gender;
   private String dob;
   private String countryOfBirth;
   private String emailAddress;
   private String physicalAddressStreet;
   private String physicalAddressCity;
   private String physicalAddressState;
   private String physicalAddressZipCode;
   private String physicalAddressCountry;
   private String mailingAddressStreet;
   private String mailingAddressCity;
   private String mailingAddressState;
   private String mailingAddressZipCode;
   private String mailingAddressCountry;
   private String ownership;

   private OwnerFinancialDetails ownersFinancialDetails;
   private OwnerContactDetails ownerContactDetails;
   private OwnerIdentificationDetails ownerIdentificationDetails;
   private OwnerRegularityDetails ownerRegularityDetails;
   private OwnerMiscDetails ownerMiscDetails;

   public OwnerFinancialDetails getOwnersFinancialDetails()
   {
      return ownersFinancialDetails;
   }

   public void setOwnersFinancialDetails(OwnerFinancialDetails ownersFinancialDetails)
   {
      this.ownersFinancialDetails = ownersFinancialDetails;
   }

   public OwnerContactDetails getOwnerContactDetails()
   {
      return ownerContactDetails;
   }

   public void setOwnerContactDetails(OwnerContactDetails ownerContactDetails)
   {
      this.ownerContactDetails = ownerContactDetails;
   }

   public OwnerIdentificationDetails getOwnerIdentificationDetails()
   {
      return ownerIdentificationDetails;
   }

   public void setOwnerIdentificationDetails(OwnerIdentificationDetails ownerIdentificationDetails)
   {
      this.ownerIdentificationDetails = ownerIdentificationDetails;
   }

   public OwnerRegularityDetails getOwnerRegularityDetails()
   {
      return ownerRegularityDetails;
   }

   public void setOwnerRegularityDetails(OwnerRegularityDetails ownerRegularityDetails)
   {
      this.ownerRegularityDetails = ownerRegularityDetails;
   }

   public OwnerMiscDetails getOwnerMiscDetails()
   {
      return ownerMiscDetails;
   }

   public void setOwnerMiscDetails(OwnerMiscDetails ownerMiscDetails)
   {
      this.ownerMiscDetails = ownerMiscDetails;
   }

   public String getOwnership()
   {
      return ownership;
   }

   public Boolean isIndividualAccount()
   {
      if(getOwnership()!=null)
      {
         if (getOwnership().equals("Individual"))
         {
            return true;
         }
      }
      return false;
   }

   public void setOwnership(String ownership)
   {
      this.ownership = ownership;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Integer getAcctOwnerId()
   {
      return acctOwnerId;
   }

   public void setAcctOwnerId(Integer acctOwnerId)
   {
      this.acctOwnerId = acctOwnerId;
   }

   public String getTitle()
   {
      return title;
   }

   public void setTitle(String title)
   {
      this.title = title;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMidInitial()
   {
      return midInitial;
   }

   public void setMidInitial(String midInitial)
   {
      this.midInitial = midInitial;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public String getDob()
   {
      return dob;
   }

   public void setDob(String dob)
   {
      this.dob = dob;
   }

   public String getCountryOfBirth()
   {
      return countryOfBirth;
   }

   public void setCountryOfBirth(String countryOfBirth)
   {
      this.countryOfBirth = countryOfBirth;
   }

   public String getEmailAddress()
   {
      return emailAddress;
   }

   public void setEmailAddress(String emailAddress)
   {
      this.emailAddress = emailAddress;
   }

   public String getPhysicalAddressStreet()
   {
      return physicalAddressStreet;
   }

   public void setPhysicalAddressStreet(String physicalAddressStreet)
   {
      this.physicalAddressStreet = physicalAddressStreet;
   }

   public String getPhysicalAddressCity()
   {
      return physicalAddressCity;
   }

   public void setPhysicalAddressCity(String physicalAddressCity)
   {
      this.physicalAddressCity = physicalAddressCity;
   }

   public String getPhysicalAddressState()
   {
      return physicalAddressState;
   }

   public void setPhysicalAddressState(String physicalAddressState)
   {
      this.physicalAddressState = physicalAddressState;
   }

   public String getPhysicalAddressZipCode()
   {
      return physicalAddressZipCode;
   }

   public void setPhysicalAddressZipCode(String physicalAddressZipCode)
   {
      this.physicalAddressZipCode = physicalAddressZipCode;
   }

   public String getPhysicalAddressCountry()
   {
      return physicalAddressCountry;
   }

   public void setPhysicalAddressCountry(String physicalAddressCountry)
   {
      this.physicalAddressCountry = physicalAddressCountry;
   }

   public String getMailingAddressStreet()
   {
      return mailingAddressStreet;
   }

   public void setMailingAddressStreet(String mailingAddressStreet)
   {
      this.mailingAddressStreet = mailingAddressStreet;
   }

   public String getMailingAddressCity()
   {
      return mailingAddressCity;
   }

   public void setMailingAddressCity(String mailingAddressCity)
   {
      this.mailingAddressCity = mailingAddressCity;
   }

   public String getMailingAddressState()
   {
      return mailingAddressState;
   }

   public void setMailingAddressState(String mailingAddressState)
   {
      this.mailingAddressState = mailingAddressState;
   }

   public String getMailingAddressZipCode()
   {
      return mailingAddressZipCode;
   }

   public void setMailingAddressZipCode(String mailingAddressZipCode)
   {
      this.mailingAddressZipCode = mailingAddressZipCode;
   }

   public String getMailingAddressCountry()
   {
      return mailingAddressCountry;
   }

   public void setMailingAddressCountry(String mailingAddressCountry)
   {
      this.mailingAddressCountry = mailingAddressCountry;
   }

   @Override
   public String toString()
   {
      return "OwnerDetails{" +
         "acctnum=" + acctnum +
         ", acctOwnerId=" + acctOwnerId +
         ", title='" + title + '\'' +
         ", firstName='" + firstName + '\'' +
         ", midInitial='" + midInitial + '\'' +
         ", lastName='" + lastName + '\'' +
         ", fullName='" + fullName + '\'' +
         ", gender='" + gender + '\'' +
         ", dob='" + dob + '\'' +
         ", countryOfBirth='" + countryOfBirth + '\'' +
         ", emailAddress='" + emailAddress + '\'' +
         ", physicalAddressStreet='" + physicalAddressStreet + '\'' +
         ", physicalAddressCity='" + physicalAddressCity + '\'' +
         ", physicalAddressState='" + physicalAddressState + '\'' +
         ", physicalAddressZipCode='" + physicalAddressZipCode + '\'' +
         ", physicalAddressCountry='" + physicalAddressCountry + '\'' +
         ", mailingAddressStreet='" + mailingAddressStreet + '\'' +
         ", mailingAddressCity='" + mailingAddressCity + '\'' +
         ", mailingAddressState='" + mailingAddressState + '\'' +
         ", mailingAddressZipCode='" + mailingAddressZipCode + '\'' +
         ", mailingAddressCountry='" + mailingAddressCountry + '\'' +
         ", ownersFinancialDetails=" + ownersFinancialDetails +
         ", ownerContactDetails=" + ownerContactDetails +
         ", ownerIdentificationDetails=" + ownerIdentificationDetails +
         ", ownerRegularityDetails=" + ownerRegularityDetails +
         ", ownerMiscDetails=" + ownerMiscDetails +
         '}';
   }
}
