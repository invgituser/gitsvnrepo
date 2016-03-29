/**
 * AccountInfoResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AccountInfoResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String oldAccountNumber;

    private org.apache.axis.types.UnsignedByte accountType;

    private java.lang.String customerName;

    private java.lang.String accountName;

    private java.lang.String accounTypeName;

    private org.apache.axis.types.UnsignedByte blueskyState;

    private org.apache.axis.types.UnsignedByte ssnOrTINIndicator;

    private java.lang.String ssnOrTIN;

    private java.util.Calendar dateOfBirth;

    private org.apache.axis.types.UnsignedByte backupWithholdingIndicator;

    private int accountRepId;

    private java.lang.String shareClassId;

    private int accountAdvisorId;

    private org.apache.axis.types.UnsignedByte tradingEntity;

    private short countryOfOriginParm;

    private org.apache.axis.types.UnsignedByte blueskyExemptIndicator;

    private java.util.Calendar jointDateOfBirth;

    private java.lang.String jointSSNOrTIN;

    private java.lang.String networkControlIndicator;

    private java.lang.String dealerAccountNumber;

    private java.util.Calendar accountClosingDate;

    private org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public AccountInfoResult() {
    }

    public AccountInfoResult(
           java.lang.String accountNumber,
           java.lang.String oldAccountNumber,
           org.apache.axis.types.UnsignedByte accountType,
           java.lang.String customerName,
           java.lang.String accountName,
           java.lang.String accounTypeName,
           org.apache.axis.types.UnsignedByte blueskyState,
           org.apache.axis.types.UnsignedByte ssnOrTINIndicator,
           java.lang.String ssnOrTIN,
           java.util.Calendar dateOfBirth,
           org.apache.axis.types.UnsignedByte backupWithholdingIndicator,
           int accountRepId,
           java.lang.String shareClassId,
           int accountAdvisorId,
           org.apache.axis.types.UnsignedByte tradingEntity,
           short countryOfOriginParm,
           org.apache.axis.types.UnsignedByte blueskyExemptIndicator,
           java.util.Calendar jointDateOfBirth,
           java.lang.String jointSSNOrTIN,
           java.lang.String networkControlIndicator,
           java.lang.String dealerAccountNumber,
           java.util.Calendar accountClosingDate,
           org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.accountNumber = accountNumber;
           this.oldAccountNumber = oldAccountNumber;
           this.accountType = accountType;
           this.customerName = customerName;
           this.accountName = accountName;
           this.accounTypeName = accounTypeName;
           this.blueskyState = blueskyState;
           this.ssnOrTINIndicator = ssnOrTINIndicator;
           this.ssnOrTIN = ssnOrTIN;
           this.dateOfBirth = dateOfBirth;
           this.backupWithholdingIndicator = backupWithholdingIndicator;
           this.accountRepId = accountRepId;
           this.shareClassId = shareClassId;
           this.accountAdvisorId = accountAdvisorId;
           this.tradingEntity = tradingEntity;
           this.countryOfOriginParm = countryOfOriginParm;
           this.blueskyExemptIndicator = blueskyExemptIndicator;
           this.jointDateOfBirth = jointDateOfBirth;
           this.jointSSNOrTIN = jointSSNOrTIN;
           this.networkControlIndicator = networkControlIndicator;
           this.dealerAccountNumber = dealerAccountNumber;
           this.accountClosingDate = accountClosingDate;
           this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountNumber value for this AccountInfoResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountInfoResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the oldAccountNumber value for this AccountInfoResult.
     * 
     * @return oldAccountNumber
     */
    public java.lang.String getOldAccountNumber() {
        return oldAccountNumber;
    }


    /**
     * Sets the oldAccountNumber value for this AccountInfoResult.
     * 
     * @param oldAccountNumber
     */
    public void setOldAccountNumber(java.lang.String oldAccountNumber) {
        this.oldAccountNumber = oldAccountNumber;
    }


    /**
     * Gets the accountType value for this AccountInfoResult.
     * 
     * @return accountType
     */
    public org.apache.axis.types.UnsignedByte getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this AccountInfoResult.
     * 
     * @param accountType
     */
    public void setAccountType(org.apache.axis.types.UnsignedByte accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the customerName value for this AccountInfoResult.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this AccountInfoResult.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the accountName value for this AccountInfoResult.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AccountInfoResult.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accounTypeName value for this AccountInfoResult.
     * 
     * @return accounTypeName
     */
    public java.lang.String getAccounTypeName() {
        return accounTypeName;
    }


    /**
     * Sets the accounTypeName value for this AccountInfoResult.
     * 
     * @param accounTypeName
     */
    public void setAccounTypeName(java.lang.String accounTypeName) {
        this.accounTypeName = accounTypeName;
    }


    /**
     * Gets the blueskyState value for this AccountInfoResult.
     * 
     * @return blueskyState
     */
    public org.apache.axis.types.UnsignedByte getBlueskyState() {
        return blueskyState;
    }


    /**
     * Sets the blueskyState value for this AccountInfoResult.
     * 
     * @param blueskyState
     */
    public void setBlueskyState(org.apache.axis.types.UnsignedByte blueskyState) {
        this.blueskyState = blueskyState;
    }


    /**
     * Gets the ssnOrTINIndicator value for this AccountInfoResult.
     * 
     * @return ssnOrTINIndicator
     */
    public org.apache.axis.types.UnsignedByte getSsnOrTINIndicator() {
        return ssnOrTINIndicator;
    }


    /**
     * Sets the ssnOrTINIndicator value for this AccountInfoResult.
     * 
     * @param ssnOrTINIndicator
     */
    public void setSsnOrTINIndicator(org.apache.axis.types.UnsignedByte ssnOrTINIndicator) {
        this.ssnOrTINIndicator = ssnOrTINIndicator;
    }


    /**
     * Gets the ssnOrTIN value for this AccountInfoResult.
     * 
     * @return ssnOrTIN
     */
    public java.lang.String getSsnOrTIN() {
        return ssnOrTIN;
    }


    /**
     * Sets the ssnOrTIN value for this AccountInfoResult.
     * 
     * @param ssnOrTIN
     */
    public void setSsnOrTIN(java.lang.String ssnOrTIN) {
        this.ssnOrTIN = ssnOrTIN;
    }


    /**
     * Gets the dateOfBirth value for this AccountInfoResult.
     * 
     * @return dateOfBirth
     */
    public java.util.Calendar getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Sets the dateOfBirth value for this AccountInfoResult.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(java.util.Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets the backupWithholdingIndicator value for this AccountInfoResult.
     * 
     * @return backupWithholdingIndicator
     */
    public org.apache.axis.types.UnsignedByte getBackupWithholdingIndicator() {
        return backupWithholdingIndicator;
    }


    /**
     * Sets the backupWithholdingIndicator value for this AccountInfoResult.
     * 
     * @param backupWithholdingIndicator
     */
    public void setBackupWithholdingIndicator(org.apache.axis.types.UnsignedByte backupWithholdingIndicator) {
        this.backupWithholdingIndicator = backupWithholdingIndicator;
    }


    /**
     * Gets the accountRepId value for this AccountInfoResult.
     * 
     * @return accountRepId
     */
    public int getAccountRepId() {
        return accountRepId;
    }


    /**
     * Sets the accountRepId value for this AccountInfoResult.
     * 
     * @param accountRepId
     */
    public void setAccountRepId(int accountRepId) {
        this.accountRepId = accountRepId;
    }


    /**
     * Gets the shareClassId value for this AccountInfoResult.
     * 
     * @return shareClassId
     */
    public java.lang.String getShareClassId() {
        return shareClassId;
    }


    /**
     * Sets the shareClassId value for this AccountInfoResult.
     * 
     * @param shareClassId
     */
    public void setShareClassId(java.lang.String shareClassId) {
        this.shareClassId = shareClassId;
    }


    /**
     * Gets the accountAdvisorId value for this AccountInfoResult.
     * 
     * @return accountAdvisorId
     */
    public int getAccountAdvisorId() {
        return accountAdvisorId;
    }


    /**
     * Sets the accountAdvisorId value for this AccountInfoResult.
     * 
     * @param accountAdvisorId
     */
    public void setAccountAdvisorId(int accountAdvisorId) {
        this.accountAdvisorId = accountAdvisorId;
    }


    /**
     * Gets the tradingEntity value for this AccountInfoResult.
     * 
     * @return tradingEntity
     */
    public org.apache.axis.types.UnsignedByte getTradingEntity() {
        return tradingEntity;
    }


    /**
     * Sets the tradingEntity value for this AccountInfoResult.
     * 
     * @param tradingEntity
     */
    public void setTradingEntity(org.apache.axis.types.UnsignedByte tradingEntity) {
        this.tradingEntity = tradingEntity;
    }


    /**
     * Gets the countryOfOriginParm value for this AccountInfoResult.
     * 
     * @return countryOfOriginParm
     */
    public short getCountryOfOriginParm() {
        return countryOfOriginParm;
    }


    /**
     * Sets the countryOfOriginParm value for this AccountInfoResult.
     * 
     * @param countryOfOriginParm
     */
    public void setCountryOfOriginParm(short countryOfOriginParm) {
        this.countryOfOriginParm = countryOfOriginParm;
    }


    /**
     * Gets the blueskyExemptIndicator value for this AccountInfoResult.
     * 
     * @return blueskyExemptIndicator
     */
    public org.apache.axis.types.UnsignedByte getBlueskyExemptIndicator() {
        return blueskyExemptIndicator;
    }


    /**
     * Sets the blueskyExemptIndicator value for this AccountInfoResult.
     * 
     * @param blueskyExemptIndicator
     */
    public void setBlueskyExemptIndicator(org.apache.axis.types.UnsignedByte blueskyExemptIndicator) {
        this.blueskyExemptIndicator = blueskyExemptIndicator;
    }


    /**
     * Gets the jointDateOfBirth value for this AccountInfoResult.
     * 
     * @return jointDateOfBirth
     */
    public java.util.Calendar getJointDateOfBirth() {
        return jointDateOfBirth;
    }


    /**
     * Sets the jointDateOfBirth value for this AccountInfoResult.
     * 
     * @param jointDateOfBirth
     */
    public void setJointDateOfBirth(java.util.Calendar jointDateOfBirth) {
        this.jointDateOfBirth = jointDateOfBirth;
    }


    /**
     * Gets the jointSSNOrTIN value for this AccountInfoResult.
     * 
     * @return jointSSNOrTIN
     */
    public java.lang.String getJointSSNOrTIN() {
        return jointSSNOrTIN;
    }


    /**
     * Sets the jointSSNOrTIN value for this AccountInfoResult.
     * 
     * @param jointSSNOrTIN
     */
    public void setJointSSNOrTIN(java.lang.String jointSSNOrTIN) {
        this.jointSSNOrTIN = jointSSNOrTIN;
    }


    /**
     * Gets the networkControlIndicator value for this AccountInfoResult.
     * 
     * @return networkControlIndicator
     */
    public java.lang.String getNetworkControlIndicator() {
        return networkControlIndicator;
    }


    /**
     * Sets the networkControlIndicator value for this AccountInfoResult.
     * 
     * @param networkControlIndicator
     */
    public void setNetworkControlIndicator(java.lang.String networkControlIndicator) {
        this.networkControlIndicator = networkControlIndicator;
    }


    /**
     * Gets the dealerAccountNumber value for this AccountInfoResult.
     * 
     * @return dealerAccountNumber
     */
    public java.lang.String getDealerAccountNumber() {
        return dealerAccountNumber;
    }


    /**
     * Sets the dealerAccountNumber value for this AccountInfoResult.
     * 
     * @param dealerAccountNumber
     */
    public void setDealerAccountNumber(java.lang.String dealerAccountNumber) {
        this.dealerAccountNumber = dealerAccountNumber;
    }


    /**
     * Gets the accountClosingDate value for this AccountInfoResult.
     * 
     * @return accountClosingDate
     */
    public java.util.Calendar getAccountClosingDate() {
        return accountClosingDate;
    }


    /**
     * Sets the accountClosingDate value for this AccountInfoResult.
     * 
     * @param accountClosingDate
     */
    public void setAccountClosingDate(java.util.Calendar accountClosingDate) {
        this.accountClosingDate = accountClosingDate;
    }


    /**
     * Gets the portfolioAllocatorIndicator value for this AccountInfoResult.
     * 
     * @return portfolioAllocatorIndicator
     */
    public org.apache.axis.types.UnsignedByte getPortfolioAllocatorIndicator() {
        return portfolioAllocatorIndicator;
    }


    /**
     * Sets the portfolioAllocatorIndicator value for this AccountInfoResult.
     * 
     * @param portfolioAllocatorIndicator
     */
    public void setPortfolioAllocatorIndicator(org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
        this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }


    /**
     * Gets the errorStatus value for this AccountInfoResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this AccountInfoResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountInfoResult)) return false;
        AccountInfoResult other = (AccountInfoResult) obj;
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
            ((this.oldAccountNumber==null && other.getOldAccountNumber()==null) || 
             (this.oldAccountNumber!=null &&
              this.oldAccountNumber.equals(other.getOldAccountNumber()))) &&
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accounTypeName==null && other.getAccounTypeName()==null) || 
             (this.accounTypeName!=null &&
              this.accounTypeName.equals(other.getAccounTypeName()))) &&
            ((this.blueskyState==null && other.getBlueskyState()==null) || 
             (this.blueskyState!=null &&
              this.blueskyState.equals(other.getBlueskyState()))) &&
            ((this.ssnOrTINIndicator==null && other.getSsnOrTINIndicator()==null) || 
             (this.ssnOrTINIndicator!=null &&
              this.ssnOrTINIndicator.equals(other.getSsnOrTINIndicator()))) &&
            ((this.ssnOrTIN==null && other.getSsnOrTIN()==null) || 
             (this.ssnOrTIN!=null &&
              this.ssnOrTIN.equals(other.getSsnOrTIN()))) &&
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
            this.accountAdvisorId == other.getAccountAdvisorId() &&
            ((this.tradingEntity==null && other.getTradingEntity()==null) || 
             (this.tradingEntity!=null &&
              this.tradingEntity.equals(other.getTradingEntity()))) &&
            this.countryOfOriginParm == other.getCountryOfOriginParm() &&
            ((this.blueskyExemptIndicator==null && other.getBlueskyExemptIndicator()==null) || 
             (this.blueskyExemptIndicator!=null &&
              this.blueskyExemptIndicator.equals(other.getBlueskyExemptIndicator()))) &&
            ((this.jointDateOfBirth==null && other.getJointDateOfBirth()==null) || 
             (this.jointDateOfBirth!=null &&
              this.jointDateOfBirth.equals(other.getJointDateOfBirth()))) &&
            ((this.jointSSNOrTIN==null && other.getJointSSNOrTIN()==null) || 
             (this.jointSSNOrTIN!=null &&
              this.jointSSNOrTIN.equals(other.getJointSSNOrTIN()))) &&
            ((this.networkControlIndicator==null && other.getNetworkControlIndicator()==null) || 
             (this.networkControlIndicator!=null &&
              this.networkControlIndicator.equals(other.getNetworkControlIndicator()))) &&
            ((this.dealerAccountNumber==null && other.getDealerAccountNumber()==null) || 
             (this.dealerAccountNumber!=null &&
              this.dealerAccountNumber.equals(other.getDealerAccountNumber()))) &&
            ((this.accountClosingDate==null && other.getAccountClosingDate()==null) || 
             (this.accountClosingDate!=null &&
              this.accountClosingDate.equals(other.getAccountClosingDate()))) &&
            ((this.portfolioAllocatorIndicator==null && other.getPortfolioAllocatorIndicator()==null) || 
             (this.portfolioAllocatorIndicator!=null &&
              this.portfolioAllocatorIndicator.equals(other.getPortfolioAllocatorIndicator()))) &&
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
        if (getOldAccountNumber() != null) {
            _hashCode += getOldAccountNumber().hashCode();
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
        if (getAccounTypeName() != null) {
            _hashCode += getAccounTypeName().hashCode();
        }
        if (getBlueskyState() != null) {
            _hashCode += getBlueskyState().hashCode();
        }
        if (getSsnOrTINIndicator() != null) {
            _hashCode += getSsnOrTINIndicator().hashCode();
        }
        if (getSsnOrTIN() != null) {
            _hashCode += getSsnOrTIN().hashCode();
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
        _hashCode += getAccountAdvisorId();
        if (getTradingEntity() != null) {
            _hashCode += getTradingEntity().hashCode();
        }
        _hashCode += getCountryOfOriginParm();
        if (getBlueskyExemptIndicator() != null) {
            _hashCode += getBlueskyExemptIndicator().hashCode();
        }
        if (getJointDateOfBirth() != null) {
            _hashCode += getJointDateOfBirth().hashCode();
        }
        if (getJointSSNOrTIN() != null) {
            _hashCode += getJointSSNOrTIN().hashCode();
        }
        if (getNetworkControlIndicator() != null) {
            _hashCode += getNetworkControlIndicator().hashCode();
        }
        if (getDealerAccountNumber() != null) {
            _hashCode += getDealerAccountNumber().hashCode();
        }
        if (getAccountClosingDate() != null) {
            _hashCode += getAccountClosingDate().hashCode();
        }
        if (getPortfolioAllocatorIndicator() != null) {
            _hashCode += getPortfolioAllocatorIndicator().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountInfoResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountInfoResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oldAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "OldAccountNumber"));
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
        elemField.setFieldName("accounTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccounTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blueskyState");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BlueskyState"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ssnOrTINIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SsnOrTINIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ssnOrTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SsnOrTIN"));
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
        elemField.setFieldName("blueskyExemptIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BlueskyExemptIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jointDateOfBirth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "JointDateOfBirth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jointSSNOrTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "JointSSNOrTIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("networkControlIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NetworkControlIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealerAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DealerAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountClosingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountClosingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioAllocatorIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PortfolioAllocatorIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
