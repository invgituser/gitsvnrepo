/**
 * FundInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.transaction;

public class FundInformation  implements java.io.Serializable {
    private short fundId;

    private org.apache.axis.types.UnsignedByte amountType;

    private java.math.BigDecimal amount;

    private org.apache.axis.types.UnsignedByte fromToLineIndicator;

    private short CBDMIdForCoveredShares;

    private short CBDMIdForNonCoveredShares;

    private com.invessence.ws.provider.gemini.wsdl.transaction.CBALotInformation[] CBALotInfo;

    public FundInformation() {
    }

    public FundInformation(
           short fundId,
           org.apache.axis.types.UnsignedByte amountType,
           java.math.BigDecimal amount,
           org.apache.axis.types.UnsignedByte fromToLineIndicator,
           short CBDMIdForCoveredShares,
           short CBDMIdForNonCoveredShares,
           com.invessence.ws.provider.gemini.wsdl.transaction.CBALotInformation[] CBALotInfo) {
           this.fundId = fundId;
           this.amountType = amountType;
           this.amount = amount;
           this.fromToLineIndicator = fromToLineIndicator;
           this.CBDMIdForCoveredShares = CBDMIdForCoveredShares;
           this.CBDMIdForNonCoveredShares = CBDMIdForNonCoveredShares;
           this.CBALotInfo = CBALotInfo;
    }


    /**
     * Gets the fundId value for this FundInformation.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this FundInformation.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the amountType value for this FundInformation.
     * 
     * @return amountType
     */
    public org.apache.axis.types.UnsignedByte getAmountType() {
        return amountType;
    }


    /**
     * Sets the amountType value for this FundInformation.
     * 
     * @param amountType
     */
    public void setAmountType(org.apache.axis.types.UnsignedByte amountType) {
        this.amountType = amountType;
    }


    /**
     * Gets the amount value for this FundInformation.
     * 
     * @return amount
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this FundInformation.
     * 
     * @param amount
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }


    /**
     * Gets the fromToLineIndicator value for this FundInformation.
     * 
     * @return fromToLineIndicator
     */
    public org.apache.axis.types.UnsignedByte getFromToLineIndicator() {
        return fromToLineIndicator;
    }


    /**
     * Sets the fromToLineIndicator value for this FundInformation.
     * 
     * @param fromToLineIndicator
     */
    public void setFromToLineIndicator(org.apache.axis.types.UnsignedByte fromToLineIndicator) {
        this.fromToLineIndicator = fromToLineIndicator;
    }


    /**
     * Gets the CBDMIdForCoveredShares value for this FundInformation.
     * 
     * @return CBDMIdForCoveredShares
     */
    public short getCBDMIdForCoveredShares() {
        return CBDMIdForCoveredShares;
    }


    /**
     * Sets the CBDMIdForCoveredShares value for this FundInformation.
     * 
     * @param CBDMIdForCoveredShares
     */
    public void setCBDMIdForCoveredShares(short CBDMIdForCoveredShares) {
        this.CBDMIdForCoveredShares = CBDMIdForCoveredShares;
    }


    /**
     * Gets the CBDMIdForNonCoveredShares value for this FundInformation.
     * 
     * @return CBDMIdForNonCoveredShares
     */
    public short getCBDMIdForNonCoveredShares() {
        return CBDMIdForNonCoveredShares;
    }


    /**
     * Sets the CBDMIdForNonCoveredShares value for this FundInformation.
     * 
     * @param CBDMIdForNonCoveredShares
     */
    public void setCBDMIdForNonCoveredShares(short CBDMIdForNonCoveredShares) {
        this.CBDMIdForNonCoveredShares = CBDMIdForNonCoveredShares;
    }


    /**
     * Gets the CBALotInfo value for this FundInformation.
     * 
     * @return CBALotInfo
     */
    public com.invessence.ws.provider.gemini.wsdl.transaction.CBALotInformation[] getCBALotInfo() {
        return CBALotInfo;
    }


    /**
     * Sets the CBALotInfo value for this FundInformation.
     * 
     * @param CBALotInfo
     */
    public void setCBALotInfo(com.invessence.ws.provider.gemini.wsdl.transaction.CBALotInformation[] CBALotInfo) {
        this.CBALotInfo = CBALotInfo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FundInformation)) return false;
        FundInformation other = (FundInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fundId == other.getFundId() &&
            ((this.amountType==null && other.getAmountType()==null) || 
             (this.amountType!=null &&
              this.amountType.equals(other.getAmountType()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            ((this.fromToLineIndicator==null && other.getFromToLineIndicator()==null) || 
             (this.fromToLineIndicator!=null &&
              this.fromToLineIndicator.equals(other.getFromToLineIndicator()))) &&
            this.CBDMIdForCoveredShares == other.getCBDMIdForCoveredShares() &&
            this.CBDMIdForNonCoveredShares == other.getCBDMIdForNonCoveredShares() &&
            ((this.CBALotInfo==null && other.getCBALotInfo()==null) || 
             (this.CBALotInfo!=null &&
              java.util.Arrays.equals(this.CBALotInfo, other.getCBALotInfo())));
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
        if (getAmountType() != null) {
            _hashCode += getAmountType().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        if (getFromToLineIndicator() != null) {
            _hashCode += getFromToLineIndicator().hashCode();
        }
        _hashCode += getCBDMIdForCoveredShares();
        _hashCode += getCBDMIdForNonCoveredShares();
        if (getCBALotInfo() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCBALotInfo());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCBALotInfo(), i);
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
        new org.apache.axis.description.TypeDesc(FundInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AmountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromToLineIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FromToLineIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMIdForCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMIdForCoveredShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMIdForNonCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMIdForNonCoveredShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBALotInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBALotInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBALotInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBALotInformation"));
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
