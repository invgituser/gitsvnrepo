/**
 * PrepaidSummaryInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ws.gemini.wsdl.account;

public class PrepaidSummaryInfo  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private short fundId;

    private java.math.BigDecimal priorYearsGrossPurchases;

    private java.math.BigDecimal priorYearsNetPurchases;

    private java.math.BigDecimal LTDGrossPurchases;

    private java.math.BigDecimal LTDNetPurchases;

    private java.math.BigDecimal currentYearGrossPurchases;

    private java.math.BigDecimal currentYearNetPurchases;

    private short favoriteInstitutionId1;

    private java.lang.String favoriteInstitutionName1;

    private java.math.BigDecimal totalTuitionCredits1;

    private short favoriteInstitutionId2;

    private java.lang.String favoriteInstitutionName2;

    private java.math.BigDecimal totalTuitionCredits2;

    private short favoriteInstitutionId3;

    private java.lang.String favoriteInstitutionName3;

    private java.math.BigDecimal totalTuitionCredits3;

    private short favoriteInstitutionId4;

    private java.lang.String favoriteInstitutionName4;

    private java.math.BigDecimal totalTuitionCredits4;

    private short favoriteInstitutionId5;

    private java.lang.String favoriteInstitutionName5;

    private java.math.BigDecimal totalTuitionCredits5;

    private com.ws.gemini.wsdl.account.PrepaidDetailResult[] prepaidDetails;

    public PrepaidSummaryInfo() {
    }

    public PrepaidSummaryInfo(
           java.lang.String accountNumber,
           short fundId,
           java.math.BigDecimal priorYearsGrossPurchases,
           java.math.BigDecimal priorYearsNetPurchases,
           java.math.BigDecimal LTDGrossPurchases,
           java.math.BigDecimal LTDNetPurchases,
           java.math.BigDecimal currentYearGrossPurchases,
           java.math.BigDecimal currentYearNetPurchases,
           short favoriteInstitutionId1,
           java.lang.String favoriteInstitutionName1,
           java.math.BigDecimal totalTuitionCredits1,
           short favoriteInstitutionId2,
           java.lang.String favoriteInstitutionName2,
           java.math.BigDecimal totalTuitionCredits2,
           short favoriteInstitutionId3,
           java.lang.String favoriteInstitutionName3,
           java.math.BigDecimal totalTuitionCredits3,
           short favoriteInstitutionId4,
           java.lang.String favoriteInstitutionName4,
           java.math.BigDecimal totalTuitionCredits4,
           short favoriteInstitutionId5,
           java.lang.String favoriteInstitutionName5,
           java.math.BigDecimal totalTuitionCredits5,
           com.ws.gemini.wsdl.account.PrepaidDetailResult[] prepaidDetails) {
           this.accountNumber = accountNumber;
           this.fundId = fundId;
           this.priorYearsGrossPurchases = priorYearsGrossPurchases;
           this.priorYearsNetPurchases = priorYearsNetPurchases;
           this.LTDGrossPurchases = LTDGrossPurchases;
           this.LTDNetPurchases = LTDNetPurchases;
           this.currentYearGrossPurchases = currentYearGrossPurchases;
           this.currentYearNetPurchases = currentYearNetPurchases;
           this.favoriteInstitutionId1 = favoriteInstitutionId1;
           this.favoriteInstitutionName1 = favoriteInstitutionName1;
           this.totalTuitionCredits1 = totalTuitionCredits1;
           this.favoriteInstitutionId2 = favoriteInstitutionId2;
           this.favoriteInstitutionName2 = favoriteInstitutionName2;
           this.totalTuitionCredits2 = totalTuitionCredits2;
           this.favoriteInstitutionId3 = favoriteInstitutionId3;
           this.favoriteInstitutionName3 = favoriteInstitutionName3;
           this.totalTuitionCredits3 = totalTuitionCredits3;
           this.favoriteInstitutionId4 = favoriteInstitutionId4;
           this.favoriteInstitutionName4 = favoriteInstitutionName4;
           this.totalTuitionCredits4 = totalTuitionCredits4;
           this.favoriteInstitutionId5 = favoriteInstitutionId5;
           this.favoriteInstitutionName5 = favoriteInstitutionName5;
           this.totalTuitionCredits5 = totalTuitionCredits5;
           this.prepaidDetails = prepaidDetails;
    }


    /**
     * Gets the accountNumber value for this PrepaidSummaryInfo.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this PrepaidSummaryInfo.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the fundId value for this PrepaidSummaryInfo.
     * 
     * @return fundId
     */
    public short getFundId() {
        return fundId;
    }


    /**
     * Sets the fundId value for this PrepaidSummaryInfo.
     * 
     * @param fundId
     */
    public void setFundId(short fundId) {
        this.fundId = fundId;
    }


    /**
     * Gets the priorYearsGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @return priorYearsGrossPurchases
     */
    public java.math.BigDecimal getPriorYearsGrossPurchases() {
        return priorYearsGrossPurchases;
    }


    /**
     * Sets the priorYearsGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @param priorYearsGrossPurchases
     */
    public void setPriorYearsGrossPurchases(java.math.BigDecimal priorYearsGrossPurchases) {
        this.priorYearsGrossPurchases = priorYearsGrossPurchases;
    }


    /**
     * Gets the priorYearsNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @return priorYearsNetPurchases
     */
    public java.math.BigDecimal getPriorYearsNetPurchases() {
        return priorYearsNetPurchases;
    }


    /**
     * Sets the priorYearsNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @param priorYearsNetPurchases
     */
    public void setPriorYearsNetPurchases(java.math.BigDecimal priorYearsNetPurchases) {
        this.priorYearsNetPurchases = priorYearsNetPurchases;
    }


    /**
     * Gets the LTDGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @return LTDGrossPurchases
     */
    public java.math.BigDecimal getLTDGrossPurchases() {
        return LTDGrossPurchases;
    }


    /**
     * Sets the LTDGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @param LTDGrossPurchases
     */
    public void setLTDGrossPurchases(java.math.BigDecimal LTDGrossPurchases) {
        this.LTDGrossPurchases = LTDGrossPurchases;
    }


    /**
     * Gets the LTDNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @return LTDNetPurchases
     */
    public java.math.BigDecimal getLTDNetPurchases() {
        return LTDNetPurchases;
    }


    /**
     * Sets the LTDNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @param LTDNetPurchases
     */
    public void setLTDNetPurchases(java.math.BigDecimal LTDNetPurchases) {
        this.LTDNetPurchases = LTDNetPurchases;
    }


    /**
     * Gets the currentYearGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @return currentYearGrossPurchases
     */
    public java.math.BigDecimal getCurrentYearGrossPurchases() {
        return currentYearGrossPurchases;
    }


    /**
     * Sets the currentYearGrossPurchases value for this PrepaidSummaryInfo.
     * 
     * @param currentYearGrossPurchases
     */
    public void setCurrentYearGrossPurchases(java.math.BigDecimal currentYearGrossPurchases) {
        this.currentYearGrossPurchases = currentYearGrossPurchases;
    }


    /**
     * Gets the currentYearNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @return currentYearNetPurchases
     */
    public java.math.BigDecimal getCurrentYearNetPurchases() {
        return currentYearNetPurchases;
    }


    /**
     * Sets the currentYearNetPurchases value for this PrepaidSummaryInfo.
     * 
     * @param currentYearNetPurchases
     */
    public void setCurrentYearNetPurchases(java.math.BigDecimal currentYearNetPurchases) {
        this.currentYearNetPurchases = currentYearNetPurchases;
    }


    /**
     * Gets the favoriteInstitutionId1 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionId1
     */
    public short getFavoriteInstitutionId1() {
        return favoriteInstitutionId1;
    }


    /**
     * Sets the favoriteInstitutionId1 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionId1
     */
    public void setFavoriteInstitutionId1(short favoriteInstitutionId1) {
        this.favoriteInstitutionId1 = favoriteInstitutionId1;
    }


    /**
     * Gets the favoriteInstitutionName1 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionName1
     */
    public java.lang.String getFavoriteInstitutionName1() {
        return favoriteInstitutionName1;
    }


    /**
     * Sets the favoriteInstitutionName1 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionName1
     */
    public void setFavoriteInstitutionName1(java.lang.String favoriteInstitutionName1) {
        this.favoriteInstitutionName1 = favoriteInstitutionName1;
    }


    /**
     * Gets the totalTuitionCredits1 value for this PrepaidSummaryInfo.
     * 
     * @return totalTuitionCredits1
     */
    public java.math.BigDecimal getTotalTuitionCredits1() {
        return totalTuitionCredits1;
    }


    /**
     * Sets the totalTuitionCredits1 value for this PrepaidSummaryInfo.
     * 
     * @param totalTuitionCredits1
     */
    public void setTotalTuitionCredits1(java.math.BigDecimal totalTuitionCredits1) {
        this.totalTuitionCredits1 = totalTuitionCredits1;
    }


    /**
     * Gets the favoriteInstitutionId2 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionId2
     */
    public short getFavoriteInstitutionId2() {
        return favoriteInstitutionId2;
    }


    /**
     * Sets the favoriteInstitutionId2 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionId2
     */
    public void setFavoriteInstitutionId2(short favoriteInstitutionId2) {
        this.favoriteInstitutionId2 = favoriteInstitutionId2;
    }


    /**
     * Gets the favoriteInstitutionName2 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionName2
     */
    public java.lang.String getFavoriteInstitutionName2() {
        return favoriteInstitutionName2;
    }


    /**
     * Sets the favoriteInstitutionName2 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionName2
     */
    public void setFavoriteInstitutionName2(java.lang.String favoriteInstitutionName2) {
        this.favoriteInstitutionName2 = favoriteInstitutionName2;
    }


    /**
     * Gets the totalTuitionCredits2 value for this PrepaidSummaryInfo.
     * 
     * @return totalTuitionCredits2
     */
    public java.math.BigDecimal getTotalTuitionCredits2() {
        return totalTuitionCredits2;
    }


    /**
     * Sets the totalTuitionCredits2 value for this PrepaidSummaryInfo.
     * 
     * @param totalTuitionCredits2
     */
    public void setTotalTuitionCredits2(java.math.BigDecimal totalTuitionCredits2) {
        this.totalTuitionCredits2 = totalTuitionCredits2;
    }


    /**
     * Gets the favoriteInstitutionId3 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionId3
     */
    public short getFavoriteInstitutionId3() {
        return favoriteInstitutionId3;
    }


    /**
     * Sets the favoriteInstitutionId3 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionId3
     */
    public void setFavoriteInstitutionId3(short favoriteInstitutionId3) {
        this.favoriteInstitutionId3 = favoriteInstitutionId3;
    }


    /**
     * Gets the favoriteInstitutionName3 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionName3
     */
    public java.lang.String getFavoriteInstitutionName3() {
        return favoriteInstitutionName3;
    }


    /**
     * Sets the favoriteInstitutionName3 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionName3
     */
    public void setFavoriteInstitutionName3(java.lang.String favoriteInstitutionName3) {
        this.favoriteInstitutionName3 = favoriteInstitutionName3;
    }


    /**
     * Gets the totalTuitionCredits3 value for this PrepaidSummaryInfo.
     * 
     * @return totalTuitionCredits3
     */
    public java.math.BigDecimal getTotalTuitionCredits3() {
        return totalTuitionCredits3;
    }


    /**
     * Sets the totalTuitionCredits3 value for this PrepaidSummaryInfo.
     * 
     * @param totalTuitionCredits3
     */
    public void setTotalTuitionCredits3(java.math.BigDecimal totalTuitionCredits3) {
        this.totalTuitionCredits3 = totalTuitionCredits3;
    }


    /**
     * Gets the favoriteInstitutionId4 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionId4
     */
    public short getFavoriteInstitutionId4() {
        return favoriteInstitutionId4;
    }


    /**
     * Sets the favoriteInstitutionId4 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionId4
     */
    public void setFavoriteInstitutionId4(short favoriteInstitutionId4) {
        this.favoriteInstitutionId4 = favoriteInstitutionId4;
    }


    /**
     * Gets the favoriteInstitutionName4 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionName4
     */
    public java.lang.String getFavoriteInstitutionName4() {
        return favoriteInstitutionName4;
    }


    /**
     * Sets the favoriteInstitutionName4 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionName4
     */
    public void setFavoriteInstitutionName4(java.lang.String favoriteInstitutionName4) {
        this.favoriteInstitutionName4 = favoriteInstitutionName4;
    }


    /**
     * Gets the totalTuitionCredits4 value for this PrepaidSummaryInfo.
     * 
     * @return totalTuitionCredits4
     */
    public java.math.BigDecimal getTotalTuitionCredits4() {
        return totalTuitionCredits4;
    }


    /**
     * Sets the totalTuitionCredits4 value for this PrepaidSummaryInfo.
     * 
     * @param totalTuitionCredits4
     */
    public void setTotalTuitionCredits4(java.math.BigDecimal totalTuitionCredits4) {
        this.totalTuitionCredits4 = totalTuitionCredits4;
    }


    /**
     * Gets the favoriteInstitutionId5 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionId5
     */
    public short getFavoriteInstitutionId5() {
        return favoriteInstitutionId5;
    }


    /**
     * Sets the favoriteInstitutionId5 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionId5
     */
    public void setFavoriteInstitutionId5(short favoriteInstitutionId5) {
        this.favoriteInstitutionId5 = favoriteInstitutionId5;
    }


    /**
     * Gets the favoriteInstitutionName5 value for this PrepaidSummaryInfo.
     * 
     * @return favoriteInstitutionName5
     */
    public java.lang.String getFavoriteInstitutionName5() {
        return favoriteInstitutionName5;
    }


    /**
     * Sets the favoriteInstitutionName5 value for this PrepaidSummaryInfo.
     * 
     * @param favoriteInstitutionName5
     */
    public void setFavoriteInstitutionName5(java.lang.String favoriteInstitutionName5) {
        this.favoriteInstitutionName5 = favoriteInstitutionName5;
    }


    /**
     * Gets the totalTuitionCredits5 value for this PrepaidSummaryInfo.
     * 
     * @return totalTuitionCredits5
     */
    public java.math.BigDecimal getTotalTuitionCredits5() {
        return totalTuitionCredits5;
    }


    /**
     * Sets the totalTuitionCredits5 value for this PrepaidSummaryInfo.
     * 
     * @param totalTuitionCredits5
     */
    public void setTotalTuitionCredits5(java.math.BigDecimal totalTuitionCredits5) {
        this.totalTuitionCredits5 = totalTuitionCredits5;
    }


    /**
     * Gets the prepaidDetails value for this PrepaidSummaryInfo.
     * 
     * @return prepaidDetails
     */
    public com.ws.gemini.wsdl.account.PrepaidDetailResult[] getPrepaidDetails() {
        return prepaidDetails;
    }


    /**
     * Sets the prepaidDetails value for this PrepaidSummaryInfo.
     * 
     * @param prepaidDetails
     */
    public void setPrepaidDetails(com.ws.gemini.wsdl.account.PrepaidDetailResult[] prepaidDetails) {
        this.prepaidDetails = prepaidDetails;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PrepaidSummaryInfo)) return false;
        PrepaidSummaryInfo other = (PrepaidSummaryInfo) obj;
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
            ((this.priorYearsGrossPurchases==null && other.getPriorYearsGrossPurchases()==null) || 
             (this.priorYearsGrossPurchases!=null &&
              this.priorYearsGrossPurchases.equals(other.getPriorYearsGrossPurchases()))) &&
            ((this.priorYearsNetPurchases==null && other.getPriorYearsNetPurchases()==null) || 
             (this.priorYearsNetPurchases!=null &&
              this.priorYearsNetPurchases.equals(other.getPriorYearsNetPurchases()))) &&
            ((this.LTDGrossPurchases==null && other.getLTDGrossPurchases()==null) || 
             (this.LTDGrossPurchases!=null &&
              this.LTDGrossPurchases.equals(other.getLTDGrossPurchases()))) &&
            ((this.LTDNetPurchases==null && other.getLTDNetPurchases()==null) || 
             (this.LTDNetPurchases!=null &&
              this.LTDNetPurchases.equals(other.getLTDNetPurchases()))) &&
            ((this.currentYearGrossPurchases==null && other.getCurrentYearGrossPurchases()==null) || 
             (this.currentYearGrossPurchases!=null &&
              this.currentYearGrossPurchases.equals(other.getCurrentYearGrossPurchases()))) &&
            ((this.currentYearNetPurchases==null && other.getCurrentYearNetPurchases()==null) || 
             (this.currentYearNetPurchases!=null &&
              this.currentYearNetPurchases.equals(other.getCurrentYearNetPurchases()))) &&
            this.favoriteInstitutionId1 == other.getFavoriteInstitutionId1() &&
            ((this.favoriteInstitutionName1==null && other.getFavoriteInstitutionName1()==null) || 
             (this.favoriteInstitutionName1!=null &&
              this.favoriteInstitutionName1.equals(other.getFavoriteInstitutionName1()))) &&
            ((this.totalTuitionCredits1==null && other.getTotalTuitionCredits1()==null) || 
             (this.totalTuitionCredits1!=null &&
              this.totalTuitionCredits1.equals(other.getTotalTuitionCredits1()))) &&
            this.favoriteInstitutionId2 == other.getFavoriteInstitutionId2() &&
            ((this.favoriteInstitutionName2==null && other.getFavoriteInstitutionName2()==null) || 
             (this.favoriteInstitutionName2!=null &&
              this.favoriteInstitutionName2.equals(other.getFavoriteInstitutionName2()))) &&
            ((this.totalTuitionCredits2==null && other.getTotalTuitionCredits2()==null) || 
             (this.totalTuitionCredits2!=null &&
              this.totalTuitionCredits2.equals(other.getTotalTuitionCredits2()))) &&
            this.favoriteInstitutionId3 == other.getFavoriteInstitutionId3() &&
            ((this.favoriteInstitutionName3==null && other.getFavoriteInstitutionName3()==null) || 
             (this.favoriteInstitutionName3!=null &&
              this.favoriteInstitutionName3.equals(other.getFavoriteInstitutionName3()))) &&
            ((this.totalTuitionCredits3==null && other.getTotalTuitionCredits3()==null) || 
             (this.totalTuitionCredits3!=null &&
              this.totalTuitionCredits3.equals(other.getTotalTuitionCredits3()))) &&
            this.favoriteInstitutionId4 == other.getFavoriteInstitutionId4() &&
            ((this.favoriteInstitutionName4==null && other.getFavoriteInstitutionName4()==null) || 
             (this.favoriteInstitutionName4!=null &&
              this.favoriteInstitutionName4.equals(other.getFavoriteInstitutionName4()))) &&
            ((this.totalTuitionCredits4==null && other.getTotalTuitionCredits4()==null) || 
             (this.totalTuitionCredits4!=null &&
              this.totalTuitionCredits4.equals(other.getTotalTuitionCredits4()))) &&
            this.favoriteInstitutionId5 == other.getFavoriteInstitutionId5() &&
            ((this.favoriteInstitutionName5==null && other.getFavoriteInstitutionName5()==null) || 
             (this.favoriteInstitutionName5!=null &&
              this.favoriteInstitutionName5.equals(other.getFavoriteInstitutionName5()))) &&
            ((this.totalTuitionCredits5==null && other.getTotalTuitionCredits5()==null) || 
             (this.totalTuitionCredits5!=null &&
              this.totalTuitionCredits5.equals(other.getTotalTuitionCredits5()))) &&
            ((this.prepaidDetails==null && other.getPrepaidDetails()==null) || 
             (this.prepaidDetails!=null &&
              java.util.Arrays.equals(this.prepaidDetails, other.getPrepaidDetails())));
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
        if (getPriorYearsGrossPurchases() != null) {
            _hashCode += getPriorYearsGrossPurchases().hashCode();
        }
        if (getPriorYearsNetPurchases() != null) {
            _hashCode += getPriorYearsNetPurchases().hashCode();
        }
        if (getLTDGrossPurchases() != null) {
            _hashCode += getLTDGrossPurchases().hashCode();
        }
        if (getLTDNetPurchases() != null) {
            _hashCode += getLTDNetPurchases().hashCode();
        }
        if (getCurrentYearGrossPurchases() != null) {
            _hashCode += getCurrentYearGrossPurchases().hashCode();
        }
        if (getCurrentYearNetPurchases() != null) {
            _hashCode += getCurrentYearNetPurchases().hashCode();
        }
        _hashCode += getFavoriteInstitutionId1();
        if (getFavoriteInstitutionName1() != null) {
            _hashCode += getFavoriteInstitutionName1().hashCode();
        }
        if (getTotalTuitionCredits1() != null) {
            _hashCode += getTotalTuitionCredits1().hashCode();
        }
        _hashCode += getFavoriteInstitutionId2();
        if (getFavoriteInstitutionName2() != null) {
            _hashCode += getFavoriteInstitutionName2().hashCode();
        }
        if (getTotalTuitionCredits2() != null) {
            _hashCode += getTotalTuitionCredits2().hashCode();
        }
        _hashCode += getFavoriteInstitutionId3();
        if (getFavoriteInstitutionName3() != null) {
            _hashCode += getFavoriteInstitutionName3().hashCode();
        }
        if (getTotalTuitionCredits3() != null) {
            _hashCode += getTotalTuitionCredits3().hashCode();
        }
        _hashCode += getFavoriteInstitutionId4();
        if (getFavoriteInstitutionName4() != null) {
            _hashCode += getFavoriteInstitutionName4().hashCode();
        }
        if (getTotalTuitionCredits4() != null) {
            _hashCode += getTotalTuitionCredits4().hashCode();
        }
        _hashCode += getFavoriteInstitutionId5();
        if (getFavoriteInstitutionName5() != null) {
            _hashCode += getFavoriteInstitutionName5().hashCode();
        }
        if (getTotalTuitionCredits5() != null) {
            _hashCode += getTotalTuitionCredits5().hashCode();
        }
        if (getPrepaidDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPrepaidDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPrepaidDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PrepaidSummaryInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidSummaryInfo"));
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
        elemField.setFieldName("priorYearsGrossPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriorYearsGrossPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priorYearsNetPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PriorYearsNetPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LTDGrossPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LTDGrossPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LTDNetPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "LTDNetPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentYearGrossPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrentYearGrossPurchases"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currentYearNetPurchases");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrentYearNetPurchases"));
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
        elemField.setFieldName("totalTuitionCredits1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalTuitionCredits1"));
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
        elemField.setFieldName("totalTuitionCredits2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalTuitionCredits2"));
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
        elemField.setFieldName("totalTuitionCredits3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalTuitionCredits3"));
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
        elemField.setFieldName("totalTuitionCredits4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalTuitionCredits4"));
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
        elemField.setFieldName("totalTuitionCredits5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "TotalTuitionCredits5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prepaidDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrepaidDetailResult"));
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
