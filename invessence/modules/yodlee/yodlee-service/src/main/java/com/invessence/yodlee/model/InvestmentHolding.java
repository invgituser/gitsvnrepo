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
 * YldInvestmentHolding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_investment_holdings", catalog = "yodleepoc")
public class InvestmentHolding implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String holdingType;
	private String symbol;
	private Long quantity;
	private Double price;
	private Double totValue;
	private Long cusipNum;
	private String description;
	private String mutualFundType;
	private Double couponRate;
	private Double couponFrequency;
	private Double strikePrice;
	private String espType;
	private Double percAlloc;
	private Double empContr;
	private Double costBasis;
	private Long term;
	private Double faceValue;
	private Double intRate;
	private Date matDate;
	private Date expirationDate;
	private String optionType;
	private Long lotSize;
	private String linkedBankAccNum;
	private Date priceAsOfDate;
	private String planName;
	private String planNum;
	private Double parValue;
	private String commodityType;
	private Double lastContr;
	private Timestamp insertedOn;
	private Long insertedBy;

	// Constructors

	/** default constructor */
	public InvestmentHolding() {
	}

	/** minimal constructor */
	public InvestmentHolding(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public InvestmentHolding(Long id, AccountDetail accountDetail,
			String holdingType, String symbol, Long quantity, Double price,
			Double totValue, Long cusipNum, String description,
			String mutualFundType, Double couponRate, Double couponFrequency,
			Double strikePrice, String espType, Double percAlloc,
			Double empContr, Double costBasis, Long term, Double faceValue,
			Double intRate, Date matDate, Date expirationDate,
			String optionType, Long lotSize, String linkedBankAccNum,
			Date priceAsOfDate, String planName, String planNum,
			Double parValue, String commodityType, Double lastContr,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.holdingType = holdingType;
		this.symbol = symbol;
		this.quantity = quantity;
		this.price = price;
		this.totValue = totValue;
		this.cusipNum = cusipNum;
		this.description = description;
		this.mutualFundType = mutualFundType;
		this.couponRate = couponRate;
		this.couponFrequency = couponFrequency;
		this.strikePrice = strikePrice;
		this.espType = espType;
		this.percAlloc = percAlloc;
		this.empContr = empContr;
		this.costBasis = costBasis;
		this.term = term;
		this.faceValue = faceValue;
		this.intRate = intRate;
		this.matDate = matDate;
		this.expirationDate = expirationDate;
		this.optionType = optionType;
		this.lotSize = lotSize;
		this.linkedBankAccNum = linkedBankAccNum;
		this.priceAsOfDate = priceAsOfDate;
		this.planName = planName;
		this.planNum = planNum;
		this.parValue = parValue;
		this.commodityType = commodityType;
		this.lastContr = lastContr;
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

	@Column(name = "HOLDING_TYPE", length = 100)
	public String getHoldingType() {
		return this.holdingType;
	}

	public void setHoldingType(String holdingType) {
		this.holdingType = holdingType;
	}

	@Column(name = "SYMBOL", length = 100)
	public String getSymbol() {
		return this.symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	@Column(name = "QUANTITY")
	public Long getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE", precision = 22, scale = 0)
	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "TOT_VALUE", precision = 20, scale = 4)
	public Double getTotValue() {
		return this.totValue;
	}

	public void setTotValue(Double totValue) {
		this.totValue = totValue;
	}

	@Column(name = "CUSIP_NUM")
	public Long getCusipNum() {
		return this.cusipNum;
	}

	public void setCusipNum(Long cusipNum) {
		this.cusipNum = cusipNum;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "MUTUAL_FUND_TYPE", length = 100)
	public String getMutualFundType() {
		return this.mutualFundType;
	}

	public void setMutualFundType(String mutualFundType) {
		this.mutualFundType = mutualFundType;
	}

	@Column(name = "COUPON_RATE", precision = 22, scale = 0)
	public Double getCouponRate() {
		return this.couponRate;
	}

	public void setCouponRate(Double couponRate) {
		this.couponRate = couponRate;
	}

	@Column(name = "COUPON_FREQUENCY", precision = 22, scale = 0)
	public Double getCouponFrequency() {
		return this.couponFrequency;
	}

	public void setCouponFrequency(Double couponFrequency) {
		this.couponFrequency = couponFrequency;
	}

	@Column(name = "STRIKE_PRICE", precision = 22, scale = 0)
	public Double getStrikePrice() {
		return this.strikePrice;
	}

	public void setStrikePrice(Double strikePrice) {
		this.strikePrice = strikePrice;
	}

	@Column(name = "ESP_TYPE", length = 100)
	public String getEspType() {
		return this.espType;
	}

	public void setEspType(String espType) {
		this.espType = espType;
	}

	@Column(name = "PERC_ALLOC", precision = 22, scale = 0)
	public Double getPercAlloc() {
		return this.percAlloc;
	}

	public void setPercAlloc(Double percAlloc) {
		this.percAlloc = percAlloc;
	}

	@Column(name = "EMP_CONTR", precision = 20, scale = 4)
	public Double getEmpContr() {
		return this.empContr;
	}

	public void setEmpContr(Double empContr) {
		this.empContr = empContr;
	}

	@Column(name = "COST_BASIS", precision = 20, scale = 4)
	public Double getCostBasis() {
		return this.costBasis;
	}

	public void setCostBasis(Double costBasis) {
		this.costBasis = costBasis;
	}

	@Column(name = "TERM")
	public Long getTerm() {
		return this.term;
	}

	public void setTerm(Long term) {
		this.term = term;
	}

	@Column(name = "FACE_VALUE", precision = 20, scale = 4)
	public Double getFaceValue() {
		return this.faceValue;
	}

	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}

	@Column(name = "INT_RATE", precision = 22, scale = 0)
	public Double getIntRate() {
		return this.intRate;
	}

	public void setIntRate(Double intRate) {
		this.intRate = intRate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "MAT_DATE", length = 10)
	public Date getMatDate() {
		return this.matDate;
	}

	public void setMatDate(Date matDate) {
		this.matDate = matDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRATION_DATE", length = 10)
	public Date getExpirationDate() {
		return this.expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Column(name = "OPTION_TYPE", length = 100)
	public String getOptionType() {
		return this.optionType;
	}

	public void setOptionType(String optionType) {
		this.optionType = optionType;
	}

	@Column(name = "LOT_SIZE")
	public Long getLotSize() {
		return this.lotSize;
	}

	public void setLotSize(Long lotSize) {
		this.lotSize = lotSize;
	}

	@Column(name = "LINKED_BANK_ACC_NUM", length = 100)
	public String getLinkedBankAccNum() {
		return this.linkedBankAccNum;
	}

	public void setLinkedBankAccNum(String linkedBankAccNum) {
		this.linkedBankAccNum = linkedBankAccNum;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PRICE_AS_OF_DATE", length = 10)
	public Date getPriceAsOfDate() {
		return this.priceAsOfDate;
	}

	public void setPriceAsOfDate(Date priceAsOfDate) {
		this.priceAsOfDate = priceAsOfDate;
	}

	@Column(name = "PLAN_NAME", length = 100)
	public String getPlanName() {
		return this.planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	@Column(name = "PLAN_NUM", length = 100)
	public String getPlanNum() {
		return this.planNum;
	}

	public void setPlanNum(String planNum) {
		this.planNum = planNum;
	}

	@Column(name = "PAR_VALUE", precision = 20, scale = 4)
	public Double getParValue() {
		return this.parValue;
	}

	public void setParValue(Double parValue) {
		this.parValue = parValue;
	}

	@Column(name = "COMMODITY_TYPE", length = 100)
	public String getCommodityType() {
		return this.commodityType;
	}

	public void setCommodityType(String commodityType) {
		this.commodityType = commodityType;
	}

	@Column(name = "LAST_CONTR", precision = 20, scale = 4)
	public Double getLastContr() {
		return this.lastContr;
	}

	public void setLastContr(Double lastContr) {
		this.lastContr = lastContr;
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