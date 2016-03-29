/**
 * WebUserResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.login;

public class WebUserResult  implements java.io.Serializable {
    private java.lang.String userId;

    private org.apache.axis.types.UnsignedByte verificationStatus;

    private java.lang.String verificationCode;

    private java.lang.String password;

    private org.apache.axis.types.UnsignedByte userType;

    private java.lang.String accountNumber;

    private java.math.BigDecimal firmNumber;

    private java.lang.String firmBranchNumber;

    private int accountRepId;

    private java.lang.String defaultWebPage;

    private org.apache.axis.types.UnsignedByte accessLinkedAccounts;

    private org.apache.axis.types.UnsignedByte profileId;

    private org.apache.axis.types.UnsignedByte userStatus;

    private java.math.BigDecimal noOfUnsuccessfulLoginAttempts;

    private java.util.Calendar lastUpdatedDateTime;

    private java.lang.String customValue1;

    private java.lang.String customValue2;

    private java.lang.String customValue3;

    private java.lang.String customValue4;

    private java.lang.String customValue5;

    private java.lang.String emailAddress;

    private java.lang.String secretQuestion;

    private java.lang.String secretAnswer;

    private java.lang.String ssnOrTIN;

    private java.lang.String zipCode;

    private int counts;

    private java.lang.String firmRepNumber;

    private com.ws.gemini.wsdl.login.Status errorStatus;

    public WebUserResult() {
    }

    public WebUserResult(
           java.lang.String userId,
           org.apache.axis.types.UnsignedByte verificationStatus,
           java.lang.String verificationCode,
           java.lang.String password,
           org.apache.axis.types.UnsignedByte userType,
           java.lang.String accountNumber,
           java.math.BigDecimal firmNumber,
           java.lang.String firmBranchNumber,
           int accountRepId,
           java.lang.String defaultWebPage,
           org.apache.axis.types.UnsignedByte accessLinkedAccounts,
           org.apache.axis.types.UnsignedByte profileId,
           org.apache.axis.types.UnsignedByte userStatus,
           java.math.BigDecimal noOfUnsuccessfulLoginAttempts,
           java.util.Calendar lastUpdatedDateTime,
           java.lang.String customValue1,
           java.lang.String customValue2,
           java.lang.String customValue3,
           java.lang.String customValue4,
           java.lang.String customValue5,
           java.lang.String emailAddress,
           java.lang.String secretQuestion,
           java.lang.String secretAnswer,
           java.lang.String ssnOrTIN,
           java.lang.String zipCode,
           int counts,
           java.lang.String firmRepNumber,
           com.ws.gemini.wsdl.login.Status errorStatus) {
           this.userId = userId;
           this.verificationStatus = verificationStatus;
           this.verificationCode = verificationCode;
           this.password = password;
           this.userType = userType;
           this.accountNumber = accountNumber;
           this.firmNumber = firmNumber;
           this.firmBranchNumber = firmBranchNumber;
           this.accountRepId = accountRepId;
           this.defaultWebPage = defaultWebPage;
           this.accessLinkedAccounts = accessLinkedAccounts;
           this.profileId = profileId;
           this.userStatus = userStatus;
           this.noOfUnsuccessfulLoginAttempts = noOfUnsuccessfulLoginAttempts;
           this.lastUpdatedDateTime = lastUpdatedDateTime;
           this.customValue1 = customValue1;
           this.customValue2 = customValue2;
           this.customValue3 = customValue3;
           this.customValue4 = customValue4;
           this.customValue5 = customValue5;
           this.emailAddress = emailAddress;
           this.secretQuestion = secretQuestion;
           this.secretAnswer = secretAnswer;
           this.ssnOrTIN = ssnOrTIN;
           this.zipCode = zipCode;
           this.counts = counts;
           this.firmRepNumber = firmRepNumber;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the userId value for this WebUserResult.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this WebUserResult.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the verificationStatus value for this WebUserResult.
     * 
     * @return verificationStatus
     */
    public org.apache.axis.types.UnsignedByte getVerificationStatus() {
        return verificationStatus;
    }


    /**
     * Sets the verificationStatus value for this WebUserResult.
     * 
     * @param verificationStatus
     */
    public void setVerificationStatus(org.apache.axis.types.UnsignedByte verificationStatus) {
        this.verificationStatus = verificationStatus;
    }


    /**
     * Gets the verificationCode value for this WebUserResult.
     * 
     * @return verificationCode
     */
    public java.lang.String getVerificationCode() {
        return verificationCode;
    }


    /**
     * Sets the verificationCode value for this WebUserResult.
     * 
     * @param verificationCode
     */
    public void setVerificationCode(java.lang.String verificationCode) {
        this.verificationCode = verificationCode;
    }


    /**
     * Gets the password value for this WebUserResult.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this WebUserResult.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the userType value for this WebUserResult.
     * 
     * @return userType
     */
    public org.apache.axis.types.UnsignedByte getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this WebUserResult.
     * 
     * @param userType
     */
    public void setUserType(org.apache.axis.types.UnsignedByte userType) {
        this.userType = userType;
    }


    /**
     * Gets the accountNumber value for this WebUserResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this WebUserResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the firmNumber value for this WebUserResult.
     * 
     * @return firmNumber
     */
    public java.math.BigDecimal getFirmNumber() {
        return firmNumber;
    }


    /**
     * Sets the firmNumber value for this WebUserResult.
     * 
     * @param firmNumber
     */
    public void setFirmNumber(java.math.BigDecimal firmNumber) {
        this.firmNumber = firmNumber;
    }


    /**
     * Gets the firmBranchNumber value for this WebUserResult.
     * 
     * @return firmBranchNumber
     */
    public java.lang.String getFirmBranchNumber() {
        return firmBranchNumber;
    }


    /**
     * Sets the firmBranchNumber value for this WebUserResult.
     * 
     * @param firmBranchNumber
     */
    public void setFirmBranchNumber(java.lang.String firmBranchNumber) {
        this.firmBranchNumber = firmBranchNumber;
    }


    /**
     * Gets the accountRepId value for this WebUserResult.
     * 
     * @return accountRepId
     */
    public int getAccountRepId() {
        return accountRepId;
    }


    /**
     * Sets the accountRepId value for this WebUserResult.
     * 
     * @param accountRepId
     */
    public void setAccountRepId(int accountRepId) {
        this.accountRepId = accountRepId;
    }


    /**
     * Gets the defaultWebPage value for this WebUserResult.
     * 
     * @return defaultWebPage
     */
    public java.lang.String getDefaultWebPage() {
        return defaultWebPage;
    }


    /**
     * Sets the defaultWebPage value for this WebUserResult.
     * 
     * @param defaultWebPage
     */
    public void setDefaultWebPage(java.lang.String defaultWebPage) {
        this.defaultWebPage = defaultWebPage;
    }


    /**
     * Gets the accessLinkedAccounts value for this WebUserResult.
     * 
     * @return accessLinkedAccounts
     */
    public org.apache.axis.types.UnsignedByte getAccessLinkedAccounts() {
        return accessLinkedAccounts;
    }


    /**
     * Sets the accessLinkedAccounts value for this WebUserResult.
     * 
     * @param accessLinkedAccounts
     */
    public void setAccessLinkedAccounts(org.apache.axis.types.UnsignedByte accessLinkedAccounts) {
        this.accessLinkedAccounts = accessLinkedAccounts;
    }


    /**
     * Gets the profileId value for this WebUserResult.
     * 
     * @return profileId
     */
    public org.apache.axis.types.UnsignedByte getProfileId() {
        return profileId;
    }


    /**
     * Sets the profileId value for this WebUserResult.
     * 
     * @param profileId
     */
    public void setProfileId(org.apache.axis.types.UnsignedByte profileId) {
        this.profileId = profileId;
    }


    /**
     * Gets the userStatus value for this WebUserResult.
     * 
     * @return userStatus
     */
    public org.apache.axis.types.UnsignedByte getUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this WebUserResult.
     * 
     * @param userStatus
     */
    public void setUserStatus(org.apache.axis.types.UnsignedByte userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the noOfUnsuccessfulLoginAttempts value for this WebUserResult.
     * 
     * @return noOfUnsuccessfulLoginAttempts
     */
    public java.math.BigDecimal getNoOfUnsuccessfulLoginAttempts() {
        return noOfUnsuccessfulLoginAttempts;
    }


    /**
     * Sets the noOfUnsuccessfulLoginAttempts value for this WebUserResult.
     * 
     * @param noOfUnsuccessfulLoginAttempts
     */
    public void setNoOfUnsuccessfulLoginAttempts(java.math.BigDecimal noOfUnsuccessfulLoginAttempts) {
        this.noOfUnsuccessfulLoginAttempts = noOfUnsuccessfulLoginAttempts;
    }


    /**
     * Gets the lastUpdatedDateTime value for this WebUserResult.
     * 
     * @return lastUpdatedDateTime
     */
    public java.util.Calendar getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }


    /**
     * Sets the lastUpdatedDateTime value for this WebUserResult.
     * 
     * @param lastUpdatedDateTime
     */
    public void setLastUpdatedDateTime(java.util.Calendar lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }


    /**
     * Gets the customValue1 value for this WebUserResult.
     * 
     * @return customValue1
     */
    public java.lang.String getCustomValue1() {
        return customValue1;
    }


    /**
     * Sets the customValue1 value for this WebUserResult.
     * 
     * @param customValue1
     */
    public void setCustomValue1(java.lang.String customValue1) {
        this.customValue1 = customValue1;
    }


    /**
     * Gets the customValue2 value for this WebUserResult.
     * 
     * @return customValue2
     */
    public java.lang.String getCustomValue2() {
        return customValue2;
    }


    /**
     * Sets the customValue2 value for this WebUserResult.
     * 
     * @param customValue2
     */
    public void setCustomValue2(java.lang.String customValue2) {
        this.customValue2 = customValue2;
    }


    /**
     * Gets the customValue3 value for this WebUserResult.
     * 
     * @return customValue3
     */
    public java.lang.String getCustomValue3() {
        return customValue3;
    }


    /**
     * Sets the customValue3 value for this WebUserResult.
     * 
     * @param customValue3
     */
    public void setCustomValue3(java.lang.String customValue3) {
        this.customValue3 = customValue3;
    }


    /**
     * Gets the customValue4 value for this WebUserResult.
     * 
     * @return customValue4
     */
    public java.lang.String getCustomValue4() {
        return customValue4;
    }


    /**
     * Sets the customValue4 value for this WebUserResult.
     * 
     * @param customValue4
     */
    public void setCustomValue4(java.lang.String customValue4) {
        this.customValue4 = customValue4;
    }


    /**
     * Gets the customValue5 value for this WebUserResult.
     * 
     * @return customValue5
     */
    public java.lang.String getCustomValue5() {
        return customValue5;
    }


    /**
     * Sets the customValue5 value for this WebUserResult.
     * 
     * @param customValue5
     */
    public void setCustomValue5(java.lang.String customValue5) {
        this.customValue5 = customValue5;
    }


    /**
     * Gets the emailAddress value for this WebUserResult.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this WebUserResult.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the secretQuestion value for this WebUserResult.
     * 
     * @return secretQuestion
     */
    public java.lang.String getSecretQuestion() {
        return secretQuestion;
    }


    /**
     * Sets the secretQuestion value for this WebUserResult.
     * 
     * @param secretQuestion
     */
    public void setSecretQuestion(java.lang.String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }


    /**
     * Gets the secretAnswer value for this WebUserResult.
     * 
     * @return secretAnswer
     */
    public java.lang.String getSecretAnswer() {
        return secretAnswer;
    }


    /**
     * Sets the secretAnswer value for this WebUserResult.
     * 
     * @param secretAnswer
     */
    public void setSecretAnswer(java.lang.String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }


    /**
     * Gets the ssnOrTIN value for this WebUserResult.
     * 
     * @return ssnOrTIN
     */
    public java.lang.String getSsnOrTIN() {
        return ssnOrTIN;
    }


    /**
     * Sets the ssnOrTIN value for this WebUserResult.
     * 
     * @param ssnOrTIN
     */
    public void setSsnOrTIN(java.lang.String ssnOrTIN) {
        this.ssnOrTIN = ssnOrTIN;
    }


    /**
     * Gets the zipCode value for this WebUserResult.
     * 
     * @return zipCode
     */
    public java.lang.String getZipCode() {
        return zipCode;
    }


    /**
     * Sets the zipCode value for this WebUserResult.
     * 
     * @param zipCode
     */
    public void setZipCode(java.lang.String zipCode) {
        this.zipCode = zipCode;
    }


    /**
     * Gets the counts value for this WebUserResult.
     * 
     * @return counts
     */
    public int getCounts() {
        return counts;
    }


    /**
     * Sets the counts value for this WebUserResult.
     * 
     * @param counts
     */
    public void setCounts(int counts) {
        this.counts = counts;
    }


    /**
     * Gets the firmRepNumber value for this WebUserResult.
     * 
     * @return firmRepNumber
     */
    public java.lang.String getFirmRepNumber() {
        return firmRepNumber;
    }


    /**
     * Sets the firmRepNumber value for this WebUserResult.
     * 
     * @param firmRepNumber
     */
    public void setFirmRepNumber(java.lang.String firmRepNumber) {
        this.firmRepNumber = firmRepNumber;
    }


    /**
     * Gets the errorStatus value for this WebUserResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.login.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this WebUserResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.login.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebUserResult)) return false;
        WebUserResult other = (WebUserResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.verificationStatus==null && other.getVerificationStatus()==null) || 
             (this.verificationStatus!=null &&
              this.verificationStatus.equals(other.getVerificationStatus()))) &&
            ((this.verificationCode==null && other.getVerificationCode()==null) || 
             (this.verificationCode!=null &&
              this.verificationCode.equals(other.getVerificationCode()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.userType==null && other.getUserType()==null) || 
             (this.userType!=null &&
              this.userType.equals(other.getUserType()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.firmNumber==null && other.getFirmNumber()==null) || 
             (this.firmNumber!=null &&
              this.firmNumber.equals(other.getFirmNumber()))) &&
            ((this.firmBranchNumber==null && other.getFirmBranchNumber()==null) || 
             (this.firmBranchNumber!=null &&
              this.firmBranchNumber.equals(other.getFirmBranchNumber()))) &&
            this.accountRepId == other.getAccountRepId() &&
            ((this.defaultWebPage==null && other.getDefaultWebPage()==null) || 
             (this.defaultWebPage!=null &&
              this.defaultWebPage.equals(other.getDefaultWebPage()))) &&
            ((this.accessLinkedAccounts==null && other.getAccessLinkedAccounts()==null) || 
             (this.accessLinkedAccounts!=null &&
              this.accessLinkedAccounts.equals(other.getAccessLinkedAccounts()))) &&
            ((this.profileId==null && other.getProfileId()==null) || 
             (this.profileId!=null &&
              this.profileId.equals(other.getProfileId()))) &&
            ((this.userStatus==null && other.getUserStatus()==null) || 
             (this.userStatus!=null &&
              this.userStatus.equals(other.getUserStatus()))) &&
            ((this.noOfUnsuccessfulLoginAttempts==null && other.getNoOfUnsuccessfulLoginAttempts()==null) || 
             (this.noOfUnsuccessfulLoginAttempts!=null &&
              this.noOfUnsuccessfulLoginAttempts.equals(other.getNoOfUnsuccessfulLoginAttempts()))) &&
            ((this.lastUpdatedDateTime==null && other.getLastUpdatedDateTime()==null) || 
             (this.lastUpdatedDateTime!=null &&
              this.lastUpdatedDateTime.equals(other.getLastUpdatedDateTime()))) &&
            ((this.customValue1==null && other.getCustomValue1()==null) || 
             (this.customValue1!=null &&
              this.customValue1.equals(other.getCustomValue1()))) &&
            ((this.customValue2==null && other.getCustomValue2()==null) || 
             (this.customValue2!=null &&
              this.customValue2.equals(other.getCustomValue2()))) &&
            ((this.customValue3==null && other.getCustomValue3()==null) || 
             (this.customValue3!=null &&
              this.customValue3.equals(other.getCustomValue3()))) &&
            ((this.customValue4==null && other.getCustomValue4()==null) || 
             (this.customValue4!=null &&
              this.customValue4.equals(other.getCustomValue4()))) &&
            ((this.customValue5==null && other.getCustomValue5()==null) || 
             (this.customValue5!=null &&
              this.customValue5.equals(other.getCustomValue5()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.secretQuestion==null && other.getSecretQuestion()==null) || 
             (this.secretQuestion!=null &&
              this.secretQuestion.equals(other.getSecretQuestion()))) &&
            ((this.secretAnswer==null && other.getSecretAnswer()==null) || 
             (this.secretAnswer!=null &&
              this.secretAnswer.equals(other.getSecretAnswer()))) &&
            ((this.ssnOrTIN==null && other.getSsnOrTIN()==null) || 
             (this.ssnOrTIN!=null &&
              this.ssnOrTIN.equals(other.getSsnOrTIN()))) &&
            ((this.zipCode==null && other.getZipCode()==null) || 
             (this.zipCode!=null &&
              this.zipCode.equals(other.getZipCode()))) &&
            this.counts == other.getCounts() &&
            ((this.firmRepNumber==null && other.getFirmRepNumber()==null) || 
             (this.firmRepNumber!=null &&
              this.firmRepNumber.equals(other.getFirmRepNumber()))) &&
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
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getVerificationStatus() != null) {
            _hashCode += getVerificationStatus().hashCode();
        }
        if (getVerificationCode() != null) {
            _hashCode += getVerificationCode().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUserType() != null) {
            _hashCode += getUserType().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getFirmNumber() != null) {
            _hashCode += getFirmNumber().hashCode();
        }
        if (getFirmBranchNumber() != null) {
            _hashCode += getFirmBranchNumber().hashCode();
        }
        _hashCode += getAccountRepId();
        if (getDefaultWebPage() != null) {
            _hashCode += getDefaultWebPage().hashCode();
        }
        if (getAccessLinkedAccounts() != null) {
            _hashCode += getAccessLinkedAccounts().hashCode();
        }
        if (getProfileId() != null) {
            _hashCode += getProfileId().hashCode();
        }
        if (getUserStatus() != null) {
            _hashCode += getUserStatus().hashCode();
        }
        if (getNoOfUnsuccessfulLoginAttempts() != null) {
            _hashCode += getNoOfUnsuccessfulLoginAttempts().hashCode();
        }
        if (getLastUpdatedDateTime() != null) {
            _hashCode += getLastUpdatedDateTime().hashCode();
        }
        if (getCustomValue1() != null) {
            _hashCode += getCustomValue1().hashCode();
        }
        if (getCustomValue2() != null) {
            _hashCode += getCustomValue2().hashCode();
        }
        if (getCustomValue3() != null) {
            _hashCode += getCustomValue3().hashCode();
        }
        if (getCustomValue4() != null) {
            _hashCode += getCustomValue4().hashCode();
        }
        if (getCustomValue5() != null) {
            _hashCode += getCustomValue5().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getSecretQuestion() != null) {
            _hashCode += getSecretQuestion().hashCode();
        }
        if (getSecretAnswer() != null) {
            _hashCode += getSecretAnswer().hashCode();
        }
        if (getSsnOrTIN() != null) {
            _hashCode += getSsnOrTIN().hashCode();
        }
        if (getZipCode() != null) {
            _hashCode += getZipCode().hashCode();
        }
        _hashCode += getCounts();
        if (getFirmRepNumber() != null) {
            _hashCode += getFirmRepNumber().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebUserResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "VerificationStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "VerificationCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firmNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FirmNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firmBranchNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FirmBranchNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRepId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRepId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultWebPage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DefaultWebPage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessLinkedAccounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccessLinkedAccounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("profileId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProfileId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfUnsuccessfulLoginAttempts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfUnsuccessfulLoginAttempts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastUpdatedDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LastUpdatedDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secretQuestion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SecretQuestion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secretAnswer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SecretAnswer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("zipCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ZipCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("counts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Counts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firmRepNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FirmRepNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
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

    @Override
    public String toString()
    {
        return "WebUserResult{" +
           "firmNumber=" + firmNumber +
           ", accessLinkedAccounts=" + accessLinkedAccounts +
           ", accountNumber='" + accountNumber + '\'' +
           ", accountRepId=" + accountRepId +
           ", counts=" + counts +
           ", customValue1='" + customValue1 + '\'' +
           ", customValue2='" + customValue2 + '\'' +
           ", customValue3='" + customValue3 + '\'' +
           ", customValue4='" + customValue4 + '\'' +
           ", customValue5='" + customValue5 + '\'' +
           ", defaultWebPage='" + defaultWebPage + '\'' +
           ", emailAddress='" + emailAddress + '\'' +
           ", errorStatus=" + errorStatus +
           ", firmBranchNumber='" + firmBranchNumber + '\'' +
           ", firmRepNumber='" + firmRepNumber + '\'' +
           ", lastUpdatedDateTime=" + lastUpdatedDateTime +
           ", noOfUnsuccessfulLoginAttempts=" + noOfUnsuccessfulLoginAttempts +
           ", password='" + password + '\'' +
           ", profileId=" + profileId +
           ", secretAnswer='" + secretAnswer + '\'' +
           ", secretQuestion='" + secretQuestion + '\'' +
           ", ssnOrTIN='" + ssnOrTIN + '\'' +
           ", userId='" + userId + '\'' +
           ", userStatus=" + userStatus +
           ", userType=" + userType +
           ", verificationCode='" + verificationCode + '\'' +
           ", verificationStatus=" + verificationStatus +
           ", zipCode='" + zipCode + '\'' +
           '}';
    }
}
