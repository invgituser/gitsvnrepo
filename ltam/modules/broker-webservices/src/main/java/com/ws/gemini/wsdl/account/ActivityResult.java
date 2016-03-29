/**
 * ActivityResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class ActivityResult  implements java.io.Serializable {
    private short fundId;

    private java.lang.String fundName;

    private int transactionId;

    private org.apache.axis.types.UnsignedByte fundTransactionLineno;

    private java.util.Calendar tradeDate;

    private java.util.Calendar postingDate;

    private org.apache.axis.types.UnsignedByte fundTransactionType;

    private java.math.BigDecimal grossAmount;

    private java.math.BigDecimal withholdingTaxAmount;

    private java.math.BigDecimal netAmount;

    private java.math.BigDecimal pricePerShare;

    private java.math.BigDecimal shareAmount;

    private short shareChangeIndicator;

    private org.apache.axis.types.UnsignedByte fromToTransactionLineno;

    private java.lang.String sharesOrAmountIndicator;

    private java.lang.String fromToAccountNumber;

    private short fromToFundid;

    private java.lang.String fundTransactionSource;

    private java.lang.String retirementIndicator;

    private java.lang.String bookPhysicalSharesIndicator;

    private java.lang.String accountStatementLines;

    private java.math.BigDecimal dividendRate;

    private java.lang.String fundTransactionStatus;

    private java.lang.String memo;

    private java.math.BigDecimal stateTaxAmount;

    private org.apache.axis.types.UnsignedByte feeType;

    private java.math.BigDecimal feeAmount;

    private java.lang.String accountNumber;

    private java.lang.String loadIndicator;

    private java.math.BigDecimal dealerCommissionAmount;

    private java.math.BigDecimal totalCommissionAmount;

    private java.math.BigDecimal fxGrossAmount;

    private java.lang.String currencyName;

    private java.math.BigDecimal fxRate;

    private java.lang.String fundBaseCurrency;

    private java.math.BigDecimal rate;

    private java.util.Calendar maturityDate;

    private java.math.BigDecimal amortizedValue;

    private java.lang.String transactionDescription;

    private java.math.BigDecimal signedGrossAmount;

    private java.math.BigDecimal signedShareAmount;

    private java.math.BigDecimal cummulativeSharesOwned;

    public ActivityResult() {
    }

    public ActivityResult(
           short fundId,
           java.lang.String fundName,
           int transactionId,
           org.apache.axis.types.UnsignedByte fundTransactionLineno,
           java.util.Calendar tradeDate,
           java.util.Calendar postingDate,
           org.apache.axis.types.UnsignedByte fundTransactionType,
           java.math.BigDecimal grossAmount,
           java.math.BigDecimal withholdingTaxAmount,
           java.math.BigDecimal netAmount,
           java.math.BigDecimal pricePerShare,
           java.math.BigDecimal shareAmount,
           short shareChangeIndicator,
           org.apache.axis.types.UnsignedByte fromToTransactionLineno,
           java.lang.String sharesOrAmountIndicator,
           java.lang.String fromToAccountNumber,
           short fromToFundid,
           java.lang.String fundTransactionSource,
           java.lang.String retirementIndicator,
           java.lang.String bookPhysicalSharesIndicator,
           java.lang.String accountStatementLines,
           java.math.BigDecimal dividendRate,
           java.lang.String fundTransactionStatus,
           java.lang.String memo,
           java.math.BigDecimal stateTaxAmount,
           org.apache.axis.types.UnsignedByte feeType,
           java.math.BigDecimal feeAmount,
           java.lang.String accountNumber,
           java.lang.String loadIndicator,
           java.math.BigDecimal dealerCommissionAmount,
           java.math.BigDecimal totalCommissionAmount,
           java.math.BigDecimal fxGrossAmount,
           java.lang.String currencyName,
           java.math.BigDecimal fxRate,
           java.lang.String fundBaseCurrency,
           java.math.BigDecimal rate,
           java.util.Calendar maturityDate,
           java.math.BigDecimal amortizedValue,
           java.lang.String transactionDescription,
           java.math.BigDecimal signedGrossAmount,
           java.math.BigDecimal signedShareAmount,
           java.math.BigDecimal cummulativeSharesOwned) {
           this.fundId = fundId;
           this.fundName = fundName;
           this.transactionId = transactionId;
           this.fundTransactionLineno = fundTransactionLineno;
           this.tradeDate = tradeDate;
           this.postingDate = postingDate;
           this.fundTransactionType = fundTransactionType;
           this.grossAmount = grossAmount;
           this.withholdingTaxAmount = withholdingTaxAmount;
           this.netAmount = netAmount;
           this.pricePerShare = pricePerShare;
           this.shareAmount = shareAmount;
           this.shareChangeIndicator = shareChangeIndicator;
           this.fromToTransactionLineno = fromToTransactionLineno;
           this.sharesOrAmountIndicator = sharesOrAmountIndicator;
           this.fromToAccountNumber = fromToAccountNumber;
           this.fromToFundid = fromToFundid;
           this.fundTransactionSource = fundTransactionSource;
           this.retirementIndicator = retirementIndicator;
           this.bookPhysicalSharesIndicator = bookPhysicalSharesIndicator;
           this.accountStatementLines = accountStatementLines;
           this.dividendRate = dividendRate;
           this.fundTransactionStatus = fundTransactionStatus;
           this.memo = memo;
           this.stateTaxAmount = stateTaxAmount;
           this.feeType = feeType;
           this.feeAmount = feeAmount;
           this.accountNumber = accountNumber;
           this.loadIndicator = loadIndicator;
           this.dealerCommissionAmount = dealerCommissionAmount;
           this.totalCommissionAmount = totalCommissionAmount;
           this.fxGrossAmount = fxGrossAmount;
           this.currencyName = currencyName;
           this.fxRate = fxRate;
           this.fundBaseCurrency = fundBaseCurrency;
           this.rate = rate;
           this.maturityDate = maturityDate;
           this.amortizedValue = amortizedValue;
           this.transactionDescription = transactionDescription;
           this.signedGrossAmount = signedGrossAmount;
           this.signedShareAmount = signedShareAmount;
           this.cummulativeSharesOwned = cummulativeSharesOwned;
    }


    /**
     * Gets the fundId value for this ActivityResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this ActivityResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundName value for this ActivityResult.
     * 
     * @return fundName
     */
    public java.lang.String getFundName() {
        return fundName;
    }


    /**
     * Sets the fundName value for this ActivityResult.
     * 
     * @param fundName
     */
    public void setFundName(java.lang.String fundName) {
        this.fundName = fundName;
    }


    /**
     * Gets the transactionId value for this ActivityResult.
     * 
     * @return transactionId
     */
    public int getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this ActivityResult.
     * 
     * @param transactionId
     */
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the fundTransactionLineno value for this ActivityResult.
     * 
     * @return fundTransactionLineno
     */
    public org.apache.axis.types.UnsignedByte getFundTransactionLineno() {
        return fundTransactionLineno;
    }


    /**
     * Sets the fundTransactionLineno value for this ActivityResult.
     * 
     * @param fundTransactionLineno
     */
    public void setFundTransactionLineno(org.apache.axis.types.UnsignedByte fundTransactionLineno) {
        this.fundTransactionLineno = fundTransactionLineno;
    }


    /**
     * Gets the tradeDate value for this ActivityResult.
     * 
     * @return tradeDate
     */
    public java.util.Calendar getTradeDate() {
        return tradeDate;
    }


    /**
     * Sets the tradeDate value for this ActivityResult.
     * 
     * @param tradeDate
     */
    public void setTradeDate(java.util.Calendar tradeDate) {
        this.tradeDate = tradeDate;
    }


    /**
     * Gets the postingDate value for this ActivityResult.
     * 
     * @return postingDate
     */
    public java.util.Calendar getPostingDate() {
        return postingDate;
    }


    /**
     * Sets the postingDate value for this ActivityResult.
     * 
     * @param postingDate
     */
    public void setPostingDate(java.util.Calendar postingDate) {
        this.postingDate = postingDate;
    }


    /**
     * Gets the fundTransactionType value for this ActivityResult.
     * 
     * @return fundTransactionType
     */
    public org.apache.axis.types.UnsignedByte getFundTransactionType() {
        return fundTransactionType;
    }


    /**
     * Sets the fundTransactionType value for this ActivityResult.
     * 
     * @param fundTransactionType
     */
    public void setFundTransactionType(org.apache.axis.types.UnsignedByte fundTransactionType) {
        this.fundTransactionType = fundTransactionType;
    }


    /**
     * Gets the grossAmount value for this ActivityResult.
     * 
     * @return grossAmount
     */
    public java.math.BigDecimal getGrossAmount() {
        return grossAmount;
    }


    /**
     * Sets the grossAmount value for this ActivityResult.
     * 
     * @param grossAmount
     */
    public void setGrossAmount(java.math.BigDecimal grossAmount) {
        this.grossAmount = grossAmount;
    }


    /**
     * Gets the withholdingTaxAmount value for this ActivityResult.
     * 
     * @return withholdingTaxAmount
     */
    public java.math.BigDecimal getWithholdingTaxAmount() {
        return withholdingTaxAmount;
    }


    /**
     * Sets the withholdingTaxAmount value for this ActivityResult.
     * 
     * @param withholdingTaxAmount
     */
    public void setWithholdingTaxAmount(java.math.BigDecimal withholdingTaxAmount) {
        this.withholdingTaxAmount = withholdingTaxAmount;
    }


    /**
     * Gets the netAmount value for this ActivityResult.
     * 
     * @return netAmount
     */
    public java.math.BigDecimal getNetAmount() {
        return netAmount;
    }


    /**
     * Sets the netAmount value for this ActivityResult.
     * 
     * @param netAmount
     */
    public void setNetAmount(java.math.BigDecimal netAmount) {
        this.netAmount = netAmount;
    }


    /**
     * Gets the pricePerShare value for this ActivityResult.
     * 
     * @return pricePerShare
     */
    public java.math.BigDecimal getPricePerShare() {
        return pricePerShare;
    }


    /**
     * Sets the pricePerShare value for this ActivityResult.
     * 
     * @param pricePerShare
     */
    public void setPricePerShare(java.math.BigDecimal pricePerShare) {
        this.pricePerShare = pricePerShare;
    }


    /**
     * Gets the shareAmount value for this ActivityResult.
     * 
     * @return shareAmount
     */
    public java.math.BigDecimal getShareAmount() {
        return shareAmount;
    }


    /**
     * Sets the shareAmount value for this ActivityResult.
     * 
     * @param shareAmount
     */
    public void setShareAmount(java.math.BigDecimal shareAmount) {
        this.shareAmount = shareAmount;
    }


    /**
     * Gets the shareChangeIndicator value for this ActivityResult.
     * 
     * @return shareChangeIndicator
     */
    public short getShareChangeIndicator() {
        return shareChangeIndicator;
    }


    /**
     * Sets the shareChangeIndicator value for this ActivityResult.
     * 
     * @param shareChangeIndicator
     */
    public void setShareChangeIndicator(short shareChangeIndicator) {
        this.shareChangeIndicator = shareChangeIndicator;
    }


    /**
     * Gets the fromToTransactionLineno value for this ActivityResult.
     * 
     * @return fromToTransactionLineno
     */
    public org.apache.axis.types.UnsignedByte getFromToTransactionLineno() {
        return fromToTransactionLineno;
    }


    /**
     * Sets the fromToTransactionLineno value for this ActivityResult.
     * 
     * @param fromToTransactionLineno
     */
    public void setFromToTransactionLineno(org.apache.axis.types.UnsignedByte fromToTransactionLineno) {
        this.fromToTransactionLineno = fromToTransactionLineno;
    }


    /**
     * Gets the sharesOrAmountIndicator value for this ActivityResult.
     * 
     * @return sharesOrAmountIndicator
     */
    public java.lang.String getSharesOrAmountIndicator() {
        return sharesOrAmountIndicator;
    }


    /**
     * Sets the sharesOrAmountIndicator value for this ActivityResult.
     * 
     * @param sharesOrAmountIndicator
     */
    public void setSharesOrAmountIndicator(java.lang.String sharesOrAmountIndicator) {
        this.sharesOrAmountIndicator = sharesOrAmountIndicator;
    }


    /**
     * Gets the fromToAccountNumber value for this ActivityResult.
     * 
     * @return fromToAccountNumber
     */
    public java.lang.String getFromToAccountNumber() {
        return fromToAccountNumber;
    }


    /**
     * Sets the fromToAccountNumber value for this ActivityResult.
     * 
     * @param fromToAccountNumber
     */
    public void setFromToAccountNumber(java.lang.String fromToAccountNumber) {
        this.fromToAccountNumber = fromToAccountNumber;
    }


    /**
     * Gets the fromToFundid value for this ActivityResult.
     * 
     * @return fromToFundid
     */
    public short getFromToFundid() {
        return fromToFundid;
    }


    /**
     * Sets the fromToFundid value for this ActivityResult.
     * 
     * @param fromToFundid
     */
    public void setFromToFundid(short fromToFundid) {
        this.fromToFundid = fromToFundid;
    }


    /**
     * Gets the fundTransactionSource value for this ActivityResult.
     * 
     * @return fundTransactionSource
     */
    public java.lang.String getFundTransactionSource() {
        return fundTransactionSource;
    }


    /**
     * Sets the fundTransactionSource value for this ActivityResult.
     * 
     * @param fundTransactionSource
     */
    public void setFundTransactionSource(java.lang.String fundTransactionSource) {
        this.fundTransactionSource = fundTransactionSource;
    }


    /**
     * Gets the retirementIndicator value for this ActivityResult.
     * 
     * @return retirementIndicator
     */
    public java.lang.String getRetirementIndicator() {
        return retirementIndicator;
    }


    /**
     * Sets the retirementIndicator value for this ActivityResult.
     * 
     * @param retirementIndicator
     */
    public void setRetirementIndicator(java.lang.String retirementIndicator) {
        this.retirementIndicator = retirementIndicator;
    }


    /**
     * Gets the bookPhysicalSharesIndicator value for this ActivityResult.
     * 
     * @return bookPhysicalSharesIndicator
     */
    public java.lang.String getBookPhysicalSharesIndicator() {
        return bookPhysicalSharesIndicator;
    }


    /**
     * Sets the bookPhysicalSharesIndicator value for this ActivityResult.
     * 
     * @param bookPhysicalSharesIndicator
     */
    public void setBookPhysicalSharesIndicator(java.lang.String bookPhysicalSharesIndicator) {
        this.bookPhysicalSharesIndicator = bookPhysicalSharesIndicator;
    }


    /**
     * Gets the accountStatementLines value for this ActivityResult.
     * 
     * @return accountStatementLines
     */
    public java.lang.String getAccountStatementLines() {
        return accountStatementLines;
    }


    /**
     * Sets the accountStatementLines value for this ActivityResult.
     * 
     * @param accountStatementLines
     */
    public void setAccountStatementLines(java.lang.String accountStatementLines) {
        this.accountStatementLines = accountStatementLines;
    }


    /**
     * Gets the dividendRate value for this ActivityResult.
     * 
     * @return dividendRate
     */
    public java.math.BigDecimal getDividendRate() {
        return dividendRate;
    }


    /**
     * Sets the dividendRate value for this ActivityResult.
     * 
     * @param dividendRate
     */
    public void setDividendRate(java.math.BigDecimal dividendRate) {
        this.dividendRate = dividendRate;
    }


    /**
     * Gets the fundTransactionStatus value for this ActivityResult.
     * 
     * @return fundTransactionStatus
     */
    public java.lang.String getFundTransactionStatus() {
        return fundTransactionStatus;
    }


    /**
     * Sets the fundTransactionStatus value for this ActivityResult.
     * 
     * @param fundTransactionStatus
     */
    public void setFundTransactionStatus(java.lang.String fundTransactionStatus) {
        this.fundTransactionStatus = fundTransactionStatus;
    }


    /**
     * Gets the memo value for this ActivityResult.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this ActivityResult.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the stateTaxAmount value for this ActivityResult.
     * 
     * @return stateTaxAmount
     */
    public java.math.BigDecimal getStateTaxAmount() {
        return stateTaxAmount;
    }


    /**
     * Sets the stateTaxAmount value for this ActivityResult.
     * 
     * @param stateTaxAmount
     */
    public void setStateTaxAmount(java.math.BigDecimal stateTaxAmount) {
        this.stateTaxAmount = stateTaxAmount;
    }


    /**
     * Gets the feeType value for this ActivityResult.
     * 
     * @return feeType
     */
    public org.apache.axis.types.UnsignedByte getFeeType() {
        return feeType;
    }


    /**
     * Sets the feeType value for this ActivityResult.
     * 
     * @param feeType
     */
    public void setFeeType(org.apache.axis.types.UnsignedByte feeType) {
        this.feeType = feeType;
    }


    /**
     * Gets the feeAmount value for this ActivityResult.
     * 
     * @return feeAmount
     */
    public java.math.BigDecimal getFeeAmount() {
        return feeAmount;
    }


    /**
     * Sets the feeAmount value for this ActivityResult.
     * 
     * @param feeAmount
     */
    public void setFeeAmount(java.math.BigDecimal feeAmount) {
        this.feeAmount = feeAmount;
    }


    /**
     * Gets the accountNumber value for this ActivityResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this ActivityResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the loadIndicator value for this ActivityResult.
     * 
     * @return loadIndicator
     */
    public java.lang.String getLoadIndicator() {
        return loadIndicator;
    }


    /**
     * Sets the loadIndicator value for this ActivityResult.
     * 
     * @param loadIndicator
     */
    public void setLoadIndicator(java.lang.String loadIndicator) {
        this.loadIndicator = loadIndicator;
    }


    /**
     * Gets the dealerCommissionAmount value for this ActivityResult.
     * 
     * @return dealerCommissionAmount
     */
    public java.math.BigDecimal getDealerCommissionAmount() {
        return dealerCommissionAmount;
    }


    /**
     * Sets the dealerCommissionAmount value for this ActivityResult.
     * 
     * @param dealerCommissionAmount
     */
    public void setDealerCommissionAmount(java.math.BigDecimal dealerCommissionAmount) {
        this.dealerCommissionAmount = dealerCommissionAmount;
    }


    /**
     * Gets the totalCommissionAmount value for this ActivityResult.
     * 
     * @return totalCommissionAmount
     */
    public java.math.BigDecimal getTotalCommissionAmount() {
        return totalCommissionAmount;
    }


    /**
     * Sets the totalCommissionAmount value for this ActivityResult.
     * 
     * @param totalCommissionAmount
     */
    public void setTotalCommissionAmount(java.math.BigDecimal totalCommissionAmount) {
        this.totalCommissionAmount = totalCommissionAmount;
    }


    /**
     * Gets the fxGrossAmount value for this ActivityResult.
     * 
     * @return fxGrossAmount
     */
    public java.math.BigDecimal getFxGrossAmount() {
        return fxGrossAmount;
    }


    /**
     * Sets the fxGrossAmount value for this ActivityResult.
     * 
     * @param fxGrossAmount
     */
    public void setFxGrossAmount(java.math.BigDecimal fxGrossAmount) {
        this.fxGrossAmount = fxGrossAmount;
    }


    /**
     * Gets the currencyName value for this ActivityResult.
     * 
     * @return currencyName
     */
    public java.lang.String getCurrencyName() {
        return currencyName;
    }


    /**
     * Sets the currencyName value for this ActivityResult.
     * 
     * @param currencyName
     */
    public void setCurrencyName(java.lang.String currencyName) {
        this.currencyName = currencyName;
    }


    /**
     * Gets the fxRate value for this ActivityResult.
     * 
     * @return fxRate
     */
    public java.math.BigDecimal getFxRate() {
        return fxRate;
    }


    /**
     * Sets the fxRate value for this ActivityResult.
     * 
     * @param fxRate
     */
    public void setFxRate(java.math.BigDecimal fxRate) {
        this.fxRate = fxRate;
    }


    /**
     * Gets the fundBaseCurrency value for this ActivityResult.
     * 
     * @return fundBaseCurrency
     */
    public java.lang.String getFundBaseCurrency() {
        return fundBaseCurrency;
    }


    /**
     * Sets the fundBaseCurrency value for this ActivityResult.
     * 
     * @param fundBaseCurrency
     */
    public void setFundBaseCurrency(java.lang.String fundBaseCurrency) {
        this.fundBaseCurrency = fundBaseCurrency;
    }


    /**
     * Gets the rate value for this ActivityResult.
     * 
     * @return rate
     */
    public java.math.BigDecimal getRate() {
        return rate;
    }


    /**
     * Sets the rate value for this ActivityResult.
     * 
     * @param rate
     */
    public void setRate(java.math.BigDecimal rate) {
        this.rate = rate;
    }


    /**
     * Gets the maturityDate value for this ActivityResult.
     * 
     * @return maturityDate
     */
    public java.util.Calendar getMaturityDate() {
        return maturityDate;
    }


    /**
     * Sets the maturityDate value for this ActivityResult.
     * 
     * @param maturityDate
     */
    public void setMaturityDate(java.util.Calendar maturityDate) {
        this.maturityDate = maturityDate;
    }


    /**
     * Gets the amortizedValue value for this ActivityResult.
     * 
     * @return amortizedValue
     */
    public java.math.BigDecimal getAmortizedValue() {
        return amortizedValue;
    }


    /**
     * Sets the amortizedValue value for this ActivityResult.
     * 
     * @param amortizedValue
     */
    public void setAmortizedValue(java.math.BigDecimal amortizedValue) {
        this.amortizedValue = amortizedValue;
    }


    /**
     * Gets the transactionDescription value for this ActivityResult.
     * 
     * @return transactionDescription
     */
    public java.lang.String getTransactionDescription() {
        return transactionDescription;
    }


    /**
     * Sets the transactionDescription value for this ActivityResult.
     * 
     * @param transactionDescription
     */
    public void setTransactionDescription(java.lang.String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }


    /**
     * Gets the signedGrossAmount value for this ActivityResult.
     * 
     * @return signedGrossAmount
     */
    public java.math.BigDecimal getSignedGrossAmount() {
        return signedGrossAmount;
    }


    /**
     * Sets the signedGrossAmount value for this ActivityResult.
     * 
     * @param signedGrossAmount
     */
    public void setSignedGrossAmount(java.math.BigDecimal signedGrossAmount) {
        this.signedGrossAmount = signedGrossAmount;
    }


    /**
     * Gets the signedShareAmount value for this ActivityResult.
     * 
     * @return signedShareAmount
     */
    public java.math.BigDecimal getSignedShareAmount() {
        return signedShareAmount;
    }


    /**
     * Sets the signedShareAmount value for this ActivityResult.
     * 
     * @param signedShareAmount
     */
    public void setSignedShareAmount(java.math.BigDecimal signedShareAmount) {
        this.signedShareAmount = signedShareAmount;
    }


    /**
     * Gets the cummulativeSharesOwned value for this ActivityResult.
     * 
     * @return cummulativeSharesOwned
     */
    public java.math.BigDecimal getCummulativeSharesOwned() {
        return cummulativeSharesOwned;
    }


    /**
     * Sets the cummulativeSharesOwned value for this ActivityResult.
     * 
     * @param cummulativeSharesOwned
     */
    public void setCummulativeSharesOwned(java.math.BigDecimal cummulativeSharesOwned) {
        this.cummulativeSharesOwned = cummulativeSharesOwned;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ActivityResult)) return false;
        ActivityResult other = (ActivityResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.fundId == other.getFundId() &&
            ((this.fundName==null && other.getFundName()==null) || 
             (this.fundName!=null &&
              this.fundName.equals(other.getFundName()))) &&
            this.transactionId == other.getTransactionId() &&
            ((this.fundTransactionLineno==null && other.getFundTransactionLineno()==null) || 
             (this.fundTransactionLineno!=null &&
              this.fundTransactionLineno.equals(other.getFundTransactionLineno()))) &&
            ((this.tradeDate==null && other.getTradeDate()==null) || 
             (this.tradeDate!=null &&
              this.tradeDate.equals(other.getTradeDate()))) &&
            ((this.postingDate==null && other.getPostingDate()==null) || 
             (this.postingDate!=null &&
              this.postingDate.equals(other.getPostingDate()))) &&
            ((this.fundTransactionType==null && other.getFundTransactionType()==null) || 
             (this.fundTransactionType!=null &&
              this.fundTransactionType.equals(other.getFundTransactionType()))) &&
            ((this.grossAmount==null && other.getGrossAmount()==null) || 
             (this.grossAmount!=null &&
              this.grossAmount.equals(other.getGrossAmount()))) &&
            ((this.withholdingTaxAmount==null && other.getWithholdingTaxAmount()==null) || 
             (this.withholdingTaxAmount!=null &&
              this.withholdingTaxAmount.equals(other.getWithholdingTaxAmount()))) &&
            ((this.netAmount==null && other.getNetAmount()==null) || 
             (this.netAmount!=null &&
              this.netAmount.equals(other.getNetAmount()))) &&
            ((this.pricePerShare==null && other.getPricePerShare()==null) || 
             (this.pricePerShare!=null &&
              this.pricePerShare.equals(other.getPricePerShare()))) &&
            ((this.shareAmount==null && other.getShareAmount()==null) || 
             (this.shareAmount!=null &&
              this.shareAmount.equals(other.getShareAmount()))) &&
            this.shareChangeIndicator == other.getShareChangeIndicator() &&
            ((this.fromToTransactionLineno==null && other.getFromToTransactionLineno()==null) || 
             (this.fromToTransactionLineno!=null &&
              this.fromToTransactionLineno.equals(other.getFromToTransactionLineno()))) &&
            ((this.sharesOrAmountIndicator==null && other.getSharesOrAmountIndicator()==null) || 
             (this.sharesOrAmountIndicator!=null &&
              this.sharesOrAmountIndicator.equals(other.getSharesOrAmountIndicator()))) &&
            ((this.fromToAccountNumber==null && other.getFromToAccountNumber()==null) || 
             (this.fromToAccountNumber!=null &&
              this.fromToAccountNumber.equals(other.getFromToAccountNumber()))) &&
            this.fromToFundid == other.getFromToFundid() &&
            ((this.fundTransactionSource==null && other.getFundTransactionSource()==null) || 
             (this.fundTransactionSource!=null &&
              this.fundTransactionSource.equals(other.getFundTransactionSource()))) &&
            ((this.retirementIndicator==null && other.getRetirementIndicator()==null) || 
             (this.retirementIndicator!=null &&
              this.retirementIndicator.equals(other.getRetirementIndicator()))) &&
            ((this.bookPhysicalSharesIndicator==null && other.getBookPhysicalSharesIndicator()==null) || 
             (this.bookPhysicalSharesIndicator!=null &&
              this.bookPhysicalSharesIndicator.equals(other.getBookPhysicalSharesIndicator()))) &&
            ((this.accountStatementLines==null && other.getAccountStatementLines()==null) || 
             (this.accountStatementLines!=null &&
              this.accountStatementLines.equals(other.getAccountStatementLines()))) &&
            ((this.dividendRate==null && other.getDividendRate()==null) || 
             (this.dividendRate!=null &&
              this.dividendRate.equals(other.getDividendRate()))) &&
            ((this.fundTransactionStatus==null && other.getFundTransactionStatus()==null) || 
             (this.fundTransactionStatus!=null &&
              this.fundTransactionStatus.equals(other.getFundTransactionStatus()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            ((this.stateTaxAmount==null && other.getStateTaxAmount()==null) || 
             (this.stateTaxAmount!=null &&
              this.stateTaxAmount.equals(other.getStateTaxAmount()))) &&
            ((this.feeType==null && other.getFeeType()==null) || 
             (this.feeType!=null &&
              this.feeType.equals(other.getFeeType()))) &&
            ((this.feeAmount==null && other.getFeeAmount()==null) || 
             (this.feeAmount!=null &&
              this.feeAmount.equals(other.getFeeAmount()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.loadIndicator==null && other.getLoadIndicator()==null) || 
             (this.loadIndicator!=null &&
              this.loadIndicator.equals(other.getLoadIndicator()))) &&
            ((this.dealerCommissionAmount==null && other.getDealerCommissionAmount()==null) || 
             (this.dealerCommissionAmount!=null &&
              this.dealerCommissionAmount.equals(other.getDealerCommissionAmount()))) &&
            ((this.totalCommissionAmount==null && other.getTotalCommissionAmount()==null) || 
             (this.totalCommissionAmount!=null &&
              this.totalCommissionAmount.equals(other.getTotalCommissionAmount()))) &&
            ((this.fxGrossAmount==null && other.getFxGrossAmount()==null) || 
             (this.fxGrossAmount!=null &&
              this.fxGrossAmount.equals(other.getFxGrossAmount()))) &&
            ((this.currencyName==null && other.getCurrencyName()==null) || 
             (this.currencyName!=null &&
              this.currencyName.equals(other.getCurrencyName()))) &&
            ((this.fxRate==null && other.getFxRate()==null) || 
             (this.fxRate!=null &&
              this.fxRate.equals(other.getFxRate()))) &&
            ((this.fundBaseCurrency==null && other.getFundBaseCurrency()==null) || 
             (this.fundBaseCurrency!=null &&
              this.fundBaseCurrency.equals(other.getFundBaseCurrency()))) &&
            ((this.rate==null && other.getRate()==null) || 
             (this.rate!=null &&
              this.rate.equals(other.getRate()))) &&
            ((this.maturityDate==null && other.getMaturityDate()==null) || 
             (this.maturityDate!=null &&
              this.maturityDate.equals(other.getMaturityDate()))) &&
            ((this.amortizedValue==null && other.getAmortizedValue()==null) || 
             (this.amortizedValue!=null &&
              this.amortizedValue.equals(other.getAmortizedValue()))) &&
            ((this.transactionDescription==null && other.getTransactionDescription()==null) || 
             (this.transactionDescription!=null &&
              this.transactionDescription.equals(other.getTransactionDescription()))) &&
            ((this.signedGrossAmount==null && other.getSignedGrossAmount()==null) || 
             (this.signedGrossAmount!=null &&
              this.signedGrossAmount.equals(other.getSignedGrossAmount()))) &&
            ((this.signedShareAmount==null && other.getSignedShareAmount()==null) || 
             (this.signedShareAmount!=null &&
              this.signedShareAmount.equals(other.getSignedShareAmount()))) &&
            ((this.cummulativeSharesOwned==null && other.getCummulativeSharesOwned()==null) || 
             (this.cummulativeSharesOwned!=null &&
              this.cummulativeSharesOwned.equals(other.getCummulativeSharesOwned())));
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
        _hashCode += getFundId();
        if (getFundName() != null) {
            _hashCode += getFundName().hashCode();
        }
        _hashCode += getTransactionId();
        if (getFundTransactionLineno() != null) {
            _hashCode += getFundTransactionLineno().hashCode();
        }
        if (getTradeDate() != null) {
            _hashCode += getTradeDate().hashCode();
        }
        if (getPostingDate() != null) {
            _hashCode += getPostingDate().hashCode();
        }
        if (getFundTransactionType() != null) {
            _hashCode += getFundTransactionType().hashCode();
        }
        if (getGrossAmount() != null) {
            _hashCode += getGrossAmount().hashCode();
        }
        if (getWithholdingTaxAmount() != null) {
            _hashCode += getWithholdingTaxAmount().hashCode();
        }
        if (getNetAmount() != null) {
            _hashCode += getNetAmount().hashCode();
        }
        if (getPricePerShare() != null) {
            _hashCode += getPricePerShare().hashCode();
        }
        if (getShareAmount() != null) {
            _hashCode += getShareAmount().hashCode();
        }
        _hashCode += getShareChangeIndicator();
        if (getFromToTransactionLineno() != null) {
            _hashCode += getFromToTransactionLineno().hashCode();
        }
        if (getSharesOrAmountIndicator() != null) {
            _hashCode += getSharesOrAmountIndicator().hashCode();
        }
        if (getFromToAccountNumber() != null) {
            _hashCode += getFromToAccountNumber().hashCode();
        }
        _hashCode += getFromToFundid();
        if (getFundTransactionSource() != null) {
            _hashCode += getFundTransactionSource().hashCode();
        }
        if (getRetirementIndicator() != null) {
            _hashCode += getRetirementIndicator().hashCode();
        }
        if (getBookPhysicalSharesIndicator() != null) {
            _hashCode += getBookPhysicalSharesIndicator().hashCode();
        }
        if (getAccountStatementLines() != null) {
            _hashCode += getAccountStatementLines().hashCode();
        }
        if (getDividendRate() != null) {
            _hashCode += getDividendRate().hashCode();
        }
        if (getFundTransactionStatus() != null) {
            _hashCode += getFundTransactionStatus().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        if (getStateTaxAmount() != null) {
            _hashCode += getStateTaxAmount().hashCode();
        }
        if (getFeeType() != null) {
            _hashCode += getFeeType().hashCode();
        }
        if (getFeeAmount() != null) {
            _hashCode += getFeeAmount().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getLoadIndicator() != null) {
            _hashCode += getLoadIndicator().hashCode();
        }
        if (getDealerCommissionAmount() != null) {
            _hashCode += getDealerCommissionAmount().hashCode();
        }
        if (getTotalCommissionAmount() != null) {
            _hashCode += getTotalCommissionAmount().hashCode();
        }
        if (getFxGrossAmount() != null) {
            _hashCode += getFxGrossAmount().hashCode();
        }
        if (getCurrencyName() != null) {
            _hashCode += getCurrencyName().hashCode();
        }
        if (getFxRate() != null) {
            _hashCode += getFxRate().hashCode();
        }
        if (getFundBaseCurrency() != null) {
            _hashCode += getFundBaseCurrency().hashCode();
        }
        if (getRate() != null) {
            _hashCode += getRate().hashCode();
        }
        if (getMaturityDate() != null) {
            _hashCode += getMaturityDate().hashCode();
        }
        if (getAmortizedValue() != null) {
            _hashCode += getAmortizedValue().hashCode();
        }
        if (getTransactionDescription() != null) {
            _hashCode += getTransactionDescription().hashCode();
        }
        if (getSignedGrossAmount() != null) {
            _hashCode += getSignedGrossAmount().hashCode();
        }
        if (getSignedShareAmount() != null) {
            _hashCode += getSignedShareAmount().hashCode();
        }
        if (getCummulativeSharesOwned() != null) {
            _hashCode += getCummulativeSharesOwned().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ActivityResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ActivityResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionLineno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionLineno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tradeDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TradeDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PostingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("withholdingTaxAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "WithholdingTaxAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("netAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NetAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pricePerShare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PricePerShare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shareChangeIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ShareChangeIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromToTransactionLineno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FromToTransactionLineno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sharesOrAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SharesOrAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromToAccountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FromToAccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromToFundid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FromToFundid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionSource");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retirementIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RetirementIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bookPhysicalSharesIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BookPhysicalSharesIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountStatementLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountStatementLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dividendRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DividendRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundTransactionStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundTransactionStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Memo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stateTaxAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StateTaxAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feeAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FeeAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
        elemField.setFieldName("loadIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LoadIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dealerCommissionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DealerCommissionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalCommissionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalCommissionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fxGrossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FxGrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrencyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fxRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FxRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundBaseCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundBaseCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Rate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("maturityDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MaturityDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amortizedValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AmortizedValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TransactionDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signedGrossAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SignedGrossAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signedShareAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SignedShareAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cummulativeSharesOwned");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CummulativeSharesOwned"));
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

}
