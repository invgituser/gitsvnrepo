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
 * YdlBankDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_bank_details", catalog = "yodleepoc")
public class BankDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String accHolder;
	private String accType;
	private String accNum;
	private String accName;
	private Double avilbBal;
	private Date matDate;
	private Double curBal;
	private Double overdraftProtection;
	private Double preYearIntEarned;
	private Long routingNum;
	private Double intRate;
	private Date asOfDate;
	private Double matAmt;
	private String term;
	private Double intEarnedYtd;
	private String nomineeName;
	private String accNicknameSrcSite;
	private String accClassification;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public BankDetail() {
	}

	/** minimal constructor */
	public BankDetail(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public BankDetail(Long id, AccountDetail accountDetail,
			String accHolder, String accType, String accNum, String accName,
			Double avilbBal, Date matDate, Double curBal,
			Double overdraftProtection, Double preYearIntEarned,
			Long routingNum, Double intRate, Date asOfDate, Double matAmt,
			String term, Double intEarnedYtd, String nomineeName,
			String accNicknameSrcSite, String accClassification,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.accHolder = accHolder;
		this.accType = accType;
		this.accNum = accNum;
		this.accName = accName;
		this.avilbBal = avilbBal;
		this.matDate = matDate;
		this.curBal = curBal;
		this.overdraftProtection = overdraftProtection;
		this.preYearIntEarned = preYearIntEarned;
		this.routingNum = routingNum;
		this.intRate = intRate;
		this.asOfDate = asOfDate;
		this.matAmt = matAmt;
		this.term = term;
		this.intEarnedYtd = intEarnedYtd;
		this.nomineeName = nomineeName;
		this.accNicknameSrcSite = accNicknameSrcSite;
		this.accClassification = accClassification;
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

	@Column(name = "ACC_HOLDER", length = 100)
	public String getAccHolder() {
		return this.accHolder;
	}

	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}

	@Column(name = "ACC_TYPE", length = 100)
	public String getAccType() {
		return this.accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
	}

	@Column(name = "ACC_NUM", length = 100)
	public String getAccNum() {
		return this.accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	@Column(name = "ACC_NAME", length = 100)
	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	@Column(name = "AVILB_BAL", precision = 20, scale = 4)
	public Double getAvilbBal() {
		return this.avilbBal;
	}

	public void setAvilbBal(Double avilbBal) {
		this.avilbBal = avilbBal;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MAT_DATE", length = 10)
	public Date getMatDate() {
		return this.matDate;
	}

	public void setMatDate(Date matDate) {
		this.matDate = matDate;
	}

	@Column(name = "CUR_BAL", precision = 20, scale = 4)
	public Double getCurBal() {
		return this.curBal;
	}

	public void setCurBal(Double curBal) {
		this.curBal = curBal;
	}

	@Column(name = "OVERDRAFT_PROTECTION", precision = 20, scale = 4)
	public Double getOverdraftProtection() {
		return this.overdraftProtection;
	}

	public void setOverdraftProtection(Double overdraftProtection) {
		this.overdraftProtection = overdraftProtection;
	}

	@Column(name = "PRE_YEAR_INT_EARNED", precision = 20, scale = 4)
	public Double getPreYearIntEarned() {
		return this.preYearIntEarned;
	}

	public void setPreYearIntEarned(Double preYearIntEarned) {
		this.preYearIntEarned = preYearIntEarned;
	}

	@Column(name = "ROUTING_NUM")
	public Long getRoutingNum() {
		return this.routingNum;
	}

	public void setRoutingNum(Long routingNum) {
		this.routingNum = routingNum;
	}

	@Column(name = "INT_RATE", precision = 22, scale = 0)
	public Double getIntRate() {
		return this.intRate;
	}

	public void setIntRate(Double intRate) {
		this.intRate = intRate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "AS_OF_DATE", length = 10)
	public Date getAsOfDate() {
		return this.asOfDate;
	}

	public void setAsOfDate(Date asOfDate) {
		this.asOfDate = asOfDate;
	}

	@Column(name = "MAT_AMT", precision = 20, scale = 4)
	public Double getMatAmt() {
		return this.matAmt;
	}

	public void setMatAmt(Double matAmt) {
		this.matAmt = matAmt;
	}

	@Column(name = "TERM", length = 100)
	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	@Column(name = "INT_EARNED_YTD", precision = 20, scale = 4)
	public Double getIntEarnedYtd() {
		return this.intEarnedYtd;
	}

	public void setIntEarnedYtd(Double intEarnedYtd) {
		this.intEarnedYtd = intEarnedYtd;
	}

	@Column(name = "NOMINEE_NAME", length = 100)
	public String getNomineeName() {
		return this.nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	@Column(name = "ACC_NICKNAME_SRC_SITE", length = 100)
	public String getAccNicknameSrcSite() {
		return this.accNicknameSrcSite;
	}

	public void setAccNicknameSrcSite(String accNicknameSrcSite) {
		this.accNicknameSrcSite = accNicknameSrcSite;
	}

	@Column(name = "ACC_CLASSIFICATION", length = 100)
	public String getAccClassification() {
		return this.accClassification;
	}

	public void setAccClassification(String accClassification) {
		this.accClassification = accClassification;
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