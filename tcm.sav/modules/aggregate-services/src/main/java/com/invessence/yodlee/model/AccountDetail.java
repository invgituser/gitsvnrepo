package com.invessence.yodlee.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * YdlAccountDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_account_details", catalog = "invdb")
public class AccountDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private ItemDetail itemDetail;
	private Long itemAccId;
	private Long accId;
	private String accName;
	private String status;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;
	private Set<CardTransaction> cardTransaction = new HashSet<CardTransaction>(
			0);
	private Set<LoanTransaction> loanTransaction = new HashSet<LoanTransaction>(
			0);
	private Set<LoanDetail> loanDetail = new HashSet<LoanDetail>(
			0);
	private Set<CardDetail> cardDetail = new HashSet<CardDetail>(
			0);
	private Set<CardStatement> cardStatement = new HashSet<CardStatement>(
			0);
	private Set<LoanHolding> loanHolding = new HashSet<LoanHolding>(
			0);
	private Set<InvestmentTransaction> investmentTransaction = new HashSet<InvestmentTransaction>(
			0);
	private Set<InvestmentDetail> investmentDetail = new HashSet<InvestmentDetail>(
			0);
	private Set<InvestmentHolding> investmentHolding = new HashSet<InvestmentHolding>(
			0);
	private Set<ConsolidateData> consolidateData = new HashSet<ConsolidateData>(
			0);
	private Set<BankDetail> bankDetail = new HashSet<BankDetail>(
			0);
	private Set<BankTransaction> bankTransaction = new HashSet<BankTransaction>(
			0);

	// Constructors

	/** default constructor */
	public AccountDetail() {
	}

	/** minimal constructor */
	public AccountDetail(Long id, ItemDetail itemDetail,
			Long itemAccId, Long accId, String accName, Timestamp insertedOn,
			Long insertedBy, Timestamp updatedOn) {
		this.id = id;
		this.itemDetail = itemDetail;
		this.itemAccId = itemAccId;
		this.accId = accId;
		this.accName = accName;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
	}

	/** full constructor */
	public AccountDetail(Long id, ItemDetail itemDetail,
			Long itemAccId, Long accId, String accName, String status,
			Timestamp insertedOn, Long insertedBy, Timestamp updatedOn,
			Long updatedBy, Set<CardTransaction> cardTransaction,
			Set<LoanTransaction> loanTransaction,
			Set<LoanDetail> loanDetail,
			Set<CardDetail> cardDetail,
			Set<CardStatement> cardStatement,
			Set<LoanHolding> loanHolding,
			Set<InvestmentTransaction> investmentTransaction,
			Set<InvestmentDetail> investmentDetail,
			Set<InvestmentHolding> investmentHolding,
			Set<ConsolidateData> consolidateData,
			Set<BankDetail> bankDetail,
			Set<BankTransaction> bankTransaction) {
		this.id = id;
		this.itemDetail = itemDetail;
		this.itemAccId = itemAccId;
		this.accId = accId;
		this.accName = accName;
		this.status = status;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.cardTransaction = cardTransaction;
		this.loanTransaction = loanTransaction;
		this.loanDetail = loanDetail;
		this.cardDetail = cardDetail;
		this.cardStatement = cardStatement;
		this.loanHolding = loanHolding;
		this.investmentTransaction = investmentTransaction;
		this.investmentDetail = investmentDetail;
		this.investmentHolding = investmentHolding;
		this.consolidateData = consolidateData;
		this.bankDetail = bankDetail;
		this.bankTransaction = bankTransaction;
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
	@JoinColumn(name = "ITEM_DET_ID", nullable = false)
	public ItemDetail getItemDetail() {
		return this.itemDetail;
	}

	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	@Column(name = "ITEM_ACC_ID", nullable = false)
	public Long getItemAccId() {
		return this.itemAccId;
	}

	public void setItemAccId(Long itemAccId) {
		this.itemAccId = itemAccId;
	}

	@Column(name = "ACC_ID", nullable = false)
	public Long getAccId() {
		return this.accId;
	}

	public void setAccId(Long accId) {
		this.accId = accId;
	}

	@Column(name = "ACC_NAME", nullable = false, length = 100)
	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	@Column(name = "STATUS", length = 10)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	@Column(name = "UPDATED_ON", length = 19)
	public Timestamp getUpdatedOn() {
		return this.updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	@Column(name = "UPDATED_BY")
	public Long getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<CardTransaction> getCardTransactiones() {
		return this.cardTransaction;
	}

	public void setCardTransactiones(
			Set<CardTransaction> cardTransaction) {
		this.cardTransaction = cardTransaction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<LoanTransaction> getLoanTransaction() {
		return this.loanTransaction;
	}

	public void setLoanTransaction(
			Set<LoanTransaction> loanTransaction) {
		this.loanTransaction = loanTransaction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<LoanDetail> getLoanDetails() {
		return this.loanDetail;
	}

	public void setLoanDetails(Set<LoanDetail> loanDetail) {
		this.loanDetail = loanDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<CardDetail> getCardDetails() {
		return this.cardDetail;
	}

	public void setCardDetails(Set<CardDetail> cardDetail) {
		this.cardDetail = cardDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<CardStatement> getCardStatements() {
		return this.cardStatement;
	}

	public void setCardStatements(Set<CardStatement> cardStatement) {
		this.cardStatement = cardStatement;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<LoanHolding> getLoanHoldinges() {
		return this.loanHolding;
	}

	public void setLoanHoldinges(Set<LoanHolding> loanHolding) {
		this.loanHolding = loanHolding;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<InvestmentTransaction> getInvestmentTransaction() {
		return this.investmentTransaction;
	}

	public void setInvestmentTransaction(
			Set<InvestmentTransaction> investmentTransaction) {
		this.investmentTransaction = investmentTransaction;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<InvestmentDetail> getInvestmentDetails() {
		return this.investmentDetail;
	}

	public void setInvestmentDetails(
			Set<InvestmentDetail> investmentDetail) {
		this.investmentDetail = investmentDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<InvestmentHolding> getInvestmentHoldinges() {
		return this.investmentHolding;
	}

	public void setInvestmentHoldinges(
			Set<InvestmentHolding> investmentHolding) {
		this.investmentHolding = investmentHolding;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<ConsolidateData> getConsolidateDatas() {
		return this.consolidateData;
	}

	public void setConsolidateDatas(
			Set<ConsolidateData> consolidateData) {
		this.consolidateData = consolidateData;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<BankDetail> getBankDetails() {
		return this.bankDetail;
	}

	public void setBankDetails(Set<BankDetail> bankDetail) {
		this.bankDetail = bankDetail;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "accountDetail")
	public Set<BankTransaction> getBankTransaction() {
		return this.bankTransaction;
	}

	public void setBankTransaction(
			Set<BankTransaction> bankTransaction) {
		this.bankTransaction = bankTransaction;
	}

}