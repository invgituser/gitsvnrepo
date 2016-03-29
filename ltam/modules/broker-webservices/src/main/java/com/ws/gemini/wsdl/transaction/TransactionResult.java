/**
 * TransactionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public class TransactionResult  implements java.io.Serializable {
    private short fundId;

    private org.apache.axis.types.UnsignedByte fundTransactionType;

    private java.util.Calendar tradeDate;

    private org.apache.axis.types.UnsignedByte priceCycleId;

    private java.math.BigDecimal totalGrossAmount;

    private java.math.BigDecimal totalFeeAmount;

    private java.math.BigDecimal totalWithholdngAmount;

    public TransactionResult() {
    }

    public TransactionResult(
           short fundId,
           org.apache.axis.types.UnsignedByte fundTransactionType,
           java.util.Calendar tradeDate,
           org.apache.axis.types.UnsignedByte priceCycleId,
           java.math.BigDecimal totalGrossAmount,
           java.math.BigDecimal totalFeeAmount,
           java.math.BigDecimal totalWithholdngAmount) {
           this.fundId = fundId;
           this.fundTransactionType = fundTransactionType;
           this.tradeDate = tradeDate;
           this.priceCycleId = priceCycleId;
           this.totalGrossAmount = totalGrossAmount;
           this.totalFeeAmount = totalFeeAmount;
           this.totalWithholdngAmount = totalWithholdngAmount;
    }


    /**
     * Gets the fundId value for this TransactionResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this TransactionResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundTransactionType value for this TransactionResult.
     * 
     * @return fundTransactionType
     */
    public org.apache.axis.types.UnsignedByte getFundTransactionType() {
        return fundTransactionType;
    }


    /**
     * Sets the fundTransactionType value for this TransactionResult.
     * 
     * @param fundTransactionType
     */
    public void setFundTransactionType(org.apache.axis.types.UnsignedByte fundTransactionType) {
        this.fundTransactionType = fundTransactionType;
    }


    /**
     * Gets the tradeDate value for this TransactionResult.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this TransactionResult.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the priceCycleId value for this TransactionResult.
     * 
     * @return priceCycleId
     */
    public org.apache.axis.types.UnsignedByte getPriceCycleId() {
        return priceCycleId;
    }


    /**
     * Sets the priceCycleId value for this TransactionResult.
     * 
     * @param priceCycleId
     */
    public void setPriceCycleId(org.apache.axis.types.UnsignedByte priceCycleId) {
        this.priceCycleId = priceCycleId;
    }


    /**
     * Gets the totalGrossAmount value for this TransactionResult.
     * 
     * @return totalGrossAmount
     */
    public java.math.BigDecimal getTotalGrossAmount() {
        return totalGrossAmount;
    }


    /**
     * Sets the totalGrossAmount value for this TransactionResult.
     * 
     * @param totalGrossAmount
     */
    public void setTotalGrossAmount(java.math.BigDecimal totalGrossAmount) {
        this.totalGrossAmount = totalGrossAmount;
    }


    /**
     * Gets the totalFeeAmount value for this TransactionResult.
     * 
     * @return totalFeeAmount
     */
    public java.math.BigDecimal getTotalFeeAmount() {
        return totalFeeAmount;
    }


    /**
     * Sets the totalFeeAmount value for this TransactionResult.
     * 
     * @param totalFeeAmount
     */
    public void setTotalFeeAmount(java.math.BigDecimal totalFeeAmount) {
        this.totalFeeAmount = totalFeeAmount;
    }


    /**
     * Gets the totalWithholdngAmount value for this TransactionResult.
     * 
     * @return totalWithholdngAmount
     */
    public java.math.BigDecimal getTotalWithholdngAmount() {
        return totalWithholdngAmount;
    }


    /**
     * Sets the totalWithholdngAmount value for this TransactionResult.
     * 
     * @param totalWithholdngAmount
     */
    public void setTotalWithholdngAmount(java.math.BigDecimal totalWithholdngAmount) {
        this.totalWithholdngAmount = totalWithholdngAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionResult)) return false;
        TransactionResult other = (TransactionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fundId == other.getFundId() &&
            ((this.fundTransactionType==null && other.getFundTransactionType()==null) || 
             (this.fundTransactionType!=null &&
              this.fundTransactionType.equals(other.getFundTransactionType()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.priceCycleId==null && other.getPriceCycleId()==null) || 
             (this.priceCycleId!=null &&
              this.priceCycleId.equals(other.getPriceCycleId()))) &&
            ((this.totalGrossAmount==null && other.getTotalGrossAmount()==null) || 
             (this.totalGrossAmount!=null &&
              this.totalGrossAmount.equals(other.getTotalGrossAmount()))) &&
            ((this.totalFeeAmount==null && other.getTotalFeeAmount()==null) || 
             (this.totalFeeAmount!=null &&
              this.totalFeeAmount.equals(other.getTotalFeeAmount()))) &&
            ((this.totalWithholdngAmount==null && other.getTotalWithholdngAmount()==null) || 
             (this.totalWithholdngAmount!=null &&
              this.totalWithholdngAmount.equals(other.getTotalWithholdngAmount())));
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
        if (getFundTransactionType() != null) {
            _hashCode += getFundTransactionType().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getPriceCycleId() != null) {
            _hashCode += getPriceCycleId().hashCode();
        }
        if (getTotalGrossAmount() != null) {
            _hashCode += getTotalGrossAmount().hashCode();
        }
        if (getTotalFeeAmount() != null) {
            _hashCode += getTotalFeeAmount().hashCode();
        }
        if (getTotalWithholdngAmount() != null) {
            _hashCode += getTotalWithholdngAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCycleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriceCycleId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalGrossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalGrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalFeeAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalFeeAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalWithholdngAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalWithholdngAmount"));
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
