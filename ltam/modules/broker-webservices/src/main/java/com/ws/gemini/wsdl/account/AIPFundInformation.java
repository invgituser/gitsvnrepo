/**
 * AIPFundInformation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AIPFundInformation  implements java.io.Serializable {
    private short fundId;

    private java.math.BigDecimal moneyAmount;

    private org.apache.axis.types.UnsignedByte retirementIndicator;

    private org.apache.axis.types.UnsignedByte settlementAmountIndicator;

    public AIPFundInformation() {
    }

    public AIPFundInformation(
           short fundId,
           java.math.BigDecimal moneyAmount,
           org.apache.axis.types.UnsignedByte retirementIndicator,
           org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
           this.fundId = fundId;
           this.moneyAmount = moneyAmount;
           this.retirementIndicator = retirementIndicator;
           this.settlementAmountIndicator = settlementAmountIndicator;
    }


    /**
     * Gets the fundId value for this AIPFundInformation.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this AIPFundInformation.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the moneyAmount value for this AIPFundInformation.
     * 
     * @return moneyAmount
     */
    public java.math.BigDecimal getMoneyAmount() {
        return moneyAmount;
    }


    /**
     * Sets the moneyAmount value for this AIPFundInformation.
     * 
     * @param moneyAmount
     */
    public void setMoneyAmount(java.math.BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }


    /**
     * Gets the retirementIndicator value for this AIPFundInformation.
     * 
     * @return retirementIndicator
     */
    public org.apache.axis.types.UnsignedByte getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this AIPFundInformation.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(org.apache.axis.types.UnsignedByte retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the settlementAmountIndicator value for this AIPFundInformation.
     * 
     * @return settlementAmountIndicator
     */
    public org.apache.axis.types.UnsignedByte getSettlementAmountIndicator() {
        return settlementAmountIndicator;
    }


    /**
     * Sets the settlementAmountIndicator value for this AIPFundInformation.
     * 
     * @param settlementAmountIndicator
     */
    public void setSettlementAmountIndicator(org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
        this.settlementAmountIndicator = settlementAmountIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AIPFundInformation)) return false;
        AIPFundInformation other = (AIPFundInformation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fundId == other.getFundId() &&
            ((this.moneyAmount==null && other.getMoneyAmount()==null) || 
             (this.moneyAmount!=null &&
              this.moneyAmount.equals(other.getMoneyAmount()))) &&
            ((this.retirementIndicator==null && other.getRetirementIndicator()==null) || 
             (this.retirementIndicator!=null &&
              this.retirementIndicator.equals(other.getRetirementIndicator()))) &&
            ((this.settlementAmountIndicator==null && other.getSettlementAmountIndicator()==null) || 
             (this.settlementAmountIndicator!=null &&
              this.settlementAmountIndicator.equals(other.getSettlementAmountIndicator())));
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
        if (getMoneyAmount() != null) {
            _hashCode += getMoneyAmount().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        if (getSettlementAmountIndicator() != null) {
            _hashCode += getSettlementAmountIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AIPFundInformation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moneyAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MoneyAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retirementIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RetirementIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SettlementAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
