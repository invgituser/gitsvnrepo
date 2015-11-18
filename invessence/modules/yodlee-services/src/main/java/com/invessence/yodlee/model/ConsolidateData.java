package com.invessence.yodlee.model;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;

/**
 * YldConsolidateData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_consolidate_data", catalog = "invdb")
public class ConsolidateData implements java.io.Serializable {

	// Fields

	private Long id;
	private UserLogon userLogon;
	private ItemDetail itemDetail;
	private SiteDetail siteDetail;
	private AccountDetail accountDetail;
	private Long pfolioDetId;
	private String status;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;

	// Constructors

	/** default constructor */
	public ConsolidateData() {
	}

	/** minimal constructor */
	public ConsolidateData(Long id, UserLogon userLogon,
								  ItemDetail itemDetail, SiteDetail siteDetail,
								  AccountDetail accountDetail, Long pfolioDetId,
								  Timestamp insertedOn, Long insertedBy, Timestamp updatedOn) {
		this.id = id;
		this.userLogon = userLogon;
		this.itemDetail = itemDetail;
		this.siteDetail = siteDetail;
		this.accountDetail = accountDetail;
		this.pfolioDetId = pfolioDetId;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
	}

	/** full constructor */
	public ConsolidateData(Long id, UserLogon userLogon,
								  ItemDetail itemDetail, SiteDetail siteDetail,
								  AccountDetail accountDetail, Long pfolioDetId,
								  String status, Timestamp insertedOn, Long insertedBy,
								  Timestamp updatedOn, Long updatedBy) {
		this.id = id;
		this.userLogon = userLogon;
		this.itemDetail = itemDetail;
		this.siteDetail = siteDetail;
		this.accountDetail = accountDetail;
		this.pfolioDetId = pfolioDetId;
		this.status = status;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
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
	@JoinColumn(name = "USER_LOG_ID", nullable = false)
	public UserLogon getUserLogon() {
		return this.userLogon;
	}

	public void setUserLogon(UserLogon userLogon) {
		this.userLogon = userLogon;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ITEM_DET_ID", nullable = false)
	public ItemDetail getItemDetail() {
		return this.itemDetail;
	}

	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SITE_DET_ID", nullable = false)
	public SiteDetail getSiteDetail() {
		return this.siteDetail;
	}

	public void setSiteDetail(SiteDetail siteDetail) {
		this.siteDetail = siteDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACC_DET_ID", nullable = false)
	public AccountDetail getAccountDetail() {
		return this.accountDetail;
	}

	public void setAccountDetail(AccountDetail accountDetail) {
		this.accountDetail = accountDetail;
	}

	@Column(name = "PFOLIO_DET_ID", nullable = false)
	public Long getPfolioDetId() {
		return this.pfolioDetId;
	}

	public void setPfolioDetId(Long pfolioDetId) {
		this.pfolioDetId = pfolioDetId;
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

}