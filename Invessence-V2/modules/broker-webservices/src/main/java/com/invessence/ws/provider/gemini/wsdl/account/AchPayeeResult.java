/**
 * AchPayeeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AchPayeeResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private int achPayeeId;

    private java.lang.String bankName;

    private java.lang.String bankRoutingNumber;

    private java.lang.String bankAccountNumber;

    private java.lang.String nameOnAccount;

    private org.apache.axis.types.UnsignedByte bankAccountType;

    private org.apache.axis.types.UnsignedByte preferredCurrencyId;

    private java.util.Calendar preNotificationDate;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    private short powerAgentUserId;

    private short isAccountPayeeBeingUsed;

    private com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus;

    public AchPayeeResult() {
    }

    public AchPayeeResult(
           java.lang.String accountNumber,
           int achPayeeId,
           java.lang.String bankName,
           java.lang.String bankRoutingNumber,
           java.lang.String bankAccountNumber,
           java.lang.String nameOnAccount,
           org.apache.axis.types.UnsignedByte bankAccountType,
           org.apache.axis.types.UnsignedByte preferredCurrencyId,
           java.util.Calendar preNotificationDate,
           org.apache.axis.types.UnsignedByte accountPayeeId,
           short powerAgentUserId,
           short isAccountPayeeBeingUsed,
           com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
           this.accountNumber = accountNumber;
           this.achPayeeId = achPayeeId;
           this.bankName = bankName;
           this.bankRoutingNumber = bankRoutingNumber;
           this.bankAccountNumber = bankAccountNumber;
           this.nameOnAccount = nameOnAccount;
           this.bankAccountType = bankAccountType;
           this.preferredCurrencyId = preferredCurrencyId;
           this.preNotificationDate = preNotificationDate;
           this.accountPayeeId = accountPayeeId;
           this.powerAgentUserId = powerAgentUserId;
           this.isAccountPayeeBeingUsed = isAccountPayeeBeingUsed;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountNumber value for this AchPayeeResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AchPayeeResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the achPayeeId value for this AchPayeeResult.
     * 
     * @return achPayeeId
     */
    public int getAchPayeeId() {
        return achPayeeId;
    }


    /**
     * Sets the achPayeeId value for this AchPayeeResult.
     * 
     * @param achPayeeId
     */
    public void setAchPayeeId(int achPayeeId) {
        this.achPayeeId = achPayeeId;
    }


    /**
     * Gets the bankName value for this AchPayeeResult.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this AchPayeeResult.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the bankRoutingNumber value for this AchPayeeResult.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this AchPayeeResult.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the bankAccountNumber value for this AchPayeeResult.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this AchPayeeResult.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the nameOnAccount value for this AchPayeeResult.
     * 
     * @return nameOnAccount
     */
    public java.lang.String getNameOnAccount() {
        return nameOnAccount;
    }


    /**
     * Sets the nameOnAccount value for this AchPayeeResult.
     * 
     * @param nameOnAccount
     */
    public void setNameOnAccount(java.lang.String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }


    /**
     * Gets the bankAccountType value for this AchPayeeResult.
     * 
     * @return bankAccountType
     */
    public org.apache.axis.types.UnsignedByte getBankAccountType() {
        return bankAccountType;
    }


    /**
     * Sets the bankAccountType value for this AchPayeeResult.
     * 
     * @param bankAccountType
     */
    public void setBankAccountType(org.apache.axis.types.UnsignedByte bankAccountType) {
        this.bankAccountType = bankAccountType;
    }


    /**
     * Gets the preferredCurrencyId value for this AchPayeeResult.
     * 
     * @return preferredCurrencyId
     */
    public org.apache.axis.types.UnsignedByte getPreferredCurrencyId() {
        return preferredCurrencyId;
    }


    /**
     * Sets the preferredCurrencyId value for this AchPayeeResult.
     * 
     * @param preferredCurrencyId
     */
    public void setPreferredCurrencyId(org.apache.axis.types.UnsignedByte preferredCurrencyId) {
        this.preferredCurrencyId = preferredCurrencyId;
    }


    /**
     * Gets the preNotificationDate value for this AchPayeeResult.
     * 
     * @return preNotificationDate
     */
    public java.util.Calendar getPreNotificationDate() {
        return preNotificationDate;
    }


    /**
     * Sets the preNotificationDate value for this AchPayeeResult.
     * 
     * @param preNotificationDate
     */
    public void setPreNotificationDate(java.util.Calendar preNotificationDate) {
        this.preNotificationDate = preNotificationDate;
    }


    /**
     * Gets the accountPayeeId value for this AchPayeeResult.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this AchPayeeResult.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the powerAgentUserId value for this AchPayeeResult.
     * 
     * @return powerAgentUserId
     */
    public short getPowerAgentUserId() {
        return powerAgentUserId;
    }


    /**
     * Sets the powerAgentUserId value for this AchPayeeResult.
     * 
     * @param powerAgentUserId
     */
    public void setPowerAgentUserId(short powerAgentUserId) {
        this.powerAgentUserId = powerAgentUserId;
    }


    /**
     * Gets the isAccountPayeeBeingUsed value for this AchPayeeResult.
     * 
     * @return isAccountPayeeBeingUsed
     */
    public short getIsAccountPayeeBeingUsed() {
        return isAccountPayeeBeingUsed;
    }


    /**
     * Sets the isAccountPayeeBeingUsed value for this AchPayeeResult.
     * 
     * @param isAccountPayeeBeingUsed
     */
    public void setIsAccountPayeeBeingUsed(short isAccountPayeeBeingUsed) {
        this.isAccountPayeeBeingUsed = isAccountPayeeBeingUsed;
    }


    /**
     * Gets the errorStatus value for this AchPayeeResult.
     * 
     * @return errorStatus
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this AchPayeeResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AchPayeeResult)) return false;
        AchPayeeResult other = (AchPayeeResult) obj;
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
            this.achPayeeId == other.getAchPayeeId() &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.bankRoutingNumber==null && other.getBankRoutingNumber()==null) || 
             (this.bankRoutingNumber!=null &&
              this.bankRoutingNumber.equals(other.getBankRoutingNumber()))) &&
            ((this.bankAccountNumber==null && other.getBankAccountNumber()==null) || 
             (this.bankAccountNumber!=null &&
              this.bankAccountNumber.equals(other.getBankAccountNumber()))) &&
            ((this.nameOnAccount==null && other.getNameOnAccount()==null) || 
             (this.nameOnAccount!=null &&
              this.nameOnAccount.equals(other.getNameOnAccount()))) &&
            ((this.bankAccountType==null && other.getBankAccountType()==null) || 
             (this.bankAccountType!=null &&
              this.bankAccountType.equals(other.getBankAccountType()))) &&
            ((this.preferredCurrencyId==null && other.getPreferredCurrencyId()==null) || 
             (this.preferredCurrencyId!=null &&
              this.preferredCurrencyId.equals(other.getPreferredCurrencyId()))) &&
            ((this.preNotificationDate==null && other.getPreNotificationDate()==null) || 
             (this.preNotificationDate!=null &&
              this.preNotificationDate.equals(other.getPreNotificationDate()))) &&
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId()))) &&
            this.powerAgentUserId == other.getPowerAgentUserId() &&
            this.isAccountPayeeBeingUsed == other.getIsAccountPayeeBeingUsed() &&
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
        _hashCode += getAchPayeeId();
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getBankRoutingNumber() != null) {
            _hashCode += getBankRoutingNumber().hashCode();
        }
        if (getBankAccountNumber() != null) {
            _hashCode += getBankAccountNumber().hashCode();
        }
        if (getNameOnAccount() != null) {
            _hashCode += getNameOnAccount().hashCode();
        }
        if (getBankAccountType() != null) {
            _hashCode += getBankAccountType().hashCode();
        }
        if (getPreferredCurrencyId() != null) {
            _hashCode += getPreferredCurrencyId().hashCode();
        }
        if (getPreNotificationDate() != null) {
            _hashCode += getPreNotificationDate().hashCode();
        }
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        _hashCode += getPowerAgentUserId();
        _hashCode += getIsAccountPayeeBeingUsed();
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AchPayeeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("achPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankRoutingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankRoutingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameOnAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameOnAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preferredCurrencyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PreferredCurrencyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preNotificationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PreNotificationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("powerAgentUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PowerAgentUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAccountPayeeBeingUsed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsAccountPayeeBeingUsed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
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
        return "AchPayeeResult{" +
           "accountNumber='" + accountNumber + '\'' +
           ", achPayeeId=" + achPayeeId +
           ", bankName='" + bankName + '\'' +
           ", bankRoutingNumber='" + bankRoutingNumber + '\'' +
           ", bankAccountNumber='" + bankAccountNumber + '\'' +
           ", nameOnAccount='" + nameOnAccount + '\'' +
           ", bankAccountType=" + bankAccountType +
           ", preferredCurrencyId=" + preferredCurrencyId +
           ", preNotificationDate=" + preNotificationDate +
           ", accountPayeeId=" + accountPayeeId +
           ", powerAgentUserId=" + powerAgentUserId +
           ", isAccountPayeeBeingUsed=" + isAccountPayeeBeingUsed +
           ", errorStatus=" + errorStatus +
           '}';
    }
}
