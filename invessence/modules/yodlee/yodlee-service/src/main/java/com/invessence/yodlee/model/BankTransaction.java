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
 * YdlBankTransaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_bank_transactions", catalog = "yodleepoc")
public class BankTransaction implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String description;
	private Date postDate;
	private Double tranAmt;
	private String tranBaseType;
	private String tranStatus;
	private Long tranId;
	private Date tranDate;
	private Double runningBal;
	private Long postedOrder;
	private String merchantCatgCode;
	private String consumerDesc;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public BankTransaction() {
	}

	/** minimal constructor */
	public BankTransaction(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public BankTransaction(Long id, AccountDetail accountDetail,
			String description, Date postDate, Double tranAmt,
			String tranBaseType, String tranStatus, Long tranId, Date tranDate,
			Double runningBal, Long postedOrder, String merchantCatgCode,
			String consumerDesc, Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.description = description;
		this.postDate = postDate;
		this.tranAmt = tranAmt;
		this.tranBaseType = tranBaseType;
		this.tranStatus = tranStatus;
		this.tranId = tranId;
		this.tranDate = tranDate;
		this.runningBal = runningBal;
		this.postedOrder = postedOrder;
		this.merchantCatgCode = merchantCatgCode;
		this.consumerDesc = consumerDesc;
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

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "POST_DATE", length = 10)
	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Column(name = "TRAN_AMT", precision = 20, scale = 4)
	public Double getTranAmt() {
		return this.tranAmt;
	}

	public void setTranAmt(Double tranAmt) {
		this.tranAmt = tranAmt;
	}

	@Column(name = "TRAN_BASE_TYPE", length = 100)
	public String getTranBaseType() {
		return this.tranBaseType;
	}

	public void setTranBaseType(String tranBaseType) {
		this.tranBaseType = tranBaseType;
	}

	@Column(name = "TRAN_STATUS", length = 100)
	public String getTranStatus() {
		return this.tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	@Column(name = "TRAN_ID")
	public Long getTranId() {
		return this.tranId;
	}

	public void setTranId(Long tranId) {
		this.tranId = tranId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "TRAN_DATE", length = 10)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "RUNNING_BAL", precision = 20, scale = 4)
	public Double getRunningBal() {
		return this.runningBal;
	}

	public void setRunningBal(Double runningBal) {
		this.runningBal = runningBal;
	}

	@Column(name = "POSTED_ORDER")
	public Long getPostedOrder() {
		return this.postedOrder;
	}

	public void setPostedOrder(Long postedOrder) {
		this.postedOrder = postedOrder;
	}

	@Column(name = "MERCHANT_CATG_CODE", length = 100)
	public String getMerchantCatgCode() {
		return this.merchantCatgCode;
	}

	public void setMerchantCatgCode(String merchantCatgCode) {
		this.merchantCatgCode = merchantCatgCode;
	}

	@Column(name = "CONSUMER_DESC", length = 100)
	public String getConsumerDesc() {
		return this.consumerDesc;
	}

	public void setConsumerDesc(String consumerDesc) {
		this.consumerDesc = consumerDesc;
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