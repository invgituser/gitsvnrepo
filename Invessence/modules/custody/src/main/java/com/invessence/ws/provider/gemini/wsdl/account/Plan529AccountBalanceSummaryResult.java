/**
 * Plan529AccountBalanceSummaryResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class Plan529AccountBalanceSummaryResult  implements java.io.Serializable {
    private java.lang.String beneficiaryName;

    private java.math.BigDecimal begOfYearBalance;

    private java.math.BigDecimal YTDContributions;

    private java.math.BigDecimal YTDWithdrawals;

    private java.math.BigDecimal currentBalance;

    private java.math.BigDecimal changeInValue;

    private java.lang.String beneficiarySSN;

    private java.lang.String loggedOnUserAccount;

    private java.math.BigDecimal IRR;

    private java.math.BigDecimal LTDContributions;

    private java.math.BigDecimal LTDWithdrawals;

    private org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator;

    public Plan529AccountBalanceSummaryResult() {
    }

    public Plan529AccountBalanceSummaryResult(
           java.lang.String beneficiaryName,
           java.math.BigDecimal begOfYearBalance,
           java.math.BigDecimal YTDContributions,
           java.math.BigDecimal YTDWithdrawals,
           java.math.BigDecimal currentBalance,
           java.math.BigDecimal changeInValue,
           java.lang.String beneficiarySSN,
           java.lang.String loggedOnUserAccount,
           java.math.BigDecimal IRR,
           java.math.BigDecimal LTDContributions,
           java.math.BigDecimal LTDWithdrawals,
           org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
           this.beneficiaryName = beneficiaryName;
           this.begOfYearBalance = begOfYearBalance;
           this.YTDContributions = YTDContributions;
           this.YTDWithdrawals = YTDWithdrawals;
           this.currentBalance = currentBalance;
           this.changeInValue = changeInValue;
           this.beneficiarySSN = beneficiarySSN;
           this.loggedOnUserAccount = loggedOnUserAccount;
           this.IRR = IRR;
           this.LTDContributions = LTDContributions;
           this.LTDWithdrawals = LTDWithdrawals;
           this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }


    /**
     * Gets the beneficiaryName value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return beneficiaryName
     */
    public java.lang.String getBeneficiaryName() {
        return beneficiaryName;
    }


    /**
     * Sets the beneficiaryName value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param beneficiaryName
     */
    public void setBeneficiaryName(java.lang.String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }


    /**
     * Gets the begOfYearBalance value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return begOfYearBalance
     */
    public java.math.BigDecimal getBegOfYearBalance() {
        return begOfYearBalance;
    }


    /**
     * Sets the begOfYearBalance value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param begOfYearBalance
     */
    public void setBegOfYearBalance(java.math.BigDecimal begOfYearBalance) {
        this.begOfYearBalance = begOfYearBalance;
    }


    /**
     * Gets the YTDContributions value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return YTDContributions
     */
    public java.math.BigDecimal getYTDContributions() {
        return YTDContributions;
    }


    /**
     * Sets the YTDContributions value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param YTDContributions
     */
    public void setYTDContributions(java.math.BigDecimal YTDContributions) {
        this.YTDContributions = YTDContributions;
    }


    /**
     * Gets the YTDWithdrawals value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return YTDWithdrawals
     */
    public java.math.BigDecimal getYTDWithdrawals() {
        return YTDWithdrawals;
    }


    /**
     * Sets the YTDWithdrawals value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param YTDWithdrawals
     */
    public void setYTDWithdrawals(java.math.BigDecimal YTDWithdrawals) {
        this.YTDWithdrawals = YTDWithdrawals;
    }


    /**
     * Gets the currentBalance value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return currentBalance
     */
    public java.math.BigDecimal getCurrentBalance() {
        return currentBalance;
    }


    /**
     * Sets the currentBalance value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param currentBalance
     */
    public void setCurrentBalance(java.math.BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }


    /**
     * Gets the changeInValue value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return changeInValue
     */
    public java.math.BigDecimal getChangeInValue() {
        return changeInValue;
    }


    /**
     * Sets the changeInValue value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param changeInValue
     */
    public void setChangeInValue(java.math.BigDecimal changeInValue) {
        this.changeInValue = changeInValue;
    }


    /**
     * Gets the beneficiarySSN value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return beneficiarySSN
     */
    public java.lang.String getBeneficiarySSN() {
        return beneficiarySSN;
    }


    /**
     * Sets the beneficiarySSN value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param beneficiarySSN
     */
    public void setBeneficiarySSN(java.lang.String beneficiarySSN) {
        this.beneficiarySSN = beneficiarySSN;
    }


    /**
     * Gets the loggedOnUserAccount value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return loggedOnUserAccount
     */
    public java.lang.String getLoggedOnUserAccount() {
        return loggedOnUserAccount;
    }


    /**
     * Sets the loggedOnUserAccount value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param loggedOnUserAccount
     */
    public void setLoggedOnUserAccount(java.lang.String loggedOnUserAccount) {
        this.loggedOnUserAccount = loggedOnUserAccount;
    }


    /**
     * Gets the IRR value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return IRR
     */
    public java.math.BigDecimal getIRR() {
        return IRR;
    }


    /**
     * Sets the IRR value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param IRR
     */
    public void setIRR(java.math.BigDecimal IRR) {
        this.IRR = IRR;
    }


    /**
     * Gets the LTDContributions value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return LTDContributions
     */
    public java.math.BigDecimal getLTDContributions() {
        return LTDContributions;
    }


    /**
     * Sets the LTDContributions value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param LTDContributions
     */
    public void setLTDContributions(java.math.BigDecimal LTDContributions) {
        this.LTDContributions = LTDContributions;
    }


    /**
     * Gets the LTDWithdrawals value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return LTDWithdrawals
     */
    public java.math.BigDecimal getLTDWithdrawals() {
        return LTDWithdrawals;
    }


    /**
     * Sets the LTDWithdrawals value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param LTDWithdrawals
     */
    public void setLTDWithdrawals(java.math.BigDecimal LTDWithdrawals) {
        this.LTDWithdrawals = LTDWithdrawals;
    }


    /**
     * Gets the portfolioAllocatorIndicator value for this Plan529AccountBalanceSummaryResult.
     * 
     * @return portfolioAllocatorIndicator
     */
    public org.apache.axis.types.UnsignedByte getPortfolioAllocatorIndicator() {
        return portfolioAllocatorIndicator;
    }


    /**
     * Sets the portfolioAllocatorIndicator value for this Plan529AccountBalanceSummaryResult.
     * 
     * @param portfolioAllocatorIndicator
     */
    public void setPortfolioAllocatorIndicator(org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
        this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Plan529AccountBalanceSummaryResult)) return false;
        Plan529AccountBalanceSummaryResult other = (Plan529AccountBalanceSummaryResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.beneficiaryName==null && other.getBeneficiaryName()==null) || 
             (this.beneficiaryName!=null &&
              this.beneficiaryName.equals(other.getBeneficiaryName()))) &&
            ((this.begOfYearBalance==null && other.getBegOfYearBalance()==null) || 
             (this.begOfYearBalance!=null &&
              this.begOfYearBalance.equals(other.getBegOfYearBalance()))) &&
            ((this.YTDContributions==null && other.getYTDContributions()==null) || 
             (this.YTDContributions!=null &&
              this.YTDContributions.equals(other.getYTDContributions()))) &&
            ((this.YTDWithdrawals==null && other.getYTDWithdrawals()==null) || 
             (this.YTDWithdrawals!=null &&
              this.YTDWithdrawals.equals(other.getYTDWithdrawals()))) &&
            ((this.currentBalance==null && other.getCurrentBalance()==null) || 
             (this.currentBalance!=null &&
              this.currentBalance.equals(other.getCurrentBalance()))) &&
            ((this.changeInValue==null && other.getChangeInValue()==null) || 
             (this.changeInValue!=null &&
              this.changeInValue.equals(other.getChangeInValue()))) &&
            ((this.beneficiarySSN==null && other.getBeneficiarySSN()==null) || 
             (this.beneficiarySSN!=null &&
              this.beneficiarySSN.equals(other.getBeneficiarySSN()))) &&
            ((this.loggedOnUserAccount==null && other.getLoggedOnUserAccount()==null) || 
             (this.loggedOnUserAccount!=null &&
              this.loggedOnUserAccount.equals(other.getLoggedOnUserAccount()))) &&
            ((this.IRR==null && other.getIRR()==null) || 
             (this.IRR!=null &&
              this.IRR.equals(other.getIRR()))) &&
            ((this.LTDContributions==null && other.getLTDContributions()==null) || 
             (this.LTDContributions!=null &&
              this.LTDContributions.equals(other.getLTDContributions()))) &&
            ((this.LTDWithdrawals==null && other.getLTDWithdrawals()==null) || 
             (this.LTDWithdrawals!=null &&
              this.LTDWithdrawals.equals(other.getLTDWithdrawals()))) &&
            ((this.portfolioAllocatorIndicator==null && other.getPortfolioAllocatorIndicator()==null) || 
             (this.portfolioAllocatorIndicator!=null &&
              this.portfolioAllocatorIndicator.equals(other.getPortfolioAllocatorIndicator())));
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
        if (getBeneficiaryName() != null) {
            _hashCode += getBeneficiaryName().hashCode();
        }
        if (getBegOfYearBalance() != null) {
            _hashCode += getBegOfYearBalance().hashCode();
        }
        if (getYTDContributions() != null) {
            _hashCode += getYTDContributions().hashCode();
        }
        if (getYTDWithdrawals() != null) {
            _hashCode += getYTDWithdrawals().hashCode();
        }
        if (getCurrentBalance() != null) {
            _hashCode += getCurrentBalance().hashCode();
        }
        if (getChangeInValue() != null) {
            _hashCode += getChangeInValue().hashCode();
        }
        if (getBeneficiarySSN() != null) {
            _hashCode += getBeneficiarySSN().hashCode();
        }
        if (getLoggedOnUserAccount() != null) {
            _hashCode += getLoggedOnUserAccount().hashCode();
        }
        if (getIRR() != null) {
            _hashCode += getIRR().hashCode();
        }
        if (getLTDContributions() != null) {
            _hashCode += getLTDContributions().hashCode();
        }
        if (getLTDWithdrawals() != null) {
            _hashCode += getLTDWithdrawals().hashCode();
        }
        if (getPortfolioAllocatorIndicator() != null) {
            _hashCode += getPortfolioAllocatorIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Plan529AccountBalanceSummaryResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Plan529AccountBalanceSummaryResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("begOfYearBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BegOfYearBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDContributions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDContributions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDWithdrawals");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDWithdrawals"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrentBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("changeInValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ChangeInValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("loggedOnUserAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LoggedOnUserAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IRR");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IRR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LTDContributions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LTDContributions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LTDWithdrawals");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LTDWithdrawals"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioAllocatorIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PortfolioAllocatorIndicator"));
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
