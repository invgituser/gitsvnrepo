/**
 * FavoriteInstitutionsCollectionResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class FavoriteInstitutionsCollectionResult  implements java.io.Serializable {
    private com.ws.gemini.wsdl.account.Institution[] favoriteInstitutions;

    private com.ws.gemini.wsdl.account.Status errorStatus;

    public FavoriteInstitutionsCollectionResult() {
    }

    public FavoriteInstitutionsCollectionResult(
           com.ws.gemini.wsdl.account.Institution[] favoriteInstitutions,
           com.ws.gemini.wsdl.account.Status errorStatus) {
           this.favoriteInstitutions = favoriteInstitutions;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the favoriteInstitutions value for this FavoriteInstitutionsCollectionResult.
     * 
     * @return favoriteInstitutions
     */
    public com.ws.gemini.wsdl.account.Institution[] getFavoriteInstitutions() {
        return favoriteInstitutions;
    }


    /**
     * Sets the favoriteInstitutions value for this FavoriteInstitutionsCollectionResult.
     * 
     * @param favoriteInstitutions
     */
    public void setFavoriteInstitutions(com.ws.gemini.wsdl.account.Institution[] favoriteInstitutions) {
        this.favoriteInstitutions = favoriteInstitutions;
    }


    /**
     * Gets the errorStatus value for this FavoriteInstitutionsCollectionResult.
     * 
     * @return errorStatus
     */
    public com.ws.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this FavoriteInstitutionsCollectionResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.ws.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FavoriteInstitutionsCollectionResult)) return false;
        FavoriteInstitutionsCollectionResult other = (FavoriteInstitutionsCollectionResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.favoriteInstitutions==null && other.getFavoriteInstitutions()==null) || 
             (this.favoriteInstitutions!=null &&
              java.util.Arrays.equals(this.favoriteInstitutions, other.getFavoriteInstitutions()))) &&
            ((this.errorStatus==null && other.getErrorStatus()==null) || 
             (this.errorStatus!=null &&
              this.errorStatus.equals(other.getErrorStatus())));
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
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FavoriteInstitutionsCollectionResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionsCollectionResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Institution"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CallStatus"));
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
