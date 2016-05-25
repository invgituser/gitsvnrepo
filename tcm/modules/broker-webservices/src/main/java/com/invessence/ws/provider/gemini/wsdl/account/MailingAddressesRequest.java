/**
 * MailingAddressesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class MailingAddressesRequest  implements java.io.Serializable {
    private int mailingAddressId;

    private java.lang.String nameLines;

    private java.lang.String addressLines;

    private java.lang.String postalZip;

    private short countryCode;

    private java.lang.String voicePhone;

    private java.lang.String altPhone;

    private java.lang.String faxPhone;

    private java.lang.String emailAddress;

    private org.apache.axis.types.UnsignedByte mailingAddressType;

    private java.lang.String entityIdentifier;

    public MailingAddressesRequest() {
    }

    public MailingAddressesRequest(
           int mailingAddressId,
           java.lang.String nameLines,
           java.lang.String addressLines,
           java.lang.String postalZip,
           short countryCode,
           java.lang.String voicePhone,
           java.lang.String altPhone,
           java.lang.String faxPhone,
           java.lang.String emailAddress,
           org.apache.axis.types.UnsignedByte mailingAddressType,
           java.lang.String entityIdentifier) {
           this.mailingAddressId = mailingAddressId;
           this.nameLines = nameLines;
           this.addressLines = addressLines;
           this.postalZip = postalZip;
           this.countryCode = countryCode;
           this.voicePhone = voicePhone;
           this.altPhone = altPhone;
           this.faxPhone = faxPhone;
           this.emailAddress = emailAddress;
           this.mailingAddressType = mailingAddressType;
           this.entityIdentifier = entityIdentifier;
    }


    /**
     * Gets the mailingAddressId value for this MailingAddressesRequest.
     * 
     * @return mailingAddressId
     */
    public int getMailingAddressId() {
        return mailingAddressId;
    }


    /**
     * Sets the mailingAddressId value for this MailingAddressesRequest.
     * 
     * @param mailingAddressId
     */
    public void setMailingAddressId(int mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }


    /**
     * Gets the nameLines value for this MailingAddressesRequest.
     * 
     * @return nameLines
     */
    public java.lang.String getNameLines() {
        return nameLines;
    }


    /**
     * Sets the nameLines value for this MailingAddressesRequest.
     * 
     * @param nameLines
     */
    public void setNameLines(java.lang.String nameLines) {
        this.nameLines = nameLines;
    }


    /**
     * Gets the addressLines value for this MailingAddressesRequest.
     * 
     * @return addressLines
     */
    public java.lang.String getAddressLines() {
        return addressLines;
    }


    /**
     * Sets the addressLines value for this MailingAddressesRequest.
     * 
     * @param addressLines
     */
    public void setAddressLines(java.lang.String addressLines) {
        this.addressLines = addressLines;
    }


    /**
     * Gets the postalZip value for this MailingAddressesRequest.
     * 
     * @return postalZip
     */
    public java.lang.String getPostalZip() {
        return postalZip;
    }


    /**
     * Sets the postalZip value for this MailingAddressesRequest.
     * 
     * @param postalZip
     */
    public void setPostalZip(java.lang.String postalZip) {
        this.postalZip = postalZip;
    }


    /**
     * Gets the countryCode value for this MailingAddressesRequest.
     * 
     * @return countryCode
     */
    public short getCountryCode() {
        return countryCode;
    }


    /**
     * Sets the countryCode value for this MailingAddressesRequest.
     * 
     * @param countryCode
     */
    public void setCountryCode(short countryCode) {
        this.countryCode = countryCode;
    }


    /**
     * Gets the voicePhone value for this MailingAddressesRequest.
     * 
     * @return voicePhone
     */
    public java.lang.String getVoicePhone() {
        return voicePhone;
    }


    /**
     * Sets the voicePhone value for this MailingAddressesRequest.
     * 
     * @param voicePhone
     */
    public void setVoicePhone(java.lang.String voicePhone) {
        this.voicePhone = voicePhone;
    }


    /**
     * Gets the altPhone value for this MailingAddressesRequest.
     * 
     * @return altPhone
     */
    public java.lang.String getAltPhone() {
        return altPhone;
    }


    /**
     * Sets the altPhone value for this MailingAddressesRequest.
     * 
     * @param altPhone
     */
    public void setAltPhone(java.lang.String altPhone) {
        this.altPhone = altPhone;
    }


    /**
     * Gets the faxPhone value for this MailingAddressesRequest.
     * 
     * @return faxPhone
     */
    public java.lang.String getFaxPhone() {
        return faxPhone;
    }


    /**
     * Sets the faxPhone value for this MailingAddressesRequest.
     * 
     * @param faxPhone
     */
    public void setFaxPhone(java.lang.String faxPhone) {
        this.faxPhone = faxPhone;
    }


    /**
     * Gets the emailAddress value for this MailingAddressesRequest.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this MailingAddressesRequest.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the mailingAddressType value for this MailingAddressesRequest.
     * 
     * @return mailingAddressType
     */
    public org.apache.axis.types.UnsignedByte getMailingAddressType() {
        return mailingAddressType;
    }


    /**
     * Sets the mailingAddressType value for this MailingAddressesRequest.
     * 
     * @param mailingAddressType
     */
    public void setMailingAddressType(org.apache.axis.types.UnsignedByte mailingAddressType) {
        this.mailingAddressType = mailingAddressType;
    }


    /**
     * Gets the entityIdentifier value for this MailingAddressesRequest.
     * 
     * @return entityIdentifier
     */
    public java.lang.String getEntityIdentifier() {
        return entityIdentifier;
    }


    /**
     * Sets the entityIdentifier value for this MailingAddressesRequest.
     * 
     * @param entityIdentifier
     */
    public void setEntityIdentifier(java.lang.String entityIdentifier) {
        this.entityIdentifier = entityIdentifier;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MailingAddressesRequest)) return false;
        MailingAddressesRequest other = (MailingAddressesRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.mailingAddressId == other.getMailingAddressId() &&
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
            ((this.voicePhone==null && other.getVoicePhone()==null) || 
             (this.voicePhone!=null &&
              this.voicePhone.equals(other.getVoicePhone()))) &&
            ((this.altPhone==null && other.getAltPhone()==null) || 
             (this.altPhone!=null &&
              this.altPhone.equals(other.getAltPhone()))) &&
            ((this.faxPhone==null && other.getFaxPhone()==null) || 
             (this.faxPhone!=null &&
              this.faxPhone.equals(other.getFaxPhone()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.mailingAddressType==null && other.getMailingAddressType()==null) || 
             (this.mailingAddressType!=null &&
              this.mailingAddressType.equals(other.getMailingAddressType()))) &&
            ((this.entityIdentifier==null && other.getEntityIdentifier()==null) || 
             (this.entityIdentifier!=null &&
              this.entityIdentifier.equals(other.getEntityIdentifier())));
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
        _hashCode += getMailingAddressId();
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
        if (getVoicePhone() != null) {
            _hashCode += getVoicePhone().hashCode();
        }
        if (getAltPhone() != null) {
            _hashCode += getAltPhone().hashCode();
        }
        if (getFaxPhone() != null) {
            _hashCode += getFaxPhone().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getMailingAddressType() != null) {
            _hashCode += getMailingAddressType().hashCode();
        }
        if (getEntityIdentifier() != null) {
            _hashCode += getEntityIdentifier().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MailingAddressesRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddressId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("voicePhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "VoicePhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("altPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AltPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FaxPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddressType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entityIdentifier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EntityIdentifier"));
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
        return "MailingAddressesRequest{" +
           "entityIdentifier='" + entityIdentifier + '\'' +
           ", mailingAddressId=" + mailingAddressId +
           ", nameLines='" + nameLines + '\'' +
           ", addressLines='" + addressLines + '\'' +
           ", postalZip='" + postalZip + '\'' +
           ", countryCode=" + countryCode +
           ", voicePhone='" + voicePhone + '\'' +
           ", altPhone='" + altPhone + '\'' +
           ", faxPhone='" + faxPhone + '\'' +
           ", emailAddress='" + emailAddress + '\'' +
           ", mailingAddressType=" + mailingAddressType +
           '}';
    }
}
