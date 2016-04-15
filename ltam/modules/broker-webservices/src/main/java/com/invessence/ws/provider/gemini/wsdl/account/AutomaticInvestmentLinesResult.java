/**
 * AutomaticInvestmentLinesResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AutomaticInvestmentLinesResult  implements java.io.Serializable {
    private int automaticInvestmentId;

    private org.apache.axis.types.UnsignedByte automaticInvestmentLineno;

    private short fundid;

    private java.math.BigDecimal moneyAmount;

    private org.apache.axis.types.UnsignedByte retirementIndicator;

    private java.lang.String accountStatementLines;

    private org.apache.axis.types.UnsignedByte loadIndicator;

    private java.lang.String NAVReasonCode;

    private org.apache.axis.types.UnsignedByte settlementAmountIndicator;

    private org.apache.axis.types.UnsignedByte redemptionFeeIndicatorId;

    private org.apache.axis.types.UnsignedByte feeType;

    private java.math.BigDecimal feeAmount;

    private org.apache.axis.types.UnsignedByte feeComputationMethodIndicator;

    private java.math.BigDecimal feePercent;

    public AutomaticInvestmentLinesResult() {
    }

    public AutomaticInvestmentLinesResult(
           int automaticInvestmentId,
           org.apache.axis.types.UnsignedByte automaticInvestmentLineno,
           short fundid,
           java.math.BigDecimal moneyAmount,
           org.apache.axis.types.UnsignedByte retirementIndicator,
           java.lang.String accountStatementLines,
           org.apache.axis.types.UnsignedByte loadIndicator,
           java.lang.String NAVReasonCode,
           org.apache.axis.types.UnsignedByte settlementAmountIndicator,
           org.apache.axis.types.UnsignedByte redemptionFeeIndicatorId,
           org.apache.axis.types.UnsignedByte feeType,
           java.math.BigDecimal feeAmount,
           org.apache.axis.types.UnsignedByte feeComputationMethodIndicator,
           java.math.BigDecimal feePercent) {
           this.automaticInvestmentId = automaticInvestmentId;
           this.automaticInvestmentLineno = automaticInvestmentLineno;
           this.fundid = fundid;
           this.moneyAmount = moneyAmount;
           this.retirementIndicator = retirementIndicator;
           this.accountStatementLines = accountStatementLines;
           this.loadIndicator = loadIndicator;
           this.NAVReasonCode = NAVReasonCode;
           this.settlementAmountIndicator = settlementAmountIndicator;
           this.redemptionFeeIndicatorId = redemptionFeeIndicatorId;
           this.feeType = feeType;
           this.feeAmount = feeAmount;
           this.feeComputationMethodIndicator = feeComputationMethodIndicator;
           this.feePercent = feePercent;
    }


    /**
     * Gets the automaticInvestmentId value for this AutomaticInvestmentLinesResult.
     * 
     * @return automaticInvestmentId
     */
    public int getAutomaticInvestmentId() {
        return automaticInvestmentId;
    }


    /**
     * Sets the automaticInvestmentId value for this AutomaticInvestmentLinesResult.
     * 
     * @param automaticInvestmentId
     */
    public void setAutomaticInvestmentId(int automaticInvestmentId) {
        this.automaticInvestmentId = automaticInvestmentId;
    }


    /**
     * Gets the automaticInvestmentLineno value for this AutomaticInvestmentLinesResult.
     * 
     * @return automaticInvestmentLineno
     */
    public org.apache.axis.types.UnsignedByte getAutomaticInvestmentLineno() {
        return automaticInvestmentLineno;
    }


    /**
     * Sets the automaticInvestmentLineno value for this AutomaticInvestmentLinesResult.
     * 
     * @param automaticInvestmentLineno
     */
    public void setAutomaticInvestmentLineno(org.apache.axis.types.UnsignedByte automaticInvestmentLineno) {
        this.automaticInvestmentLineno = automaticInvestmentLineno;
    }


    /**
     * Gets the fundid value for this AutomaticInvestmentLinesResult.
     * 
     * @return fundid
     */
    public short getFundid() {
        return fundid;
    }


    /**
     * Sets the fundid value for this AutomaticInvestmentLinesResult.
     * 
     * @param fundid
     */
    public void setFundid(short fundid) {
        this.fundid = fundid;
    }


    /**
     * Gets the moneyAmount value for this AutomaticInvestmentLinesResult.
     * 
     * @return moneyAmount
     */
    public java.math.BigDecimal getMoneyAmount() {
        return moneyAmount;
    }


    /**
     * Sets the moneyAmount value for this AutomaticInvestmentLinesResult.
     * 
     * @param moneyAmount
     */
    public void setMoneyAmount(java.math.BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }


    /**
     * Gets the retirementIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @return retirementIndicator
     */
    public org.apache.axis.types.UnsignedByte getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(org.apache.axis.types.UnsignedByte retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the accountStatementLines value for this AutomaticInvestmentLinesResult.
     * 
     * @return accountStatementLines
     */
    public java.lang.String getAccountStatementLines() {
        return accountStatementLines;
    }


    /**
     * Sets the accountStatementLines value for this AutomaticInvestmentLinesResult.
     * 
     * @param accountStatementLines
     */
    public void setAccountStatementLines(java.lang.String accountStatementLines) {
        this.accountStatementLines = accountStatementLines;
    }


    /**
     * Gets the loadIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @return loadIndicator
     */
    public org.apache.axis.types.UnsignedByte getLoadIndicator() {
        return loadIndicator;
    }


    /**
     * Sets the loadIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @param loadIndicator
     */
    public void setLoadIndicator(org.apache.axis.types.UnsignedByte loadIndicator) {
        this.loadIndicator = loadIndicator;
    }


    /**
     * Gets the NAVReasonCode value for this AutomaticInvestmentLinesResult.
     * 
     * @return NAVReasonCode
     */
    public java.lang.String getNAVReasonCode() {
        return NAVReasonCode;
    }


    /**
     * Sets the NAVReasonCode value for this AutomaticInvestmentLinesResult.
     * 
     * @param NAVReasonCode
     */
    public void setNAVReasonCode(java.lang.String NAVReasonCode) {
        this.NAVReasonCode = NAVReasonCode;
    }


    /**
     * Gets the settlementAmountIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @return settlementAmountIndicator
     */
    public org.apache.axis.types.UnsignedByte getSettlementAmountIndicator() {
        return settlementAmountIndicator;
    }


    /**
     * Sets the settlementAmountIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @param settlementAmountIndicator
     */
    public void setSettlementAmountIndicator(org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
        this.settlementAmountIndicator = settlementAmountIndicator;
    }


    /**
     * Gets the redemptionFeeIndicatorId value for this AutomaticInvestmentLinesResult.
     * 
     * @return redemptionFeeIndicatorId
     */
    public org.apache.axis.types.UnsignedByte getRedemptionFeeIndicatorId() {
        return redemptionFeeIndicatorId;
    }


    /**
     * Sets the redemptionFeeIndicatorId value for this AutomaticInvestmentLinesResult.
     * 
     * @param redemptionFeeIndicatorId
     */
    public void setRedemptionFeeIndicatorId(org.apache.axis.types.UnsignedByte redemptionFeeIndicatorId) {
        this.redemptionFeeIndicatorId = redemptionFeeIndicatorId;
    }


    /**
     * Gets the feeType value for this AutomaticInvestmentLinesResult.
     * 
     * @return feeType
     */
    public org.apache.axis.types.UnsignedByte getFeeType() {
        return feeType;
    }


    /**
     * Sets the feeType value for this AutomaticInvestmentLinesResult.
     * 
     * @param feeType
     */
    public void setFeeType(org.apache.axis.types.UnsignedByte feeType) {
        this.feeType = feeType;
    }


    /**
     * Gets the feeAmount value for this AutomaticInvestmentLinesResult.
     * 
     * @return feeAmount
     */
    public java.math.BigDecimal getFeeAmount() {
        return feeAmount;
    }


    /**
     * Sets the feeAmount value for this AutomaticInvestmentLinesResult.
     * 
     * @param feeAmount
     */
    public void setFeeAmount(java.math.BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }


    /**
     * Gets the feeComputationMethodIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @return feeComputationMethodIndicator
     */
    public org.apache.axis.types.UnsignedByte getFeeComputationMethodIndicator() {
        return feeComputationMethodIndicator;
    }


    /**
     * Sets the feeComputationMethodIndicator value for this AutomaticInvestmentLinesResult.
     * 
     * @param feeComputationMethodIndicator
     */
    public void setFeeComputationMethodIndicator(org.apache.axis.types.UnsignedByte feeComputationMethodIndicator) {
        this.feeComputationMethodIndicator = feeComputationMethodIndicator;
    }


    /**
     * Gets the feePercent value for this AutomaticInvestmentLinesResult.
     * 
     * @return feePercent
     */
    public java.math.BigDecimal getFeePercent() {
        return feePercent;
    }


    /**
     * Sets the feePercent value for this AutomaticInvestmentLinesResult.
     * 
     * @param feePercent
     */
    public void setFeePercent(java.math.BigDecimal feePercent) {
        this.feePercent = feePercent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomaticInvestmentLinesResult)) return false;
        AutomaticInvestmentLinesResult other = (AutomaticInvestmentLinesResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.automaticInvestmentId == other.getAutomaticInvestmentId() &&
            ((this.automaticInvestmentLineno==null && other.getAutomaticInvestmentLineno()==null) || 
             (this.automaticInvestmentLineno!=null &&
              this.automaticInvestmentLineno.equals(other.getAutomaticInvestmentLineno()))) &&
            this.fundid == other.getFundid() &&
            ((this.moneyAmount==null && other.getMoneyAmount()==null) || 
             (this.moneyAmount!=null &&
              this.moneyAmount.equals(other.getMoneyAmount()))) &&
            ((this.retirementIndicator==null && other.getRetirementIndicator()==null) || 
             (this.retirementIndicator!=null &&
              this.retirementIndicator.equals(other.getRetirementIndicator()))) &&
            ((this.accountStatementLines==null && other.getAccountStatementLines()==null) || 
             (this.accountStatementLines!=null &&
              this.accountStatementLines.equals(other.getAccountStatementLines()))) &&
            ((this.loadIndicator==null && other.getLoadIndicator()==null) || 
             (this.loadIndicator!=null &&
              this.loadIndicator.equals(other.getLoadIndicator()))) &&
            ((this.NAVReasonCode==null && other.getNAVReasonCode()==null) || 
             (this.NAVReasonCode!=null &&
              this.NAVReasonCode.equals(other.getNAVReasonCode()))) &&
            ((this.settlementAmountIndicator==null && other.getSettlementAmountIndicator()==null) || 
             (this.settlementAmountIndicator!=null &&
              this.settlementAmountIndicator.equals(other.getSettlementAmountIndicator()))) &&
            ((this.redemptionFeeIndicatorId==null && other.getRedemptionFeeIndicatorId()==null) || 
             (this.redemptionFeeIndicatorId!=null &&
              this.redemptionFeeIndicatorId.equals(other.getRedemptionFeeIndicatorId()))) &&
            ((this.feeType==null && other.getFeeType()==null) || 
             (this.feeType!=null &&
              this.feeType.equals(other.getFeeType()))) &&
            ((this.feeAmount==null && other.getFeeAmount()==null) || 
             (this.feeAmount!=null &&
              this.feeAmount.equals(other.getFeeAmount()))) &&
            ((this.feeComputationMethodIndicator==null && other.getFeeComputationMethodIndicator()==null) || 
             (this.feeComputationMethodIndicator!=null &&
              this.feeComputationMethodIndicator.equals(other.getFeeComputationMethodIndicator()))) &&
            ((this.feePercent==null && other.getFeePercent()==null) || 
             (this.feePercent!=null &&
              this.feePercent.equals(other.getFeePercent())));
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
        _hashCode += getAutomaticInvestmentId();
        if (getAutomaticInvestmentLineno() != null) {
            _hashCode += getAutomaticInvestmentLineno().hashCode();
        }
        _hashCode += getFundid();
        if (getMoneyAmount() != null) {
            _hashCode += getMoneyAmount().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        if (getAccountStatementLines() != null) {
            _hashCode += getAccountStatementLines().hashCode();
        }
        if (getLoadIndicator() != null) {
            _hashCode += getLoadIndicator().hashCode();
        }
        if (getNAVReasonCode() != null) {
            _hashCode += getNAVReasonCode().hashCode();
        }
        if (getSettlementAmountIndicator() != null) {
            _hashCode += getSettlementAmountIndicator().hashCode();
        }
        if (getRedemptionFeeIndicatorId() != null) {
            _hashCode += getRedemptionFeeIndicatorId().hashCode();
        }
        if (getFeeType() != null) {
            _hashCode += getFeeType().hashCode();
        }
        if (getFeeAmount() != null) {
            _hashCode += getFeeAmount().hashCode();
        }
        if (getFeeComputationMethodIndicator() != null) {
            _hashCode += getFeeComputationMethodIndicator().hashCode();
        }
        if (getFeePercent() != null) {
            _hashCode += getFeePercent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutomaticInvestmentLinesResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentLineno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLineno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Fundid"));
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
        elemField.setFieldName("accountStatementLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountStatementLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("loadIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LoadIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NAVReasonCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NAVReasonCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SettlementAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionFeeIndicatorId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionFeeIndicatorId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeeAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeComputationMethodIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeeComputationMethodIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feePercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeePercent"));
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
