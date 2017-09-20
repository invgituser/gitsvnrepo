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
 * YldLoanHolding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_loan_holdings", catalog = "invdb")
public class LoanHolding implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private Date dueDate;
	private Date billDate;
	private Date statementDate;
	private String historic;
	private Double intAmt;
	private Double princAmt;
	private Double curBal;
	private Double balAmtDue;
	private Double lateChargesDue;
	private Double intPaidLastYear;
	private Double miscFees;
	private String miscFeeDesc;
	private Double pastDueAmt;
	private Date billingPrdStart;
	private Date billingPrdEnd;
	private Date lastPayDate;
	private Date tranListFromDate;
	private Date tranListToDate;
	private Double latePayCharge;
	private Double prevEndingBal;
	private Double insurancePaidYtd;
	private Double escrow;
	private Double propertyTax;
	private Double propertyTaxYtd;
	private Double insuranceEscrow;
	private Double propertyTaxEscrow;
	private Double intPaidYtd;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public LoanHolding() {
	}

	/** minimal constructor */
	public LoanHolding(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public LoanHolding(Long id, AccountDetail accountDetail,
			Date dueDate, Date billDate, Date statementDate, String historic,
			Double intAmt, Double princAmt, Double curBal, Double balAmtDue,
			Double lateChargesDue, Double intPaidLastYear, Double miscFees,
			String miscFeeDesc, Double pastDueAmt, Date billingPrdStart,
			Date billingPrdEnd, Date lastPayDate, Date tranListFromDate,
			Date tranListToDate, Double latePayCharge, Double prevEndingBal,
			Double insurancePaidYtd, Double escrow, Double propertyTax,
			Double propertyTaxYtd, Double insuranceEscrow,
			Double propertyTaxEscrow, Double intPaidYtd, Timestamp insertedOn,
			Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.dueDate = dueDate;
		this.billDate = billDate;
		this.statementDate = statementDate;
		this.historic = historic;
		this.intAmt = intAmt;
		this.princAmt = princAmt;
		this.curBal = curBal;
		this.balAmtDue = balAmtDue;
		this.lateChargesDue = lateChargesDue;
		this.intPaidLastYear = intPaidLastYear;
		this.miscFees = miscFees;
		this.miscFeeDesc = miscFeeDesc;
		this.pastDueAmt = pastDueAmt;
		this.billingPrdStart = billingPrdStart;
		this.billingPrdEnd = billingPrdEnd;
		this.lastPayDate = lastPayDate;
		this.tranListFromDate = tranListFromDate;
		this.tranListToDate = tranListToDate;
		this.latePayCharge = latePayCharge;
		this.prevEndingBal = prevEndingBal;
		this.insurancePaidYtd = insurancePaidYtd;
		this.escrow = escrow;
		this.propertyTax = propertyTax;
		this.propertyTaxYtd = propertyTaxYtd;
		this.insuranceEscrow = insuranceEscrow;
		this.propertyTaxEscrow = propertyTaxEscrow;
		this.intPaidYtd = intPaidYtd;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	// Property accessors
	@Id @GeneratedValue
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_DET_ID", nullable = false)
	public AccountDetail getAccountDetail() {
		return this.accountDetail;
	}

	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BILL_DATE", length = 10)
	public Date getBillDate() {
		return this.billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "STATEMENT_DATE", length = 10)
	public Date getStatementDate() {
		return this.statementDate;
	}

	public void setStatementDate(Date statementDate) {
		this.statementDate = statementDate;
	}

	@Column(name = "HISTORIC", length = 100)
	public String getHistoric() {
		return this.historic;
	}

	public void setHistoric(String historic) {
		this.historic = historic;
	}

	@Column(name = "INT_AMT", precision = 20, scale = 4)
	public Double getIntAmt() {
		return this.intAmt;
	}

	public void setIntAmt(Double intAmt) {
		this.intAmt = intAmt;
	}

	@Column(name = "PRINC_AMT", precision = 20, scale = 4)
	public Double getPrincAmt() {
		return this.princAmt;
	}

	public void setPrincAmt(Double princAmt) {
		this.princAmt = princAmt;
	}

	@Column(name = "CUR_BAL", precision = 20, scale = 4)
	public Double getCurBal() {
		return this.curBal;
	}

	public void setCurBal(Double curBal) {
		this.curBal = curBal;
	}

	@Column(name = "BAL_AMT_DUE", precision = 20, scale = 4)
	public Double getBalAmtDue() {
		return this.balAmtDue;
	}

	public void setBalAmtDue(Double balAmtDue) {
		this.balAmtDue = balAmtDue;
	}

	@Column(name = "LATE_CHARGES_DUE", precision = 20, scale = 4)
	public Double getLateChargesDue() {
		return this.lateChargesDue;
	}

	public void setLateChargesDue(Double lateChargesDue) {
		this.lateChargesDue = lateChargesDue;
	}

	@Column(name = "INT_PAID_LAST_YEAR", precision = 20, scale = 4)
	public Double getIntPaidLastYear() {
		return this.intPaidLastYear;
	}

	public void setIntPaidLastYear(Double intPaidLastYear) {
		this.intPaidLastYear = intPaidLastYear;
	}

	@Column(name = "MISC_FEES", precision = 20, scale = 4)
	public Double getMiscFees() {
		return this.miscFees;
	}

	public void setMiscFees(Double miscFees) {
		this.miscFees = miscFees;
	}

	@Column(name = "MISC_FEE_DESC", length = 100)
	public String getMiscFeeDesc() {
		return this.miscFeeDesc;
	}

	public void setMiscFeeDesc(String miscFeeDesc) {
		this.miscFeeDesc = miscFeeDesc;
	}

	@Column(name = "PAST_DUE_AMT", precision = 20, scale = 4)
	public Double getPastDueAmt() {
		return this.pastDueAmt;
	}

	public void setPastDueAmt(Double pastDueAmt) {
		this.pastDueAmt = pastDueAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BILLING_PRD_START", length = 10)
	public Date getBillingPrdStart() {
		return this.billingPrdStart;
	}

	public void setBillingPrdStart(Date billingPrdStart) {
		this.billingPrdStart = billingPrdStart;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BILLING_PRD_END", length = 10)
	public Date getBillingPrdEnd() {
		return this.billingPrdEnd;
	}

	public void setBillingPrdEnd(Date billingPrdEnd) {
		this.billingPrdEnd = billingPrdEnd;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_PAY_DATE", length = 10)
	public Date getLastPayDate() {
		return this.lastPayDate;
	}

	public void setLastPayDate(Date lastPayDate) {
		this.lastPayDate = lastPayDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAN_LIST_FROM_DATE", length = 10)
	public Date getTranListFromDate() {
		return this.tranListFromDate;
	}

	public void setTranListFromDate(Date tranListFromDate) {
		this.tranListFromDate = tranListFromDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAN_LIST_TO_DATE", length = 10)
	public Date getTranListToDate() {
		return this.tranListToDate;
	}

	public void setTranListToDate(Date tranListToDate) {
		this.tranListToDate = tranListToDate;
	}

	@Column(name = "LATE_PAY_CHARGE", precision = 20, scale = 4)
	public Double getLatePayCharge() {
		return this.latePayCharge;
	}

	public void setLatePayCharge(Double latePayCharge) {
		this.latePayCharge = latePayCharge;
	}

	@Column(name = "PREV_ENDING_BAL", precision = 20, scale = 4)
	public Double getPrevEndingBal() {
		return this.prevEndingBal;
	}

	public void setPrevEndingBal(Double prevEndingBal) {
		this.prevEndingBal = prevEndingBal;
	}

	@Column(name = "INSURANCE_PAID_YTD", precision = 20, scale = 4)
	public Double getInsurancePaidYtd() {
		return this.insurancePaidYtd;
	}

	public void setInsurancePaidYtd(Double insurancePaidYtd) {
		this.insurancePaidYtd = insurancePaidYtd;
	}

	@Column(name = "ESCROW", precision = 20, scale = 4)
	public Double getEscrow() {
		return this.escrow;
	}

	public void setEscrow(Double escrow) {
		this.escrow = escrow;
	}

	@Column(name = "PROPERTY_TAX", precision = 20, scale = 4)
	public Double getPropertyTax() {
		return this.propertyTax;
	}

	public void setPropertyTax(Double propertyTax) {
		this.propertyTax = propertyTax;
	}

	@Column(name = "PROPERTY_TAX_YTD", precision = 20, scale = 4)
	public Double getPropertyTaxYtd() {
		return this.propertyTaxYtd;
	}

	public void setPropertyTaxYtd(Double propertyTaxYtd) {
		this.propertyTaxYtd = propertyTaxYtd;
	}

	@Column(name = "INSURANCE_ESCROW", precision = 20, scale = 4)
	public Double getInsuranceEscrow() {
		return this.insuranceEscrow;
	}

	public void setInsuranceEscrow(Double insuranceEscrow) {
		this.insuranceEscrow = insuranceEscrow;
	}

	@Column(name = "PROPERTY_TAX_ESCROW", precision = 20, scale = 4)
	public Double getPropertyTaxEscrow() {
		return this.propertyTaxEscrow;
	}

	public void setPropertyTaxEscrow(Double propertyTaxEscrow) {
		this.propertyTaxEscrow = propertyTaxEscrow;
	}

	@Column(name = "INT_PAID_YTD", precision = 20, scale = 4)
	public Double getIntPaidYtd() {
		return this.intPaidYtd;
	}

	public void setIntPaidYtd(Double intPaidYtd) {
		this.intPaidYtd = intPaidYtd;
	}

	@Column(name = "INSERTED_ON", nullable = false, length = 19)
	public Timestamp getInsertedOn() {
		return this.insertedOn;
	}

	public void setInsertedOn(Timestamp insertedOn) {
		this.insertedOn = insertedOn;
	}

	@Column(name = "INSERTED_BY", nullable = false)
	public Long getInsertedBy() {
		return this.insertedBy;
	}

	public void setInsertedBy(Long insertedBy) {
		this.insertedBy = insertedBy;
	}

}