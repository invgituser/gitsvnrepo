/**
 * CustomFieldListValuesResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class CustomFieldListValuesResult  implements java.io.Serializable {
    private java.lang.String customFieldDefinition;

    private short listId;

    private com.invessence.ws.provider.gemini.wsdl.account.CustomFieldListValue[] customFieldListValues;

    public CustomFieldListValuesResult() {
    }

    public CustomFieldListValuesResult(
           java.lang.String customFieldDefinition,
           short listId,
           com.invessence.ws.provider.gemini.wsdl.account.CustomFieldListValue[] customFieldListValues) {
           this.customFieldDefinition = customFieldDefinition;
           this.listId = listId;
           this.customFieldListValues = customFieldListValues;
    }


    /**
     * Gets the customFieldDefinition value for this CustomFieldListValuesResult.
     * 
     * @return customFieldDefinition
     */
    public java.lang.String getCustomFieldDefinition() {
        return customFieldDefinition;
    }


    /**
     * Sets the customFieldDefinition value for this CustomFieldListValuesResult.
     * 
     * @param customFieldDefinition
     */
    public void setCustomFieldDefinition(java.lang.String customFieldDefinition) {
        this.customFieldDefinition = customFieldDefinition;
    }


    /**
     * Gets the listId value for this CustomFieldListValuesResult.
     * 
     * @return listId
     */
    public short getListId() {
        return listId;
    }


    /**
     * Sets the listId value for this CustomFieldListValuesResult.
     * 
     * @param listId
     */
    public void setListId(short listId) {
        this.listId = listId;
    }


    /**
     * Gets the customFieldListValues value for this CustomFieldListValuesResult.
     * 
     * @return customFieldListValues
     */
    public com.invessence.ws.provider.gemini.wsdl.account.CustomFieldListValue[] getCustomFieldListValues() {
        return customFieldListValues;
    }


    /**
     * Sets the customFieldListValues value for this CustomFieldListValuesResult.
     * 
     * @param customFieldListValues
     */
    public void setCustomFieldListValues(com.invessence.ws.provider.gemini.wsdl.account.CustomFieldListValue[] customFieldListValues) {
        this.customFieldListValues = customFieldListValues;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CustomFieldListValuesResult)) return false;
        CustomFieldListValuesResult other = (CustomFieldListValuesResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customFieldDefinition==null && other.getCustomFieldDefinition()==null) || 
             (this.customFieldDefinition!=null &&
              this.customFieldDefinition.equals(other.getCustomFieldDefinition()))) &&
            this.listId == other.getListId() &&
            ((this.customFieldListValues==null && other.getCustomFieldListValues()==null) || 
             (this.customFieldListValues!=null &&
              java.util.Arrays.equals(this.customFieldListValues, other.getCustomFieldListValues())));
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
        if (getCustomFieldDefinition() != null) {
            _hashCode += getCustomFieldDefinition().hashCode();
        }
        _hashCode += getListId();
        if (getCustomFieldListValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCustomFieldListValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCustomFieldListValues(), i);
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
        new org.apache.axis.description.TypeDesc(CustomFieldListValuesResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValuesResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldDefinition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldDefinition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ListId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customFieldListValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomFieldListValue"));
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
