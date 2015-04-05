package com.invessence.data.consumer;

import java.util.*;

import com.invessence.data.Country;

public class ClientData
{
   private Long acctnum = null;
   private Long logonid = null;
   private String fullName = null;
   private String prefix = null;
   private String firstName = null;
   private String middleName = null;
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
   private boolean checked;
   private String checkboxId;



   private String beneficiaryFullName = null;
   private String beneficiaryPrefix = null;
   private String beneficiaryFirstName = null;
   private String beneficiaryMiddleInitial = null;
   private String beneficiaryLastName = null;
   private String beneficiarySuffix = null;

   private String beneficiaryAddress1 = null;
   private String beneficiaryAddress2 = null;
   private String beneficiaryCity = null;
   private String beneficiaryStateCode = null;
   private String beneficiaryStateName = null;
   private String beneficiaryStateProvince = null;
   private String beneficiaryZipCode = null;
   private String beneficiaryCountry = null;
   private String beneficiarySocialSecurity;
   private String beneficiaryTypeOfID = null;
   private String beneficiaryCountryOfIssuance = null;
   private String beneficiaryIDNumber = null;
   private Date beneficiaryDateOfBirth = null;
   private String beneficiaryRelationship = null;

private boolean consultingchecked;
   private String consultingcheckboxId;
   private String consultingpai;
   private String consultingdesc;

   private boolean disabilitychecked;
   private String disabilitycheckboxId;
   private String disabilitypai;
   private String disabilitydesc;

   private boolean inheritancechecked;
   private String inheritancecheckboxId;
   private String inheritancepai;
   private String inheritancedesc;

   private boolean otherchecked;
   private String othercheckboxId;
   private String otherpai;
   private String otherdesc;

   private boolean realestatechecked;
   private String realestatecheckboxId;
   private String realestatepai;
   private String realestatedesc;

   private boolean rentalchecked;
   private String rentalcheckboxId;
   private String rentalpai;
   private String rentaldesc;

   private boolean severancechecked;
   private String severancecheckboxId;
   private String severancepai;
   private String severancedesc;

   private boolean spousechecked;
   private String spousecheckboxId;
   private String spousepai;
   private String spousedesc;

   private boolean tradechecked;
   private String tradecheckboxId;
   private String tradepai;
   private String tradedesc;

   private boolean unemploymentchecked;
   private String unemploymentcheckboxId;
   private String unemploymentpai;
   private String unemploymentdesc;

   private boolean regulatorychecked;
   private String regulatorycheckboxId;
   private String regulatoryemployer;

   private boolean regulatorymchecked;
   private String regulatorymcheckboxId;
   private boolean regulatorydchecked;
   private String regulatorydcheckboxId;
   private boolean regulatorycchecked;
   private String regulatoryccheckboxId;
   private boolean regulatorypchecked;
   private String regulatorypcheckboxId;

   private String editordtext;
   private String editorctext;
   private String editorptext;


   public Long getAcctnum()
   {
      return acctnum;
   }

   public void setAcctnum(Long acctnum)
   {
      this.acctnum = acctnum;
   }

   public Long getLogonid()
   {
      return logonid;
   }

   public void setLogonid(Long logonid)
   {
      this.logonid = logonid;
   }

   public String getMiddleName()
   {
      return middleName;
   }

   public void setMiddleName(String middleName)
   {
      this.middleName = middleName;
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

   public boolean isChecked() {
      return checked;
   }

   public void setChecked(boolean checked) {
      System.out.println("checked :" + checked);
      this.checked = checked;
   }

   public String getCheckboxId()
   {
      return checkboxId;
   }

   public void setCheckboxId(String checkboxId)
   {
      this.checkboxId = checkboxId;
   }


   public String getBeneficiaryFullName()
   {
      return beneficiaryFullName;
   }

   public void setBeneficiaryFullName(String beneficiaryFullName)
   {
      this.beneficiaryFullName = beneficiaryFullName;
   }

   public String getBeneficiaryPrefix()
   {
      return beneficiaryPrefix;
   }

   public void setBeneficiaryPrefix(String beneficiaryPrefix)
   {
      this.beneficiaryPrefix = beneficiaryPrefix;
   }

   public String getBeneficiaryFirstName()
   {
      return beneficiaryFirstName;
   }

   public void setBeneficiaryFirstName(String beneficiaryFirstName)
   {
      this.beneficiaryFirstName = beneficiaryFirstName;
   }

   public String getBeneficiaryMiddleInitial()
   {
      return beneficiaryMiddleInitial;
   }

   public void setBeneficiaryMiddleInitial(String beneficiaryMiddleInitial)
   {
      this.beneficiaryMiddleInitial = beneficiaryMiddleInitial;
   }

   public String getBeneficiaryLastName()
   {
      return beneficiaryLastName;
   }

   public void setBeneficiaryLastName(String beneficiaryLastName)
   {
      this.beneficiaryLastName = beneficiaryLastName;
   }

   public String getBeneficiarySuffix()
   {
      return beneficiarySuffix;
   }

   public void setBeneficiarySuffix(String beneficiarySuffix)
   {
      this.beneficiarySuffix = beneficiarySuffix;
   }

   public String getBeneficiaryAddress1()
   {
      return beneficiaryAddress1;
   }

   public void setBeneficiaryAddress1(String beneficiaryAddress1)
   {
      this.beneficiaryAddress1 = beneficiaryAddress1;
   }

   public String getBeneficiaryAddress2()
   {
      return beneficiaryAddress2;
   }

   public void setBeneficiaryAddress2(String beneficiaryAddress2)
   {
      this.beneficiaryAddress2 = beneficiaryAddress2;
   }

   public String getBeneficiaryCity()
   {
      return beneficiaryCity;
   }

   public void setBeneficiaryCity(String beneficiaryCity)
   {
      this.beneficiaryCity = beneficiaryCity;
   }

   public String getBeneficiaryStateCode()
   {
      return beneficiaryStateCode;
   }

   public void setBeneficiaryStateCode(String beneficiaryStateCode)
   {
      this.beneficiaryStateCode = beneficiaryStateCode;
   }

   public String getBeneficiaryStateName()
   {
      return beneficiaryStateName;
   }

   public void setBeneficiaryStateName(String beneficiaryStateName)
   {
      this.beneficiaryStateName = beneficiaryStateName;
   }

   public String getBeneficiaryStateProvince()
   {
      return beneficiaryStateProvince;
   }

   public void setBeneficiaryStateProvince(String beneficiaryStateProvince)
   {
      this.beneficiaryStateProvince = beneficiaryStateProvince;
   }

   public String getBeneficiaryZipCode()
   {
      return beneficiaryZipCode;
   }

   public void setBeneficiaryZipCode(String beneficiaryZipCode)
   {
      this.beneficiaryZipCode = beneficiaryZipCode;
   }

   public String getBeneficiaryCountry()
   {
      return beneficiaryCountry;
   }

   public void setBeneficiaryCountry(String beneficiaryCountry)
   {
      this.beneficiaryCountry = beneficiaryCountry;
   }

   public String getBeneficiarySocialSecurity(){
      return beneficiarySocialSecurity;
   }

   public void setBeneficiarySocialSecurity(String beneficiarySocialSecurity){
      this.beneficiarySocialSecurity = beneficiarySocialSecurity;
   }

   public String getBeneficiaryTypeOfID()
   {
      return beneficiaryTypeOfID;
   }

   public void setBeneficiaryTypeOfID(String beneficiaryTypeOfID)
   {
      this.beneficiaryTypeOfID = beneficiaryTypeOfID;
   }

   public String getBeneficiaryCountryOfIssuance()
   {
      return beneficiaryCountryOfIssuance;
   }

   public void setBeneficiaryCountryOfIssuance(String beneficiaryCountryOfIssuance)
   {
      this.beneficiaryCountryOfIssuance = beneficiaryCountryOfIssuance;
   }

   public String getBeneficiaryIDNumber()
   {
      return beneficiaryIDNumber;
   }

   public void setBeneficiaryIDNumber(String beneficiaryIDNumber)
   {
      this.beneficiaryIDNumber = beneficiaryIDNumber;
   }


   public Date getBeneficiaryDateOfBirth()
   {
      return beneficiaryDateOfBirth;
   }

   public void setBeneficiaryDateOfBirth(Date beneficiaryDateOfBirth)
   {
      this.beneficiaryDateOfBirth = beneficiaryDateOfBirth;
   }

   public String getBeneficiaryRelationship()
   {
      return beneficiaryRelationship;
   }

   public void setBeneficiaryRelationship(String beneficiaryRelationship)
   {
      this.beneficiaryRelationship = beneficiaryRelationship;
   }

    public boolean isConsultingchecked() {
      return consultingchecked;
   }

   public void setConsultingchecked(boolean consultingchecked) {
      System.out.println("consulting checked :" + consultingchecked);
      this.consultingchecked = consultingchecked;
   }

   public String getConsultingcheckboxId()
   {
      return consultingcheckboxId;
   }

   public void setConsultingcheckboxId(String consultingcheckboxId)
   {
      this.consultingcheckboxId = consultingcheckboxId;
   }

   public String getConsultingpai()
   {
      return consultingpai;
   }

   public void setConsultingpai(String consultingpai)
   {
      this.consultingpai = consultingpai;
   }

   public String getConsultingdesc()
   {
      return consultingdesc;
   }

   public void setConsultingdesc(String consultingdesc)
   {
      this.consultingdesc = consultingdesc;
   }


   public boolean isDisabilitychecked() {
      return disabilitychecked;
   }

   public void setDisabilitychecked(boolean disabilitychecked) {
      System.out.println("disability checked :" + disabilitychecked);
      this.disabilitychecked = disabilitychecked;
   }

   public String getDisabilitycheckboxId()
   {
      return disabilitycheckboxId;
   }

   public void setDisabilitycheckboxId(String disabilitycheckboxId)
   {
      this.disabilitycheckboxId = disabilitycheckboxId;
   }

   public String getDisabilitypai()
   {
      return disabilitypai;
   }

   public void setDisabilitypai(String disabilitypai)
   {
      this.disabilitypai = disabilitypai;
   }

   public String getDisabilitydesc()
   {
      return disabilitydesc;
   }

   public void setDisabilitydesc(String disabilitydesc)
   {
      this.disabilitydesc = disabilitydesc;
   }

   public boolean isInheritancechecked() {
      return inheritancechecked;
   }

   public void setInheritancechecked(boolean inheritancechecked) {
      System.out.println("inheritance checked :" + inheritancechecked);
      this.inheritancechecked = inheritancechecked;
   }

   public String getInheritancecheckboxId()
   {
      return inheritancecheckboxId;
   }

   public void setInheritancecheckboxId(String inheritancecheckboxId)
   {
      this.inheritancecheckboxId = inheritancecheckboxId;
   }

   public String getInheritancepai()
   {
      return inheritancepai;
   }

   public void setInheritancepai(String inheritancepai)
   {
      this.inheritancepai = inheritancepai;
   }

   public String getInheritancedesc()
   {
      return inheritancedesc;
   }

   public void setInheritancedesc(String inheritancedesc)
   {
      this.inheritancedesc = inheritancedesc;
   }

   public boolean isOtherchecked() {
      return otherchecked;
   }

   public void setOtherchecked(boolean otherchecked) {
      System.out.println("other checked :" + otherchecked);
      this.otherchecked = otherchecked;
   }

   public String getOthercheckboxId()
   {
      return othercheckboxId;
   }

   public void setOthercheckboxId(String othercheckboxId)
   {
      this.othercheckboxId = othercheckboxId;
   }

   public String getOtherpai()
   {
      return otherpai;
   }

   public void setOtherpai(String otherpai)
   {
      this.otherpai = otherpai;
   }

   public String getOtherdesc()
   {
      return otherdesc;
   }

   public void setOtherdesc(String otherdesc)
   {
      this.otherdesc = otherdesc;
   }

   public boolean isRealestatechecked() {
      return realestatechecked;
   }

   public void setRealestatechecked(boolean realestatechecked) {
      System.out.println("real estate checked :" + realestatechecked);
      this.realestatechecked = realestatechecked;
   }

   public String getRealestatecheckboxId()
   {
      return realestatecheckboxId;
   }

   public void setRealestatecheckboxId(String realestatecheckboxId)
   {
      this.realestatecheckboxId = realestatecheckboxId;
   }

   public String getRealestatepai()
   {
      return realestatepai;
   }

   public void setRealestatepai(String realestatepai)
   {
      this.realestatepai = realestatepai;
   }

   public String getRealestatedesc()
   {
      return realestatedesc;
   }

   public void setRealestatedesc(String realestatedesc)
   {
      this.realestatedesc = realestatedesc;
   }

   public boolean isRentalchecked() {
      return rentalchecked;
   }

   public void setRentalchecked(boolean rentalchecked) {
      System.out.println("rental checked :" + rentalchecked);
      this.rentalchecked = rentalchecked;
   }

   public String getRentalcheckboxId()
   {
      return rentalcheckboxId;
   }

   public void setRentalcheckboxId(String rentalcheckboxId)
   {
      this.rentalcheckboxId = rentalcheckboxId;
   }

   public String getRentalpai()
   {
      return rentalpai;
   }

   public void setRentalpai(String rentalpai)
   {
      this.rentalpai = rentalpai;
   }

   public String getRentaldesc()
   {
      return rentaldesc;
   }

   public void setRentaldesc(String rentaldesc)
   {
      this.rentaldesc = rentaldesc;
   }

   public boolean isSeverancechecked() {
      return severancechecked;
   }

   public void setSeverancechecked(boolean severancechecked) {
      System.out.println("severance checked :" + severancechecked);
      this.severancechecked = severancechecked;
   }

   public String getSeverancecheckboxId()
   {
      return severancecheckboxId;
   }

   public void setSeverancechecked(String severancecheckboxId)
   {
      this.severancecheckboxId = severancecheckboxId;
   }

   public String getSeverancepai()
   {
      return severancepai;
   }

   public void setSeverancepai(String severancepai)
   {
      this.severancepai = severancepai;
   }

   public String getSeverancedesc()
   {
      return severancedesc;
   }

   public void setSeverancedesc(String severancedesc)
   {
      this.severancedesc = severancedesc;
   }

   public boolean isSpousechecked() {
      return spousechecked;
   }

   public void setSpousechecked(boolean spousechecked) {
      System.out.println("spouse checked :" + spousechecked);
      this.spousechecked = spousechecked;
   }

   public String getSpousecheckboxId()
   {
      return spousecheckboxId;
   }

   public void setSpousecheckboxId(String spousecheckboxId)
   {
      this.spousecheckboxId = spousecheckboxId;
   }

   public String getSpousepai()
   {
      return spousepai;
   }

   public void setSpousepai(String spousepai)
   {
      this.spousepai = spousepai;
   }

   public String getSpousedesc()
   {
      return spousedesc;
   }

   public void setSpousedesc(String spousedesc)
   {
      this.spousedesc = spousedesc;
   }

   public boolean isTradechecked() {
      return tradechecked;
   }

   public void setTradechecked(boolean tradechecked) {
      System.out.println("trade checked :" + tradechecked);
      this.tradechecked = tradechecked;
   }

   public String getTradecheckboxId()
   {
      return tradecheckboxId;
   }

   public void setTradecheckboxId(String tradecheckboxId)
   {
      this.tradecheckboxId = tradecheckboxId;
   }

   public String getTradepai()
   {
      return tradepai;
   }

   public void setTradepai(String tradepai)
   {
      this.tradepai = tradepai;
   }

   public String getTradedesc()
   {
      return tradedesc;
   }

   public void setTradedesc(String tradedesc)
   {
      this.tradedesc = tradedesc;
   }

   public boolean isUnemploymentchecked() {
      return unemploymentchecked;
   }

   public void setUnemploymentchecked(boolean unemploymentchecked) {
      System.out.println("unemployment checked :" + unemploymentchecked);
      this.unemploymentchecked = unemploymentchecked;
   }

   public String getUnemploymentcheckboxId()
   {
      return unemploymentcheckboxId;
   }

   public void setUnemploymentcheckboxId(String unemploymentcheckboxId)
   {
      this.unemploymentcheckboxId = unemploymentcheckboxId;
   }

   public String getUnemploymentpai()
   {
      return unemploymentpai;
   }

   public void setUnemploymentpai(String unemploymentpai)
   {
      this.unemploymentpai = unemploymentpai;
   }

   public String getUnemploymentdesc()
   {
      return unemploymentdesc;
   }

   public void setUnemploymentdesc(String unemploymentdesc)
   {
      this.unemploymentdesc = unemploymentdesc;
   }


   public boolean isRegulatorychecked() {
      return regulatorychecked;
   }

   public void setRegulatorychecked(boolean regulatorychecked) {
      System.out.println("regulatory checked :" + regulatorychecked);
      this.regulatorychecked = regulatorychecked;
   }

   public String getRegulatorycheckboxId()
   {
      return regulatorycheckboxId;
   }

   public void setRegulatorycheckboxId(String regulatorycheckboxId)
   {
      this.regulatorycheckboxId = regulatorycheckboxId;
   }

   public String getRegulatoryemployer()
   {
      return regulatoryemployer;
   }

   public void setRegulatoryemployer(String regulatoryemployer)
   {
      this.regulatoryemployer = regulatoryemployer;
   }


   public boolean isRegulatorymchecked() {
      return regulatorymchecked;
   }

   public void setRegulatorymchecked(boolean regulatorymchecked) {
      System.out.println("regulatory-m- checked :" + regulatorymchecked);
      this.regulatorymchecked = regulatorymchecked;
   }

   public String getRegulatorymcheckboxId()
   {
      return regulatorymcheckboxId;
   }

   public void regulatorymcheckboxId(String regulatorymcheckboxId) {
      this.regulatorymcheckboxId = regulatorymcheckboxId;
   }

   public boolean isRegulatorydchecked() {
      return regulatorydchecked;
   }

   public void setRegulatorydchecked(boolean regulatorydchecked) {
      System.out.println("regulatory-d- checked :" + regulatorydchecked);
      this.regulatorydchecked = regulatorydchecked;
   }

   public String getRegulatorydcheckboxId()
   {
      return regulatorydcheckboxId;
   }

   public void setRegulatorydcheckboxId(String regulatorydcheckboxId){
      this.regulatorydcheckboxId = regulatorydcheckboxId;
   }


   public boolean isRegulatorycchecked() {
      return regulatorycchecked;
   }

   public void setRegulatorycchecked(boolean regulatorycchecked) {
      System.out.println("regulatory-c- checked :" + regulatorycchecked);
      this.regulatorycchecked = regulatorycchecked;
   }

   public String getRegulatoryccheckboxId()
   {
      return regulatoryccheckboxId;
   }

   public void setRegulatoryccheckboxId(String regulatoryccheckboxId){
      this.regulatoryccheckboxId = regulatoryccheckboxId;
   }


   public boolean isRegulatorypchecked() {
      return regulatorypchecked;
   }

   public void setRegulatorypchecked(boolean regulatorypchecked) {
      System.out.println("regulatory-p- checked :" + regulatorypchecked);
      this.regulatorypchecked = regulatorypchecked;
   }

   public String getRegulatorypcheckboxId()
   {
      return regulatorypcheckboxId;
   }

   public void setRegulatorypcheckboxId(String regulatorypcheckboxId){
      this.regulatorypcheckboxId = regulatorypcheckboxId;
   }


}
