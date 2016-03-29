/**
 * SuccessorBeneficiaryResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class SuccessorBeneficiaryResult  implements java.io.Serializable {
    private java.lang.String userId;

    private java.lang.String accountNumber;

    private int mailingAddressId;

    private java.lang.String SSNOrTIN;

    private org.apache.axis.types.UnsignedByte beneficiaryType;

    private org.apache.axis.types.UnsignedByte beneficiaryStatus;

    private org.apache.axis.types.UnsignedByte primaryBeneficiaryIndicator;

    private boolean primaryBeneIndicator;

    private java.math.BigDecimal percentAssigned;

    private java.util.Calendar dateOfBirth;

    private java.util.Calendar dateAssigned;

    private java.util.Calendar dateDropped;

    private com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddresses;

    public SuccessorBeneficiaryResult() {
    }

    public SuccessorBeneficiaryResult(
           java.lang.String userId,
           java.lang.String accountNumber,
           int mailingAddressId,
           java.lang.String SSNOrTIN,
           org.apache.axis.types.UnsignedByte beneficiaryType,
           org.apache.axis.types.UnsignedByte beneficiaryStatus,
           org.apache.axis.types.UnsignedByte primaryBeneficiaryIndicator,
           boolean primaryBeneIndicator,
           java.math.BigDecimal percentAssigned,
           java.util.Calendar dateOfBirth,
           java.util.Calendar dateAssigned,
           java.util.Calendar dateDropped,
           com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddresses) {
           this.userId = userId;
           this.accountNumber = accountNumber;
           this.mailingAddressId = mailingAddressId;
           this.SSNOrTIN = SSNOrTIN;
           this.beneficiaryType = beneficiaryType;
           this.beneficiaryStatus = beneficiaryStatus;
           this.primaryBeneficiaryIndicator = primaryBeneficiaryIndicator;
           this.primaryBeneIndicator = primaryBeneIndicator;
           this.percentAssigned = percentAssigned;
           this.dateOfBirth = dateOfBirth;
           this.dateAssigned = dateAssigned;
           this.dateDropped = dateDropped;
           this.mailingAddresses = mailingAddresses;
    }


    /**
     * Gets the userId value for this SuccessorBeneficiaryResult.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this SuccessorBeneficiaryResult.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the accountNumber value for this SuccessorBeneficiaryResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this SuccessorBeneficiaryResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the mailingAddressId value for this SuccessorBeneficiaryResult.
     * 
     * @return mailingAddressId
     */
    public int getMailingAddressId() {
        return mailingAddressId;
    }


    /**
     * Sets the mailingAddressId value for this SuccessorBeneficiaryResult.
     * 
     * @param mailingAddressId
     */
    public void setMailingAddressId(int mailingAddressId) {
        this.mailingAddressId = mailingAddressId;
    }


    /**
     * Gets the SSNOrTIN value for this SuccessorBeneficiaryResult.
     * 
     * @return SSNOrTIN
     */
    public java.lang.String getSSNOrTIN() {
        return SSNOrTIN;
    }


    /**
     * Sets the SSNOrTIN value for this SuccessorBeneficiaryResult.
     * 
     * @param SSNOrTIN
     */
    public void setSSNOrTIN(java.lang.String SSNOrTIN) {
        this.SSNOrTIN = SSNOrTIN;
    }


    /**
     * Gets the beneficiaryType value for this SuccessorBeneficiaryResult.
     * 
     * @return beneficiaryType
     */
    public org.apache.axis.types.UnsignedByte getBeneficiaryType() {
        return beneficiaryType;
    }


    /**
     * Sets the beneficiaryType value for this SuccessorBeneficiaryResult.
     * 
     * @param beneficiaryType
     */
    public void setBeneficiaryType(org.apache.axis.types.UnsignedByte beneficiaryType) {
        this.beneficiaryType = beneficiaryType;
    }


    /**
     * Gets the beneficiaryStatus value for this SuccessorBeneficiaryResult.
     * 
     * @return beneficiaryStatus
     */
    public org.apache.axis.types.UnsignedByte getBeneficiaryStatus() {
        return beneficiaryStatus;
    }


    /**
     * Sets the beneficiaryStatus value for this SuccessorBeneficiaryResult.
     * 
     * @param beneficiaryStatus
     */
    public void setBeneficiaryStatus(org.apache.axis.types.UnsignedByte beneficiaryStatus) {
        this.beneficiaryStatus = beneficiaryStatus;
    }


    /**
     * Gets the primaryBeneficiaryIndicator value for this SuccessorBeneficiaryResult.
     * 
     * @return primaryBeneficiaryIndicator
     */
    public org.apache.axis.types.UnsignedByte getPrimaryBeneficiaryIndicator() {
        return primaryBeneficiaryIndicator;
    }


    /**
     * Sets the primaryBeneficiaryIndicator value for this SuccessorBeneficiaryResult.
     * 
     * @param primaryBeneficiaryIndicator
     */
    public void setPrimaryBeneficiaryIndicator(org.apache.axis.types.UnsignedByte primaryBeneficiaryIndicator) {
        this.primaryBeneficiaryIndicator = primaryBeneficiaryIndicator;
    }


    /**
     * Gets the primaryBeneIndicator value for this SuccessorBeneficiaryResult.
     * 
     * @return primaryBeneIndicator
     */
    public boolean isPrimaryBeneIndicator() {
        return primaryBeneIndicator;
    }


    /**
     * Sets the primaryBeneIndicator value for this SuccessorBeneficiaryResult.
     * 
     * @param primaryBeneIndicator
     */
    public void setPrimaryBeneIndicator(boolean primaryBeneIndicator) {
        this.primaryBeneIndicator = primaryBeneIndicator;
    }


    /**
     * Gets the percentAssigned value for this SuccessorBeneficiaryResult.
     * 
     * @return percentAssigned
     */
    public java.math.BigDecimal getPercentAssigned() {
        return percentAssigned;
    }


    /**
     * Sets the percentAssigned value for this SuccessorBeneficiaryResult.
     * 
     * @param percentAssigned
     */
    public void setPercentAssigned(java.math.BigDecimal percentAssigned) {
        this.percentAssigned = percentAssigned;
    }


    /**
     * Gets the dateOfBirth value for this SuccessorBeneficiaryResult.
     * 
     * @return dateOfBirth
     */
    public java.util.Calendar getDateOfBirth() {
        return dateOfBirth;
    }


    /**
     * Sets the dateOfBirth value for this SuccessorBeneficiaryResult.
     * 
     * @param dateOfBirth
     */
    public void setDateOfBirth(java.util.Calendar dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    /**
     * Gets the dateAssigned value for this SuccessorBeneficiaryResult.
     * 
     * @return dateAssigned
     */
    public java.util.Calendar getDateAssigned() {
        return dateAssigned;
    }


    /**
     * Sets the dateAssigned value for this SuccessorBeneficiaryResult.
     * 
     * @param dateAssigned
     */
    public void setDateAssigned(java.util.Calendar dateAssigned) {
        this.dateAssigned = dateAssigned;
    }


    /**
     * Gets the dateDropped value for this SuccessorBeneficiaryResult.
     * 
     * @return dateDropped
     */
    public java.util.Calendar getDateDropped() {
        return dateDropped;
    }


    /**
     * Sets the dateDropped value for this SuccessorBeneficiaryResult.
     * 
     * @param dateDropped
     */
    public void setDateDropped(java.util.Calendar dateDropped) {
        this.dateDropped = dateDropped;
    }


    /**
     * Gets the mailingAddresses value for this SuccessorBeneficiaryResult.
     * 
     * @return mailingAddresses
     */
    public com.ws.gemini.wsdl.account.MailingAddressesResult getMailingAddresses() {
        return mailingAddresses;
    }


    /**
     * Sets the mailingAddresses value for this SuccessorBeneficiaryResult.
     * 
     * @param mailingAddresses
     */
    public void setMailingAddresses(com.ws.gemini.wsdl.account.MailingAddressesResult mailingAddresses) {
        this.mailingAddresses = mailingAddresses;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SuccessorBeneficiaryResult)) return false;
        SuccessorBeneficiaryResult other = (SuccessorBeneficiaryResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.userId==null && other.getUserId()==null) || 
             (this.userId!=null &&
              this.userId.equals(other.getUserId()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            this.mailingAddressId == other.getMailingAddressId() &&
            ((this.SSNOrTIN==null && other.getSSNOrTIN()==null) || 
             (this.SSNOrTIN!=null &&
              this.SSNOrTIN.equals(other.getSSNOrTIN()))) &&
            ((this.beneficiaryType==null && other.getBeneficiaryType()==null) || 
             (this.beneficiaryType!=null &&
              this.beneficiaryType.equals(other.getBeneficiaryType()))) &&
            ((this.beneficiaryStatus==null && other.getBeneficiaryStatus()==null) || 
             (this.beneficiaryStatus!=null &&
              this.beneficiaryStatus.equals(other.getBeneficiaryStatus()))) &&
            ((this.primaryBeneficiaryIndicator==null && other.getPrimaryBeneficiaryIndicator()==null) || 
             (this.primaryBeneficiaryIndicator!=null &&
              this.primaryBeneficiaryIndicator.equals(other.getPrimaryBeneficiaryIndicator()))) &&
            this.primaryBeneIndicator == other.isPrimaryBeneIndicator() &&
            ((this.percentAssigned==null && other.getPercentAssigned()==null) || 
             (this.percentAssigned!=null &&
              this.percentAssigned.equals(other.getPercentAssigned()))) &&
            ((this.dateOfBirth==null && other.getDateOfBirth()==null) || 
             (this.dateOfBirth!=null &&
              this.dateOfBirth.equals(other.getDateOfBirth()))) &&
            ((this.dateAssigned==null && other.getDateAssigned()==null) || 
             (this.dateAssigned!=null &&
              this.dateAssigned.equals(other.getDateAssigned()))) &&
            ((this.dateDropped==null && other.getDateDropped()==null) || 
             (this.dateDropped!=null &&
              this.dateDropped.equals(other.getDateDropped()))) &&
            ((this.mailingAddresses==null && other.getMailingAddresses()==null) || 
             (this.mailingAddresses!=null &&
              this.mailingAddresses.equals(other.getMailingAddresses())));
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
        if (getUserId() != null) {
            _hashCode += getUserId().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        _hashCode += getMailingAddressId();
        if (getSSNOrTIN() != null) {
            _hashCode += getSSNOrTIN().hashCode();
        }
        if (getBeneficiaryType() != null) {
            _hashCode += getBeneficiaryType().hashCode();
        }
        if (getBeneficiaryStatus() != null) {
            _hashCode += getBeneficiaryStatus().hashCode();
        }
        if (getPrimaryBeneficiaryIndicator() != null) {
            _hashCode += getPrimaryBeneficiaryIndicator().hashCode();
        }
        _hashCode += (isPrimaryBeneIndicator() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPercentAssigned() != null) {
            _hashCode += getPercentAssigned().hashCode();
        }
        if (getDateOfBirth() != null) {
            _hashCode += getDateOfBirth().hashCode();
        }
        if (getDateAssigned() != null) {
            _hashCode += getDateAssigned().hashCode();
        }
        if (getDateDropped() != null) {
            _hashCode += getDateDropped().hashCode();
        }
        if (getMailingAddresses() != null) {
            _hashCode += getMailingAddresses().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SuccessorBeneficiaryResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SuccessorBeneficiaryResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("mailingAddressId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SSNOrTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SSNOrTIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryBeneficiaryIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrimaryBeneficiaryIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryBeneIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrimaryBeneIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentAssigned");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PercentAssigned"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateOfBirth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateOfBirth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateAssigned");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateAssigned"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateDropped");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateDropped"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailingAddresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MailingAddressesResult"));
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

}
