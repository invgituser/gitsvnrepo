/**
 * UpdateFavoriteInstitutionsRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class UpdateFavoriteInstitutionsRequest  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private com.invessence.ws.provider.gemini.wsdl.account.FavoriteInstitutionsRequest[] favoriteInstitutions;

    public UpdateFavoriteInstitutionsRequest() {
    }

    public UpdateFavoriteInstitutionsRequest(
           java.lang.String accountNumber,
           com.invessence.ws.provider.gemini.wsdl.account.FavoriteInstitutionsRequest[] favoriteInstitutions) {
           this.accountNumber = accountNumber;
           this.favoriteInstitutions = favoriteInstitutions;
    }


    /**
     * Gets the accountNumber value for this UpdateFavoriteInstitutionsRequest.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this UpdateFavoriteInstitutionsRequest.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the favoriteInstitutions value for this UpdateFavoriteInstitutionsRequest.
     * 
     * @return favoriteInstitutions
     */
    public com.invessence.ws.provider.gemini.wsdl.account.FavoriteInstitutionsRequest[] getFavoriteInstitutions() {
        return favoriteInstitutions;
    }


    /**
     * Sets the favoriteInstitutions value for this UpdateFavoriteInstitutionsRequest.
     * 
     * @param favoriteInstitutions
     */
    public void setFavoriteInstitutions(com.invessence.ws.provider.gemini.wsdl.account.FavoriteInstitutionsRequest[] favoriteInstitutions) {
        this.favoriteInstitutions = favoriteInstitutions;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateFavoriteInstitutionsRequest)) return false;
        UpdateFavoriteInstitutionsRequest other = (UpdateFavoriteInstitutionsRequest) obj;
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
            ((this.favoriteInstitutions==null && other.getFavoriteInstitutions()==null) || 
             (this.favoriteInstitutions!=null &&
              java.util.Arrays.equals(this.favoriteInstitutions, other.getFavoriteInstitutions())));
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
        if (getFavoriteInstitutions() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFavoriteInstitutions());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFavoriteInstitutions(), i);
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
        new org.apache.axis.description.TypeDesc(UpdateFavoriteInstitutionsRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateFavoriteInstitutionsRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsRequest"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsRequest"));
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
