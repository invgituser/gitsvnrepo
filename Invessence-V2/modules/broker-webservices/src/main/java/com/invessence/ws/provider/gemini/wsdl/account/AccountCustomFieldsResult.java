/**
 * AccountCustomFieldsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountCustomFieldsResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private org.apache.axis.types.UnsignedByte accountCustomFieldDefinitionSortOrder;

    private short accountCustomFieldId;

    private java.lang.String accountCustomFieldDefinition;

    private java.lang.String accountCustomFieldValue;

    public AccountCustomFieldsResult() {
    }

    public AccountCustomFieldsResult(
           java.lang.String accountNumber,
           org.apache.axis.types.UnsignedByte accountCustomFieldDefinitionSortOrder,
           short accountCustomFieldId,
           java.lang.String accountCustomFieldDefinition,
           java.lang.String accountCustomFieldValue) {
           this.accountNumber = accountNumber;
           this.accountCustomFieldDefinitionSortOrder = accountCustomFieldDefinitionSortOrder;
           this.accountCustomFieldId = accountCustomFieldId;
           this.accountCustomFieldDefinition = accountCustomFieldDefinition;
           this.accountCustomFieldValue = accountCustomFieldValue;
    }


    /**
     * Gets the accountNumber value for this AccountCustomFieldsResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountCustomFieldsResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the accountCustomFieldDefinitionSortOrder value for this AccountCustomFieldsResult.
     * 
     * @return accountCustomFieldDefinitionSortOrder
     */
    public org.apache.axis.types.UnsignedByte getAccountCustomFieldDefinitionSortOrder() {
        return accountCustomFieldDefinitionSortOrder;
    }


    /**
     * Sets the accountCustomFieldDefinitionSortOrder value for this AccountCustomFieldsResult.
     * 
     * @param accountCustomFieldDefinitionSortOrder
     */
    public void setAccountCustomFieldDefinitionSortOrder(org.apache.axis.types.UnsignedByte accountCustomFieldDefinitionSortOrder) {
        this.accountCustomFieldDefinitionSortOrder = accountCustomFieldDefinitionSortOrder;
    }


    /**
     * Gets the accountCustomFieldId value for this AccountCustomFieldsResult.
     * 
     * @return accountCustomFieldId
     */
    public short getAccountCustomFieldId() {
        return accountCustomFieldId;
    }


    /**
     * Sets the accountCustomFieldId value for this AccountCustomFieldsResult.
     * 
     * @param accountCustomFieldId
     */
    public void setAccountCustomFieldId(short accountCustomFieldId) {
        this.accountCustomFieldId = accountCustomFieldId;
    }


    /**
     * Gets the accountCustomFieldDefinition value for this AccountCustomFieldsResult.
     * 
     * @return accountCustomFieldDefinition
     */
    public java.lang.String getAccountCustomFieldDefinition() {
        return accountCustomFieldDefinition;
    }


    /**
     * Sets the accountCustomFieldDefinition value for this AccountCustomFieldsResult.
     * 
     * @param accountCustomFieldDefinition
     */
    public void setAccountCustomFieldDefinition(java.lang.String accountCustomFieldDefinition) {
        this.accountCustomFieldDefinition = accountCustomFieldDefinition;
    }


    /**
     * Gets the accountCustomFieldValue value for this AccountCustomFieldsResult.
     * 
     * @return accountCustomFieldValue
     */
    public java.lang.String getAccountCustomFieldValue() {
        return accountCustomFieldValue;
    }


    /**
     * Sets the accountCustomFieldValue value for this AccountCustomFieldsResult.
     * 
     * @param accountCustomFieldValue
     */
    public void setAccountCustomFieldValue(java.lang.String accountCustomFieldValue) {
        this.accountCustomFieldValue = accountCustomFieldValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountCustomFieldsResult)) return false;
        AccountCustomFieldsResult other = (AccountCustomFieldsResult) obj;
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
            ((this.accountCustomFieldDefinitionSortOrder==null && other.getAccountCustomFieldDefinitionSortOrder()==null) || 
             (this.accountCustomFieldDefinitionSortOrder!=null &&
              this.accountCustomFieldDefinitionSortOrder.equals(other.getAccountCustomFieldDefinitionSortOrder()))) &&
            this.accountCustomFieldId == other.getAccountCustomFieldId() &&
            ((this.accountCustomFieldDefinition==null && other.getAccountCustomFieldDefinition()==null) || 
             (this.accountCustomFieldDefinition!=null &&
              this.accountCustomFieldDefinition.equals(other.getAccountCustomFieldDefinition()))) &&
            ((this.accountCustomFieldValue==null && other.getAccountCustomFieldValue()==null) || 
             (this.accountCustomFieldValue!=null &&
              this.accountCustomFieldValue.equals(other.getAccountCustomFieldValue())));
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
        if (getAccountCustomFieldDefinitionSortOrder() != null) {
            _hashCode += getAccountCustomFieldDefinitionSortOrder().hashCode();
        }
        _hashCode += getAccountCustomFieldId();
        if (getAccountCustomFieldDefinition() != null) {
            _hashCode += getAccountCustomFieldDefinition().hashCode();
        }
        if (getAccountCustomFieldValue() != null) {
            _hashCode += getAccountCustomFieldValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountCustomFieldsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCustomFieldDefinitionSortOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldDefinitionSortOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCustomFieldId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCustomFieldDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCustomFieldValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldValue"));
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
