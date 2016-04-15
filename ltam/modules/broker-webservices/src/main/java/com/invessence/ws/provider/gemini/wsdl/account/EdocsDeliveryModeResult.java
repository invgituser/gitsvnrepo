/**
 * EdocsDeliveryModeResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class EdocsDeliveryModeResult  implements java.io.Serializable {
    private short formCategoryId;

    private java.lang.String formCategoryName;

    private org.apache.axis.types.UnsignedByte deliveryMode;

    private java.lang.String deliveryModeDescription;

    public EdocsDeliveryModeResult() {
    }

    public EdocsDeliveryModeResult(
           short formCategoryId,
           java.lang.String formCategoryName,
           org.apache.axis.types.UnsignedByte deliveryMode,
           java.lang.String deliveryModeDescription) {
           this.formCategoryId = formCategoryId;
           this.formCategoryName = formCategoryName;
           this.deliveryMode = deliveryMode;
           this.deliveryModeDescription = deliveryModeDescription;
    }


    /**
     * Gets the formCategoryId value for this EdocsDeliveryModeResult.
     * 
     * @return formCategoryId
     */
    public short getFormCategoryId() {
        return formCategoryId;
    }


    /**
     * Sets the formCategoryId value for this EdocsDeliveryModeResult.
     * 
     * @param formCategoryId
     */
    public void setFormCategoryId(short formCategoryId) {
        this.formCategoryId = formCategoryId;
    }


    /**
     * Gets the formCategoryName value for this EdocsDeliveryModeResult.
     * 
     * @return formCategoryName
     */
    public java.lang.String getFormCategoryName() {
        return formCategoryName;
    }


    /**
     * Sets the formCategoryName value for this EdocsDeliveryModeResult.
     * 
     * @param formCategoryName
     */
    public void setFormCategoryName(java.lang.String formCategoryName) {
        this.formCategoryName = formCategoryName;
    }


    /**
     * Gets the deliveryMode value for this EdocsDeliveryModeResult.
     * 
     * @return deliveryMode
     */
    public org.apache.axis.types.UnsignedByte getDeliveryMode() {
        return deliveryMode;
    }


    /**
     * Sets the deliveryMode value for this EdocsDeliveryModeResult.
     * 
     * @param deliveryMode
     */
    public void setDeliveryMode(org.apache.axis.types.UnsignedByte deliveryMode) {
        this.deliveryMode = deliveryMode;
    }


    /**
     * Gets the deliveryModeDescription value for this EdocsDeliveryModeResult.
     * 
     * @return deliveryModeDescription
     */
    public java.lang.String getDeliveryModeDescription() {
        return deliveryModeDescription;
    }


    /**
     * Sets the deliveryModeDescription value for this EdocsDeliveryModeResult.
     * 
     * @param deliveryModeDescription
     */
    public void setDeliveryModeDescription(java.lang.String deliveryModeDescription) {
        this.deliveryModeDescription = deliveryModeDescription;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EdocsDeliveryModeResult)) return false;
        EdocsDeliveryModeResult other = (EdocsDeliveryModeResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.formCategoryId == other.getFormCategoryId() &&
            ((this.formCategoryName==null && other.getFormCategoryName()==null) || 
             (this.formCategoryName!=null &&
              this.formCategoryName.equals(other.getFormCategoryName()))) &&
            ((this.deliveryMode==null && other.getDeliveryMode()==null) || 
             (this.deliveryMode!=null &&
              this.deliveryMode.equals(other.getDeliveryMode()))) &&
            ((this.deliveryModeDescription==null && other.getDeliveryModeDescription()==null) || 
             (this.deliveryModeDescription!=null &&
              this.deliveryModeDescription.equals(other.getDeliveryModeDescription())));
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
        _hashCode += getFormCategoryId();
        if (getFormCategoryName() != null) {
            _hashCode += getFormCategoryName().hashCode();
        }
        if (getDeliveryMode() != null) {
            _hashCode += getDeliveryMode().hashCode();
        }
        if (getDeliveryModeDescription() != null) {
            _hashCode += getDeliveryModeDescription().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EdocsDeliveryModeResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EdocsDeliveryModeResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formCategoryId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FormCategoryId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("formCategoryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FormCategoryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryMode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DeliveryMode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("deliveryModeDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DeliveryModeDescription"));
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
