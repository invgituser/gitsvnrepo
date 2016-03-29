/**
 * TransactionCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public class TransactionCollectionResult  implements java.io.Serializable {
    private int masterTransactionId;

    private int minConfirmationNumber;

    private int maxConfirmationNumber;

    private short noOfInformationLines;

    private short noOfWarnings;

    private short noOfOverrides;

    private short noOfErrors;

    private com.ws.gemini.wsdl.transaction.Status errorStatus;

    private com.ws.gemini.wsdl.transaction.TransactionResult[] transaction;

    private com.ws.gemini.wsdl.transaction.TransactionMessageResult[] transactionMessageCollection;

    private java.util.Calendar minConfirmNumberCrDateTime;

    public TransactionCollectionResult() {
    }

    public TransactionCollectionResult(
           int masterTransactionId,
           int minConfirmationNumber,
           int maxConfirmationNumber,
           short noOfInformationLines,
           short noOfWarnings,
           short noOfOverrides,
           short noOfErrors,
           com.ws.gemini.wsdl.transaction.Status errorStatus,
           com.ws.gemini.wsdl.transaction.TransactionResult[] transaction,
           com.ws.gemini.wsdl.transaction.TransactionMessageResult[] transactionMessageCollection,
           java.util.Calendar minConfirmNumberCrDateTime) {
           this.masterTransactionId = masterTransactionId;
           this.minConfirmationNumber = minConfirmationNumber;
           this.maxConfirmationNumber = maxConfirmationNumber;
           this.noOfInformationLines = noOfInformationLines;
           this.noOfWarnings = noOfWarnings;
           this.noOfOverrides = noOfOverrides;
           this.noOfErrors = noOfErrors;
           this.errorStatus = errorStatus;
           this.transaction = transaction;
           this.transactionMessageCollection = transactionMessageCollection;
           this.minConfirmNumberCrDateTime = minConfirmNumberCrDateTime;
    }


    /**
     * Gets the masterTransactionId value for this TransactionCollectionResult.
     * 
     * @return masterTransactionId
     */
    public int getMasterTransactionId() {
        return masterTransactionId;
    }


    /**
     * Sets the masterTransactionId value for this TransactionCollectionResult.
     * 
     * @param masterTransactionId
     */
    public void setMasterTransactionId(int masterTransactionId) {
        this.masterTransactionId = masterTransactionId;
    }


    /**
     * Gets the minConfirmationNumber value for this TransactionCollectionResult.
     * 
     * @return minConfirmationNumber
     */
    public int getMinConfirmationNumber() {
        return minConfirmationNumber;
    }


    /**
     * Sets the minConfirmationNumber value for this TransactionCollectionResult.
     * 
     * @param minConfirmationNumber
     */
    public void setMinConfirmationNumber(int minConfirmationNumber) {
        this.minConfirmationNumber = minConfirmationNumber;
    }


    /**
     * Gets the maxConfirmationNumber value for this TransactionCollectionResult.
     * 
     * @return maxConfirmationNumber
     */
    public int getMaxConfirmationNumber() {
        return maxConfirmationNumber;
    }


    /**
     * Sets the maxConfirmationNumber value for this TransactionCollectionResult.
     * 
     * @param maxConfirmationNumber
     */
    public void setMaxConfirmationNumber(int maxConfirmationNumber) {
        this.maxConfirmationNumber = maxConfirmationNumber;
    }


    /**
     * Gets the noOfInformationLines value for this TransactionCollectionResult.
     * 
     * @return noOfInformationLines
     */
    public short getNoOfInformationLines() {
        return noOfInformationLines;
    }


    /**
     * Sets the noOfInformationLines value for this TransactionCollectionResult.
     * 
     * @param noOfInformationLines
     */
    public void setNoOfInformationLines(short noOfInformationLines) {
        this.noOfInformationLines = noOfInformationLines;
    }


    /**
     * Gets the noOfWarnings value for this TransactionCollectionResult.
     * 
     * @return noOfWarnings
     */
    public short getNoOfWarnings() {
        return noOfWarnings;
    }


    /**
     * Sets the noOfWarnings value for this TransactionCollectionResult.
     * 
     * @param noOfWarnings
     */
    public void setNoOfWarnings(short noOfWarnings) {
        this.noOfWarnings = noOfWarnings;
    }


    /**
     * Gets the noOfOverrides value for this TransactionCollectionResult.
     * 
     * @return noOfOverrides
     */
    public short getNoOfOverrides() {
        return noOfOverrides;
    }


    /**
     * Sets the noOfOverrides value for this TransactionCollectionResult.
     * 
     * @param noOfOverrides
     */
    public void setNoOfOverrides(short noOfOverrides) {
        this.noOfOverrides = noOfOverrides;
    }


    /**
     * Gets the noOfErrors value for this TransactionCollectionResult.
     * 
     * @return noOfErrors
     */
    public short getNoOfErrors() {
        return noOfErrors;
    }


    /**
     * Sets the noOfErrors value for this TransactionCollectionResult.
     * 
     * @param noOfErrors
     */
    public void setNoOfErrors(short noOfErrors) {
        this.noOfErrors = noOfErrors;
    }


    /**
     * Gets the errorStatus value for this TransactionCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.transaction.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this TransactionCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.transaction.Status errorStatus) {
        this.errorStatus = errorStatus;
    }


    /**
     * Gets the transaction value for this TransactionCollectionResult.
     * 
     * @return transaction
     */
    public com.ws.gemini.wsdl.transaction.TransactionResult[] getTransaction() {
        return transaction;
    }


    /**
     * Sets the transaction value for this TransactionCollectionResult.
     * 
     * @param transaction
     */
    public void setTransaction(com.ws.gemini.wsdl.transaction.TransactionResult[] transaction) {
        this.transaction = transaction;
    }


    /**
     * Gets the transactionMessageCollection value for this TransactionCollectionResult.
     * 
     * @return transactionMessageCollection
     */
    public com.ws.gemini.wsdl.transaction.TransactionMessageResult[] getTransactionMessageCollection() {
        return transactionMessageCollection;
    }


    /**
     * Sets the transactionMessageCollection value for this TransactionCollectionResult.
     * 
     * @param transactionMessageCollection
     */
    public void setTransactionMessageCollection(com.ws.gemini.wsdl.transaction.TransactionMessageResult[] transactionMessageCollection) {
        this.transactionMessageCollection = transactionMessageCollection;
    }


    /**
     * Gets the minConfirmNumberCrDateTime value for this TransactionCollectionResult.
     * 
     * @return minConfirmNumberCrDateTime
     */
    public java.util.Calendar getMinConfirmNumberCrDateTime() {
        return minConfirmNumberCrDateTime;
    }


    /**
     * Sets the minConfirmNumberCrDateTime value for this TransactionCollectionResult.
     * 
     * @param minConfirmNumberCrDateTime
     */
    public void setMinConfirmNumberCrDateTime(java.util.Calendar minConfirmNumberCrDateTime) {
        this.minConfirmNumberCrDateTime = minConfirmNumberCrDateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionCollectionResult)) return false;
        TransactionCollectionResult other = (TransactionCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.masterTransactionId == other.getMasterTransactionId() &&
            this.minConfirmationNumber == other.getMinConfirmationNumber() &&
            this.maxConfirmationNumber == other.getMaxConfirmationNumber() &&
            this.noOfInformationLines == other.getNoOfInformationLines() &&
            this.noOfWarnings == other.getNoOfWarnings() &&
            this.noOfOverrides == other.getNoOfOverrides() &&
            this.noOfErrors == other.getNoOfErrors() &&
            ((this.errorStatus==null && other.getErrorStatus()==null) || 
             (this.errorStatus!=null &&
              this.errorStatus.equals(other.getErrorStatus()))) &&
            ((this.transaction==null && other.getTransaction()==null) || 
             (this.transaction!=null &&
              java.util.Arrays.equals(this.transaction, other.getTransaction()))) &&
            ((this.transactionMessageCollection==null && other.getTransactionMessageCollection()==null) || 
             (this.transactionMessageCollection!=null &&
              java.util.Arrays.equals(this.transactionMessageCollection, other.getTransactionMessageCollection()))) &&
            ((this.minConfirmNumberCrDateTime==null && other.getMinConfirmNumberCrDateTime()==null) || 
             (this.minConfirmNumberCrDateTime!=null &&
              this.minConfirmNumberCrDateTime.equals(other.getMinConfirmNumberCrDateTime())));
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
        _hashCode += getMasterTransactionId();
        _hashCode += getMinConfirmationNumber();
        _hashCode += getMaxConfirmationNumber();
        _hashCode += getNoOfInformationLines();
        _hashCode += getNoOfWarnings();
        _hashCode += getNoOfOverrides();
        _hashCode += getNoOfErrors();
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        if (getTransaction() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransaction());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransaction(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTransactionMessageCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransactionMessageCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransactionMessageCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMinConfirmNumberCrDateTime() != null) {
            _hashCode += getMinConfirmNumberCrDateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterTransactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterTransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minConfirmationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MinConfirmationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maxConfirmationNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MaxConfirmationNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfInformationLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfInformationLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfWarnings");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfWarnings"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfOverrides");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfOverrides"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfErrors");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfErrors"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Status"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Transaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionMessageCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessageCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessageResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionMessageResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("minConfirmNumberCrDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MinConfirmNumberCrDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
