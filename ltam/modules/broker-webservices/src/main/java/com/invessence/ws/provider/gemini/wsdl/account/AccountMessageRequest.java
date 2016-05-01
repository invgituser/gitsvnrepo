/**
 * AccountMessageRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountMessageRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short messageTypeCode;

    private java.lang.String messageDescription;

    private java.lang.String webUserId;

    private int invitationGiftReferenceId;

    public AccountMessageRequest() {
    }

    public AccountMessageRequest(
           java.lang.String accountNumber,
           short messageTypeCode,
           java.lang.String messageDescription,
           java.lang.String webUserId,
           int invitationGiftReferenceId) {
           this.accountNumber = accountNumber;
           this.messageTypeCode = messageTypeCode;
           this.messageDescription = messageDescription;
           this.webUserId = webUserId;
           this.invitationGiftReferenceId = invitationGiftReferenceId;
    }


    /**
     * Gets the accountNumber value for this AccountMessageRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountMessageRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the messageTypeCode value for this AccountMessageRequest.
     * 
     * @return messageTypeCode
     */
    public short getMessageTypeCode() {
        return messageTypeCode;
    }


    /**
     * Sets the messageTypeCode value for this AccountMessageRequest.
     * 
     * @param messageTypeCode
     */
    public void setMessageTypeCode(short messageTypeCode) {
        this.messageTypeCode = messageTypeCode;
    }


    /**
     * Gets the messageDescription value for this AccountMessageRequest.
     * 
     * @return messageDescription
     */
    public java.lang.String getMessageDescription() {
        return messageDescription;
    }


    /**
     * Sets the messageDescription value for this AccountMessageRequest.
     * 
     * @param messageDescription
     */
    public void setMessageDescription(java.lang.String messageDescription) {
        this.messageDescription = messageDescription;
    }


    /**
     * Gets the webUserId value for this AccountMessageRequest.
     * 
     * @return webUserId
     */
    public java.lang.String getWebUserId() {
        return webUserId;
    }


    /**
     * Sets the webUserId value for this AccountMessageRequest.
     * 
     * @param webUserId
     */
    public void setWebUserId(java.lang.String webUserId) {
        this.webUserId = webUserId;
    }


    /**
     * Gets the invitationGiftReferenceId value for this AccountMessageRequest.
     * 
     * @return invitationGiftReferenceId
     */
    public int getInvitationGiftReferenceId() {
        return invitationGiftReferenceId;
    }


    /**
     * Sets the invitationGiftReferenceId value for this AccountMessageRequest.
     * 
     * @param invitationGiftReferenceId
     */
    public void setInvitationGiftReferenceId(int invitationGiftReferenceId) {
        this.invitationGiftReferenceId = invitationGiftReferenceId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountMessageRequest)) return false;
        AccountMessageRequest other = (AccountMessageRequest) obj;
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
            this.messageTypeCode == other.getMessageTypeCode() &&
            ((this.messageDescription==null && other.getMessageDescription()==null) || 
             (this.messageDescription!=null &&
              this.messageDescription.equals(other.getMessageDescription()))) &&
            ((this.webUserId==null && other.getWebUserId()==null) || 
             (this.webUserId!=null &&
              this.webUserId.equals(other.getWebUserId()))) &&
            this.invitationGiftReferenceId == other.getInvitationGiftReferenceId();
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
        _hashCode += getMessageTypeCode();
        if (getMessageDescription() != null) {
            _hashCode += getMessageDescription().hashCode();
        }
        if (getWebUserId() != null) {
            _hashCode += getWebUserId().hashCode();
        }
        _hashCode += getInvitationGiftReferenceId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountMessageRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountMessageRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("webUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invitationGiftReferenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InvitationGiftReferenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
