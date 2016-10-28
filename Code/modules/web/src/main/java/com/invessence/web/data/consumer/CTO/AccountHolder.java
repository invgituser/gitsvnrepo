package com.invessence.web.data.consumer.CTO;

import java.util.Date;

public class AccountHolder
{

   private Long logonid;
   private Long acctnum;
   private Integer percentOwnership;
   private String socialsecurity;
   private String prefix;
   private String firstname;
   private String middlename;
   private String lastname;
   private String suffix;
   private Date dateOfBirth;
   private String countryofbirth;
   private String countryofresidence;
   private String driverslicensenumber;
   private String driverslicensestate;

   private String primaryEmail;
   private String altEmail;
   private String primaryphone;
   private String altPhone;
   private String methodOfCommunication;

   private String gender;
   private String maritalStatus;

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public String getSocialsecurity()
   {
      return socialsecurity;
   }

   public void setSocialsecurity(String socialsecurity)
   {
      this.socialsecurity = socialsecurity;

   }
   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getFirstname()
   {
      return firstname;
   }

   public void setFirstname(String firstname)
   {
      this.firstname = firstname;
   }

   public String getMiddlename()
   {
      return middlename;
   }

   public void setMiddlename(String middlename)
   {
      this.middlename = middlename;
   }

   public String getLastname()
   {
      return lastname;
   }

   public void setLastname(String lastname)
   {
      this.lastname = lastname;
   }

   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

    public String getCountryofbirth() {
        return countryofbirth;
    }

    public void setCountryofbirth(String countryofbirth) {
        this.countryofbirth = countryofbirth;
    }

    public String getCountryofresidence() {
        return countryofresidence;
    }

    public void setCountryofresidence(String countryofresidence) {
        this.countryofresidence = countryofresidence;
    }


   public String getDriverslicensenumber()
   {
      return driverslicensenumber;
   }

   public void setDriverslicensenumber(String driverslicensenumber)
   {
      this.driverslicensenumber = driverslicensenumber;
   }

   public String getDriverslicensestate()
   {
      return driverslicensestate;
   }

   public void setDriverslicensestate(String driverslicensestate)
   {
      this.driverslicensestate = driverslicensestate;
   }

   public String getPrimaryEmail()
   {
      return primaryEmail;
   }

   public void setPrimaryEmail(String primaryEmail)
   {
      this.primaryEmail = primaryEmail;
   }

   public String getAltEmail()
   {
      return altEmail;
   }

   public void setAltEmail(String altEmail)
   {
      this.altEmail = altEmail;
   }

   public String getPrimaryphone()
   {
      return primaryphone;
   }

   public void setPrimaryphone(String primaryphone)
   {
      this.primaryphone = primaryphone;
   }

   public String getAltPhone()
   {
      return altPhone;
   }

   public void setAltPhone(String altPhone)
   {
      this.altPhone = altPhone;
   }

   public String getMethodOfCommunication()
   {
      return methodOfCommunication;
   }

   public void setMethodOfCommunication(String methodOfCommunication)
   {
      this.methodOfCommunication = methodOfCommunication;
   }

   public String getGender()
   {
      return gender;
   }

   public void setGender(String gender)
   {
      this.gender = gender;
   }

   public String getMaritalStatus()
   {
      return maritalStatus;
   }

   public void setMaritalStatus(String maritalStatus)
   {
      this.maritalStatus = maritalStatus;
   }

}
