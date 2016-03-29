/**
 * AccountMessageResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AccountMessageResult  implements java.io.Serializable {
    private int messageId;

    private java.lang.String accountNumber;

    private short messageTypeCode;

    private java.lang.String messageDescription;

    private java.util.Calendar creationDateTime;

    private short createdByUserId;

    private java.lang.String webUserId;

    public AccountMessageResult() {
    }

    public AccountMessageResult(
           int messageId,
           java.lang.String accountNumber,
           short messageTypeCode,
           java.lang.String messageDescription,
           java.util.Calendar creationDateTime,
           short createdByUserId,
           java.lang.String webUserId) {
           this.messageId = messageId;
           this.accountNumber = accountNumber;
           this.messageTypeCode = messageTypeCode;
           this.messageDescription = messageDescription;
           this.creationDateTime = creationDateTime;
           this.createdByUserId = createdByUserId;
           this.webUserId = webUserId;
    }


    /**
     * Gets the messageId value for this AccountMessageResult.
     * 
     * @return messageId
     */
    public int getMessageId() {
        return messageId;
    }


    /**
     * Sets the messageId value for this AccountMessageResult.
     * 
     * @param messageId
     */
    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }


    /**
     * Gets the accountNumber value for this AccountMessageResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountMessageResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the messageTypeCode value for this AccountMessageResult.
     * 
     * @return messageTypeCode
     */
    public short getMessageTypeCode() {
        return messageTypeCode;
    }


    /**
     * Sets the messageTypeCode value for this AccountMessageResult.
     * 
     * @param messageTypeCode
     */
    public void setMessageTypeCode(short messageTypeCode) {
        this.messageTypeCode = messageTypeCode;
    }


    /**
     * Gets the messageDescription value for this AccountMessageResult.
     * 
     * @return messageDescription
     */
    public java.lang.String getMessageDescription() {
        return messageDescription;
    }


    /**
     * Sets the messageDescription value for this AccountMessageResult.
     * 
     * @param messageDescription
     */
    public void setMessageDescription(java.lang.String messageDescription) {
        this.messageDescription = messageDescription;
    }


    /**
     * Gets the creationDateTime value for this AccountMessageResult.
     * 
     * @return creationDateTime
     */
    public java.util.Calendar getCreationDateTime() {
        return creationDateTime;
    }


    /**
     * Sets the creationDateTime value for this AccountMessageResult.
     * 
     * @param creationDateTime
     */
    public void setCreationDateTime(java.util.Calendar creationDateTime) {
        this.creationDateTime = creationDateTime;
    }


    /**
     * Gets the createdByUserId value for this AccountMessageResult.
     * 
     * @return createdByUserId
     */
    public short getCreatedByUserId() {
        return createdByUserId;
    }


    /**
     * Sets the createdByUserId value for this AccountMessageResult.
     * 
     * @param createdByUserId
     */
    public void setCreatedByUserId(short createdByUserId) {
        this.createdByUserId = createdByUserId;
    }


    /**
     * Gets the webUserId value for this AccountMessageResult.
     * 
     * @return webUserId
     */
    public java.lang.String getWebUserId() {
        return webUserId;
    }


    /**
     * Sets the webUserId value for this AccountMessageResult.
     * 
     * @param webUserId
     */
    public void setWebUserId(java.lang.String webUserId) {
        this.webUserId = webUserId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountMessageResult)) return false;
        AccountMessageResult other = (AccountMessageResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.messageId == other.getMessageId() &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.messageTypeCode == other.getMessageTypeCode() &&
            ((this.messageDescription==null && other.getMessageDescription()==null) || 
             (this.messageDescription!=null &&
              this.messageDescription.equals(other.getMessageDescription()))) &&
            ((this.creationDateTime==null && other.getCreationDateTime()==null) || 
             (this.creationDateTime!=null &&
              this.creationDateTime.equals(other.getCreationDateTime()))) &&
            this.createdByUserId == other.getCreatedByUserId() &&
            ((this.webUserId==null && other.getWebUserId()==null) || 
             (this.webUserId!=null &&
              this.webUserId.equals(other.getWebUserId())));
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
        _hashCode += getMessageId();
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getMessageTypeCode();
        if (getMessageDescription() != null) {
            _hashCode += getMessageDescription().hashCode();
        }
        if (getCreationDateTime() != null) {
            _hashCode += getCreationDateTime().hashCode();
        }
        _hashCode += getCreatedByUserId();
        if (getWebUserId() != null) {
            _hashCode += getWebUserId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountMessageResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MessageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("messageTypeCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MessageTypeCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("messageDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MessageDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creationDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CreationDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createdByUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CreatedByUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
