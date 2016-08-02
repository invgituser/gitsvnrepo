package com.invessence.web.data.custody;

/**
 * Created with IntelliJ IDEA.
 * User: Prashant
 * Date: 8/1/16
 * Time: 10:05 AM
 * To change this template use File | Settings | File Templates.
 */
public class AccountHolder
{
   private Long acctnum;
   private Integer id;
   private String ownership;
   private String firstName;
   private String midInitial;
   private String lastName;
   private String ssn;
   private String dob;
   private String phoneNumber, phoneNumberNonUS;
   private String secondPhoneNumber, secondPhoneNumberNonUS;
   private String emailAddress;

   private String physicalAddressStreet1;
   private String physicalAddressStreet2;
   private String physicalAddressStreet3;
   private String physicalAddressCity;
   private String physicalAddressState;
   private String physicalAddressZipCode;

   private String mailingAddressStreet1;
   private String mailingAddressStreet2;
   private String mailingAddressStreet3;
   private String mailingAddressCity;
   private String mailingAddressState;
   private String mailingAddressZipCode;

   private String sourceOfIncomeId;
   private String citizenshiId;
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

   public AccountHolder()
   {
   }

   public Integer getId()
   {
      return id;
   }

   public void setId(Integer id)
   {
      this.id = id;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
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

   public String getCitizenshiId()
   {
      return citizenshiId;
   }

   public void setCitizenshiId(String citizenshiId)
   {
      this.citizenshiId = citizenshiId;
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

   public String getSPF()
   {
      return isSPF;
   }

   public void setSPF(String SPF)
   {
      isSPF = SPF;
   }

   public String getSpfDetail()
   {
      return spfDetail;
   }

   public void setSpfDetail(String spfDetail)
   {
      this.spfDetail = spfDetail;
   }

   public String getDirectorShareholder()
   {
      return isDirectorShareholder;
   }

   public void setDirectorShareholder(String directorShareholder)
   {
      isDirectorShareholder = directorShareholder;
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
}
