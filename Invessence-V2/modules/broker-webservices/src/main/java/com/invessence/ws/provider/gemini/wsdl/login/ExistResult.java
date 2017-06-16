/**
 * ExistResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.login;

public class ExistResult  implements java.io.Serializable {
    private boolean isExist;

    private com.invessence.ws.provider.gemini.wsdl.login.Status errorStatus;

    public ExistResult() {
    }

    public ExistResult(
           boolean isExist,
           com.invessence.ws.provider.gemini.wsdl.login.Status errorStatus) {
           this.isExist = isExist;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the isExist value for this ExistResult.
     * 
     * @return isExist
     */
    public boolean isIsExist() {
        return isExist;
    }


    /**
     * Sets the isExist value for this ExistResult.
     * 
     * @param isExist
     */
    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }


    /**
     * Gets the errorStatus value for this ExistResult.
     * 
     * @return errorStatus
     */
    public com.invessence.ws.provider.gemini.wsdl.login.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this ExistResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.invessence.ws.provider.gemini.wsdl.login.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExistResult)) return false;
        ExistResult other = (ExistResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.isExist == other.isIsExist() &&
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
        _hashCode += (isIsExist() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExistResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExistResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isExist");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsExist"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
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
