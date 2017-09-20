/**
 * Institution.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class Institution  implements java.io.Serializable {
    private short institutionId;

    private java.lang.String institutionName;

    private org.apache.axis.types.UnsignedByte institutionStateId;

    private java.lang.String institutionStateShortName;

    private java.lang.String institutionStateFullName;

    private java.lang.String institutionURL;

    private short participationStartYearId;

    private short participationStartYear;

    private short participationEndYearId;

    private short participationEndYear;

    private java.math.BigDecimal defaultDiscountRate;

    private java.math.BigDecimal defaultAlmaMaterDiscountRate;

    private short religiousAffiliationsId;

    private java.lang.String religiousAffiliationsDescription;

    private short typeOfSchoolId;

    private java.lang.String typeOfSchoolDescription;

    private short sizeOfSchoolId;

    private java.lang.String sizeOfSchoolDescription;

    public Institution() {
    }

    public Institution(
           short institutionId,
           java.lang.String institutionName,
           org.apache.axis.types.UnsignedByte institutionStateId,
           java.lang.String institutionStateShortName,
           java.lang.String institutionStateFullName,
           java.lang.String institutionURL,
           short participationStartYearId,
           short participationStartYear,
           short participationEndYearId,
           short participationEndYear,
           java.math.BigDecimal defaultDiscountRate,
           java.math.BigDecimal defaultAlmaMaterDiscountRate,
           short religiousAffiliationsId,
           java.lang.String religiousAffiliationsDescription,
           short typeOfSchoolId,
           java.lang.String typeOfSchoolDescription,
           short sizeOfSchoolId,
           java.lang.String sizeOfSchoolDescription) {
           this.institutionId = institutionId;
           this.institutionName = institutionName;
           this.institutionStateId = institutionStateId;
           this.institutionStateShortName = institutionStateShortName;
           this.institutionStateFullName = institutionStateFullName;
           this.institutionURL = institutionURL;
           this.participationStartYearId = participationStartYearId;
           this.participationStartYear = participationStartYear;
           this.participationEndYearId = participationEndYearId;
           this.participationEndYear = participationEndYear;
           this.defaultDiscountRate = defaultDiscountRate;
           this.defaultAlmaMaterDiscountRate = defaultAlmaMaterDiscountRate;
           this.religiousAffiliationsId = religiousAffiliationsId;
           this.religiousAffiliationsDescription = religiousAffiliationsDescription;
           this.typeOfSchoolId = typeOfSchoolId;
           this.typeOfSchoolDescription = typeOfSchoolDescription;
           this.sizeOfSchoolId = sizeOfSchoolId;
           this.sizeOfSchoolDescription = sizeOfSchoolDescription;
    }


    /**
     * Gets the institutionId value for this Institution.
     * 
     * @return institutionId
     */
    public short getInstitutionId() {
        return institutionId;
    }


    /**
     * Sets the institutionId value for this Institution.
     * 
     * @param institutionId
     */
    public void setInstitutionId(short institutionId) {
        this.institutionId = institutionId;
    }


    /**
     * Gets the institutionName value for this Institution.
     * 
     * @return institutionName
     */
    public java.lang.String getInstitutionName() {
        return institutionName;
    }


    /**
     * Sets the institutionName value for this Institution.
     * 
     * @param institutionName
     */
    public void setInstitutionName(java.lang.String institutionName) {
        this.institutionName = institutionName;
    }


    /**
     * Gets the institutionStateId value for this Institution.
     * 
     * @return institutionStateId
     */
    public org.apache.axis.types.UnsignedByte getInstitutionStateId() {
        return institutionStateId;
    }


    /**
     * Sets the institutionStateId value for this Institution.
     * 
     * @param institutionStateId
     */
    public void setInstitutionStateId(org.apache.axis.types.UnsignedByte institutionStateId) {
        this.institutionStateId = institutionStateId;
    }


    /**
     * Gets the institutionStateShortName value for this Institution.
     * 
     * @return institutionStateShortName
     */
    public java.lang.String getInstitutionStateShortName() {
        return institutionStateShortName;
    }


    /**
     * Sets the institutionStateShortName value for this Institution.
     * 
     * @param institutionStateShortName
     */
    public void setInstitutionStateShortName(java.lang.String institutionStateShortName) {
        this.institutionStateShortName = institutionStateShortName;
    }


    /**
     * Gets the institutionStateFullName value for this Institution.
     * 
     * @return institutionStateFullName
     */
    public java.lang.String getInstitutionStateFullName() {
        return institutionStateFullName;
    }


    /**
     * Sets the institutionStateFullName value for this Institution.
     * 
     * @param institutionStateFullName
     */
    public void setInstitutionStateFullName(java.lang.String institutionStateFullName) {
        this.institutionStateFullName = institutionStateFullName;
    }


    /**
     * Gets the institutionURL value for this Institution.
     * 
     * @return institutionURL
     */
    public java.lang.String getInstitutionURL() {
        return institutionURL;
    }


    /**
     * Sets the institutionURL value for this Institution.
     * 
     * @param institutionURL
     */
    public void setInstitutionURL(java.lang.String institutionURL) {
        this.institutionURL = institutionURL;
    }


    /**
     * Gets the participationStartYearId value for this Institution.
     * 
     * @return participationStartYearId
     */
    public short getParticipationStartYearId() {
        return participationStartYearId;
    }


    /**
     * Sets the participationStartYearId value for this Institution.
     * 
     * @param participationStartYearId
     */
    public void setParticipationStartYearId(short participationStartYearId) {
        this.participationStartYearId = participationStartYearId;
    }


    /**
     * Gets the participationStartYear value for this Institution.
     * 
     * @return participationStartYear
     */
    public short getParticipationStartYear() {
        return participationStartYear;
    }


    /**
     * Sets the participationStartYear value for this Institution.
     * 
     * @param participationStartYear
     */
    public void setParticipationStartYear(short participationStartYear) {
        this.participationStartYear = participationStartYear;
    }


    /**
     * Gets the participationEndYearId value for this Institution.
     * 
     * @return participationEndYearId
     */
    public short getParticipationEndYearId() {
        return participationEndYearId;
    }


    /**
     * Sets the participationEndYearId value for this Institution.
     * 
     * @param participationEndYearId
     */
    public void setParticipationEndYearId(short participationEndYearId) {
        this.participationEndYearId = participationEndYearId;
    }


    /**
     * Gets the participationEndYear value for this Institution.
     * 
     * @return participationEndYear
     */
    public short getParticipationEndYear() {
        return participationEndYear;
    }


    /**
     * Sets the participationEndYear value for this Institution.
     * 
     * @param participationEndYear
     */
    public void setParticipationEndYear(short participationEndYear) {
        this.participationEndYear = participationEndYear;
    }


    /**
     * Gets the defaultDiscountRate value for this Institution.
     * 
     * @return defaultDiscountRate
     */
    public java.math.BigDecimal getDefaultDiscountRate() {
        return defaultDiscountRate;
    }


    /**
     * Sets the defaultDiscountRate value for this Institution.
     * 
     * @param defaultDiscountRate
     */
    public void setDefaultDiscountRate(java.math.BigDecimal defaultDiscountRate) {
        this.defaultDiscountRate = defaultDiscountRate;
    }


    /**
     * Gets the defaultAlmaMaterDiscountRate value for this Institution.
     * 
     * @return defaultAlmaMaterDiscountRate
     */
    public java.math.BigDecimal getDefaultAlmaMaterDiscountRate() {
        return defaultAlmaMaterDiscountRate;
    }


    /**
     * Sets the defaultAlmaMaterDiscountRate value for this Institution.
     * 
     * @param defaultAlmaMaterDiscountRate
     */
    public void setDefaultAlmaMaterDiscountRate(java.math.BigDecimal defaultAlmaMaterDiscountRate) {
        this.defaultAlmaMaterDiscountRate = defaultAlmaMaterDiscountRate;
    }


    /**
     * Gets the religiousAffiliationsId value for this Institution.
     * 
     * @return religiousAffiliationsId
     */
    public short getReligiousAffiliationsId() {
        return religiousAffiliationsId;
    }


    /**
     * Sets the religiousAffiliationsId value for this Institution.
     * 
     * @param religiousAffiliationsId
     */
    public void setReligiousAffiliationsId(short religiousAffiliationsId) {
        this.religiousAffiliationsId = religiousAffiliationsId;
    }


    /**
     * Gets the religiousAffiliationsDescription value for this Institution.
     * 
     * @return religiousAffiliationsDescription
     */
    public java.lang.String getReligiousAffiliationsDescription() {
        return religiousAffiliationsDescription;
    }


    /**
     * Sets the religiousAffiliationsDescription value for this Institution.
     * 
     * @param religiousAffiliationsDescription
     */
    public void setReligiousAffiliationsDescription(java.lang.String religiousAffiliationsDescription) {
        this.religiousAffiliationsDescription = religiousAffiliationsDescription;
    }


    /**
     * Gets the typeOfSchoolId value for this Institution.
     * 
     * @return typeOfSchoolId
     */
    public short getTypeOfSchoolId() {
        return typeOfSchoolId;
    }


    /**
     * Sets the typeOfSchoolId value for this Institution.
     * 
     * @param typeOfSchoolId
     */
    public void setTypeOfSchoolId(short typeOfSchoolId) {
        this.typeOfSchoolId = typeOfSchoolId;
    }


    /**
     * Gets the typeOfSchoolDescription value for this Institution.
     * 
     * @return typeOfSchoolDescription
     */
    public java.lang.String getTypeOfSchoolDescription() {
        return typeOfSchoolDescription;
    }


    /**
     * Sets the typeOfSchoolDescription value for this Institution.
     * 
     * @param typeOfSchoolDescription
     */
    public void setTypeOfSchoolDescription(java.lang.String typeOfSchoolDescription) {
        this.typeOfSchoolDescription = typeOfSchoolDescription;
    }


    /**
     * Gets the sizeOfSchoolId value for this Institution.
     * 
     * @return sizeOfSchoolId
     */
    public short getSizeOfSchoolId() {
        return sizeOfSchoolId;
    }


    /**
     * Sets the sizeOfSchoolId value for this Institution.
     * 
     * @param sizeOfSchoolId
     */
    public void setSizeOfSchoolId(short sizeOfSchoolId) {
        this.sizeOfSchoolId = sizeOfSchoolId;
    }


    /**
     * Gets the sizeOfSchoolDescription value for this Institution.
     * 
     * @return sizeOfSchoolDescription
     */
    public java.lang.String getSizeOfSchoolDescription() {
        return sizeOfSchoolDescription;
    }


    /**
     * Sets the sizeOfSchoolDescription value for this Institution.
     * 
     * @param sizeOfSchoolDescription
     */
    public void setSizeOfSchoolDescription(java.lang.String sizeOfSchoolDescription) {
        this.sizeOfSchoolDescription = sizeOfSchoolDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Institution)) return false;
        Institution other = (Institution) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.institutionId == other.getInstitutionId() &&
            ((this.institutionName==null && other.getInstitutionName()==null) || 
             (this.institutionName!=null &&
              this.institutionName.equals(other.getInstitutionName()))) &&
            ((this.institutionStateId==null && other.getInstitutionStateId()==null) || 
             (this.institutionStateId!=null &&
              this.institutionStateId.equals(other.getInstitutionStateId()))) &&
            ((this.institutionStateShortName==null && other.getInstitutionStateShortName()==null) || 
             (this.institutionStateShortName!=null &&
              this.institutionStateShortName.equals(other.getInstitutionStateShortName()))) &&
            ((this.institutionStateFullName==null && other.getInstitutionStateFullName()==null) || 
             (this.institutionStateFullName!=null &&
              this.institutionStateFullName.equals(other.getInstitutionStateFullName()))) &&
            ((this.institutionURL==null && other.getInstitutionURL()==null) || 
             (this.institutionURL!=null &&
              this.institutionURL.equals(other.getInstitutionURL()))) &&
            this.participationStartYearId == other.getParticipationStartYearId() &&
            this.participationStartYear == other.getParticipationStartYear() &&
            this.participationEndYearId == other.getParticipationEndYearId() &&
            this.participationEndYear == other.getParticipationEndYear() &&
            ((this.defaultDiscountRate==null && other.getDefaultDiscountRate()==null) || 
             (this.defaultDiscountRate!=null &&
              this.defaultDiscountRate.equals(other.getDefaultDiscountRate()))) &&
            ((this.defaultAlmaMaterDiscountRate==null && other.getDefaultAlmaMaterDiscountRate()==null) || 
             (this.defaultAlmaMaterDiscountRate!=null &&
              this.defaultAlmaMaterDiscountRate.equals(other.getDefaultAlmaMaterDiscountRate()))) &&
            this.religiousAffiliationsId == other.getReligiousAffiliationsId() &&
            ((this.religiousAffiliationsDescription==null && other.getReligiousAffiliationsDescription()==null) || 
             (this.religiousAffiliationsDescription!=null &&
              this.religiousAffiliationsDescription.equals(other.getReligiousAffiliationsDescription()))) &&
            this.typeOfSchoolId == other.getTypeOfSchoolId() &&
            ((this.typeOfSchoolDescription==null && other.getTypeOfSchoolDescription()==null) || 
             (this.typeOfSchoolDescription!=null &&
              this.typeOfSchoolDescription.equals(other.getTypeOfSchoolDescription()))) &&
            this.sizeOfSchoolId == other.getSizeOfSchoolId() &&
            ((this.sizeOfSchoolDescription==null && other.getSizeOfSchoolDescription()==null) || 
             (this.sizeOfSchoolDescription!=null &&
              this.sizeOfSchoolDescription.equals(other.getSizeOfSchoolDescription())));
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
        _hashCode += getInstitutionId();
        if (getInstitutionName() != null) {
            _hashCode += getInstitutionName().hashCode();
        }
        if (getInstitutionStateId() != null) {
            _hashCode += getInstitutionStateId().hashCode();
        }
        if (getInstitutionStateShortName() != null) {
            _hashCode += getInstitutionStateShortName().hashCode();
        }
        if (getInstitutionStateFullName() != null) {
            _hashCode += getInstitutionStateFullName().hashCode();
        }
        if (getInstitutionURL() != null) {
            _hashCode += getInstitutionURL().hashCode();
        }
        _hashCode += getParticipationStartYearId();
        _hashCode += getParticipationStartYear();
        _hashCode += getParticipationEndYearId();
        _hashCode += getParticipationEndYear();
        if (getDefaultDiscountRate() != null) {
            _hashCode += getDefaultDiscountRate().hashCode();
        }
        if (getDefaultAlmaMaterDiscountRate() != null) {
            _hashCode += getDefaultAlmaMaterDiscountRate().hashCode();
        }
        _hashCode += getReligiousAffiliationsId();
        if (getReligiousAffiliationsDescription() != null) {
            _hashCode += getReligiousAffiliationsDescription().hashCode();
        }
        _hashCode += getTypeOfSchoolId();
        if (getTypeOfSchoolDescription() != null) {
            _hashCode += getTypeOfSchoolDescription().hashCode();
        }
        _hashCode += getSizeOfSchoolId();
        if (getSizeOfSchoolDescription() != null) {
            _hashCode += getSizeOfSchoolDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Institution.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("institutionStateId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionStateId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionStateShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionStateShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionStateFullName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionStateFullName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("institutionURL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InstitutionURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participationStartYearId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ParticipationStartYearId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participationStartYear");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ParticipationStartYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participationEndYearId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ParticipationEndYearId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("participationEndYear");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ParticipationEndYear"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultDiscountRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DefaultDiscountRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultAlmaMaterDiscountRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DefaultAlmaMaterDiscountRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("religiousAffiliationsId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReligiousAffiliationsId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("religiousAffiliationsDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReligiousAffiliationsDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeOfSchoolId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TypeOfSchoolId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("typeOfSchoolDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TypeOfSchoolDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sizeOfSchoolId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SizeOfSchoolId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sizeOfSchoolDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SizeOfSchoolDescription"));
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

}
