package com.invessence.custody.uob.data;

import java.util.*;

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
   private String physicalAddressStreet1;
   private String physicalAddressStreet2;
   private String physicalAddressStreet3;
   private String physicalAddressStreet4;
   private String physicalAddressCity;
   private String physicalAddressState;
   private String physicalAddressZipCode;
   private String physicalAddressCountry;
   private String mailingAddressStreet1;
   private String mailingAddressStreet2;
   private String mailingAddressStreet3;
   private String mailingAddressStreet4;
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
   private OwnerCitizenshipDetails ownerCitizenshipDetails;
   private OwnerEmploymentDetails ownerEmploymentDetails;
   private List<OwnerTaxationDetails> ownerTaxationDetails;

   public OwnerDetails(){
      setOwnerCitizenshipDetails(new OwnerCitizenshipDetails());
      setOwnerContactDetails(new OwnerContactDetails());
      setOwnerRegularityDetails(new OwnerRegularityDetails());
      setOwnerIdentificationDetails(new OwnerIdentificationDetails());
      setOwnersFinancialDetails(new OwnerFinancialDetails());
      setOwnerMiscDetails(new OwnerMiscDetails());
      setOwnerEmploymentDetails(new OwnerEmploymentDetails());
      setOwnerTaxationDetails(new LinkedList<OwnerTaxationDetails>());
   }

   public OwnerEmploymentDetails getOwnerEmploymentDetails()
   {
      return ownerEmploymentDetails;
   }

   public void setOwnerEmploymentDetails(OwnerEmploymentDetails ownerEmploymentDetails)
   {
      this.ownerEmploymentDetails = ownerEmploymentDetails;
   }

   public List<OwnerTaxationDetails> getOwnerTaxationDetails()
   {
      return ownerTaxationDetails;
   }

   public void setOwnerTaxationDetails(List<OwnerTaxationDetails> ownerTaxationDetails)
   {
      this.ownerTaxationDetails = ownerTaxationDetails;
   }

   public OwnerCitizenshipDetails getOwnerCitizenshipDetails()
   {
      return ownerCitizenshipDetails;
   }

   public void setOwnerCitizenshipDetails(OwnerCitizenshipDetails ownerCitizenshipDetails)
   {
      this.ownerCitizenshipDetails = ownerCitizenshipDetails;
   }

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

   public String getPhysicalAddressStreet1()
   {
      return physicalAddressStreet1;
   }

   public void setPhysicalAddressStreet1(String physicalAddressStreet1)
   {
      this.physicalAddressStreet1 = physicalAddressStreet1;
   }

   public String getPhysicalAddressStreet2()
   {
      return physicalAddressStreet2;
   }

   public void setPhysicalAddressStreet2(String physicalAddressStreet2)
   {
      this.physicalAddressStreet2 = physicalAddressStreet2;
   }

   public String getPhysicalAddressStreet3()
   {
      return physicalAddressStreet3;
   }

   public void setPhysicalAddressStreet3(String physicalAddressStreet3)
   {
      this.physicalAddressStreet3 = physicalAddressStreet3;
   }

   public String getPhysicalAddressStreet4()
   {
      return physicalAddressStreet4;
   }

   public void setPhysicalAddressStreet4(String physicalAddressStreet4)
   {
      this.physicalAddressStreet4 = physicalAddressStreet4;
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

   public String getMailingAddressStreet1()
   {
      return mailingAddressStreet1;
   }

   public void setMailingAddressStreet1(String mailingAddressStreet1)
   {
      this.mailingAddressStreet1 = mailingAddressStreet1;
   }

   public String getMailingAddressStreet2()
   {
      return mailingAddressStreet2;
   }

   public void setMailingAddressStreet2(String mailingAddressStreet2)
   {
      this.mailingAddressStreet2 = mailingAddressStreet2;
   }

   public String getMailingAddressStreet3()
   {
      return mailingAddressStreet3;
   }

   public void setMailingAddressStreet3(String mailingAddressStreet3)
   {
      this.mailingAddressStreet3 = mailingAddressStreet3;
   }

   public String getMailingAddressStreet4()
   {
      return mailingAddressStreet4;
   }

   public void setMailingAddressStreet4(String mailingAddressStreet4)
   {
      this.mailingAddressStreet4 = mailingAddressStreet4;
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
         ", physicalAddressStreet1='" + physicalAddressStreet1 + '\'' +
         ", physicalAddressStreet2='" + physicalAddressStreet2 + '\'' +
         ", physicalAddressStreet3='" + physicalAddressStreet3 + '\'' +
         ", physicalAddressStreet4='" + physicalAddressStreet4 + '\'' +
         ", physicalAddressCity='" + physicalAddressCity + '\'' +
         ", physicalAddressState='" + physicalAddressState + '\'' +
         ", physicalAddressZipCode='" + physicalAddressZipCode + '\'' +
         ", physicalAddressCountry='" + physicalAddressCountry + '\'' +
         ", mailingAddressStreet1='" + mailingAddressStreet1 + '\'' +
         ", mailingAddressStreet2='" + mailingAddressStreet2 + '\'' +
         ", mailingAddressStreet3='" + mailingAddressStreet3 + '\'' +
         ", mailingAddressStreet4='" + mailingAddressStreet4 + '\'' +
         ", mailingAddressCity='" + mailingAddressCity + '\'' +
         ", mailingAddressState='" + mailingAddressState + '\'' +
         ", mailingAddressZipCode='" + mailingAddressZipCode + '\'' +
         ", mailingAddressCountry='" + mailingAddressCountry + '\'' +
         ", ownership='" + ownership + '\'' +
         ", ownersFinancialDetails=" + ownersFinancialDetails +
         ", ownerContactDetails=" + ownerContactDetails +
         ", ownerIdentificationDetails=" + ownerIdentificationDetails +
         ", ownerRegularityDetails=" + ownerRegularityDetails +
         ", ownerMiscDetails=" + ownerMiscDetails +
         ", ownerCitizenshipDetails=" + ownerCitizenshipDetails +
         ", ownerEmploymentDetails=" + ownerEmploymentDetails +
         ", ownerTaxationDetails=" + ownerTaxationDetails +
         '}';
   }
}
