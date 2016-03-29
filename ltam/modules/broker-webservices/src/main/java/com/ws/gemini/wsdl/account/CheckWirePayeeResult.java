/**
 * CheckWirePayeeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class CheckWirePayeeResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    private java.lang.String nameLines;

    private java.lang.String addressLines;

    private java.lang.String postalZip;

    private short countryCode;

    private org.apache.axis.types.UnsignedByte mode;

    public CheckWirePayeeResult() {
    }

    public CheckWirePayeeResult(
           java.lang.String accountNumber,
           org.apache.axis.types.UnsignedByte accountPayeeId,
           java.lang.String nameLines,
           java.lang.String addressLines,
           java.lang.String postalZip,
           short countryCode,
           org.apache.axis.types.UnsignedByte mode) {
           this.accountNumber = accountNumber;
           this.accountPayeeId = accountPayeeId;
           this.nameLines = nameLines;
           this.addressLines = addressLines;
           this.postalZip = postalZip;
           this.countryCode = countryCode;
           this.mode = mode;
    }


    /**
     * Gets the accountNumber value for this CheckWirePayeeResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this CheckWirePayeeResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the accountPayeeId value for this CheckWirePayeeResult.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this CheckWirePayeeResult.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the nameLines value for this CheckWirePayeeResult.
     * 
     * @return nameLines
     */
    public java.lang.String getNameLines() {
        return nameLines;
    }


    /**
     * Sets the nameLines value for this CheckWirePayeeResult.
     * 
     * @param nameLines
     */
    public void setNameLines(java.lang.String nameLines) {
        this.nameLines = nameLines;
    }


    /**
     * Gets the addressLines value for this CheckWirePayeeResult.
     * 
     * @return addressLines
     */
    public java.lang.String getAddressLines() {
        return addressLines;
    }


    /**
     * Sets the addressLines value for this CheckWirePayeeResult.
     * 
     * @param addressLines
     */
    public void setAddressLines(java.lang.String addressLines) {
        this.addressLines = addressLines;
    }


    /**
     * Gets the postalZip value for this CheckWirePayeeResult.
     * 
     * @return postalZip
     */
    public java.lang.String getPostalZip() {
        return postalZip;
    }


    /**
     * Sets the postalZip value for this CheckWirePayeeResult.
     * 
     * @param postalZip
     */
    public void setPostalZip(java.lang.String postalZip) {
        this.postalZip = postalZip;
    }


    /**
     * Gets the countryCode value for this CheckWirePayeeResult.
     * 
     * @return countryCode
     */
    public short getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this CheckWirePayeeResult.
     * 
     * @param countryCode
     */
    public void setCountryCode(short countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the mode value for this CheckWirePayeeResult.
     * 
     * @return mode
     */
    public org.apache.axis.types.UnsignedByte getMode() {
        return mode;
    }


    /**
     * Sets the mode value for this CheckWirePayeeResult.
     * 
     * @param mode
     */
    public void setMode(org.apache.axis.types.UnsignedByte mode) {
        this.mode = mode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckWirePayeeResult)) return false;
        CheckWirePayeeResult other = (CheckWirePayeeResult) obj;
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
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId()))) &&
            ((this.nameLines==null && other.getNameLines()==null) || 
             (this.nameLines!=null &&
              this.nameLines.equals(other.getNameLines()))) &&
            ((this.addressLines==null && other.getAddressLines()==null) || 
             (this.addressLines!=null &&
              this.addressLines.equals(other.getAddressLines()))) &&
            ((this.postalZip==null && other.getPostalZip()==null) || 
             (this.postalZip!=null &&
              this.postalZip.equals(other.getPostalZip()))) &&
            this.countryCode == other.getCountryCode() &&
            ((this.mode==null && other.getMode()==null) || 
             (this.mode!=null &&
              this.mode.equals(other.getMode())));
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
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        if (getNameLines() != null) {
            _hashCode += getNameLines().hashCode();
        }
        if (getAddressLines() != null) {
            _hashCode += getAddressLines().hashCode();
        }
        if (getPostalZip() != null) {
            _hashCode += getPostalZip().hashCode();
        }
        _hashCode += getCountryCode();
        if (getMode() != null) {
            _hashCode += getMode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckWirePayeeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CheckWirePayeeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
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
        elemField.setFieldName("nameLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AddressLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalZip");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PostalZip"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("countryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CountryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Mode"));
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
