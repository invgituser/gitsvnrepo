package com.invessence.yodlee.model;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * YldInvestmentDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ydl_investment_details",catalog="invdb")

public class InvestmentDetail  implements java.io.Serializable {


    // Fields    

     private Long id;
     private AccountDetail accountDetail;
     private String accType;
     private String accNum;
     private String accName;
     private String accClassification;
     private String planName;
     private Date asOf;
     private Double cash;
     private Double margBal;
     private Double totBal;
     private Double totVestedBal;
     private String accHolder;
     private Double moneyMarketBal;
     private Double accruedInt;
     private String accStatus;
     private Double totUnvestedBal;
     private Double totInvestedAmt;
     private Double loan401k;
     private String secAccHolderName;
     private String linkedBankAccNum;
     private String nomineeName;
     private Date accOpenDate;
     private Date accCloseDate;
     private String planNum;
     private String mutualFundFolioNum;
     private String annuityBal;
     private Double annuityDeathBenefit;
     private Double cashOptionBuyingPower;
     private Double dayTradMargBuyingPower;
     private Double dividendEarnedAsPayOut;
     private Double shortBal;
     private Timestamp insertedOn;
     private Long insertedBy;


    // Constructors

    /** default constructor */
    public InvestmentDetail() {
    }

	/** minimal constructor */
    public InvestmentDetail(Long id, AccountDetail accountDetail, Timestamp insertedOn, Long insertedBy) {
        this.id = id;
        this.accountDetail = accountDetail;
        this.insertedOn = insertedOn;
        this.insertedBy = insertedBy;
    }
    
    /** full constructor */
    public InvestmentDetail(Long id, AccountDetail accountDetail, String accType, String accNum, String accName, String accClassification, String planName, Date asOf, Double cash, Double margBal, Double totBal, Double totVestedBal, String accHolder, Double moneyMarketBal, Double accruedInt, String accStatus, Double totUnvestedBal, Double totInvestedAmt, Double loan401k, String secAccHolderName, String linkedBankAccNum, String nomineeName, Date accOpenDate, Date accCloseDate, String planNum, String mutualFundFolioNum, String annuityBal, Double annuityDeathBenefit, Double cashOptionBuyingPower, Double dayTradMargBuyingPower, Double dividendEarnedAsPayOut, Double shortBal, Timestamp insertedOn, Long insertedBy) {
        this.id = id;
        this.accountDetail = accountDetail;
        this.accType = accType;
        this.accNum = accNum;
        this.accName = accName;
        this.accClassification = accClassification;
        this.planName = planName;
        this.asOf = asOf;
        this.cash = cash;
        this.margBal = margBal;
        this.totBal = totBal;
        this.totVestedBal = totVestedBal;
        this.accHolder = accHolder;
        this.moneyMarketBal = moneyMarketBal;
        this.accruedInt = accruedInt;
        this.accStatus = accStatus;
        this.totUnvestedBal = totUnvestedBal;
        this.totInvestedAmt = totInvestedAmt;
        this.loan401k = loan401k;
        this.secAccHolderName = secAccHolderName;
        this.linkedBankAccNum = linkedBankAccNum;
        this.nomineeName = nomineeName;
        this.accOpenDate = accOpenDate;
        this.accCloseDate = accCloseDate;
        this.planNum = planNum;
        this.mutualFundFolioNum = mutualFundFolioNum;
        this.annuityBal = annuityBal;
        this.annuityDeathBenefit = annuityDeathBenefit;
        this.cashOptionBuyingPower = cashOptionBuyingPower;
        this.dayTradMargBuyingPower = dayTradMargBuyingPower;
        this.dividendEarnedAsPayOut = dividendEarnedAsPayOut;
        this.shortBal = shortBal;
        this.insertedOn = insertedOn;
        this.insertedBy = insertedBy;
    }

   
    // Property accessors
    @Id @GeneratedValue 
    
    @Column(name="ID", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="ACC_DET_ID", nullable=false)

    public AccountDetail getAccountDetail() {
        return this.accountDetail;
    }
    
    public void setAccountDetail(AccountDetail accountDetail) {
        this.accountDetail = accountDetail;
    }
    
    @Column(name="ACC_TYPE", length=100)

    public String getAccType() {
        return this.accType;
    }
    
    public void setAccType(String accType) {
        this.accType = accType;
    }
    
    @Column(name="ACC_NUM", length=100)

    public String getAccNum() {
        return this.accNum;
    }
    
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
    
    @Column(name="ACC_NAME", length=100)

    public String getAccName() {
        return this.accName;
    }
    
    public void setAccName(String accName) {
        this.accName = accName;
    }
    
    @Column(name="ACC_CLASSIFICATION", length=100)

    public String getAccClassification() {
        return this.accClassification;
    }
    
    public void setAccClassification(String accClassification) {
        this.accClassification = accClassification;
    }
    
    @Column(name="PLAN_NAME", length=100)

    public String getPlanName() {
        return this.planName;
    }
    
    public void setPlanName(String planName) {
        this.planName = planName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="AS_OF", length=10)

    public Date getAsOf() {
        return this.asOf;
    }
    
    public void setAsOf(Date asOf) {
        this.asOf = asOf;
    }
    
    @Column(name="CASH", precision=20, scale=4)

    public Double getCash() {
        return this.cash;
    }
    
    public void setCash(Double cash) {
        this.cash = cash;
    }
    
    @Column(name="MARG_BAL", precision=20, scale=4)

    public Double getMargBal() {
        return this.margBal;
    }
    
    public void setMargBal(Double margBal) {
        this.margBal = margBal;
    }
    
    @Column(name="TOT_BAL", precision=20, scale=4)

    public Double getTotBal() {
        return this.totBal;
    }
    
    public void setTotBal(Double totBal) {
        this.totBal = totBal;
    }
    
    @Column(name="TOT_VESTED_BAL", precision=20, scale=4)

    public Double getTotVestedBal() {
        return this.totVestedBal;
    }
    
    public void setTotVestedBal(Double totVestedBal) {
        this.totVestedBal = totVestedBal;
    }
    
    @Column(name="ACC_HOLDER", length=100)

    public String getAccHolder() {
        return this.accHolder;
    }
    
    public void setAccHolder(String accHolder) {
        this.accHolder = accHolder;
    }
    
    @Column(name="MONEY_MARKET_BAL", precision=20, scale=4)

    public Double getMoneyMarketBal() {
        return this.moneyMarketBal;
    }
    
    public void setMoneyMarketBal(Double moneyMarketBal) {
        this.moneyMarketBal = moneyMarketBal;
    }
    
    @Column(name="ACCRUED_INT", precision=22, scale=0)

    public Double getAccruedInt() {
        return this.accruedInt;
    }
    
    public void setAccruedInt(Double accruedInt) {
        this.accruedInt = accruedInt;
    }
    
    @Column(name="ACC_STATUS", length=100)

    public String getAccStatus() {
        return this.accStatus;
    }
    
    public void setAccStatus(String accStatus) {
        this.accStatus = accStatus;
    }
    
    @Column(name="TOT_UNVESTED_BAL", precision=20, scale=4)

    public Double getTotUnvestedBal() {
        return this.totUnvestedBal;
    }
    
    public void setTotUnvestedBal(Double totUnvestedBal) {
        this.totUnvestedBal = totUnvestedBal;
    }
    
    @Column(name="TOT_INVESTED_AMT", precision=20, scale=4)

    public Double getTotInvestedAmt() {
        return this.totInvestedAmt;
    }
    
    public void setTotInvestedAmt(Double totInvestedAmt) {
        this.totInvestedAmt = totInvestedAmt;
    }
    
    @Column(name="401K_LOAN", precision=20, scale=4)

    public Double getLoan401k() {
        return this.loan401k;
    }
    
    public void setLoan401k(Double loan401k) {
        this.loan401k = loan401k;
    }
    
    @Column(name="SEC_ACC_HOLDER_NAME", length=100)

    public String getSecAccHolderName() {
        return this.secAccHolderName;
    }
    
    public void setSecAccHolderName(String secAccHolderName) {
        this.secAccHolderName = secAccHolderName;
    }
    
    @Column(name="LINKED_BANK_ACC_NUM", length=100)

    public String getLinkedBankAccNum() {
        return this.linkedBankAccNum;
    }
    
    public void setLinkedBankAccNum(String linkedBankAccNum) {
        this.linkedBankAccNum = linkedBankAccNum;
    }
    
    @Column(name="NOMINEE_NAME", length=100)

    public String getNomineeName() {
        return this.nomineeName;
    }
    
    public void setNomineeName(String nomineeName) {
        this.nomineeName = nomineeName;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ACC_OPEN_DATE", length=10)

    public Date getAccOpenDate() {
        return this.accOpenDate;
    }
    
    public void setAccOpenDate(Date accOpenDate) {
        this.accOpenDate = accOpenDate;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="ACC_CLOSE_DATE", length=10)

    public Date getAccCloseDate() {
        return this.accCloseDate;
    }
    
    public void setAccCloseDate(Date accCloseDate) {
        this.accCloseDate = accCloseDate;
    }
    
    @Column(name="PLAN_NUM", length=100)

    public String getPlanNum() {
        return this.planNum;
    }
    
    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }
    
    @Column(name="MUTUAL_FUND_FOLIO_NUM", length=100)

    public String getMutualFundFolioNum() {
        return this.mutualFundFolioNum;
    }
    
    public void setMutualFundFolioNum(String mutualFundFolioNum) {
        this.mutualFundFolioNum = mutualFundFolioNum;
    }
    
    @Column(name="ANNUITY_BAL", length=100)

    public String getAnnuityBal() {
        return this.annuityBal;
    }
    
    public void setAnnuityBal(String annuityBal) {
        this.annuityBal = annuityBal;
    }
    
    @Column(name="ANNUITY_DEATH_BENEFIT", precision=20, scale=4)

    public Double getAnnuityDeathBenefit() {
        return this.annuityDeathBenefit;
    }
    
    public void setAnnuityDeathBenefit(Double annuityDeathBenefit) {
        this.annuityDeathBenefit = annuityDeathBenefit;
    }
    
    @Column(name="CASH_OPTION_BUYING_POWER", precision=20, scale=4)

    public Double getCashOptionBuyingPower() {
        return this.cashOptionBuyingPower;
    }
    
    public void setCashOptionBuyingPower(Double cashOptionBuyingPower) {
        this.cashOptionBuyingPower = cashOptionBuyingPower;
    }
    
    @Column(name="DAY_TRAD_MARG_BUYING_POWER", precision=20, scale=4)

    public Double getDayTradMargBuyingPower() {
        return this.dayTradMargBuyingPower;
    }
    
    public void setDayTradMargBuyingPower(Double dayTradMargBuyingPower) {
        this.dayTradMargBuyingPower = dayTradMargBuyingPower;
    }
    
    @Column(name="DIVIDEND_EARNED_AS_PAY_OUT", precision=20, scale=4)

    public Double getDividendEarnedAsPayOut() {
        return this.dividendEarnedAsPayOut;
    }
    
    public void setDividendEarnedAsPayOut(Double dividendEarnedAsPayOut) {
        this.dividendEarnedAsPayOut = dividendEarnedAsPayOut;
    }
    
    @Column(name="SHORT_BAL", precision=20, scale=4)

    public Double getShortBal() {
        return this.shortBal;
    }
    
    public void setShortBal(Double shortBal) {
        this.shortBal = shortBal;
    }
    
    @Column(name="INSERTED_ON", nullable=false, length=19)

    public Timestamp getInsertedOn() {
        return this.insertedOn;
    }
    
    public void setInsertedOn(Timestamp insertedOn) {
        this.insertedOn = insertedOn;
    }
    
    @Column(name="INSERTED_BY", nullable=false)

    public Long getInsertedBy() {
        return this.insertedBy;
    }
    
    public void setInsertedBy(Long insertedBy) {
        this.insertedBy = insertedBy;
    }
   








}