/**
 * EdocsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class EdocsResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String emailAddress;

    private com.invessence.ws.provider.gemini.wsdl.account.EdocsDeliveryModeResult[] edocsDeliveryMode;

    public EdocsResult() {
    }

    public EdocsResult(
           java.lang.String accountNumber,
           java.lang.String emailAddress,
           com.invessence.ws.provider.gemini.wsdl.account.EdocsDeliveryModeResult[] edocsDeliveryMode) {
           this.accountNumber = accountNumber;
           this.emailAddress = emailAddress;
           this.edocsDeliveryMode = edocsDeliveryMode;
    }


    /**
     * Gets the accountNumber value for this EdocsResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this EdocsResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the emailAddress value for this EdocsResult.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this EdocsResult.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the edocsDeliveryMode value for this EdocsResult.
     * 
     * @return edocsDeliveryMode
     */
    public com.invessence.ws.provider.gemini.wsdl.account.EdocsDeliveryModeResult[] getEdocsDeliveryMode() {
        return edocsDeliveryMode;
    }


    /**
     * Sets the edocsDeliveryMode value for this EdocsResult.
     * 
     * @param edocsDeliveryMode
     */
    public void setEdocsDeliveryMode(com.invessence.ws.provider.gemini.wsdl.account.EdocsDeliveryModeResult[] edocsDeliveryMode) {
        this.edocsDeliveryMode = edocsDeliveryMode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EdocsResult)) return false;
        EdocsResult other = (EdocsResult) obj;
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
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.edocsDeliveryMode==null && other.getEdocsDeliveryMode()==null) || 
             (this.edocsDeliveryMode!=null &&
              java.util.Arrays.equals(this.edocsDeliveryMode, other.getEdocsDeliveryMode())));
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
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getEdocsDeliveryMode() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEdocsDeliveryMode());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEdocsDeliveryMode(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EdocsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
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
        elemField.setFieldName("edocsDeliveryMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult"));
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
