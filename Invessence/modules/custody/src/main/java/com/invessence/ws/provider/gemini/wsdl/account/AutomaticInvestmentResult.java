/**
 * AutomaticInvestmentResult.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.invessence.ws.provider.gemini.wsdl.account;

public class AutomaticInvestmentResult  implements java.io.Serializable {
    private java.lang.String accountNumber;

    private java.util.Calendar startDate;

    private java.util.Calendar finalProcessingDate;

    private org.apache.axis.types.UnsignedByte schedulerFrequency;

    private java.lang.String frequencyText;

    private java.lang.String startMonth;

    private org.apache.axis.types.UnsignedByte dateQualifierForDay1;

    private org.apache.axis.types.UnsignedByte dateQualifierForDay2;

    private org.apache.axis.types.UnsignedByte dayOfTheMonth1;

    private org.apache.axis.types.UnsignedByte dayOfTheMonth2;

    private java.lang.String monthIndicators;

    private org.apache.axis.types.UnsignedByte accountPayeeId;

    private int automaticInvestmentId;

    private org.apache.axis.types.UnsignedByte noOfAutomaticInvestmentLines;

    private org.apache.axis.types.UnsignedByte currencyId;

    private java.lang.String memo;

    private int monthlySchedulerId;

    private int achPayeeId;

    private short userId;

    private java.lang.String bankName;

    private java.lang.String bankRoutingNumber;

    private org.apache.axis.types.UnsignedByte fundSelectionIndicator;

    private java.lang.String bankAccountNumber;

    private java.lang.String nameOnAccount;

    private org.apache.axis.types.UnsignedByte bankAccountType;

    private java.util.Calendar prenotificationDate;

    private org.apache.axis.types.UnsignedByte sendPrenotification;

    private java.math.BigDecimal AIPAmount;

    private java.lang.String AIPText;

    private org.apache.axis.types.UnsignedByte automaticInvestmentStatus;

    private org.apache.axis.types.UnsignedByte fundid;

    private com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformationResult[] AIPFunds;

    private com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentLinesResult[] automaticInvestmentLines;

    public AutomaticInvestmentResult() {
    }

    public AutomaticInvestmentResult(
           java.lang.String accountNumber,
           java.util.Calendar startDate,
           java.util.Calendar finalProcessingDate,
           org.apache.axis.types.UnsignedByte schedulerFrequency,
           java.lang.String frequencyText,
           java.lang.String startMonth,
           org.apache.axis.types.UnsignedByte dateQualifierForDay1,
           org.apache.axis.types.UnsignedByte dateQualifierForDay2,
           org.apache.axis.types.UnsignedByte dayOfTheMonth1,
           org.apache.axis.types.UnsignedByte dayOfTheMonth2,
           java.lang.String monthIndicators,
           org.apache.axis.types.UnsignedByte accountPayeeId,
           int automaticInvestmentId,
           org.apache.axis.types.UnsignedByte noOfAutomaticInvestmentLines,
           org.apache.axis.types.UnsignedByte currencyId,
           java.lang.String memo,
           int monthlySchedulerId,
           int achPayeeId,
           short userId,
           java.lang.String bankName,
           java.lang.String bankRoutingNumber,
           org.apache.axis.types.UnsignedByte fundSelectionIndicator,
           java.lang.String bankAccountNumber,
           java.lang.String nameOnAccount,
           org.apache.axis.types.UnsignedByte bankAccountType,
           java.util.Calendar prenotificationDate,
           org.apache.axis.types.UnsignedByte sendPrenotification,
           java.math.BigDecimal AIPAmount,
           java.lang.String AIPText,
           org.apache.axis.types.UnsignedByte automaticInvestmentStatus,
           org.apache.axis.types.UnsignedByte fundid,
           com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformationResult[] AIPFunds,
           com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentLinesResult[] automaticInvestmentLines) {
           this.accountNumber = accountNumber;
           this.startDate = startDate;
           this.finalProcessingDate = finalProcessingDate;
           this.schedulerFrequency = schedulerFrequency;
           this.frequencyText = frequencyText;
           this.startMonth = startMonth;
           this.dateQualifierForDay1 = dateQualifierForDay1;
           this.dateQualifierForDay2 = dateQualifierForDay2;
           this.dayOfTheMonth1 = dayOfTheMonth1;
           this.dayOfTheMonth2 = dayOfTheMonth2;
           this.monthIndicators = monthIndicators;
           this.accountPayeeId = accountPayeeId;
           this.automaticInvestmentId = automaticInvestmentId;
           this.noOfAutomaticInvestmentLines = noOfAutomaticInvestmentLines;
           this.currencyId = currencyId;
           this.memo = memo;
           this.monthlySchedulerId = monthlySchedulerId;
           this.achPayeeId = achPayeeId;
           this.userId = userId;
           this.bankName = bankName;
           this.bankRoutingNumber = bankRoutingNumber;
           this.fundSelectionIndicator = fundSelectionIndicator;
           this.bankAccountNumber = bankAccountNumber;
           this.nameOnAccount = nameOnAccount;
           this.bankAccountType = bankAccountType;
           this.prenotificationDate = prenotificationDate;
           this.sendPrenotification = sendPrenotification;
           this.AIPAmount = AIPAmount;
           this.AIPText = AIPText;
           this.automaticInvestmentStatus = automaticInvestmentStatus;
           this.fundid = fundid;
           this.AIPFunds = AIPFunds;
           this.automaticInvestmentLines = automaticInvestmentLines;
    }


    /**
     * Gets the accountNumber value for this AutomaticInvestmentResult.
     * 
     * @return accountNumber
     */
    public java.lang.String getAccountNumber() {
        return accountNumber;
    }


    /**
     * Sets the accountNumber value for this AutomaticInvestmentResult.
     * 
     * @param accountNumber
     */
    public void setAccountNumber(java.lang.String accountNumber) {
        this.accountNumber = accountNumber;
    }


    /**
     * Gets the startDate value for this AutomaticInvestmentResult.
     * 
     * @return startDate
     */
    public java.util.Calendar getStartDate() {
        return startDate;
    }


    /**
     * Sets the startDate value for this AutomaticInvestmentResult.
     * 
     * @param startDate
     */
    public void setStartDate(java.util.Calendar startDate) {
        this.startDate = startDate;
    }


    /**
     * Gets the finalProcessingDate value for this AutomaticInvestmentResult.
     * 
     * @return finalProcessingDate
     */
    public java.util.Calendar getFinalProcessingDate() {
        return finalProcessingDate;
    }


    /**
     * Sets the finalProcessingDate value for this AutomaticInvestmentResult.
     * 
     * @param finalProcessingDate
     */
    public void setFinalProcessingDate(java.util.Calendar finalProcessingDate) {
        this.finalProcessingDate = finalProcessingDate;
    }


    /**
     * Gets the schedulerFrequency value for this AutomaticInvestmentResult.
     * 
     * @return schedulerFrequency
     */
    public org.apache.axis.types.UnsignedByte getSchedulerFrequency() {
        return schedulerFrequency;
    }


    /**
     * Sets the schedulerFrequency value for this AutomaticInvestmentResult.
     * 
     * @param schedulerFrequency
     */
    public void setSchedulerFrequency(org.apache.axis.types.UnsignedByte schedulerFrequency) {
        this.schedulerFrequency = schedulerFrequency;
    }


    /**
     * Gets the frequencyText value for this AutomaticInvestmentResult.
     * 
     * @return frequencyText
     */
    public java.lang.String getFrequencyText() {
        return frequencyText;
    }


    /**
     * Sets the frequencyText value for this AutomaticInvestmentResult.
     * 
     * @param frequencyText
     */
    public void setFrequencyText(java.lang.String frequencyText) {
        this.frequencyText = frequencyText;
    }


    /**
     * Gets the startMonth value for this AutomaticInvestmentResult.
     * 
     * @return startMonth
     */
    public java.lang.String getStartMonth() {
        return startMonth;
    }


    /**
     * Sets the startMonth value for this AutomaticInvestmentResult.
     * 
     * @param startMonth
     */
    public void setStartMonth(java.lang.String startMonth) {
        this.startMonth = startMonth;
    }


    /**
     * Gets the dateQualifierForDay1 value for this AutomaticInvestmentResult.
     * 
     * @return dateQualifierForDay1
     */
    public org.apache.axis.types.UnsignedByte getDateQualifierForDay1() {
        return dateQualifierForDay1;
    }


    /**
     * Sets the dateQualifierForDay1 value for this AutomaticInvestmentResult.
     * 
     * @param dateQualifierForDay1
     */
    public void setDateQualifierForDay1(org.apache.axis.types.UnsignedByte dateQualifierForDay1) {
        this.dateQualifierForDay1 = dateQualifierForDay1;
    }


    /**
     * Gets the dateQualifierForDay2 value for this AutomaticInvestmentResult.
     * 
     * @return dateQualifierForDay2
     */
    public org.apache.axis.types.UnsignedByte getDateQualifierForDay2() {
        return dateQualifierForDay2;
    }


    /**
     * Sets the dateQualifierForDay2 value for this AutomaticInvestmentResult.
     * 
     * @param dateQualifierForDay2
     */
    public void setDateQualifierForDay2(org.apache.axis.types.UnsignedByte dateQualifierForDay2) {
        this.dateQualifierForDay2 = dateQualifierForDay2;
    }


    /**
     * Gets the dayOfTheMonth1 value for this AutomaticInvestmentResult.
     * 
     * @return dayOfTheMonth1
     */
    public org.apache.axis.types.UnsignedByte getDayOfTheMonth1() {
        return dayOfTheMonth1;
    }


    /**
     * Sets the dayOfTheMonth1 value for this AutomaticInvestmentResult.
     * 
     * @param dayOfTheMonth1
     */
    public void setDayOfTheMonth1(org.apache.axis.types.UnsignedByte dayOfTheMonth1) {
        this.dayOfTheMonth1 = dayOfTheMonth1;
    }


    /**
     * Gets the dayOfTheMonth2 value for this AutomaticInvestmentResult.
     * 
     * @return dayOfTheMonth2
     */
    public org.apache.axis.types.UnsignedByte getDayOfTheMonth2() {
        return dayOfTheMonth2;
    }


    /**
     * Sets the dayOfTheMonth2 value for this AutomaticInvestmentResult.
     * 
     * @param dayOfTheMonth2
     */
    public void setDayOfTheMonth2(org.apache.axis.types.UnsignedByte dayOfTheMonth2) {
        this.dayOfTheMonth2 = dayOfTheMonth2;
    }


    /**
     * Gets the monthIndicators value for this AutomaticInvestmentResult.
     * 
     * @return monthIndicators
     */
    public java.lang.String getMonthIndicators() {
        return monthIndicators;
    }


    /**
     * Sets the monthIndicators value for this AutomaticInvestmentResult.
     * 
     * @param monthIndicators
     */
    public void setMonthIndicators(java.lang.String monthIndicators) {
        this.monthIndicators = monthIndicators;
    }


    /**
     * Gets the accountPayeeId value for this AutomaticInvestmentResult.
     * 
     * @return accountPayeeId
     */
    public org.apache.axis.types.UnsignedByte getAccountPayeeId() {
        return accountPayeeId;
    }


    /**
     * Sets the accountPayeeId value for this AutomaticInvestmentResult.
     * 
     * @param accountPayeeId
     */
    public void setAccountPayeeId(org.apache.axis.types.UnsignedByte accountPayeeId) {
        this.accountPayeeId = accountPayeeId;
    }


    /**
     * Gets the automaticInvestmentId value for this AutomaticInvestmentResult.
     * 
     * @return automaticInvestmentId
     */
    public int getAutomaticInvestmentId() {
        return automaticInvestmentId;
    }


    /**
     * Sets the automaticInvestmentId value for this AutomaticInvestmentResult.
     * 
     * @param automaticInvestmentId
     */
    public void setAutomaticInvestmentId(int automaticInvestmentId) {
        this.automaticInvestmentId = automaticInvestmentId;
    }


    /**
     * Gets the noOfAutomaticInvestmentLines value for this AutomaticInvestmentResult.
     * 
     * @return noOfAutomaticInvestmentLines
     */
    public org.apache.axis.types.UnsignedByte getNoOfAutomaticInvestmentLines() {
        return noOfAutomaticInvestmentLines;
    }


    /**
     * Sets the noOfAutomaticInvestmentLines value for this AutomaticInvestmentResult.
     * 
     * @param noOfAutomaticInvestmentLines
     */
    public void setNoOfAutomaticInvestmentLines(org.apache.axis.types.UnsignedByte noOfAutomaticInvestmentLines) {
        this.noOfAutomaticInvestmentLines = noOfAutomaticInvestmentLines;
    }


    /**
     * Gets the currencyId value for this AutomaticInvestmentResult.
     * 
     * @return currencyId
     */
    public org.apache.axis.types.UnsignedByte getCurrencyId() {
        return currencyId;
    }


    /**
     * Sets the currencyId value for this AutomaticInvestmentResult.
     * 
     * @param currencyId
     */
    public void setCurrencyId(org.apache.axis.types.UnsignedByte currencyId) {
        this.currencyId = currencyId;
    }


    /**
     * Gets the memo value for this AutomaticInvestmentResult.
     * 
     * @return memo
     */
    public java.lang.String getMemo() {
        return memo;
    }


    /**
     * Sets the memo value for this AutomaticInvestmentResult.
     * 
     * @param memo
     */
    public void setMemo(java.lang.String memo) {
        this.memo = memo;
    }


    /**
     * Gets the monthlySchedulerId value for this AutomaticInvestmentResult.
     * 
     * @return monthlySchedulerId
     */
    public int getMonthlySchedulerId() {
        return monthlySchedulerId;
    }


    /**
     * Sets the monthlySchedulerId value for this AutomaticInvestmentResult.
     * 
     * @param monthlySchedulerId
     */
    public void setMonthlySchedulerId(int monthlySchedulerId) {
        this.monthlySchedulerId = monthlySchedulerId;
    }


    /**
     * Gets the achPayeeId value for this AutomaticInvestmentResult.
     * 
     * @return achPayeeId
     */
    public int getAchPayeeId() {
        return achPayeeId;
    }


    /**
     * Sets the achPayeeId value for this AutomaticInvestmentResult.
     * 
     * @param achPayeeId
     */
    public void setAchPayeeId(int achPayeeId) {
        this.achPayeeId = achPayeeId;
    }


    /**
     * Gets the userId value for this AutomaticInvestmentResult.
     * 
     * @return userId
     */
    public short getUserId() {
        return userId;
    }


    /**
     * Sets the userId value for this AutomaticInvestmentResult.
     * 
     * @param userId
     */
    public void setUserId(short userId) {
        this.userId = userId;
    }


    /**
     * Gets the bankName value for this AutomaticInvestmentResult.
     * 
     * @return bankName
     */
    public java.lang.String getBankName() {
        return bankName;
    }


    /**
     * Sets the bankName value for this AutomaticInvestmentResult.
     * 
     * @param bankName
     */
    public void setBankName(java.lang.String bankName) {
        this.bankName = bankName;
    }


    /**
     * Gets the bankRoutingNumber value for this AutomaticInvestmentResult.
     * 
     * @return bankRoutingNumber
     */
    public java.lang.String getBankRoutingNumber() {
        return bankRoutingNumber;
    }


    /**
     * Sets the bankRoutingNumber value for this AutomaticInvestmentResult.
     * 
     * @param bankRoutingNumber
     */
    public void setBankRoutingNumber(java.lang.String bankRoutingNumber) {
        this.bankRoutingNumber = bankRoutingNumber;
    }


    /**
     * Gets the fundSelectionIndicator value for this AutomaticInvestmentResult.
     * 
     * @return fundSelectionIndicator
     */
    public org.apache.axis.types.UnsignedByte getFundSelectionIndicator() {
        return fundSelectionIndicator;
    }


    /**
     * Sets the fundSelectionIndicator value for this AutomaticInvestmentResult.
     * 
     * @param fundSelectionIndicator
     */
    public void setFundSelectionIndicator(org.apache.axis.types.UnsignedByte fundSelectionIndicator) {
        this.fundSelectionIndicator = fundSelectionIndicator;
    }


    /**
     * Gets the bankAccountNumber value for this AutomaticInvestmentResult.
     * 
     * @return bankAccountNumber
     */
    public java.lang.String getBankAccountNumber() {
        return bankAccountNumber;
    }


    /**
     * Sets the bankAccountNumber value for this AutomaticInvestmentResult.
     * 
     * @param bankAccountNumber
     */
    public void setBankAccountNumber(java.lang.String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }


    /**
     * Gets the nameOnAccount value for this AutomaticInvestmentResult.
     * 
     * @return nameOnAccount
     */
    public java.lang.String getNameOnAccount() {
        return nameOnAccount;
    }


    /**
     * Sets the nameOnAccount value for this AutomaticInvestmentResult.
     * 
     * @param nameOnAccount
     */
    public void setNameOnAccount(java.lang.String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }


    /**
     * Gets the bankAccountType value for this AutomaticInvestmentResult.
     * 
     * @return bankAccountType
     */
    public org.apache.axis.types.UnsignedByte getBankAccountType() {
        return bankAccountType;
    }


    /**
     * Sets the bankAccountType value for this AutomaticInvestmentResult.
     * 
     * @param bankAccountType
     */
    public void setBankAccountType(org.apache.axis.types.UnsignedByte bankAccountType) {
        this.bankAccountType = bankAccountType;
    }


    /**
     * Gets the prenotificationDate value for this AutomaticInvestmentResult.
     * 
     * @return prenotificationDate
     */
    public java.util.Calendar getPrenotificationDate() {
        return prenotificationDate;
    }


    /**
     * Sets the prenotificationDate value for this AutomaticInvestmentResult.
     * 
     * @param prenotificationDate
     */
    public void setPrenotificationDate(java.util.Calendar prenotificationDate) {
        this.prenotificationDate = prenotificationDate;
    }


    /**
     * Gets the sendPrenotification value for this AutomaticInvestmentResult.
     * 
     * @return sendPrenotification
     */
    public org.apache.axis.types.UnsignedByte getSendPrenotification() {
        return sendPrenotification;
    }


    /**
     * Sets the sendPrenotification value for this AutomaticInvestmentResult.
     * 
     * @param sendPrenotification
     */
    public void setSendPrenotification(org.apache.axis.types.UnsignedByte sendPrenotification) {
        this.sendPrenotification = sendPrenotification;
    }


    /**
     * Gets the AIPAmount value for this AutomaticInvestmentResult.
     * 
     * @return AIPAmount
     */
    public java.math.BigDecimal getAIPAmount() {
        return AIPAmount;
    }


    /**
     * Sets the AIPAmount value for this AutomaticInvestmentResult.
     * 
     * @param AIPAmount
     */
    public void setAIPAmount(java.math.BigDecimal AIPAmount) {
        this.AIPAmount = AIPAmount;
    }


    /**
     * Gets the AIPText value for this AutomaticInvestmentResult.
     * 
     * @return AIPText
     */
    public java.lang.String getAIPText() {
        return AIPText;
    }


    /**
     * Sets the AIPText value for this AutomaticInvestmentResult.
     * 
     * @param AIPText
     */
    public void setAIPText(java.lang.String AIPText) {
        this.AIPText = AIPText;
    }


    /**
     * Gets the automaticInvestmentStatus value for this AutomaticInvestmentResult.
     * 
     * @return automaticInvestmentStatus
     */
    public org.apache.axis.types.UnsignedByte getAutomaticInvestmentStatus() {
        return automaticInvestmentStatus;
    }


    /**
     * Sets the automaticInvestmentStatus value for this AutomaticInvestmentResult.
     * 
     * @param automaticInvestmentStatus
     */
    public void setAutomaticInvestmentStatus(org.apache.axis.types.UnsignedByte automaticInvestmentStatus) {
        this.automaticInvestmentStatus = automaticInvestmentStatus;
    }


    /**
     * Gets the fundid value for this AutomaticInvestmentResult.
     * 
     * @return fundid
     */
    public org.apache.axis.types.UnsignedByte getFundid() {
        return fundid;
    }


    /**
     * Sets the fundid value for this AutomaticInvestmentResult.
     * 
     * @param fundid
     */
    public void setFundid(org.apache.axis.types.UnsignedByte fundid) {
        this.fundid = fundid;
    }


    /**
     * Gets the AIPFunds value for this AutomaticInvestmentResult.
     * 
     * @return AIPFunds
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformationResult[] getAIPFunds() {
        return AIPFunds;
    }


    /**
     * Sets the AIPFunds value for this AutomaticInvestmentResult.
     * 
     * @param AIPFunds
     */
    public void setAIPFunds(com.invessence.ws.provider.gemini.wsdl.account.AIPFundInformationResult[] AIPFunds) {
        this.AIPFunds = AIPFunds;
    }


    /**
     * Gets the automaticInvestmentLines value for this AutomaticInvestmentResult.
     * 
     * @return automaticInvestmentLines
     */
    public com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentLinesResult[] getAutomaticInvestmentLines() {
        return automaticInvestmentLines;
    }


    /**
     * Sets the automaticInvestmentLines value for this AutomaticInvestmentResult.
     * 
     * @param automaticInvestmentLines
     */
    public void setAutomaticInvestmentLines(com.invessence.ws.provider.gemini.wsdl.account.AutomaticInvestmentLinesResult[] automaticInvestmentLines) {
        this.automaticInvestmentLines = automaticInvestmentLines;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AutomaticInvestmentResult)) return false;
        AutomaticInvestmentResult other = (AutomaticInvestmentResult) obj;
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
            ((this.startDate==null && other.getStartDate()==null) || 
             (this.startDate!=null &&
              this.startDate.equals(other.getStartDate()))) &&
            ((this.finalProcessingDate==null && other.getFinalProcessingDate()==null) || 
             (this.finalProcessingDate!=null &&
              this.finalProcessingDate.equals(other.getFinalProcessingDate()))) &&
            ((this.schedulerFrequency==null && other.getSchedulerFrequency()==null) || 
             (this.schedulerFrequency!=null &&
              this.schedulerFrequency.equals(other.getSchedulerFrequency()))) &&
            ((this.frequencyText==null && other.getFrequencyText()==null) || 
             (this.frequencyText!=null &&
              this.frequencyText.equals(other.getFrequencyText()))) &&
            ((this.startMonth==null && other.getStartMonth()==null) || 
             (this.startMonth!=null &&
              this.startMonth.equals(other.getStartMonth()))) &&
            ((this.dateQualifierForDay1==null && other.getDateQualifierForDay1()==null) || 
             (this.dateQualifierForDay1!=null &&
              this.dateQualifierForDay1.equals(other.getDateQualifierForDay1()))) &&
            ((this.dateQualifierForDay2==null && other.getDateQualifierForDay2()==null) || 
             (this.dateQualifierForDay2!=null &&
              this.dateQualifierForDay2.equals(other.getDateQualifierForDay2()))) &&
            ((this.dayOfTheMonth1==null && other.getDayOfTheMonth1()==null) || 
             (this.dayOfTheMonth1!=null &&
              this.dayOfTheMonth1.equals(other.getDayOfTheMonth1()))) &&
            ((this.dayOfTheMonth2==null && other.getDayOfTheMonth2()==null) || 
             (this.dayOfTheMonth2!=null &&
              this.dayOfTheMonth2.equals(other.getDayOfTheMonth2()))) &&
            ((this.monthIndicators==null && other.getMonthIndicators()==null) || 
             (this.monthIndicators!=null &&
              this.monthIndicators.equals(other.getMonthIndicators()))) &&
            ((this.accountPayeeId==null && other.getAccountPayeeId()==null) || 
             (this.accountPayeeId!=null &&
              this.accountPayeeId.equals(other.getAccountPayeeId()))) &&
            this.automaticInvestmentId == other.getAutomaticInvestmentId() &&
            ((this.noOfAutomaticInvestmentLines==null && other.getNoOfAutomaticInvestmentLines()==null) || 
             (this.noOfAutomaticInvestmentLines!=null &&
              this.noOfAutomaticInvestmentLines.equals(other.getNoOfAutomaticInvestmentLines()))) &&
            ((this.currencyId==null && other.getCurrencyId()==null) || 
             (this.currencyId!=null &&
              this.currencyId.equals(other.getCurrencyId()))) &&
            ((this.memo==null && other.getMemo()==null) || 
             (this.memo!=null &&
              this.memo.equals(other.getMemo()))) &&
            this.monthlySchedulerId == other.getMonthlySchedulerId() &&
            this.achPayeeId == other.getAchPayeeId() &&
            this.userId == other.getUserId() &&
            ((this.bankName==null && other.getBankName()==null) || 
             (this.bankName!=null &&
              this.bankName.equals(other.getBankName()))) &&
            ((this.bankRoutingNumber==null && other.getBankRoutingNumber()==null) || 
             (this.bankRoutingNumber!=null &&
              this.bankRoutingNumber.equals(other.getBankRoutingNumber()))) &&
            ((this.fundSelectionIndicator==null && other.getFundSelectionIndicator()==null) || 
             (this.fundSelectionIndicator!=null &&
              this.fundSelectionIndicator.equals(other.getFundSelectionIndicator()))) &&
            ((this.bankAccountNumber==null && other.getBankAccountNumber()==null) || 
             (this.bankAccountNumber!=null &&
              this.bankAccountNumber.equals(other.getBankAccountNumber()))) &&
            ((this.nameOnAccount==null && other.getNameOnAccount()==null) || 
             (this.nameOnAccount!=null &&
              this.nameOnAccount.equals(other.getNameOnAccount()))) &&
            ((this.bankAccountType==null && other.getBankAccountType()==null) || 
             (this.bankAccountType!=null &&
              this.bankAccountType.equals(other.getBankAccountType()))) &&
            ((this.prenotificationDate==null && other.getPrenotificationDate()==null) || 
             (this.prenotificationDate!=null &&
              this.prenotificationDate.equals(other.getPrenotificationDate()))) &&
            ((this.sendPrenotification==null && other.getSendPrenotification()==null) || 
             (this.sendPrenotification!=null &&
              this.sendPrenotification.equals(other.getSendPrenotification()))) &&
            ((this.AIPAmount==null && other.getAIPAmount()==null) || 
             (this.AIPAmount!=null &&
              this.AIPAmount.equals(other.getAIPAmount()))) &&
            ((this.AIPText==null && other.getAIPText()==null) || 
             (this.AIPText!=null &&
              this.AIPText.equals(other.getAIPText()))) &&
            ((this.automaticInvestmentStatus==null && other.getAutomaticInvestmentStatus()==null) || 
             (this.automaticInvestmentStatus!=null &&
              this.automaticInvestmentStatus.equals(other.getAutomaticInvestmentStatus()))) &&
            ((this.fundid==null && other.getFundid()==null) || 
             (this.fundid!=null &&
              this.fundid.equals(other.getFundid()))) &&
            ((this.AIPFunds==null && other.getAIPFunds()==null) || 
             (this.AIPFunds!=null &&
              java.util.Arrays.equals(this.AIPFunds, other.getAIPFunds()))) &&
            ((this.automaticInvestmentLines==null && other.getAutomaticInvestmentLines()==null) || 
             (this.automaticInvestmentLines!=null &&
              java.util.Arrays.equals(this.automaticInvestmentLines, other.getAutomaticInvestmentLines())));
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
        if (getStartDate() != null) {
            _hashCode += getStartDate().hashCode();
        }
        if (getFinalProcessingDate() != null) {
            _hashCode += getFinalProcessingDate().hashCode();
        }
        if (getSchedulerFrequency() != null) {
            _hashCode += getSchedulerFrequency().hashCode();
        }
        if (getFrequencyText() != null) {
            _hashCode += getFrequencyText().hashCode();
        }
        if (getStartMonth() != null) {
            _hashCode += getStartMonth().hashCode();
        }
        if (getDateQualifierForDay1() != null) {
            _hashCode += getDateQualifierForDay1().hashCode();
        }
        if (getDateQualifierForDay2() != null) {
            _hashCode += getDateQualifierForDay2().hashCode();
        }
        if (getDayOfTheMonth1() != null) {
            _hashCode += getDayOfTheMonth1().hashCode();
        }
        if (getDayOfTheMonth2() != null) {
            _hashCode += getDayOfTheMonth2().hashCode();
        }
        if (getMonthIndicators() != null) {
            _hashCode += getMonthIndicators().hashCode();
        }
        if (getAccountPayeeId() != null) {
            _hashCode += getAccountPayeeId().hashCode();
        }
        _hashCode += getAutomaticInvestmentId();
        if (getNoOfAutomaticInvestmentLines() != null) {
            _hashCode += getNoOfAutomaticInvestmentLines().hashCode();
        }
        if (getCurrencyId() != null) {
            _hashCode += getCurrencyId().hashCode();
        }
        if (getMemo() != null) {
            _hashCode += getMemo().hashCode();
        }
        _hashCode += getMonthlySchedulerId();
        _hashCode += getAchPayeeId();
        _hashCode += getUserId();
        if (getBankName() != null) {
            _hashCode += getBankName().hashCode();
        }
        if (getBankRoutingNumber() != null) {
            _hashCode += getBankRoutingNumber().hashCode();
        }
        if (getFundSelectionIndicator() != null) {
            _hashCode += getFundSelectionIndicator().hashCode();
        }
        if (getBankAccountNumber() != null) {
            _hashCode += getBankAccountNumber().hashCode();
        }
        if (getNameOnAccount() != null) {
            _hashCode += getNameOnAccount().hashCode();
        }
        if (getBankAccountType() != null) {
            _hashCode += getBankAccountType().hashCode();
        }
        if (getPrenotificationDate() != null) {
            _hashCode += getPrenotificationDate().hashCode();
        }
        if (getSendPrenotification() != null) {
            _hashCode += getSendPrenotification().hashCode();
        }
        if (getAIPAmount() != null) {
            _hashCode += getAIPAmount().hashCode();
        }
        if (getAIPText() != null) {
            _hashCode += getAIPText().hashCode();
        }
        if (getAutomaticInvestmentStatus() != null) {
            _hashCode += getAutomaticInvestmentStatus().hashCode();
        }
        if (getFundid() != null) {
            _hashCode += getFundid().hashCode();
        }
        if (getAIPFunds() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAIPFunds());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAIPFunds(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAutomaticInvestmentLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAutomaticInvestmentLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAutomaticInvestmentLines(), i);
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
        new org.apache.axis.description.TypeDesc(AutomaticInvestmentResult.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentResult"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("finalProcessingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FinalProcessingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("schedulerFrequency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SchedulerFrequency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frequencyText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FrequencyText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startMonth");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "StartMonth"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateQualifierForDay1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateQualifierForDay1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateQualifierForDay2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DateQualifierForDay2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayOfTheMonth1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DayOfTheMonth1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dayOfTheMonth2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "DayOfTheMonth2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("monthIndicators");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MonthIndicators"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AccountPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("noOfAutomaticInvestmentLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NoOfAutomaticInvestmentLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "CurrencyId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
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
        elemField.setFieldName("monthlySchedulerId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "MonthlySchedulerId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("achPayeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AchPayeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "UserId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
        elemField.setFieldName("fundSelectionIndicator");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "FundSelectionIndicator"));
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
        elemField.setFieldName("nameOnAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "NameOnAccount"));
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
        elemField.setFieldName("prenotificationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "PrenotificationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sendPrenotification");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "SendPrenotification"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AIPAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AIPText");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPText"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fundid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "Fundid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "unsignedByte"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AIPFunds");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFunds"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformationResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AIPFundInformationResult"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("automaticInvestmentLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://enfs.com/webservices/", "AutomaticInvestmentLinesResult"));
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
