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
 * YdlSiteDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ydl_site_details", catalog = "invdb")
public class SiteDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private UserLogon userLogon;
	private Long siteAccId;
	private Long siteId;
	private String siteName;
	private Long orgId;
	private String refreshMode;
	private String status;
	private Timestamp insertedOn;
	private Long insertedBy;
	private Timestamp updatedOn;
	private Long updatedBy;
	private Set<ItemDetail> itemDetails = new HashSet<ItemDetail>(
			0);
	private Set<ConsolidateData> consolidateData = new HashSet<ConsolidateData>(
			0);

	// Constructors

	/** default constructor */
	public SiteDetail() {
	}

	/** minimal constructor */
	public SiteDetail(Long id, UserLogon userLogon, Long siteAccId,
			Long siteId, String siteName, Long orgId, Timestamp insertedOn,
			Long insertedBy, Timestamp updatedOn) {
		this.id = id;
		this.userLogon = userLogon;
		this.siteAccId = siteAccId;
		this.siteId = siteId;
		this.siteName = siteName;
		this.orgId = orgId;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
	}

	/** full constructor */
	public SiteDetail(Long id, UserLogon userLogon, Long siteAccId,
			Long siteId, String siteName, Long orgId, String refreshMode,
			String status, Timestamp insertedOn, Long insertedBy,
			Timestamp updatedOn, Long updatedBy,
			Set<ItemDetail> itemDetails,
			Set<ConsolidateData> consolidateData) {
		this.id = id;
		this.userLogon = userLogon;
		this.siteAccId = siteAccId;
		this.siteId = siteId;
		this.siteName = siteName;
		this.orgId = orgId;
		this.refreshMode = refreshMode;
		this.status = status;
		this.insertedOn = insertedOn;
		this.insertedBy = insertedBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.itemDetails = itemDetails;
		this.consolidateData = consolidateData;
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

	@Column(name = "SITE_ACC_ID", nullable = false)
	public Long getSiteAccId() {
		return this.siteAccId;
	}

	public void setSiteAccId(Long siteAccId) {
		this.siteAccId = siteAccId;
	}

	@Column(name = "SITE_ID", nullable = false)
	public Long getSiteId() {
		return this.siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
	}

	@Column(name = "SITE_NAME", nullable = false, length = 100)
	public String getSiteName() {
		return this.siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	@Column(name = "ORG_ID", nullable = false)
	public Long getOrgId() {
		return this.orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	@Column(name = "REFRESH_MODE", length = 10)
	public String getRefreshMode() {
		return this.refreshMode;
	}

	public void setRefreshMode(String refreshMode) {
		this.refreshMode = refreshMode;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "siteDetail")
	public Set<ItemDetail> getItemDetails() {
		return this.itemDetails;
	}

	public void setItemDetails(Set<ItemDetail> itemDetails) {
		this.itemDetails = itemDetails;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "siteDetail")
	public Set<ConsolidateData> getConsolidateDatas() {
		return this.consolidateData;
	}

	public void setConsolidateDatas(
			Set<ConsolidateData> consolidateData) {
		this.consolidateData = consolidateData;
	}

}