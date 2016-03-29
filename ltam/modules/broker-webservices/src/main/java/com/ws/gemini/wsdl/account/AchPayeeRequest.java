/**
 * AchPayeeRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AchPayeeRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private int achPayeeId;

    private java.lang.String bankName;

    private java.lang.String bankRoutingNumber;

    private java.lang.String bankAccountNumber;

    private java.lang.String nameOnAccount;

    private org.apache.axis.types.UnsignedByte bankAccountType;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    public AchPayeeRequest() {
    }

    public AchPayeeRequest(
           java.lang.String accountNumber,
           int achPayeeId,
           java.lang.String bankName,
           java.lang.String bankRoutingNumber,
           java.lang.String bankAccountNumber,
           java.lang.String nameOnAccount,
           org.apache.axis.types.UnsignedByte bankAccountType,
           org.apache.axis.types.UnsignedByte accountPayeeId) {
           this.accountNumber = accountNumber;
           this.achPayeeId = achPayeeId;
           this.bankName = bankName;
           this.bankRoutingNumber = bankRoutingNumber;
           this.bankAccountNumber = bankAccountNumber;
           this.nameOnAccount = nameOnAccount;
           this.bankAccountType = bankAccountType;
           this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the accountNumber value for this AchPayeeRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AchPayeeRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the achPayeeId value for this AchPayeeRequest.
     * 
     * @return achPayeeId
     */
    public int getAchPayeeId() {
        return achPayeeId;
    }


    /**
     * Sets the achPayeeId value for this AchPayeeRequest.
     * 
     * @param achPayeeId
     */
    public void setAchPayeeId(int achPayeeId) {
        this.achPayeeId = achPayeeId;
    }


    /**
     * Gets the bankName value for this AchPayeeRequest.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this AchPayeeRequest.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the bankRoutingNumber value for this AchPayeeRequest.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this AchPayeeRequest.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the bankAccountNumber value for this AchPayeeRequest.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this AchPayeeRequest.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the nameOnAccount value for this AchPayeeRequest.
     * 
     * @return nameOnAccount
     */
    public java.lang.String getNameOnAccount() {
        return nameOnAccount;
    }


    /**
     * Sets the nameOnAccount value for this AchPayeeRequest.
     * 
     * @param nameOnAccount
     */
    public void setNameOnAccount(java.lang.String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }


    /**
     * Gets the bankAccountType value for this AchPayeeRequest.
     * 
     * @return bankAccountType
     */
    public org.apache.axis.types.UnsignedByte getBankAccountType() {
        return bankAccountType;
    }


    /**
     * Sets the bankAccountType value for this AchPayeeRequest.
     * 
     * @param bankAccountType
     */
    public void setBankAccountType(org.apache.axis.types.UnsignedByte bankAccountType) {
        this.bankAccountType = bankAccountType;
    }


    /**
     * Gets the accountPayeeId value for this AchPayeeRequest.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this AchPayeeRequest.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AchPayeeRequest)) return false;
        AchPayeeRequest other = (AchPayeeRequest) obj;
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
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId())));
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
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AchPayeeRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeRequest"));
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
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
