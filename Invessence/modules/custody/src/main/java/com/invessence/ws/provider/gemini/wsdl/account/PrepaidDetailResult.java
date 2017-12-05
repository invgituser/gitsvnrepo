/**
 * PrepaidDetailResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class PrepaidDetailResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private short programYearId;

    private java.lang.String programYearDescription;

    private java.math.BigDecimal refundValue;

    private java.math.BigDecimal latestNAV;

    private java.util.Calendar latestNAVDate;

    private java.util.Calendar earliestEligibleRefundDate;

    private java.util.Calendar earliestEligibleRedemptionDate;

    private java.util.Calendar firstPurchaseDate;

    private java.math.BigDecimal programYearGrossPurchases;

    private java.math.BigDecimal programYearNetPurchases;

    private java.math.BigDecimal totalBasisAmount;

    private java.math.BigDecimal totalContributionAmount;

    private short favoriteInstitutionId1;

    private java.lang.String favoriteInstitutionName1;

    private java.math.BigDecimal tuitionCredits1;

    private short favoriteInstitutionId2;

    private java.lang.String favoriteInstitutionName2;

    private java.math.BigDecimal tuitionCredits2;

    private short favoriteInstitutionId3;

    private java.lang.String favoriteInstitutionName3;

    private java.math.BigDecimal tuitionCredits3;

    private short favoriteInstitutionId4;

    private java.lang.String favoriteInstitutionName4;

    private java.math.BigDecimal tuitionCredits4;

    private short favoriteInstitutionId5;

    private java.lang.String favoriteInstitutionName5;

    private java.math.BigDecimal tuitionCredits5;

    public PrepaidDetailResult() {
    }

    public PrepaidDetailResult(
           java.lang.String accountNumber,
           short fundId,
           short programYearId,
           java.lang.String programYearDescription,
           java.math.BigDecimal refundValue,
           java.math.BigDecimal latestNAV,
           java.util.Calendar latestNAVDate,
           java.util.Calendar earliestEligibleRefundDate,
           java.util.Calendar earliestEligibleRedemptionDate,
           java.util.Calendar firstPurchaseDate,
           java.math.BigDecimal programYearGrossPurchases,
           java.math.BigDecimal programYearNetPurchases,
           java.math.BigDecimal totalBasisAmount,
           java.math.BigDecimal totalContributionAmount,
           short favoriteInstitutionId1,
           java.lang.String favoriteInstitutionName1,
           java.math.BigDecimal tuitionCredits1,
           short favoriteInstitutionId2,
           java.lang.String favoriteInstitutionName2,
           java.math.BigDecimal tuitionCredits2,
           short favoriteInstitutionId3,
           java.lang.String favoriteInstitutionName3,
           java.math.BigDecimal tuitionCredits3,
           short favoriteInstitutionId4,
           java.lang.String favoriteInstitutionName4,
           java.math.BigDecimal tuitionCredits4,
           short favoriteInstitutionId5,
           java.lang.String favoriteInstitutionName5,
           java.math.BigDecimal tuitionCredits5) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.programYearId = programYearId;
           this.programYearDescription = programYearDescription;
           this.refundValue = refundValue;
           this.latestNAV = latestNAV;
           this.latestNAVDate = latestNAVDate;
           this.earliestEligibleRefundDate = earliestEligibleRefundDate;
           this.earliestEligibleRedemptionDate = earliestEligibleRedemptionDate;
           this.firstPurchaseDate = firstPurchaseDate;
           this.programYearGrossPurchases = programYearGrossPurchases;
           this.programYearNetPurchases = programYearNetPurchases;
           this.totalBasisAmount = totalBasisAmount;
           this.totalContributionAmount = totalContributionAmount;
           this.favoriteInstitutionId1 = favoriteInstitutionId1;
           this.favoriteInstitutionName1 = favoriteInstitutionName1;
           this.tuitionCredits1 = tuitionCredits1;
           this.favoriteInstitutionId2 = favoriteInstitutionId2;
           this.favoriteInstitutionName2 = favoriteInstitutionName2;
           this.tuitionCredits2 = tuitionCredits2;
           this.favoriteInstitutionId3 = favoriteInstitutionId3;
           this.favoriteInstitutionName3 = favoriteInstitutionName3;
           this.tuitionCredits3 = tuitionCredits3;
           this.favoriteInstitutionId4 = favoriteInstitutionId4;
           this.favoriteInstitutionName4 = favoriteInstitutionName4;
           this.tuitionCredits4 = tuitionCredits4;
           this.favoriteInstitutionId5 = favoriteInstitutionId5;
           this.favoriteInstitutionName5 = favoriteInstitutionName5;
           this.tuitionCredits5 = tuitionCredits5;
    }


    /**
     * Gets the accountNumber value for this PrepaidDetailResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this PrepaidDetailResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this PrepaidDetailResult.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this PrepaidDetailResult.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the programYearId value for this PrepaidDetailResult.
     * 
     * @return programYearId
     */
    public short getProgramYearId() {
        return programYearId;
    }


    /**
     * Sets the programYearId value for this PrepaidDetailResult.
     * 
     * @param programYearId
     */
    public void setProgramYearId(short programYearId) {
        this.programYearId = programYearId;
    }


    /**
     * Gets the programYearDescription value for this PrepaidDetailResult.
     * 
     * @return programYearDescription
     */
    public java.lang.String getProgramYearDescription() {
        return programYearDescription;
    }


    /**
     * Sets the programYearDescription value for this PrepaidDetailResult.
     * 
     * @param programYearDescription
     */
    public void setProgramYearDescription(java.lang.String programYearDescription) {
        this.programYearDescription = programYearDescription;
    }


    /**
     * Gets the refundValue value for this PrepaidDetailResult.
     * 
     * @return refundValue
     */
    public java.math.BigDecimal getRefundValue() {
        return refundValue;
    }


    /**
     * Sets the refundValue value for this PrepaidDetailResult.
     * 
     * @param refundValue
     */
    public void setRefundValue(java.math.BigDecimal refundValue) {
        this.refundValue = refundValue;
    }


    /**
     * Gets the latestNAV value for this PrepaidDetailResult.
     * 
     * @return latestNAV
     */
    public java.math.BigDecimal getLatestNAV() {
        return latestNAV;
    }


    /**
     * Sets the latestNAV value for this PrepaidDetailResult.
     * 
     * @param latestNAV
     */
    public void setLatestNAV(java.math.BigDecimal latestNAV) {
        this.latestNAV = latestNAV;
    }


    /**
     * Gets the latestNAVDate value for this PrepaidDetailResult.
     * 
     * @return latestNAVDate
     */
    public java.util.Calendar getLatestNAVDate() {
        return latestNAVDate;
    }


    /**
     * Sets the latestNAVDate value for this PrepaidDetailResult.
     * 
     * @param latestNAVDate
     */
    public void setLatestNAVDate(java.util.Calendar latestNAVDate) {
        this.latestNAVDate = latestNAVDate;
    }


    /**
     * Gets the earliestEligibleRefundDate value for this PrepaidDetailResult.
     * 
     * @return earliestEligibleRefundDate
     */
    public java.util.Calendar getEarliestEligibleRefundDate() {
        return earliestEligibleRefundDate;
    }


    /**
     * Sets the earliestEligibleRefundDate value for this PrepaidDetailResult.
     * 
     * @param earliestEligibleRefundDate
     */
    public void setEarliestEligibleRefundDate(java.util.Calendar earliestEligibleRefundDate) {
        this.earliestEligibleRefundDate = earliestEligibleRefundDate;
    }


    /**
     * Gets the earliestEligibleRedemptionDate value for this PrepaidDetailResult.
     * 
     * @return earliestEligibleRedemptionDate
     */
    public java.util.Calendar getEarliestEligibleRedemptionDate() {
        return earliestEligibleRedemptionDate;
    }


    /**
     * Sets the earliestEligibleRedemptionDate value for this PrepaidDetailResult.
     * 
     * @param earliestEligibleRedemptionDate
     */
    public void setEarliestEligibleRedemptionDate(java.util.Calendar earliestEligibleRedemptionDate) {
        this.earliestEligibleRedemptionDate = earliestEligibleRedemptionDate;
    }


    /**
     * Gets the firstPurchaseDate value for this PrepaidDetailResult.
     * 
     * @return firstPurchaseDate
     */
    public java.util.Calendar getFirstPurchaseDate() {
        return firstPurchaseDate;
    }


    /**
     * Sets the firstPurchaseDate value for this PrepaidDetailResult.
     * 
     * @param firstPurchaseDate
     */
    public void setFirstPurchaseDate(java.util.Calendar firstPurchaseDate) {
        this.firstPurchaseDate = firstPurchaseDate;
    }


    /**
     * Gets the programYearGrossPurchases value for this PrepaidDetailResult.
     * 
     * @return programYearGrossPurchases
     */
    public java.math.BigDecimal getProgramYearGrossPurchases() {
        return programYearGrossPurchases;
    }


    /**
     * Sets the programYearGrossPurchases value for this PrepaidDetailResult.
     * 
     * @param programYearGrossPurchases
     */
    public void setProgramYearGrossPurchases(java.math.BigDecimal programYearGrossPurchases) {
        this.programYearGrossPurchases = programYearGrossPurchases;
    }


    /**
     * Gets the programYearNetPurchases value for this PrepaidDetailResult.
     * 
     * @return programYearNetPurchases
     */
    public java.math.BigDecimal getProgramYearNetPurchases() {
        return programYearNetPurchases;
    }


    /**
     * Sets the programYearNetPurchases value for this PrepaidDetailResult.
     * 
     * @param programYearNetPurchases
     */
    public void setProgramYearNetPurchases(java.math.BigDecimal programYearNetPurchases) {
        this.programYearNetPurchases = programYearNetPurchases;
    }


    /**
     * Gets the totalBasisAmount value for this PrepaidDetailResult.
     * 
     * @return totalBasisAmount
     */
    public java.math.BigDecimal getTotalBasisAmount() {
        return totalBasisAmount;
    }


    /**
     * Sets the totalBasisAmount value for this PrepaidDetailResult.
     * 
     * @param totalBasisAmount
     */
    public void setTotalBasisAmount(java.math.BigDecimal totalBasisAmount) {
        this.totalBasisAmount = totalBasisAmount;
    }


    /**
     * Gets the totalContributionAmount value for this PrepaidDetailResult.
     * 
     * @return totalContributionAmount
     */
    public java.math.BigDecimal getTotalContributionAmount() {
        return totalContributionAmount;
    }


    /**
     * Sets the totalContributionAmount value for this PrepaidDetailResult.
     * 
     * @param totalContributionAmount
     */
    public void setTotalContributionAmount(java.math.BigDecimal totalContributionAmount) {
        this.totalContributionAmount = totalContributionAmount;
    }


    /**
     * Gets the favoriteInstitutionId1 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionId1
     */
    public short getFavoriteInstitutionId1() {
        return favoriteInstitutionId1;
    }


    /**
     * Sets the favoriteInstitutionId1 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionId1
     */
    public void setFavoriteInstitutionId1(short favoriteInstitutionId1) {
        this.favoriteInstitutionId1 = favoriteInstitutionId1;
    }


    /**
     * Gets the favoriteInstitutionName1 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionName1
     */
    public java.lang.String getFavoriteInstitutionName1() {
        return favoriteInstitutionName1;
    }


    /**
     * Sets the favoriteInstitutionName1 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionName1
     */
    public void setFavoriteInstitutionName1(java.lang.String favoriteInstitutionName1) {
        this.favoriteInstitutionName1 = favoriteInstitutionName1;
    }


    /**
     * Gets the tuitionCredits1 value for this PrepaidDetailResult.
     * 
     * @return tuitionCredits1
     */
    public java.math.BigDecimal getTuitionCredits1() {
        return tuitionCredits1;
    }


    /**
     * Sets the tuitionCredits1 value for this PrepaidDetailResult.
     * 
     * @param tuitionCredits1
     */
    public void setTuitionCredits1(java.math.BigDecimal tuitionCredits1) {
        this.tuitionCredits1 = tuitionCredits1;
    }


    /**
     * Gets the favoriteInstitutionId2 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionId2
     */
    public short getFavoriteInstitutionId2() {
        return favoriteInstitutionId2;
    }


    /**
     * Sets the favoriteInstitutionId2 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionId2
     */
    public void setFavoriteInstitutionId2(short favoriteInstitutionId2) {
        this.favoriteInstitutionId2 = favoriteInstitutionId2;
    }


    /**
     * Gets the favoriteInstitutionName2 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionName2
     */
    public java.lang.String getFavoriteInstitutionName2() {
        return favoriteInstitutionName2;
    }


    /**
     * Sets the favoriteInstitutionName2 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionName2
     */
    public void setFavoriteInstitutionName2(java.lang.String favoriteInstitutionName2) {
        this.favoriteInstitutionName2 = favoriteInstitutionName2;
    }


    /**
     * Gets the tuitionCredits2 value for this PrepaidDetailResult.
     * 
     * @return tuitionCredits2
     */
    public java.math.BigDecimal getTuitionCredits2() {
        return tuitionCredits2;
    }


    /**
     * Sets the tuitionCredits2 value for this PrepaidDetailResult.
     * 
     * @param tuitionCredits2
     */
    public void setTuitionCredits2(java.math.BigDecimal tuitionCredits2) {
        this.tuitionCredits2 = tuitionCredits2;
    }


    /**
     * Gets the favoriteInstitutionId3 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionId3
     */
    public short getFavoriteInstitutionId3() {
        return favoriteInstitutionId3;
    }


    /**
     * Sets the favoriteInstitutionId3 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionId3
     */
    public void setFavoriteInstitutionId3(short favoriteInstitutionId3) {
        this.favoriteInstitutionId3 = favoriteInstitutionId3;
    }


    /**
     * Gets the favoriteInstitutionName3 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionName3
     */
    public java.lang.String getFavoriteInstitutionName3() {
        return favoriteInstitutionName3;
    }


    /**
     * Sets the favoriteInstitutionName3 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionName3
     */
    public void setFavoriteInstitutionName3(java.lang.String favoriteInstitutionName3) {
        this.favoriteInstitutionName3 = favoriteInstitutionName3;
    }


    /**
     * Gets the tuitionCredits3 value for this PrepaidDetailResult.
     * 
     * @return tuitionCredits3
     */
    public java.math.BigDecimal getTuitionCredits3() {
        return tuitionCredits3;
    }


    /**
     * Sets the tuitionCredits3 value for this PrepaidDetailResult.
     * 
     * @param tuitionCredits3
     */
    public void setTuitionCredits3(java.math.BigDecimal tuitionCredits3) {
        this.tuitionCredits3 = tuitionCredits3;
    }


    /**
     * Gets the favoriteInstitutionId4 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionId4
     */
    public short getFavoriteInstitutionId4() {
        return favoriteInstitutionId4;
    }


    /**
     * Sets the favoriteInstitutionId4 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionId4
     */
    public void setFavoriteInstitutionId4(short favoriteInstitutionId4) {
        this.favoriteInstitutionId4 = favoriteInstitutionId4;
    }


    /**
     * Gets the favoriteInstitutionName4 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionName4
     */
    public java.lang.String getFavoriteInstitutionName4() {
        return favoriteInstitutionName4;
    }


    /**
     * Sets the favoriteInstitutionName4 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionName4
     */
    public void setFavoriteInstitutionName4(java.lang.String favoriteInstitutionName4) {
        this.favoriteInstitutionName4 = favoriteInstitutionName4;
    }


    /**
     * Gets the tuitionCredits4 value for this PrepaidDetailResult.
     * 
     * @return tuitionCredits4
     */
    public java.math.BigDecimal getTuitionCredits4() {
        return tuitionCredits4;
    }


    /**
     * Sets the tuitionCredits4 value for this PrepaidDetailResult.
     * 
     * @param tuitionCredits4
     */
    public void setTuitionCredits4(java.math.BigDecimal tuitionCredits4) {
        this.tuitionCredits4 = tuitionCredits4;
    }


    /**
     * Gets the favoriteInstitutionId5 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionId5
     */
    public short getFavoriteInstitutionId5() {
        return favoriteInstitutionId5;
    }


    /**
     * Sets the favoriteInstitutionId5 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionId5
     */
    public void setFavoriteInstitutionId5(short favoriteInstitutionId5) {
        this.favoriteInstitutionId5 = favoriteInstitutionId5;
    }


    /**
     * Gets the favoriteInstitutionName5 value for this PrepaidDetailResult.
     * 
     * @return favoriteInstitutionName5
     */
    public java.lang.String getFavoriteInstitutionName5() {
        return favoriteInstitutionName5;
    }


    /**
     * Sets the favoriteInstitutionName5 value for this PrepaidDetailResult.
     * 
     * @param favoriteInstitutionName5
     */
    public void setFavoriteInstitutionName5(java.lang.String favoriteInstitutionName5) {
        this.favoriteInstitutionName5 = favoriteInstitutionName5;
    }


    /**
     * Gets the tuitionCredits5 value for this PrepaidDetailResult.
     * 
     * @return tuitionCredits5
     */
    public java.math.BigDecimal getTuitionCredits5() {
        return tuitionCredits5;
    }


    /**
     * Sets the tuitionCredits5 value for this PrepaidDetailResult.
     * 
     * @param tuitionCredits5
     */
    public void setTuitionCredits5(java.math.BigDecimal tuitionCredits5) {
        this.tuitionCredits5 = tuitionCredits5;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrepaidDetailResult)) return false;
        PrepaidDetailResult other = (PrepaidDetailResult) obj;
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
            this.fundId == other.getFundId() &&
            this.programYearId == other.getProgramYearId() &&
            ((this.programYearDescription==null && other.getProgramYearDescription()==null) || 
             (this.programYearDescription!=null &&
              this.programYearDescription.equals(other.getProgramYearDescription()))) &&
            ((this.refundValue==null && other.getRefundValue()==null) || 
             (this.refundValue!=null &&
              this.refundValue.equals(other.getRefundValue()))) &&
            ((this.latestNAV==null && other.getLatestNAV()==null) || 
             (this.latestNAV!=null &&
              this.latestNAV.equals(other.getLatestNAV()))) &&
            ((this.latestNAVDate==null && other.getLatestNAVDate()==null) || 
             (this.latestNAVDate!=null &&
              this.latestNAVDate.equals(other.getLatestNAVDate()))) &&
            ((this.earliestEligibleRefundDate==null && other.getEarliestEligibleRefundDate()==null) || 
             (this.earliestEligibleRefundDate!=null &&
              this.earliestEligibleRefundDate.equals(other.getEarliestEligibleRefundDate()))) &&
            ((this.earliestEligibleRedemptionDate==null && other.getEarliestEligibleRedemptionDate()==null) || 
             (this.earliestEligibleRedemptionDate!=null &&
              this.earliestEligibleRedemptionDate.equals(other.getEarliestEligibleRedemptionDate()))) &&
            ((this.firstPurchaseDate==null && other.getFirstPurchaseDate()==null) || 
             (this.firstPurchaseDate!=null &&
              this.firstPurchaseDate.equals(other.getFirstPurchaseDate()))) &&
            ((this.programYearGrossPurchases==null && other.getProgramYearGrossPurchases()==null) || 
             (this.programYearGrossPurchases!=null &&
              this.programYearGrossPurchases.equals(other.getProgramYearGrossPurchases()))) &&
            ((this.programYearNetPurchases==null && other.getProgramYearNetPurchases()==null) || 
             (this.programYearNetPurchases!=null &&
              this.programYearNetPurchases.equals(other.getProgramYearNetPurchases()))) &&
            ((this.totalBasisAmount==null && other.getTotalBasisAmount()==null) || 
             (this.totalBasisAmount!=null &&
              this.totalBasisAmount.equals(other.getTotalBasisAmount()))) &&
            ((this.totalContributionAmount==null && other.getTotalContributionAmount()==null) || 
             (this.totalContributionAmount!=null &&
              this.totalContributionAmount.equals(other.getTotalContributionAmount()))) &&
            this.favoriteInstitutionId1 == other.getFavoriteInstitutionId1() &&
            ((this.favoriteInstitutionName1==null && other.getFavoriteInstitutionName1()==null) || 
             (this.favoriteInstitutionName1!=null &&
              this.favoriteInstitutionName1.equals(other.getFavoriteInstitutionName1()))) &&
            ((this.tuitionCredits1==null && other.getTuitionCredits1()==null) || 
             (this.tuitionCredits1!=null &&
              this.tuitionCredits1.equals(other.getTuitionCredits1()))) &&
            this.favoriteInstitutionId2 == other.getFavoriteInstitutionId2() &&
            ((this.favoriteInstitutionName2==null && other.getFavoriteInstitutionName2()==null) || 
             (this.favoriteInstitutionName2!=null &&
              this.favoriteInstitutionName2.equals(other.getFavoriteInstitutionName2()))) &&
            ((this.tuitionCredits2==null && other.getTuitionCredits2()==null) || 
             (this.tuitionCredits2!=null &&
              this.tuitionCredits2.equals(other.getTuitionCredits2()))) &&
            this.favoriteInstitutionId3 == other.getFavoriteInstitutionId3() &&
            ((this.favoriteInstitutionName3==null && other.getFavoriteInstitutionName3()==null) || 
             (this.favoriteInstitutionName3!=null &&
              this.favoriteInstitutionName3.equals(other.getFavoriteInstitutionName3()))) &&
            ((this.tuitionCredits3==null && other.getTuitionCredits3()==null) || 
             (this.tuitionCredits3!=null &&
              this.tuitionCredits3.equals(other.getTuitionCredits3()))) &&
            this.favoriteInstitutionId4 == other.getFavoriteInstitutionId4() &&
            ((this.favoriteInstitutionName4==null && other.getFavoriteInstitutionName4()==null) || 
             (this.favoriteInstitutionName4!=null &&
              this.favoriteInstitutionName4.equals(other.getFavoriteInstitutionName4()))) &&
            ((this.tuitionCredits4==null && other.getTuitionCredits4()==null) || 
             (this.tuitionCredits4!=null &&
              this.tuitionCredits4.equals(other.getTuitionCredits4()))) &&
            this.favoriteInstitutionId5 == other.getFavoriteInstitutionId5() &&
            ((this.favoriteInstitutionName5==null && other.getFavoriteInstitutionName5()==null) || 
             (this.favoriteInstitutionName5!=null &&
              this.favoriteInstitutionName5.equals(other.getFavoriteInstitutionName5()))) &&
            ((this.tuitionCredits5==null && other.getTuitionCredits5()==null) || 
             (this.tuitionCredits5!=null &&
              this.tuitionCredits5.equals(other.getTuitionCredits5())));
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
        _hashCode += getFundId();
        _hashCode += getProgramYearId();
        if (getProgramYearDescription() != null) {
            _hashCode += getProgramYearDescription().hashCode();
        }
        if (getRefundValue() != null) {
            _hashCode += getRefundValue().hashCode();
        }
        if (getLatestNAV() != null) {
            _hashCode += getLatestNAV().hashCode();
        }
        if (getLatestNAVDate() != null) {
            _hashCode += getLatestNAVDate().hashCode();
        }
        if (getEarliestEligibleRefundDate() != null) {
            _hashCode += getEarliestEligibleRefundDate().hashCode();
        }
        if (getEarliestEligibleRedemptionDate() != null) {
            _hashCode += getEarliestEligibleRedemptionDate().hashCode();
        }
        if (getFirstPurchaseDate() != null) {
            _hashCode += getFirstPurchaseDate().hashCode();
        }
        if (getProgramYearGrossPurchases() != null) {
            _hashCode += getProgramYearGrossPurchases().hashCode();
        }
        if (getProgramYearNetPurchases() != null) {
            _hashCode += getProgramYearNetPurchases().hashCode();
        }
        if (getTotalBasisAmount() != null) {
            _hashCode += getTotalBasisAmount().hashCode();
        }
        if (getTotalContributionAmount() != null) {
            _hashCode += getTotalContributionAmount().hashCode();
        }
        _hashCode += getFavoriteInstitutionId1();
        if (getFavoriteInstitutionName1() != null) {
            _hashCode += getFavoriteInstitutionName1().hashCode();
        }
        if (getTuitionCredits1() != null) {
            _hashCode += getTuitionCredits1().hashCode();
        }
        _hashCode += getFavoriteInstitutionId2();
        if (getFavoriteInstitutionName2() != null) {
            _hashCode += getFavoriteInstitutionName2().hashCode();
        }
        if (getTuitionCredits2() != null) {
            _hashCode += getTuitionCredits2().hashCode();
        }
        _hashCode += getFavoriteInstitutionId3();
        if (getFavoriteInstitutionName3() != null) {
            _hashCode += getFavoriteInstitutionName3().hashCode();
        }
        if (getTuitionCredits3() != null) {
            _hashCode += getTuitionCredits3().hashCode();
        }
        _hashCode += getFavoriteInstitutionId4();
        if (getFavoriteInstitutionName4() != null) {
            _hashCode += getFavoriteInstitutionName4().hashCode();
        }
        if (getTuitionCredits4() != null) {
            _hashCode += getTuitionCredits4().hashCode();
        }
        _hashCode += getFavoriteInstitutionId5();
        if (getFavoriteInstitutionName5() != null) {
            _hashCode += getFavoriteInstitutionName5().hashCode();
        }
        if (getTuitionCredits5() != null) {
            _hashCode += getTuitionCredits5().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrepaidDetailResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refundValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "RefundValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latestNAV");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LatestNAV"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("latestNAVDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LatestNAVDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("earliestEligibleRefundDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EarliestEligibleRefundDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("earliestEligibleRedemptionDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "EarliestEligibleRedemptionDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstPurchaseDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FirstPurchaseDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearGrossPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearGrossPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("programYearNetPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "ProgramYearNetPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalBasisAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalBasisAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("totalContributionAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalContributionAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionId1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionId1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionName1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionName1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCredits1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCredits1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionId2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionId2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionName2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionName2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCredits2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCredits2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionId3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionId3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionName3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionName3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCredits3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCredits3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionId4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionId4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionName4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionName4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCredits4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCredits4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionId5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionId5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("favoriteInstitutionName5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FavoriteInstitutionName5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tuitionCredits5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TuitionCredits5"));
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
