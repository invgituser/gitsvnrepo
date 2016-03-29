/**
 * WebUserCustomValuesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.login;

public class WebUserCustomValuesRequest  implements java.io.Serializable {
    private java.lang.String customValue1;

    private java.lang.String customValue2;

    private java.lang.String customValue3;

    private java.lang.String customValue4;

    private java.lang.String customValue5;

    public WebUserCustomValuesRequest() {
    }

    public WebUserCustomValuesRequest(
           java.lang.String customValue1,
           java.lang.String customValue2,
           java.lang.String customValue3,
           java.lang.String customValue4,
           java.lang.String customValue5) {
           this.customValue1 = customValue1;
           this.customValue2 = customValue2;
           this.customValue3 = customValue3;
           this.customValue4 = customValue4;
           this.customValue5 = customValue5;
    }


    /**
     * Gets the customValue1 value for this WebUserCustomValuesRequest.
     * 
     * @return customValue1
     */
    public java.lang.String getCustomValue1() {
        return customValue1;
    }


    /**
     * Sets the customValue1 value for this WebUserCustomValuesRequest.
     * 
     * @param customValue1
     */
    public void setCustomValue1(java.lang.String customValue1) {
        this.customValue1 = customValue1;
    }


    /**
     * Gets the customValue2 value for this WebUserCustomValuesRequest.
     * 
     * @return customValue2
     */
    public java.lang.String getCustomValue2() {
        return customValue2;
    }


    /**
     * Sets the customValue2 value for this WebUserCustomValuesRequest.
     * 
     * @param customValue2
     */
    public void setCustomValue2(java.lang.String customValue2) {
        this.customValue2 = customValue2;
    }


    /**
     * Gets the customValue3 value for this WebUserCustomValuesRequest.
     * 
     * @return customValue3
     */
    public java.lang.String getCustomValue3() {
        return customValue3;
    }


    /**
     * Sets the customValue3 value for this WebUserCustomValuesRequest.
     * 
     * @param customValue3
     */
    public void setCustomValue3(java.lang.String customValue3) {
        this.customValue3 = customValue3;
    }


    /**
     * Gets the customValue4 value for this WebUserCustomValuesRequest.
     * 
     * @return customValue4
     */
    public java.lang.String getCustomValue4() {
        return customValue4;
    }


    /**
     * Sets the customValue4 value for this WebUserCustomValuesRequest.
     * 
     * @param customValue4
     */
    public void setCustomValue4(java.lang.String customValue4) {
        this.customValue4 = customValue4;
    }


    /**
     * Gets the customValue5 value for this WebUserCustomValuesRequest.
     * 
     * @return customValue5
     */
    public java.lang.String getCustomValue5() {
        return customValue5;
    }


    /**
     * Sets the customValue5 value for this WebUserCustomValuesRequest.
     * 
     * @param customValue5
     */
    public void setCustomValue5(java.lang.String customValue5) {
        this.customValue5 = customValue5;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof WebUserCustomValuesRequest)) return false;
        WebUserCustomValuesRequest other = (WebUserCustomValuesRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.customValue1==null && other.getCustomValue1()==null) || 
             (this.customValue1!=null &&
              this.customValue1.equals(other.getCustomValue1()))) &&
            ((this.customValue2==null && other.getCustomValue2()==null) || 
             (this.customValue2!=null &&
              this.customValue2.equals(other.getCustomValue2()))) &&
            ((this.customValue3==null && other.getCustomValue3()==null) || 
             (this.customValue3!=null &&
              this.customValue3.equals(other.getCustomValue3()))) &&
            ((this.customValue4==null && other.getCustomValue4()==null) || 
             (this.customValue4!=null &&
              this.customValue4.equals(other.getCustomValue4()))) &&
            ((this.customValue5==null && other.getCustomValue5()==null) || 
             (this.customValue5!=null &&
              this.customValue5.equals(other.getCustomValue5())));
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
        if (getCustomValue1() != null) {
            _hashCode += getCustomValue1().hashCode();
        }
        if (getCustomValue2() != null) {
            _hashCode += getCustomValue2().hashCode();
        }
        if (getCustomValue3() != null) {
            _hashCode += getCustomValue3().hashCode();
        }
        if (getCustomValue4() != null) {
            _hashCode += getCustomValue4().hashCode();
        }
        if (getCustomValue5() != null) {
            _hashCode += getCustomValue5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(WebUserCustomValuesRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserCustomValuesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customValue5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomValue5"));
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
