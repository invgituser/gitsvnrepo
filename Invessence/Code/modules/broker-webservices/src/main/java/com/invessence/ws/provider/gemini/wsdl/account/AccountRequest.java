/**
 * AccountRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private org.apache.axis.types.UnsignedByte accountType;

    private java.lang.String customerName;

    private java.lang.String accountName;

    private boolean isBlueSkyState;

    private java.lang.String blueSkyState;

    private boolean isSsn;

    private java.lang.String ssnOrTin;

    private java.util.Calendar dateOfBirth;

    private org.apache.axis.types.UnsignedByte backupWithholdingIndicator;

    private int accountRepId;

    private java.lang.String shareClassId;

    private java.lang.String userId;

    private int accountAdvisorId;

    private org.apache.axis.types.UnsignedByte tradingEntity;

    private short countryOfOriginParm;

    private boolean isBlueSkyExempt;

    private java.util.Calendar jointDateOfBirth;

    private int mailingAddressId;

    private java.lang.String jointSsnOrTin;

    private org.apache.axis.types.UnsignedByte gender;

    private org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator;

    private com.invessence.ws.provider.gemini.wsdl.account.AccountMessageCollectionRequest accountMessages;

    private com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsRequest assetAllocationModel;

    private com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentRequest automaticInvestments;

    private com.invessence.ws.provider.gemini.wsdl.account.AchPayeeCollectionRequest bankAccounts;

    private com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Request beneficiary;

    private com.invessence.ws.provider.gemini.wsdl.account.MailingAddressesRequest mailingAddress;

    private com.invessence.ws.provider.gemini.wsdl.account.SuccessorBeneficiaryCollectionRequest successors;

    public AccountRequest() {
    }

    public AccountRequest(
           java.lang.String accountNumber,
           org.apache.axis.types.UnsignedByte accountType,
           java.lang.String customerName,
           java.lang.String accountName,
           boolean isBlueSkyState,
           java.lang.String blueSkyState,
           boolean isSsn,
           java.lang.String ssnOrTin,
           java.util.Calendar dateOfBirth,
           org.apache.axis.types.UnsignedByte backupWithholdingIndicator,
           int accountRepId,
           java.lang.String shareClassId,
           java.lang.String userId,
           int accountAdvisorId,
           org.apache.axis.types.UnsignedByte tradingEntity,
           short countryOfOriginParm,
           boolean isBlueSkyExempt,
           java.util.Calendar jointDateOfBirth,
           int mailingAddressId,
           java.lang.String jointSsnOrTin,
           org.apache.axis.types.UnsignedByte gender,
           org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator,
           com.invessence.ws.provider.gemini.wsdl.account.AccountMessageCollectionRequest accountMessages,
           com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsRequest assetAllocationModel,
           com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentRequest automaticInvestments,
           com.invessence.ws.provider.gemini.wsdl.account.AchPayeeCollectionRequest bankAccounts,
           com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Request beneficiary,
           com.invessence.ws.provider.gemini.wsdl.account.MailingAddressesRequest mailingAddress,
           com.invessence.ws.provider.gemini.wsdl.account.SuccessorBeneficiaryCollectionRequest successors) {
           this.accountNumber = accountNumber;
           this.accountType = accountType;
           this.customerName = customerName;
           this.accountName = accountName;
           this.isBlueSkyState = isBlueSkyState;
           this.blueSkyState = blueSkyState;
           this.isSsn = isSsn;
           this.ssnOrTin = ssnOrTin;
           this.dateOfBirth = dateOfBirth;
           this.backupWithholdingIndicator = backupWithholdingIndicator;
           this.accountRepId = accountRepId;
           this.shareClassId = shareClassId;
           this.userId = userId;
           this.accountAdvisorId = accountAdvisorId;
           this.tradingEntity = tradingEntity;
           this.countryOfOriginParm = countryOfOriginParm;
           this.isBlueSkyExempt = isBlueSkyExempt;
           this.jointDateOfBirth = jointDateOfBirth;
           this.mailingAddressId = mailingAddressId;
           this.jointSsnOrTin = jointSsnOrTin;
           this.gender = gender;
           this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
           this.accountMessages = accountMessages;
           this.assetAllocationModel = assetAllocationModel;
           this.automaticInvestments = automaticInvestments;
           this.bankAccounts = bankAccounts;
           this.beneficiary = beneficiary;
           this.mailingAddress = mailingAddress;
           this.successors = successors;
    }


    /**
     * Gets the accountNumber value for this AccountRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the accountType value for this AccountRequest.
     * 
     * @return accountType
     */
    public org.apache.axis.types.UnsignedByte getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this AccountRequest.
     * 
     * @param accountType
     */
    public void setAccountType(org.apache.axis.types.UnsignedByte accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the customerName value for this AccountRequest.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this AccountRequest.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the accountName value for this AccountRequest.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AccountRequest.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the isBlueSkyState value for this AccountRequest.
     * 
     * @return isBlueSkyState
     */
    public boolean isIsBlueSkyState() {
        return isBlueSkyState;
    }


    /**
     * Sets the isBlueSkyState value for this AccountRequest.
     * 
     * @param isBlueSkyState
     */
    public void setIsBlueSkyState(boolean isBlueSkyState) {
        this.isBlueSkyState = isBlueSkyState;
    }


    /**
     * Gets the blueSkyState value for this AccountRequest.
     * 
     * @return blueSkyState
     */
    public java.lang.String getBlueSkyState() {
        return blueSkyState;
    }


    /**
     * Sets the blueSkyState value for this AccountRequest.
     * 
     * @param blueSkyState
     */
    public void setBlueSkyState(java.lang.String blueSkyState) {
        this.blueSkyState = blueSkyState;
    }


    /**
     * Gets the isSsn value for this AccountRequest.
     * 
     * @return isSsn
     */
    public boolean isIsSsn() {
        return isSsn;
    }


    /**
     * Sets the isSsn value for this AccountRequest.
     * 
     * @param isSsn
     */
    public void setIsSsn(boolean isSsn) {
        this.isSsn = isSsn;
    }


    /**
     * Gets the ssnOrTin value for this AccountRequest.
     * 
     * @return ssnOrTin
     */
    public java.lang.String getSsnOrTin() {
        return ssnOrTin;
    }


    /**
     * Sets the ssnOrTin value for this AccountRequest.
     * 
     * @param ssnOrTin
     */
    public void setSsnOrTin(java.lang.String ssnOrTin) {
        this.ssnOrTin = ssnOrTin;
    }


    /**
     * Gets the dateOfBirth value for this AccountRequest.
     * 
     * @return dateOfBirth
     */
    public java.util.Calendar getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Sets the dateOfBirth value for this AccountRequest.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(java.util.Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets the backupWithholdingIndicator value for this AccountRequest.
     * 
     * @return backupWithholdingIndicator
     */
    public org.apache.axis.types.UnsignedByte getBackupWithholdingIndicator() {
        return backupWithholdingIndicator;
    }


    /**
     * Sets the backupWithholdingIndicator value for this AccountRequest.
     * 
     * @param backupWithholdingIndicator
     */
    public void setBackupWithholdingIndicator(org.apache.axis.types.UnsignedByte backupWithholdingIndicator) {
        this.backupWithholdingIndicator = backupWithholdingIndicator;
    }


    /**
     * Gets the accountRepId value for this AccountRequest.
     * 
     * @return accountRepId
     */
    public int getAccountRepId() {
        return accountRepId;
    }


    /**
     * Sets the accountRepId value for this AccountRequest.
     * 
     * @param accountRepId
     */
    public void setAccountRepId(int accountRepId) {
        this.accountRepId = accountRepId;
    }


    /**
     * Gets the shareClassId value for this AccountRequest.
     * 
     * @return shareClassId
     */
    public java.lang.String getShareClassId() {
        return shareClassId;
    }


    /**
     * Sets the shareClassId value for this AccountRequest.
     * 
     * @param shareClassId
     */
    public void setShareClassId(java.lang.String shareClassId) {
        this.shareClassId = shareClassId;
    }


    /**
     * Gets the userId value for this AccountRequest.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this AccountRequest.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the accountAdvisorId value for this AccountRequest.
     * 
     * @return accountAdvisorId
     */
    public int getAccountAdvisorId() {
        return accountAdvisorId;
    }


    /**
     * Sets the accountAdvisorId value for this AccountRequest.
     * 
     * @param accountAdvisorId
     */
    public void setAccountAdvisorId(int accountAdvisorId) {
        this.accountAdvisorId = accountAdvisorId;
    }


    /**
     * Gets the tradingEntity value for this AccountRequest.
     * 
     * @return tradingEntity
     */
    public org.apache.axis.types.UnsignedByte getTradingEntity() {
        return tradingEntity;
    }


    /**
     * Sets the tradingEntity value for this AccountRequest.
     * 
     * @param tradingEntity
     */
    public void setTradingEntity(org.apache.axis.types.UnsignedByte tradingEntity) {
        this.tradingEntity = tradingEntity;
    }


    /**
     * Gets the countryOfOriginParm value for this AccountRequest.
     * 
     * @return countryOfOriginParm
     */
    public short getCountryOfOriginParm() {
        return countryOfOriginParm;
    }


    /**
     * Sets the countryOfOriginParm value for this AccountRequest.
     * 
     * @param countryOfOriginParm
     */
    public void setCountryOfOriginParm(short countryOfOriginParm) {
        this.countryOfOriginParm = countryOfOriginParm;
    }


    /**
     * Gets the isBlueSkyExempt value for this AccountRequest.
     * 
     * @return isBlueSkyExempt
     */
    public boolean isIsBlueSkyExempt() {
        return isBlueSkyExempt;
    }


    /**
     * Sets the isBlueSkyExempt value for this AccountRequest.
     * 
     * @param isBlueSkyExempt
     */
    public void setIsBlueSkyExempt(boolean isBlueSkyExempt) {
        this.isBlueSkyExempt = isBlueSkyExempt;
    }


    /**
     * Gets the jointDateOfBirth value for this AccountRequest.
     * 
     * @return jointDateOfBirth
     */
    public java.util.Calendar getJointDateOfBirth() {
        return jointDateOfBirth;
    }


    /**
     * Sets the jointDateOfBirth value for this AccountRequest.
     * 
     * @param jointDateOfBirth
     */
    public void setJointDateOfBirth(java.util.Calendar jointDateOfBirth) {
        this.jointDateOfBirth = jointDateOfBirth;
    }


    /**
     * Gets the mailingAddressId value for this AccountRequest.
     * 
     * @return mailingAddressId
     */
    public int getMailingAddressId() {
        return mailingAddressId;
    }


    /**
     * Sets the mailingAddressId value for this AccountRequest.
     * 
     * @param mailingAddressId
     */
    public void setMailingAddressId(int mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }


    /**
     * Gets the jointSsnOrTin value for this AccountRequest.
     * 
     * @return jointSsnOrTin
     */
    public java.lang.String getJointSsnOrTin() {
        return jointSsnOrTin;
    }


    /**
     * Sets the jointSsnOrTin value for this AccountRequest.
     * 
     * @param jointSsnOrTin
     */
    public void setJointSsnOrTin(java.lang.String jointSsnOrTin) {
        this.jointSsnOrTin = jointSsnOrTin;
    }


    /**
     * Gets the gender value for this AccountRequest.
     * 
     * @return gender
     */
    public org.apache.axis.types.UnsignedByte getGender() {
        return gender;
    }


    /**
     * Sets the gender value for this AccountRequest.
     * 
     * @param gender
     */
    public void setGender(org.apache.axis.types.UnsignedByte gender) {
        this.gender = gender;
    }


    /**
     * Gets the portfolioAllocatorIndicator value for this AccountRequest.
     * 
     * @return portfolioAllocatorIndicator
     */
    public org.apache.axis.types.UnsignedByte getPortfolioAllocatorIndicator() {
        return portfolioAllocatorIndicator;
    }


    /**
     * Sets the portfolioAllocatorIndicator value for this AccountRequest.
     * 
     * @param portfolioAllocatorIndicator
     */
    public void setPortfolioAllocatorIndicator(org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
        this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }


    /**
     * Gets the accountMessages value for this AccountRequest.
     * 
     * @return accountMessages
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AccountMessageCollectionRequest getAccountMessages() {
        return accountMessages;
    }


    /**
     * Sets the accountMessages value for this AccountRequest.
     * 
     * @param accountMessages
     */
    public void setAccountMessages(com.invessence.ws.provider.gemini.wsdl.account.AccountMessageCollectionRequest accountMessages) {
        this.accountMessages = accountMessages;
    }


    /**
     * Gets the assetAllocationModel value for this AccountRequest.
     * 
     * @return assetAllocationModel
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsRequest getAssetAllocationModel() {
        return assetAllocationModel;
    }


    /**
     * Sets the assetAllocationModel value for this AccountRequest.
     * 
     * @param assetAllocationModel
     */
    public void setAssetAllocationModel(com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsRequest assetAllocationModel) {
        this.assetAllocationModel = assetAllocationModel;
    }


    /**
     * Gets the automaticInvestments value for this AccountRequest.
     * 
     * @return automaticInvestments
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentRequest getAutomaticInvestments() {
        return automaticInvestments;
    }


    /**
     * Sets the automaticInvestments value for this AccountRequest.
     * 
     * @param automaticInvestments
     */
    public void setAutomaticInvestments(com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentRequest automaticInvestments) {
        this.automaticInvestments = automaticInvestments;
    }


    /**
     * Gets the bankAccounts value for this AccountRequest.
     * 
     * @return bankAccounts
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AchPayeeCollectionRequest getBankAccounts() {
        return bankAccounts;
    }


    /**
     * Sets the bankAccounts value for this AccountRequest.
     * 
     * @param bankAccounts
     */
    public void setBankAccounts(com.invessence.ws.provider.gemini.wsdl.account.AchPayeeCollectionRequest bankAccounts) {
        this.bankAccounts = bankAccounts;
    }


    /**
     * Gets the beneficiary value for this AccountRequest.
     * 
     * @return beneficiary
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Request getBeneficiary() {
        return beneficiary;
    }


    /**
     * Sets the beneficiary value for this AccountRequest.
     * 
     * @param beneficiary
     */
    public void setBeneficiary(com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Request beneficiary) {
        this.beneficiary = beneficiary;
    }


    /**
     * Gets the mailingAddress value for this AccountRequest.
     * 
     * @return mailingAddress
     */
    public com.invessence.ws.provider.gemini.wsdl.account.MailingAddressesRequest getMailingAddress() {
        return mailingAddress;
    }


    /**
     * Sets the mailingAddress value for this AccountRequest.
     * 
     * @param mailingAddress
     */
    public void setMailingAddress(com.invessence.ws.provider.gemini.wsdl.account.MailingAddressesRequest mailingAddress) {
        this.mailingAddress = mailingAddress;
    }


    /**
     * Gets the successors value for this AccountRequest.
     * 
     * @return successors
     */
    public com.invessence.ws.provider.gemini.wsdl.account.SuccessorBeneficiaryCollectionRequest getSuccessors() {
        return successors;
    }


    /**
     * Sets the successors value for this AccountRequest.
     * 
     * @param successors
     */
    public void setSuccessors(com.invessence.ws.provider.gemini.wsdl.account.SuccessorBeneficiaryCollectionRequest successors) {
        this.successors = successors;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountRequest)) return false;
        AccountRequest other = (AccountRequest) obj;
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
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            this.isBlueSkyState == other.isIsBlueSkyState() &&
            ((this.blueSkyState==null && other.getBlueSkyState()==null) || 
             (this.blueSkyState!=null &&
              this.blueSkyState.equals(other.getBlueSkyState()))) &&
            this.isSsn == other.isIsSsn() &&
            ((this.ssnOrTin==null && other.getSsnOrTin()==null) || 
             (this.ssnOrTin!=null &&
              this.ssnOrTin.equals(other.getSsnOrTin()))) &&
            ((this.dateOfBirth==null && other.getDateOfBirth()==null) || 
             (this.dateOfBirth!=null &&
              this.dateOfBirth.equals(other.getDateOfBirth()))) &&
            ((this.backupWithholdingIndicator==null && other.getBackupWithholdingIndicator()==null) || 
             (this.backupWithholdingIndicator!=null &&
              this.backupWithholdingIndicator.equals(other.getBackupWithholdingIndicator()))) &&
            this.accountRepId == other.getAccountRepId() &&
            ((this.shareClassId==null && other.getShareClassId()==null) || 
             (this.shareClassId!=null &&
              this.shareClassId.equals(other.getShareClassId()))) &&
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            this.accountAdvisorId == other.getAccountAdvisorId() &&
            ((this.tradingEntity==null && other.getTradingEntity()==null) || 
             (this.tradingEntity!=null &&
              this.tradingEntity.equals(other.getTradingEntity()))) &&
            this.countryOfOriginParm == other.getCountryOfOriginParm() &&
            this.isBlueSkyExempt == other.isIsBlueSkyExempt() &&
            ((this.jointDateOfBirth==null && other.getJointDateOfBirth()==null) || 
             (this.jointDateOfBirth!=null &&
              this.jointDateOfBirth.equals(other.getJointDateOfBirth()))) &&
            this.mailingAddressId == other.getMailingAddressId() &&
            ((this.jointSsnOrTin==null && other.getJointSsnOrTin()==null) || 
             (this.jointSsnOrTin!=null &&
              this.jointSsnOrTin.equals(other.getJointSsnOrTin()))) &&
            ((this.gender==null && other.getGender()==null) || 
             (this.gender!=null &&
              this.gender.equals(other.getGender()))) &&
            ((this.portfolioAllocatorIndicator==null && other.getPortfolioAllocatorIndicator()==null) || 
             (this.portfolioAllocatorIndicator!=null &&
              this.portfolioAllocatorIndicator.equals(other.getPortfolioAllocatorIndicator()))) &&
            ((this.accountMessages==null && other.getAccountMessages()==null) || 
             (this.accountMessages!=null &&
              this.accountMessages.equals(other.getAccountMessages()))) &&
            ((this.assetAllocationModel==null && other.getAssetAllocationModel()==null) || 
             (this.assetAllocationModel!=null &&
              this.assetAllocationModel.equals(other.getAssetAllocationModel()))) &&
            ((this.automaticInvestments==null && other.getAutomaticInvestments()==null) || 
             (this.automaticInvestments!=null &&
              this.automaticInvestments.equals(other.getAutomaticInvestments()))) &&
            ((this.bankAccounts==null && other.getBankAccounts()==null) || 
             (this.bankAccounts!=null &&
              this.bankAccounts.equals(other.getBankAccounts()))) &&
            ((this.beneficiary==null && other.getBeneficiary()==null) || 
             (this.beneficiary!=null &&
              this.beneficiary.equals(other.getBeneficiary()))) &&
            ((this.mailingAddress==null && other.getMailingAddress()==null) || 
             (this.mailingAddress!=null &&
              this.mailingAddress.equals(other.getMailingAddress()))) &&
            ((this.successors==null && other.getSuccessors()==null) || 
             (this.successors!=null &&
              this.successors.equals(other.getSuccessors())));
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
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        _hashCode += (isIsBlueSkyState() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBlueSkyState() != null) {
            _hashCode += getBlueSkyState().hashCode();
        }
        _hashCode += (isIsSsn() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getSsnOrTin() != null) {
            _hashCode += getSsnOrTin().hashCode();
        }
        if (getDateOfBirth() != null) {
            _hashCode += getDateOfBirth().hashCode();
        }
        if (getBackupWithholdingIndicator() != null) {
            _hashCode += getBackupWithholdingIndicator().hashCode();
        }
        _hashCode += getAccountRepId();
        if (getShareClassId() != null) {
            _hashCode += getShareClassId().hashCode();
        }
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        _hashCode += getAccountAdvisorId();
        if (getTradingEntity() != null) {
            _hashCode += getTradingEntity().hashCode();
        }
        _hashCode += getCountryOfOriginParm();
        _hashCode += (isIsBlueSkyExempt() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getJointDateOfBirth() != null) {
            _hashCode += getJointDateOfBirth().hashCode();
        }
        _hashCode += getMailingAddressId();
        if (getJointSsnOrTin() != null) {
            _hashCode += getJointSsnOrTin().hashCode();
        }
        if (getGender() != null) {
            _hashCode += getGender().hashCode();
        }
        if (getPortfolioAllocatorIndicator() != null) {
            _hashCode += getPortfolioAllocatorIndicator().hashCode();
        }
        if (getAccountMessages() != null) {
            _hashCode += getAccountMessages().hashCode();
        }
        if (getAssetAllocationModel() != null) {
            _hashCode += getAssetAllocationModel().hashCode();
        }
        if (getAutomaticInvestments() != null) {
            _hashCode += getAutomaticInvestments().hashCode();
        }
        if (getBankAccounts() != null) {
            _hashCode += getBankAccounts().hashCode();
        }
        if (getBeneficiary() != null) {
            _hashCode += getBeneficiary().hashCode();
        }
        if (getMailingAddress() != null) {
            _hashCode += getMailingAddress().hashCode();
        }
        if (getSuccessors() != null) {
            _hashCode += getSuccessors().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isBlueSkyState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsBlueSkyState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blueSkyState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BlueSkyState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("dateOfBirth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateOfBirth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("backupWithholdingIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BackupWithholdingIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareClassId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareClassId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAdvisorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAdvisorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradingEntity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TradingEntity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryOfOriginParm");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CountryOfOriginParm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isBlueSkyExempt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsBlueSkyExempt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jointDateOfBirth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "JointDateOfBirth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddressId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jointSsnOrTin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "JointSsnOrTin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("gender");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Gender"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioAllocatorIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PortfolioAllocatorIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountMessages");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessages"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageCollectionRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetAllocationModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeCollectionRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Request"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("successors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Successors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryCollectionRequest"));
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
