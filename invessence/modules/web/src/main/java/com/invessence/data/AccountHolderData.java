package com.invessence.data;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Shankari
 * Date: 9/6/14
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountHolderData
{
   private String fullName = null;
   private String prefix = null;
   private String firstName = null;
   private String middleInitial = null;
   private String lastName = null;
   private String suffix = null;

   private String address1 = null;
   private String address2 = null;
   private String city = null;
   private String stateCode = null;
   private String stateName = null;
   private String stateProvince = null;
   private String zipCode = null;
   private String country = null;

   private String mailingAddress1 = null;
   private String mailingAddress2 = null;
   private String mailingCity = null;
   private String mailingStateCode = null;
   private String mailingStateName = null;
   private String mailingStateProvince = null;
   private String mailingZipCode = null;
   private String mailingCountry = null;

   private String phone = null;
   private String phonetype = null;


   private String countryOfCitizenship;
   private String countryOfCitizenshipId;
   private String countryOfResidence;
   private String countryOfResidenceId;
   private String mailingCountryOfResidence;
   private String mailingCountryOfResidenceId;


   private Date dateOfBirth;
   private String gender;
   private String maritalStatus;
   private String dependents;
   private String socialSecurity;


   private String employmentStatus;
   private String occupation;
   private String natureOfBusiness;
   private String employerName;
   private String employerAddress1 = null;
   private String employerAddress2 = null;
   private String employerCity = null;
   private String employerStateCode = null;
   private String employerStateName = null;
   private String employerStateProvince = null;
   private String employerZipCode = null;
   private String employerCountry = null;
   private String employerCountryOfResidence;
   private String employerCountryOfResidenceId;

   public String getFullName()
   {
      return fullName;
   }

   public void setFullName(String fullName)
   {
      this.fullName = fullName;
   }

   public String getPrefix()
   {
      return prefix;
   }

   public void setPrefix(String prefix)
   {
      this.prefix = prefix;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleInitial()
   {
      return middleInitial;
   }

   public void setMiddleInitial(String middleInitial)
   {
      this.middleInitial = middleInitial;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getSuffix()
   {
      return suffix;
   }

   public void setSuffix(String suffix)
   {
      this.suffix = suffix;
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

   public String getCity()
   {
      return city;
   }

   public void setCity(String city)
   {
      this.city = city;
   }

   public String getStateCode()
   {
      return stateCode;
   }

   public void setStateCode(String stateCode)
   {
      this.stateCode = stateCode;
   }

   public String getStateName()
   {
      return stateName;
   }

   public void setStateName(String stateName)
   {
      this.stateName = stateName;
   }

   public String getStateProvince()
   {
      return stateProvince;
   }

   public void setStateProvince(String stateProvince)
   {
      this.stateProvince = stateProvince;
   }

   public String getZipCode()
   {
      return zipCode;
   }

   public void setZipCode(String zipCode)
   {
      this.zipCode = zipCode;
   }

   public String getCountry()
   {
      return country;
   }

   public void setCountry(String country)
   {
      this.country = country;
   }

   public String getPhone()
   {
      return phone;
   }

   public void setPhone(String phone)
   {
      this.phone = phone;
   }

   public String getPhonetype()
   {
      return phonetype;
   }

   public void setPhonetype(String phonetype)
   {
      this.phonetype = phonetype;
   }

   public String getMailingAddress1()
   {
      return mailingAddress1;
   }

   public void setMailingAddress1(String mailingAddress1)
   {
      this.mailingAddress1 = mailingAddress1;
   }

   public String getMailingAddress2()
   {
      return mailingAddress2;
   }

   public void setMailingAddress2(String mailingAddress2)
   {
      this.mailingAddress2 = mailingAddress2;
   }

   public String getMailingCity()
   {
      return mailingCity;
   }

   public void setMailingCity(String mailingCity)
   {
      this.mailingCity = mailingCity;
   }

   public String getMailingStateCode()
   {
      return mailingStateCode;
   }

   public void setMailingStateCode(String mailingStateCode)
   {
      this.mailingStateCode = mailingStateCode;
   }

   public String getMailingStateName()
   {
      return mailingStateName;
   }

   public void setMailingStateName(String mailingStateName)
   {
      this.mailingStateName = mailingStateName;
   }

   public String getMailingStateProvince()
   {
      return mailingStateProvince;
   }

   public void setMailingStateProvince(String mailingStateProvince)
   {
      this.mailingStateProvince = mailingStateProvince;
   }

   public String getMailingZipCode()
   {
      return mailingZipCode;
   }

   public void setMailingZipCode(String mailingZipCode)
   {
      this.mailingZipCode = mailingZipCode;
   }

   public String getMailingCountry()
   {
      return mailingCountry;
   }

   public void setMailingCountry(String mailingCountry)
   {
      this.mailingCountry = mailingCountry;
   }




   public String getCountryOfCitizenship()
   {
      return countryOfCitizenship;
   }

   public void setCountryOfCitizenship(String countryOfCitizenship)
   {
      this.countryOfCitizenship = countryOfCitizenship;
   }

   public String getCountryOfCitizenshipId()
   {
      return countryOfCitizenshipId;
   }

   public void setCountryOfCitizenshipId(String countryOfCitizenshipId)
   {
      this.countryOfCitizenshipId = countryOfCitizenshipId;
   }

   public String getMailingCountryOfResidence()
   {
      return mailingCountryOfResidence;
   }

   public void setMailingCountryOfResidence(String mailingCountryOfResidence)
   {
      this.mailingCountryOfResidence = mailingCountryOfResidence;
   }

   public String getMailingCountryOfResidenceId()
   {
      return mailingCountryOfResidenceId;
   }

   public void setMailingCountryOfResidenceId(String mailingCountryOfResidenceId)
   {
      this.mailingCountryOfResidenceId = mailingCountryOfResidenceId;
   }

   public String getCountryOfResidence()
   {
      return countryOfResidence;
   }

   public void setCountryOfResidence(String countryOfResidence)
   {
      this.countryOfResidence = countryOfResidence;
   }

   public String getCountryOfResidenceId()
   {
      return countryOfResidenceId;
   }

   public void setCountryOfResidenceId(String countryOfResidenceId)
   {
      this.countryOfResidenceId = countryOfResidenceId;
   }
   public List<Country> getCountries()
   {

      String[] locales = Locale.getISOCountries();
      List<Country> countries = new ArrayList<Country>();
      for (String countryCode : locales)
      {
         Locale obj = new Locale("", countryCode);
         //System.out.println("Country Code = " + obj.getCountryOfResidence() + ", Country Name = " + obj.getDisplayCountry());
         countries.add(new Country(obj.getCountry(), obj.getDisplayCountry()));
      }
      return countries;
   }

   public Date getDateOfBirth()
   {
      return dateOfBirth;
   }

   public void setDateOfBirth(Date dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   public String getGender(){
      return gender;
   }

   public void setGender(String gender){
      this.gender = gender;
   }

   public String getMaritalStatus(){
      return maritalStatus;
   }

   public void setMaritalStatus(String maritalStatus){
      this.maritalStatus = maritalStatus;
   }

   public String getDependents(){
      return dependents;
   }

   public void setDependents(String dependents){
      this.dependents = dependents;
   }

   public String getSocialSecurity(){
      return socialSecurity;
   }

   public void setSocialSecurity(String socialSecurity){
      this.socialSecurity = socialSecurity;
   }

   public String getEmploymentStatus()
   {
      return employmentStatus;
   }

   public void setEmploymentStatus(String employmentStatus)
   {
      this.employmentStatus = employmentStatus;
   }

   public String getOccupation()
   {
      return occupation;
   }

   public void setOccupation(String occupation)
   {
      this.occupation = occupation;
   }

   public String getEmployerName()
   {
      return employerName;
   }

   public void setEmployerName(String employerName)
   {
      this.employerName = employerName;
   }

   public String getNatureOfBusiness()
   {
      return natureOfBusiness;
   }

   public void setNatureOfBusiness(String natureOfBusiness)
   {
      this.natureOfBusiness = natureOfBusiness;
   }



   public String getEmployerAddress1()
   {
      return employerAddress1;
   }

   public void setEmployerAddress1(String employerAddress1)
   {
      this.employerAddress1 = employerAddress1;
   }

   public String getEmployerAddress2()
   {
      return employerAddress2;
   }

   public void setEmployerAddress2(String employerAddress2)
   {
      this.employerAddress2 = employerAddress2;
   }

   public String getEmployerCity()
   {
      return employerCity;
   }

   public void setEmployerCity(String employerCity)
   {
      this.employerCity = employerCity;
   }

   public String getEmployerStateCode()
   {
      return employerStateCode;
   }

   public void setEmployerStateCode(String employerStateCode)
   {
      this.employerStateCode = employerStateCode;
   }

   public String getEmployerStateName()
   {
      return employerStateName;
   }

   public void setEmployerStateName(String employerStateName)
   {
      this.employerStateName = employerStateName;
   }

   public String getEmployerStateProvince()
   {
      return employerStateProvince;
   }

   public void setEmployerStateProvince(String employerStateProvince)
   {
      this.employerStateProvince = employerStateProvince;
   }

   public String getEmployerZipCode()
   {
      return employerZipCode;
   }

   public void setEmployerZipCode(String employerZipCode)
   {
      this.employerZipCode = employerZipCode;
   }

   public String getEmployerCountry()
   {
      return employerCountry;
   }

   public void setEmployerCountry(String employerCountry)
   {
      this.employerCountry = employerCountry;
   }

   public String getEmployerCountryOfResidence()
   {
      return employerCountryOfResidence;
   }

   public void setEmployerCountryOfResidence(String employerCountryOfResidence)
   {
      this.employerCountryOfResidence = employerCountryOfResidence;
   }

   public String getEmployerCountryOfResidenceId()
   {
      return employerCountryOfResidenceId;
   }

   public void setEmployerCountryOfResidenceId(String employerCountryOfResidenceId)
   {
      this.employerCountryOfResidenceId = employerCountryOfResidenceId;
   }

}
