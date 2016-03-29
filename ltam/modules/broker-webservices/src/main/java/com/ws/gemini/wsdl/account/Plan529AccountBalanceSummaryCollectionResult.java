/**
 * Plan529AccountBalanceSummaryCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class Plan529AccountBalanceSummaryCollectionResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult[] plan529AccountBalanceSummary;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public Plan529AccountBalanceSummaryCollectionResult() {
    }

    public Plan529AccountBalanceSummaryCollectionResult(
           com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult[] plan529AccountBalanceSummary,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.plan529AccountBalanceSummary = plan529AccountBalanceSummary;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the plan529AccountBalanceSummary value for this Plan529AccountBalanceSummaryCollectionResult.
     * 
     * @return plan529AccountBalanceSummary
     */
    public com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult[] getPlan529AccountBalanceSummary() {
        return plan529AccountBalanceSummary;
    }


    /**
     * Sets the plan529AccountBalanceSummary value for this Plan529AccountBalanceSummaryCollectionResult.
     * 
     * @param plan529AccountBalanceSummary
     */
    public void setPlan529AccountBalanceSummary(com.ws.gemini.wsdl.account.Plan529AccountBalanceSummaryResult[] plan529AccountBalanceSummary) {
        this.plan529AccountBalanceSummary = plan529AccountBalanceSummary;
    }


    /**
     * Gets the errorStatus value for this Plan529AccountBalanceSummaryCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this Plan529AccountBalanceSummaryCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Plan529AccountBalanceSummaryCollectionResult)) return false;
        Plan529AccountBalanceSummaryCollectionResult other = (Plan529AccountBalanceSummaryCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.plan529AccountBalanceSummary==null && other.getPlan529AccountBalanceSummary()==null) || 
             (this.plan529AccountBalanceSummary!=null &&
              java.util.Arrays.equals(this.plan529AccountBalanceSummary, other.getPlan529AccountBalanceSummary()))) &&
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
        if (getPlan529AccountBalanceSummary() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPlan529AccountBalanceSummary());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPlan529AccountBalanceSummary(), i);
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
        new org.apache.axis.description.TypeDesc(Plan529AccountBalanceSummaryCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("plan529AccountBalanceSummary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult"));
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
