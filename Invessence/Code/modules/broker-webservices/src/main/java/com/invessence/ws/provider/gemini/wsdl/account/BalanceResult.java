/**
 * BalanceResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class BalanceResult  implements java.io.Serializable {
    private short fundId;

    private java.lang.String fundName;

    private java.lang.String fundShortName;

    private java.util.Calendar inceptionDate;

    private java.math.BigDecimal totalShares;

    private java.math.BigDecimal beginningTotalShares;

    private java.math.BigDecimal beginningNAVPricePerShare;

    private java.math.BigDecimal postedSettledShares;

    private java.math.BigDecimal unsettledShares;

    private java.math.BigDecimal totalValue;

    private java.math.BigDecimal YTDIncomeDividend;

    private java.math.BigDecimal YTDSTCG;

    private java.math.BigDecimal YTDLTCG;

    private java.math.BigDecimal YTDMTCG;

    private java.math.BigDecimal YTDReturnofCapital;

    private java.math.BigDecimal marketValue;

    private java.math.BigDecimal totalPhysicalShares;

    private java.math.BigDecimal navPricePerShare;

    private java.util.Calendar priceDate;

    private java.lang.String accountNumber;

    private java.lang.String customerName;

    private org.apache.axis.types.UnsignedByte priceCycleId;

    private org.apache.axis.types.UnsignedByte reinvestDividend;

    private org.apache.axis.types.UnsignedByte reinvestSTCG;

    private org.apache.axis.types.UnsignedByte reinvestLTCG;

    private java.lang.String fundUrl;

    private java.math.BigDecimal redemptionAvailableShares;

    private java.math.BigDecimal exchangeAvailableShares;

    private java.math.BigDecimal redemptionAvailableBalance;

    private java.math.BigDecimal exchangeAvailableBalance;

    private java.math.BigDecimal lastCBAComputedShareAmount;

    private java.util.Calendar lastCostbasisComputedDate;

    private short CBDMIdForCoveredShares;

    private java.lang.String CBDMNameForCoveredShares;

    private short CBDMIdForNonCoveredShares;

    private java.lang.String CBDMNameForNonCoveredShares;

    public BalanceResult() {
    }

    public BalanceResult(
           short fundId,
           java.lang.String fundName,
           java.lang.String fundShortName,
           java.util.Calendar inceptionDate,
           java.math.BigDecimal totalShares,
           java.math.BigDecimal beginningTotalShares,
           java.math.BigDecimal beginningNAVPricePerShare,
           java.math.BigDecimal postedSettledShares,
           java.math.BigDecimal unsettledShares,
           java.math.BigDecimal totalValue,
           java.math.BigDecimal YTDIncomeDividend,
           java.math.BigDecimal YTDSTCG,
           java.math.BigDecimal YTDLTCG,
           java.math.BigDecimal YTDMTCG,
           java.math.BigDecimal YTDReturnofCapital,
           java.math.BigDecimal marketValue,
           java.math.BigDecimal totalPhysicalShares,
           java.math.BigDecimal navPricePerShare,
           java.util.Calendar priceDate,
           java.lang.String accountNumber,
           java.lang.String customerName,
           org.apache.axis.types.UnsignedByte priceCycleId,
           org.apache.axis.types.UnsignedByte reinvestDividend,
           org.apache.axis.types.UnsignedByte reinvestSTCG,
           org.apache.axis.types.UnsignedByte reinvestLTCG,
           java.lang.String fundUrl,
           java.math.BigDecimal redemptionAvailableShares,
           java.math.BigDecimal exchangeAvailableShares,
           java.math.BigDecimal redemptionAvailableBalance,
           java.math.BigDecimal exchangeAvailableBalance,
           java.math.BigDecimal lastCBAComputedShareAmount,
           java.util.Calendar lastCostbasisComputedDate,
           short CBDMIdForCoveredShares,
           java.lang.String CBDMNameForCoveredShares,
           short CBDMIdForNonCoveredShares,
           java.lang.String CBDMNameForNonCoveredShares) {
           this.fundId = fundId;
           this.fundName = fundName;
           this.fundShortName = fundShortName;
           this.inceptionDate = inceptionDate;
           this.totalShares = totalShares;
           this.beginningTotalShares = beginningTotalShares;
           this.beginningNAVPricePerShare = beginningNAVPricePerShare;
           this.postedSettledShares = postedSettledShares;
           this.unsettledShares = unsettledShares;
           this.totalValue = totalValue;
           this.YTDIncomeDividend = YTDIncomeDividend;
           this.YTDSTCG = YTDSTCG;
           this.YTDLTCG = YTDLTCG;
           this.YTDMTCG = YTDMTCG;
           this.YTDReturnofCapital = YTDReturnofCapital;
           this.marketValue = marketValue;
           this.totalPhysicalShares = totalPhysicalShares;
           this.navPricePerShare = navPricePerShare;
           this.priceDate = priceDate;
           this.accountNumber = accountNumber;
           this.customerName = customerName;
           this.priceCycleId = priceCycleId;
           this.reinvestDividend = reinvestDividend;
           this.reinvestSTCG = reinvestSTCG;
           this.reinvestLTCG = reinvestLTCG;
           this.fundUrl = fundUrl;
           this.redemptionAvailableShares = redemptionAvailableShares;
           this.exchangeAvailableShares = exchangeAvailableShares;
           this.redemptionAvailableBalance = redemptionAvailableBalance;
           this.exchangeAvailableBalance = exchangeAvailableBalance;
           this.lastCBAComputedShareAmount = lastCBAComputedShareAmount;
           this.lastCostbasisComputedDate = lastCostbasisComputedDate;
           this.CBDMIdForCoveredShares = CBDMIdForCoveredShares;
           this.CBDMNameForCoveredShares = CBDMNameForCoveredShares;
           this.CBDMIdForNonCoveredShares = CBDMIdForNonCoveredShares;
           this.CBDMNameForNonCoveredShares = CBDMNameForNonCoveredShares;
    }


    /**
     * Gets the fundId value for this BalanceResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this BalanceResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the fundName value for this BalanceResult.
     * 
     * @return fundName
     */
    public java.lang.String getFundName() {
        return fundName;
    }


    /**
     * Sets the fundName value for this BalanceResult.
     * 
     * @param fundName
     */
    public void setFundName(java.lang.String fundName) {
        this.fundName = fundName;
    }


    /**
     * Gets the fundShortName value for this BalanceResult.
     * 
     * @return fundShortName
     */
    public java.lang.String getFundShortName() {
        return fundShortName;
    }


    /**
     * Sets the fundShortName value for this BalanceResult.
     * 
     * @param fundShortName
     */
    public void setFundShortName(java.lang.String fundShortName) {
        this.fundShortName = fundShortName;
    }


    /**
     * Gets the inceptionDate value for this BalanceResult.
     * 
     * @return inceptionDate
     */
    public java.util.Calendar getInceptionDate() {
        return inceptionDate;
    }


    /**
     * Sets the inceptionDate value for this BalanceResult.
     * 
     * @param inceptionDate
     */
    public void setInceptionDate(java.util.Calendar inceptionDate) {
        this.inceptionDate = inceptionDate;
    }


    /**
     * Gets the totalShares value for this BalanceResult.
     * 
     * @return totalShares
     */
    public java.math.BigDecimal getTotalShares() {
        return totalShares;
    }


    /**
     * Sets the totalShares value for this BalanceResult.
     * 
     * @param totalShares
     */
    public void setTotalShares(java.math.BigDecimal totalShares) {
        this.totalShares = totalShares;
    }


    /**
     * Gets the beginningTotalShares value for this BalanceResult.
     * 
     * @return beginningTotalShares
     */
    public java.math.BigDecimal getBeginningTotalShares() {
        return beginningTotalShares;
    }


    /**
     * Sets the beginningTotalShares value for this BalanceResult.
     * 
     * @param beginningTotalShares
     */
    public void setBeginningTotalShares(java.math.BigDecimal beginningTotalShares) {
        this.beginningTotalShares = beginningTotalShares;
    }


    /**
     * Gets the beginningNAVPricePerShare value for this BalanceResult.
     * 
     * @return beginningNAVPricePerShare
     */
    public java.math.BigDecimal getBeginningNAVPricePerShare() {
        return beginningNAVPricePerShare;
    }


    /**
     * Sets the beginningNAVPricePerShare value for this BalanceResult.
     * 
     * @param beginningNAVPricePerShare
     */
    public void setBeginningNAVPricePerShare(java.math.BigDecimal beginningNAVPricePerShare) {
        this.beginningNAVPricePerShare = beginningNAVPricePerShare;
    }


    /**
     * Gets the postedSettledShares value for this BalanceResult.
     * 
     * @return postedSettledShares
     */
    public java.math.BigDecimal getPostedSettledShares() {
        return postedSettledShares;
    }


    /**
     * Sets the postedSettledShares value for this BalanceResult.
     * 
     * @param postedSettledShares
     */
    public void setPostedSettledShares(java.math.BigDecimal postedSettledShares) {
        this.postedSettledShares = postedSettledShares;
    }


    /**
     * Gets the unsettledShares value for this BalanceResult.
     * 
     * @return unsettledShares
     */
    public java.math.BigDecimal getUnsettledShares() {
        return unsettledShares;
    }


    /**
     * Sets the unsettledShares value for this BalanceResult.
     * 
     * @param unsettledShares
     */
    public void setUnsettledShares(java.math.BigDecimal unsettledShares) {
        this.unsettledShares = unsettledShares;
    }


    /**
     * Gets the totalValue value for this BalanceResult.
     * 
     * @return totalValue
     */
    public java.math.BigDecimal getTotalValue() {
        return totalValue;
    }


    /**
     * Sets the totalValue value for this BalanceResult.
     * 
     * @param totalValue
     */
    public void setTotalValue(java.math.BigDecimal totalValue) {
        this.totalValue = totalValue;
    }


    /**
     * Gets the YTDIncomeDividend value for this BalanceResult.
     * 
     * @return YTDIncomeDividend
     */
    public java.math.BigDecimal getYTDIncomeDividend() {
        return YTDIncomeDividend;
    }


    /**
     * Sets the YTDIncomeDividend value for this BalanceResult.
     * 
     * @param YTDIncomeDividend
     */
    public void setYTDIncomeDividend(java.math.BigDecimal YTDIncomeDividend) {
        this.YTDIncomeDividend = YTDIncomeDividend;
    }


    /**
     * Gets the YTDSTCG value for this BalanceResult.
     * 
     * @return YTDSTCG
     */
    public java.math.BigDecimal getYTDSTCG() {
        return YTDSTCG;
    }


    /**
     * Sets the YTDSTCG value for this BalanceResult.
     * 
     * @param YTDSTCG
     */
    public void setYTDSTCG(java.math.BigDecimal YTDSTCG) {
        this.YTDSTCG = YTDSTCG;
    }


    /**
     * Gets the YTDLTCG value for this BalanceResult.
     * 
     * @return YTDLTCG
     */
    public java.math.BigDecimal getYTDLTCG() {
        return YTDLTCG;
    }


    /**
     * Sets the YTDLTCG value for this BalanceResult.
     * 
     * @param YTDLTCG
     */
    public void setYTDLTCG(java.math.BigDecimal YTDLTCG) {
        this.YTDLTCG = YTDLTCG;
    }


    /**
     * Gets the YTDMTCG value for this BalanceResult.
     * 
     * @return YTDMTCG
     */
    public java.math.BigDecimal getYTDMTCG() {
        return YTDMTCG;
    }


    /**
     * Sets the YTDMTCG value for this BalanceResult.
     * 
     * @param YTDMTCG
     */
    public void setYTDMTCG(java.math.BigDecimal YTDMTCG) {
        this.YTDMTCG = YTDMTCG;
    }


    /**
     * Gets the YTDReturnofCapital value for this BalanceResult.
     * 
     * @return YTDReturnofCapital
     */
    public java.math.BigDecimal getYTDReturnofCapital() {
        return YTDReturnofCapital;
    }


    /**
     * Sets the YTDReturnofCapital value for this BalanceResult.
     * 
     * @param YTDReturnofCapital
     */
    public void setYTDReturnofCapital(java.math.BigDecimal YTDReturnofCapital) {
        this.YTDReturnofCapital = YTDReturnofCapital;
    }


    /**
     * Gets the marketValue value for this BalanceResult.
     * 
     * @return marketValue
     */
    public java.math.BigDecimal getMarketValue() {
        return marketValue;
    }


    /**
     * Sets the marketValue value for this BalanceResult.
     * 
     * @param marketValue
     */
    public void setMarketValue(java.math.BigDecimal marketValue) {
        this.marketValue = marketValue;
    }


    /**
     * Gets the totalPhysicalShares value for this BalanceResult.
     * 
     * @return totalPhysicalShares
     */
    public java.math.BigDecimal getTotalPhysicalShares() {
        return totalPhysicalShares;
    }


    /**
     * Sets the totalPhysicalShares value for this BalanceResult.
     * 
     * @param totalPhysicalShares
     */
    public void setTotalPhysicalShares(java.math.BigDecimal totalPhysicalShares) {
        this.totalPhysicalShares = totalPhysicalShares;
    }


    /**
     * Gets the navPricePerShare value for this BalanceResult.
     * 
     * @return navPricePerShare
     */
    public java.math.BigDecimal getNavPricePerShare() {
        return navPricePerShare;
    }


    /**
     * Sets the navPricePerShare value for this BalanceResult.
     * 
     * @param navPricePerShare
     */
    public void setNavPricePerShare(java.math.BigDecimal navPricePerShare) {
        this.navPricePerShare = navPricePerShare;
    }


    /**
     * Gets the priceDate value for this BalanceResult.
     * 
     * @return priceDate
     */
    public java.util.Calendar getPriceDate() {
        return priceDate;
    }


    /**
     * Sets the priceDate value for this BalanceResult.
     * 
     * @param priceDate
     */
    public void setPriceDate(java.util.Calendar priceDate) {
        this.priceDate = priceDate;
    }


    /**
     * Gets the accountNumber value for this BalanceResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this BalanceResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the customerName value for this BalanceResult.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this BalanceResult.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the priceCycleId value for this BalanceResult.
     * 
     * @return priceCycleId
     */
    public org.apache.axis.types.UnsignedByte getPriceCycleId() {
        return priceCycleId;
    }


    /**
     * Sets the priceCycleId value for this BalanceResult.
     * 
     * @param priceCycleId
     */
    public void setPriceCycleId(org.apache.axis.types.UnsignedByte priceCycleId) {
        this.priceCycleId = priceCycleId;
    }


    /**
     * Gets the reinvestDividend value for this BalanceResult.
     * 
     * @return reinvestDividend
     */
    public org.apache.axis.types.UnsignedByte getReinvestDividend() {
        return reinvestDividend;
    }


    /**
     * Sets the reinvestDividend value for this BalanceResult.
     * 
     * @param reinvestDividend
     */
    public void setReinvestDividend(org.apache.axis.types.UnsignedByte reinvestDividend) {
        this.reinvestDividend = reinvestDividend;
    }


    /**
     * Gets the reinvestSTCG value for this BalanceResult.
     * 
     * @return reinvestSTCG
     */
    public org.apache.axis.types.UnsignedByte getReinvestSTCG() {
        return reinvestSTCG;
    }


    /**
     * Sets the reinvestSTCG value for this BalanceResult.
     * 
     * @param reinvestSTCG
     */
    public void setReinvestSTCG(org.apache.axis.types.UnsignedByte reinvestSTCG) {
        this.reinvestSTCG = reinvestSTCG;
    }


    /**
     * Gets the reinvestLTCG value for this BalanceResult.
     * 
     * @return reinvestLTCG
     */
    public org.apache.axis.types.UnsignedByte getReinvestLTCG() {
        return reinvestLTCG;
    }


    /**
     * Sets the reinvestLTCG value for this BalanceResult.
     * 
     * @param reinvestLTCG
     */
    public void setReinvestLTCG(org.apache.axis.types.UnsignedByte reinvestLTCG) {
        this.reinvestLTCG = reinvestLTCG;
    }


    /**
     * Gets the fundUrl value for this BalanceResult.
     * 
     * @return fundUrl
     */
    public java.lang.String getFundUrl() {
        return fundUrl;
    }


    /**
     * Sets the fundUrl value for this BalanceResult.
     * 
     * @param fundUrl
     */
    public void setFundUrl(java.lang.String fundUrl) {
        this.fundUrl = fundUrl;
    }


    /**
     * Gets the redemptionAvailableShares value for this BalanceResult.
     * 
     * @return redemptionAvailableShares
     */
    public java.math.BigDecimal getRedemptionAvailableShares() {
        return redemptionAvailableShares;
    }


    /**
     * Sets the redemptionAvailableShares value for this BalanceResult.
     * 
     * @param redemptionAvailableShares
     */
    public void setRedemptionAvailableShares(java.math.BigDecimal redemptionAvailableShares) {
        this.redemptionAvailableShares = redemptionAvailableShares;
    }


    /**
     * Gets the exchangeAvailableShares value for this BalanceResult.
     * 
     * @return exchangeAvailableShares
     */
    public java.math.BigDecimal getExchangeAvailableShares() {
        return exchangeAvailableShares;
    }


    /**
     * Sets the exchangeAvailableShares value for this BalanceResult.
     * 
     * @param exchangeAvailableShares
     */
    public void setExchangeAvailableShares(java.math.BigDecimal exchangeAvailableShares) {
        this.exchangeAvailableShares = exchangeAvailableShares;
    }


    /**
     * Gets the redemptionAvailableBalance value for this BalanceResult.
     * 
     * @return redemptionAvailableBalance
     */
    public java.math.BigDecimal getRedemptionAvailableBalance() {
        return redemptionAvailableBalance;
    }


    /**
     * Sets the redemptionAvailableBalance value for this BalanceResult.
     * 
     * @param redemptionAvailableBalance
     */
    public void setRedemptionAvailableBalance(java.math.BigDecimal redemptionAvailableBalance) {
        this.redemptionAvailableBalance = redemptionAvailableBalance;
    }


    /**
     * Gets the exchangeAvailableBalance value for this BalanceResult.
     * 
     * @return exchangeAvailableBalance
     */
    public java.math.BigDecimal getExchangeAvailableBalance() {
        return exchangeAvailableBalance;
    }


    /**
     * Sets the exchangeAvailableBalance value for this BalanceResult.
     * 
     * @param exchangeAvailableBalance
     */
    public void setExchangeAvailableBalance(java.math.BigDecimal exchangeAvailableBalance) {
        this.exchangeAvailableBalance = exchangeAvailableBalance;
    }


    /**
     * Gets the lastCBAComputedShareAmount value for this BalanceResult.
     * 
     * @return lastCBAComputedShareAmount
     */
    public java.math.BigDecimal getLastCBAComputedShareAmount() {
        return lastCBAComputedShareAmount;
    }


    /**
     * Sets the lastCBAComputedShareAmount value for this BalanceResult.
     * 
     * @param lastCBAComputedShareAmount
     */
    public void setLastCBAComputedShareAmount(java.math.BigDecimal lastCBAComputedShareAmount) {
        this.lastCBAComputedShareAmount = lastCBAComputedShareAmount;
    }


    /**
     * Gets the lastCostbasisComputedDate value for this BalanceResult.
     * 
     * @return lastCostbasisComputedDate
     */
    public java.util.Calendar getLastCostbasisComputedDate() {
        return lastCostbasisComputedDate;
    }


    /**
     * Sets the lastCostbasisComputedDate value for this BalanceResult.
     * 
     * @param lastCostbasisComputedDate
     */
    public void setLastCostbasisComputedDate(java.util.Calendar lastCostbasisComputedDate) {
        this.lastCostbasisComputedDate = lastCostbasisComputedDate;
    }


    /**
     * Gets the CBDMIdForCoveredShares value for this BalanceResult.
     * 
     * @return CBDMIdForCoveredShares
     */
    public short getCBDMIdForCoveredShares() {
        return CBDMIdForCoveredShares;
    }


    /**
     * Sets the CBDMIdForCoveredShares value for this BalanceResult.
     * 
     * @param CBDMIdForCoveredShares
     */
    public void setCBDMIdForCoveredShares(short CBDMIdForCoveredShares) {
        this.CBDMIdForCoveredShares = CBDMIdForCoveredShares;
    }


    /**
     * Gets the CBDMNameForCoveredShares value for this BalanceResult.
     * 
     * @return CBDMNameForCoveredShares
     */
    public java.lang.String getCBDMNameForCoveredShares() {
        return CBDMNameForCoveredShares;
    }


    /**
     * Sets the CBDMNameForCoveredShares value for this BalanceResult.
     * 
     * @param CBDMNameForCoveredShares
     */
    public void setCBDMNameForCoveredShares(java.lang.String CBDMNameForCoveredShares) {
        this.CBDMNameForCoveredShares = CBDMNameForCoveredShares;
    }


    /**
     * Gets the CBDMIdForNonCoveredShares value for this BalanceResult.
     * 
     * @return CBDMIdForNonCoveredShares
     */
    public short getCBDMIdForNonCoveredShares() {
        return CBDMIdForNonCoveredShares;
    }


    /**
     * Sets the CBDMIdForNonCoveredShares value for this BalanceResult.
     * 
     * @param CBDMIdForNonCoveredShares
     */
    public void setCBDMIdForNonCoveredShares(short CBDMIdForNonCoveredShares) {
        this.CBDMIdForNonCoveredShares = CBDMIdForNonCoveredShares;
    }


    /**
     * Gets the CBDMNameForNonCoveredShares value for this BalanceResult.
     * 
     * @return CBDMNameForNonCoveredShares
     */
    public java.lang.String getCBDMNameForNonCoveredShares() {
        return CBDMNameForNonCoveredShares;
    }


    /**
     * Sets the CBDMNameForNonCoveredShares value for this BalanceResult.
     * 
     * @param CBDMNameForNonCoveredShares
     */
    public void setCBDMNameForNonCoveredShares(java.lang.String CBDMNameForNonCoveredShares) {
        this.CBDMNameForNonCoveredShares = CBDMNameForNonCoveredShares;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalanceResult)) return false;
        BalanceResult other = (BalanceResult) obj;
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
            ((this.fundShortName==null && other.getFundShortName()==null) || 
             (this.fundShortName!=null &&
              this.fundShortName.equals(other.getFundShortName()))) &&
            ((this.inceptionDate==null && other.getInceptionDate()==null) || 
             (this.inceptionDate!=null &&
              this.inceptionDate.equals(other.getInceptionDate()))) &&
            ((this.totalShares==null && other.getTotalShares()==null) || 
             (this.totalShares!=null &&
              this.totalShares.equals(other.getTotalShares()))) &&
            ((this.beginningTotalShares==null && other.getBeginningTotalShares()==null) || 
             (this.beginningTotalShares!=null &&
              this.beginningTotalShares.equals(other.getBeginningTotalShares()))) &&
            ((this.beginningNAVPricePerShare==null && other.getBeginningNAVPricePerShare()==null) || 
             (this.beginningNAVPricePerShare!=null &&
              this.beginningNAVPricePerShare.equals(other.getBeginningNAVPricePerShare()))) &&
            ((this.postedSettledShares==null && other.getPostedSettledShares()==null) || 
             (this.postedSettledShares!=null &&
              this.postedSettledShares.equals(other.getPostedSettledShares()))) &&
            ((this.unsettledShares==null && other.getUnsettledShares()==null) || 
             (this.unsettledShares!=null &&
              this.unsettledShares.equals(other.getUnsettledShares()))) &&
            ((this.totalValue==null && other.getTotalValue()==null) || 
             (this.totalValue!=null &&
              this.totalValue.equals(other.getTotalValue()))) &&
            ((this.YTDIncomeDividend==null && other.getYTDIncomeDividend()==null) || 
             (this.YTDIncomeDividend!=null &&
              this.YTDIncomeDividend.equals(other.getYTDIncomeDividend()))) &&
            ((this.YTDSTCG==null && other.getYTDSTCG()==null) || 
             (this.YTDSTCG!=null &&
              this.YTDSTCG.equals(other.getYTDSTCG()))) &&
            ((this.YTDLTCG==null && other.getYTDLTCG()==null) || 
             (this.YTDLTCG!=null &&
              this.YTDLTCG.equals(other.getYTDLTCG()))) &&
            ((this.YTDMTCG==null && other.getYTDMTCG()==null) || 
             (this.YTDMTCG!=null &&
              this.YTDMTCG.equals(other.getYTDMTCG()))) &&
            ((this.YTDReturnofCapital==null && other.getYTDReturnofCapital()==null) || 
             (this.YTDReturnofCapital!=null &&
              this.YTDReturnofCapital.equals(other.getYTDReturnofCapital()))) &&
            ((this.marketValue==null && other.getMarketValue()==null) || 
             (this.marketValue!=null &&
              this.marketValue.equals(other.getMarketValue()))) &&
            ((this.totalPhysicalShares==null && other.getTotalPhysicalShares()==null) || 
             (this.totalPhysicalShares!=null &&
              this.totalPhysicalShares.equals(other.getTotalPhysicalShares()))) &&
            ((this.navPricePerShare==null && other.getNavPricePerShare()==null) || 
             (this.navPricePerShare!=null &&
              this.navPricePerShare.equals(other.getNavPricePerShare()))) &&
            ((this.priceDate==null && other.getPriceDate()==null) || 
             (this.priceDate!=null &&
              this.priceDate.equals(other.getPriceDate()))) &&
            ((this.accountNumber==null && other.getAccountNumber()==null) || 
             (this.accountNumber!=null &&
              this.accountNumber.equals(other.getAccountNumber()))) &&
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.priceCycleId==null && other.getPriceCycleId()==null) || 
             (this.priceCycleId!=null &&
              this.priceCycleId.equals(other.getPriceCycleId()))) &&
            ((this.reinvestDividend==null && other.getReinvestDividend()==null) || 
             (this.reinvestDividend!=null &&
              this.reinvestDividend.equals(other.getReinvestDividend()))) &&
            ((this.reinvestSTCG==null && other.getReinvestSTCG()==null) || 
             (this.reinvestSTCG!=null &&
              this.reinvestSTCG.equals(other.getReinvestSTCG()))) &&
            ((this.reinvestLTCG==null && other.getReinvestLTCG()==null) || 
             (this.reinvestLTCG!=null &&
              this.reinvestLTCG.equals(other.getReinvestLTCG()))) &&
            ((this.fundUrl==null && other.getFundUrl()==null) || 
             (this.fundUrl!=null &&
              this.fundUrl.equals(other.getFundUrl()))) &&
            ((this.redemptionAvailableShares==null && other.getRedemptionAvailableShares()==null) || 
             (this.redemptionAvailableShares!=null &&
              this.redemptionAvailableShares.equals(other.getRedemptionAvailableShares()))) &&
            ((this.exchangeAvailableShares==null && other.getExchangeAvailableShares()==null) || 
             (this.exchangeAvailableShares!=null &&
              this.exchangeAvailableShares.equals(other.getExchangeAvailableShares()))) &&
            ((this.redemptionAvailableBalance==null && other.getRedemptionAvailableBalance()==null) || 
             (this.redemptionAvailableBalance!=null &&
              this.redemptionAvailableBalance.equals(other.getRedemptionAvailableBalance()))) &&
            ((this.exchangeAvailableBalance==null && other.getExchangeAvailableBalance()==null) || 
             (this.exchangeAvailableBalance!=null &&
              this.exchangeAvailableBalance.equals(other.getExchangeAvailableBalance()))) &&
            ((this.lastCBAComputedShareAmount==null && other.getLastCBAComputedShareAmount()==null) || 
             (this.lastCBAComputedShareAmount!=null &&
              this.lastCBAComputedShareAmount.equals(other.getLastCBAComputedShareAmount()))) &&
            ((this.lastCostbasisComputedDate==null && other.getLastCostbasisComputedDate()==null) || 
             (this.lastCostbasisComputedDate!=null &&
              this.lastCostbasisComputedDate.equals(other.getLastCostbasisComputedDate()))) &&
            this.CBDMIdForCoveredShares == other.getCBDMIdForCoveredShares() &&
            ((this.CBDMNameForCoveredShares==null && other.getCBDMNameForCoveredShares()==null) || 
             (this.CBDMNameForCoveredShares!=null &&
              this.CBDMNameForCoveredShares.equals(other.getCBDMNameForCoveredShares()))) &&
            this.CBDMIdForNonCoveredShares == other.getCBDMIdForNonCoveredShares() &&
            ((this.CBDMNameForNonCoveredShares==null && other.getCBDMNameForNonCoveredShares()==null) || 
             (this.CBDMNameForNonCoveredShares!=null &&
              this.CBDMNameForNonCoveredShares.equals(other.getCBDMNameForNonCoveredShares())));
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
        if (getFundShortName() != null) {
            _hashCode += getFundShortName().hashCode();
        }
        if (getInceptionDate() != null) {
            _hashCode += getInceptionDate().hashCode();
        }
        if (getTotalShares() != null) {
            _hashCode += getTotalShares().hashCode();
        }
        if (getBeginningTotalShares() != null) {
            _hashCode += getBeginningTotalShares().hashCode();
        }
        if (getBeginningNAVPricePerShare() != null) {
            _hashCode += getBeginningNAVPricePerShare().hashCode();
        }
        if (getPostedSettledShares() != null) {
            _hashCode += getPostedSettledShares().hashCode();
        }
        if (getUnsettledShares() != null) {
            _hashCode += getUnsettledShares().hashCode();
        }
        if (getTotalValue() != null) {
            _hashCode += getTotalValue().hashCode();
        }
        if (getYTDIncomeDividend() != null) {
            _hashCode += getYTDIncomeDividend().hashCode();
        }
        if (getYTDSTCG() != null) {
            _hashCode += getYTDSTCG().hashCode();
        }
        if (getYTDLTCG() != null) {
            _hashCode += getYTDLTCG().hashCode();
        }
        if (getYTDMTCG() != null) {
            _hashCode += getYTDMTCG().hashCode();
        }
        if (getYTDReturnofCapital() != null) {
            _hashCode += getYTDReturnofCapital().hashCode();
        }
        if (getMarketValue() != null) {
            _hashCode += getMarketValue().hashCode();
        }
        if (getTotalPhysicalShares() != null) {
            _hashCode += getTotalPhysicalShares().hashCode();
        }
        if (getNavPricePerShare() != null) {
            _hashCode += getNavPricePerShare().hashCode();
        }
        if (getPriceDate() != null) {
            _hashCode += getPriceDate().hashCode();
        }
        if (getAccountNumber() != null) {
            _hashCode += getAccountNumber().hashCode();
        }
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getPriceCycleId() != null) {
            _hashCode += getPriceCycleId().hashCode();
        }
        if (getReinvestDividend() != null) {
            _hashCode += getReinvestDividend().hashCode();
        }
        if (getReinvestSTCG() != null) {
            _hashCode += getReinvestSTCG().hashCode();
        }
        if (getReinvestLTCG() != null) {
            _hashCode += getReinvestLTCG().hashCode();
        }
        if (getFundUrl() != null) {
            _hashCode += getFundUrl().hashCode();
        }
        if (getRedemptionAvailableShares() != null) {
            _hashCode += getRedemptionAvailableShares().hashCode();
        }
        if (getExchangeAvailableShares() != null) {
            _hashCode += getExchangeAvailableShares().hashCode();
        }
        if (getRedemptionAvailableBalance() != null) {
            _hashCode += getRedemptionAvailableBalance().hashCode();
        }
        if (getExchangeAvailableBalance() != null) {
            _hashCode += getExchangeAvailableBalance().hashCode();
        }
        if (getLastCBAComputedShareAmount() != null) {
            _hashCode += getLastCBAComputedShareAmount().hashCode();
        }
        if (getLastCostbasisComputedDate() != null) {
            _hashCode += getLastCostbasisComputedDate().hashCode();
        }
        _hashCode += getCBDMIdForCoveredShares();
        if (getCBDMNameForCoveredShares() != null) {
            _hashCode += getCBDMNameForCoveredShares().hashCode();
        }
        _hashCode += getCBDMIdForNonCoveredShares();
        if (getCBDMNameForNonCoveredShares() != null) {
            _hashCode += getCBDMNameForNonCoveredShares().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalanceResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BalanceResult"));
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
        elemField.setFieldName("fundShortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inceptionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "InceptionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginningTotalShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeginningTotalShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("beginningNAVPricePerShare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "BeginningNAVPricePerShare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postedSettledShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PostedSettledShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unsettledShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UnsettledShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDIncomeDividend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDIncomeDividend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDSTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDSTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDLTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDLTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDMTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDMTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("YTDReturnofCapital");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "YTDReturnofCapital"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("marketValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MarketValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalPhysicalShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalPhysicalShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("navPricePerShare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NavPricePerShare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriceDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priceCycleId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriceCycleId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reinvestDividend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReinvestDividend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reinvestSTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReinvestSTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reinvestLTCG");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ReinvestLTCG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionAvailableShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionAvailableShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchangeAvailableShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExchangeAvailableShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionAvailableBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionAvailableBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("exchangeAvailableBalance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ExchangeAvailableBalance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastCBAComputedShareAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LastCBAComputedShareAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastCostbasisComputedDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LastCostbasisComputedDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMIdForCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMIdForCoveredShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMNameForCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMNameForCoveredShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMIdForNonCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMIdForNonCoveredShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CBDMNameForNonCoveredShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CBDMNameForNonCoveredShares"));
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
