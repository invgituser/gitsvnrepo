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
 * YldLoanTransaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_loan_transactions", catalog = "yodleepoc")
public class LoanTransaction implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private Date postDate;
	private String description;
	private Double intrest;
	private Double amt;
	private String tranStatus;
	private Date tranDate;
	private Double princAmt;
	private String tranCatg;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public LoanTransaction() {
	}

	/** minimal constructor */
	public LoanTransaction(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public LoanTransaction(Long id, AccountDetail accountDetail,
			Date postDate, String description, Double intrest, Double amt,
			String tranStatus, Date tranDate, Double princAmt, String tranCatg,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.postDate = postDate;
		this.description = description;
		this.intrest = intrest;
		this.amt = amt;
		this.tranStatus = tranStatus;
		this.tranDate = tranDate;
		this.princAmt = princAmt;
		this.tranCatg = tranCatg;
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
	@Column(name = "POST_DATE", length = 10)
	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "INTREST", precision = 22, scale = 0)
	public Double getIntrest() {
		return this.intrest;
	}

	public void setIntrest(Double intrest) {
		this.intrest = intrest;
	}

	@Column(name = "AMT", precision = 20, scale = 4)
	public Double getAmt() {
		return this.amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Column(name = "TRAN_STATUS", length = 100)
	public String getTranStatus() {
		return this.tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAN_DATE", length = 10)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "PRINC_AMT", precision = 20, scale = 4)
	public Double getPrincAmt() {
		return this.princAmt;
	}

	public void setPrincAmt(Double princAmt) {
		this.princAmt = princAmt;
	}

	@Column(name = "TRAN_CATG", length = 100)
	public String getTranCatg() {
		return this.tranCatg;
	}

	public void setTranCatg(String tranCatg) {
		this.tranCatg = tranCatg;
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