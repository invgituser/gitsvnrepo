/**
 * CustomFieldListValue.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class CustomFieldListValue  implements java.io.Serializable {
    private short listValueId;

    private java.lang.String listValue;

    public CustomFieldListValue() {
    }

    public CustomFieldListValue(
           short listValueId,
           java.lang.String listValue) {
           this.listValueId = listValueId;
           this.listValue = listValue;
    }


    /**
     * Gets the listValueId value for this CustomFieldListValue.
     * 
     * @return listValueId
     */
    public short getListValueId() {
        return listValueId;
    }


    /**
     * Sets the listValueId value for this CustomFieldListValue.
     * 
     * @param listValueId
     */
    public void setListValueId(short listValueId) {
        this.listValueId = listValueId;
    }


    /**
     * Gets the listValue value for this CustomFieldListValue.
     * 
     * @return listValue
     */
    public java.lang.String getListValue() {
        return listValue;
    }


    /**
     * Sets the listValue value for this CustomFieldListValue.
     * 
     * @param listValue
     */
    public void setListValue(java.lang.String listValue) {
        this.listValue = listValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomFieldListValue)) return false;
        CustomFieldListValue other = (CustomFieldListValue) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.listValueId == other.getListValueId() &&
            ((this.listValue==null && other.getListValue()==null) || 
             (this.listValue!=null &&
              this.listValue.equals(other.getListValue())));
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
        _hashCode += getListValueId();
        if (getListValue() != null) {
            _hashCode += getListValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CustomFieldListValue.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listValueId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ListValueId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ListValue"));
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
