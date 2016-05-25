/**
 * AutomaticInvestmentRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AutomaticInvestmentRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.util.Calendar startDate;

    private org.apache.axis.types.UnsignedByte schedulerFrequency;

    private org.apache.axis.types.UnsignedByte dateQualifierForDay1;

    private org.apache.axis.types.UnsignedByte dateQualifierForDay2;

    private org.apache.axis.types.UnsignedByte dayOfTheMonth1;

    private org.apache.axis.types.UnsignedByte dayOfTheMonth2;

    private java.lang.String monthIndicators;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    private int automaticInvestmentId;

    private java.lang.String memo;

    private int monthlySchedulerId;

    private org.apache.axis.types.UnsignedByte fundSelectionIndicator;

    private com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformation[] AIPFunds;

    public AutomaticInvestmentRequest() {
    }

    public AutomaticInvestmentRequest(
           java.lang.String accountNumber,
           java.util.Calendar startDate,
           org.apache.axis.types.UnsignedByte schedulerFrequency,
           org.apache.axis.types.UnsignedByte dateQualifierForDay1,
           org.apache.axis.types.UnsignedByte dateQualifierForDay2,
           org.apache.axis.types.UnsignedByte dayOfTheMonth1,
           org.apache.axis.types.UnsignedByte dayOfTheMonth2,
           java.lang.String monthIndicators,
           org.apache.axis.types.UnsignedByte accountPayeeId,
           int automaticInvestmentId,
           java.lang.String memo,
           int monthlySchedulerId,
           org.apache.axis.types.UnsignedByte fundSelectionIndicator,
           com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformation[] AIPFunds) {
           this.accountNumber = accountNumber;
           this.startDate = startDate;
           this.schedulerFrequency = schedulerFrequency;
           this.dateQualifierForDay1 = dateQualifierForDay1;
           this.dateQualifierForDay2 = dateQualifierForDay2;
           this.dayOfTheMonth1 = dayOfTheMonth1;
           this.dayOfTheMonth2 = dayOfTheMonth2;
           this.monthIndicators = monthIndicators;
           this.accountPayeeId = accountPayeeId;
           this.automaticInvestmentId = automaticInvestmentId;
           this.memo = memo;
           this.monthlySchedulerId = monthlySchedulerId;
           this.fundSelectionIndicator = fundSelectionIndicator;
           this.AIPFunds = AIPFunds;
    }


    /**
     * Gets the accountNumber value for this AutomaticInvestmentRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AutomaticInvestmentRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the startDate value for this AutomaticInvestmentRequest.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this AutomaticInvestmentRequest.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the schedulerFrequency value for this AutomaticInvestmentRequest.
     * 
     * @return schedulerFrequency
     */
    public org.apache.axis.types.UnsignedByte getSchedulerFrequency() {
        return schedulerFrequency;
    }


    /**
     * Sets the schedulerFrequency value for this AutomaticInvestmentRequest.
     * 
     * @param schedulerFrequency
     */
    public void setSchedulerFrequency(org.apache.axis.types.UnsignedByte schedulerFrequency) {
        this.schedulerFrequency = schedulerFrequency;
    }


    /**
     * Gets the dateQualifierForDay1 value for this AutomaticInvestmentRequest.
     * 
     * @return dateQualifierForDay1
     */
    public org.apache.axis.types.UnsignedByte getDateQualifierForDay1() {
        return dateQualifierForDay1;
    }


    /**
     * Sets the dateQualifierForDay1 value for this AutomaticInvestmentRequest.
     * 
     * @param dateQualifierForDay1
     */
    public void setDateQualifierForDay1(org.apache.axis.types.UnsignedByte dateQualifierForDay1) {
        this.dateQualifierForDay1 = dateQualifierForDay1;
    }


    /**
     * Gets the dateQualifierForDay2 value for this AutomaticInvestmentRequest.
     * 
     * @return dateQualifierForDay2
     */
    public org.apache.axis.types.UnsignedByte getDateQualifierForDay2() {
        return dateQualifierForDay2;
    }


    /**
     * Sets the dateQualifierForDay2 value for this AutomaticInvestmentRequest.
     * 
     * @param dateQualifierForDay2
     */
    public void setDateQualifierForDay2(org.apache.axis.types.UnsignedByte dateQualifierForDay2) {
        this.dateQualifierForDay2 = dateQualifierForDay2;
    }


    /**
     * Gets the dayOfTheMonth1 value for this AutomaticInvestmentRequest.
     * 
     * @return dayOfTheMonth1
     */
    public org.apache.axis.types.UnsignedByte getDayOfTheMonth1() {
        return dayOfTheMonth1;
    }


    /**
     * Sets the dayOfTheMonth1 value for this AutomaticInvestmentRequest.
     * 
     * @param dayOfTheMonth1
     */
    public void setDayOfTheMonth1(org.apache.axis.types.UnsignedByte dayOfTheMonth1) {
        this.dayOfTheMonth1 = dayOfTheMonth1;
    }


    /**
     * Gets the dayOfTheMonth2 value for this AutomaticInvestmentRequest.
     * 
     * @return dayOfTheMonth2
     */
    public org.apache.axis.types.UnsignedByte getDayOfTheMonth2() {
        return dayOfTheMonth2;
    }


    /**
     * Sets the dayOfTheMonth2 value for this AutomaticInvestmentRequest.
     * 
     * @param dayOfTheMonth2
     */
    public void setDayOfTheMonth2(org.apache.axis.types.UnsignedByte dayOfTheMonth2) {
        this.dayOfTheMonth2 = dayOfTheMonth2;
    }


    /**
     * Gets the monthIndicators value for this AutomaticInvestmentRequest.
     * 
     * @return monthIndicators
     */
    public java.lang.String getMonthIndicators() {
        return monthIndicators;
    }


    /**
     * Sets the monthIndicators value for this AutomaticInvestmentRequest.
     * 
     * @param monthIndicators
     */
    public void setMonthIndicators(java.lang.String monthIndicators) {
        this.monthIndicators = monthIndicators;
    }


    /**
     * Gets the accountPayeeId value for this AutomaticInvestmentRequest.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this AutomaticInvestmentRequest.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the automaticInvestmentId value for this AutomaticInvestmentRequest.
     * 
     * @return automaticInvestmentId
     */
    public int getAutomaticInvestmentId() {
        return automaticInvestmentId;
    }


    /**
     * Sets the automaticInvestmentId value for this AutomaticInvestmentRequest.
     * 
     * @param automaticInvestmentId
     */
    public void setAutomaticInvestmentId(int automaticInvestmentId) {
        this.automaticInvestmentId = automaticInvestmentId;
    }


    /**
     * Gets the memo value for this AutomaticInvestmentRequest.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this AutomaticInvestmentRequest.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the monthlySchedulerId value for this AutomaticInvestmentRequest.
     * 
     * @return monthlySchedulerId
     */
    public int getMonthlySchedulerId() {
        return monthlySchedulerId;
    }


    /**
     * Sets the monthlySchedulerId value for this AutomaticInvestmentRequest.
     * 
     * @param monthlySchedulerId
     */
    public void setMonthlySchedulerId(int monthlySchedulerId) {
        this.monthlySchedulerId = monthlySchedulerId;
    }


    /**
     * Gets the fundSelectionIndicator value for this AutomaticInvestmentRequest.
     * 
     * @return fundSelectionIndicator
     */
    public org.apache.axis.types.UnsignedByte getFundSelectionIndicator() {
        return fundSelectionIndicator;
    }


    /**
     * Sets the fundSelectionIndicator value for this AutomaticInvestmentRequest.
     * 
     * @param fundSelectionIndicator
     */
    public void setFundSelectionIndicator(org.apache.axis.types.UnsignedByte fundSelectionIndicator) {
        this.fundSelectionIndicator = fundSelectionIndicator;
    }


    /**
     * Gets the AIPFunds value for this AutomaticInvestmentRequest.
     * 
     * @return AIPFunds
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformation[] getAIPFunds() {
        return AIPFunds;
    }


    /**
     * Sets the AIPFunds value for this AutomaticInvestmentRequest.
     * 
     * @param AIPFunds
     */
    public void setAIPFunds(com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformation[] AIPFunds) {
        this.AIPFunds = AIPFunds;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomaticInvestmentRequest)) return false;
        AutomaticInvestmentRequest other = (AutomaticInvestmentRequest) obj;
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
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.schedulerFrequency==null && other.getSchedulerFrequency()==null) || 
             (this.schedulerFrequency!=null &&
              this.schedulerFrequency.equals(other.getSchedulerFrequency()))) &&
            ((this.dateQualifierForDay1==null && other.getDateQualifierForDay1()==null) || 
             (this.dateQualifierForDay1!=null &&
              this.dateQualifierForDay1.equals(other.getDateQualifierForDay1()))) &&
            ((this.dateQualifierForDay2==null && other.getDateQualifierForDay2()==null) || 
             (this.dateQualifierForDay2!=null &&
              this.dateQualifierForDay2.equals(other.getDateQualifierForDay2()))) &&
            ((this.dayOfTheMonth1==null && other.getDayOfTheMonth1()==null) || 
             (this.dayOfTheMonth1!=null &&
              this.dayOfTheMonth1.equals(other.getDayOfTheMonth1()))) &&
            ((this.dayOfTheMonth2==null && other.getDayOfTheMonth2()==null) || 
             (this.dayOfTheMonth2!=null &&
              this.dayOfTheMonth2.equals(other.getDayOfTheMonth2()))) &&
            ((this.monthIndicators==null && other.getMonthIndicators()==null) || 
             (this.monthIndicators!=null &&
              this.monthIndicators.equals(other.getMonthIndicators()))) &&
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId()))) &&
            this.automaticInvestmentId == other.getAutomaticInvestmentId() &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            this.monthlySchedulerId == other.getMonthlySchedulerId() &&
            ((this.fundSelectionIndicator==null && other.getFundSelectionIndicator()==null) || 
             (this.fundSelectionIndicator!=null &&
              this.fundSelectionIndicator.equals(other.getFundSelectionIndicator()))) &&
            ((this.AIPFunds==null && other.getAIPFunds()==null) || 
             (this.AIPFunds!=null &&
              java.util.Arrays.equals(this.AIPFunds, other.getAIPFunds())));
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
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getSchedulerFrequency() != null) {
            _hashCode += getSchedulerFrequency().hashCode();
        }
        if (getDateQualifierForDay1() != null) {
            _hashCode += getDateQualifierForDay1().hashCode();
        }
        if (getDateQualifierForDay2() != null) {
            _hashCode += getDateQualifierForDay2().hashCode();
        }
        if (getDayOfTheMonth1() != null) {
            _hashCode += getDayOfTheMonth1().hashCode();
        }
        if (getDayOfTheMonth2() != null) {
            _hashCode += getDayOfTheMonth2().hashCode();
        }
        if (getMonthIndicators() != null) {
            _hashCode += getMonthIndicators().hashCode();
        }
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        _hashCode += getAutomaticInvestmentId();
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        _hashCode += getMonthlySchedulerId();
        if (getFundSelectionIndicator() != null) {
            _hashCode += getFundSelectionIndicator().hashCode();
        }
        if (getAIPFunds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAIPFunds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAIPFunds(), i);
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
        new org.apache.axis.description.TypeDesc(AutomaticInvestmentRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedulerFrequency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SchedulerFrequency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateQualifierForDay1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateQualifierForDay1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateQualifierForDay2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateQualifierForDay2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayOfTheMonth1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DayOfTheMonth1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayOfTheMonth2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DayOfTheMonth2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthIndicators");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MonthIndicators"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("monthlySchedulerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MonthlySchedulerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundSelectionIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundSelectionIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AIPFunds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFunds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformation"));
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
