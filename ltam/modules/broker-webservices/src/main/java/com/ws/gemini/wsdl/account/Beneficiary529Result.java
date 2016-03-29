/**
 * Beneficiary529Result.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class Beneficiary529Result  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private int mailingAddressId;

    private java.lang.String beneficiaryName;

    private java.util.Calendar dateOfBirth;

    private boolean isSsn;

    private java.lang.String ssnOrTin;

    private short countryOfOriginTaxResidence;

    private short countryOfCitizenship;

    private org.apache.axis.types.UnsignedByte relationship;

    private org.apache.axis.types.UnsignedByte collegeEnteringAge;

    private java.util.Calendar collegeEnteringDate;

    private com.ws.gemini.wsdl.account.GenderEnum gender;

    private boolean isAddressSameAsAccountOwner;

    private java.lang.String nameLine1;

    private java.lang.String nameLine2;

    private java.lang.String addressLine;

    private java.lang.String city;

    private org.apache.axis.types.UnsignedByte state;

    private java.lang.String zip;

    private short countryCode;

    private org.apache.axis.types.UnsignedByte investmentOption;

    private java.util.Calendar lastInvestmentOptionChangeDate;

    private java.lang.String portfolio;

    private short ageBasedModelId;

    private int noOfAvaliableInvestmentChanges;

    private org.apache.axis.types.UnsignedByte investmentOptionType;

    private short targetPortfolioFundId;

    private int expectedEnrollmentYearId;

    private java.lang.String expectedEnrollmentYearDescription;

    private java.lang.String beneficiaryFullName;

    private java.lang.String ageBasedModelName;

    private org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator;

    private com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddress;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public Beneficiary529Result() {
    }

    public Beneficiary529Result(
           java.lang.String accountNumber,
           int mailingAddressId,
           java.lang.String beneficiaryName,
           java.util.Calendar dateOfBirth,
           boolean isSsn,
           java.lang.String ssnOrTin,
           short countryOfOriginTaxResidence,
           short countryOfCitizenship,
           org.apache.axis.types.UnsignedByte relationship,
           org.apache.axis.types.UnsignedByte collegeEnteringAge,
           java.util.Calendar collegeEnteringDate,
           com.ws.gemini.wsdl.account.GenderEnum gender,
           boolean isAddressSameAsAccountOwner,
           java.lang.String nameLine1,
           java.lang.String nameLine2,
           java.lang.String addressLine,
           java.lang.String city,
           org.apache.axis.types.UnsignedByte state,
           java.lang.String zip,
           short countryCode,
           org.apache.axis.types.UnsignedByte investmentOption,
           java.util.Calendar lastInvestmentOptionChangeDate,
           java.lang.String portfolio,
           short ageBasedModelId,
           int noOfAvaliableInvestmentChanges,
           org.apache.axis.types.UnsignedByte investmentOptionType,
           short targetPortfolioFundId,
           int expectedEnrollmentYearId,
           java.lang.String expectedEnrollmentYearDescription,
           java.lang.String beneficiaryFullName,
           java.lang.String ageBasedModelName,
           org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator,
           com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddress,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.accountNumber = accountNumber;
           this.mailingAddressId = mailingAddressId;
           this.beneficiaryName = beneficiaryName;
           this.dateOfBirth = dateOfBirth;
           this.isSsn = isSsn;
           this.ssnOrTin = ssnOrTin;
           this.countryOfOriginTaxResidence = countryOfOriginTaxResidence;
           this.countryOfCitizenship = countryOfCitizenship;
           this.relationship = relationship;
           this.collegeEnteringAge = collegeEnteringAge;
           this.collegeEnteringDate = collegeEnteringDate;
           this.gender = gender;
           this.isAddressSameAsAccountOwner = isAddressSameAsAccountOwner;
           this.nameLine1 = nameLine1;
           this.nameLine2 = nameLine2;
           this.addressLine = addressLine;
           this.city = city;
           this.state = state;
           this.zip = zip;
           this.countryCode = countryCode;
           this.investmentOption = investmentOption;
           this.lastInvestmentOptionChangeDate = lastInvestmentOptionChangeDate;
           this.portfolio = portfolio;
           this.ageBasedModelId = ageBasedModelId;
           this.noOfAvaliableInvestmentChanges = noOfAvaliableInvestmentChanges;
           this.investmentOptionType = investmentOptionType;
           this.targetPortfolioFundId = targetPortfolioFundId;
           this.expectedEnrollmentYearId = expectedEnrollmentYearId;
           this.expectedEnrollmentYearDescription = expectedEnrollmentYearDescription;
           this.beneficiaryFullName = beneficiaryFullName;
           this.ageBasedModelName = ageBasedModelName;
           this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
           this.mailingAddress = mailingAddress;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountNumber value for this Beneficiary529Result.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this Beneficiary529Result.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the mailingAddressId value for this Beneficiary529Result.
     * 
     * @return mailingAddressId
     */
    public int getMailingAddressId() {
        return mailingAddressId;
    }


    /**
     * Sets the mailingAddressId value for this Beneficiary529Result.
     * 
     * @param mailingAddressId
     */
    public void setMailingAddressId(int mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }


    /**
     * Gets the beneficiaryName value for this Beneficiary529Result.
     * 
     * @return beneficiaryName
     */
    public java.lang.String getBeneficiaryName() {
        return beneficiaryName;
    }


    /**
     * Sets the beneficiaryName value for this Beneficiary529Result.
     * 
     * @param beneficiaryName
     */
    public void setBeneficiaryName(java.lang.String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }


    /**
     * Gets the dateOfBirth value for this Beneficiary529Result.
     * 
     * @return dateOfBirth
     */
    public java.util.Calendar getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Sets the dateOfBirth value for this Beneficiary529Result.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(java.util.Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets the isSsn value for this Beneficiary529Result.
     * 
     * @return isSsn
     */
    public boolean isIsSsn() {
        return isSsn;
    }


    /**
     * Sets the isSsn value for this Beneficiary529Result.
     * 
     * @param isSsn
     */
    public void setIsSsn(boolean isSsn) {
        this.isSsn = isSsn;
    }


    /**
     * Gets the ssnOrTin value for this Beneficiary529Result.
     * 
     * @return ssnOrTin
     */
    public java.lang.String getSsnOrTin() {
        return ssnOrTin;
    }


    /**
     * Sets the ssnOrTin value for this Beneficiary529Result.
     * 
     * @param ssnOrTin
     */
    public void setSsnOrTin(java.lang.String ssnOrTin) {
        this.ssnOrTin = ssnOrTin;
    }


    /**
     * Gets the countryOfOriginTaxResidence value for this Beneficiary529Result.
     * 
     * @return countryOfOriginTaxResidence
     */
    public short getCountryOfOriginTaxResidence() {
        return countryOfOriginTaxResidence;
    }


    /**
     * Sets the countryOfOriginTaxResidence value for this Beneficiary529Result.
     * 
     * @param countryOfOriginTaxResidence
     */
    public void setCountryOfOriginTaxResidence(short countryOfOriginTaxResidence) {
        this.countryOfOriginTaxResidence = countryOfOriginTaxResidence;
    }


    /**
     * Gets the countryOfCitizenship value for this Beneficiary529Result.
     * 
     * @return countryOfCitizenship
     */
    public short getCountryOfCitizenship() {
        return countryOfCitizenship;
    }


    /**
     * Sets the countryOfCitizenship value for this Beneficiary529Result.
     * 
     * @param countryOfCitizenship
     */
    public void setCountryOfCitizenship(short countryOfCitizenship) {
        this.countryOfCitizenship = countryOfCitizenship;
    }


    /**
     * Gets the relationship value for this Beneficiary529Result.
     * 
     * @return relationship
     */
    public org.apache.axis.types.UnsignedByte getRelationship() {
        return relationship;
    }


    /**
     * Sets the relationship value for this Beneficiary529Result.
     * 
     * @param relationship
     */
    public void setRelationship(org.apache.axis.types.UnsignedByte relationship) {
        this.relationship = relationship;
    }


    /**
     * Gets the collegeEnteringAge value for this Beneficiary529Result.
     * 
     * @return collegeEnteringAge
     */
    public org.apache.axis.types.UnsignedByte getCollegeEnteringAge() {
        return collegeEnteringAge;
    }


    /**
     * Sets the collegeEnteringAge value for this Beneficiary529Result.
     * 
     * @param collegeEnteringAge
     */
    public void setCollegeEnteringAge(org.apache.axis.types.UnsignedByte collegeEnteringAge) {
        this.collegeEnteringAge = collegeEnteringAge;
    }


    /**
     * Gets the collegeEnteringDate value for this Beneficiary529Result.
     * 
     * @return collegeEnteringDate
     */
    public java.util.Calendar getCollegeEnteringDate() {
        return collegeEnteringDate;
    }


    /**
     * Sets the collegeEnteringDate value for this Beneficiary529Result.
     * 
     * @param collegeEnteringDate
     */
    public void setCollegeEnteringDate(java.util.Calendar collegeEnteringDate) {
        this.collegeEnteringDate = collegeEnteringDate;
    }


    /**
     * Gets the gender value for this Beneficiary529Result.
     * 
     * @return gender
     */
    public com.ws.gemini.wsdl.account.GenderEnum getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this Beneficiary529Result.
     * 
     * @param gender
     */
    public void setGender(com.ws.gemini.wsdl.account.GenderEnum gender) {
        this.gender = gender;
    }


    /**
     * Gets the isAddressSameAsAccountOwner value for this Beneficiary529Result.
     * 
     * @return isAddressSameAsAccountOwner
     */
    public boolean isIsAddressSameAsAccountOwner() {
        return isAddressSameAsAccountOwner;
    }


    /**
     * Sets the isAddressSameAsAccountOwner value for this Beneficiary529Result.
     * 
     * @param isAddressSameAsAccountOwner
     */
    public void setIsAddressSameAsAccountOwner(boolean isAddressSameAsAccountOwner) {
        this.isAddressSameAsAccountOwner = isAddressSameAsAccountOwner;
    }


    /**
     * Gets the nameLine1 value for this Beneficiary529Result.
     * 
     * @return nameLine1
     */
    public java.lang.String getNameLine1() {
        return nameLine1;
    }


    /**
     * Sets the nameLine1 value for this Beneficiary529Result.
     * 
     * @param nameLine1
     */
    public void setNameLine1(java.lang.String nameLine1) {
        this.nameLine1 = nameLine1;
    }


    /**
     * Gets the nameLine2 value for this Beneficiary529Result.
     * 
     * @return nameLine2
     */
    public java.lang.String getNameLine2() {
        return nameLine2;
    }


    /**
     * Sets the nameLine2 value for this Beneficiary529Result.
     * 
     * @param nameLine2
     */
    public void setNameLine2(java.lang.String nameLine2) {
        this.nameLine2 = nameLine2;
    }


    /**
     * Gets the addressLine value for this Beneficiary529Result.
     * 
     * @return addressLine
     */
    public java.lang.String getAddressLine() {
        return addressLine;
    }


    /**
     * Sets the addressLine value for this Beneficiary529Result.
     * 
     * @param addressLine
     */
    public void setAddressLine(java.lang.String addressLine) {
        this.addressLine = addressLine;
    }


    /**
     * Gets the city value for this Beneficiary529Result.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Beneficiary529Result.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the state value for this Beneficiary529Result.
     * 
     * @return state
     */
    public org.apache.axis.types.UnsignedByte getState() {
        return state;
    }


    /**
     * Sets the state value for this Beneficiary529Result.
     * 
     * @param state
     */
    public void setState(org.apache.axis.types.UnsignedByte state) {
        this.state = state;
    }


    /**
     * Gets the zip value for this Beneficiary529Result.
     * 
     * @return zip
     */
    public java.lang.String getZip() {
        return zip;
    }


    /**
     * Sets the zip value for this Beneficiary529Result.
     * 
     * @param zip
     */
    public void setZip(java.lang.String zip) {
        this.zip = zip;
    }


    /**
     * Gets the countryCode value for this Beneficiary529Result.
     * 
     * @return countryCode
     */
    public short getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this Beneficiary529Result.
     * 
     * @param countryCode
     */
    public void setCountryCode(short countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the investmentOption value for this Beneficiary529Result.
     * 
     * @return investmentOption
     */
    public org.apache.axis.types.UnsignedByte getInvestmentOption() {
        return investmentOption;
    }


    /**
     * Sets the investmentOption value for this Beneficiary529Result.
     * 
     * @param investmentOption
     */
    public void setInvestmentOption(org.apache.axis.types.UnsignedByte investmentOption) {
        this.investmentOption = investmentOption;
    }


    /**
     * Gets the lastInvestmentOptionChangeDate value for this Beneficiary529Result.
     * 
     * @return lastInvestmentOptionChangeDate
     */
    public java.util.Calendar getLastInvestmentOptionChangeDate() {
        return lastInvestmentOptionChangeDate;
    }


    /**
     * Sets the lastInvestmentOptionChangeDate value for this Beneficiary529Result.
     * 
     * @param lastInvestmentOptionChangeDate
     */
    public void setLastInvestmentOptionChangeDate(java.util.Calendar lastInvestmentOptionChangeDate) {
        this.lastInvestmentOptionChangeDate = lastInvestmentOptionChangeDate;
    }


    /**
     * Gets the portfolio value for this Beneficiary529Result.
     * 
     * @return portfolio
     */
    public java.lang.String getPortfolio() {
        return portfolio;
    }


    /**
     * Sets the portfolio value for this Beneficiary529Result.
     * 
     * @param portfolio
     */
    public void setPortfolio(java.lang.String portfolio) {
        this.portfolio = portfolio;
    }


    /**
     * Gets the ageBasedModelId value for this Beneficiary529Result.
     * 
     * @return ageBasedModelId
     */
    public short getAgeBasedModelId() {
        return ageBasedModelId;
    }


    /**
     * Sets the ageBasedModelId value for this Beneficiary529Result.
     * 
     * @param ageBasedModelId
     */
    public void setAgeBasedModelId(short ageBasedModelId) {
        this.ageBasedModelId = ageBasedModelId;
    }


    /**
     * Gets the noOfAvaliableInvestmentChanges value for this Beneficiary529Result.
     * 
     * @return noOfAvaliableInvestmentChanges
     */
    public int getNoOfAvaliableInvestmentChanges() {
        return noOfAvaliableInvestmentChanges;
    }


    /**
     * Sets the noOfAvaliableInvestmentChanges value for this Beneficiary529Result.
     * 
     * @param noOfAvaliableInvestmentChanges
     */
    public void setNoOfAvaliableInvestmentChanges(int noOfAvaliableInvestmentChanges) {
        this.noOfAvaliableInvestmentChanges = noOfAvaliableInvestmentChanges;
    }


    /**
     * Gets the investmentOptionType value for this Beneficiary529Result.
     * 
     * @return investmentOptionType
     */
    public org.apache.axis.types.UnsignedByte getInvestmentOptionType() {
        return investmentOptionType;
    }


    /**
     * Sets the investmentOptionType value for this Beneficiary529Result.
     * 
     * @param investmentOptionType
     */
    public void setInvestmentOptionType(org.apache.axis.types.UnsignedByte investmentOptionType) {
        this.investmentOptionType = investmentOptionType;
    }


    /**
     * Gets the targetPortfolioFundId value for this Beneficiary529Result.
     * 
     * @return targetPortfolioFundId
     */
    public short getTargetPortfolioFundId() {
        return targetPortfolioFundId;
    }


    /**
     * Sets the targetPortfolioFundId value for this Beneficiary529Result.
     * 
     * @param targetPortfolioFundId
     */
    public void setTargetPortfolioFundId(short targetPortfolioFundId) {
        this.targetPortfolioFundId = targetPortfolioFundId;
    }


    /**
     * Gets the expectedEnrollmentYearId value for this Beneficiary529Result.
     * 
     * @return expectedEnrollmentYearId
     */
    public int getExpectedEnrollmentYearId() {
        return expectedEnrollmentYearId;
    }


    /**
     * Sets the expectedEnrollmentYearId value for this Beneficiary529Result.
     * 
     * @param expectedEnrollmentYearId
     */
    public void setExpectedEnrollmentYearId(int expectedEnrollmentYearId) {
        this.expectedEnrollmentYearId = expectedEnrollmentYearId;
    }


    /**
     * Gets the expectedEnrollmentYearDescription value for this Beneficiary529Result.
     * 
     * @return expectedEnrollmentYearDescription
     */
    public java.lang.String getExpectedEnrollmentYearDescription() {
        return expectedEnrollmentYearDescription;
    }


    /**
     * Sets the expectedEnrollmentYearDescription value for this Beneficiary529Result.
     * 
     * @param expectedEnrollmentYearDescription
     */
    public void setExpectedEnrollmentYearDescription(java.lang.String expectedEnrollmentYearDescription) {
        this.expectedEnrollmentYearDescription = expectedEnrollmentYearDescription;
    }


    /**
     * Gets the beneficiaryFullName value for this Beneficiary529Result.
     * 
     * @return beneficiaryFullName
     */
    public java.lang.String getBeneficiaryFullName() {
        return beneficiaryFullName;
    }


    /**
     * Sets the beneficiaryFullName value for this Beneficiary529Result.
     * 
     * @param beneficiaryFullName
     */
    public void setBeneficiaryFullName(java.lang.String beneficiaryFullName) {
        this.beneficiaryFullName = beneficiaryFullName;
    }


    /**
     * Gets the ageBasedModelName value for this Beneficiary529Result.
     * 
     * @return ageBasedModelName
     */
    public java.lang.String getAgeBasedModelName() {
        return ageBasedModelName;
    }


    /**
     * Sets the ageBasedModelName value for this Beneficiary529Result.
     * 
     * @param ageBasedModelName
     */
    public void setAgeBasedModelName(java.lang.String ageBasedModelName) {
        this.ageBasedModelName = ageBasedModelName;
    }


    /**
     * Gets the portfolioAllocatorIndicator value for this Beneficiary529Result.
     * 
     * @return portfolioAllocatorIndicator
     */
    public org.apache.axis.types.UnsignedByte getPortfolioAllocatorIndicator() {
        return portfolioAllocatorIndicator;
    }


    /**
     * Sets the portfolioAllocatorIndicator value for this Beneficiary529Result.
     * 
     * @param portfolioAllocatorIndicator
     */
    public void setPortfolioAllocatorIndicator(org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
        this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }


    /**
     * Gets the mailingAddress value for this Beneficiary529Result.
     * 
     * @return mailingAddress
     */
    public com.ws.gemini.wsdl.account.MailingAddressesResult getMailingAddress() {
        return mailingAddress;
    }


    /**
     * Sets the mailingAddress value for this Beneficiary529Result.
     * 
     * @param mailingAddress
     */
    public void setMailingAddress(com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


    /**
     * Gets the errorStatus value for this Beneficiary529Result.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this Beneficiary529Result.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Beneficiary529Result)) return false;
        Beneficiary529Result other = (Beneficiary529Result) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.mailingAddressId == other.getMailingAddressId() &&
            ((this.beneficiaryName==null && other.getBeneficiaryName()==null) || 
             (this.beneficiaryName!=null &&
              this.beneficiaryName.equals(other.getBeneficiaryName()))) &&
            ((this.dateOfBirth==null && other.getDateOfBirth()==null) || 
             (this.dateOfBirth!=null &&
              this.dateOfBirth.equals(other.getDateOfBirth()))) &&
            this.isSsn == other.isIsSsn() &&
            ((this.ssnOrTin==null && other.getSsnOrTin()==null) || 
             (this.ssnOrTin!=null &&
              this.ssnOrTin.equals(other.getSsnOrTin()))) &&
            this.countryOfOriginTaxResidence == other.getCountryOfOriginTaxResidence() &&
            this.countryOfCitizenship == other.getCountryOfCitizenship() &&
            ((this.relationship==null && other.getRelationship()==null) || 
             (this.relationship!=null &&
              this.relationship.equals(other.getRelationship()))) &&
            ((this.collegeEnteringAge==null && other.getCollegeEnteringAge()==null) || 
             (this.collegeEnteringAge!=null &&
              this.collegeEnteringAge.equals(other.getCollegeEnteringAge()))) &&
            ((this.collegeEnteringDate==null && other.getCollegeEnteringDate()==null) || 
             (this.collegeEnteringDate!=null &&
              this.collegeEnteringDate.equals(other.getCollegeEnteringDate()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            this.isAddressSameAsAccountOwner == other.isIsAddressSameAsAccountOwner() &&
            ((this.nameLine1==null && other.getNameLine1()==null) || 
             (this.nameLine1!=null &&
              this.nameLine1.equals(other.getNameLine1()))) &&
            ((this.nameLine2==null && other.getNameLine2()==null) || 
             (this.nameLine2!=null &&
              this.nameLine2.equals(other.getNameLine2()))) &&
            ((this.addressLine==null && other.getAddressLine()==null) || 
             (this.addressLine!=null &&
              this.addressLine.equals(other.getAddressLine()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.zip==null && other.getZip()==null) || 
             (this.zip!=null &&
              this.zip.equals(other.getZip()))) &&
            this.countryCode == other.getCountryCode() &&
            ((this.investmentOption==null && other.getInvestmentOption()==null) || 
             (this.investmentOption!=null &&
              this.investmentOption.equals(other.getInvestmentOption()))) &&
            ((this.lastInvestmentOptionChangeDate==null && other.getLastInvestmentOptionChangeDate()==null) || 
             (this.lastInvestmentOptionChangeDate!=null &&
              this.lastInvestmentOptionChangeDate.equals(other.getLastInvestmentOptionChangeDate()))) &&
            ((this.portfolio==null && other.getPortfolio()==null) || 
             (this.portfolio!=null &&
              this.portfolio.equals(other.getPortfolio()))) &&
            this.ageBasedModelId == other.getAgeBasedModelId() &&
            this.noOfAvaliableInvestmentChanges == other.getNoOfAvaliableInvestmentChanges() &&
            ((this.investmentOptionType==null && other.getInvestmentOptionType()==null) || 
             (this.investmentOptionType!=null &&
              this.investmentOptionType.equals(other.getInvestmentOptionType()))) &&
            this.targetPortfolioFundId == other.getTargetPortfolioFundId() &&
            this.expectedEnrollmentYearId == other.getExpectedEnrollmentYearId() &&
            ((this.expectedEnrollmentYearDescription==null && other.getExpectedEnrollmentYearDescription()==null) || 
             (this.expectedEnrollmentYearDescription!=null &&
              this.expectedEnrollmentYearDescription.equals(other.getExpectedEnrollmentYearDescription()))) &&
            ((this.beneficiaryFullName==null && other.getBeneficiaryFullName()==null) || 
             (this.beneficiaryFullName!=null &&
              this.beneficiaryFullName.equals(other.getBeneficiaryFullName()))) &&
            ((this.ageBasedModelName==null && other.getAgeBasedModelName()==null) || 
             (this.ageBasedModelName!=null &&
              this.ageBasedModelName.equals(other.getAgeBasedModelName()))) &&
            ((this.portfolioAllocatorIndicator==null && other.getPortfolioAllocatorIndicator()==null) || 
             (this.portfolioAllocatorIndicator!=null &&
              this.portfolioAllocatorIndicator.equals(other.getPortfolioAllocatorIndicator()))) &&
            ((this.mailingAddress==null && other.getMailingAddress()==null) || 
             (this.mailingAddress!=null &&
              this.mailingAddress.equals(other.getMailingAddress()))) &&
            ((this.errorStatus==null && other.getErrorStatus()==null) || 
             (this.errorStatus!=null &&
              this.errorStatus.equals(other.getErrorStatus())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getMailingAddressId();
        if (getBeneficiaryName() != null) {
            _hashCode += getBeneficiaryName().hashCode();
        }
        if (getDateOfBirth() != null) {
            _hashCode += getDateOfBirth().hashCode();
        }
        _hashCode += (isIsSsn() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSsnOrTin() != null) {
            _hashCode += getSsnOrTin().hashCode();
        }
        _hashCode += getCountryOfOriginTaxResidence();
        _hashCode += getCountryOfCitizenship();
        if (getRelationship() != null) {
            _hashCode += getRelationship().hashCode();
        }
        if (getCollegeEnteringAge() != null) {
            _hashCode += getCollegeEnteringAge().hashCode();
        }
        if (getCollegeEnteringDate() != null) {
            _hashCode += getCollegeEnteringDate().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        _hashCode += (isIsAddressSameAsAccountOwner() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNameLine1() != null) {
            _hashCode += getNameLine1().hashCode();
        }
        if (getNameLine2() != null) {
            _hashCode += getNameLine2().hashCode();
        }
        if (getAddressLine() != null) {
            _hashCode += getAddressLine().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getZip() != null) {
            _hashCode += getZip().hashCode();
        }
        _hashCode += getCountryCode();
        if (getInvestmentOption() != null) {
            _hashCode += getInvestmentOption().hashCode();
        }
        if (getLastInvestmentOptionChangeDate() != null) {
            _hashCode += getLastInvestmentOptionChangeDate().hashCode();
        }
        if (getPortfolio() != null) {
            _hashCode += getPortfolio().hashCode();
        }
        _hashCode += getAgeBasedModelId();
        _hashCode += getNoOfAvaliableInvestmentChanges();
        if (getInvestmentOptionType() != null) {
            _hashCode += getInvestmentOptionType().hashCode();
        }
        _hashCode += getTargetPortfolioFundId();
        _hashCode += getExpectedEnrollmentYearId();
        if (getExpectedEnrollmentYearDescription() != null) {
            _hashCode += getExpectedEnrollmentYearDescription().hashCode();
        }
        if (getBeneficiaryFullName() != null) {
            _hashCode += getBeneficiaryFullName().hashCode();
        }
        if (getAgeBasedModelName() != null) {
            _hashCode += getAgeBasedModelName().hashCode();
        }
        if (getPortfolioAllocatorIndicator() != null) {
            _hashCode += getPortfolioAllocatorIndicator().hashCode();
        }
        if (getMailingAddress() != null) {
            _hashCode += getMailingAddress().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Beneficiary529Result.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Result"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddressId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateOfBirth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateOfBirth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSsn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsSsn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ssnOrTin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SsnOrTin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfOriginTaxResidence");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CountryOfOriginTaxResidence"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfCitizenship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CountryOfCitizenship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationship");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Relationship"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collegeEnteringAge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CollegeEnteringAge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collegeEnteringDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CollegeEnteringDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GenderEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAddressSameAsAccountOwner");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsAddressSameAsAccountOwner"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameLine1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameLine1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameLine2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameLine2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AddressLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Zip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("investmentOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InvestmentOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastInvestmentOptionChangeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LastInvestmentOptionChangeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Portfolio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ageBasedModelId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AgeBasedModelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfAvaliableInvestmentChanges");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfAvaliableInvestmentChanges"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("investmentOptionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InvestmentOptionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("targetPortfolioFundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TargetPortfolioFundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedEnrollmentYearId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExpectedEnrollmentYearId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expectedEnrollmentYearDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExpectedEnrollmentYearDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryFullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryFullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ageBasedModelName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AgeBasedModelName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioAllocatorIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PortfolioAllocatorIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CallStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
