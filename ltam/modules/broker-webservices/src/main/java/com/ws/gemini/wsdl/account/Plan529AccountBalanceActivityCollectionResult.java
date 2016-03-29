/**
 * Plan529AccountBalanceActivityCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class Plan529AccountBalanceActivityCollectionResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.Status errorStatus;

    private com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult[] accountBalanceActivity;

    public Plan529AccountBalanceActivityCollectionResult() {
    }

    public Plan529AccountBalanceActivityCollectionResult(
           com.ws.gemini.wsdl.account.Status errorStatus,
           com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult[] accountBalanceActivity) {
           this.errorStatus = errorStatus;
           this.accountBalanceActivity = accountBalanceActivity;
    }


    /**
     * Gets the errorStatus value for this Plan529AccountBalanceActivityCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this Plan529AccountBalanceActivityCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountBalanceActivity value for this Plan529AccountBalanceActivityCollectionResult.
     * 
     * @return accountBalanceActivity
     */
    public com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult[] getAccountBalanceActivity() {
        return accountBalanceActivity;
    }


    /**
     * Sets the accountBalanceActivity value for this Plan529AccountBalanceActivityCollectionResult.
     * 
     * @param accountBalanceActivity
     */
    public void setAccountBalanceActivity(com.ws.gemini.wsdl.account.Plan529AccountBalanceActivityResult[] accountBalanceActivity) {
        this.accountBalanceActivity = accountBalanceActivity;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Plan529AccountBalanceActivityCollectionResult)) return false;
        Plan529AccountBalanceActivityCollectionResult other = (Plan529AccountBalanceActivityCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorStatus==null && other.getErrorStatus()==null) || 
             (this.errorStatus!=null &&
              this.errorStatus.equals(other.getErrorStatus()))) &&
            ((this.accountBalanceActivity==null && other.getAccountBalanceActivity()==null) || 
             (this.accountBalanceActivity!=null &&
              java.util.Arrays.equals(this.accountBalanceActivity, other.getAccountBalanceActivity())));
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
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        if (getAccountBalanceActivity() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccountBalanceActivity());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccountBalanceActivity(), i);
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
        new org.apache.axis.description.TypeDesc(Plan529AccountBalanceActivityCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CallStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountBalanceActivity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountBalanceActivity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult"));
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
