/**
 * AuthenticateLogin.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.transaction;

public class AuthenticateLogin  implements java.io.Serializable {
    private java.lang.String userId;

    private java.lang.String password;

    private java.lang.String fundGroupName;

    private java.lang.String allowableShareClassList;

    public AuthenticateLogin() {
    }

    public AuthenticateLogin(
           java.lang.String userId,
           java.lang.String password,
           java.lang.String fundGroupName,
           java.lang.String allowableShareClassList) {
           this.userId = userId;
           this.password = password;
           this.fundGroupName = fundGroupName;
           this.allowableShareClassList = allowableShareClassList;
    }


    /**
     * Gets the userId value for this AuthenticateLogin.
     * 
     * @return userId
     */
    public java.lang.String getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this AuthenticateLogin.
     * 
     * @param userId
     */
    public void setUserId(java.lang.String userId) {
        this.userId = userId;
    }


    /**
     * Gets the password value for this AuthenticateLogin.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AuthenticateLogin.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME value for this AuthenticateLogin.
     * 
     * @return BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME
     */
    public java.lang.String getFundGroupName() {
        return fundGroupName;
    }


    /**
     * Sets the BROKER_WEBSERVICES_GEMINI_FUND_GROUP_NAME value for this AuthenticateLogin.
     * 
     * @param fundGroupName
     */
    public void setFundGroupName(java.lang.String fundGroupName) {
        this.fundGroupName = fundGroupName;
    }


    /**
     * Gets the allowableShareClassList value for this AuthenticateLogin.
     * 
     * @return allowableShareClassList
     */
    public java.lang.String getAllowableShareClassList() {
        return allowableShareClassList;
    }


    /**
     * Sets the allowableShareClassList value for this AuthenticateLogin.
     * 
     * @param allowableShareClassList
     */
    public void setAllowableShareClassList(java.lang.String allowableShareClassList) {
        this.allowableShareClassList = allowableShareClassList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthenticateLogin)) return false;
        AuthenticateLogin other = (AuthenticateLogin) obj;
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
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.fundGroupName==null && other.getFundGroupName()==null) || 
             (this.fundGroupName!=null &&
              this.fundGroupName.equals(other.getFundGroupName()))) &&
            ((this.allowableShareClassList==null && other.getAllowableShareClassList()==null) || 
             (this.allowableShareClassList!=null &&
              this.allowableShareClassList.equals(other.getAllowableShareClassList())));
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
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getFundGroupName() != null) {
            _hashCode += getFundGroupName().hashCode();
        }
        if (getAllowableShareClassList() != null) {
            _hashCode += getAllowableShareClassList().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthenticateLogin.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthenticateLogin"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundGroupName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundGroupName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowableShareClassList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AllowableShareClassList"));
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
