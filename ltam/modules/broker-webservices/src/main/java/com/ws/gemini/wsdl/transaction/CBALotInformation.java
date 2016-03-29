/**
 * CBALotInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public class CBALotInformation  implements java.io.Serializable {
    private int lotId;

    private java.math.BigDecimal shareAmount;

    public CBALotInformation() {
    }

    public CBALotInformation(
           int lotId,
           java.math.BigDecimal shareAmount) {
           this.lotId = lotId;
           this.shareAmount = shareAmount;
    }


    /**
     * Gets the lotId value for this CBALotInformation.
     * 
     * @return lotId
     */
    public int getLotId() {
        return lotId;
    }


    /**
     * Sets the lotId value for this CBALotInformation.
     * 
     * @param lotId
     */
    public void setLotId(int lotId) {
        this.lotId = lotId;
    }


    /**
     * Gets the shareAmount value for this CBALotInformation.
     * 
     * @return shareAmount
     */
    public java.math.BigDecimal getShareAmount() {
        return shareAmount;
    }


    /**
     * Sets the shareAmount value for this CBALotInformation.
     * 
     * @param shareAmount
     */
    public void setShareAmount(java.math.BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CBALotInformation)) return false;
        CBALotInformation other = (CBALotInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.lotId == other.getLotId() &&
            ((this.shareAmount==null && other.getShareAmount()==null) || 
             (this.shareAmount!=null &&
              this.shareAmount.equals(other.getShareAmount())));
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
        _hashCode += getLotId();
        if (getShareAmount() != null) {
            _hashCode += getShareAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CBALotInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBALotInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lotId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareAmount"));
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
