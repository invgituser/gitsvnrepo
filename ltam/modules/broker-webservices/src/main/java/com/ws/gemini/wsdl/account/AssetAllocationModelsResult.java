/**
 * AssetAllocationModelsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class AssetAllocationModelsResult  implements java.io.Serializable {
    private int modelId;

    private java.lang.String accountNumber;

    private java.lang.String modelName;

    private java.lang.String modelDescription;

    private org.apache.axis.types.UnsignedByte modelScope;

    private org.apache.axis.types.UnsignedByte status;

    private boolean rebalanceOnPurchasesFlag;

    private boolean rebalanceOnRedemptionsFlag;

    private java.math.BigDecimal allocationPercentageTolerance;

    private org.apache.axis.types.UnsignedByte noOfFundsInModel;

    private short ageBasedModelId;

    private org.apache.axis.types.UnsignedByte investmentOption;

    private boolean updateBeneficiaryAgeBasedModel;

    private com.ws.gemini.wsdl.account.AssetAllocationFundsResult[] assetAllocationFunds;

    private com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection;

    private java.lang.String ageBasedModelName;

    public AssetAllocationModelsResult() {
    }

    public AssetAllocationModelsResult(
           int modelId,
           java.lang.String accountNumber,
           java.lang.String modelName,
           java.lang.String modelDescription,
           org.apache.axis.types.UnsignedByte modelScope,
           org.apache.axis.types.UnsignedByte status,
           boolean rebalanceOnPurchasesFlag,
           boolean rebalanceOnRedemptionsFlag,
           java.math.BigDecimal allocationPercentageTolerance,
           org.apache.axis.types.UnsignedByte noOfFundsInModel,
           short ageBasedModelId,
           org.apache.axis.types.UnsignedByte investmentOption,
           boolean updateBeneficiaryAgeBasedModel,
           com.ws.gemini.wsdl.account.AssetAllocationFundsResult[] assetAllocationFunds,
           com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection,
           java.lang.String ageBasedModelName) {
           this.modelId = modelId;
           this.accountNumber = accountNumber;
           this.modelName = modelName;
           this.modelDescription = modelDescription;
           this.modelScope = modelScope;
           this.status = status;
           this.rebalanceOnPurchasesFlag = rebalanceOnPurchasesFlag;
           this.rebalanceOnRedemptionsFlag = rebalanceOnRedemptionsFlag;
           this.allocationPercentageTolerance = allocationPercentageTolerance;
           this.noOfFundsInModel = noOfFundsInModel;
           this.ageBasedModelId = ageBasedModelId;
           this.investmentOption = investmentOption;
           this.updateBeneficiaryAgeBasedModel = updateBeneficiaryAgeBasedModel;
           this.assetAllocationFunds = assetAllocationFunds;
           this.accountAssetAllocationModelWeightsCollection = accountAssetAllocationModelWeightsCollection;
           this.ageBasedModelName = ageBasedModelName;
    }


    /**
     * Gets the modelId value for this AssetAllocationModelsResult.
     * 
     * @return modelId
     */
    public int getModelId() {
        return modelId;
    }


    /**
     * Sets the modelId value for this AssetAllocationModelsResult.
     * 
     * @param modelId
     */
    public void setModelId(int modelId) {
        this.modelId = modelId;
    }


    /**
     * Gets the accountNumber value for this AssetAllocationModelsResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AssetAllocationModelsResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the modelName value for this AssetAllocationModelsResult.
     * 
     * @return modelName
     */
    public java.lang.String getModelName() {
        return modelName;
    }


    /**
     * Sets the modelName value for this AssetAllocationModelsResult.
     * 
     * @param modelName
     */
    public void setModelName(java.lang.String modelName) {
        this.modelName = modelName;
    }


    /**
     * Gets the modelDescription value for this AssetAllocationModelsResult.
     * 
     * @return modelDescription
     */
    public java.lang.String getModelDescription() {
        return modelDescription;
    }


    /**
     * Sets the modelDescription value for this AssetAllocationModelsResult.
     * 
     * @param modelDescription
     */
    public void setModelDescription(java.lang.String modelDescription) {
        this.modelDescription = modelDescription;
    }


    /**
     * Gets the modelScope value for this AssetAllocationModelsResult.
     * 
     * @return modelScope
     */
    public org.apache.axis.types.UnsignedByte getModelScope() {
        return modelScope;
    }


    /**
     * Sets the modelScope value for this AssetAllocationModelsResult.
     * 
     * @param modelScope
     */
    public void setModelScope(org.apache.axis.types.UnsignedByte modelScope) {
        this.modelScope = modelScope;
    }


    /**
     * Gets the status value for this AssetAllocationModelsResult.
     * 
     * @return status
     */
    public org.apache.axis.types.UnsignedByte getStatus() {
        return status;
    }


    /**
     * Sets the status value for this AssetAllocationModelsResult.
     * 
     * @param status
     */
    public void setStatus(org.apache.axis.types.UnsignedByte status) {
        this.status = status;
    }


    /**
     * Gets the rebalanceOnPurchasesFlag value for this AssetAllocationModelsResult.
     * 
     * @return rebalanceOnPurchasesFlag
     */
    public boolean isRebalanceOnPurchasesFlag() {
        return rebalanceOnPurchasesFlag;
    }


    /**
     * Sets the rebalanceOnPurchasesFlag value for this AssetAllocationModelsResult.
     * 
     * @param rebalanceOnPurchasesFlag
     */
    public void setRebalanceOnPurchasesFlag(boolean rebalanceOnPurchasesFlag) {
        this.rebalanceOnPurchasesFlag = rebalanceOnPurchasesFlag;
    }


    /**
     * Gets the rebalanceOnRedemptionsFlag value for this AssetAllocationModelsResult.
     * 
     * @return rebalanceOnRedemptionsFlag
     */
    public boolean isRebalanceOnRedemptionsFlag() {
        return rebalanceOnRedemptionsFlag;
    }


    /**
     * Sets the rebalanceOnRedemptionsFlag value for this AssetAllocationModelsResult.
     * 
     * @param rebalanceOnRedemptionsFlag
     */
    public void setRebalanceOnRedemptionsFlag(boolean rebalanceOnRedemptionsFlag) {
        this.rebalanceOnRedemptionsFlag = rebalanceOnRedemptionsFlag;
    }


    /**
     * Gets the allocationPercentageTolerance value for this AssetAllocationModelsResult.
     * 
     * @return allocationPercentageTolerance
     */
    public java.math.BigDecimal getAllocationPercentageTolerance() {
        return allocationPercentageTolerance;
    }


    /**
     * Sets the allocationPercentageTolerance value for this AssetAllocationModelsResult.
     * 
     * @param allocationPercentageTolerance
     */
    public void setAllocationPercentageTolerance(java.math.BigDecimal allocationPercentageTolerance) {
        this.allocationPercentageTolerance = allocationPercentageTolerance;
    }


    /**
     * Gets the noOfFundsInModel value for this AssetAllocationModelsResult.
     * 
     * @return noOfFundsInModel
     */
    public org.apache.axis.types.UnsignedByte getNoOfFundsInModel() {
        return noOfFundsInModel;
    }


    /**
     * Sets the noOfFundsInModel value for this AssetAllocationModelsResult.
     * 
     * @param noOfFundsInModel
     */
    public void setNoOfFundsInModel(org.apache.axis.types.UnsignedByte noOfFundsInModel) {
        this.noOfFundsInModel = noOfFundsInModel;
    }


    /**
     * Gets the ageBasedModelId value for this AssetAllocationModelsResult.
     * 
     * @return ageBasedModelId
     */
    public short getAgeBasedModelId() {
        return ageBasedModelId;
    }


    /**
     * Sets the ageBasedModelId value for this AssetAllocationModelsResult.
     * 
     * @param ageBasedModelId
     */
    public void setAgeBasedModelId(short ageBasedModelId) {
        this.ageBasedModelId = ageBasedModelId;
    }


    /**
     * Gets the investmentOption value for this AssetAllocationModelsResult.
     * 
     * @return investmentOption
     */
    public org.apache.axis.types.UnsignedByte getInvestmentOption() {
        return investmentOption;
    }


    /**
     * Sets the investmentOption value for this AssetAllocationModelsResult.
     * 
     * @param investmentOption
     */
    public void setInvestmentOption(org.apache.axis.types.UnsignedByte investmentOption) {
        this.investmentOption = investmentOption;
    }


    /**
     * Gets the updateBeneficiaryAgeBasedModel value for this AssetAllocationModelsResult.
     * 
     * @return updateBeneficiaryAgeBasedModel
     */
    public boolean isUpdateBeneficiaryAgeBasedModel() {
        return updateBeneficiaryAgeBasedModel;
    }


    /**
     * Sets the updateBeneficiaryAgeBasedModel value for this AssetAllocationModelsResult.
     * 
     * @param updateBeneficiaryAgeBasedModel
     */
    public void setUpdateBeneficiaryAgeBasedModel(boolean updateBeneficiaryAgeBasedModel) {
        this.updateBeneficiaryAgeBasedModel = updateBeneficiaryAgeBasedModel;
    }


    /**
     * Gets the assetAllocationFunds value for this AssetAllocationModelsResult.
     * 
     * @return assetAllocationFunds
     */
    public com.ws.gemini.wsdl.account.AssetAllocationFundsResult[] getAssetAllocationFunds() {
        return assetAllocationFunds;
    }


    /**
     * Sets the assetAllocationFunds value for this AssetAllocationModelsResult.
     * 
     * @param assetAllocationFunds
     */
    public void setAssetAllocationFunds(com.ws.gemini.wsdl.account.AssetAllocationFundsResult[] assetAllocationFunds) {
        this.assetAllocationFunds = assetAllocationFunds;
    }


    /**
     * Gets the accountAssetAllocationModelWeightsCollection value for this AssetAllocationModelsResult.
     * 
     * @return accountAssetAllocationModelWeightsCollection
     */
    public com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] getAccountAssetAllocationModelWeightsCollection() {
        return accountAssetAllocationModelWeightsCollection;
    }


    /**
     * Sets the accountAssetAllocationModelWeightsCollection value for this AssetAllocationModelsResult.
     * 
     * @param accountAssetAllocationModelWeightsCollection
     */
    public void setAccountAssetAllocationModelWeightsCollection(com.ws.gemini.wsdl.account.AccountAssetAllocationModelWeightsResult[] accountAssetAllocationModelWeightsCollection) {
        this.accountAssetAllocationModelWeightsCollection = accountAssetAllocationModelWeightsCollection;
    }


    /**
     * Gets the ageBasedModelName value for this AssetAllocationModelsResult.
     * 
     * @return ageBasedModelName
     */
    public java.lang.String getAgeBasedModelName() {
        return ageBasedModelName;
    }


    /**
     * Sets the ageBasedModelName value for this AssetAllocationModelsResult.
     * 
     * @param ageBasedModelName
     */
    public void setAgeBasedModelName(java.lang.String ageBasedModelName) {
        this.ageBasedModelName = ageBasedModelName;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AssetAllocationModelsResult)) return false;
        AssetAllocationModelsResult other = (AssetAllocationModelsResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.modelId == other.getModelId() &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.modelName==null && other.getModelName()==null) || 
             (this.modelName!=null &&
              this.modelName.equals(other.getModelName()))) &&
            ((this.modelDescription==null && other.getModelDescription()==null) || 
             (this.modelDescription!=null &&
              this.modelDescription.equals(other.getModelDescription()))) &&
            ((this.modelScope==null && other.getModelScope()==null) || 
             (this.modelScope!=null &&
              this.modelScope.equals(other.getModelScope()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            this.rebalanceOnPurchasesFlag == other.isRebalanceOnPurchasesFlag() &&
            this.rebalanceOnRedemptionsFlag == other.isRebalanceOnRedemptionsFlag() &&
            ((this.allocationPercentageTolerance==null && other.getAllocationPercentageTolerance()==null) || 
             (this.allocationPercentageTolerance!=null &&
              this.allocationPercentageTolerance.equals(other.getAllocationPercentageTolerance()))) &&
            ((this.noOfFundsInModel==null && other.getNoOfFundsInModel()==null) || 
             (this.noOfFundsInModel!=null &&
              this.noOfFundsInModel.equals(other.getNoOfFundsInModel()))) &&
            this.ageBasedModelId == other.getAgeBasedModelId() &&
            ((this.investmentOption==null && other.getInvestmentOption()==null) || 
             (this.investmentOption!=null &&
              this.investmentOption.equals(other.getInvestmentOption()))) &&
            this.updateBeneficiaryAgeBasedModel == other.isUpdateBeneficiaryAgeBasedModel() &&
            ((this.assetAllocationFunds==null && other.getAssetAllocationFunds()==null) || 
             (this.assetAllocationFunds!=null &&
              java.util.Arrays.equals(this.assetAllocationFunds, other.getAssetAllocationFunds()))) &&
            ((this.accountAssetAllocationModelWeightsCollection==null && other.getAccountAssetAllocationModelWeightsCollection()==null) || 
             (this.accountAssetAllocationModelWeightsCollection!=null &&
              java.util.Arrays.equals(this.accountAssetAllocationModelWeightsCollection, other.getAccountAssetAllocationModelWeightsCollection()))) &&
            ((this.ageBasedModelName==null && other.getAgeBasedModelName()==null) || 
             (this.ageBasedModelName!=null &&
              this.ageBasedModelName.equals(other.getAgeBasedModelName())));
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
        _hashCode += getModelId();
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getModelName() != null) {
            _hashCode += getModelName().hashCode();
        }
        if (getModelDescription() != null) {
            _hashCode += getModelDescription().hashCode();
        }
        if (getModelScope() != null) {
            _hashCode += getModelScope().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        _hashCode += (isRebalanceOnPurchasesFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isRebalanceOnRedemptionsFlag() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAllocationPercentageTolerance() != null) {
            _hashCode += getAllocationPercentageTolerance().hashCode();
        }
        if (getNoOfFundsInModel() != null) {
            _hashCode += getNoOfFundsInModel().hashCode();
        }
        _hashCode += getAgeBasedModelId();
        if (getInvestmentOption() != null) {
            _hashCode += getInvestmentOption().hashCode();
        }
        _hashCode += (isUpdateBeneficiaryAgeBasedModel() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getAssetAllocationFunds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAssetAllocationFunds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAssetAllocationFunds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccountAssetAllocationModelWeightsCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAccountAssetAllocationModelWeightsCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAccountAssetAllocationModelWeightsCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAgeBasedModelName() != null) {
            _hashCode += getAgeBasedModelName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AssetAllocationModelsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ModelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
        elemField.setFieldName("modelName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ModelName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ModelDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelScope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ModelScope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CallStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rebalanceOnPurchasesFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RebalanceOnPurchasesFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rebalanceOnRedemptionsFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RebalanceOnRedemptionsFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allocationPercentageTolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AllocationPercentageTolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfFundsInModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfFundsInModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ageBasedModelId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AgeBasedModelId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("investmentOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InvestmentOption"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateBeneficiaryAgeBasedModel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateBeneficiaryAgeBasedModel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetAllocationFunds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFunds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationFundsResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountAssetAllocationModelWeightsCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountAssetAllocationModelWeightsResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ageBasedModelName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AgeBasedModelName"));
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
