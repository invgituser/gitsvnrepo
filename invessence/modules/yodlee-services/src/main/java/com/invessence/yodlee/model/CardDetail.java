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
 * YldCardDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_card_details", catalog = "invdb")
public class CardDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String accHolder;
	private String accType;
	private String accNum;
	private String accName;
	private String brandName;
	private String accClassification;
	private Date lastPayDate;
	private Date dueDate;
	private Double amtDue;
	private Double minPay;
	private Double lastPay;
	private Double runningBal;
	private Double availCredit;
	private Double availCash;
	private Double totCreditLine;
	private Double totCashLimit;
	private Long apr;
	private Long cashApr;
	private String secAccHolderName;
	private Double newCharges;
	private Double pays;
	private String aprType;
	private String cashAprType;
	private Long balTransferApr;
	private Long balTransfer;
	private String payDetail;
	private Date accOpenDate;
	private Double intPaidThisPrd;
	private Double intPaidytd;
	private String accNicknameAtSrcSite;
	private String cardType;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public CardDetail() {
	}

	/** minimal constructor */
	public CardDetail(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public CardDetail(Long id, AccountDetail accountDetail,
			String accHolder, String accType, String accNum, String accName,
			String brandName, String accClassification, Date lastPayDate,
			Date dueDate, Double amtDue, Double minPay, Double lastPay,
			Double runningBal, Double availCredit, Double availCash,
			Double totCreditLine, Double totCashLimit, Long apr, Long cashApr,
			String secAccHolderName, Double newCharges, Double pays,
			String aprType, String cashAprType, Long balTransferApr,
			Long balTransfer, String payDetail, Date accOpenDate,
			Double intPaidThisPrd, Double intPaidytd,
			String accNicknameAtSrcSite, String cardType, Timestamp insertedOn,
			Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.accHolder = accHolder;
		this.accType = accType;
		this.accNum = accNum;
		this.accName = accName;
		this.brandName = brandName;
		this.accClassification = accClassification;
		this.lastPayDate = lastPayDate;
		this.dueDate = dueDate;
		this.amtDue = amtDue;
		this.minPay = minPay;
		this.lastPay = lastPay;
		this.runningBal = runningBal;
		this.availCredit = availCredit;
		this.availCash = availCash;
		this.totCreditLine = totCreditLine;
		this.totCashLimit = totCashLimit;
		this.apr = apr;
		this.cashApr = cashApr;
		this.secAccHolderName = secAccHolderName;
		this.newCharges = newCharges;
		this.pays = pays;
		this.aprType = aprType;
		this.cashAprType = cashAprType;
		this.balTransferApr = balTransferApr;
		this.balTransfer = balTransfer;
		this.payDetail = payDetail;
		this.accOpenDate = accOpenDate;
		this.intPaidThisPrd = intPaidThisPrd;
		this.intPaidytd = intPaidytd;
		this.accNicknameAtSrcSite = accNicknameAtSrcSite;
		this.cardType = cardType;
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

	@Column(name = "BRAND_NAME", length = 100)
	public String getBrandName() {
		return this.brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Column(name = "ACC_CLASSIFICATION", length = 100)
	public String getAccClassification() {
		return this.accClassification;
	}

	public void setAccClassification(String accClassification) {
		this.accClassification = accClassification;
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
	@Column(name = "DUE_DATE", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "AMT_DUE", precision = 20, scale = 4)
	public Double getAmtDue() {
		return this.amtDue;
	}

	public void setAmtDue(Double amtDue) {
		this.amtDue = amtDue;
	}

	@Column(name = "MIN_PAY", precision = 20, scale = 4)
	public Double getMinPay() {
		return this.minPay;
	}

	public void setMinPay(Double minPay) {
		this.minPay = minPay;
	}

	@Column(name = "LAST_PAY", precision = 20, scale = 4)
	public Double getLastPay() {
		return this.lastPay;
	}

	public void setLastPay(Double lastPay) {
		this.lastPay = lastPay;
	}

	@Column(name = "RUNNING_BAL", precision = 20, scale = 4)
	public Double getRunningBal() {
		return this.runningBal;
	}

	public void setRunningBal(Double runningBal) {
		this.runningBal = runningBal;
	}

	@Column(name = "AVAIL_CREDIT", precision = 20, scale = 4)
	public Double getAvailCredit() {
		return this.availCredit;
	}

	public void setAvailCredit(Double availCredit) {
		this.availCredit = availCredit;
	}

	@Column(name = "AVAIL_CASH", precision = 20, scale = 4)
	public Double getAvailCash() {
		return this.availCash;
	}

	public void setAvailCash(Double availCash) {
		this.availCash = availCash;
	}

	@Column(name = "TOT_CREDIT_LINE", precision = 20, scale = 4)
	public Double getTotCreditLine() {
		return this.totCreditLine;
	}

	public void setTotCreditLine(Double totCreditLine) {
		this.totCreditLine = totCreditLine;
	}

	@Column(name = "TOT_CASH_LIMIT", precision = 20, scale = 4)
	public Double getTotCashLimit() {
		return this.totCashLimit;
	}

	public void setTotCashLimit(Double totCashLimit) {
		this.totCashLimit = totCashLimit;
	}

	@Column(name = "APR")
	public Long getApr() {
		return this.apr;
	}

	public void setApr(Long apr) {
		this.apr = apr;
	}

	@Column(name = "CASH_APR")
	public Long getCashApr() {
		return this.cashApr;
	}

	public void setCashApr(Long cashApr) {
		this.cashApr = cashApr;
	}

	@Column(name = "SEC_ACC_HOLDER_NAME", length = 100)
	public String getSecAccHolderName() {
		return this.secAccHolderName;
	}

	public void setSecAccHolderName(String secAccHolderName) {
		this.secAccHolderName = secAccHolderName;
	}

	@Column(name = "NEW_CHARGES", precision = 20, scale = 4)
	public Double getNewCharges() {
		return this.newCharges;
	}

	public void setNewCharges(Double newCharges) {
		this.newCharges = newCharges;
	}

	@Column(name = "PAYS", precision = 20, scale = 4)
	public Double getPays() {
		return this.pays;
	}

	public void setPays(Double pays) {
		this.pays = pays;
	}

	@Column(name = "APR_TYPE", length = 100)
	public String getAprType() {
		return this.aprType;
	}

	public void setAprType(String aprType) {
		this.aprType = aprType;
	}

	@Column(name = "CASH_APR_TYPE", length = 100)
	public String getCashAprType() {
		return this.cashAprType;
	}

	public void setCashAprType(String cashAprType) {
		this.cashAprType = cashAprType;
	}

	@Column(name = "BAL_TRANSFER_APR")
	public Long getBalTransferApr() {
		return this.balTransferApr;
	}

	public void setBalTransferApr(Long balTransferApr) {
		this.balTransferApr = balTransferApr;
	}

	@Column(name = "BAL_TRANSFER")
	public Long getBalTransfer() {
		return this.balTransfer;
	}

	public void setBalTransfer(Long balTransfer) {
		this.balTransfer = balTransfer;
	}

	@Column(name = "PAY_DETAIL", length = 100)
	public String getPayDetail() {
		return this.payDetail;
	}

	public void setPayDetail(String payDetail) {
		this.payDetail = payDetail;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ACC_OPEN_DATE", length = 10)
	public Date getAccOpenDate() {
		return this.accOpenDate;
	}

	public void setAccOpenDate(Date accOpenDate) {
		this.accOpenDate = accOpenDate;
	}

	@Column(name = "INT_PAID_THIS_PRD", precision = 20, scale = 4)
	public Double getIntPaidThisPrd() {
		return this.intPaidThisPrd;
	}

	public void setIntPaidThisPrd(Double intPaidThisPrd) {
		this.intPaidThisPrd = intPaidThisPrd;
	}

	@Column(name = "INT_PAIDYTD", precision = 20, scale = 4)
	public Double getIntPaidytd() {
		return this.intPaidytd;
	}

	public void setIntPaidytd(Double intPaidytd) {
		this.intPaidytd = intPaidytd;
	}

	@Column(name = "ACC_NICKNAME_AT_SRC_SITE", length = 100)
	public String getAccNicknameAtSrcSite() {
		return this.accNicknameAtSrcSite;
	}

	public void setAccNicknameAtSrcSite(String accNicknameAtSrcSite) {
		this.accNicknameAtSrcSite = accNicknameAtSrcSite;
	}

	@Column(name = "CARD_TYPE", length = 100)
	public String getCardType() {
		return this.cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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