/**
 * StatementbyFormCategoryRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class StatementbyFormCategoryRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String formCategoryName;

    private org.apache.axis.types.UnsignedByte accountLinkType;

    private org.apache.axis.types.UnsignedByte deliveryMode;

    public StatementbyFormCategoryRequest() {
    }

    public StatementbyFormCategoryRequest(
           java.lang.String accountNumber,
           java.lang.String formCategoryName,
           org.apache.axis.types.UnsignedByte accountLinkType,
           org.apache.axis.types.UnsignedByte deliveryMode) {
           this.accountNumber = accountNumber;
           this.formCategoryName = formCategoryName;
           this.accountLinkType = accountLinkType;
           this.deliveryMode = deliveryMode;
    }


    /**
     * Gets the accountNumber value for this StatementbyFormCategoryRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this StatementbyFormCategoryRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the formCategoryName value for this StatementbyFormCategoryRequest.
     * 
     * @return formCategoryName
     */
    public java.lang.String getFormCategoryName() {
        return formCategoryName;
    }


    /**
     * Sets the formCategoryName value for this StatementbyFormCategoryRequest.
     * 
     * @param formCategoryName
     */
    public void setFormCategoryName(java.lang.String formCategoryName) {
        this.formCategoryName = formCategoryName;
    }


    /**
     * Gets the accountLinkType value for this StatementbyFormCategoryRequest.
     * 
     * @return accountLinkType
     */
    public org.apache.axis.types.UnsignedByte getAccountLinkType() {
        return accountLinkType;
    }


    /**
     * Sets the accountLinkType value for this StatementbyFormCategoryRequest.
     * 
     * @param accountLinkType
     */
    public void setAccountLinkType(org.apache.axis.types.UnsignedByte accountLinkType) {
        this.accountLinkType = accountLinkType;
    }


    /**
     * Gets the deliveryMode value for this StatementbyFormCategoryRequest.
     * 
     * @return deliveryMode
     */
    public org.apache.axis.types.UnsignedByte getDeliveryMode() {
        return deliveryMode;
    }


    /**
     * Sets the deliveryMode value for this StatementbyFormCategoryRequest.
     * 
     * @param deliveryMode
     */
    public void setDeliveryMode(org.apache.axis.types.UnsignedByte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StatementbyFormCategoryRequest)) return false;
        StatementbyFormCategoryRequest other = (StatementbyFormCategoryRequest) obj;
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
            ((this.formCategoryName==null && other.getFormCategoryName()==null) || 
             (this.formCategoryName!=null &&
              this.formCategoryName.equals(other.getFormCategoryName()))) &&
            ((this.accountLinkType==null && other.getAccountLinkType()==null) || 
             (this.accountLinkType!=null &&
              this.accountLinkType.equals(other.getAccountLinkType()))) &&
            ((this.deliveryMode==null && other.getDeliveryMode()==null) || 
             (this.deliveryMode!=null &&
              this.deliveryMode.equals(other.getDeliveryMode())));
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
        if (getFormCategoryName() != null) {
            _hashCode += getFormCategoryName().hashCode();
        }
        if (getAccountLinkType() != null) {
            _hashCode += getAccountLinkType().hashCode();
        }
        if (getDeliveryMode() != null) {
            _hashCode += getDeliveryMode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StatementbyFormCategoryRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StatementbyFormCategoryRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formCategoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FormCategoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountLinkType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountLinkType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DeliveryMode"));
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
