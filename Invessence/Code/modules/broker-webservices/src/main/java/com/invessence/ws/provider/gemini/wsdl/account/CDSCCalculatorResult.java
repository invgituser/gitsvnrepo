/**
 * CDSCCalculatorResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class CDSCCalculatorResult  implements java.io.Serializable {
    private java.math.BigDecimal grossSharesRedeemed;

    private java.math.BigDecimal redemptionShares;

    private java.math.BigDecimal moneyMarketShares;

    private java.math.BigDecimal frontLoadNoLotShares;

    private java.math.BigDecimal noLoadNoLotShares;

    private java.math.BigDecimal CDSCApplicableShares;

    private java.math.BigDecimal CDSCReinvestedShares;

    private java.math.BigDecimal CDSCExcludedShares;

    private java.math.BigDecimal FLCDSCApplicableShares;

    private java.math.BigDecimal FLCDSCReinvestedShares;

    private java.math.BigDecimal FLCDSCExcludedShares;

    private java.math.BigDecimal noLoadLotShares;

    private java.util.Calendar valuationDate;

    private java.math.BigDecimal valuationPrice;

    private java.math.BigDecimal redemptionAmount;

    private java.math.BigDecimal CDSCAmount;

    private java.math.BigDecimal settlementAmount;

    private org.apache.axis.types.UnsignedByte settlementAmountIndicator;

    private java.lang.String settlementAmountIndicatorDescription;

    private java.lang.String fundBaseCurrencyCode;

    private java.lang.String fundShortName;

    private com.invessence.ws.provider.gemini.wsdl.account.LotDetailResult[] lotDetails;

    private com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus;

    public CDSCCalculatorResult() {
    }

    public CDSCCalculatorResult(
           java.math.BigDecimal grossSharesRedeemed,
           java.math.BigDecimal redemptionShares,
           java.math.BigDecimal moneyMarketShares,
           java.math.BigDecimal frontLoadNoLotShares,
           java.math.BigDecimal noLoadNoLotShares,
           java.math.BigDecimal CDSCApplicableShares,
           java.math.BigDecimal CDSCReinvestedShares,
           java.math.BigDecimal CDSCExcludedShares,
           java.math.BigDecimal FLCDSCApplicableShares,
           java.math.BigDecimal FLCDSCReinvestedShares,
           java.math.BigDecimal FLCDSCExcludedShares,
           java.math.BigDecimal noLoadLotShares,
           java.util.Calendar valuationDate,
           java.math.BigDecimal valuationPrice,
           java.math.BigDecimal redemptionAmount,
           java.math.BigDecimal CDSCAmount,
           java.math.BigDecimal settlementAmount,
           org.apache.axis.types.UnsignedByte settlementAmountIndicator,
           java.lang.String settlementAmountIndicatorDescription,
           java.lang.String fundBaseCurrencyCode,
           java.lang.String fundShortName,
           com.invessence.ws.provider.gemini.wsdl.account.LotDetailResult[] lotDetails,
           com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
           this.grossSharesRedeemed = grossSharesRedeemed;
           this.redemptionShares = redemptionShares;
           this.moneyMarketShares = moneyMarketShares;
           this.frontLoadNoLotShares = frontLoadNoLotShares;
           this.noLoadNoLotShares = noLoadNoLotShares;
           this.CDSCApplicableShares = CDSCApplicableShares;
           this.CDSCReinvestedShares = CDSCReinvestedShares;
           this.CDSCExcludedShares = CDSCExcludedShares;
           this.FLCDSCApplicableShares = FLCDSCApplicableShares;
           this.FLCDSCReinvestedShares = FLCDSCReinvestedShares;
           this.FLCDSCExcludedShares = FLCDSCExcludedShares;
           this.noLoadLotShares = noLoadLotShares;
           this.valuationDate = valuationDate;
           this.valuationPrice = valuationPrice;
           this.redemptionAmount = redemptionAmount;
           this.CDSCAmount = CDSCAmount;
           this.settlementAmount = settlementAmount;
           this.settlementAmountIndicator = settlementAmountIndicator;
           this.settlementAmountIndicatorDescription = settlementAmountIndicatorDescription;
           this.fundBaseCurrencyCode = fundBaseCurrencyCode;
           this.fundShortName = fundShortName;
           this.lotDetails = lotDetails;
           this.errorStatus = errorStatus;
    }


    /**
     * Gets the grossSharesRedeemed value for this CDSCCalculatorResult.
     * 
     * @return grossSharesRedeemed
     */
    public java.math.BigDecimal getGrossSharesRedeemed() {
        return grossSharesRedeemed;
    }


    /**
     * Sets the grossSharesRedeemed value for this CDSCCalculatorResult.
     * 
     * @param grossSharesRedeemed
     */
    public void setGrossSharesRedeemed(java.math.BigDecimal grossSharesRedeemed) {
        this.grossSharesRedeemed = grossSharesRedeemed;
    }


    /**
     * Gets the redemptionShares value for this CDSCCalculatorResult.
     * 
     * @return redemptionShares
     */
    public java.math.BigDecimal getRedemptionShares() {
        return redemptionShares;
    }


    /**
     * Sets the redemptionShares value for this CDSCCalculatorResult.
     * 
     * @param redemptionShares
     */
    public void setRedemptionShares(java.math.BigDecimal redemptionShares) {
        this.redemptionShares = redemptionShares;
    }


    /**
     * Gets the moneyMarketShares value for this CDSCCalculatorResult.
     * 
     * @return moneyMarketShares
     */
    public java.math.BigDecimal getMoneyMarketShares() {
        return moneyMarketShares;
    }


    /**
     * Sets the moneyMarketShares value for this CDSCCalculatorResult.
     * 
     * @param moneyMarketShares
     */
    public void setMoneyMarketShares(java.math.BigDecimal moneyMarketShares) {
        this.moneyMarketShares = moneyMarketShares;
    }


    /**
     * Gets the frontLoadNoLotShares value for this CDSCCalculatorResult.
     * 
     * @return frontLoadNoLotShares
     */
    public java.math.BigDecimal getFrontLoadNoLotShares() {
        return frontLoadNoLotShares;
    }


    /**
     * Sets the frontLoadNoLotShares value for this CDSCCalculatorResult.
     * 
     * @param frontLoadNoLotShares
     */
    public void setFrontLoadNoLotShares(java.math.BigDecimal frontLoadNoLotShares) {
        this.frontLoadNoLotShares = frontLoadNoLotShares;
    }


    /**
     * Gets the noLoadNoLotShares value for this CDSCCalculatorResult.
     * 
     * @return noLoadNoLotShares
     */
    public java.math.BigDecimal getNoLoadNoLotShares() {
        return noLoadNoLotShares;
    }


    /**
     * Sets the noLoadNoLotShares value for this CDSCCalculatorResult.
     * 
     * @param noLoadNoLotShares
     */
    public void setNoLoadNoLotShares(java.math.BigDecimal noLoadNoLotShares) {
        this.noLoadNoLotShares = noLoadNoLotShares;
    }


    /**
     * Gets the CDSCApplicableShares value for this CDSCCalculatorResult.
     * 
     * @return CDSCApplicableShares
     */
    public java.math.BigDecimal getCDSCApplicableShares() {
        return CDSCApplicableShares;
    }


    /**
     * Sets the CDSCApplicableShares value for this CDSCCalculatorResult.
     * 
     * @param CDSCApplicableShares
     */
    public void setCDSCApplicableShares(java.math.BigDecimal CDSCApplicableShares) {
        this.CDSCApplicableShares = CDSCApplicableShares;
    }


    /**
     * Gets the CDSCReinvestedShares value for this CDSCCalculatorResult.
     * 
     * @return CDSCReinvestedShares
     */
    public java.math.BigDecimal getCDSCReinvestedShares() {
        return CDSCReinvestedShares;
    }


    /**
     * Sets the CDSCReinvestedShares value for this CDSCCalculatorResult.
     * 
     * @param CDSCReinvestedShares
     */
    public void setCDSCReinvestedShares(java.math.BigDecimal CDSCReinvestedShares) {
        this.CDSCReinvestedShares = CDSCReinvestedShares;
    }


    /**
     * Gets the CDSCExcludedShares value for this CDSCCalculatorResult.
     * 
     * @return CDSCExcludedShares
     */
    public java.math.BigDecimal getCDSCExcludedShares() {
        return CDSCExcludedShares;
    }


    /**
     * Sets the CDSCExcludedShares value for this CDSCCalculatorResult.
     * 
     * @param CDSCExcludedShares
     */
    public void setCDSCExcludedShares(java.math.BigDecimal CDSCExcludedShares) {
        this.CDSCExcludedShares = CDSCExcludedShares;
    }


    /**
     * Gets the FLCDSCApplicableShares value for this CDSCCalculatorResult.
     * 
     * @return FLCDSCApplicableShares
     */
    public java.math.BigDecimal getFLCDSCApplicableShares() {
        return FLCDSCApplicableShares;
    }


    /**
     * Sets the FLCDSCApplicableShares value for this CDSCCalculatorResult.
     * 
     * @param FLCDSCApplicableShares
     */
    public void setFLCDSCApplicableShares(java.math.BigDecimal FLCDSCApplicableShares) {
        this.FLCDSCApplicableShares = FLCDSCApplicableShares;
    }


    /**
     * Gets the FLCDSCReinvestedShares value for this CDSCCalculatorResult.
     * 
     * @return FLCDSCReinvestedShares
     */
    public java.math.BigDecimal getFLCDSCReinvestedShares() {
        return FLCDSCReinvestedShares;
    }


    /**
     * Sets the FLCDSCReinvestedShares value for this CDSCCalculatorResult.
     * 
     * @param FLCDSCReinvestedShares
     */
    public void setFLCDSCReinvestedShares(java.math.BigDecimal FLCDSCReinvestedShares) {
        this.FLCDSCReinvestedShares = FLCDSCReinvestedShares;
    }


    /**
     * Gets the FLCDSCExcludedShares value for this CDSCCalculatorResult.
     * 
     * @return FLCDSCExcludedShares
     */
    public java.math.BigDecimal getFLCDSCExcludedShares() {
        return FLCDSCExcludedShares;
    }


    /**
     * Sets the FLCDSCExcludedShares value for this CDSCCalculatorResult.
     * 
     * @param FLCDSCExcludedShares
     */
    public void setFLCDSCExcludedShares(java.math.BigDecimal FLCDSCExcludedShares) {
        this.FLCDSCExcludedShares = FLCDSCExcludedShares;
    }


    /**
     * Gets the noLoadLotShares value for this CDSCCalculatorResult.
     * 
     * @return noLoadLotShares
     */
    public java.math.BigDecimal getNoLoadLotShares() {
        return noLoadLotShares;
    }


    /**
     * Sets the noLoadLotShares value for this CDSCCalculatorResult.
     * 
     * @param noLoadLotShares
     */
    public void setNoLoadLotShares(java.math.BigDecimal noLoadLotShares) {
        this.noLoadLotShares = noLoadLotShares;
    }


    /**
     * Gets the valuationDate value for this CDSCCalculatorResult.
     * 
     * @return valuationDate
     */
    public java.util.Calendar getValuationDate() {
        return valuationDate;
    }


    /**
     * Sets the valuationDate value for this CDSCCalculatorResult.
     * 
     * @param valuationDate
     */
    public void setValuationDate(java.util.Calendar valuationDate) {
        this.valuationDate = valuationDate;
    }


    /**
     * Gets the valuationPrice value for this CDSCCalculatorResult.
     * 
     * @return valuationPrice
     */
    public java.math.BigDecimal getValuationPrice() {
        return valuationPrice;
    }


    /**
     * Sets the valuationPrice value for this CDSCCalculatorResult.
     * 
     * @param valuationPrice
     */
    public void setValuationPrice(java.math.BigDecimal valuationPrice) {
        this.valuationPrice = valuationPrice;
    }


    /**
     * Gets the redemptionAmount value for this CDSCCalculatorResult.
     * 
     * @return redemptionAmount
     */
    public java.math.BigDecimal getRedemptionAmount() {
        return redemptionAmount;
    }


    /**
     * Sets the redemptionAmount value for this CDSCCalculatorResult.
     * 
     * @param redemptionAmount
     */
    public void setRedemptionAmount(java.math.BigDecimal redemptionAmount) {
        this.redemptionAmount = redemptionAmount;
    }


    /**
     * Gets the CDSCAmount value for this CDSCCalculatorResult.
     * 
     * @return CDSCAmount
     */
    public java.math.BigDecimal getCDSCAmount() {
        return CDSCAmount;
    }


    /**
     * Sets the CDSCAmount value for this CDSCCalculatorResult.
     * 
     * @param CDSCAmount
     */
    public void setCDSCAmount(java.math.BigDecimal CDSCAmount) {
        this.CDSCAmount = CDSCAmount;
    }


    /**
     * Gets the settlementAmount value for this CDSCCalculatorResult.
     * 
     * @return settlementAmount
     */
    public java.math.BigDecimal getSettlementAmount() {
        return settlementAmount;
    }


    /**
     * Sets the settlementAmount value for this CDSCCalculatorResult.
     * 
     * @param settlementAmount
     */
    public void setSettlementAmount(java.math.BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount;
    }


    /**
     * Gets the settlementAmountIndicator value for this CDSCCalculatorResult.
     * 
     * @return settlementAmountIndicator
     */
    public org.apache.axis.types.UnsignedByte getSettlementAmountIndicator() {
        return settlementAmountIndicator;
    }


    /**
     * Sets the settlementAmountIndicator value for this CDSCCalculatorResult.
     * 
     * @param settlementAmountIndicator
     */
    public void setSettlementAmountIndicator(org.apache.axis.types.UnsignedByte settlementAmountIndicator) {
        this.settlementAmountIndicator = settlementAmountIndicator;
    }


    /**
     * Gets the settlementAmountIndicatorDescription value for this CDSCCalculatorResult.
     * 
     * @return settlementAmountIndicatorDescription
     */
    public java.lang.String getSettlementAmountIndicatorDescription() {
        return settlementAmountIndicatorDescription;
    }


    /**
     * Sets the settlementAmountIndicatorDescription value for this CDSCCalculatorResult.
     * 
     * @param settlementAmountIndicatorDescription
     */
    public void setSettlementAmountIndicatorDescription(java.lang.String settlementAmountIndicatorDescription) {
        this.settlementAmountIndicatorDescription = settlementAmountIndicatorDescription;
    }


    /**
     * Gets the fundBaseCurrencyCode value for this CDSCCalculatorResult.
     * 
     * @return fundBaseCurrencyCode
     */
    public java.lang.String getFundBaseCurrencyCode() {
        return fundBaseCurrencyCode;
    }


    /**
     * Sets the fundBaseCurrencyCode value for this CDSCCalculatorResult.
     * 
     * @param fundBaseCurrencyCode
     */
    public void setFundBaseCurrencyCode(java.lang.String fundBaseCurrencyCode) {
        this.fundBaseCurrencyCode = fundBaseCurrencyCode;
    }


    /**
     * Gets the fundShortName value for this CDSCCalculatorResult.
     * 
     * @return fundShortName
     */
    public java.lang.String getFundShortName() {
        return fundShortName;
    }


    /**
     * Sets the fundShortName value for this CDSCCalculatorResult.
     * 
     * @param fundShortName
     */
    public void setFundShortName(java.lang.String fundShortName) {
        this.fundShortName = fundShortName;
    }


    /**
     * Gets the lotDetails value for this CDSCCalculatorResult.
     * 
     * @return lotDetails
     */
    public com.invessence.ws.provider.gemini.wsdl.account.LotDetailResult[] getLotDetails() {
        return lotDetails;
    }


    /**
     * Sets the lotDetails value for this CDSCCalculatorResult.
     * 
     * @param lotDetails
     */
    public void setLotDetails(com.invessence.ws.provider.gemini.wsdl.account.LotDetailResult[] lotDetails) {
        this.lotDetails = lotDetails;
    }


    /**
     * Gets the errorStatus value for this CDSCCalculatorResult.
     * 
     * @return errorStatus
     */
    public com.invessence.ws.provider.gemini.wsdl.account.Status getErrorStatus() {
        return errorStatus;
    }


    /**
     * Sets the errorStatus value for this CDSCCalculatorResult.
     * 
     * @param errorStatus
     */
    public void setErrorStatus(com.invessence.ws.provider.gemini.wsdl.account.Status errorStatus) {
        this.errorStatus = errorStatus;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CDSCCalculatorResult)) return false;
        CDSCCalculatorResult other = (CDSCCalculatorResult) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.grossSharesRedeemed==null && other.getGrossSharesRedeemed()==null) || 
             (this.grossSharesRedeemed!=null &&
              this.grossSharesRedeemed.equals(other.getGrossSharesRedeemed()))) &&
            ((this.redemptionShares==null && other.getRedemptionShares()==null) || 
             (this.redemptionShares!=null &&
              this.redemptionShares.equals(other.getRedemptionShares()))) &&
            ((this.moneyMarketShares==null && other.getMoneyMarketShares()==null) || 
             (this.moneyMarketShares!=null &&
              this.moneyMarketShares.equals(other.getMoneyMarketShares()))) &&
            ((this.frontLoadNoLotShares==null && other.getFrontLoadNoLotShares()==null) || 
             (this.frontLoadNoLotShares!=null &&
              this.frontLoadNoLotShares.equals(other.getFrontLoadNoLotShares()))) &&
            ((this.noLoadNoLotShares==null && other.getNoLoadNoLotShares()==null) || 
             (this.noLoadNoLotShares!=null &&
              this.noLoadNoLotShares.equals(other.getNoLoadNoLotShares()))) &&
            ((this.CDSCApplicableShares==null && other.getCDSCApplicableShares()==null) || 
             (this.CDSCApplicableShares!=null &&
              this.CDSCApplicableShares.equals(other.getCDSCApplicableShares()))) &&
            ((this.CDSCReinvestedShares==null && other.getCDSCReinvestedShares()==null) || 
             (this.CDSCReinvestedShares!=null &&
              this.CDSCReinvestedShares.equals(other.getCDSCReinvestedShares()))) &&
            ((this.CDSCExcludedShares==null && other.getCDSCExcludedShares()==null) || 
             (this.CDSCExcludedShares!=null &&
              this.CDSCExcludedShares.equals(other.getCDSCExcludedShares()))) &&
            ((this.FLCDSCApplicableShares==null && other.getFLCDSCApplicableShares()==null) || 
             (this.FLCDSCApplicableShares!=null &&
              this.FLCDSCApplicableShares.equals(other.getFLCDSCApplicableShares()))) &&
            ((this.FLCDSCReinvestedShares==null && other.getFLCDSCReinvestedShares()==null) || 
             (this.FLCDSCReinvestedShares!=null &&
              this.FLCDSCReinvestedShares.equals(other.getFLCDSCReinvestedShares()))) &&
            ((this.FLCDSCExcludedShares==null && other.getFLCDSCExcludedShares()==null) || 
             (this.FLCDSCExcludedShares!=null &&
              this.FLCDSCExcludedShares.equals(other.getFLCDSCExcludedShares()))) &&
            ((this.noLoadLotShares==null && other.getNoLoadLotShares()==null) || 
             (this.noLoadLotShares!=null &&
              this.noLoadLotShares.equals(other.getNoLoadLotShares()))) &&
            ((this.valuationDate==null && other.getValuationDate()==null) || 
             (this.valuationDate!=null &&
              this.valuationDate.equals(other.getValuationDate()))) &&
            ((this.valuationPrice==null && other.getValuationPrice()==null) || 
             (this.valuationPrice!=null &&
              this.valuationPrice.equals(other.getValuationPrice()))) &&
            ((this.redemptionAmount==null && other.getRedemptionAmount()==null) || 
             (this.redemptionAmount!=null &&
              this.redemptionAmount.equals(other.getRedemptionAmount()))) &&
            ((this.CDSCAmount==null && other.getCDSCAmount()==null) || 
             (this.CDSCAmount!=null &&
              this.CDSCAmount.equals(other.getCDSCAmount()))) &&
            ((this.settlementAmount==null && other.getSettlementAmount()==null) || 
             (this.settlementAmount!=null &&
              this.settlementAmount.equals(other.getSettlementAmount()))) &&
            ((this.settlementAmountIndicator==null && other.getSettlementAmountIndicator()==null) || 
             (this.settlementAmountIndicator!=null &&
              this.settlementAmountIndicator.equals(other.getSettlementAmountIndicator()))) &&
            ((this.settlementAmountIndicatorDescription==null && other.getSettlementAmountIndicatorDescription()==null) || 
             (this.settlementAmountIndicatorDescription!=null &&
              this.settlementAmountIndicatorDescription.equals(other.getSettlementAmountIndicatorDescription()))) &&
            ((this.fundBaseCurrencyCode==null && other.getFundBaseCurrencyCode()==null) || 
             (this.fundBaseCurrencyCode!=null &&
              this.fundBaseCurrencyCode.equals(other.getFundBaseCurrencyCode()))) &&
            ((this.fundShortName==null && other.getFundShortName()==null) || 
             (this.fundShortName!=null &&
              this.fundShortName.equals(other.getFundShortName()))) &&
            ((this.lotDetails==null && other.getLotDetails()==null) || 
             (this.lotDetails!=null &&
              java.util.Arrays.equals(this.lotDetails, other.getLotDetails()))) &&
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
        if (getGrossSharesRedeemed() != null) {
            _hashCode += getGrossSharesRedeemed().hashCode();
        }
        if (getRedemptionShares() != null) {
            _hashCode += getRedemptionShares().hashCode();
        }
        if (getMoneyMarketShares() != null) {
            _hashCode += getMoneyMarketShares().hashCode();
        }
        if (getFrontLoadNoLotShares() != null) {
            _hashCode += getFrontLoadNoLotShares().hashCode();
        }
        if (getNoLoadNoLotShares() != null) {
            _hashCode += getNoLoadNoLotShares().hashCode();
        }
        if (getCDSCApplicableShares() != null) {
            _hashCode += getCDSCApplicableShares().hashCode();
        }
        if (getCDSCReinvestedShares() != null) {
            _hashCode += getCDSCReinvestedShares().hashCode();
        }
        if (getCDSCExcludedShares() != null) {
            _hashCode += getCDSCExcludedShares().hashCode();
        }
        if (getFLCDSCApplicableShares() != null) {
            _hashCode += getFLCDSCApplicableShares().hashCode();
        }
        if (getFLCDSCReinvestedShares() != null) {
            _hashCode += getFLCDSCReinvestedShares().hashCode();
        }
        if (getFLCDSCExcludedShares() != null) {
            _hashCode += getFLCDSCExcludedShares().hashCode();
        }
        if (getNoLoadLotShares() != null) {
            _hashCode += getNoLoadLotShares().hashCode();
        }
        if (getValuationDate() != null) {
            _hashCode += getValuationDate().hashCode();
        }
        if (getValuationPrice() != null) {
            _hashCode += getValuationPrice().hashCode();
        }
        if (getRedemptionAmount() != null) {
            _hashCode += getRedemptionAmount().hashCode();
        }
        if (getCDSCAmount() != null) {
            _hashCode += getCDSCAmount().hashCode();
        }
        if (getSettlementAmount() != null) {
            _hashCode += getSettlementAmount().hashCode();
        }
        if (getSettlementAmountIndicator() != null) {
            _hashCode += getSettlementAmountIndicator().hashCode();
        }
        if (getSettlementAmountIndicatorDescription() != null) {
            _hashCode += getSettlementAmountIndicatorDescription().hashCode();
        }
        if (getFundBaseCurrencyCode() != null) {
            _hashCode += getFundBaseCurrencyCode().hashCode();
        }
        if (getFundShortName() != null) {
            _hashCode += getFundShortName().hashCode();
        }
        if (getLotDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLotDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLotDetails(), i);
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
        new org.apache.axis.description.TypeDesc(CDSCCalculatorResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCCalculatorResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("grossSharesRedeemed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "GrossSharesRedeemed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moneyMarketShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MoneyMarketShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frontLoadNoLotShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FrontLoadNoLotShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noLoadNoLotShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoLoadNoLotShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCApplicableShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCApplicableShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCReinvestedShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCReinvestedShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCExcludedShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCExcludedShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLCDSCApplicableShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FLCDSCApplicableShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLCDSCReinvestedShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FLCDSCReinvestedShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FLCDSCExcludedShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FLCDSCExcludedShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noLoadLotShares");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoLoadLotShares"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ValuationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valuationPrice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ValuationPrice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("redemptionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RedemptionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CDSCAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CDSCAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SettlementAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementAmountIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SettlementAmountIndicator"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlementAmountIndicatorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SettlementAmountIndicatorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundBaseCurrencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundBaseCurrencyCode"));
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
        elemField.setFieldName("lotDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LotDetailResult"));
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
