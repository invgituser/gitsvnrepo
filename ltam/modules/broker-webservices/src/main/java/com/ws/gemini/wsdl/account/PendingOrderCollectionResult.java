/**
 * PendingOrderCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class PendingOrderCollectionResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.PendingOrderResult[] pendingOrder;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public PendingOrderCollectionResult() {
    }

    public PendingOrderCollectionResult(
           com.ws.gemini.wsdl.account.PendingOrderResult[] pendingOrder,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.pendingOrder = pendingOrder;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the pendingOrder value for this PendingOrderCollectionResult.
     * 
     * @return pendingOrder
     */
    public com.ws.gemini.wsdl.account.PendingOrderResult[] getPendingOrder() {
        return pendingOrder;
    }


    /**
     * Sets the pendingOrder value for this PendingOrderCollectionResult.
     * 
     * @param pendingOrder
     */
    public void setPendingOrder(com.ws.gemini.wsdl.account.PendingOrderResult[] pendingOrder) {
        this.pendingOrder = pendingOrder;
    }


    /**
     * Gets the errorStatus value for this PendingOrderCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this PendingOrderCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PendingOrderCollectionResult)) return false;
        PendingOrderCollectionResult other = (PendingOrderCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pendingOrder==null && other.getPendingOrder()==null) || 
             (this.pendingOrder!=null &&
              java.util.Arrays.equals(this.pendingOrder, other.getPendingOrder()))) &&
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
        if (getPendingOrder() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPendingOrder());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPendingOrder(), i);
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
        new org.apache.axis.description.TypeDesc(PendingOrderCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pendingOrder");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrder"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
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
