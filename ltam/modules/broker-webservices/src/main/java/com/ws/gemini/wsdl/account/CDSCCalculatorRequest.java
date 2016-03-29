/**
 * CDSCCalculatorRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class CDSCCalculatorRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private org.apache.axis.types.UnsignedByte sharesOrAmountIndicator;

    private java.math.BigDecimal grossAmount;

    private java.math.BigDecimal shareAmount;

    private java.util.Calendar valuationDate;

    private java.math.BigDecimal valuationPrice;

    private org.apache.axis.types.UnsignedByte settlementAmountIndicator;

    public CDSCCalculatorRequest() {
    }

    public CDSCCalculatorRequest(
           java.lang.String accountNumber,
           short fundId,
           org.apache.axis.types.UnsignedByte sharesOrAmountIndicator,
           java.math.BigDecimal grossAmount,
           java.math.BigDecimal shareAmount,
           java.util.Calendar valuationDate,
           java.math.BigDecimal valuationPrice,
           org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.sharesOrAmountIndicator = sharesOrAmountIndicator;
           this.grossAmount = grossAmount;
           this.shareAmount = shareAmount;
           this.valuationDate = valuationDate;
           this.valuationPrice = valuationPrice;
           this.settlementAmountIndicator = settlementAmountIndicator;
    }


    /**
     * Gets the accountNumber value for this CDSCCalculatorRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this CDSCCalculatorRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this CDSCCalculatorRequest.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this CDSCCalculatorRequest.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the sharesOrAmountIndicator value for this CDSCCalculatorRequest.
     * 
     * @return sharesOrAmountIndicator
     */
    public org.apache.axis.types.UnsignedByte getSharesOrAmountIndicator() {
        return sharesOrAmountIndicator;
    }


    /**
     * Sets the sharesOrAmountIndicator value for this CDSCCalculatorRequest.
     * 
     * @param sharesOrAmountIndicator
     */
    public void setSharesOrAmountIndicator(org.apache.axis.types.UnsignedByte sharesOrAmountIndicator) {
        this.sharesOrAmountIndicator = sharesOrAmountIndicator;
    }


    /**
     * Gets the grossAmount value for this CDSCCalculatorRequest.
     * 
     * @return grossAmount
     */
    public java.math.BigDecimal getGrossAmount() {
        return grossAmount;
    }


    /**
     * Sets the grossAmount value for this CDSCCalculatorRequest.
     * 
     * @param grossAmount
     */
    public void setGrossAmount(java.math.BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }


    /**
     * Gets the shareAmount value for this CDSCCalculatorRequest.
     * 
     * @return shareAmount
     */
    public java.math.BigDecimal getShareAmount() {
        return shareAmount;
    }


    /**
     * Sets the shareAmount value for this CDSCCalculatorRequest.
     * 
     * @param shareAmount
     */
    public void setShareAmount(java.math.BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }


    /**
     * Gets the valuationDate value for this CDSCCalculatorRequest.
     * 
     * @return valuationDate
     */
    public java.util.Calendar getValuationDate() {
        return valuationDate;
    }


    /**
     * Sets the valuationDate value for this CDSCCalculatorRequest.
     * 
     * @param valuationDate
     */
    public void setValuationDate(java.util.Calendar valuationDate) {
        this.valuationDate = valuationDate;
    }


    /**
     * Gets the valuationPrice value for this CDSCCalculatorRequest.
     * 
     * @return valuationPrice
     */
    public java.math.BigDecimal getValuationPrice() {
        return valuationPrice;
    }


    /**
     * Sets the valuationPrice value for this CDSCCalculatorRequest.
     * 
     * @param valuationPrice
     */
    public void setValuationPrice(java.math.BigDecimal valuationPrice) {
        this.valuationPrice = valuationPrice;
    }


    /**
     * Gets the settlementAmountIndicator value for this CDSCCalculatorRequest.
     * 
     * @return settlementAmountIndicator
     */
    public org.apache.axis.types.UnsignedByte getSettlementAmountIndicator() {
        return settlementAmountIndicator;
    }


    /**
     * Sets the settlementAmountIndicator value for this CDSCCalculatorRequest.
     * 
     * @param settlementAmountIndicator
     */
    public void setSettlementAmountIndicator(org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
        this.settlementAmountIndicator = settlementAmountIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CDSCCalculatorRequest)) return false;
        CDSCCalculatorRequest other = (CDSCCalculatorRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.fundId == other.getFundId() &&
            ((this.sharesOrAmountIndicator==null && other.getSharesOrAmountIndicator()==null) || 
             (this.sharesOrAmountIndicator!=null &&
              this.sharesOrAmountIndicator.equals(other.getSharesOrAmountIndicator()))) &&
            ((this.grossAmount==null && other.getGrossAmount()==null) || 
             (this.grossAmount!=null &&
              this.grossAmount.equals(other.getGrossAmount()))) &&
            ((this.shareAmount==null && other.getShareAmount()==null) || 
             (this.shareAmount!=null &&
              this.shareAmount.equals(other.getShareAmount()))) &&
            ((this.valuationDate==null && other.getValuationDate()==null) || 
             (this.valuationDate!=null &&
              this.valuationDate.equals(other.getValuationDate()))) &&
            ((this.valuationPrice==null && other.getValuationPrice()==null) || 
             (this.valuationPrice!=null &&
              this.valuationPrice.equals(other.getValuationPrice()))) &&
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
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getFundId();
        if (getSharesOrAmountIndicator() != null) {
            _hashCode += getSharesOrAmountIndicator().hashCode();
        }
        if (getGrossAmount() != null) {
            _hashCode += getGrossAmount().hashCode();
        }
        if (getShareAmount() != null) {
            _hashCode += getShareAmount().hashCode();
        }
        if (getValuationDate() != null) {
            _hashCode += getValuationDate().hashCode();
        }
        if (getValuationPrice() != null) {
            _hashCode += getValuationPrice().hashCode();
        }
        if (getSettlementAmountIndicator() != null) {
            _hashCode += getSettlementAmountIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CDSCCalculatorRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharesOrAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SharesOrAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ValuationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuationPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ValuationPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
