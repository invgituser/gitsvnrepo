/**
 * Plan529AccountBalanceActivityResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class Plan529AccountBalanceActivityResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private java.lang.String fundShortName;

    private java.lang.String beneficiarySSN;

    private java.lang.String beneficiaryName;

    private java.math.BigDecimal currentBalance;

    private java.math.BigDecimal totalShares;

    private java.math.BigDecimal NAV;

    private java.math.BigDecimal rate;

    private java.util.Calendar infoDate;

    private java.math.BigDecimal principalProtectionAmount;

    public Plan529AccountBalanceActivityResult() {
    }

    public Plan529AccountBalanceActivityResult(
           java.lang.String accountNumber,
           short fundId,
           java.lang.String fundShortName,
           java.lang.String beneficiarySSN,
           java.lang.String beneficiaryName,
           java.math.BigDecimal currentBalance,
           java.math.BigDecimal totalShares,
           java.math.BigDecimal NAV,
           java.math.BigDecimal rate,
           java.util.Calendar infoDate,
           java.math.BigDecimal principalProtectionAmount) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.fundShortName = fundShortName;
           this.beneficiarySSN = beneficiarySSN;
           this.beneficiaryName = beneficiaryName;
           this.currentBalance = currentBalance;
           this.totalShares = totalShares;
           this.NAV = NAV;
           this.rate = rate;
           this.infoDate = infoDate;
           this.principalProtectionAmount = principalProtectionAmount;
    }


    /**
     * Gets the accountNumber value for this Plan529AccountBalanceActivityResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this Plan529AccountBalanceActivityResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this Plan529AccountBalanceActivityResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this Plan529AccountBalanceActivityResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundShortName value for this Plan529AccountBalanceActivityResult.
     * 
     * @return fundShortName
     */
    public java.lang.String getFundShortName() {
        return fundShortName;
    }


    /**
     * Sets the fundShortName value for this Plan529AccountBalanceActivityResult.
     * 
     * @param fundShortName
     */
    public void setFundShortName(java.lang.String fundShortName) {
        this.fundShortName = fundShortName;
    }


    /**
     * Gets the beneficiarySSN value for this Plan529AccountBalanceActivityResult.
     * 
     * @return beneficiarySSN
     */
    public java.lang.String getBeneficiarySSN() {
        return beneficiarySSN;
    }


    /**
     * Sets the beneficiarySSN value for this Plan529AccountBalanceActivityResult.
     * 
     * @param beneficiarySSN
     */
    public void setBeneficiarySSN(java.lang.String beneficiarySSN) {
        this.beneficiarySSN = beneficiarySSN;
    }


    /**
     * Gets the beneficiaryName value for this Plan529AccountBalanceActivityResult.
     * 
     * @return beneficiaryName
     */
    public java.lang.String getBeneficiaryName() {
        return beneficiaryName;
    }


    /**
     * Sets the beneficiaryName value for this Plan529AccountBalanceActivityResult.
     * 
     * @param beneficiaryName
     */
    public void setBeneficiaryName(java.lang.String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }


    /**
     * Gets the currentBalance value for this Plan529AccountBalanceActivityResult.
     * 
     * @return currentBalance
     */
    public java.math.BigDecimal getCurrentBalance() {
        return currentBalance;
    }


    /**
     * Sets the currentBalance value for this Plan529AccountBalanceActivityResult.
     * 
     * @param currentBalance
     */
    public void setCurrentBalance(java.math.BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }


    /**
     * Gets the totalShares value for this Plan529AccountBalanceActivityResult.
     * 
     * @return totalShares
     */
    public java.math.BigDecimal getTotalShares() {
        return totalShares;
    }


    /**
     * Sets the totalShares value for this Plan529AccountBalanceActivityResult.
     * 
     * @param totalShares
     */
    public void setTotalShares(java.math.BigDecimal totalShares) {
        this.totalShares = totalShares;
    }


    /**
     * Gets the NAV value for this Plan529AccountBalanceActivityResult.
     * 
     * @return NAV
     */
    public java.math.BigDecimal getNAV() {
        return NAV;
    }


    /**
     * Sets the NAV value for this Plan529AccountBalanceActivityResult.
     * 
     * @param NAV
     */
    public void setNAV(java.math.BigDecimal NAV) {
        this.NAV = NAV;
    }


    /**
     * Gets the rate value for this Plan529AccountBalanceActivityResult.
     * 
     * @return rate
     */
    public java.math.BigDecimal getRate() {
        return rate;
    }


    /**
     * Sets the rate value for this Plan529AccountBalanceActivityResult.
     * 
     * @param rate
     */
    public void setRate(java.math.BigDecimal rate) {
        this.rate = rate;
    }


    /**
     * Gets the infoDate value for this Plan529AccountBalanceActivityResult.
     * 
     * @return infoDate
     */
    public java.util.Calendar getInfoDate() {
        return infoDate;
    }


    /**
     * Sets the infoDate value for this Plan529AccountBalanceActivityResult.
     * 
     * @param infoDate
     */
    public void setInfoDate(java.util.Calendar infoDate) {
        this.infoDate = infoDate;
    }


    /**
     * Gets the principalProtectionAmount value for this Plan529AccountBalanceActivityResult.
     * 
     * @return principalProtectionAmount
     */
    public java.math.BigDecimal getPrincipalProtectionAmount() {
        return principalProtectionAmount;
    }


    /**
     * Sets the principalProtectionAmount value for this Plan529AccountBalanceActivityResult.
     * 
     * @param principalProtectionAmount
     */
    public void setPrincipalProtectionAmount(java.math.BigDecimal principalProtectionAmount) {
        this.principalProtectionAmount = principalProtectionAmount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Plan529AccountBalanceActivityResult)) return false;
        Plan529AccountBalanceActivityResult other = (Plan529AccountBalanceActivityResult) obj;
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
            ((this.fundShortName==null && other.getFundShortName()==null) || 
             (this.fundShortName!=null &&
              this.fundShortName.equals(other.getFundShortName()))) &&
            ((this.beneficiarySSN==null && other.getBeneficiarySSN()==null) || 
             (this.beneficiarySSN!=null &&
              this.beneficiarySSN.equals(other.getBeneficiarySSN()))) &&
            ((this.beneficiaryName==null && other.getBeneficiaryName()==null) || 
             (this.beneficiaryName!=null &&
              this.beneficiaryName.equals(other.getBeneficiaryName()))) &&
            ((this.currentBalance==null && other.getCurrentBalance()==null) || 
             (this.currentBalance!=null &&
              this.currentBalance.equals(other.getCurrentBalance()))) &&
            ((this.totalShares==null && other.getTotalShares()==null) || 
             (this.totalShares!=null &&
              this.totalShares.equals(other.getTotalShares()))) &&
            ((this.NAV==null && other.getNAV()==null) || 
             (this.NAV!=null &&
              this.NAV.equals(other.getNAV()))) &&
            ((this.rate==null && other.getRate()==null) || 
             (this.rate!=null &&
              this.rate.equals(other.getRate()))) &&
            ((this.infoDate==null && other.getInfoDate()==null) || 
             (this.infoDate!=null &&
              this.infoDate.equals(other.getInfoDate()))) &&
            ((this.principalProtectionAmount==null && other.getPrincipalProtectionAmount()==null) || 
             (this.principalProtectionAmount!=null &&
              this.principalProtectionAmount.equals(other.getPrincipalProtectionAmount())));
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
        if (getFundShortName() != null) {
            _hashCode += getFundShortName().hashCode();
        }
        if (getBeneficiarySSN() != null) {
            _hashCode += getBeneficiarySSN().hashCode();
        }
        if (getBeneficiaryName() != null) {
            _hashCode += getBeneficiaryName().hashCode();
        }
        if (getCurrentBalance() != null) {
            _hashCode += getCurrentBalance().hashCode();
        }
        if (getTotalShares() != null) {
            _hashCode += getTotalShares().hashCode();
        }
        if (getNAV() != null) {
            _hashCode += getNAV().hashCode();
        }
        if (getRate() != null) {
            _hashCode += getRate().hashCode();
        }
        if (getInfoDate() != null) {
            _hashCode += getInfoDate().hashCode();
        }
        if (getPrincipalProtectionAmount() != null) {
            _hashCode += getPrincipalProtectionAmount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Plan529AccountBalanceActivityResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceActivityResult"));
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
        elemField.setFieldName("fundShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiarySSN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiarySSN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrentBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NAV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NAV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InfoDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("principalProtectionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrincipalProtectionAmount"));
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
