/**
 * AccountAssetAllocationModelWeightsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AccountAssetAllocationModelWeightsResult  implements java.io.Serializable {
    private java.math.BigDecimal modelId;

    private short fundid;

    private java.math.BigDecimal allocationPercentage;

    private java.lang.String fundName;

    private com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus;

    public AccountAssetAllocationModelWeightsResult() {
    }

    public AccountAssetAllocationModelWeightsResult(
           java.math.BigDecimal modelId,
           short fundid,
           java.math.BigDecimal allocationPercentage,
           java.lang.String fundName,
           com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
           this.modelId = modelId;
           this.fundid = fundid;
           this.allocationPercentage = allocationPercentage;
           this.fundName = fundName;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the modelId value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @return modelId
     */
    public java.math.BigDecimal getModelId() {
        return modelId;
    }


    /**
     * Sets the modelId value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @param modelId
     */
    public void setModelId(java.math.BigDecimal modelId) {
        this.modelId = modelId;
    }


    /**
     * Gets the fundid value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @return fundid
     */
    public short getFundid() {
        return fundid;
    }


    /**
     * Sets the fundid value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @param fundid
     */
    public void setFundid(short fundid) {
        this.fundid = fundid;
    }


    /**
     * Gets the allocationPercentage value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @return allocationPercentage
     */
    public java.math.BigDecimal getAllocationPercentage() {
        return allocationPercentage;
    }


    /**
     * Sets the allocationPercentage value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @param allocationPercentage
     */
    public void setAllocationPercentage(java.math.BigDecimal allocationPercentage) {
        this.allocationPercentage = allocationPercentage;
    }


    /**
     * Gets the fundName value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @return fundName
     */
    public java.lang.String getFundName() {
        return fundName;
    }


    /**
     * Sets the fundName value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @param fundName
     */
    public void setFundName(java.lang.String fundName) {
        this.fundName = fundName;
    }


    /**
     * Gets the errorStatus value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @return errorStatus
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this AccountAssetAllocationModelWeightsResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AccountAssetAllocationModelWeightsResult)) return false;
        AccountAssetAllocationModelWeightsResult other = (AccountAssetAllocationModelWeightsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modelId==null && other.getModelId()==null) || 
             (this.modelId!=null &&
              this.modelId.equals(other.getModelId()))) &&
            this.fundid == other.getFundid() &&
            ((this.allocationPercentage==null && other.getAllocationPercentage()==null) || 
             (this.allocationPercentage!=null &&
              this.allocationPercentage.equals(other.getAllocationPercentage()))) &&
            ((this.fundName==null && other.getFundName()==null) || 
             (this.fundName!=null &&
              this.fundName.equals(other.getFundName()))) &&
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
        if (getModelId() != null) {
            _hashCode += getModelId().hashCode();
        }
        _hashCode += getFundid();
        if (getAllocationPercentage() != null) {
            _hashCode += getAllocationPercentage().hashCode();
        }
        if (getFundName() != null) {
            _hashCode += getFundName().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AccountAssetAllocationModelWeightsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ModelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Fundid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allocationPercentage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AllocationPercentage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ErrorStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WSCallStatus"));
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
