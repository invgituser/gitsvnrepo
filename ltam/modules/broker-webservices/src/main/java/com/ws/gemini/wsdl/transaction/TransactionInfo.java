/**
 * TransactionInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.transaction;

public class TransactionInfo  implements java.io.Serializable {
    private org.apache.axis.types.UnsignedByte masterTransactionType;

    private java.lang.String accountNumber;

    private org.apache.axis.types.UnsignedByte moneyTransactionType;

    private org.apache.axis.types.UnsignedByte masterTransactionSource;

    private java.math.BigDecimal moneyAmount;

    private org.apache.axis.types.UnsignedByte currencyId;

    private org.apache.axis.types.UnsignedByte retirementIndicator;

    private org.apache.axis.types.UnsignedByte dealerTransactionIndicator;

    private int accountPayeeId;

    private org.apache.axis.types.UnsignedByte assetAllocationTradeIndicator;

    private org.apache.axis.types.UnsignedByte endResultExchangeInd;

    private java.lang.String bankAccountNumber;

    private java.lang.String excludeAccountsList;

    private org.apache.axis.types.UnsignedByte returnReadBackInfo;

    private org.apache.axis.types.UnsignedByte returnMessagesInfo;

    private org.apache.axis.types.UnsignedByte numberOfMasterTransactionLines;

    private short userId;

    private java.util.Calendar tradeDate;

    private short addToExcludedAccounts;

    private org.apache.axis.types.UnsignedByte priceCycleId;

    private java.lang.String nameOnAccount;

    private java.lang.String bankName;

    private java.lang.String bankRoutingNumber;

    private java.lang.String bankAccount;

    private org.apache.axis.types.UnsignedByte bankAccountType;

    private boolean isAmountSpecified;

    private java.lang.String nameLines;

    private java.lang.String addressLines;

    private org.apache.axis.types.UnsignedByte updateAgeBasedModelIdInd;

    private org.apache.axis.types.UnsignedByte investmentOption;

    private short ageBasedModelId;

    private short amountTypeIndicator;

    private int giftReferenceId;

    private int autoDetermineFunds;

    private java.lang.String webUserId;

    private org.apache.axis.types.UnsignedByte withholdingIndicator;

    private org.apache.axis.types.UnsignedByte withholdingPercentOrAmount;

    private java.math.BigDecimal withholdingAmount;

    private java.math.BigDecimal withholdingPercent;

    public TransactionInfo() {
    }

    public TransactionInfo(
           org.apache.axis.types.UnsignedByte masterTransactionType,
           java.lang.String accountNumber,
           org.apache.axis.types.UnsignedByte moneyTransactionType,
           org.apache.axis.types.UnsignedByte masterTransactionSource,
           java.math.BigDecimal moneyAmount,
           org.apache.axis.types.UnsignedByte currencyId,
           org.apache.axis.types.UnsignedByte retirementIndicator,
           org.apache.axis.types.UnsignedByte dealerTransactionIndicator,
           int accountPayeeId,
           org.apache.axis.types.UnsignedByte assetAllocationTradeIndicator,
           org.apache.axis.types.UnsignedByte endResultExchangeInd,
           java.lang.String bankAccountNumber,
           java.lang.String excludeAccountsList,
           org.apache.axis.types.UnsignedByte returnReadBackInfo,
           org.apache.axis.types.UnsignedByte returnMessagesInfo,
           org.apache.axis.types.UnsignedByte numberOfMasterTransactionLines,
           short userId,
           java.util.Calendar tradeDate,
           short addToExcludedAccounts,
           org.apache.axis.types.UnsignedByte priceCycleId,
           java.lang.String nameOnAccount,
           java.lang.String bankName,
           java.lang.String bankRoutingNumber,
           java.lang.String bankAccount,
           org.apache.axis.types.UnsignedByte bankAccountType,
           boolean isAmountSpecified,
           java.lang.String nameLines,
           java.lang.String addressLines,
           org.apache.axis.types.UnsignedByte updateAgeBasedModelIdInd,
           org.apache.axis.types.UnsignedByte investmentOption,
           short ageBasedModelId,
           short amountTypeIndicator,
           int giftReferenceId,
           int autoDetermineFunds,
           java.lang.String webUserId,
           org.apache.axis.types.UnsignedByte withholdingIndicator,
           org.apache.axis.types.UnsignedByte withholdingPercentOrAmount,
           java.math.BigDecimal withholdingAmount,
           java.math.BigDecimal withholdingPercent) {
           this.masterTransactionType = masterTransactionType;
           this.accountNumber = accountNumber;
           this.moneyTransactionType = moneyTransactionType;
           this.masterTransactionSource = masterTransactionSource;
           this.moneyAmount = moneyAmount;
           this.currencyId = currencyId;
           this.retirementIndicator = retirementIndicator;
           this.dealerTransactionIndicator = dealerTransactionIndicator;
           this.accountPayeeId = accountPayeeId;
           this.assetAllocationTradeIndicator = assetAllocationTradeIndicator;
           this.endResultExchangeInd = endResultExchangeInd;
           this.bankAccountNumber = bankAccountNumber;
           this.excludeAccountsList = excludeAccountsList;
           this.returnReadBackInfo = returnReadBackInfo;
           this.returnMessagesInfo = returnMessagesInfo;
           this.numberOfMasterTransactionLines = numberOfMasterTransactionLines;
           this.userId = userId;
           this.tradeDate = tradeDate;
           this.addToExcludedAccounts = addToExcludedAccounts;
           this.priceCycleId = priceCycleId;
           this.nameOnAccount = nameOnAccount;
           this.bankName = bankName;
           this.bankRoutingNumber = bankRoutingNumber;
           this.bankAccount = bankAccount;
           this.bankAccountType = bankAccountType;
           this.isAmountSpecified = isAmountSpecified;
           this.nameLines = nameLines;
           this.addressLines = addressLines;
           this.updateAgeBasedModelIdInd = updateAgeBasedModelIdInd;
           this.investmentOption = investmentOption;
           this.ageBasedModelId = ageBasedModelId;
           this.amountTypeIndicator = amountTypeIndicator;
           this.giftReferenceId = giftReferenceId;
           this.autoDetermineFunds = autoDetermineFunds;
           this.webUserId = webUserId;
           this.withholdingIndicator = withholdingIndicator;
           this.withholdingPercentOrAmount = withholdingPercentOrAmount;
           this.withholdingAmount = withholdingAmount;
           this.withholdingPercent = withholdingPercent;
    }


    /**
     * Gets the masterTransactionType value for this TransactionInfo.
     * 
     * @return masterTransactionType
     */
    public org.apache.axis.types.UnsignedByte getMasterTransactionType() {
        return masterTransactionType;
    }


    /**
     * Sets the masterTransactionType value for this TransactionInfo.
     * 
     * @param masterTransactionType
     */
    public void setMasterTransactionType(org.apache.axis.types.UnsignedByte masterTransactionType) {
        this.masterTransactionType = masterTransactionType;
    }


    /**
     * Gets the accountNumber value for this TransactionInfo.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this TransactionInfo.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the moneyTransactionType value for this TransactionInfo.
     * 
     * @return moneyTransactionType
     */
    public org.apache.axis.types.UnsignedByte getMoneyTransactionType() {
        return moneyTransactionType;
    }


    /**
     * Sets the moneyTransactionType value for this TransactionInfo.
     * 
     * @param moneyTransactionType
     */
    public void setMoneyTransactionType(org.apache.axis.types.UnsignedByte moneyTransactionType) {
        this.moneyTransactionType = moneyTransactionType;
    }


    /**
     * Gets the masterTransactionSource value for this TransactionInfo.
     * 
     * @return masterTransactionSource
     */
    public org.apache.axis.types.UnsignedByte getMasterTransactionSource() {
        return masterTransactionSource;
    }


    /**
     * Sets the masterTransactionSource value for this TransactionInfo.
     * 
     * @param masterTransactionSource
     */
    public void setMasterTransactionSource(org.apache.axis.types.UnsignedByte masterTransactionSource) {
        this.masterTransactionSource = masterTransactionSource;
    }


    /**
     * Gets the moneyAmount value for this TransactionInfo.
     * 
     * @return moneyAmount
     */
    public java.math.BigDecimal getMoneyAmount() {
        return moneyAmount;
    }


    /**
     * Sets the moneyAmount value for this TransactionInfo.
     * 
     * @param moneyAmount
     */
    public void setMoneyAmount(java.math.BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }


    /**
     * Gets the currencyId value for this TransactionInfo.
     * 
     * @return currencyId
     */
    public org.apache.axis.types.UnsignedByte getCurrencyId() {
        return currencyId;
    }


    /**
     * Sets the currencyId value for this TransactionInfo.
     * 
     * @param currencyId
     */
    public void setCurrencyId(org.apache.axis.types.UnsignedByte currencyId) {
        this.currencyId = currencyId;
    }


    /**
     * Gets the retirementIndicator value for this TransactionInfo.
     * 
     * @return retirementIndicator
     */
    public org.apache.axis.types.UnsignedByte getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this TransactionInfo.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(org.apache.axis.types.UnsignedByte retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the dealerTransactionIndicator value for this TransactionInfo.
     * 
     * @return dealerTransactionIndicator
     */
    public org.apache.axis.types.UnsignedByte getDealerTransactionIndicator() {
        return dealerTransactionIndicator;
    }


    /**
     * Sets the dealerTransactionIndicator value for this TransactionInfo.
     * 
     * @param dealerTransactionIndicator
     */
    public void setDealerTransactionIndicator(org.apache.axis.types.UnsignedByte dealerTransactionIndicator) {
        this.dealerTransactionIndicator = dealerTransactionIndicator;
    }


    /**
     * Gets the accountPayeeId value for this TransactionInfo.
     * 
     * @return accountPayeeId
     */
    public int getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this TransactionInfo.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(int accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the assetAllocationTradeIndicator value for this TransactionInfo.
     * 
     * @return assetAllocationTradeIndicator
     */
    public org.apache.axis.types.UnsignedByte getAssetAllocationTradeIndicator() {
        return assetAllocationTradeIndicator;
    }


    /**
     * Sets the assetAllocationTradeIndicator value for this TransactionInfo.
     * 
     * @param assetAllocationTradeIndicator
     */
    public void setAssetAllocationTradeIndicator(org.apache.axis.types.UnsignedByte assetAllocationTradeIndicator) {
        this.assetAllocationTradeIndicator = assetAllocationTradeIndicator;
    }


    /**
     * Gets the endResultExchangeInd value for this TransactionInfo.
     * 
     * @return endResultExchangeInd
     */
    public org.apache.axis.types.UnsignedByte getEndResultExchangeInd() {
        return endResultExchangeInd;
    }


    /**
     * Sets the endResultExchangeInd value for this TransactionInfo.
     * 
     * @param endResultExchangeInd
     */
    public void setEndResultExchangeInd(org.apache.axis.types.UnsignedByte endResultExchangeInd) {
        this.endResultExchangeInd = endResultExchangeInd;
    }


    /**
     * Gets the bankAccountNumber value for this TransactionInfo.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this TransactionInfo.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the excludeAccountsList value for this TransactionInfo.
     * 
     * @return excludeAccountsList
     */
    public java.lang.String getExcludeAccountsList() {
        return excludeAccountsList;
    }


    /**
     * Sets the excludeAccountsList value for this TransactionInfo.
     * 
     * @param excludeAccountsList
     */
    public void setExcludeAccountsList(java.lang.String excludeAccountsList) {
        this.excludeAccountsList = excludeAccountsList;
    }


    /**
     * Gets the returnReadBackInfo value for this TransactionInfo.
     * 
     * @return returnReadBackInfo
     */
    public org.apache.axis.types.UnsignedByte getReturnReadBackInfo() {
        return returnReadBackInfo;
    }


    /**
     * Sets the returnReadBackInfo value for this TransactionInfo.
     * 
     * @param returnReadBackInfo
     */
    public void setReturnReadBackInfo(org.apache.axis.types.UnsignedByte returnReadBackInfo) {
        this.returnReadBackInfo = returnReadBackInfo;
    }


    /**
     * Gets the returnMessagesInfo value for this TransactionInfo.
     * 
     * @return returnMessagesInfo
     */
    public org.apache.axis.types.UnsignedByte getReturnMessagesInfo() {
        return returnMessagesInfo;
    }


    /**
     * Sets the returnMessagesInfo value for this TransactionInfo.
     * 
     * @param returnMessagesInfo
     */
    public void setReturnMessagesInfo(org.apache.axis.types.UnsignedByte returnMessagesInfo) {
        this.returnMessagesInfo = returnMessagesInfo;
    }


    /**
     * Gets the numberOfMasterTransactionLines value for this TransactionInfo.
     * 
     * @return numberOfMasterTransactionLines
     */
    public org.apache.axis.types.UnsignedByte getNumberOfMasterTransactionLines() {
        return numberOfMasterTransactionLines;
    }


    /**
     * Sets the numberOfMasterTransactionLines value for this TransactionInfo.
     * 
     * @param numberOfMasterTransactionLines
     */
    public void setNumberOfMasterTransactionLines(org.apache.axis.types.UnsignedByte numberOfMasterTransactionLines) {
        this.numberOfMasterTransactionLines = numberOfMasterTransactionLines;
    }


    /**
     * Gets the userId value for this TransactionInfo.
     * 
     * @return userId
     */
    public short getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this TransactionInfo.
     * 
     * @param userId
     */
    public void setUserId(short userId) {
        this.userId = userId;
    }


    /**
     * Gets the tradeDate value for this TransactionInfo.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this TransactionInfo.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the addToExcludedAccounts value for this TransactionInfo.
     * 
     * @return addToExcludedAccounts
     */
    public short getAddToExcludedAccounts() {
        return addToExcludedAccounts;
    }


    /**
     * Sets the addToExcludedAccounts value for this TransactionInfo.
     * 
     * @param addToExcludedAccounts
     */
    public void setAddToExcludedAccounts(short addToExcludedAccounts) {
        this.addToExcludedAccounts = addToExcludedAccounts;
    }


    /**
     * Gets the priceCycleId value for this TransactionInfo.
     * 
     * @return priceCycleId
     */
    public org.apache.axis.types.UnsignedByte getPriceCycleId() {
        return priceCycleId;
    }


    /**
     * Sets the priceCycleId value for this TransactionInfo.
     * 
     * @param priceCycleId
     */
    public void setPriceCycleId(org.apache.axis.types.UnsignedByte priceCycleId) {
        this.priceCycleId = priceCycleId;
    }


    /**
     * Gets the nameOnAccount value for this TransactionInfo.
     * 
     * @return nameOnAccount
     */
    public java.lang.String getNameOnAccount() {
        return nameOnAccount;
    }


    /**
     * Sets the nameOnAccount value for this TransactionInfo.
     * 
     * @param nameOnAccount
     */
    public void setNameOnAccount(java.lang.String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }


    /**
     * Gets the bankName value for this TransactionInfo.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this TransactionInfo.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the bankRoutingNumber value for this TransactionInfo.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this TransactionInfo.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the bankAccount value for this TransactionInfo.
     * 
     * @return bankAccount
     */
    public java.lang.String getBankAccount() {
        return bankAccount;
    }


    /**
     * Sets the bankAccount value for this TransactionInfo.
     * 
     * @param bankAccount
     */
    public void setBankAccount(java.lang.String bankAccount) {
        this.bankAccount = bankAccount;
    }


    /**
     * Gets the bankAccountType value for this TransactionInfo.
     * 
     * @return bankAccountType
     */
    public org.apache.axis.types.UnsignedByte getBankAccountType() {
        return bankAccountType;
    }


    /**
     * Sets the bankAccountType value for this TransactionInfo.
     * 
     * @param bankAccountType
     */
    public void setBankAccountType(org.apache.axis.types.UnsignedByte bankAccountType) {
        this.bankAccountType = bankAccountType;
    }


    /**
     * Gets the isAmountSpecified value for this TransactionInfo.
     * 
     * @return isAmountSpecified
     */
    public boolean isIsAmountSpecified() {
        return isAmountSpecified;
    }


    /**
     * Sets the isAmountSpecified value for this TransactionInfo.
     * 
     * @param isAmountSpecified
     */
    public void setIsAmountSpecified(boolean isAmountSpecified) {
        this.isAmountSpecified = isAmountSpecified;
    }


    /**
     * Gets the nameLines value for this TransactionInfo.
     * 
     * @return nameLines
     */
    public java.lang.String getNameLines() {
        return nameLines;
    }


    /**
     * Sets the nameLines value for this TransactionInfo.
     * 
     * @param nameLines
     */
    public void setNameLines(java.lang.String nameLines) {
        this.nameLines = nameLines;
    }


    /**
     * Gets the addressLines value for this TransactionInfo.
     * 
     * @return addressLines
     */
    public java.lang.String getAddressLines() {
        return addressLines;
    }


    /**
     * Sets the addressLines value for this TransactionInfo.
     * 
     * @param addressLines
     */
    public void setAddressLines(java.lang.String addressLines) {
        this.addressLines = addressLines;
    }


    /**
     * Gets the updateAgeBasedModelIdInd value for this TransactionInfo.
     * 
     * @return updateAgeBasedModelIdInd
     */
    public org.apache.axis.types.UnsignedByte getUpdateAgeBasedModelIdInd() {
        return updateAgeBasedModelIdInd;
    }


    /**
     * Sets the updateAgeBasedModelIdInd value for this TransactionInfo.
     * 
     * @param updateAgeBasedModelIdInd
     */
    public void setUpdateAgeBasedModelIdInd(org.apache.axis.types.UnsignedByte updateAgeBasedModelIdInd) {
        this.updateAgeBasedModelIdInd = updateAgeBasedModelIdInd;
    }


    /**
     * Gets the investmentOption value for this TransactionInfo.
     * 
     * @return investmentOption
     */
    public org.apache.axis.types.UnsignedByte getInvestmentOption() {
        return investmentOption;
    }


    /**
     * Sets the investmentOption value for this TransactionInfo.
     * 
     * @param investmentOption
     */
    public void setInvestmentOption(org.apache.axis.types.UnsignedByte investmentOption) {
        this.investmentOption = investmentOption;
    }


    /**
     * Gets the ageBasedModelId value for this TransactionInfo.
     * 
     * @return ageBasedModelId
     */
    public short getAgeBasedModelId() {
        return ageBasedModelId;
    }


    /**
     * Sets the ageBasedModelId value for this TransactionInfo.
     * 
     * @param ageBasedModelId
     */
    public void setAgeBasedModelId(short ageBasedModelId) {
        this.ageBasedModelId = ageBasedModelId;
    }


    /**
     * Gets the amountTypeIndicator value for this TransactionInfo.
     * 
     * @return amountTypeIndicator
     */
    public short getAmountTypeIndicator() {
        return amountTypeIndicator;
    }


    /**
     * Sets the amountTypeIndicator value for this TransactionInfo.
     * 
     * @param amountTypeIndicator
     */
    public void setAmountTypeIndicator(short amountTypeIndicator) {
        this.amountTypeIndicator = amountTypeIndicator;
    }


    /**
     * Gets the giftReferenceId value for this TransactionInfo.
     * 
     * @return giftReferenceId
     */
    public int getGiftReferenceId() {
        return giftReferenceId;
    }


    /**
     * Sets the giftReferenceId value for this TransactionInfo.
     * 
     * @param giftReferenceId
     */
    public void setGiftReferenceId(int giftReferenceId) {
        this.giftReferenceId = giftReferenceId;
    }


    /**
     * Gets the autoDetermineFunds value for this TransactionInfo.
     * 
     * @return autoDetermineFunds
     */
    public int getAutoDetermineFunds() {
        return autoDetermineFunds;
    }


    /**
     * Sets the autoDetermineFunds value for this TransactionInfo.
     * 
     * @param autoDetermineFunds
     */
    public void setAutoDetermineFunds(int autoDetermineFunds) {
        this.autoDetermineFunds = autoDetermineFunds;
    }


    /**
     * Gets the webUserId value for this TransactionInfo.
     * 
     * @return webUserId
     */
    public java.lang.String getWebUserId() {
        return webUserId;
    }


    /**
     * Sets the webUserId value for this TransactionInfo.
     * 
     * @param webUserId
     */
    public void setWebUserId(java.lang.String webUserId) {
        this.webUserId = webUserId;
    }


    /**
     * Gets the withholdingIndicator value for this TransactionInfo.
     * 
     * @return withholdingIndicator
     */
    public org.apache.axis.types.UnsignedByte getWithholdingIndicator() {
        return withholdingIndicator;
    }


    /**
     * Sets the withholdingIndicator value for this TransactionInfo.
     * 
     * @param withholdingIndicator
     */
    public void setWithholdingIndicator(org.apache.axis.types.UnsignedByte withholdingIndicator) {
        this.withholdingIndicator = withholdingIndicator;
    }


    /**
     * Gets the withholdingPercentOrAmount value for this TransactionInfo.
     * 
     * @return withholdingPercentOrAmount
     */
    public org.apache.axis.types.UnsignedByte getWithholdingPercentOrAmount() {
        return withholdingPercentOrAmount;
    }


    /**
     * Sets the withholdingPercentOrAmount value for this TransactionInfo.
     * 
     * @param withholdingPercentOrAmount
     */
    public void setWithholdingPercentOrAmount(org.apache.axis.types.UnsignedByte withholdingPercentOrAmount) {
        this.withholdingPercentOrAmount = withholdingPercentOrAmount;
    }


    /**
     * Gets the withholdingAmount value for this TransactionInfo.
     * 
     * @return withholdingAmount
     */
    public java.math.BigDecimal getWithholdingAmount() {
        return withholdingAmount;
    }


    /**
     * Sets the withholdingAmount value for this TransactionInfo.
     * 
     * @param withholdingAmount
     */
    public void setWithholdingAmount(java.math.BigDecimal withholdingAmount) {
        this.withholdingAmount = withholdingAmount;
    }


    /**
     * Gets the withholdingPercent value for this TransactionInfo.
     * 
     * @return withholdingPercent
     */
    public java.math.BigDecimal getWithholdingPercent() {
        return withholdingPercent;
    }


    /**
     * Sets the withholdingPercent value for this TransactionInfo.
     * 
     * @param withholdingPercent
     */
    public void setWithholdingPercent(java.math.BigDecimal withholdingPercent) {
        this.withholdingPercent = withholdingPercent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TransactionInfo)) return false;
        TransactionInfo other = (TransactionInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.masterTransactionType==null && other.getMasterTransactionType()==null) || 
             (this.masterTransactionType!=null &&
              this.masterTransactionType.equals(other.getMasterTransactionType()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.moneyTransactionType==null && other.getMoneyTransactionType()==null) || 
             (this.moneyTransactionType!=null &&
              this.moneyTransactionType.equals(other.getMoneyTransactionType()))) &&
            ((this.masterTransactionSource==null && other.getMasterTransactionSource()==null) || 
             (this.masterTransactionSource!=null &&
              this.masterTransactionSource.equals(other.getMasterTransactionSource()))) &&
            ((this.moneyAmount==null && other.getMoneyAmount()==null) || 
             (this.moneyAmount!=null &&
              this.moneyAmount.equals(other.getMoneyAmount()))) &&
            ((this.currencyId==null && other.getCurrencyId()==null) || 
             (this.currencyId!=null &&
              this.currencyId.equals(other.getCurrencyId()))) &&
            ((this.retirementIndicator==null && other.getRetirementIndicator()==null) || 
             (this.retirementIndicator!=null &&
              this.retirementIndicator.equals(other.getRetirementIndicator()))) &&
            ((this.dealerTransactionIndicator==null && other.getDealerTransactionIndicator()==null) || 
             (this.dealerTransactionIndicator!=null &&
              this.dealerTransactionIndicator.equals(other.getDealerTransactionIndicator()))) &&
            this.accountPayeeId == other.getAccountPayeeId() &&
            ((this.assetAllocationTradeIndicator==null && other.getAssetAllocationTradeIndicator()==null) || 
             (this.assetAllocationTradeIndicator!=null &&
              this.assetAllocationTradeIndicator.equals(other.getAssetAllocationTradeIndicator()))) &&
            ((this.endResultExchangeInd==null && other.getEndResultExchangeInd()==null) || 
             (this.endResultExchangeInd!=null &&
              this.endResultExchangeInd.equals(other.getEndResultExchangeInd()))) &&
            ((this.bankAccountNumber==null && other.getBankAccountNumber()==null) || 
             (this.bankAccountNumber!=null &&
              this.bankAccountNumber.equals(other.getBankAccountNumber()))) &&
            ((this.excludeAccountsList==null && other.getExcludeAccountsList()==null) || 
             (this.excludeAccountsList!=null &&
              this.excludeAccountsList.equals(other.getExcludeAccountsList()))) &&
            ((this.returnReadBackInfo==null && other.getReturnReadBackInfo()==null) || 
             (this.returnReadBackInfo!=null &&
              this.returnReadBackInfo.equals(other.getReturnReadBackInfo()))) &&
            ((this.returnMessagesInfo==null && other.getReturnMessagesInfo()==null) || 
             (this.returnMessagesInfo!=null &&
              this.returnMessagesInfo.equals(other.getReturnMessagesInfo()))) &&
            ((this.numberOfMasterTransactionLines==null && other.getNumberOfMasterTransactionLines()==null) || 
             (this.numberOfMasterTransactionLines!=null &&
              this.numberOfMasterTransactionLines.equals(other.getNumberOfMasterTransactionLines()))) &&
            this.userId == other.getUserId() &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            this.addToExcludedAccounts == other.getAddToExcludedAccounts() &&
            ((this.priceCycleId==null && other.getPriceCycleId()==null) || 
             (this.priceCycleId!=null &&
              this.priceCycleId.equals(other.getPriceCycleId()))) &&
            ((this.nameOnAccount==null && other.getNameOnAccount()==null) || 
             (this.nameOnAccount!=null &&
              this.nameOnAccount.equals(other.getNameOnAccount()))) &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.bankRoutingNumber==null && other.getBankRoutingNumber()==null) || 
             (this.bankRoutingNumber!=null &&
              this.bankRoutingNumber.equals(other.getBankRoutingNumber()))) &&
            ((this.bankAccount==null && other.getBankAccount()==null) || 
             (this.bankAccount!=null &&
              this.bankAccount.equals(other.getBankAccount()))) &&
            ((this.bankAccountType==null && other.getBankAccountType()==null) || 
             (this.bankAccountType!=null &&
              this.bankAccountType.equals(other.getBankAccountType()))) &&
            this.isAmountSpecified == other.isIsAmountSpecified() &&
            ((this.nameLines==null && other.getNameLines()==null) || 
             (this.nameLines!=null &&
              this.nameLines.equals(other.getNameLines()))) &&
            ((this.addressLines==null && other.getAddressLines()==null) || 
             (this.addressLines!=null &&
              this.addressLines.equals(other.getAddressLines()))) &&
            ((this.updateAgeBasedModelIdInd==null && other.getUpdateAgeBasedModelIdInd()==null) || 
             (this.updateAgeBasedModelIdInd!=null &&
              this.updateAgeBasedModelIdInd.equals(other.getUpdateAgeBasedModelIdInd()))) &&
            ((this.investmentOption==null && other.getInvestmentOption()==null) || 
             (this.investmentOption!=null &&
              this.investmentOption.equals(other.getInvestmentOption()))) &&
            this.ageBasedModelId == other.getAgeBasedModelId() &&
            this.amountTypeIndicator == other.getAmountTypeIndicator() &&
            this.giftReferenceId == other.getGiftReferenceId() &&
            this.autoDetermineFunds == other.getAutoDetermineFunds() &&
            ((this.webUserId==null && other.getWebUserId()==null) || 
             (this.webUserId!=null &&
              this.webUserId.equals(other.getWebUserId()))) &&
            ((this.withholdingIndicator==null && other.getWithholdingIndicator()==null) || 
             (this.withholdingIndicator!=null &&
              this.withholdingIndicator.equals(other.getWithholdingIndicator()))) &&
            ((this.withholdingPercentOrAmount==null && other.getWithholdingPercentOrAmount()==null) || 
             (this.withholdingPercentOrAmount!=null &&
              this.withholdingPercentOrAmount.equals(other.getWithholdingPercentOrAmount()))) &&
            ((this.withholdingAmount==null && other.getWithholdingAmount()==null) || 
             (this.withholdingAmount!=null &&
              this.withholdingAmount.equals(other.getWithholdingAmount()))) &&
            ((this.withholdingPercent==null && other.getWithholdingPercent()==null) || 
             (this.withholdingPercent!=null &&
              this.withholdingPercent.equals(other.getWithholdingPercent())));
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
        if (getMasterTransactionType() != null) {
            _hashCode += getMasterTransactionType().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getMoneyTransactionType() != null) {
            _hashCode += getMoneyTransactionType().hashCode();
        }
        if (getMasterTransactionSource() != null) {
            _hashCode += getMasterTransactionSource().hashCode();
        }
        if (getMoneyAmount() != null) {
            _hashCode += getMoneyAmount().hashCode();
        }
        if (getCurrencyId() != null) {
            _hashCode += getCurrencyId().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        if (getDealerTransactionIndicator() != null) {
            _hashCode += getDealerTransactionIndicator().hashCode();
        }
        _hashCode += getAccountPayeeId();
        if (getAssetAllocationTradeIndicator() != null) {
            _hashCode += getAssetAllocationTradeIndicator().hashCode();
        }
        if (getEndResultExchangeInd() != null) {
            _hashCode += getEndResultExchangeInd().hashCode();
        }
        if (getBankAccountNumber() != null) {
            _hashCode += getBankAccountNumber().hashCode();
        }
        if (getExcludeAccountsList() != null) {
            _hashCode += getExcludeAccountsList().hashCode();
        }
        if (getReturnReadBackInfo() != null) {
            _hashCode += getReturnReadBackInfo().hashCode();
        }
        if (getReturnMessagesInfo() != null) {
            _hashCode += getReturnMessagesInfo().hashCode();
        }
        if (getNumberOfMasterTransactionLines() != null) {
            _hashCode += getNumberOfMasterTransactionLines().hashCode();
        }
        _hashCode += getUserId();
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        _hashCode += getAddToExcludedAccounts();
        if (getPriceCycleId() != null) {
            _hashCode += getPriceCycleId().hashCode();
        }
        if (getNameOnAccount() != null) {
            _hashCode += getNameOnAccount().hashCode();
        }
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getBankRoutingNumber() != null) {
            _hashCode += getBankRoutingNumber().hashCode();
        }
        if (getBankAccount() != null) {
            _hashCode += getBankAccount().hashCode();
        }
        if (getBankAccountType() != null) {
            _hashCode += getBankAccountType().hashCode();
        }
        _hashCode += (isIsAmountSpecified() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getNameLines() != null) {
            _hashCode += getNameLines().hashCode();
        }
        if (getAddressLines() != null) {
            _hashCode += getAddressLines().hashCode();
        }
        if (getUpdateAgeBasedModelIdInd() != null) {
            _hashCode += getUpdateAgeBasedModelIdInd().hashCode();
        }
        if (getInvestmentOption() != null) {
            _hashCode += getInvestmentOption().hashCode();
        }
        _hashCode += getAgeBasedModelId();
        _hashCode += getAmountTypeIndicator();
        _hashCode += getGiftReferenceId();
        _hashCode += getAutoDetermineFunds();
        if (getWebUserId() != null) {
            _hashCode += getWebUserId().hashCode();
        }
        if (getWithholdingIndicator() != null) {
            _hashCode += getWithholdingIndicator().hashCode();
        }
        if (getWithholdingPercentOrAmount() != null) {
            _hashCode += getWithholdingPercentOrAmount().hashCode();
        }
        if (getWithholdingAmount() != null) {
            _hashCode += getWithholdingAmount().hashCode();
        }
        if (getWithholdingPercent() != null) {
            _hashCode += getWithholdingPercent().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TransactionInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
        elemField.setFieldName("moneyTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MoneyTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("masterTransactionSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MasterTransactionSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moneyAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MoneyAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrencyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retirementIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RetirementIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealerTransactionIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DealerTransactionIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetAllocationTradeIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AssetAllocationTradeIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endResultExchangeInd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EndResultExchangeInd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeAccountsList");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExcludeAccountsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnReadBackInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReturnReadBackInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnMessagesInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReturnMessagesInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberOfMasterTransactionLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NumberOfMasterTransactionLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addToExcludedAccounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AddToExcludedAccounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCycleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriceCycleId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameOnAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameOnAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankRoutingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankRoutingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankAccountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BankAccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isAmountSpecified");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "IsAmountSpecified"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nameLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addressLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AddressLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateAgeBasedModelIdInd");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UpdateAgeBasedModelIdInd"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("investmentOption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InvestmentOption"));
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
        elemField.setFieldName("amountTypeIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AmountTypeIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("giftReferenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GiftReferenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoDetermineFunds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutoDetermineFunds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("webUserId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WebUserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withholdingIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WithholdingIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withholdingPercentOrAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WithholdingPercentOrAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withholdingAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WithholdingAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withholdingPercent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WithholdingPercent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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

    @Override
    public String toString()
    {
        return "TransactionInfo{" +
           "masterTransactionType=" + masterTransactionType +
           ", accountNumber='" + accountNumber + '\'' +
           ", moneyTransactionType=" + moneyTransactionType +
           ", masterTransactionSource=" + masterTransactionSource +
           ", moneyAmount=" + moneyAmount +
           ", currencyId=" + currencyId +
           ", retirementIndicator=" + retirementIndicator +
           ", dealerTransactionIndicator=" + dealerTransactionIndicator +
           ", accountPayeeId=" + accountPayeeId +
           ", assetAllocationTradeIndicator=" + assetAllocationTradeIndicator +
           ", endResultExchangeInd=" + endResultExchangeInd +
           ", bankAccountNumber='" + bankAccountNumber + '\'' +
           ", excludeAccountsList='" + excludeAccountsList + '\'' +
           ", returnReadBackInfo=" + returnReadBackInfo +
           ", returnMessagesInfo=" + returnMessagesInfo +
           ", numberOfMasterTransactionLines=" + numberOfMasterTransactionLines +
           ", userId=" + userId +
           ", tradeDate=" + tradeDate +
           ", addToExcludedAccounts=" + addToExcludedAccounts +
           ", priceCycleId=" + priceCycleId +
           ", nameOnAccount='" + nameOnAccount + '\'' +
           ", bankName='" + bankName + '\'' +
           ", bankRoutingNumber='" + bankRoutingNumber + '\'' +
           ", bankAccount='" + bankAccount + '\'' +
           ", bankAccountType=" + bankAccountType +
           ", isAmountSpecified=" + isAmountSpecified +
           ", nameLines='" + nameLines + '\'' +
           ", addressLines='" + addressLines + '\'' +
           ", updateAgeBasedModelIdInd=" + updateAgeBasedModelIdInd +
           ", investmentOption=" + investmentOption +
           ", ageBasedModelId=" + ageBasedModelId +
           ", amountTypeIndicator=" + amountTypeIndicator +
           ", giftReferenceId=" + giftReferenceId +
           ", autoDetermineFunds=" + autoDetermineFunds +
           ", webUserId='" + webUserId + '\'' +
           ", withholdingIndicator=" + withholdingIndicator +
           ", withholdingPercentOrAmount=" + withholdingPercentOrAmount +
           ", withholdingAmount=" + withholdingAmount +
           ", withholdingPercent=" + withholdingPercent +
           '}';
    }
}
