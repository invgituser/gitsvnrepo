/**
 * PrepaidActivityResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class PrepaidActivityResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private int transactionId;

    private org.apache.axis.types.UnsignedByte transactionLineNo;

    private java.util.Calendar tradeDate;

    private org.apache.axis.types.UnsignedByte transactionType;

    private java.lang.String transactionDescription;

    private java.math.BigDecimal transactionAmount;

    private java.math.BigDecimal basisAmount;

    private java.math.BigDecimal earningsAmount;

    private short institutionId;

    private java.lang.String institutionName;

    private short programYearId;

    private java.lang.String programYearDescription;

    private java.math.BigDecimal tuitionCreditYears;

    private java.lang.String fromToAccountNumber;

    private org.apache.axis.types.UnsignedByte redemptionType;

    private java.lang.String memo;

    private java.lang.String retirementIndicatorDescription;

    private org.apache.axis.types.UnsignedByte retirementIndicator;

    public PrepaidActivityResult() {
    }

    public PrepaidActivityResult(
           java.lang.String accountNumber,
           short fundId,
           int transactionId,
           org.apache.axis.types.UnsignedByte transactionLineNo,
           java.util.Calendar tradeDate,
           org.apache.axis.types.UnsignedByte transactionType,
           java.lang.String transactionDescription,
           java.math.BigDecimal transactionAmount,
           java.math.BigDecimal basisAmount,
           java.math.BigDecimal earningsAmount,
           short institutionId,
           java.lang.String institutionName,
           short programYearId,
           java.lang.String programYearDescription,
           java.math.BigDecimal tuitionCreditYears,
           java.lang.String fromToAccountNumber,
           org.apache.axis.types.UnsignedByte redemptionType,
           java.lang.String memo,
           java.lang.String retirementIndicatorDescription,
           org.apache.axis.types.UnsignedByte retirementIndicator) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.transactionId = transactionId;
           this.transactionLineNo = transactionLineNo;
           this.tradeDate = tradeDate;
           this.transactionType = transactionType;
           this.transactionDescription = transactionDescription;
           this.transactionAmount = transactionAmount;
           this.basisAmount = basisAmount;
           this.earningsAmount = earningsAmount;
           this.institutionId = institutionId;
           this.institutionName = institutionName;
           this.programYearId = programYearId;
           this.programYearDescription = programYearDescription;
           this.tuitionCreditYears = tuitionCreditYears;
           this.fromToAccountNumber = fromToAccountNumber;
           this.redemptionType = redemptionType;
           this.memo = memo;
           this.retirementIndicatorDescription = retirementIndicatorDescription;
           this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the accountNumber value for this PrepaidActivityResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this PrepaidActivityResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this PrepaidActivityResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this PrepaidActivityResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the transactionId value for this PrepaidActivityResult.
     * 
     * @return transactionId
     */
    public int getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this PrepaidActivityResult.
     * 
     * @param transactionId
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the transactionLineNo value for this PrepaidActivityResult.
     * 
     * @return transactionLineNo
     */
    public org.apache.axis.types.UnsignedByte getTransactionLineNo() {
        return transactionLineNo;
    }


    /**
     * Sets the transactionLineNo value for this PrepaidActivityResult.
     * 
     * @param transactionLineNo
     */
    public void setTransactionLineNo(org.apache.axis.types.UnsignedByte transactionLineNo) {
        this.transactionLineNo = transactionLineNo;
    }


    /**
     * Gets the tradeDate value for this PrepaidActivityResult.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this PrepaidActivityResult.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the transactionType value for this PrepaidActivityResult.
     * 
     * @return transactionType
     */
    public org.apache.axis.types.UnsignedByte getTransactionType() {
        return transactionType;
    }


    /**
     * Sets the transactionType value for this PrepaidActivityResult.
     * 
     * @param transactionType
     */
    public void setTransactionType(org.apache.axis.types.UnsignedByte transactionType) {
        this.transactionType = transactionType;
    }


    /**
     * Gets the transactionDescription value for this PrepaidActivityResult.
     * 
     * @return transactionDescription
     */
    public java.lang.String getTransactionDescription() {
        return transactionDescription;
    }


    /**
     * Sets the transactionDescription value for this PrepaidActivityResult.
     * 
     * @param transactionDescription
     */
    public void setTransactionDescription(java.lang.String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }


    /**
     * Gets the transactionAmount value for this PrepaidActivityResult.
     * 
     * @return transactionAmount
     */
    public java.math.BigDecimal getTransactionAmount() {
        return transactionAmount;
    }


    /**
     * Sets the transactionAmount value for this PrepaidActivityResult.
     * 
     * @param transactionAmount
     */
    public void setTransactionAmount(java.math.BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }


    /**
     * Gets the basisAmount value for this PrepaidActivityResult.
     * 
     * @return basisAmount
     */
    public java.math.BigDecimal getBasisAmount() {
        return basisAmount;
    }


    /**
     * Sets the basisAmount value for this PrepaidActivityResult.
     * 
     * @param basisAmount
     */
    public void setBasisAmount(java.math.BigDecimal basisAmount) {
        this.basisAmount = basisAmount;
    }


    /**
     * Gets the earningsAmount value for this PrepaidActivityResult.
     * 
     * @return earningsAmount
     */
    public java.math.BigDecimal getEarningsAmount() {
        return earningsAmount;
    }


    /**
     * Sets the earningsAmount value for this PrepaidActivityResult.
     * 
     * @param earningsAmount
     */
    public void setEarningsAmount(java.math.BigDecimal earningsAmount) {
        this.earningsAmount = earningsAmount;
    }


    /**
     * Gets the institutionId value for this PrepaidActivityResult.
     * 
     * @return institutionId
     */
    public short getInstitutionId() {
        return institutionId;
    }


    /**
     * Sets the institutionId value for this PrepaidActivityResult.
     * 
     * @param institutionId
     */
    public void setInstitutionId(short institutionId) {
        this.institutionId = institutionId;
    }


    /**
     * Gets the institutionName value for this PrepaidActivityResult.
     * 
     * @return institutionName
     */
    public java.lang.String getInstitutionName() {
        return institutionName;
    }


    /**
     * Sets the institutionName value for this PrepaidActivityResult.
     * 
     * @param institutionName
     */
    public void setInstitutionName(java.lang.String institutionName) {
        this.institutionName = institutionName;
    }


    /**
     * Gets the programYearId value for this PrepaidActivityResult.
     * 
     * @return programYearId
     */
    public short getProgramYearId() {
        return programYearId;
    }


    /**
     * Sets the programYearId value for this PrepaidActivityResult.
     * 
     * @param programYearId
     */
    public void setProgramYearId(short programYearId) {
        this.programYearId = programYearId;
    }


    /**
     * Gets the programYearDescription value for this PrepaidActivityResult.
     * 
     * @return programYearDescription
     */
    public java.lang.String getProgramYearDescription() {
        return programYearDescription;
    }


    /**
     * Sets the programYearDescription value for this PrepaidActivityResult.
     * 
     * @param programYearDescription
     */
    public void setProgramYearDescription(java.lang.String programYearDescription) {
        this.programYearDescription = programYearDescription;
    }


    /**
     * Gets the tuitionCreditYears value for this PrepaidActivityResult.
     * 
     * @return tuitionCreditYears
     */
    public java.math.BigDecimal getTuitionCreditYears() {
        return tuitionCreditYears;
    }


    /**
     * Sets the tuitionCreditYears value for this PrepaidActivityResult.
     * 
     * @param tuitionCreditYears
     */
    public void setTuitionCreditYears(java.math.BigDecimal tuitionCreditYears) {
        this.tuitionCreditYears = tuitionCreditYears;
    }


    /**
     * Gets the fromToAccountNumber value for this PrepaidActivityResult.
     * 
     * @return fromToAccountNumber
     */
    public java.lang.String getFromToAccountNumber() {
        return fromToAccountNumber;
    }


    /**
     * Sets the fromToAccountNumber value for this PrepaidActivityResult.
     * 
     * @param fromToAccountNumber
     */
    public void setFromToAccountNumber(java.lang.String fromToAccountNumber) {
        this.fromToAccountNumber = fromToAccountNumber;
    }


    /**
     * Gets the redemptionType value for this PrepaidActivityResult.
     * 
     * @return redemptionType
     */
    public org.apache.axis.types.UnsignedByte getRedemptionType() {
        return redemptionType;
    }


    /**
     * Sets the redemptionType value for this PrepaidActivityResult.
     * 
     * @param redemptionType
     */
    public void setRedemptionType(org.apache.axis.types.UnsignedByte redemptionType) {
        this.redemptionType = redemptionType;
    }


    /**
     * Gets the memo value for this PrepaidActivityResult.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this PrepaidActivityResult.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the retirementIndicatorDescription value for this PrepaidActivityResult.
     * 
     * @return retirementIndicatorDescription
     */
    public java.lang.String getRetirementIndicatorDescription() {
        return retirementIndicatorDescription;
    }


    /**
     * Sets the retirementIndicatorDescription value for this PrepaidActivityResult.
     * 
     * @param retirementIndicatorDescription
     */
    public void setRetirementIndicatorDescription(java.lang.String retirementIndicatorDescription) {
        this.retirementIndicatorDescription = retirementIndicatorDescription;
    }


    /**
     * Gets the retirementIndicator value for this PrepaidActivityResult.
     * 
     * @return retirementIndicator
     */
    public org.apache.axis.types.UnsignedByte getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this PrepaidActivityResult.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(org.apache.axis.types.UnsignedByte retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrepaidActivityResult)) return false;
        PrepaidActivityResult other = (PrepaidActivityResult) obj;
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
            this.transactionId == other.getTransactionId() &&
            ((this.transactionLineNo==null && other.getTransactionLineNo()==null) || 
             (this.transactionLineNo!=null &&
              this.transactionLineNo.equals(other.getTransactionLineNo()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.transactionType==null && other.getTransactionType()==null) || 
             (this.transactionType!=null &&
              this.transactionType.equals(other.getTransactionType()))) &&
            ((this.transactionDescription==null && other.getTransactionDescription()==null) || 
             (this.transactionDescription!=null &&
              this.transactionDescription.equals(other.getTransactionDescription()))) &&
            ((this.transactionAmount==null && other.getTransactionAmount()==null) || 
             (this.transactionAmount!=null &&
              this.transactionAmount.equals(other.getTransactionAmount()))) &&
            ((this.basisAmount==null && other.getBasisAmount()==null) || 
             (this.basisAmount!=null &&
              this.basisAmount.equals(other.getBasisAmount()))) &&
            ((this.earningsAmount==null && other.getEarningsAmount()==null) || 
             (this.earningsAmount!=null &&
              this.earningsAmount.equals(other.getEarningsAmount()))) &&
            this.institutionId == other.getInstitutionId() &&
            ((this.institutionName==null && other.getInstitutionName()==null) || 
             (this.institutionName!=null &&
              this.institutionName.equals(other.getInstitutionName()))) &&
            this.programYearId == other.getProgramYearId() &&
            ((this.programYearDescription==null && other.getProgramYearDescription()==null) || 
             (this.programYearDescription!=null &&
              this.programYearDescription.equals(other.getProgramYearDescription()))) &&
            ((this.tuitionCreditYears==null && other.getTuitionCreditYears()==null) || 
             (this.tuitionCreditYears!=null &&
              this.tuitionCreditYears.equals(other.getTuitionCreditYears()))) &&
            ((this.fromToAccountNumber==null && other.getFromToAccountNumber()==null) || 
             (this.fromToAccountNumber!=null &&
              this.fromToAccountNumber.equals(other.getFromToAccountNumber()))) &&
            ((this.redemptionType==null && other.getRedemptionType()==null) || 
             (this.redemptionType!=null &&
              this.redemptionType.equals(other.getRedemptionType()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.retirementIndicatorDescription==null && other.getRetirementIndicatorDescription()==null) || 
             (this.retirementIndicatorDescription!=null &&
              this.retirementIndicatorDescription.equals(other.getRetirementIndicatorDescription()))) &&
            ((this.retirementIndicator==null && other.getRetirementIndicator()==null) || 
             (this.retirementIndicator!=null &&
              this.retirementIndicator.equals(other.getRetirementIndicator())));
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
        _hashCode += getTransactionId();
        if (getTransactionLineNo() != null) {
            _hashCode += getTransactionLineNo().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getTransactionType() != null) {
            _hashCode += getTransactionType().hashCode();
        }
        if (getTransactionDescription() != null) {
            _hashCode += getTransactionDescription().hashCode();
        }
        if (getTransactionAmount() != null) {
            _hashCode += getTransactionAmount().hashCode();
        }
        if (getBasisAmount() != null) {
            _hashCode += getBasisAmount().hashCode();
        }
        if (getEarningsAmount() != null) {
            _hashCode += getEarningsAmount().hashCode();
        }
        _hashCode += getInstitutionId();
        if (getInstitutionName() != null) {
            _hashCode += getInstitutionName().hashCode();
        }
        _hashCode += getProgramYearId();
        if (getProgramYearDescription() != null) {
            _hashCode += getProgramYearDescription().hashCode();
        }
        if (getTuitionCreditYears() != null) {
            _hashCode += getTuitionCreditYears().hashCode();
        }
        if (getFromToAccountNumber() != null) {
            _hashCode += getFromToAccountNumber().hashCode();
        }
        if (getRedemptionType() != null) {
            _hashCode += getRedemptionType().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getRetirementIndicatorDescription() != null) {
            _hashCode += getRetirementIndicatorDescription().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrepaidActivityResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidActivityResult"));
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
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionLineNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionLineNo"));
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
        elemField.setFieldName("transactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("basisAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BasisAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("earningsAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EarningsAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCreditYears");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCreditYears"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromToAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FromToAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Memo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retirementIndicatorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RetirementIndicatorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retirementIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RetirementIndicator"));
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
