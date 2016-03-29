/**
 * AssetAllocationFundsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AssetAllocationFundsRequest  implements java.io.Serializable {
    private short fundId;

    private java.math.BigDecimal assetAllocationPercentage;

    public AssetAllocationFundsRequest() {
    }

    public AssetAllocationFundsRequest(
           short fundId,
           java.math.BigDecimal assetAllocationPercentage) {
           this.fundId = fundId;
           this.assetAllocationPercentage = assetAllocationPercentage;
    }


    /**
     * Gets the fundId value for this AssetAllocationFundsRequest.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this AssetAllocationFundsRequest.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the assetAllocationPercentage value for this AssetAllocationFundsRequest.
     * 
     * @return assetAllocationPercentage
     */
    public java.math.BigDecimal getAssetAllocationPercentage() {
        return assetAllocationPercentage;
    }


    /**
     * Sets the assetAllocationPercentage value for this AssetAllocationFundsRequest.
     * 
     * @param assetAllocationPercentage
     */
    public void setAssetAllocationPercentage(java.math.BigDecimal assetAllocationPercentage) {
        this.assetAllocationPercentage = assetAllocationPercentage;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetAllocationFundsRequest)) return false;
        AssetAllocationFundsRequest other = (AssetAllocationFundsRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fundId == other.getFundId() &&
            ((this.assetAllocationPercentage==null && other.getAssetAllocationPercentage()==null) || 
             (this.assetAllocationPercentage!=null &&
              this.assetAllocationPercentage.equals(other.getAssetAllocationPercentage())));
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
        _hashCode += getFundId();
        if (getAssetAllocationPercentage() != null) {
            _hashCode += getAssetAllocationPercentage().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetAllocationFundsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetAllocationPercentage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationPercentage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
