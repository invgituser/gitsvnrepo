package com.invessence.ws.provider.td.bean;

import java.util.List;

/**
 * Created by abhangp on 8/22/2016.
 */
public class AcctOwnerDetails
{

   private Integer acctOwnerId;
   private String ownership;
   private String firstName;
   private String midInitial;
   private String lastName;
   private String fullName;
   private String ssn;
   private String dob;
   private String phoneNumber;
   private String phoneNumberNonUS;
   private String secondPhoneNumber;
   private String secondPhoneNumberNonUS;
   private String emailAddress;
   private String physicalAddressStreet;
   private String physicalAddressCity;
   private String physicalAddressState;
   private String physicalAddressZipCode;
   private List<EmploymentDetails> employmentDetails;
   private VisaDetails visaDetails;
   private String feesCheck;

   public List<EmploymentDetails> getEmploymentDetails()
   {
      return employmentDetails;
   }

   public void setEmploymentDetails(List<EmploymentDetails> employmentDetails)
   {
      this.employmentDetails = employmentDetails;
   }

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public VisaDetails getVisaDetails()
   {
      return visaDetails;
   }

   public void setVisaDetails(VisaDetails visaDetails)
   {
      this.visaDetails = visaDetails;
   }

   @Override
   public String toString()
   {
      return "AcctOwnerDetails{" +
         "ownershipPercent=" + ownershipPercent +
         ", acctOwnerId=" + acctOwnerId +
         ", ownership='" + ownership + '\'' +
         ", firstName='" + firstName + '\'' +
         ", midInitial='" + midInitial + '\'' +
         ", lastName='" + lastName + '\'' +
         ", ssn='" + ssn + '\'' +
         ", dob='" + dob + '\'' +
         ", phoneNumber='" + phoneNumber + '\'' +
         ", phoneNumberNonUS='" + phoneNumberNonUS + '\'' +
         ", secondPhoneNumber='" + secondPhoneNumber + '\'' +
         ", secondPhoneNumberNonUS='" + secondPhoneNumberNonUS + '\'' +
         ", emailAddress='" + emailAddress + '\'' +
         ", physicalAddressStreet='" + physicalAddressStreet + '\'' +
         ", physicalAddressCity='" + physicalAddressCity + '\'' +
         ", physicalAddressState='" + physicalAddressState + '\'' +
         ", physicalAddressZipCode='" + physicalAddressZipCode + '\'' +
         ", mailingAddressStreet='" + mailingAddressStreet + '\'' +
         ", mailingAddressCity='" + mailingAddressCity + '\'' +
         ", mailingAddressState='" + mailingAddressState + '\'' +
         ", mailingAddressZipCode='" + mailingAddressZipCode + '\'' +
         ", sourceOfIncomeId='" + sourceOfIncomeId + '\'' +
         ", citizenship='" + citizenship + '\'' +
         ", countryOfCitizenship='" + countryOfCitizenship + '\'' +
         ", countryOfDualCitizenship='" + countryOfDualCitizenship + '\'' +
         ", countryOfBirth='" + countryOfBirth + '\'' +
         ", isSPF='" + isSPF + '\'' +
         ", spfDetail='" + spfDetail + '\'' +
         ", isDirectorShareholder='" + isDirectorShareholder + '\'' +
         ", directorShareholderDetail='" + directorShareholderDetail + '\'' +
         ", bd='" + bd + '\'' +
         ", bdDetail='" + bdDetail + '\'' +
         ", feesCheck='" + feesCheck + '\'' +
         '}';
   }

   private String mailingAddressStreet;
   private String mailingAddressCity;
   private String mailingAddressState;
   private String mailingAddressZipCode;
   private String sourceOfIncomeId;
   private String citizenship;
   private String countryOfCitizenship;
   private String countryOfDualCitizenship;
   private String countryOfBirth;
   private String isSPF;
   private String spfDetail;
   private String isDirectorShareholder;
   private String directorShareholderDetail;
   private String bd;
   private String bdDetail;
   private Double ownershipPercent;

   public Integer getAcctOwnerId()
   {
      return acctOwnerId;
   }

   public void setAcctOwnerId(Integer acctOwnerId)
   {
      this.acctOwnerId = acctOwnerId;
   }

   public String getOwnership()
   {
      return ownership;
   }

   public void setOwnership(String ownership)
   {
      this.ownership = ownership;
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

   public String getSsn()
   {
      return ssn;
   }

   public void setSsn(String ssn)
   {
      this.ssn = ssn;
   }

   public String getDob()
   {
      return dob;
   }

   public void setDob(String dob)
   {
      this.dob = dob;
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

   public String getSourceOfIncomeId()
   {
      return sourceOfIncomeId;
   }

   public void setSourceOfIncomeId(String sourceOfIncomeId)
   {
      this.sourceOfIncomeId = sourceOfIncomeId;
   }

   public String getCitizenship()
   {
      return citizenship;
   }

   public void setCitizenship(String citizenship)
   {
      this.citizenship = citizenship;
   }

   public String getCountryOfCitizenship()
   {
      return countryOfCitizenship;
   }

   public void setCountryOfCitizenship(String countryOfCitizenship)
   {
      this.countryOfCitizenship = countryOfCitizenship;
   }

   public String getCountryOfDualCitizenship()
   {
      return countryOfDualCitizenship;
   }

   public void setCountryOfDualCitizenship(String countryOfDualCitizenship)
   {
      this.countryOfDualCitizenship = countryOfDualCitizenship;
   }

   public String getCountryOfBirth()
   {
      return countryOfBirth;
   }

   public void setCountryOfBirth(String countryOfBirth)
   {
      this.countryOfBirth = countryOfBirth;
   }

   public String getIsSPF()
   {
      return isSPF;
   }

   public void setIsSPF(String isSPF)
   {
      this.isSPF = isSPF;
   }

   public String getSpfDetail()
   {
      return spfDetail;
   }

   public void setSpfDetail(String spfDetail)
   {
      this.spfDetail = spfDetail;
   }

   public String getIsDirectorShareholder()
   {
      return isDirectorShareholder;
   }

   public void setIsDirectorShareholder(String isDirectorShareholder)
   {
      this.isDirectorShareholder = isDirectorShareholder;
   }

   public String getDirectorShareholderDetail()
   {
      return directorShareholderDetail;
   }

   public void setDirectorShareholderDetail(String directorShareholderDetail)
   {
      this.directorShareholderDetail = directorShareholderDetail;
   }

   public String getBd()
   {
      return bd;
   }

   public void setBd(String bd)
   {
      this.bd = bd;
   }

   public String getBdDetail()
   {
      return bdDetail;
   }

   public void setBdDetail(String bdDetail)
   {
      this.bdDetail = bdDetail;
   }

   public Double getOwnershipPercent()
   {
      return ownershipPercent;
   }

   public void setOwnershipPercent(Double ownershipPercent)
   {
      this.ownershipPercent = ownershipPercent;
   }

   public String getFeesCheck()
   {
      return feesCheck;
   }

   public void setFeesCheck(String feesCheck)
   {
      this.feesCheck = feesCheck;
   }
}
