/**
 * TransactionMessageResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public class TransactionMessageResult  implements java.io.Serializable {
    private int transactionId;

    private org.apache.axis.types.UnsignedByte fundTransactionLineNo;

    private short transactionMessageId;

    private java.lang.String transactionMessage;

    private org.apache.axis.types.UnsignedByte selectedMessageType;

    private java.lang.String accountNumber;

    private short fundId;

    private java.lang.String fundShortName;

    private java.lang.String customTransactionMessageString;

    public TransactionMessageResult() {
    }

    public TransactionMessageResult(
           int transactionId,
           org.apache.axis.types.UnsignedByte fundTransactionLineNo,
           short transactionMessageId,
           java.lang.String transactionMessage,
           org.apache.axis.types.UnsignedByte selectedMessageType,
           java.lang.String accountNumber,
           short fundId,
           java.lang.String fundShortName,
           java.lang.String customTransactionMessageString) {
           this.transactionId = transactionId;
           this.fundTransactionLineNo = fundTransactionLineNo;
           this.transactionMessageId = transactionMessageId;
           this.transactionMessage = transactionMessage;
           this.selectedMessageType = selectedMessageType;
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.fundShortName = fundShortName;
           this.customTransactionMessageString = customTransactionMessageString;
    }


    /**
     * Gets the transactionId value for this TransactionMessageResult.
     * 
     * @return transactionId
     */
    public int getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this TransactionMessageResult.
     * 
     * @param transactionId
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the fundTransactionLineNo value for this TransactionMessageResult.
     * 
     * @return fundTransactionLineNo
     */
    public org.apache.axis.types.UnsignedByte getFundTransactionLineNo() {
        return fundTransactionLineNo;
    }


    /**
     * Sets the fundTransactionLineNo value for this TransactionMessageResult.
     * 
     * @param fundTransactionLineNo
     */
    public void setFundTransactionLineNo(org.apache.axis.types.UnsignedByte fundTransactionLineNo) {
        this.fundTransactionLineNo = fundTransactionLineNo;
    }


    /**
     * Gets the transactionMessageId value for this TransactionMessageResult.
     * 
     * @return transactionMessageId
     */
    public short getTransactionMessageId() {
        return transactionMessageId;
    }


    /**
     * Sets the transactionMessageId value for this TransactionMessageResult.
     * 
     * @param transactionMessageId
     */
    public void setTransactionMessageId(short transactionMessageId) {
        this.transactionMessageId = transactionMessageId;
    }


    /**
     * Gets the transactionMessage value for this TransactionMessageResult.
     * 
     * @return transactionMessage
     */
    public java.lang.String getTransactionMessage() {
        return transactionMessage;
    }


    /**
     * Sets the transactionMessage value for this TransactionMessageResult.
     * 
     * @param transactionMessage
     */
    public void setTransactionMessage(java.lang.String transactionMessage) {
        this.transactionMessage = transactionMessage;
    }


    /**
     * Gets the selectedMessageType value for this TransactionMessageResult.
     * 
     * @return selectedMessageType
     */
    public org.apache.axis.types.UnsignedByte getSelectedMessageType() {
        return selectedMessageType;
    }


    /**
     * Sets the selectedMessageType value for this TransactionMessageResult.
     * 
     * @param selectedMessageType
     */
    public void setSelectedMessageType(org.apache.axis.types.UnsignedByte selectedMessageType) {
        this.selectedMessageType = selectedMessageType;
    }


    /**
     * Gets the accountNumber value for this TransactionMessageResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this TransactionMessageResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this TransactionMessageResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this TransactionMessageResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundShortName value for this TransactionMessageResult.
     * 
     * @return fundShortName
     */
    public java.lang.String getFundShortName() {
        return fundShortName;
    }


    /**
     * Sets the fundShortName value for this TransactionMessageResult.
     * 
     * @param fundShortName
     */
    public void setFundShortName(java.lang.String fundShortName) {
        this.fundShortName = fundShortName;
    }


    /**
     * Gets the customTransactionMessageString value for this TransactionMessageResult.
     * 
     * @return customTransactionMessageString
     */
    public java.lang.String getCustomTransactionMessageString() {
        return customTransactionMessageString;
    }


    /**
     * Sets the customTransactionMessageString value for this TransactionMessageResult.
     * 
     * @param customTransactionMessageString
     */
    public void setCustomTransactionMessageString(java.lang.String customTransactionMessageString) {
        this.customTransactionMessageString = customTransactionMessageString;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionMessageResult)) return false;
        TransactionMessageResult other = (TransactionMessageResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.transactionId == other.getTransactionId() &&
            ((this.fundTransactionLineNo==null && other.getFundTransactionLineNo()==null) || 
             (this.fundTransactionLineNo!=null &&
              this.fundTransactionLineNo.equals(other.getFundTransactionLineNo()))) &&
            this.transactionMessageId == other.getTransactionMessageId() &&
            ((this.transactionMessage==null && other.getTransactionMessage()==null) || 
             (this.transactionMessage!=null &&
              this.transactionMessage.equals(other.getTransactionMessage()))) &&
            ((this.selectedMessageType==null && other.getSelectedMessageType()==null) || 
             (this.selectedMessageType!=null &&
              this.selectedMessageType.equals(other.getSelectedMessageType()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.fundId == other.getFundId() &&
            ((this.fundShortName==null && other.getFundShortName()==null) || 
             (this.fundShortName!=null &&
              this.fundShortName.equals(other.getFundShortName()))) &&
            ((this.customTransactionMessageString==null && other.getCustomTransactionMessageString()==null) || 
             (this.customTransactionMessageString!=null &&
              this.customTransactionMessageString.equals(other.getCustomTransactionMessageString())));
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
        _hashCode += getTransactionId();
        if (getFundTransactionLineNo() != null) {
            _hashCode += getFundTransactionLineNo().hashCode();
        }
        _hashCode += getTransactionMessageId();
        if (getTransactionMessage() != null) {
            _hashCode += getTransactionMessage().hashCode();
        }
        if (getSelectedMessageType() != null) {
            _hashCode += getSelectedMessageType().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getFundId();
        if (getFundShortName() != null) {
            _hashCode += getFundShortName().hashCode();
        }
        if (getCustomTransactionMessageString() != null) {
            _hashCode += getCustomTransactionMessageString().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionMessageResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessageResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionLineNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionLineNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionMessageId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessageId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionMessage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedMessageType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SelectedMessageType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
        elemField.setFieldName("customTransactionMessageString");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomTransactionMessageString"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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

    @Override
    public String toString()
    {
        return "TransactionMessageResult{" +
           "transactionId=" + transactionId +
           ", fundTransactionLineNo=" + fundTransactionLineNo +
           ", transactionMessageId=" + transactionMessageId +
           ", transactionMessage='" + transactionMessage + '\'' +
           ", selectedMessageType=" + selectedMessageType +
           ", accountNumber='" + accountNumber + '\'' +
           ", fundId=" + fundId +
           ", fundShortName='" + fundShortName + '\'' +
           ", customTransactionMessageString='" + customTransactionMessageString + '\'' +
           '}';
    }
}
