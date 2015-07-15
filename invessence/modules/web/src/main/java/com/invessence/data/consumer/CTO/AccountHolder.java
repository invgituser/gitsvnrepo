package com.invessence.data.consumer.CTO;

import java.io.Serializable;
import java.util.Date;

public class AccountHolder implements Serializable
{
   private static final long serialVersionUID = 8872844731512695614L;

   private Long acctnum;
   private String userid;
   private String socialsecurity;
   private String prefix;
   private String suffix;
   private String firstname;
   private String middlename;
   private String lastname;
   private Date dateOfBirth;
   private String countryofbirth;
   private String countryofresidence;
   private String driverslicensenumber;
   private String driverslicensestate;


   public String getUserid()
   {
      return userid;
   }

   public void setUserid(String userid)
   {
      this.userid = userid;
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


}
