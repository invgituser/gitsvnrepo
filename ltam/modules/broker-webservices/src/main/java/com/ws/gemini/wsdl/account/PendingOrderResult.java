/**
 * PendingOrderResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class PendingOrderResult  implements java.io.Serializable {
    private int transactionID;

    private java.lang.String accountNumber;

    private int fundTransactionLineNo;

    private int fundTransactionStatus;

    private int fundId;

    private java.lang.String fundShortName;

    private int fundTransactionType;

    private int sharesOrAmountIndicator;

    private java.math.BigDecimal fxGrossAmount;

    private java.math.BigDecimal shareAmount;

    private java.util.Calendar tradeDate;

    private java.util.Calendar settlementdate;

    private int currencyId;

    private int fundBaseCurrencyId;

    public PendingOrderResult() {
    }

    public PendingOrderResult(
           int transactionID,
           java.lang.String accountNumber,
           int fundTransactionLineNo,
           int fundTransactionStatus,
           int fundId,
           java.lang.String fundShortName,
           int fundTransactionType,
           int sharesOrAmountIndicator,
           java.math.BigDecimal fxGrossAmount,
           java.math.BigDecimal shareAmount,
           java.util.Calendar tradeDate,
           java.util.Calendar settlementdate,
           int currencyId,
           int fundBaseCurrencyId) {
           this.transactionID = transactionID;
           this.accountNumber = accountNumber;
           this.fundTransactionLineNo = fundTransactionLineNo;
           this.fundTransactionStatus = fundTransactionStatus;
           this.fundId = fundId;
           this.fundShortName = fundShortName;
           this.fundTransactionType = fundTransactionType;
           this.sharesOrAmountIndicator = sharesOrAmountIndicator;
           this.fxGrossAmount = fxGrossAmount;
           this.shareAmount = shareAmount;
           this.tradeDate = tradeDate;
           this.settlementdate = settlementdate;
           this.currencyId = currencyId;
           this.fundBaseCurrencyId = fundBaseCurrencyId;
    }


    /**
     * Gets the transactionID value for this PendingOrderResult.
     * 
     * @return transactionID
     */
    public int getTransactionID() {
        return transactionID;
    }


    /**
     * Sets the transactionID value for this PendingOrderResult.
     * 
     * @param transactionID
     */
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }


    /**
     * Gets the accountNumber value for this PendingOrderResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this PendingOrderResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundTransactionLineNo value for this PendingOrderResult.
     * 
     * @return fundTransactionLineNo
     */
    public int getFundTransactionLineNo() {
        return fundTransactionLineNo;
    }


    /**
     * Sets the fundTransactionLineNo value for this PendingOrderResult.
     * 
     * @param fundTransactionLineNo
     */
    public void setFundTransactionLineNo(int fundTransactionLineNo) {
        this.fundTransactionLineNo = fundTransactionLineNo;
    }


    /**
     * Gets the fundTransactionStatus value for this PendingOrderResult.
     * 
     * @return fundTransactionStatus
     */
    public int getFundTransactionStatus() {
        return fundTransactionStatus;
    }


    /**
     * Sets the fundTransactionStatus value for this PendingOrderResult.
     * 
     * @param fundTransactionStatus
     */
    public void setFundTransactionStatus(int fundTransactionStatus) {
        this.fundTransactionStatus = fundTransactionStatus;
    }


    /**
     * Gets the fundId value for this PendingOrderResult.
     * 
     * @return fundId
     */
    public int getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this PendingOrderResult.
     * 
     * @param fundId
     */
    public void setFundId(int fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundShortName value for this PendingOrderResult.
     * 
     * @return fundShortName
     */
    public java.lang.String getFundShortName() {
        return fundShortName;
    }


    /**
     * Sets the fundShortName value for this PendingOrderResult.
     * 
     * @param fundShortName
     */
    public void setFundShortName(java.lang.String fundShortName) {
        this.fundShortName = fundShortName;
    }


    /**
     * Gets the fundTransactionType value for this PendingOrderResult.
     * 
     * @return fundTransactionType
     */
    public int getFundTransactionType() {
        return fundTransactionType;
    }


    /**
     * Sets the fundTransactionType value for this PendingOrderResult.
     * 
     * @param fundTransactionType
     */
    public void setFundTransactionType(int fundTransactionType) {
        this.fundTransactionType = fundTransactionType;
    }


    /**
     * Gets the sharesOrAmountIndicator value for this PendingOrderResult.
     * 
     * @return sharesOrAmountIndicator
     */
    public int getSharesOrAmountIndicator() {
        return sharesOrAmountIndicator;
    }


    /**
     * Sets the sharesOrAmountIndicator value for this PendingOrderResult.
     * 
     * @param sharesOrAmountIndicator
     */
    public void setSharesOrAmountIndicator(int sharesOrAmountIndicator) {
        this.sharesOrAmountIndicator = sharesOrAmountIndicator;
    }


    /**
     * Gets the fxGrossAmount value for this PendingOrderResult.
     * 
     * @return fxGrossAmount
     */
    public java.math.BigDecimal getFxGrossAmount() {
        return fxGrossAmount;
    }


    /**
     * Sets the fxGrossAmount value for this PendingOrderResult.
     * 
     * @param fxGrossAmount
     */
    public void setFxGrossAmount(java.math.BigDecimal fxGrossAmount) {
        this.fxGrossAmount = fxGrossAmount;
    }


    /**
     * Gets the shareAmount value for this PendingOrderResult.
     * 
     * @return shareAmount
     */
    public java.math.BigDecimal getShareAmount() {
        return shareAmount;
    }


    /**
     * Sets the shareAmount value for this PendingOrderResult.
     * 
     * @param shareAmount
     */
    public void setShareAmount(java.math.BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }


    /**
     * Gets the tradeDate value for this PendingOrderResult.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this PendingOrderResult.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the settlementdate value for this PendingOrderResult.
     * 
     * @return settlementdate
     */
    public java.util.Calendar getSettlementdate() {
        return settlementdate;
    }


    /**
     * Sets the settlementdate value for this PendingOrderResult.
     * 
     * @param settlementdate
     */
    public void setSettlementdate(java.util.Calendar settlementdate) {
        this.settlementdate = settlementdate;
    }


    /**
     * Gets the currencyId value for this PendingOrderResult.
     * 
     * @return currencyId
     */
    public int getCurrencyId() {
        return currencyId;
    }


    /**
     * Sets the currencyId value for this PendingOrderResult.
     * 
     * @param currencyId
     */
    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }


    /**
     * Gets the fundBaseCurrencyId value for this PendingOrderResult.
     * 
     * @return fundBaseCurrencyId
     */
    public int getFundBaseCurrencyId() {
        return fundBaseCurrencyId;
    }


    /**
     * Sets the fundBaseCurrencyId value for this PendingOrderResult.
     * 
     * @param fundBaseCurrencyId
     */
    public void setFundBaseCurrencyId(int fundBaseCurrencyId) {
        this.fundBaseCurrencyId = fundBaseCurrencyId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PendingOrderResult)) return false;
        PendingOrderResult other = (PendingOrderResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.transactionID == other.getTransactionID() &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.fundTransactionLineNo == other.getFundTransactionLineNo() &&
            this.fundTransactionStatus == other.getFundTransactionStatus() &&
            this.fundId == other.getFundId() &&
            ((this.fundShortName==null && other.getFundShortName()==null) || 
             (this.fundShortName!=null &&
              this.fundShortName.equals(other.getFundShortName()))) &&
            this.fundTransactionType == other.getFundTransactionType() &&
            this.sharesOrAmountIndicator == other.getSharesOrAmountIndicator() &&
            ((this.fxGrossAmount==null && other.getFxGrossAmount()==null) || 
             (this.fxGrossAmount!=null &&
              this.fxGrossAmount.equals(other.getFxGrossAmount()))) &&
            ((this.shareAmount==null && other.getShareAmount()==null) || 
             (this.shareAmount!=null &&
              this.shareAmount.equals(other.getShareAmount()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.settlementdate==null && other.getSettlementdate()==null) || 
             (this.settlementdate!=null &&
              this.settlementdate.equals(other.getSettlementdate()))) &&
            this.currencyId == other.getCurrencyId() &&
            this.fundBaseCurrencyId == other.getFundBaseCurrencyId();
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
        _hashCode += getTransactionID();
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getFundTransactionLineNo();
        _hashCode += getFundTransactionStatus();
        _hashCode += getFundId();
        if (getFundShortName() != null) {
            _hashCode += getFundShortName().hashCode();
        }
        _hashCode += getFundTransactionType();
        _hashCode += getSharesOrAmountIndicator();
        if (getFxGrossAmount() != null) {
            _hashCode += getFxGrossAmount().hashCode();
        }
        if (getShareAmount() != null) {
            _hashCode += getShareAmount().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getSettlementdate() != null) {
            _hashCode += getSettlementdate().hashCode();
        }
        _hashCode += getCurrencyId();
        _hashCode += getFundBaseCurrencyId();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PendingOrderResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PendingOrderResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionLineNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionLineNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharesOrAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SharesOrAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fxGrossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FxGrossAmount"));
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
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Settlementdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrencyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundBaseCurrencyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundBaseCurrencyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
