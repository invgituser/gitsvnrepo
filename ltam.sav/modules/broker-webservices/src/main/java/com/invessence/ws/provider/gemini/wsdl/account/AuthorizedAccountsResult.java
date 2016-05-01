/**
 * AuthorizedAccountsResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AuthorizedAccountsResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.lang.String dealerAccountNumber;

    private java.lang.String customerName;

    private java.lang.String accountName;

    private java.lang.String accountTypeName;

    private java.lang.String SSNOrTIN;

    private org.apache.axis.types.UnsignedByte SSNOrTINIndicator;

    private int groupAccountIndicator;

    private org.apache.axis.types.UnsignedByte webAccessIndicator;

    private org.apache.axis.types.UnsignedByte accountTaxReportingIndicator;

    private java.lang.String beneficiaryName;

    private java.lang.String portfolio;

    private java.lang.String shareClassId;

    private java.util.Calendar accountClosingDate;

    private org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator;

    private com.invessence.ws.provider.gemini.wsdl.account.BalanceCollectionResult balanceCollection;

    private com.invessence.ws.provider.gemini.wsdl.account.ActivityResult[] activityCollection;

    private com.invessence.ws.provider.gemini.wsdl.account.FundForRedemptionResult[] fundForRedemptionCollection;

    private com.invessence.ws.provider.gemini.wsdl.account.AccountInfoResult accountInfo;

    private com.invessence.ws.provider.gemini.wsdl.account.TaxFormResult[] taxFormCollection;

    private com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Result beneficiary529;

    private com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsResult assetAllocationModels;

    private com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus;

    public AuthorizedAccountsResult() {
    }

    public AuthorizedAccountsResult(
           java.lang.String accountNumber,
           java.lang.String dealerAccountNumber,
           java.lang.String customerName,
           java.lang.String accountName,
           java.lang.String accountTypeName,
           java.lang.String SSNOrTIN,
           org.apache.axis.types.UnsignedByte SSNOrTINIndicator,
           int groupAccountIndicator,
           org.apache.axis.types.UnsignedByte webAccessIndicator,
           org.apache.axis.types.UnsignedByte accountTaxReportingIndicator,
           java.lang.String beneficiaryName,
           java.lang.String portfolio,
           java.lang.String shareClassId,
           java.util.Calendar accountClosingDate,
           org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator,
           com.invessence.ws.provider.gemini.wsdl.account.BalanceCollectionResult balanceCollection,
           com.invessence.ws.provider.gemini.wsdl.account.ActivityResult[] activityCollection,
           com.invessence.ws.provider.gemini.wsdl.account.FundForRedemptionResult[] fundForRedemptionCollection,
           com.invessence.ws.provider.gemini.wsdl.account.AccountInfoResult accountInfo,
           com.invessence.ws.provider.gemini.wsdl.account.TaxFormResult[] taxFormCollection,
           com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Result beneficiary529,
           com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsResult assetAllocationModels,
           com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
           this.accountNumber = accountNumber;
           this.dealerAccountNumber = dealerAccountNumber;
           this.customerName = customerName;
           this.accountName = accountName;
           this.accountTypeName = accountTypeName;
           this.SSNOrTIN = SSNOrTIN;
           this.SSNOrTINIndicator = SSNOrTINIndicator;
           this.groupAccountIndicator = groupAccountIndicator;
           this.webAccessIndicator = webAccessIndicator;
           this.accountTaxReportingIndicator = accountTaxReportingIndicator;
           this.beneficiaryName = beneficiaryName;
           this.portfolio = portfolio;
           this.shareClassId = shareClassId;
           this.accountClosingDate = accountClosingDate;
           this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
           this.balanceCollection = balanceCollection;
           this.activityCollection = activityCollection;
           this.fundForRedemptionCollection = fundForRedemptionCollection;
           this.accountInfo = accountInfo;
           this.taxFormCollection = taxFormCollection;
           this.beneficiary529 = beneficiary529;
           this.assetAllocationModels = assetAllocationModels;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the accountNumber value for this AuthorizedAccountsResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AuthorizedAccountsResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the dealerAccountNumber value for this AuthorizedAccountsResult.
     * 
     * @return dealerAccountNumber
     */
    public java.lang.String getDealerAccountNumber() {
        return dealerAccountNumber;
    }


    /**
     * Sets the dealerAccountNumber value for this AuthorizedAccountsResult.
     * 
     * @param dealerAccountNumber
     */
    public void setDealerAccountNumber(java.lang.String dealerAccountNumber) {
        this.dealerAccountNumber = dealerAccountNumber;
    }


    /**
     * Gets the customerName value for this AuthorizedAccountsResult.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this AuthorizedAccountsResult.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the accountName value for this AuthorizedAccountsResult.
     * 
     * @return accountName
     */
    public java.lang.String getAccountName() {
        return accountName;
    }


    /**
     * Sets the accountName value for this AuthorizedAccountsResult.
     * 
     * @param accountName
     */
    public void setAccountName(java.lang.String accountName) {
        this.accountName = accountName;
    }


    /**
     * Gets the accountTypeName value for this AuthorizedAccountsResult.
     * 
     * @return accountTypeName
     */
    public java.lang.String getAccountTypeName() {
        return accountTypeName;
    }


    /**
     * Sets the accountTypeName value for this AuthorizedAccountsResult.
     * 
     * @param accountTypeName
     */
    public void setAccountTypeName(java.lang.String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }


    /**
     * Gets the SSNOrTIN value for this AuthorizedAccountsResult.
     * 
     * @return SSNOrTIN
     */
    public java.lang.String getSSNOrTIN() {
        return SSNOrTIN;
    }


    /**
     * Sets the SSNOrTIN value for this AuthorizedAccountsResult.
     * 
     * @param SSNOrTIN
     */
    public void setSSNOrTIN(java.lang.String SSNOrTIN) {
        this.SSNOrTIN = SSNOrTIN;
    }


    /**
     * Gets the SSNOrTINIndicator value for this AuthorizedAccountsResult.
     * 
     * @return SSNOrTINIndicator
     */
    public org.apache.axis.types.UnsignedByte getSSNOrTINIndicator() {
        return SSNOrTINIndicator;
    }


    /**
     * Sets the SSNOrTINIndicator value for this AuthorizedAccountsResult.
     * 
     * @param SSNOrTINIndicator
     */
    public void setSSNOrTINIndicator(org.apache.axis.types.UnsignedByte SSNOrTINIndicator) {
        this.SSNOrTINIndicator = SSNOrTINIndicator;
    }


    /**
     * Gets the groupAccountIndicator value for this AuthorizedAccountsResult.
     * 
     * @return groupAccountIndicator
     */
    public int getGroupAccountIndicator() {
        return groupAccountIndicator;
    }


    /**
     * Sets the groupAccountIndicator value for this AuthorizedAccountsResult.
     * 
     * @param groupAccountIndicator
     */
    public void setGroupAccountIndicator(int groupAccountIndicator) {
        this.groupAccountIndicator = groupAccountIndicator;
    }


    /**
     * Gets the webAccessIndicator value for this AuthorizedAccountsResult.
     * 
     * @return webAccessIndicator
     */
    public org.apache.axis.types.UnsignedByte getWebAccessIndicator() {
        return webAccessIndicator;
    }


    /**
     * Sets the webAccessIndicator value for this AuthorizedAccountsResult.
     * 
     * @param webAccessIndicator
     */
    public void setWebAccessIndicator(org.apache.axis.types.UnsignedByte webAccessIndicator) {
        this.webAccessIndicator = webAccessIndicator;
    }


    /**
     * Gets the accountTaxReportingIndicator value for this AuthorizedAccountsResult.
     * 
     * @return accountTaxReportingIndicator
     */
    public org.apache.axis.types.UnsignedByte getAccountTaxReportingIndicator() {
        return accountTaxReportingIndicator;
    }


    /**
     * Sets the accountTaxReportingIndicator value for this AuthorizedAccountsResult.
     * 
     * @param accountTaxReportingIndicator
     */
    public void setAccountTaxReportingIndicator(org.apache.axis.types.UnsignedByte accountTaxReportingIndicator) {
        this.accountTaxReportingIndicator = accountTaxReportingIndicator;
    }


    /**
     * Gets the beneficiaryName value for this AuthorizedAccountsResult.
     * 
     * @return beneficiaryName
     */
    public java.lang.String getBeneficiaryName() {
        return beneficiaryName;
    }


    /**
     * Sets the beneficiaryName value for this AuthorizedAccountsResult.
     * 
     * @param beneficiaryName
     */
    public void setBeneficiaryName(java.lang.String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }


    /**
     * Gets the portfolio value for this AuthorizedAccountsResult.
     * 
     * @return portfolio
     */
    public java.lang.String getPortfolio() {
        return portfolio;
    }


    /**
     * Sets the portfolio value for this AuthorizedAccountsResult.
     * 
     * @param portfolio
     */
    public void setPortfolio(java.lang.String portfolio) {
        this.portfolio = portfolio;
    }


    /**
     * Gets the shareClassId value for this AuthorizedAccountsResult.
     * 
     * @return shareClassId
     */
    public java.lang.String getShareClassId() {
        return shareClassId;
    }


    /**
     * Sets the shareClassId value for this AuthorizedAccountsResult.
     * 
     * @param shareClassId
     */
    public void setShareClassId(java.lang.String shareClassId) {
        this.shareClassId = shareClassId;
    }


    /**
     * Gets the accountClosingDate value for this AuthorizedAccountsResult.
     * 
     * @return accountClosingDate
     */
    public java.util.Calendar getAccountClosingDate() {
        return accountClosingDate;
    }


    /**
     * Sets the accountClosingDate value for this AuthorizedAccountsResult.
     * 
     * @param accountClosingDate
     */
    public void setAccountClosingDate(java.util.Calendar accountClosingDate) {
        this.accountClosingDate = accountClosingDate;
    }


    /**
     * Gets the portfolioAllocatorIndicator value for this AuthorizedAccountsResult.
     * 
     * @return portfolioAllocatorIndicator
     */
    public org.apache.axis.types.UnsignedByte getPortfolioAllocatorIndicator() {
        return portfolioAllocatorIndicator;
    }


    /**
     * Sets the portfolioAllocatorIndicator value for this AuthorizedAccountsResult.
     * 
     * @param portfolioAllocatorIndicator
     */
    public void setPortfolioAllocatorIndicator(org.apache.axis.types.UnsignedByte portfolioAllocatorIndicator) {
        this.portfolioAllocatorIndicator = portfolioAllocatorIndicator;
    }


    /**
     * Gets the balanceCollection value for this AuthorizedAccountsResult.
     * 
     * @return balanceCollection
     */
    public com.invessence.ws.provider.gemini.wsdl.account.BalanceCollectionResult getBalanceCollection() {
        return balanceCollection;
    }


    /**
     * Sets the balanceCollection value for this AuthorizedAccountsResult.
     * 
     * @param balanceCollection
     */
    public void setBalanceCollection(com.invessence.ws.provider.gemini.wsdl.account.BalanceCollectionResult balanceCollection) {
        this.balanceCollection = balanceCollection;
    }


    /**
     * Gets the activityCollection value for this AuthorizedAccountsResult.
     * 
     * @return activityCollection
     */
    public com.invessence.ws.provider.gemini.wsdl.account.ActivityResult[] getActivityCollection() {
        return activityCollection;
    }


    /**
     * Sets the activityCollection value for this AuthorizedAccountsResult.
     * 
     * @param activityCollection
     */
    public void setActivityCollection(com.invessence.ws.provider.gemini.wsdl.account.ActivityResult[] activityCollection) {
        this.activityCollection = activityCollection;
    }


    /**
     * Gets the fundForRedemptionCollection value for this AuthorizedAccountsResult.
     * 
     * @return fundForRedemptionCollection
     */
    public com.invessence.ws.provider.gemini.wsdl.account.FundForRedemptionResult[] getFundForRedemptionCollection() {
        return fundForRedemptionCollection;
    }


    /**
     * Sets the fundForRedemptionCollection value for this AuthorizedAccountsResult.
     * 
     * @param fundForRedemptionCollection
     */
    public void setFundForRedemptionCollection(com.invessence.ws.provider.gemini.wsdl.account.FundForRedemptionResult[] fundForRedemptionCollection) {
        this.fundForRedemptionCollection = fundForRedemptionCollection;
    }


    /**
     * Gets the accountInfo value for this AuthorizedAccountsResult.
     * 
     * @return accountInfo
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AccountInfoResult getAccountInfo() {
        return accountInfo;
    }


    /**
     * Sets the accountInfo value for this AuthorizedAccountsResult.
     * 
     * @param accountInfo
     */
    public void setAccountInfo(com.invessence.ws.provider.gemini.wsdl.account.AccountInfoResult accountInfo) {
        this.accountInfo = accountInfo;
    }


    /**
     * Gets the taxFormCollection value for this AuthorizedAccountsResult.
     * 
     * @return taxFormCollection
     */
    public com.invessence.ws.provider.gemini.wsdl.account.TaxFormResult[] getTaxFormCollection() {
        return taxFormCollection;
    }


    /**
     * Sets the taxFormCollection value for this AuthorizedAccountsResult.
     * 
     * @param taxFormCollection
     */
    public void setTaxFormCollection(com.invessence.ws.provider.gemini.wsdl.account.TaxFormResult[] taxFormCollection) {
        this.taxFormCollection = taxFormCollection;
    }


    /**
     * Gets the beneficiary529 value for this AuthorizedAccountsResult.
     * 
     * @return beneficiary529
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Result getBeneficiary529() {
        return beneficiary529;
    }


    /**
     * Sets the beneficiary529 value for this AuthorizedAccountsResult.
     * 
     * @param beneficiary529
     */
    public void setBeneficiary529(com.invessence.ws.provider.gemini.wsdl.account.Beneficiary529Result beneficiary529) {
        this.beneficiary529 = beneficiary529;
    }


    /**
     * Gets the assetAllocationModels value for this AuthorizedAccountsResult.
     * 
     * @return assetAllocationModels
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsResult getAssetAllocationModels() {
        return assetAllocationModels;
    }


    /**
     * Sets the assetAllocationModels value for this AuthorizedAccountsResult.
     * 
     * @param assetAllocationModels
     */
    public void setAssetAllocationModels(com.invessence.ws.provider.gemini.wsdl.account.AssetAllocationModelsResult assetAllocationModels) {
        this.assetAllocationModels = assetAllocationModels;
    }


    /**
     * Gets the errorStatus value for this AuthorizedAccountsResult.
     * 
     * @return errorStatus
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this AuthorizedAccountsResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AuthorizedAccountsResult)) return false;
        AuthorizedAccountsResult other = (AuthorizedAccountsResult) obj;
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
            ((this.dealerAccountNumber==null && other.getDealerAccountNumber()==null) || 
             (this.dealerAccountNumber!=null &&
              this.dealerAccountNumber.equals(other.getDealerAccountNumber()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.accountName==null && other.getAccountName()==null) || 
             (this.accountName!=null &&
              this.accountName.equals(other.getAccountName()))) &&
            ((this.accountTypeName==null && other.getAccountTypeName()==null) || 
             (this.accountTypeName!=null &&
              this.accountTypeName.equals(other.getAccountTypeName()))) &&
            ((this.SSNOrTIN==null && other.getSSNOrTIN()==null) || 
             (this.SSNOrTIN!=null &&
              this.SSNOrTIN.equals(other.getSSNOrTIN()))) &&
            ((this.SSNOrTINIndicator==null && other.getSSNOrTINIndicator()==null) || 
             (this.SSNOrTINIndicator!=null &&
              this.SSNOrTINIndicator.equals(other.getSSNOrTINIndicator()))) &&
            this.groupAccountIndicator == other.getGroupAccountIndicator() &&
            ((this.webAccessIndicator==null && other.getWebAccessIndicator()==null) || 
             (this.webAccessIndicator!=null &&
              this.webAccessIndicator.equals(other.getWebAccessIndicator()))) &&
            ((this.accountTaxReportingIndicator==null && other.getAccountTaxReportingIndicator()==null) || 
             (this.accountTaxReportingIndicator!=null &&
              this.accountTaxReportingIndicator.equals(other.getAccountTaxReportingIndicator()))) &&
            ((this.beneficiaryName==null && other.getBeneficiaryName()==null) || 
             (this.beneficiaryName!=null &&
              this.beneficiaryName.equals(other.getBeneficiaryName()))) &&
            ((this.portfolio==null && other.getPortfolio()==null) || 
             (this.portfolio!=null &&
              this.portfolio.equals(other.getPortfolio()))) &&
            ((this.shareClassId==null && other.getShareClassId()==null) || 
             (this.shareClassId!=null &&
              this.shareClassId.equals(other.getShareClassId()))) &&
            ((this.accountClosingDate==null && other.getAccountClosingDate()==null) || 
             (this.accountClosingDate!=null &&
              this.accountClosingDate.equals(other.getAccountClosingDate()))) &&
            ((this.portfolioAllocatorIndicator==null && other.getPortfolioAllocatorIndicator()==null) || 
             (this.portfolioAllocatorIndicator!=null &&
              this.portfolioAllocatorIndicator.equals(other.getPortfolioAllocatorIndicator()))) &&
            ((this.balanceCollection==null && other.getBalanceCollection()==null) || 
             (this.balanceCollection!=null &&
              this.balanceCollection.equals(other.getBalanceCollection()))) &&
            ((this.activityCollection==null && other.getActivityCollection()==null) || 
             (this.activityCollection!=null &&
              java.util.Arrays.equals(this.activityCollection, other.getActivityCollection()))) &&
            ((this.fundForRedemptionCollection==null && other.getFundForRedemptionCollection()==null) || 
             (this.fundForRedemptionCollection!=null &&
              java.util.Arrays.equals(this.fundForRedemptionCollection, other.getFundForRedemptionCollection()))) &&
            ((this.accountInfo==null && other.getAccountInfo()==null) || 
             (this.accountInfo!=null &&
              this.accountInfo.equals(other.getAccountInfo()))) &&
            ((this.taxFormCollection==null && other.getTaxFormCollection()==null) || 
             (this.taxFormCollection!=null &&
              java.util.Arrays.equals(this.taxFormCollection, other.getTaxFormCollection()))) &&
            ((this.beneficiary529==null && other.getBeneficiary529()==null) || 
             (this.beneficiary529!=null &&
              this.beneficiary529.equals(other.getBeneficiary529()))) &&
            ((this.assetAllocationModels==null && other.getAssetAllocationModels()==null) || 
             (this.assetAllocationModels!=null &&
              this.assetAllocationModels.equals(other.getAssetAllocationModels()))) &&
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
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getDealerAccountNumber() != null) {
            _hashCode += getDealerAccountNumber().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getAccountName() != null) {
            _hashCode += getAccountName().hashCode();
        }
        if (getAccountTypeName() != null) {
            _hashCode += getAccountTypeName().hashCode();
        }
        if (getSSNOrTIN() != null) {
            _hashCode += getSSNOrTIN().hashCode();
        }
        if (getSSNOrTINIndicator() != null) {
            _hashCode += getSSNOrTINIndicator().hashCode();
        }
        _hashCode += getGroupAccountIndicator();
        if (getWebAccessIndicator() != null) {
            _hashCode += getWebAccessIndicator().hashCode();
        }
        if (getAccountTaxReportingIndicator() != null) {
            _hashCode += getAccountTaxReportingIndicator().hashCode();
        }
        if (getBeneficiaryName() != null) {
            _hashCode += getBeneficiaryName().hashCode();
        }
        if (getPortfolio() != null) {
            _hashCode += getPortfolio().hashCode();
        }
        if (getShareClassId() != null) {
            _hashCode += getShareClassId().hashCode();
        }
        if (getAccountClosingDate() != null) {
            _hashCode += getAccountClosingDate().hashCode();
        }
        if (getPortfolioAllocatorIndicator() != null) {
            _hashCode += getPortfolioAllocatorIndicator().hashCode();
        }
        if (getBalanceCollection() != null) {
            _hashCode += getBalanceCollection().hashCode();
        }
        if (getActivityCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFundForRedemptionCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFundForRedemptionCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFundForRedemptionCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAccountInfo() != null) {
            _hashCode += getAccountInfo().hashCode();
        }
        if (getTaxFormCollection() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxFormCollection());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxFormCollection(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBeneficiary529() != null) {
            _hashCode += getBeneficiary529().hashCode();
        }
        if (getAssetAllocationModels() != null) {
            _hashCode += getAssetAllocationModels().hashCode();
        }
        if (getErrorStatus() != null) {
            _hashCode += getErrorStatus().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AuthorizedAccountsResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AuthorizedAccountsResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealerAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DealerAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountTypeName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountTypeName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SSNOrTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SSNOrTIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SSNOrTINIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SSNOrTINIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groupAccountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GroupAccountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webAccessIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebAccessIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountTaxReportingIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountTaxReportingIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiaryName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeneficiaryName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Portfolio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareClassId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareClassId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountClosingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountClosingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("portfolioAllocatorIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PortfolioAllocatorIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balanceCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceCollectionResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundForRedemptionCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundForRedemptionResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountInfoResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxFormCollection");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormCollection"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TaxFormResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beneficiary529");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Beneficiary529Result"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetAllocationModels");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModels"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationModelsResult"));
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
