/**
 * AccountLinkagesRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountLinkagesRequest  implements java.io.Serializable {
    private java.lang.String webUserId;

    private java.lang.String accountToAdd;

    private org.apache.axis.types.UnsignedByte relationType;

    public AccountLinkagesRequest() {
    }

    public AccountLinkagesRequest(
           java.lang.String webUserId,
           java.lang.String accountToAdd,
           org.apache.axis.types.UnsignedByte relationType) {
           this.webUserId = webUserId;
           this.accountToAdd = accountToAdd;
           this.relationType = relationType;
    }


    /**
     * Gets the webUserId value for this AccountLinkagesRequest.
     * 
     * @return webUserId
     */
    public java.lang.String getWebUserId() {
        return webUserId;
    }


    /**
     * Sets the webUserId value for this AccountLinkagesRequest.
     * 
     * @param webUserId
     */
    public void setWebUserId(java.lang.String webUserId) {
        this.webUserId = webUserId;
    }


    /**
     * Gets the accountToAdd value for this AccountLinkagesRequest.
     * 
     * @return accountToAdd
     */
    public java.lang.String getAccountToAdd() {
        return accountToAdd;
    }


    /**
     * Sets the accountToAdd value for this AccountLinkagesRequest.
     * 
     * @param accountToAdd
     */
    public void setAccountToAdd(java.lang.String accountToAdd) {
        this.accountToAdd = accountToAdd;
    }


    /**
     * Gets the relationType value for this AccountLinkagesRequest.
     * 
     * @return relationType
     */
    public org.apache.axis.types.UnsignedByte getRelationType() {
        return relationType;
    }


    /**
     * Sets the relationType value for this AccountLinkagesRequest.
     * 
     * @param relationType
     */
    public void setRelationType(org.apache.axis.types.UnsignedByte relationType) {
        this.relationType = relationType;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountLinkagesRequest)) return false;
        AccountLinkagesRequest other = (AccountLinkagesRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.webUserId==null && other.getWebUserId()==null) || 
             (this.webUserId!=null &&
              this.webUserId.equals(other.getWebUserId()))) &&
            ((this.accountToAdd==null && other.getAccountToAdd()==null) || 
             (this.accountToAdd!=null &&
              this.accountToAdd.equals(other.getAccountToAdd()))) &&
            ((this.relationType==null && other.getRelationType()==null) || 
             (this.relationType!=null &&
              this.relationType.equals(other.getRelationType())));
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
        if (getWebUserId() != null) {
            _hashCode += getWebUserId().hashCode();
        }
        if (getAccountToAdd() != null) {
            _hashCode += getAccountToAdd().hashCode();
        }
        if (getRelationType() != null) {
            _hashCode += getRelationType().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountLinkagesRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountLinkagesRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountToAdd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountToAdd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relationType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RelationType"));
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
