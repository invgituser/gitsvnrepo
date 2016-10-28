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
 * YldInvestmentTransaction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_investment_transactions", catalog = "invdb")
public class InvestmentTransaction implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private Date tranDate;
	private String description;
	private Double secFee;
	private String commission;
	private Double amt;
	private String tranType;
	private String tranBaseType;
	private String symbol;
	private Double quantity;
	private Double price;
	private Date settleDate;
	private String cusipNum;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public InvestmentTransaction() {
	}

	/** minimal constructor */
	public InvestmentTransaction(Long id,
			AccountDetail accountDetail, Timestamp insertedOn,
			Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public InvestmentTransaction(Long id,
			AccountDetail accountDetail, Date tranDate,
			String description, Double secFee, String commission, Double amt,
			String tranType, String tranBaseType, String symbol,
			Double quantity, Double price, Date settleDate, String cusipNum,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.tranDate = tranDate;
		this.description = description;
		this.secFee = secFee;
		this.commission = commission;
		this.amt = amt;
		this.tranType = tranType;
		this.tranBaseType = tranBaseType;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		this.settleDate = settleDate;
		this.cusipNum = cusipNum;
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
	@Column(name = "TRAN_DATE", length = 10)
	public Date getTranDate() {
		return this.tranDate;
	}

	public void setTranDate(Date tranDate) {
		this.tranDate = tranDate;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "SEC_FEE", precision = 20, scale = 4)
	public Double getSecFee() {
		return this.secFee;
	}

	public void setSecFee(Double secFee) {
		this.secFee = secFee;
	}

	@Column(name = "COMMISSION", length = 100)
	public String getCommission() {
		return this.commission;
	}

	public void setCommission(String commission) {
		this.commission = commission;
	}

	@Column(name = "AMT", precision = 20, scale = 4)
	public Double getAmt() {
		return this.amt;
	}

	public void setAmt(Double amt) {
		this.amt = amt;
	}

	@Column(name = "TRAN_TYPE", length = 100)
	public String getTranType() {
		return this.tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	@Column(name = "TRAN_BASE_TYPE", length = 100)
	public String getTranBaseType() {
		return this.tranBaseType;
	}

	public void setTranBaseType(String tranBaseType) {
		this.tranBaseType = tranBaseType;
	}

	@Column(name = "SYMBOL", length = 100)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "QUANTITY", precision = 22, scale = 0)
	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE", precision = 20, scale = 4)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "SETTLE_DATE", length = 10)
	public Date getSettleDate() {
		return this.settleDate;
	}

	public void setSettleDate(Date settleDate) {
		this.settleDate = settleDate;
	}

	@Column(name = "CUSIP_NUM", length = 100)
	public String getCusipNum() {
		return this.cusipNum;
	}

	public void setCusipNum(String cusipNum) {
		this.cusipNum = cusipNum;
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