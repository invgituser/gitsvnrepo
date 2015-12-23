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
 * YldLoanDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_loan_details", catalog = "invdb")
public class LoanDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private AccountDetail accountDetail;
	private String typeLoan;
	private String accName;
	private String description;
	private Double lastPayAmt;
	private Double princBal;
	private Double orgnlLoanAmt;
	private Double intPaidYtd;
	private Double intPaidLastYear;
	private Date lastPayDate;
	private Date matDate;
	private Date originationDate;
	private Double intRate;
	private Double payOffAmt;
	private Date payByDate;
	private Double availCredit;
	private Double creditLimit;
	private String accNum;
	private String accHolderFullName;
	private String longTerm;
	private String collateral;
	private Double amtDue;
	private Date dueDate;
	private Double escrowBal;
	private String guarantor;
	private Double recurringPay;
	private String vin;
	private String make;
	private String model;
	private String polYear;
	private String lienHolder;
	private String loaned;
	private String leased;
	private String drivingRecord;
	private String hasSr22;
	private String driverType;
	private String accClassification;
	private String accStatus;
	private Date firstPayDate;
	private String lender;
	private String paysRemaining;
	private String intRateType;
	private String frequency;
	private Double minPay;
	private String vehicle;
	private String driver;
	private String lienName;
	private String loanNum;
	private String phoneNum;
	private String address;
	private String propertyListId;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public LoanDetail() {
	}

	/** minimal constructor */
	public LoanDetail(Long id, AccountDetail accountDetail,
			Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
	}

	/** full constructor */
	public LoanDetail(Long id, AccountDetail accountDetail,
			String typeLoan, String accName, String description,
			Double lastPayAmt, Double princBal, Double orgnlLoanAmt,
			Double intPaidYtd, Double intPaidLastYear, Date lastPayDate,
			Date matDate, Date originationDate, Double intRate,
			Double payOffAmt, Date payByDate, Double availCredit,
			Double creditLimit, String accNum, String accHolderFullName,
			String longTerm, String collateral, Double amtDue, Date dueDate,
			Double escrowBal, String guarantor, Double recurringPay,
			String vin, String make, String model, String polYear,
			String lienHolder, String loaned, String leased,
			String drivingRecord, String hasSr22, String driverType,
			String accClassification, String accStatus, Date firstPayDate,
			String lender, String paysRemaining, String intRateType,
			String frequency, Double minPay, String vehicle, String driver,
			String lienName, String loanNum, String phoneNum, String address,
			String propertyListId, Timestamp insertedOn, Long insertedBy) {
		this.id = id;
		this.accountDetail = accountDetail;
		this.typeLoan = typeLoan;
		this.accName = accName;
		this.description = description;
		this.lastPayAmt = lastPayAmt;
		this.princBal = princBal;
		this.orgnlLoanAmt = orgnlLoanAmt;
		this.intPaidYtd = intPaidYtd;
		this.intPaidLastYear = intPaidLastYear;
		this.lastPayDate = lastPayDate;
		this.matDate = matDate;
		this.originationDate = originationDate;
		this.intRate = intRate;
		this.payOffAmt = payOffAmt;
		this.payByDate = payByDate;
		this.availCredit = availCredit;
		this.creditLimit = creditLimit;
		this.accNum = accNum;
		this.accHolderFullName = accHolderFullName;
		this.longTerm = longTerm;
		this.collateral = collateral;
		this.amtDue = amtDue;
		this.dueDate = dueDate;
		this.escrowBal = escrowBal;
		this.guarantor = guarantor;
		this.recurringPay = recurringPay;
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.polYear = polYear;
		this.lienHolder = lienHolder;
		this.loaned = loaned;
		this.leased = leased;
		this.drivingRecord = drivingRecord;
		this.hasSr22 = hasSr22;
		this.driverType = driverType;
		this.accClassification = accClassification;
		this.accStatus = accStatus;
		this.firstPayDate = firstPayDate;
		this.lender = lender;
		this.paysRemaining = paysRemaining;
		this.intRateType = intRateType;
		this.frequency = frequency;
		this.minPay = minPay;
		this.vehicle = vehicle;
		this.driver = driver;
		this.lienName = lienName;
		this.loanNum = loanNum;
		this.phoneNum = phoneNum;
		this.address = address;
		this.propertyListId = propertyListId;
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

	@Column(name = "TYPE_LOAN", length = 100)
	public String getTypeLoan() {
		return this.typeLoan;
	}

	public void setTypeLoan(String typeLoan) {
		this.typeLoan = typeLoan;
	}

	@Column(name = "ACC_NAME", length = 100)
	public String getAccName() {
		return this.accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	@Column(name = "DESCRIPTION", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "LAST_PAY_AMT", precision = 20, scale = 4)
	public Double getLastPayAmt() {
		return this.lastPayAmt;
	}

	public void setLastPayAmt(Double lastPayAmt) {
		this.lastPayAmt = lastPayAmt;
	}

	@Column(name = "PRINC_BAL", precision = 20, scale = 4)
	public Double getPrincBal() {
		return this.princBal;
	}

	public void setPrincBal(Double princBal) {
		this.princBal = princBal;
	}

	@Column(name = "ORGNL_LOAN_AMT", precision = 20, scale = 4)
	public Double getOrgnlLoanAmt() {
		return this.orgnlLoanAmt;
	}

	public void setOrgnlLoanAmt(Double orgnlLoanAmt) {
		this.orgnlLoanAmt = orgnlLoanAmt;
	}

	@Column(name = "INT_PAID_YTD", precision = 20, scale = 4)
	public Double getIntPaidYtd() {
		return this.intPaidYtd;
	}

	public void setIntPaidYtd(Double intPaidYtd) {
		this.intPaidYtd = intPaidYtd;
	}

	@Column(name = "INT_PAID_LAST_YEAR", precision = 20, scale = 4)
	public Double getIntPaidLastYear() {
		return this.intPaidLastYear;
	}

	public void setIntPaidLastYear(Double intPaidLastYear) {
		this.intPaidLastYear = intPaidLastYear;
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
	@Column(name = "MAT_DATE", length = 10)
	public Date getMatDate() {
		return this.matDate;
	}

	public void setMatDate(Date matDate) {
		this.matDate = matDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ORIGINATION_DATE", length = 10)
	public Date getOriginationDate() {
		return this.originationDate;
	}

	public void setOriginationDate(Date originationDate) {
		this.originationDate = originationDate;
	}

	@Column(name = "INT_RATE", precision = 22, scale = 0)
	public Double getIntRate() {
		return this.intRate;
	}

	public void setIntRate(Double intRate) {
		this.intRate = intRate;
	}

	@Column(name = "PAY_OFF_AMT", precision = 20, scale = 4)
	public Double getPayOffAmt() {
		return this.payOffAmt;
	}

	public void setPayOffAmt(Double payOffAmt) {
		this.payOffAmt = payOffAmt;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PAY_BY_DATE", length = 10)
	public Date getPayByDate() {
		return this.payByDate;
	}

	public void setPayByDate(Date payByDate) {
		this.payByDate = payByDate;
	}

	@Column(name = "AVAIL_CREDIT", precision = 20, scale = 4)
	public Double getAvailCredit() {
		return this.availCredit;
	}

	public void setAvailCredit(Double availCredit) {
		this.availCredit = availCredit;
	}

	@Column(name = "CREDIT_LIMIT", precision = 20, scale = 4)
	public Double getCreditLimit() {
		return this.creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Column(name = "ACC_NUM", length = 100)
	public String getAccNum() {
		return this.accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	@Column(name = "ACC_HOLDER_FULL_NAME", length = 100)
	public String getAccHolderFullName() {
		return this.accHolderFullName;
	}

	public void setAccHolderFullName(String accHolderFullName) {
		this.accHolderFullName = accHolderFullName;
	}

	@Column(name = "LONG_TERM", length = 100)
	public String getLongTerm() {
		return this.longTerm;
	}

	public void setLongTerm(String longTerm) {
		this.longTerm = longTerm;
	}

	@Column(name = "COLLATERAL", length = 100)
	public String getCollateral() {
		return this.collateral;
	}

	public void setCollateral(String collateral) {
		this.collateral = collateral;
	}

	@Column(name = "AMT_DUE", precision = 20, scale = 4)
	public Double getAmtDue() {
		return this.amtDue;
	}

	public void setAmtDue(Double amtDue) {
		this.amtDue = amtDue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DUE_DATE", length = 10)
	public Date getDueDate() {
		return this.dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	@Column(name = "ESCROW_BAL", precision = 20, scale = 4)
	public Double getEscrowBal() {
		return this.escrowBal;
	}

	public void setEscrowBal(Double escrowBal) {
		this.escrowBal = escrowBal;
	}

	@Column(name = "GUARANTOR", length = 100)
	public String getGuarantor() {
		return this.guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}

	@Column(name = "RECURRING_PAY", precision = 20, scale = 4)
	public Double getRecurringPay() {
		return this.recurringPay;
	}

	public void setRecurringPay(Double recurringPay) {
		this.recurringPay = recurringPay;
	}

	@Column(name = "VIN", length = 100)
	public String getVin() {
		return this.vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	@Column(name = "MAKE", length = 100)
	public String getMake() {
		return this.make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	@Column(name = "MODEL", length = 100)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "POL_YEAR", length = 100)
	public String getPolYear() {
		return this.polYear;
	}

	public void setPolYear(String polYear) {
		this.polYear = polYear;
	}

	@Column(name = "LIEN_HOLDER", length = 100)
	public String getLienHolder() {
		return this.lienHolder;
	}

	public void setLienHolder(String lienHolder) {
		this.lienHolder = lienHolder;
	}

	@Column(name = "LOANED", length = 100)
	public String getLoaned() {
		return this.loaned;
	}

	public void setLoaned(String loaned) {
		this.loaned = loaned;
	}

	@Column(name = "LEASED", length = 100)
	public String getLeased() {
		return this.leased;
	}

	public void setLeased(String leased) {
		this.leased = leased;
	}

	@Column(name = "DRIVING_RECORD", length = 100)
	public String getDrivingRecord() {
		return this.drivingRecord;
	}

	public void setDrivingRecord(String drivingRecord) {
		this.drivingRecord = drivingRecord;
	}

	@Column(name = "HAS_SR22", length = 100)
	public String getHasSr22() {
		return this.hasSr22;
	}

	public void setHasSr22(String hasSr22) {
		this.hasSr22 = hasSr22;
	}

	@Column(name = "DRIVER_TYPE", length = 100)
	public String getDriverType() {
		return this.driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	@Column(name = "ACC_CLASSIFICATION", length = 100)
	public String getAccClassification() {
		return this.accClassification;
	}

	public void setAccClassification(String accClassification) {
		this.accClassification = accClassification;
	}

	@Column(name = "ACC_STATUS", length = 100)
	public String getAccStatus() {
		return this.accStatus;
	}

	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FIRST_PAY_DATE", length = 10)
	public Date getFirstPayDate() {
		return this.firstPayDate;
	}

	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

	@Column(name = "LENDER", length = 100)
	public String getLender() {
		return this.lender;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}

	@Column(name = "PAYS_REMAINING", length = 100)
	public String getPaysRemaining() {
		return this.paysRemaining;
	}

	public void setPaysRemaining(String paysRemaining) {
		this.paysRemaining = paysRemaining;
	}

	@Column(name = "INT_RATE_TYPE", length = 100)
	public String getIntRateType() {
		return this.intRateType;
	}

	public void setIntRateType(String intRateType) {
		this.intRateType = intRateType;
	}

	@Column(name = "FREQUENCY", length = 100)
	public String getFrequency() {
		return this.frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Column(name = "MIN_PAY", precision = 20, scale = 4)
	public Double getMinPay() {
		return this.minPay;
	}

	public void setMinPay(Double minPay) {
		this.minPay = minPay;
	}

	@Column(name = "VEHICLE", length = 100)
	public String getVehicle() {
		return this.vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	@Column(name = "DRIVER", length = 100)
	public String getDriver() {
		return this.driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	@Column(name = "LIEN_NAME", length = 100)
	public String getLienName() {
		return this.lienName;
	}

	public void setLienName(String lienName) {
		this.lienName = lienName;
	}

	@Column(name = "LOAN_NUM", length = 100)
	public String getLoanNum() {
		return this.loanNum;
	}

	public void setLoanNum(String loanNum) {
		this.loanNum = loanNum;
	}

	@Column(name = "PHONE_NUM", length = 100)
	public String getPhoneNum() {
		return this.phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	@Column(name = "ADDRESS", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "PROPERTY_LIST_ID", length = 100)
	public String getPropertyListId() {
		return this.propertyListId;
	}

	public void setPropertyListId(String propertyListId) {
		this.propertyListId = propertyListId;
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

}