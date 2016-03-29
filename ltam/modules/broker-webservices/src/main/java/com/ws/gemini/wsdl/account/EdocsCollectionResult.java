/**
 * EdocsCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class EdocsCollectionResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.EdocsResult[] edocs;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public EdocsCollectionResult() {
    }

    public EdocsCollectionResult(
           com.ws.gemini.wsdl.account.EdocsResult[] edocs,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.edocs = edocs;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the edocs value for this EdocsCollectionResult.
     * 
     * @return edocs
     */
    public com.ws.gemini.wsdl.account.EdocsResult[] getEdocs() {
        return edocs;
    }


    /**
     * Sets the edocs value for this EdocsCollectionResult.
     * 
     * @param edocs
     */
    public void setEdocs(com.ws.gemini.wsdl.account.EdocsResult[] edocs) {
        this.edocs = edocs;
    }


    /**
     * Gets the errorStatus value for this EdocsCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this EdocsCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EdocsCollectionResult)) return false;
        EdocsCollectionResult other = (EdocsCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.edocs==null && other.getEdocs()==null) || 
             (this.edocs!=null &&
              java.util.Arrays.equals(this.edocs, other.getEdocs()))) &&
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
        if (getEdocs() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEdocs());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEdocs(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EdocsCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("edocs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Edocs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CallStatus"));
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
