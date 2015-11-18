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
 * YldCardStatement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ydl_card_statements",catalog="invdb"
)

public class CardStatement  implements java.io.Serializable {


    // Fields    

     private Long id;
     private AccountDetail accountDetail;
     private String accType;
     private String accNum;
     private String accName;
     private String accHolder;
     private Date billDate;
     private Double totCreditLine;
     private Date dueDate;
     private Double avilbCredit;
     private Double amtDue;
     private Double minPay;
     private Double endingBal;
     private Double pastDueAmt;
     private Date billingPrdStart;
     private Date billingPrdEnd;
     private Date apr;
     private Date lastPayDate;
     private Double newCharges;
     private Double cashAdvance;
     private Double financeCharges;
     private Double prevEndingBal;
     private Double credit;
     private Double lastPay;
     private Double pays;
     private Double avilbCash;
     private Double totCashLimit;
     private Double prevAmtDue;
     private Double intPaidThisPrd;
     private Double intPaidYtd;
     private Timestamp insertedOn;
     private Long insertedBy;


    // Constructors

    /** default constructor */
    public CardStatement() {
    }

	/** minimal constructor */
    public CardStatement(Long id, AccountDetail accountDetail, Timestamp insertedOn, Long insertedBy) {
        this.id = id;
        this.accountDetail = accountDetail;
        this.insertedOn = insertedOn;
        this.insertedBy = insertedBy;
    }
    
    /** full constructor */
    public CardStatement(Long id, AccountDetail accountDetail, String accType, String accNum, String accName, String accHolder, Date billDate, Double totCreditLine, Date dueDate, Double avilbCredit, Double amtDue, Double minPay, Double endingBal, Double pastDueAmt, Date billingPrdStart, Date billingPrdEnd, Date apr, Date lastPayDate, Double newCharges, Double cashAdvance, Double financeCharges, Double prevEndingBal, Double credit, Double lastPay, Double pays, Double avilbCash, Double totCashLimit, Double prevAmtDue, Double intPaidThisPrd, Double intPaidYtd, Timestamp insertedOn, Long insertedBy) {
        this.id = id;
        this.accountDetail = accountDetail;
        this.accType = accType;
        this.accNum = accNum;
        this.accName = accName;
        this.accHolder = accHolder;
        this.billDate = billDate;
        this.totCreditLine = totCreditLine;
        this.dueDate = dueDate;
        this.avilbCredit = avilbCredit;
        this.amtDue = amtDue;
        this.minPay = minPay;
        this.endingBal = endingBal;
        this.pastDueAmt = pastDueAmt;
        this.billingPrdStart = billingPrdStart;
        this.billingPrdEnd = billingPrdEnd;
        this.apr = apr;
        this.lastPayDate = lastPayDate;
        this.newCharges = newCharges;
        this.cashAdvance = cashAdvance;
        this.financeCharges = financeCharges;
        this.prevEndingBal = prevEndingBal;
        this.credit = credit;
        this.lastPay = lastPay;
        this.pays = pays;
        this.avilbCash = avilbCash;
        this.totCashLimit = totCashLimit;
        this.prevAmtDue = prevAmtDue;
        this.intPaidThisPrd = intPaidThisPrd;
        this.intPaidYtd = intPaidYtd;
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
    
    @Column(name="ACC_HOLDER", length=100)

    public String getAccHolder() {
        return this.accHolder;
    }
    
    public void setAccHolder(String accHolder) {
        this.accHolder = accHolder;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BILL_DATE", length=10)

    public Date getBillDate() {
        return this.billDate;
    }
    
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }
    
    @Column(name="TOT_CREDIT_LINE", precision=20, scale=4)

    public Double getTotCreditLine() {
        return this.totCreditLine;
    }
    
    public void setTotCreditLine(Double totCreditLine) {
        this.totCreditLine = totCreditLine;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="DUE_DATE", length=10)

    public Date getDueDate() {
        return this.dueDate;
    }
    
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
    
    @Column(name="AVILB_CREDIT", precision=20, scale=4)

    public Double getAvilbCredit() {
        return this.avilbCredit;
    }
    
    public void setAvilbCredit(Double avilbCredit) {
        this.avilbCredit = avilbCredit;
    }
    
    @Column(name="AMT_DUE", precision=20, scale=4)

    public Double getAmtDue() {
        return this.amtDue;
    }
    
    public void setAmtDue(Double amtDue) {
        this.amtDue = amtDue;
    }
    
    @Column(name="MIN_PAY", precision=20, scale=4)

    public Double getMinPay() {
        return this.minPay;
    }
    
    public void setMinPay(Double minPay) {
        this.minPay = minPay;
    }
    
    @Column(name="ENDING_BAL", precision=20, scale=4)

    public Double getEndingBal() {
        return this.endingBal;
    }
    
    public void setEndingBal(Double endingBal) {
        this.endingBal = endingBal;
    }
    
    @Column(name="PAST_DUE_AMT", precision=20, scale=4)

    public Double getPastDueAmt() {
        return this.pastDueAmt;
    }
    
    public void setPastDueAmt(Double pastDueAmt) {
        this.pastDueAmt = pastDueAmt;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BILLING_PRD_START", length=10)

    public Date getBillingPrdStart() {
        return this.billingPrdStart;
    }
    
    public void setBillingPrdStart(Date billingPrdStart) {
        this.billingPrdStart = billingPrdStart;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="BILLING_PRD_END", length=10)

    public Date getBillingPrdEnd() {
        return this.billingPrdEnd;
    }
    
    public void setBillingPrdEnd(Date billingPrdEnd) {
        this.billingPrdEnd = billingPrdEnd;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="APR", length=10)

    public Date getApr() {
        return this.apr;
    }
    
    public void setApr(Date apr) {
        this.apr = apr;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="LAST_PAY_DATE", length=10)

    public Date getLastPayDate() {
        return this.lastPayDate;
    }
    
    public void setLastPayDate(Date lastPayDate) {
        this.lastPayDate = lastPayDate;
    }
    
    @Column(name="NEW_CHARGES", precision=20, scale=4)

    public Double getNewCharges() {
        return this.newCharges;
    }
    
    public void setNewCharges(Double newCharges) {
        this.newCharges = newCharges;
    }
    
    @Column(name="CASH_ADVANCE", precision=20, scale=4)

    public Double getCashAdvance() {
        return this.cashAdvance;
    }
    
    public void setCashAdvance(Double cashAdvance) {
        this.cashAdvance = cashAdvance;
    }
    
    @Column(name="FINANCE_CHARGES", precision=20, scale=4)

    public Double getFinanceCharges() {
        return this.financeCharges;
    }
    
    public void setFinanceCharges(Double financeCharges) {
        this.financeCharges = financeCharges;
    }
    
    @Column(name="PREV_ENDING_BAL", precision=20, scale=4)

    public Double getPrevEndingBal() {
        return this.prevEndingBal;
    }
    
    public void setPrevEndingBal(Double prevEndingBal) {
        this.prevEndingBal = prevEndingBal;
    }
    
    @Column(name="CREDIT", precision=20, scale=4)

    public Double getCredit() {
        return this.credit;
    }
    
    public void setCredit(Double credit) {
        this.credit = credit;
    }
    
    @Column(name="LAST_PAY", precision=20, scale=4)

    public Double getLastPay() {
        return this.lastPay;
    }
    
    public void setLastPay(Double lastPay) {
        this.lastPay = lastPay;
    }
    
    @Column(name="PAYS", precision=20, scale=4)

    public Double getPays() {
        return this.pays;
    }
    
    public void setPays(Double pays) {
        this.pays = pays;
    }
    
    @Column(name="AVILB_CASH", precision=20, scale=4)

    public Double getAvilbCash() {
        return this.avilbCash;
    }
    
    public void setAvilbCash(Double avilbCash) {
        this.avilbCash = avilbCash;
    }
    
    @Column(name="TOT_CASH_LIMIT", precision=20, scale=4)

    public Double getTotCashLimit() {
        return this.totCashLimit;
    }
    
    public void setTotCashLimit(Double totCashLimit) {
        this.totCashLimit = totCashLimit;
    }
    
    @Column(name="PREV_AMT_DUE", precision=20, scale=4)

    public Double getPrevAmtDue() {
        return this.prevAmtDue;
    }
    
    public void setPrevAmtDue(Double prevAmtDue) {
        this.prevAmtDue = prevAmtDue;
    }
    
    @Column(name="INT_PAID_THIS_PRD", precision=20, scale=4)

    public Double getIntPaidThisPrd() {
        return this.intPaidThisPrd;
    }
    
    public void setIntPaidThisPrd(Double intPaidThisPrd) {
        this.intPaidThisPrd = intPaidThisPrd;
    }
    
    @Column(name="INT PAID_YTD", precision=20, scale=4)

    public Double getIntPaidYtd() {
        return this.intPaidYtd;
    }
    
    public void setIntPaidYtd(Double intPaidYtd) {
        this.intPaidYtd = intPaidYtd;
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