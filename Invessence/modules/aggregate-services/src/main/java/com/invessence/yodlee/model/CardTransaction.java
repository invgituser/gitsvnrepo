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
 * YldCardTransaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_card_transactions", catalog = "invdb")
public class CardTransaction implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String description;
	private Date tranDate;
	private Date postDate;
	private Double amt;
	private String tranBaseType;
	private String tranStatus;
	private Long tranId;
	private String merchantCatgCode;
	private String tranType;
	private Date cardStatementDate;
	private Double runningBal;
	private Long cardAccId;
	private String srcTranType;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public CardTransaction() {
	}

	/** minimal constructor */
	public CardTransaction(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public CardTransaction(Long id, AccountDetail accountDetail,
			String description, Date tranDate, Date postDate, Double amt,
			String tranBaseType, String tranStatus, Long tranId,
			String merchantCatgCode, String tranType, Date cardStatementDate,
			Double runningBal, Long cardAccId, String srcTranType,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.description = description;
		this.tranDate = tranDate;
		this.postDate = postDate;
		this.amt = amt;
		this.tranBaseType = tranBaseType;
		this.tranStatus = tranStatus;
		this.tranId = tranId;
		this.merchantCatgCode = merchantCatgCode;
		this.tranType = tranType;
		this.cardStatementDate = cardStatementDate;
		this.runningBal = runningBal;
		this.cardAccId = cardAccId;
		this.srcTranType = srcTranType;
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
	@Column(name = "TRAN_DATE", length = 10)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "POST_DATE", length = 10)
	public Date getPostDate() {
		return this.postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	@Column(name = "AMT", precision = 20, scale = 4)
	public Double getAmt() {
		return this.amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
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

	@Column(name = "MERCHANT_CATG_CODE", length = 100)
	public String getMerchantCatgCode() {
		return this.merchantCatgCode;
	}

	public void setMerchantCatgCode(String merchantCatgCode) {
		this.merchantCatgCode = merchantCatgCode;
	}

	@Column(name = "TRAN_TYPE", length = 100)
	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CARD_STATEMENT_DATE", length = 10)
	public Date getCardStatementDate() {
		return this.cardStatementDate;
	}

	public void setCardStatementDate(Date cardStatementDate) {
		this.cardStatementDate = cardStatementDate;
	}

	@Column(name = "RUNNING_BAL", precision = 20, scale = 4)
	public Double getRunningBal() {
		return this.runningBal;
	}

	public void setRunningBal(Double runningBal) {
		this.runningBal = runningBal;
	}

	@Column(name = "CARD_ACC_ID")
	public Long getCardAccId() {
		return this.cardAccId;
	}

	public void setCardAccId(Long cardAccId) {
		this.cardAccId = cardAccId;
	}

	@Column(name = "SRC_TRAN_TYPE", length = 100)
	public String getSrcTranType() {
		return this.srcTranType;
	}

	public void setSrcTranType(String srcTranType) {
		this.srcTranType = srcTranType;
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