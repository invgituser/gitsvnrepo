/**
 * AssetAllocationModelWeightsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AssetAllocationModelWeightsResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public AssetAllocationModelWeightsResult() {
    }

    public AssetAllocationModelWeightsResult(
           com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.accountAssetAllocationModelWeightsCollection = accountAssetAllocationModelWeightsCollection;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountAssetAllocationModelWeightsCollection value for this AssetAllocationModelWeightsResult.
     * 
     * @return accountAssetAllocationModelWeightsCollection
     */
    public com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] getAccountAssetAllocationModelWeightsCollection() {
        return accountAssetAllocationModelWeightsCollection;
    }


    /**
     * Sets the accountAssetAllocationModelWeightsCollection value for this AssetAllocationModelWeightsResult.
     * 
     * @param accountAssetAllocationModelWeightsCollection
     */
    public void setAccountAssetAllocationModelWeightsCollection(com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection) {
        this.accountAssetAllocationModelWeightsCollection = accountAssetAllocationModelWeightsCollection;
    }


    /**
     * Gets the errorStatus value for this AssetAllocationModelWeightsResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this AssetAllocationModelWeightsResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetAllocationModelWeightsResult)) return false;
        AssetAllocationModelWeightsResult other = (AssetAllocationModelWeightsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountAssetAllocationModelWeightsCollection==null && other.getAccountAssetAllocationModelWeightsCollection()==null) || 
             (this.accountAssetAllocationModelWeightsCollection!=null &&
              java.util.Arrays.equals(this.accountAssetAllocationModelWeightsCollection, other.getAccountAssetAllocationModelWeightsCollection()))) &&
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
        if (getAccountAssetAllocationModelWeightsCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccountAssetAllocationModelWeightsCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccountAssetAllocationModelWeightsCollection(), i);
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
        new org.apache.axis.description.TypeDesc(AssetAllocationModelWeightsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelWeightsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAssetAllocationModelWeightsCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult"));
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
