/**
 * AccountDividendSetupRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountDividendSetupRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private org.apache.axis.types.UnsignedByte dividend;

    private org.apache.axis.types.UnsignedByte STCG;

    private org.apache.axis.types.UnsignedByte LTCG;

    private org.apache.axis.types.UnsignedByte returnOfCapital;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    private org.apache.axis.types.UnsignedByte retirementIndicator;

    public AccountDividendSetupRequest() {
    }

    public AccountDividendSetupRequest(
           java.lang.String accountNumber,
           short fundId,
           org.apache.axis.types.UnsignedByte dividend,
           org.apache.axis.types.UnsignedByte STCG,
           org.apache.axis.types.UnsignedByte LTCG,
           org.apache.axis.types.UnsignedByte returnOfCapital,
           org.apache.axis.types.UnsignedByte accountPayeeId,
           org.apache.axis.types.UnsignedByte retirementIndicator) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.dividend = dividend;
           this.STCG = STCG;
           this.LTCG = LTCG;
           this.returnOfCapital = returnOfCapital;
           this.accountPayeeId = accountPayeeId;
           this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the accountNumber value for this AccountDividendSetupRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AccountDividendSetupRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this AccountDividendSetupRequest.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this AccountDividendSetupRequest.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the dividend value for this AccountDividendSetupRequest.
     * 
     * @return dividend
     */
    public org.apache.axis.types.UnsignedByte getDividend() {
        return dividend;
    }


    /**
     * Sets the dividend value for this AccountDividendSetupRequest.
     * 
     * @param dividend
     */
    public void setDividend(org.apache.axis.types.UnsignedByte dividend) {
        this.dividend = dividend;
    }


    /**
     * Gets the STCG value for this AccountDividendSetupRequest.
     * 
     * @return STCG
     */
    public org.apache.axis.types.UnsignedByte getSTCG() {
        return STCG;
    }


    /**
     * Sets the STCG value for this AccountDividendSetupRequest.
     * 
     * @param STCG
     */
    public void setSTCG(org.apache.axis.types.UnsignedByte STCG) {
        this.STCG = STCG;
    }


    /**
     * Gets the LTCG value for this AccountDividendSetupRequest.
     * 
     * @return LTCG
     */
    public org.apache.axis.types.UnsignedByte getLTCG() {
        return LTCG;
    }


    /**
     * Sets the LTCG value for this AccountDividendSetupRequest.
     * 
     * @param LTCG
     */
    public void setLTCG(org.apache.axis.types.UnsignedByte LTCG) {
        this.LTCG = LTCG;
    }


    /**
     * Gets the returnOfCapital value for this AccountDividendSetupRequest.
     * 
     * @return returnOfCapital
     */
    public org.apache.axis.types.UnsignedByte getReturnOfCapital() {
        return returnOfCapital;
    }


    /**
     * Sets the returnOfCapital value for this AccountDividendSetupRequest.
     * 
     * @param returnOfCapital
     */
    public void setReturnOfCapital(org.apache.axis.types.UnsignedByte returnOfCapital) {
        this.returnOfCapital = returnOfCapital;
    }


    /**
     * Gets the accountPayeeId value for this AccountDividendSetupRequest.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this AccountDividendSetupRequest.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the retirementIndicator value for this AccountDividendSetupRequest.
     * 
     * @return retirementIndicator
     */
    public org.apache.axis.types.UnsignedByte getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this AccountDividendSetupRequest.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(org.apache.axis.types.UnsignedByte retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountDividendSetupRequest)) return false;
        AccountDividendSetupRequest other = (AccountDividendSetupRequest) obj;
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
            ((this.dividend==null && other.getDividend()==null) || 
             (this.dividend!=null &&
              this.dividend.equals(other.getDividend()))) &&
            ((this.STCG==null && other.getSTCG()==null) || 
             (this.STCG!=null &&
              this.STCG.equals(other.getSTCG()))) &&
            ((this.LTCG==null && other.getLTCG()==null) || 
             (this.LTCG!=null &&
              this.LTCG.equals(other.getLTCG()))) &&
            ((this.returnOfCapital==null && other.getReturnOfCapital()==null) || 
             (this.returnOfCapital!=null &&
              this.returnOfCapital.equals(other.getReturnOfCapital()))) &&
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId()))) &&
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
        if (getDividend() != null) {
            _hashCode += getDividend().hashCode();
        }
        if (getSTCG() != null) {
            _hashCode += getSTCG().hashCode();
        }
        if (getLTCG() != null) {
            _hashCode += getLTCG().hashCode();
        }
        if (getReturnOfCapital() != null) {
            _hashCode += getReturnOfCapital().hashCode();
        }
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountDividendSetupRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountDividendSetupRequest"));
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
        elemField.setFieldName("dividend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Dividend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "STCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnOfCapital");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReturnOfCapital"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(true);
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
