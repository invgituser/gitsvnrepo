/**
 * AccountCustomFieldRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountCustomFieldRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String customFieldDefinition;

    private java.lang.String customFieldValue;

    public AccountCustomFieldRequest() {
    }

    public AccountCustomFieldRequest(
           java.lang.String accountNumber,
           java.lang.String customFieldDefinition,
           java.lang.String customFieldValue) {
           this.accountNumber = accountNumber;
           this.customFieldDefinition = customFieldDefinition;
           this.customFieldValue = customFieldValue;
    }


    /**
     * Gets the accountNumber value for this AccountCustomFieldRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountCustomFieldRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the customFieldDefinition value for this AccountCustomFieldRequest.
     * 
     * @return customFieldDefinition
     */
    public java.lang.String getCustomFieldDefinition() {
        return customFieldDefinition;
    }


    /**
     * Sets the customFieldDefinition value for this AccountCustomFieldRequest.
     * 
     * @param customFieldDefinition
     */
    public void setCustomFieldDefinition(java.lang.String customFieldDefinition) {
        this.customFieldDefinition = customFieldDefinition;
    }


    /**
     * Gets the customFieldValue value for this AccountCustomFieldRequest.
     * 
     * @return customFieldValue
     */
    public java.lang.String getCustomFieldValue() {
        return customFieldValue;
    }


    /**
     * Sets the customFieldValue value for this AccountCustomFieldRequest.
     * 
     * @param customFieldValue
     */
    public void setCustomFieldValue(java.lang.String customFieldValue) {
        this.customFieldValue = customFieldValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountCustomFieldRequest)) return false;
        AccountCustomFieldRequest other = (AccountCustomFieldRequest) obj;
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
            ((this.customFieldDefinition==null && other.getCustomFieldDefinition()==null) || 
             (this.customFieldDefinition!=null &&
              this.customFieldDefinition.equals(other.getCustomFieldDefinition()))) &&
            ((this.customFieldValue==null && other.getCustomFieldValue()==null) || 
             (this.customFieldValue!=null &&
              this.customFieldValue.equals(other.getCustomFieldValue())));
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
        if (getCustomFieldDefinition() != null) {
            _hashCode += getCustomFieldDefinition().hashCode();
        }
        if (getCustomFieldValue() != null) {
            _hashCode += getCustomFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountCustomFieldRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldValue"));
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
