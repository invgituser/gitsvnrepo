/**
 * UpdateCustomFieldsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class UpdateCustomFieldsRequest  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.AccountCustomFieldsRequest[] accountCustomFields;

    public UpdateCustomFieldsRequest() {
    }

    public UpdateCustomFieldsRequest(
           com.ws.gemini.wsdl.account.AccountCustomFieldsRequest[] accountCustomFields) {
           this.accountCustomFields = accountCustomFields;
    }


    /**
     * Gets the accountCustomFields value for this UpdateCustomFieldsRequest.
     * 
     * @return accountCustomFields
     */
    public com.ws.gemini.wsdl.account.AccountCustomFieldsRequest[] getAccountCustomFields() {
        return accountCustomFields;
    }


    /**
     * Sets the accountCustomFields value for this UpdateCustomFieldsRequest.
     * 
     * @param accountCustomFields
     */
    public void setAccountCustomFields(com.ws.gemini.wsdl.account.AccountCustomFieldsRequest[] accountCustomFields) {
        this.accountCustomFields = accountCustomFields;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateCustomFieldsRequest)) return false;
        UpdateCustomFieldsRequest other = (UpdateCustomFieldsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountCustomFields==null && other.getAccountCustomFields()==null) || 
             (this.accountCustomFields!=null &&
              java.util.Arrays.equals(this.accountCustomFields, other.getAccountCustomFields())));
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
        if (getAccountCustomFields() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccountCustomFields());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccountCustomFields(), i);
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
        new org.apache.axis.description.TypeDesc(UpdateCustomFieldsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateCustomFieldsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCustomFields");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFields"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountCustomFieldsRequest"));
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
