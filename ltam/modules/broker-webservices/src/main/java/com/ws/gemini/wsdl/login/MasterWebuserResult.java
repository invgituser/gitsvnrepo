/**
 * MasterWebuserResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.login;

public class MasterWebuserResult  implements java.io.Serializable {
    private java.lang.String userId;

    private java.lang.String password;

    private org.apache.axis.types.UnsignedByte userType;

    private java.math.BigDecimal masterFirmNumber;

    private java.lang.String branchNumber;

    private java.lang.String accountRepNumber;

    private boolean userStatus;

    private java.math.BigDecimal noOfUnsuccessfulLoginAttempts;

    private java.util.Calendar lastUpdatedDateTime;

    private java.lang.String emailAddress;

    private java.lang.String secretQuestion;

    private java.lang.String secretAnswer;

    private com.ws.gemini.wsdl.login.Status errorStatus;

    public MasterWebuserResult() {
    }

    public MasterWebuserResult(
           java.lang.String userId,
           java.lang.String password,
           org.apache.axis.types.UnsignedByte userType,
           java.math.BigDecimal masterFirmNumber,
           java.lang.String branchNumber,
           java.lang.String accountRepNumber,
           boolean userStatus,
           java.math.BigDecimal noOfUnsuccessfulLoginAttempts,
           java.util.Calendar lastUpdatedDateTime,
           java.lang.String emailAddress,
           java.lang.String secretQuestion,
           java.lang.String secretAnswer,
           com.ws.gemini.wsdl.login.Status errorStatus) {
           this.userId = userId;
           this.password = password;
           this.userType = userType;
           this.masterFirmNumber = masterFirmNumber;
           this.branchNumber = branchNumber;
           this.accountRepNumber = accountRepNumber;
           this.userStatus = userStatus;
           this.noOfUnsuccessfulLoginAttempts = noOfUnsuccessfulLoginAttempts;
           this.lastUpdatedDateTime = lastUpdatedDateTime;
           this.emailAddress = emailAddress;
           this.secretQuestion = secretQuestion;
           this.secretAnswer = secretAnswer;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the userId value for this MasterWebuserResult.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this MasterWebuserResult.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the password value for this MasterWebuserResult.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this MasterWebuserResult.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the userType value for this MasterWebuserResult.
     * 
     * @return userType
     */
    public org.apache.axis.types.UnsignedByte getUserType() {
        return userType;
    }


    /**
     * Sets the userType value for this MasterWebuserResult.
     * 
     * @param userType
     */
    public void setUserType(org.apache.axis.types.UnsignedByte userType) {
        this.userType = userType;
    }


    /**
     * Gets the masterFirmNumber value for this MasterWebuserResult.
     * 
     * @return masterFirmNumber
     */
    public java.math.BigDecimal getMasterFirmNumber() {
        return masterFirmNumber;
    }


    /**
     * Sets the masterFirmNumber value for this MasterWebuserResult.
     * 
     * @param masterFirmNumber
     */
    public void setMasterFirmNumber(java.math.BigDecimal masterFirmNumber) {
        this.masterFirmNumber = masterFirmNumber;
    }


    /**
     * Gets the branchNumber value for this MasterWebuserResult.
     * 
     * @return branchNumber
     */
    public java.lang.String getBranchNumber() {
        return branchNumber;
    }


    /**
     * Sets the branchNumber value for this MasterWebuserResult.
     * 
     * @param branchNumber
     */
    public void setBranchNumber(java.lang.String branchNumber) {
        this.branchNumber = branchNumber;
    }


    /**
     * Gets the accountRepNumber value for this MasterWebuserResult.
     * 
     * @return accountRepNumber
     */
    public java.lang.String getAccountRepNumber() {
        return accountRepNumber;
    }


    /**
     * Sets the accountRepNumber value for this MasterWebuserResult.
     * 
     * @param accountRepNumber
     */
    public void setAccountRepNumber(java.lang.String accountRepNumber) {
        this.accountRepNumber = accountRepNumber;
    }


    /**
     * Gets the userStatus value for this MasterWebuserResult.
     * 
     * @return userStatus
     */
    public boolean isUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this MasterWebuserResult.
     * 
     * @param userStatus
     */
    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the noOfUnsuccessfulLoginAttempts value for this MasterWebuserResult.
     * 
     * @return noOfUnsuccessfulLoginAttempts
     */
    public java.math.BigDecimal getNoOfUnsuccessfulLoginAttempts() {
        return noOfUnsuccessfulLoginAttempts;
    }


    /**
     * Sets the noOfUnsuccessfulLoginAttempts value for this MasterWebuserResult.
     * 
     * @param noOfUnsuccessfulLoginAttempts
     */
    public void setNoOfUnsuccessfulLoginAttempts(java.math.BigDecimal noOfUnsuccessfulLoginAttempts) {
        this.noOfUnsuccessfulLoginAttempts = noOfUnsuccessfulLoginAttempts;
    }


    /**
     * Gets the lastUpdatedDateTime value for this MasterWebuserResult.
     * 
     * @return lastUpdatedDateTime
     */
    public java.util.Calendar getLastUpdatedDateTime() {
        return lastUpdatedDateTime;
    }


    /**
     * Sets the lastUpdatedDateTime value for this MasterWebuserResult.
     * 
     * @param lastUpdatedDateTime
     */
    public void setLastUpdatedDateTime(java.util.Calendar lastUpdatedDateTime) {
        this.lastUpdatedDateTime = lastUpdatedDateTime;
    }


    /**
     * Gets the emailAddress value for this MasterWebuserResult.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this MasterWebuserResult.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the secretQuestion value for this MasterWebuserResult.
     * 
     * @return secretQuestion
     */
    public java.lang.String getSecretQuestion() {
        return secretQuestion;
    }


    /**
     * Sets the secretQuestion value for this MasterWebuserResult.
     * 
     * @param secretQuestion
     */
    public void setSecretQuestion(java.lang.String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }


    /**
     * Gets the secretAnswer value for this MasterWebuserResult.
     * 
     * @return secretAnswer
     */
    public java.lang.String getSecretAnswer() {
        return secretAnswer;
    }


    /**
     * Sets the secretAnswer value for this MasterWebuserResult.
     * 
     * @param secretAnswer
     */
    public void setSecretAnswer(java.lang.String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }


    /**
     * Gets the errorStatus value for this MasterWebuserResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.login.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this MasterWebuserResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.login.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MasterWebuserResult)) return false;
        MasterWebuserResult other = (MasterWebuserResult) obj;
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
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.userType==null && other.getUserType()==null) || 
             (this.userType!=null &&
              this.userType.equals(other.getUserType()))) &&
            ((this.masterFirmNumber==null && other.getMasterFirmNumber()==null) || 
             (this.masterFirmNumber!=null &&
              this.masterFirmNumber.equals(other.getMasterFirmNumber()))) &&
            ((this.branchNumber==null && other.getBranchNumber()==null) || 
             (this.branchNumber!=null &&
              this.branchNumber.equals(other.getBranchNumber()))) &&
            ((this.accountRepNumber==null && other.getAccountRepNumber()==null) || 
             (this.accountRepNumber!=null &&
              this.accountRepNumber.equals(other.getAccountRepNumber()))) &&
            this.userStatus == other.isUserStatus() &&
            ((this.noOfUnsuccessfulLoginAttempts==null && other.getNoOfUnsuccessfulLoginAttempts()==null) || 
             (this.noOfUnsuccessfulLoginAttempts!=null &&
              this.noOfUnsuccessfulLoginAttempts.equals(other.getNoOfUnsuccessfulLoginAttempts()))) &&
            ((this.lastUpdatedDateTime==null && other.getLastUpdatedDateTime()==null) || 
             (this.lastUpdatedDateTime!=null &&
              this.lastUpdatedDateTime.equals(other.getLastUpdatedDateTime()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.secretQuestion==null && other.getSecretQuestion()==null) || 
             (this.secretQuestion!=null &&
              this.secretQuestion.equals(other.getSecretQuestion()))) &&
            ((this.secretAnswer==null && other.getSecretAnswer()==null) || 
             (this.secretAnswer!=null &&
              this.secretAnswer.equals(other.getSecretAnswer()))) &&
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
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUserType() != null) {
            _hashCode += getUserType().hashCode();
        }
        if (getMasterFirmNumber() != null) {
            _hashCode += getMasterFirmNumber().hashCode();
        }
        if (getBranchNumber() != null) {
            _hashCode += getBranchNumber().hashCode();
        }
        if (getAccountRepNumber() != null) {
            _hashCode += getAccountRepNumber().hashCode();
        }
        _hashCode += (isUserStatus() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNoOfUnsuccessfulLoginAttempts() != null) {
            _hashCode += getNoOfUnsuccessfulLoginAttempts().hashCode();
        }
        if (getLastUpdatedDateTime() != null) {
            _hashCode += getLastUpdatedDateTime().hashCode();
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
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MasterWebuserResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterWebuserResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
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
        elemField.setFieldName("masterFirmNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterFirmNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("branchNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BranchNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRepNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountRepNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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

}
