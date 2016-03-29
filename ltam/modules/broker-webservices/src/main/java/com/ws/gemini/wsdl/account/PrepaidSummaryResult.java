/**
 * PrepaidSummaryResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class PrepaidSummaryResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.PrepaidSummaryInfo prepaidSummary;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public PrepaidSummaryResult() {
    }

    public PrepaidSummaryResult(
           com.ws.gemini.wsdl.account.PrepaidSummaryInfo prepaidSummary,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.prepaidSummary = prepaidSummary;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the prepaidSummary value for this PrepaidSummaryResult.
     * 
     * @return prepaidSummary
     */
    public com.ws.gemini.wsdl.account.PrepaidSummaryInfo getPrepaidSummary() {
        return prepaidSummary;
    }


    /**
     * Sets the prepaidSummary value for this PrepaidSummaryResult.
     * 
     * @param prepaidSummary
     */
    public void setPrepaidSummary(com.ws.gemini.wsdl.account.PrepaidSummaryInfo prepaidSummary) {
        this.prepaidSummary = prepaidSummary;
    }


    /**
     * Gets the errorStatus value for this PrepaidSummaryResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this PrepaidSummaryResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrepaidSummaryResult)) return false;
        PrepaidSummaryResult other = (PrepaidSummaryResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.prepaidSummary==null && other.getPrepaidSummary()==null) || 
             (this.prepaidSummary!=null &&
              this.prepaidSummary.equals(other.getPrepaidSummary()))) &&
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
        if (getPrepaidSummary() != null) {
            _hashCode += getPrepaidSummary().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrepaidSummaryResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prepaidSummary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
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
