package com.invessence.web.data.consumer.CTO;

import java.util.Date;

public class Beneficiary
{

   private String prefix;
   private String firstname;
   private String middlename;
   private String lastname;
   private String suffix;
   private String relationship;

   private String address1;
   private String address2;
   private String address3;
   private String address4;
   private String city;
   private String statecode;
   private String statename;
   private String stateProvince;
   private String zipcode;
   private String country;
   private String identification;
   private String typeofid; // It could be SS#, Driver Licence#, Passport#, etc. for International support
   private String countryofIssuance;
   private Date dateOfBirth;
   private Double allocation;

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
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

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
   }

   public String getRelationship()
   {
      return relationship;
   }

   public void setRelationship(String relationship)
   {
      this.relationship = relationship;
   }

   public String getAddress1()
   {
      return address1;
   }

   public void setAddress1(String address1)
   {
      this.address1 = address1;
   }

   public String getAddress2()
   {
      return address2;
   }

   public void setAddress2(String address2)
   {
      this.address2 = address2;
   }

   public String getAddress3()
   {
      return address3;
   }

   public void setAddress3(String address3)
   {
      this.address3 = address3;
   }

   public String getAddress4()
   {
      return address4;
   }

   public void setAddress4(String address4)
   {
      this.address4 = address4;
   }

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getStatecode()
   {
      return statecode;
   }

   public void setStatecode(String statecode)
   {
      this.statecode = statecode;
   }

   public String getStatename()
   {
      return statename;
   }

   public void setStatename(String statename)
   {
      this.statename = statename;
   }

   public String getStateProvince()
   {
      return stateProvince;
   }

   public void setStateProvince(String stateProvince)
   {
      this.stateProvince = stateProvince;
   }

   public String getZipcode()
   {
      return zipcode;
   }

   public void setZipcode(String zipcode)
   {
      this.zipcode = zipcode;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getIdentification()
   {
      return identification;
   }

   public void setIdentification(String identification)
   {
      this.identification = identification;
   }

   public String getTypeofid()
   {
      return typeofid;
   }

   public void setTypeofid(String typeofid)
   {
      this.typeofid = typeofid;
   }

   public String getCountryofIssuance()
   {
      return countryofIssuance;
   }

   public void setCountryofIssuance(String countryofIssuance)
   {
      this.countryofIssuance = countryofIssuance;
   }

   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public Double getAllocation()
   {
      return allocation;
   }

   public void setAllocation(Double allocation)
   {
      this.allocation = allocation;
   }

   public String getName()
   {
      return prefix + " " + firstname + " " + middlename + " " + lastname + " " + suffix;
   }
}
